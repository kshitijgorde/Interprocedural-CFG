// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;

public interface Visualizable
{
    Dimension getSize();
    
    void paint(final Graphics p0, final Point p1);
}
