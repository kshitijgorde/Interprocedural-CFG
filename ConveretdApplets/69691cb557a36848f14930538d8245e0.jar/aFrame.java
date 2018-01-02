import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class aFrame
{
    int duration;
    int[] imageArray;
    int[] imageArrayBig;
    Image img;
    Image imgSmall;
    int pageSize;
    int RowLen;
    int ColTotal;
    int RowLenB;
    int ColTotalB;
    
    public aFrame(final int duration, final int pageSize) {
        this.pageSize = 0;
        this.RowLen = 48;
        this.ColTotal = 32;
        this.RowLenB = this.RowLen * 3;
        this.ColTotalB = this.ColTotal * 4;
        this.duration = duration;
        this.pageSize = pageSize;
        this.imageArray = new int[this.pageSize];
    }
    
    public void convert() {
        this.imageArrayBig = new int[this.RowLenB * this.ColTotalB];
        int n = 0;
        for (int i = 0; i < this.ColTotal; ++i) {
            for (int j = 0; j < this.RowLen; ++j) {
                final int n2 = this.imageArray[j + i * this.RowLen];
                this.imageArrayBig[n] = n2;
                this.imageArrayBig[n + 1] = n2;
                this.imageArrayBig[n + this.RowLenB] = n2;
                this.imageArrayBig[n + 1 + this.RowLenB] = n2;
                n += 3;
            }
            n = n + this.RowLenB + this.RowLenB + this.RowLenB;
        }
        this.imageArrayBig[this.RowLenB * this.ColTotalB - 1] = -16777216;
    }
    
    public void putPixel(final int n, final int n2) {
        this.imageArray[n] = n2;
    }
}
