// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import com.daysofwonder.util.K;
import com.daysofwonder.b.a;
import java.awt.Rectangle;

public abstract class B
{
    public boolean d;
    public Rectangle e;
    
    public B() {
        this.e = new Rectangle();
    }
    
    public abstract void a(final a p0, final K p1, final Dimension p2, final Point p3);
    
    public abstract void a(final a p0, final Point p1, final Color p2);
}
