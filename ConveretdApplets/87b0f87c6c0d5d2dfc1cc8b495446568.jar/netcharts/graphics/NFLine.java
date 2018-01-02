// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class NFLine
{
    public static final int NONE = 0;
    public static final int SOLID = 1;
    public static final int DOTTED = 2;
    public static final int DASHED = 3;
    public static final int DOTDASH = 4;
    private Graphics a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private NFArrow h;
    private NFArrow i;
    private Color j;
    private double k;
    private boolean l;
    private Polygon m;
    
    public NFLine(final Graphics a) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1;
        this.g = 1;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 0.0;
        this.l = false;
        this.m = null;
        this.a = a;
    }
    
    public NFLine(final Graphics a, final int b, final int c, final int d, final int e) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1;
        this.g = 1;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 0.0;
        this.l = false;
        this.m = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void setThickness(final int f) {
        this.f = f;
    }
    
    public int getThickness() {
        return this.f;
    }
    
    public int getScaleThickness() {
        return a(this.f, this.k);
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
    
    public void setScale(final double k) {
        this.k = k;
    }
    
    public void setColor(final Color j) {
        this.j = j;
    }
    
    public Color getColor() {
        return this.j;
    }
    
    public void setStyle(final int g) {
        switch (g) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                this.g = g;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public void setJoinLines(final boolean l) {
        if (l) {
            this.m = new Polygon();
        }
        else {
            this.m = null;
        }
        this.l = l;
    }
    
    public static String getLineStyleName(final int n) {
        switch (n) {
            case 0: {
                return "NONE";
            }
            case 1: {
                return "SOLID";
            }
            case 2: {
                return "DOTTED";
            }
            case 3: {
                return "DASHED";
            }
            case 4: {
                return "DOTDASH";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
    
    public void setArrows(final NFArrow h, final NFArrow i) {
        this.h = h;
        this.i = i;
    }
    
    public void draw() {
        draw(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.j, this.h, this.i, this.k, this.m);
    }
    
    public void draw(final int n, final int n2, final int n3, final int n4) {
        draw(this.a, n, n2, n3, n4, this.f, this.g, this.j, this.h, this.i, this.k, this.m);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        draw(graphics, n, n2, n3, n4, this.f, this.g, this.j, this.h, this.i, this.k, this.m);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color) {
        draw(graphics, n, n2, n3, n4, n5, n6, color, null, null, 0.0, null);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final NFArrow nfArrow, final NFArrow nfArrow2) {
        draw(graphics, n, n2, n3, n4, n5, n6, color, nfArrow, nfArrow2, 0.0, null);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final NFArrow nfArrow, final NFArrow nfArrow2, final double n7) {
        draw(graphics, n, n2, n3, n4, n5, n6, color, nfArrow, nfArrow2, n7, null);
    }
    
    public static void draw(final Graphics graphics, int n, int n2, int n3, int n4, int n5, final int n6, final Color color, final NFArrow nfArrow, final NFArrow nfArrow2, final double n7, final Polygon polygon) {
        final double sqrt = Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
        final double n8 = (n3 - n) / sqrt;
        final double n9 = (n4 - n2) / sqrt;
        final int n10 = n;
        final int n11 = n2;
        final int n12 = n3;
        final int n13 = n4;
        Color color2 = null;
        if (n6 == 0) {
            return;
        }
        if (n5 < 1) {
            n5 = 1;
        }
        if (color != null) {
            color2 = graphics.getColor();
            graphics.setColor(color);
        }
        final int a = a(n5, n7);
        if (nfArrow != null) {
            nfArrow.setThickness(n5);
            final int headSize = nfArrow.getHeadSize(a);
            if (headSize < sqrt / 2.0) {
                n += (int)(headSize * n8);
                n2 += (int)(headSize * n9);
            }
        }
        if (nfArrow2 != null) {
            nfArrow2.setThickness(n5);
            final int headSize2 = nfArrow2.getHeadSize(a);
            if (headSize2 < sqrt / 2.0) {
                n3 -= (int)(headSize2 * n8);
                n4 -= (int)(headSize2 * n9);
            }
        }
        if (n6 != 1) {
            a(graphics, n, n2, n3, n4, n5, n6, nfArrow, nfArrow2, n7);
            a(graphics, n12, n13, n10, n11, nfArrow);
            a(graphics, n10, n11, n12, n13, nfArrow2);
            if (color != null) {
                graphics.setColor(color2);
            }
            return;
        }
        Polygon polygon2 = null;
        if (polygon != null && a > 1) {
            polygon2 = new Polygon();
        }
        NFSimpleLine.draw(graphics, n, n2, n3, n4, n5, n7, polygon2);
        a(graphics, n12, n13, n10, n11, nfArrow);
        a(graphics, n10, n11, n12, n13, nfArrow2);
        if (a < 1 || polygon == null || polygon2 == null) {
            if (color != null) {
                graphics.setColor(color2);
            }
            return;
        }
        if (polygon.npoints > 0) {
            final int[] array = new int[5];
            final int[] array2 = new int[5];
            array[0] = polygon.xpoints[2];
            array2[0] = polygon.ypoints[2];
            array[1] = polygon2.xpoints[0];
            array2[1] = polygon2.ypoints[0];
            array[2] = polygon.xpoints[3];
            array2[2] = polygon.ypoints[3];
            array[3] = polygon2.xpoints[1];
            array2[3] = polygon2.ypoints[1];
            array[4] = array[0];
            array2[4] = array2[0];
            graphics.fillPolygon(array, array2, 5);
        }
        polygon.xpoints = polygon2.xpoints;
        polygon.ypoints = polygon2.ypoints;
        polygon.npoints = polygon2.npoints;
        if (color != null) {
            graphics.setColor(color2);
        }
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final NFArrow nfArrow, final NFArrow nfArrow2, final double n7) {
        if (NFUtil.getJDKVersion() >= 1.2) {
            NF12GraphicsUtil.drawLine(graphics, n, n2, n3, n4, a(n5, n7), n6);
            return;
        }
        final int[] dashArray = getDashArray(n6, n5);
        final double sqrt = Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
        final double n8 = (n3 - n) / sqrt;
        final double n9 = (n4 - n2) / sqrt;
        int n10 = 0;
        int n11 = dashArray[n10];
        int n12 = (int)(n11 * n8);
        int n13 = (int)(n11 * n9);
        int n14 = n;
        int n15 = n2;
        while (((n8 > 0.0 && n + n12 < n3) || (n8 < 0.0 && n + n12 > n3) || (n == n3 && n2 != n4)) && ((n9 > 0.0 && n2 + n13 < n4) || (n9 < 0.0 && n2 + n13 > n4) || (n2 == n4 && n != n3))) {
            if (n10 == 0 || n10 == 2) {
                NFSimpleLine.draw(graphics, n14, n15, n + n12, n2 + n13, n5, n7, null);
            }
            n14 = n + n12;
            n15 = n2 + n13;
            if (++n10 > 3) {
                n10 = 0;
            }
            n11 += dashArray[n10];
            n12 = (int)(n11 * n8);
            n13 = (int)(n11 * n9);
        }
        if (n10 == 0 || n10 == 2) {
            NFSimpleLine.draw(graphics, n14, n15, n3, n4, n5, n7, null);
        }
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final NFArrow nfArrow) {
        if (nfArrow != null) {
            nfArrow.draw(graphics, n, n2, n3, n4);
        }
    }
    
    public void drawPoly(final Graphics graphics, final Polygon polygon) {
        if (NFUtil.getJDKVersion() >= 1.2) {
            Color color = null;
            if (this.j != null) {
                color = graphics.getColor();
                graphics.setColor(this.j);
            }
            NF12GraphicsUtil.drawPolyline(graphics, polygon, this.getScaleThickness(), this.g);
            for (int i = 1; i < polygon.npoints; ++i) {
                a(graphics, polygon.xpoints[i], polygon.ypoints[i], polygon.xpoints[i - 1], polygon.ypoints[i - 1], this.h);
                a(graphics, polygon.xpoints[i - 1], polygon.ypoints[i - 1], polygon.xpoints[i], polygon.ypoints[i], this.i);
            }
            if (color != null) {
                graphics.setColor(color);
            }
            return;
        }
        if (this.m == null && this.g == 1) {
            this.m = new Polygon();
        }
        if (this.m != null) {
            this.m.npoints = 0;
        }
        for (int j = 1; j < polygon.npoints; ++j) {
            this.draw(graphics, polygon.xpoints[j - 1], polygon.ypoints[j - 1], polygon.xpoints[j], polygon.ypoints[j]);
        }
        this.m = null;
    }
    
    public static NFLine loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFLine loadParams(final NFParam nfParam, final Object o, final int n) {
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        final int intValue = vector.elementAt(n + 0).intValue();
        if (intValue == 0) {
            return null;
        }
        final NFLine nfLine = new NFLine(null);
        nfLine.setStyle(intValue);
        nfLine.setThickness(vector.elementAt(n + 1).intValue());
        nfLine.setColor((Color)vector.elementAt(n + 2));
        nfLine.setScale(nfParam.getChartScale());
        return nfLine;
    }
    
    public int[] getDashArray() {
        return getDashArray(this.g, this.f);
    }
    
    public static int[] getDashArray(final int n, final int n2) {
        final int n3 = 1;
        final int[] array = new int[4];
        array[1] = (array[3] = n3 + 2 * n2);
        switch (n) {
            case 2: {
                array[0] = (array[2] = array[1]);
                break;
            }
            case 3: {
                array[0] = (array[2] = 3 * array[1]);
                break;
            }
            case 4: {
                array[0] = array[1];
                array[2] = 3 * array[1];
                break;
            }
        }
        return array;
    }
    
    public int getMinimumVisibleStyleWidth() {
        final int[] dashArray = this.getDashArray();
        switch (this.g) {
            case 2: {
                return dashArray[0] + dashArray[1] + dashArray[2];
            }
            case 3: {
                return dashArray[0] + dashArray[1] + dashArray[2];
            }
            case 4: {
                return 2 * dashArray[0] + dashArray[1] + dashArray[2] + dashArray[3];
            }
            default: {
                return 0;
            }
        }
    }
    
    public static NFLine createCopy(final NFLine nfLine) {
        if (nfLine == null) {
            return null;
        }
        final NFLine nfLine2 = new NFLine(nfLine.a);
        nfLine2.b = nfLine.b;
        nfLine2.c = nfLine.c;
        nfLine2.d = nfLine.d;
        nfLine2.e = nfLine.e;
        nfLine2.f = nfLine.f;
        nfLine2.g = nfLine.g;
        nfLine2.h = NFArrow.createCopy(nfLine.h);
        nfLine2.i = NFArrow.createCopy(nfLine.i);
        nfLine2.j = nfLine.j;
        nfLine2.k = nfLine.k;
        nfLine2.l = nfLine.l;
        nfLine2.m = ((nfLine.m != null) ? new Polygon(nfLine.m.xpoints, nfLine.m.ypoints, nfLine.m.npoints) : null);
        return nfLine2;
    }
}
