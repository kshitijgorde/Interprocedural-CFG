// 
// Decompiled by Procyon v0.5.30
// 

public final class Water
{
    public int[] data;
    int[] ytab;
    int[] palette;
    public int width;
    public int height;
    int hwidth;
    int hheight;
    int dim;
    private int wInd;
    private int wInd2;
    int dotsize;
    int dotdepth;
    int ndotsize;
    int logoheight;
    int logoheight2;
    
    public Water(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.hwidth = width >> 1;
        this.hheight = height >> 1;
        this.logoheight = 824;
        this.logoheight2 = 200;
        this.dotsize = 9;
        this.dotdepth = 450;
        this.ndotsize = 6;
        this.dim = 5;
        this.data = new int[this.width * (this.height + 2) * 2];
        this.palette = new int[1024];
        this.wInd = this.width;
        this.wInd2 = this.width * (this.height + 3);
        this.ytab = new int[this.height + 2];
        int n = 0;
        for (int i = 0; i < this.height + 2; ++i) {
            this.ytab[i] = n;
            n += this.width;
        }
    }
    
    public final void createPalette(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11, int n12, final int n13, final int n14, final int n15) {
        final double n16 = n4 - n;
        final double n17 = n5 - n2;
        final double n18 = n6 - n3;
        final double n19 = n16 / 256.0;
        final double n20 = n17 / 256.0;
        final double n21 = n18 / 256.0;
        double n22 = n;
        double n23 = n2;
        double n24 = n3;
        final double n25 = n7 - n4;
        final double n26 = n8 - n5;
        final double n27 = n9 - n6;
        final double n28 = n25 / 256.0;
        final double n29 = n26 / 256.0;
        final double n30 = n27 / 256.0;
        double n31 = n4;
        double n32 = n5;
        double n33 = n6;
        final double n34 = n10 - n7;
        final double n35 = n11 - n8;
        final double n36 = n12 - n9;
        final double n37 = n34 / 256.0;
        final double n38 = n35 / 256.0;
        final double n39 = n36 / 256.0;
        double n40 = n7;
        double n41 = n8;
        double n42 = n9;
        final double n43 = n13 - n10;
        final double n44 = n14 - n11;
        final double n45 = n15 - n12;
        final double n46 = n43 / 256.0;
        final double n47 = n44 / 256.0;
        final double n48 = n45 / 256.0;
        double n49 = n10;
        double n50 = n11;
        double n51 = n12;
        for (int i = 0; i < 256; ++i) {
            n = (int)n22;
            n2 = (int)n23;
            n3 = (int)n24;
            n4 = (int)n31;
            n5 = (int)n32;
            n6 = (int)n33;
            n7 = (int)n40;
            n8 = (int)n41;
            n9 = (int)n42;
            n10 = (int)n49;
            n11 = (int)n50;
            n12 = (int)n51;
            this.palette[i] = (n << 16) + (n2 << 8) + n3;
            this.palette[i + 256] = (n4 << 16) + (n5 << 8) + n6;
            this.palette[i + 512] = (n7 << 16) + (n8 << 8) + n9;
            this.palette[i + 768] = (n10 << 16) + (n11 << 8) + n12;
            n22 += n19;
            n23 += n20;
            n24 += n21;
            n31 += n28;
            n32 += n29;
            n33 += n30;
            n40 += n37;
            n41 += n38;
            n42 += n39;
            n49 += n46;
            n50 += n47;
            n51 += n48;
        }
    }
    
    public final void calcOverlay(final Screen32 screen32, final Screen32 screen33) {
        int wInd = this.wInd;
        for (int i = 0; i < this.height * this.width; ++i) {
            final int n = (this.data[wInd - this.width] + this.data[wInd + this.width] + this.data[wInd - 1] + this.data[wInd + 1] >> 1) - this.data[this.wInd2 + i];
            int n2 = n - (n >> this.dim);
            if ((this.data[this.wInd2 + i] = n2) < -511) {
                n2 = -511;
            }
            if (n2 > 511) {
                n2 = 511;
            }
            final int n3 = 512 + n2 & 0x3FF;
            if ((screen33.data[i] & 0xFF000000) != 0x0) {
                if (n3 > this.logoheight2 && n3 < this.logoheight) {
                    screen32.data[i] = screen33.data[i];
                }
                else {
                    screen32.data[i] = this.palette[n3];
                }
            }
            else {
                screen32.data[i] = this.palette[n3];
            }
            ++wInd;
        }
    }
    
    public final void calcWater(final Screen32 screen32) {
        int wInd = this.wInd;
        for (int n = this.height * this.width, i = 0; i < n; ++i) {
            final int n2 = (this.data[wInd - this.width] + this.data[wInd + this.width] + this.data[wInd - 1] + this.data[wInd + 1] >> 1) - this.data[this.wInd2 + i];
            int n3 = n2 - (n2 >> this.dim);
            if ((this.data[this.wInd2 + i] = n3) < -511) {
                n3 = -511;
            }
            if (n3 > 511) {
                n3 = 511;
            }
            screen32.data[i] = this.palette[512 + n3 & 0x3FF];
            ++wInd;
        }
    }
    
    public final void flip() {
        if (this.wInd == this.width) {
            this.wInd = this.wInd2;
            this.wInd2 = this.width;
        }
        else {
            this.wInd2 = this.wInd;
            this.wInd = this.width;
        }
    }
    
    public final void setDot(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n2 + i + 1 > 0 && n2 + i + 1 < this.height - n4) {
                    final int[] data = this.data;
                    final int n5 = this.wInd2 + this.ytab[n2 + i + 1] + n + j + 1;
                    data[n5] += n3;
                }
            }
        }
    }
}
