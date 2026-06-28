package ejecutarjuego;

public abstract class Objeto {

    protected String nombreObj;
    protected int valor;

    public Objeto(String nombreObj, int valor) {
        this.nombreObj = nombreObj;
        this.valor = valor;
    }

    public String getNombreObj() {
        return nombreObj;
    }

    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}