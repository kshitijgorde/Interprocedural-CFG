// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import ji.font.j;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import ji.util.d;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import ji.v1event.c9;
import ji.document.ad;
import ji.graphic.jiImageButton;

public abstract class dm extends jiImageButton
{
    private String a;
    private ad b;
    c9 c;
    private Image d;
    
    protected Image h() {
        return this.n();
    }
    
    protected Image k() throws Exception {
        return this.n();
    }
    
    protected Image i() throws Exception {
        return this.n();
    }
    
    protected Image j() throws Exception {
        return this.n();
    }
    
    private Image n() {
        if (this.d == null) {
            this.d = this.m();
        }
        return this.d;
    }
    
    protected abstract Image m();
    
    public dm(final String s, final String a, final int n, final ad b) {
        super(s, 1, -1, -1, false, true, false, true, 2, "".concat(String.valueOf(String.valueOf(n))), "".concat(String.valueOf(String.valueOf(n))), 1, false, null, null, true, null, 0, a, true);
        this.c = null;
        this.d = null;
        this.a = a;
        this.b = b;
        super.p = true;
        this.o();
    }
    
    private final void o() {
        if (this.c == null && this.b != null) {
            this.c = this.b.gs();
            if (this.c != null) {
                this.a(this.b.gs());
            }
        }
    }
    
    public void releaseResources() {
        try {
            if (this.c != null) {
                this.b(this.c);
            }
        }
        catch (Exception ex) {}
        if (this.d != null) {
            this.d.flush();
        }
        this.d = null;
        this.b = null;
        super.releaseResources();
    }
    
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    
    protected final Image a(final Color color, final Color color2, final int n, final boolean b, final boolean b2) {
        try {
            final int width = this.l().width;
            final int height = this.l().height;
            if (width > 0 && height > 0) {
                this.c();
                final int[] array = new int[width * height];
                final int j = ji.util.d.j(ji.util.d.h);
                for (int i = 0; i < array.length; ++i) {
                    array[i] = j;
                }
                final int k = ji.util.d.j(color2);
                final int n2 = 4;
                switch (n) {
                    case 0: {
                        int max = width / 2 - 1;
                        int min = max + 2;
                        final int n3 = (height - n2) / 2;
                        for (int n4 = n3 + n2, l = n3; l < n4; ++l) {
                            final int n5 = l * width;
                            for (int n6 = max; n6 < min; ++n6) {
                                array[n5 + n6] = k;
                            }
                            max = Math.max(max - 1, 0);
                            min = Math.min(min + 1, width);
                        }
                        if (b) {
                            for (int n7 = max + 1; n7 < min - 1; ++n7) {
                                array[(n3 - 1) * width + n7] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 90: {
                        int min2 = (height - n2 - 4) / 2;
                        int max2 = min2 + n2 + 4;
                        final int n8 = 1 + (width - n2) / 2;
                        final int n9 = n8 + n2;
                        for (int n10 = n8; n10 < n9; ++n10) {
                            for (int n11 = min2; n11 < max2; ++n11) {
                                array[n10 + n11 * width] = k;
                            }
                            min2 = Math.min(min2 + 1, height);
                            max2 = Math.max(max2 - 1, 0);
                        }
                        final int n12 = (height - n2 - 4) / 2;
                        final int n13 = n12 + n2 + 4;
                        if (b) {
                            for (int n14 = n12; n14 < n13; ++n14) {
                                array[n9 + n14 * width] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 180: {
                        int max3 = width / 2 - 1;
                        int min3 = max3 + 2;
                        int n15;
                        int n17;
                        int n16;
                        for (n15 = (height - n2) / 2 - 1, n16 = (n17 = n15 + n2); n17 > n15; --n17) {
                            final int n18 = n17 * width;
                            for (int n19 = max3; n19 < min3; ++n19) {
                                array[n18 + n19] = k;
                            }
                            max3 = Math.max(max3 - 1, 0);
                            min3 = Math.min(min3 + 1, width);
                        }
                        if (b) {
                            for (int n20 = max3 + 1; n20 < min3 - 1; ++n20) {
                                array[(n16 + 1) * width + n20] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 270: {
                        int min4 = (height - n2 - 4) / 2;
                        int max4 = min4 + n2 + 4;
                        final int n21 = (width - n2) / 2 - 1;
                        for (int n22 = n21 + n2; n22 > n21; --n22) {
                            for (int n23 = min4; n23 < max4; ++n23) {
                                array[n22 + n23 * width] = k;
                            }
                            min4 = Math.min(min4 + 1, height);
                            max4 = Math.max(max4 - 1, 0);
                        }
                        final int n24 = (height - n2 - 4) / 2;
                        final int n25 = n24 + n2 + 4;
                        if (b) {
                            for (int n26 = n24; n26 < n25; ++n26) {
                                array[n21 + n26 * width] = k;
                            }
                            break;
                        }
                        break;
                    }
                }
                return this.a(this.createImage(new MemoryImageSource(width, height, ColorModel.getRGBdefault(), array, 0, width)), ji.util.d.h);
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected Image a(final int n, final int n2) {
        Image image;
        if (!ji.util.d.em()) {
            image = ji.font.j.a(n, n2, 2);
            if (image == null) {
                image = this.b.createImage(n, n2);
            }
        }
        else {
            image = this.createImage(n, n2);
        }
        return image;
    }
    
    public Image a(final Image image, final Color color) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new aaq(color)));
    }
}
