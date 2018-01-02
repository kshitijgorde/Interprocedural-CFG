import java.awt.Dimension;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

class q extends o
{
    protected ImageProducer e;
    protected int f;
    
    q() {
        this.e = null;
    }
    
    ImageProducer d() {
        final ImageProducer e = this.e;
        this.e = null;
        return e;
    }
    
    Object e() {
        final q q = new q();
        q.d = (Hashtable)this.d.clone();
        return q;
    }
    
    int f() {
        return this.f;
    }
    
    synchronized void a(final Image image) throws RuntimeException, InterruptedException {
        new w(this, image).a();
        this.e = image.getSource();
    }
    
    void a(final Dimension b) throws RuntimeException {
        if (this.a.size() != 0) {
            return;
        }
        this.b = b;
        try {
            this.a.addElement(new int[this.b.width * this.b.height]);
            this.f = this.b.height;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.f = 1000000 / this.b.width;
            this.f = 1 << (int)(Math.log(this.f) / Math.log(2.0));
            for (int i = 0; i < this.b.height; i += this.f) {
                this.a.addElement(new int[this.b.width * Math.min(this.f, this.b.height - i)]);
            }
        }
    }
}
