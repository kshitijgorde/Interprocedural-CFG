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

public class continue
{
    static String ya = "\u647d\u644c\u644c\u6450\u6459\u6448\u641c\u6454\u645d\u644f\u641c\u645d\u641c\u644c\u644e\u6453\u645e\u6450\u6459\u6451\u6410\u641c\u645f\u6453\u6452\u6448\u645d\u645f\u6448\u641c\u6448\u6454\u6459\u641c\u644b\u6459\u645e\u6451\u645d\u644f\u6448\u6459\u644e";
    static String za = "\u6470\u6453\u645d\u6458\u6455\u6452\u645b\u6410\u641c\u644c\u6450\u6459\u645d\u644f\u6459\u641c\u644b\u645d\u6455\u6448\u6412\u6412\u6412";
    static String Aa = "\u647d\u6452\u641c\u6459\u644e\u644e\u6453\u644e\u641c\u6454\u645d\u644f\u641c\u6453\u645f\u645f\u6449\u644e\u6459\u6458";
    private Hashtable Ba;
    private static String qb = "";
    private static String rb = "\u6413\u6413";
    private static String sb = "\u6401";
    private static String tb = "\u644e\u6459\u645d\u6458\u6455\u6452\u645b\u641c\u645a\u644e\u6453\u6451\u641c";
    private static String _ = "\u641c\u6454\u645d\u644f\u641c\u6452\u6453\u6448\u641c\u644f\u6449\u645f\u645f\u6459\u6459\u6458\u6459\u6458";
    private static String a = "\u644f\u6448\u645d\u6448\u6449\u644f\u645e\u6463\u6451\u644f\u645b";
    private static String b = "\u647d\u644c\u644c\u6450\u6459\u6448\u641c\u6454\u645d\u644f\u641c\u645d\u641c\u644c\u644e\u6453\u645e\u6450\u6459\u6451\u6410\u641c\u645f\u6453\u6452\u6448\u645d\u645f\u6448\u641c\u6448\u6454\u6459\u641c\u644b\u6459\u645e\u6451\u645d\u644f\u6448\u6459\u644e";
    private static String C = "\u6450\u6453\u645d\u6458\u6455\u6452\u645b\u6463\u6451\u644f\u645b";
    private static String D = "\u6470\u6453\u645d\u6458\u6455\u6452\u645b\u6410\u641c\u644c\u6450\u6459\u645d\u644f\u6459\u641c\u644b\u645d\u6455\u6448\u6412\u6412\u6412";
    private static String E = "\u6450\u6453\u645d\u6458\u6459\u644e\u644e\u6463\u6451\u644f\u645b";
    private static String F = "\u647d\u6452\u641c\u6459\u644e\u644e\u6453\u644e\u641c\u6454\u645d\u644f\u641c\u6453\u645f\u645f\u6449\u644e\u6459\u6458";
    private static String G = "\u6418";
    
    public continue(final URL url, final String s) {
        this.Ba = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(continue.qb) && !s2.startsWith(continue.rb)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(continue.sb);
                    if (index == -1) {
                        continue;
                    }
                    this.Ba.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(continue.tb + s + continue._);
        }
        finally {
            default.b(bufferedReader);
        }
    }
    
    public String a() {
        String b = this.Ba.get(continue.a);
        if (b == null) {
            b = continue.b;
        }
        return b;
    }
    
    public String b() {
        String d = this.Ba.get(continue.C);
        if (d == null) {
            d = continue.D;
        }
        return d;
    }
    
    public String _() {
        String f = this.Ba.get(continue.E);
        if (f == null) {
            f = continue.F;
        }
        return f;
    }
    
    public String i(final String s) {
        String qb = this.Ba.get(s);
        if (qb == null) {
            qb = continue.qb;
        }
        return qb;
    }
    
    public synchronized void _(final String s, final String s2) {
        this.Ba.put(s, s2);
    }
    
    public String b(final String s, final Hashtable hashtable) {
        final String s2 = this.Ba.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(continue.G) && n++ < hashtable.size()) {
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
        return continue.qb;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u643c');
        }
        return new String(array);
    }
    
    static {
        continue.ya = a(continue.ya);
        continue.za = a(continue.za);
        continue.Aa = a(continue.Aa);
        continue.qb = a(continue.qb);
        continue.rb = a(continue.rb);
        continue.sb = a(continue.sb);
        continue.tb = a(continue.tb);
        continue._ = a(continue._);
        continue.a = a(continue.a);
        continue.b = a(continue.b);
        continue.C = a(continue.C);
        continue.D = a(continue.D);
        continue.E = a(continue.E);
        continue.F = a(continue.F);
        continue.G = a(continue.G);
    }
}
