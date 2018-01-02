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

public class e
{
    public static String Qa = "\u6b87\u6b9c\u6b85\u6b85\u6bb6\u6b9a\u6b8c\u6b8a\u6b9d\u6b80\u6b86\u6b87";
    public static String Ra = "\u6b8c\u6b84\u6b99\u6b9d\u6b90\u6bb6\u6b9a\u6b8c\u6b8a\u6b9d\u6b80\u6b86\u6b87";
    private boolean Sa;
    private Hashtable Ta;
    private static String Ma = "";
    private static String Na = "\u6bc6\u6bc6";
    private static String Oa = "\u6b87\u6b9c\u6b85\u6b85\u6bb6\u6b9a\u6b8c\u6b8a\u6b9d\u6b80\u6b86\u6b87";
    private static String Pa = "\u6b8c\u6b84\u6b99\u6b9d\u6b90\u6bb6\u6b9a\u6b8c\u6b8a\u6b9d\u6b80\u6b86\u6b87";
    private static String Ua = "\u6bd4";
    private static String Va = "\u6bb2";
    private static String Wa = "\u6bb4";
    
    public boolean a(final URL url, final String s) {
        return this.b(url, s, true);
    }
    
    public boolean b(final URL url, final String s, final boolean sa) {
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
                if (!trim.equals(e.Ma) && !trim.startsWith(e.Na)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.Sa && this.b(trim)) {
                        if (s2 != null) {
                            this._(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.Sa && s2 == null) {
                            s2 = e.Oa;
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
            d._(bufferedReader);
        }
        return true;
    }
    
    private void _(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.Ta.put(s, e.Pa);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(e.Ua) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(e.Ua);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.Ta.put(s, hashtable);
            return;
        }
        this.Ta.put(s, array);
    }
    
    private boolean b(final String s) {
        return s.indexOf(e.Va) != -1 && s.indexOf(e.Wa) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(e.Va) + 1, s.indexOf(e.Wa)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.Sa) {
            final Hashtable<Object, String> value = this.Ta.get(e.Oa);
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
    
    public String b(final String s, final String s2) {
        final Hashtable<Object, String> value = this.Ta.get(s);
        if (value == null || value instanceof String[] || value == e.Pa) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] b(final String s) {
        final String[] value = this.Ta.get(s);
        if (value == null || value instanceof Hashtable || value == e.Pa) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.Ta.get(s);
        if (value == null || value instanceof String[] || value == e.Pa) {
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
            array[i] = (char)(s.charAt(i) ^ '\u6be9');
        }
        return new String(array);
    }
    
    static {
        e.Qa = a(e.Qa);
        e.Ra = a(e.Ra);
        e.Ma = a(e.Ma);
        e.Na = a(e.Na);
        e.Oa = a(e.Oa);
        e.Pa = a(e.Pa);
        e.Ua = a(e.Ua);
        e.Va = a(e.Va);
        e.Wa = a(e.Wa);
    }
}
