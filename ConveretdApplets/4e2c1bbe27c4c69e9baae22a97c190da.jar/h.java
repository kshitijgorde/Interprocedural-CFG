import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Frame implements Runnable, b
{
    public g a;
    public Thread b;
    public Image c;
    public int d;
    public int e;
    public boolean f;
    public Image g;
    
    public h(final g a, final String s) {
        super(s);
        this.b = null;
        this.c = null;
        this.f = false;
        this.g = null;
        this.a = a;
    }
    
    public void run() {
        try {
            final int ac = this.a.a.ac;
            final Graphics ag = this.a.a.ag;
            while (true) {
                if (!this.a.s) {
                    final int n = this.a.n;
                    if (this.a.r[n]) {
                        while (this.g != null && this.a.o[n] + ac >= this.a.a.a()) {
                            Thread.currentThread();
                            Thread.sleep(5L);
                        }
                        this.d = this.a.a.ae;
                        this.e = this.a.a.af;
                        this.c = this.a.q[n];
                        if (this.g == null) {
                            this.g = this.a.a.al.createImage(this.a.j, this.a.k);
                        }
                        this.g.getGraphics().drawImage(this.c, 0, 0, this.a.j, this.a.k, this);
                        if (!this.a.a.bc) {
                            ag.drawImage(this.c, this.d, this.e, this.a.j, this.a.k, this);
                        }
                        this.a.a.a2.av = this.a.p[n];
                        final g a = this.a;
                        ++a.n;
                        final g a2 = this.a;
                        a2.n %= this.a.l;
                        final f a3 = this.a.a.a2;
                        ++a3.ao;
                        this.a.r[n] = false;
                    }
                    else {
                        Thread.currentThread();
                        Thread.sleep(5L);
                    }
                }
                else {
                    Thread.currentThread();
                    Thread.sleep(5L);
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void a() {
        if (this.b != null && !this.b.isAlive()) {
            this.b = null;
        }
        if (this.b == null) {
            (this.b = new Thread(this, zkmToString("xURH;Oodj"))).start();
        }
    }
    
    public void b() {
        if (this.b != null) {
            this.b.stop();
            this.b = null;
        }
    }
    
    public static void a(final Applet applet, final Graphics graphics, final int n, final int n2, final int n3, final int n4, final String s) {
        final Image image = applet.createImage(n3, n4);
        final Graphics graphics2 = image.getGraphics();
        graphics2.clearRect(0, 0, n3, n4);
        graphics2.drawString(s, 0, n4 - 4);
        graphics.drawImage(image, n, n2, n3, n4, null);
        graphics2.dispose();
        image.flush();
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '.';
                    break;
                }
                case 1: {
                    c2 = '\u0016';
                    break;
                }
                case 2: {
                    c2 = '\u0001';
                    break;
                }
                case 3: {
                    c2 = '\u0018';
                    break;
                }
                default: {
                    c2 = 'W';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
