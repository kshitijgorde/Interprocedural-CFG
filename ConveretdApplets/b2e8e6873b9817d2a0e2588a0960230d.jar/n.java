import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class n
{
    private final e wJc;
    Vector xJc;
    int yJc;
    int zJc;
    boolean AJc;
    
    n(final e e, final int n, final int n2, final int n3, final int n4, final boolean aJc) {
        this.wJc = e;
        this.wJc = e;
        this.xJc = new Vector();
        this.yJc = e.a(this.wJc) / 2;
        this.zJc = e.b(this.wJc) / 2;
        this.AJc = aJc;
        this.xJc.addElement(this.b(n, n2, n3, n4));
    }
    
    n(final e e, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.wJc = e;
        this.wJc = e;
        this.xJc = new Vector();
        this.yJc = e.a(this.wJc) / 2;
        this.zJc = e.b(this.wJc) / 2;
        this.xJc.addElement(this.b(n, n2, n3, n4));
        this.xJc.addElement(this.b(n3, n4, n5, n6));
    }
    
    n(final e e, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.wJc = e;
        this.wJc = e;
        this.xJc = new Vector();
        this.yJc = e.a(this.wJc) / 2;
        this.zJc = e.b(this.wJc) / 2;
        this.xJc.addElement(this.b(n, n2, n3, n4));
        this.xJc.addElement(this.b(n3, n4, n5, n6));
        this.xJc.addElement(this.b(n5, n6, n7, n8));
    }
    
    int[] b(final int n, final int n2, final int n3, final int n4) {
        return new int[] { e._(this.wJc).x + (n - 1) * e.a(this.wJc) + ((n == 0) ? (this.yJc + 6) : ((n == e._(this.wJc) + 1) ? (this.yJc - 6) : this.yJc)), e._(this.wJc).y + (n2 - 1) * e.b(this.wJc) + ((n2 == 0) ? (this.zJc + 6) : ((n2 == e.n(this.wJc) + 1) ? (this.zJc - 6) : this.zJc)), e._(this.wJc).x + (n3 - 1) * e.a(this.wJc) + ((n3 == 0) ? (this.yJc + 6) : ((n3 == e._(this.wJc) + 1) ? (this.yJc - 6) : this.yJc)), e._(this.wJc).y + (n4 - 1) * e.b(this.wJc) + ((n4 == 0) ? (this.zJc + 6) : ((n4 == e.n(this.wJc) + 1) ? (this.zJc - 6) : this.zJc)) };
    }
}
