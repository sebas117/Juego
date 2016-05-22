/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import control.Teclado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Administrador
 *
 * Version minima de java: 1.5
 */
public class Juego extends Canvas implements Runnable { //implements Runnable -> Implementacion de una interfaz

    private static final long serialVersionUID = 1; // Si se modifica, guarda la primera version o no se xd

    private static final int ANCHO = 800; //Final -> No va cambiar, durante toda la ejecución del programa
    private static final int ALTO = 600;

    private static volatile boolean enFuncionamiento = false; //Ésta variable no podrá utilizarse de forma simultanea por los dos threads
    private static final String NOMBRE = "Juego"; // Nombre de mi ventana

    private static int aps = 0; // Actualizaciones por segundo
    private static int fps = 0; // Frame per second

    private static int x = 0;
    private static int y = 0;

    private static JFrame ventana; //Ventana donde se ejecuta el juego
    private static Thread thread; // Creación de un thread
    private static Teclado teclado;
    private static Pantalla pantalla;

    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);  //Cargamos una nueva imagen en buffered, IMAGEN 
    //EN blanco, modo de color que usan los pC

    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData(); //NOS DEVUELVE UN ARRAY DE INTS QUE REPRESENTA LOS 
    //PIXELES DE LA IMAGEN
    
    public static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/iconos/Pokemon.png"));

    public Juego() {

        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);
        teclado = new Teclado();
        addKeyListener(teclado); // Detecta toda las teclas que se pulsen en esta clase

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana, también se cierra la ejecion
        //del programa
        ventana.setResizable(false); //El usuario no puede cambiar los bordes de la ventana para cambiar su tamaño
        ventana.setIconImage(icono.getImage());
        ventana.setLayout(new BorderLayout()); //Diseño de la ventana, organización interna
        ventana.add(this, BorderLayout.CENTER); //Añade el Canvas en el centro de la ventana, no hay ningun hueco entre
        //el canvas y el borde de la ventana

        ventana.pack(); //Sirve para que todo el contenido de la venta se ajuste al tamaño que queremos
        ventana.setLocationRelativeTo(null); //Fijara la ventana en el centro del escritorio
        ventana.setVisible(true);

    }
    // synchhronized -> se asegura que los dos métodos no puedan modificar simultanemanete la variable "enFuncionamiento"

    public synchronized void iniciar() {  //Iniciar el segundo Thread 
        enFuncionamiento = true;

        thread = new Thread(this, "Graficos");       //this -> identifica a la clase en que estamos trabajando
        thread.start();                                             //"Graficos, nombre del thread

    }

    public synchronized void detener() {

        enFuncionamiento = false;
        try {                                     //Si se presenta algo, lo informará a la consola y el juego seguiría normal su curso
            thread.join();                    //.join -> No para inmediatamente el thred, espera que termina lo que tenga que hacer y luego si lo detiene
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizar() {

        teclado.actualizar();
        if (teclado.arriba) {
            
            y++;
           
        }
        if (teclado.abajo) {

            y--;
        }
        if (teclado.izquierda) {

           x++;
        }
        if (teclado.derecha) {

            x--;
        }
        aps++;

    }

    /*
    Un Buffer es un espacio de memoria que guard cosas, y en este caso va crear nuestras imagenes los dibujos
    Sin un buffer lo que haríamos es preparar la imagen y dibujarla en la pantalla
    
     */
    private void mostrar() {

        BufferStrategy estrategia = getBufferStrategy();

        if (estrategia == null) {

            createBufferStrategy(3);    //3 espacios en la memoria
            return;
        }

        pantalla.limpiar();
        pantalla.mostrar(x, y);
        
        /* Este bucle es un poco costos para el ordenador
        for (int i = 0; i < pixeles.length; i++) {

            pixeles[i] = pantalla.pixeles[i];   //Los pixeles de la clase pantalla se van a copiar a los pixeles de esta clase Juego

        }
        */
        
        //Finalmente se dibua todo
        
        Graphics g = estrategia.getDrawGraphics(); // g se va encargar de dibujar todooo, dibuja todo lo que este dentro de la estrategia(Buffer)
        
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null); //me dibuja desde el 0,0 con el ancho y la altura de la imagen
        g.dispose(); //Una vez que g ha dinujado la imagen, g se destruira, todo sera más eficiente
        
        estrategia.show(); //Se mostrara lo que este en pantalla 
        
        
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length); //Hace exactamente lo mismo, y es menos costosso
        fps++;      //Actualiza el contador de frames por segundo

    }

    public void run() {

        //System.currentTimeMillis();  Es mejor no usarlo 
        final int NS_POR_SEGUNDO = 1000000000; //Cuantos nanosegundos hay en un segundo (REFERENCIA)
        final byte APS_OBJETIVO = 60; // Actualizaciones por segundo
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; //Nanosegundos que transcurren por actualización 

        long referenciaActualizacion = System.nanoTime(); // Se atribuye una cantidad de nanosegundos en un tiempo exacto
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0; // Cantidad de tiempo que ha transcurrido desde que se realiza una actualizacion

        requestFocus();

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime(); // Se toma una referencia inicial para el bucle
            tiempoTranscurrido = inicioBucle - referenciaActualizacion; //Tiempo transcurrido entre IB y RA
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {

                actualizar();
                delta--;

            }

            mostrar();

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                ventana.setTitle(NOMBRE + "|| APS: " + aps + " || FPS: " + fps);

                aps = 0;
                fps = 0;

                referenciaContador = System.nanoTime();

            }

        }
    }

}
