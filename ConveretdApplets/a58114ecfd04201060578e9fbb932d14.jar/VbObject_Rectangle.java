// 
// Decompiled by Procyon v0.5.30
// 

class VbObject_Rectangle extends VbObject
{
    VbObject_Rectangle(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final int n5) {
        final int n6 = n3 / n;
        final int n7 = n4 / n2;
        super.iNumBalls = n * n2;
        super.Balls = new VbBall[super.iNumBalls];
        int n8 = 0;
        int n9 = -(n4 / 2);
        for (int i = 0; i < n2; ++i) {
            int n10 = -(n3 / 2);
            for (int j = 0; j < n; ++j) {
                float n11 = 0.0f;
                if (b2) {
                    n11 = n5 * SineTable.value[(int)(i / n2 * 4096.0f)] + n5 * SineTable.value[(int)(j / n * 4096.0f)];
                }
                int n12;
                if (b) {
                    n12 = i % 6;
                }
                else {
                    n12 = n8 % 6;
                }
                super.Balls[n8] = new VbBall(n10, n9, n11, n12, i * 4096 / n2, j * 4096 / n);
                n10 += n6;
                ++n8;
            }
            n9 += n7;
        }
    }
}
