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

    public abstract void aplicarInicioTurno(Personaje personaje);

    public abstract int modificarAtaque(int ataqueBase);

    public abstract boolean permiteAtacar();

    public void reducirDuracion() {
        turnos--;
    }

    public boolean estaActivo() {
        return turnos > 0;
    }
}