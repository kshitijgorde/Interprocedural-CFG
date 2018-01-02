// 
// Decompiled by Procyon v0.5.30
// 

public class aea extends public
{
    public aea(final String s, final int[] array, final b b) {
        super(s, 1, null, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 2;
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        if (_ == null || g == null || h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        double n = 0.02;
        int n2 = 1;
        if (h.length > 1) {
            if (_[1] - _[0] >= g[1] - g[0]) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
        }
        double n3 = _[0];
        double n4 = g[0];
        double n5;
        double n6;
        if (n2 != 0) {
            if (h.length > 1) {
                n5 = _[1];
            }
            else {
                n5 = _[0];
            }
            n6 = n4;
        }
        else {
            if (h.length > 1) {
                n5 = g[1];
            }
            else {
                n5 = g[0];
            }
            n6 = n3;
        }
        int i = 0;
        int n7 = 0;
        while (i < h.length) {
            final double n8 = n4;
            final double n9 = n3;
            n4 = g[i];
            n3 = _[i];
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
                    super.Woa[0][n7++] = ((n2 != 0) ? n10 : (-n10));
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
                    super.Woa[0][n7++] = ((n2 != 0) ? n6 : (-n6));
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
                super.Woa[0][n7++] = ((n2 != 0) ? n11 : (-n11));
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
                super.Woa[0][n7++] = ((n2 != 0) ? n6 : (-n6));
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
