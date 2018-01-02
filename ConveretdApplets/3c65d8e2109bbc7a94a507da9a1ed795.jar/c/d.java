// 
// Decompiled by Procyon v0.5.30
// 

package c;

class d
{
    int else;
    int new;
    int try;
    int int;
    int a;
    int char;
    int for;
    int c;
    int[][] d;
    int[] do;
    int[] goto;
    int[] b;
    int[] case;
    int[][] void;
    int[][] long;
    int[][][] if;
    int[][][] byte;
    
    public d(final int else1) {
        this.else = else1;
        if (this.else != 20 && this.else != 30) {
            System.out.println("Unknown mode " + else1);
            return;
        }
        this.d = new int[6][g.m + 2];
        this.do = new int[g.m + 2];
        this.goto = new int[g.m + 2];
        this.b = new int[g.m + 2];
        this.case = new int[g.m + 2];
        this.void = new int[g.as][g.m + 2];
        this.long = new int[g.as][g.m + 2];
        this.if = new int[g.U][g.as][g.m + 2];
        this.byte = new int[g.U][g.as][g.m + 2];
        if (this.else == 20) {
            this.new = g.int;
            this.try = g.J;
            this.int = g.W;
            this.a = g.g;
            this.char = g.aB;
            this.for = g.ab;
            this.c = g.aO;
            System.arraycopy(g.M, 0, this.d, 0, 6);
            System.arraycopy(g.C, 0, this.do, 0, g.C.length);
            System.arraycopy(g.ae, 0, this.goto, 0, g.ae.length);
            System.arraycopy(g.goto, 0, this.b, 0, g.goto.length);
            System.arraycopy(g.aD, 0, this.case, 0, g.aD.length);
            System.arraycopy(g.aw, 0, this.void, 0, g.as);
            System.arraycopy(g.aE, 0, this.long, 0, g.as);
            System.arraycopy(g.aC, 0, this.if, 0, g.J);
            System.arraycopy(g.f, 0, this.byte, 0, g.J);
        }
        else if (this.else == 30) {
            this.new = g.case;
            this.try = g.O;
            this.int = g.Z;
            this.a = g.l;
            this.char = g.aF;
            this.for = g.ai;
            this.c = g.a;
            System.arraycopy(g.Q, 0, this.d, 0, 6);
            System.arraycopy(g.I, 0, this.do, 0, g.I.length);
            System.arraycopy(g.aj, 0, this.goto, 0, g.aj.length);
            System.arraycopy(g.b, 0, this.b, 0, g.b.length);
            System.arraycopy(g.aI, 0, this.case, 0, g.aI.length);
            System.arraycopy(g.ay, 0, this.void, 0, g.as);
            System.arraycopy(g.aJ, 0, this.long, 0, g.as);
            System.arraycopy(g.aH, 0, this.if, 0, g.O);
            System.arraycopy(g.k, 0, this.byte, 0, g.O);
        }
    }
}
