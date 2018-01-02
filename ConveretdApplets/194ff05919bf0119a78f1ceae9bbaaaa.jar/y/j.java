// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Image;

public final class j implements db
{
    private aj a;
    
    public j() {
    }
    
    public static Image a(String s) {
        if ((s = s).equals("X")) {
            s = "\u0001\u0001\u0001\u00f8\ufcf8\u0000";
        }
        final int b = b(s = s);
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(b, a(s), a(s, 1, 1), 0, b));
    }
    
    public static boolean[][] a(String s) {
        if (s.equals("X")) {
            s = "\u0001\u0001\u0001\u00f8\ufcf8\u0000";
        }
        final int[] a = a(s, 1, 1);
        final int a2 = a(s);
        final int b = b(s);
        final boolean[][] array = new boolean[a2][b];
        for (int i = 0; i < a2; ++i) {
            for (int j = 0; j < b; ++j) {
                if ((a[b * i + j] & 0xFF000000) == 0xFF000000) {
                    array[i][j] = true;
                }
            }
        }
        return array;
    }
    
    public static int a(final String s) {
        return s.charAt(0) + '\u0001' - '\u0001';
    }
    
    public static int b(final String s) {
        return s.charAt(1) + '\u0001' - '\u0001';
    }
    
    public static int[] a(final String s, int n, int char1) {
        n = -1;
        ++n;
        char1 = s.charAt(0);
        ++n;
        final char char2 = s.charAt(1);
        ++n;
        final char char3;
        final int[] array = new int[char3 = s.charAt(2)];
        for (char c = '\0'; c < char3; ++c) {
            array[c] = (s.charAt(++n) << 16) + s.charAt(++n);
            if ((array[c] & 0xFF000000) == 0x0) {
                array[c] = 16777215;
            }
        }
        int n2 = 1;
        for (int i = 2; i < array.length; i <<= 1) {
            ++n2;
        }
        final char c2 = (char)(char2 + '\u0001' - '\u0001');
        final int[] array2 = new int[c2 * (char1 + 1 - 1)];
        ++n;
        for (char c3 = '\0'; c3 < char2 * char1; ++c3) {
            final char c4 = (char)(c3 % char2);
            final char c5 = (char)(c3 / char2);
            final char c6 = (char)(c3 / '\u0007');
            final long n3 = 1L << c3 % '\u0007';
            int n4 = 0;
            int n5 = 1;
            for (int j = 0; j < n2; ++j) {
                if ((s.charAt(n + j * ((char2 * char1 + 6) / 7) + c6) & n3) != 0x0L) {
                    n4 += n5;
                }
                n5 <<= 1;
            }
            final char c7 = (char)(c4 + c5 * c2);
            final int n6 = array2[c7];
            final int n7 = array[n4];
            if (n6 != 0) {
                array2[c7] = 0;
                for (int k = 0; k < 4; ++k) {
                    final int n8 = ((n6 >> (k << 3) & 0xFF) + (n7 >> (k << 3) & 0xFF)) / 2;
                    final int[] array3 = array2;
                    final char c8 = c7;
                    array3[c8] |= n8 << (k << 3);
                }
            }
            else {
                array2[c7] = n7;
            }
        }
        return array2;
    }
    
    public j(final aj a) {
        this.a = a;
    }
    
    public final void a() {
    }
    
    public final void b() {
        this.a.a().a(this.a.b());
    }
    
    public final void c() {
        this.a.a().a((u)null);
    }
    
    public final void a(final eu eu) {
    }
    
    public final void d() {
    }
    
    public final void a(final String s) {
    }
    
    public final void e() {
    }
    
    public final void a(final int n) {
    }
    
    public final boolean a(final byte b, final DataInputStream dataInputStream) {
        return false;
    }
    
    public final void f() {
    }
    
    public final void g() {
    }
    
    public final boolean a() {
        return true;
    }
    
    public final boolean a(final boolean b) {
        return true;
    }
    
    public final void b(final int n) {
    }
}
