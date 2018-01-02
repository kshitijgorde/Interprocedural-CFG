// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Graphics;
import java.awt.Color;
import JAVACharter.b.c;
import JAVACharter.b.h;
import java.awt.Rectangle;

public class d
{
    public static final int int = 0;
    public static final int a = 1;
    public static final int new = 2;
    public static final int try = 3;
    public static final int case = 4;
    public static final int byte = 5;
    private static int char;
    private j if;
    private g for;
    private Rectangle do;
    
    public d(final Rectangle do1, final j if1, final g for1) {
        this.do = do1;
        this.if = if1;
        this.for = for1;
    }
    
    public static void a(final int char1) {
        d.char = char1;
    }
    
    public int a() {
        return d.char;
    }
    
    public void if(final Object o, final Object o2) {
        final int[] array = (int[])o2;
        if (o instanceof h) {
            final h h = (h)o;
            for (int a = h.a(), i = 0; i < a; ++i) {
                array[i * 2] = this.for.a(i);
                if (h.for(i) == 9.223372E18f) {
                    array[i * 2 + 1] = -1;
                }
                else {
                    array[i * 2 + 1] = this.if.do(h.for(i));
                }
            }
        }
        else if (o instanceof c) {
            final c c = (c)o;
            for (int a2 = c.a(), j = 0; j < a2; ++j) {
                array[j * 2] = this.for.a(j);
                if (c.new(j) == 9.223372E18f) {
                    array[j * 2 + 1] = -1;
                }
                else {
                    array[j * 2 + 1] = this.if.a(c.new(j));
                }
            }
        }
        else if (o instanceof float[]) {
            final float[] array2 = (float[])o;
            for (int length = array2.length, k = 0; k < length; ++k) {
                array[k * 2] = this.for.a(k);
                if (array2[k] == 9.223372E18f) {
                    array[k * 2 + 1] = -1;
                }
                else {
                    array[k * 2 + 1] = this.if.do(array2[k]);
                }
            }
        }
        else if (o instanceof long[]) {
            final long[] array3 = (long[])o;
            for (int length2 = array3.length, l = 0; l < length2; ++l) {
                array[l * 2] = this.for.a(l);
                if (array3[l] == 9.223372E18f) {
                    array[l * 2 + 1] = -1;
                }
                else {
                    array[l * 2 + 1] = this.if.a(array3[l]);
                }
            }
        }
    }
    
    public void a(final Object o, final Object o2, final String s) {
        final int[] array = (int[])o2;
        if (o instanceof h) {
            final h h = (h)o;
            for (int a = h.a(), i = 0; i < a; ++i) {
                array[i * 2] = this.for.a(i);
                if (h.for(i) == 9.223372E18f) {
                    array[i * 2 + 1] = -1;
                }
                else {
                    array[i * 2 + 1] = this.if.do(h.for(i));
                }
            }
        }
        else if (o instanceof c) {
            final c c = (c)o;
            for (int a2 = c.a(), j = 0; j < a2; ++j) {
                array[j * 2] = this.for.a(j);
                if (c.new(j) == 9.223372E18f) {
                    array[j * 2 + 1] = -1;
                }
                else {
                    array[j * 2 + 1] = this.if.a(c.new(j));
                }
            }
        }
        else if (o instanceof float[]) {
            final float[] array2 = (float[])o;
            final int length = array2.length;
            int n = 0;
            for (int k = 0; k < length; ++k) {
                if (array2[k] == 9.223372E18f) {
                    n = k;
                    break;
                }
                if (length > 2 && length < 5 && array2[k] == 100.0) {
                    break;
                }
            }
            for (int l = 0; l < length; ++l) {
                if (n > 0) {
                    if (s.equals("Stoc") && l >= n - 2) {
                        array2[l] = 9.223372E18f;
                    }
                }
                else if (n != 0 || s.equals("Stoc")) {}
                array[l * 2] = this.for.a(l);
                if (array2[l] == 9.223372E18f) {
                    array[l * 2 + 1] = -1;
                }
                else {
                    array[l * 2 + 1] = this.if.do(array2[l]);
                }
            }
        }
        else if (o instanceof long[]) {
            final long[] array3 = (long[])o;
            for (int length2 = array3.length, n2 = 0; n2 < length2; ++n2) {
                array[n2 * 2] = this.for.a(n2);
                if (array3[n2] == 9.223372E18f) {
                    array[n2 * 2 + 1] = -1;
                }
                else {
                    array[n2 * 2 + 1] = this.if.a(array3[n2]);
                }
            }
        }
    }
    
