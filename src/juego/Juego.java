/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Administrador                     //implements Runnable -> Implementacion de una interfa
 */
public class Juego extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1; // Si se modifica, guarda la primera version o no se xd
    
    private static final int ANCHO = 800; //Final -> No va cambiar, durante toda la ejecución del programa
    private static final int ALTO = 800;
    private static final String NOMBRE = "Juego"; // Nombre de mi ventana
    
    private static JFrame ventana; //Ventana donde se ejecuta el juego
    private static Thread thread; // Creación de un thread
    
    public Juego(){
        
        setPreferredSize(new Dimension(ANCHO, ALTO));
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana, también se cierra la ejecion
                                                                //del programa
        ventana.setResizable(true); //El usuario no puede cambiar los bordes de la ventana para cambiar su tamaño
        ventana.setLayout(new BorderLayout()); //Diseño de la ventana, organización interna
        ventana.add(this, BorderLayout.CENTER); //Añade el Canvas en el centro de la ventana, no hay ningun hueco entre
                                                //el canvas y el centro de la ventana
        
        ventana.pack(); //Sirve para que todo el contenido de la venta se ajuste al tamaño que queremos
        ventana.setLocationRelativeTo(null); //Fijara la ventana en el centro del escritorio
        ventana.setVisible(true);
        
        
        
    }
    
    public void iniciar(){  //Iniciar el segundo Thread 
      
        thread = new Thread(this, "Graficos");       //this -> identifica a la clase en que estamos trabajando
        thread.start();                                             //"Graficos, nombre del thread
        
    }
    public void detener(){
        
        
        
    }
    
    
    public void run() {
        
        System.out.println("EL thread 2 se está ejecutando con éxito"); 
    }

  
}
