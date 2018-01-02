import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends ImageFilter
{
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int[] e;
    protected int[] f;
    protected int[] g;
    protected static final int h = 2;
    
    public void setHints(final int hints) throws NoSuchMethodError {
        if ((hints & 0x6) != 0x6) {
            throw new NoSuchMethodError();
        }
        super.setHints(hints);
    }
    
    public void setDimensions(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.a = c * 2;
        this.b = d * 2;
        super.setDimensions(this.a, this.b);
        this.f = new int[this.c];
        this.g = new int[this.c];
        this.e = new int[this.a];
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) throws NoSuchMethodError {
        for (int i = n2; i < n2 + n4; ++i) {
            if (i == 0) {
                for (int j = 0; j < this.g.length; ++j) {
                    this.g[j] = colorModel.getRGB(array[(i - n2) * n6 + n5 + j] & 0xFF);
                }
            }
            else {
                System.arraycopy(this.g, 0, this.f, 0, this.f.length);
                for (int k = 0; k < this.g.length; ++k) {
                    this.g[k] = colorModel.getRGB(array[(i - n2) * n6 + n5 + k] & 0xFF);
                }
                this.a(i - 1, ColorModel.getRGBdefault());
            }
            if (i == this.d - 1) {
                System.arraycopy(this.g, 0, this.f, 0, this.f.length);
                this.a(i, ColorModel.getRGBdefault());
            }
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        for (int i = n2; i < n2 + n4; ++i) {
            if (i == 0) {
                System.arraycopy(array, (i - n2) * n6 + n5, this.g, 0, this.g.length);
            }
            else {
                System.arraycopy(this.g, 0, this.f, 0, this.f.length);
                System.arraycopy(array, (i - n2) * n6 + n5, this.g, 0, this.g.length);
                this.a(i - 1, colorModel);
            }
            if (i == this.d - 1) {
                System.arraycopy(this.g, 0, this.f, 0, this.f.length);
                this.a(i, colorModel);
            }
        }
    }
    
    protected void a(final int n, final ColorModel colorModel) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < this.a; ++j) {
                final int n2 = j / 2;
                int n3 = n2 + 1;
                if (n3 >= this.c - 1) {
                    n3 = n2;
                }
                if ((i & 0x1) == 0x0) {
                    if ((j & 0x1) == 0x0) {
                        this.e[j] = this.f[n2];
                    }
                    else {
                        this.e[j] = this.a(this.f[n2], this.f[n3]);
                    }
                }
                else if ((j & 0x1) == 0x0) {
                    this.e[j] = this.a(this.f[n2], this.g[n2]);
                }
                else {
                    this.e[j] = this.a(this.f[n2], this.g[n2], this.f[n3], this.g[n3]);
                }
            }
            this.consumer.setPixels(0, i + n * 2, this.a, 1, colorModel, this.e, 0, this.a);
        }
    }
    
    private int a(final int n, final int n2) {
        return 0xFF000000 | (n & 0xFF) + (n2 & 0xFF) >> 1 | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000);
    }
    
    private int a(final int n, final int n2, final int n3, final int n4) {
        return 0xFF000000 | (n & 0xFF) + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF) >> 2 | ((n & 0xFF00) + (n2 & 0xFF00) + (n3 & 0xFF00) + (n4 & 0xFF00) >> 2 & 0xFF00) | ((n & 0xFF0000) + (n2 & 0xFF0000) + (n3 & 0xFF0000) + (n4 & 0xFF0000) >> 2 & 0xFF0000);
    }
}
