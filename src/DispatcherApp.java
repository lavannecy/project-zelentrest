import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// ----------------------------------------------------------------------
// ДОПОМІЖНІ КЛАСИ (Пакетно-приватні: без модифікатора public)
// ----------------------------------------------------------------------



class ReferenceDataDAO {
    public ArrayList<String> getAllAddresses() { return new AddressList().getAllAddresses(); }
    public ArrayList<String> getAllServices() { return new ServiceList().getAllServices(); }
}


class OrderDAO {
    public void saveOrderToDB(Order order) {
        System.out.println("DEBUG: Замовлення збережено в БД (імітація): " + order.toString());
    }
}

class MasterPanel {
    public void refreshOrders() {}
    public void refreshOrderSelector() {}
}


// ----------------------------------------------------------------------
// ОСНОВНИЙ ПУБЛІЧНИЙ КЛАС: DispatcherApp
// ----------------------------------------------------------------------

public class DispatcherApp extends JPanel {
    private final JTextField squareField;
    private final JComboBox<String> addressBox;
    private final JComboBox<String> serviceBox;
    private final ArrayList<Order> orderList = (ArrayList<Order>) OrderList.getOrders();
    private MasterPanel masterPanel;

    private final CalculateTime timeCalculator = new CalculateTime();
    private final CalculatePrice priceCalculator = new CalculatePrice();


    public void setMasterPanel(MasterPanel masterPanel) {
        this.masterPanel = masterPanel;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public DispatcherApp() {
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Площа (м²):"));
        squareField = new JTextField();
        add(squareField);

        ReferenceDataDAO refDAO = new ReferenceDataDAO();

        add(new JLabel("Адреса:"));
        ArrayList<String> addressList = refDAO.getAllAddresses();
        if (addressList.isEmpty()) addressList.add("Немає адрес");
        addressBox = new JComboBox<>(addressList.toArray(new String[0]));
        add(addressBox);

        add(new JLabel("Послуга:"));
        ArrayList<String> serviceList = refDAO.getAllServices();
        if (serviceList.isEmpty()) serviceList.add("Немає послуг");
        serviceBox = new JComboBox<>(serviceList.toArray(new String[0]));
        add(serviceBox);

        JButton timeButton = new JButton("Розрахувати час");
        timeButton.addActionListener(e -> calculateTime());
        add(timeButton);

        JButton priceButton = new JButton("Розрахувати ціну");
        priceButton.addActionListener(e -> calculatePrice());
        add(priceButton);

        JButton saveButton = new JButton("Зберегти замовлення");
        saveButton.addActionListener(e -> saveOrder());
        add(saveButton);

        JButton showOrdersButton = new JButton("Показати усі замовлення");
        showOrdersButton.addActionListener(e -> showAllOrders());
        add(showOrdersButton);
    }

    private void calculateTime() {
        try {
            double square = Double.parseDouble(squareField.getText());
            String service = (String) serviceBox.getSelectedItem();

            double duration = timeCalculator.estimateDuration(service, square);

            JOptionPane.showMessageDialog(this, "Орієнтовна тривалість: " + duration + " днів");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введіть правильну площу.");
        }
    }

    private void calculatePrice() {
        try {
            double square = Double.parseDouble(squareField.getText());
            String service = (String) serviceBox.getSelectedItem();

            double price = priceCalculator.estimatePrice(service, square);

            JOptionPane.showMessageDialog(this, "Орієнтовна ціна: " + price + " грн");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введіть правильну площу.");
        }
    }

    private void saveOrder() {
        try {
            double square = Double.parseDouble(squareField.getText());
            String service = (String) serviceBox.getSelectedItem();
            String address = (String) addressBox.getSelectedItem();

            double price = priceCalculator.estimatePrice(service, square);
            double duration = timeCalculator.estimateDuration(service, square);

            Order order = new Order();
            order.setService(service);
            order.setAddress(address);
            order.setSquare(square);
            order.setPrice(price);
            order.setEstimatedTime(duration);

            OrderList.addOrder(order);

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.saveOrderToDB(order);

            if (masterPanel != null) {
                masterPanel.refreshOrders();
                masterPanel.refreshOrderSelector();
            }

            JOptionPane.showMessageDialog(this, "Замовлення збережено!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введіть правильну площу.");
        }
    }

    private void showAllOrders() {
        if (orderList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Немає збережених замовлень.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < orderList.size(); i++) {
                sb.append((i + 1)).append(". ").append(orderList.get(i)).append("\n");
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Список замовлень", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
