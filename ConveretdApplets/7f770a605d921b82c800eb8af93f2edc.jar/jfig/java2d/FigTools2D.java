// 
// Decompiled by Procyon v0.5.30
// 

package jfig.java2d;

import jfig.objects.FigBbox;
import java.awt.Color;
import java.awt.Graphics;
import jfig.objects.FillPatterns;
import java.awt.Paint;
import jfig.canvas.FigTrafo2D;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import jfig.objects.FigAttribs;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.Point;

public class FigTools2D
{
    static boolean thinLinesHack;
    
    public static Point2D.Float[] convertToFloatPoints(final Point[] array) {
        final int length = array.length;
        final Point2D.Float[] array2 = new Point2D.Float[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = new Point2D.Float(array[i].x, array[i].y);
        }
        return array2;
    }
    
    public static Point[] convertToIntegerPoints(final Point2D.Float[] array) {
        final int length = array.length;
        final Point[] array2 = new Point[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = new Point((int)(array[i].x + 0.5), (int)(array[i].y + 0.5));
        }
        return array2;
    }
    
    public static GeneralPath createPath(final Point[] array, final boolean b) {
        final GeneralPath generalPath = new GeneralPath(0);
        if (array != null && array.length > 1) {
            generalPath.moveTo(array[0].x, array[0].y);
            for (int i = 1; i < array.length; ++i) {
                generalPath.lineTo(array[i].x, array[i].y);
            }
            if (b) {
                generalPath.closePath();
            }
        }
        return generalPath;
    }
    
    public static void checkRestoreDashLength(final FigAttribs figAttribs) {
        if (figAttribs.lineStyle != 0 && figAttribs.dashLength <= 0.0) {
            msg("-W- found zero dash/dot length, restoring default 4");
            figAttribs.dashLength = 120.0;
        }
    }
    
    public static BasicStroke createStroke(final FigAttribs figAttribs) {
        final float lineWidth;
        final float n = lineWidth = getLineWidth(figAttribs.lineWidth);
        final float n2 = 1.0f;
        final float n3 = 0.0f;
        int n4 = 0;
        switch (figAttribs.fig_join_style) {
            case 0: {
                n4 = 0;
                break;
            }
            case 1: {
                n4 = 2;
                break;
            }
            case 2: {
                n4 = 1;
                break;
            }
            default: {
                n4 = 2;
                break;
            }
        }
        int n5 = 0;
        switch (figAttribs.fig_cap_style) {
            case 0: {
                n5 = 0;
                break;
            }
            case 1: {
                n5 = 1;
                break;
            }
            case 2: {
                n5 = 2;
                break;
            }
            default: {
                n5 = 0;
                break;
            }
        }
        checkRestoreDashLength(figAttribs);
        float[] array = null;
        switch (figAttribs.lineStyle) {
            case 0: {
                array = null;
                break;
            }
            case 1: {
                array = new float[] { (float)figAttribs.dashLength, (float)figAttribs.dashLength };
                break;
            }
            case 2: {
                array = new float[] { n, (float)figAttribs.dashLength };
                break;
            }
            case 3: {
                array = new float[] { (float)figAttribs.dashLength, lineWidth, lineWidth, lineWidth };
                break;
            }
            case 4: {
                array = new float[] { (float)figAttribs.dashLength, lineWidth, lineWidth, lineWidth, lineWidth, lineWidth };
                break;
            }
            case 5: {
                array = new float[] { (float)figAttribs.dashLength, lineWidth, lineWidth, lineWidth, lineWidth, lineWidth, lineWidth, lineWidth };
                break;
            }
            default: {
                msg("-W- unsupported line style: " + figAttribs.lineStyle);
                array = null;
                break;
            }
        }
        BasicStroke basicStroke;
        if (array != null) {
            try {
                basicStroke = new BasicStroke(n, n5, n4, n2, array, n3);
            }
            catch (Throwable t) {
                msg("-E- could not create dash/dot pattern stroke: " + t);
                msg("-E- using solid line instead...");
                basicStroke = new BasicStroke(n, n5, n4);
            }
        }
        else {
            basicStroke = new BasicStroke(n, n5, n4);
        }
        return basicStroke;
    }
    
    public static float getLineWidth(final double n) {
        if (!FigTools2D.thinLinesHack) {
            return (float)n;
        }
        if (n > 30.0) {
            return (float)(n - 30.0);
        }
        if (n > 0.0) {
            return 15.0f;
        }
        return 0.0f;
    }
    
    public static AffineTransform createCompoundTransform(final AffineTransform affineTransform, final FigTrafo2D figTrafo2D) {
        final AffineTransform transform = createTransform(figTrafo2D);
        transform.preConcatenate(affineTransform);
        return transform;
    }
    
    public static AffineTransform createTransform(final FigTrafo2D figTrafo2D) {
        final double n = figTrafo2D.getZoomFactor() / 32.0;
        return new AffineTransform(n, 0.0, 0.0, n, -1.0 * n * figTrafo2D.getAnchor().x, -1.0 * n * figTrafo2D.getAnchor().y);
    }
    
    public static Paint getTexture(final FigAttribs figAttribs) {
        return FillPatterns.getTexturePaint(figAttribs.fig_area_fill - 40 + 20, 32, figAttribs.lineColor, figAttribs.fillColor);
    }
    
    public static void showPoints(final Graphics graphics, final FigTrafo2D figTrafo2D, final Point[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final int wc_to_screen_x = figTrafo2D.wc_to_screen_x(array[i].x);
            final int wc_to_screen_y = figTrafo2D.wc_to_screen_y(array[i].y);
            graphics.setColor(Color.black);
            graphics.drawRect(wc_to_screen_x - 2, wc_to_screen_y - 2, 4, 4);
            graphics.setColor(Color.white);
            graphics.fillRect(wc_to_screen_x - 1, wc_to_screen_y - 1, 3, 3);
        }
    }
    
    public static void showPoints(final Graphics graphics, final FigTrafo2D figTrafo2D, final FigBbox figBbox) {
        showPoints(graphics, figTrafo2D, convertToPoints(figBbox));
    }
    
    public static void showSelected(final Graphics graphics, final FigTrafo2D figTrafo2D, final Point[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final int wc_to_screen_x = figTrafo2D.wc_to_screen_x(array[i].x);
            final int wc_to_screen_y = figTrafo2D.wc_to_screen_y(array[i].y);
            graphics.setColor(Color.white);
            graphics.drawRect(wc_to_screen_x - 2, wc_to_screen_y - 2, 4, 4);
            graphics.setColor(Color.black);
            graphics.fillRect(wc_to_screen_x - 1, wc_to_screen_y - 1, 3, 3);
        }
    }
    
    public static void showSelected(final Graphics graphics, final FigTrafo2D figTrafo2D, final FigBbox figBbox) {
        showSelected(graphics, figTrafo2D, convertToPoints(figBbox));
    }
    
    public static Point[] convertToPoints(final FigBbox figBbox) {
        if (figBbox == null) {
            return null;
        }
        final int xl = figBbox.getXl();
        final int yt = figBbox.getYt();
        final int xr = figBbox.getXr();
        final int yb = figBbox.getYb();
        return new Point[] { new Point(xl, yt), new Point(xr, yt), new Point(xr, yb), new Point(xl, yb) };
    }
    
    public static void msg(final String s) {
        System.err.println(s);
    }
    
    static {
        FigTools2D.thinLinesHack = true;
    }
}
