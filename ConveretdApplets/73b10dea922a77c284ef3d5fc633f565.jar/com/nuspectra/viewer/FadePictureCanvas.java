// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

class FadePictureCanvas extends PictureCanvas
{
    int[] b1;
    int[] b2;
    int[] b3;
    MemoryImageSource memImage;
    static int slowCount;
    final int maxSlowCount = 2;
    boolean quit;
    int fadeTime;
    
    static {
        FadePictureCanvas.slowCount = 0;
    }
    
    private void init() {
        final int bytes = super.w * super.h;
        this.b1 = new int[bytes];
        this.b2 = new int[bytes];
        this.b3 = new int[bytes];
        this.memImage = new MemoryImageSource(super.w, super.h, this.b3, 0, super.w);
    }
    
    FadePictureCanvas(final int width, final int height) {
        super(width, height);
        this.quit = false;
        this.fadeTime = 250;
        this.init();
    }
    
    protected void quit() {
        this.quit = true;
    }
    
    void fadePixels(final int i, final int[] outImage, final int[] image1, final int[] image2) {
        for (int j = image1.length, k = 0; k < j; ++k) {
            final int r1 = image1[k] >> 16 & 0xFF;
            final int r2 = image2[k] >> 16 & 0xFF;
            final int nred = r1 + ((r2 - r1) * i >> 8);
            final int g1 = image1[k] >> 8 & 0xFF;
            final int g2 = image2[k] >> 8 & 0xFF;
            final int ngreen = g1 + ((g2 - g1) * i >> 8);
            final int bl1 = image1[k] & 0xFF;
            final int bl2 = image2[k] & 0xFF;
            final int nblue = bl1 + ((bl2 - bl1) * i >> 8);
            outImage[k] = (0xFF000000 | nred << 16 | ngreen << 8 | nblue);
        }
    }
    
    protected boolean fadeImage(final Image src1, final Image src2) throws Exception {
        if (FadePictureCanvas.slowCount > 2) {
            return false;
        }
        int step = 2;
        boolean ok = true;
        if (src1.getWidth(null) != super.w) {
            ok = false;
        }
        if (src2.getWidth(null) != super.w) {
            ok = false;
        }
        if (src1.getHeight(null) != super.h) {
            ok = false;
        }
        if (src2.getHeight(null) != super.h) {
            ok = false;
        }
        if (!ok) {
            Debug.println("image size failure. w=" + super.w + " h=" + super.h + " w1=" + src1.getWidth(null) + " h1=" + src1.getHeight(null));
            return false;
        }
        final PixelGrabber p1 = new PixelGrabber(src1, 0, 0, super.w, super.h, this.b1, 0, super.w);
        if (!p1.grabPixels()) {
            return false;
        }
        final PixelGrabber p2 = new PixelGrabber(src2, 0, 0, super.w, super.h, this.b2, 0, super.w);
        if (!p2.grabPixels()) {
            return false;
        }
        int processTime = 0;
        final long tooSlow = System.currentTimeMillis() + 2 * this.fadeTime;
        int sleepTime = 5;
        int images = 0;
        for (int x = 0; x < 255; x += step) {
            final long fs = System.currentTimeMillis();
            this.fadePixels(x, this.b3, this.b1, this.b2);
            final Image img = this.createImage(this.memImage);
            super.setImage(img);
            if (++images == 1) {
                final long now = System.currentTimeMillis();
                processTime = (int)(now - fs);
                int estSleep = processTime / 2;
                if (estSleep > 25) {
                    estSleep = 25;
                }
                if (estSleep < 4) {
                    estSleep = 4;
                }
                final int cycleTime = estSleep + processTime;
                double cycles = this.fadeTime / cycleTime;
                if (cycles > 256.0) {
                    cycles = 256.0;
                    estSleep = this.fadeTime / 256 - cycleTime;
                    if (estSleep < 0) {
                        estSleep = 0;
                    }
                }
                step = (int)Math.round(256.0 / cycles);
                Debug.println("First time.. " + processTime + " cycles=" + cycles + " estSleep=" + estSleep + " newStep  = " + step);
                if (step < 1) {
                    step = 1;
                }
                if (step > 64) {
                    ++FadePictureCanvas.slowCount;
                    return false;
                }
                sleepTime = estSleep;
            }
            if (fs > tooSlow) {
                ++FadePictureCanvas.slowCount;
                Debug.println("fade slow: steps: " + step + " first=" + processTime);
                return false;
            }
            Thread.sleep(sleepTime);
            if (this.quit) {
                return true;
            }
        }
        return true;
    }
    
    protected void setImage(final Image image) {
        if (super.curImage != null) {
            try {
                this.fadeImage(super.curImage, image);
            }
            catch (Exception e) {
                Debug.report(e);
            }
        }
        super.setImage(image);
        System.gc();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
