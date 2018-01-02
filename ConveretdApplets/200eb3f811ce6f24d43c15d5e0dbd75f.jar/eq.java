import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class eq
{
    private Vector a;
    private char b;
    private char c;
    public String d;
    public Character e;
    
    public eq(final char b, final char c, final String d, final Character e) {
        this.a = new Vector();
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public Vector a(final String s) {
        b7 b7 = null;
        synchronized (this.a) {
            final int size = this.a.size();
            if (size > 0) {
                b7 = (b7)this.a.elementAt(size - 1);
                this.a.removeElementAt(size - 1);
            }
        }
        if (b7 == null) {
            b7 = new b7(this.b, this.c, this.d, this.e);
        }
        final Vector a = b7.a(s);
        this.a.addElement(b7);
        return a;
    }
}
