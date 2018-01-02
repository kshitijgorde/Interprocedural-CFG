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

public class t extends abstract
{
    public static String J = "\u83b4";
    public static String K = "\u83b2";
    private Hashtable L;
    private o M;
    private String j;
    private boolean N;
    private String h;
    private static String l = "\u83d0\u83e9\u83fa\u83ed\u83f3\u83f0\u83fe\u83fb\u83b1\u83cc\u83eb\u83f0\u83fc\u83f4\u83ec\u83cf\u83ed\u83f0\u83e9\u83f6\u83fb\u83fa\u83ed";
    private static String m = "\u83f9\u83fa\u83fa\u83fb";
    private static String o = "\u83ea\u83f1\u83f4\u83f1\u83f0\u83e8\u83f1\u83cc\u83eb\u83f0\u83fc\u83f4\u83d9\u83fa\u83fa\u83fb";
    private static String p = "\u83ed\u83fa\u83f9\u83ed\u83fa\u83ec\u83f7\u83db\u83fa\u83f3\u83fe\u83e6";
    private static String s = "\u83f2\u83ea\u83f3\u83eb\u83f6\u83ef\u83f3\u83fa";
    private static String u = "\u83ea\u83ef\u83fb\u83fe\u83eb\u83fa\u83cb\u83e6\u83ef\u83fa";
    private static String v = "\u83cc\u83c6\u83d2\u83dd\u83d0\u83d3";
    private static String w = "\u83ec\u83e6\u83f2\u83fd\u83f0\u83f3\u83ec\u83a2";
    private static String O = "\u83f6\u83f1\u83e9\u83fe\u83f3\u83f6\u83fb\u83bf\u83ea\u83ed\u83f3\u83a5\u83bf";
    private static String P = "\u83fa\u83ed\u83ed\u83f0\u83ed\u83bf\u83ed\u83fa\u83fe\u83fb\u83f6\u83f1\u83fb\u83bf\u83fb\u83fe\u83eb\u83fe\u83bf\u83f9\u83f0\u83ed\u83a5\u83bf";
    
    public t(final o m, final Object o) {
        super(t.l, o);
        this.L = new Hashtable();
        this.M = m;
        this.h = break.a(m.a(o, t.m, t.o));
        super.n = m.b(o, t.p, 1000);
        if (super.n < 0) {
            super.n = 1000;
        }
        this.N = t.s.equalsIgnoreCase(m.a(o, t.u, null));
    }
    
    public void _(final Hashtable hashtable) {
        final Object value = hashtable.get(t.v);
        synchronized (this.L) {
            if (value != null && !this.L.containsKey(value)) {
                this.L.put(value, hashtable);
                if (this.N) {
                    final StringBuffer sb = new StringBuffer(100);
                    final Enumeration<Object> keys = this.L.keys();
                    while (keys.hasMoreElements()) {
                        if (sb.length() != 0) {
                            sb.append('-');
                        }
                        sb.append(keys.nextElement());
                    }
                    this.j = URLEncoder.encode(sb.toString());
                }
            }
        }
        // monitorexit(this.L)
    }
    
    protected URL _(final String s) {
        final String string = String.valueOf(this.h) + t.w + s;
        try {
            return m.b(s, string, false);
        }
        catch (Exception ex) {
            System.out.println(t.O + string);
            return null;
        }
    }
    
    protected void a(final String s) {
        final URL _ = this._(s);
        if (_ == null) {
            return;
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_.openStream()));
        Hashtable<String, String> hashtable = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line.trim();
            final int index = line.indexOf(58);
            if (index != -1) {
                final String substring = line.substring(0, index);
                if (substring.equals(t.v)) {
                    if (hashtable != null) {
                        this.M.b(hashtable, this);
                    }
                    hashtable = (Hashtable<String, String>)this.L.get(line.substring(index + 1));
                }
                if (hashtable == null) {
                    continue;
                }
                hashtable.put(substring, line.substring(index + 1));
            }
        }
        if (hashtable != null) {
            this.M.b(hashtable, this);
        }
    }
    
    public void run() {
        if (!this.b(this.h)) {
            super.q = null;
        }
        final Thread currentThread = Thread.currentThread();
        while (currentThread == super.q) {
            if (this.N) {
                try {
                    this.a(this.j);
                }
                catch (IOException ex) {
                    System.out.println(t.P + this.j);
                }
            }
            else {
                final Enumeration<Object> keys = this.L.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    try {
                        this.a(URLEncoder.encode(nextElement.toString()));
                    }
                    catch (IOException ex2) {
                        System.out.println(t.P + nextElement);
                    }
                }
            }
            if (currentThread == super.q) {
                try {
                    Thread.sleep(super.n);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    private static String i(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x1839F);
        }
        return new String(array);
    }
    
    static {
        t.J = i(t.J);
        t.K = i(t.K);
        t.l = i(t.l);
        t.m = i(t.m);
        t.o = i(t.o);
        t.p = i(t.p);
        t.s = i(t.s);
        t.u = i(t.u);
        t.v = i(t.v);
        t.w = i(t.w);
        t.O = i(t.O);
        t.P = i(t.P);
    }
}
