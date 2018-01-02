import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Random
{
    private static m BJc;
    private static String ta = "\uca0c\uca00\uca19\uca41\uca0c\uca14\uca12\uca15\uca41\uca03\uca04\uca41\uca11\uca0e\uca12\uca08\uca15\uca08\uca17\uca04";
    
    private m() {
        super(a(65535L));
    }
    
    private m(final long n) {
        super(n);
    }
    
    private int a(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(m.ta);
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
    
    public static int _(final int n) {
        return _().a(n);
    }
    
    public static int b(final int n, final int n2) {
        return _().a(n2 - n + 1) + n;
    }
    
    public static boolean b() {
        return _().a();
    }
    
    public static m _() {
        if (m.BJc == null) {
            m.BJc = new m();
        }
        return m.BJc;
    }
    
    public static m a(final long n) {
        if (m.BJc == null) {
            m.BJc = new m(n);
        }
        return m.BJc;
    }
    
    public static long a(final long n) {
        return System.currentTimeMillis() & n;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFCA61);
        }
        return new String(array);
    }
    
    static {
        m.ta = a(m.ta);
    }
}
