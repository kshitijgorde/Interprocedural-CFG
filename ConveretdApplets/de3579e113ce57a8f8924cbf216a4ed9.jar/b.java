import java.util.NoSuchElementException;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class b implements _
{
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    private static String o = "\u945f\u9453";
    private static String p = "\u943f";
    private static String q = "\u9430";
    private static String C = "\u9435";
    private static String D = "\u9421";
    private static String E = "\u9427";
    private static String F = "\u9431";
    
    public b() {
        final boolean b = false;
        this.v = (b ? 1 : 0);
        this.v = (b ? 1 : 0);
        this.u = (b ? 1 : 0);
        this.t = (b ? 1 : 0);
        final int n = 2;
        this.y = n;
        this.x = n;
    }
    
    public b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, b.o);
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 2;
        this.y = 2;
        String s2 = null;
        Label_0302: {
            try {
                s2 = stringTokenizer.nextToken();
                this.t = new Integer(s2);
                this.v = this.t;
                s2 = stringTokenizer.nextToken();
                this.u = new Integer(s2);
                this.w = this.u;
                s2 = stringTokenizer.nextToken();
                this.v = new Integer(s2);
                s2 = stringTokenizer.nextToken();
                this.w = new Integer(s2);
            }
            catch (NoSuchElementException ex) {}
            catch (NumberFormatException ex2) {
                try {
                    if (s2.equalsIgnoreCase(b.p)) {
                        this.x = 0;
                    }
                    else if (s2.equalsIgnoreCase(b.q)) {
                        this.x = 1;
                    }
                    else if (s2.equalsIgnoreCase(b.C)) {
                        this.x = 2;
                    }
                    else if (s2.equalsIgnoreCase(b.D)) {
                        this.x = 3;
                    }
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.equalsIgnoreCase(b.E)) {
                        this.y = 0;
                        break Label_0302;
                    }
                    if (nextToken.equalsIgnoreCase(b.q)) {
                        this.y = 1;
                        break Label_0302;
                    }
                    if (nextToken.equalsIgnoreCase(b.C)) {
                        this.y = 2;
                        break Label_0302;
                    }
                    if (nextToken.equalsIgnoreCase(b.F)) {
                        this.y = 3;
                    }
                }
                catch (NoSuchElementException ex3) {}
            }
        }
        if (this.w < this.u) {
            this.w = this.u;
        }
        if (this.v < this.t) {
            this.v = this.t;
        }
    }
    
    public b(final int t, final int u, final int v, final int w, final int x, final int y) {
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
        if (x < 0 || x > 3) {
            this.x = 2;
        }
        else {
            this.x = x;
        }
        if (y < 0 || y > 3) {
            this.y = 2;
            return;
        }
        this.y = y;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.u);
        sb.append(b.o);
        sb.append(this.t);
        sb.append(b.o);
        if (this.u == this.w && this.t == this.v) {
            final char[] array = { 'L', 'C', 'F', 'R' };
            final char[] array2 = { 'T', 'C', 'F', 'B' };
            sb.append(array[this.x]);
            sb.append(b.o);
            sb.append(array2[this.y]);
        }
        else {
            sb.append(this.w);
            sb.append(b.o);
            sb.append(this.v);
        }
        return sb.toString();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u9473');
        }
        return new String(array);
    }
    
    static {
        b.o = _(b.o);
        b.p = _(b.p);
        b.q = _(b.q);
        b.C = _(b.C);
        b.D = _(b.D);
        b.E = _(b.E);
        b.F = _(b.F);
    }
}
