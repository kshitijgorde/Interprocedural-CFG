// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;

public class VWrapper implements Visualizable
{
    protected Visualizable visualizable;
    
    public Visualizable getVisualizable() {
        return this.visualizable;
    }
    
    public void setVisualizable(final Visualizable visualizable) {
        if (visualizable == null) {
            throw new NullPointerException("VWrapper, setVisualizable(): null visualizable");
        }
        this.visualizable = visualizable;
    }
    
    public Dimension getSize() {
        return this.visualizable.getSize();
    }
    
    public void paint(final Graphics graphics, final Point point) {
        this.visualizable.paint(graphics, point);
    }
    
    public VWrapper(final Visualizable visualizable) {
        if (visualizable == null) {
            throw new NullPointerException("VWrapper(): null visualizable");
        }
        this.visualizable = visualizable;
    }
}
