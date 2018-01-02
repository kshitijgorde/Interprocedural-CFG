import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends ImageFilter
{
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int[] e;
    protected int[] f;
    protected int[] g;
    protected int h;
    
    public void setHints(final int hints) throws NoSuchMethodError {
        if ((hints & 0x6) != 0x6) {
            throw new NoSuchMethodError();
        }
        super.setHints(hints);
    }
    
    public b(final int h) throws IllegalArgumentException {
        this.h = h;
        if (this.h != 2) {
            throw new IllegalArgumentException("Upscale value must be 2.");
        }
    }
    
    protected void a(final int n, final ColorModel colorModel) {
        int n2 = 0;
        do {
            for (int i = 0; i < this.a; ++i) {
                final int n3 = i / 2;
                int n4 = n3 + 1;
                if (n4 >= this.c - 1) {
                    n4 = n3;
                }
                if ((n2 & 0x1) == 0x0) {
                    if ((i & 0x1) == 0x0) {
                        this.e[i] = this.f[n3];
                    }
                    else {
                        this.e[i] = this.a(this.f[n3], this.f[n4]);
                    }
                }
                else if ((i & 0x1) == 0x0) {
                    this.e[i] = this.a(this.f[n3], this.g[n3]);
                }
                else {
                    this.e[i] = this.a(this.f[n3], this.g[n3], this.f[n4], this.g[n4]);
                }
            }
            super.consumer.setPixels(0, n2 + n * 2, this.a, 1, colorModel, this.e, 0, this.a);
        } while (++n2 < 2);
    }
    
    private int a(final int n, final int n2) {
        return 0xFF000000 | (n & 0xFF) + (n2 & 0xFF) >> 1 | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000);
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
    
    private int a(final int n, final int n2, final int n3, final int n4) {
        return 0xFF000000 | (n & 0xFF) + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF) >> 2 | ((n & 0xFF00) + (n2 & 0xFF00) + (n3 & 0xFF00) + (n4 & 0xFF00) >> 2 & 0xFF00) | ((n & 0xFF0000) + (n2 & 0xFF0000) + (n3 & 0xFF0000) + (n4 & 0xFF0000) >> 2 & 0xFF0000);
    }
    
    public void setDimensions(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.a = c * this.h;
        this.b = d * this.h;
        super.setDimensions(this.a, this.b);
        this.f = new int[this.c];
        this.g = new int[this.c];
        this.e = new int[this.a];
    }
}
