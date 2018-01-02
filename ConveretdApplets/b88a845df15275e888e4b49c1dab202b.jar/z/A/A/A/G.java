// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A;

import java.io.Serializable;

public class G implements Serializable
{
    private final int B;
    private final A A;
    
    public G(final int b, final A a) {
        this.B = b;
        this.A = a;
    }
    
    public int B() {
        return this.B;
    }
    
    public String E() {
        String s;
        for (s = Integer.toHexString(this.B); s.length() < 4; s = "0" + s) {}
        return "0x" + s;
    }
    
    public String D() throws C {
        return this.A.P(this.B);
    }
    
    public String A() {
        return this.A.J(this.B);
    }
    
    public String C() {
        return this.A.F();
    }
    
    public String toString() {
        String s;
        try {
            s = this.D();
        }
        catch (C c) {
            s = this.A.K(this.B()) + " (unable to formulate description)";
        }
        return "[" + this.A.F() + "] " + this.A() + " - " + s;
    }
}
