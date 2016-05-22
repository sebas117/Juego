/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

/**
 *
 * @author Juansebastian
 * Entorno o escenario
 * 
 * La clase Mapa no podrá instanciarse
 * Especie de plantilla, donde se  podrán crear mas mapas
 */
public abstract class Mapa {
    
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros; //tiles
    
    public Mapa(int ancho, int alto){
        
        this.ancho = ancho;
        this.alto = alto;
        
        cuadros = new int[ancho*alto];
        generarMapa();
        
    }
    
    public Mapa(String ruta){
        
        cargarMapa(ruta);
        
    }
    
    //Mapas aleatoris
    protected void generarMapa(){   
    }
    
    //Mapas predefinidos
    private void cargarMapa(String ruta){
    }
    
    public void actualizar(){
    }
    
    /*
    Bit Shifting -> Nos da velocidad, rendimiento, operaiones matemáticas
    */

    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
        
        int o = compensacionX >> 5;       // /32    Tamaño que se tiene de nuestros sprites -> Nuestro personaje se movera a nivel de tiles
        int e = (compensacionX + pantalla.getAncho()) >> 5;     //Nos da la parte mpas derecha de la pantalla
        int n = compensacionY >>5;
        int s= (compensacionY + pantalla.getAlto()) >>5;
        
        
    }
    
    public Cuadro obtenCuadro(final int x, final int y){
        
        switch(cuadros[x+ y *ancho]){
            
            case 0:
                return Cuadro.HIERBA;
                   
            case 1:
            case 2:
            case 3:
            default:
                
                return null;
            
            
        }
        
    }


}