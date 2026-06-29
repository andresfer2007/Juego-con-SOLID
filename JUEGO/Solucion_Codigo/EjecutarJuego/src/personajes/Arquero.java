package personajes;


public class Arquero extends Personaje { // OCP: umbrales más bajos — el arquero es más ágil y rápido 

    private static final int COSTO_ENERGIA_HABILIDAD = 20;
    private static final int COOLDOWN_HABILIDAD = 2;
    private static final int MULTIPLICADOR_HABILIDAD = 2;
    private int precision;

    public Arquero(String nombre, int vida, int nivel, int precision) {
        super(nombre, vida, nivel);
        this.precision = precision;
    }

    @Override
    public int atacar() {
        return (precision * nivel) + getBonusAtaque();
    }

    @Override
    public int defender() {
        return 4 + getBonusDefensa();
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
        return "\n--- ARQUERO ---" + "\n" + super.toString() + "\nPrecision: " + precision;
    }
}
