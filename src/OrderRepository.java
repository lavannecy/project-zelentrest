import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return new ArrayList<>(orders); // Повертаємо копію
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }
}
