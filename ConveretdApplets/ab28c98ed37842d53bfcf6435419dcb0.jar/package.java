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

public class package
{
    static String Z = "\uffa9\uff98\uff98\uff84\uff8d\uff9c\uffc8\uff80\uff89\uff9b\uffc8\uff89\uffc8\uff98\uff9a\uff87\uff8a\uff84\uff8d\uff85\uffc4\uffc8\uff8b\uff87\uff86\uff9c\uff89\uff8b\uff9c\uffc8\uff9c\uff80\uff8d\uffc8\uff9f\uff8d\uff8a\uff85\uff89\uff9b\uff9c\uff8d\uff9a";
    static String _a = "\uffa4\uff87\uff89\uff8c\uff81\uff86\uff8f\uffc4\uffc8\uff98\uff84\uff8d\uff89\uff9b\uff8d\uffc8\uff9f\uff89\uff81\uff9c\uffc6\uffc6\uffc6";
    static String aa = "\uffa9\uff86\uffc8\uff8d\uff9a\uff9a\uff87\uff9a\uffc8\uff80\uff89\uff9b\uffc8\uff87\uff8b\uff8b\uff9d\uff9a\uff8d\uff8c";
    private Hashtable ba;
    private static String ib = "";
    private static String jb = "\uffc7\uffc7";
    private static String kb = "\uffd5";
    private static String lb = "\uff9a\uff8d\uff89\uff8c\uff81\uff86\uff8f\uffc8\uff8e\uff9a\uff87\uff85\uffc8";
    private static String mb = "\uffc8\uff80\uff89\uff9b\uffc8\uff86\uff87\uff9c\uffc8\uff9b\uff9d\uff8b\uff8b\uff8d\uff8d\uff8c\uff8d\uff8c";
    private static String nb = "\uff9b\uff9c\uff89\uff9c\uff9d\uff9b\uff8a\uffb7\uff85\uff9b\uff8f";
    private static String ob = "\uffa9\uff98\uff98\uff84\uff8d\uff9c\uffc8\uff80\uff89\uff9b\uffc8\uff89\uffc8\uff98\uff9a\uff87\uff8a\uff84\uff8d\uff85\uffc4\uffc8\uff8b\uff87\uff86\uff9c\uff89\uff8b\uff9c\uffc8\uff9c\uff80\uff8d\uffc8\uff9f\uff8d\uff8a\uff85\uff89\uff9b\uff9c\uff8d\uff9a";
    private static String c = "\uff84\uff87\uff89\uff8c\uff81\uff86\uff8f\uffb7\uff85\uff9b\uff8f";
    private static String d = "\uffa4\uff87\uff89\uff8c\uff81\uff86\uff8f\uffc4\uffc8\uff98\uff84\uff8d\uff89\uff9b\uff8d\uffc8\uff9f\uff89\uff81\uff9c\uffc6\uffc6\uffc6";
    private static String e = "\uff84\uff87\uff89\uff8c\uff8d\uff9a\uff9a\uffb7\uff85\uff9b\uff8f";
    private static String f = "\uffa9\uff86\uffc8\uff8d\uff9a\uff9a\uff87\uff9a\uffc8\uff80\uff89\uff9b\uffc8\uff87\uff8b\uff8b\uff9d\uff9a\uff8d\uff8c";
    private static String g = "\uffcc";
    
    public package(final URL url, final String s) {
        this.ba = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String s2 = new String(line.trim());
                if (!s2.equals(package.ib) && !s2.startsWith(package.jb)) {
                    if (s2.charAt(0) == '\u00ff') {
                        continue;
                    }
                    final int index = s2.indexOf(package.kb);
                    if (index == -1) {
                        continue;
                    }
                    this.ba.put(s2.substring(0, index).toLowerCase().trim(), s2.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.err.println(package.lb + s + package.mb);
        }
        finally {
            private.a(bufferedReader);
        }
    }
    
    public String b() {
        String ob = this.ba.get(package.nb);
        if (ob == null) {
            ob = package.ob;
        }
        return ob;
    }
    
    public String _() {
        String d = this.ba.get(package.c);
        if (d == null) {
            d = package.d;
        }
        return d;
    }
    
    public String a() {
        String f = this.ba.get(package.e);
        if (f == null) {
            f = package.f;
        }
        return f;
    }
    
    public String f(final String s) {
        String ib = this.ba.get(s);
        if (ib == null) {
            ib = package.ib;
        }
        return ib;
    }
    
    public synchronized void b(final String s, final String s2) {
        this.ba.put(s, s2);
    }
    
    public String _(final String s, final Hashtable hashtable) {
        final String s2 = this.ba.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(package.g) && n++ < hashtable.size()) {
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
        return package.ib;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\uffe8');
        }
        return new String(array);
    }
    
    static {
        package.Z = a(package.Z);
        package._a = a(package._a);
        package.aa = a(package.aa);
        package.ib = a(package.ib);
        package.jb = a(package.jb);
        package.kb = a(package.kb);
        package.lb = a(package.lb);
        package.mb = a(package.mb);
        package.nb = a(package.nb);
        package.ob = a(package.ob);
        package.c = a(package.c);
        package.d = a(package.d);
        package.e = a(package.e);
        package.f = a(package.f);
        package.g = a(package.g);
    }
}
