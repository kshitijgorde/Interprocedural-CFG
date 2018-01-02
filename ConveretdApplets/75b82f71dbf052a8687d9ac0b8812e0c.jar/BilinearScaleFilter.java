import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class BilinearScaleFilter extends ImageFilter
{
    protected int destWidth;
    protected int destHeight;
    protected int srcWidth;
    protected int srcHeight;
    protected int[] outpix;
    protected int[] lineBuf1;
    protected int[] lineBuf2;
    protected static final int RATIO = 2;
    
    public void setHints(final int hints) throws NoSuchMethodError {
        final int neededHints = 6;
        if ((hints & 0x6) != 0x6) {
            throw new NoSuchMethodError();
        }
        super.setHints(hints);
    }
    
    public void setDimensions(final int w, final int h) {
        this.srcWidth = w;
        this.srcHeight = h;
        this.destWidth = w * 2;
        this.destHeight = h * 2;
        super.setDimensions(this.destWidth, this.destHeight);
        this.lineBuf1 = new int[this.srcWidth];
        this.lineBuf2 = new int[this.srcWidth];
        this.outpix = new int[this.destWidth];
    }
    
    public void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final byte[] srcImage, final int off, final int scansize) throws NoSuchMethodError {
        for (int j = y; j < y + h; ++j) {
            if (j == 0) {
                for (int k = 0; k < this.lineBuf2.length; ++k) {
                    this.lineBuf2[k] = model.getRGB(srcImage[(j - y) * scansize + off + k] & 0xFF);
                }
            }
            else {
                System.arraycopy(this.lineBuf2, 0, this.lineBuf1, 0, this.lineBuf1.length);
                for (int k = 0; k < this.lineBuf2.length; ++k) {
                    this.lineBuf2[k] = model.getRGB(srcImage[(j - y) * scansize + off + k] & 0xFF);
                }
                this.upSample(j - 1, ColorModel.getRGBdefault());
            }
            if (j == this.srcHeight - 1) {
                System.arraycopy(this.lineBuf2, 0, this.lineBuf1, 0, this.lineBuf1.length);
                this.upSample(j, ColorModel.getRGBdefault());
            }
        }
    }
    
    public void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final int[] srcImage, final int off, final int scansize) {
        for (int j = y; j < y + h; ++j) {
            if (j == 0) {
                System.arraycopy(srcImage, (j - y) * scansize + off, this.lineBuf2, 0, this.lineBuf2.length);
            }
            else {
                System.arraycopy(this.lineBuf2, 0, this.lineBuf1, 0, this.lineBuf1.length);
                System.arraycopy(srcImage, (j - y) * scansize + off, this.lineBuf2, 0, this.lineBuf2.length);
                this.upSample(j - 1, model);
            }
            if (j == this.srcHeight - 1) {
                System.arraycopy(this.lineBuf2, 0, this.lineBuf1, 0, this.lineBuf1.length);
                this.upSample(j, model);
            }
        }
    }
    
    protected void upSample(final int y, final ColorModel model) {
        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < this.destWidth; ++i) {
                final int u0 = i / 2;
                int u2 = u0 + 1;
                if (u2 >= this.srcWidth - 1) {
                    u2 = u0;
                }
                if ((j & 0x1) == 0x0) {
                    if ((i & 0x1) == 0x0) {
                        this.outpix[i] = this.lineBuf1[u0];
                    }
                    else {
                        this.outpix[i] = this.MeanOfTwo(this.lineBuf1[u0], this.lineBuf1[u2]);
                    }
                }
                else if ((i & 0x1) == 0x0) {
                    this.outpix[i] = this.MeanOfTwo(this.lineBuf1[u0], this.lineBuf2[u0]);
                }
                else {
                    this.outpix[i] = this.MeanOfFour(this.lineBuf1[u0], this.lineBuf2[u0], this.lineBuf1[u2], this.lineBuf2[u2]);
                }
            }
            this.consumer.setPixels(0, j + y * 2, this.destWidth, 1, model, this.outpix, 0, this.destWidth);
        }
    }
    
    private int MeanOfTwo(final int Px1, final int Px2) {
        final int b = (Px1 & 0xFF) + (Px2 & 0xFF) >> 1;
        final int g = (Px1 & 0xFF00) + (Px2 & 0xFF00) >> 1;
        final int r = (Px1 & 0xFF0000) + (Px2 & 0xFF0000) >> 1;
        return 0xFF000000 | b | (g & 0xFF00) | (r & 0xFF0000);
    }
    
    private int MeanOfFour(final int Px1, final int Px2, final int Px3, final int Px4) {
        final int b = (Px1 & 0xFF) + (Px2 & 0xFF) + (Px3 & 0xFF) + (Px4 & 0xFF) >> 2;
        final int g = (Px1 & 0xFF00) + (Px2 & 0xFF00) + (Px3 & 0xFF00) + (Px4 & 0xFF00) >> 2;
        final int r = (Px1 & 0xFF0000) + (Px2 & 0xFF0000) + (Px3 & 0xFF0000) + (Px4 & 0xFF0000) >> 2;
        return 0xFF000000 | b | (g & 0xFF00) | (r & 0xFF0000);
    }
}
