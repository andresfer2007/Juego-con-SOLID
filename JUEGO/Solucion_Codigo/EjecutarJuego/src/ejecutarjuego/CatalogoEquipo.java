/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutarjuego;

/**
 *
 * @author msjim
 */
import inventario.Arma;
import inventario.Armadura;

public class CatalogoEquipo {

    private static final Arma[] ARMAS = {
        new Arma("Espada de Hierro", 10, "Espada", 100),
        new Arma("Hacha Berserker", 15, "Hacha", 80),
        new Arma("Baston Arcano", 12, "Baston", 90),
        new Arma("Arco Largo", 11, "Arco", 95),
        new Arma("Daga Envenenada", 8, "Daga", 70),
        new Arma("Lanza de Titan", 18, "Lanza", 60)
    };

    private static final Armadura[] ARMADURAS = {
        new Armadura("Armadura de Cuero", 5, "Cuero"),
        new Armadura("Armadura de Hierro", 10, "Hierro"),
        new Armadura("Tunica Magica", 7, "Tela Encantada"),
        new Armadura("Cota de Malla", 12, "Acero"),
        new Armadura("Escudo de Roble", 8, "Madera"),
        new Armadura("Placas de Dragon", 20, "Escamas")
    };

    public Arma[] getArmas() {
        return ARMAS.clone();
    }

    public Armadura[] getArmaduras() {
        return ARMADURAS.clone();
    }
}
