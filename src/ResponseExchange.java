import java.util.Map;

public class ResponseExchange {
    private String base_code;
    private String date;
    private Map<String, Double> conversion_rates;

    public String getBaseCode() {
        return base_code;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}
