package ejecutarjuego;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjecutarJuego {

    // Plantillas predefinidas por tipo: {vida, nivel, stat_especial}
    // Guerrero: fuerza | Mago: magia | Arquero: precision
    private static final int[][] STATS_GUERRERO = {
        {100, 1, 8},   // Guerrero Novato
        {130, 2, 12},  // Guerrero Veterano
        {160, 3, 18}   // Guerrero Legendario
    };

    private static final int[][] STATS_MAGO = {
        {70,  1, 10},  // Mago Aprendiz
        {90,  2, 15},  // Mago Sabio
        {110, 3, 22}   // Mago Supremo
    };

    private static final int[][] STATS_ARQUERO = {
        {80,  1, 9},   // Arquero Bisoño
        {100, 2, 13},  // Arquero Experto
        {120, 3, 19}   // Arquero Maestro
    };

    // Armas y armaduras predefinidas
    private static final Arma[] ARMAS = {
        new Arma("Espada de Hierro",  10, "Espada", 100),
        new Arma("Hacha Berserker",   15, "Hacha",   80),
        new Arma("Baston Arcano",     12, "Baston",  90),
        new Arma("Arco Largo",        11, "Arco",    95),
        new Arma("Daga Envenenada",    8, "Daga",    70),
        new Arma("Lanza de Titan",    18, "Lanza",   60)
    };

    private static final Armadura[] ARMADURAS = {
        new Armadura("Armadura de Cuero",  5,  "Cuero"),
        new Armadura("Armadura de Hierro", 10, "Hierro"),
        new Armadura("Tunica Magica",       7, "Tela Encantada"),
        new Armadura("Cota de Malla",      12, "Acero"),
        new Armadura("Escudo de Roble",     8, "Madera"),
        new Armadura("Placas de Dragon",   20, "Escamas")
    };

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        System.out.print("Cuantos personajes participaran en el torneo? ");
        int cantidad = leerEntero(leer);

        while (cantidad < 2) {
            System.out.print("Se necesitan al menos 2 personajes. Cuantos? ");
            cantidad = leerEntero(leer);
        }

        List<Personaje> participantes = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            Personaje p = crearPersonaje(leer, i);
            participantes.add(p);
        }

        System.out.println("\n===== PERSONAJES CREADOS =====");
        for (Personaje p : participantes) {
            System.out.println(p);
        }

        Combate.jugarTorneoTodosContraTodos(participantes);

        leer.close();
    }

    private static Personaje crearPersonaje(Scanner leer, int numero) {

        System.out.println("\n--- Personaje " + numero + " ---");

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        int tipo = elegirTipo(leer);

        Personaje personaje;

        switch (tipo) {
            case 1:
                personaje = elegirClaseGuerrero(leer, nombre);
                break;
            case 2:
                personaje = elegirClaseMago(leer, nombre);
                break;
            default:
                personaje = elegirClaseArquero(leer, nombre);
                break;
        }

        // Elegir arma
        System.out.println("\nElige un arma para " + nombre + ":");
        mostrarListaArmas();
        System.out.println((ARMAS.length + 1) + ". Sin arma");
        System.out.print("Opcion: ");
        int opcionArma = leerOpcion(leer, 1, ARMAS.length + 1);

        if (opcionArma <= ARMAS.length) {
            Arma arma = ARMAS[opcionArma - 1];
            personaje.agregarObjeto(arma);
            personaje.equiparArma(arma);
            System.out.println("Equipado: " + arma);
        } else {
            System.out.println("Sin arma equipada.");
        }

        // Elegir armadura
        System.out.println("\nElige una armadura para " + nombre + ":");
        mostrarListaArmaduras();
        System.out.println((ARMADURAS.length + 1) + ". Sin armadura");
        System.out.print("Opcion: ");
        int opcionArmadura = leerOpcion(leer, 1, ARMADURAS.length + 1);

        if (opcionArmadura <= ARMADURAS.length) {
            Armadura armadura = ARMADURAS[opcionArmadura - 1];
            personaje.agregarObjeto(armadura);
            personaje.equiparArmadura(armadura);
            System.out.println("Equipado: " + armadura);
        } else {
            System.out.println("Sin armadura equipada.");
        }

        return personaje;
    }

    private static Personaje elegirClaseGuerrero(Scanner leer, String nombre) {
        System.out.println("\nElige la clase de Guerrero:");
        System.out.println("1. Guerrero Novato     | Vida: 100 | Nivel: 1 | Fuerza: 8");
        System.out.println("2. Guerrero Veterano   | Vida: 130 | Nivel: 2 | Fuerza: 12");
        System.out.println("3. Guerrero Legendario | Vida: 160 | Nivel: 3 | Fuerza: 18");
        System.out.print("Opcion: ");
        int op = leerOpcion(leer, 1, 3);
        int[] s = STATS_GUERRERO[op - 1];
        return new Guerrero(nombre, s[0], s[1], s[2]);
    }

    private static Personaje elegirClaseMago(Scanner leer, String nombre) {
        System.out.println("\nElige la clase de Mago:");
        System.out.println("1. Mago Aprendiz  | Vida: 70  | Nivel: 1 | Magia: 10");
        System.out.println("2. Mago Sabio     | Vida: 90  | Nivel: 2 | Magia: 15");
        System.out.println("3. Mago Supremo   | Vida: 110 | Nivel: 3 | Magia: 22");
        System.out.print("Opcion: ");
        int op = leerOpcion(leer, 1, 3);
        int[] s = STATS_MAGO[op - 1];
        return new Mago(nombre, s[0], s[1], s[2]);
    }

    private static Personaje elegirClaseArquero(Scanner leer, String nombre) {
        System.out.println("\nElige la clase de Arquero:");
        System.out.println("1. Arquero Bisoño  | Vida: 80  | Nivel: 1 | Precision: 9");
        System.out.println("2. Arquero Experto | Vida: 100 | Nivel: 2 | Precision: 13");
        System.out.println("3. Arquero Maestro | Vida: 120 | Nivel: 3 | Precision: 19");
        System.out.print("Opcion: ");
        int op = leerOpcion(leer, 1, 3);
        int[] s = STATS_ARQUERO[op - 1];
        return new Arquero(nombre, s[0], s[1], s[2]);
    }

    private static void mostrarListaArmas() {
        for (int i = 0; i < ARMAS.length; i++) {
            System.out.println((i + 1) + ". " + ARMAS[i].getNombreObj()
                    + " | Daño extra: +" + ARMAS[i].getValor()
                    + " | Tipo: " + ARMAS[i].getTipo()
                    + " | Durabilidad: " + ARMAS[i].getDurabilidad());
        }
    }

    private static void mostrarListaArmaduras() {
        for (int i = 0; i < ARMADURAS.length; i++) {
            System.out.println((i + 1) + ". " + ARMADURAS[i].getNombreObj()
                    + " | Defensa extra: +" + ARMADURAS[i].getValor()
                    + " | Material: " + ARMADURAS[i].getMaterial());
        }
    }

    private static int elegirTipo(Scanner leer) {
        int opcion = 0;
        while (opcion < 1 || opcion > 3) {
            System.out.println("Elige el tipo de personaje:");
            System.out.println("1. Guerrero");
            System.out.println("2. Mago");
            System.out.println("3. Arquero");
            System.out.print("Opcion: ");
            opcion = leerEntero(leer);
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opcion invalida, intenta de nuevo.\n");
            }
        }
        return opcion;
    }

    private static int leerEntero(Scanner leer) {
        int valor = -1;
        while (valor < 0) {
            try {
                valor = Integer.parseInt(leer.nextLine().trim());
                if (valor < 0) {
                    System.out.print("Ingresa un numero valido: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero valido: ");
            }
        }
        return valor;
    }

    private static int leerOpcion(Scanner leer, int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            try {
                valor = Integer.parseInt(leer.nextLine().trim());
                if (valor < min || valor > max) {
                    System.out.print("Opcion invalida, elige entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero valido: ");
            }
        }
        return valor;
    }
}