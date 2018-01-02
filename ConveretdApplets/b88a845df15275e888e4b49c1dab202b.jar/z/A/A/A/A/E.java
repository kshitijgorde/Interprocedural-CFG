// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.A;

import z.A.A.A.C;
import java.io.Serializable;

public class E implements Serializable
{
    private final int C;
    private final int A;
    private final int B;
    
    public E(final int c, final int a, final int b) {
        this.C = c;
        this.A = a;
        this.B = b;
    }
    
    public int D() {
        return this.C;
    }
    
    public String E() throws C {
        switch (this.C) {
            case 1: {
                return "Y";
            }
            case 2: {
                return "Cb";
            }
            case 3: {
                return "Cr";
            }
            case 4: {
                return "I";
            }
            case 5: {
                return "Q";
            }
            default: {
                throw new C("Unsupported component id: " + this.C);
            }
        }
    }
    
    public int A() {
        return this.B;
    }
    
    public int C() {
        return this.A & 0xF;
    }
    
    public int B() {
        return this.A >> 4 & 0xF;
    }
}
