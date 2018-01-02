import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ButtonImageSource implements Cloneable
{
    private int[] facePixels;
    private int[] labelPixels;
    private int[] faceShadowPixels;
    private int[] labelShadowPixels;
    private int[] pixels;
    private Color faceColor;
    private Color labelColor;
    private double faceOpacity;
    private double labelOpacity;
    private double faceColorIntensity;
    private double labelColorIntensity;
    private double shadowAmount;
    private int faceWidth;
    private int faceHeight;
    private int labelWidth;
    private int labelHeight;
    private int faceShadowWidth;
    private int faceShadowHeight;
    private int labelShadowWidth;
    private int labelShadowHeight;
    private int pixelsWidth;
    private int pixelsHeight;
    private int angle;
    private int faceSmooth;
    private int labelSmooth;
    private int shadowRadius;
    private int shadowDisp;
    private int labelDy;
    private int labelDx;
    private int labelAlign;
    private Vector consumers;
    private Rectangle activeBounds;
    private Point faceCenter;
    private boolean pixelsComplete;
    
    public void setFace(final int[] facePixels, final int faceWidth, final int faceHeight, final int n, final boolean b) {
        if (facePixels != null) {
            this.faceWidth = faceWidth;
            this.faceHeight = faceHeight;
            this.facePixels = facePixels;
            if (b) {
                this.softenMask(this.facePixels, this.faceWidth, this.faceHeight, n);
            }
            else {
                this.sharpenMask(this.facePixels, this.faceWidth, this.faceHeight, n);
            }
            this.updateFaceShadow();
            this.pixelsComplete = false;
        }
    }
    
    public void setFace(final Image image, final int n, final boolean b) {
        if (image != null) {
            this.faceWidth = image.getWidth(null);
            this.faceHeight = image.getHeight(null);
            this.facePixels = this.getPixels(image, this.faceWidth, this.faceHeight);
            if (b) {
                this.softenMask(this.facePixels, this.faceWidth, this.faceHeight, n);
            }
            else {
                this.sharpenMask(this.facePixels, this.faceWidth, this.faceHeight, n);
            }
            this.updateFaceShadow();
            this.pixelsComplete = false;
        }
    }
    
    private Rectangle effectiveRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final Rectangle intersection = new Rectangle(n3, n4, n9, n10).intersection(new Rectangle(n, n2));
        intersection.translate(n7 - n3, n8 - n4);
        return intersection.intersection(new Rectangle(n5, n6));
    }
    
    private void sharpenMask(final int[] array, final int n, final int n2, final int n3) {
        final int[] array2 = new int[n * n2];
        this.featherMask(array, n, n2, 0, 0, n, n2, array2, n, n2, 0, 0, 1, 0);
        this.copyPixels(array2, n, n2, 0, 0, n, n2, array, n, n2, 0, 0);
        this.bevelMask(array, n, n2, 2, 0.7);
        this.overlayPixels(array, n, n2, 0, 0, n, n2, array2, n, n2, 0, 0, 1.0);
        this.overlayPixels(array2, n, n2, 0, 0, n, n2, array, n, n2, 0, 0, 1.0);
        this.trimMask(array, n, n2);
        this.bevelMask(array, n, n2, 1, 0.7);
        this.bevelMask(array, n, n2, 2, 0.9);
    }
    
    public ButtonImageSource() {
        this.faceOpacity = 1.0;
        this.labelOpacity = 1.0;
        this.angle = 110;
        this.labelAlign = 1;
        this.consumers = new Vector();
    }
    
    public ButtonImageSource(final int angle) {
        this();
        this.angle = angle;
    }
    
    public void setLabelOffset(final int labelDx, final int labelDy) {
        if (labelDx != this.labelDx || labelDy != this.labelDy) {
            this.pixelsComplete = false;
            this.labelDx = labelDx;
            this.labelDy = labelDy;
        }
    }
    
    public void setLabelAlignment(int labelAlign) {
        labelAlign %= 3;
        if (labelAlign != this.labelAlign) {
            this.pixelsComplete = false;
            this.labelAlign = labelAlign;
        }
    }
    
    private void bevelMask(final int[] array, final int n, final int n2, final int n3, final double n4) {
        final int n5 = (int)(-n3 * Math.cos(this.angle * 3.141592653589793 / 180.0));
        final int n6 = (int)(n3 * Math.sin(this.angle * 3.141592653589793 / 180.0));
        final int n7 = (int)(255.0 * n4);
        final int n8 = n * n2;
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        int n9;
        int n10;
        if (n5 > 0) {
            n9 = -n5;
            n10 = n;
        }
        else {
            n9 = 0;
            n10 = n - n5;
        }
        int n11;
        int n12;
        if (n6 > 0) {
            n11 = -n6;
            n12 = n2;
        }
        else {
            n11 = 0;
            n12 = n2 - n6;
        }
        for (int i = n11; i < n12; ++i) {
            for (int j = n9; j < n10; ++j) {
                final int n13 = i * n + j;
                final int n14 = (i + n6) * n + j + n5;
                final int n15 = (n13 >= 0 && n13 < n8 && j >= 0 && j < n) ? array[n13] : 0;
                final int n16 = (n14 >= 0 && n14 < n8 && i >= 0 && i < n2) ? array[n14] : 0;
                this.splitPixel(n15, array2);
                this.splitPixel(n16, array3);
                final int n17 = array2[0] - array3[0];
                if (n17 > 0) {
                    array2[1] = array2[1] * (65025 - n17 * n7) / 65025;
                    array2[2] = array2[2] * (65025 - n17 * n7) / 65025;
                    array2[3] = array2[3] * (65025 - n17 * n7) / 65025;
                    array[n13] = ((n15 & 0xFF000000) | array2[1] << 16 | array2[2] << 8 | array2[3]);
                }
                else if (n17 < 0) {
                    array3[1] = 255 + (array3[1] - 255) * (65025 + n17 * n7) / 65025;
                    array3[2] = 255 + (array3[2] - 255) * (65025 + n17 * n7) / 65025;
                    array3[3] = 255 + (array3[3] - 255) * (65025 + n17 * n7) / 65025;
                    array[n14] = ((n16 & 0xFF000000) | array3[1] << 16 | array3[2] << 8 | array3[3]);
                }
            }
        }
    }
    
    private int[] createColoredPixels(final int[] array, final int n, final int n2, final int n3, final double n4) {
        final int n5 = n * n2;
        final int n6 = (int)(this.makeUnity(n4) * 255.0);
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        final int[] array4 = new int[n5];
        final float[] array5 = new float[3];
        final float[] array6 = new float[3];
        this.splitPixel(n3, array3);
        Color.RGBtoHSB(array3[1], array3[2], array3[3], array6);
        for (int i = 0; i < n5; ++i) {
            this.splitPixel(array[i], array2);
            Color.RGBtoHSB(array2[1], array2[2], array2[3], array5);
            final float n7 = array5[1];
            final float n8 = array5[2];
            array2[1] = (n6 * array3[1] + (255 - n6) * array2[1]) / 255;
            array2[2] = (n6 * array3[2] + (255 - n6) * array2[2]) / 255;
            array2[3] = (n6 * array3[3] + (255 - n6) * array2[3]) / 255;
            Color.RGBtoHSB(array2[1], array2[2], array2[3], array5);
            array5[1] = (float)(n4 * array6[1] + (1.0 - n4) * n7);
            array5[2] = (float)(0.5 * n4 * array6[2] + (1.0 - 0.5 * n4) * n8);
            array4[i] = (array2[0] << 24 | (0xFFFFFF & Color.HSBtoRGB(array5[0], array5[1], array5[2])));
        }
        return array4;
    }
    
    private double makeUnity(double n) {
        if (n < 0.0) {
            n = 0.0;
        }
        else if (n > 1.0) {
            n = 1.0;
        }
        return n;
    }
    
    private void softenMask(final int[] array, final int n, final int n2, final int n3) {
        final int[] array2 = new int[n * n2];
        for (int i = 0; i < n3; ++i) {
            this.featherMask(array, n, n2, 0, 0, n, n2, array2, n, n2, 0, 0, 1, 0);
            this.featherMask(array2, n, n2, 0, 0, n, n2, array, n, n2, 0, 0, 1, 0);
            this.bevelMask(array, n, n2, i + 1, 0.8);
        }
    }
    
    private void featherMask(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final int n11, int n12) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n13 = 2 * n11 + 1;
        final int n14 = n * n2;
        final int n15 = (n11 + 1) * (n11 + 1) * 255;
        final int n16 = n9 - n3;
        final int n17 = n10 - n4;
        final int[] array3 = new int[n13];
        n12 |= 0xFFFFFF;
        effectiveRect.translate(-n16, -n17);
        final int n18 = effectiveRect.x + effectiveRect.width + n11;
        final int n19 = effectiveRect.y + effectiveRect.height + n11;
        for (int i = effectiveRect.x - n11; i < n18; ++i) {
            for (int j = 0; j < n13; ++j) {
                array3[j] = 0;
            }
            for (int k = effectiveRect.y - n11; k < n19; ++k) {
                final int n20 = (k + n13 - 1) % n13;
                array3[n20] = 0;
                final int n21 = (k + n11) * n + i;
                for (int l = -n11; l <= n11; ++l) {
                    final int n22 = n21 + l;
                    if (n22 >= 0 && n22 < n14 && i + l >= 0 && i + l < n) {
                        final int[] array4 = array3;
                        final int n23 = n20;
                        array4[n23] += (array[n22] >> 24 & 0xFF) * ((l < 0) ? (n11 + 1 + l) : (n11 + 1 - l)) / (n11 + 1);
                    }
                }
                final int n24 = i + n16;
                final int n25 = k + n17;
                if (n24 >= 0 && n24 < n7 && n25 >= 0 && n25 < n8) {
                    int n26 = 0;
                    final int n27 = k + n13;
                    final int n28 = n27 + n13 - 1;
                    final int n29 = n27 + n11;
                    for (int n30 = n27; n30 <= n28; ++n30) {
                        n26 += ((n30 < n29) ? (n30 - n27 + 1) : (n28 + 1 - n30)) * array3[n30 % n13] / (n11 + 1);
                    }
                    int n31;
                    if (k >= 0 && k < n2 && (i >= 0 & i < n)) {
                        n31 = (array[k * n + i] & 0xFFFFFF);
                    }
                    else {
                        n31 = n12;
                    }
                    array2[n25 * n7 + n24] = (n26 * 255 / n15 << 24 | n31);
                }
            }
        }
    }
    
    private void splitPixel(final int n, final int[] array) {
        array[0] = (n >> 24 & 0xFF);
        array[1] = (n >> 16 & 0xFF);
        array[2] = (n >> 8 & 0xFF);
        array[3] = (n & 0xFF);
    }
    
    private void copyPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n11 = n3 - n9;
        final int n12 = n4 - n10;
        int i = effectiveRect.y * n7;
        int n13 = (effectiveRect.y + n12) * n;
        final int n14 = effectiveRect.x + effectiveRect.width;
        while (i < (effectiveRect.y + effectiveRect.height) * n7) {
            for (int j = effectiveRect.x; j < n14; ++j) {
                array2[i + j] = array[n13 + j + n11];
            }
            i += n7;
            n13 += n;
        }
    }
    
    private void overlayPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final double n11) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n12 = n9 - n3;
        final int n13 = n10 - n4;
        final int n14 = effectiveRect.x + effectiveRect.width;
        final int n15 = effectiveRect.y + effectiveRect.height;
        final int n16 = (int)(this.makeUnity(n11) * 255.0);
        final int[] array3 = new int[4];
        final int[] array4 = new int[4];
        for (int i = effectiveRect.y; i < n15; ++i) {
            for (int j = effectiveRect.x; j < n14; ++j) {
                final int n17 = i * n7 + j;
                final int n18 = array2[n17];
                final int n19 = array[(i - n13) * n + (j - n12)];
                this.splitPixel(n18, array4);
                this.splitPixel(n19, array3);
                if (array3[0] == 0) {
                    array2[n17] = n18;
                }
                else {
                    array3[0] = array3[0] * n16 / 255;
                    final int n20 = (255 - array3[0]) * array4[0] / 255;
                    array4[0] = n20 + array3[0];
                    if (array4[0] != 0) {
                        array4[1] = (n20 * array4[1] + array3[0] * array3[1]) / array4[0];
                        array4[2] = (n20 * array4[2] + array3[0] * array3[2]) / array4[0];
                        array4[3] = (n20 * array4[3] + array3[0] * array3[3]) / array4[0];
                        array2[n17] = (array4[0] << 24 | array4[1] << 16 | array4[2] << 8 | array4[3]);
                    }
                }
            }
        }
    }
    
    private void overlayShadowPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final double n11) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n12 = n9 - n3;
        final int n13 = n10 - n4;
        final int n14 = effectiveRect.x + effectiveRect.width;
        final int n15 = effectiveRect.y + effectiveRect.height;
        final int n16 = (int)(this.makeUnity(n11) * 255.0);
        final int[] array3 = new int[4];
        for (int i = effectiveRect.y; i < n15; ++i) {
            for (int j = effectiveRect.x; j < n14; ++j) {
                final int n17 = i * n7 + j;
                final int n18 = 255 - (array[(i - n13) * n + (j - n12)] >> 24 & 0xFF) * n16 / 255;
                this.splitPixel(array2[n17], array3);
                array3[1] = array3[1] * n18 / 255;
                array3[2] = array3[2] * n18 / 255;
                array3[3] = array3[3] * n18 / 255;
                array2[n17] = (array3[0] << 24 | array3[1] << 16 | array3[2] << 8 | array3[3]);
            }
        }
    }
    
    private int[] getPixels(final Image image, final int n, final int n2) {
        int[] array = new int[n * n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            array = null;
        }
        return array;
    }
    
    public void setFaceColor(final Color faceColor, double unity) {
        unity = this.makeUnity(unity);
        if (faceColor != this.faceColor || unity != this.faceColorIntensity) {
            this.faceColor = faceColor;
            this.faceColorIntensity = unity;
            this.pixelsComplete = false;
        }
    }
    
    public void setLabelColor(final Color labelColor, double unity) {
        unity = this.makeUnity(unity);
        if (labelColor != this.labelColor || unity != this.labelColorIntensity) {
            this.labelColor = labelColor;
            this.labelColorIntensity = unity;
            this.pixelsComplete = false;
        }
    }
    
    public void setShadow(int abs, final int shadowDisp, double unity) {
        unity = this.makeUnity(unity);
        abs = Math.abs(abs);
        if (abs != this.shadowRadius || shadowDisp != this.shadowDisp || unity != this.shadowAmount) {
            this.pixelsComplete = false;
        }
        this.shadowDisp = shadowDisp;
        this.shadowAmount = unity;
        if (abs != this.shadowRadius) {
            this.shadowRadius = abs;
            if (this.facePixels != null) {
                this.updateFaceShadow();
            }
            if (this.labelPixels != null) {
                this.updateLabelShadow();
            }
        }
    }
    
    private void updateFaceShadow() {
        if (this.shadowAmount != 0.0) {
            this.faceShadowWidth = this.faceWidth + 2 * this.shadowRadius;
            this.faceShadowHeight = this.faceHeight + 2 * this.shadowRadius;
            final int n = this.faceShadowWidth * this.faceShadowHeight;
            this.faceShadowPixels = new int[n];
            this.featherMask(this.facePixels, this.faceWidth, this.faceHeight, 0, 0, this.faceWidth, this.faceHeight, this.faceShadowPixels, this.faceShadowWidth, this.faceShadowHeight, this.shadowRadius, this.shadowRadius, this.shadowRadius, 0);
            for (int i = 0; i < n; i += this.faceShadowWidth) {
                for (int j = 0; j < this.faceShadowWidth; ++j) {
                    final int[] faceShadowPixels = this.faceShadowPixels;
                    final int n2 = j + i;
                    faceShadowPixels[n2] &= 0xFF000000;
                }
            }
        }
        else {
            this.faceShadowWidth = 0;
            this.faceShadowHeight = 0;
            this.faceShadowPixels = null;
        }
    }
    
    public Object clone() {
        final ButtonImageSource buttonImageSource = new ButtonImageSource();
        buttonImageSource.faceOpacity = this.faceOpacity;
        buttonImageSource.labelOpacity = this.labelOpacity;
        buttonImageSource.faceColorIntensity = this.faceColorIntensity;
        buttonImageSource.labelColorIntensity = this.labelColorIntensity;
        buttonImageSource.shadowAmount = this.shadowAmount;
        buttonImageSource.faceWidth = this.faceWidth;
        buttonImageSource.faceHeight = this.faceHeight;
        buttonImageSource.labelWidth = this.labelWidth;
        buttonImageSource.labelHeight = this.labelHeight;
        buttonImageSource.faceShadowWidth = this.faceShadowWidth;
        buttonImageSource.faceShadowHeight = this.faceShadowHeight;
        buttonImageSource.labelShadowWidth = this.labelShadowWidth;
        buttonImageSource.labelShadowHeight = this.labelShadowHeight;
        buttonImageSource.angle = this.angle;
        buttonImageSource.faceSmooth = this.faceSmooth;
        buttonImageSource.labelSmooth = this.labelSmooth;
        buttonImageSource.shadowRadius = this.shadowRadius;
        buttonImageSource.shadowDisp = this.shadowDisp;
        buttonImageSource.labelDx = this.labelDx;
        buttonImageSource.labelDy = this.labelDy;
        buttonImageSource.labelAlign = this.labelAlign;
        buttonImageSource.pixelsComplete = this.pixelsComplete;
        buttonImageSource.consumers = (Vector)this.consumers.clone();
        buttonImageSource.faceColor = ((this.faceColor == null) ? null : new Color(this.faceColor.getRGB()));
        buttonImageSource.labelColor = ((this.labelColor == null) ? null : new Color(this.labelColor.getRGB()));
        if (this.facePixels != null) {
            buttonImageSource.facePixels = new int[this.faceWidth * this.faceHeight];
            this.copyPixels(this.facePixels, this.faceWidth, this.faceHeight, 0, 0, this.faceWidth, this.faceHeight, buttonImageSource.facePixels, this.faceWidth, this.faceHeight, 0, 0);
        }
        if (this.labelPixels != null) {
            buttonImageSource.labelPixels = new int[this.labelWidth * this.labelHeight];
            this.copyPixels(this.labelPixels, this.labelWidth, this.labelHeight, 0, 0, this.labelWidth, this.labelHeight, buttonImageSource.labelPixels, this.labelWidth, this.labelHeight, 0, 0);
        }
        if (this.faceShadowPixels != null) {
            buttonImageSource.faceShadowPixels = new int[this.faceShadowWidth * this.faceShadowHeight];
            this.copyPixels(this.faceShadowPixels, this.faceShadowWidth, this.faceShadowHeight, 0, 0, this.faceShadowWidth, this.faceShadowHeight, buttonImageSource.faceShadowPixels, this.faceShadowWidth, this.faceShadowHeight, 0, 0);
        }
        if (this.labelShadowPixels != null) {
            buttonImageSource.labelShadowPixels = new int[this.labelShadowWidth * this.labelShadowHeight];
            this.copyPixels(this.labelShadowPixels, this.labelShadowWidth, this.labelShadowHeight, 0, 0, this.labelShadowWidth, this.labelShadowHeight, buttonImageSource.labelShadowPixels, this.labelShadowWidth, this.labelShadowHeight, 0, 0);
        }
        return buttonImageSource;
    }
    
    public void setFaceOpacity(double unity) {
        unity = this.makeUnity(unity);
        if (unity != this.faceOpacity) {
            this.faceOpacity = unity;
            this.pixelsComplete = false;
        }
    }
    
    public void setLabelOpacity(double unity) {
        unity = this.makeUnity(unity);
        if (unity != this.labelOpacity) {
            this.labelOpacity = unity;
            this.pixelsComplete = false;
        }
    }
    
    private void updateLabelShadow() {
        if (this.shadowAmount != 0.0) {
            this.labelShadowWidth = this.labelWidth + 2 * this.shadowRadius;
            this.labelShadowHeight = this.labelHeight + 2 * this.shadowRadius;
            final int n = this.labelShadowWidth * this.labelShadowHeight;
            this.labelShadowPixels = new int[n];
            this.featherMask(this.labelPixels, this.labelWidth, this.labelHeight, 0, 0, this.labelWidth, this.labelHeight, this.labelShadowPixels, this.labelShadowWidth, this.labelShadowHeight, this.shadowRadius, this.shadowRadius, this.shadowRadius, 0);
            for (int i = 0; i < n; i += this.labelShadowWidth) {
                for (int j = 0; j < this.labelShadowWidth; ++j) {
                    final int[] labelShadowPixels = this.labelShadowPixels;
                    final int n2 = j + i;
                    labelShadowPixels[n2] &= 0xFF000000;
                }
            }
        }
        else {
            this.labelShadowWidth = 0;
            this.labelShadowHeight = 0;
            this.labelShadowPixels = null;
        }
    }
    
    private void trimMask(final int[] array, final int n, final int n2) {
        for (int n3 = n * n2, i = 0; i < n3; ++i) {
            final int n4 = array[i];
            final int n5 = n4 >> 24 & 0xFF;
            array[i] = (n5 * n5 / 255 << 24 | (n4 & 0xFFFFFF));
        }
    }
    
    public void setLabel(final int[] labelPixels, final int labelWidth, final int labelHeight, final int n, final boolean b) {
        if (labelPixels != null) {
            this.labelWidth = labelWidth;
            this.labelHeight = labelHeight;
            this.labelPixels = labelPixels;
            if (b) {
                this.softenMask(this.labelPixels, this.labelWidth, this.labelHeight, n);
            }
            else {
                this.sharpenMask(this.labelPixels, this.labelWidth, this.labelHeight, n);
            }
            this.updateLabelShadow();
            this.pixelsComplete = false;
        }
    }
    
    public void setLabel(final Image image, final int n, final boolean b) {
        if (image != null) {
            this.labelWidth = image.getWidth(null);
            this.labelHeight = image.getHeight(null);
            this.labelPixels = this.getPixels(image, this.labelWidth, this.labelHeight);
            if (b) {
                this.softenMask(this.labelPixels, this.labelWidth, this.labelHeight, n);
            }
            else {
                this.sharpenMask(this.labelPixels, this.labelWidth, this.labelHeight, n);
            }
            this.updateLabelShadow();
            this.pixelsComplete = false;
        }
    }
    
    public int[] getSource(final Hashtable hashtable) {
        final int n = ((this.labelPixels != null) ? this.labelDx : 0) + ((this.labelAlign == 0) ? ((this.labelWidth - this.faceWidth) / 2) : ((this.labelAlign == 2) ? ((this.faceWidth - this.labelWidth) / 2) : 0));
        final int n2 = (this.labelPixels != null) ? this.labelDy : 0;
        int[] coloredPixels = null;
        int[] coloredPixels2 = null;
        if (!this.pixelsComplete) {
            final int n3 = -this.shadowRadius - (int)(this.shadowDisp * Math.cos(0.017453292519943295 * this.angle));
            final int n4 = -this.shadowRadius + (int)(this.shadowDisp * Math.sin(0.017453292519943295 * this.angle));
            final Rectangle rectangle = new Rectangle(-this.faceWidth / 2, -this.faceHeight / 2, this.faceWidth, this.faceHeight);
            final Rectangle rectangle2 = new Rectangle(n - this.labelWidth / 2, n2 - this.labelHeight / 2, this.labelWidth, this.labelHeight);
            final Rectangle union = rectangle.union(rectangle2);
            union.add(new Rectangle(union.x + n3, union.y + n4, union.width + 2 * this.shadowRadius, union.height + 2 * this.shadowRadius));
            rectangle.translate(-union.x, -union.y);
            rectangle2.translate(-union.x, -union.y);
            this.pixelsWidth = union.width;
            this.pixelsHeight = union.height;
            final int n5 = this.pixelsWidth * this.pixelsHeight;
            this.pixels = new int[n5];
            for (int i = 0; i < n5; ++i) {
                this.pixels[i] = 0;
            }
            if (this.faceColor != null && this.facePixels != null) {
                coloredPixels = this.createColoredPixels(this.facePixels, this.faceWidth, this.faceHeight, this.faceColor.getRGB(), this.faceColorIntensity);
            }
            if (this.labelColor != null && this.labelPixels != null) {
                coloredPixels2 = this.createColoredPixels(this.labelPixels, this.labelWidth, this.labelHeight, this.labelColor.getRGB(), this.labelColorIntensity);
            }
            if (this.faceShadowPixels != null) {
                this.overlayPixels(this.faceShadowPixels, this.faceShadowWidth, this.faceShadowHeight, 0, 0, this.faceShadowWidth, this.faceShadowHeight, this.pixels, this.pixelsWidth, this.pixelsHeight, rectangle.x + n3, rectangle.y + n4, this.faceOpacity);
            }
            if (this.labelShadowPixels != null) {
                this.overlayPixels(this.labelShadowPixels, this.labelShadowWidth, this.labelShadowHeight, 0, 0, this.labelShadowWidth, this.labelShadowHeight, this.pixels, this.pixelsWidth, this.pixelsHeight, rectangle2.x + n3, rectangle2.y + n4, this.labelOpacity);
                if (this.facePixels != null) {
                    if (coloredPixels == null) {
                        final int n6 = this.faceWidth * this.faceHeight;
                        coloredPixels = new int[n6];
                        for (int j = 0; j < n6; ++j) {
                            coloredPixels[j] = this.facePixels[j];
                        }
                    }
                    this.overlayShadowPixels(this.labelShadowPixels, this.labelShadowWidth, this.labelShadowHeight, 0, 0, this.labelShadowWidth, this.labelShadowHeight, coloredPixels, this.faceWidth, this.faceHeight, (this.faceWidth - this.labelWidth) / 2 + (-this.shadowRadius - (int)(4.0 * Math.cos(0.017453292519943295 * this.angle))) + n, (this.faceHeight - this.labelHeight) / 2 + (-this.shadowRadius + (int)(4.0 * Math.sin(0.017453292519943295 * this.angle))) + n2, 0.2 + 0.3 * this.labelOpacity);
                }
            }
            if (this.faceShadowPixels != null || this.labelShadowPixels != null) {
                final int n7 = this.pixelsWidth * this.pixelsHeight;
                final int n8 = (int)(this.shadowAmount * 255.0);
                for (int k = 0; k < n7; ++k) {
                    final int n9 = this.pixels[k];
                    this.pixels[k] = ((n9 >> 24 & 0xFF) * n8 / 255 << 24 | (n9 & 0xFFFFFF));
                }
            }
            if (this.facePixels != null) {
                this.overlayPixels((coloredPixels != null) ? coloredPixels : this.facePixels, this.faceWidth, this.faceHeight, 0, 0, this.faceWidth, this.faceHeight, this.pixels, this.pixelsWidth, this.pixelsHeight, rectangle.x, rectangle.y, this.faceOpacity);
            }
            if (this.labelPixels != null) {
                this.overlayPixels((coloredPixels2 != null) ? coloredPixels2 : this.labelPixels, this.labelWidth, this.labelHeight, 0, 0, this.labelWidth, this.labelHeight, this.pixels, this.pixelsWidth, this.pixelsHeight, rectangle2.x, rectangle2.y, this.labelOpacity);
            }
            this.activeBounds = rectangle.union(rectangle2);
            this.faceCenter = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
            this.pixelsComplete = true;
        }
        hashtable.put("SIZE", new Dimension(this.pixelsWidth, this.pixelsHeight));
        hashtable.put("ACTIVE_BOUNDS", this.activeBounds);
        hashtable.put("FACE_CENTER", this.faceCenter);
        return this.pixels;
    }
}
