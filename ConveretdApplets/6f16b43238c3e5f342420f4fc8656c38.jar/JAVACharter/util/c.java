// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.util;

public class c
{
    public static int a(final int n, final int n2) {
        if (n == n2) {
            return n;
        }
        int n3;
        int n4;
        if (n > n2) {
            n3 = n;
            n4 = n2;
        }
        else {
            n4 = n;
            n3 = n2;
        }
        if (n3 % n4 == 0) {
            return n4;
        }
        int n5;
        for (n5 = n4; n3 % n5 != 0; --n5) {}
        return n5;
    }
    
    public static long a(final long n, final long n2) {
        if (n == n2) {
            return n;
        }
        long n3;
        long n4;
        if (n > n2) {
            n3 = n;
            n4 = n2;
        }
        else {
            n4 = n;
            n3 = n2;
        }
        if (n3 % n4 == 0L) {
            return n4;
        }
        long n5;
        for (n5 = n4; n3 % n5 != 0L || n4 % n5 != 0L; --n5) {}
        return n5;
    }
}
