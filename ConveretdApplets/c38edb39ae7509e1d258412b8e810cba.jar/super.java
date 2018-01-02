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

public class super
{
    static String s = "\u63c3\u63f2\u63f2\u63ee\u63e7\u63f6\u63a2\u63ea\u63e3\u63f1\u63a2\u63e3\u63a2\u63f2\u63f0\u63ed\u63e0\u63ee\u63e7\u63ef\u63ae\u63a2\u63e1\u63ed\u63ec\u63f6\u63e3\u63e1\u63f6\u63a2\u63f6\u63ea\u63e7\u63a2\u63f5\u63e7\u63e0\u63ef\u63e3\u63f1\u63f6\u63e7\u63f0";
    static String t = "\u63ce\u63ed\u63e3\u63e6\u63eb\u63ec\u63e5\u63ae\u63a2\u63f2\u63ee\u63e7\u63e3\u63f1\u63e7\u63a2\u63f5\u63e3\u63eb\u63f6\u63ac\u63ac\u63ac";
    static String u = "\u63c3\u63ec\u63a2\u63e7\u63f0\u63f0\u63ed\u63f0\u63a2\u63ea\u63e3\u63f1\u63a2\u63ed\u63e1\u63e1\u63f7\u63f0\u63e7\u63e6";
    private Hashtable v;
    private static String ya = "";
    private static String za = "\u63ad\u63ad";
    private static String Aa = "\u63bf";
    private static String Ba = "\u63f0\u63e7\u63e3\u63e6\u63eb\u63ec\u63e5\u63a2\u63e4\u63f0\u63ed\u63ef\u63a2";
    private static String Ca = "\u63a2\u63ea\u63e3\u63f1\u63a2\u63ec\u63ed\u63f6\u63a2\u63f1\u63f7\u63e1\u63e1\u63e7\u63e7\u63e6\u63e7\u63e6";
    private static String Da = "\u63f1\u63f6\u63e3\u63f6\u63f7\u63f1\u63e0\u63dd\u63ef\u63f1\u63e5";
    private static String Ea = "\u63c3\u63f2\u63f2\u63ee\u63e7\u63f6\u63a2\u63ea\u63e3\u63f1\u63a2\u63e3\u63a2\u63f2\u63f0\u63ed\u63e0\u63ee\u63e7\u63ef\u63ae\u63a2\u63e1\u63ed\u63ec\u63f6\u63e3\u63e1\u63f6\u63a2\u63f6\u63ea\u63e7\u63a2\u63f5\u63e7\u63e0\u63ef\u63e3\u63f1\u63f6\u63e7\u63f0";
    private static String Ta = "\u63ee\u63ed\u63e3\u63e6\u63eb\u63ec\u63e5\u63dd\u63ef\u63f1\u63e5";
    private static String Ua = "\u63ce\u63ed\u63e3\u63e6\u63eb\u63ec\u63e5\u63ae\u63a2\u63f2\u63ee\u63e7\u63e3\u63f1\u63e7\u63a2\u63f5\u63e3\u63eb\u63f6\u63ac\u63ac\u63ac";
    private static String Va = "\u63ee\u63ed\u63e3\u63e6\u63e7\u63f0\u63f0\u63dd\u63ef\u63f1\u63e5";
    private static String Wa = "\u63c3\u63ec\u63a2\u63e7\u63f0\u63f0\u63ed\u63f0\u63a2\u63ea\u63e3\u63f1\u63a2\u63ed\u63e1\u63e1\u63f7\u63f0\u63e7\u63e6";
    private static String Xa = "\u63a6";
    
    public super(final URL url, final String s) {
        this.v = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(super.ya) && !s2.startsWith(super.za)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(super.Aa);
                    if (index == -1) {
                        continue;
                    }
                    this.v.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(super.Ba + s + super.Ca);
        }
        finally {
            switch.a(bufferedReader);
        }
    }
    
    public String _() {
        String ea = this.v.get(super.Da);
        if (ea == null) {
            ea = super.Ea;
        }
        return ea;
    }
    
    public String a() {
        String ua = this.v.get(super.Ta);
        if (ua == null) {
            ua = super.Ua;
        }
        return ua;
    }
    
    public String i() {
        String wa = this.v.get(super.Va);
        if (wa == null) {
            wa = super.Wa;
        }
        return wa;
    }
    
    public String m(final String s) {
        String ya = this.v.get(s);
        if (ya == null) {
            ya = super.ya;
        }
        return ya;
    }
    
    public synchronized void a(final String s, final String s2) {
        this.v.put(s, s2);
    }
    
    public String _(final String s, final Hashtable hashtable) {
        final String s2 = this.v.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(super.Xa) && n++ < hashtable.size()) {
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
        return super.ya;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6382');
        }
        return new String(array);
    }
    
    static {
        super.s = _(super.s);
        super.t = _(super.t);
        super.u = _(super.u);
        super.ya = _(super.ya);
        super.za = _(super.za);
        super.Aa = _(super.Aa);
        super.Ba = _(super.Ba);
        super.Ca = _(super.Ca);
        super.Da = _(super.Da);
        super.Ea = _(super.Ea);
        super.Ta = _(super.Ta);
        super.Ua = _(super.Ua);
        super.Va = _(super.Va);
        super.Wa = _(super.Wa);
        super.Xa = _(super.Xa);
    }
}
