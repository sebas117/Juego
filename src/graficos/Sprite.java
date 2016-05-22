/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

/**
 *
 * @author Juansebastian
 */
public final class Sprite {
    
    private final int lado; //Tamaño del sprite (Lado de cada uno de los cuadrados)
    
    private int x;
    private int y;
    
     
    public int[] pixeles; //Coleccion de colores del sprite
    private  HojaSprites hoja;
    
    //Coleccion Sprites 
    public final static Sprite VACIO = new Sprite(32,0);
    public final static Sprite HIERBA = new Sprite(32, 4,5, HojaSprites.desierto);
    
    
    //Fin de la coleccion
    
    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
        
        this.lado = lado;
        pixeles = new int[lado*lado];   //Pixeles por cada cuadrado
        
        this.x = columna*lado;
        this.y = fila*lado;
        this.hoja =  hoja;
        
        for(int y=0; y< lado; y++){
            
            for(int x=0; x<lado; x++){
                
                pixeles[x+y * lado] = hoja.pixeles[(x+this.x) + (y+this.y) * hoja.getAncho()]; //Va llenando toda la hoja de sprites, sprite por sprite
                
            }
            
            
            
        }
        
    }
    
    public Sprite(final int lado, final int color){
        
        this.lado = lado;
        pixeles = new int[lado*lado];
        
        for (int i = 0; i<pixeles.length; i++){
            
            pixeles[i] = color;
            
        }
        
        
    }

    public int getLado() {
        return lado;
    }
    
    
    
    
}
