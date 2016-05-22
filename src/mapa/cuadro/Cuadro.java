/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 *
 * @author Juansebastian
 *
 * No podrá instanciarse (Especie de plantilla)
 */
public abstract class Cuadro {

    public int x;
    public int y;

    public Sprite sprite;
    
    
    //Coleccion de cuadros 
    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
    public static final Cuadro HIERBA = new CuadroHierba(Sprite.HIERBA);
    
    
    
    //Fin de la coleccion de cuadros

    public Cuadro(Sprite sprite) {

        this.sprite = sprite;

    }

    public void mostrar(int x, int y, Pantalla pantalla) {

    }

    public boolean solido() {   //Se puede o no atravesar sobre este tile?

        return false;
    }

}
