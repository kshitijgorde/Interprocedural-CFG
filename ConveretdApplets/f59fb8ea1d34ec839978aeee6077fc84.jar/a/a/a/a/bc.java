// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bc
{
    az al;
    String Z;
    String t;
    static final char[] ak;
    static final char[] F;
    static final char[] case;
    static final char[] ac;
    static final char[] ag;
    static final char[] p;
    static final char[] c;
    static final char[] M;
    static final char[] P;
    static final char[] ad;
    static final char[] J;
    static final char[] L;
    static final char[] T;
    static final char[] D;
    static final char[] g;
    static final char[] N;
    static final char[] s;
    static final char[] z;
    static final char[] try;
    static final char[] new;
    static final char[] aa;
    static final char[] int;
    static final char[] do;
    static final char[] V;
    static final char[] H;
    static final char[] ai;
    static final char[] aj;
    static final char[] Q;
    static final char[] e;
    static final char[] X;
    static final char[] q;
    static final char[] K;
    static final char[] W;
    static final char[] else;
    static final char[] O;
    static final char[] j;
    static final int v = 1;
    static final int long = 2;
    static final int I = 3;
    static final int o = 4;
    static final int u = 5;
    static final int am = 6;
    static final int R = 7;
    static final int f = 8;
    static final int l = 9;
    static final int n = 10;
    static final int char = 11;
    static final int i = 12;
    static final int r = 13;
    static final int a = 14;
    static final int k = 15;
    static final int goto = 16;
    static final int S = 17;
    static final int for = 18;
    static final int B = 19;
    static final int G = 20;
    static final int w = 21;
    static final int C = 22;
    static final int y = 23;
    static final int m = 24;
    static final int af = 25;
    static final int byte = 26;
    static final int x = 27;
    static final int E = 28;
    static final int d = 29;
    static final int U = 30;
    static final int Y = 31;
    static final int ah = 32;
    static final int void = 33;
    static final int A = 34;
    static final int b = 35;
    static final int ae = 36;
    static final int if = 37;
    static final int ab = 38;
    b3 h;
    
    static {
        ak = new char[] { 's', 'q', 'r', 't', '\0' };
        F = new char[] { 's', 'i', 'n', '\0' };
        case = new char[] { 'c', 'o', 's', '\0' };
        ac = new char[] { 't', 'a', 'n', '\0' };
        ag = new char[] { 'a', 's', 'i', 'n', '\0' };
        p = new char[] { 'a', 'c', 'o', 's', '\0' };
        c = new char[] { 'a', 't', 'a', 'n', '\0' };
        M = new char[] { 'p', 'o', 'w', '\0' };
        P = new char[] { 't', 'r', 'u', 'n', 'c', '\0' };
        ad = new char[] { 'm', 'o', 'd', '\0' };
        J = new char[] { 'g', 'e', 't', 't', 'i', 'm', 'e', '\0' };
        L = new char[] { 's', 'l', 'e', 'e', 'p', '\0' };
        T = new char[] { 'p', 'r', 'i', 'n', 't', '\0' };
        D = new char[] { 'g', 'e', 't', '\0' };
        g = new char[] { 's', 'e', 't', '\0' };
        N = new char[] { 'l', 'o', 'a', 'd', 'p', 'a', 'n', 'o', '\0' };
        s = new char[] { 'p', 'r', 'e', 'l', 'o', 'a', 'd', '\0' };
        z = new char[] { 'r', 'm', 'p', 'r', 'e', 'l', 'o', 'a', 'd', '\0' };
        try = new char[] { 'g', 'u', 'i', '\0' };
        new = new char[] { 'p', 'a', 'n', 'o', '\0' };
        aa = new char[] { 'a', 'b', 'o', 'u', 't', '\0' };
        int = new char[] { 'h', 'e', 'l', 'p', '\0' };
        do = new char[] { 'o', 'p', 'e', 'n', '\0' };
        V = new char[] { 'j', 'a', 'v', 'a', 's', 'c', 'r', 'i', 'p', 't', '\0' };
        H = new char[] { 'd', 'i', 's', 'p', 'l', 'a', 'y', '\0' };
        ai = new char[] { 't', 'o', 'f', 'l', 'o', 'a', 't', '\0' };
        aj = new char[] { 't', 'o', 'i', 'n', 't', '\0' };
        Q = new char[] { 't', 'o', 'b', 'o', 'o', 'l', '\0' };
        e = new char[] { 't', 'o', 'a', 's', 'c', 'i', 'i', '\0' };
        X = new char[] { 'a', 's', 'c', 'i', 'i', 't', 'o', 's', 't', 'r', 'i', 'n', 'g', '\0' };
        q = new char[] { 'l', 'o', 'c', 'k', 'm', 'o', 'u', 's', 'e', '\0' };
        K = new char[] { 'l', 'o', 'c', 'k', 'k', 'e', 'y', 'b', 'o', 'a', 'r', 'd', '\0' };
        W = new char[] { 's', 'e', 't', 'a', 'r', 'r', 'a', 'y', '\0' };
        else = new char[] { 'l', 'e', 'n', 'g', 't', 'h', '\0' };
        O = new char[] { 'r', 'a', 'n', 'd', 'o', 'm', '\0' };
        j = new char[] { 's', 'h', 'o', 'w', 's', 't', 'a', 't', 'u', 's', '\0' };
    }
    
    bc(final b3 h) {
        this.Z = "bad number of arguments";
        this.t = "bad argument type";
        this.al = new az();
        this.h = h;
    }
    
    public void a() {
        this.al = null;
    }
    
    int a(final char[] array) {
        if (a.a.a.a.i.if(array, bc.ak) == 0) {
            return 1;
        }
        if (a.a.a.a.i.if(array, bc.F) == 0) {
            return 2;
        }
        if (a.a.a.a.i.if(array, bc.case) == 0) {
            return 3;
        }
        if (a.a.a.a.i.if(array, bc.ac) == 0) {
            return 4;
        }
        if (a.a.a.a.i.if(array, bc.ag) == 0) {
            return 5;
        }
        if (a.a.a.a.i.if(array, bc.p) == 0) {
            return 6;
        }
        if (a.a.a.a.i.if(array, bc.c) == 0) {
            return 7;
        }
        if (a.a.a.a.i.if(array, bc.M) == 0) {
            return 8;
        }
        if (a.a.a.a.i.if(array, bc.P) == 0) {
            return 9;
        }
        if (a.a.a.a.i.if(array, bc.ad) == 0) {
            return 10;
        }
        if (a.a.a.a.i.if(array, bc.J) == 0) {
            return 11;
        }
        if (a.a.a.a.i.if(array, bc.L) == 0) {
            return 12;
        }
        if (a.a.a.a.i.if(array, bc.T) == 0) {
            return 13;
        }
        if (a.a.a.a.i.if(array, bc.D) == 0) {
            return 16;
        }
        if (a.a.a.a.i.if(array, bc.g) == 0) {
            return 17;
        }
        if (a.a.a.a.i.if(array, bc.N) == 0) {
            return 18;
        }
        if (a.a.a.a.i.if(array, bc.new) == 0) {
            return 20;
        }
        if (a.a.a.a.i.if(array, bc.try) == 0) {
            return 19;
        }
        if (a.a.a.a.i.if(array, bc.aa) == 0) {
            return 21;
        }
        if (a.a.a.a.i.if(array, bc.int) == 0) {
            return 22;
        }
        if (a.a.a.a.i.if(array, bc.do) == 0) {
            return 23;
        }
        if (a.a.a.a.i.if(array, bc.V) == 0) {
            return 24;
        }
        if (a.a.a.a.i.if(array, bc.s) == 0) {
            return 25;
        }
        if (a.a.a.a.i.if(array, bc.z) == 0) {
            return 37;
        }
        if (a.a.a.a.i.if(array, bc.H) == 0) {
            return 26;
        }
        if (a.a.a.a.i.if(array, bc.ai) == 0) {
            return 27;
        }
        if (a.a.a.a.i.if(array, bc.aj) == 0) {
            return 28;
        }
        if (a.a.a.a.i.if(array, bc.Q) == 0) {
            return 29;
        }
        if (a.a.a.a.i.if(array, bc.e) == 0) {
            return 30;
        }
        if (a.a.a.a.i.if(array, bc.X) == 0) {
            return 38;
        }
        if (a.a.a.a.i.if(array, bc.q) == 0) {
            return 31;
        }
        if (a.a.a.a.i.if(array, bc.K) == 0) {
            return 32;
        }
        if (a.a.a.a.i.if(array, bc.W) == 0) {
            return 33;
        }
        if (a.a.a.a.i.if(array, bc.else) == 0) {
            return 34;
        }
        if (a.a.a.a.i.if(array, bc.O) == 0) {
            return 35;
        }
        if (a.a.a.a.i.if(array, bc.j) == 0) {
            return 36;
        }
        return -1;
    }
    
    void a(final bi bi, final bi[] array, final int n, final int n2) throws a5 {
        switch (n) {
            case 1: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.sqrt(bi.else);
                break;
            }
            case 2: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.sin(bi.else);
                break;
            }
            case 3: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.cos(bi.else);
                break;
            }
            case 4: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.tan(bi.else);
                break;
            }
            case 5: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.asin(bi.else);
                break;
            }
            case 6: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.acos(bi.else);
                break;
            }
            case 7: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi.else = (float)Math.atan(bi.else);
                break;
            }
            case 8: {
                final bi bi2 = new bi();
                if (n2 != 2) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4 || array[1].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 3;
                this.al.a(bi, array[0], true);
                bi2.char = 3;
                this.al.a(bi2, array[1], true);
                bi.else = (float)Math.pow(bi.else, bi2.else);
                break;
            }
            case 9: {
                final bi bi3 = new bi();
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 4) {
                    throw new a5(this.t);
                }
                bi.char = 2;
                bi3.char = 3;
                this.al.a(bi3, array[0], true);
                bi.case = (int)bi3.else;
                break;
            }
            case 10: {
                final bi bi4 = new bi();
                if (n2 != 2) {
                    throw new a5(this.Z);
                }
                if (array[0].char > 2 || array[1].char > 2) {
                    throw new a5(this.t);
                }
                bi.char = 2;
                this.al.a(bi, array[0], true);
                bi4.char = 2;
                this.al.a(bi4, array[1], true);
                bi.case %= bi4.case;
                break;
            }
            case 11: {
                if (n2 != 0) {
                    throw new a5(this.Z);
                }
                bi.char = 2;
                bi.case = System.currentTimeMillis();
                break;
            }
            case 12: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char > 2) {
                    throw new a5(this.t);
                }
                bi.char = 2;
                this.al.a(bi, array[0], true);
                try {
                    Thread.sleep(bi.case);
                }
                catch (InterruptedException ex) {}
                break;
            }
            case 13: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                switch (array[0].char) {
                    case 1: {
                        if (array[0].long) {
                            System.out.print("true");
                            break;
                        }
                        System.out.print("false");
                        break;
                    }
                    case 2: {
                        System.out.print(array[0].case);
                        break;
                    }
                    case 3: {
                        System.out.print(array[0].else);
                        break;
                    }
                    case 4: {
                        for (int n3 = 0; n3 < array[0].int.length && array[0].int[n3] != '\0'; ++n3) {
                            System.out.print(array[0].int[n3]);
                        }
                        break;
                    }
                }
                bi.char = 2;
                bi.case = 0L;
                break;
            }
            case 16: {
                if (n2 != 2) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4 || array[1].char != 4) {
                    throw new a5(this.t);
                }
                final bi if1 = this.h.if(array[0].int, array[1].int);
                if (if1 == null) {
                    throw new a5("Can't get data");
                }
                bi.char = if1.char;
                this.al.a(bi, if1, true);
                break;
            }
            case 17: {
                if (n2 != 3) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4 || array[1].char != 4) {
                    throw new a5(this.t);
                }
                this.h.a(array[0].int, array[1].int, array[2]);
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 18: {
                if (n2 != 2) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4 || array[1].char != 1) {
                    throw new a5(this.t);
                }
                this.h.i.a(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)), this.h.void, array[1].long);
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 20: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                final bi if2 = this.h.i.if(array[0].int);
                bi.char = if2.char;
                bi.long = if2.long;
                bi.case = if2.case;
                bi.else = if2.else;
                bi.int = if2.int;
                bi.a = if2.a;
                bi.goto = if2.goto;
                break;
            }
            case 19: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                final bi do1 = this.h.i.do(array[0].int);
                bi.char = do1.char;
                bi.long = do1.long;
                bi.case = do1.case;
                bi.else = do1.else;
                bi.int = do1.int;
                bi.a = do1.a;
                bi.goto = do1.goto;
                break;
            }
            case 22: {
                if (n2 == 1 && array[0].char == 4) {
                    this.h.i.int(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)));
                }
                else {
                    this.h.i.int("");
                }
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 21: {
                this.h.i.char();
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 23: {
                if (n2 == 2) {
                    if (array[0].char != 4 || array[1].char != 4) {
                        throw new a5(this.t);
                    }
                    this.h.i.a(array[0].int, array[1].int);
                }
                else {
                    if (n2 != 1) {
                        throw new a5(this.Z);
                    }
                    if (array[0].char != 4) {
                        throw new a5(this.t);
                    }
                    this.h.i.a(array[0].int, null);
                }
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 24: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                this.h.i.a(array[0].int);
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 25: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                this.h.i.if(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)), this.h.void);
                bi.char = 1;
                bi.long = true;
                break;
            }
            case 37: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                bi.char = 1;
                bi.long = this.h.i.do(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)));
                break;
            }
            case 26: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                bi.long = this.h.i.try(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)));
                bi.char = 1;
                break;
            }
            case 29: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                bi.long = az.a(array[0]);
                bi.char = 1;
                break;
            }
            case 28: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                bi.case = az.if(array[0]);
                bi.char = 2;
                break;
            }
            case 27: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                bi.else = az.do(array[0]);
                bi.char = 3;
                break;
            }
            case 30: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                if (a.a.a.a.i.a(array[0].int) < 1) {
                    bi.case = 0L;
                }
                else {
                    bi.case = array[0].int[0];
                }
                bi.char = 2;
                break;
            }
            case 38: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 2) {
                    throw new a5(this.t);
                }
                (bi.int = new char[1])[0] = (char)array[0].case;
                bi.char = 4;
                break;
            }
            case 31: {
                if (n2 != 1 && n2 != 0) {
                    throw new a5(this.Z);
                }
                if (n2 == 1 && array[0].char != 1) {
                    throw new a5(this.t);
                }
                if (n2 == 1) {
                    this.h.i.r = array[0].long;
                    bi.long = true;
                }
                else {
                    bi.long = this.h.i.r;
                }
                bi.char = 1;
                break;
            }
            case 32: {
                if (n2 != 1 && n2 != 0) {
                    throw new a5(this.Z);
                }
                if (n2 == 1 && array[0].char != 1) {
                    throw new a5(this.t);
                }
                if (n2 == 1) {
                    this.h.i.v = array[0].long;
                    bi.long = true;
                }
                else {
                    bi.long = this.h.i.v;
                }
                bi.char = 1;
                break;
            }
            case 33: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                int n4;
                if (array[0].char == 2) {
                    n4 = (int)array[0].case;
                }
                else {
                    if (array[0].char != 3) {
                        throw new a5(this.t);
                    }
                    n4 = (int)array[0].else;
                }
                a(bi, n4);
                break;
            }
            case 34: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char == 5) {
                    bi.case = array[0].goto;
                }
                else if (array[0].char == 4) {
                    bi.case = a.a.a.a.i.a(array[0].int);
                }
                else {
                    bi.case = 0L;
                }
                bi.char = 2;
                break;
            }
            case 35: {
                if (n2 != 0) {
                    throw new a5(this.Z);
                }
                bi.char = 3;
                bi.else = Math.random();
                break;
            }
            case 36: {
                if (n2 != 1) {
                    throw new a5(this.Z);
                }
                if (array[0].char != 4) {
                    throw new a5(this.t);
                }
                this.h.i.showStatus(new String(array[0].int, 0, a.a.a.a.i.a(array[0].int)));
                bi.char = 1;
                bi.long = true;
                break;
            }
        }
    }
    
    public static void a(final bi bi, final int goto1) throws a5 {
        if (goto1 < 0) {
            throw new a5("Array size=" + goto1 + ".\n Can create an array with negative size.");
        }
        bi.char = 5;
        bi.goto = goto1;
        bi.a = new bi[goto1];
        for (int i = 0; i < goto1; ++i) {
            bi.a[i] = new bi();
        }
    }
}
