// 
// Decompiled by Procyon v0.5.30
// 

public class No extends o
{
    public No(final String s, final int[] array, final a a) {
        super(s, 1, null, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 2;
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        if (j == null || k == null || l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        double n = 0.02;
        int n2 = 1;
        if (l.length > 1) {
            if (j[1] - j[0] >= k[1] - k[0]) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
        }
        double n3 = j[0];
        double n4 = k[0];
        double n5;
        double n6;
        if (n2 != 0) {
            if (l.length > 1) {
                n5 = j[1];
            }
            else {
                n5 = j[0];
            }
            n6 = n4;
        }
        else {
            if (l.length > 1) {
                n5 = k[1];
            }
            else {
                n5 = k[0];
            }
            n6 = n3;
        }
        int i = 0;
        int n7 = 0;
        while (i < l.length) {
            final double n8 = n4;
            final double n9 = n3;
            n4 = k[i];
            n3 = j[i];
            ++i;
            if (n2 != 0) {
                if (n4 <= n6) {
                    n2 = 0;
                    double n10 = n5;
                    if (n10 < n9) {
                        n10 = n9;
                    }
                    if (n10 < n3) {
                        n10 = n3;
                    }
                    super.sIb[0][n7++] = ((n2 != 0) ? n10 : (-n10));
                    n = 0.02;
                    n5 = n4;
                    n6 = n10 + n * (n5 - n10);
                    if (n6 < n9) {
                        n6 = n9;
                    }
                    if (n6 >= n3) {
                        continue;
                    }
                    n6 = n3;
                }
                else {
                    super.sIb[0][n7++] = ((n2 != 0) ? n6 : (-n6));
                    if (n3 > n5) {
                        n5 = n3;
                        n += 0.02;
                        if (n > 0.2) {
                            n = 0.2;
                        }
                    }
                    n6 += n * (n5 - n6);
                    if (n6 > n8) {
                        n6 = n8;
                    }
                    if (n6 <= n4) {
                        continue;
                    }
                    n6 = n4;
                }
            }
            else if (n3 >= n6) {
                n2 = 1;
                double n11 = n5;
                if (n11 > n8) {
                    n11 = n8;
                }
                if (n11 > n4) {
                    n11 = n4;
                }
                super.sIb[0][n7++] = ((n2 != 0) ? n11 : (-n11));
                n = 0.02;
                n5 = n3;
                n6 = n11 + n * (n5 - n11);
                if (n6 > n8) {
                    n6 = n8;
                }
                if (n6 <= n4) {
                    continue;
                }
                n6 = n4;
            }
            else {
                super.sIb[0][n7++] = ((n2 != 0) ? n6 : (-n6));
                if (n4 < n5) {
                    n5 = n4;
                    n += 0.02;
                    if (n > 0.2) {
                        n = 0.2;
                    }
                }
                n6 += n * (n5 - n6);
                if (n6 < n9) {
                    n6 = n9;
                }
                if (n6 >= n3) {
                    continue;
                }
                n6 = n3;
            }
        }
    }
}
