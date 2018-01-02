// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFColor;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Image;
import java.awt.Color;

public final class NFGrid
{
    public Color background;
    public Color lineColor;
    public Color borderColor;
    public int lineStyle;
    public int lineWidth;
    public boolean showBackground;
    public boolean showGrid;
    public boolean showBorder;
    public boolean showHorizontal;
    public boolean showVertical;
    public Image im;
    public int imageType;
    public NFAxis XAxis;
    public NFAxis YAxis;
    public NFAxis ZAxis;
    public int axisThickness;
    public Color ribColor;
    public static final int BOTTOMLEFT = 0;
    public static final int BOTTOMRIGHT = 1;
    public static final int TOPLEFT = 2;
    public static final int TOPRIGHT = 3;
    public NFSpacing verticalSpacing;
    public NFSpacing horizontalSpacing;
    NFAxis a;
    NFAxis b;
    private Polygon c;
    private Polygon d;
    private Graphics e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private NFActiveLabel y;
    
    public NFGrid() {
        this.background = Color.gray;
        this.lineColor = Color.gray.darker();
        this.borderColor = Color.gray.darker();
        this.lineStyle = 1;
        this.lineWidth = 1;
        this.showBackground = false;
        this.showGrid = false;
        this.showBorder = false;
        this.showHorizontal = true;
        this.showVertical = true;
        this.im = null;
        this.imageType = 0;
        this.XAxis = null;
        this.YAxis = null;
        this.ZAxis = null;
        this.axisThickness = 0;
        this.ribColor = null;
        this.verticalSpacing = null;
        this.horizontalSpacing = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = null;
    }
    
