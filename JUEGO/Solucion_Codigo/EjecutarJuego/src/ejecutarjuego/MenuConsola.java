/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutarjuego;

import inventario.Arma;
import inventario.Armadura;
import personajes.Personaje;

public class MenuConsola {

    private final lectorEntrada lector;
    private final CatalogoEquipo catalogo;
    private final FabricaPersonaje fabrica;

    public MenuConsola(lectorEntrada lector, CatalogoEquipo catalogo, FabricaPersonaje fabrica) {
        this.lector   = lector;
        this.catalogo = catalogo;
        this.fabrica  = fabrica;
    }

    public int pedirCantidadParticipantes() {
        System.out.print("Cuantos personajes participaran en el torneo? ");
        int cantidad = lector.leerEntero();
        while (cantidad < 2) {
            System.out.print("Se necesitan al menos 2 personajes. Cuantos? ");
            cantidad = lector.leerEntero();
        }
        return cantidad;
    }

    public Personaje crearPersonaje(int numero) {
        System.out.println("\n--- Personaje " + numero + " ---");

        System.out.print("Nombre: ");
        String nombre = lector.leerTexto();

        int tipo  = pedirTipo();
        int clase = pedirClase(tipo);

        Personaje personaje = fabrica.crear(nombre, tipo, clase);

        equiparArma(personaje, nombre);
        equiparArmadura(personaje, nombre);

        return personaje;
    }

    // ── Menus de tipo y clase ────────────────────────────────────────────────

    private int pedirTipo() {
        System.out.println("\nElige el tipo de personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Arquero");
        System.out.print("Opcion: ");
        return lector.leerOpcion(1, 3);
    }

    private int pedirClase(int tipo) {
        switch (tipo) {
            case 1: return pedirClaseGuerrero();
            case 2: return pedirClaseMago();
            default: return pedirClaseArquero();
        }
    }

    private int pedirClaseGuerrero() {
        System.out.println("\nElige la clase de Guerrero:");
        System.out.println("1. Guerrero Novato     | Vida: 100 | Nivel: 1 | Fuerza: 8");
        System.out.println("2. Guerrero Veterano   | Vida: 130 | Nivel: 2 | Fuerza: 12");
        System.out.println("3. Guerrero Legendario | Vida: 160 | Nivel: 3 | Fuerza: 18");
        System.out.print("Opcion: ");
        return lector.leerOpcion(1, 3);
    }

    private int pedirClaseMago() {
        System.out.println("\nElige la clase de Mago:");
        System.out.println("1. Mago Aprendiz  | Vida: 70  | Nivel: 1 | Magia: 10");
        System.out.println("2. Mago Sabio     | Vida: 90  | Nivel: 2 | Magia: 15");
        System.out.println("3. Mago Supremo   | Vida: 110 | Nivel: 3 | Magia: 22");
        System.out.print("Opcion: ");
        return lector.leerOpcion(1, 3);
    }

    private int pedirClaseArquero() {
        System.out.println("\nElige la clase de Arquero:");
        System.out.println("1. Arquero Bisoño  | Vida: 80  | Nivel: 1 | Precision: 9");
        System.out.println("2. Arquero Experto | Vida: 100 | Nivel: 2 | Precision: 13");
        System.out.println("3. Arquero Maestro | Vida: 120 | Nivel: 3 | Precision: 19");
        System.out.print("Opcion: ");
        return lector.leerOpcion(1, 3);
    }

    // ── Equipamiento ─────────────────────────────────────────────────────────

    private void equiparArma(Personaje personaje, String nombre) {
        Arma[] armas = catalogo.getArmas();
        System.out.println("\nElige un arma para " + nombre + ":");
        for (int i = 0; i < armas.length; i++) {
            System.out.println((i + 1) + ". " + armas[i].getNombreObj()
                    + " | Daño extra: +" + armas[i].getValor()
                    + " | Tipo: "        + armas[i].getTipo()
                    + " | Durabilidad: " + armas[i].getDurabilidad());
        }
        System.out.println((armas.length + 1) + ". Sin arma");
        System.out.print("Opcion: ");
        int op = lector.leerOpcion(1, armas.length + 1);

        if (op <= armas.length) {
            Arma arma = armas[op - 1];
            personaje.agregarObjeto(arma);
            personaje.equiparArma(arma);
            System.out.println("Equipado: " + arma);
        } else {
            System.out.println("Sin arma equipada.");
        }
    }

    private void equiparArmadura(Personaje personaje, String nombre) {
        Armadura[] armaduras = catalogo.getArmaduras();
        System.out.println("\nElige una armadura para " + nombre + ":");
        for (int i = 0; i < armaduras.length; i++) {
            System.out.println((i + 1) + ". " + armaduras[i].getNombreObj()
                    + " | Defensa extra: +" + armaduras[i].getValor()
                    + " | Material: "       + armaduras[i].getMaterial());
        }
        System.out.println((armaduras.length + 1) + ". Sin armadura");
        System.out.print("Opcion: ");
        int op = lector.leerOpcion(1, armaduras.length + 1);

        if (op <= armaduras.length) {
            Armadura armadura = armaduras[op - 1];
            personaje.agregarObjeto(armadura);
            personaje.equiparArmadura(armadura);
            System.out.println("Equipado: " + armadura);
        } else {
            System.out.println("Sin armadura equipada.");
        }
    }

    public void mostrarPersonajes(java.util.List<Personaje> lista) {
        System.out.println("\n===== PERSONAJES CREADOS =====");
        for (Personaje p : lista) {
            System.out.println(p);
        }
    }
}
