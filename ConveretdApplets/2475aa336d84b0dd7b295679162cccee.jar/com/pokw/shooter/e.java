// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public abstract class e
{
    public abstract Rectangle2D a();
    
    public abstract Line2D b();
    
    public boolean a(final e e) {
        return e != null && (e.a().intersects(this.a()) || e.b().intersects(this.a()) || e.b().intersectsLine(this.b()));
    }
    
    public abstract void a(final Graphics2D p0, final ImageObserver p1);
}
