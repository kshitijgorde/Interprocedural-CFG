// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Color;
import ji.awt.d5;
import java.awt.Rectangle;
import ji.awt.d4;
import ji.io.ac;
import ji.io.q;
import ji.v1event.a6;
import ji.util.e;
import ji.v1event.af;
import java.awt.Component;
import java.awt.Point;
import java.awt.Dimension;

public class fl
{
    static final int a;
    private static boolean b;
    private static String c;
    
    private static final double a(final double n) {
        return n / 180.0 * 3.141592653589793;
    }
    
    public static final Dimension a(final double n, final Dimension dimension, final Point point, final Point point2) {
        return a(n, dimension, point, point2, 0, 0);
    }
    
    public static final Dimension a(final double n, final Dimension dimension, final Point point, final Point point2, final int n2, final int n3) {
        final double a = a(n);
        final double sin = Math.sin(a);
        final double cos = Math.cos(a);
        int max = 0;
        int max2 = 0;
        for (int max3 = Math.max(dimension.height - 1, 1), i = 0; i < dimension.height; i += max3) {
            for (int max4 = Math.max(dimension.width - 1, 1), j = 0; j < dimension.width; j += max4) {
                final int n4 = (int)Math.round((j - point.x) * cos - (i - point.y) * sin) + point.x + n2;
                final int n5 = (int)Math.round((j - point.x) * sin + (i - point.y) * cos) + point.y + n3;
                point2.y = Math.min(point2.y, n5);
                point2.x = Math.min(point2.x, n4);
                max = Math.max(max, n5);
                max2 = Math.max(max2, n4);
            }
        }
        if (point2.x < 0) {
            max2 -= point2.x;
        }
        if (point2.y < 0) {
            max -= point2.y;
        }
        return new Dimension(Math.abs(max2), Math.abs(max));
    }
    
    public static void a(final String c) {
        fl.b = true;
        fl.c = c;
    }
    
