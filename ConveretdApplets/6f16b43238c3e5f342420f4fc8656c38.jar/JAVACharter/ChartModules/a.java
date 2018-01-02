// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import JAVACharter.b.h;

public class a
{
    private static float if;
    private static long a;
    
    public void a() {
    }
    
    public static void a(final int n, final int n2, final int n3, final float[] array, final float[] array2) {
        int n4 = 0;
        if (array.length != array2.length) {
            n4 = n * -1;
        }
        for (int i = n; i <= n2; ++i) {
            if (array[i] != JAVACharter.ChartModules.a.if) {
                float n5 = 0.0f;
                int j = 0;
                int n6 = 0;
                while (j < n3) {
                    if (array[i - j] != JAVACharter.ChartModules.a.if) {
                        n5 += array[i - j];
                        ++n6;
                    }
                    ++j;
                }
                if (n6 == n3) {
                    array2[i + n4] = n5 / n3;
                }
                else {
                    array2[i + n4] = JAVACharter.ChartModules.a.if;
                }
            }
            else {
                array2[i + n4] = JAVACharter.ChartModules.a.if;
            }
        }
    }
    
    public static void a(final int n, final int n2, final long n3, final h h, final float[] array) {
        for (int i = n; i <= n2; ++i) {
            float n4 = 0.0f;
            if (h.for(i) != JAVACharter.ChartModules.a.if) {
                int n5 = 0;
                int n6 = 0;
                while (n5 < n3) {
                    if (h.for(i - n5) != JAVACharter.ChartModules.a.if) {
                        n4 += h.for(i - n5);
                        ++n6;
                    }
                    ++n5;
                }
                if (n6 == n3) {
                    array[i] = n4 / n3;
                }
                else {
                    array[i] = JAVACharter.ChartModules.a.if;
                }
            }
            else {
                array[i] = JAVACharter.ChartModules.a.if;
            }
        }
    }
    
    static {
        JAVACharter.ChartModules.a.if = 9.223372E18f;
        JAVACharter.ChartModules.a.a = Long.MAX_VALUE;
    }
}
