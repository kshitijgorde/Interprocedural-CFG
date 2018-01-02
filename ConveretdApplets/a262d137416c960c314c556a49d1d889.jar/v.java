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
    static String k2 = "\u93b4\u9385\u9385\u9399\u9390\u9381\u93d5\u939d\u9394\u9386\u93d5\u9394\u93d5\u9385\u9387\u939a\u9397\u9399\u9390\u9398\u93d9\u93d5\u9396\u939a\u939b\u9381\u9394\u9396\u9381\u93d5\u9381\u939d\u9390\u93d5\u9382\u9390\u9397\u9398\u9394\u9386\u9381\u9390\u9387";
    static String l2 = "\u93b9\u939a\u9394\u9391\u939c\u939b\u9392\u93d9\u93d5\u9385\u9399\u9390\u9394\u9386\u9390\u93d5\u9382\u9394\u939c\u9381\u93db\u93db\u93db";
    static String m2 = "\u93b4\u939b\u93d5\u9390\u9387\u9387\u939a\u9387\u93d5\u939d\u9394\u9386\u93d5\u939a\u9396\u9396\u9380\u9387\u9390\u9391";
    private Hashtable n2;
    private static String z = "";
    private static String A = "\u93da\u93da";
    private static String B = "\u93c8";
    private static String C = "\u9387\u9390\u9394\u9391\u939c\u939b\u9392\u93d5\u9393\u9387\u939a\u9398\u93d5";
    private static String D = "\u93d5\u939d\u9394\u9386\u93d5\u939b\u939a\u9381\u93d5\u9386\u9380\u9396\u9396\u9390\u9390\u9391\u9390\u9391";
    private static String E = "\u9386\u9381\u9394\u9381\u9380\u9386\u9397\u93aa\u9398\u9386\u9392";
    private static String F = "\u93b4\u9385\u9385\u9399\u9390\u9381\u93d5\u939d\u9394\u9386\u93d5\u9394\u93d5\u9385\u9387\u939a\u9397\u9399\u9390\u9398\u93d9\u93d5\u9396\u939a\u939b\u9381\u9394\u9396\u9381\u93d5\u9381\u939d\u9390\u93d5\u9382\u9390\u9397\u9398\u9394\u9386\u9381\u9390\u9387";
    private static String G = "\u9399\u939a\u9394\u9391\u939c\u939b\u9392\u93aa\u9398\u9386\u9392";
    private static String H = "\u93b9\u939a\u9394\u9391\u939c\u939b\u9392\u93d9\u93d5\u9385\u9399\u9390\u9394\u9386\u9390\u93d5\u9382\u9394\u939c\u9381\u93db\u93db\u93db";
    private static String I = "\u9399\u939a\u9394\u9391\u9390\u9387\u9387\u93aa\u9398\u9386\u9392";
    private static String J = "\u93b4\u939b\u93d5\u9390\u9387\u9387\u939a\u9387\u93d5\u939d\u9394\u9386\u93d5\u939a\u9396\u9396\u9380\u9387\u9390\u9391";
    private static String K = "\u93d1";
    
    public v(final URL url, final String s) {
        this.n2 = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(v.z) && !s2.startsWith(v.A)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(v.B);
                    if (index == -1) {
                        continue;
                    }
                    this.n2.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(v.C + s + v.D);
        }
        finally {
            abstract.b(bufferedReader);
        }
    }
    
    public String b() {
        String f = this.n2.get(v.E);
        if (f == null) {
            f = v.F;
        }
        return f;
    }
    
    public String _() {
        String h = this.n2.get(v.G);
        if (h == null) {
            h = v.H;
        }
        return h;
    }
    
    public String a() {
        String j = this.n2.get(v.I);
        if (j == null) {
            j = v.J;
        }
        return j;
    }
    
    public String f(final String s) {
        String z = this.n2.get(s);
        if (z == null) {
            z = v.z;
        }
        return z;
    }
    
    public synchronized void b(final String s, final String s2) {
        this.n2.put(s, s2);
    }
    
    public String b(final String s, final Hashtable hashtable) {
        final String s2 = this.n2.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(v.K) && n++ < hashtable.size()) {
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
        return v.z;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE93F5);
        }
        return new String(array);
    }
    
    static {
        v.k2 = _(v.k2);
        v.l2 = _(v.l2);
        v.m2 = _(v.m2);
        v.z = _(v.z);
        v.A = _(v.A);
        v.B = _(v.B);
        v.C = _(v.C);
        v.D = _(v.D);
        v.E = _(v.E);
        v.F = _(v.F);
        v.G = _(v.G);
        v.H = _(v.H);
        v.I = _(v.I);
        v.J = _(v.J);
        v.K = _(v.K);
    }
}