    public static void for(final Object o, final int n, final Color color, final Graphics graphics) {
        final int[] array = (int[])o;
        graphics.setColor(color);
        for (int i = 0; i < n - 1; ++i) {
            if (array[i * 2 + 1] != -1 && array[i * 2 + 3] != -1) {
                graphics.drawLine(array[i * 2], array[i * 2 + 1], array[i * 2 + 2], array[i * 2 + 3]);
            }
        }
    }
    
    public void a(final h h, final h h2, final h h3, final h h4, final Object o) {
        final int[][] array = (int[][])o;
        final int a = h.a();
        final int n = (int)((this.do.width / a + 1) * 0.8);
        final int n2 = n / 2;
        int n3 = n / 8;
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < a; ++i) {
            final int a2 = this.for.a(i);
            if (h.for(i) != 9.223372E18f) {
                array[i * 4][0] = a2 - n2 / 2 - n3;
                array[i * 4][1] = this.if.do(h.for(i));
                array[i * 4 + 1][0] = a2 - n2 / 2;
                array[i * 4 + 1][1] = this.if.do(h2.for(i));
                array[i * 4 + 2][0] = a2 + n2 / 2;
                array[i * 4 + 2][1] = this.if.do(h3.for(i));
                array[i * 4 + 3][0] = a2 + n2 / 2 + n3;
                array[i * 4 + 3][1] = this.if.do(h4.for(i));
            }
            else {
                array[i * 4][0] = -1;
                array[i * 4][1] = -1;
                array[i * 4 + 1][0] = -1;
                array[i * 4 + 1][1] = -1;
                array[i * 4 + 2][0] = -1;
                array[i * 4 + 2][1] = -1;
                array[i * 4 + 3][0] = -1;
                array[i * 4 + 3][1] = -1;
            }
        }
    }
    
    public static void do(final Object o, final int n, final Color color, final Graphics graphics) {
        final int[][] array = (int[][])o;
        graphics.setColor(color);
        for (int i = 0; i < n; ++i) {
            if (array[i * 4][0] != -1) {
                graphics.drawLine(array[i * 4][0], array[i * 4][1], array[i * 4 + 1][0], array[i * 4][1]);
                int n2 = array[i * 4 + 2][0] - array[i * 4 + 1][0];
                if (n2 < 1) {
                    n2 = 1;
                }
                int n3 = array[i * 4 + 2][1] - array[i * 4 + 1][1];
                if (n3 < 1) {
                    n3 = 1;
                }
                graphics.fillRect(array[i * 4 + 1][0], array[i * 4 + 1][1], n2, n3);
                graphics.drawLine(array[i * 4 + 2][0], array[i * 4 + 3][1], array[i * 4 + 3][0], array[i * 4 + 3][1]);
            }
        }
    }
    
    public void if(final h h, final h h2, final h h3, final h h4, final Object o) {
        final int[][] array = (int[][])o;
        final int a = h.a();
        int n = (int)(this.do.width / (a + 1) * 0.8f);
        if (n < 1) {
            n = 1;
        }
        for (int i = 0; i < a; ++i) {
            final int a2 = this.for.a(i);
            if (h.for(i) != 9.223372E18f) {
                array[i * 4][0] = a2;
                array[i * 4][1] = this.if.do(h2.for(i));
                array[i * 4 + 1][0] = a2 - n / 2;
                array[i * 4 + 1][1] = this.if.do(h.for(i));
                array[i * 4 + 2][0] = a2 + n / 2;
                array[i * 4 + 2][1] = this.if.do(h4.for(i));
                array[i * 4 + 3][0] = a2;
                array[i * 4 + 3][1] = this.if.do(h3.for(i));
            }
            else {
                array[i * 4][0] = -1;
                array[i * 4][1] = -1;
                array[i * 4 + 1][0] = -1;
                array[i * 4 + 1][1] = -1;
                array[i * 4 + 2][0] = -1;
                array[i * 4 + 2][1] = -1;
                array[i * 4 + 3][0] = -1;
                array[i * 4 + 3][1] = -1;
            }
        }
    }
    
    public static void a(final Object o, final int n, final Color color, final Color color2, final Graphics graphics) {
        final int[][] array = (int[][])o;
        graphics.setColor(color2);
        for (int i = 0; i < n; ++i) {
            if (array[i * 4][0] != -1) {
                int n2 = array[i * 4 + 2][0] - array[i * 4 + 1][0];
                if (n2 < 1) {
                    n2 = 1;
                }
                if (array[i * 4 + 1][1] == array[i * 4 + 2][1]) {
                    final int n3 = 1;
                    graphics.drawLine(array[i * 4][0], array[i * 4][1], array[i * 4][0], array[i * 4 + 1][1]);
                    graphics.fillRect(array[i * 4 + 1][0], array[i * 4 + 1][1], n2, n3);
                    graphics.drawLine(array[i * 4 + 3][0], array[i * 4 + 2][1], array[i * 4 + 3][0], array[i * 4 + 3][1]);
                }
                else if (array[i * 4 + 1][1] > array[i * 4 + 2][1]) {
                    graphics.setColor(color);
                    final int n4 = array[i * 4 + 1][1] - array[i * 4 + 2][1];
                    graphics.drawLine(array[i * 4][0], array[i * 4][1], array[i * 4][0], array[i * 4 + 2][1]);
                    graphics.drawRect(array[i * 4 + 1][0], array[i * 4 + 2][1], n2, n4);
                    graphics.drawLine(array[i * 4 + 3][0], array[i * 4 + 1][1], array[i * 4 + 3][0], array[i * 4 + 3][1]);
                    graphics.setColor(color2);
                }
                else if (array[i * 4 + 1][1] < array[i * 4 + 2][1]) {
                    final int n5 = array[i * 4 + 2][1] - array[i * 4 + 1][1];
                    graphics.drawLine(array[i * 4][0], array[i * 4][1], array[i * 4][0], array[i * 4 + 1][1]);
                    graphics.fillRect(array[i * 4 + 1][0], array[i * 4 + 1][1], n2, n5);
                    graphics.drawLine(array[i * 4 + 3][0], array[i * 4 + 2][1], array[i * 4 + 3][0], array[i * 4 + 3][1]);
                }
            }
        }
    }
    
    public void a(final Object o, final Object o2, final float n) {
        final int[][] array = (int[][])o2;
        if (o instanceof h) {
            final h h = (h)o;
            final int a = h.a();
            array[0][0] = this.for.a(0);
            array[1][0] = this.do.y + this.do.height;
            int i;
            for (i = 0; i < a; ++i) {
                array[0][i + 1] = this.for.a(i);
                if (h.for(i) != 9.223372E18f) {
                    array[1][i + 1] = this.if.do(h.for(i));
                }
                else {
                    array[1][i + 1] = this.if.do(n);
                }
            }
            array[0][i + 1] = this.for.a(i - 1);
            array[1][i + 1] = this.do.y + this.do.height;
        }
        else if (o instanceof c) {
            final c c = (c)o;
            final int a2 = c.a();
            array[0][0] = this.for.a(0);
            array[1][0] = this.do.y + this.do.height;
            int j;
            for (j = 0; j < a2; ++j) {
                array[0][j + 1] = this.for.a(j);
                if (c.new(j) != 9.223372E18f) {
                    array[1][j + 1] = this.if.a(c.new(j));
                }
                else {
                    array[1][j + 1] = this.if.do(n);
                }
            }
            array[0][j + 1] = this.for.a(j - 1);
            array[1][j + 1] = this.do.y + this.do.height;
        }
        else {
            final float[] array2 = (float[])o;
            final int length = array2.length;
            array[0][0] = this.for.a(0);
            array[1][0] = this.do.y + this.do.height;
            int k;
            for (k = 0; k < length; ++k) {
                array[0][k + 1] = this.for.a(k);
                if (array2[k] != 9.223372E18f) {
                    array[1][k + 1] = this.if.do(array2[k]);
                }
                else {
                    array[1][k + 1] = this.if.do(n);
                }
            }
            array[0][k + 1] = this.for.a(k - 1);
            array[1][k + 1] = this.do.y + this.do.height;
        }
    }
    
    public static void if(final Object o, final int n, final Color color, final Graphics graphics) {
        final int[][] array = (int[][])o;
        graphics.setColor(color);
        graphics.fillPolygon(array[0], array[1], n + 2);
    }
    
    public static void int(final Object o, final int n, final Color color, final Graphics graphics) {
        final int[] array = (int[])o;
        graphics.setColor(color);
        for (int i = 0; i < n; ++i) {
            if (array[i * 2 + 1] != -1) {
                graphics.drawOval(array[i * 2] - 1, array[i * 2 + 1] - 1, 2, 2);
            }
        }
    }
    
    public void a(final Object o, final Object o2) {
        final int[][] array = (int[][])o2;
        if (o instanceof h) {
            final h h = (h)o;
            final int a = h.a();
            int n = (int)(this.do.width / a / 2.0f / 2.1);
            if (n < 1) {
                n = 0;
            }
            for (int i = 0; i < a; ++i) {
                final int a2 = this.for.a(i);
                array[i * 2][0] = a2 - n;
                array[i * 2 + 1][0] = a2 + n;
                if (array[i * 2][0] == array[i * 2 + 1][0]) {
                    final int[] array2 = array[i * 2 + 1];
                    final int n2 = 0;
                    ++array2[n2];
                }
                if (h.for(i) == 9.223372E18f) {
                    array[i * 2][1] = this.if.a(0L);
                    array[i * 2 + 1][1] = 0;
                }
                else {
                    array[i * 2][1] = this.if.do(h.for(i));
                    array[i * 2 + 1][1] = this.do.y + this.do.height - array[i * 2][1] + 1;
                }
            }
        }
        else if (o instanceof c) {
            final c c = (c)o;
            final int a3 = c.a();
            int n3 = (int)(this.do.width / a3 / 2.0f / 2.1);
            if (n3 < 1) {
                n3 = 0;
            }
            for (int j = 0; j < a3; ++j) {
                final int a4 = this.for.a(j);
                array[j * 2][0] = a4 - n3;
                array[j * 2 + 1][0] = a4 + n3;
                if (array[j * 2][0] == array[j * 2 + 1][0]) {
                    final int[] array3 = array[j * 2 + 1];
                    final int n4 = 0;
                    ++array3[n4];
                }
                if (c.new(j) == 9.223372E18f) {
                    array[j * 2][1] = this.if.a(0L);
                    array[j * 2 + 1][1] = 0;
                }
                else {
                    array[j * 2][1] = this.if.a(c.new(j));
                    array[j * 2 + 1][1] = this.do.y + this.do.height - array[j * 2][1] + 1;
                }
            }
        }
        else if (o instanceof float[]) {
            final float[] array4 = (float[])o;
            final int length = array4.length;
            int n5 = (int)(this.do.width / length / 2.0f / 2.1);
            if (n5 < 1) {
                n5 = 0;
            }
            for (int k = 0; k < length; ++k) {
                final int a5 = this.for.a(k);
                array[k * 2][0] = a5 - n5;
                array[k * 2 + 1][0] = a5 + n5;
                if (array[k * 2][0] == array[k * 2 + 1][0]) {
                    final int[] array5 = array[k * 2 + 1];
                    final int n6 = 0;
                    ++array5[n6];
                }
                if (array4[k] == 9.223372E18f) {
                    array[k * 2][1] = this.if.a(0L);
                    array[k * 2 + 1][1] = 0;
                }
                else {
                    array[k * 2][1] = this.if.do(array4[k]);
                    array[k * 2 + 1][1] = this.do.y + this.do.height - array[k * 2][1] + 1;
                }
            }
        }
        else if (o instanceof long[]) {
            final long[] array6 = (long[])o;
            final int length2 = array6.length;
            int n7 = (int)(this.do.width / length2 / 2.0f / 2.1);
            if (n7 < 1) {
                n7 = 0;
            }
            for (int l = 0; l < length2; ++l) {
                final int a6 = this.for.a(l);
                array[l * 2][0] = a6 - n7;
                array[l * 2 + 1][0] = a6 + n7;
                if (array[l * 2][0] == array[l * 2 + 1][0]) {
                    final int[] array7 = array[l * 2 + 1];
                    final int n8 = 0;
                    ++array7[n8];
                }
                if (array6[l] == 9.223372E18f) {
                    array[l * 2][1] = this.if.a(0L);
                    array[l * 2 + 1][1] = 0;
                }
                else {
                    array[l * 2][1] = this.if.a(array6[l]);
                    array[l * 2 + 1][1] = this.do.y + this.do.height - array[l * 2][1] + 1;
                }
            }
        }
    }
    
    public static void a(final Object o, final int n, final Color color, final Graphics graphics) {
        final int[][] array = (int[][])o;
        graphics.setColor(color);
        for (int i = 0; i < n; ++i) {
            graphics.fillRect(array[i * 2][0], array[i * 2][1], array[i * 2 + 1][0] - array[i * 2][0], array[i * 2 + 1][1]);
        }
    }
    
    static {
        d.char = 0;
    }
}
