import java.util.Enumeration;
import java.io.IOException;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class t
{
    public static String a = "\u750f\u7514\u750d\u750d\u753e\u7512\u7504\u7502\u7515\u7508\u750e\u750f";
    public static String b = "\u7504\u750c\u7511\u7515\u7518\u753e\u7512\u7504\u7502\u7515\u7508\u750e\u750f";
    private boolean c;
    private Hashtable d;
    private static String e = "";
    private static String f = "\u754e\u754e";
    private static String g = "\u750f\u7514\u750d\u750d\u753e\u7512\u7504\u7502\u7515\u7508\u750e\u750f";
    private static String h = "\u7504\u750c\u7511\u7515\u7518\u753e\u7512\u7504\u7502\u7515\u7508\u750e\u750f";
    private static String i = "\u755c";
    private static String j = "\u753a";
    private static String k = "\u753c";
    
    public boolean _(final URL url, final String s) {
        return this._(url, s, true);
    }
    
    public boolean _(final URL url, final String s, final boolean c) {
        this.c = c;
        this.d = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(t.e) && !trim.startsWith(t.f)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.c && this._(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.c && s2 == null) {
                            s2 = t.g;
                            vector = new Vector<String>();
                        }
                        vector.addElement(new String(trim));
                    }
                }
            }
            if (s2 != null) {
                this.a(s2, vector);
            }
        }
        catch (IOException ex) {
            return false;
        }
        finally {
            s._(bufferedReader);
        }
        return true;
    }
    
    private void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.d.put(s, t.h);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(t.i) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(t.i);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.d.put(s, hashtable);
            return;
        }
        this.d.put(s, array);
    }
    
    private boolean _(final String s) {
        return s.indexOf(t.j) != -1 && s.indexOf(t.k) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(t.j) + 1, s.indexOf(t.k)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.c) {
            final Hashtable<Object, String> value = this.d.get(t.g);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.d.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.d.get(keys.nextElement());
                if (value2 instanceof Hashtable) {
                    s2 = ((Hashtable<K, String>)value2).get(s);
                    if (s2 != null) {
                        break;
                    }
                    continue;
                }
            }
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        final Hashtable<Object, String> value = this.d.get(s);
        if (value == null || value instanceof String[] || value == t.h) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] b(final String s) {
        final String[] value = this.d.get(s);
        if (value == null || value instanceof Hashtable || value == t.h) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.d.get(s);
        if (value == null || value instanceof String[] || value == t.h) {
            return null;
        }
        return value;
    }
    
    public int _() {
        return this.d.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x17561);
        }
        return new String(array);
    }
    
    static {
        t.a = a(t.a);
        t.b = a(t.b);
        t.e = a(t.e);
        t.f = a(t.f);
        t.g = a(t.g);
        t.h = a(t.h);
        t.i = a(t.i);
        t.j = a(t.j);
        t.k = a(t.k);
    }
}
