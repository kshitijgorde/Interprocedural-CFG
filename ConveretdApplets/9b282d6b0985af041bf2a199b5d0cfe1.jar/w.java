import java.util.Hashtable;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

class w extends t
{
    protected ImageProducer e;
    
    void a(final Dimension b) throws RuntimeException {
        if (super.a.size() != 0) {
            return;
        }
        super.b = b;
        try {
            super.a.addElement(new z(super.b));
        }
        catch (OutOfMemoryError outOfMemoryError) {
            for (int n = 1 << (int)(Math.log(1000000 / super.b.width) / Math.log(2.0)), i = 0; i < super.b.height; i += n) {
                super.a.addElement(new z(new Dimension(super.b.width, Math.min(n, super.b.height - i))));
            }
        }
    }
    
    w() {
        this.e = null;
    }
    
    synchronized void a(final Image image) throws RuntimeException, InterruptedException {
        new ba(this, image).a();
        this.e = image.getSource();
    }
    
    ImageProducer d() {
        final ImageProducer e = this.e;
        this.e = null;
        return e;
    }
    
    public Object clone() {
        final w w = new w();
        w.d = (Hashtable)super.d.clone();
        return w;
    }
}
