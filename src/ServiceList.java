import java.util.ArrayList;

public class ServiceList {
    private final ArrayList<String> services;

    public ServiceList() {
        services = new ArrayList<>();
        services.add("Прибирання");
        services.add("Обслуговування газону");
        services.add("Посипання піском");
        services.add("Обрізка кущів"); // Додано для відповідності DispatcherApp
    }

    public boolean contains(String service) {
        return services.contains(service);
    }

    public ArrayList<String> getAllServices() {
        return new ArrayList<>(services); // Повертаємо копію для безпеки
    }
}
