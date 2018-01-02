import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class x extends bk
{
    bk C;
    bk B;
    
    public x(final bk c, final bk b) {
        super(c._fldnew);
        this.C = c;
        this.B = b;
    }
    
    public double _mthfor(final int n) throws c {
        return Math.abs(this.C.a(n) - this.B.a(n));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
