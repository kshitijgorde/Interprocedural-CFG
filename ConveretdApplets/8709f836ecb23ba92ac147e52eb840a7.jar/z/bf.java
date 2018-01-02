// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.net.URL;
import foo.Bubba;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

final class bf
{
    private byte[] a;
    private ImageIcon b;
    private X c;
    private /* synthetic */ F d;
    
    public bf(final F d) {
        this.d = d;
        this.b = new ImageIcon(d.f.getImage());
        this.c = X.a;
    }
    
    public final ImageIcon a() {
        return this.b;
    }
    
    public final Image b() {
        if (this.a == null) {
            return null;
        }
        return new ImageIcon(this.a).getImage();
    }
    
    public final void a(Image image) {
        final Image image2 = image;
        final Integer value = this.d.a;
        image = image2;
        final Dimension dimension = new Dimension(value, value);
        Image image4 = null;
        switch (av.a[this.c.ordinal()]) {
            case 1: {
                final float n = 0.6f;
                final Image image3 = image;
                final int c = this.d.b;
                final float n2 = n;
                final int n3 = c;
                this.a = V.a(n2, V.a(new ImageIcon(image3), new Dimension(n3, n3), Z.b).getImage(), null);
                image4 = V.a(new ImageIcon(image), dimension, Z.a).getImage();
                break;
            }
            case 2: {
                image4 = (image = (this.d.h ? V.a(new ImageIcon(image), dimension, Z.c).getImage() : V.a(new ImageIcon(image), dimension, Z.b).getImage()));
                break;
            }
            case 3: {
                image4 = image;
                break;
            }
            default: {
                throw new RuntimeException("Invalid thumbnail state: " + this.c);
            }
        }
        image = image4;
        X c2 = null;
        switch (av.a[this.c.ordinal()]) {
            case 1: {
                c2 = X.b;
                break;
            }
            case 2: {
                c2 = X.c;
                break;
            }
            case 3: {
                c2 = X.c;
                break;
            }
            default: {
                throw new RuntimeException("Invalid thumbnail state: " + this.c);
            }
        }
        this.c = c2;
        this.b.setImage(image);
    }
    
    public final void b(final Image image) {
        this.b.setImage(image);
    }
    
    public final void c() {
        this.b.setImage(this.d.d);
    }
    
    public final void d() {
        this.b.setImage(this.d.e);
    }
    
    public final boolean e() {
        return this.c == X.c;
    }
    
    public final boolean f() {
        return this.c == X.a;
    }
}
