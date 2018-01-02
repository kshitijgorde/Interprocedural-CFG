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

public class native
{
    public static String Qa = "\uc8f9\uc8e2\uc8fb\uc8fb\uc8c8\uc8e4\uc8f2\uc8f4\uc8e3\uc8fe\uc8f8\uc8f9";
    public static String Ra = "\uc8f2\uc8fa\uc8e7\uc8e3\uc8ee\uc8c8\uc8e4\uc8f2\uc8f4\uc8e3\uc8fe\uc8f8\uc8f9";
    private boolean Sa;
    private Hashtable Ta;
    private static String Ua = "";
    private static String Va = "\uc8b8\uc8b8";
    private static String Wa = "\uc8f9\uc8e2\uc8fb\uc8fb\uc8c8\uc8e4\uc8f2\uc8f4\uc8e3\uc8fe\uc8f8\uc8f9";
    private static String Xa = "\uc8f2\uc8fa\uc8e7\uc8e3\uc8ee\uc8c8\uc8e4\uc8f2\uc8f4\uc8e3\uc8fe\uc8f8\uc8f9";
    private static String Ya = "\uc8aa";
    private static String Za = "\uc8cc";
    private static String _b = "\uc8ca";
    
    public boolean a(final URL url, final String s) {
        return this.a(url, s, true);
    }
    
    public boolean a(final URL url, final String s, final boolean sa) {
        this.Sa = sa;
        this.Ta = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(native.Ua) && !trim.startsWith(native.Va)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.Sa && this.a(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.Sa && s2 == null) {
                            s2 = native.Wa;
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
            instanceof._(bufferedReader);
        }
        return true;
    }
    
    private void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.Ta.put(s, native.Xa);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(native.Ya) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(native.Ya);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.Ta.put(s, hashtable);
            return;
        }
        this.Ta.put(s, array);
    }
    
    private boolean a(final String s) {
        return s.indexOf(native.Za) != -1 && s.indexOf(native._b) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(native.Za) + 1, s.indexOf(native._b)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.Sa) {
            final Hashtable<Object, String> value = this.Ta.get(native.Wa);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.Ta.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.Ta.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.Ta.get(s);
        if (value == null || value instanceof String[] || value == native.Xa) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] a(final String s) {
        final String[] value = this.Ta.get(s);
        if (value == null || value instanceof Hashtable || value == native.Xa) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.Ta.get(s);
        if (value == null || value instanceof String[] || value == native.Xa) {
            return null;
        }
        return value;
    }
    
    public int a() {
        return this.Ta.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEC897);
        }
        return new String(array);
    }
    
    static {
        native.Qa = a(native.Qa);
        native.Ra = a(native.Ra);
        native.Ua = a(native.Ua);
        native.Va = a(native.Va);
        native.Wa = a(native.Wa);
        native.Xa = a(native.Xa);
        native.Ya = a(native.Ya);
        native.Za = a(native.Za);
        native._b = a(native._b);
    }
}
