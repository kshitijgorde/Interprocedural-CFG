import java.awt.Dimension;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

class o extends m
{
    protected ImageProducer e;
    protected int f;
    
    o() {
        this.e = null;
    }
    
    ImageProducer d() {
        final ImageProducer e = this.e;
        this.e = null;
        return e;
    }
    
    Object e() {
        final o o = new o();
        o.d = (Hashtable)super.d.clone();
        return o;
    }
    
    int f() {
        return this.f;
    }
    
    synchronized void a(final Image image) throws RuntimeException, InterruptedException {
        new q(this, image).a();
        this.e = image.getSource();
    }
    
    void a(final Dimension b) throws RuntimeException {
        if (super.a.size() != 0) {
            return;
        }
        super.b = b;
        try {
            super.a.addElement(new int[super.b.width * super.b.height]);
            this.f = super.b.height;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.f = 1000000 / super.b.width;
            this.f = 1 << (int)(Math.log(this.f) / Math.log(2.0));
            for (int i = 0; i < super.b.height; i += this.f) {
                super.a.addElement(new int[super.b.width * Math.min(this.f, super.b.height - i)]);
            }
        }
    }
}
