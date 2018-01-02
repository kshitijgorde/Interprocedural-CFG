import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Display extends Canvas
{
    private int hour;
    private int min;
    private int sec;
    private Dimension dim;
    private int cxImage;
    private Image offImage;
    private Graphics offGraph;
    private static final int COLON = 10;
    private static final int MAX_IMAGES = 11;
    static final Image[] images;
    private static boolean imageInit;
    
    Display() {
        if (!Display.imageInit) {
            Display.imageInit = true;
            for (int i = 0; i < Display.images.length; ++i) {
                Display.images[i] = RCLoadImage.loadImage(this.getClass(), String.valueOf(new Integer(i).toString()) + ".gif", this);
            }
        }
        this.cxImage = Display.images[0].getWidth(null);
        this.dim = new Dimension(this.cxImage * 9, Display.images[0].getHeight(null));
    }
    
    public Dimension getPreferredSize() {
        return this.dim;
    }
    
    public Dimension getMinimumSize() {
        return this.dim;
    }
    
    public void addNotify() {
        super.addNotify();
        this.offImage = this.createImage(this.dim.width, this.dim.height);
        this.offGraph = this.offImage.getGraphics();
    }
    
    public void showTime(final int n) {
        if (n < 0) {
            System.err.println("Display:showTime -- Bad Param: seconds < 0");
        }
        this.hour = n / 3600;
        this.min = n % 3600 / 60;
        this.sec = n % 3600 % 60;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImage == null) {
            System.err.println("BV offImage paint()");
        }
        if (this.offGraph == null) {
            System.err.println("BV offGraph paint()");
        }
        this.offGraph.drawImage(Display.images[this.hour / 10], this.cxImage * 0, 0, null);
        this.offGraph.drawImage(Display.images[this.hour % 10], this.cxImage, 0, null);
        this.offGraph.drawImage(Display.images[10], this.cxImage * 2, 0, null);
        this.offGraph.drawImage(Display.images[this.min / 10], this.cxImage * 3, 0, null);
        this.offGraph.drawImage(Display.images[this.min % 10], this.cxImage * 4, 0, null);
        this.offGraph.drawImage(Display.images[10], this.cxImage * 5, 0, null);
        this.offGraph.drawImage(Display.images[this.sec / 10], this.cxImage * 6, 0, null);
        this.offGraph.drawImage(Display.images[this.sec % 10], this.cxImage * 7, 0, null);
        graphics.drawImage(this.offImage, 0, 0, null);
    }
    
    static {
        images = new Image[11];
    }
}
