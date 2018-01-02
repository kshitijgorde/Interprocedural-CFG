// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import java.awt.Graphics2D;
import prefuse.Display;
import java.util.EventListener;

public interface PaintListener extends EventListener
{
    void prePaint(final Display p0, final Graphics2D p1);
    
    void postPaint(final Display p0, final Graphics2D p1);
}
