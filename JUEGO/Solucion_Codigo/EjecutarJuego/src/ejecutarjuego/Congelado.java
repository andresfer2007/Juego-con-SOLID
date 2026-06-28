package ejecutarjuego;

public class Congelado extends EstadoAlterado {

    public Congelado(int turnos) {
        super("Congelado", turnos);
    }

    @Override
    public void aplicarInicioTurno(Personaje personaje) {
        System.out.println(personaje.getNombre()
                + " esta congelado y no puede atacar.");
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        return ataqueBase;
    }

    @Override
    public boolean permiteAtacar() {
        return false;
    }
}