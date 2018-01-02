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

public class v
{
    static String Za = "\u3f72\u3f43\u3f43\u3f5f\u3f56\u3f47\u3f13\u3f5b\u3f52\u3f40\u3f13\u3f52\u3f13\u3f43\u3f41\u3f5c\u3f51\u3f5f\u3f56\u3f5e\u3f1f\u3f13\u3f50\u3f5c\u3f5d\u3f47\u3f52\u3f50\u3f47\u3f13\u3f47\u3f5b\u3f56\u3f13\u3f44\u3f56\u3f51\u3f5e\u3f52\u3f40\u3f47\u3f56\u3f41";
    static String _b = "\u3f7f\u3f5c\u3f52\u3f57\u3f5a\u3f5d\u3f54\u3f1f\u3f13\u3f43\u3f5f\u3f56\u3f52\u3f40\u3f56\u3f13\u3f44\u3f52\u3f5a\u3f47\u3f1d\u3f1d\u3f1d";
    static String ab = "\u3f72\u3f5d\u3f13\u3f56\u3f41\u3f41\u3f5c\u3f41\u3f13\u3f5b\u3f52\u3f40\u3f13\u3f5c\u3f50\u3f50\u3f46\u3f41\u3f56\u3f57";
    private Hashtable bb;
    private static String Q = "";
    private static String R = "\u3f1c\u3f1c";
    private static String S = "\u3f0e";
    private static String T = "\u3f41\u3f56\u3f52\u3f57\u3f5a\u3f5d\u3f54\u3f13\u3f55\u3f41\u3f5c\u3f5e\u3f13";
    private static String U = "\u3f13\u3f5b\u3f52\u3f40\u3f13\u3f5d\u3f5c\u3f47\u3f13\u3f40\u3f46\u3f50\u3f50\u3f56\u3f56\u3f57\u3f56\u3f57";
    private static String V = "\u3f40\u3f47\u3f52\u3f47\u3f46\u3f40\u3f51\u3f6c\u3f5e\u3f40\u3f54";
    private static String W = "\u3f72\u3f43\u3f43\u3f5f\u3f56\u3f47\u3f13\u3f5b\u3f52\u3f40\u3f13\u3f52\u3f13\u3f43\u3f41\u3f5c\u3f51\u3f5f\u3f56\u3f5e\u3f1f\u3f13\u3f50\u3f5c\u3f5d\u3f47\u3f52\u3f50\u3f47\u3f13\u3f47\u3f5b\u3f56\u3f13\u3f44\u3f56\u3f51\u3f5e\u3f52\u3f40\u3f47\u3f56\u3f41";
    private static String ea = "\u3f5f\u3f5c\u3f52\u3f57\u3f5a\u3f5d\u3f54\u3f6c\u3f5e\u3f40\u3f54";
    private static String fa = "\u3f7f\u3f5c\u3f52\u3f57\u3f5a\u3f5d\u3f54\u3f1f\u3f13\u3f43\u3f5f\u3f56\u3f52\u3f40\u3f56\u3f13\u3f44\u3f52\u3f5a\u3f47\u3f1d\u3f1d\u3f1d";
    private static String ga = "\u3f5f\u3f5c\u3f52\u3f57\u3f56\u3f41\u3f41\u3f6c\u3f5e\u3f40\u3f54";
    private static String ha = "\u3f72\u3f5d\u3f13\u3f56\u3f41\u3f41\u3f5c\u3f41\u3f13\u3f5b\u3f52\u3f40\u3f13\u3f5c\u3f50\u3f50\u3f46\u3f41\u3f56\u3f57";
    private static String ia = "\u3f17";
    
    public v(final URL url, final String s) {
        this.bb = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(v.Q) && !s2.startsWith(v.R)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(v.S);
                    if (index == -1) {
                        continue;
                    }
                    this.bb.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(v.T + s + v.U);
        }
        finally {
            abstract._(bufferedReader);
        }
    }
    
    public String _() {
        String w = this.bb.get(v.V);
        if (w == null) {
            w = v.W;
        }
        return w;
    }
    
    public String a() {
        String fa = this.bb.get(v.ea);
        if (fa == null) {
            fa = v.fa;
        }
        return fa;
    }
    
    public String b() {
        String ha = this.bb.get(v.ga);
        if (ha == null) {
            ha = v.ha;
        }
        return ha;
    }
    
    public String k(final String s) {
        String q = this.bb.get(s);
        if (q == null) {
            q = v.Q;
        }
        return q;
    }
    
    public synchronized void a(final String s, final String s2) {
        this.bb.put(s, s2);
    }
    
    public String _(final String s, final Hashtable hashtable) {
        final String s2 = this.bb.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(v.ia) && n++ < hashtable.size()) {
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
        return v.Q;
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x13F33);
        }
        return new String(array);
    }
    
    static {
        v.Za = b(v.Za);
        v._b = b(v._b);
        v.ab = b(v.ab);
        v.Q = b(v.Q);
        v.R = b(v.R);
        v.S = b(v.S);
        v.T = b(v.T);
        v.U = b(v.U);
        v.V = b(v.V);
        v.W = b(v.W);
        v.ea = b(v.ea);
        v.fa = b(v.fa);
        v.ga = b(v.ga);
        v.ha = b(v.ha);
        v.ia = b(v.ia);
    }
}
