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

public class o
{
    public static String sJc = "\u7d8b\u7d90\u7d89\u7d89\u7dba\u7d96\u7d80\u7d86\u7d91\u7d8c\u7d8a\u7d8b";
    public static String tJc = "\u7d80\u7d88\u7d95\u7d91\u7d9c\u7dba\u7d96\u7d80\u7d86\u7d91\u7d8c\u7d8a\u7d8b";
    protected boolean uJc;
    protected Hashtable vJc;
    private static String ta = "";
    private static String ua = "\u7dca\u7dca";
    private static String va = "\u7d8b\u7d90\u7d89\u7d89\u7dba\u7d96\u7d80\u7d86\u7d91\u7d8c\u7d8a\u7d8b";
    private static String wa = "\u7d80\u7d88\u7d95\u7d91\u7d9c\u7dba\u7d96\u7d80\u7d86\u7d91\u7d8c\u7d8a\u7d8b";
    private static String xa = "\u7dd8";
    private static String ya = "\u7dbe";
    private static String za = "\u7db8";
    
    public boolean _(final URL url, final String s) {
        return this.b(url, s, true);
    }
    
    public boolean b(final URL url, final String s, final boolean uJc) {
        this.uJc = uJc;
        this.vJc = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(o.ta) && !trim.startsWith(o.ua)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.uJc && this.b(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.uJc && s2 == null) {
                            s2 = o.va;
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
            j._(bufferedReader);
        }
        return true;
    }
    
    protected void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.vJc.put(s, o.wa);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(o.xa) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(o.xa);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.vJc.put(s, hashtable);
        }
        else {
            this.vJc.put(s, array);
        }
    }
    
    protected boolean b(final String s) {
        return s.indexOf(o.ya) != -1 && s.indexOf(o.za) != -1;
    }
    
    protected String b(final String s) {
        return s.substring(s.indexOf(o.ya) + 1, s.indexOf(o.za)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.uJc) {
            final Hashtable<Object, String> value = this.vJc.get(o.va);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.vJc.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.vJc.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.vJc.get(s);
        if (value == null || value instanceof String[] || value == o.wa) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] b(final String s) {
        final String[] value = this.vJc.get(s);
        if (value == null || value instanceof Hashtable || value == o.wa) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.vJc.get(s);
        if (value == null || value instanceof String[] || value == o.wa) {
            return null;
        }
        return value;
    }
    
    public int b() {
        return this.vJc.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x17DE5);
        }
        return new String(array);
    }
    
    static {
        o.sJc = a(o.sJc);
        o.tJc = a(o.tJc);
        o.ta = a(o.ta);
        o.ua = a(o.ua);
        o.va = a(o.va);
        o.wa = a(o.wa);
        o.xa = a(o.xa);
        o.ya = a(o.ya);
        o.za = a(o.za);
    }
}
