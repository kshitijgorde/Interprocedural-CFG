// 
// Decompiled by Procyon v0.5.30
// 

public final class Waterpic
{
    public int[] data;
    int[] ytab;
    public int width;
    public int height;
    int hwidth;
    int hheight;
    int dim;
    int ptrData;
    int ptrData2;
    int dotsize;
    int dotdepth;
    int ndotsize;
    int ptrIndex;
    int dInd;
    int ptrDestData;
    int v;
    int newx;
    int newy;
    int y;
    int x;
    
    public Waterpic(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.hwidth = width >> 1;
        this.hheight = height >> 1;
        this.dotsize = 9;
        this.dotdepth = 450;
        this.ndotsize = 6;
        this.dim = 5;
        this.data = new int[this.width * (this.height + 2) * 4];
        this.ptrData = this.width + 1;
        this.ptrData2 = 4 + this.width * (this.height + 3);
        this.ytab = new int[this.height + 2];
        int n = 0;
        for (int i = 0; i < this.height + 2; ++i) {
            this.ytab[i] = n;
            n += this.width;
        }
    }
    
    protected final void calcPersp(final Screen32 screen32, final Screen32 screen33) {
        this.ptrIndex = this.ptrData;
        this.dInd = 0;
        this.ptrDestData = this.ptrData2;
        this.y = 0;
        while (this.y < this.height) {
            this.x = 0;
            while (this.x < this.width) {
                this.v = this.data[this.ptrIndex - this.width] + this.data[this.ptrIndex + this.width] + this.data[this.ptrIndex - 1] + this.data[this.ptrIndex + 1] + this.data[this.ptrIndex - this.width - 1] + this.data[this.ptrIndex - this.width + 1] + this.data[this.ptrIndex + this.width - 1] + this.data[this.ptrIndex + this.width + 1] >> 2;
                this.v -= this.data[this.ptrDestData];
                this.v -= this.v >> this.dim;
                this.data[this.ptrDestData++] = this.v;
                this.v = 1024 - this.v;
                this.newx = ((this.x - this.hwidth) * this.v >> 10) + this.hwidth;
                this.newx = ((this.newx >= this.width) ? (this.width - 1) : ((this.newx < 0) ? 0 : this.newx));
                this.newy = ((this.y - this.hheight) * this.v >> 10) + this.hheight;
                this.newy = ((this.newy >= this.height) ? (this.height - 1) : ((this.newy < 0) ? 0 : this.newy));
                screen32.data[this.dInd++] = screen33.data[this.newx + screen33.ytab[this.newy]];
                ++this.ptrIndex;
                ++this.x;
            }
            ++this.y;
        }
    }
    
    protected final void calcWaterOnly() {
        this.ptrIndex = this.ptrData;
        this.dInd = 0;
        this.ptrDestData = this.ptrData2;
        final int n = this.height * this.width;
        this.y = 0;
        while (this.y < n) {
            this.v = this.data[this.ptrIndex - this.width] + this.data[this.ptrIndex + this.width] + this.data[this.ptrIndex - 1] + this.data[this.ptrIndex + 1] + this.data[this.ptrIndex - this.width - 1] + this.data[this.ptrIndex - this.width + 1] + this.data[this.ptrIndex + this.width - 1] + this.data[this.ptrIndex + this.width + 1] >> 2;
            this.v -= this.data[this.ptrDestData];
            this.v -= this.v >> this.dim;
            this.data[this.ptrDestData++] = this.v;
            ++this.ptrIndex;
            ++this.y;
        }
    }
    
    protected final void TransformWater2(final Screen32 screen32, final Screen32 screen33) {
        this.dInd = 0;
        this.y = 0;
        while (this.y < screen32.height) {
            this.x = 0;
            while (this.x < screen32.width) {
                this.v = 1024 - this.data[this.ptrData2 + this.ytab[this.y >> 1] + (this.x >> 1)];
                this.newx = ((this.x - screen32.hwidth) * this.v >> 10) + screen32.hwidth;
                this.newx = ((this.newx >= screen32.width) ? (screen32.width - 1) : ((this.newx < 0) ? 0 : this.newx));
                this.newy = ((this.y - screen32.hheight) * this.v >> 10) + screen32.hheight;
                this.newy = ((this.newy >= screen32.height) ? (screen32.height - 1) : ((this.newy < 0) ? 0 : this.newy));
                screen32.data[this.dInd++] = screen33.data[this.newx + screen33.ytab[this.newy]];
                ++this.x;
            }
            ++this.y;
        }
    }
    
    protected final void TransformWaterFree(final Screen32 screen32, final Screen32 screen33) {
        this.dInd = 0;
        final int n = (this.width << 16) / screen32.width;
        final int n2 = (this.height << 16) / screen32.height;
        int n3 = 0;
        int n4 = 0;
        this.y = 0;
        while (this.y < screen32.height) {
            this.x = 0;
            while (this.x < screen32.width) {
                this.v = 1024 - this.data[this.ptrData2 + this.ytab[n4 >> 16] + (n3 >> 16)];
                this.newx = ((this.x - screen32.hwidth) * this.v >> 10) + screen32.hwidth;
                this.newx = ((this.newx >= screen32.width) ? (screen32.width - 1) : ((this.newx < 0) ? 0 : this.newx));
                this.newy = ((this.y - screen32.hheight) * this.v >> 10) + screen32.hheight;
                this.newy = ((this.newy >= screen32.height) ? (screen32.height - 1) : ((this.newy < 0) ? 0 : this.newy));
                screen32.data[this.dInd++] = screen33.data[this.newx + screen33.ytab[this.newy]];
                n3 += n;
                ++this.x;
            }
            n4 += n2;
            n3 = 0;
            ++this.y;
        }
    }
    
    public final void render(final Screen32 screen32, final Screen32 screen33) {
        if (this.width == screen32.width) {
            this.calcPersp(screen32, screen33);
        }
        else if (this.width << 1 == screen32.width && this.height << 1 == screen32.height) {
            this.calcWaterOnly();
            this.TransformWater2(screen32, screen33);
        }
        else {
            this.calcWaterOnly();
            this.TransformWaterFree(screen32, screen33);
        }
    }
    
    public final void flip() {
        if (this.ptrData == this.width + 1) {
            this.ptrData = this.ptrData2;
            this.ptrData2 = this.width + 1;
        }
        else {
            this.ptrData2 = this.ptrData;
            this.ptrData = this.width + 1;
        }
    }
    
    public final void setDot(int n, int n2, final int n3, final int n4, final int n5, final int n6) {
        n2 = n2 * this.height / n6;
        n = n * this.width / n5;
        final int n7 = n4 * this.height / n6;
        final int n8 = n4 * this.width / n5;
        for (int i = 0; i < n7; ++i) {
            for (int j = 0; j < n8; ++j) {
                if (n2 + i + 1 > 0 && n2 + i + 1 < this.height - n7) {
                    final int[] data = this.data;
                    final int n9 = this.ptrData2 + this.ytab[n2 + i + 1] + n + j + 1;
                    data[n9] += n3;
                }
            }
        }
    }
}
