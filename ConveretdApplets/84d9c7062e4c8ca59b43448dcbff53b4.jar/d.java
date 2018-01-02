import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public String a;
    public Vector b;
    
    public d c(final String a) {
        final float a2 = this.a(a);
        if ((int)(a2 * 10.0f) % 10 != 0) {
            if (this.b == null) {
                this.b = new Vector();
            }
            final d d = new d();
            d.a = a;
            this.b.insertElementAt(d, (int)a2);
            return d;
        }
        return (d)this.b.elementAt((int)a2);
    }
    
    public void a(final int n) {
        if (this.b != null) {
            this.b.removeElementAt(n);
            if (this.b.size() == 0) {
                this.b = null;
            }
        }
    }
    
    public d b(final String s) {
        final float a = this.a(s);
        if ((int)(a * 10.0f) % 10 == 0) {
            return (d)this.b.elementAt((int)a);
        }
        return null;
    }
    
    public float a(final String s) {
        if (this.b == null) {
            return 0.5f;
        }
        final int size = this.b.size();
        if (size == 0) {
            return 0.5f;
        }
        int n = 0;
        int n2 = size - 1;
        while (true) {
            final int n3 = (n + n2) / 2;
            final int compareTo = s.compareTo(this.b.elementAt(n3).a);
            if (compareTo == 0) {
                return n3;
            }
            if (compareTo < 0) {
                if (n == n3) {
                    return n + 0.5f;
                }
                n2 = n3 - 1;
            }
            else {
                if (n2 == n3) {
                    return n + 1.5f;
                }
                n = n3 + 1;
            }
        }
    }
}
