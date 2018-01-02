import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageFilter
{
    BufferedImage filteredImage;
    BufferedImage tempImage;
    
    void setSourceImage(final BufferedImage bufferedImage) {
        if (this.filteredImage == null || this.filteredImage.getWidth(null) != bufferedImage.getWidth(null) || this.filteredImage.getHeight(null) != bufferedImage.getHeight(null)) {
            if (this.filteredImage != null) {
                this.filteredImage.flush();
            }
            this.filteredImage = new BufferedImage(bufferedImage.getWidth(null), bufferedImage.getHeight(null), 2);
        }
        this.filteredImage.getRaster().setDataElements(0, 0, bufferedImage.getData());
    }
    
    BufferedImage makeOutputImage(final BufferedImage bufferedImage) {
        if (this.tempImage == null || this.tempImage.getWidth(null) != bufferedImage.getWidth(null) || this.tempImage.getHeight(null) != bufferedImage.getHeight(null)) {
            if (this.tempImage != null) {
                this.tempImage.flush();
            }
            this.tempImage = new BufferedImage(bufferedImage.getWidth(null), bufferedImage.getHeight(null), 2);
        }
        return this.tempImage;
    }
    
    void applyHueShift(final int n) {
        final BufferedImage filteredImage = this.filteredImage;
        final BufferedImage outputImage = this.makeOutputImage(filteredImage);
        final int width = filteredImage.getWidth();
        final int height = filteredImage.getHeight();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                final int rgb;
                int n2 = rgb = filteredImage.getRGB(i, j);
                final int n3 = rgb & 0xFF000000;
                if (n3 != 0) {
                    final int n4 = rgb >> 16 & 0xFF;
                    final int n5 = rgb >> 8 & 0xFF;
                    final int n6 = rgb & 0xFF;
                    int n7 = (n4 < n5) ? n4 : n5;
                    if (n6 < n7) {
                        n7 = n6;
                    }
                    int n8 = (n4 > n5) ? n4 : n5;
                    if (n6 > n8) {
                        n8 = n6;
                    }
                    int n9 = n8 * 1000 / 255;
                    if (n9 < 150) {
                        n9 = 150;
                    }
                    int n10 = (n8 == 0) ? 0 : ((n8 - n7) * 1000 / n8);
                    if (n10 < 150) {
                        n10 = 150;
                    }
                    n2 = (n3 | this.hsv2rgb(this.rgb2hue(n4, n5, n6, n7, n8) + 180 * n / 100, n10, n9));
                }
                outputImage.setRGB(i, j, n2);
            }
        }
        this.tempImage = this.filteredImage;
        this.filteredImage = outputImage;
    }
    
    void applyBrightnessShift(final int n) {
        final BufferedImage filteredImage = this.filteredImage;
        final BufferedImage outputImage = this.makeOutputImage(filteredImage);
        final int width = filteredImage.getWidth();
        final int height = filteredImage.getHeight();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                final int rgb;
                int n2 = rgb = filteredImage.getRGB(i, j);
                final int n3 = rgb & 0xFF000000;
                if (n3 != 0) {
                    final int n4 = rgb >> 16 & 0xFF;
                    final int n5 = rgb >> 8 & 0xFF;
                    final int n6 = rgb & 0xFF;
                    int n7 = (n4 < n5) ? n4 : n5;
                    if (n6 < n7) {
                        n7 = n6;
                    }
                    int n8 = (n4 > n5) ? n4 : n5;
                    if (n6 > n8) {
                        n8 = n6;
                    }
                    final int rgb2hue = this.rgb2hue(n4, n5, n6, n7, n8);
                    final int n9 = (n8 == 0) ? 0 : ((n8 - n7) * 1000 / n8);
                    int n10 = n8 * 1000 / 255 + 10 * n;
                    if (n10 > 1000) {
                        n10 = 1000;
                    }
                    if (n10 < 0) {
                        n10 = 0;
                    }
                    n2 = (n3 | this.hsv2rgb(rgb2hue, n9, n10));
                }
                outputImage.setRGB(i, j, n2);
            }
        }
        this.tempImage = this.filteredImage;
        this.filteredImage = outputImage;
    }
    
    int rgb2hue(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n5 - n4;
        if (n6 == 0) {
            return 0;
        }
        if (n == n5) {
            return 60 * (n2 - n3) / n6;
        }
        if (n2 == n5) {
            return 120 + 60 * (n3 - n) / n6;
        }
        return 240 + 60 * (n - n2) / n6;
    }
    
    int hsv2rgb(final int n, final int n2, final int n3) {
        int n4 = n % 360;
        if (n4 < 0) {
            n4 += 360;
        }
        final int n5 = n4 / 60;
        final int n6 = n4 % 60;
        final int n7 = (1000 - n2) * n3 / 3922;
        final int n8 = (1000 - n2 * n6 / 60) * n3 / 3922;
        final int n9 = (1000 - n2 * (60 - n6) / 60) * n3 / 3922;
        final int n10 = n3 * 1000 / 3922;
        switch (n5) {
            case 0: {
                return n10 << 16 | n9 << 8 | n7;
            }
            case 1: {
                return n8 << 16 | n10 << 8 | n7;
            }
            case 2: {
                return n7 << 16 | n10 << 8 | n9;
            }
            case 3: {
                return n7 << 16 | n8 << 8 | n10;
            }
            case 4: {
                return n9 << 16 | n7 << 8 | n10;
            }
            case 5: {
                return n10 << 16 | n7 << 8 | n8;
            }
            default: {
                return 0;
            }
        }
    }
    
    void applyFisheye(final double n) {
        final BufferedImage filteredImage = this.filteredImage;
        final BufferedImage outputImage = this.makeOutputImage(filteredImage);
        final int width = filteredImage.getWidth();
        final int height = filteredImage.getHeight();
        final double n2 = width / 2;
        final double n3 = height / 2;
        final double n4 = (n + 100.0) / 100.0;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                final double n5 = (i - n2) / n2;
                final double n6 = (j - n3) / n3;
                final double pow = Math.pow(Math.sqrt(n5 * n5 + n6 * n6), n4);
                double n7;
                double n8;
                if (pow <= 1.0) {
                    final double atan2 = Math.atan2(n6, n5);
                    n7 = n2 + pow * Math.cos(atan2) * n2;
                    n8 = n3 + pow * Math.sin(atan2) * n3;
                }
                else {
                    n7 = i;
                    n8 = j;
                }
                outputImage.setRGB(i, j, this.interpolate(filteredImage, n7, n8));
            }
        }
        this.tempImage = this.filteredImage;
        this.filteredImage = outputImage;
    }
    
    int interpolate(final BufferedImage bufferedImage, final double n, final double n2) {
        int n3 = (int)Math.round(n);
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 >= bufferedImage.getWidth(null)) {
            n3 = bufferedImage.getWidth(null) - 1;
        }
        int n4 = (int)Math.round(n2);
        if (n4 < 0) {
            n4 = 0;
        }
        if (n4 >= bufferedImage.getHeight(null)) {
            n4 = bufferedImage.getHeight(null) - 1;
        }
        return bufferedImage.getRGB(n3, n4);
    }
    
    void applyWhirl(final double n) {
        final BufferedImage filteredImage = this.filteredImage;
        final BufferedImage outputImage = this.makeOutputImage(filteredImage);
        final double radians = Math.toRadians(-n);
        final int width = filteredImage.getWidth();
        final int height = filteredImage.getHeight();
        final double n2 = width / 2;
        final double n3 = height / 2;
        double n4;
        double n5;
        double n6;
        if (n2 < n3) {
            n4 = n2;
            n5 = n3 / n2;
            n6 = 1.0;
        }
        else {
            n4 = n3;
            n5 = 1.0;
            if (n3 < n2) {
                n6 = n2 / n3;
            }
            else {
                n6 = 1.0;
            }
        }
        final double n7 = n4 * n4;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                final double n8 = n5 * (i - n2);
                final double n9 = n6 * (j - n3);
                final double n10 = n8 * n8 + n9 * n9;
                if (n10 < n7) {
                    final double n11 = 1.0 - Math.sqrt(n10) / n4;
                    final double n12 = radians * (n11 * n11);
                    final double sin = Math.sin(n12);
                    final double cos = Math.cos(n12);
                    outputImage.setRGB(i, j, filteredImage.getRGB((int)((cos * n8 - sin * n9) / n5 + n2), (int)((sin * n8 + cos * n9) / n6 + n3)));
                }
                else {
                    outputImage.setRGB(i, j, filteredImage.getRGB(i, j));
                }
            }
        }
        this.tempImage = this.filteredImage;
        this.filteredImage = outputImage;
    }
    
    void applyMosaic(final double n) {
        final BufferedImage filteredImage = this.filteredImage;
        final int min = Math.min((int)(Math.abs(n) + 10.0) / 10, Math.min(filteredImage.getWidth(null), filteredImage.getHeight(null)) - 1);
        if (min <= 1) {
            return;
        }
        final BufferedImage filter = new AffineTransformOp(AffineTransform.getScaleInstance(1.0 / min, 1.0 / min), 1).filter(filteredImage, null);
        final BufferedImage bufferedImage = new BufferedImage(min * filter.getWidth(null), min * filter.getHeight(null), 2);
        bufferedImage.getRaster();
        final Graphics graphics = bufferedImage.getGraphics();
        final int width = filter.getWidth(null);
        for (int height = filter.getHeight(null), i = 0; i < bufferedImage.getHeight(null); i += height) {
            for (int j = 0; j < bufferedImage.getWidth(null); j += width) {
                graphics.drawImage(filter, j, i, null);
            }
        }
        graphics.dispose();
        filter.flush();
        if (this.filteredImage != null) {
            this.filteredImage.flush();
        }
        this.filteredImage = new AffineTransformOp(AffineTransform.getScaleInstance(filteredImage.getWidth(null) / bufferedImage.getWidth(null), filteredImage.getHeight(null) / bufferedImage.getHeight(null)), 1).filter(bufferedImage, null);
        bufferedImage.flush();
    }
    
    void applyPixelate(final double n) {
        final BufferedImage filteredImage = this.filteredImage;
        final double min = Math.min((Math.abs(n) + 10.0) / 10.0, Math.min(filteredImage.getWidth(null), filteredImage.getHeight(null)));
        if (min <= 1.0) {
            return;
        }
        final BufferedImage filter = new AffineTransformOp(AffineTransform.getScaleInstance(1.0 / min, 1.0 / min), 2).filter(filteredImage, null);
        this.filteredImage = new AffineTransformOp(AffineTransform.getScaleInstance(min, min), 1).filter(filter, this.filteredImage);
        filter.flush();
    }
}
