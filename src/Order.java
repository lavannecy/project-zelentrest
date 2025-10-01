public class Order {
    private String serviceType;
    private String address;
    private double square;
    private double duration;
    private double price;
    private boolean isConfirmed = false;
    private double estimatedTime;
    private String status = "До виконання";

    // Конструктор за замовчуванням

    // --- Геттери та Сеттери ---

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void setEstimatedTime(double estimatedTime) { this.estimatedTime = estimatedTime; }
    public double getEstimatedTime() { return estimatedTime; }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return price; }

    public void setService(String serviceType) { this.serviceType = serviceType; }
    public String getService() { return serviceType; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return address; }

    public void setSquare(double square) { this.square = square; }
    public double getSquare() { return square; }

    // --- БІЗНЕС-ЛОГІКА (Викликається з контролера/сервісу) ---

    // Ці методи переміщені з Order у Register (або Service/Validator)
    // public boolean isService(String service, ServiceList sl) { ... }
    // public double getEstimatedTime(CalculateTime calculator) { ... }

    /** Розрахунок часу та встановлення результату */
    public double calculateDuration(CalculateTime calculator) {
        this.duration = calculator.estimateDuration(serviceType, square);
        this.estimatedTime = this.duration; // Використовуємо duration як estimatedTime
        return this.duration;
    }

    /** Розрахунок ціни та перевірка оплати */
    public double processPayment(double payment, CalculatePrice calculator) {
        this.price = calculator.estimatePrice(serviceType, square);
        return payment >= this.price ? this.price : -1;
    }

    public String generateContract() {
        if (serviceType == null || address == null || square <= 0) {
            return "Не вдалося створити контракт: неповні дані.";
        }
        isConfirmed = true;
        return "Контракт сформовано: " + serviceType + ", " + address + ", площа: " + square + " м².";
    }

    @Override
    public String toString() {
        return "Послуга: " + serviceType + ", Адреса: " + address + ", Площа: " + square + ", Статус: " + status;
    }
}
