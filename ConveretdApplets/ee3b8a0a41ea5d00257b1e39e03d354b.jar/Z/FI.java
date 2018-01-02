// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class FI extends H
{
    private static int[][][] pow;
    
    final Object I(final L l, final Z z) {
        int n = 0;
        final Q q = new Q();
        q.I = z.C(24);
        q.Z = z.C(24);
        q.C = z.C(24) + 1;
        q.B = z.C(6) + 1;
        q.D = z.C(8);
        for (int i = 0; i < q.B; ++i) {
            int c = z.C(3);
            if (z.C(1) != 0) {
                c |= z.C(5) << 3;
            }
            q.F[i] = c;
            n += pow(c);
        }
        for (int j = 0; j < n; ++j) {
            q.J[j] = z.C(8);
        }
        if (q.D >= l.K) {
            this.I(q);
            return null;
        }
        for (int k = 0; k < n; ++k) {
            if (q.J[k] >= l.K) {
                this.I(q);
                return null;
            }
        }
        return q;
    }
    
    final Object I(final J j, final P p3, final Object o) {
        final Q i = (Q)o;
        final V v = new V();
        int n;
        int b = n = 0;
        v.I = i;
        v.Z = p3.B;
        v.C = i.B;
        v.D = j.P;
        v.F = j.P[i.D];
        final int k = v.F.I;
        v.J = new int[v.C][];
        for (int l = 0; l < v.C; ++l) {
            final int m = I(i.F[l]);
            if (m != 0) {
                if (m > b) {
                    b = m;
                }
                v.J[l] = new int[m];
                for (int n2 = 0; n2 < m; ++n2) {
                    if ((i.F[l] & 1 << n2) != 0x0) {
                        v.J[l][n2] = i.J[n++];
                    }
                }
            }
        }
        v.S = (int)Math.rint(Math.pow(v.C, k));
        v.B = b;
        v.A = new int[v.S][];
        for (int n3 = 0; n3 < v.S; ++n3) {
            int n4 = n3;
            int n5 = v.S / v.C;
            v.A[n3] = new int[k];
            for (int n6 = 0; n6 < k; ++n6) {
                final int n7 = n4 / n5;
                n4 -= n7 * n5;
                n5 /= v.C;
                v.A[n3][n6] = n7;
            }
        }
        return v;
    }
    
    final void I(final Object o) {
    }
    
    static final synchronized int I(final I i, final Object o, final float[][] array, final int n, final int n2) {
        final V v = (V)o;
        final Q j = v.I;
        final int c = j.C;
        final int k = v.F.I;
        final int n3 = (j.Z - j.I) / c;
        final int n4 = (n3 + k - 1) / k;
        if (FI.pow.length < n) {
            FI.pow = new int[n][][];
            for (int l = 0; l < n; ++l) {
                FI.pow[l] = new int[n4][];
            }
        }
        else {
            for (int n5 = 0; n5 < n; ++n5) {
                if (FI.pow[n5] == null || FI.pow[n5].length < n4) {
                    FI.pow[n5] = new int[n4][];
                }
            }
        }
        for (int n6 = 0; n6 < v.B; ++n6) {
            int n8;
            int n7 = n8 = 0;
            while (n8 < n3) {
                if (n6 == 0) {
                    for (int n9 = 0; n9 < n; ++n9) {
                        final int m = v.F.I(i.Z);
                        if (m == -1) {
                            return 0;
                        }
                        FI.pow[n9][n7] = v.A[m];
                        if (FI.pow[n9][n7] == null) {
                            return 0;
                        }
                    }
                }
                for (int n10 = 0; n10 < k && n8 < n3; ++n10, ++n8) {
                    for (int n11 = 0; n11 < n; ++n11) {
                        final int n12 = j.I + n8 * c;
                        if ((j.F[FI.pow[n11][n7][n10]] & 1 << n6) != 0x0) {
                            final C c2 = v.D[v.J[FI.pow[n11][n7][n10]][n6]];
                            if (c2 != null) {
                                if (n2 == 0) {
                                    if (c2.I(array[n11], n12, i.Z, c) == -1) {
                                        return 0;
                                    }
                                }
                                else if (n2 == 1 && c2.Z(array[n11], n12, i.Z, c) == -1) {
                                    return 0;
                                }
                            }
                        }
                    }
                }
                ++n7;
            }
        }
        return 0;
    }
    
    static final int I(final I i, final Object o, final float[][] array, final int n) {
        final V v = (V)o;
        final Q j = v.I;
        final int c = j.C;
        final int k = v.F.I;
        final int n2 = (j.Z - j.I) / c;
        final int[][] array2 = new int[(n2 + k - 1) / k][];
        for (int l = 0; l < v.B; ++l) {
            int n4;
            int n3 = n4 = 0;
            while (n4 < n2) {
                if (l == 0) {
                    final int m = v.F.I(i.Z);
                    if (m == -1) {
                        return 0;
                    }
                    array2[n3] = v.A[m];
                    if (array2[n3] == null) {
                        return 0;
                    }
                }
                for (int n5 = 0; n5 < k && n4 < n2; ++n5, ++n4) {
                    final int n6 = j.I + n4 * c;
                    if ((j.F[array2[n3][n5]] & 1 << l) != 0x0) {
                        final C c2 = v.D[v.J[array2[n3][n5]][l]];
                        if (c2 != null && c2.I(array, n6, n, i.Z, c) == -1) {
                            return 0;
                        }
                    }
                }
                ++n3;
            }
        }
        return 0;
    }
    
    int I(final I i, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2 = 0;
        for (int j = 0; j < n; ++j) {
            if (array2[j] != 0) {
                array[n2++] = array[j];
            }
        }
        if (n2 != 0) {
            return I(i, o, array, n2, 0);
        }
        return 0;
    }
    
    private static int I(int i) {
        int n = 0;
        while (i != 0) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
    
    private static int pow(int i) {
        int n = 0;
        while (i != 0) {
            n += (i & 0x1);
            i >>>= 1;
        }
        return n;
    }
    
    static {
        FI.pow = new int[2][][];
    }
}
