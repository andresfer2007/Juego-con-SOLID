package ejecutarjuego;

public class AumentoFuerza extends EstadoAlterado {

    private int bonoAtaque;

    public AumentoFuerza(int turnos, int bonoAtaque) {
        super("Aumento de fuerza", turnos);
        this.bonoAtaque = bonoAtaque;
    }

    @Override
    public void aplicarInicioTurno(Personaje personaje) {
        System.out.println(personaje.getNombre()
                + " tiene aumento de fuerza activo.");
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        return ataqueBase + bonoAtaque;
    }

    @Override
    public boolean permiteAtacar() {
        return true;
    }
}