import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DuriusWaterPic extends WrApp implements Runnable
{
    Screen32 jm2;
    Screen32 jm3;
    Waterpic jm49;
    int jm6;
    int jm7;
    boolean jm50;
    boolean jm51;
    
    public void jm0() {
        super.jm86 = 15637;
    }
    
    public final void update(final Graphics graphics) {
        if (!super.jm77) {
            this.jm2 = new Screen32(super.jm81, super.jm82);
            for (int i = 0; i < this.jm2.jm24 * this.jm2.jm25; ++i) {
                this.jm2.jm21[i] = super.jm80;
            }
            super.jm62 = new DirectColorModel(32, 16711680, 65280, 255);
            super.jm63 = new MemoryImageSource(this.jm2.jm101(), this.jm2.jm96(), super.jm62, this.jm2.jm97(), 0, this.jm2.jm101());
            super.jm64 = this.createImage(super.jm63);
            this.showStatus("Applet initializing.");
            super.jm64.flush();
            graphics.drawImage(super.jm64, 0, 0, this);
            this.jm3 = new Screen32(super.jm81, super.jm82);
            this.jm49 = new Waterpic(super.jm81, super.jm82);
            this.jm50 = false;
            this.jm51 = true;
            super.jm71 = this.getParameter("noise");
            if (super.jm71 != null && Integer.parseInt(super.jm71) == 1) {
                this.jm50 = true;
            }
            super.jm71 = this.getParameter("mouse");
            if (super.jm71 != null && Integer.parseInt(super.jm71) != 1) {
                this.jm51 = false;
            }
            super.jm71 = this.getParameter("dim");
            if (super.jm71 != null) {
                this.jm49.jm28 = Integer.parseInt(super.jm71);
            }
            super.jm71 = this.getParameter("dotsize");
            if (super.jm71 != null) {
                this.jm49.jm31 = Integer.parseInt(super.jm71);
            }
            super.jm71 = this.getParameter("ndotsize");
            if (super.jm71 != null) {
                this.jm49.jm32 = Integer.parseInt(super.jm71);
            }
            super.jm71 = this.getParameter("image");
            final Image image = this.getImage(this.getDocumentBase(), super.jm71);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                this.showStatus("InterruptedException");
            }
            if (mediaTracker.isErrorAny()) {
                super.jm78 = true;
                super.jm72 = super.jm71;
            }
            this.jm6 = image.getWidth(this);
            this.jm7 = image.getHeight(this);
            this.jm3.jm98(image);
            super.jm77 = true;
            this.showStatus("");
        }
        super.jm73 = System.currentTimeMillis();
        if (super.jm57 != null) {
            if (!super.jm76) {
                this.jm49.jm19();
                if (this.jm50) {
                    this.jm49.jm18((int)(Math.random() * (this.jm49.jm24 >> 1) + (this.jm49.jm24 >> 2) - (this.jm49.jm32 >> 1)), (int)(Math.random() * (this.jm49.jm25 >> 1) + (this.jm49.jm25 >> 2) - (this.jm49.jm32 >> 1)), 200, this.jm49.jm32);
                }
                this.jm49.jm35(this.jm2, this.jm3);
            }
            else {
                this.showStatus("http://www.durius.com/");
            }
            super.jm64.flush();
            graphics.drawImage(super.jm64, 0, 0, this);
            super.jm77 = true;
        }
        super.jm74 = System.currentTimeMillis();
        final long n = super.jm75 - (super.jm74 - super.jm73);
        if (n > 1L) {
            try {
                Thread.sleep(n);
                return;
            }
            catch (Exception ex2) {
                return;
            }
        }
        try {
            Thread.sleep(1L);
        }
        catch (Exception ex3) {}
    }
    
    public final void jm48(final Event event, int n, int n2) {
        if (this.jm51) {
            if (n > super.jm81 - this.jm49.jm31) {
                n = super.jm81 - this.jm49.jm31;
            }
            if (n2 > super.jm82 - this.jm49.jm31) {
                n2 = super.jm82 - this.jm49.jm31;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            this.jm49.jm18(n, n2, 450, this.jm49.jm31);
        }
    }
}
