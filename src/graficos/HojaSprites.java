/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Juansebastian
 */
public class HojaSprites {
    
    private final int ancho;    //Ancho imagen
    private final int alto;     //Alto imagen
    public final int [] pixeles;
    
    //Coleccion Hojas de Sprite
    public static HojaSprites desierto = new HojaSprites("/texturas/Escenario.jpg", 320, 320);
            
    //Fin de la coleccion
    
    public HojaSprites(final String ruta, final int ancho, final int alto){
        
        this.ancho = ancho;
        this.alto= alto;
        
        
        pixeles = new int[ancho*alto];  //ancho*alto = #pixeles
        
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta)); //Se crea una imagen y se le atribuye el valor de una ruta
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
    }

    public int getAncho() {
        return ancho;
    }
    
   
    
}
