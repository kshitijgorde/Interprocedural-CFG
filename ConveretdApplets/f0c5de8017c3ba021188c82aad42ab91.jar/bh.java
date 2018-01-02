import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bh
{
    private int a;
    private int b;
    private int c;
    private byte[] d;
    private int e;
    private Vector f;
    
    public bh(final byte[] array, final int n, final b5 b5) throws b6 {
        this(array, n, -1, b5);
    }
    
    public bh(final byte[] d, final int n, final int n2, final b5 b5) throws b6 {
        this.d = d;
        this.b = n;
        this.a = n;
        this.e = ((n2 > -1) ? n2 : d.length);
        this.f = this.a(b5);
    }
    
    public bh() {
        this.f = new Vector();
    }
    
    private Vector a(final b5 b5) {
        final Vector vector = new Vector<bi>();
        if (this.e <= 0) {
            vector.addElement(new bi(this.d, this.a, this.a));
            return vector;
        }
        do {
            final byte b6 = this.d[this.a];
            if (b6 != b5.d && !b5.a(b6) && b6 != b5.c) {
                vector.addElement(this.b(b5));
            }
            else {
                if (b6 == b5.d) {
                    break;
                }
                if (b6 == b5.c) {
                    vector.addElement(this.c(b5));
                }
            }
            ++this.a;
        } while (this.a < this.e);
        if (vector.size() == 0) {
            vector.addElement(new bi(this.d, this.a, this.e));
        }
        this.c = this.a - this.b;
        return vector;
    }
    
    private bi b(final b5 b5) {
        final int a = this.a;
        int e = -1;
        for (int i = this.a; i < this.e; ++i) {
            if (this.d[i] == b5.b) {
                e = i;
                break;
            }
        }
        if (e < 0) {
            e = this.e;
        }
        final int a2 = e;
        final int a3 = this.a(e, b5);
        this.a = a2;
        return new bi(this.d, a, a3);
    }
    
    private bi c(final b5 b5) throws b6 {
        final int a = this.a;
        int i = this.a;
        int n = 0;
        do {
            final byte b6 = this.d[i];
            if (b6 == b5.c) {
                ++n;
            }
            ++i;
            if (b6 == b5.b && n % 2 == 0) {
                break;
            }
        } while (i < this.e);
        if (n % 2 != 0) {
            throw new b6("Unclosed quoted attribute at the end of CSV-line.");
        }
        int n2 = --i;
        if (i == this.e - 1 && !b5.g) {
            ++n2;
        }
        final int a2 = this.a(n2, b5);
        if (this.d[a2 - 1] != b5.c) {
            throw new b6("Quoted attribute trailed by invalid characters (" + this.d[a2 - 1] + ") at position " + (a2 - 1) + ".");
        }
        final bi bi = new bi(this.d, a, a2);
        this.a = i;
        return bi;
    }
    
    private int a(final int n, final b5 b5) {
        int n2 = 0;
        for (int n3 = n - 1; n3 > this.a && b5.a(this.d[n3]); --n3) {
            ++n2;
        }
        return n - n2;
    }
    
    public final int a() {
        return this.f.size();
    }
    
    public final int b() {
        return this.c;
    }
    
    public final bi a(final int n) {
        return this.f.elementAt(n);
    }
    
    public final void a(final bi bi, final int n) {
        this.f.setElementAt(bi, n);
    }
    
    public final void a(final bi bi) {
        this.f.addElement(bi);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.f.size(); ++i) {
            sb.append(this.f.elementAt(i).toString() + ";");
        }
        sb.append("\n");
        return sb.toString();
    }
}
