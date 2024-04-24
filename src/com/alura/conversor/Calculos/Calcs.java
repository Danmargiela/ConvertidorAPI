package com.alura.conversor.Principal;

import java.io.*;
import java.util.Scanner;

public class Calcs {

    private static final String HIST_FILE = "historial.txt";

    public void convert(double rate, String origin, String destination, Scanner sc) {
        System.out.print("Ingrese cantidad que quieres convertir en " + origin + ": ");
        double amount = sc.nextDouble();
        double converted = amount * rate;
        System.out.println("Tu cantidad "+amount + " " + origin + " son " + converted + " " + destination);
        saveHistory(amount, origin, converted, destination);
    }

    private void saveHistory(double amount, String origin, double converted, String destination) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(HIST_FILE, true)))) {
            out.printf("Valor convertido: %.2f %s a %.2f %s%n", amount, origin, converted, destination);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de historial: " + e.getMessage());
        }
    }
}