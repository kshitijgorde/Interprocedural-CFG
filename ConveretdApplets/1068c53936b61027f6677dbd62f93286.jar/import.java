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

public class import
{
    static String K = "\ub8ce\ub8ff\ub8ff\ub8e3\ub8ea\ub8fb\ub8af\ub8e7\ub8ee\ub8fc\ub8af\ub8ee\ub8af\ub8ff\ub8fd\ub8e0\ub8ed\ub8e3\ub8ea\ub8e2\ub8a3\ub8af\ub8ec\ub8e0\ub8e1\ub8fb\ub8ee\ub8ec\ub8fb\ub8af\ub8fb\ub8e7\ub8ea\ub8af\ub8f8\ub8ea\ub8ed\ub8e2\ub8ee\ub8fc\ub8fb\ub8ea\ub8fd";
    static String L = "\ub8c3\ub8e0\ub8ee\ub8eb\ub8e6\ub8e1\ub8e8\ub8a3\ub8af\ub8ff\ub8e3\ub8ea\ub8ee\ub8fc\ub8ea\ub8af\ub8f8\ub8ee\ub8e6\ub8fb\ub8a1\ub8a1\ub8a1";
    static String M = "\ub8ce\ub8e1\ub8af\ub8ea\ub8fd\ub8fd\ub8e0\ub8fd\ub8af\ub8e7\ub8ee\ub8fc\ub8af\ub8e0\ub8ec\ub8ec\ub8fa\ub8fd\ub8ea\ub8eb";
    private Hashtable N;
    private static String Ua = "";
    private static String Va = "\ub8a0\ub8a0";
    private static String Wa = "\ub8b2";
    private static String Xa = "\ub8fd\ub8ea\ub8ee\ub8eb\ub8e6\ub8e1\ub8e8\ub8af\ub8e9\ub8fd\ub8e0\ub8e2\ub8af";
    private static String Ya = "\ub8af\ub8e7\ub8ee\ub8fc\ub8af\ub8e1\ub8e0\ub8fb\ub8af\ub8fc\ub8fa\ub8ec\ub8ec\ub8ea\ub8ea\ub8eb\ub8ea\ub8eb";
    private static String Za = "\ub8fc\ub8fb\ub8ee\ub8fb\ub8fa\ub8fc\ub8ed\ub8d0\ub8e2\ub8fc\ub8e8";
    private static String _b = "\ub8ce\ub8ff\ub8ff\ub8e3\ub8ea\ub8fb\ub8af\ub8e7\ub8ee\ub8fc\ub8af\ub8ee\ub8af\ub8ff\ub8fd\ub8e0\ub8ed\ub8e3\ub8ea\ub8e2\ub8a3\ub8af\ub8ec\ub8e0\ub8e1\ub8fb\ub8ee\ub8ec\ub8fb\ub8af\ub8fb\ub8e7\ub8ea\ub8af\ub8f8\ub8ea\ub8ed\ub8e2\ub8ee\ub8fc\ub8fb\ub8ea\ub8fd";
    private static String ib = "\ub8e3\ub8e0\ub8ee\ub8eb\ub8e6\ub8e1\ub8e8\ub8d0\ub8e2\ub8fc\ub8e8";
    private static String jb = "\ub8c3\ub8e0\ub8ee\ub8eb\ub8e6\ub8e1\ub8e8\ub8a3\ub8af\ub8ff\ub8e3\ub8ea\ub8ee\ub8fc\ub8ea\ub8af\ub8f8\ub8ee\ub8e6\ub8fb\ub8a1\ub8a1\ub8a1";
    private static String kb = "\ub8e3\ub8e0\ub8ee\ub8eb\ub8ea\ub8fd\ub8fd\ub8d0\ub8e2\ub8fc\ub8e8";
    private static String lb = "\ub8ce\ub8e1\ub8af\ub8ea\ub8fd\ub8fd\ub8e0\ub8fd\ub8af\ub8e7\ub8ee\ub8fc\ub8af\ub8e0\ub8ec\ub8ec\ub8fa\ub8fd\ub8ea\ub8eb";
    private static String mb = "\ub8ab";
    
    public import(final URL url, final String s) {
        this.N = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(import.Ua) && !s2.startsWith(import.Va)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(import.Wa);
                    if (index == -1) {
                        continue;
                    }
                    this.N.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(import.Xa + s + import.Ya);
        }
        finally {
            instanceof._(bufferedReader);
        }
    }
    
    public String b() {
        String b = this.N.get(import.Za);
        if (b == null) {
            b = import._b;
        }
        return b;
    }
    
    public String _() {
        String jb = this.N.get(import.ib);
        if (jb == null) {
            jb = import.jb;
        }
        return jb;
    }
    
    public String a() {
        String lb = this.N.get(import.kb);
        if (lb == null) {
            lb = import.lb;
        }
        return lb;
    }
    
    public String n(final String s) {
        String ua = this.N.get(s);
        if (ua == null) {
            ua = import.Ua;
        }
        return ua;
    }
    
    public synchronized void _(final String s, final String s2) {
        this.N.put(s, s2);
    }
    
    public String a(final String s, final Hashtable hashtable) {
        final String s2 = this.N.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(import.mb) && n++ < hashtable.size()) {
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
        return import.Ua;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ub88f');
        }
        return new String(array);
    }
    
    static {
        import.K = a(import.K);
        import.L = a(import.L);
        import.M = a(import.M);
        import.Ua = a(import.Ua);
        import.Va = a(import.Va);
        import.Wa = a(import.Wa);
        import.Xa = a(import.Xa);
        import.Ya = a(import.Ya);
        import.Za = a(import.Za);
        import._b = a(import._b);
        import.ib = a(import.ib);
        import.jb = a(import.jb);
        import.kb = a(import.kb);
        import.lb = a(import.lb);
        import.mb = a(import.mb);
    }
}
