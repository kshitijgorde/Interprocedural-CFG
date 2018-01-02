// 
// Decompiled by Procyon v0.5.30
// 

public final class BumpMap
{
    public int[] mDeltaX;
    public int[] mDeltaY;
    
    public BumpMap(final Screen32 screen32, final double n) {
        final int getwidth = screen32.getwidth();
        final int getheight = screen32.getheight();
        this.mDeltaX = new int[screen32.getwidthheight()];
        this.mDeltaY = new int[screen32.getwidthheight()];
        for (int i = 1; i < getheight - 1; ++i) {
            for (int j = 0; j < getwidth; ++j) {
                final int n2 = screen32.data[screen32.ytab[i + 1] + j];
                final int n3 = screen32.data[screen32.ytab[i - 1] + j];
                final int n4 = screen32.data[screen32.ytab[i] + j + 1];
                final int n5 = screen32.data[screen32.ytab[i] + j - 1];
                final double n6 = n2 >> 16 & 0xFF;
                final double n7 = n2 >> 8 & 0xFF;
                final double n8 = n2 & 0xFF;
                final double n9 = n3 >> 16 & 0xFF;
                final double n10 = n3 >> 8 & 0xFF;
                final double n11 = n3 & 0xFF;
                final double n12 = n4 >> 16 & 0xFF;
                final double n13 = n4 >> 8 & 0xFF;
                final double n14 = n4 & 0xFF;
                final double n15 = n5 >> 16 & 0xFF;
                final double n16 = n5 >> 8 & 0xFF;
                final double n17 = n5 & 0xFF;
                final double n18 = (n6 + n7 + n8) / 3.0;
                final double n19 = (n9 + n10 + n11) / 3.0;
                final double n20 = (n12 + n13 + n14) / 3.0;
                final double n21 = (n15 + n16 + n17) / 3.0;
                this.mDeltaY[screen32.ytab[i] + j] = (int)((n18 - n19) / 512.0 * n);
                this.mDeltaX[screen32.ytab[i] + j] = (int)((n20 - n21) / 512.0 * n);
            }
        }
    }
}
