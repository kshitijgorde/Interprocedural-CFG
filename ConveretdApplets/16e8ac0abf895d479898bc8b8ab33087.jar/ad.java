import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.URL;
import java.awt.Container;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ad extends Canvas
{
    public Image a;
    private static String a;
    private String b;
    private boolean b;
    public int b;
    public Color b;
    private int a;
    private int c;
    public boolean a;
    private int d;
    private int e;
    private MediaTracker a;
    private static int f;
    private int g;
    private boolean c;
    private Container a;
    
    static {
        ad.a = "http://java.sun.com/lib/images/logo.java.color-transp.55x60.gif";
        ad.f = 0;
    }
    
    public ad() {
        this(ad.a);
    }
    
    public ad(final String s) {
        this(a(s));
    }
    
    public ad(final URL url) {
        this(a(url));
        this.b = url.toExternalForm();
    }
    
    public ad(final URL url, final String b) {
        this(a(url, b));
        this.b = b;
    }
    
    public ad(final Image a) {
        this.b = "<Existing Image>";
        this.b = false;
        this.b = 0;
        this.b = null;
        this.a = false;
        this.d = 0;
        this.e = 0;
        this.c = false;
        this.a = a;
        this.a = new MediaTracker(this);
        this.g = ad.f++;
        this.a.addImage(a, this.g);
    }
    
    public final void a(final boolean b) {
        if (!this.c) {
            this.a("[waitForImage] - Resizing and waiting for " + this.b);
            try {
                this.a.waitForID(this.g);
            }
            catch (InterruptedException ex2) {}
            catch (Exception ex) {
                System.out.println("Error loading " + this.b + ": " + ex.getMessage());
                ex.printStackTrace();
            }
            if (this.a.isErrorID(0)) {
                new Throwable("Error loading image " + this.b).printStackTrace();
            }
            this.c = true;
            if (this.d != 0) {
                this.a = this.d;
            }
            else {
                this.a = this.a.getWidth(this) + 2 * this.b;
            }
            if (this.e != 0) {
                this.c = this.e;
            }
            else {
                this.c = this.a.getHeight(this) + 2 * this.b;
            }
            this.setSize(this.a, this.c);
            this.a("[waitForImage] - " + this.b + " is " + this.a + "x" + this.c + ".");
            final Container parent = this.getParent();
            this.a = parent;
            if (parent != null && b) {
                this.setBackground(this.a.getBackground());
                this.a.doLayout();
            }
        }
    }
    
    public synchronized boolean contains(final int n, final int n2) {
        return n >= 0 && n <= this.a && n2 >= 0 && n2 <= this.c;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.c) {
            this.a(true);
            return;
        }
        if (this.a) {
            graphics.drawImage(this.a, this.b, this.b, this.a - 2 * this.b, this.c - 2 * this.b, this);
        }
        else {
            graphics.drawImage(this.a, this.b, this.b, this);
        }
        a(graphics, 0, 0, this.a - 1, this.c - 1, this.b, this.b);
    }
    
    public Dimension getPreferredSize() {
        if (!this.c) {
            this.a(false);
        }
        return super.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        if (!this.c) {
            this.a(false);
        }
        return super.getMinimumSize();
    }
    
    public void setSize(final int d, final int e) {
        if (!this.c) {
            this.a = true;
            if (d > 0) {
                this.d = d;
            }
            if (e > 0) {
                this.e = e;
            }
        }
        super.setSize(d, e);
    }
    
    public void setBounds(final int n, final int n2, final int d, final int e) {
        if (!this.c) {
            this.a = true;
            if (d > 0) {
                this.d = d;
            }
            if (e > 0) {
                this.e = e;
            }
        }
        super.setBounds(n, n2, d, e);
    }
    
    public static void a(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 0; i < n5; ++i) {
            graphics.drawRect(n, n2, n3, n4);
            if (i < n5 - 1) {
                ++n;
                ++n2;
                n3 -= 2;
                n4 -= 2;
            }
        }
    }
    
    public final void a(final String s) {
        if (this.b) {
            System.out.println(s);
        }
    }
    
    private static URL a(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL " + s + ": " + ex);
            ex.printStackTrace();
        }
        return url;
    }
    
    private static URL a(final URL url, final String s) {
        URL url2 = null;
        try {
            url2 = new URL(url, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL " + url.toExternalForm() + ", " + s + ": " + ex);
            ex.printStackTrace();
        }
        return url2;
    }
    
    private static Image a(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public int getWidth() {
        return this.a;
    }
    
    public int getHeight() {
        return this.c;
    }
}
