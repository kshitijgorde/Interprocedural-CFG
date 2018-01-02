// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.RenderingHints;
import java.util.Map;
import java.awt.geom.PathIterator;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Dimension;

public class NFOffscreenImage
{
    public static Image createImage(final Dimension dimension) {
        return createImage(dimension.width, dimension.height);
    }
    
    public static Image createImage(final int n, final int n2) {
        final ColorModel colorModel = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getColorModel(3);
        return new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(n, n2), colorModel.isAlphaPremultiplied(), null);
    }
    
    public static void setRotationTransform(final Graphics graphics, final int n, final Point point) {
        setRotationTransform(graphics, n, point.x, point.y);
    }
    
    public static void setRotationTransform(final Graphics graphics, final int n, final int n2, final int n3) {
        final Graphics2D graphics2D = getGraphics2D(graphics);
        if (graphics2D != null) {
            graphics2D.setTransform(AffineTransform.getRotateInstance(Math.toRadians(360 - n), n2, n3));
        }
    }
    
    public static Polygon getRotatedBounds(final int n, final int n2, final int n3, final int n4, final int n5) {
        return getRotatedBounds(new Rectangle(n, n2, n3, n4), n5);
    }
    
    public static Polygon getRotatedBounds(final Rectangle rectangle, final int n) {
        final PathIterator pathIterator = rectangle.getPathIterator(AffineTransform.getRotateInstance(Math.toRadians(360 - n), rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2));
        final Polygon polygon = new Polygon();
        final double[] array = new double[6];
        while (!pathIterator.isDone()) {
            final int currentSegment = pathIterator.currentSegment(array);
            if (currentSegment == 0 || currentSegment == 1) {
                polygon.addPoint((int)array[0], (int)array[1]);
            }
            pathIterator.next();
        }
        return polygon;
    }
    
    public static void setRenderingHints(final Graphics graphics, final Graphics graphics2) {
        final Graphics2D graphics2D = getGraphics2D(graphics);
        final Graphics2D graphics2D2 = getGraphics2D(graphics2);
        if (graphics2D != null && graphics2D2 != null) {
            graphics2D2.setRenderingHints(graphics2D.getRenderingHints());
        }
    }
    
    public static void setAntiAliasing(final Graphics graphics, final boolean b) {
        final Graphics2D graphics2D = getGraphics2D(graphics);
        if (graphics2D != null) {
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, b ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    
    public static void getAllFonts() {
        GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    }
    
    protected static Graphics2D getGraphics2D(final Graphics graphics) {
        if (graphics != null && graphics instanceof Graphics2D) {
            return (Graphics2D)graphics;
        }
        return null;
    }
}
