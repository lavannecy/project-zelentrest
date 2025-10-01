import java.util.ArrayList;
import java.util.List;
import java.util.Date; // Припускаючи, що клас Date імпортується

public class Service {
    private List<String> bookedSlots = new ArrayList<>();
    private List<String> services = new ArrayList<>();
    private String serviceType;
    private double basePrice;

    public List<String> findAvailableTime(List<String> allTimeSlots) {
        List<String> available = new ArrayList<>(allTimeSlots);
        available.removeAll(bookedSlots);
        return available;
    }

    public void chooseServiceType(String type) {
        this.serviceType = type;
    }

    public void removeService(String service) {
        services.remove(service);
    }

    public void addService(String service) {
        services.add(service);
    }

    public void selectTimeForService(Date timeSlot) {
        bookedSlots.add(timeSlot.toString());
    }
}
