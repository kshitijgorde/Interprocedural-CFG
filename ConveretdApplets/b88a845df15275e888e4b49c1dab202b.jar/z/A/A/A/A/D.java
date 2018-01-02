// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.A;

import z.A.A.A.C;
import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class D extends A
{
    public static final int \u039b = 0;
    public static final int \u0398 = 1;
    public static final int \u039a = 3;
    public static final int \u0397 = 5;
    public static final int \u039f = 6;
    public static final int \u039e = 7;
    public static final int \u039d = 8;
    public static final int \u039c = 9;
    protected static final HashMap \u0399;
    
    public D() {
        this.A(new F(this));
    }
    
    public String F() {
        return "Jpeg";
    }
    
    protected HashMap D() {
        return D.\u0399;
    }
    
    public E Q(final int n) {
        return (E)this.C(6 + n);
    }
    
    public int L() throws C {
        return this.I(3);
    }
    
    public int M() throws C {
        return this.I(1);
    }
    
    public int K() throws C {
        return this.I(5);
    }
    
    static {
        (\u0399 = new HashMap()).put(new Integer(0), "Data Precision");
        D.\u0399.put(new Integer(3), "Image Width");
        D.\u0399.put(new Integer(1), "Image Height");
        D.\u0399.put(new Integer(5), "Number of Components");
        D.\u0399.put(new Integer(6), "Component 1");
        D.\u0399.put(new Integer(7), "Component 2");
        D.\u0399.put(new Integer(8), "Component 3");
        D.\u0399.put(new Integer(9), "Component 4");
    }
}
