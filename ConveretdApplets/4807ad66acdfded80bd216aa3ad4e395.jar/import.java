import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.net.URLEncoder;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class import extends native
{
    public static String Ja = "\u548c";
    public static String Ka = "\u548a";
    private Hashtable La;
    private finally Ma;
    private String ha;
    private boolean Na;
    private static String ja = "\u54e8\u54d1\u54c2\u54d5\u54cb\u54c8\u54c6\u54c3\u5489\u54f4\u54d3\u54c8\u54c4\u54cc\u54d4\u54f7\u54d5\u54c8\u54d1\u54ce\u54c3\u54c2\u54d5";
    private static String na = "\u54ca\u54d2\u54cb\u54d3\u54ce\u54d7\u54cb\u54c2";
    private static String oa = "\u54d2\u54d7\u54c3\u54c6\u54d3\u54c2\u54f3\u54de\u54d7\u54c2";
    private static String pa = "\u54f4\u54fe\u54ea\u54e5\u54e8\u54eb";
    private static String sa = "\u54d4\u54de\u54ca\u54c5\u54c8\u54cb\u54d4\u549a";
    private static String ta = "\u54ce\u54c9\u54d1\u54c6\u54cb\u54ce\u54c3\u5487\u54d2\u54d5\u54cb\u549d\u5487";
    private static String ua = "\u54c2\u54d5\u54d5\u54c8\u54d5\u5487\u54d5\u54c2\u54c6\u54c3\u54ce\u54c9\u54c3\u5487\u54c3\u54c6\u54d3\u54c6\u5487\u54c1\u54c8\u54d5\u549d\u5487";
    
    public import(final finally ma, final Object o) {
        super(import.ja, 1000, o);
        this.La = new Hashtable();
        this.Ma = ma;
        this.Na = import.na.equalsIgnoreCase(extends._(o, import.oa, null));
    }
    
    public void a(final Hashtable hashtable) {
        final Object value = hashtable.get(import.pa);
        synchronized (this.La) {
            if (value != null && !this.La.containsKey(value)) {
                this.La.put(value, hashtable);
                if (this.Na) {
                    final StringBuffer sb = new StringBuffer(100);
                    final Enumeration<Object> keys = this.La.keys();
                    while (keys.hasMoreElements()) {
                        if (sb.length() != 0) {
                            sb.append('-');
                        }
                        sb.append(keys.nextElement());
                    }
                    this.ha = URLEncoder.encode(sb.toString());
                }
            }
        }
        // monitorexit(this.La)
    }
    
    protected URL a(final String s) {
        final String string = String.valueOf(super.ka) + import.sa + s;
        try {
            return extends._(s, string, false);
        }
        catch (Exception ex) {
            System.out.println(import.ta + string);
            return null;
        }
    }
    
    protected void a(final String s) {
        final URL a = this.a(s);
        if (a == null) {
            return;
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a.openStream()));
        Hashtable<String, String> hashtable = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line.trim();
            final int index = line.indexOf(58);
            if (index != -1) {
                final String substring = line.substring(0, index);
                if (substring.equals(import.pa)) {
                    if (hashtable != null) {
                        this.Ma.a(hashtable, this);
                    }
                    hashtable = (Hashtable<String, String>)this.La.get(line.substring(index + 1));
                }
                if (hashtable == null) {
                    continue;
                }
                hashtable.put(substring, line.substring(index + 1));
            }
        }
        if (hashtable != null) {
            this.Ma.a(hashtable, this);
        }
    }
    
    public void run() {
        if (!this.a(super.ka)) {
            super.la = null;
        }
        final Thread currentThread = Thread.currentThread();
        while (currentThread == super.la) {
            if (this.Na) {
                try {
                    this.a(this.ha);
                }
                catch (IOException ex) {
                    System.out.println(import.ua + this.ha);
                }
            }
            else {
                final Enumeration<Object> keys = this.La.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    try {
                        this.a(URLEncoder.encode(nextElement.toString()));
                    }
                    catch (IOException ex2) {
                        System.out.println(import.ua + nextElement);
                    }
                }
            }
            if (currentThread == super.la) {
                try {
                    Thread.sleep(super.qa);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    private static String h(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF54A7);
        }
        return new String(array);
    }
    
    static {
        import.Ja = h(import.Ja);
        import.Ka = h(import.Ka);
        import.ja = h(import.ja);
        import.na = h(import.na);
        import.oa = h(import.oa);
        import.pa = h(import.pa);
        import.sa = h(import.sa);
        import.ta = h(import.ta);
        import.ua = h(import.ua);
    }
}
