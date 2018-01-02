import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class qb
{
    public static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final boolean a = ub.a;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final int n6 = 4;
        final double n7 = (n2 - n4) / (n - n3);
        if (n5 == 1) {
            graphics.drawLine(n, n2, n3, n4);
            return;
        }
        if (n == n3) {
            int i = 0;
            while (i < n5) {
                graphics.drawLine(n + i - n5 / 2, n2, n3 + i - n5 / 2, n4);
                ++i;
                if (a) {
                    break;
                }
            }
            return;
        }
        if (n2 == n4) {
            int j = 0;
            while (j < n5) {
                graphics.drawLine(n, n2 + j - n5 / 2, n3, n4 + j - n5 / 2);
                ++j;
                if (a) {
                    break;
                }
            }
            return;
        }
        Label_0378: {
            if (n7 < -1.0 || n7 >= 1.0) {
                array[0] = n - (int)Math.floor((n5 - 1) / 2.0);
                array2[0] = n2;
                array[1] = n + (int)Math.ceil((n5 - 1) / 2.0);
                array2[1] = n2;
                array[2] = n3 + (int)Math.floor((n5 - 1) / 2.0);
                array2[2] = n4;
                array[3] = n3 - (int)Math.ceil((n5 - 1) / 2.0);
                array2[3] = n4;
                if (!a) {
                    break Label_0378;
                }
            }
            if (n7 >= -1.0 || n7 < 1.0) {
                array[0] = n;
                array2[0] = n2 - (int)Math.floor((n5 - 1) / 2.0);
                array[1] = n;
                array2[1] = n2 + (int)Math.ceil((n5 - 1) / 2.0);
                array[2] = n3;
                array2[2] = n4 + (int)Math.floor((n5 - 1) / 2.0);
                array[3] = n3;
                array2[3] = n4 - (int)Math.ceil((n5 - 1) / 2.0);
            }
        }
        graphics.fillPolygon(array, array2, n6);
        if (q.a != 0) {
            ub.a = !a;
        }
    }
    
    public static void a(final Graphics graphics, final int[] array, final int[] array2, final int n) {
        final boolean a = ub.a;
        final int min = Math.min(array.length, array2.length);
        int i = 0;
        while (i < min - 1) {
            a(graphics, array[i], array2[i], array[i + 1], array2[i + 1], n);
            ++i;
            if (a) {
                break;
            }
        }
    }
}
