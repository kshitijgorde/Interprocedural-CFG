// 
// Decompiled by Procyon v0.5.30
// 

public class static
{
    public static final int fa = 0;
    public static final int ga = 1;
    public static final int ha = 2;
    public static final int ia = 3;
    public static final int ja = 4;
    private int ka;
    private int la;
    private int ma;
    private int na;
    private int oa;
    private int pa;
    private String qa;
    private String ra;
    private String sa;
    private static String T = "";
    private static String U = "\u593b\u5908\u591b\u5910\u590a\u5937\u593a\u5944\u595e";
    private static String V = "\u5952\u595e\u5930\u593b\u592a\u595e\u591a\u591b\u591d\u590c\u591b\u591b\u590d\u5944\u595e";
    private static String W = "\u5952\u595e\u5930\u593b\u592a\u595e\u5913\u5917\u5910\u590b\u590a\u591b\u590d\u5944\u595e";
    private static String ba = "\u5952\u595e\u5930\u593b\u592a\u595e\u590d\u591b\u591d\u5911\u5910\u591a\u590d\u5944\u595e";
    private static String ca = "\u5952\u595e\u5932\u5911\u591d\u591f\u5912\u595e\u5916\u5911\u590b\u590c\u590d\u5944\u595e";
    private static String da = "\u5952\u595e\u5932\u5911\u591d\u591f\u5912\u595e\u5913\u5917\u5910\u590b\u590a\u591b\u590d\u5944\u595e";
    private static String ea = "\u5952\u595e\u5930\u593b\u592a\u595e\u591a\u591f\u5907\u5944\u595e";
    private static String ta = "\u5952\u595e\u5930\u593b\u592a\u595e\u5913\u5911\u5910\u590a\u5916\u5944\u595e";
    private static String ua = "\u5952\u595e\u5930\u593b\u592a\u595e\u5907\u591b\u591f\u590c\u5944\u595e";
    
    public static(final int ka) {
        this.ka = 0;
        this.la = 0;
        this.ma = 0;
        this.na = 0;
        this.oa = 0;
        this.pa = 0;
        this.qa = static.T;
        this.ra = static.T;
        this.sa = static.T;
        this.ka = ka;
    }
    
    public int g() {
        return this.ka;
    }
    
    public void b(final int la) {
        this.la = la;
    }
    
    public void h(final int na) {
        this.na = na;
    }
    
    public void i(final int ma) {
        this.ma = ma;
    }
    
    public void _(final int oa) {
        this.oa = oa;
    }
    
    public void a(final int pa) {
        this.pa = pa;
    }
    
    public void _(final String qa) {
        this.qa = qa;
    }
    
    public void a(final String ra) {
        this.ra = ra;
    }
    
    public void h(final String sa) {
        this.sa = sa;
    }
    
    public String a() {
        return this.qa;
    }
    
    public String b() {
        return this.ra;
    }
    
    public String _() {
        return this.sa;
    }
    
    public int a() {
        return this.la;
    }
    
    public int _() {
        return this.na;
    }
    
    public int b() {
        return this.ma;
    }
    
    public int h() {
        return this.na * 6;
    }
    
    public int i() {
        return this.ma * 6;
    }
    
    public int j() {
        return this.oa;
    }
    
    public int k() {
        return this.pa;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(static.U).append(this.ka).append(static.V).append(this.la).append(static.W).append(this.na).append(static.ba).append(this.ma).append(static.ca).append(this.oa).append(static.da).append(this.pa).append(static.ea).append(this.qa).append(static.ta).append(this.ra).append(static.ua).append(this.sa)));
    }
    
    static {
        static.T = b(static.T);
        static.U = b(static.U);
        static.V = b(static.V);
        static.W = b(static.W);
        static.ba = b(static.ba);
        static.ca = b(static.ca);
        static.da = b(static.da);
        static.ea = b(static.ea);
        static.ta = b(static.ta);
        static.ua = b(static.ua);
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x1597E);
        }
        return new String(array);
    }
}
