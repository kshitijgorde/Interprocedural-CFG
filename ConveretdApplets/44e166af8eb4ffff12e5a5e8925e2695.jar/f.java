// 
// Decompiled by Procyon v0.5.30
// 

public class f extends o
{
    private static o[] L;
    private static int M;
    private static int N;
    private static o O;
    private static String P;
    
    public f(final String s) {
        super.n = s.length() * f.M;
        super.o = f.N;
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += C(s.charAt(i)).j;
        }
        final int j = n;
        super.j = j;
        super.k = new l[j];
        int n2 = 0;
        for (int k = 0; k < s.length(); ++k) {
            final o c = C(s.charAt(k));
            for (int l = 0; l < c.j; ++l) {
                super.k[n2++] = new l((f.M - super.n >> 1) + f.M * k + c.k[l].d, c.k[l].e);
            }
        }
    }
    
    private static o C(final char c) {
        for (int i = 0; i < f.P.length(); ++i) {
            if (c == f.P.charAt(i)) {
                return f.L[i];
            }
        }
        return f.O;
    }
    
    public static void B(final o[] l, final String p2) {
        f.L = l;
        f.M = l[0].n;
        f.N = l[0].o;
        f.P = p2;
        f.O = new o();
    }
}
