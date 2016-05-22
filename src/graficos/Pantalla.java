/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import mapa.cuadro.Cuadro;

/**
 *
 * @author Juansebastian
 */
public final class Pantalla {

    private final int ancho;    //Dimensiones ventan
    private final int alto;

    public final int[] pixeles;

    //Temporales 
    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_SPRITE = LADO_SPRITE - 1; //Límite del Sprite 

    //Fin Temporal
    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        pixeles = new int[ancho * alto];
    }

    public void limpiar() {  //Recorre toda la pantalla para limpiarla y colorear de negro durante la ejecucion

        for (int i = 0; i < pixeles.length; i++) {

            pixeles[i] = 0;

        }

    }

    //Metodo Temporal
    public void mostrar(final int compensacionX, final int compensacionY) {          //Compensacio X y Y -> Movimiento que estemos realizando con las flechas 

        for (int y = 0; y < alto; y++) {
            int posicionY = y + compensacionY;

            if (posicionY < 0 || posicionY >= alto) { //Nos aseguramos que no nos salgamos del mapa

                continue;
            }

            for (int x = 0; x < ancho; x++) {

                int posicionX = x + compensacionX;

                if (posicionX < 0 || posicionX >= ancho) { //Nos aseguramos que no nos salgamos del mapa

                    continue;
                }

                //Temporal
                pixeles[posicionX + posicionY * ancho] = Sprite.HIERBA.pixeles[(x & MASCARA_SPRITE) + (y & MASCARA_SPRITE) * LADO_SPRITE];

                //& --> Si X es 31 vuelva a valer cero
            }

        }

    }
    //Fin Temporal
    
    //Se actualiza de izquierda a derecha y de arriba a abajo 
    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {

        for (int y = 0; y < cuadro.sprite.getLado(); y++) {

            int posicionY = y + compensacionY;

            for (int x = 0; x < cuadro.sprite.getLado(); x++) {

                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX > ancho || posicionY < 0 || posicionY > alto) {    //Si se intenta dibujar algo fuera de la pantalla

                    break; //Salimos del bucle

                }
                
                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
            }

        }

    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    

}
