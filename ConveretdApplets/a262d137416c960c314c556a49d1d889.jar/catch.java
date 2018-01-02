import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class catch extends Random
{
    private static catch T1;
    private static String z = "\ub7ca\ub7c6\ub7df\ub787\ub7ca\ub7d2\ub7d4\ub7d3\ub787\ub7c5\ub7c2\ub787\ub7d7\ub7c8\ub7d4\ub7ce\ub7d3\ub7ce\ub7d1\ub7c2";
    
    private catch() {
        super(a(65535L));
    }
    
    private catch(final long n) {
        super(n);
    }
    
    private int a(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(catch.z);
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
    
    public static int _(final int n, final int n2) {
        return _().a(n2 - n + 1) + n;
    }
    
    public static boolean b() {
        return _().a();
    }
    
    public static catch _() {
        if (catch.T1 == null) {
            catch.T1 = new catch();
        }
        return catch.T1;
    }
    
    public static catch _(final long n) {
        if (catch.T1 == null) {
            catch.T1 = new catch(n);
        }
        return catch.T1;
    }
    
    public static long a(final long n) {
        return System.currentTimeMillis() & n;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEB7A7);
        }
        return new String(array);
    }
    
    static {
        catch.z = _(catch.z);
    }
}
