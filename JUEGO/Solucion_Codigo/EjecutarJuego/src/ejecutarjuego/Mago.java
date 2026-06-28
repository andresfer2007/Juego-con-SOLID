package ejecutarjuego;

public class Mago extends Personaje {

    private int magia;

    public Mago(String nombre, int vida, int nivel, int magia) {
        super(nombre, vida, nivel);
        this.magia = magia;
    }
    public int getMagia(){
        return magia;
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
    public String toString() {
        return "\n--- MAGO ---"
                + "\n" + super.toString()
                + "\nMagia: " + magia;
    }

    //cambios
    @Override
    public int usarHabilidadEspecial(){
        if(energia>=40 && cooldown==0){
            energia-=40;
            cooldown=4;
            return (getMagia()*nivel)+50;
        }
        return atacar();
    }
}

