import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bg
{
    public byte[] a;
    public Vector b;
    
    public bg() {
        this.b = new Vector();
    }
    
    public bg(final byte[] a, final b5 b5) {
        this.b = new Vector();
        this.a = a;
        bh bh;
        for (int i = 0; i < a.length; i += bh.b() + 1) {
            bh = new bh(a, i, b5);
            if (bh.a() > 0) {
                this.b.insertElementAt(bh, this.b.size());
            }
            else if (bh.b() == 0) {
                break;
            }
        }
    }
    
    public final bh a(final int n) {
        if (n <= this.b.size()) {
            return this.b.elementAt(n);
        }
        return null;
    }
    
    public final void a(final bh bh) {
        this.b.addElement(bh);
    }
    
    public final bi a(final int n, final int n2) {
        return this.a(n).a(n2);
    }
    
    public final void a(final bi bi, final int n, final int n2) {
        this.a(n).a(bi, n2);
    }
    
    public final int a() {
        return this.b.size();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.a(); ++i) {
            sb.append(this.b.elementAt(i).toString());
        }
        return sb.toString();
    }
    
    public final int b() {
        int n = 0;
        if (this.b != null) {
            for (int i = 0; i < this.b.size(); ++i) {
                n += this.a(i).b();
            }
        }
        return n;
    }
}
