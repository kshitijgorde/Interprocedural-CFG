import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Component;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerApplet extends BaseBannerApplet implements MouseListener
{
    private p h;
    private Vector i;
    private String[] j;
    private Image k;
    private Graphics l;
    private Image m;
    private Graphics n;
    private int o;
    private int p;
    private Color q;
    private Color r;
    private Color s;
    private Color t;
    private boolean u;
    private Image v;
    
    public TickerApplet() {
        this.q = TickerApplet.b;
        this.r = Color.white;
        this.s = TickerApplet.a;
        this.t = Color.black;
        this.u = true;
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        try {
            if (this.getParameter("symbols") != null) {
                this.j = BaseBannerApplet.e(this.getParameter("symbols"));
            }
            else {
                this.j = new String[] { "&DJI", "COMP", "&SPX" };
            }
            if (this.getParameter("bgColor") != null) {
                this.q = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fgColor") != null) {
                this.r = Color.decode(this.getParameter("fgColor"));
            }
            if (this.getParameter("frameColor") != null) {
                this.s = Color.decode(this.getParameter("frameColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.t = Color.decode(this.getParameter("fontColor"));
            }
            if (this.getParameter("logo") != null) {
                this.u = !this.getParameter("logo").equalsIgnoreCase("false");
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <TICKER> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        try {
            this.v = this.getImage(new URL(this.getCodeBase(), "alphalogo.gif"));
        }
        catch (Exception ex2) {
            System.err.println("LYNX <TICKER> - ERROR FINDING IMAGE: " + ex2);
        }
        if (this.u) {
            (this.h = new p(this.v, this.q)).setBounds(this.getSize().width - 65, 15, 62, 29);
            this.h.addMouseListener(this);
            this.add(this.h);
        }
        this.b();
        this.a(this.j);
        super.e = 20L;
    }
    
    public final synchronized void b() {
        if (this.k == null) {
            if (this.l != null) {
                this.l.dispose();
                this.l = null;
            }
            this.k = this.createImage(this.getSize().width, this.getSize().height);
            if (this.k == null) {
                return;
            }
            this.l = this.k.getGraphics();
        }
        this.l.setColor(this.s);
        this.l.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.l.setColor(this.q);
        this.l.fillRect(3, 15, this.getSize().width - 6, this.getSize().height - 30);
        this.l.setFont(new Font("sansserif", 0, 10));
        this.l.setColor(this.t);
        this.l.drawString("Loading market data.", 8, 33);
        this.l.setColor(this.r);
        if (this.u) {
            this.l.drawString("Powered by:", this.getSize().width - 60, 11);
        }
        else {
            this.l.drawString("©AlphaTrade.com", this.getSize().width - 90, this.getSize().height - 3);
        }
        this.l.drawString("Quotes delayed at least 15 min.", 8, this.getSize().height - 3);
        if (this.u) {
            this.h.repaint();
        }
        this.repaint();
    }
    
    public final synchronized void a() {
        this.i = super.g;
        if (this.i.size() <= 0) {
            return;
        }
        this.p = this.i.size() * 150;
        if (this.m == null) {
            if (this.n != null) {
                this.n.dispose();
                this.n = null;
            }
            this.m = this.createImage(this.getSize().width + this.p, 30);
            if (this.m == null) {
                return;
            }
            this.n = this.m.getGraphics();
        }
        try {
            final Image image;
            final Graphics graphics;
            (graphics = (image = this.createImage(this.p, 30)).getGraphics()).setColor(this.q);
            graphics.fillRect(0, 0, this.p, 30);
            graphics.setColor(this.t);
            for (int i = 0; i < this.i.size(); ++i) {
                final String[] array = this.i.elementAt(i);
                graphics.setFont(new Font("sansserif", 0, 12));
                graphics.drawString(array[0], i * 150, 16);
                final int n = 10 + graphics.getFontMetrics().stringWidth(array[0]);
                if (array[1].startsWith("+") || array[1].startsWith("-")) {
                    graphics.drawString(array[1].substring(1), n + i * 150, 16);
                }
                else {
                    graphics.drawString(array[1], n + i * 150, 16);
                }
                final int n2 = 5 + n + graphics.getFontMetrics().stringWidth(array[1]);
                graphics.setFont(new Font("sansserif", 0, 10));
                graphics.drawString(array[8], n + i * 150, 25);
                graphics.drawString(array[2], n2 + i * 150, 12);
            }
            for (int n3 = this.getSize().width / this.p + 2, j = 0; j < n3; ++j) {
                this.n.drawImage(image, j * this.p, 0, null);
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <TICKER> - ERROR DRAWING BUFFER: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof p) {
            try {
                this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", "index.html"), "_blank");
            }
            catch (Exception ex) {
                System.err.println("LYNX <TICKER> - ERROR FINDING URL: " + ex);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof p) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof p) {
            this.setCursor(new Cursor(0));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.k == null) {
            this.b();
        }
        if (this.m != null) {
            ++this.o;
            this.o %= this.p;
            this.l.drawImage(this.m, -this.o, 15, null);
            this.l.setColor(this.s);
            this.l.fillRect(0, 0, 3, this.getSize().height);
            this.l.fillRect(this.getSize().width - 3, 0, 3, this.getSize().height);
        }
        graphics.drawImage(this.k, 0, 0, null);
    }
}
