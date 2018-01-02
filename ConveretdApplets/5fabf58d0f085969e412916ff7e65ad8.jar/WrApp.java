import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WrApp extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    Thread a;
    DirectColorModel b;
    MemoryImageSource c;
    Image d;
    static final String e = "";
    static final String f = "?~](Rx%^/\u001fyn\\*\u0001\"y\u0007;\u0007:%";
    static final String g = "1cE=";
    String h;
    String i;
    long j;
    private long k;
    static long l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    int q;
    int r;
    int s;
    int t;
    private URL u;
    private URL v;
    private URL[] w;
    private StringTokenizer x;
    private int y;
    private int z;
    private int A;
    private double B;
    private String C;
    
    public final void stop() {
        this.a = null;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (!this.p) {
            this.a.stop();
            this.getAppletContext().showDocument(this.u);
            return;
        }
        if (this.y != 0) {
            if (this.C != null) {
                this.getAppletContext().showDocument(this.w[this.a(x, y)], this.C);
                return;
            }
            this.getAppletContext().showDocument(this.w[this.a(x, y)]);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.p) {
            if (this.y != 0) {
                this.showStatus(this.w[this.a(mouseEvent.getX(), mouseEvent.getY())].toString());
            }
        }
        else {
            this.showStatus(a("?~](Rx%^/\u001fyn\\*\u0001\"y\u0007;\u0007:%"));
        }
        if (this.o) {
            final String s = new String(this.v.toString());
            this.showStatus(a("\u001egH?\rw-") + s.substring(0, s.lastIndexOf("/")) + "/" + this.i + a("p*G7\u001cwlF-\u00063+"));
        }
    }
    
    private final boolean a() {
        char c = '\0';
        char c2 = '\0';
        final String lowerCase = new String(this.v.toString().toLowerCase()).toLowerCase();
        final String substring = lowerCase.substring(0, lowerCase.lastIndexOf("/") + 1);
        final String lowerCase2 = new String(this.v.getHost()).toLowerCase();
        final char[] array = new char[substring.length()];
        final char[] array2 = new char[lowerCase2.length()];
        substring.getChars(0, substring.length(), array, 0);
        lowerCase2.getChars(0, lowerCase2.length(), array2, 0);
        for (int i = 0; i < array.length; ++i) {
            c += array[i];
        }
        final int n = c * this.t;
        for (int j = 0; j < array2.length; ++j) {
            c2 += array2[j];
        }
        final char c3 = (char)(c2 * '\u3d15');
        final int n2 = c2 * this.t;
        if (substring.startsWith(a("1cE="))) {
            return true;
        }
        if (this.m) {
            return false;
        }
        this.h = this.getParameter(a("%oN"));
        if (this.h == null) {
            this.h = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.h);
        if (stringTokenizer.countTokens() != 0 && stringTokenizer.countTokens() < 100) {
            while (stringTokenizer.hasMoreTokens()) {
                this.h = stringTokenizer.nextToken();
                this.h = this.h.trim();
                this.z = Integer.parseInt(this.h);
                if (n == this.z) {
                    return true;
                }
                if (n + this.t * 403 == this.z) {
                    return true;
                }
                if (n - this.t * 403 == this.z) {
                    return true;
                }
                if (n2 == this.z) {
                    return true;
                }
                if (c3 == this.z) {
                    return true;
                }
                if (c3 + 6301711 == this.z) {
                    return true;
                }
                if (c3 - 6301711 == this.z) {
                    return true;
                }
                if (n2 == 969 * this.t + this.t * 403) {
                    return true;
                }
                if (n2 == 969 * this.t - this.t * 403) {
                    return true;
                }
                if (n2 == 969 * this.t) {
                    return true;
                }
                if (n2 + this.t * 403 == this.z) {
                    return true;
                }
                if (n2 - this.t * 403 == this.z) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private final boolean b() {
        final char c = '\u3039';
        final String s = new String(this.u.toString());
        final char[] array = new char[s.length()];
        final char[] array2 = new char[a("1cE=").length()];
        s.getChars(0, s.length(), array, 0);
        a("1cE=").getChars(0, a("1cE=").length(), array2, 0);
        char c2 = '\0';
        char c3 = '\0';
        for (int i = 0; i < array.length; ++i) {
            c2 += array[i];
        }
        final char c4 = (char)(c2 * c);
        for (int j = 0; j < array2.length; ++j) {
            c3 += array2[j];
        }
        return !(c4 == 25714635 & c3 * c == 5135520);
    }
    
    public WrApp() {
        this.m = true;
        this.n = false;
        this.o = false;
        this.p = false;
        this.B = 0.0;
    }
    
    public void c() {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.p) {
            if (this.y != 0) {
                this.showStatus(this.w[this.a(mouseEvent.getX(), mouseEvent.getY())].toString());
            }
        }
        else {
            this.showStatus(a("?~](Rx%^/\u001fyn\\*\u0001\"y\u0007;\u0007:%"));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.showStatus(" ");
    }
    
    static {
        WrApp.l = 20L;
    }
    
    private final int a(final int n, final int n2) {
        if (this.y < 1) {
            return 0;
        }
        if (this.A == 0) {
            return Math.min(this.y - 1, (int)(n2 / this.B));
        }
        return Math.min(this.y - 1, (int)(n / this.B));
    }
    
    public final void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).setPriority(3);
            this.a.start();
        }
    }
    
    public final String getAppletInfo() {
        return a("\u007fi\u0000x,\"x@-\u001bwb],\u0018m%\u0006/\u001f $M-\u001a>\u007fZv\u000b8g\u0006");
    }
    
    public final void run() {
        while (this.a != null) {
            this.repaint();
            this.k = System.currentTimeMillis();
            final long n = WrApp.l - (this.k - this.j);
            if (n > 1L) {
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void init() {
        try {
            this.v = this.getDocumentBase();
        }
        catch (Exception ex) {}
        try {
            this.u = new URL(a("?~](Rx%^/\u001fyn\\*\u0001\"y\u0007;\u0007:%"));
        }
        catch (Exception ex2) {}
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.h = this.getParameter(a("5m"));
        if (this.h != null) {
            this.q = Integer.valueOf(this.h, 16);
            this.setBackground(new Color(this.q));
        }
        else {
            this.setBackground(new Color(0, 0, 0));
        }
        this.z = 1;
        this.y = 0;
        this.A = 0;
        this.h = this.getParameter(a(" cM,\u0000"));
        if (this.h != null) {
            this.r = Integer.parseInt(this.h);
        }
        this.h = this.getParameter(a("?o@?\u0000#"));
        if (this.h != null) {
            this.s = Integer.parseInt(this.h);
        }
        this.h = this.getParameter(a(">d]\u0017\u001a>oG,\t#cF6"));
        if (this.h != null) {
            this.A = ((this.h.toLowerCase().compareTo("v") == 0) ? 1 : 0);
        }
        this.h = this.getParameter(a("\"xE"));
        if (this.h != null && this.h.length() > 1) {
            this.x = new StringTokenizer(this.h);
            this.w = new URL[this.x.countTokens()];
        }
        this.h = this.getParameter(a("#k[?\r#"));
        if (this.h != null) {
            this.C = this.h;
        }
        if (this.x != null) {
            this.setCursor(new Cursor(12));
            while (this.x.hasMoreTokens()) {
                this.h = this.x.nextToken();
                String s = this.h;
                this.h = this.h.toLowerCase();
                if (this.h.startsWith("/")) {
                    s = this.v.getProtocol().toString() + this.v.getHost().toString() + s;
                }
                else if (!this.h.startsWith(a(":k@4\u001c80")) && !this.h.startsWith(a("?~](Rx%")) && !this.h.startsWith(a("?~](\u001bm%\u0006"))) {
                    if (this.h.startsWith(".")) {
                        s = this.v.toString().substring(0, this.v.toString().lastIndexOf("/") + 1) + s;
                    }
                    else if (!this.h.startsWith("/") && !this.h.startsWith(a("=k_9\u001b4x@(\u001cm"))) {
                        s = this.v.toString().substring(0, this.v.toString().lastIndexOf("/") + 1) + s;
                    }
                }
                try {
                    this.w[this.y] = new URL(s);
                }
                catch (Exception ex3) {}
                ++this.y;
            }
        }
        this.B = 0.0;
        if (this.y != 0) {
            if (this.A == 0) {
                this.B = this.s / this.y;
            }
            else {
                this.B = this.r / this.y;
            }
        }
        this.m = this.b();
        this.c();
        if (!(this.p = this.a())) {
            this.setCursor(new Cursor(12));
        }
        System.gc();
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'W';
                            break;
                        }
                        case 1: {
                            c2 = '\n';
                            break;
                        }
                        case 2: {
                            c2 = ')';
                            break;
                        }
                        case 3: {
                            c2 = 'X';
                            break;
                        }
                        default: {
                            c2 = 'h';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
