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

public class protected
{
    public static String eb = "\u3090\u308b\u3092\u3092\u30a1\u308d\u309b\u309d\u308a\u3097\u3091\u3090";
    public static String fb = "\u309b\u3093\u308e\u308a\u3087\u30a1\u308d\u309b\u309d\u308a\u3097\u3091\u3090";
    private boolean gb;
    private Hashtable hb;
    private static String ib = "";
    private static String jb = "\u30d1\u30d1";
    private static String kb = "\u3090\u308b\u3092\u3092\u30a1\u308d\u309b\u309d\u308a\u3097\u3091\u3090";
    private static String lb = "\u309b\u3093\u308e\u308a\u3087\u30a1\u308d\u309b\u309d\u308a\u3097\u3091\u3090";
    private static String mb = "\u30c3";
    private static String nb = "\u30a5";
    private static String ob = "\u30a3";
    
    public boolean _(final URL url, final String s) {
        return this.a(url, s, true);
    }
    
    public boolean a(final URL url, final String s, final boolean gb) {
        this.gb = gb;
        this.hb = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(protected.ib) && !trim.startsWith(protected.jb)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.gb && this.a(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.gb && s2 == null) {
                            s2 = protected.kb;
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
            private.a(bufferedReader);
        }
        return true;
    }
    
    private void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.hb.put(s, protected.lb);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(protected.mb) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(protected.mb);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.hb.put(s, hashtable);
            return;
        }
        this.hb.put(s, array);
    }
    
    private boolean a(final String s) {
        return s.indexOf(protected.nb) != -1 && s.indexOf(protected.ob) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(protected.nb) + 1, s.indexOf(protected.ob)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.gb) {
            final Hashtable<Object, String> value = this.hb.get(protected.kb);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.hb.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.hb.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.hb.get(s);
        if (value == null || value instanceof String[] || value == protected.lb) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] a(final String s) {
        final String[] value = this.hb.get(s);
        if (value == null || value instanceof Hashtable || value == protected.lb) {
            return null;
        }
        return value;
    }
    
    public Hashtable a(final String s) {
        final Hashtable value = this.hb.get(s);
        if (value == null || value instanceof String[] || value == protected.lb) {
            return null;
        }
        return value;
    }
    
    public int _() {
        return this.hb.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF30FE);
        }
        return new String(array);
    }
    
    static {
        protected.eb = a(protected.eb);
        protected.fb = a(protected.fb);
        protected.ib = a(protected.ib);
        protected.jb = a(protected.jb);
        protected.kb = a(protected.kb);
        protected.lb = a(protected.lb);
        protected.mb = a(protected.mb);
        protected.nb = a(protected.nb);
        protected.ob = a(protected.ob);
    }
}
