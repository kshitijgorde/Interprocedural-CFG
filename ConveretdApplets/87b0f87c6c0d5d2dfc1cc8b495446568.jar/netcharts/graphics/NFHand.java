// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class NFHand
{
    public static final int NONE = 0;
    public static final int LINE = 1;
    public static final int BLOCK = 2;
    public static final int SHARP = 3;
    public static final int ROUND = 4;
    public static final int HIT = 10;
    private NFDial a;
    private String b;
    private Graphics c;
    private int d;
    private int e;
    private int f;
    private int g;
    private double h;
    private double i;
    private int j;
    private int k;
    private double l;
    private int m;
    private int n;
    private int o;
    private double p;
    private Color q;
    private Color r;
    private static StringBuffer s;
    private NFActiveLabel t;
    private Polygon u;
    
    public NFHand() {
        this.a = null;
        this.b = "Hand";
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0.0;
        this.i = 100.0;
        this.j = 20;
        this.k = 10;
        this.l = 0.3;
        this.m = 1;
        this.n = 1;
        this.o = 1;
        this.p = 0.0;
        this.q = Color.black;
        this.r = Color.black;
        this.t = null;
        this.u = new Polygon();
    }
    
    public NFHand(final String b) {
        this.a = null;
        this.b = "Hand";
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0.0;
        this.i = 100.0;
        this.j = 20;
        this.k = 10;
        this.l = 0.3;
        this.m = 1;
        this.n = 1;
        this.o = 1;
        this.p = 0.0;
        this.q = Color.black;
        this.r = Color.black;
        this.t = null;
        this.u = new Polygon();
        this.b = b;
    }
    
    public NFHand(final Graphics graphics) {
        this("Hand", graphics);
    }
    
    public NFHand(final String b, final Graphics c) {
        this.a = null;
        this.b = "Hand";
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0.0;
        this.i = 100.0;
        this.j = 20;
        this.k = 10;
        this.l = 0.3;
        this.m = 1;
        this.n = 1;
        this.o = 1;
        this.p = 0.0;
        this.q = Color.black;
        this.r = Color.black;
        this.t = null;
        this.u = new Polygon();
        this.b = b;
        this.c = c;
    }
    
    public void setOrigin(final int d, final int e) {
        this.d = d;
        this.e = e;
    }
    
    public Point getOrigin() {
        return new Point(this.d, this.e);
    }
    
    public Polygon getActivePoly() {
        if (this.u != null && this.u.npoints > 0) {
            final Polygon polygon = new Polygon(this.u.xpoints, this.u.ypoints, this.u.npoints);
            polygon.addPoint(this.u.xpoints[0], this.u.ypoints[0]);
            return polygon;
        }
        return null;
    }
    
    public void setDial(final NFDial a) {
        this.a = a;
    }
    
    public NFDial getDial() {
        return this.a;
    }
    
    public void setActiveLabel(final NFActiveLabel t) {
        this.t = t;
    }
    
    public NFActiveLabel getActiveLabel() {
        return this.t;
    }
    
    public void setDestination(final int f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    public Point getDestination() {
        return new Point(this.f, this.g);
    }
    
    public boolean contains(final Point point) {
        return this.contains(point.x, point.y);
    }
    
    public boolean contains(final int n, final int n2) {
        final Polygon activePoly = this.getActivePoly();
        if (activePoly != null) {
            return activePoly.inside(n, n2);
        }
        final Point destination = this.getDestination();
        return destination.x - 10 <= n && destination.x + 10 >= n && destination.y - 10 <= n2 && destination.y + 10 >= n2;
    }
    
    public String getName() {
        return this.b;
    }
    
    public void setName(final String b) {
        this.b = b;
    }
    
    public double getValue() {
        return this.i;
    }
    
    public void setValue(final double i) {
        this.i = i;
    }
    
    public double getAngle() {
        return this.h;
    }
    
    public void setAngle(final double h) {
        this.h = h;
    }
    
    public void setStyle(final int o) {
        switch (o) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                this.o = o;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public int getStyle() {
        return this.o;
    }
    
    public int getTipLength() {
        return this.j;
    }
    
    public void setTipLength(int j) {
        if (j < 1) {
            j = 1;
        }
        this.j = j;
    }
    
    public double getShaftLength() {
        return this.l;
    }
    
    public void setShaftLength(double l) {
        if (l < 0.0) {
            l = 0.0;
        }
        this.l = l;
    }
    
    public int getTipWidth() {
        return this.k;
    }
    
    public void setTipWidth(int k) {
        if (k < 0) {
            k = 0;
        }
        this.k = k;
    }
    
    public int getShaftWidth() {
        return this.m;
    }
    
    public void setShaftWidth(int m) {
        if (m < 0) {
            m = 0;
        }
        this.setThickness(this.m = m);
    }
    
    public Color getShaftColor() {
        return this.r;
    }
    
    public void setShaftColor(Color black) {
        if (black == null) {
            black = Color.black;
        }
        this.r = black;
    }
    
    public Color getTipColor() {
        return this.q;
    }
    
    public void setTipColor(Color black) {
        if (black == null) {
            black = Color.black;
        }
        this.q = black;
    }
    
    public int getThickness() {
        return this.n;
    }
    
    public void setThickness(int n) {
        if (n < 0) {
            n = 0;
        }
        this.n = n;
    }
    
    public void setScale(final double p) {
        this.p = p;
    }
    
    public int getHeadSize(final int n) {
        switch (this.o) {
            case 0:
            case 4: {
                return 0;
            }
            case 1: {
                return 2 * n - 2;
            }
            case 2:
            case 3: {
                if (n < this.j) {
                    return this.j / 2;
                }
                if (n > this.j) {
                    return this.j;
                }
                return n;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void draw() {
        draw(this.c, this.d, this.e, this.f, this.g, this.o, this.j, this.k, this.m, this.n, this.p, this.q, this.r, this.u);
    }
    
    public void draw(final Graphics graphics) {
        draw(graphics, this.d, this.e, this.f, this.g, this.o, this.j, this.k, this.m, this.n, this.p, this.q, this.r, this.u);
    }
    
    public void draw(final int n, final int n2, final int n3, final int n4) {
        draw(this.c, n, n2, n3, n4, this.o, this.j, this.k, this.m, this.n, this.p, this.q, this.r, this.u);
    }
    
    public void draw(final Graphics graphics, final Rectangle rectangle) {
        final double n = 3.141592653589793 * (this.h / 180.0);
        final int n2 = rectangle.width / 2;
        final int n3 = rectangle.height / 2;
        final int n4 = rectangle.x + n2;
        final int n5 = rectangle.y + n3;
        final int n6 = n4 + (int)NFUtil.rint(Math.cos(n) * n2 * this.l);
        final int n7 = n5 - (int)NFUtil.rint(Math.sin(n) * n3 * this.l);
        this.setOrigin(n4, n5);
        this.setDestination(n6, n7);
        draw(graphics, n4, n5, n6, n7, this.o, this.j, this.k, this.m, this.n, this.p, this.q, this.r, this.u);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        draw(graphics, n, n2, n3, n4, this.o, this.j, this.k, this.m, this.n, this.p, this.q, this.r, this.u);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        draw(graphics, n, n2, n3, n4, n5, n6, n7, n8, n9, 0.0, Color.black, Color.black, null);
    }
    
    private static int a(int n, final double n2) {
        if (n > 0 && n2 > 0.0) {
            n *= (int)n2;
            if (n < 1) {
                n = 1;
            }
        }
        return n;
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, int a, int a2, final int n6, int a3, final double n7, final Color color, final Color color2, final Polygon polygon) {
        if (n7 > 0.0) {
            a = a(a, n7);
            a2 = a(a2, n7);
            a3 = a(a3, n7);
        }
        if (n6 > 0 && n5 != 2 && n5 != 3) {
            graphics.setColor(color2);
            NFSimpleLine.draw(graphics, n, n2, n3, n4, n6, n7, null);
        }
        if (n5 == 0) {
            return;
        }
        if (a2 > 0) {
            graphics.setColor(color);
            if (n5 == 4) {
                a(graphics, n3, n4, a2);
                if (polygon != null) {
                    final int[] xpoints = new int[4];
                    final int[] ypoints = new int[4];
                    xpoints[0] = n3 - a2 / 2;
                    ypoints[0] = n4 - a2 / 2;
                    xpoints[1] = n3 - a2 / 2 + a2;
                    ypoints[1] = n4 - a2 / 2;
                    xpoints[2] = n3 - a2 / 2 + a2;
                    ypoints[2] = n4 - a2 / 2 + a2;
                    xpoints[3] = n3 - a2 / 2;
                    ypoints[3] = n4 - a2 / 2 + a2;
                    polygon.xpoints = xpoints;
                    polygon.ypoints = ypoints;
                    polygon.npoints = 4;
                }
                return;
            }
            final double n8 = n - n3;
            final double n9 = n2 - n4;
            double atan2;
            if (n8 == 0.0 && n9 == 0.0) {
                atan2 = 0.0;
            }
            else {
                atan2 = Math.atan2(n9, n8);
            }
            if (atan2 < 0.0) {
                atan2 += 6.283185307179586;
            }
            final double n11;
            final double n10 = n11 = 1.0;
            double n12;
            double n13;
            double n14;
            double n15;
            if (n8 == 0.0) {
                n12 = 0.0;
                n13 = ((n9 > 0.0) ? n11 : (-n11));
                n14 = ((n9 > 0.0) ? (-n10) : n10);
                n15 = 0.0;
            }
            else {
                final double sin = Math.sin(atan2);
                final double cos = Math.cos(atan2);
                n12 = n11 * cos;
                n13 = n11 * sin;
                n14 = n10 * -sin;
                n15 = n10 * cos;
            }
            final Point a4 = a(n12, n13, n14, n15, a, a2, n3, n4);
            final Point a5 = a(n12, n13, n14, n15, a, -a2, n3, n4);
            switch (n5) {
                case 1: {
                    if (a3 <= 0) {
                        break;
                    }
                    NFSimpleLine.draw(graphics, n3, n4, a4.x, a4.y, a3, n7, null);
                    NFSimpleLine.draw(graphics, n3, n4, a5.x, a5.y, a3, n7, null);
                    a(graphics, a4.x, a4.y, a3);
                    a(graphics, a5.x, a5.y, a3);
                    a(graphics, n3, n4, a3);
                    if (polygon != null) {
                        polygon.npoints = 0;
                        break;
                    }
                    break;
                }
                case 2: {
                    final Point a6 = a(n12, n13, n14, n15, a, 0, n3, n4);
                    if (n6 > 0) {
                        graphics.setColor(color2);
                        NFSimpleLine.draw(graphics, n, n2, a6.x, a6.y, n6, n7, null);
                        graphics.setColor(color);
                    }
                    final int[] xpoints2 = new int[3];
                    final int[] ypoints2 = new int[3];
                    xpoints2[0] = n3;
                    ypoints2[0] = n4;
                    xpoints2[1] = a4.x;
                    ypoints2[1] = a4.y;
                    xpoints2[2] = a5.x;
                    ypoints2[2] = a5.y;
                    graphics.fillPolygon(xpoints2, ypoints2, 3);
                    if (polygon != null) {
                        polygon.xpoints = xpoints2;
                        polygon.ypoints = ypoints2;
                        polygon.npoints = 3;
                        break;
                    }
                    break;
                }
                case 3: {
                    final Point a7 = a(n12, n13, n14, n15, a / 2, 0, n3, n4);
                    if (n6 > 0) {
                        graphics.setColor(color2);
                        NFSimpleLine.draw(graphics, n, n2, a7.x, a7.y, n6, n7, null);
                        graphics.setColor(color);
                    }
                    final int[] xpoints3 = new int[4];
                    final int[] ypoints3 = new int[4];
                    xpoints3[0] = n3;
                    ypoints3[0] = n4;
                    xpoints3[1] = a4.x;
                    ypoints3[1] = a4.y;
                    xpoints3[2] = a7.x;
                    ypoints3[2] = a7.y;
                    xpoints3[3] = a5.x;
                    ypoints3[3] = a5.y;
                    graphics.fillPolygon(xpoints3, ypoints3, 4);
                    if (polygon != null) {
                        polygon.xpoints = xpoints3;
                        polygon.ypoints = ypoints3;
                        polygon.npoints = 4;
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3) {
        if (n3 > 1) {
            graphics.fillOval(n - n3 / 2, n2 - n3 / 2, n3, n3);
        }
    }
    
    private static Point a(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7, final int n8) {
        final Point point = new Point(0, 0);
        point.x = a(n5 * n + n6 * n3 + n7);
        point.y = a(n5 * n2 + n6 * n4 + n8);
        return point;
    }
    
    private static int a(final double n) {
        if (n > 0.0) {
            return (int)NFUtil.rint(n + 0.01);
        }
        return (int)NFUtil.rint(n - 0.01);
    }
    
    public static NFHand loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFHand loadParams(final NFParam nfParam, final Object o, final int n) {
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        final String s = vector.elementAt(n + 0);
        final int intValue = ((Number)vector.elementAt(n + 1)).intValue();
        if (intValue == 0) {
            return null;
        }
        final NFHand nfHand = new NFHand(s);
        nfHand.setStyle(intValue);
        nfHand.setTipLength(((Number)vector.elementAt(n + 2)).intValue());
        nfHand.setTipWidth(((Number)vector.elementAt(n + 3)).intValue());
        nfHand.setShaftLength(((Number)vector.elementAt(n + 4)).intValue());
        nfHand.setShaftWidth(((Number)vector.elementAt(n + 5)).intValue());
        nfHand.setScale(nfParam.getChartScale());
        return nfHand;
    }
    
    public void getParams(final StringBuffer sb) {
        switch (this.o) {
            case 0: {
                sb.append("NONE");
                break;
            }
            case 1: {
                sb.append("LINE");
                break;
            }
            case 2: {
                sb.append("BLOCK");
                break;
            }
            case 3: {
                sb.append("SHARP");
                break;
            }
            case 4: {
                sb.append("ROUND");
                break;
            }
            default: {
                sb.append("UNKNOWN");
                break;
            }
        }
        sb.append(',');
        sb.append(this.j);
        sb.append(',');
        sb.append(this.k);
    }
    
    static {
        NFHand.s = new StringBuffer();
    }
}
