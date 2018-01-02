// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import com.daysofwonder.util.K;
import com.daysofwonder.b.a;

class ay extends B
{
    public void a(final a a, final K k, final Dimension dimension, final Point point) {
        k.b(this);
        point.x = 0;
    }
    
    public void a(final a a, final Point point, final Color color) {
        point.x = 0;
        point.y += a.d().getHeight();
    }
}
