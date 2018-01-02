// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;

public abstract class VObject implements Visualizable
{
    int width;
    int height;
    
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }
    
    public abstract void paint(final Graphics p0, final Point p1);
    
    public VObject() {
        final boolean b = true;
        this.height = (b ? 1 : 0);
        this.width = (b ? 1 : 0);
    }
    
    public VObject(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
}
