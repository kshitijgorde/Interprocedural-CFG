import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class b
{
    private String e;
    private URL b;
    private boolean g;
    private int f;
    private int j;
    private int a;
    private Image h;
    private Image i;
    private String d;
    private final /* synthetic */ HorizontalProTrial c;
    
    public void a(final String s, final String e) {
        this.h = this.c.createImage(this.a, this.j);
        final Graphics graphics = this.h.getGraphics();
        graphics.setColor(HorizontalProTrial.f(this.c));
        graphics.fillRect(0, 0, this.a, this.j);
        graphics.setColor(HorizontalProTrial.c(this.c));
        graphics.setFont(HorizontalProTrial.d(this.c));
        graphics.drawString(this.d, 0, this.j / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
        graphics.drawLine(0, this.j / 2 + graphics.getFontMetrics().getHeight() / 2, this.a, this.j / 2 + graphics.getFontMetrics().getHeight() / 2);
        final Graphics graphics2 = this.i.getGraphics();
        graphics2.setFont(HorizontalProTrial.d(this.c));
        graphics2.setColor(HorizontalProTrial.e(this.c));
        graphics2.drawLine(0, this.j / 2 + graphics2.getFontMetrics().getHeight() / 2, this.a, this.j / 2 + graphics2.getFontMetrics().getHeight() / 2);
        try {
            this.b = new URL(this.c.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad Link URL in ScrollText [" + s + "]");
        }
        this.e = e;
        this.g = true;
    }
    
    private boolean a() {
        return this.b != null;
    }
    
    public Image a(final boolean b) {
        if (b && this.g) {
            return this.h;
        }
        return this.i;
    }
    
    private void b() {
        if (this.b != null) {
            if (this.e != null) {
                this.c.getAppletContext().showDocument(this.b, this.e);
            }
            else {
                this.c.getAppletContext().showDocument(this.b);
            }
        }
    }
    
    public boolean a(final int n, final int n2) {
        return this.f > n - this.a && this.f < n2;
    }
    
    static void d(final b b) {
        b.b();
    }
    
    static boolean c(final b b) {
        return b.a();
    }
    
    static int b(final b b) {
        return b.a;
    }
    
    static int a(final b b) {
        return b.f;
    }
    
    b(final HorizontalProTrial c, final String d, final int f, final int j) {
        this.c = c;
        this.d = null;
        this.i = null;
        this.h = null;
        this.a = 0;
        this.j = 0;
        this.f = 0;
        this.g = false;
        this.b = null;
        this.e = null;
        this.j = j;
        this.f = f;
        this.d = d;
        c.getGraphics();
        this.a = HorizontalProTrial.a(c, d);
        this.i = c.createImage(this.a, j);
        final Graphics graphics = this.i.getGraphics();
        graphics.setColor(HorizontalProTrial.f(c));
        graphics.fillRect(0, 0, this.a, this.j);
        graphics.setColor(HorizontalProTrial.e(c));
        graphics.setFont(HorizontalProTrial.d(c));
        graphics.drawString(this.d, 0, j / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
    }
    
    b(final HorizontalProTrial c, final Image i, final int f, final int j) {
        this.c = c;
        this.d = null;
        this.i = null;
        this.h = null;
        this.a = 0;
        this.j = 0;
        this.f = 0;
        this.g = false;
        this.b = null;
        this.e = null;
        this.j = j;
        this.f = f;
        this.i = i;
        this.a = this.i.getWidth(null);
    }
}
