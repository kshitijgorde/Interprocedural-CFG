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

public class synchronized
{
    static String Aoa = "\u5d6c\u5d5d\u5d5d\u5d41\u5d48\u5d59\u5d0d\u5d45\u5d4c\u5d5e\u5d0d\u5d4c\u5d0d\u5d5d\u5d5f\u5d42\u5d4f\u5d41\u5d48\u5d40\u5d01\u5d0d\u5d4e\u5d42\u5d43\u5d59\u5d4c\u5d4e\u5d59\u5d0d\u5d59\u5d45\u5d48\u5d0d\u5d5a\u5d48\u5d4f\u5d40\u5d4c\u5d5e\u5d59\u5d48\u5d5f";
    static String Boa = "\u5d61\u5d42\u5d4c\u5d49\u5d44\u5d43\u5d4a\u5d01\u5d0d\u5d5d\u5d41\u5d48\u5d4c\u5d5e\u5d48\u5d0d\u5d5a\u5d4c\u5d44\u5d59\u5d03\u5d03\u5d03";
    static String Coa = "\u5d6c\u5d43\u5d0d\u5d48\u5d5f\u5d5f\u5d42\u5d5f\u5d0d\u5d45\u5d4c\u5d5e\u5d0d\u5d42\u5d4e\u5d4e\u5d58\u5d5f\u5d48\u5d49";
    private Hashtable Doa;
    private static String o = "";
    private static String p = "\u5d02\u5d02";
    private static String q = "\u5d10";
    private static String C = "\u5d5f\u5d48\u5d4c\u5d49\u5d44\u5d43\u5d4a\u5d0d\u5d4b\u5d5f\u5d42\u5d40\u5d0d";
    private static String D = "\u5d0d\u5d45\u5d4c\u5d5e\u5d0d\u5d43\u5d42\u5d59\u5d0d\u5d5e\u5d58\u5d4e\u5d4e\u5d48\u5d48\u5d49\u5d48\u5d49";
    private static String E = "\u5d5e\u5d59\u5d4c\u5d59\u5d58\u5d5e\u5d4f\u5d72\u5d40\u5d5e\u5d4a";
    private static String F = "\u5d6c\u5d5d\u5d5d\u5d41\u5d48\u5d59\u5d0d\u5d45\u5d4c\u5d5e\u5d0d\u5d4c\u5d0d\u5d5d\u5d5f\u5d42\u5d4f\u5d41\u5d48\u5d40\u5d01\u5d0d\u5d4e\u5d42\u5d43\u5d59\u5d4c\u5d4e\u5d59\u5d0d\u5d59\u5d45\u5d48\u5d0d\u5d5a\u5d48\u5d4f\u5d40\u5d4c\u5d5e\u5d59\u5d48\u5d5f";
    private static String ta = "\u5d41\u5d42\u5d4c\u5d49\u5d44\u5d43\u5d4a\u5d72\u5d40\u5d5e\u5d4a";
    private static String ua = "\u5d61\u5d42\u5d4c\u5d49\u5d44\u5d43\u5d4a\u5d01\u5d0d\u5d5d\u5d41\u5d48\u5d4c\u5d5e\u5d48\u5d0d\u5d5a\u5d4c\u5d44\u5d59\u5d03\u5d03\u5d03";
    private static String wa = "\u5d41\u5d42\u5d4c\u5d49\u5d48\u5d5f\u5d5f\u5d72\u5d40\u5d5e\u5d4a";
    private static String xa = "\u5d6c\u5d43\u5d0d\u5d48\u5d5f\u5d5f\u5d42\u5d5f\u5d0d\u5d45\u5d4c\u5d5e\u5d0d\u5d42\u5d4e\u5d4e\u5d58\u5d5f\u5d48\u5d49";
    private static String Eoa = "\u5d09";
    
    public synchronized(final URL url, final String s) {
        this.Doa = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(synchronized.o) && !s2.startsWith(synchronized.p)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(synchronized.q);
                    if (index == -1) {
                        continue;
                    }
                    this.Doa.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(synchronized.C + s + synchronized.D);
        }
        finally {
            switch.b(bufferedReader);
        }
    }
    
    public String _() {
        String f = this.Doa.get(synchronized.E);
        if (f == null) {
            f = synchronized.F;
        }
        return f;
    }
    
    public String k() {
        String ua = this.Doa.get(synchronized.ta);
        if (ua == null) {
            ua = synchronized.ua;
        }
        return ua;
    }
    
    public String l() {
        String xa = this.Doa.get(synchronized.wa);
        if (xa == null) {
            xa = synchronized.xa;
        }
        return xa;
    }
    
    public String k(final String s) {
        String o = this.Doa.get(s);
        if (o == null) {
            o = synchronized.o;
        }
        return o;
    }
    
    public synchronized void b(final String s, final String s2) {
        this.Doa.put(s, s2);
    }
    
    public String _(final String s, final Hashtable hashtable) {
        final String s2 = this.Doa.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(synchronized.Eoa) && n++ < hashtable.size()) {
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
        return synchronized.o;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF5D2D);
        }
        return new String(array);
    }
    
    static {
        synchronized.Aoa = _(synchronized.Aoa);
        synchronized.Boa = _(synchronized.Boa);
        synchronized.Coa = _(synchronized.Coa);
        synchronized.o = _(synchronized.o);
        synchronized.p = _(synchronized.p);
        synchronized.q = _(synchronized.q);
        synchronized.C = _(synchronized.C);
        synchronized.D = _(synchronized.D);
        synchronized.E = _(synchronized.E);
        synchronized.F = _(synchronized.F);
        synchronized.ta = _(synchronized.ta);
        synchronized.ua = _(synchronized.ua);
        synchronized.wa = _(synchronized.wa);
        synchronized.xa = _(synchronized.xa);
        synchronized.Eoa = _(synchronized.Eoa);
    }
}
