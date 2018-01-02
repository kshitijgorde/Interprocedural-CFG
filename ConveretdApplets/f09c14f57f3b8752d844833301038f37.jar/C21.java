import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class C21
{
    int[] d;
    String e;
    String f;
    Random g;
    char[] h;
    
    public C21() {
        this.g = new Random();
        this.f = "+-abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.e = "uhHcMz0gs8G+SDn5LItbNy3mRCfrV9-PlZi4xTa6XAo1UvkJEpYeOWdQ7w2KjBFq";
        this.h = this.e.toCharArray();
        this.d = new int[256];
        for (int i = 0; i < 64; ++i) {
            this.d[this.h[i]] = i;
        }
    }
    
    public boolean a(final String s, final String s2) {
        if (s.length() < 3) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        if (s2.length() != 9) {
            return false;
        }
        final char char1 = s2.charAt(8);
        return ((char1 >= '0' && char1 <= '9') || (char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || char1 == '+' || char1 == '-') && s2.equals(this.b(s, s2));
    }
    
    public String b(final String s, final String s2) {
        String string;
        if (s.length() % 6 == 0) {
            string = s;
        }
        else {
            string = s + s.substring(0, 6 - s.length() % 6);
        }
        final char[] charArray = string.toCharArray();
        for (int i = 0; i < string.length(); ++i) {
            charArray[i] = (char)((charArray[i] + (charArray[i] << 8) << i % 8 & '\uff00') >> 8);
        }
        final int n = string.length() / 6;
        final long[] array = new long[n];
        int j = 0;
        int n2 = 0;
        while (j < n) {
            array[j] = 0L;
            for (int k = 0; k < 6; ++k) {
                final long[] array2 = array;
                final int n3 = j;
                array2[n3] <<= 8;
                final long[] array3 = array;
                final int n4 = j;
                array3[n4] |= charArray[n2++];
            }
            ++j;
        }
        int l = 9999;
        long n5 = 0L;
        if (s2 == null) {
            while (l > 63) {
                l = (int)Math.floor(this.g.nextDouble() * 64.0);
            }
        }
        else {
            l = this.d[s2.charAt(8)];
        }
        final char c = (char)l;
        for (int n6 = 0; n6 < 8; ++n6) {
            n5 = (n5 << 8 | l);
        }
        long n7 = n5;
        for (int n8 = 0; n8 < n; ++n8) {
            n7 ^= array[n8];
        }
        final char[] array4 = new char[9];
        final long n9 = 63L;
        for (int n10 = 7; n10 >= 0; --n10) {
            array4[n10] = (char)(n9 & n7);
            n7 >>= 6;
        }
        array4[8] = c;
        for (int n11 = 0; n11 < 9; ++n11) {
            array4[n11] = this.h[array4[n11]];
        }
        return new String(array4);
    }
}
