// 
// Decompiled by Procyon v0.5.30
// 

public class protected extends implements
{
    public protected(final String s, final int[] array, final class class1) {
        super(s, 1, null, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 2;
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        if (a == null || b == null || _ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        double n = 0.02;
        int n2 = 1;
        if (_.length > 1) {
            if (a[1] - a[0] >= b[1] - b[0]) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
        }
        double n3 = a[0];
        double n4 = b[0];
        double n5;
        double n6;
        if (n2 != 0) {
            if (_.length > 1) {
                n5 = a[1];
            }
            else {
                n5 = a[0];
            }
            n6 = n4;
        }
        else {
            if (_.length > 1) {
                n5 = b[1];
            }
            else {
                n5 = b[0];
            }
            n6 = n3;
        }
        int i = 0;
        int n7 = 0;
        while (i < _.length) {
            final double n8 = n4;
            final double n9 = n3;
            n4 = b[i];
            n3 = a[i];
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
                    super.t[0][n7++] = ((n2 != 0) ? n10 : (-n10));
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
                    super.t[0][n7++] = ((n2 != 0) ? n6 : (-n6));
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
                super.t[0][n7++] = ((n2 != 0) ? n11 : (-n11));
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
                super.t[0][n7++] = ((n2 != 0) ? n6 : (-n6));
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
