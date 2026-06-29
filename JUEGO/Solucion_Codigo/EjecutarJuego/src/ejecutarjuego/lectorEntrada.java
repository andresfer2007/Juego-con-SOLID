/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutarjuego;

import java.util.Scanner;

public class lectorEntrada {

    private final Scanner scanner;

    public lectorEntrada(Scanner scanner) {
        this.scanner = scanner;
    }

    public int leerEntero() {
        int valor = -1;
        while (valor < 0) {
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor < 0) System.out.print("Ingresa un numero valido: ");
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero valido: ");
            }
        }
        return valor;
    }

    public int leerOpcion(int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor < min || valor > max)
                    System.out.print("Opcion invalida, elige entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero valido: ");
            }
        }
        return valor;
    }

    public String leerTexto() {
        return scanner.nextLine();
    }
}
