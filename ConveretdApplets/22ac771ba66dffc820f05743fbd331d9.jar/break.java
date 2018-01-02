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

public class break
{
    public static String _ = "\u0e18\u0e03\u0e1a\u0e1a\u0e29\u0e05\u0e13\u0e15\u0e02\u0e1f\u0e19\u0e18";
    public static String a = "\u0e13\u0e1b\u0e06\u0e02\u0e0f\u0e29\u0e05\u0e13\u0e15\u0e02\u0e1f\u0e19\u0e18";
    private boolean b;
    private Hashtable c;
    private static String d = "";
    private static String e = "\u0e59\u0e59";
    private static String f = "\u0e18\u0e03\u0e1a\u0e1a\u0e29\u0e05\u0e13\u0e15\u0e02\u0e1f\u0e19\u0e18";
    private static String g = "\u0e13\u0e1b\u0e06\u0e02\u0e0f\u0e29\u0e05\u0e13\u0e15\u0e02\u0e1f\u0e19\u0e18";
    private static String h = "\u0e4b";
    private static String i = "\u0e2d";
    private static String j = "\u0e2b";
    
    public boolean _(final URL url, final String s) {
        return this._(url, s, true);
    }
    
    public boolean _(final URL url, final String s, final boolean b) {
        this.b = b;
        this.c = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(break.d) && !trim.startsWith(break.e)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.b && this.b(trim)) {
                        if (s2 != null) {
                            this._(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.b && s2 == null) {
                            s2 = break.f;
                            vector = new Vector<String>();
                        }
                        vector.addElement(new String(trim));
                    }
                }
            }
            if (s2 != null) {
                this._(s2, vector);
            }
        }
        catch (IOException ex) {
            return false;
        }
        finally {
            v.a(bufferedReader);
        }
        return true;
    }
    
    private void _(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.c.put(s, break.g);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(break.h) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(break.h);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.c.put(s, hashtable);
        }
        else {
            this.c.put(s, array);
        }
    }
    
    private boolean b(final String s) {
        return s.indexOf(break.i) != -1 && s.indexOf(break.j) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(break.i) + 1, s.indexOf(break.j)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.b) {
            final Hashtable<Object, String> value = this.c.get(break.f);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.c.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.c.get(keys.nextElement());
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
    
    public String b(final String s, final String s2) {
        final Hashtable<Object, String> value = this.c.get(s);
        if (value == null || value instanceof String[] || value == break.g) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] a(final String s) {
        final String[] value = this.c.get(s);
        if (value == null || value instanceof Hashtable || value == break.g) {
            return null;
        }
        return value;
    }
    
    public Hashtable a(final String s) {
        final Hashtable value = this.c.get(s);
        if (value == null || value instanceof String[] || value == break.g) {
            return null;
        }
        return value;
    }
    
    public int b() {
        return this.c.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF0E76);
        }
        return new String(array);
    }
    
    static {
        break._ = a(break._);
        break.a = a(break.a);
        break.d = a(break.d);
        break.e = a(break.e);
        break.f = a(break.f);
        break.g = a(break.g);
        break.h = a(break.h);
        break.i = a(break.i);
        break.j = a(break.j);
    }
}
