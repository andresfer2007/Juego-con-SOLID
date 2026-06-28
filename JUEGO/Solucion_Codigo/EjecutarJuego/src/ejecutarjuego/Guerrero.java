
package ejecutarjuego;

public class Guerrero extends Personaje {

    private int fuerza;

    public Guerrero(String nombre, int vida, int nivel, int fuerza) {
        super(nombre, vida, nivel);
        this.fuerza = fuerza;
    }

    @Override
    public String toString() {
        return "\n--- GUERRERO ---"
                + "\n" + super.toString()
                + "\nFuerza: " + fuerza;
    }

    @Override
    public int atacar() {
        return (fuerza * nivel) + getBonusAtaque(); // incluye bonus del arma
    }

    @Override
    public int defender() {
        return 5 + getBonusDefensa(); // incluye bonus de armadura
    }

    @Override
    public int usarHabilidadEspecial(){
        if(energia>=30 && cooldown==0){
            energia-=30;
            cooldown=3;
            return atacar()*3;
        }
        return atacar();
    }
}