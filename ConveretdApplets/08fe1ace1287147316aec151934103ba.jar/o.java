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

public class o
{
    static String Va = "\ubb84\ubbb5\ubbb5\ubba9\ubba0\ubbb1\ubbe5\ubbad\ubba4\ubbb6\ubbe5\ubba4\ubbe5\ubbb5\ubbb7\ubbaa\ubba7\ubba9\ubba0\ubba8\ubbe9\ubbe5\ubba6\ubbaa\ubbab\ubbb1\ubba4\ubba6\ubbb1\ubbe5\ubbb1\ubbad\ubba0\ubbe5\ubbb2\ubba0\ubba7\ubba8\ubba4\ubbb6\ubbb1\ubba0\ubbb7";
    static String Wa = "\ubb89\ubbaa\ubba4\ubba1\ubbac\ubbab\ubba2\ubbe9\ubbe5\ubbb5\ubba9\ubba0\ubba4\ubbb6\ubba0\ubbe5\ubbb2\ubba4\ubbac\ubbb1\ubbeb\ubbeb\ubbeb";
    static String Xa = "\ubb84\ubbab\ubbe5\ubba0\ubbb7\ubbb7\ubbaa\ubbb7\ubbe5\ubbad\ubba4\ubbb6\ubbe5\ubbaa\ubba6\ubba6\ubbb0\ubbb7\ubba0\ubba1";
    private Hashtable Ya;
    private static String w = "";
    private static String x = "\ubbea\ubbea";
    private static String y = "\ubbf8";
    private static String z = "\ubbb7\ubba0\ubba4\ubba1\ubbac\ubbab\ubba2\ubbe5\ubba3\ubbb7\ubbaa\ubba8\ubbe5";
    private static String A = "\ubbe5\ubbad\ubba4\ubbb6\ubbe5\ubbab\ubbaa\ubbb1\ubbe5\ubbb6\ubbb0\ubba6\ubba6\ubba0\ubba0\ubba1\ubba0\ubba1";
    private static String B = "\ubbb6\ubbb1\ubba4\ubbb1\ubbb0\ubbb6\ubba7\ubb9a\ubba8\ubbb6\ubba2";
    private static String C = "\ubb84\ubbb5\ubbb5\ubba9\ubba0\ubbb1\ubbe5\ubbad\ubba4\ubbb6\ubbe5\ubba4\ubbe5\ubbb5\ubbb7\ubbaa\ubba7\ubba9\ubba0\ubba8\ubbe9\ubbe5\ubba6\ubbaa\ubbab\ubbb1\ubba4\ubba6\ubbb1\ubbe5\ubbb1\ubbad\ubba0\ubbe5\ubbb2\ubba0\ubba7\ubba8\ubba4\ubbb6\ubbb1\ubba0\ubbb7";
    private static String T = "\ubba9\ubbaa\ubba4\ubba1\ubbac\ubbab\ubba2\ubb9a\ubba8\ubbb6\ubba2";
    private static String U = "\ubb89\ubbaa\ubba4\ubba1\ubbac\ubbab\ubba2\ubbe9\ubbe5\ubbb5\ubba9\ubba0\ubba4\ubbb6\ubba0\ubbe5\ubbb2\ubba4\ubbac\ubbb1\ubbeb\ubbeb\ubbeb";
    private static String V = "\ubba9\ubbaa\ubba4\ubba1\ubba0\ubbb7\ubbb7\ubb9a\ubba8\ubbb6\ubba2";
    private static String W = "\ubb84\ubbab\ubbe5\ubba0\ubbb7\ubbb7\ubbaa\ubbb7\ubbe5\ubbad\ubba4\ubbb6\ubbe5\ubbaa\ubba6\ubba6\ubbb0\ubbb7\ubba0\ubba1";
    private static String X = "\ubbe1";
    
    public o(final URL url, final String s) {
        this.Ya = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(o.w) && !s2.startsWith(o.x)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(o.y);
                    if (index == -1) {
                        continue;
                    }
                    this.Ya.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(o.z + s + o.A);
        }
        finally {
            p.a(bufferedReader);
        }
    }
    
    public String a() {
        String c = this.Ya.get(o.B);
        if (c == null) {
            c = o.C;
        }
        return c;
    }
    
    public String b() {
        String u = this.Ya.get(o.T);
        if (u == null) {
            u = o.U;
        }
        return u;
    }
    
    public String _() {
        String w = this.Ya.get(o.V);
        if (w == null) {
            w = o.W;
        }
        return w;
    }
    
    public String m(final String s) {
        String w = this.Ya.get(s);
        if (w == null) {
            w = o.w;
        }
        return w;
    }
    
    public synchronized void b(final String s, final String s2) {
        this.Ya.put(s, s2);
    }
    
    public String a(final String s, final Hashtable hashtable) {
        final String s2 = this.Ya.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(o.X) && n++ < hashtable.size()) {
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
        return o.w;
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEBBC5);
        }
        return new String(array);
    }
    
    static {
        o.Va = b(o.Va);
        o.Wa = b(o.Wa);
        o.Xa = b(o.Xa);
        o.w = b(o.w);
        o.x = b(o.x);
        o.y = b(o.y);
        o.z = b(o.z);
        o.A = b(o.A);
        o.B = b(o.B);
        o.C = b(o.C);
        o.T = b(o.T);
        o.U = b(o.U);
        o.V = b(o.V);
        o.W = b(o.W);
        o.X = b(o.X);
    }
}
