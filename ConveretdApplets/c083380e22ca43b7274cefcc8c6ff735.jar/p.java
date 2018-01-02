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

public class p
{
    static String Ga = "\u7f7d\u7f4c\u7f4c\u7f50\u7f59\u7f48\u7f1c\u7f54\u7f5d\u7f4f\u7f1c\u7f5d\u7f1c\u7f4c\u7f4e\u7f53\u7f5e\u7f50\u7f59\u7f51\u7f10\u7f1c\u7f5f\u7f53\u7f52\u7f48\u7f5d\u7f5f\u7f48\u7f1c\u7f48\u7f54\u7f59\u7f1c\u7f4b\u7f59\u7f5e\u7f51\u7f5d\u7f4f\u7f48\u7f59\u7f4e";
    static String Ha = "\u7f70\u7f53\u7f5d\u7f58\u7f55\u7f52\u7f5b\u7f10\u7f1c\u7f4c\u7f50\u7f59\u7f5d\u7f4f\u7f59\u7f1c\u7f4b\u7f5d\u7f55\u7f48\u7f12\u7f12\u7f12";
    static String Ia = "\u7f7d\u7f52\u7f1c\u7f59\u7f4e\u7f4e\u7f53\u7f4e\u7f1c\u7f54\u7f5d\u7f4f\u7f1c\u7f53\u7f5f\u7f5f\u7f49\u7f4e\u7f59\u7f58";
    private Hashtable Ja;
    private static String t = "";
    private static String y = "\u7f13\u7f13";
    private static String z = "\u7f01";
    private static String A = "\u7f4e\u7f59\u7f5d\u7f58\u7f55\u7f52\u7f5b\u7f1c\u7f5a\u7f4e\u7f53\u7f51\u7f1c";
    private static String B = "\u7f1c\u7f54\u7f5d\u7f4f\u7f1c\u7f52\u7f53\u7f48\u7f1c\u7f4f\u7f49\u7f5f\u7f5f\u7f59\u7f59\u7f58\u7f59\u7f58";
    private static String C = "\u7f4f\u7f48\u7f5d\u7f48\u7f49\u7f4f\u7f5e\u7f63\u7f51\u7f4f\u7f5b";
    private static String D = "\u7f7d\u7f4c\u7f4c\u7f50\u7f59\u7f48\u7f1c\u7f54\u7f5d\u7f4f\u7f1c\u7f5d\u7f1c\u7f4c\u7f4e\u7f53\u7f5e\u7f50\u7f59\u7f51\u7f10\u7f1c\u7f5f\u7f53\u7f52\u7f48\u7f5d\u7f5f\u7f48\u7f1c\u7f48\u7f54\u7f59\u7f1c\u7f4b\u7f59\u7f5e\u7f51\u7f5d\u7f4f\u7f48\u7f59\u7f4e";
    private static String M = "\u7f50\u7f53\u7f5d\u7f58\u7f55\u7f52\u7f5b\u7f63\u7f51\u7f4f\u7f5b";
    private static String N = "\u7f70\u7f53\u7f5d\u7f58\u7f55\u7f52\u7f5b\u7f10\u7f1c\u7f4c\u7f50\u7f59\u7f5d\u7f4f\u7f59\u7f1c\u7f4b\u7f5d\u7f55\u7f48\u7f12\u7f12\u7f12";
    private static String O = "\u7f50\u7f53\u7f5d\u7f58\u7f59\u7f4e\u7f4e\u7f63\u7f51\u7f4f\u7f5b";
    private static String P = "\u7f7d\u7f52\u7f1c\u7f59\u7f4e\u7f4e\u7f53\u7f4e\u7f1c\u7f54\u7f5d\u7f4f\u7f1c\u7f53\u7f5f\u7f5f\u7f49\u7f4e\u7f59\u7f58";
    private static String Q = "\u7f18";
    
    public p(final URL url, final String s) {
        this.Ja = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(p.t) && !s2.startsWith(p.y)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(p.z);
                    if (index == -1) {
                        continue;
                    }
                    this.Ja.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(p.A + s + p.B);
        }
        finally {
            q.a(bufferedReader);
        }
    }
    
    public String b() {
        String d = this.Ja.get(p.C);
        if (d == null) {
            d = p.D;
        }
        return d;
    }
    
    public String _() {
        String n = this.Ja.get(p.M);
        if (n == null) {
            n = p.N;
        }
        return n;
    }
    
    public String a() {
        String p = this.Ja.get(p.O);
        if (p == null) {
            p = p.P;
        }
        return p;
    }
    
    public String d(final String s) {
        String t = this.Ja.get(s);
        if (t == null) {
            t = p.t;
        }
        return t;
    }
    
    public synchronized void b(final String s, final String s2) {
        this.Ja.put(s, s2);
    }
    
    public String b(final String s, final Hashtable hashtable) {
        final String s2 = this.Ja.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(p.Q) && n++ < hashtable.size()) {
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
        return p.t;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF7F3C);
        }
        return new String(array);
    }
    
    static {
        p.Ga = _(p.Ga);
        p.Ha = _(p.Ha);
        p.Ia = _(p.Ia);
        p.t = _(p.t);
        p.y = _(p.y);
        p.z = _(p.z);
        p.A = _(p.A);
        p.B = _(p.B);
        p.C = _(p.C);
        p.D = _(p.D);
        p.M = _(p.M);
        p.N = _(p.N);
        p.O = _(p.O);
        p.P = _(p.P);
        p.Q = _(p.Q);
    }
}
