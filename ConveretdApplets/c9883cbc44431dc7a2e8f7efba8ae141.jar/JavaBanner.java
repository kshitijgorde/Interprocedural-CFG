import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaBanner extends Applet implements Runnable
{
    private Graphics offG;
    private Dimension size;
    private Image offImage;
    private Thread animate;
    private Color bgcolor;
    private long delay;
    private String target;
    private ImageList list;
    
    public void destroy() {
        this.animate = null;
    }
    
    public void init() {
        this.size = this.size();
        final String parameter = this.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter, 16));
        }
        else {
            this.bgcolor = Color.white;
        }
        this.setBackground(this.bgcolor);
        this.offImage = this.createImage(this.size.width, this.size.height);
        this.offG = this.offImage.getGraphics();
        final String parameter2 = this.getParameter("delay");
        if (parameter2 != null) {
            this.delay = Integer.parseInt(parameter2);
        }
        else {
            this.delay = 100L;
        }
        final String parameter3 = this.getParameter("target");
        if (parameter3 != null) {
            this.target = new String(parameter3);
        }
        else {
            this.target = "_self";
        }
        (this.list = new ImageList(this)).loadImages();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.getAppletContext().showDocument(this.list.getURL(), this.target);
        if (this.target.equals("_self")) {
            this.stop();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.list.setMouseEnter(true);
        this.showStatus(this.list.getURLString());
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.list.setMouseEnter(false);
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void run() {
        while (Thread.currentThread() == this.animate) {
            try {
                this.list.update();
                this.repaint();
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void start() {
        if (this.animate == null || !this.animate.isAlive()) {
            this.animate = new Thread(this);
        }
        this.animate.start();
    }
    
    public void stop() {
        if (this.animate != null && this.animate.isAlive()) {
            this.animate.stop();
        }
    }
    
    public void update(final Graphics graphics) {
        this.list.paint(this.offG);
        this.paint(graphics);
    }
}
