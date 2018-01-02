import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaClock extends Applet implements Runnable
{
    private ParamParser param;
    private Dimension size;
    private Image offImage;
    private Graphics offG;
    private Thread animate;
    private String link;
    private String target;
    private long delay;
    private Color bgcolor;
    private Font cfont;
    private FontMetrics cfm;
    private Rectangle arect;
    private int border;
    private AnalogClock aclock;
    
    public void init() {
        this.size = this.size();
        this.param = new ParamParser(this);
        this.setBackground(this.bgcolor = this.param.parseColor("bgcolor", Color.white));
        this.delay = this.param.parseInt("delay", 100);
        this.link = this.param.parseString("link", null);
        this.target = this.param.parseString("target", "_self");
        this.offImage = this.createImage(this.size.width, this.size.height);
        (this.offG = this.offImage.getGraphics()).setColor(this.bgcolor);
        this.offG.fillRect(0, 0, this.size.width, this.size.height);
        this.border = this.param.parseInt("border", 10);
        this.arect = new Rectangle(this.border, this.border, this.size.width - 2 * this.border, this.size.height - 2 * this.border);
        this.cfont = this.param.parseFont("cfont", "TimesRoman", 0, 10);
        this.offG.setFont(this.cfont);
        this.cfm = this.offG.getFontMetrics();
        this.aclock = new AnalogClock(this.arect, this.param, this.cfm);
    }
    
    public void start() {
        if (this.animate == null || !this.animate.isAlive()) {
            this.animate = new Thread(this);
        }
        this.animate.start();
    }
    
    public void run() {
        while (Thread.currentThread() == this.animate) {
            try {
                this.repaint();
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void stop() {
        if (this.animate != null && this.animate.isAlive()) {
            this.animate.stop();
        }
    }
    
    public void destroy() {
        this.animate = null;
    }
    
    public void update(final Graphics graphics) {
        this.aclock.draw(this.offG);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.link != null) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.link), this.target);
                if (this.target.equals("_self")) {
                    this.stop();
                }
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.link != null) {
            this.showStatus(this.link);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.link != null) {
            this.showStatus("");
        }
        return true;
    }
}
