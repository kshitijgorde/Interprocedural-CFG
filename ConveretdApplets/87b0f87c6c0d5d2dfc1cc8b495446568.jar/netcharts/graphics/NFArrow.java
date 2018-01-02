// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Graphics;

public class NFArrow
{
    public static final int NONE = 0;
    public static final int LINE = 1;
    public static final int BLOCK = 2;
    public static final int SHARP = 3;
    public static final int ROUND = 4;
    private Graphics a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private double j;
    
    public NFArrow() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 20;
        this.g = 10;
        this.h = 1;
        this.i = 1;
        this.j = 0.0;
    }
    
    public NFArrow(final Graphics a) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 20;
        this.g = 10;
        this.h = 1;
        this.i = 1;
        this.j = 0.0;
        this.a = a;
    }
    
    public NFArrow(final Graphics a, final int b, final int c, final int d, final int e) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 20;
        this.g = 10;
        this.h = 1;
        this.i = 1;
        this.j = 0.0;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void setStyle(final int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                this.i = i;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public int getStyle() {
        return this.i;
    }
    
    public void setLength(int f) {
        if (f < 1) {
            f = 1;
        }
        this.f = f;
    }
    
    public void setWidth(int g) {
        if (g < 1) {
            g = 1;
        }
        this.g = g;
    }
    
    public void setThickness(int h) {
        if (h < 1) {
            h = 1;
        }
        this.h = h;
    }
    
    public void setScale(final double j) {
        this.j = j;
    }
    
    public int getHeadSize(final int n) {
        switch (this.i) {
            case 0:
            case 4: {
                return 0;
            }
            case 1: {
                return 2 * n - 2;
            }
            case 2:
            case 3: {
                if (n < this.f) {
                    return this.f / 2;
                }
                if (n > this.f) {
                    return this.f;
                }
                return n;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void draw() {
        draw(this.a, this.b, this.c, this.d, this.e, this.i, this.f, this.g, this.h, this.j);
    }
    
    public void draw(final int n, final int n2, final int n3, final int n4) {
        draw(this.a, n, n2, n3, n4, this.i, this.f, this.g, this.h, this.j);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        draw(graphics, n, n2, n3, n4, this.i, this.f, this.g, this.h, this.j);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        draw(graphics, n, n2, n3, n4, n5, n6, n7, n8, 0.0);
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
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, int a, int a2, int a3, final double n6) {
        if (n5 == 0) {
            return;
        }
        if (n6 > 0.0) {
            a = a(a, n6);
            a2 = a(a2, n6);
            a3 = a(a3, n6);
        }
        if (n5 == 4) {
            a(graphics, n3, n4, a2);
            return;
        }
        final double n7 = n - n3;
        final double n8 = n2 - n4;
        double atan2;
        if (n7 == 0.0 && n8 == 0.0) {
            atan2 = 0.0;
        }
        else {
            atan2 = Math.atan2(n8, n7);
        }
        if (atan2 < 0.0) {
            atan2 += 6.283185307179586;
        }
        final double n10;
        final double n9 = n10 = 1.0;
        double n11;
        double n12;
        double n13;
        double n14;
        if (n7 == 0.0) {
            n11 = 0.0;
            n12 = ((n8 > 0.0) ? n10 : (-n10));
            n13 = ((n8 > 0.0) ? (-n9) : n9);
            n14 = 0.0;
        }
        else {
            final double sin = Math.sin(atan2);
            final double cos = Math.cos(atan2);
            n11 = n10 * cos;
            n12 = n10 * sin;
            n13 = n9 * -sin;
            n14 = n9 * cos;
        }
        final Point a4 = a(n11, n12, n13, n14, a, a2, n3, n4);
        final Point a5 = a(n11, n12, n13, n14, a, -a2, n3, n4);
        switch (n5) {
            case 1: {
                NFSimpleLine.draw(graphics, n3, n4, a4.x, a4.y, a3, n6, null);
                NFSimpleLine.draw(graphics, n3, n4, a5.x, a5.y, a3, n6, null);
                a(graphics, a4.x, a4.y, a3);
                a(graphics, a5.x, a5.y, a3);
                a(graphics, n3, n4, a3);
                break;
            }
            case 2: {
                final int[] array = new int[3];
                final int[] array2 = new int[3];
                array[0] = n3;
                array2[0] = n4;
                array[1] = a4.x;
                array2[1] = a4.y;
                array[2] = a5.x;
                array2[2] = a5.y;
                graphics.fillPolygon(array, array2, 3);
                break;
            }
            case 3: {
                final Point a6 = a(n11, n12, n13, n14, a / 2, 0, n3, n4);
                final int[] array3 = new int[4];
                final int[] array4 = new int[4];
                array3[0] = n3;
                array4[0] = n4;
                array3[1] = a4.x;
                array4[1] = a4.y;
                array3[2] = a6.x;
                array4[2] = a6.y;
                array3[3] = a5.x;
                array4[3] = a5.y;
                graphics.fillPolygon(array3, array4, 4);
                break;
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
    
    public static NFArrow loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFArrow loadParams(final NFParam nfParam, final Object o, final int n) {
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        final int intValue = vector.elementAt(n + 0).intValue();
        if (intValue == 0) {
            return null;
        }
        final NFArrow nfArrow = new NFArrow(null);
        nfArrow.setStyle(intValue);
        nfArrow.setLength(vector.elementAt(n + 1).intValue());
        nfArrow.setWidth(vector.elementAt(n + 2).intValue());
        nfArrow.setScale(nfParam.getChartScale());
        return nfArrow;
    }
    
    public void getParams(final StringBuffer sb) {
        switch (this.i) {
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
        sb.append(this.f);
        sb.append(',');
        sb.append(this.g);
    }
    
    public static NFArrow createCopy(final NFArrow nfArrow) {
        if (nfArrow == null) {
            return null;
        }
        final NFArrow nfArrow2 = new NFArrow(nfArrow.a);
        nfArrow2.b = nfArrow.b;
        nfArrow2.c = nfArrow.c;
        nfArrow2.d = nfArrow.d;
        nfArrow2.e = nfArrow.e;
        nfArrow2.f = nfArrow.f;
        nfArrow2.g = nfArrow.g;
        nfArrow2.h = nfArrow.h;
        nfArrow2.i = nfArrow.i;
        nfArrow2.j = nfArrow.j;
        return nfArrow2;
    }
}
