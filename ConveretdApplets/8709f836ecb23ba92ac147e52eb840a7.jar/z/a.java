// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;

public final class a
{
    public Image a;
    public boolean b;
    public Dimension c;
    
    public a(final Image a) {
        this.a = null;
        this.b = false;
        this.a = a;
        this.c = new Dimension(a.getWidth(null), a.getHeight(null));
    }
}
