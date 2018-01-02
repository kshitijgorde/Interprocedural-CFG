// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class II extends G
{
    private float[][] Z;
    private int[] C;
    private int[] B;
    private Object[] D;
    
    final void I(final Object o) {
    }
    
    final Object I(final J j, final P i, final Object o) {
        final L z = j.Z;
        final U u2;
        final U u = u2 = new U();
        final O z2 = (O)o;
        u2.Z = z2;
        final O o2 = z2;
        u.I = i;
        u.C = new Object[o2.I];
        u.B = new Object[o2.I];
        u.D = new Object[o2.I];
        u.F = new K[o2.I];
        u.J = new E[o2.I];
        u.S = new H[o2.I];
        for (int k = 0; k < o2.I; ++k) {
            final int n = o2.C[k];
            final int n2 = o2.B[k];
            final int n3 = o2.D[k];
            u.F[k] = K.I[z.P[n]];
            u.C[k] = u.F[k].I(j, i, z.Q[n]);
            u.J[k] = E.Z[z.R[n2]];
            u.B[k] = u.J[k].I(j, i, z.T[n2]);
            u.S[k] = H.I[z.U[n3]];
            u.D[k] = u.S[k].I(j, i, z.V[n3]);
        }
        if (z.L == 0 || j.I != 0) {}
        u.A = z.Z;
        return u;
    }
    
    final Object I(final L l, final Z z) {
        final O o = new O();
        if (z.C(1) != 0) {
            o.I = z.C(4) + 1;
        }
        else {
            o.I = 1;
        }
        if (z.C(1) != 0) {
            o.J = z.C(8) + 1;
            for (int i = 0; i < o.J; ++i) {
                final int[] s = o.S;
                final int n = i;
                final int c = z.C(I(l.Z));
                s[n] = c;
                final int n2 = c;
                final int[] a = o.A;
                final int n3 = i;
                final int c2 = z.C(I(l.Z));
                a[n3] = c2;
                final int n4 = c2;
                if (n2 < 0 || n4 < 0 || n2 == n4 || n2 >= l.Z || n4 >= l.Z) {
                    o.I();
                    return null;
                }
            }
        }
        if (z.C(2) > 0) {
            o.I();
            return null;
        }
        if (o.I > 1) {
            for (int j = 0; j < l.Z; ++j) {
                o.Z[j] = z.C(4);
                if (o.Z[j] >= o.I) {
                    o.I();
                    return null;
                }
            }
        }
        for (int k = 0; k < o.I; ++k) {
            o.C[k] = z.C(8);
            if (o.C[k] >= l.E) {
                o.I();
                return null;
            }
            o.B[k] = z.C(8);
            if (o.B[k] >= l.G) {
                o.I();
                return null;
            }
            o.D[k] = z.C(8);
            if (o.D[k] >= l.H) {
                o.I();
                return null;
            }
        }
        return o;
    }
    
    final synchronized int I(final I i, final Object o) {
        final J e = i.E;
        final L z = e.Z;
        final U u = (U)o;
        final O z2 = u.Z;
        final P j = u.I;
        final int d = z.J[i.W];
        i.D = d;
        final int n = d;
        final float[] array = e.N[i.W][i.C][i.B][j.Z];
        if (this.Z == null || this.Z.length < z.Z) {
            this.Z = new float[z.Z][];
            this.B = new int[z.Z];
            this.C = new int[z.Z];
            this.D = new Object[z.Z];
        }
        for (int k = 0; k < z.Z; ++k) {
            final float[] array2 = i.I[k];
            final int n2 = z2.Z[k];
            this.D[k] = u.J[n2].I(i, u.B[n2], this.D[k]);
            if (this.D[k] != null) {
                this.B[k] = 1;
            }
            else {
                this.B[k] = 0;
            }
            for (int l = 0; l < n / 2; ++l) {
                array2[l] = 0.0f;
            }
        }
        for (int n3 = 0; n3 < z2.J; ++n3) {
            if (this.B[z2.S[n3]] != 0 || this.B[z2.A[n3]] != 0) {
                this.B[z2.S[n3]] = 1;
                this.B[z2.A[n3]] = 1;
            }
        }
        for (int n4 = 0; n4 < z2.I; ++n4) {
            int n5 = 0;
            for (int n6 = 0; n6 < z.Z; ++n6) {
                if (z2.Z[n6] == n4) {
                    if (this.B[n6] != 0) {
                        this.C[n5] = 1;
                    }
                    else {
                        this.C[n5] = 0;
                    }
                    this.Z[n5++] = i.I[n6];
                }
            }
            u.S[n4].I(i, u.D[n4], this.Z, this.C, n5);
        }
        for (int n7 = z2.J - 1; n7 >= 0; --n7) {
            final float[] array3 = i.I[z2.S[n7]];
            final float[] array4 = i.I[z2.A[n7]];
            for (int n8 = 0; n8 < n / 2; ++n8) {
                final float n9 = array3[n8];
                final float n10 = array4[n8];
                if (n9 > 0.0f) {
                    if (n10 > 0.0f) {
                        array4[n8] = (array3[n8] = n9) - n10;
                    }
                    else {
                        array3[n8] = (array4[n8] = n9) + n10;
                    }
                }
                else if (n10 > 0.0f) {
                    array4[n8] = (array3[n8] = n9) + n10;
                }
                else {
                    array3[n8] = (array4[n8] = n9) - n10;
                }
            }
        }
        for (int n11 = 0; n11 < z.Z; ++n11) {
            final float[] array5 = i.I[n11];
            final int n12 = z2.Z[n11];
            u.J[n12].I(i, u.B[n12], this.D[n11], array5);
        }
        for (int n13 = 0; n13 < z.Z; ++n13) {
            final float[] array6 = i.I[n13];
            ((ZI)e.O[i.W][0]).I(array6, array6);
        }
        for (int n14 = 0; n14 < z.Z; ++n14) {
            final float[] array7 = i.I[n14];
            if (this.B[n14] != 0) {
                for (int n15 = 0; n15 < n; ++n15) {
                    final float[] array8 = array7;
                    final int n16 = n15;
                    array8[n16] *= array[n15];
                }
            }
            else {
                for (int n17 = 0; n17 < n; ++n17) {
                    array7[n17] = 0.0f;
                }
            }
        }
        return 0;
    }
    
    private static int I(int i) {
        int n = 0;
        while (i > 1) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
}
