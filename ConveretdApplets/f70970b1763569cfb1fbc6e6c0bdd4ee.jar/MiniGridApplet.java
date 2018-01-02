import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MiniGridApplet extends BaseBannerApplet implements ActionListener, MouseListener
{
    private p h;
    private static final String[] i;
    private Vector j;
    private String[] k;
    private Image l;
    private Graphics m;
    private Image n;
    private Graphics o;
    private int p;
    private int q;
    private Label[] r;
    private Label s;
    private TextField t;
    private Color u;
    private Color v;
    private Color w;
    private Color x;
    private boolean y;
    private Image z;
    private int A;
    
    public MiniGridApplet() {
        this.u = MiniGridApplet.b;
        this.v = Color.white;
        this.w = MiniGridApplet.a;
        this.x = Color.black;
        this.y = true;
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        try {
            if (this.getParameter("symbol") != null) {
                this.k = BaseBannerApplet.e(this.getParameter("symbol"));
            }
            else {
                this.k = new String[] { "APTD" };
            }
            if (this.getParameter("bgColor") != null) {
                this.u = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fgColor") != null) {
                this.v = Color.decode(this.getParameter("fgColor"));
            }
            if (this.getParameter("frameColor") != null) {
                this.w = Color.decode(this.getParameter("frameColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.x = Color.decode(this.getParameter("fontColor"));
            }
            if (this.getParameter("logo") != null) {
                this.y = !this.getParameter("logo").equalsIgnoreCase("false");
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <MINI GRID> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        if (this.getSize().width >= 450) {
            this.p = 3;
            this.q = 100;
        }
        else {
            this.p = 2;
            this.q = 58;
        }
        try {
            this.z = this.getImage(new URL(this.getCodeBase(), "alphalogo.gif"));
        }
        catch (Exception ex2) {
            System.err.println("LYNX <MINI GRID> - ERROR FINDING IMAGE: " + ex2);
        }
        this.b();
        this.a(this.k);
    }
    
    private void b() {
        this.r = new Label[this.p];
        this.A = 0;
        while (this.A < this.p) {
            (this.r[this.A] = new Label(MiniGridApplet.i[this.A])).setBounds(this.q + this.A * this.q, 0, this.q, 15);
            this.r[this.A].setFont(new Font("sansserif", 0, 10));
            this.r[this.A].setForeground(this.v);
            this.r[this.A].setBackground(this.w);
            this.r[this.A].addMouseListener(this);
            this.add(this.r[this.A]);
            ++this.A;
        }
        (this.t = new TextField()).setBounds(4, 29, 50, 15);
        this.t.setFont(new Font("sansserif", 0, 10));
        this.t.setForeground(this.x);
        this.t.addActionListener(this);
        this.add(this.t);
        if (this.n == null) {
            this.n = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.o = this.n.getGraphics()).setColor(this.w);
        this.o.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.o.setColor(this.u);
        this.o.fillRect(3, 15, this.getSize().width - 6, this.getSize().height - 30);
        if (this.y) {
            (this.h = new p(this.z, this.u)).setBounds(this.getSize().width - 65, 15, 62, 29);
            this.h.addMouseListener(this);
            this.add(this.h);
            this.h.repaint();
        }
        this.o.setFont(new Font("sansserif", 0, 10));
        this.o.setColor(this.x);
        this.o.drawString("Loading market data. Click top fields to change them.", 8, 27);
        this.o.setColor(this.v);
        this.o.drawString("Symbol", 8, 11);
        if (this.y) {
            this.o.drawString("Powered by:", this.getSize().width - 60, 11);
        }
        else {
            this.o.drawString("©AlphaTrade.com", this.getSize().width - 90, this.getSize().height - 3);
        }
        this.o.drawString("Quotes delayed at least 15 min.", 8, this.getSize().height - 3);
        this.repaint();
    }
    
    public final synchronized void a() {
        this.j = super.g;
        if (this.l == null) {
            this.l = this.createImage(this.getSize().width - 6, this.getSize().height - 30);
        }
        (this.m = this.l.getGraphics()).setColor(this.u);
        this.m.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.m.setFont(new Font("sansserif", 0, 10));
        this.m.setColor(this.x);
        try {
            this.A = 0;
            while (this.A < this.j.size()) {
                final String[] array = this.j.elementAt(this.A);
                this.m.drawString(array[0], 8, 12 + this.A * 15);
                for (int i = 0; i < this.r.length; ++i) {
                    final int d;
                    if ((d = BaseBannerApplet.d(this.r[i].getText().intern())) == 1) {
                        if (array[d].startsWith("+") || array[d].startsWith("-")) {
                            this.m.drawString(array[d].substring(1), this.q + i * this.q, 12 + this.A * 15);
                        }
                        else {
                            this.m.drawString(array[d], this.q + i * this.q, 12 + this.A * 15);
                        }
                    }
                    else {
                        this.m.drawString(array[d], this.q + i * this.q, 12 + this.A * 15);
                    }
                }
                ++this.A;
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <MINI GRID> - ERROR DRAWING BUFFER: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.t)) {
            final String upperCase = this.t.getText().toUpperCase();
            final String s = this.k[0];
            this.setCursor(new Cursor(3));
            this.t.setText(upperCase);
            this.a(this.k = new String[] { s, upperCase });
            this.setCursor(new Cursor(0));
            this.a();
            this.repaint();
            this.t.selectAll();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Label) {
            this.s = (Label)mouseEvent.getSource();
            int n;
            for (n = 0; !this.s.getText().equals(MiniGridApplet.i[n]); ++n) {}
            if (n + 1 < MiniGridApplet.i.length) {
                this.s.setText(MiniGridApplet.i[n + 1]);
            }
            else {
                this.s.setText(MiniGridApplet.i[0]);
            }
            this.a();
            this.repaint();
            return;
        }
        if (mouseEvent.getSource() instanceof p) {
            try {
                this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", "index.html"), "_blank");
            }
            catch (Exception ex) {
                System.err.println("LYNX <MINI GRID> - ERROR FINDING URL: " + ex);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Label) {
            (this.s = (Label)mouseEvent.getSource()).setBackground(this.v);
            this.s.setForeground(this.w);
            return;
        }
        if (mouseEvent.getSource() instanceof p) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Label) {
            (this.s = (Label)mouseEvent.getSource()).setBackground(this.w);
            this.s.setForeground(this.v);
            return;
        }
        if (mouseEvent.getSource() instanceof p) {
            this.setCursor(new Cursor(0));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.n != null) {
            graphics.drawImage(this.n, 0, 0, null);
        }
        if (this.l != null) {
            graphics.drawImage(this.l, 3, 15, null);
        }
        for (int i = 0; i < this.r.length; ++i) {
            if (this.r[i] != null) {
                this.r[i].repaint();
            }
        }
        if (this.s != null) {
            this.s.repaint();
        }
        if (this.t != null) {
            this.t.repaint();
        }
    }
    
    static {
        i = new String[] { "Last Trade", "Change", "Volume", "Bid", "Ask", "Open", "Previous", "High", "Low", "Year Low", "Year High", "% Change" };
    }
}
