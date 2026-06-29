package ejecutarjuego;

import personajes.Personaje;
import java.util.List;

public class Combate {

    private static final int REGENERACION_ENERGIA_POR_TURNO = 10;
    private static final int TURNOS_MAXIMOS = 200;

    public static void jugarTorneoTodosContraTodos(List<Personaje> participantes) {
        System.out.println("\n===== TORNEO: TODOS CONTRA TODOS =====");
        System.out.println("Participantes: " + participantes.size());

        for (int i = 0; i < participantes.size(); i++) {
            for (int j = i + 1; j < participantes.size(); j++) {
                Personaje p1 = participantes.get(i);
                Personaje p2 = participantes.get(j);

                System.out.println("\n--------------------------------------");
                System.out.println("Enfrentamiento: " + p1.getNombre() + " VS " + p2.getNombre());

                Personaje ganador = batallar(p1, p2);
                ganador.sumarVictoria();

                System.out.println("Ganador del enfrentamiento: " + ganador.getNombre());
            }
        }

        mostrarTablaFinal(participantes);
    }

    public static Personaje batallar(Personaje p1, Personaje p2) {
        p1.restaurarVida();
        p2.restaurarVida();
        p1.restaurarEnergia();   
        p2.restaurarEnergia();

        System.out.println("\n===== COMBATE =====");
        System.out.println(p1.getNombre() + " VS " + p2.getNombre());

        int turno = 0;

        while (p1.estaVivo() && p2.estaVivo() && turno < TURNOS_MAXIMOS) {
            turno++;
            System.out.println("\n-- Turno " + turno + " --");

            int danio1 = calcularAtaque(p1, p2);
            p2.recibirDanio(danio1);

            if (!p2.estaVivo()) break;

            int danio2 = calcularAtaque(p2, p1);
            p1.recibirDanio(danio2);

            p1.regenerarEnergia(REGENERACION_ENERGIA_POR_TURNO);
            p2.regenerarEnergia(REGENERACION_ENERGIA_POR_TURNO);

            p1.reducirCooldown();
            p2.reducirCooldown();

            mostrarEstadoTurno(p1);
            mostrarEstadoTurno(p2);
        }

        return determinarGanador(p1, p2);
    }

    // ── Logica de ataque por turno ────────────────────────────────────────────

    private static int calcularAtaque(Personaje atacante, Personaje defensor) {
        int ataqueBruto;
        String tipoAtaque;

        if (atacante.puedeUsarHabilidad()) {
            ataqueBruto = atacante.usarHabilidadEspecial();
            tipoAtaque  = "usa HABILIDAD ESPECIAL";
        } else {
            ataqueBruto = atacante.atacar();
            tipoAtaque  = "ataca";
        }

        int danio = Math.max(0, ataqueBruto - defensor.defender());

        System.out.println(atacante.getNombre() + " " + tipoAtaque
                + " -> " + danio + " de danio a " + defensor.getNombre()
                + "  [Energia: " + atacante.getEnergia()
                + " | CD: " + atacante.getCooldown() + "]");

        return danio;
    }

    private static void mostrarEstadoTurno(Personaje p) {
        System.out.println("  " + p.getNombre()
                + " | Vida: "    + p.getVida()
                + " | Energia: " + p.getEnergia()
                + " | CD: "      + p.getCooldown());
    }

   

    private static Personaje determinarGanador(Personaje p1, Personaje p2) {
        Personaje ganador;

        if (!p1.estaVivo()) {
            ganador = p2;
        } else if (!p2.estaVivo()) {
            ganador = p1;
        } else {
            // Empate por limite de turnos: gana quien tenga mas vida
            ganador = (p1.getVida() >= p2.getVida()) ? p1 : p2;
            System.out.println("\nLimite de turnos alcanzado. Gana por vida mayor.");
        }

        System.out.println("\nGanador: " + ganador.getNombre());
        ganador.subirNivel();
        return ganador;
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
}