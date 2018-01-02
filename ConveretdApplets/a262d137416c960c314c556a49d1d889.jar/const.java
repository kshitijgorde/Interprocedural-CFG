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

public class const
{
    public static String K1 = "\ue694\ue68f\ue696\ue696\ue6a5\ue689\ue69f\ue699\ue68e\ue693\ue695\ue694";
    public static String L1 = "\ue69f\ue697\ue68a\ue68e\ue683\ue6a5\ue689\ue69f\ue699\ue68e\ue693\ue695\ue694";
    protected boolean M1;
    protected Hashtable N1;
    private static String z = "";
    private static String A = "\ue6d5\ue6d5";
    private static String B = "\ue694\ue68f\ue696\ue696\ue6a5\ue689\ue69f\ue699\ue68e\ue693\ue695\ue694";
    private static String C = "\ue69f\ue697\ue68a\ue68e\ue683\ue6a5\ue689\ue69f\ue699\ue68e\ue693\ue695\ue694";
    private static String D = "\ue6c7";
    private static String E = "\ue6a1";
    private static String F = "\ue6a7";
    
    public boolean a(final URL url, final String s) {
        return this.a(url, s, true);
    }
    
    public boolean a(final URL url, final String s, final boolean m1) {
        this.M1 = m1;
        this.N1 = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(const.z) && !trim.startsWith(const.A)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.M1 && this.b(trim)) {
                        if (s2 != null) {
                            this.a(s2, vector);
                        }
                        s2 = this.a(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.M1 && s2 == null) {
                            s2 = const.B;
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
            abstract.b(bufferedReader);
        }
        return true;
    }
    
    protected void a(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.N1.put(s, const.C);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(const.D) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(const.D);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.N1.put(s, hashtable);
        }
        else {
            this.N1.put(s, array);
        }
    }
    
    protected boolean b(final String s) {
        return s.indexOf(const.E) != -1 && s.indexOf(const.F) != -1;
    }
    
    protected String a(final String s) {
        return s.substring(s.indexOf(const.E) + 1, s.indexOf(const.F)).toLowerCase().trim();
    }
    
    public String b(final String s) {
        String s2 = null;
        if (!this.M1) {
            final Hashtable<Object, String> value = this.N1.get(const.B);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.N1.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.N1.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.N1.get(s);
        if (value == null || value instanceof String[] || value == const.C) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] b(final String s) {
        final String[] value = this.N1.get(s);
        if (value == null || value instanceof Hashtable || value == const.C) {
            return null;
        }
        return value;
    }
    
    public Hashtable b(final String s) {
        final Hashtable value = this.N1.get(s);
        if (value == null || value instanceof String[] || value == const.C) {
            return null;
        }
        return value;
    }
    
    public int a() {
        return this.N1.size();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFE6FA);
        }
        return new String(array);
    }
    
    static {
        const.K1 = _(const.K1);
        const.L1 = _(const.L1);
        const.z = _(const.z);
        const.A = _(const.A);
        const.B = _(const.B);
        const.C = _(const.C);
        const.D = _(const.D);
        const.E = _(const.E);
        const.F = _(const.F);
    }
}
