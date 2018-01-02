// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;

public interface FigRenderer
{
    void paint(final Graphics p0);
    
    void paint(final Graphics p0, final FigTrafo2D p1);
    
    void rebuild();
}
