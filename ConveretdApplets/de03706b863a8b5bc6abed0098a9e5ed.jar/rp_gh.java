import java.util.HashMap;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_gh implements Cloneable
{
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long a;
    private String f;
    private String g;
    private boolean a;
    private int a;
    private long b;
    private static Map a;
    
    private rp_gh(String trim, final String b) {
        this.a = -1L;
        this.a = 1;
        this.b = 0L;
        final String s;
        if ((trim = trim.trim()).length() == 0 || !a(trim) || ((s = trim).equalsIgnoreCase("Comment") || s.equalsIgnoreCase("CommentURL") || s.equalsIgnoreCase("Discard") || s.equalsIgnoreCase("Domain") || s.equalsIgnoreCase("Expires") || s.equalsIgnoreCase("Max-Age") || s.equalsIgnoreCase("Path") || s.equalsIgnoreCase("Port") || s.equalsIgnoreCase("Secure") || s.equalsIgnoreCase("Version") || s.charAt(0) == '$')) {
            throw new IllegalArgumentException("Illegal cookie name");
        }
        this.a = trim;
        this.b = b;
        this.a = false;
        this.b = System.currentTimeMillis();
    }
    
    public static List a(String s) {
        final String s2 = s;
        boolean b = false;
        final String lowerCase;
        if ((lowerCase = s2.toLowerCase()).indexOf("expires=") != -1) {
            b = false;
        }
        else if (lowerCase.indexOf("version=") != -1) {
            b = true;
        }
        else if (lowerCase.indexOf("max-age") != -1) {
            b = true;
        }
        else if (c(lowerCase, "set-cookie2:")) {
            b = true;
        }
        final boolean b2 = b;
        if (c(s, "set-cookie2:")) {
            s = s.substring("set-cookie2:".length());
        }
        else if (c(s, "set-cookie:")) {
            s = s.substring("set-cookie:".length());
        }
        final ArrayList<rp_gh> list = new ArrayList<rp_gh>();
        if (!b2) {
            final rp_gh a;
            (a = a(s)).a(0);
            list.add(a);
        }
        else {
            final Iterator<String> iterator = (Iterator<String>)b(s).iterator();
            while (iterator.hasNext()) {
                final rp_gh a2;
                (a2 = a((String)iterator.next())).a(1);
                list.add(a2);
            }
        }
        return list;
    }
    
    public final boolean a() {
        return this.a == 0L || (this.a != -1L && (System.currentTimeMillis() - this.b) / 1000L > this.a);
    }
    
    public final void a(final String c) {
        this.c = c;
    }
    
    public final String a() {
        return this.c;
    }
    
    public final void b(final String d) {
        this.d = d;
    }
    
    public final String b() {
        return this.d;
    }
    
    public final void c(final String g) {
        this.g = g;
    }
    
    public final String c() {
        return this.g;
    }
    
    public final void d(final String e) {
        if (e != null) {
            this.e = e.toLowerCase();
            return;
        }
        this.e = e;
    }
    
    public final String d() {
        return this.e;
    }
    
    public final void a(final long a) {
        this.a = a;
    }
    
    public final long a() {
        return this.a;
    }
    
    public final void e(final String f) {
        this.f = f;
    }
    
    public final String e() {
        return this.f;
    }
    
    public final void a(final boolean b) {
        this.a = true;
    }
    
    public final boolean b() {
        return this.a;
    }
    
    public final String f() {
        return this.a;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final void a(final int a) {
        if (a != 0 && a != 1) {
            throw new IllegalArgumentException("cookie version should be 0 or 1");
        }
        this.a = a;
    }
    
    public static boolean a(final String s, String substring) {
        if (s == null || substring == null) {
            return false;
        }
        final boolean equalsIgnoreCase = ".local".equalsIgnoreCase(s);
        int n;
        if ((n = s.indexOf(46)) == 0) {
            n = s.indexOf(46, 1);
        }
        if (!equalsIgnoreCase && (n == -1 || n == s.length() - 1)) {
            return false;
        }
        if (substring.indexOf(46) == -1 && equalsIgnoreCase) {
            return true;
        }
        final int n2;
        if ((n2 = substring.length() - s.length()) == 0) {
            return substring.equalsIgnoreCase(s);
        }
        if (n2 > 0) {
            final String substring2 = substring.substring(0, n2);
            substring = substring.substring(n2);
            return substring2.indexOf(46) == -1 && substring.equalsIgnoreCase(s);
        }
        return n2 == -1 && (s.charAt(0) == '.' && substring.equalsIgnoreCase(s.substring(1)));
    }
    
    public final String toString() {
        if (this.a > 0) {
            this = this;
            final StringBuilder sb;
            (sb = new StringBuilder()).append(this.a).append("=\"").append(this.b).append('\"');
            if (this.f != null) {
                sb.append(";$Path=\"").append(this.f).append('\"');
            }
            if (this.e != null) {
                sb.append(";$Domain=\"").append(this.e).append('\"');
            }
            if (this.g != null) {
                sb.append(";$Port=\"").append(this.g).append('\"');
            }
            return sb.toString();
        }
        this = this;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a + "=" + this.b);
        return sb2.toString();
    }
    
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof rp_gh)) {
            return false;
        }
        final rp_gh rp_gh = (rp_gh)o;
        if (b(this.a, rp_gh.a) && b(this.e, rp_gh.e)) {
            final String f = this.f;
            final String f2 = rp_gh.f;
            final String s = f;
            if (f == f2 || (s != null && f2 != null && s.equals(f2))) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return this.a.toLowerCase().hashCode() + ((this.e != null) ? this.e.toLowerCase().hashCode() : 0) + ((this.f != null) ? this.f.hashCode() : 0);
    }
    
    public final Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private static boolean a(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1;
            if ((char1 = s.charAt(i)) < ' ' || char1 >= '\u007f' || ",;".indexOf(char1) != -1) {
                return false;
            }
        }
        return true;
    }
    
    private static rp_gh a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        rp_gh rp_gh;
        try {
            final String nextToken;
            final int index;
            if ((index = (nextToken = stringTokenizer.nextToken()).indexOf(61)) == -1) {
                throw new IllegalArgumentException("Invalid cookie name-value pair");
            }
            rp_gh = new rp_gh(nextToken.substring(0, index).trim(), a(nextToken.substring(index + 1).trim()));
        }
        catch (NoSuchElementException ex) {
            throw new IllegalArgumentException("Empty cookie header string");
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken2;
            final int index2;
            String s2;
            String trim;
            if ((index2 = (nextToken2 = stringTokenizer.nextToken()).indexOf(61)) != -1) {
                s2 = nextToken2.substring(0, index2).trim();
                trim = nextToken2.substring(index2 + 1).trim();
            }
            else {
                s2 = nextToken2.trim();
                trim = null;
            }
            final rp_gh rp_gh2 = rp_gh;
            final String s3 = s2;
            final String s4 = trim;
            final String s5 = s3;
            final rp_gh rp_gh3 = rp_gh2;
            final String a = a(s4);
            final rp_dA rp_dA;
            if ((rp_dA = rp_gh.a.get(s5.toLowerCase())) == null) {
                throw new IllegalArgumentException("Illegal cookie attribute");
            }
            rp_dA.a(rp_gh3, a);
        }
        return rp_gh;
    }
    
    private long a(final String s) {
        final SimpleDateFormat simpleDateFormat;
        (simpleDateFormat = new SimpleDateFormat("EEE',' dd-MMM-yyyy HH:mm:ss 'GMT'")).setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return (simpleDateFormat.parse(s).getTime() - this.b) / 1000L;
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    private static String a(final String s) {
        if (s != null && s.length() > 0 && s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
    
    private static boolean b(final String s, final String s2) {
        return s == s2 || (s != null && s2 != null && s.equalsIgnoreCase(s2));
    }
    
    private static boolean c(final String s, final String s2) {
        return s != null && s2 != null && (s.length() >= s2.length() && s2.equalsIgnoreCase(s.substring(0, s2.length())));
    }
    
    private static List b(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        int n = 0;
        int i = 0;
        int n2 = 0;
        while (i < s.length()) {
            final char char1;
            if ((char1 = s.charAt(i)) == '\"') {
                ++n;
            }
            if (char1 == ',' && n % 2 == 0) {
                list.add(s.substring(n2, i));
                n2 = i + 1;
            }
            ++i;
        }
        list.add(s.substring(n2));
        return list;
    }
    
    static {
        rp_gh.a = null;
        (rp_gh.a = new HashMap()).put("comment", new rp_go());
        rp_gh.a.put("commenturl", new rp_gq());
        rp_gh.a.put("discard", new rp_gr());
        rp_gh.a.put("domain", new rp_gs());
        rp_gh.a.put("max-age", new rp_gt());
        rp_gh.a.put("path", new rp_gw());
        rp_gh.a.put("port", new rp_gv());
        rp_gh.a.put("secure", new rp_gy());
        rp_gh.a.put("version", new rp_gx());
        rp_gh.a.put("expires", new rp_fL());
    }
}
