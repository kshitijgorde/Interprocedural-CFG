import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class Ball implements ImageObserver
{
    static final double slowdown = 0.75;
    static final double getout = 1.0;
    double x;
    double y;
    double vx;
    double vy;
    double vlen;
    double r;
    int w;
    int h;
    Image itsImage;
    AudioClip itsBoink;
    
    Ball(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.vx = 0.0;
        this.vy = 0.0;
        this.vlen = 0.0;
    }
    
    void SetBoinkSound(final AudioClip itsBoink) {
        this.itsBoink = itsBoink;
    }
    
    void Move() {
        this.x += this.vx;
        this.y += this.vy;
    }
    
    void Accelerate(final double n, final double n2) {
        this.vx += n;
        this.vy += n2;
        this.vlen = Math.sqrt(this.vx * this.vx + this.vy * this.vy);
    }
    
    boolean Boink(final int n, final int n2, final double n3) {
        final double n4 = this.x - n;
        final double n5 = this.y - n2;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
        if (sqrt <= n3 + this.r) {
            if (this.itsBoink != null) {
                this.itsBoink.stop();
                this.itsBoink.play();
            }
            final double n6 = this.vx / this.vlen;
            final double n7 = this.vy / this.vlen;
            final double n8 = n4 / sqrt;
            final double n9 = n5 / sqrt;
            final double n10 = n6 * n8 + n7 * n9;
            final double n11 = n6 - 2.0 * n10 * n8;
            final double n12 = n7 - 2.0 * n10 * n9;
            this.vx = n11 * this.vlen;
            this.vy = n12 * this.vlen;
            double n13;
            double n14;
            do {
                this.x += this.vx;
                this.y += this.vy;
                n13 = this.x - n;
                n14 = this.y - n2;
            } while (Math.sqrt(n13 * n13 + n14 * n14) <= n3 + this.r);
            this.vx *= 0.75;
            this.vy *= 0.75;
            this.vlen = Math.sqrt(this.vx * this.vx + this.vy * this.vy);
            return true;
        }
        return false;
    }
    
    void SetImage(final Image itsImage) {
        this.itsImage = itsImage;
        this.w = this.itsImage.getWidth(this);
        this.h = this.itsImage.getHeight(this);
        this.r = this.w / 2.0;
    }
    
    void Draw(final Graphics graphics) {
        if (this.itsImage != null) {
            graphics.drawImage(this.itsImage, (int)this.x - this.w / 2, (int)this.y - this.h / 2, this);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int w, final int h) {
        if ((n & 0x3) == 0x3) {
            if (image == this.itsImage) {
                this.w = w;
                this.h = h;
                this.r = this.w / 2;
            }
            return false;
        }
        return true;
    }
}
