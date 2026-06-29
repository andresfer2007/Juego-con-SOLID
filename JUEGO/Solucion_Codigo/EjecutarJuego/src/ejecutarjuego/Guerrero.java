package ejecutarjuego;

public class Guerrero extends Personaje {

    private int fuerza;

    public Guerrero(String nombre, int vida, int nivel, int fuerza) {
        super(nombre, vida, nivel);
        this.fuerza = fuerza;
    }

    @Override
    public int atacar() {
        return fuerza * nivel;
    }

    @Override
    public int defender() {
        return 5;
    }

    @Override
    public String toString() {
        return "\n--- GUERRERO ---"
                + "\n" + super.toString()
                + "\nFuerza: " + fuerza;
    }
}