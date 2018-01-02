// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.geom.Point2D;

interface JGroupableNote extends JScoreElement
{
    void setStemYEnd(final int p0);
    
    int getStemYEnd();
    
    Point2D getStemBegin();
}
