// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.awt.Graphics;
import java.awt.Point;

public interface ItemEditSite extends ItemPaintSite
{
    void safeEndEdit(final boolean p0);
    
    Point getEditClickPoint();
    
    Graphics getSiteGraphics();
}
