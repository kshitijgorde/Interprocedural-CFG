import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupImageFilter extends RGBImageFilter
{
    private float bright;
    private double brightd;
    private boolean applyImageLevelFilters;
    private int imageBrightness;
    private int viewerBrightness;
    private float platformGamma;
    private float imageGamma;
    private float viewerGamma;
    private double viewerGammad;
    private float imageContrast;
    private float viewerContrast;
    private double viewerContrastd;
    private float imageSaturation;
    private float viewerSaturation;
    private double viewerSaturationd;
    private float imageRedGamma;
    private float imageGreenGamma;
    private float imageBlueGamma;
    private float viewerRedGamma;
    private double viewerRedGammad;
    private float viewerGreenGamma;
    private double viewerGreenGammad;
    private float viewerBlueGamma;
    private double viewerBlueGammad;
    private int redChannel;
    private int greenChannel;
    private int blueChannel;
    private float alpha;
    private double alphad;
    private int alphaColor;
    private int alphaRed;
    private int alphaGreen;
    private int alphaBlue;
    private int posterSteps;
    private float sharpness;
    private double sharpnessd;
    private int sharpnessThreshold;
    private float stepSize;
    private boolean blackAndWhite;
    private boolean negative;
    private boolean titlebar;
    private int titlebarHeight;
    private int origWidth;
    private int origHeight;
    private int cellWidth;
    private int cellHeight;
    private int matrixView;
    private boolean fakeSky;
    private int fakeSkyTolerance;
    private int fakeSkyMin;
    private float fakeSkyFade;
    private int fakeSkyRed;
    private int fakeSkyGreen;
    private int fakeSkyBlue;
    long[] histogram;
    private boolean createHistogram;
    private boolean haveHistogram;
    private boolean mirror;
    private boolean flip;
    private int rotate;
    private int[][] matrix;
    private int matrixSum;
    private int convMatrixOffset;
    private int threshold;
    private int noiseLevel;
    private int noiseFrequency;
    
    public int getThreshold() {
        return this.threshold;
    }
    
    public void setThreshold(final int threshold) {
        this.threshold = threshold;
    }
    
    public void setImageContrast(final double n) {
        this.imageContrast = this.roundHundreds(n);
    }
    
    public double getImageContrast() {
        return this.imageContrast;
    }
    
    public void setRotate(int rotate) {
        if (rotate != 0 && rotate != 90 && rotate != 180 && rotate != 270) {
            rotate = 0;
        }
        this.rotate = rotate;
    }
    
    public int getRotate() {
        return this.rotate;
    }
    
    public int getGreenChannel() {
        return this.greenChannel;
    }
    
    public int getFilterWidth() {
        if (this.rotate == 90 || this.rotate == 270) {
            return this.origHeight;
        }
        return this.origWidth;
    }
    
    public void setTitlebar(final boolean titlebar) {
        this.titlebar = titlebar;
    }
    
    public boolean getTitlebar() {
        return this.titlebar;
    }
    
    public void setImageRGBGamma(final float imageRedGamma, final float imageGreenGamma, final float imageBlueGamma) {
        this.imageRedGamma = imageRedGamma;
        this.imageGreenGamma = imageGreenGamma;
        this.imageBlueGamma = imageBlueGamma;
    }
    
    public float getImageGreenGamma() {
        return this.imageGreenGamma;
    }
    
    public void setPlatformGamma(final double n) {
        this.platformGamma = this.roundHundreds(n);
    }
    
    public void setViewerGamma(final double viewerGammad) {
        this.viewerGammad = viewerGammad;
        this.viewerGamma = this.roundHundreds(viewerGammad);
    }
    
    public double getViewerGamma() {
        return this.viewerGammad;
    }
    
    public int getCellHeight() {
        return this.cellHeight;
    }
    
    public void setWidthHeight(final int origWidth, final int origHeight) {
        this.origWidth = origWidth;
        this.origHeight = origHeight;
        this.cellWidth = (this.origWidth + 2) / 3;
        this.cellHeight = (this.origHeight + 2) / 3;
    }
    
    public boolean getFakeSky() {
        return this.fakeSky;
    }
    
    public void setFakeSky(final boolean fakeSky) {
        this.fakeSky = fakeSky;
    }
    
    public void setStepSize(final float stepSize) {
        this.stepSize = stepSize;
    }
    
    public void setBright(final double brightd) {
        this.brightd = brightd;
        this.bright = this.roundHundreds(brightd);
    }
    
    public double getBright() {
        return this.brightd;
    }
    
    public void setImageRed(final double n) {
        this.imageRedGamma = this.roundHundreds(n);
    }
    
    public double getImageRed() {
        return this.imageRedGamma;
    }
    
    public boolean getApplyImageLevelFilters() {
        return this.applyImageLevelFilters;
    }
    
    public void setApplyImageLevelFilters(final boolean applyImageLevelFilters) {
        this.applyImageLevelFilters = applyImageLevelFilters;
    }
    
    public void setImageBlue(final double n) {
        this.imageBlueGamma = this.roundHundreds(n);
    }
    
    public double getImageBlue() {
        return this.imageBlueGamma;
    }
    
    private int restrictedInt(int n) {
        if (n > 0) {
            n = 1;
        }
        else if (n < 0) {
            n = -1;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    public void setBlackAndWhite(final boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }
    
    public boolean getBlackAndWhite() {
        return this.blackAndWhite;
    }
    
    public void filterImageOrig(final int[] array, final int[] array2, final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array2[n3] = this.filterRGB(i, j, array[n3]);
                ++n3;
            }
        }
    }
    
    public void setNegative(final boolean negative) {
        this.negative = negative;
    }
    
    public boolean getNegative() {
        return this.negative;
    }
    
    private float roundHundreds(final double n) {
        return (int)(100.0 * n) / 100.0f;
    }
    
    private int intToByte(int n) {
        if (n < 0) {
            n = 0;
        }
        else if (n > 255) {
            n = 255;
        }
        return n;
    }
    
    public void setFlip(final boolean flip) {
        this.flip = flip;
    }
    
    public boolean getFlip() {
        return this.flip;
    }
    
    public void setMatrixView(final int matrixView) {
        this.matrixView = matrixView;
    }
    
    public int getMatrixView() {
        return this.matrixView;
    }
    
    public void setRGBChannels(final int n, final int n2, final int n3) {
        this.redChannel = this.restrictedInt(n);
        this.greenChannel = this.restrictedInt(n2);
        this.blueChannel = this.restrictedInt(n3);
    }
    
    public void setMirror(final boolean mirror) {
        this.mirror = mirror;
    }
    
    public boolean getMirror() {
        return this.mirror;
    }
    
    public int getCellWidth() {
        return this.cellWidth;
    }
    
    public double getViewerSaturation() {
        return this.viewerSaturationd;
    }
    
    public void setViewerSaturation(final double viewerSaturationd) {
        this.viewerSaturationd = viewerSaturationd;
        this.viewerSaturation = this.roundHundreds(viewerSaturationd);
    }
    
    public void setImageGamma(final double n) {
        this.imageGamma = this.roundHundreds(n);
    }
    
    public double getImageSaturation() {
        return this.imageSaturation;
    }
    
    public double getImageGamma() {
        return this.imageGamma;
    }
    
    public void setImageSaturation(final double n) {
        this.imageSaturation = this.roundHundreds(n);
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return 0;
    }
    
    public void setViewerGreen(final double viewerGreenGammad) {
        this.viewerGreenGammad = viewerGreenGammad;
        this.viewerGreenGamma = this.roundHundreds(viewerGreenGammad);
    }
    
    public double getViewerGreen() {
        return this.viewerGreenGammad;
    }
    
    public double getplatformGamma() {
        return this.platformGamma;
    }
    
    private int applyGamma(final int n, float n2) {
        if (n2 == 0.0f) {
            n2 = 0.01f;
        }
        return (int)(255.0 * Math.pow(n / 255.0f, 1.0f / n2));
    }
    
    public int getViewerBrightness() {
        return this.viewerBrightness;
    }
    
    public void setViewerBrightness(final int viewerBrightness) {
        this.viewerBrightness = viewerBrightness;
    }
    
    public double getSharpness() {
        return this.sharpnessd;
    }
    
    public void setSharpness(final double sharpnessd) {
        this.sharpnessd = sharpnessd;
        this.sharpness = this.roundHundreds(sharpnessd);
    }
    
    public boolean getHaveHistogram() {
        return this.haveHistogram;
    }
    
    public int getPosterSteps() {
        return this.posterSteps;
    }
    
    public void setPosterSteps(final int posterSteps) {
        this.posterSteps = posterSteps;
    }
    
    public void setViewerContrast(final double viewerContrastd) {
        this.viewerContrastd = viewerContrastd;
        this.viewerContrast = this.roundHundreds(viewerContrastd);
    }
    
    public double getViewerContrast() {
        return this.viewerContrast;
    }
    
    public void filterImage(final int[] array, final int[] array2, final int origHeight, final int origWidth) {
        int viewerBrightness = this.viewerBrightness;
        float bright = this.bright;
        float viewerGamma = this.viewerGamma;
        float viewerContrast = this.viewerContrast;
        float viewerSaturation = this.viewerSaturation;
        float viewerRedGamma = this.viewerRedGamma;
        float viewerGreenGamma = this.viewerGreenGamma;
        float viewerBlueGamma = this.viewerBlueGamma;
        final float platformGamma = this.platformGamma;
        float n = Math.abs(this.redChannel) + Math.abs(this.greenChannel) + Math.abs(this.blueChannel);
        if (this.applyImageLevelFilters) {
            viewerBrightness += this.imageBrightness;
            viewerGamma *= this.imageGamma;
            viewerContrast *= this.imageContrast;
            viewerSaturation *= this.imageSaturation;
            viewerRedGamma *= this.imageRedGamma;
            viewerGreenGamma *= this.imageGreenGamma;
            viewerBlueGamma *= this.imageBlueGamma;
        }
        final boolean b = viewerSaturation != 1.0f;
        final float n2 = 1.0f - this.alpha;
        final int[] array3 = new int[256];
        final int[] array4 = new int[256];
        final int[] array5 = new int[256];
        final int[] array6 = new int[256];
        final int[] array7 = new int[256];
        final int[] array8 = new int[256];
        final int[] array9 = new int[256];
        final int[] array10 = new int[256];
        final int[] array11 = new int[256];
        final int[] array12 = new int[256];
        this.origWidth = origWidth;
        this.origHeight = origHeight;
        this.haveHistogram = true;
        int n3 = 0;
        do {
            this.histogram[n3] = 0L;
        } while (++n3 < 768);
        if (viewerGamma == 0.0f) {
            viewerGamma = 0.01f;
        }
        if (viewerRedGamma == 0.0f) {
            viewerRedGamma = 0.01f;
        }
        if (viewerGreenGamma == 0.0f) {
            viewerGreenGamma = 0.01f;
        }
        if (viewerBlueGamma == 0.0f) {
            viewerBlueGamma = 0.01f;
        }
        if (this.negative && bright != 0.0f) {
            bright = 1.0f / bright;
        }
        if (bright == 0.0f) {
            bright = 0.01f;
        }
        if (n == 0.0f) {
            n = 3.0f;
        }
        int n4;
        if (this.posterSteps == 0) {
            n4 = 0;
        }
        else {
            n4 = 256 / this.posterSteps;
            if (256 % this.posterSteps > 0) {
                ++n4;
            }
        }
        final float n5 = bright * platformGamma * viewerGamma;
        final float n6 = viewerRedGamma * n5;
        final float n7 = viewerGreenGamma * n5;
        final float n8 = viewerBlueGamma * n5;
        final float n9 = this.alpha * this.alphaRed;
        final float n10 = this.alpha * this.alphaGreen;
        final float n11 = this.alpha * this.alphaBlue;
        int n12 = 0;
        do {
            int intToByte = n12;
            if (this.posterSteps > 0 && this.posterSteps < 256) {
                intToByte = this.intToByte(intToByte - intToByte % n4 + n4 / 2);
            }
            int n13 = intToByte;
            int n14 = intToByte;
            int n15 = intToByte;
            if (this.negative) {
                n13 = 255 - n13;
                n14 = 255 - n14;
                n15 = 255 - n15;
            }
            final int applyGamma = this.applyGamma(n13, n6);
            final int applyGamma2 = this.applyGamma(n14, n7);
            final int applyGamma3 = this.applyGamma(n15, n8);
            final int n16 = 127 - (int)(viewerContrast * (127 - applyGamma));
            final int n17 = 127 - (int)(viewerContrast * (127 - applyGamma2));
            final int n18 = 127 - (int)(viewerContrast * (127 - applyGamma3));
            final int intToByte2 = this.intToByte(n16 + viewerBrightness);
            final int intToByte3 = this.intToByte(n17 + viewerBrightness);
            final int intToByte4 = this.intToByte(n18 + viewerBrightness);
            int applyScale = this.applyScale(this.intToByte(intToByte2), this.redChannel);
            int applyScale2 = this.applyScale(this.intToByte(intToByte3), this.greenChannel);
            int applyScale3 = this.applyScale(this.intToByte(intToByte4), this.blueChannel);
            if (!b && !this.blackAndWhite) {
                applyScale = (int)(n2 * applyScale + this.alpha * this.alphaRed);
                applyScale2 = (int)(n2 * applyScale2 + this.alpha * this.alphaGreen);
                applyScale3 = (int)(n2 * applyScale3 + this.alpha * this.alphaBlue);
            }
            array3[n12] = this.intToByte(applyScale);
            array4[n12] = this.intToByte(applyScale2);
            array5[n12] = this.intToByte(applyScale3);
            array10[n12] = this.intToByte(array3[n12] + this.noiseLevel);
            array11[n12] = this.intToByte(array4[n12] + this.noiseLevel);
            array12[n12] = this.intToByte(array5[n12] + this.noiseLevel);
            array6[n12] = this.intToByte((int)(n2 * intToByte + n9));
            array7[n12] = this.intToByte((int)(n2 * intToByte + n10));
            array8[n12] = this.intToByte((int)(n2 * intToByte + n11));
            if (this.threshold != -1) {
                array3[n12] = this.applyThreshold(array3[n12]);
                array4[n12] = this.applyThreshold(array4[n12]);
                array5[n12] = this.applyThreshold(array5[n12]);
            }
        } while (++n12 < 256);
        int n19 = 0;
        if (this.noiseLevel == 0 && this.noiseFrequency > 0) {
            this.noiseLevel = -30;
        }
        final int n20 = this.noiseFrequency + 3;
        final int n21 = 1 + (n20 - 1) / 2 / 4;
        final int n22 = 1 + (n20 - 1) / 2 % 4;
        int n23 = n21;
        int n24 = n22;
        int n25 = 0;
        int n26 = n21 / 2;
        if (n20 % 2 == 0) {
            n26 = 0;
        }
        for (int i = 0; i < origHeight; ++i) {
            boolean b2 = false;
            if (this.noiseFrequency > 0 && --n24 <= 0) {
                b2 = true;
                n24 = n22;
                n23 = n25 + 1;
                n25 = n26 - n25;
            }
            if (this.createHistogram || b || b2) {
                for (int j = 0; j < origWidth; ++j) {
                    final int n27 = array[n19];
                    --n23;
                    int n28;
                    int n29;
                    int n30;
                    if (b2 && n23 == 0) {
                        n23 = n21;
                        n28 = array10[(0xFF0000 & n27) >> 16];
                        n29 = array11[(0xFF00 & n27) >> 8];
                        n30 = array12[0xFF & n27];
                    }
                    else {
                        n28 = array3[(0xFF0000 & n27) >> 16];
                        n29 = array4[(0xFF00 & n27) >> 8];
                        n30 = array5[0xFF & n27];
                    }
                    if (this.blackAndWhite || b) {
                        if (this.blackAndWhite) {
                            int applyThreshold = (int)((299L * n28 + 587L * n29 + 114L * n30) / 333L / n);
                            if (this.threshold != -1) {
                                applyThreshold = this.applyThreshold(applyThreshold);
                            }
                            n28 = applyThreshold;
                            n29 = applyThreshold;
                            n30 = applyThreshold;
                        }
                        else {
                            final int n31 = (int)((299L * n28 + 587L * n29 + 114L * n30) / 333L / 3L);
                            n28 = n31 - (int)(viewerSaturation * (n31 - n28));
                            n29 = n31 - (int)(viewerSaturation * (n31 - n29));
                            n30 = n31 - (int)(viewerSaturation * (n31 - n30));
                            if (n28 < 0) {
                                n28 = 0;
                            }
                            else if (n28 > 255) {
                                n28 = 255;
                            }
                            if (n29 < 0) {
                                n29 = 0;
                            }
                            else if (n29 > 255) {
                                n29 = 255;
                            }
                            if (n30 < 0) {
                                n30 = 0;
                            }
                            else if (n30 > 255) {
                                n30 = 255;
                            }
                        }
                    }
                    final int n32 = array6[n28];
                    final int n33 = array7[n29];
                    final int n34 = array8[n30];
                    final long[] histogram = this.histogram;
                    final int n35 = n32;
                    ++histogram[n35];
                    final long[] histogram2 = this.histogram;
                    final int n36 = 256 + n33;
                    ++histogram2[n36];
                    final long[] histogram3 = this.histogram;
                    final int n37 = 512 + n34;
                    ++histogram3[n37];
                    array2[n19] = -16777216 + (n32 << 16) + (n33 << 8) + n34;
                    ++n19;
                }
            }
            else if (this.blackAndWhite) {
                this.haveHistogram = false;
                for (int k = 0; k < origWidth; ++k) {
                    final int n38 = array[n19];
                    int applyThreshold2 = (int)((299L * array3[(0xFF0000 & n38) >> 16] + 587L * array4[(0xFF00 & n38) >> 8] + 114L * array5[0xFF & n38]) / 333L / n);
                    if (this.threshold != -1) {
                        applyThreshold2 = this.applyThreshold(applyThreshold2);
                    }
                    array2[n19] = -16777216 + (array6[applyThreshold2] << 16) + (array7[applyThreshold2] << 8) + array8[applyThreshold2];
                    ++n19;
                }
            }
            else {
                this.haveHistogram = false;
                for (int l = 0; l < origWidth; ++l) {
                    final int n39 = array[n19];
                    array2[n19] = -16777216 + (array3[(0xFF0000 & n39) >> 16] << 16) + (array4[(0xFF00 & n39) >> 8] << 8) + array5[0xFF & n39];
                    ++n19;
                }
            }
        }
        if (this.mirror) {
            for (int n40 = 0; n40 < origHeight; ++n40) {
                int n43;
                for (int n41 = n40 * origWidth, n42 = n41 + origWidth - 1; n41 < n41 + origWidth / 2; array2[n41++] = array2[n42], array2[n42--] = n43) {
                    n43 = array2[n41];
                }
            }
        }
        if (this.flip) {
            final int n44 = origWidth * origHeight;
            int n45 = 0;
            for (int n46 = 0; n46 < origHeight / 2; ++n46) {
                int n47 = n44 - (n46 + 1) * origWidth;
                while (n45 < n45 + origWidth) {
                    final int n48 = array2[n47];
                    array2[n47++] = array2[n45];
                    array2[n45++] = n48;
                }
            }
        }
        if (this.rotate != 0) {
            final int n49 = origWidth * origHeight;
            switch (this.rotate) {
                case 90: {
                    final int[] array13 = new int[n49];
                    for (int n50 = 0; n50 < n49; ++n50) {
                        array13[n50] = array2[n50];
                    }
                    int n51 = 0;
                    for (int n52 = 0; n52 < origWidth; ++n52) {
                        for (int n53 = n49 - origWidth + n52, n54 = 0; n54 < origHeight; ++n54, ++n51, n53 -= origWidth) {
                            array2[n51] = array13[n53];
                        }
                    }
                }
                case 270: {
                    final int[] array14 = new int[n49];
                    for (int n55 = 0; n55 < n49; ++n55) {
                        array14[n55] = array2[n55];
                    }
                    int n56 = 0;
                    for (int n57 = origWidth - 1; n57 >= 0; --n57) {
                        for (int n58 = n57, n59 = 0; n59 < origHeight; ++n59, n58 += origWidth) {
                            array2[n56++] = array14[n58];
                        }
                    }
                }
                case 180: {
                    int n60 = 0;
                    int n61 = n49 - 1;
                    for (int n62 = 0; n62 < origHeight / 2; ++n62) {
                        for (int n63 = 0; n63 < origWidth; ++n63, ++n60, --n61) {
                            final int n64 = array2[n61];
                            array2[n61] = array2[n60];
                            array2[n60] = n64;
                        }
                    }
                    while (n60 < n61) {
                        final int n65 = array2[n61];
                        array2[n61] = array2[n60];
                        array2[n60] = n65;
                        ++n60;
                        --n61;
                    }
                }
            }
        }
    }
    
    public int getRedChannel() {
        return this.redChannel;
    }
    
    public int getBlueChannel() {
        return this.blueChannel;
    }
    
    private int applyThreshold(final int n) {
        if (n >= this.threshold) {
            return 255;
        }
        return 0;
    }
    
    public int getFilterHeight() {
        if (this.rotate == 90 || this.rotate == 270) {
            return this.origWidth;
        }
        return this.origHeight;
    }
    
    public void setTitlebarHeight(final int titlebarHeight) {
        this.titlebarHeight = titlebarHeight;
    }
    
    public void setImageGreen(final double n) {
        this.imageGreenGamma = this.roundHundreds(n);
    }
    
    public double getImageGreen() {
        return this.imageGreenGamma;
    }
    
    public int getAlphaColor() {
        return this.alphaColor;
    }
    
    public void setAlphaColor(final int alphaColor) {
        this.alphaColor = alphaColor;
        this.alphaRed = (0xFF0000 & alphaColor) >> 16;
        this.alphaGreen = (0xFF00 & alphaColor) >> 8;
        this.alphaBlue = (0xFF & alphaColor);
    }
    
    public TouchupImageFilter() {
        this.bright = 1.0f;
        this.brightd = 1.0;
        this.applyImageLevelFilters = true;
        this.imageBrightness = 0;
        this.viewerBrightness = 0;
        this.platformGamma = 1.0f;
        this.imageGamma = 1.0f;
        this.viewerGamma = 1.0f;
        this.viewerGammad = 1.0;
        this.imageContrast = 1.0f;
        this.viewerContrast = 1.0f;
        this.viewerContrastd = 1.0;
        this.imageSaturation = 1.0f;
        this.viewerSaturation = 1.0f;
        this.viewerSaturationd = 1.0;
        this.imageRedGamma = 1.0f;
        this.imageGreenGamma = 1.0f;
        this.imageBlueGamma = 1.0f;
        this.viewerRedGamma = 1.0f;
        this.viewerRedGammad = 1.0;
        this.viewerGreenGamma = 1.0f;
        this.viewerGreenGammad = 1.0;
        this.viewerBlueGamma = 1.0f;
        this.viewerBlueGammad = 1.0;
        this.redChannel = 1;
        this.greenChannel = 1;
        this.blueChannel = 1;
        this.alpha = 0.0f;
        this.alphad = 0.0;
        this.alphaColor = 16777215;
        this.alphaRed = 255;
        this.alphaGreen = 255;
        this.alphaBlue = 255;
        this.posterSteps = 0;
        this.sharpness = 0.0f;
        this.sharpnessd = 0.0;
        this.sharpnessThreshold = 4;
        this.stepSize = 1.1f;
        this.blackAndWhite = false;
        this.negative = false;
        this.titlebar = false;
        this.titlebarHeight = 25;
        this.fakeSky = false;
        this.fakeSkyTolerance = 35;
        this.fakeSkyMin = 200;
        this.fakeSkyFade = 0.4f;
        this.fakeSkyRed = 187;
        this.fakeSkyGreen = 238;
        this.fakeSkyBlue = 255;
        this.histogram = new long[768];
        this.createHistogram = false;
        this.haveHistogram = false;
        this.mirror = false;
        this.flip = false;
        this.rotate = 0;
        this.matrix = new int[3][3];
        this.convMatrixOffset = 0;
        this.threshold = -1;
        this.noiseLevel = -30;
        this.noiseFrequency = 0;
        super.canFilterIndexColorModel = true;
    }
    
    public int getNoiseFrequency() {
        return this.noiseFrequency;
    }
    
    public void setNoiseFrequency(final int noiseFrequency) {
        this.noiseFrequency = noiseFrequency;
    }
    
    public void setViewerRed(final double viewerRedGammad) {
        this.viewerRedGammad = viewerRedGammad;
        this.viewerRedGamma = this.roundHundreds(viewerRedGammad);
    }
    
    public int getNoiseLevel() {
        return this.noiseLevel;
    }
    
    public void setNoiseLevel(final int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }
    
    public double getViewerRed() {
        return this.viewerRedGammad;
    }
    
    public float getImageRedGamma() {
        return this.imageRedGamma;
    }
    
    public float getImageBlueGamma() {
        return this.imageBlueGamma;
    }
    
    public int getImageBrightness() {
        return this.imageBrightness;
    }
    
    public void setImageBrightness(final int imageBrightness) {
        this.imageBrightness = imageBrightness;
    }
    
    public double getAlpha() {
        return this.alphad;
    }
    
    public void setAlpha(final double alphad) {
        this.alphad = alphad;
        this.alpha = this.roundHundreds(alphad);
    }
    
    public void setViewerBlue(final double viewerBlueGammad) {
        this.viewerBlueGammad = viewerBlueGammad;
        this.viewerBlueGamma = this.roundHundreds(viewerBlueGammad);
    }
    
    public double getViewerBlue() {
        return this.viewerBlueGammad;
    }
    
    public void applySprite(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        Math.max(0, n6);
        Math.min(n6 + n5, n3);
        for (int i = n7; i < n7 + n4; ++i) {
            if (i >= 0 && i < n2) {
                for (int n8 = i * n3 + n6, n9 = (i - n7) * n5, n10 = n6, j = n5; j > 0; --j, ++n10, ++n8, ++n9) {
                    if (n10 >= 0 && n10 < n3) {
                        final int n11 = array2[n9];
                        if ((n11 & 0xFFFFFF) != n) {
                            array[n8] = n11;
                        }
                    }
                }
            }
        }
    }
    
    public void setCreateHistogram(final boolean createHistogram) {
        this.createHistogram = createHistogram;
    }
    
    public boolean getCreateHistogram() {
        return this.createHistogram;
    }
    
    private int applyScale(int n, final int n2) {
        switch (n2) {
            case 0: {
                n = 0;
                break;
            }
            case -1: {
                n = 255 - n;
                break;
            }
        }
        return n;
    }
}
