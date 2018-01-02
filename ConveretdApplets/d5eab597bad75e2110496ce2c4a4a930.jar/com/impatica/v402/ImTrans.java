// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

class ImTrans extends ImBase
{
    int I;
    int Z;
    int C;
    int B;
    int D;
    int F;
    int J;
    int S;
    int A;
    int E;
    static final short[] G;
    
    static final int I(final int n) {
        return ImTrans.G[(n >> 7) + 64 & 0xFF];
    }
    
    static final int Z(final int n) {
        return ImTrans.G[n >> 7 & 0xFF];
    }
    
    ImTrans() {
        this.I();
    }
    
    final void C(final int n) {
        this.E = this.E * n >> 8;
    }
    
    final void I(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.I(ImBase.I(n, this.I) + ImBase.I(n2, this.B), ImBase.I(n, this.Z) + ImBase.I(n2, this.D), ImBase.I(n, this.C) + ImBase.I(n2, this.F), ImBase.I(n3, this.I) + ImBase.I(n4, this.B), ImBase.I(n3, this.Z) + ImBase.I(n4, this.D), ImBase.I(n3, this.C) + ImBase.I(n4, this.F), ImBase.I(n5, this.I) + ImBase.I(n6, this.B) + this.J, ImBase.I(n5, this.Z) + ImBase.I(n6, this.D) + this.S, ImBase.I(n5, this.C) + ImBase.I(n6, this.F) + this.A);
    }
    
    final void I() {
        this.I(32768, 0, 0, 0, 32768, 0, 0, 0, 32768);
        this.E = 256;
    }
    
    final int Z(final int n, final int n2) {
        return this.I * n + this.B * n2 + this.J;
    }
    
    final int C(final int n, final int n2) {
        return this.Z(n, n2) >> 15;
    }
    
    final int B(final int n, final int n2) {
        return this.Z * n + this.D * n2 + this.S;
    }
    
    final int D(final int n, final int n2) {
        return this.B(n, n2) >> 15;
    }
    
    final void B(final int n) {
        if (n != 0) {
            final int n2 = -n * 91 & 0x7FFF;
            final int z = Z(n2);
            final int i = I(n2);
            this.I(i, -z, z, i, 0, 0);
        }
    }
    
    final boolean Z() {
        return this.I < 0 || this.Z != 0 || this.B != 0 || this.D < 0;
    }
    
    final void F(final int n, final int n2) {
        if (n != 32768 || n2 != 32768) {
            this.I(n, 0, 0, n2, 0, 0);
        }
    }
    
    final void I(final int i, final int z, final int c, final int b, final int d, final int f, final int j, final int s, final int a) {
        this.I = i;
        this.Z = z;
        this.C = c;
        this.B = b;
        this.D = d;
        this.F = f;
        this.J = j;
        this.S = s;
        this.A = a;
    }
    
    final void I(final ImTrans imTrans) {
        this.I(imTrans.I, imTrans.Z, imTrans.C, imTrans.B, imTrans.D, imTrans.F, imTrans.J, imTrans.S, imTrans.A);
        this.E = imTrans.E;
    }
    
    final void J(final int n, final int n2) {
        if (n != 0 || n2 != 0) {
            this.I(32768, 0, 0, 32768, n, n2);
        }
    }
    
    final void S(final int n, final int n2) {
        this.J(n << 15, n2 << 15);
    }
    
    static {
        G = new short[] { 0, 804, 1607, 2410, 3211, 4011, 4807, 5601, 6392, 7179, 7961, 8739, 9511, 10278, 11038, 11792, 12539, 13278, 14009, 14732, 15446, 16150, 16845, 17530, 18204, 18867, 19519, 20159, 20787, 21402, 22004, 22594, 23169, 23731, 24278, 24811, 25329, 25831, 26318, 26789, 27244, 27683, 28105, 28510, 28897, 29268, 29621, 29955, 30272, 30571, 30851, 31113, 31356, 31580, 31785, 31970, 32137, 32284, 32412, 32520, 32609, 32678, 32727, 32757, 32767, 32757, 32727, 32678, 32609, 32520, 32412, 32284, 32137, 31970, 31785, 31580, 31356, 31113, 30851, 30571, 30272, 29955, 29621, 29268, 28897, 28510, 28105, 27683, 27244, 26789, 26318, 25831, 25329, 24811, 24278, 23731, 23169, 22594, 22004, 21402, 20787, 20159, 19519, 18867, 18204, 17530, 16845, 16150, 15446, 14732, 14009, 13278, 12539, 11792, 11038, 10278, 9511, 8739, 7961, 7179, 6392, 5601, 4807, 4011, 3211, 2410, 1607, 804, 0, -804, -1607, -2410, -3211, -4011, -4807, -5601, -6392, -7179, -7961, -8739, -9511, -10278, -11038, -11792, -12539, -13278, -14009, -14732, -15446, -16150, -16845, -17530, -18204, -18867, -19519, -20159, -20787, -21402, -22004, -22594, -23169, -23731, -24278, -24811, -25329, -25831, -26318, -26789, -27244, -27683, -28105, -28510, -28897, -29268, -29621, -29955, -30272, -30571, -30851, -31113, -31356, -31580, -31785, -31970, -32137, -32284, -32412, -32520, -32609, -32678, -32727, -32757, -32767, -32757, -32727, -32678, -32609, -32520, -32412, -32284, -32137, -31970, -31785, -31580, -31356, -31113, -30851, -30571, -30272, -29955, -29621, -29268, -28897, -28510, -28105, -27683, -27244, -26789, -26318, -25831, -25329, -24811, -24278, -23731, -23169, -22594, -22004, -21402, -20787, -20159, -19519, -18867, -18204, -17530, -16845, -16150, -15446, -14732, -14009, -13278, -12539, -11792, -11038, -10278, -9511, -8739, -7961, -7179, -6392, -5601, -4807, -4011, -3211, -2410, -1607, -804 };
    }
}
