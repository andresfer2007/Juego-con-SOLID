package ejecutarjuego;

public class Guerrero extends Personaje {


    private static final int COSTO_ENERGIA_HABILIDAD = 30;
    private static final int COOLDOWN_HABILIDAD = 3;
    private static final int MULTIPLICADOR_HABILIDAD = 3;
    private int fuerza;

    public Guerrero(String nombre, int vida, int nivel, int fuerza) {
        super(nombre, vida, nivel);
        this.fuerza = fuerza;
    }

    @Override
    public int atacar() {
        return (fuerza * nivel) + getBonusAtaque();
    }

    @Override
    public int defender() {
        return 5 + getBonusDefensa();
    } // LSP: implementa el contrato de Personaje 

    @Override
    public boolean puedeUsarHabilidad() {
        return energia >= COSTO_ENERGIA_HABILIDAD && cooldown == 0;
    } 

    @Override
    public int usarHabilidadEspecial() {
        if (puedeUsarHabilidad()) {
            energia -= COSTO_ENERGIA_HABILIDAD;
            cooldown = COOLDOWN_HABILIDAD;
            return atacar() * MULTIPLICADOR_HABILIDAD;
        }
        return atacar();
    }

    @Override
    public String toString() {
        return "\n--- GUERRERO ---" + "\n" + super.toString() + "\nFuerza: " + fuerza;
    }
}