    public static final du a(double n, final du du, final Component component, final String s, final Point point, final boolean b, final int n2, final int n3, final af af, final Dimension dimension, final String s2, final String s3, final boolean b2) {
        fl.b = false;
        final boolean b3 = fl.b;
        try {
            while (n >= 360.0) {
                n -= 360.0;
            }
            while (n < 0.0) {
                n += 360.0;
            }
            if (n == 0.0 && n2 == 0 && n3 == 0) {
                return du;
            }
            final int h = du.h();
            final int f = du.f();
            final int i = du.i();
            final int g = du.g();
            final double a = a(n);
            final double sin = Math.sin(a);
            final double cos = Math.cos(a);
            int width = g;
            int height = i;
            final Point point2 = new Point(0, 0);
            if (af != null && af != null) {
                if (s3 != null) {
                    e.a(true, s3);
                }
                else {
                    e.a(true, "1050");
                }
                af.a(new a6(component, 9, ""));
            }
            if (b) {
                final Dimension a2 = a(n, new Dimension(f, h), point, point2, n2, n3);
                width = a2.width;
                height = a2.height;
            }
            else if (dimension != null) {
                final Point point3 = new Point(0, 0);
                final double n4 = dimension.width / (a(n, new Dimension(f, h), point, point3, n2, n3).width + point3.x);
                height = h - point3.y;
                point2.y = point3.y;
            }
            int[] array = null;
            ac ac = null;
            String o = null;
            q a3 = null;
            boolean b4 = false;
            final int n5 = width * height;
            int n6 = 0;
            final a6 a4 = new a6(component, 4, 0);
            final int n7 = height / 0;
            if (du.c()) {
                b4 = true;
                a3 = q.a(component, s);
                o = a3.o();
                ac = new ac(o, true, false, 0, false, component, s);
                final int[] array2 = new int[width];
                for (int j = 0; j < width; ++j) {
                    array2[j] = fl.a;
                }
                for (int k = 0; k < width; ++k) {
                    array2[k] = 0;
                }
                for (int l = 0; l < height; ++l) {
                    ac.b(array2);
                }
            }
            else {
                array = new int[n5];
                for (int n8 = 0; n8 < n5; ++n8) {
                    array[n8] = fl.a;
                }
            }
            final int[] array3 = { 0 };
            int n9 = -1;
            a4.a(10);
            if (af != null) {
                af.a(a4);
            }
            boolean b5 = fl.b && fl.c == s2;
            for (double n10 = 0.0; n10 < h && !b5; n10 += 0.5) {
                final int[] a5 = du.a((int)(h - n10 - 1));
                final int x = point.x;
                final int y = point.y;
                for (double n11 = 0.0; n11 < f && !b5; b5 = (fl.b && fl.c == s2), n11 += 0.5) {
                    array3[0] = a5[(int)n11];
                    if (array3[0] != 0) {
                        final int n12 = (int)Math.round((n11 - x) * cos - (n10 - y) * sin + x - point2.x + n2);
                        final int n13 = (int)Math.round((n11 - x) * sin + (n10 - y) * cos + y - point2.y + n3);
                        if (b || (n12 >= 0 && n13 >= 0 && n12 < width && n13 < height)) {
                            final int n14 = (height - n13) * width + n12;
                            if (n14 != n9 && (n9 = n14) > -1 && n14 < n5) {
                                if (b4) {
                                    ac.a((long)n14);
                                    ac.b(array3, 0, 1);
                                }
                                else {
                                    array[n14] = array3[0];
                                }
                            }
                        }
                    }
                }
                if (n10 % n7 == 0) {
                    n6 += 10;
                    a4.a(n6);
                    if (af != null) {
                        af.a(a4);
                    }
                }
            }
            if (!b5) {
                if (b4) {
                    ac.a(component);
                    ac = new ac(o, false, true, 102400, false, component, s);
                }
                final du du2 = new du(height, width, component, du.b(), s, false, du.a(), b2);
                du2.a(du.k(), du.l(), du.m(), du.n(), 0, 0);
                if (b4) {
                    final int[] array4 = new int[width];
                    for (int n15 = 0; n15 < height; ++n15) {
                        ac.a(array4);
                        du2.a(n15, array4);
                    }
                }
                else {
                    du2.a(0, array, width, height);
                }
                if (b4) {
                    ac.a(component);
                    a3.d(o);
                }
                return du2;
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            e.a(false, "");
            af.a(new a6(component, 10, ""));
        }
        return null;
    }
    
    public static final dw a(final double n, final dw dw, final Component component, final String s, final Point point, final boolean b, final int n2, final int n3, final byte b2, final af af, final Dimension dimension, final String s2, final String s3) {
        return a(n, dw, null, null, component, s, false, point, b, n2, n3, b2, af, dimension, s2, s3);
    }
    
    public static final dw a(final double n, final dw dw, final d4[] array, final Rectangle rectangle, final Component component, final String s, final boolean b, final Point point, final String s2) {
        return a(n, dw, array, rectangle, component, s, b, point, true, 0, 0, (byte)0, null, null, s2);
    }
    
    public static final dw a(final double n, final dw dw, final d4[] array, final Rectangle rectangle, final Component component, final String s, final boolean b, final Point point, final boolean b2, final int n2, final int n3, final byte b3, final af af, final Dimension dimension, final String s2) {
        return a(n, dw, array, rectangle, component, s, b, point, b2, n2, n3, b3, af, dimension, s2, "1050");
    }
    
    public static final dw a(double n, final dw dw, final d4[] array, final Rectangle rectangle, final Component component, final String s, final boolean b, final Point point, final boolean b2, final int n2, final int n3, final byte b3, final af af, final Dimension dimension, final String s2, final String s3) {
        fl.b = false;
        final boolean b4 = fl.b;
        try {
            while (n >= 360.0) {
                n -= 360.0;
            }
            while (n < 0.0) {
                n += 360.0;
            }
            if (n == 0.0 && n2 == 0 && n3 == 0) {
                return dw;
            }
            final int k = dw.k();
            final int i = dw.i();
            final int l = dw.l();
            dw.j();
            if (af != null) {
                if (s3 != null) {
                    e.a(true, s3);
                }
                else {
                    e.a(true, "1050");
                }
                af.a(new a6(component, 9, ""));
            }
            final double a = a(n);
            final double sin = Math.sin(a);
            final double cos = Math.cos(a);
            final Point point2 = new Point(0, 0);
            final Point point3 = new Point(0, 0);
            Dimension a2 = new Dimension(i, k);
            if (b2) {
                a2 = a(n, new Dimension(i, k), point, point2, n2, n3);
                if (rectangle != null) {
                    final Point point4 = new Point(rectangle.x, k - (rectangle.y + rectangle.height));
                    final int n4 = (int)(rectangle.x * dw.r());
                    final int n5 = (int)(rectangle.y * dw.r());
                    a(n, new Dimension(rectangle.x + rectangle.width, rectangle.y + rectangle.height), point, point3, n2, n3);
                    final Point point5 = point3;
                    point5.x -= rectangle.x;
                    final Point point6 = point3;
                    point6.y -= rectangle.y;
                }
            }
            else if (dimension != null) {
                final Point point7 = new Point(0, 0);
                final double n6 = dimension.width / (a(n, new Dimension(i, k), point, point7, n2, n3).width + point7.x);
                a2.height -= point7.y;
                point2.y = point7.y;
            }
            final int width = a2.width;
            final int height = a2.height;
            byte[] array2 = null;
            ac ac = null;
            String o = null;
            q a3 = null;
            boolean b5 = false;
            final int n7 = width * height;
            int n8 = 10;
            final a6 a4 = new a6(component, 4, 0);
            final int n9 = height / 9;
            if (dw.e()) {
                b5 = true;
                a3 = q.a(component, s);
                o = a3.o();
                ac = new ac(o, true, false, 0, false, component, s);
                final byte[] array3 = new byte[width];
                for (int j = 0; j < width; ++j) {
                    array3[j] = b3;
                }
                for (int n10 = 0; n10 < height; ++n10) {
                    ac.b(array3);
                }
            }
            else {
                array2 = new byte[n7];
                for (int n11 = 0; n11 < n7; ++n11) {
                    array2[n11] = b3;
                }
            }
            final byte[] array4 = { 0 };
            if (array != null) {
                final boolean b6 = false;
                final int n12 = (int)array[0].a(0).a / 2;
                for (int n13 = 0; n13 < array.length; ++n13) {
                    final d4 d4 = array[n13];
                    if (d4 != null) {
                        final d4 d5 = new d4();
                        d5.getBounds();
                        for (int b7 = d4.b(), n14 = 0; n14 < b7; ++n14) {
                            final d5 a5 = d4.a(n14);
                            a5.a -= n12;
                            a5.b = l - a5.b - (b6 ? 1 : 0);
                            final int n15 = (int)Math.round((a5.a - point.x) * cos - (a5.b - point.y) * sin) + point.x - point3.x + n2;
                            final int n16 = (int)Math.round((a5.a - point.x) * sin + (a5.b - point.y) * cos) + point.y - point3.y + n3;
                            if (b2 || (n15 >= 0 && n16 >= 0 && n15 < width && n16 < height)) {
                                d5.addPoint(n15, n16);
                            }
                        }
                        array[n13] = d5;
                    }
                }
            }
            a4.a(n8);
            if (af != null) {
                af.a(a4);
            }
            int n17 = -1;
            boolean b8 = fl.b && fl.c == s2;
            for (double n18 = 0.0; n18 < k && !b8; ++n18) {
                final byte[] b9 = dw.b((int)(k - n18 - 1));
                final int x = point.x;
                final int y = point.y;
                for (double n19 = 0.0; n19 < i && !b8; b8 = (fl.b && fl.c == s2), n19 += 0.5) {
                    array4[0] = b9[(int)n19];
                    if (array4[0] != 0) {
                        final int n20 = (int)Math.round((n19 - x) * cos - (n18 - y) * sin) + x - point2.x + n2;
                        final int n21 = (int)Math.round((n19 - x) * sin + (n18 - y) * cos + y - point2.y + n3);
                        if (b2 || (n20 >= 0 && n21 >= 0 && n20 < width && n21 < height)) {
                            final int n22 = (height - n21) * width + n20;
                            if (n22 != n17 && (n17 = n22) > -1 && n22 < n7) {
                                if (b5) {
                                    ac.a((long)n22);
                                    ac.b(array4, 0, 1);
                                }
                                else {
                                    array2[n22] = array4[0];
                                }
                            }
                        }
                    }
                }
                if (n18 % n9 == 0) {
                    n8 += 10;
                    a4.a(n8);
                    if (af != null) {
                        af.a(a4);
                    }
                }
            }
            if (!b8) {
                if (b5) {
                    ac.a(component);
                    ac = new ac(o, false, true, 102400, false, component, s);
                }
                final dw dw2 = new dw(height, width, component, dw.d(), s, b, dw.c());
                dw2.a(dw.h());
                dw2.a(dw.p(), dw.q(), dw.r());
                dw2.a(dw.a());
                dw2.a(dw.b());
                dw2.a(dw.f());
                if (b5) {
                    final byte[] array5 = new byte[width];
                    for (int n23 = 0; n23 < height; ++n23) {
                        ac.a(array5);
                        dw2.a(n23, array5);
                    }
                }
                else {
                    dw2.a(0, array2, width, height);
                }
                if (b5) {
                    ac.a(component);
                    a3.d(o);
                }
                return dw2;
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            e.a(false, "");
            if (af != null) {
                af.a(new a6(component, 10, ""));
            }
        }
        return null;
    }
    
    static {
        a = Color.white.getRGB();
        fl.b = false;
        fl.c = "";
    }
}
