import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public final class i
{
    private static Random a;
    
    public static void a() {
        i.a = null;
        i.a = new Random(System.currentTimeMillis());
    }
    
    public static int a(final int n, final int n2) {
        final int min = Math.min(n, n2);
        return Math.abs(i.a.nextInt()) % (Math.max(n, n2) - min + 1) + min;
    }
    
    public static int b(final int n, final int n2) {
        return n * 100 / n2;
    }
    
    public static float a(final float n, final float n2) {
        return n * n2 / 100.0f;
    }
    
    public static int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static long a(final long n, final long n2, final long n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static boolean c(final int n, final int n2) {
        return (n & n2) != 0x0;
    }
    
    public static float a(final float n, final float n2, final float n3, final float n4) {
        return (float)Math.sqrt(b(n, n2, n3, n4));
    }
    
    public static float b(final float n, final float n2, final float n3, final float n4) {
        final float n5 = n3 - n;
        final float n6 = n4 - n2;
        final float n7 = n5;
        final float n8 = n7 * n7;
        final float n9 = n6;
        return n8 + n9 * n9;
    }
    
    public static boolean b(final int n, final int n2, final int n3) {
        return n >= n2 && n <= n3;
    }
    
    public static boolean b(final long n, final long n2, final long n3) {
        return n >= n2 && n <= n3;
    }
    
    public static float c(final float n, final float n2, final float n3, final float n4) {
        final double n5 = n3 - n;
        final double n6 = n4 - n2;
        if (n5 == 0.0) {
            if (n6 > 0.0) {
                return 180.0f;
            }
            return 0.0f;
        }
        else if (n6 == 0.0) {
            if (n5 > 0.0) {
                return 90.0f;
            }
            return 270.0f;
        }
        else {
            final double n7 = Math.atan(n5 / n6) * 180.0 / 3.141592653589793;
            if (n5 < 0.0) {
                return (float)(((n6 < 0.0) ? 360.0 : 180.0) - n7);
            }
            return (float)(((n6 < 0.0) ? 0.0 : 180.0) - n7);
        }
    }
    
    public static float b(final float n, final float n2) {
        final float n3;
        if ((n3 = n2 - n) > 180.0f) {
            return n3 - 360.0f;
        }
        if (n3 < -180.0f) {
            return 360.0f + n3;
        }
        return n3;
    }
    
    public static int a(final int n) {
        if (n == 0) {
            return 0;
        }
        return n / Math.abs(n);
    }
    
    public static float b(final int n) {
        return (float)Math.sin(n * 3.141592653589793 / 180.0);
    }
    
    public static float c(final int n) {
        return (float)Math.cos(n * 3.141592653589793 / 180.0);
    }
    
    public static float d(final int n) {
        return (float)Math.tan(n * 3.141592653589793 / 180.0);
    }
    
    public static float a(final float n) {
        return (float)Math.sin(n * 3.141592653589793 / 180.0);
    }
    
    public static float b(final float n) {
        return (float)Math.cos(n * 3.141592653589793 / 180.0);
    }
    
    public static float c(final float n) {
        return (float)Math.tan(n * 3.141592653589793 / 180.0);
    }
    
    public static float b(final float n, final float n2, final float n3) {
        return n2 + (n3 - n2) * n;
    }
    
    public static float a(final float n, final float n2, final float n3, final float n4, final float n5) {
        return b((n - n2) / (n3 - n2), n4, n5);
    }
    
    public static String a(final long n) {
        Math.max(1L, n);
        if (n % 10L == 1L && n != 11L) {
            return a("\u0005\u000f");
        }
        if (n % 10L == 2L && n != 12L) {
            return a("\u0018\u001f");
        }
        if (n % 10L == 3L && n != 13L) {
            return a("\u0004\u001f");
        }
        return a("\u0002\u0013");
    }
    
    public static int e(final int n) {
        if (n <= 0) {
            return 1;
        }
        return n * e(n - 1);
    }
    
    static {
        i.a = new Random(System.currentTimeMillis());
    }
    
    private static String a(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'v';
                    break;
                }
                case 1: {
                    c2 = '{';
                    break;
                }
                case 2: {
                    c2 = 'H';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = 'D';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
