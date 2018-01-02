// 
// Decompiled by Procyon v0.5.30
// 

public final class Lens
{
    public int width;
    public int height;
    private double dblXDegree;
    private double dblYDegree;
    private double dblXAdd;
    private double dblYAdd;
    public int depth;
    int[] data;
    int[] ytab;
    int xpos;
    int ypos;
    int oldx;
    int oldy;
    int hwidth;
    int hheight;
    int intBackgroundColor;
    boolean boolSkipDraw;
    
    public Lens(final int width, final int height, final double dblXAdd, final double dblYAdd, final int intBackgroundColor) {
        this.dblXDegree = 0.0;
        this.dblYDegree = 0.0;
        this.dblXAdd = 0.0;
        this.dblYAdd = 0.0;
        this.boolSkipDraw = false;
        this.dblXAdd = dblXAdd;
        this.dblYAdd = dblYAdd;
        this.intBackgroundColor = intBackgroundColor;
        this.width = width;
        this.height = height;
        this.hwidth = width >> 1;
        this.hheight = height >> 1;
        this.xpos = this.hwidth;
        this.ypos = this.hheight;
        this.oldx = this.hwidth;
        this.oldy = this.hheight;
        this.depth = 1024;
        this.data = new int[this.width * this.height];
        this.ytab = new int[this.height];
        int n = 0;
        for (int i = 0; i < this.height; ++i) {
            this.ytab[i] = n;
            n += this.width;
        }
    }
    
    public final void addFrame(final int n, final int n2, final double n3) {
        this.oldx = this.xpos;
        this.oldy = this.ypos;
        this.dblXDegree += this.dblXAdd;
        this.dblYDegree += this.dblYAdd;
        final int n4 = (int)(n * n3);
        final int n5 = (int)(n2 * n3);
        this.xpos = (int)(Math.sin(this.dblXDegree) * (n4 >> 1)) + (n >> 1);
        this.ypos = (int)(Math.sin(this.dblYDegree) * (n5 >> 1)) + (n2 >> 1);
    }
    
    public final void setpos(final int xpos, final int ypos, final boolean b) {
        if (b) {
            this.oldx = this.xpos;
            this.oldy = this.ypos;
        }
        this.xpos = xpos;
        this.ypos = ypos;
    }
    
    public final void restoreOld2(final Screen32 screen32, final Screen32 screen33) {
        int n;
        int n2;
        if (this.oldx > this.xpos) {
            n = this.oldx + this.hwidth - (this.oldx - this.xpos);
            n2 = this.oldx - this.xpos + 2;
        }
        else {
            n = this.oldx - this.hwidth;
            n2 = this.xpos - this.oldx;
        }
        int n3 = this.oldy - this.hheight;
        int height = this.height;
        if (n < 0) {
            n2 += n;
            n = 0;
        }
        if (n + n2 > screen32.width) {
            n2 -= n + n2 - screen32.width;
        }
        if (n3 < 0) {
            height += n3;
            n3 = 0;
        }
        if (n3 + height > screen32.height) {
            height -= n3 + height - screen32.height;
        }
        final int n4 = screen32.width - n2;
        int n5 = n + n3 * screen32.width;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < n2; ++j) {
                screen33.data[n5] = screen32.data[n5];
                ++n5;
            }
            n5 += n4;
        }
        int n6;
        int n7;
        if (this.oldy > this.ypos) {
            n6 = this.oldy + this.hheight - (this.oldy - this.ypos);
            n7 = this.oldy - this.ypos + 2;
        }
        else {
            n6 = this.oldy - this.hheight;
            n7 = this.ypos - this.oldy;
        }
        int n8 = this.oldx - this.hwidth;
        int width = this.width;
        if (n8 < 0) {
            width += n8;
            n8 = 0;
        }
        if (n8 + width > screen32.width) {
            width -= n8 + width - screen32.width;
        }
        if (n6 < 0) {
            n7 += n6;
            n6 = 0;
        }
        if (n6 + n7 > screen32.height) {
            n7 -= n6 + n7 - screen32.height;
        }
        final int n9 = screen32.width - width;
        int n10 = n8 + n6 * screen32.width;
        for (int k = 0; k < n7; ++k) {
            for (int l = 0; l < width; ++l) {
                screen33.data[n10] = screen32.data[n10];
                ++n10;
            }
            n10 += n9;
        }
    }
    
    public final void createPhongDot() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final double n = (j - this.width / 2) / (this.width / 2);
                final double n2 = (i - this.height / 2) / (this.height / 2);
                double n3 = 1.0 - Math.sqrt(n * n + n2 * n2);
                if (n3 < 0.0) {
                    n3 = 0.0;
                }
                this.data[j + this.ytab[i]] = (int)(n3 * (this.depth << 4));
            }
        }
    }
    
    public final void calcPersp(final Screen32 screen32, final Screen32 screen33) {
        if (this.boolSkipDraw) {
            this.boolSkipDraw = false;
            return;
        }
        int n = 0;
        int n2 = 0;
        int height = this.height;
        int width = this.width;
        int n3 = this.ypos - this.hheight;
        int n4 = this.xpos - this.hwidth;
        if (n3 + this.height >= screen32.height) {
            height = screen32.height - n3;
        }
        if (n3 < 0) {
            n = -n3;
            n3 = 0;
        }
        if (n4 + this.width >= screen32.width) {
            width = screen32.width - n4;
        }
        if (n4 < 0) {
            n2 = -n4;
            n4 = 0;
        }
        for (int i = n; i < height; ++i) {
            int n5 = n4;
            for (int j = n2; j < width; ++j) {
                final int n6 = 65536 - this.data[j + this.ytab[i]];
                final int n7 = ((n5 - screen33.hwidth) * n6 >> 16) + screen33.hwidth;
                final int n8 = ((n3 - screen33.hheight) * n6 >> 16) + screen33.hheight;
                if (n7 >= screen33.width || n7 < 0 || n8 >= screen33.height || n8 < 0) {
                    screen32.data[n5 + screen32.ytab[n3]] = this.intBackgroundColor;
                }
                else {
                    screen32.data[n5 + screen32.ytab[n3]] = screen33.data[n7 + screen33.ytab[n8]];
                }
                ++n5;
            }
            ++n3;
        }
    }
}
