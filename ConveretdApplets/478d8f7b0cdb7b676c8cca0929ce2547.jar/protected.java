import java.util.Locale;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class protected
{
    private SimpleDateFormat Ia;
    private SimpleDateFormat Ja;
    private SimpleDateFormat Ka;
    private SimpleDateFormat La;
    private SimpleDateFormat Ma;
    private SimpleDateFormat Na;
    private SimpleDateFormat Oa;
    private SimpleDateFormat Pa;
    private Date Qa;
    private boolean ya;
    private Thread Ra;
    private private wa;
    private static String T = "\u6a79";
    private static String U = "\u6a55\u6a5f\u6a46";
    private static String V = "\u6a7a";
    private static String W = "\u6a7f";
    private static String ba = "\u6a61";
    private static String ca = "\u6a76";
    private static String da = "\u6a5f\u6a5f\u6a5f";
    private static String ea = "\u6a6b\u6a6b";
    private static String ta = "\u6a40\u6a77\u6a61\u6a67\u6a7e\u6a66\u6a32\u6a74\u6a60\u6a7d\u6a7f\u6a32\u6a73\u6a62\u6a62\u6a7e\u6a6b\u6a7b\u6a7c\u6a75\u6a32\u6a74\u6a7d\u6a60\u6a7f\u6a67\u6a7e\u6a73\u6a32\u6a35\u6a3a\u6a7a\u6a7d\u6a67\u6a60\u6a32\u6a38\u6a32\u6a23\u6a27\u6a3b\u6a32\u6a39\u6a32\u6a3a\u6a7f\u6a7b\u6a7c\u6a67\u6a66\u6a77\u6a61\u6a32\u6a3d\u6a32\u6a26\u6a3b\u6a35\u6a28\u6a32";
    private static String ua = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a76\u6a73\u6a6b\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Sa = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a7f\u6a7d\u6a7c\u6a66\u6a7a\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Ta = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a4b\u6a77\u6a73\u6a60\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Ua = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a7a\u6a7d\u6a67\u6a60\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Va = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a7f\u6a7b\u6a7c\u6a67\u6a66\u6a77\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Wa = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a55\u6a5f\u6a46\u6a32\u6a61\u6a77\u6a71\u6a7d\u6a7c\u6a76\u6a61\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Xa = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a7e\u6a7d\u6a71\u6a73\u6a7e\u6a32\u6a7a\u6a7d\u6a67\u6a60\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Ya = "\u6a51\u6a67\u6a60\u6a60\u6a77\u6a7c\u6a66\u6a32\u6a7e\u6a7d\u6a71\u6a73\u6a7e\u6a32\u6a7f\u6a7b\u6a7c\u6a67\u6a66\u6a77\u6a32\u6a64\u6a73\u6a7e\u6a67\u6a77\u6a28\u6a32";
    private static String Za = "\u6a32\u6a3f\u6a32";
    
    public protected(final boolean ya, final private wa) {
        this.ya = false;
        this.ya = ya;
        this.wa = wa;
        (this.Ia = new SimpleDateFormat(protected.T)).setTimeZone(TimeZone.getTimeZone(protected.U));
        (this.Oa = new SimpleDateFormat(protected.V)).setTimeZone(TimeZone.getDefault());
        (this.Pa = new SimpleDateFormat(protected.W)).setTimeZone(TimeZone.getDefault());
        (this.Ja = new SimpleDateFormat(protected.W)).setTimeZone(TimeZone.getTimeZone(protected.U));
        (this.Ka = new SimpleDateFormat(protected.ba)).setTimeZone(TimeZone.getTimeZone(protected.U));
        (this.La = new SimpleDateFormat(protected.ca)).setTimeZone(TimeZone.getTimeZone(protected.U));
        (this.Ma = new SimpleDateFormat(protected.da, Locale.ENGLISH)).setTimeZone(TimeZone.getTimeZone(protected.U));
        (this.Na = new SimpleDateFormat(protected.ea)).setTimeZone(TimeZone.getTimeZone(protected.U));
        this.Qa = new Date();
        this.Ra = new Thread(new switch(this, null));
    }
    
    public void start() {
        this.Ra.start();
    }
    
    public int a() {
        final int n = this.getHours() * 15 + this.getMinutes() / 4;
        this.b(protected.ta.concat(String.valueOf(String.valueOf(n))));
        return n;
    }
    
    public int _() {
        return this.getMinutes() * 15 % 60 + this.getSeconds() / 4;
    }
    
    public int b() {
        return this.getSeconds() * 15 % 60;
    }
    
    public void reset() {
        this.Qa = new Date();
    }
    
    public String a() {
        final String format = this.La.format(this.Qa);
        this.b(protected.ua.concat(String.valueOf(String.valueOf(format))));
        return format;
    }
    
    public String b() {
        final String format = this.Ma.format(this.Qa);
        this.b(protected.Sa.concat(String.valueOf(String.valueOf(format))));
        return format;
    }
    
    public String _() {
        final String format = this.Na.format(this.Qa);
        this.b(protected.Ta.concat(String.valueOf(String.valueOf(format))));
        return format;
    }
    
    private int getHours() {
        final int int1 = Integer.parseInt(this.Ia.format(this.Qa));
        this.b(protected.Ua.concat(String.valueOf(String.valueOf(int1))));
        return int1;
    }
    
    private int getMinutes() {
        final int int1 = Integer.parseInt(this.Ja.format(this.Qa));
        this.b(protected.Va.concat(String.valueOf(String.valueOf(int1))));
        return int1;
    }
    
    private int getSeconds() {
        final int int1 = Integer.parseInt(this.Ka.format(this.Qa));
        this.b(protected.Wa.concat(String.valueOf(String.valueOf(int1))));
        return int1;
    }
    
    private int j() {
        final int int1 = Integer.parseInt(this.Oa.format(this.Qa));
        this.b(protected.Xa.concat(String.valueOf(String.valueOf(int1))));
        return int1;
    }
    
    private int k() {
        final int int1 = Integer.parseInt(this.Pa.format(this.Qa));
        this.b(protected.Ya.concat(String.valueOf(String.valueOf(int1))));
        return int1;
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(protected.Za).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static int b(final protected protected1) {
        return protected1.j();
    }
    
    static int _(final protected protected1) {
        return protected1.k();
    }
    
    static private a(final protected protected1) {
        return protected1.wa;
    }
    
    static boolean _(final protected protected1) {
        return protected1.ya;
    }
    
    private static String j(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6a12');
        }
        return new String(array);
    }
    
    static {
        protected.T = j(protected.T);
        protected.U = j(protected.U);
        protected.V = j(protected.V);
        protected.W = j(protected.W);
        protected.ba = j(protected.ba);
        protected.ca = j(protected.ca);
        protected.da = j(protected.da);
        protected.ea = j(protected.ea);
        protected.ta = j(protected.ta);
        protected.ua = j(protected.ua);
        protected.Sa = j(protected.Sa);
        protected.Ta = j(protected.Ta);
        protected.Ua = j(protected.Ua);
        protected.Va = j(protected.Va);
        protected.Wa = j(protected.Wa);
        protected.Xa = j(protected.Xa);
        protected.Ya = j(protected.Ya);
        protected.Za = j(protected.Za);
    }
}
