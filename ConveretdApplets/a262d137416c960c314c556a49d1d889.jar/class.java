import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class class
{
    private final r O1;
    Vector P1;
    int Q1;
    int R1;
    boolean S1;
    
    class(final r r, final int n, final int n2, final int n3, final int n4, final boolean s1) {
        this.O1 = r;
        this.O1 = r;
        this.P1 = new Vector();
        this.Q1 = r.b(this.O1) / 2;
        this.R1 = r._(this.O1) / 2;
        this.S1 = s1;
        this.P1.addElement(this._(n, n2, n3, n4));
    }
    
    class(final r r, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.O1 = r;
        this.O1 = r;
        this.P1 = new Vector();
        this.Q1 = r.b(this.O1) / 2;
        this.R1 = r._(this.O1) / 2;
        this.P1.addElement(this._(n, n2, n3, n4));
        this.P1.addElement(this._(n3, n4, n5, n6));
    }
    
    class(final r r, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.O1 = r;
        this.O1 = r;
        this.P1 = new Vector();
        this.Q1 = r.b(this.O1) / 2;
        this.R1 = r._(this.O1) / 2;
        this.P1.addElement(this._(n, n2, n3, n4));
        this.P1.addElement(this._(n3, n4, n5, n6));
        this.P1.addElement(this._(n5, n6, n7, n8));
    }
    
    int[] _(final int n, final int n2, final int n3, final int n4) {
        return new int[] { r.a(this.O1).x + (n - 1) * r.b(this.O1) + ((n == 0) ? (this.Q1 + 6) : ((n == r.a(this.O1) + 1) ? (this.Q1 - 6) : this.Q1)), r.a(this.O1).y + (n2 - 1) * r._(this.O1) + ((n2 == 0) ? (this.R1 + 6) : ((n2 == r.m(this.O1) + 1) ? (this.R1 - 6) : this.R1)), r.a(this.O1).x + (n3 - 1) * r.b(this.O1) + ((n3 == 0) ? (this.Q1 + 6) : ((n3 == r.a(this.O1) + 1) ? (this.Q1 - 6) : this.Q1)), r.a(this.O1).y + (n4 - 1) * r._(this.O1) + ((n4 == 0) ? (this.R1 + 6) : ((n4 == r.m(this.O1) + 1) ? (this.R1 - 6) : this.R1)) };
    }
}
