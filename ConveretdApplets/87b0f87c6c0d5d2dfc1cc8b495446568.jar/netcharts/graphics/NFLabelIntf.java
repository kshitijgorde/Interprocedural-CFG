// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

public interface NFLabelIntf
{
    NFRegion getRegion();
    
    void setLabel(final String p0);
    
    void draw(final Graphics p0, final int p1, final int p2);
    
    Dimension getBounds(final Graphics p0);
}
