import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Component;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.GeneralPath;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class IsGraphics2D
{
    static Object KEY_ALPHA_INTERPOLATION;
    public static boolean anti;
    public static boolean oldAnti;
    public static Class graphicsClass;
    private static Graphics defaultGraphics;
    
    public static final void setAntialiasing(final Graphics graphics, final boolean b, final int n) {
        if (graphics instanceof Graphics2D) {
            if (b) {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                IsGraphics2D.anti = true;
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, (n == 2) ? RenderingHints.VALUE_TEXT_ANTIALIAS_OFF : RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
            }
            else {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                IsGraphics2D.anti = false;
            }
        }
    }
    
    public static final void setTextAntialiasing(final Graphics graphics, final Font font) {
        setTextAntialiasing(graphics, font, false);
    }
    
    public static final void setTextAntialiasing(final Graphics graphics, final Font font, boolean b) {
        if (graphics instanceof Graphics2D) {
            if (font != null) {
                final int size = font.getSize();
                b = (b || size >= 11);
            }
            if (b) {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            }
            else {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            }
        }
    }
    
    public static final void setRendering(final Graphics graphics, final boolean b) {
        if (graphics instanceof Graphics2D) {
            if (b) {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            }
            else {
                ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
            }
        }
    }
    
    public static final void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        pushAntialiasing(graphics);
        graphics.drawLine(n, n2, n3, n4);
        popAntialiasing(graphics);
    }
    
    public static final void pushAntialiasing(final Graphics graphics) {
        IsGraphics2D.oldAnti = IsGraphics2D.anti;
        if (IsGraphics2D.anti) {
            setAntialiasing(graphics, false, 2);
        }
    }
    
    public static final void popAntialiasing(final Graphics graphics) {
        if (IsGraphics2D.oldAnti) {
            setAntialiasing(graphics, true, 2);
        }
    }
    
    public static final void drawLine(final Graphics graphics, final float n, final float n2, final float n3, final float n4) {
        ((Line2D.Float)IsGraphics2D.KEY_ALPHA_INTERPOLATION).setLine(n, n2, n3, n4);
        ((Graphics2D)graphics).draw((Shape)IsGraphics2D.KEY_ALPHA_INTERPOLATION);
    }
    
    public static final void dibuixaPoligonal(final float[] array, final float[] array2, final int n, final Graphics graphics, final boolean b) {
        if (n == 0) {
            return;
        }
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(array[0], array2[0]);
        for (int i = 1; i < n; ++i) {
            generalPath.lineTo(array[i], array2[i]);
        }
        if (b) {
            ((Graphics2D)graphics).fill(generalPath);
        }
        ((Graphics2D)graphics).draw(generalPath);
    }
    
    public static final void dibuixaPoligon(final float[] array, final float[] array2, final int n, final float n2, final Graphics graphics, final boolean b, final boolean b2) {
        if (n == 0) {
            return;
        }
        setLineWidth(graphics, n2);
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(array[0], array2[0]);
        for (int i = 1; i < n; ++i) {
            generalPath.lineTo(array[i], array2[i]);
        }
        generalPath.closePath();
        if (b) {
            ((Graphics2D)graphics).fill(generalPath);
        }
        if (b2) {
            ((Graphics2D)graphics).draw(generalPath);
        }
    }
    
    public static final void setLineWidth(final Graphics graphics, final float n) {
        IsGraphics2D.KEY_ALPHA_INTERPOLATION = new Line2D.Float();
        ((Graphics2D)graphics).setStroke(new BasicStroke(n));
    }
    
    public static final Image createImage(final Component component, final int n, final int n2, final int n3) {
        try {
            return new BufferedImage(n, n2, n3);
        }
        catch (Exception ex) {
            return component.createImage(n, n2);
        }
    }
    
    public static final Image createImage(final Component component, final int n, final int n2) {
        return createImage(component, n, n2, 2);
    }
    
    public static final Graphics getGraphics(final int n, final int n2) {
        if (IsGraphics2D.graphicsClass != null) {
            try {
                final Object instance = IsGraphics2D.graphicsClass.newInstance();
                return (Graphics)instance.getClass().getMethod("create", Integer.TYPE, Integer.TYPE).invoke(instance, new Integer(n), new Integer(n2));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    private static Graphics getDefaultGraphics() {
        if (IsGraphics2D.defaultGraphics == null) {
            IsGraphics2D.defaultGraphics = new BufferedImage(10, 10, 6).getGraphics();
        }
        return IsGraphics2D.defaultGraphics;
    }
    
    public static final FontMetrics getFontMetrics(final Font font) {
        return getDefaultGraphics().getFontMetrics(font);
    }
}
