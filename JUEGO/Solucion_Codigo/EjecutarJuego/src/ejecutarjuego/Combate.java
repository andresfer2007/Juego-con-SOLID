package ejecutarjuego;
import java.util.List;

public class Combate {

    public static void jugarTorneoTodosContraTodos(List<Personaje> participantes) {

        System.out.println("\n===== TORNEO: TODOS CONTRA TODOS =====");
        System.out.println("Participantes: " + participantes.size());

        for (int i = 0; i < participantes.size(); i++) {
            for (int j = i + 1; j < participantes.size(); j++) {

                Personaje p1 = participantes.get(i);
                Personaje p2 = participantes.get(j);

                System.out.println("\n--------------------------------------");
                System.out.println(
                        "Enfrentamiento: " + p1.getNombre()
                        + " VS " + p2.getNombre()
                );

                Personaje ganador = batallar(p1, p2);

                ganador.sumarVictoria();

                System.out.println(
                        "Ganador del enfrentamiento: " + ganador.getNombre()
                );
            }
        }

        mostrarTablaFinal(participantes);
    }

    private static void mostrarTablaFinal(List<Personaje> participantes) {

        System.out.println("\n===== TABLA FINAL DEL TORNEO =====");

        Personaje campeon = participantes.get(0);

        for (Personaje p : participantes) {

            System.out.println(p.getNombre() + " - Victorias: " + p.getVictorias());

            if (p.getVictorias() > campeon.getVictorias()) {
                campeon = p;
            }
        }

        System.out.println("\n===== CAMPEON DEL TORNEO =====");
        System.out.println(campeon.getNombre()
                + " con " + campeon.getVictorias() + " victorias.");
        System.out.println(campeon);
    }

    public static Personaje batallar(Personaje p1, Personaje p2) {

        p1.restaurarVida();
        p2.restaurarVida();

        System.out.println("\n===== COMBATE =====");
        System.out.println(p1.getNombre() + " VS " + p2.getNombre());

        int turnosMaximos = 200;
        int turno = 0;

        while (p1.estaVivo() && p2.estaVivo() && turno < turnosMaximos) {

            int danio1 = p1.atacar() - p2.defender();

            if (danio1 < 0) {
                danio1 = 0;
            }

            p2.recibirDanio(danio1);

            System.out.println(
                    p1.getNombre() + " ataca y causa "
                    + danio1 + " de danio."
            );

            if (!p2.estaVivo()) {
                break;
            }

            int danio2 = p2.atacar() - p1.defender();

            if (danio2 < 0) {
                danio2 = 0;
            }

            p1.recibirDanio(danio2);

            System.out.println(
                    p2.getNombre() + " ataca y causa "
                    + danio2 + " de danio."
            );

            System.out.println(
                    p1.getNombre() + " Vida: " + p1.getVida()
            );

            System.out.println(
                    p2.getNombre() + " Vida: " + p2.getVida()
            );

            turno++;
        }

        Personaje ganador;

        if (!p1.estaVivo()) {
            ganador = p2;
        } else if (!p2.estaVivo()) {
            ganador = p1;
        } else {
            // Empate por limite de turnos: gana quien tenga mas vida.
            if (p1.getVida() >= p2.getVida()) {
                ganador = p1;
            } else {
                ganador = p2;
            }
        }

        System.out.println("\nGanador: " + ganador.getNombre());
        ganador.subirNivel();

        return ganador;
    }
}