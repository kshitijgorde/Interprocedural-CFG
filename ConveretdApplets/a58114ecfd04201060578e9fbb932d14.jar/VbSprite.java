import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class VbSprite
{
    int sx;
    int sy;
    int fr;
    Image[] imSpriteA;
    int w;
    int h;
    int numFrames;
    
    void Draw(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.drawImage(this.imSpriteA[n3], n - this.w / 2, n2 - this.h / 2, this.w, this.h, null);
    }
    
    void Draw(final Graphics graphics) {
        this.Draw(graphics, this.sx, this.sy, this.fr);
    }
    
    VbSprite(final Image[] array, final int numFrames) {
        this.numFrames = numFrames;
        this.w = array[0].getWidth(null);
        this.h = array[0].getHeight(null);
        this.imSpriteA = new Image[numFrames];
        for (int i = 0; i < numFrames; ++i) {
            this.imSpriteA[i] = array[i];
        }
    }
    
    VbSprite(final Image image, final int numFrames, final Component component) {
        this.imSpriteA = new Image[numFrames];
        this.numFrames = numFrames;
        this.w = image.getWidth(null);
        this.h = image.getHeight(null);
        this.w /= numFrames;
        for (int i = 0; i < numFrames; ++i) {
            final int[] array = new int[this.w * this.h];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, this.w * i, 0, this.w, this.h, array, 0, this.w);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            this.imSpriteA[i] = component.createImage(new MemoryImageSource(this.w, this.h, array, 0, this.w));
        }
    }
}
