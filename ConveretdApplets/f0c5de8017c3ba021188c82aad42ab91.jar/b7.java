import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class b7
{
    private char a;
    private char b;
    private String c;
    public Character d;
    private int e;
    private int f;
    public l g;
    
    public b7() {
        this(';', '\"', "", null);
    }
    
    public b7(final char c) {
        this(c, '\"', "", null);
    }
    
    public b7(final char a, final char b, String c, final Character d) {
        this.a = ';';
        this.b = '\"';
        this.c = " \t";
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = new l(0);
        if (c == null) {
            c = "";
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public Vector a(final String s) throws IllegalArgumentException {
        this.g.a(0);
        return this.a(s, this.g);
    }
    
    public Vector a(final String s, final l l) throws IllegalArgumentException {
        boolean b = false;
        this.f = s.length();
        this.e = 0;
        if (l.b() >= 0 && l.b() < this.f) {
            this.e = l.b();
        }
        final Vector<String> vector = new Vector<String>();
        if (this.f <= 0) {
            vector.addElement(s);
            return vector;
        }
        do {
            final char char1 = s.charAt(this.e);
            if (this.d != null && char1 == this.d) {
                break;
            }
            if (char1 == this.b) {
                vector.addElement(this.c(s));
                b = true;
            }
            else if (char1 == this.a) {
                vector.addElement(null);
                b = true;
            }
            else if (this.c != null) {
                if (this.c.indexOf(char1) <= -1) {
                    vector.addElement(this.b(s));
                    b = true;
                }
            }
            ++this.e;
        } while (this.e < this.f);
        if (!b) {
            vector.addElement(s);
        }
        l.a(this.e);
        return vector;
    }
    
    private String b(final String s) {
        final int e = this.e;
        int n = s.indexOf(this.a, this.e);
        if (n < 0) {
            n = this.f;
        }
        final int e2 = n;
        final int a = this.a(n, s);
        this.e = e2;
        return s.substring(e, a);
    }
    
    private String c(final String s) throws IllegalArgumentException {
        final int e = this.e;
        int i = this.e;
        int n = 0;
        do {
            final char char1 = s.charAt(i);
            if (char1 == this.b) {
                ++n;
            }
            ++i;
            if (char1 == this.a && n % 2 == 0) {
                break;
            }
        } while (i < this.f);
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Unclosed quoted attribute at the end of CSV-line.");
        }
        final int a = this.a(--i, s);
        if (!s.substring(e + 1, a).endsWith("" + this.b)) {
            throw new IllegalArgumentException("Quoted attribute trailed by invalid characters at position " + a + ".");
        }
        String s2 = s.substring(e + 1, a - 1);
        for (int j = 0; j < s2.length(); j += 2) {
            if (s2.charAt(j) == this.b) {
                s2 = s2.substring(0, j) + s2.substring(j + 1, s2.length());
            }
        }
        this.e = i;
        return s2;
    }
    
    private int a(final int n, final String s) {
        int n2 = 0;
        for (int i = n - 1; i > this.e; --i) {
            if (this.c != null) {
                if (this.c.indexOf(s.charAt(i)) < 0) {
                    break;
                }
                ++n2;
            }
        }
        return n - n2;
    }
}
