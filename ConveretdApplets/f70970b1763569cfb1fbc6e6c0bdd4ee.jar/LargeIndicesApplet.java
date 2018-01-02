import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Point;
import java.awt.event.MouseListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LargeIndicesApplet extends BaseBannerApplet implements MouseListener
{
    private static final Point h;
    private static final Point i;
    private static final Point j;
    private static final Point k;
    private Vector l;
    private Image m;
    private Graphics n;
    private Image o;
    private Image p;
    private String q;
    private Color r;
    private Color s;
    private String[] t;
    
    public LargeIndicesApplet() {
        this.q = "US Indices by AlphaTrade.com";
        this.r = LargeIndicesApplet.b;
        this.s = LargeIndicesApplet.a;
    }
    
    public void init() {
        super.init();
        try {
            if (this.getParameter("symbols") != null) {
                this.t = BaseBannerApplet.e(this.getParameter("symbols"));
            }
            else {
                this.t = new String[] { "&DJI", "COMP", "&SPX" };
            }
            if (this.getParameter("title") != null) {
                this.q = this.getParameter("title");
            }
            if (this.getParameter("bgColor") != null) {
                this.r = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.s = Color.decode(this.getParameter("fontColor"));
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <LARGEINDICES> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        try {
            this.o = this.getImage(new URL(this.getCodeBase(), "up.gif"));
            this.p = this.getImage(new URL(this.getCodeBase(), "down.gif"));
        }
        catch (Exception ex2) {
            System.err.println("LYNX <LARGEINDICES> - ERROR FINDING IMAGES: " + ex2);
        }
        this.addMouseListener(this);
        this.a(this.t);
    }
    
    public final synchronized void a() {
        this.l = super.g;
        if (this.m == null) {
            this.m = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.n = this.m.getGraphics()).setColor(this.r);
        this.n.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.n.setColor(this.s);
        this.n.setFont(new Font("sansserif", 1, 18));
        this.n.drawString(this.q, 0, 20);
        this.n.setFont(new Font("sansserif", 0, 10));
        this.n.drawString("Quotes delayed at least 15 min.", 0, this.getSize().height - 3);
        this.n.setFont(new Font("sansserif", 0, 18));
        try {
            for (int i = 0; i < this.l.size(); ++i) {
                final String[] array;
                final String s;
                if ((s = (array = ((String[])this.l.elementAt(i)).clone())[0]).equals("&DJI")) {
                    this.n.drawString("DJIA", 0, LargeIndicesApplet.h.y + i * 25);
                }
                else if (s.equals("COMP")) {
                    this.n.drawString("Nasdaq", 0, LargeIndicesApplet.h.y + i * 25);
                }
                else if (s.equals("&SPX")) {
                    this.n.drawString("S&P 500", 0, LargeIndicesApplet.h.y + i * 25);
                }
                else if (s.equals("&XAX")) {
                    this.n.drawString("AMEX", 0, LargeIndicesApplet.h.y + i * 25);
                }
                else if (s.equals("&RUT")) {
                    this.n.drawString("Rus 2000", 0, LargeIndicesApplet.h.y + i * 25);
                }
                else {
                    this.n.drawString(s, 0, LargeIndicesApplet.h.y + i * 25);
                }
                final String s2;
                if ((s2 = array[1]).startsWith("-") || s2.startsWith("+")) {
                    this.n.drawString(s2.substring(1), LargeIndicesApplet.h.x - this.n.getFontMetrics().stringWidth(s2.substring(1)), LargeIndicesApplet.h.y + i * 25);
                }
                else {
                    this.n.drawString(s2, LargeIndicesApplet.h.x - this.n.getFontMetrics().stringWidth(s2), LargeIndicesApplet.h.y + i * 25);
                }
                if (this.getSize().width >= 155) {
                    this.n.drawString(array[2], LargeIndicesApplet.i.x - this.n.getFontMetrics().stringWidth(array[2]), LargeIndicesApplet.i.y + i * 25);
                    if (array[2].startsWith("-")) {
                        this.n.drawImage(this.p, LargeIndicesApplet.k.x, LargeIndicesApplet.k.y + i * 25, this.r, null);
                    }
                    else {
                        this.n.drawImage(this.o, LargeIndicesApplet.k.x, LargeIndicesApplet.k.y + i * 25, this.r, null);
                    }
                }
                else if (array[2].startsWith("-")) {
                    this.n.drawImage(this.p, LargeIndicesApplet.j.x, LargeIndicesApplet.j.y + i * 25, this.r, null);
                }
                else {
                    this.n.drawImage(this.o, LargeIndicesApplet.j.x, LargeIndicesApplet.j.y + i * 25, this.r, null);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <LARGEINDICES> - ERROR DRAWING BUFFER: " + ex);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", ""), "_blank");
        }
        catch (Exception ex) {
            System.err.println("LYNX <LARGEINDICES> - ERROR FINDING URL: " + ex);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.m != null) {
            graphics.drawImage(this.m, 0, 0, null);
        }
    }
    
    static {
        h = new Point(150, 50);
        i = new Point(220, 50);
        j = new Point(155, 42);
        k = new Point(225, 42);
    }
}
