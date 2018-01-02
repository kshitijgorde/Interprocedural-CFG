// 
// Decompiled by Procyon v0.5.30
// 

package jfig.canvas;

import jfig.objects.FigBbox;
import java.awt.Graphics;

public interface FigDrawable
{
    void paint(final Graphics p0);
    
    void paint(final Graphics p0, final FigTrafo2D p1);
    
    void setTrafo(final FigTrafo2D p0);
    
    boolean isVisible(final FigBbox p0);
    
    FigBbox getBbox();
    
    FigBbox get_sc_bbox();
    
    boolean getSyncRedrawFlag();
    
    void setSyncRedrawFlag(final boolean p0);
}
