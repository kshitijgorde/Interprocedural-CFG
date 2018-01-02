import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Menu extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    private Image[] imagenes;
    private int yRaton;
    private int velocidadHilo;
    private int cargarArray;
    private int altoImagen;
    private Thread hilo;
    private Image buffer;
    private ControladorImg[] controladorImg;
    private String[] direccionesWeb;
    
    public void init() {
        this.cargarArray = Integer.parseInt(this.getParameter("numeroMenus"));
        this.setBackground(Color.white);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.buffer = this.createImage(this.getBounds().width, this.getBounds().height);
        this.imagenes = new Image[this.cargarArray];
        this.controladorImg = new ControladorImg[this.cargarArray];
        this.direccionesWeb = new String[this.cargarArray];
        this.velocidadHilo = 0;
        this.hilo = null;
        this.cargarParametros();
        this.cargaImagenes();
    }
    
    public void start() {
        if (this.hilo == null) {
            (this.hilo = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.hilo = null;
    }
    
    private void cargarParametros() {
        this.altoImagen = Integer.parseInt(this.getParameter("altoImagen"));
        this.velocidadHilo = Integer.parseInt(this.getParameter("velocidad"));
        for (int n = 0; n < this.cargarArray && this.getParameter("menu" + n) != null; ++n) {
            this.direccionesWeb[n] = this.getParameter("url" + n);
            this.controladorImg[n] = new ControladorImg(n * this.altoImagen, this.direccionesWeb[n], this);
            this.imagenes[n] = this.getImage(this.getCodeBase(), this.getParameter("menu" + n));
        }
    }
    
    private void cargaImagenes() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < this.imagenes.length; ++i) {
            mediaTracker.addImage(this.imagenes[i], i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        while (true) {
            for (int i = 0; i < this.controladorImg.length; ++i) {
                this.controladorImg[i].aumentaAltura();
                if (this.controladorImg[i].getAltura() > this.getBounds().height) {
                    this.controladorImg[i].setAltura(-this.altoImagen);
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.velocidadHilo);
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(this.buffer.getGraphics());
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        graphics.setColor(Color.white);
        for (int i = 0; i < this.imagenes.length; ++i) {
            graphics.drawImage(this.imagenes[i], 0, this.controladorImg[i].getAltura(), this);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.yRaton = mouseEvent.getY();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.imagenes.length; ++i) {
            this.controladorImg[i].comprobarImagen(this.yRaton);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.hilo.suspend();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.hilo.resume();
    }
}
