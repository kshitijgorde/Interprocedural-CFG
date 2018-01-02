// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

class ImSprite extends ImBase
{
    int I;
    int Z;
    int C;
    StringBuffer B;
    int D;
    ImSprite F;
    int J;
    int S;
    int A;
    int E;
    int G;
    int H;
    ImSprite K;
    ImRect L;
    int M;
    ImSprite N;
    int O;
    int P;
    int Q;
    ImTrans R;
    ImVi T;
    int U;
    int V;
    int W;
    int X;
    int Y;
    int i;
    int z;
    int c;
    int b;
    int d;
    int f;
    int j;
    int s;
    
    final int I(final ImTrans imTrans) {
        if (this.H == 0 && this.L.C()) {
            return 0;
        }
        return imTrans.I + imTrans.B + imTrans.J + imTrans.Z + imTrans.D + (imTrans.S << 10) + imTrans.E + this.I + this.Z + this.C + this.J + this.S + this.E + this.A + this.H + this.M + this.U + (this.G << 10) + this.U * this.G + this.V + this.i + (this.d << 10) + this.f + this.j + this.s + ((this.T != null) ? 1 : 0);
    }
    
    final void I() {
        this.I = 256;
        this.Z = 0;
        this.C = 0;
        if (this.B != null) {
            this.B.setLength(0);
        }
        this.G = 0;
        this.H = 0;
        this.M = 0;
        this.O = 0;
        this.Q = 0;
        this.j = 32768;
        this.s = 32768;
        this.U = 0;
        this.V = 0;
        this.i = 0;
        this.d = 0;
        this.f = 0;
    }
    
    final void Z() {
        this.I();
        final ImSprite k = this.K;
        if (k != null) {
            if (k.F == this) {
                k.F = this.N;
            }
            else {
                ImSprite imSprite;
                for (imSprite = k.F; imSprite.N != this; imSprite = imSprite.N) {}
                imSprite.N = this.N;
            }
            this.K = null;
            this.N = null;
        }
    }
    
    ImSprite() {
        this.Z();
        this.L = new ImRect();
        this.R = new ImTrans();
    }
}
