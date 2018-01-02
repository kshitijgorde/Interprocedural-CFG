// 
// Decompiled by Procyon v0.5.30
// 

public class da
{
    public int[][] p;
    public int[][] d;
    
    public final void p() {
        this.p[0][0] = 0;
        this.p[0][1] = 1;
        this.p[0][2] = 2;
        this.p[1][0] = 0;
        this.p[1][1] = 3;
        this.p[1][2] = 1;
        this.p[2][0] = 1;
        this.p[2][1] = 3;
        this.p[2][2] = 2;
        this.p[3][0] = 0;
        this.p[3][1] = 2;
        this.p[3][2] = 3;
    }
    
    public final void d() {
        this.d[0][0] = 2;
        this.d[0][1] = 3;
        this.d[0][2] = 0;
        this.d[0][3] = 1;
        this.d[1][0] = 7;
        this.d[1][1] = 6;
        this.d[1][2] = 5;
        this.d[1][3] = 4;
        this.d[2][0] = 0;
        this.d[2][1] = 4;
        this.d[2][2] = 5;
        this.d[2][3] = 1;
        this.d[3][0] = 1;
        this.d[3][1] = 5;
        this.d[3][2] = 6;
        this.d[3][3] = 2;
        this.d[4][0] = 2;
        this.d[4][1] = 6;
        this.d[4][2] = 7;
        this.d[4][3] = 3;
        this.d[5][0] = 3;
        this.d[5][1] = 7;
        this.d[5][2] = 4;
        this.d[5][3] = 0;
    }
    
    public da() {
        this.p = new int[4][3];
        this.d = new int[6][4];
        this.p();
        this.d();
    }
}
