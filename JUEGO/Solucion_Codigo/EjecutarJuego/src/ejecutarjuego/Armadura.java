package ejecutarjuego;

public class Armadura extends Objeto {

   private String material;

    public Armadura(String nombreObj, int valor, String material) {
        super(nombreObj, valor);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
   @Override
    public String toString() {
        return "Armadura{" +
                "nombre='" + nombreObj + '\'' +
                ", defensaExtra=" + valor +   
                ", material='" + material + '\'' +
                '}';
    }
}