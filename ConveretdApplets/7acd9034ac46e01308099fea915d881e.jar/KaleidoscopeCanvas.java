import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class KaleidoscopeCanvas extends Canvas
{
    final double SQRT3;
    Dimension preferredSize;
    Image baseTriangle;
    int triangleWidth;
    int triangleHeight;
    Image repeatRectangle;
    int rectangleWidth;
    int rectangleHeight;
    Image imageBuff;
    
    public KaleidoscopeCanvas(final Dimension preferredSize) {
        this.SQRT3 = Math.sqrt(3.0);
        this.preferredSize = preferredSize;
    }
    
    public void setBaseTriangle(final Image baseTriangle, final int n) {
        this.baseTriangle = baseTriangle;
        this.triangleWidth = (n - 1) / 2 * 2 + 1;
        this.triangleHeight = (int)(this.triangleWidth * this.SQRT3 / 2.0);
        this.createRepeatRectangle();
    }
    
    void createRepeatRectangle() {
        final int triangleWidth = this.triangleWidth;
        final int triangleHeight = this.triangleHeight;
        final int rectangleWidth = this.triangleWidth + (this.triangleWidth - 1) / 2;
        this.rectangleWidth = rectangleWidth;
        final int n = rectangleWidth;
        final int rectangleHeight = 2 * this.triangleHeight - 1;
        this.rectangleHeight = rectangleHeight;
        final int n2 = rectangleHeight;
        final int[] array = new int[triangleWidth * triangleHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.baseTriangle, 0, 0, triangleWidth, triangleHeight, array, 0, triangleWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            System.err.println("interrupted waiting for pixels");
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            return;
        }
        final int[] array2 = new int[n * n2];
        final int n3 = triangleWidth - 1;
        for (int i = 0; i < n2 / 2 + 1; ++i) {
            for (int j = 0; j < n3 / 2; ++j) {
                int n4;
                int n5;
                if (i > this.SQRT3 * j) {
                    n4 = (int)((-j + this.SQRT3 * i) / 2.0);
                    n5 = (int)((this.SQRT3 * j + i) / 2.0);
                }
                else {
                    n4 = j;
                    n5 = i;
                }
                array2[j + n * (n2 - 1 - i)] = (array2[j + n * i] = array[n4 + n5 * triangleWidth]);
            }
            for (int k = n3 / 2; k < n3; ++k) {
                int n6;
                int n7;
                if (i > (n3 - k) * this.SQRT3) {
                    n6 = (int)((-k - this.SQRT3 * i + 3 * n3) / 2.0);
                    n7 = (int)((-this.SQRT3 * k + i + this.SQRT3 * n3) / 2.0);
                }
                else {
                    n6 = k;
                    n7 = i;
                }
                array2[k + n * (n2 - 1 - i)] = (array2[k + n * i] = array[n6 + n7 * triangleWidth]);
            }
            for (int l = n3; l < n; ++l) {
                int n8;
                int n9;
                if (i > (l - n3) * this.SQRT3) {
                    n8 = (int)((-l - this.SQRT3 * i + 3 * n3) / 2.0);
                    n9 = (int)((-this.SQRT3 * l + i + this.SQRT3 * n3) / 2.0);
                }
                else {
                    n8 = (int)((-l - this.SQRT3 * i + 3 * n3) / 2.0);
                    n9 = (int)((this.SQRT3 * l - i - this.SQRT3 * n3) / 2.0);
                }
                array2[l + n * (n2 - 1 - i)] = (array2[l + n * i] = array[n8 + n9 * triangleWidth]);
            }
        }
        this.repeatRectangle = this.createImage(new MemoryImageSource(n, n2, array2, 0, n));
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        final int height = this.size().height;
        final int width = this.size().width;
        if (this.repeatRectangle != null) {
            for (int n = 0; n * (this.rectangleWidth - 1) < width; ++n) {
                int i;
                if (n % 2 == 0) {
                    i = 0;
                }
                else {
                    i = -this.rectangleHeight / 2;
                }
                while (i < height) {
                    graphics.drawImage(this.repeatRectangle, n * (this.rectangleWidth - 1), i, this.rectangleWidth, this.rectangleHeight, this);
                    i += this.rectangleHeight - 1;
                }
            }
        }
    }
    
    public Dimension preferredSize() {
        return this.preferredSize;
    }
}
