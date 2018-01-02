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

public class protected extends static
{
    public static String va = "\u01ae";
    public static String wa = "\u01a8";
    private Hashtable xa;
    private native ya;
    private String S;
    private boolean za;
    private static String V = "\u01ca\u01f3\u01e0\u01f7\u01e9\u01ea\u01e4\u01e1\u01ab\u01d6\u01f1\u01ea\u01e6\u01ee\u01f6\u01d5\u01f7\u01ea\u01f3\u01ec\u01e1\u01e0\u01f7";
    private static String W = "\u01e8\u01f0\u01e9\u01f1\u01ec\u01f5\u01e9\u01e0";
    private static String _a = "\u01f0\u01f5\u01e1\u01e4\u01f1\u01e0\u01d1\u01fc\u01f5\u01e0";
    private static String aa = "\u01d6\u01dc\u01c8\u01c7\u01ca\u01c9";
    private static String ba = "\u01f6\u01fc\u01e8\u01e7\u01ea\u01e9\u01f6\u01b8";
    private static String fa = "\u01ec\u01eb\u01f3\u01e4\u01e9\u01ec\u01e1\u01a5\u01f0\u01f7\u01e9\u01bf\u01a5";
    private static String ga = "\u01e0\u01f7\u01f7\u01ea\u01f7\u01a5\u01f7\u01e0\u01e4\u01e1\u01ec\u01eb\u01e1\u01a5\u01e1\u01e4\u01f1\u01e4\u01a5\u01e3\u01ea\u01f7\u01bf\u01a5";
    
    public protected(final native ya, final Object o) {
        super(protected.V, 1000, o);
        this.xa = new Hashtable();
        this.ya = ya;
        this.za = protected.W.equalsIgnoreCase(instanceof._(o, protected._a, null));
    }
    
    public void b(final Hashtable hashtable) {
        final Object value = hashtable.get(protected.aa);
        synchronized (this.xa) {
            if (value != null && !this.xa.containsKey(value)) {
                this.xa.put(value, hashtable);
                if (this.za) {
                    final StringBuffer sb = new StringBuffer(100);
                    final Enumeration<Object> keys = this.xa.keys();
                    while (keys.hasMoreElements()) {
                        if (sb.length() != 0) {
                            sb.append('-');
                        }
                        sb.append(keys.nextElement());
                    }
                    this.S = URLEncoder.encode(sb.toString());
                }
            }
        }
        // monitorexit(this.xa)
    }
    
    protected URL _(final String s) {
        final String string = String.valueOf(super.X) + protected.ba + s;
        try {
            return instanceof._((Object)s, string, false);
        }
        catch (Exception ex) {
            System.out.println(protected.fa + string);
            return null;
        }
    }
    
    protected void _(final String s) {
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
                if (substring.equals(protected.aa)) {
                    if (hashtable != null) {
                        this.ya._(hashtable, this);
                    }
                    hashtable = (Hashtable<String, String>)this.xa.get(line.substring(index + 1));
                }
                if (hashtable == null) {
                    continue;
                }
                hashtable.put(substring, line.substring(index + 1));
            }
        }
        if (hashtable != null) {
            this.ya._(hashtable, this);
        }
    }
    
    public void run() {
        if (!this.b(super.X)) {
            super.Y = null;
        }
        final Thread currentThread = Thread.currentThread();
        while (currentThread == super.Y) {
            if (this.za) {
                try {
                    this._(this.S);
                }
                catch (IOException ex) {
                    System.out.println(protected.ga + this.S);
                }
            }
            else {
                final Enumeration<Object> keys = this.xa.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    try {
                        this._(URLEncoder.encode(nextElement.toString()));
                    }
                    catch (IOException ex2) {
                        System.out.println(protected.ga + nextElement);
                    }
                }
            }
            if (currentThread == super.Y) {
                try {
                    Thread.sleep(super.ca);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    private static String m(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF0185);
        }
        return new String(array);
    }
    
    static {
        protected.va = m(protected.va);
        protected.wa = m(protected.wa);
        protected.V = m(protected.V);
        protected.W = m(protected.W);
        protected._a = m(protected._a);
        protected.aa = m(protected.aa);
        protected.ba = m(protected.ba);
        protected.fa = m(protected.fa);
        protected.ga = m(protected.ga);
    }
}
