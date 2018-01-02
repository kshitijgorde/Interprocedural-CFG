// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.a.b;

import java.awt.Toolkit;
import java.awt.Image;

public class c implements d
{
    private Image a;
    private Toolkit b;
    
    public c() {
        this((Image)null);
    }
    
    public c(final Image image) {
        this.b = Toolkit.getDefaultToolkit();
        this.a(image);
    }
    
    public c(final byte[] array) {
        this.b = Toolkit.getDefaultToolkit();
        this.a(array);
    }
    
    public Image a() {
        return this.a;
    }
    
    public void a(final Image a) {
        this.a = a;
    }
    
    public void a(final byte[] array) {
        if (array != null) {
            this.a = this.b.createImage(array);
        }
        else {
            this.a = null;
        }
    }
}
