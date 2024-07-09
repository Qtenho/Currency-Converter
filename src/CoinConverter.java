import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinConverter {

    private static final String URL_API = "https://v6.exchangerate-api.com/v6/e1643d60f196860ce7c54e0f/latest/";
    private HttpClient clienteHttp;
    private Gson gson;

    public CoinConverter(HttpClient clienteHttp, Gson gson) {
        this.clienteHttp = clienteHttp;
        this.gson = gson;
    }

    public double coinConverter(double valueUser, String coinInitial, String finalCoin) {
        try {
            URI uri = URI.create(URL_API + coinInitial);
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> respuesta = clienteHttp.send(solicitud, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() == 200) {
                ResponseExchange responseExchange = gson.fromJson(respuesta.body(), ResponseExchange.class);

                if (responseExchange.getConversionRates() != null) {
                    Double ratioConversion = responseExchange.getConversionRates().get(finalCoin);
                    if (ratioConversion != null) {
                        return valueUser * ratioConversion;
                    } else {
                        System.out.println("Ratio de conversión no hallado para la divisa: " + finalCoin);
                        return 0.0;
                    }
                } else {
                    System.out.println("La API no contiene los ratios de cambio.");
                    return 0.0;
                }
            } else {
                throw new RuntimeException("Error al obtener el ratio de cambio: " + respuesta.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error al convertir la divisa: " + e.getMessage());
            return 0.0;
        }
    }

}