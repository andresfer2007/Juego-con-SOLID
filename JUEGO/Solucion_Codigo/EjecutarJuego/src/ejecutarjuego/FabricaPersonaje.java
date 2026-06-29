/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutarjuego;

import personajes.Arquero;
import personajes.Guerrero;
import personajes.Mago;
import personajes.Personaje;

public class FabricaPersonaje {

    private static final int[][] STATS_GUERRERO = {
        {100, 1,  8},
        {130, 2, 12},
        {160, 3, 18}
    };

    private static final int[][] STATS_MAGO = {
        { 70, 1, 10},
        { 90, 2, 15},
        {110, 3, 22}
    };

    private static final int[][] STATS_ARQUERO = {
        { 80, 1,  9},
        {100, 2, 13},
        {120, 3, 19}
    };

    public Personaje crear(String nombre, int tipo, int clase) {
        switch (tipo) {
            case 1: return crearGuerrero(nombre, clase);
            case 2: return crearMago(nombre, clase);
            default: return crearArquero(nombre, clase);
        }
    }

    private Guerrero crearGuerrero(String nombre, int clase) {
        int[] s = STATS_GUERRERO[clase - 1];
        return new Guerrero(nombre, s[0], s[1], s[2]);
    }

    private Mago crearMago(String nombre, int clase) {
        int[] s = STATS_MAGO[clase - 1];
        return new Mago(nombre, s[0], s[1], s[2]);
    }

    private Arquero crearArquero(String nombre, int clase) {
        int[] s = STATS_ARQUERO[clase - 1];
        return new Arquero(nombre, s[0], s[1], s[2]);
    }
}