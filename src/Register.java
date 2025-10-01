public class Register {
    private Order o;
    private final ServiceList sl;
    private final MaterialList ml;
    private final AddressList al;
    private final CalculateTime timeCalculator;
    private final CalculatePrice priceCalculator;
    private final OrderRepository orderRepository; // Використовуємо репозиторій

    public Register(ServiceList sl, MaterialList ml, AddressList al,
                    CalculateTime timeCalculator, CalculatePrice priceCalculator,
                    OrderRepository orderRepository) {
        this.sl = sl;
        this.ml = ml;
        this.al = al;
        this.timeCalculator = timeCalculator;
        this.priceCalculator = priceCalculator;
        this.orderRepository = orderRepository;
    }

    public void newOrder() {
        this.o = new Order();
        orderRepository.addOrder(o);
    }

    // Валідація тепер проводиться на рівні контролера, використовуючи списки
    public boolean isServiceValid(String service) {
        return sl.contains(service);
    }

    public boolean isMaterialValid(String material) {
        return ml.contains(material);
    }

    public boolean isAddressServiced(String address) {
        return al.contains(address);
    }

    public double getEstimatedTime() {
        // Використовуємо інжектовану стратегію
        return o.calculateDuration(timeCalculator);
    }

    public double makePayment(double payment) {
        // Використовуємо інжектовану стратегію
        return o.processPayment(payment, priceCalculator);
    }

    public String generateContract() {
        return o.generateContract();
    }

    public void setService(String service) { o.setService(service); }
    public void setAddress(String address) { o.setAddress(address); }
    public void setSquare(double square) { o.setSquare(square); }
    public Order getOrder() { return o; }
}