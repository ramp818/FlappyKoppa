package examen2;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyKoopa extends JFrame implements Runnable, KeyListener
{
    private static final long serialVersionUID = 1L;
    //Se declaran las variables.
    //Se declaran variables tipo Image
    private Image dbImage;
    private Image background;
    private Image ImagenGameOver;
    private Image ImagenPausa;
    //Se declaran variables tipo Graphics
    private Graphics dbg;
    //Se declaran variables tipo int
    private int score;
    //Se declaran variables tipo long
    private long tiempoActual;
    private long tiempoInicial;
    //Se declaran variables tipo Pajaro
    private Pajaro koopa;
    //Se declaran variables tipo Planta
    private Planta carnivora;
    //Se declaran variables tipo Animacion
    private Animacion animKoopa;
    private Animacion animCarnivora;
    //Se declaran variables tipo SoundClip
    private SoundClip musicaFondo;
    //Se declaran variables tipo boolean
    private boolean pausa;
    private boolean gameOver;
    
    
    
    public FlappyKoopa(){
       
        //Se inicializan variables
        score=0;
        
        //Se cargan imagenes
        Image Koopa1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb1.gif"));
        Image Koopa2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb2.gif"));
        Image Koopa3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb3.gif"));
        Image Koopa4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb4.gif"));
        Image Koopa5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb1.gif"));
        Image Koopa6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb2.gif"));
        Image Koopa7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb3.gif"));
        Image Koopa8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb4.gif"));
        Image Koopa9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fb1.gif"));
        
        animKoopa= new Animacion();
        animKoopa.sumaCuadro(Koopa1,100);
        animKoopa.sumaCuadro(Koopa2,100);
        animKoopa.sumaCuadro(Koopa3,100);
        animKoopa.sumaCuadro(Koopa4,100);
        animKoopa.sumaCuadro(Koopa5,100);
        animKoopa.sumaCuadro(Koopa6,100);
        animKoopa.sumaCuadro(Koopa7,100);
        animKoopa.sumaCuadro(Koopa8,100);
        animKoopa.sumaCuadro(Koopa9,100);
        
        koopa=new Pajaro(600,300,animKoopa);
        
        //Caracteristicas JFrame
        setBackground(Color.white);
        setSize(1200,600);
        addKeyListener(this);
        
        Thread th = new Thread(this);
        //Empieza el hilo
        th.start();
        
   }
    
   /** 
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se incrementa
     * la posicion en x o y dependiendo de la direccion, finalmente 
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     * 
     */
        public void run() {
            tiempoActual = System.currentTimeMillis();
            while (true) {
                if (!pausa && !gameOver) {
                    //checaColision();
                    actualiza();
            }
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    System.out.println("Error en " + ex.toString());
                }   
            }
        }
        
       /**
	 * Metodo usado para actualizar la posicion de objetos.
	 * 
	 */
        
        public void actualiza(){
           
            //Actualiza animaciones
            long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
            tiempoActual += tiempoTranscurrido;
            koopa.getAnimacion().actualiza(tiempoTranscurrido);
            
        }
        
        /**
	 * Metodo usado para checar las colisiones del objeto Pajaro
	 * con las orillas del <code>JFrame</code>.
	 */
        
        public void checaColision(){
            
        }
        
        /**
	 * Metodo <I>paint</I> sobrescrito de la clase <code>JFrame</code>,
	 * heredado de la clase Container.<P>
	 * En este metodo lo que hace es actualizar el contenedor
	 * @param g es el <code>objeto grafico</code> usado para dibujar.
	 */
	public void paint(Graphics g){
		// Inicializan el DoubleBuffer
		if (dbImage == null){
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}

		// Actualiza la imagen de fondo.
		dbg.setColor(getBackground ());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

		// Actualiza el Foreground.
		dbg.setColor(getForeground());
		paint1(dbg);

		// Dibuja la imagen actualizada
		g.drawImage (dbImage, 0, 0, this);
	}
       
       /**
	 * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>,
	 * heredado de la clase Container.<P>
	 * En este metodo se dibuja la imagen con la posicion actualizada,
	 * ademas que cuando la imagen es cargada te despliega una advertencia.
	 * @param g es el <code>objeto grafico</code> usado para dibujar.
	 */
        
        public void paint1(Graphics g){
            
            if(koopa != null){
                
                g.drawImage(koopa.getAnimacion().getImagen(), koopa.getPosX(), koopa.getPosY(), this);
            }
        }
        
   /**
     * Metodo <I>keyPressed</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar cualquier la
     * tecla.
     *
     * @param e es el <code>evento</code> generado al presionar las teclas.
     */
    public void keyPressed(KeyEvent e) {
        
       if (e.getKeyCode() == KeyEvent.VK_RIGHT) {    //Presiono flecha derecha
                
                    
       } 
       else if (e.getKeyCode() == KeyEvent.VK_P){
                
       pausa=!pausa;
       }
    }

    /**
     * Metodo <I>keyTyped</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void keyTyped(KeyEvent e) {
       
    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla
     * presionada.
     *
     * @param e es el <code>evento</code> que se genera en al soltar las teclas.
     */
    
    public void keyReleased(KeyEvent e) {
        
    }
}


