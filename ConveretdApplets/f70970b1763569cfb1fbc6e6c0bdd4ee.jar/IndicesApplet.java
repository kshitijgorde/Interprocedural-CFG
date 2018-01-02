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
import java.util.Hashtable;
import java.awt.event.MouseListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IndicesApplet extends BaseBannerApplet implements MouseListener
{
    private static final Hashtable h;
    private static final Point i;
    private static final Point j;
    private static final Point k;
    private static final Point l;
    private Vector m;
    private Image n;
    private Graphics o;
    private Image p;
    private Image q;
    private String r;
    private Color s;
    private Color t;
    private int u;
    private int v;
    private boolean w;
    private String[] x;
    private boolean y;
    
    public IndicesApplet() {
        this.r = "Indices by AlphaTrade.com";
        this.s = IndicesApplet.b;
        this.t = IndicesApplet.a;
        this.u = 10;
        this.w = true;
        this.y = true;
    }
    
    public void init() {
        super.init();
        try {
            if (this.getParameter("symbols") != null) {
                this.x = BaseBannerApplet.e(this.getParameter("symbols"));
            }
            else {
                this.x = new String[] { "COMP", "NYA", "&RUT" };
            }
            if (this.getParameter("title") != null) {
                this.r = this.getParameter("title");
            }
            if (this.getParameter("bgColor") != null) {
                this.s = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.t = Color.decode(this.getParameter("fontColor"));
            }
            if (this.getParameter("fontSize") != null) {
                this.u = Integer.parseInt(this.getParameter("fontSize"));
            }
            if (this.getParameter("link") != null) {
                this.y = !this.getParameter("link").equalsIgnoreCase("false");
            }
            if (this.getParameter("showFooter") != null) {
                this.w = !this.getParameter("showFooter").equalsIgnoreCase("false");
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <INDICES> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        try {
            this.p = this.getImage(new URL(this.getCodeBase(), "up.gif"));
            this.q = this.getImage(new URL(this.getCodeBase(), "down.gif"));
        }
        catch (Exception ex2) {
            System.err.println("LYNX <INDICES> - ERROR FINDING IMAGES: " + ex2);
        }
        if (this.y) {
            this.addMouseListener(this);
        }
        if (this.x.length > 15) {
            final String[] x = new String[15];
            for (int i = 0; i < 15; ++i) {
                x[i] = this.x[i];
            }
            this.x = x;
        }
        this.a(this.x);
        this.v = this.u + 2;
    }
    
    public final synchronized void a() {
        this.m = super.g;
        if (this.n == null) {
            this.n = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.o = this.n.getGraphics()).setColor(this.s);
        this.o.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.o.setColor(this.t);
        this.o.setFont(new Font("sansserif", 1, this.u));
        this.o.drawString(this.r, 0, this.u);
        if (this.w) {
            this.o.setFont(new Font("sansserif", 0, this.u - 1));
            this.o.drawString("Quotes delayed at least 15 min.", 0, this.getSize().height - 3);
        }
        this.o.setFont(new Font("sansserif", 0, this.u));
        try {
            for (int i = 0; i < this.m.size(); ++i) {
                final String[] array;
                final String s = (array = this.m.elementAt(i))[0];
                if (IndicesApplet.h.containsKey(s)) {
                    this.o.drawString(IndicesApplet.h.get(s).toString(), 0, IndicesApplet.i.y + i * this.v);
                }
                else {
                    this.o.drawString(s, 0, IndicesApplet.i.y + i * this.v);
                }
                final String s2;
                if ((s2 = array[1]).startsWith("-") || s2.startsWith("+")) {
                    this.o.drawString(s2.substring(1), IndicesApplet.i.x - this.o.getFontMetrics().stringWidth(s2.substring(1)), IndicesApplet.i.y + i * this.v);
                }
                else {
                    this.o.drawString(s2, IndicesApplet.i.x - this.o.getFontMetrics().stringWidth(s2), IndicesApplet.i.y + i * this.v);
                }
                if (this.getSize().width >= 155) {
                    this.o.drawString(array[2], IndicesApplet.j.x - this.o.getFontMetrics().stringWidth(array[2]), IndicesApplet.j.y + i * this.v);
                    if (array[2].startsWith("-")) {
                        this.o.drawImage(this.q, IndicesApplet.l.x, IndicesApplet.l.y + i * this.v, this.s, null);
                    }
                    else {
                        this.o.drawImage(this.p, IndicesApplet.l.x, IndicesApplet.l.y + i * this.v, this.s, null);
                    }
                }
                else if (array[2].startsWith("-")) {
                    this.o.drawImage(this.q, IndicesApplet.k.x, IndicesApplet.k.y + i * this.v, this.s, null);
                }
                else {
                    this.o.drawImage(this.p, IndicesApplet.k.x, IndicesApplet.k.y + i * this.v, this.s, null);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <INDICES> - ERROR DRAWING BUFFER: " + ex);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", ""), "_blank");
        }
        catch (Exception ex) {
            System.err.println("LYNX <INDICES> - ERROR FINDING URL: " + ex);
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
        if (this.n != null) {
            graphics.drawImage(this.n, 0, 0, null);
        }
    }
    
    static {
        i = new Point(92, 27);
        j = new Point(135, 27);
        k = new Point(97, 21);
        l = new Point(140, 21);
        (h = new Hashtable()).put("&DJI", "DJIA");
        IndicesApplet.h.put("COMP", "Nasdaq");
        IndicesApplet.h.put("SPX", "S&P 500");
        IndicesApplet.h.put("NYA", "NYSE");
        IndicesApplet.h.put("&XAX", "AMEX");
        IndicesApplet.h.put("&RUT", "Rus 2000");
        IndicesApplet.h.put("CA;TSEA", "TSX");
        IndicesApplet.h.put("CA;JX", "TSX Ven");
        IndicesApplet.h.put("ca;JX", "TSX Ven");
        IndicesApplet.h.put("$$EURUSD", "Euro");
        IndicesApplet.h.put("$$CADUSD", "Cdn $");
        IndicesApplet.h.put("$$JPYUSD", "Yen ¥");
        IndicesApplet.h.put("@?KRCGL", "Gold");
        IndicesApplet.h.put("@?KRCSL", "Silver");
        IndicesApplet.h.put("@OWTI", "Crude Oil");
        IndicesApplet.h.put(";NGV", "Nat. Gas");
    }
}
