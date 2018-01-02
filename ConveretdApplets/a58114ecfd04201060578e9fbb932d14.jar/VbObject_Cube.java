// 
// Decompiled by Procyon v0.5.30
// 

class VbObject_Cube extends VbObject
{
    VbObject_Cube(final int n, final int n2, final boolean b, final boolean b2) {
        if (b) {
            final int n3 = n - 2;
            super.iNumBalls = n * n * n - n3 * n3 * n3;
        }
        else {
            super.iNumBalls = n * n * n;
        }
        super.Balls = new VbBall[super.iNumBalls];
        final int n4 = n2 / n;
        int n5 = 0;
        int n6 = -(n2 / 2);
        for (int i = 0; i < n; ++i) {
            int n7 = -(n2 / 2);
            for (int j = 0; j < n; ++j) {
                int n8 = -(n2 / 2);
                for (int k = 0; k < n; ++k) {
                    if (!b || i == 0 || i == n - 1 || k == 0 || k == n - 1 || j == 0 || j == n - 1) {
                        int n9;
                        if (b2) {
                            n9 = (i * n * n + j * n + k) % 6;
                        }
                        else {
                            n9 = k % 6;
                        }
                        super.Balls[n5] = new VbBall(n8, n7, n6, n9, 0, k * 4096 / n);
                        ++n5;
                    }
                    n8 += n4;
                }
                n7 += n4;
            }
            n6 += n4;
        }
    }
}
