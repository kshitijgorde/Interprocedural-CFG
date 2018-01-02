import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImageOp;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class implements extends Canvas implements private
{
    private protected oUa;
    private public pUa;
    private public qUa;
    private return va;
    private interface rUa;
    private interface sUa;
    private native tUa;
    private null uUa;
    private Color vUa;
    private BufferedImage wUa;
    private boolean xUa;
    private boolean yUa;
    private boolean zUa;
    private boolean ya;
    private int AUa;
    private int BUa;
    private MediaTracker CUa;
    private static String T = "\u29f6\u29c0\u29d3\u29c5\u29db\u29dc\u29d5\u2992\u29db\u29c1\u2992\u29d6\u29db\u29c1\u29d3\u29d0\u29de\u29d7\u29d6\u2993";
    private static String U = "\u29e6\u29db\u29df\u29d7\u2992\u29d1\u29da\u29d3\u29dc\u29d5\u29d7\u2992\u29c0\u29d7\u29c3\u29c7\u29d7\u29c1\u29c6\u29d7\u29d6";
    private static String V = "\u29e0\u29d7\u29c2\u29d3\u29db\u29dc\u29c6\u2992\u29c0\u29d7\u29c3\u29c7\u29d7\u29c1\u29c6\u29d7\u29d6";
    private static String W = "\u29e0\u29d7\u29c2\u29d3\u29db\u29dc\u29c6\u2992\u29c0\u29d7\u29c3\u29c7\u29d7\u29c1\u29c6\u2992\u29db\u29dc\u2992\u29d1\u29dd\u29dc\u29c6\u29c0\u29dd\u29de";
    private static String ba = "\u29e0\u29d7\u29c2\u29d3\u29db\u29dc\u29c6\u2992\u29c0\u29d7\u29c3\u29c7\u29d7\u29c1\u29c6\u2992\u29d1\u29dd\u29dc\u29c6\u29c0\u29dd\u29de\u2992\u29c1\u29c6\u29dd\u29c2\u29c2\u29d7\u29d6";
    private static String ca = "\u29fc\u29dd\u29c6\u2992\u29d6\u29d7\u29d4\u29db\u29dc\u29d7\u29d6\u2992\u29d7\u29c4\u29d7\u29dc\u29c6\u2992\u29db\u29d6\u299e\u2992\u29db\u29d5\u29dc\u29dd\u29c0\u29db\u29dc\u29d5\u2988\u2992";
    private static String da = "\u2992\u299f\u2992";
    
    public implements(final boolean ya, final interface sUa, final interface rUa, final interface interface1, final interface interface2, final interface interface3, final native tUa, final null uUa, final Color vUa) {
        this.wUa = null;
        this.xUa = false;
        this.yUa = true;
        this.zUa = false;
        this.ya = false;
        this.AUa = 0;
        this.BUa = 0;
        this.CUa = null;
        this.ya = ya;
        this.uUa = uUa;
        this.vUa = vUa;
        this.pUa = new public(interface2, 1, this);
        this.qUa = new public(interface3, 1, this);
        this.sUa = sUa;
        this.rUa = rUa;
        this.va = new return(interface1, this, this, ya);
        this.tUa = tUa;
        this.oUa = new protected(this.ya, this);
        this.addMouseListener(this.tUa);
        this.addMouseMotionListener(this.tUa);
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (this.isShowing() && this.yUa) {
            this.AUa = this.getWidth();
            this.BUa = this.getHeight();
            this.yUa = false;
            final static static1 = new static(0);
            this.oUa.reset();
            static1.h(this.oUa._());
            static1.b(this.oUa.a());
            static1.i(this.oUa.b());
            static1._(this.oUa.a());
            static1.a(this.oUa.b());
            static1.h(this.oUa._());
            this.pUa.b(static1.a());
            this.qUa.b(static1.h());
            this.uUa.h(static1._());
            this.uUa.b(static1.a());
            this.uUa.i(static1.a());
            this.uUa.j(static1.b());
            this.uUa.k(static1._());
            this.uUa._();
            this.va.start();
            this.oUa.start();
        }
        if (this.xUa) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            if (!this.zUa) {
                this.d();
            }
            if (this.wUa != null) {
                graphics2D.drawImage(this.wUa, null, 0, 0);
            }
        }
        else {
            this.b(implements.T);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void a(final boolean xUa) {
        this.xUa = xUa;
    }
    
    public synchronized void a(final static static1) {
        switch (static1.g()) {
            case 1: {
                this.b(implements.U);
                this.b(static1);
                break;
            }
            case 2: {
                this.b(implements.V);
                this.d();
                this.repaint();
                break;
            }
            case 3: {
                this.b(implements.W);
                this.zUa = true;
                break;
            }
            case 4: {
                this.b(implements.ba);
                this.zUa = false;
                break;
            }
            default: {
                this.b(implements.ca.concat(String.valueOf(String.valueOf(static1.g()))));
                break;
            }
        }
    }
    
    public void d() {
        final BufferedImage _ = this._(this.getWidth(), this.getHeight());
        final Graphics2D graphics = _.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setBackground(this.vUa);
        graphics.clearRect(0, 0, this.AUa, this.BUa);
        graphics.drawImage(this.rUa._(), this.rUa.d(), this.rUa.e(), this);
        this.va._(graphics);
        this.uUa._(graphics);
        this.qUa._(graphics);
        this.pUa._(graphics);
        if (this.sUa != null) {
            graphics.drawImage(this.sUa._(), this.sUa.d(), this.sUa.e(), this);
        }
        this.wUa = _;
    }
    
    private void b(final static static1) {
        this.uUa.h(static1._());
        this.uUa.b(static1.a());
        this.uUa.i(static1.a());
        this.uUa.j(static1.b());
        this.uUa.k(static1._());
        this.uUa._();
        this.pUa.b(static1.a());
        this.qUa.b(static1.h());
        if (!this.zUa) {
            this.repaint();
        }
    }
    
    private BufferedImage _(final int n, final int n2) {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(n, n2);
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(implements.da).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u29b2');
        }
        return new String(array);
    }
    
    static {
        implements.T = e(implements.T);
        implements.U = e(implements.U);
        implements.V = e(implements.V);
        implements.W = e(implements.W);
        implements.ba = e(implements.ba);
        implements.ca = e(implements.ca);
        implements.da = e(implements.da);
    }
}
