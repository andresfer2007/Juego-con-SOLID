package ejecutarjuego;

public class Envenenado extends EstadoAlterado {

    private int danioPorTurno;

    public Envenenado(int turnos, int danioPorTurno) {
        super("Envenenado", turnos);
        this.danioPorTurno = danioPorTurno;
    }

    @Override
    public void aplicarInicioTurno(Personaje personaje) {
        personaje.recibirDanio(danioPorTurno);
        System.out.println(personaje.getNombre() + " pierde "
                + danioPorTurno + " de vida por veneno.");
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        return ataqueBase;
    }

    @Override
    public boolean permiteAtacar() {
        return true;
    }
}