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

public class s
{
    public static String s = "\ud758\ud743\ud75a\ud75a\ud769\ud745\ud753\ud755\ud742\ud75f\ud759\ud758";
    public static String t = "\ud753\ud75b\ud746\ud742\ud74f\ud769\ud745\ud753\ud755\ud742\ud75f\ud759\ud758";
    private boolean u;
    private Hashtable v;
    private static String w = "";
    private static String x = "\ud719\ud719";
    private static String y = "\ud758\ud743\ud75a\ud75a\ud769\ud745\ud753\ud755\ud742\ud75f\ud759\ud758";
    private static String z = "\ud753\ud75b\ud746\ud742\ud74f\ud769\ud745\ud753\ud755\ud742\ud75f\ud759\ud758";
    private static String A = "\ud70b";
    private static String B = "\ud76d";
    private static String C = "\ud76b";
    
    public boolean b(final URL url, final String s) {
        return this._(url, s, true);
    }
    
    public boolean _(final URL url, final String s, final boolean u) {
        this.u = u;
        this.v = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(s.w) && !trim.startsWith(s.x)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.u && this._(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this._(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.u && s2 == null) {
                            s2 = s.y;
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
            p.a(bufferedReader);
        }
        return true;
    }
    
    private void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.v.put(s, s.z);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(s.A) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(s.A);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.v.put(s, hashtable);
            return;
        }
        this.v.put(s, array);
    }
    
    private boolean _(final String s) {
        return s.indexOf(s.B) != -1 && s.indexOf(s.C) != -1;
    }
    
    private String _(final String s) {
        return s.substring(s.indexOf(s.B) + 1, s.indexOf(s.C)).toLowerCase().trim();
    }
    
    public String a(final String s) {
        String s2 = null;
        if (!this.u) {
            final Hashtable<Object, String> value = this.v.get(s.y);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.v.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.v.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.v.get(s);
        if (value == null || value instanceof String[] || value == s.z) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] _(final String s) {
        final String[] value = this.v.get(s);
        if (value == null || value instanceof Hashtable || value == s.z) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.v.get(s);
        if (value == null || value instanceof String[] || value == s.z) {
            return null;
        }
        return value;
    }
    
    public int _() {
        return this.v.size();
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFD736);
        }
        return new String(array);
    }
    
    static {
        s.s = b(s.s);
        s.t = b(s.t);
        s.w = b(s.w);
        s.x = b(s.x);
        s.y = b(s.y);
        s.z = b(s.z);
        s.A = b(s.A);
        s.B = b(s.B);
        s.C = b(s.C);
    }
}
