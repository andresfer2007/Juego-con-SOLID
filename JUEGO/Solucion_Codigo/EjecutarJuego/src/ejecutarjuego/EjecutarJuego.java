package ejecutarjuego;

import inventario.Arma;
import inventario.Armadura;
import personajes.Arquero;
import personajes.Guerrero;
import personajes.Personaje;
import personajes.Mago;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjecutarJuego {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        lectorEntrada lector = new lectorEntrada(scanner);
        CatalogoEquipo catalogo = new CatalogoEquipo();
        FabricaPersonaje fabrica = new FabricaPersonaje();
        MenuConsola menu = new MenuConsola(lector, catalogo, fabrica);

        int cantidad = menu.pedirCantidadParticipantes();

        List<Personaje> participantes = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            participantes.add(menu.crearPersonaje(i));
        }

        menu.mostrarPersonajes(participantes);

        Combate.jugarTorneoTodosContraTodos(participantes);

        scanner.close();
    }
}
