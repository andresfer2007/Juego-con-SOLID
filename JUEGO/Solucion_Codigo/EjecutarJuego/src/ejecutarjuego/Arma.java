package ejecutarjuego;

public class Arma extends Objeto {
    private String tipo;
    private int durabilidad;

    public Arma(String nombreObj, int valor, String tipo, int durabilidad) {
        super(nombreObj, valor);
        this.tipo = tipo;
        this.durabilidad = durabilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }
    
    @Override
    public String toString() {
        return "Arma{" +
                "nombre='" + nombreObj + '\'' +
                ", ataqueExtra=" + valor +
                ", tipo='" + tipo + '\'' +
                ", durabilidad=" + durabilidad +
                '}';
    }
}