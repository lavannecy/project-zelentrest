import java.util.Map;
import java.util.HashMap;

public class CalculateTime {
    // Виносимо фактори в конфігурацію
    private static final Map<String, Double> TIME_FACTORS = new HashMap<>() {{
        put("Прибирання", 0.1);
        put("Обслуговування газону", 0.2);
        put("Посипання піском", 0.05);
        put("Обрізка кущів", 0.07); // Додано для відповідності DispatcherApp
    }};

    public double estimateDuration(String serviceType, double square) {
        // Використовуємо фактор з конфігурації, або 1.0 (як у DispatcherApp default)
        double factor = TIME_FACTORS.getOrDefault(serviceType, 1.0);

        // Округлення до десятих
        return Math.round(factor * square * 10.0) / 10.0;
    }
}