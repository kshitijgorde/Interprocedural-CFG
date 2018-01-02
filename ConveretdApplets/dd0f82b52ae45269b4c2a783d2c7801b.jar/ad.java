import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ad
{
    public static int a;
    private ArrayList c;
    public boolean[] b;
    
    public ad() {
        final String[] array = { "a", "p", "z", "l", "g", "i", "t", "y", "e", "o", "x", "u", "k", "s", "v", "n" };
        final int[] array2 = { 20, 21, 22, 3, 16, 17, 18, 23, 4, 5, 6, 11, 12, 13, 14, 19, 0, 1, 2, 7, 8, 9, 10 };
        final int[] array3 = { 28, 29, 30, 3, 24, 25, 26, 31, 12, 13, 14, 19, 20, 21, 22, 27, 8, 9, 10, 15, 16, 17, 18, 4, 5, 6, 11, 0, 1, 2, 7 };
        this.b = new boolean[65536];
        this.c = new ArrayList();
    }
    
    public final int a(final int n) {
        for (int i = 0; i < this.c.size(); ++i) {
            this.e(i);
            if (n == 0) {
                return i;
            }
        }
        return 0;
    }
    
    public final int a() {
        return this.c.size();
    }
    
    private Z e(final int n) {
        return this.c.get(n);
    }
    
    public final int b(final int n) {
        this.e(n);
        return 0;
    }
    
    public final int c(final int n) {
        this.e(n);
        return 0;
    }
    
    public final int d(final int n) {
        this.e(n);
        return 0;
    }
    
    static {
        ad.a = 0;
    }
}
