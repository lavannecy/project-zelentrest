import java.util.Map;
import java.util.HashMap;

public class CalculatePrice {
    // Виносимо ставки в конфігурацію
    private static final Map<String, Double> PRICE_RATES = new HashMap<>() {{
        put("Прибирання", 20.0);
        put("Обслуговування газону", 30.0);
        put("Посипання піском", 15.0);
        put("Обрізка кущів", 9.0); // Додано для відповідності DispatcherApp, але використовуємо його ставку
    }};

    public double estimatePrice(String serviceType, double square) {
        // Використовуємо ставку з конфігурації, або 10.0 (як у DispatcherApp default)
        double rate = PRICE_RATES.getOrDefault(serviceType, 10.0);

        // Округлення до сотих
        return Math.round(rate * square * 100.0) / 100.0;
    }
}
