import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scroller extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    private Thread \u00c0;
    private Image \u00c1;
    private Vector \u00c2;
    private int \u00c3;
    private int \u00c4;
    private int \u00c5;
    private int \u00c6;
    private Color \u00c7;
    private Color \u00c8;
    private Color \u00c9;
    private boolean \u00ca;
    private Object \u00cb;
    Image \u00cc;
    private boolean \u00cd;
    private int \u00ce;
    private int mx;
    private int \u00cf;
    
    public void init() {
        if (this.getParameter("background") != null) {
            this.\u00c7 = Color.decode(this.getParameter("background"));
        }
        this.setBackground(this.\u00c7);
        this.\u00c0.start();
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.\u00c1, 0, 0, this);
        this.\u00ca = true;
        synchronized (this.\u00cb) {
            this.\u00cb.notifyAll();
        }
        // monitorexit(this.\u00cb)
    }
    
    private void \u00c0() {
        final Graphics graphics = this.\u00cc.getGraphics();
        graphics.clearRect(0, 0, this.\u00c5, this.\u00c6);
        for (int i = 0; i < this.\u00c2.size(); ++i) {
            final Scroller$Section scroller$Section = this.\u00c2.elementAt(i);
            if (scroller$Section.display(this.\u00c3, this.\u00c3 + this.\u00c5)) {
                boolean b = false;
                if (Scroller$Section.\u00c2(scroller$Section) - this.\u00c3 < this.mx && Scroller$Section.\u00c2(scroller$Section) + Scroller$Section.\u00c3(scroller$Section) - this.\u00c3 > this.mx && this.mx > 0) {
                    b = true;
                }
                graphics.drawImage(scroller$Section.getImage(b), Scroller$Section.\u00c2(scroller$Section) - this.\u00c3, 0, null);
            }
        }
    }
    
    public void run() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            return;
        }
        if (!parameter.equalsIgnoreCase("(c) 1999 Dan MacFarlane. http://www.dancity.com/")) {
            return;
        }
        if (this.getParameter("textcol") != null) {
            this.\u00c8 = Color.decode(this.getParameter("textcol"));
        }
        if (this.getParameter("linkcol") != null) {
            this.\u00c9 = Color.decode(this.getParameter("linkcol"));
        }
        final Dimension size = this.getSize();
        this.\u00c5 = size.width;
        this.\u00c6 = size.height;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        new Scroller$TextParser(this, this.\u00c5, String.valueOf(this.getParameter("text")) + "    This Scroller came from DanCity - visit <a href='http://www.dancity.com' value='www.dancity.com'> for more applets.");
        this.\u00c1 = this.createImage(this.\u00c5, this.\u00c6);
        this.\u00cc = this.createImage(this.\u00c5, this.\u00c6);
        while (this.\u00c1 != null) {
            if (this.\u00cd) {
                this.\u00c3 = this.\u00cf + this.\u00ce - this.mx;
            }
            else {
                ++this.\u00c3;
            }
            if (this.\u00c3 > this.\u00c4) {
                this.\u00c3 = 0;
            }
            this.\u00c1.getGraphics().drawImage(this.\u00cc, 0, 0, this);
            this.\u00ca = false;
            this.repaint();
            this.\u00c0();
            synchronized (this.\u00cb) {
                while (!this.\u00ca) {
                    try {
                        this.\u00cb.wait(100L);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Error in synchronized wait(): " + ex);
                        System.exit(0);
                    }
                }
            }
            // monitorexit(this.\u00cb)
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {
                System.out.println("Error in thread sleep(): " + ex2);
                System.exit(0);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.\u00c2.size(); ++i) {
            final Scroller$Section scroller$Section = this.\u00c2.elementAt(i);
            if (scroller$Section.display(this.\u00c3, this.\u00c3 + this.\u00c5) && Scroller$Section.\u00c4(scroller$Section) && Scroller$Section.\u00c2(scroller$Section) - this.\u00c3 < this.mx && Scroller$Section.\u00c2(scroller$Section) + Scroller$Section.\u00c3(scroller$Section) - this.\u00c3 > this.mx && this.mx > 0) {
                Scroller$Section.\u00c5(scroller$Section);
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mx = 0;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u00cf = this.\u00c3;
        final int x = mouseEvent.getX();
        this.mx = x;
        this.\u00ce = x;
        this.\u00cd = true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u00cd = false;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mx = mouseEvent.getX();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mx = mouseEvent.getX();
    }
    
    public Image loadImage(final String s) {
        URL url;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex2) {
            System.out.println("bad url");
            return null;
        }
        final Image image = this.getImage(url);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.out.println("Error loading images(): " + ex);
        }
        return image;
    }
    
    private int \u00c1(final String s) {
        return this.getGraphics().getFontMetrics().stringWidth(s);
    }
    
    public Scroller() {
        this.\u00c0 = new Thread(this);
        this.\u00c2 = new Vector();
        this.\u00c7 = Color.black;
        this.\u00c8 = Color.white;
        this.\u00c9 = Color.blue;
        this.\u00ca = false;
        this.\u00cb = new Object();
        this.\u00cd = false;
    }
    
    static int \u00c2(final Scroller scroller) {
        return scroller.\u00c4;
    }
    
    static void \u00c3(final Scroller scroller, final int \u00e4) {
        scroller.\u00c4 = \u00e4;
    }
    
    static Vector \u00c4(final Scroller scroller) {
        return scroller.\u00c2;
    }
    
    static int \u00c5(final Scroller scroller, final String s) {
        return scroller.\u00c1(s);
    }
    
    static Color \u00c6(final Scroller scroller) {
        return scroller.\u00c7;
    }
    
    static Color \u00c7(final Scroller scroller) {
        return scroller.\u00c8;
    }
    
    static Color \u00c8(final Scroller scroller) {
        return scroller.\u00c9;
    }
}
