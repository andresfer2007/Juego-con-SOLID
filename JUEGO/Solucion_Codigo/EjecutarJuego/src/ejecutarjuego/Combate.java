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

        p1.recuperarEnergia();
        p2.recuperarEnergia();

        p1.actualizarCooldown();
        p2.actualizarCooldown();

        p1.restaurarVida();
        p2.restaurarVida();

        System.out.println("\n===== COMBATE =====");
        System.out.println(p1.getNombre() + " VS " + p2.getNombre());

        int turnosMaximos = 200;
        int turno = 0;

        while (p1.estaVivo() && p2.estaVivo() && turno < turnosMaximos) {

            // Turno del personaje 1
            p1.aplicarEstadosInicioTurno();

            if (!p1.estaVivo()) {
                break;
            }

            if (p1.puedeAtacar()) {

                int ataque1;

                if (p1.getCooldown() == 0 && p1.getEnergia() >= 30) {
                    ataque1 = p1.usarHabilidadEspecial();

                    System.out.println(
                            p1.getNombre() + " usa habilidad especial!"
                    );
                } else {
                    ataque1 = p1.atacar();
                }

                ataque1 = p1.obtenerAtaqueConEstados(ataque1);

                int danio1 = ataque1 - p2.defender();

                if (danio1 < 0) {
                    danio1 = 0;
                }

                p2.recibirDanio(danio1);

                System.out.println(
                        p1.getNombre() + " ataca y causa "
                        + danio1 + " de danio."
                );

            } else {
                System.out.println(p1.getNombre()
                        + " no puede atacar por un estado alterado.");
            }

            p1.actualizarEstadosAlterados();

            if (!p2.estaVivo()) {
                break;
            }

            // Turno del personaje 2
            p2.aplicarEstadosInicioTurno();

            if (!p2.estaVivo()) {
                break;
            }

            if (p2.puedeAtacar()) {

                int ataque2;

                if (p2.getCooldown() == 0 && p2.getEnergia() >= 30) {
                    ataque2 = p2.usarHabilidadEspecial();

                    System.out.println(
                            p2.getNombre() + " usa habilidad especial!"
                    );
                } else {
                    ataque2 = p2.atacar();
                }

                ataque2 = p2.obtenerAtaqueConEstados(ataque2);

                int danio2 = ataque2 - p1.defender();

                if (danio2 < 0) {
                    danio2 = 0;
                }

                p1.recibirDanio(danio2);

                System.out.println(
                        p2.getNombre() + " ataca y causa "
                        + danio2 + " de danio."
                );

            } else {
                System.out.println(p2.getNombre()
                        + " no puede atacar por un estado alterado.");
            }

            p2.actualizarEstadosAlterados();

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