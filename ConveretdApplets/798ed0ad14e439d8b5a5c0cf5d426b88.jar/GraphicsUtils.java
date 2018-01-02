import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphicsUtils
{
    public static boolean ps_graphics;
    
    public static final void setLineWidth(final Graphics graphics, final float lineWidth) {
        if (graphics instanceof VecGraphics) {
            ((VecGraphics)graphics).setLineWidth(lineWidth);
        }
        else if (OmegaSystem.versio_java >= 2) {
            IsGraphics2D.setLineWidth(graphics, lineWidth);
        }
    }
    
    public static final void setRendering(final Graphics graphics, final boolean b) {
        if (graphics instanceof VecGraphics) {
            return;
        }
        if (!GraphicsUtils.ps_graphics && OmegaSystem.versio_java >= 2) {
            IsGraphics2D.setRendering(graphics, b);
        }
    }
    
    public static final void setTextAntialiasing(final Graphics graphics, final Font font) {
        if (graphics instanceof VecGraphics) {
            return;
        }
        if (!GraphicsUtils.ps_graphics && OmegaSystem.versio_java >= 2) {
            IsGraphics2D.setTextAntialiasing(graphics, font);
        }
    }
    
    public static final boolean isFileGraphics(final Graphics graphics) {
        return GraphicsUtils.ps_graphics || graphics instanceof VecGraphics;
    }
    
    public static final void drawDashBox(final Graphics graphics, final int n, final int n2, final Rectangle rectangle) {
    }
    
    public static final void drawDashLine(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final int n4, final int n5) {
    }
    
    public static final Color colorDissimulat(final Color color) {
        return new Color(255 - (255 - color.getRed()) / 4, 255 - (255 - color.getGreen()) / 4, 255 - (255 - color.getBlue()) / 4);
    }
    
    public static final Color colorMig(final Color color, final Color color2, final int n, final int n2) {
        final int n3 = n + n2;
        return new Color((color.getRed() * n + color2.getRed() * n2) / n3, (color.getGreen() * n + color2.getGreen() * n2) / n3, (color.getBlue() * n + color2.getBlue() * n2) / n3);
    }
    
    public static final String colorToString(final Color color) {
        String s = Integer.toHexString(color.getRGB() & 0xFFFFFF);
        for (int i = s.length(); i < 6; ++i) {
            s = '0' + s;
        }
        return '#' + s;
    }
    
    static {
        GraphicsUtils.ps_graphics = false;
    }
}
