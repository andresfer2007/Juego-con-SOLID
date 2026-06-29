package ejecutarjuego;

public abstract class EstadoAlterado {

    protected String nombre;
    protected int turnos;

    public EstadoAlterado(String nombre, int turnos) {
        this.nombre = nombre;
        this.turnos = turnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void aplicarInicioTurno(Personaje personaje) {
        // Por defecto no hace nada.
    }

    public int modificarAtaque(int ataqueBase) {
        return ataqueBase;
    }

    public boolean permiteAtacar() {
        return true;
    }

    public void reducirDuracion() {
        turnos--;
    }

    public boolean estaActivo() {
        return turnos > 0;
    }
}