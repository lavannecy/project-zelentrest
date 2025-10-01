import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainApp {

    public static void main(String[] args) {
        // Рекомендовано для програм Swing: запустити графічний інтерфейс у потоці диспетчеризації подій (EDT)
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // 1. Створення головного вікна
        JFrame frame = new JFrame("Система управління замовленнями");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Створення екземпляру вашого класу GUI
        // Припускаємо, що всі допоміжні класи (Order, CalculatePrice, OrderDAO, MasterPanel тощо)
        // або знаходяться в окремих файлах і є public, або знаходяться у DispatcherApp.java (як у Варіанті 1).

        DispatcherApp dispatcherApp = new DispatcherApp();

        // Створення MasterPanel для імітації залежності (якщо потрібно)
        MasterPanel masterPanel = new MasterPanel();
        dispatcherApp.setMasterPanel(masterPanel);

        // 3. Додавання панелі до вікна
        frame.add(dispatcherApp);

        // 4. Налаштування та відображення вікна
        frame.pack(); // Підбирає розмір вікна під вміст
        frame.setLocationRelativeTo(null); // Вікно по центру екрана
        frame.setVisible(true);
    }
}
