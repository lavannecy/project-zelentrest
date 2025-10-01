import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private static final List<Order> orders = new ArrayList<>();

    public static List<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }
}
