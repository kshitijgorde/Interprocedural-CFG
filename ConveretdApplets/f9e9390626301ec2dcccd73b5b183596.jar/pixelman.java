import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class pixelman extends Component
{
    private Image original;
    private int height;
    private int width;
    private int[] original_pixels;
    private int[] draw_pixels;
    
    public pixelman() {
        this.original = null;
        System.out.println("Warning: no original image specified");
    }
    
    public pixelman(final Image original) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(original, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.original = original;
        this.height = this.original.getHeight(this);
        this.width = this.original.getWidth(this);
        this.original_pixels = new int[this.width * this.height];
        this.draw_pixels = new int[this.width * this.height];
        this.LoadPixels();
        System.out.println("Pixel Manager v1.1");
        System.out.println("By Warren Price");
        System.out.println("Hey, java can be a bitch sometimes");
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void LoadPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.original, 0, 0, this.width, this.height, this.original_pixels, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.ResetPixels();
    }
    
    public void ResetPixels() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.draw_pixels[i * this.width + j] = this.original_pixels[i * this.width + j];
            }
        }
    }
    
    public void MakeEqual(final pixelman pixelman) {
        this.width = pixelman.getWidth();
        this.height = pixelman.getHeight();
        this.original_pixels = new int[this.width * this.height];
        this.draw_pixels = new int[this.width * this.height];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.original_pixels[i * this.width + j] = pixelman.getRGB(j, i);
            }
        }
        this.ResetPixels();
    }
    
    public void DrawPixels(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawImage(this.createImage(new MemoryImageSource(this.width, this.height, this.draw_pixels, 0, this.width)), n, n2, n + n3, n2 + n4, null);
    }
    
    public int RGB(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.original_pixels[n2 * this.width + n];
        }
        return 0;
    }
    
    public int Alpha(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.original_pixels[n2 * this.width + n] >> 24 & 0xFF;
        }
        return 0;
    }
    
    public int Red(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.original_pixels[n2 * this.width + n] >> 16 & 0xFF;
        }
        return 0;
    }
    
    public int Green(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.original_pixels[n2 * this.width + n] >> 8 & 0xFF;
        }
        return 0;
    }
    
    public int Blue(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.original_pixels[n2 * this.width + n] & 0xFF;
        }
        return 0;
    }
    
    public int getRGB(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.draw_pixels[n2 * this.width + n];
        }
        return 0;
    }
    
    public int getAlpha(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.draw_pixels[n2 * this.width + n] >> 24 & 0xFF;
        }
        return 0;
    }
    
    public int getRed(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.draw_pixels[n2 * this.width + n] >> 16 & 0xFF;
        }
        return 0;
    }
    
    public int getGreen(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.draw_pixels[n2 * this.width + n] >> 8 & 0xFF;
        }
        return 0;
    }
    
    public int getBlue(final int n, final int n2) {
        if (n >= 0 && n < this.width && n2 >= 0 && n2 < this.height) {
            return this.draw_pixels[n2 * this.width + n] & 0xFF;
        }
        return 0;
    }
    
    public void setRGB(final int n, final int n2, final int n3) {
        if (n2 >= 0 && n2 <= this.width && n3 >= 0 && n3 <= this.height) {
            this.draw_pixels[n3 * this.width + n2] = n;
        }
    }
    
    public void setAlpha(int n, final int n2, final int n3) {
        if (n >= 0 && n < 256 && n2 >= 0 && n2 <= this.width && n3 >= 0 && n3 <= this.height) {
            final int n4 = n3 * this.width + n2;
            this.draw_pixels[n4] &= 0xFFFFFF;
            n <<= 24;
            this.draw_pixels[n4] |= n;
        }
        else {
            System.out.println("Warning: alpha value out of bouds");
        }
    }
    
    public void setRed(int n, final int n2, final int n3) {
        if (n >= 0 && n < 256 && n2 >= 0 && n2 <= this.width && n3 >= 0 && n3 <= this.height) {
            final int n4 = n3 * this.width + n2;
            this.draw_pixels[n4] &= 0xFF00FFFF;
            n <<= 16;
            this.draw_pixels[n4] |= n;
        }
        else {
            System.out.println("Warning: red value out of bounds");
        }
    }
    
    public void setGreen(int n, final int n2, final int n3) {
        if (n >= 0 && n < 256 && n2 >= 0 && n2 <= this.width && n3 >= 0 && n3 <= this.height) {
            final int n4 = n3 * this.width + n2;
            this.draw_pixels[n4] &= 0xFFFF00FF;
            n <<= 8;
            this.draw_pixels[n4] |= n;
        }
        else {
            System.out.println("Warning: grenn value out of bounds");
        }
    }
    
    public void setBlue(int n, final int n2, final int n3) {
        if (n >= 0 && n < 256 && n2 >= 0 && n2 <= this.width && n3 >= 0 && n3 <= this.height) {
            final int n4 = n3 * this.width + n2;
            this.draw_pixels[n4] &= 0xFFFFFF00;
            n = n;
            this.draw_pixels[n4] |= n;
        }
        else {
            System.out.println("Warning: blue value out of bounds");
        }
    }
}
