/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Juansebastian
 */
public final class Teclado implements KeyListener {

    private final static int numeroTeclas = 120; //Debe de ser tan grande como el codigo de la tecla mas alta que vamos utilizar
    private final boolean[] teclas = new boolean[numeroTeclas];

    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;

    public void actualizar() {

        arriba = teclas[KeyEvent.VK_W]; //La tecla arriba va equivaler a pulsar la tecla "W"
        abajo = teclas[KeyEvent.VK_S]; //La tecla abajo va equivaler a pulsar la tecla "S"
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];

    }

    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {

    }

}
