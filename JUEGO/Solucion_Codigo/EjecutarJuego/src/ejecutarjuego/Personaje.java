package ejecutarjuego;

import java.util.ArrayList;

public abstract class Personaje {

    protected String nombre;
    protected int vida;
    protected int vidaMaxima;
    protected int nivel;
    protected int victorias;
    protected int energia = 100;
    protected int energiaMaxima = 100;
    protected int cooldown = 0;

   

    protected ArrayList<Objeto> inventario;
    protected Arma armaEquipada = null;
    protected Armadura armaduraEquipada = null;

    public Personaje(String nombre, int vida, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.nivel = nivel;
        this.victorias = 0;

        this.inventario = new ArrayList<>();
    }
    
     public abstract int usarHabilidadEspecial();
     public abstract boolean puedeUsarHabilidad();

    public void sumarVictoria() {
        victorias++;
    }

    public int getVictorias() {
        return victorias;
    }

    public void restaurarVida() {
        vida = vidaMaxima;
    }

    public abstract int atacar();

    public abstract int defender();

    public void recibirDanio(int danio) {
        vida -= danio;

        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 20;
        vida = vidaMaxima;
    }

    public void agregarObjeto(Objeto objeto) {
        inventario.add(objeto);
    }

    public void equiparArma(Arma arma) {
        armaEquipada = arma;
    }

    public void equiparArmadura(Armadura armadura) {
        armaduraEquipada = armadura;
    }

    public int getBonusAtaque() {
        if (armaEquipada != null) {
            return armaEquipada.getValor();
        }
        return 0;
    }

    public int getBonusDefensa() {
        if (armaduraEquipada != null) {
            return armaduraEquipada.getValor();
        }
        return 0;
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public Armadura getArmaduraEquipada() {
        return armaduraEquipada;
    }

    public void mostrarInventario() {
        System.out.println("\nInventario de " + nombre);
        for (Objeto objeto : inventario) {
            System.out.println(objeto);
        }
    }

    public boolean tieneObjetos() {
        return !inventario.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {

        String arma = "Ninguna";
        String armadura = "Ninguna";

        if (armaEquipada != null) {
            arma = armaEquipada.getNombreObj();
        }

        if (armaduraEquipada != null) {
            armadura = armaduraEquipada.getNombreObj();
        }

        return "Nombre: " + nombre
                + "\nVida: " + vida
                + "\nNivel: " + nivel
                + "\nArma equipada: " + arma
                + "\nArmadura equipada: " + armadura;
    }
}
