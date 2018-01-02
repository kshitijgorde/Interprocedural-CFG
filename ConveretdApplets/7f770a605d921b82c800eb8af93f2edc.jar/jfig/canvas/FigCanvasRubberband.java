// 
// Decompiled by Procyon v0.5.30
// 

package jfig.canvas;

import java.awt.FontMetrics;
import jfig.utils.Format;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jfig.utils.GeometryManager;
import java.awt.Font;
import java.awt.Polygon;
import jfig.objects.FigBbox;

public class FigCanvasRubberband
{
    public static final int noRubber = 0;
    public static final int pointRubber = 1;
    public static final int lineRubber = 2;
    public static final int line2Rubber = 3;
    public static final int rectRubber = 4;
    public static final int bboxRubber = 5;
    public static final int circleRubber = 6;
    public static final int ellipseRubber = 7;
    public static final int polylineRubber = 8;
    public static final int scaleRubber = 11;
    public static final int arcRubber = 12;
    public static final int restrictedBboxRubber = 13;
    public static final int restrictedScaleRubber = 14;
    public static final int geometryManagedRubber = 15;
    public static final int circle2Rubber = 16;
    public static final int RC = 0;
    public static final int LC = 1;
    public static final int CC = 2;
    public static final int CT = 3;
    public static final int CB = 5;
    public static final int RT = 6;
    public static final int RB = 7;
    public static final int LB = 8;
    public static final int LT = 9;
    int mode;
    Object obj;
    FigBbox bbox;
    FigTrafo2D trafo;
    Polygon pg;
    int base_x;
    int base_y;
    int base2_x;
    int base2_y;
    int old_x;
    int old_y;
    int x;
    int y;
    int dx;
    int dy;
    int offset_x;
    int offset_y;
    double aspect;
    boolean debug;
    boolean showLineLengthsEnable;
    Font mfont;
    GeometryManager geometryManager;
    
    public boolean setDebug(final boolean debug) {
        return this.debug = debug;
    }
    
    public boolean getDebug() {
        return this.debug;
    }
    
    public void setTrafo(final FigTrafo2D trafo) {
        this.trafo = trafo;
    }
    
    public void setShowLineLengths(final boolean showLineLengthsEnable) {
        this.showLineLengthsEnable = showLineLengthsEnable;
    }
    
    public void setBasePoint(final Point point) {
        this.base_x = point.x;
        this.base_y = point.y;
        if (this.debug) {
            System.out.println("FigCanvasRubberband.setBasePoint at (" + point.x + ", " + point.y + ") ");
        }
    }
    
    public void setBasePoint(final int base_x, final int base_y) {
        this.base_x = base_x;
        this.base_y = base_y;
        if (this.debug) {
            System.out.println("FigCanvasRubberband.setBasePoint at (" + base_x + ", " + base_y + ") ");
        }
    }
    
    public void setGeometryManager(final GeometryManager geometryManager) {
        this.geometryManager = geometryManager;
    }
    
    public void setBasePoint2(final Point point) {
        this.base2_x = point.x;
        this.base2_y = point.y;
    }
    
