import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class java2ext
{
    public static final void setAntiAlias(final Graphics graphics) {
        try {
            if (graphics instanceof Graphics2D) {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
