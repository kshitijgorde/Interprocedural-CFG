import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class extends extends Random
{
    private static extends c;
    private static String qb = "\ua955\ua959\ua940\ua918\ua955\ua94d\ua94b\ua94c\ua918\ua95a\ua95d\ua918\ua948\ua957\ua94b\ua951\ua94c\ua951\ua94e\ua95d";
    
    private extends() {
        super(_(65535L));
    }
    
    private extends(final long n) {
        super(n);
    }
    
    private int _(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(extends.qb);
        }
        if ((n & -n) == n) {
            return n * this.next(31) >> 31;
        }
        int next;
        int n2;
        do {
            next = this.next(31);
            n2 = next % n;
        } while (next - n2 + (n - 1) < 0);
        return n2;
    }
    
    private boolean a() {
        return (this.nextInt() & 0x8000) != 0x0;
    }
    
    public static int a(final int n) {
        return _()._(n);
    }
    
    public static int a(final int n, final int n2) {
        return _()._(n2 - n + 1) + n;
    }
    
    public static boolean b() {
        return _().a();
    }
    
    public static extends _() {
        if (extends.c == null) {
            extends.c = new extends();
        }
        return extends.c;
    }
    
    public static extends _(final long n) {
        if (extends.c == null) {
            extends.c = new extends(n);
        }
        return extends.c;
    }
    
    public static long _(final long n) {
        return System.currentTimeMillis() & n;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFA938);
        }
        return new String(array);
    }
    
    static {
        extends.qb = a(extends.qb);
    }
}
