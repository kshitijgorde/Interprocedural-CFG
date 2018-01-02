import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class synchronized
{
    static final int Fa = 5;
    static final int Ga = 6;
    static final int Ha = 3;
    static int[] sa;
    static int[] ta;
    static boolean[] Ia;
    static Rectangle oa;
    static int Ja;
    private static Font Ka;
    private static String ya = "\u0d1b\u0d28\u0d33\u0d3b\u0d36";
    
    static void a() {
        for (int i = 0; i < 5; ++i) {
            if (!synchronized.Ia[i]) {
                synchronized.sa[i] = (int)(Math.random() * 6.0) + 1;
            }
        }
        for (int j = 0; j < 6; ++j) {
            synchronized.ta[j] = 0;
        }
        for (int k = 0; k < 5; ++k) {
            final int[] ta = synchronized.ta;
            final int n = synchronized.sa[k] - 1;
            ++ta[n];
        }
        ++synchronized.Ja;
    }
    
    static void b(final int n) {
        if (n >= 0 && n < 5) {
            synchronized.Ia[n] = !synchronized.Ia[n];
        }
    }
    
    static void reset() {
        b();
        synchronized.Ja = 0;
    }
    
    static void b() {
        for (int i = 0; i < 5; ++i) {
            synchronized.Ia[i] = false;
        }
    }
    
    static void b(final Graphics graphics) {
        final String value = String.valueOf(3 - synchronized.Ja);
        final int b = switch.b(value, true, synchronized.oa, graphics);
        graphics.setColor(Color.white);
        graphics.setFont(synchronized.Ka);
        graphics.drawString(value, b, 396);
    }
    
    static boolean contains(final int n, final int n2) {
        return synchronized.oa.contains(n, n2);
    }
    
    static {
        synchronized.ya = _(synchronized.ya);
        synchronized.sa = new int[5];
        synchronized.ta = new int[6];
        synchronized.Ia = new boolean[5];
        synchronized.oa = new Rectangle();
        synchronized.Ka = new Font(synchronized.ya, 1, 11);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF0D5A);
        }
        return new String(array);
    }
}