    public void setAspect(final double aspect) {
        this.aspect = aspect;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public void changeMode(final int mode) {
        this.mode = mode;
        this.obj = null;
        if (this.debug) {
            System.out.println("FigCanvasRubberband.changeMode: " + mode);
        }
    }
    
    public void changeMode(final int n, final GeometryManager geometryManager) {
        this.changeMode(n);
        this.setGeometryManager(geometryManager);
    }
    
    public void changeMode(final int mode, final FigTrafo2D trafo, final Object obj) {
        this.mode = mode;
        this.trafo = trafo;
        this.obj = obj;
        if (mode == 5) {
            final FigBbox figBbox = (FigBbox)obj;
            if (figBbox != null) {
                final Point point = new Point(figBbox.getXl(), figBbox.getYt());
                final Point point2 = new Point(figBbox.getXr(), figBbox.getYb());
                final Point point3 = new Point(0, 0);
                final Point point4 = new Point(0, 0);
                final Point wc_to_screen = trafo.wc_to_screen(point, point3);
                final Point wc_to_screen2 = trafo.wc_to_screen(point2, point4);
                this.offset_x = wc_to_screen.x - this.base_x;
                this.offset_y = wc_to_screen.y - this.base_y;
                this.dx = wc_to_screen2.x - wc_to_screen.x;
                this.dy = wc_to_screen2.y - wc_to_screen.y;
                if (this.dx <= 0) {
                    this.dx = 1;
                }
                if (this.dy <= 0) {
                    this.dy = 1;
                }
            }
            else {
                System.out.println("FigCanvasRubberband.changeMode: wrong object for bboxRubber mode! ");
                this.dx = 1;
                this.dy = 1;
            }
        }
        else if (mode == 8 && obj == null) {
            System.out.println("FigCanvasRubberband.changeMode: wrong object for polygonRubber mode! ");
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            array[0] = 100;
            array[1] = 105;
            array2[0] = 100;
            array2[1] = 105;
            final Polygon polygon = new Polygon(array, array2, 2);
        }
    }
    
    public void paint(final Graphics graphics, final int old_x, final int old_y, final boolean b) {
        if (this.mode == 0) {
            return;
        }
        graphics.setXORMode(Color.white);
        graphics.setColor(Color.black);
        if (b) {
            this.paintOnce(graphics, this.old_x, this.old_y);
        }
        this.paintOnce(graphics, old_x, old_y);
        this.old_x = old_x;
        this.old_y = old_y;
    }
    
    public Point getBasePoint() {
        return new Point(this.base_x, this.base_y);
    }
    
    public void paintOnce(final Graphics graphics, final int n, int n2) {
        if (this.mode == 0) {
            return;
        }
        if (this.debug) {
            System.out.println("FigCanvasRubberband.paintOnce(): mode: " + this.mode + " at (" + n + ", " + n2 + ") from base at (" + this.base_x + ", " + this.base_y + ") ");
        }
        switch (this.mode) {
            case 1: {
                graphics.drawLine(n - 3, n2, n + 3, n2);
                graphics.drawLine(n, n2 - 3, n, n2 + 3);
                break;
            }
            case 2: {
                if (this.showLineLengthsEnable) {
                    graphics.drawLine(this.base_x, this.base_y, n, n2);
                    final int n3 = n - this.base_x;
                    final int n4 = n2 - this.base_y;
                    final int n5 = (int)(0.5 + Math.sqrt(n3 * n3 + n4 * n4));
                    final Color color = graphics.getColor();
                    graphics.setColor(Color.red);
                    graphics.setFont(this.mfont);
                    if (n3 > 0) {
                        if (n4 > 0) {
                            graphics.drawLine(this.base_x, n2, n, n2);
                            graphics.drawLine(this.base_x, this.base_y, this.base_x, n2);
                            this.measure(graphics, n3, (n + this.base_x) / 2, n2, 5);
                            this.measure(graphics, n4, this.base_x, (n2 + this.base_y) / 2, 1);
                            this.measure(graphics, n5, (n + this.base_x) / 2, (n2 + this.base_y) / 2, 6);
                        }
                        else if (n4 < 0) {
                            graphics.drawLine(this.base_x, n2, n, n2);
                            graphics.drawLine(this.base_x, this.base_y, this.base_x, n2);
                            this.measure(graphics, n3, (n + this.base_x) / 2, n2, 3);
                            this.measure(graphics, n4, this.base_x, (n2 + this.base_y) / 2, 1);
                            this.measure(graphics, n5, (n + this.base_x) / 2, (n2 + this.base_y) / 2, 7);
                        }
                        else {
                            this.measure(graphics, n5, (n + this.base_x) / 2, this.base_y, 6);
                        }
                    }
                    else if (n3 < 0) {
                        if (n4 > 0) {
                            graphics.drawLine(this.base_x, n2, n, n2);
                            graphics.drawLine(this.base_x, this.base_y, this.base_x, n2);
                            this.measure(graphics, n3, (n + this.base_x) / 2, n2, 5);
                            this.measure(graphics, n4, this.base_x, (n2 + this.base_y) / 2, 0);
                            this.measure(graphics, n5, (n + this.base_x) / 2, (n2 + this.base_y) / 2, 9);
                        }
                        else if (n4 < 0) {
                            graphics.drawLine(this.base_x, n2, n, n2);
                            graphics.drawLine(this.base_x, this.base_y, this.base_x, n2);
                            this.measure(graphics, n4, this.base_x, (n2 + this.base_y) / 2, 0);
                            this.measure(graphics, n3, (n + this.base_x) / 2, n2, 3);
                            this.measure(graphics, n5, (n + this.base_x) / 2, (n2 + this.base_y) / 2, 8);
                        }
                        else {
                            this.measure(graphics, n5, this.base_x, (n2 + this.base_y) / 2, 9);
                        }
                    }
                    else {
                        this.measure(graphics, n5, this.base_x, (n2 + this.base_y) / 2, 0);
                    }
                    graphics.setColor(color);
                }
                else {
                    graphics.drawLine(this.base_x, this.base_y, n, n2);
                }
                break;
            }
            case 3: {
                graphics.drawLine(this.base_x, this.base_y, n, n2);
                graphics.drawLine(this.base2_x, this.base2_y, n, n2);
                break;
            }
            case 4: {
                final int n6 = n - this.base_x;
                final int n7 = n2 - this.base_y;
                graphics.drawLine(this.base_x, this.base_y, n, this.base_y);
                graphics.drawLine(this.base_x, this.base_y, this.base_x, n2);
                if (n7 != 0) {
                    graphics.drawLine(this.base_x, n2, n, n2);
                }
                if (n6 != 0) {
                    graphics.drawLine(n, this.base_y, n, n2);
                }
                if (this.showLineLengthsEnable) {
                    final Color color2 = graphics.getColor();
                    graphics.setFont(this.mfont);
                    if (n6 > 0) {
                        this.measure(graphics, n6, n, (n2 + this.base_y) / 2, 0);
                    }
                    else if (n6 < 0) {
                        this.measure(graphics, n6, n, (n2 + this.base_y) / 2, 1);
                    }
                    if (n7 > 0) {
                        this.measure(graphics, n7, (n + this.base_x) / 2, n2, 5);
                    }
                    else if (n7 < 0) {
                        this.measure(graphics, n7, (n + this.base_x) / 2, n2, 3);
                    }
                    graphics.setColor(color2);
                }
                break;
            }
            case 5: {
                graphics.drawRect(this.offset_x + n, this.offset_y + n2, this.dx, this.dy);
                break;
            }
            case 13: {
                int base_x = n;
                int base_y = n2;
                if (Math.abs(base_x - this.base_x) > Math.abs(base_y - this.base_y)) {
                    base_y = this.base_y;
                }
                else {
                    base_x = this.base_x;
                }
                graphics.drawRect(this.offset_x + base_x, this.offset_y + base_y, this.dx, this.dy);
                break;
            }
            case 6: {
                final int max = Math.max(Math.abs(n - this.base_x), Math.abs(n2 - this.base_y));
                graphics.drawOval(this.base_x - max, this.base_y - max, 2 * max, 2 * max);
                break;
            }
            case 16: {
                final int n8 = n - this.base_x;
                final int n9 = n2 - this.base_y;
                final int n10 = (int)Math.sqrt(n8 * n8 + n9 * n9);
                graphics.drawOval(this.base_x - n10, this.base_y - n10, 2 * n10, 2 * n10);
                break;
            }
            case 7: {
                graphics.drawOval((n < this.base_x) ? n : (2 * this.base_x - n), (n2 < this.base_y) ? n2 : (2 * this.base_y - n2), Math.abs(2 * (n - this.base_x)), Math.abs(2 * (n2 - this.base_y)));
                break;
            }
            case 8: {
                graphics.drawPolygon(this.pg);
                break;
            }
            case 11: {
                graphics.drawRect((n < this.base_x) ? n : (2 * this.base_x - n), (n2 < this.base_y) ? n2 : (2 * this.base_y - n2), Math.abs(2 * (n - this.base_x)), Math.abs(2 * (n2 - this.base_y)));
                break;
            }
            case 14: {
                n2 = this.base_y + (int)(this.aspect * (n - this.base_x) + 0.5);
                graphics.drawRect((n < this.base_x) ? n : (2 * this.base_x - n), (n2 < this.base_y) ? n2 : (2 * this.base_y - n2), Math.abs(2 * (n - this.base_x)), Math.abs(2 * (n2 - this.base_y)));
                break;
            }
            case 12: {
                graphics.drawLine(this.base2_x, this.base2_y, this.base_x, this.base_y);
                graphics.drawLine(this.base2_x, this.base2_y, n, n2);
                break;
            }
            case 15: {
                final Point nearestAllowedPoint = this.geometryManager.getNearestAllowedPoint(this.getBasePoint(), new Point(n, n2));
                graphics.drawLine(this.base_x, this.base_y, nearestAllowedPoint.x, nearestAllowedPoint.y);
                break;
            }
            default: {
                System.out.println("FigCanvasRubberband.paintOnce(): mode not supported " + this.mode);
                break;
            }
        }
    }
    
    public Rectangle getRubberBoundingBox(final int n, int n2, final int n3, int n4) {
        int n5 = 0;
        int n6 = 0;
        int width = 0;
        int height = 0;
        switch (this.mode) {
            case 0:
            case 1: {
                n5 = Math.min(n, n3) - 3;
                n6 = Math.min(n2, n4) - 3;
                width = Math.max(n, n3) - n5 + 7;
                height = Math.max(n2, n4) - n6 + 7;
                break;
            }
            case 2: {
                if (this.showLineLengthsEnable) {
                    n5 = min(n, n3, this.base_x) - 60;
                    n6 = min(n2, n4, this.base_y) - 20;
                    width = max(n, n3, this.base_x) - n5 + 121;
                    height = max(n2, n4, this.base_y) - n6 + 41;
                }
                else {
                    n5 = min(n, n3, this.base_x);
                    n6 = min(n2, n4, this.base_y);
                    width = max(n, n3, this.base_x) - n5 + 1;
                    height = max(n2, n4, this.base_y) - n6 + 1;
                }
                break;
            }
            case 3: {
                n5 = min(n, n3, this.base_x, this.base2_x);
                n6 = min(n2, n4, this.base_y, this.base2_y);
                width = max(n, n3, this.base_x, this.base2_x) - n5 + 1;
                height = max(n2, n4, this.base_y, this.base2_y) - n6 + 1;
                break;
            }
            case 4: {
                if (this.showLineLengthsEnable) {
                    n5 = min(n, n3, this.base_x) - 60;
                    n6 = min(n2, n4, this.base_y) - 20;
                    width = max(n, n3, this.base_x) - n5 + 121;
                    height = max(n2, n4, this.base_y) - n6 + 41;
                }
                else {
                    n5 = min(n, n3, this.base_x);
                    n6 = min(n2, n4, this.base_y);
                    width = max(n, n3, this.base_x) - n5 + 1;
                    height = max(n2, n4, this.base_y) - n6 + 1;
                }
                break;
            }
            case 5: {
                n5 = Math.min(n, n3) - Math.abs(this.offset_x);
                n6 = Math.min(n2, n4) - Math.abs(this.offset_y);
                width = this.dx + 2 * Math.abs(this.offset_x) + Math.abs(n - n3) + 1;
                height = this.dy + 2 * Math.abs(this.offset_y) + Math.abs(n2 - n4) + 1;
                break;
            }
            case 13: {
                int base_x = n3;
                int base_y = n4;
                if (Math.abs(base_x - this.base_x) > Math.abs(base_y - this.base_y)) {
                    base_y = this.base_y;
                }
                else {
                    base_x = this.base_x;
                }
                int base_x2 = n;
                int base_y2 = n2;
                if (Math.abs(base_x2 - this.base_x) > Math.abs(base_y2 - this.base_y)) {
                    base_y2 = this.base_y;
                }
                else {
                    base_x2 = this.base_x;
                }
                final int n7 = min(n3, n, base_x2, base_x) - Math.abs(this.offset_x);
                final int n8 = min(n4, n2, base_y2, base_y) - Math.abs(this.offset_y);
                final int n9 = max(n3, n, base_x2, base_x) + Math.abs(this.offset_x);
                final int n10 = max(n4, n2, base_y2, base_y) + Math.abs(this.offset_y);
                n5 = n7;
                n6 = n8;
                width = n9 - n7 + this.dx + 1;
                height = n10 - n8 + this.dy + 1;
                break;
            }
            case 16: {
                final int n11 = n3 - this.base_x;
                final int n12 = n4 - this.base_y;
                final int n13 = (int)Math.sqrt(n11 * n11 + n12 * n12);
                final int n14 = n - this.base_x;
                final int n15 = n2 - this.base_y;
                final int max = Math.max(n13, (int)Math.sqrt(n14 * n14 + n15 * n15));
                n5 = this.base_x - max;
                n6 = this.base_y - max;
                width = 2 * max + 1;
                height = 2 * max + 1;
                break;
            }
            case 6: {
                final int max2 = Math.max(Math.max(Math.abs(n3 - this.base_x), Math.abs(n4 - this.base_y)), Math.max(Math.abs(n - this.base_x), Math.abs(n2 - this.base_y)));
                n5 = this.base_x - max2;
                n6 = this.base_y - max2;
                width = 2 * max2 + 1;
                height = 2 * max2 + 1;
                break;
            }
            case 7: {
                final int max3 = Math.max(Math.abs(n3 - this.base_x), Math.abs(n - this.base_x));
                final int max4 = Math.max(Math.abs(n4 - this.base_y), Math.abs(n2 - this.base_y));
                n5 = this.base_x - max3;
                n6 = this.base_y - max4;
                width = 2 * max3 + 1;
                height = 2 * max4 + 1;
                break;
            }
            case 8: {
                final Rectangle bounds = this.pg.getBounds();
                n5 = bounds.x;
                n6 = bounds.y;
                width = bounds.width;
                height = bounds.height;
                break;
            }
            case 11: {
                final int max5 = Math.max(Math.abs(n3 - this.base_x), Math.abs(n - this.base_x));
                final int max6 = Math.max(Math.abs(n4 - this.base_y), Math.abs(n2 - this.base_y));
                n5 = this.base_x - max5;
                n6 = this.base_y - max6;
                width = 2 * max5 + 1;
                height = 2 * max6 + 1;
                break;
            }
            case 14: {
                n2 = this.base_y + (int)(this.aspect * (n - this.base_x) + 0.5);
                n4 = this.base_y + (int)(this.aspect * (n3 - this.base_x) + 0.5);
                final int max7 = Math.max(Math.abs(n3 - this.base_x), Math.abs(n - this.base_x));
                final int max8 = Math.max(Math.abs(n4 - this.base_y), Math.abs(n2 - this.base_y));
                n5 = this.base_x - max7 - 5;
                n6 = this.base_y - max8 - 5;
                width = 2 * max7 + 1 + 10;
                height = 2 * max8 + 1 + 10;
                break;
            }
            case 12: {
                n5 = min(n, n3, this.base_x, this.base2_x);
                n6 = min(n2, n4, this.base_y, this.base2_y);
                width = max(n, n3, this.base_x, this.base2_x) - n5 + 1;
                height = max(n2, n4, this.base_y, this.base2_y) - n6 + 1;
                break;
            }
            case 15: {
                final Point point = new Point(this.base_x, this.base_y);
                final Point point2 = new Point(n, n2);
                final Point nearestAllowedPoint = this.geometryManager.getNearestAllowedPoint(point, point2);
                final Point point3 = new Point(n3, n4);
                final Point nearestAllowedPoint2 = this.geometryManager.getNearestAllowedPoint(point, point3);
                n5 = min(point2.x, point3.x, nearestAllowedPoint.x, nearestAllowedPoint2.x, point.x);
                n6 = min(point2.y, point3.y, nearestAllowedPoint.y, nearestAllowedPoint2.y, point.y);
                width = max(point2.x, point3.x, nearestAllowedPoint.x, nearestAllowedPoint2.x, point.x) - n5 + 1;
                height = max(point2.y, point3.y, nearestAllowedPoint.y, nearestAllowedPoint2.y, point.y) - n6 + 1;
                break;
            }
            default: {
                System.out.println("FigCanvasRubberband.paintOnce(): mode not supported " + this.mode);
                break;
            }
        }
        return new Rectangle(n5, n6, width, height);
    }
    
    void measure(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final String form = new Format("%4.3f").form(this.trafo.getValueInUnits((int)(32.0 * n / this.trafo.getZoomFactor())));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(form);
        final int maxAscent = fontMetrics.getMaxAscent();
        fontMetrics.getMaxDescent();
        graphics.setColor(Color.red);
        switch (n4) {
            case 0: {
                graphics.drawString(form, n2 + 5, n3);
                break;
            }
            case 1: {
                graphics.drawString(form, n2 - 5 - stringWidth, n3);
                break;
            }
            case 2: {
                graphics.drawString(form, n2 - stringWidth / 2, n3);
                break;
            }
            case 3: {
                graphics.drawString(form, n2 - stringWidth / 2, n3 - 5);
                break;
            }
            case 5: {
                graphics.drawString(form, n2 - stringWidth / 2, n3 + 5 + maxAscent);
                break;
            }
            case 6: {
                graphics.drawString(form, n2 + 5, n3 - 5);
                break;
            }
            case 7: {
                graphics.drawString(form, n2 + 5, n3 + 5 + maxAscent);
                break;
            }
            case 9: {
                graphics.drawString(form, n2 - 5 - stringWidth, n3 - 5);
                break;
            }
            case 8: {
                graphics.drawString(form, n2 - 5 - stringWidth, n3 + 5 + maxAscent);
                break;
            }
            default: {
                System.out.println("measure: unknown alignment..." + n4);
                break;
            }
        }
    }
    
    public static final int min(final int n, final int n2, final int n3) {
        return Math.min(n, Math.min(n2, n3));
    }
    
    public static final int min(final int n, final int n2, final int n3, final int n4) {
        return Math.min(Math.min(n, n2), Math.min(n3, n4));
    }
    
    public static final int min(final int n, final int n2, final int n3, final int n4, final int n5) {
        return min(n, n2, min(n3, n4, n5));
    }
    
    public static final int max(final int n, final int n2, final int n3) {
        return Math.max(n, Math.max(n2, n3));
    }
    
    public static final int max(final int n, final int n2, final int n3, final int n4) {
        return Math.max(Math.max(n, n2), Math.max(n3, n4));
    }
    
    public static final int max(final int n, final int n2, final int n3, final int n4, final int n5) {
        return max(n, n2, max(n3, n4, n5));
    }
    
    public FigCanvasRubberband(final FigTrafo2D trafo) {
        this.debug = false;
        this.showLineLengthsEnable = false;
        this.mfont = new Font("Monospaced", 0, 10);
        this.geometryManager = null;
        this.mode = 1;
        this.trafo = trafo;
    }
}
