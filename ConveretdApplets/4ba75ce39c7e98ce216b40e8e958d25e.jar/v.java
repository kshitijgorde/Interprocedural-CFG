import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends u
{
    protected float f;
    protected float g;
    protected float h;
    
    boolean d() {
        try {
            this.b(null);
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (NoSuchMethodError noSuchMethodError) {
            return false;
        }
        return true;
    }
    
    v(final Dimension dimension, final Image image) {
        this.h = 1.0f;
        this.a(dimension, image);
    }
    
    void a(final Graphics graphics) {
        try {
            if (this.h == 1.0f) {
                graphics.drawImage(super.e, -(int)this.f, -(int)this.g, null);
                return;
            }
            this.b(graphics);
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (NoSuchMethodError noSuchMethodError) {
            graphics.drawImage(super.e, -(int)this.f, -(int)this.g, null);
        }
    }
    
    void a(final float f, final float g, final float h) {
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    private void b(final Graphics graphics) {
        final float n = 0.5f / this.h;
        final int round = Math.round(this.f + super.b.width * n);
        final int round2 = Math.round(this.g + super.b.height * n);
        final int round3 = Math.round(super.b.width * n);
        final int round4 = Math.round(super.b.height * n);
        graphics.drawImage(super.e, 0, 0, super.b.width, super.b.height, round - round3, round2 - round4, round + round3, round2 + round4, null, null);
    }
}
