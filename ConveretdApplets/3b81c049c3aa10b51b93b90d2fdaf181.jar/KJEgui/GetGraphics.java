// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class GetGraphics
{
    public static void setGraphics(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public static void setText(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
