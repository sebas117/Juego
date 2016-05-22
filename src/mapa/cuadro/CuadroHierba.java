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
 */
public class CuadroHierba extends Cuadro{
    
    public CuadroHierba(Sprite sprite) {
        super(sprite);
    }
    
    
    @Override
    public void mostrar(int x, int y, Pantalla pantalla){   //Sobrescribimos el metodo de la clase abstracta
        
        pantalla.mostrarCuadro(x, y, this); //Pasamos la propia clase al metodo
        
    }
}
