import java.awt.image.ColorModel;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

public class a implements ImageProducer
{
    public int[] A;
    public int B;
    public int C;
    public ImageConsumer D;
    
    public a(final int b, final int c) {
        this.A = new int[b * c];
        for (int i = 0; i < b * c; ++i) {
            this.A[i] = -16777216;
        }
        this.B = b;
        this.C = c;
    }
    
    public a(final Image image) {
        this.B = image.getWidth(null);
        this.C = image.getHeight(null);
        this.A = new int[this.B * this.C];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.B, this.C, this.A, 0, this.B);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public a(final int b, final int c, final Color color, final Color color2, final Color color3, final Color color4) {
        this.A = new int[b * c];
        this.B = b;
        this.C = c;
        for (int i = 0; i < b; ++i) {
            for (int j = 0; j < c; ++j) {
                this.A[i + j * b] = -16777216 + (((c - j) * ((b - i) * color.getRed() + i * color2.getRed()) + j * ((b - i) * color3.getRed() + i * color4.getRed())) / (b * c) << 16) + (((c - j) * ((b - i) * color.getGreen() + i * color2.getGreen()) + j * ((b - i) * color3.getGreen() + i * color4.getGreen())) / (b * c) << 8) + ((c - j) * ((b - i) * color.getBlue() + i * color2.getBlue()) + j * ((b - i) * color3.getBlue() + i * color4.getBlue())) / (b * c);
            }
        }
    }
    
    public a(final a a, final int n, final int n2, final int b, final int c) {
        this.A = new int[b * c];
        this.B = b;
        this.C = c;
        for (int i = 0; i < c; ++i) {
            System.arraycopy(a.A, (i + n2) * a.B + n, this.A, i * b, b);
        }
    }
    
    public void A(final a a, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i < n6; ++i) {
            System.arraycopy(a.A, (i + n4) * a.B + n3, this.A, (i + n2) * this.B + n, n5);
        }
    }
    
    public void B() {
        if (this.D != null) {
            this.startProduction(this.D);
        }
    }
    
    public void addConsumer(final ImageConsumer d) {
        this.D = d;
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return false;
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer d) {
        this.addConsumer(d);
        d.setDimensions(this.B, this.C);
        d.setHints(14);
        d.setPixels(0, 0, this.B, this.C, ColorModel.getRGBdefault(), this.A, 0, this.B);
        d.imageComplete(2);
        this.D = d;
    }
}
