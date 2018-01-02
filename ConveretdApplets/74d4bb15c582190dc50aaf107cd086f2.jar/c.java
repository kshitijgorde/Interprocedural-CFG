import java.util.StringTokenizer;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    static String G = "\u9910\u9921\u9921\u993d\u9934\u9925\u9971\u9939\u9930\u9922\u9971\u9930\u9971\u9921\u9923\u993e\u9933\u993d\u9934\u993c\u997d\u9971\u9932\u993e\u993f\u9925\u9930\u9932\u9925\u9971\u9925\u9939\u9934\u9971\u9926\u9934\u9933\u993c\u9930\u9922\u9925\u9934\u9923";
    static String H = "\u991d\u993e\u9930\u9935\u9938\u993f\u9936\u997d\u9971\u9921\u993d\u9934\u9930\u9922\u9934\u9971\u9926\u9930\u9938\u9925\u997f\u997f\u997f";
    static String I = "\u9910\u993f\u9971\u9934\u9923\u9923\u993e\u9923\u9971\u9939\u9930\u9922\u9971\u993e\u9932\u9932\u9924\u9923\u9934\u9935";
    private Hashtable J;
    private static String Ma = "";
    private static String Na = "\u997e\u997e";
    private static String Oa = "\u996c";
    private static String Pa = "\u9923\u9934\u9930\u9935\u9938\u993f\u9936\u9971\u9937\u9923\u993e\u993c\u9971";
    private static String Ua = "\u9971\u9939\u9930\u9922\u9971\u993f\u993e\u9925\u9971\u9922\u9924\u9932\u9932\u9934\u9934\u9935\u9934\u9935";
    private static String Va = "\u9922\u9925\u9930\u9925\u9924\u9922\u9933\u990e\u993c\u9922\u9936";
    private static String Wa = "\u9910\u9921\u9921\u993d\u9934\u9925\u9971\u9939\u9930\u9922\u9971\u9930\u9971\u9921\u9923\u993e\u9933\u993d\u9934\u993c\u997d\u9971\u9932\u993e\u993f\u9925\u9930\u9932\u9925\u9971\u9925\u9939\u9934\u9971\u9926\u9934\u9933\u993c\u9930\u9922\u9925\u9934\u9923";
    private static String eb = "\u993d\u993e\u9930\u9935\u9938\u993f\u9936\u990e\u993c\u9922\u9936";
    private static String fb = "\u991d\u993e\u9930\u9935\u9938\u993f\u9936\u997d\u9971\u9921\u993d\u9934\u9930\u9922\u9934\u9971\u9926\u9930\u9938\u9925\u997f\u997f\u997f";
    private static String gb = "\u993d\u993e\u9930\u9935\u9934\u9923\u9923\u990e\u993c\u9922\u9936";
    private static String hb = "\u9910\u993f\u9971\u9934\u9923\u9923\u993e\u9923\u9971\u9939\u9930\u9922\u9971\u993e\u9932\u9932\u9924\u9923\u9934\u9935";
    private static String ib = "\u9975";
    
    public c(final URL url, final String s) {
        this.J = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(c.Ma) && !s2.startsWith(c.Na)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(c.Oa);
                    if (index == -1) {
                        continue;
                    }
                    this.J.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(c.Pa + s + c.Ua);
        }
        finally {
            d._(bufferedReader);
        }
    }
    
    public String _() {
        String wa = this.J.get(c.Va);
        if (wa == null) {
            wa = c.Wa;
        }
        return wa;
    }
    
    public String a() {
        String fb = this.J.get(c.eb);
        if (fb == null) {
            fb = c.fb;
        }
        return fb;
    }
    
    public String b() {
        String hb = this.J.get(c.gb);
        if (hb == null) {
            hb = c.hb;
        }
        return hb;
    }
    
    public String d(final String s) {
        String ma = this.J.get(s);
        if (ma == null) {
            ma = c.Ma;
        }
        return ma;
    }
    
    public synchronized void a(final String s, final String s2) {
        this.J.put(s, s2);
    }
    
    public String _(final String s, final Hashtable hashtable) {
        final String s2 = this.J.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(c.ib) && n++ < hashtable.size()) {
                    sb.append(hashtable.get(nextToken));
                }
                else {
                    sb.append(nextToken);
                }
                sb.append(' ');
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
        return c.Ma;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE9951);
        }
        return new String(array);
    }
    
    static {
        c.G = a(c.G);
        c.H = a(c.H);
        c.I = a(c.I);
        c.Ma = a(c.Ma);
        c.Na = a(c.Na);
        c.Oa = a(c.Oa);
        c.Pa = a(c.Pa);
        c.Ua = a(c.Ua);
        c.Va = a(c.Va);
        c.Wa = a(c.Wa);
        c.eb = a(c.eb);
        c.fb = a(c.fb);
        c.gb = a(c.gb);
        c.hb = a(c.hb);
        c.ib = a(c.ib);
    }
}
