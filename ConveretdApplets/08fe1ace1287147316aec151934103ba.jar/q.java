// 
// Decompiled by Procyon v0.5.30
// 

class q
{
    private static String[] G;
    private static String[] H;
    private static String[] I;
    private static String[] J;
    private static String[] K;
    private static String[] L;
    private static String[] M;
    private static String[] N;
    private static String[] O;
    private static String[] P;
    private static String[] Q;
    private static String[] R;
    private static String[][] S;
    private static String w = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String x = "\u7d2c\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d2c";
    private static String y = "\u7d2c\u7d2c\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d2c\u7d2c";
    private static String z = "\u7d2c\u7d2c\u7d2c\u7d58\u7d58\u7d58\u7d58\u7d2c\u7d2c\u7d2c";
    private static String A = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d59\u7d59\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String B = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d58\u7d58\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String C = "\u7d2c\u7d2c\u7d2c\u7d58\u7d2c\u7d2c\u7d58\u7d2c\u7d2c\u7d2c";
    private static String T = "\u7d2c\u7d58\u7d59\u7d2c\u7d2c\u7d2c\u7d2c\u7d59\u7d58\u7d2c";
    private static String U = "\u7d2c\u7d2c\u7d58\u7d58\u7d5b\u7d5b\u7d58\u7d58\u7d2c\u7d2c";
    private static String V = "\u7d2c\u7d2c\u7d59\u7d58\u7d58\u7d58\u7d58\u7d59\u7d2c\u7d2c";
    private static String W = "\u7d2c\u7d59\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d59\u7d2c";
    private static String X = "\u7d2c\u7d58\u7d58\u7d58\u7d5e\u7d5e\u7d58\u7d58\u7d58\u7d2c";
    private static String Y = "\u7d2c\u7d2c\u7d5e\u7d5e\u7d2c\u7d2c\u7d5e\u7d5e\u7d2c\u7d2c";
    private static String Z = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d2d\u7d2d\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String _a = "\u7d2c\u7d2c\u7d2c\u7d59\u7d2d\u7d2d\u7d59\u7d2c\u7d2c\u7d2c";
    private static String aa = "\u7d2c\u7d2c\u7d2c\u7d5b\u7d58\u7d58\u7d5b\u7d2c\u7d2c\u7d2c";
    private static String ba = "\u7d2c\u7d2c\u7d58\u7d5e\u7d2c\u7d2c\u7d5e\u7d58\u7d2c\u7d2c";
    private static String ca = "\u7d2c\u7d2c\u7d5e\u7d2c\u7d2c\u7d2c\u7d2c\u7d5e\u7d2c\u7d2c";
    private static String da = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d5b\u7d5b\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String ea = "\u7d2c\u7d5f\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d2c\u7d5f\u7d2c";
    private static String fa = "\u7d2c\u7d2c\u7d2c\u7d2c\u7d5f\u7d5f\u7d2c\u7d2c\u7d2c\u7d2c";
    private static String ga = "\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58";
    private static String ha = "\u7d2c\u7d5f\u7d58\u7d58\u7d58\u7d58\u7d58\u7d58\u7d5f\u7d2c";
    private static String ia = "\u7d2c\u7d58\u7d58\u7d58\u7d5b\u7d5b\u7d58\u7d58\u7d58\u7d2c";
    private static String ja = "\u7d2c\u7d2c\u7d5e\u7d5e\u7d59\u7d59\u7d5e\u7d5e\u7d2c\u7d2c";
    private static String ka = "\u7d2c\u7d58\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d58\u7d2c";
    private static String la = "\u7d2c\u7d58\u7d58\u7d5b\u7d5b\u7d5b\u7d5b\u7d58\u7d58\u7d2c";
    private static String ma = "\u7d2c\u7d2c\u7d2c\u7d58\u7d5b\u7d5b\u7d58\u7d2c\u7d2c\u7d2c";
    private static String na = "\u7d2c\u7d2c\u7d2c\u7d5e\u7d59\u7d59\u7d5e\u7d2c\u7d2c\u7d2c";
    private static String oa = "\u7d2c\u7d58\u7d59\u7d5b\u7d5b\u7d5b\u7d5b\u7d59\u7d58\u7d2c";
    private static String pa = "\u7d2c\u7d2c\u7d5e\u7d5e\u7d58\u7d58\u7d5e\u7d5e\u7d2c\u7d2c";
    private static String qa = "\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5f\u7d5b\u7d5b";
    private static String ra = "\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b\u7d5b";
    private static String sa = "\u7d5b\u7d5f\u7d5b\u7d5b\u7d5e\u7d5e\u7d59\u7d5e\u7d5b\u7d5b";
    private static String ta = "\u7d58\u7d5e\u7d5e\u7d59\u7d5e\u7d5e\u7d58\u7d58\u7d58\u7d58";
    
    public static String[] _(final int n) {
        return q.S[n % q.S.length];
    }
    
    static {
        q.w = b(q.w);
        q.x = b(q.x);
        q.y = b(q.y);
        q.z = b(q.z);
        q.A = b(q.A);
        q.B = b(q.B);
        q.C = b(q.C);
        q.T = b(q.T);
        q.U = b(q.U);
        q.V = b(q.V);
        q.W = b(q.W);
        q.X = b(q.X);
        q.Y = b(q.Y);
        q.Z = b(q.Z);
        q._a = b(q._a);
        q.aa = b(q.aa);
        q.ba = b(q.ba);
        q.ca = b(q.ca);
        q.da = b(q.da);
        q.ea = b(q.ea);
        q.fa = b(q.fa);
        q.ga = b(q.ga);
        q.ha = b(q.ha);
        q.ia = b(q.ia);
        q.ja = b(q.ja);
        q.ka = b(q.ka);
        q.la = b(q.la);
        q.ma = b(q.ma);
        q.na = b(q.na);
        q.oa = b(q.oa);
        q.pa = b(q.pa);
        q.qa = b(q.qa);
        q.ra = b(q.ra);
        q.sa = b(q.sa);
        q.ta = b(q.ta);
        q.G = new String[] { q.w, q.x, q.y, q.z, q.A, q.w };
        q.H = new String[] { q.B, q.C, q.T, q.T, q.C, q.B };
        q.I = new String[] { q.w, q.U, q.w, q.w, q.V, q.w };
        q.J = new String[] { q.w, q.z, q.W, q.z, q.w, q.X };
        q.K = new String[] { q.x, q.y, q.Y, q.B, q.Z, q._a };
        q.L = new String[] { q.y, q.aa, q.ba, q.ca, q.da, q.A };
        q.M = new String[] { q.w, q.ea, q.w, q.w, q.fa, q.w };
        q.N = new String[] { q.ga, q.ha, q.V, q.z, q.B, q.w };
        q.O = new String[] { q.ia, q.ha, q.ja, q.B, q.w, q.w };
        q.P = new String[] { q.ga, q.ka, q.la, q.y, q.ma, q.na };
        q.Q = new String[] { q.ga, q.ka, q.oa, q.y, q.z, q.pa };
        q.R = new String[] { q.qa, q.ra, q.sa, q.ga, q.ga, q.ta };
        q.S = new String[][] { q.G, q.H, q.I, q.J, q.K, q.L, q.M, q.N, q.O, q.P, q.Q, q.R };
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u7d1d');
        }
        return new String(array);
    }
}
