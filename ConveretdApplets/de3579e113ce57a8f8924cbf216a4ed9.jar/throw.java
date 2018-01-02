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

public class throw
{
    public static String kb = "\uca18\uca03\uca1a\uca1a\uca29\uca05\uca13\uca15\uca02\uca1f\uca19\uca18";
    public static String lb = "\uca13\uca1b\uca06\uca02\uca0f\uca29\uca05\uca13\uca15\uca02\uca1f\uca19\uca18";
    protected boolean mb;
    protected Hashtable zoa;
    private static String o = "";
    private static String p = "\uca59\uca59";
    private static String q = "\uca18\uca03\uca1a\uca1a\uca29\uca05\uca13\uca15\uca02\uca1f\uca19\uca18";
    private static String C = "\uca13\uca1b\uca06\uca02\uca0f\uca29\uca05\uca13\uca15\uca02\uca1f\uca19\uca18";
    private static String D = "\uca4b";
    private static String E = "\uca2d";
    private static String F = "\uca2b";
    
    public boolean b(final URL url, final String s) {
        return this.b(url, s, true);
    }
    
    public boolean b(final URL url, final String s, final boolean mb) {
        this.mb = mb;
        this.zoa = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(throw.o) && !trim.startsWith(throw.p)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.mb && this.a(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.a(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.mb && s2 == null) {
                            s2 = throw.q;
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
            switch.b(bufferedReader);
        }
        return true;
    }
    
    protected void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.zoa.put(s, throw.C);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(throw.D) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(throw.D);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.zoa.put(s, hashtable);
            return;
        }
        this.zoa.put(s, array);
    }
    
    protected boolean a(final String s) {
        return s.indexOf(throw.E) != -1 && s.indexOf(throw.F) != -1;
    }
    
    protected String a(final String s) {
        return s.substring(s.indexOf(throw.E) + 1, s.indexOf(throw.F)).toLowerCase().trim();
    }
    
    public String b(final String s) {
        String s2 = null;
        if (!this.mb) {
            final Hashtable<Object, String> value = this.zoa.get(throw.q);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.zoa.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.zoa.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.zoa.get(s);
        if (value == null || value instanceof String[] || value == throw.C) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] b(final String s) {
        final String[] value = this.zoa.get(s);
        if (value == null || value instanceof Hashtable || value == throw.C) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.zoa.get(s);
        if (value == null || value instanceof String[] || value == throw.C) {
            return null;
        }
        return value;
    }
    
    public int n() {
        return this.zoa.size();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFECA76);
        }
        return new String(array);
    }
    
    static {
        throw.kb = _(throw.kb);
        throw.lb = _(throw.lb);
        throw.o = _(throw.o);
        throw.p = _(throw.p);
        throw.q = _(throw.q);
        throw.C = _(throw.C);
        throw.D = _(throw.D);
        throw.E = _(throw.E);
        throw.F = _(throw.F);
    }
}
