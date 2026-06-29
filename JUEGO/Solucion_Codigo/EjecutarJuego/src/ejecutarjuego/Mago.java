package ejecutarjuego;

public class Mago extends Personaje { // OCP: umbrales más altos — el mago es más poderoso pero más lento 

    private static final int COSTO_ENERGIA_HABILIDAD = 40;
    private static final int COOLDOWN_HABILIDAD = 4;
    private static final int MULTIPLICADOR_HABILIDAD = 4;
    private int magia;

    public Mago(String nombre, int vida, int nivel, int magia) {
        super(nombre, vida, nivel);
        this.magia = magia;
    }

    @Override
    public int atacar() {
        return (magia * nivel) + getBonusAtaque();
    }

    @Override
    public int defender() {
        return 3 + getBonusDefensa();
    }

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
        return "\n--- MAGO ---" + "\n" + super.toString() + "\nMagia: " + magia;
    }
}
