// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.geom.AffineTransform;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import wordle.core.a.c;
import java.awt.Font;
import java.util.TimerTask;

final class k extends TimerTask
{
    private /* synthetic */ E a;
    
    k(final E a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.c.a();
        this.a.d();
    }
}
