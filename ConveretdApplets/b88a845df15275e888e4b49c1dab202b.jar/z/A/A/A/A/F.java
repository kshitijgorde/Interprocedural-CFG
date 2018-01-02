// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.A;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class F extends B
{
    public F(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 6: {
                return this.B(0);
            }
            case 7: {
                return this.B(1);
            }
            case 8: {
                return this.B(2);
            }
            case 9: {
                return this.B(3);
            }
            case 0: {
                return this.B();
            }
            case 1: {
                return this.C();
            }
            case 3: {
                return this.A();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String A() {
        return this.A.K(3) + " pixels";
    }
    
    public String C() {
        return this.A.K(1) + " pixels";
    }
    
    public String B() {
        return this.A.K(0) + " bits";
    }
    
    public String B(final int n) throws C {
        final E q = ((D)this.A).Q(n);
        if (q == null) {
            throw new C("No Jpeg component exists with number " + n);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(q.E());
        sb.append(" component: Quantization table ");
        sb.append(q.A());
        sb.append(", Sampling factors ");
        sb.append(q.C());
        sb.append(" horiz/");
        sb.append(q.B());
        sb.append(" vert");
        return sb.toString();
    }
}
