// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.HashSet;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Set;

public class z
{
    private static boolean a;
    private static Set b;
    private static /* synthetic */ boolean c;
    
    public static void a() {
        String[] availableFontFamilyNames;
        for (int length = (availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).length, i = 0; i < length; ++i) {
            z.b.add(availableFontFamilyNames[i].toLowerCase());
        }
        z.a = true;
    }
    
    public static Font a(final String s, String lowerCase, final int n) {
        if (!z.c && !z.a) {
            throw new AssertionError();
        }
        if (s == null || s.length() == 0) {
            return null;
        }
        if (n < 1) {
            return null;
        }
        int n2 = 0;
        if ((lowerCase = lowerCase.toLowerCase()).contains("bold")) {
            n2 = 1;
        }
        if (lowerCase.contains("italic")) {
            n2 |= 0x2;
        }
        if (lowerCase.contains("italic")) {
            n2 |= 0x2;
        }
        if (n2 == 0) {
            n2 = n2;
        }
        final String[] split;
        if ((split = s.split(",")).length == 0) {
            throw new RuntimeException("Invalid font name in stylesheet " + lowerCase);
        }
        String[] array;
        for (int length = (array = split).length, i = 0; i < length; ++i) {
            final String trim = array[i].trim();
            if (z.b.contains(trim.toLowerCase())) {
                return new Font(trim, n2, n);
            }
        }
        throw new RuntimeException("Invalid font name in stylesheet " + lowerCase + ": " + s);
    }
    
    static {
        z.c = !z.class.desiredAssertionStatus();
        z.a = false;
        z.b = new HashSet();
    }
}
