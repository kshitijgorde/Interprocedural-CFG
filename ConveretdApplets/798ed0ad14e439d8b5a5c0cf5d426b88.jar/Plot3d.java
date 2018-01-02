import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plot3d
{
    private static int[] buffer;
    private static float[] depth;
    private int width;
    private int height;
    private int[] offs;
    private float a0;
    private float b0;
    private float c0;
    private float a1;
    private float b1;
    private float c1;
    private float a2;
    private float b2;
    private float c2;
    private int old_r;
    private int old_g;
    private int old_b;
    private int old_a;
    private int[] old;
    private int PLANES;
    private int SMOOTH_LEVEL;
    int abs;
    private int color;
    private float u0;
    private float u1;
    private float u2;
    public float[][] view;
    public boolean doNotPlot;
    public boolean setOver;
    public boolean overFound;
    public int over_x;
    public int over_y;
    public float over_z;
    public boolean pixelsSet;
    public int pixelsX;
    public int pixelsY;
    public float tol;
    public final int transparentColor = 16777215;
    public boolean first;
    
    public Plot3d() {
        this.PLANES = 4;
        this.abs = 0;
        this.tol = 10.0f;
        this.view = zeroMatrix();
        this.view[0][0] = 1.0f;
        this.view[1][1] = 1.0f;
        this.view[2][2] = 1.0f;
        this.view[3][3] = 1.0f;
        this.old = new int[4];
        this.u0 = 1.0f;
        this.u1 = 0.0f;
        this.u2 = 0.0f;
    }
    
    public final int getPlanes() {
        return this.PLANES;
    }
    
    public final int[] getBuffer() {
        if (!this.first) {}
        this.first = false;
        this.a1();
        return Plot3d.buffer;
    }
    
    public final void drawTriangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.drawTriangle(true, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public final void drawTriangle(final boolean b, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        if (mult3(this.view, n, n2, n3) < 0.0f) {
            return;
        }
        if (mult3(this.view, n4, n5, n6) < 0.0f) {
            return;
        }
        if (mult3(this.view, n7, n8, n9) < 0.0f) {
            return;
        }
        final float mult0 = mult0(this.view, n, n2, n3);
        final float mult2 = mult1(this.view, n, n2, n3);
        final float mult3 = mult2(this.view, n, n2, n3);
        final float mult4 = mult0(this.view, n4, n5, n6);
        final float mult5 = mult1(this.view, n4, n5, n6);
        final float mult6 = mult2(this.view, n4, n5, n6);
        final float mult7 = mult0(this.view, n7, n8, n9);
        final float mult8 = mult1(this.view, n7, n8, n9);
        final float mult9 = mult2(this.view, n7, n8, n9);
        if (!this.doNotPlot) {
            this.drawTrianglePrim(b, mult0, mult2, mult3, mult4, mult5, mult6, mult7, mult8, mult9);
        }
        this.overTriangle(mult0, mult2, mult3, mult4, mult5, mult6, mult7, mult8, mult9);
    }
    
    private void overTriangle(final float n, final float n2, float over_z, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (!this.setOver) {
            return;
        }
        final float n9 = n3 - n;
        final float n10 = n4 - n2;
        final float n11 = n6 - n;
        final float n12 = n7 - n2;
        final float n13 = n9 * n12 - n10 * n11;
        final float n14 = this.over_x - n;
        final float n15 = this.over_y - n2;
        final float n16 = (n14 * n12 - n15 * n11) / n13;
        final float n17 = (-n14 * n10 + n15 * n9) / n13;
        if (n16 >= 0.0f && n17 >= 0.0f && n16 + n17 <= 1.0f) {
            over_z += n16 * (n5 - over_z) + n17 * (n8 - over_z);
            this.overFound = true;
            this.over_z = over_z;
        }
    }
    
    public final int setAlpha(final int old_a) {
        this.old_a = old_a;
        if (this.PLANES == 4) {
            if (this.old_a == 0) {
                return 0;
            }
            if (this.old_a < 95) {
                this.old_a = 64;
                this.SMOOTH_LEVEL = 1;
            }
            else if (this.old_a < 159) {
                this.old_a = 128;
                this.SMOOTH_LEVEL = 2;
            }
            else if (this.old_a < 223) {
                this.old_a = 192;
                this.SMOOTH_LEVEL = 3;
            }
            else {
                this.old_a = 255;
                this.SMOOTH_LEVEL = 4;
            }
        }
        else if (this.PLANES == 1) {
            this.SMOOTH_LEVEL = 1;
        }
        else {
            this.SMOOTH_LEVEL = 16;
        }
        return this.old_a;
    }
    
    private void drawTrianglePrim(final boolean b, float n, float n2, final float n3, float n4, float n5, final float n6, float n7, float n8, final float n9) {
        if (this.doNotPlot) {
            return;
        }
        final int color = this.color;
        this.getARGB(this.old);
        if (this.setAlpha(this.old[0]) == 0) {
            return;
        }
        this.old_r = this.old[1];
        this.old_g = this.old[2];
        this.old_b = this.old[3];
        float n10 = 1.0f;
        final float n11 = n2 * (n9 - n6) + n5 * (n3 - n9) + n8 * (n6 - n3);
        final float n12 = n * (n6 - n9) + n4 * (n9 - n3) + n7 * (n3 - n6);
        final float n13 = n * (n8 - n5) + n4 * (n2 - n8) + n7 * (n5 - n2);
        final float n14 = n * (n5 * n9 - n8 * n6) + n4 * (n8 * n3 - n2 * n9) + n7 * (n2 * n6 - n5 * n3);
        this.a0 = n2 - n5;
        this.b0 = n4 - n;
        this.c0 = -(this.b0 * n2 + this.a0 * n);
        this.a1 = n5 - n8;
        this.b1 = n7 - n4;
        this.c1 = -(this.b1 * n5 + this.a1 * n4);
        this.a2 = n8 - n2;
        this.b2 = n - n7;
        this.c2 = -(this.b2 * n8 + this.a2 * n7);
        if (b) {
            final float n15 = n11 * n11 + n12 * n12 + n13 * n13;
            if (n15 == 0.0f) {
                return;
            }
            float n16 = (n11 * this.u0 + n12 * this.u1 + n13 * this.u2) / (float)Math.sqrt(n15);
            if (n16 * n13 >= 0.0f) {
                if (n16 < 0.0f) {
                    n16 = -n16;
                }
            }
            else if (n16 > 0.0f) {
                n16 = -n16;
            }
            n10 = 1.0f - (n16 + 1.0f) / 2.0f * 0.7f;
            this.setColor(this.old_r, this.old_g, this.old_b, this.old_a, n10);
        }
        final float n17 = -n11 / n13;
        final float n18 = -n12 / n13;
        final float n19 = -n14 / n13;
        if (n5 < n2 && n5 <= n8) {
            final float n20 = n;
            n = n4;
            n4 = n20;
            final float n21 = n2;
            n2 = n5;
            n5 = n21;
        }
        if (n8 < n2 && n8 < n5) {
            final float n22 = n;
            n = n7;
            n7 = n22;
            final float n23 = n2;
            n2 = n8;
            n8 = n23;
        }
        float n24 = n5 - n2;
        float n25 = n8 - n2;
        float n26 = n4 - n;
        float n27 = n7 - n;
        final float min = Math.min(n8 - n2, n5 - n2);
        float n28;
        if (n24 == 0.0f) {
            n28 = n4;
        }
        else {
            n28 = min * n26 / n24 + n;
        }
        float n29;
        if (n25 == 0.0f) {
            n29 = n7;
        }
        else {
            n29 = min * n27 / n25 + n;
        }
        if (n29 < n28) {
            final float n30 = n4;
            n4 = n7;
            n7 = n30;
            final float n31 = n5;
            n5 = n8;
            n8 = n31;
            final float n32 = n26;
            n26 = n27;
            n27 = n32;
            final float n33 = n24;
            n24 = n25;
            n25 = n33;
            final float n34 = n28;
            n28 = n29;
            n29 = n34;
        }
        float n35;
        if (n8 > n5) {
            n35 = n7;
        }
        else {
            n35 = n4;
        }
        final float max = Math.max(n8 - n2, n5 - n2);
        float n36 = n35 - n28;
        final float n37 = n35 - n29;
        float n38 = max - min;
        float n39 = max - min;
        if (n38 == 0.0f) {
            n38 = 1.0f;
            n36 = 0.0f;
        }
        if (n39 == 0.0f) {
            n39 = 1.0f;
            n36 = 0.0f;
        }
        final float n40 = (float)Math.floor(n2);
        final float n41 = (float)Math.ceil(max + n2);
        final float n42 = (float)Math.floor(n2 + min);
        final float n43 = n2 + max;
        final float n44 = n2 + min;
        float n46;
        float n45 = n46 = n;
        for (float n47 = n40; n47 < n41; ++n47) {
            if (n47 >= 0.0f) {
                if (n47 < this.height) {
                    float n48;
                    float n49;
                    if (n47 < n42) {
                        n48 = n46;
                        n49 = n45;
                        n46 = (n47 - n2 + 1.0f) * n26 / n24 + n;
                        n45 = (n47 - n2 + 1.0f) * n27 / n25 + n;
                    }
                    else {
                        n48 = n46;
                        n49 = n45;
                        n46 = (n47 - n44 + 1.0f) * n36 / n38 + n28;
                        n45 = (n47 - n44 + 1.0f) * n37 / n39 + n29;
                    }
                    final float n50 = (float)Math.max(Math.ceil(n48), Math.ceil(n46));
                    if (n48 < this.width || n49 < this.width || n46 < this.width || n45 < this.width || n28 < this.width || n29 < this.width) {
                        final float n51 = (float)Math.min(Math.floor(n49), Math.floor(n45));
                        if (n46 >= 0.0f || n45 >= 0.0f || n48 >= 0.0f || n49 >= 0.0f || n28 >= 0.0f || n29 >= 0.0f) {
                            if (n51 - n50 >= 0.0f) {
                                if ((n47 > n2 && n47 < n42) || (n47 >= n42 + 1.0f && n47 < n43)) {
                                    this.PLANES(false, n48, n46, n47, n17, n18, n19, n10);
                                    this.PLANES(true, n49, n45, n47, n17, n18, n19, n10);
                                }
                                else {
                                    float smooth_LEVEL;
                                    float a0;
                                    float smooth_LEVEL2;
                                    float a2;
                                    if (n47 >= n42 && n47 < n42 + 1.0f) {
                                        smooth_LEVEL = this.SMOOTH_LEVEL(n48, n46, n28);
                                        a0 = this.a0(n48, n46, n28);
                                        smooth_LEVEL2 = this.SMOOTH_LEVEL(n49, n45, n29);
                                        a2 = this.a0(n49, n45, n29);
                                    }
                                    else {
                                        smooth_LEVEL = n48;
                                        a0 = n48;
                                        smooth_LEVEL2 = n49;
                                        a2 = n49;
                                    }
                                    this.vertex((int)Math.floor(smooth_LEVEL), (int)Math.floor(a0), n47, n17, n18, n19, n10);
                                    this.vertex((int)Math.floor(smooth_LEVEL2), (int)Math.floor(a2), n47, n17, n18, n19, n10);
                                }
                            }
                            else {
                                this.vertex((int)Math.floor(this.SMOOTH_LEVEL(n48, n46, n28)), (int)Math.ceil(this.a0(n49, n45, n29)), n47, n17, n18, n19, n10);
                            }
                            if (n51 - n50 > 0.0f) {
                                if (n47 > n2) {
                                    this.drawHorz((int)n50, (int)n47, (int)n51 - 1, n17 * n50 + n18 * n47 + n19, n17);
                                }
                                else {
                                    this.vertex((int)n50, (int)n51, n47, n17, n18, n19, n10);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.color = color;
    }
    
    private final void PLANES(final boolean b, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        if (n > n2) {
            final boolean b2 = !b;
        }
        this.vertex((int)n, (int)n2, n3, n4, n5, n6, n7);
    }
    
    private void vertex(int n, int n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        if (n > n2) {
            final int n8 = n;
            n = n2;
            n2 = n8;
        }
        if (n2 < 0 || n >= this.width) {
            return;
        }
        if (n < 0) {
            n = 0;
        }
        if (n2 >= this.width) {
            n2 = this.width - 1;
        }
        for (int i = n; i <= n2; ++i) {
            float n9 = 0.0f;
            if (this.SMOOTH_LEVEL == 1) {
                if (this.interior(i + 0.5f, n3 + 0.5f)) {
                    n9 = 1.0f;
                }
            }
            else if (this.SMOOTH_LEVEL == 2) {
                if (this.interior(i + 0.25f, n3 + 0.25f)) {
                    n9 += 0.5;
                }
                if (this.interior(i + 0.75f, n3 + 0.75f)) {
                    n9 += 0.5;
                }
            }
            else if (this.SMOOTH_LEVEL == 3) {
                if (this.interior(i + 0.25f, n3 + 0.25f)) {
                    n9 += 0.33333334f;
                }
                if (this.interior(i + 0.25f, n3 + 0.75f)) {
                    n9 += 0.33333334f;
                }
                if (this.interior(i + 0.75f, n3 + 0.75f)) {
                    n9 += 0.33333334f;
                }
            }
            else if (this.SMOOTH_LEVEL == 4) {
                if (this.interior(i + 0.25f, n3 + 0.25f)) {
                    n9 += 0.25;
                }
                if (this.interior(i + 0.25f, n3 + 0.75f)) {
                    n9 += 0.25;
                }
                if (this.interior(i + 0.75f, n3 + 0.75f)) {
                    n9 += 0.25;
                }
                if (this.interior(i + 0.75f, n3 + 0.25f)) {
                    n9 += 0.25;
                }
            }
            else if (this.SMOOTH_LEVEL == 16) {
                for (float n10 = 0.0f; n10 < 1.0f; n10 += 0.25) {
                    for (float n11 = 0.0f; n11 < 1.0f; n11 += 0.25) {
                        if (this.interior(i + n10 + 0.125f, n3 + n11 + 0.125f)) {
                            n9 += 0.0625f;
                        }
                    }
                }
            }
            if (n9 > 0.0f) {
                final float n12 = n4 * i + n5 * n3 + n6;
                this.setColor(this.old_r, this.old_g, this.old_b, (int)(this.old_a * n9), n7);
                this.drawPoint(i, n3, n12);
                this.setColor(this.old_r, this.old_g, this.old_b, this.old_a, n7);
            }
        }
    }
    
    private final float SMOOTH_LEVEL(final float n, final float n2, final float n3) {
        if (n < n2) {
            if (n < n3) {
                return n;
            }
            return n3;
        }
        else {
            if (n2 < n3) {
                return n2;
            }
            return n3;
        }
    }
    
    private final float a0(final float n, final float n2, final float n3) {
        if (n > n2) {
            if (n > n3) {
                return n;
            }
            return n3;
        }
        else {
            if (n2 > n3) {
                return n2;
            }
            return n3;
        }
    }
    
    private void drawHorz(final int n, final int n2, final int n3, float n4, final float n5) {
        if (this.doNotPlot) {}
        if (n2 < 0 || n2 >= this.height) {
            return;
        }
        int i = n;
        int n6 = n3;
        if (n6 > this.width - 1) {
            n6 = this.width - 1;
        }
        if (i < 0) {
            n4 += -i * n5;
            i = 0;
        }
        int n7 = this.width * n2 + i;
        while (i <= n6) {
            for (int j = 0; j < this.PLANES; ++j) {
                final int n8 = n7 + this.offs[j];
                if (n4 < Plot3d.depth[n8]) {
                    if (j > this.abs) {
                        this.abs = j;
                    }
                    int n9 = n7 + this.offs[this.PLANES - 1];
                    for (int k = this.PLANES - 2; k >= j; --k) {
                        final int n10 = n7 + this.offs[k];
                        Plot3d.buffer[n9] = Plot3d.buffer[n10];
                        Plot3d.depth[n9] = Plot3d.depth[n10];
                        n9 = n10;
                    }
                    Plot3d.buffer[n8] = this.color;
                    Plot3d.depth[n8] = n4;
                    break;
                }
            }
            ++n7;
            n4 += n5;
            ++i;
        }
    }
    
    public final void setColor(final int n, final int n2, final int n3, final int n4) {
        this.setColorImpl(n, n2, n3, n4);
    }
    
    public final int getColor() {
        return this.color;
    }
    
    private final void setColorImpl(final int n, final int n2, final int n3, final int n4) {
        this.color = (n4 << 24 | n << 16 | n2 << 8 | n3);
    }
    
    private final void setColor(final int n, final int n2, final int n3, final int n4, final float n5) {
        this.setColorImpl((int)(255.0f - (255 - n) * n5), (int)(255.0f - (255 - n2) * n5), (int)(255.0f - (255 - n3) * n5), n4);
    }
    
    private void getARGB(final int[] array) {
        array[0] = ((this.color & 0xFF000000) >> 24 & 0xFF);
        array[1] = (this.color & 0xFF0000) >> 16;
        array[2] = (this.color & 0xFF00) >> 8;
        array[3] = (this.color & 0xFF) >> 0;
    }
    
    public final void drawLine(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.doNotPlot) {
            return;
        }
        if (mult3(this.view, n, n2, n3) < 0.0f) {
            return;
        }
        if (mult3(this.view, n4, n5, n6) < 0.0f) {
            return;
        }
        this.drawLinePrim(mult0(this.view, n, n2, n3), mult1(this.view, n, n2, n3), mult2(this.view, n, n2, n3), mult0(this.view, n4, n5, n6), mult1(this.view, n4, n5, n6), mult2(this.view, n4, n5, n6));
    }
    
    public final void drawLinePrim(float n, float n2, float n3, float n4, float n5, float n6) {
        if (this.doNotPlot) {
            return;
        }
        final float n7 = n4 - n;
        final float n8 = n5 - n2;
        final float n9 = n6 - n3;
        final int n10 = (n7 > 0.0f) ? 1 : -1;
        final int n11 = (n8 > 0.0f) ? 1 : -1;
        if (n10 * n7 <= n11 * n8) {
            if (n11 < 0) {
                final float n12 = n;
                n = n4;
                n4 = n12;
                final float n13 = n2;
                n2 = n5;
                n5 = n13;
                final float n14 = n3;
                n3 = n6;
                n6 = n14;
            }
            for (float n15 = n2; n15 <= n5; ++n15) {
                this.drawPoint(n, n15, n3 - 1.0f);
                n += n7 / n8;
                n3 += n9 / n8;
            }
        }
        else {
            if (n10 < 0) {
                final float n16 = n;
                n = n4;
                n4 = n16;
                final float n17 = n2;
                n2 = n5;
                n5 = n17;
                final float n18 = n3;
                n3 = n6;
                n6 = n18;
            }
            for (float n19 = n; n19 <= n4; ++n19) {
                this.drawPoint(n19, n2, n3 - 1.0f);
                n2 += n8 / n7;
                n3 += n9 / n7;
            }
        }
    }
    
    public static final Object[] compileImage(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return new Object[] { new Integer(width), new Integer(height), array };
    }
    
    public final void drawPixels(final float n, final float n2, final float n3, final Object[] array) {
        if (mult3(this.view, n, n2, n3) < 0.0f) {
            return;
        }
        this.drawPixelsPrim(mult0(this.view, n, n2, n3), mult1(this.view, n, n2, n3), mult2(this.view, n, n2, n3), array);
    }
    
    public final void drawPixels(final float n, final float n2, final float n3, final Object[] array, final short n4) {
        this.pixelsSet = false;
        if (mult3(this.view, n, n2, n3) < 0.0f) {
            return;
        }
        this.drawPixelsPrim(mult0(this.view, n, n2, n3), mult1(this.view, n, n2, n3), mult2(this.view, n, n2, n3), array, n4);
    }
    
    public final void drawPixelsPrim(float n, float n2, final float n3, final Object[] array, final short n4) {
        if (this.doNotPlot) {
            return;
        }
        final int intValue = (int)array[0];
        final int intValue2 = (int)array[1];
        switch (n4 & 0xF) {
            case 1: {
                n -= intValue;
                break;
            }
            case 3: {
                n -= (intValue - 1) / 2.0f;
                break;
            }
        }
        switch (n4 & 0xF0) {
            case 16: {
                n2 -= intValue2;
                break;
            }
            case 48: {
                n2 -= (intValue2 - 1) / 2.0f;
                break;
            }
        }
        this.pixelsSet = true;
        this.pixelsX = (int)n;
        this.pixelsY = (int)n2;
        this.drawPixelsPrim(n, n2, n3, array);
        if (this.setOver) {
            this.overPixelsPrim(n, n2, n3, intValue, intValue2);
        }
    }
    
    private void overPixelsPrim(final float n, final float n2, final float over_z, final float n3, final float n4) {
        if (n <= this.over_x && this.over_x <= n + n3 && n2 <= this.over_y && this.over_y <= n2 + n4) {
            this.overFound = true;
            this.over_z = over_z;
        }
    }
    
    public final void drawPixelsPrim(final float n, final float n2, final float n3, final Object[] array) {
        if (this.doNotPlot) {
            return;
        }
        final int color = this.color;
        final int intValue = (int)array[0];
        final int intValue2 = (int)array[1];
        final int[] array2 = (int[])array[2];
        final int n4 = (int)n2;
        final int n5 = (int)n;
        final int n6 = intValue2 + n4;
        final int n7 = intValue + n5;
        int n8 = n4 * this.width + n5;
        int n9 = 0;
        for (int i = n4; i < n6; ++i) {
            int n10 = n8;
            for (int j = n5; j < n7; ++j) {
                final int color2 = array2[n9++];
                if (color2 != 16777215 && i >= 0 && i < this.height && j >= 0 && j < this.width) {
                    this.color = color2;
                    if (this.PLANES == 1) {
                        this.getARGB(this.old);
                        if (this.old[0] > 100) {
                            for (int k = 1; k <= 3; ++k) {
                                this.old[k] = 255 - ((255 - this.old[k]) * this.old[0] >> 8);
                            }
                            this.setColor(this.old[1], this.old[2], this.old[3], 255);
                            this.drawHorz(j, i, j, n3, 0.0f);
                        }
                    }
                    else {
                        this.drawHorz(j, i, j, n3, 0.0f);
                    }
                }
                ++n10;
            }
            n8 += this.width;
        }
        this.color = color;
    }
    
    private void drawPoint(final float n, final float n2, final float n3) {
        this.drawHorz((int)n, (int)n2, (int)n, n3, 0.0f);
    }
    
    public final void init(final int n, final int n2, final int n3) {
        this.init(n, n2, n3, null, null);
    }
    
    public final void init(final int width, final int height, final int n, final int[] array, final float[] array2) {
        this.first = true;
        if (Plot3d.buffer == null || this.PLANES * width * height > Plot3d.buffer.length) {
            Plot3d.buffer = null;
            Plot3d.depth = null;
            this.doNotPlot = false;
            Plot3d.buffer = new int[this.PLANES * width * height];
            Plot3d.depth = new float[this.PLANES * width * height];
        }
        if (this.doNotPlot) {
            if (array != null && Plot3d.buffer.length == array.length) {
                for (int i = 0; i < Plot3d.buffer.length; ++i) {
                    Plot3d.buffer[i] = array[i];
                    Plot3d.depth[i] = array2[i];
                }
            }
            else {
                this.doNotPlot = false;
            }
        }
        if (!this.doNotPlot) {
            for (int j = 0; j < this.PLANES * width * height; ++j) {
                Plot3d.buffer[j] = n;
                Plot3d.depth[j] = Float.MAX_VALUE;
            }
            this.offs = new int[this.PLANES];
            for (int k = 0; k < this.PLANES; ++k) {
                final int[] offs = this.offs;
                final int n2 = k;
                offs[n2] += k * width * height;
            }
            this.width = width;
            this.height = height;
        }
    }
    
    public final float determinant(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        return n * (n5 * n9 - n8 * n6) + n4 * (n8 * n3 - n2 * n9) + n7 * (n2 * n6 - n5 * n3);
    }
    
    public final void multiplyViewMatrix(final float[][] array) {
        this.view = multiplyMatrix(array, this.view);
    }
    
    public static final float[][] spinMatrixZ(final float n) {
        final float[][] zeroMatrix = zeroMatrix();
        zeroMatrix[0][0] = (float)Math.cos(n);
        zeroMatrix[0][1] = (float)Math.sin(n);
        zeroMatrix[1][0] = -(float)Math.sin(n);
        zeroMatrix[1][1] = (float)Math.cos(n);
        zeroMatrix[2][2] = 1.0f;
        zeroMatrix[3][3] = 1.0f;
        return zeroMatrix;
    }
    
    public static final float[][] spinMatrixY(final float n) {
        final float[][] zeroMatrix = zeroMatrix();
        zeroMatrix[0][0] = (float)Math.cos(n);
        zeroMatrix[0][2] = (float)Math.sin(n);
        zeroMatrix[2][0] = -(float)Math.sin(n);
        zeroMatrix[2][2] = (float)Math.cos(n);
        zeroMatrix[1][1] = 1.0f;
        zeroMatrix[3][3] = 1.0f;
        return zeroMatrix;
    }
    
    public static final float[][] spinMatrixX(final float n) {
        final float[][] zeroMatrix = zeroMatrix();
        zeroMatrix[2][2] = (float)Math.cos(n);
        zeroMatrix[2][1] = (float)Math.sin(n);
        zeroMatrix[1][2] = -(float)Math.sin(n);
        zeroMatrix[1][1] = (float)Math.cos(n);
        zeroMatrix[0][0] = 1.0f;
        zeroMatrix[3][3] = 1.0f;
        return zeroMatrix;
    }
    
    public static final float[][] multiplyMatrix(final float[][] array, final float[][] array2) {
        final float[][] zeroMatrix = zeroMatrix();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                float n = 0.0f;
                for (int k = 0; k < 4; ++k) {
                    n += array[i][k] * array2[k][j];
                }
                zeroMatrix[i][j] = n;
            }
        }
        return zeroMatrix;
    }
    
    public static final float[][] translateMatrix(final float n, final float n2, final float n3) {
        final float[][] zeroMatrix = zeroMatrix();
        zeroMatrix[0][0] = 1.0f;
        zeroMatrix[1][1] = 1.0f;
        zeroMatrix[2][2] = 1.0f;
        zeroMatrix[3][3] = 1.0f;
        zeroMatrix[0][3] = n;
        zeroMatrix[1][3] = n2;
        zeroMatrix[2][3] = n3;
        return zeroMatrix;
    }
    
    public static final float mult0(final float[][] array, final float n, final float n2, final float n3) {
        return (array[0][0] * n + array[0][1] * n2 + array[0][2] * n3 + array[0][3]) / mult3(array, n, n2, n3);
    }
    
    public static final float mult1(final float[][] array, final float n, final float n2, final float n3) {
        return (array[1][0] * n + array[1][1] * n2 + array[1][2] * n3 + array[1][3]) / mult3(array, n, n2, n3);
    }
    
    public static final float mult2(final float[][] array, final float n, final float n2, final float n3) {
        return (array[2][0] * n + array[2][1] * n2 + array[2][2] * n3 + array[2][3]) / mult3(array, n, n2, n3);
    }
    
    public static final float mult3(final float[][] array, final float n, final float n2, final float n3) {
        return array[3][0] * n + array[3][1] * n2 + array[3][2] * n3 + array[3][3];
    }
    
    public static final float[][] zeroMatrix() {
        return new float[][] { new float[4], new float[4], new float[4], new float[4] };
    }
    
    public final boolean interior(final float n, final float n2) {
        final float n3 = 0.001f;
        float n4 = this.a0 * n + this.b0 * n2 + this.c0;
        final float n5 = this.a1 * n + this.b1 * n2 + this.c1;
        if (Math.abs(n5) > n3) {
            if (Math.abs(n4) > n3 && n5 * n4 < 0.0f) {
                return false;
            }
            n4 = n5;
        }
        final float n6 = this.a2 * n + this.b2 * n2 + this.c2;
        return Math.abs(n6) <= n3 || Math.abs(n4) <= n3 || n6 * n4 >= 0.0f;
    }
    
    private final void a1() {
        final int n = this.PLANES - 1;
        final int n2 = this.offs[0];
        for (int n3 = this.width * this.height, i = 0; i < n3; ++i) {
            final int n4 = Plot3d.buffer[i + n2];
            final int n5 = (n > 0) ? ((n4 & 0xFF000000) >> 24 & 0xFF) : 255;
            final int n6 = (n4 & 0xFF0000) >> 16;
            final int n7 = (n4 & 0xFF00) >> 8;
            final int n8 = (n4 & 0xFF) >> 0;
            final int n9 = 255 - n6;
            final int n10 = 255 - n7;
            final int n11 = 255 - n8;
            int n12 = n5;
            int n13 = n9 * n5 >> 8;
            int n14 = n10 * n5 >> 8;
            int n15 = n11 * n5 >> 8;
            for (int n16 = 1; n16 <= n && n12 != 255; ++n16) {
                final int n17 = Plot3d.buffer[i + this.offs[n16]];
                final int n18 = (n17 & 0xFF000000) >> 24 & 0xFF;
                final int n19 = (n17 & 0xFF0000) >> 16;
                final int n20 = (n17 & 0xFF00) >> 8;
                final int n21 = (n17 & 0xFF) >> 0;
                final int n22 = 255 - n19;
                final int n23 = 255 - n20;
                final int n24 = 255 - n21;
                final int min = Math.min(255 - n12, n18);
                n12 += min;
                n13 += n22 * min >> 8;
                if (n13 > 255) {
                    n13 = 255;
                }
                n14 += n23 * min >> 8;
                if (n14 > 255) {
                    n14 = 255;
                }
                n15 += n24 * min >> 8;
                if (n15 > 255) {
                    n15 = 255;
                }
            }
            Plot3d.buffer[i + n2] = (n12 << 24 | 255 - n13 << 16 | 255 - n14 << 8 | 255 - n15);
        }
    }
    
    public final void setSmoothLevel(final int n) {
        if (n == 0) {
            this.PLANES = 1;
        }
        else {
            if (n != 1) {
                throw new Error("Smoothness not implemented.");
            }
            this.PLANES = 4;
        }
    }
    
    public final void free_resources() {
        Plot3d.buffer = null;
        Plot3d.depth = null;
        if (this.view != null) {
            for (int i = 0; i < this.view.length; ++i) {
                this.view[i] = null;
            }
        }
        this.view = null;
    }
}
