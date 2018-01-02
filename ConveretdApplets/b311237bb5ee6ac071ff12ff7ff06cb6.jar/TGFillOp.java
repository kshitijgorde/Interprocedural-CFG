import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class TGFillOp implements ImageObserver, TGGraphicsOp
{
    private static final int EXPAND_SIZE = 10;
    private boolean waitingForImage;
    private Color color;
    private Image sourceImage;
    private int maxFloodX;
    private int maxFloodY;
    private int minFloodX;
    private int minFloodY;
    private int sourceHeight;
    private int sourceWidth;
    private int subPixHt;
    private int subPixLeftX;
    private int subPixTopY;
    private int subPixWd;
    private int xCenter;
    private int yCenter;
    private int[] pixels;
    private TGPoint point;
    
    public TGFillOp(final TGPoint point, final Color color) {
        this.color = color;
        this.point = point;
        this.waitingForImage = false;
    }
    
    private void addLeftPixels() {
        int subPixLeftX = 10;
        if (this.subPixLeftX < 10) {
            subPixLeftX = this.subPixLeftX;
        }
        this.getPixels(this.subPixLeftX -= subPixLeftX, this.subPixTopY, subPixLeftX, this.subPixHt);
        this.subPixWd += subPixLeftX;
    }
    
    private void addLowerPixels() {
        final int n = this.subPixTopY + this.subPixHt;
        int n2 = 10;
        if (n + 10 > this.sourceHeight) {
            n2 = this.sourceHeight - n;
        }
        this.getPixels(this.subPixLeftX, n, this.subPixWd, n2);
        this.subPixHt += n2;
    }
    
    private void addRightPixels() {
        final int n = this.subPixLeftX + this.subPixWd;
        int n2 = 10;
        if (n + 10 > this.sourceWidth) {
            n2 = this.sourceWidth - n;
        }
        this.getPixels(n, this.subPixTopY, n2, this.subPixHt);
        this.subPixWd += n2;
    }
    
    private void addUpperPixels() {
        int subPixTopY = 10;
        if (this.subPixTopY < 10) {
            subPixTopY = this.subPixTopY;
        }
        this.subPixTopY -= subPixTopY;
        this.getPixels(this.subPixLeftX, this.subPixTopY, this.subPixWd, subPixTopY);
        this.subPixHt += subPixTopY;
    }
    
    private void floodFill(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int minFloodX = n;
        int maxFloodX = n;
        if (n2 < this.minFloodY) {
            this.minFloodY = n2;
        }
        if (n2 > this.maxFloodY) {
            this.maxFloodY = n2;
        }
        for (int i = n; i >= 0; --i) {
            if (i < this.subPixLeftX) {
                this.addLeftPixels();
            }
            final int n7 = n2 * n3 + i;
            if ((this.pixels[n7] & 0xFFFFFF) != n5) {
                break;
            }
            this.pixels[n7] = ((this.pixels[n7] & 0xFF000000) | n6);
            minFloodX = i;
        }
        if (minFloodX < this.minFloodX) {
            this.minFloodX = minFloodX;
        }
        for (int j = n + 1; j < n3; ++j) {
            if (j == this.subPixLeftX + this.subPixWd) {
                this.addRightPixels();
            }
            final int n8 = n2 * n3 + j;
            if ((this.pixels[n8] & 0xFFFFFF) != n5) {
                break;
            }
            this.pixels[n8] = ((this.pixels[n8] & 0xFF000000) | n6);
            maxFloodX = j;
        }
        if (maxFloodX > this.maxFloodX) {
            this.maxFloodX = maxFloodX;
        }
        if (n2 > 0) {
            for (int k = minFloodX; k <= maxFloodX; ++k) {
                final int n9 = n2 - 1;
                if (n9 < this.subPixTopY) {
                    this.addUpperPixels();
                }
                if ((this.pixels[n9 * n3 + k] & 0xFFFFFF) == n5) {
                    this.floodFill(k, n9, n3, n4, n5, n6);
                }
            }
        }
        if (n2 < n4 - 1) {
            for (int l = minFloodX; l <= maxFloodX; ++l) {
                final int n10 = n2 + 1;
                if (n10 == this.subPixTopY + this.subPixHt) {
                    this.addLowerPixels();
                }
                if ((this.pixels[n10 * n3 + l] & 0xFFFFFF) == n5) {
                    this.floodFill(l, n10, n3, n4, n5, n6);
                }
            }
        }
    }
    
    private void printPixels(final int n, final int n2, final int n3, final int n4) {
        int i = this.yCenter - (n2 + (n4 - 1));
        System.out.print("printPixels: sourceHeight=" + this.sourceHeight + ", YCenter=");
        System.out.println(this.yCenter + ", lines " + i + " .. " + (this.yCenter - n2));
        while (i <= this.yCenter - n2) {
            System.out.print(n + "," + (this.yCenter - i) + ":");
            for (int j = n + this.xCenter; j < n + this.xCenter + n3; ++j) {
                System.out.print(" ");
                final int n5 = this.pixels[i * this.sourceWidth + j] & 0xFFFFFF;
                System.out.print((n5 >>> 16) + ".");
                final int n6 = n5 & 0xFFFF;
                System.out.print((n6 >>> 8) + ".");
                System.out.print(n6 & 0xFF);
            }
            System.out.println("");
            ++i;
        }
    }
    
    public Rectangle doIt(final Image sourceImage) {
        this.sourceImage = sourceImage;
        this.sourceWidth = sourceImage.getWidth(this);
        if (this.sourceWidth == -1) {
            return null;
        }
        final int imageX = this.point.imageX(this.sourceWidth);
        if (imageX < 0 || imageX >= this.sourceWidth) {
            return null;
        }
        this.xCenter = this.sourceWidth / 2;
        this.sourceHeight = sourceImage.getHeight(this);
        if (this.sourceHeight == -1) {
            return null;
        }
        final int imageY = this.point.imageY(this.sourceHeight);
        if (imageY < 0 || imageY >= this.sourceHeight) {
            return null;
        }
        this.pixels = new int[this.sourceWidth * this.sourceHeight];
        this.yCenter = this.sourceHeight / 2;
        this.subPixLeftX = imageX - 10;
        if (this.subPixLeftX < 0) {
            this.subPixLeftX = 0;
        }
        this.subPixTopY = imageY - 10;
        if (this.subPixTopY < 0) {
            this.subPixTopY = 0;
        }
        this.subPixHt = 20;
        if (this.subPixHt > this.sourceHeight) {
            this.subPixHt = this.sourceHeight;
        }
        this.subPixWd = 20;
        if (this.subPixWd > this.sourceWidth) {
            this.subPixWd = this.sourceWidth;
        }
        if (!this.getPixels(this.subPixLeftX, this.subPixTopY, this.subPixWd, this.subPixHt)) {
            return null;
        }
        final int n = this.pixels[imageY * this.sourceWidth + imageX] & 0xFFFFFF;
        final int n2 = this.color.getRGB() & 0xFFFFFF;
        if (n == n2) {
            return null;
        }
        final int n3 = imageX;
        this.minFloodX = n3;
        this.maxFloodX = n3;
        final int n4 = imageY;
        this.minFloodY = n4;
        this.maxFloodY = n4;
        this.floodFill(imageX, imageY, this.sourceWidth, this.sourceHeight, n, n2);
        final int n5 = this.sourceWidth * this.minFloodY + this.minFloodX;
        final int n6 = this.maxFloodX + 1 - this.minFloodX;
        final int n7 = this.maxFloodY + 1 - this.minFloodY;
        this.drawImage(Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n6, n7, this.pixels, n5, this.sourceWidth)), this.minFloodX, this.minFloodY);
        return new Rectangle(this.minFloodX, this.minFloodY, n6, n7);
    }
    
    private synchronized void drawImage(final Image image, final int n, final int n2) {
        final Graphics graphics = this.sourceImage.getGraphics();
        this.waitingForImage = true;
        if (graphics.drawImage(image, n, n2, this)) {
            while (this.waitingForImage) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
        this.waitingForImage = false;
        this.pixels = null;
        graphics.dispose();
    }
    
    public Color getColor() {
        return this.color;
    }
    
    private boolean getPixels(final int n, final int n2, final int n3, final int n4) {
        final String s = "TGFillOp.getPixels: ";
        final PixelGrabber pixelGrabber = new PixelGrabber(this.sourceImage, n, n2, n3, n4, this.pixels, this.sourceWidth * n2 + n, this.sourceWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(s + "grabPixels interrupted");
            return false;
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println(s + "grabPixels ImageObserver.ABORT");
            return false;
        }
        return true;
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.waitingForImage) {
            return false;
        }
        if ((n & 0xE0) != 0x0) {
            this.waitingForImage = false;
            this.notifyAll();
            return false;
        }
        return true;
    }
}
