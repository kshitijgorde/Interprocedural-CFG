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

public class r
{
    public static String u = "\u7d23\u7d38\u7d21\u7d21\u7d12\u7d3e\u7d28\u7d2e\u7d39\u7d24\u7d22\u7d23";
    public static String v = "\u7d28\u7d20\u7d3d\u7d39\u7d34\u7d12\u7d3e\u7d28\u7d2e\u7d39\u7d24\u7d22\u7d23";
    private boolean w;
    private Hashtable x;
    private static String t = "";
    private static String y = "\u7d62\u7d62";
    private static String z = "\u7d23\u7d38\u7d21\u7d21\u7d12\u7d3e\u7d28\u7d2e\u7d39\u7d24\u7d22\u7d23";
    private static String A = "\u7d28\u7d20\u7d3d\u7d39\u7d34\u7d12\u7d3e\u7d28\u7d2e\u7d39\u7d24\u7d22\u7d23";
    private static String B = "\u7d70";
    private static String C = "\u7d16";
    private static String D = "\u7d10";
    
    public boolean a(final URL url, final String s) {
        return this.b(url, s, true);
    }
    
    public boolean b(final URL url, final String s, final boolean w) {
        this.w = w;
        this.x = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(r.t) && !trim.startsWith(r.y)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.w && this._(trim)) {
                        if (s2 != null) {
                            this._(s2, vector);
                        }
                        s2 = this.a(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.w && s2 == null) {
                            s2 = r.z;
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
            q.a(bufferedReader);
        }
        return true;
    }
    
    private void _(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.x.put(s, r.A);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(r.B) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(r.B);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.x.put(s, hashtable);
            return;
        }
        this.x.put(s, array);
    }
    
    private boolean _(final String s) {
        return s.indexOf(r.C) != -1 && s.indexOf(r.D) != -1;
    }
    
    private String a(final String s) {
        return s.substring(s.indexOf(r.C) + 1, s.indexOf(r.D)).toLowerCase().trim();
    }
    
    public String b(final String s) {
        String s2 = null;
        if (!this.w) {
            final Hashtable<Object, String> value = this.x.get(r.z);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.x.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.x.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.x.get(s);
        if (value == null || value instanceof String[] || value == r.A) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] _(final String s) {
        final String[] value = this.x.get(s);
        if (value == null || value instanceof Hashtable || value == r.A) {
            return null;
        }
        return value;
    }
    
    public Hashtable a(final String s) {
        final Hashtable value = this.x.get(s);
        if (value == null || value instanceof String[] || value == r.A) {
            return null;
        }
        return value;
    }
    
    public int a() {
        return this.x.size();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF7D4D);
        }
        return new String(array);
    }
    
    static {
        r.u = _(r.u);
        r.v = _(r.v);
        r.t = _(r.t);
        r.y = _(r.y);
        r.z = _(r.z);
        r.A = _(r.A);
        r.B = _(r.B);
        r.C = _(r.C);
        r.D = _(r.D);
    }
}
