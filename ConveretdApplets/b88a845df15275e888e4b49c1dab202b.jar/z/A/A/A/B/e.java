// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;

public class e
{
    public static final e N;
    public static final e E;
    public static final e A;
    public static final e J;
    public static final e C;
    public static final e I;
    public static final e H;
    public static final e F;
    public static final e M;
    public static final e B;
    public static final e L;
    public static final e G;
    private final String D;
    private final int K;
    
    public static e A(final int n) throws C {
        switch (n) {
            case 1: {
                return e.N;
            }
            case 2: {
                return e.E;
            }
            case 3: {
                return e.A;
            }
            case 4: {
                return e.J;
            }
            case 5: {
                return e.C;
            }
            case 6: {
                return e.I;
            }
            case 7: {
                return e.H;
            }
            case 8: {
                return e.F;
            }
            case 9: {
                return e.M;
            }
            case 10: {
                return e.B;
            }
            case 11: {
                return e.L;
            }
            case 12: {
                return e.G;
            }
            default: {
                throw new C("value '" + n + "' does not represent a known data format.");
            }
        }
    }
    
    private e(final String d, final int k) {
        this.D = d;
        this.K = k;
    }
    
    public int A() {
        return this.K;
    }
    
    public String toString() {
        return this.D;
    }
    
    static {
        N = new e("BYTE", 1);
        E = new e("STRING", 2);
        A = new e("USHORT", 3);
        J = new e("ULONG", 4);
        C = new e("URATIONAL", 5);
        I = new e("SBYTE", 6);
        H = new e("UNDEFINED", 7);
        F = new e("SSHORT", 8);
        M = new e("SLONG", 9);
        B = new e("SRATIONAL", 10);
        L = new e("SINGLE", 11);
        G = new e("DOUBLE", 12);
    }
}
