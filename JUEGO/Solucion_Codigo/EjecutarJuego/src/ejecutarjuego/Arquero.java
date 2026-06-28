package ejecutarjuego;

public class Arquero extends Personaje {

    private int precision;

    public Arquero(String nombre, int vida, int nivel, int precision) {
        super(nombre, vida, nivel);
        this.precision = precision;
    }
    public int getPrecision(){
        return precision;
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
    public String toString() {
        return "\n--- ARQUERO ---"
                + "\n" + super.toString()
                + "\nPrecision: " + precision;
    }



    //cambios
    @Override
    public int usarHabilidadEspecial(){
        if(energia>=20 && cooldown==0){
            energia-=20;
            cooldown=2;
            return (getPrecision()*nivel)+30;
        }
        return atacar();
    }
}

