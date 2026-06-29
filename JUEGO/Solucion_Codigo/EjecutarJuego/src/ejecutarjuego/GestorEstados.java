package ejecutarjuego;

import java.util.ArrayList;

/**
 * Responsable unicamente de gestionar los estados alterados de un personaje.
 * Aplica el principio SRP (Responsabilidad Unica): Personaje ya no mezcla
 * su propia logica de combate con la logica de estados alterados.
 */
public class GestorEstados {

    private ArrayList<EstadoAlterado> estadosAlterados;

    public GestorEstados() {
        this.estadosAlterados = new ArrayList<EstadoAlterado>();
    }

    public void agregarEstado(EstadoAlterado estado, String nombrePersonaje) {
        estadosAlterados.add(estado);
        System.out.println(nombrePersonaje + " recibe el estado: " + estado.getNombre());
    }

    public void aplicarInicioTurno(Personaje personaje) {
        for (int i = 0; i < estadosAlterados.size(); i++) {
            EstadoAlterado estado = estadosAlterados.get(i);
            estado.aplicarInicioTurno(personaje);
        }
    }

    public boolean permiteAtacar() {
        for (int i = 0; i < estadosAlterados.size(); i++) {
            EstadoAlterado estado = estadosAlterados.get(i);

            if (estado.permiteAtacar() == false) {
                return false;
            }
        }

        return true;
    }

    public int modificarAtaque(int ataqueBase) {
        int ataqueFinal = ataqueBase;

        for (int i = 0; i < estadosAlterados.size(); i++) {
            EstadoAlterado estado = estadosAlterados.get(i);
            ataqueFinal = estado.modificarAtaque(ataqueFinal);
        }

        return ataqueFinal;
    }

    public void actualizarEstados(String nombrePersonaje) {
        ArrayList<EstadoAlterado> estadosActivos = new ArrayList<EstadoAlterado>();

        for (int i = 0; i < estadosAlterados.size(); i++) {
            EstadoAlterado estado = estadosAlterados.get(i);

            estado.reducirDuracion();

            if (estado.estaActivo()) {
                estadosActivos.add(estado);
            } else {
                System.out.println(nombrePersonaje + " ya no tiene el estado: " + estado.getNombre());
            }
        }

        estadosAlterados = estadosActivos;
    }
}
