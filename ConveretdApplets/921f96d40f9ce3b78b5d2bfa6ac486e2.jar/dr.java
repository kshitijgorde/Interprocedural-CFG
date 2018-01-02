import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Hashtable;
import java.util.Vector;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class dr implements dj
{
    boolean p;
    URL p;
    String p;
    private static int p;
    private Vector p;
    private Hashtable p;
    private String d;
    private int d;
    private int a;
    private int n;
    private int v;
    private int[] p;
    private int[] d;
    private FontMetrics p;
    Color[] p;
    
    public dr(String d, final Hashtable p5, final FontMetrics p6, final ImageObserver imageObserver, final int n) {
        this.p = false;
        this.p = new Vector();
        this.p = new int[dr.p];
        this.d = new int[dr.p];
        this.p = dw.p;
        if (d == null) {
            return;
        }
        this.p = p5;
        if (d.startsWith("http://")) {
            try {
                this.p = new URL(du.p(d, 0, ' '));
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
            d = du.d(d, 0, ' ');
        }
        else if (d.startsWith("cmd://")) {
            this.p = du.p(d, 0, ' ');
            this.p = this.p.substring(6, this.p.length());
            d = du.d(d, 0, ' ');
        }
        this.d = d;
        int i = 0;
        int n2 = 0;
        int n3 = 1;
        while (i < d.length()) {
            if (d.charAt(i) == '<') {
                final int index = d.indexOf(62, i);
                if (index >= i + 2) {
                    final int p7 = du.p(d.substring(i + 1, index), 10);
                    if (p7 >= 0 && p7 < this.p.length) {
                        n3 = p7;
                        n2 = 0;
                        i = index + 1;
                        continue;
                    }
                    final int index2 = d.indexOf(44, i);
                    if (index2 >= i + 2 && index2 <= index - 2) {
                        final int p8 = du.p(d.substring(i + 1, index2), 10);
                        final int p9 = du.p(d.substring(index2 + 1, index), 10);
                        if (p8 >= 0 && p8 < this.p.length && p9 >= 0 && p9 < this.p.length) {
                            n3 = p8;
                            n2 = p9;
                            i = index + 1;
                            continue;
                        }
                    }
                }
            }
            if (n3 == 0) {
                n3 = 1;
            }
            if (n3 == n2) {
                n3 = 1;
                n2 = 0;
            }
            int n4 = d.indexOf(60, i + 1);
            if (n4 == -1) {
                n4 = d.length();
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(d.substring(i, n4));
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (p5.containsKey(nextToken.toLowerCase())) {
                    this.p.addElement(new dt(p5.get(nextToken.toLowerCase()), null, p6, imageObserver));
                }
                else {
                    this.p.addElement(new ds(nextToken, this.p[n3], this.p[n2], null, p6, imageObserver));
                }
            }
            i = n4;
        }
        if (p6 == null) {
            return;
        }
        this.p = p6;
        this.n = p6.charWidth(' ');
        this.d(n);
    }
    
    public final void p(final boolean p) {
        this.p = p;
    }
    
    public final boolean p() {
        return this.p;
    }
    
    public final FontMetrics p() {
        return this.p;
    }
    
    public final void p(final FontMetrics p) {
        this.p = p;
        this.n = p.charWidth(' ');
        for (int i = 0; i < this.p.size(); ++i) {
            ((dk)this.p.elementAt(i)).p(p);
        }
    }
    
    public final void p(final int n) {
        this.p(this.p[n]);
    }
    
    public final void p(final Color color) {
        for (int i = 0; i < this.p.size(); ++i) {
            ((dk)this.p.elementAt(i)).p(color);
        }
    }
    
    public final void p(final ImageObserver imageObserver) {
        for (int i = 0; i < this.p.size(); ++i) {
            ((dk)this.p.elementAt(i)).p(imageObserver);
        }
    }
    
    public final void d(final int d) {
        this.d = d;
        this.v = 0;
        if (d == 0) {
            return;
        }
        int n = 1;
        int n2 = 0;
        int max = 0;
        for (int i = 0; i < this.p.size(); ++i) {
            final dk dk = this.p.elementAt(i);
            if (this.v >= dr.p) {
                break;
            }
            if (n2 + dk.p() <= d) {
                n2 += ((dk)this.p.elementAt(i)).p() + this.n;
                max = Math.max(max, ((dk)this.p.elementAt(i)).d());
                n = 0;
            }
            else if (n != 0) {
                n2 = 2 * this.n;
                this.p[this.v] = i;
                this.d[this.v++] = ((dk)this.p.elementAt(i)).d();
            }
            else {
                --i;
                n = 1;
                n2 = 2 * this.n;
                this.p[this.v] = i;
                this.d[this.v++] = max;
                max = 0;
            }
        }
        if (n == 0) {
            this.p[this.v] = this.p.size() - 1;
            this.d[this.v++] = max;
        }
        this.a = 0;
        for (int j = 0; j < this.v; ++j) {
            this.a += this.d[j];
        }
        if (this.a == 0) {
            this.a = 10;
        }
    }
    
    public final int p() {
        return this.a;
    }
    
    public final void p(final Graphics graphics, final int n, final int n2, final Color color) {
        int n3 = n2;
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(n - 5, n2, this.d + 25, this.a);
        }
        for (int i = 0; i < this.v; ++i) {
            int n4 = n + ((i == 0) ? 0 : (2 * this.n));
            for (int j = (i == 0) ? 0 : (this.p[i - 1] + 1); j <= this.p[i]; ++j) {
                final dk dk = this.p.elementAt(j);
                dk.p(graphics, n4, n3 + this.d[i] - dk.d());
                n4 += ((dk)this.p.elementAt(j)).p() + this.n;
            }
            n3 += this.d[i];
        }
    }
    
    public final URL p() {
        return this.p;
    }
    
    public final String p() {
        return this.p;
    }
    
    public final String toString() {
        return this.d;
    }
    
    static {
        dr.p = 10;
    }
}
