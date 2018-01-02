// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import prefuse.util.PrefuseLib;
import java.awt.Graphics2D;
import prefuse.Display;

public class DebugStatsPainter implements PaintListener
{
    public void prePaint(final Display display, final Graphics2D graphics2D) {
    }
    
    public void postPaint(final Display display, final Graphics2D graphics2D) {
        graphics2D.setFont(display.getFont());
        graphics2D.setColor(display.getForeground());
        graphics2D.drawString(PrefuseLib.getDisplayStats(display), 5, 15);
    }
}
