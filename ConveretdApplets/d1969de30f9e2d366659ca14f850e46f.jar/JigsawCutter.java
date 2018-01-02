import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class JigsawCutter extends RGBImageFilter
{
    int dimValue;
    int w;
    int h;
    int overlap;
    int f0;
    int f1;
    int f2;
    int f3;
    int[] points;
    boolean grayScale;
    
    public JigsawCutter(final int dimValue, final boolean grayScale, final int w, final int h, final int overlap, final int f0, final int f2, final int f3, final int f4, final int[] points) {
        this.dimValue = dimValue;
        this.grayScale = grayScale;
        this.overlap = overlap;
        this.f0 = f0;
        this.f1 = f2;
        this.f2 = f3;
        this.f3 = f4;
        this.w = w;
        this.h = h;
        this.points = points;
    }
    
    private int darkenRGB(int n, int n2, int n3, final int n4) {
        if ((n = n * (100 - n4) / 100 - 10) < 0) {
            n = 0;
        }
        if ((n2 = n2 * (100 - n4) / 100 - 10) < 0) {
            n2 = 0;
        }
        if ((n3 = n3 * (100 - n4) / 100 - 10) < 0) {
            n3 = 0;
        }
        return 0xFF000000 | n << 16 | n2 << 8 | n3;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 & 0xFF;
        final int n7 = this.overlap - 3;
        if (this.dimValue > 0) {
            if (this.grayScale) {
                final int n8 = (n4 + n5 + n6) / 3;
                return this.lightenRGB(n8, n8, n8, this.dimValue);
            }
            return this.lightenRGB(n4, n5, n6, this.dimValue);
        }
        else {
            final int n9 = this.overlap / 2;
            final int n10 = (this.f0 >= 0) ? this.f0 : (-this.f0);
            final int n11 = (this.f1 >= 0) ? this.f1 : (-this.f1);
            final int n12 = (this.f2 >= 0) ? this.f2 : (-this.f2);
            final int n13 = (this.f3 >= 0) ? this.f3 : (-this.f3);
            if (n < n7) {
                if ((n2 < n7 && this.f0 > 0) || (n2 >= this.h - n7 && this.f2 > 0) || ((n2 < n13 - n9 || n2 > n13 + n9) && this.f3 > 0)) {
                    return 0;
                }
                if (n2 >= n13 - n9 && n2 <= n13 + n9 && this.f3 < 0) {
                    return this.holeCutter(3, n13, n, n2 - (n13 - n9), n4, n5, n6);
                }
                if (n2 >= n13 - n9 && n2 <= n13 + n9 && this.f3 > 0) {
                    return this.stubCutter(3, n13, n, n2 - (n13 - n9), n4, n5, n6);
                }
            }
            if (n >= this.w - n7) {
                if ((n2 < n7 && this.f0 > 0) || (n2 >= this.h - n7 && this.f2 > 0) || ((n2 < n11 - n9 || n2 > n11 + n9) && this.f1 > 0)) {
                    return 0;
                }
                if (n2 >= n11 - n9 && n2 <= n11 + n9 && this.f1 < 0) {
                    return this.holeCutter(1, n11, n - (this.w - this.overlap), n2 - (n11 - n9), n4, n5, n6);
                }
                if (n2 >= n11 - n9 && n2 <= n11 + n9 && this.f1 > 0) {
                    return this.stubCutter(1, n11, n - (this.w - this.overlap), n2 - (n11 - n9), n4, n5, n6);
                }
            }
            if (n2 < n7) {
                if ((n < n7 && this.f3 > 0) || (n >= this.w - n7 && this.f1 > 0) || ((n < n10 - n9 || n > n10 + n9) && this.f0 > 0)) {
                    return 0;
                }
                if (n >= n10 - n9 && n <= n10 + n9 && this.f0 < 0) {
                    return this.holeCutter(0, n10, n - (n10 - n9), n2, n4, n5, n6);
                }
                if (n >= n10 - n9 && n <= n10 + n9 && this.f0 > 0) {
                    return this.stubCutter(0, n10, n - (n10 - n9), n2, n4, n5, n6);
                }
            }
            if (n2 >= this.h - n7) {
                if ((n < n7 && this.f3 > 0) || (n >= this.w - n7 && this.f1 > 0) || ((n < n12 - n9 || n > n12 + n9) && this.f2 > 0)) {
                    return 0;
                }
                if (n >= n12 - n9 && n <= n12 + n9 && this.f2 < 0) {
                    return this.holeCutter(2, n12, n - (n12 - n9), n2 - (this.h - this.overlap), n4, n5, n6);
                }
                if (n >= n12 - n9 && n <= n12 + n9 && this.f2 > 0) {
                    return this.stubCutter(2, n12, n - (n12 - n9), n2 - (this.h - this.overlap), n4, n5, n6);
                }
            }
            if ((n2 == 0 && this.f0 < 0) || (n2 == this.overlap - 3 && this.f0 > 0 && (n < n10 - 2 || n > n10 + 2))) {
                return this.darkenRGB(n4, n5, n6, 15);
            }
            if ((n2 == 1 && this.f0 <= 0) || (n2 == this.overlap - 2 && this.f0 > 0 && (n < n10 - 2 || n > n10 + 2))) {
                return this.lightenRGB(n4, n5, n6, 20);
            }
            if ((n2 == 2 && this.f0 <= 0) || (n2 == this.overlap - 1 && this.f0 > 0 && (n < n10 - 2 || n > n10 + 2))) {
                return this.lightenRGB(n4, n5, n6, 15);
            }
            if ((n == this.w - 1 && this.f1 <= 0) || (this.w - 1 - n == this.overlap - 3 && this.f1 > 0 && (n2 < n11 - 2 || n2 > n11 + 2))) {
                return this.lightenRGB(n4, n5, n6, 20);
            }
            if ((n2 == this.h - 1 && this.f2 <= 0) || (this.h - 1 - n2 == this.overlap - 3 && this.f2 > 0 && (n < n12 - 2 || n > n12 + 2))) {
                return this.lightenRGB(n4, n5, n6, 20);
            }
            if ((n == 0 && this.f3 < 0) || (n == this.overlap - 3 && this.f3 > 0 && (n2 < n13 - 2 || n2 > n13 + 2))) {
                return this.darkenRGB(n4, n5, n6, 15);
            }
            if ((n == 1 && this.f3 <= 0) || (n == this.overlap - 2 && this.f3 > 0 && (n2 < n13 - 2 || n2 > n13 + 2))) {
                return this.lightenRGB(n4, n5, n6, 20);
            }
            if ((n == 2 && this.f3 <= 0) || (n == this.overlap - 1 && this.f3 > 0 && (n2 < n13 - 2 || n2 > n13 + 2))) {
                return this.lightenRGB(n4, n5, n6, 15);
            }
            return n3;
        }
    }
    
    private int holeCutter(final int n, final int n2, int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = this.overlap / 2;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        ++n3;
        switch (n) {
            case 0: {
                n9 = n4;
                n10 = n3 - 1;
                n11 = 0;
                n12 = 3;
                break;
            }
            case 1: {
                n10 = n4 - 1;
                n9 = n3 - 3;
                n11 = 0;
                n12 = 2;
                break;
            }
            case 2: {
                n9 = n4 - 2;
                n10 = n3 - 1;
                n11 = 0;
                n12 = 2;
                break;
            }
            case 3: {
                n10 = n4 - 1;
                n9 = n3 - 1;
                n11 = 0;
                n12 = 3;
                break;
            }
        }
        if (n9 >= n11 && n9 + n12 < 22) {
            if (n10 > n8 - this.points[n9 + n12] && n10 < n8 + this.points[n9 + n12]) {
                return 0;
            }
            if (n10 >= n8 - this.points[n9 + n12] && n10 <= n8 + this.points[n9 + n12]) {
                return this.darkenRGB(n5, n6, n7, 30);
            }
        }
        return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
    }
    
    private int lightenRGB(int n, int n2, int n3, final int n4) {
        if ((n = 10 + (255 - (255 - n) * (100 - n4) / 100)) > 255) {
            n = 255;
        }
        if ((n2 = 10 + (255 - (255 - n2) * (100 - n4) / 100)) > 255) {
            n2 = 255;
        }
        if ((n3 = 10 + (255 - (255 - n3) * (100 - n4) / 100)) > 255) {
            n3 = 255;
        }
        return 0xFF000000 | n << 16 | n2 << 8 | n3;
    }
    
    private int stubCutter(final int n, final int n2, int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = this.overlap / 2;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        ++n3;
        switch (n) {
            case 0: {
                n9 = n4;
                n10 = n3 - 1;
                n11 = 0;
                n12 = 3;
                break;
            }
            case 1: {
                n10 = n4 - 1;
                n9 = n3 - 3;
                n11 = 0;
                n12 = 2;
                break;
            }
            case 2: {
                n9 = n4 - 2;
                n10 = n3 - 1;
                n11 = 0;
                n12 = 2;
                break;
            }
            case 3: {
                n10 = n4 - 1;
                n9 = n3 - 1;
                n11 = 0;
                n12 = 3;
                break;
            }
        }
        if (n9 >= n11 && n9 + n12 < 23) {
            if (n10 > n8 - this.points[n9 + n12] && n10 < n8 + this.points[n9 + n12]) {
                return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
            }
            if (n10 >= n8 - this.points[n9 + n12] && n10 <= n8 + this.points[n9 + n12]) {
                return this.lightenRGB(n5, n6, n7, 30);
            }
        }
        return 0;
    }
}
