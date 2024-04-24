package com.alura.conversor.Principal;

import com.alura.conversor.Principal.Calcs;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Principal {
    //Aqui pongo mi Api Key
    private static final String KEY = "385869693f4f898107954fef";
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;
        JsonObject rates = getRates();
        Calcs calculator = new Calcs();

        do {
            printMenu();
            op = sc.nextInt();

            double rate;
            switch (op) {
                case 1:
                    rate = rates.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();
                    calculator.convert(rate, "USD", "ARS", sc);
                    break;
                case 2:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();
                    calculator.convert(rate, "ARS", "USD", sc);
                    break;
                case 3:
                    rate = rates.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
                    calculator.convert(rate, "USD", "BRL", sc);
                    break;
                case 4:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
                    calculator.convert(rate, "BRL", "USD", sc);
                    break;
                case 5:
                    rate = rates.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
                    calculator.convert(rate, "USD", "COP", sc);
                    break;
                case 6:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
                    calculator.convert(rate, "COP", "USD", sc);
                    break;
                case 7:
                    System.out.println("\033[0;33mGracias por usar el conversor. ¡Hasta luego!\033[0m");
                    break;
                default:
                    System.out.println("\033[0;31mOpción no válida. Intente de nuevo.\033[0m");
            }
        } while (op != 7);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\033[0;34m*************************************************\033[0m");
        System.out.println("\033[0;32mBienvenido/a al Conversor de Moneda =]\033[0m");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Salir");
        System.out.print("\033[0;36mElija opción válida: \033[0m");
        System.out.println("\033[0;34m*************************************************\033[0m");
    }

    private static JsonObject getRates() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonResp = gson.fromJson(response.body(), JsonObject.class);
            return jsonResp;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tasas: " + e.getMessage());
        }
    }
}
