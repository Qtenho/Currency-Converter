import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HttpClient clienteHttp = HttpClient.newHttpClient();
        Gson gson = new Gson();
        CoinConverter coinConverter = new CoinConverter(clienteHttp, gson);

        while (true) {

            System.out.println("""
            *****************************************************
                                 Bienvenid@s
                            Conversor De Divisas
            
            1) Dolar           >>> Peso colombiano
            2) Peso colombiano >>> Dolar
            3) Dolar           >>> Real brasileño
            4) Real brasileño  >>> Dolar
            5) Dolar           >>> Peso Chileno
            6) Peso Chileno    >>> Dolar
            7) Salir
            Por favor seleccione una opción:
            ******************************************************
            """);
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 7) {
                System.out.println("Cerrando aplicación. ¡Hasta Luego!");
                break;
            }

            System.out.print("Por favor digite el valor a convertir: ");
            double valueUser = scanner.nextDouble();
            scanner.nextLine();
            String initialcoin = getInitialCoin(option);
            String finalCoin = getFinalCoin(option);
            double valorFinal = coinConverter.coinConverter(valueUser, initialcoin, finalCoin);
            System.out.printf("Los %.2f %s ingresados equivalen a: %.2f %s%n", valueUser, initialcoin, valorFinal, finalCoin);
        }
    }

    private static String getInitialCoin(int option) {
        return switch (option) {
            case 1 -> "USD";
            case 2 -> "COP";
            case 3 -> "USD";
            case 4 -> "BRL";
            case 5 -> "USD";
            case 6 -> "CLP";
            default -> throw new IllegalArgumentException("Opción inválida.");
        };
    }

    private static String getFinalCoin(int option) {
        return switch (option) {
            case 1 -> "COP";
            case 2 -> "USD";
            case 3 -> "BRL";
            case 4 -> "USD";
            case 5 -> "CLP";
            case 6 -> "USD";
            default -> throw new IllegalArgumentException("Opción inválida.");
        };
    }
}