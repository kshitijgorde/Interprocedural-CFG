import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public int[] ah;
    public String ag;
    public String af;
    public String ae;
    public String ad;
    public String ac;
    public String ab;
    public String aa;
    public String z;
    public String w;
    public String v;
    
    public e(final String v) {
        this.ah = new int[] { 90, 43, 59, 33, 78, 25, 74, 86, 51, 15, 29, 91, 62, 71, 14, 56, 32, 87, 46, 85, 57, 31, 42, 79, 39, 11, 41, 17, 76, 68, 9, 70, 37, 48, 69, 4, 18, 45, 10, 21, 6, 92, 5, 8, 66, 34, 13, 36, 12, 28, 20, 3, 63, 47, 50, 26, 55, 64, 88, 24, 82, 60, 93, 83, 2, 40, 72, 61, 7, 19, 52, 27, 54, 94, 49, 16, 75, 1, 38, 23, 53, 35, 65, 73, 30, 67, 80, 22, 84, 81, 58, 77, 89, 0, 44 };
        this.v = v;
    }
    
    public final boolean p() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.v, "-");
        try {
            this.ad = stringTokenizer.nextToken();
            this.af = stringTokenizer.nextToken();
            this.ag = stringTokenizer.nextToken();
            this.ae = stringTokenizer.nextToken();
        }
        catch (Exception ex) {
            n("LicenseKey Tokens failed: " + this.v);
            return false;
        }
        this.z = this.l(this.ad);
        Integer n;
        try {
            n = new Integer(this.z);
        }
        catch (Exception ex2) {
            return false;
        }
        if ((n - 8) / 3 != this.m(String.valueOf(this.af) + "-" + this.ag + "-" + this.ae)) {
            return false;
        }
        this.ab = this.l(this.af);
        this.aa = this.l(this.ae);
        this.ac = this.l(this.ag);
        this.w = this.o(this.ac);
        return true;
    }
    
    public final String o(String s) {
        s = s.toLowerCase();
        if (s.startsWith("http://")) {
            s = s.substring(7);
        }
        final int index = s.indexOf("/");
        if (index >= 0) {
            s = s.substring(0, index);
        }
        return s;
    }
    
    private static void n(final String s) {
        System.out.println(s);
    }
    
    private final int m(final String s) {
        char c = '\0';
        for (int length = s.length(), i = 0; i < length; ++i) {
            c += s.charAt(i);
        }
        return c;
    }
    
    private final String l(final String s) {
        String string = "";
        for (int length = s.length(), i = 0; i < length; i += 2) {
            try {
                string = String.valueOf(string) + new Character((char)(this.ah[Integer.parseInt(String.valueOf(String.valueOf(s.charAt(i))) + String.valueOf(s.charAt(i + 1)))] + 32)).toString();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return string;
    }
}
