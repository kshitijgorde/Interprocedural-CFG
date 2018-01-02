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

public class i
{
    static String TJc = "\u4041\u4070\u4070\u406c\u4065\u4074\u4020\u4068\u4061\u4073\u4020\u4061\u4020\u4070\u4072\u406f\u4062\u406c\u4065\u406d\u402c\u4020\u4063\u406f\u406e\u4074\u4061\u4063\u4074\u4020\u4074\u4068\u4065\u4020\u4077\u4065\u4062\u406d\u4061\u4073\u4074\u4065\u4072";
    static String UJc = "\u404c\u406f\u4061\u4064\u4069\u406e\u4067\u402c\u4020\u4070\u406c\u4065\u4061\u4073\u4065\u4020\u4077\u4061\u4069\u4074\u402e\u402e\u402e";
    static String VJc = "\u4041\u406e\u4020\u4065\u4072\u4072\u406f\u4072\u4020\u4068\u4061\u4073\u4020\u406f\u4063\u4063\u4075\u4072\u4065\u4064";
    private Hashtable WJc;
    private static String ta = "";
    private static String ua = "\u402f\u402f";
    private static String va = "\u403d";
    private static String wa = "\u4072\u4065\u4061\u4064\u4069\u406e\u4067\u4020\u4066\u4072\u406f\u406d\u4020";
    private static String xa = "\u4020\u4068\u4061\u4073\u4020\u406e\u406f\u4074\u4020\u4073\u4075\u4063\u4063\u4065\u4065\u4064\u4065\u4064";
    private static String ya = "\u4073\u4074\u4061\u4074\u4075\u4073\u4062\u405f\u406d\u4073\u4067";
    private static String za = "\u4041\u4070\u4070\u406c\u4065\u4074\u4020\u4068\u4061\u4073\u4020\u4061\u4020\u4070\u4072\u406f\u4062\u406c\u4065\u406d\u402c\u4020\u4063\u406f\u406e\u4074\u4061\u4063\u4074\u4020\u4074\u4068\u4065\u4020\u4077\u4065\u4062\u406d\u4061\u4073\u4074\u4065\u4072";
    private static String Aa = "\u406c\u406f\u4061\u4064\u4069\u406e\u4067\u405f\u406d\u4073\u4067";
    private static String Ba = "\u404c\u406f\u4061\u4064\u4069\u406e\u4067\u402c\u4020\u4070\u406c\u4065\u4061\u4073\u4065\u4020\u4077\u4061\u4069\u4074\u402e\u402e\u402e";
    private static String Ca = "\u406c\u406f\u4061\u4064\u4065\u4072\u4072\u405f\u406d\u4073\u4067";
    private static String Da = "\u4041\u406e\u4020\u4065\u4072\u4072\u406f\u4072\u4020\u4068\u4061\u4073\u4020\u406f\u4063\u4063\u4075\u4072\u4065\u4064";
    private static String Ea = "\u4024";
    
    public i(final URL url, final String s) {
        this.WJc = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(i.ta) && !s2.startsWith(i.ua)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(i.va);
                    if (index == -1) {
                        continue;
                    }
                    this.WJc.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(i.wa + s + i.xa);
        }
        finally {
            j._(bufferedReader);
        }
    }
    
    public String a() {
        String za = this.WJc.get(i.ya);
        if (za == null) {
            za = i.za;
        }
        return za;
    }
    
    public String b() {
        String ba = this.WJc.get(i.Aa);
        if (ba == null) {
            ba = i.Ba;
        }
        return ba;
    }
    
    public String _() {
        String da = this.WJc.get(i.Ca);
        if (da == null) {
            da = i.Da;
        }
        return da;
    }
    
    public String g(final String s) {
        String ta = this.WJc.get(s);
        if (ta == null) {
            ta = i.ta;
        }
        return ta;
    }
    
    public synchronized void _(final String s, final String s2) {
        this.WJc.put(s, s2);
    }
    
    public String a(final String s, final Hashtable hashtable) {
        final String s2 = this.WJc.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(i.Ea) && n++ < hashtable.size()) {
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
        return i.ta;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u4000');
        }
        return new String(array);
    }
    
    static {
        i.TJc = a(i.TJc);
        i.UJc = a(i.UJc);
        i.VJc = a(i.VJc);
        i.ta = a(i.ta);
        i.ua = a(i.ua);
        i.va = a(i.va);
        i.wa = a(i.wa);
        i.xa = a(i.xa);
        i.ya = a(i.ya);
        i.za = a(i.za);
        i.Aa = a(i.Aa);
        i.Ba = a(i.Ba);
        i.Ca = a(i.Ca);
        i.Da = a(i.Da);
        i.Ea = a(i.Ea);
    }
}
