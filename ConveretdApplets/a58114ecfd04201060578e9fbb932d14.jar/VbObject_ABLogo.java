// 
// Decompiled by Procyon v0.5.30
// 

class VbObject_ABLogo extends VbObject
{
    private static final int FrameBk = 5;
    private static final int FrameFg = 3;
    
    VbObject_ABLogo(final int n) {
        final int n2 = 8;
        final int n3 = 8;
        super.iNumBalls = n2 * n3;
        super.Balls = new VbBall[super.iNumBalls];
        final int n4 = n / n2;
        int n5 = 0;
        for (int n6 = -(n / 2), i = 0; i < n3; ++i, n6 += n4) {
            for (int n7 = -(n / 2), j = 0; j < n2; ++j, n7 += n4) {
                super.Balls[n5++] = new VbBall(n7, n6, 0.0f, 5, 0, 0);
            }
        }
        final int n8 = 1;
        for (int k = 1; k < n2 - 1; ++k) {
            super.Balls[n8 * n2 + k].frame = 3;
        }
        final int n9 = n3 - 2;
        for (int l = 1; l < n2 - 1; ++l) {
            super.Balls[n9 * n2 + l].frame = 3;
        }
        final int n10 = 2;
        for (int n11 = 1; n11 < n3 - 1; ++n11) {
            super.Balls[n11 * n2 + n10].frame = 3;
        }
        final int n12 = n3 - 3;
        for (int n13 = 1; n13 < n3 - 1; ++n13) {
            super.Balls[n13 * n2 + n12].frame = 3;
        }
    }
}