    public NFGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.background = Color.gray;
        this.lineColor = Color.gray.darker();
        this.borderColor = Color.gray.darker();
        this.lineStyle = 1;
        this.lineWidth = 1;
        this.showBackground = false;
        this.showGrid = false;
        this.showBorder = false;
        this.showHorizontal = true;
        this.showVertical = true;
        this.im = null;
        this.imageType = 0;
        this.XAxis = null;
        this.YAxis = null;
        this.ZAxis = null;
        this.axisThickness = 0;
        this.ribColor = null;
        this.verticalSpacing = null;
        this.horizontalSpacing = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = null;
        new NFGrid(graphics, n, n2, n3, n4, null);
    }
    
    public NFGrid(final Graphics e, final int n, final int n2, final int n3, final int n4, final Image image) {
        this.background = Color.gray;
        this.lineColor = Color.gray.darker();
        this.borderColor = Color.gray.darker();
        this.lineStyle = 1;
        this.lineWidth = 1;
        this.showBackground = false;
        this.showGrid = false;
        this.showBorder = false;
        this.showHorizontal = true;
        this.showVertical = true;
        this.im = null;
        this.imageType = 0;
        this.XAxis = null;
        this.YAxis = null;
        this.ZAxis = null;
        this.axisThickness = 0;
        this.ribColor = null;
        this.verticalSpacing = null;
        this.horizontalSpacing = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = null;
        this.e = e;
        this.setGrid(n, n2, n3, n4, image);
    }
    
    public NFGrid(final Graphics e, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.background = Color.gray;
        this.lineColor = Color.gray.darker();
        this.borderColor = Color.gray.darker();
        this.lineStyle = 1;
        this.lineWidth = 1;
        this.showBackground = false;
        this.showGrid = false;
        this.showBorder = false;
        this.showHorizontal = true;
        this.showVertical = true;
        this.im = null;
        this.imageType = 0;
        this.XAxis = null;
        this.YAxis = null;
        this.ZAxis = null;
        this.axisThickness = 0;
        this.ribColor = null;
        this.verticalSpacing = null;
        this.horizontalSpacing = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = null;
        this.e = e;
        this.setGrid(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void setGrid(final int n, final int n2, final int n3, final int n4) {
        this.setGrid(n, n2, n3, n4, null);
    }
    
    public void setGrid(final int f, final int g, final int n, final int n2, final Image im) {
        this.im = im;
        this.f = f;
        this.g = g;
        this.h = n;
        this.i = n2;
        this.m = n;
        this.l = 0;
        this.n = 0;
        this.o = n2;
        (this.c = new Polygon()).addPoint(f, g);
        this.c.addPoint(f, g + n2 - 1);
        this.c.addPoint(f + n, g + n2 - 1);
        this.c.addPoint(f + n, g);
        this.c.addPoint(f, g);
    }
    
    public void setGrid(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.setGrid(n, n2, n3, n4, n5, n6, n7, n8, 0);
    }
    
    public void setGrid(final int n, final int n2, final int f, final int g, final int n3, final int n4, final int n5, final int n6, final int n7) {
        (this.c = new Polygon()).addPoint(n, n2);
        this.c.addPoint(f, g);
        this.c.addPoint(n3, n4);
        this.c.addPoint(n5, n6);
        this.c.addPoint(n, n2);
        this.m = Math.abs(n5 - n);
        this.l = Math.abs(n2 - n6);
        this.n = Math.abs(n - f);
        this.o = Math.abs(n6 - n4);
        if (n7 == 2 || n7 == 3) {
            this.l = -this.l;
            this.o = -(this.o - 1);
        }
        if (n7 == 1 || n7 == 3) {
            this.m = -this.m;
            this.n = -this.n;
        }
        this.f = f;
        this.g = g;
        this.h = this.m;
        this.i = this.o;
    }
    
    public void setAxis(final NFAxis xAxis, final NFAxis yAxis, final NFAxis zAxis) {
        this.XAxis = xAxis;
        this.YAxis = yAxis;
        this.ZAxis = zAxis;
    }
    
    public void setDepth(final int p) {
        this.p = p;
    }
    
    private void a() {
        if (this.XAxis == null || this.YAxis == null) {
            return;
        }
        final boolean b = this.p > 0 && this.axisThickness > 0;
        final int x = this.YAxis.mapValue(this.YAxis.getMin()).x;
        final Point point = new Point(x, this.YAxis.getMaxCoord());
        final Point point2 = new Point(x, this.YAxis.getMinCoord());
        if (this.YAxis.getTicPosition() == 3) {
            this.t = point.y + (b ? this.axisThickness : 0);
            this.s = this.t + this.p;
            this.r = point2.y + (b ? this.axisThickness : 0);
            this.q = this.r + this.p;
        }
        else {
            this.s = point.y;
            this.t = this.s - this.p;
            this.q = point2.y;
            this.r = this.q - this.p;
        }
        final int y = this.XAxis.mapValue(this.XAxis.getMin()).y;
        final Point point3 = new Point(this.XAxis.getMinCoord(), y);
        final Point point4 = new Point(this.XAxis.getMaxCoord(), y);
        if (this.XAxis.getTicPosition() == 1) {
            this.v = point3.x - (b ? this.axisThickness : 0);
            this.u = this.v - this.p;
            this.x = point4.x - (b ? this.axisThickness : 0);
            this.w = this.x - this.p;
        }
        else {
            this.u = point3.x;
            this.v = this.u + this.p;
            this.w = point4.x;
            this.x = this.w + this.p;
        }
    }
    
    private void a(final int n, final int n2, final int n3) {
        if (this.c == null) {
            (this.c = new Polygon()).addPoint(0, 0);
            this.c.addPoint(0, 0);
            this.c.addPoint(0, 0);
            this.c.addPoint(0, 0);
            this.c.addPoint(0, 0);
            this.c.addPoint(0, 0);
            this.c.addPoint(0, 0);
        }
        this.c.xpoints[n] = n2;
        this.c.ypoints[n] = n3;
        this.c.npoints = n + 1;
    }
    
    private Polygon a(final Graphics graphics) {
        int n = this.v;
        int n2 = this.r;
        final int n3 = this.x - this.v;
        final int n4 = this.t - this.r;
        int n5 = n3;
        int n6 = n4;
        if (n5 < 0) {
            n = this.x;
            n5 = -n5;
        }
        if (n6 < 0) {
            n2 = this.t;
            n6 = -n6;
        }
        if (this.showBackground) {
            if (this.background != null) {
                graphics.setColor(this.background);
                graphics.fillRect(n, n2, n5, n6);
            }
            if (this.im != null) {
                NFImage.drawBackgroundImage(graphics, this.im, this.imageType, n, n2, n5, n6);
            }
        }
        if (this.showGrid && this.lineColor != null) {
            graphics.setColor(this.lineColor);
            final int x = this.YAxis.mapValue(this.YAxis.getMin()).x;
            final int y = this.XAxis.mapValue(this.XAxis.getMin()).y;
            if (this.YAxis.getTicPosition() == 3) {
                this.a(graphics, this.YAxis, -n3 - (x - this.x), n3, 0, 0);
            }
            else {
                this.a(graphics, this.YAxis, this.u - x, n3, 0, 0);
            }
            if (this.XAxis.getTicPosition() == 1) {
                this.b(graphics, this.XAxis, -n4 - (this.r - y), n4, 0, 0);
            }
            else {
                this.b(graphics, this.XAxis, y - this.s, n4, 0, 0);
            }
        }
        if (this.showBorder && this.borderColor != null) {
            graphics.setColor(this.borderColor);
            graphics.drawRect(n, n2, n5, n6);
        }
        this.a(0, n, n2);
        this.a(1, n + n5, n2);
        this.a(2, n + n5, n2 + n6);
        this.a(3, n, n2 + n6);
        this.a(4, n, n2);
        return this.c;
    }
    
    public void setAxisThickness(final int axisThickness) {
        this.axisThickness = axisThickness;
    }
    
    private Polygon b(final Graphics graphics) {
        final boolean b = this.p > 0 && this.axisThickness > 0;
        this.a(0, this.u, this.s);
        this.a(1, this.u, this.q);
        this.a(2, this.v, this.r);
        this.a(3, this.x, this.r);
        this.a(4, this.x, this.t);
        this.a(5, this.w, this.s);
        this.a(6, this.u, this.s);
        if (this.showBackground) {
            if (this.background != null) {
                graphics.setColor(this.background);
                final Polygon polygon = new Polygon();
                polygon.addPoint(this.v, this.t);
                polygon.addPoint(this.x, this.t);
                polygon.addPoint(this.x, this.r);
                polygon.addPoint(this.v, this.r);
                polygon.addPoint(this.v, this.t);
                graphics.fillPolygon(polygon);
                graphics.setColor(NFColor.darker(this.background));
                final Polygon polygon2 = new Polygon();
                polygon2.addPoint(this.u, this.q);
                polygon2.addPoint(this.v, this.r);
                polygon2.addPoint(this.v, this.t);
                polygon2.addPoint(this.u, this.s);
                polygon2.addPoint(this.u, this.q);
                graphics.fillPolygon(polygon2);
                final Polygon polygon3 = new Polygon();
                polygon3.addPoint(this.u, this.s);
                polygon3.addPoint(this.v, this.t);
                polygon3.addPoint(this.x, this.t);
                polygon3.addPoint(this.w, this.s);
                polygon3.addPoint(this.u, this.s);
                graphics.fillPolygon(polygon3);
                if (b) {
                    graphics.setColor(this.background);
                    final Polygon polygon4 = new Polygon();
                    polygon4.addPoint(this.u, this.s);
                    polygon4.addPoint(this.w, this.s);
                    polygon4.addPoint(this.w, this.s + this.axisThickness);
                    polygon4.addPoint(this.u - this.axisThickness, this.s + this.axisThickness);
                    polygon4.addPoint(this.u - this.axisThickness, this.q);
                    polygon4.addPoint(this.u, this.q);
                    polygon4.addPoint(this.u, this.s);
                    graphics.fillPolygon(polygon4);
                    graphics.setColor(NFColor.brighter(this.background));
                    final Polygon polygon5 = new Polygon();
                    polygon5.addPoint(this.u, this.q);
                    polygon5.addPoint(this.v, this.r);
                    polygon5.addPoint(this.x, this.r);
                    polygon5.addPoint(this.x + this.axisThickness, this.r - this.axisThickness);
                    polygon5.addPoint(this.v, this.r - this.axisThickness);
                    polygon5.addPoint(this.u - this.axisThickness, this.q);
                    polygon5.addPoint(this.u, this.q);
                    graphics.fillPolygon(polygon5);
                    graphics.setColor(NFColor.darker(this.background));
                    final Polygon polygon6 = new Polygon();
                    polygon6.addPoint(this.x, this.r);
                    polygon6.addPoint(this.x, this.t);
                    polygon6.addPoint(this.w, this.s);
                    polygon6.addPoint(this.w, this.s + this.axisThickness);
                    polygon6.addPoint(this.x + this.axisThickness, this.t);
                    polygon6.addPoint(this.x + this.axisThickness, this.r - this.axisThickness);
                    polygon6.addPoint(this.x, this.r);
                    graphics.fillPolygon(polygon6);
                }
            }
            if (this.im != null) {
                NFImage.drawBackgroundImage(graphics, this.im, this.imageType, this.v, this.r, this.x - this.v, this.t - this.r);
            }
        }
        if (this.showGrid && this.lineColor != null) {
            graphics.setColor(this.lineColor);
            final int n = this.x - this.v;
            final int x = this.YAxis.mapValue(this.YAxis.getMin()).x;
            final int y = this.XAxis.mapValue(this.XAxis.getMin()).y;
            if (this.YAxis.getTicPosition() == 3) {
                this.a(graphics, this.YAxis, -n - (x - this.x) - this.p, this.p, -this.p - (b ? this.axisThickness : 0), b ? (-this.axisThickness) : 0);
                this.a(graphics, this.YAxis, -n - (x - this.x), n, b ? (-this.axisThickness) : 0, b ? (-this.axisThickness) : 0);
                if (b) {
                    this.a(graphics, this.YAxis, -(x - this.x), this.axisThickness, -this.axisThickness, 0);
                }
            }
            else {
                this.a(graphics, this.YAxis, this.u - x, this.p, 0, this.p);
                this.a(graphics, this.YAxis, this.u - x + this.p, n, this.p, this.p);
                if (b) {
                    this.a(graphics, this.YAxis, this.u - x - this.axisThickness, this.axisThickness, 0, 0);
                }
            }
            this.a(graphics, this.ZAxis, 0, -n, 0, 0);
            final int n2 = this.t - this.r;
            if (this.XAxis.getTicPosition() == 1) {
                this.b(graphics, this.XAxis, -n2 - (this.r - y), n2, b ? (-this.axisThickness) : 0, b ? (-this.axisThickness) : 0);
                this.b(graphics, this.XAxis, -n2 - (this.r - y), -this.p, b ? (-this.axisThickness) : 0, -this.p - (b ? this.axisThickness : 0));
                if (b) {
                    this.b(graphics, this.XAxis, -(this.r - y) + this.axisThickness, -this.axisThickness, 0, -this.axisThickness);
                }
            }
            else {
                this.b(graphics, this.XAxis, y - this.s + this.p, n2, this.p, this.p);
                this.b(graphics, this.XAxis, y - this.s, this.p, 0, this.p);
                if (b) {
                    this.b(graphics, this.XAxis, y - this.s - this.axisThickness, this.axisThickness, 0, 0);
                }
            }
            this.b(graphics, this.ZAxis, 0, n2, -n, -n);
        }
        if (this.showBorder && this.borderColor != null) {
            graphics.setColor(this.borderColor);
            graphics.drawPolygon(this.c);
            if (b) {
                final Polygon polygon7 = new Polygon();
                polygon7.addPoint(this.u, this.s);
                polygon7.addPoint(this.w, this.s);
                polygon7.addPoint(this.x, this.t);
                polygon7.addPoint(this.x, this.t + this.axisThickness);
                polygon7.addPoint(this.w, this.s + this.axisThickness);
                polygon7.addPoint(this.u - this.axisThickness, this.s + this.axisThickness);
                polygon7.addPoint(this.u - this.axisThickness, this.q);
                polygon7.addPoint(this.v, this.r - this.axisThickness);
                polygon7.addPoint(this.x + this.axisThickness, this.r - this.axisThickness);
                polygon7.addPoint(this.x + this.axisThickness, this.t);
                polygon7.addPoint(this.x, this.t + this.axisThickness);
                polygon7.addPoint(this.x, this.r);
                polygon7.addPoint(this.v, this.r);
                polygon7.addPoint(this.u, this.q);
                polygon7.addPoint(this.u, this.s);
                graphics.drawPolygon(polygon7);
                graphics.drawLine(this.u, this.q, this.u - this.axisThickness, this.q);
                graphics.drawLine(this.v, this.r, this.v - this.axisThickness, this.r);
                graphics.drawLine(this.x, this.r, this.x + this.axisThickness, this.r - this.axisThickness);
                graphics.drawLine(this.w, this.s, this.w, this.s + this.axisThickness);
                graphics.drawLine(this.u, this.s, this.u, this.s + this.axisThickness);
                graphics.drawLine(this.u, this.s, this.u - this.axisThickness, this.s);
            }
            graphics.drawLine(this.v, this.t, this.u, this.s);
            graphics.drawLine(this.v, this.t, this.v, this.r);
            graphics.drawLine(this.v, this.t, this.x, this.t);
        }
        return this.c;
    }
    
    private void a(final Graphics graphics, final NFAxis nfAxis, final int n, final int n2, final int n3, final int n4) {
        if (!this.showHorizontal) {
            return;
        }
        final NFSpacing spacing = nfAxis.getSpacing();
        if (spacing == null || spacing.size() == 0) {
            return;
        }
        final Point mapValue = nfAxis.mapValue(nfAxis.getMin());
        final Point mapValue2 = nfAxis.mapValue(nfAxis.getMax());
        final Point point2;
        Point point = point2 = mapValue;
        point2.x += n;
        final int size = spacing.size();
        int n5 = 0;
        final boolean drawMinorTics = nfAxis.getDrawMinorTics();
        final int skipCount = nfAxis.getSkipCount();
        for (int i = 0; i < size; ++i) {
            final Point nthPoint = nfAxis.getNthPoint(i);
            if (skipCount <= 0 || i % (skipCount + 1) == 0 || drawMinorTics) {
                final Point point3 = nthPoint;
                point3.x += n;
                if (nthPoint.y >= mapValue2.y) {
                    if (nthPoint.y <= mapValue.y) {
                        if (this.lineStyle != 0) {
                            if (this.lineStyle != 9) {
                                NFLine.draw(graphics, nthPoint.x, nthPoint.y - n3, nthPoint.x + n2, nthPoint.y - n4, this.lineWidth, this.lineStyle, this.lineColor);
                            }
                            else {
                                if (point.y < mapValue2.y) {
                                    point.y = mapValue2.y;
                                }
                                if (point.y > mapValue.y) {
                                    point.y = mapValue.y;
                                }
                                this.a(graphics, n5++, point, nthPoint, n2, n3, n4);
                            }
                        }
                        point = nthPoint;
                    }
                }
            }
        }
        if (this.lineStyle == 9 && point.y < mapValue.y) {
            if (point.y < mapValue2.y) {
                point.y = mapValue2.y;
            }
            if (point.y > mapValue.y) {
                point.y = mapValue.y;
            }
            final Point point4 = mapValue2;
            point4.x += n;
            this.a(graphics, n5, point, mapValue2, n2, n3, n4);
        }
    }
    
    private void b(final int n, final int n2, final int n3) {
        if (this.d == null) {
            (this.d = new Polygon()).addPoint(0, 0);
            this.d.addPoint(0, 0);
            this.d.addPoint(0, 0);
            this.d.addPoint(0, 0);
            this.d.addPoint(0, 0);
        }
        this.d.xpoints[n] = n2;
        this.d.ypoints[n] = n3;
        this.d.npoints = n + 1;
    }
    
    private void a(final Graphics graphics, final int n, final Point point, final Point point2, final int n2, final int n3, final int n4) {
        if (n % 2 == 1) {
            return;
        }
        this.b(0, point.x, point.y - n3);
        this.b(1, point.x + n2, point.y - n4);
        this.b(2, point2.x + n2, point2.y - n4);
        this.b(3, point2.x, point2.y - n3);
        this.b(4, point.x, point.y - n3);
        graphics.fillPolygon(this.d);
    }
    
    private void b(final Graphics graphics, final NFAxis nfAxis, final int n, final int n2, final int n3, final int n4) {
        if (!this.showVertical) {
            return;
        }
        final NFSpacing spacing = nfAxis.getSpacing();
        if (spacing == null || spacing.size() == 0) {
            return;
        }
        final Point mapValue = nfAxis.mapValue(nfAxis.getMin());
        final Point mapValue2 = nfAxis.mapValue(nfAxis.getMax());
        final Point point2;
        Point point = point2 = mapValue;
        point2.y -= n;
        int n5 = 0;
        final int size = spacing.size();
        final boolean drawMinorTics = nfAxis.getDrawMinorTics();
        final int skipCount = nfAxis.getSkipCount();
        for (int i = 0; i < size; ++i) {
            final Point nthPoint = nfAxis.getNthPoint(i);
            if (skipCount <= 0 || i % (skipCount + 1) == 0 || drawMinorTics) {
                final Point point3 = nthPoint;
                point3.y -= n;
                if (nthPoint.x >= mapValue.x) {
                    if (nthPoint.x <= mapValue2.x) {
                        if (this.lineStyle != 0) {
                            if (this.lineStyle != 9) {
                                NFLine.draw(graphics, nthPoint.x + n3, nthPoint.y, nthPoint.x + n4, nthPoint.y - n2, this.lineWidth, this.lineStyle, this.lineColor);
                            }
                            else {
                                if (point.x < mapValue.x) {
                                    point.x = mapValue.x;
                                }
                                if (point.x > mapValue2.x) {
                                    point.x = mapValue2.x;
                                }
                                this.b(graphics, n5++, point, nthPoint, n2, n3, n4);
                            }
                        }
                        point = nthPoint;
                    }
                }
            }
        }
        if (this.lineStyle == 9 && point.x < mapValue2.x) {
            if (point.x < mapValue.x) {
                point.x = mapValue.x;
            }
            final Point point4 = mapValue2;
            point4.y -= n;
            this.b(graphics, n5, point, mapValue2, n2, n3, n4);
        }
    }
    
    private void b(final Graphics graphics, final int n, final Point point, final Point point2, final int n2, final int n3, final int n4) {
        if (n % 2 == 1) {
            return;
        }
        this.b(0, point.x + n3, point.y);
        this.b(1, point.x + n4, point.y - n2);
        this.b(2, point2.x + n4, point2.y - n2);
        this.b(3, point2.x + n3, point2.y);
        this.b(4, point.x + n3, point.y);
        graphics.fillPolygon(this.d);
    }
    
    private void b() {
        if (!this.showBackground) {
            return;
        }
        if (this.im != null) {
            final NFImage nfImage = new NFImage();
            NFImage.tileImage(this.e, this.im, this.f, this.g, this.h, this.i);
        }
        else if (this.c != null && this.background != null) {
            final Color color = this.e.getColor();
            this.e.setColor(this.background);
            this.e.fillPolygon(this.c);
            this.e.setColor(color);
        }
    }
    
    private void c() {
        Point mapValue = null;
        Point mapValue2 = null;
        if (this.b != null) {
            this.verticalSpacing = this.b.copyPoints(0, 0);
            mapValue = this.b.mapValue(this.b.getMin());
            mapValue2 = this.b.mapValue(this.b.getMax());
        }
        if (this.verticalSpacing == null || !this.showHorizontal) {
            return;
        }
        for (int size = this.verticalSpacing.size(), i = 0; i < size; ++i) {
            final Point nthPoint = this.verticalSpacing.getNthPoint(i);
            if (mapValue != null && mapValue2 != null) {
                if (nthPoint.y < mapValue2.y) {
                    continue;
                }
                if (nthPoint.y - this.l > mapValue.y) {
                    continue;
                }
            }
            if (this.ribColor == null) {
                this.e.drawLine(nthPoint.x, nthPoint.y, nthPoint.x + (this.m - 1), nthPoint.y - this.l);
            }
            else {
                if (i % 2 == 0) {
                    this.e.setColor(this.ribColor);
                }
                else {
                    this.e.setColor(this.lineColor);
                }
                if (i != size - 1) {
                    final Point nthPoint2 = this.verticalSpacing.getNthPoint(i + 1);
                    final Polygon polygon = new Polygon();
                    polygon.addPoint(nthPoint.x, nthPoint.y);
                    polygon.addPoint(nthPoint.x + this.m, nthPoint.y - this.l);
                    polygon.addPoint(nthPoint.x + this.m, nthPoint2.y - this.l);
                    polygon.addPoint(nthPoint.x, nthPoint2.y);
                    polygon.addPoint(nthPoint.x, nthPoint.y);
                    this.e.fillPolygon(polygon);
                }
            }
        }
    }
    
    private void d() {
        Point mapValue = null;
        Point mapValue2 = null;
        if (this.a != null) {
            this.horizontalSpacing = this.a.copyPoints(0, 0);
            mapValue = this.a.mapValue(this.a.getMin());
            mapValue2 = this.a.mapValue(this.a.getMax());
        }
        if (this.horizontalSpacing == null || !this.showVertical) {
            return;
        }
        for (int size = this.horizontalSpacing.size(), i = 0; i < size; ++i) {
            final Point nthPoint = this.horizontalSpacing.getNthPoint(i);
            if (mapValue != null && mapValue2 != null) {
                if (nthPoint.x + this.n < mapValue.x) {
                    continue;
                }
                if (nthPoint.x > mapValue2.x) {
                    continue;
                }
            }
            this.e.drawLine(nthPoint.x, nthPoint.y, nthPoint.x + this.n, nthPoint.y - (this.o - 1));
        }
    }
    
    public void display(final Graphics e) {
        final Graphics e2 = this.e;
        final Color color = e.getColor();
        this.e = e;
        if (this.XAxis != null && this.YAxis != null) {
            this.a();
            Polygon activeLabelBounds;
            if (this.ZAxis == null || this.p <= 0) {
                activeLabelBounds = this.a(e);
            }
            else {
                activeLabelBounds = this.b(e);
            }
            this.setActiveLabelBounds(activeLabelBounds);
        }
        else {
            this.b();
            if (this.showGrid && this.lineColor != null) {
                e.setColor(this.lineColor);
                this.c();
                this.d();
            }
            if (this.showBorder && this.borderColor != null && this.c != null) {
                e.setColor(this.borderColor);
                e.drawPolygon(this.c);
            }
        }
        e.setColor(color);
        this.e = e2;
    }
    
    public void setActiveLabel(final NFActiveLabel y) {
        this.y = y;
    }
    
    public NFActiveLabel getActiveLabel() {
        return this.y;
    }
    
    protected void setActiveLabelBounds(final Polygon bounds) {
        if (this.y != null) {
            this.y.setBounds(bounds);
        }
    }
}
