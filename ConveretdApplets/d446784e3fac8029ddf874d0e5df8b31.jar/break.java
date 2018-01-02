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
    public static String M = "\uf3a7\uf3bc\uf3a5\uf3a5\uf396\uf3ba\uf3ac\uf3aa\uf3bd\uf3a0\uf3a6\uf3a7";
    public static String N = "\uf3ac\uf3a4\uf3b9\uf3bd\uf3b0\uf396\uf3ba\uf3ac\uf3aa\uf3bd\uf3a0\uf3a6\uf3a7";
    private boolean O;
    private Hashtable P;
    private static String Q = "";
    private static String R = "\uf3e6\uf3e6";
    private static String S = "\uf3a7\uf3bc\uf3a5\uf3a5\uf396\uf3ba\uf3ac\uf3aa\uf3bd\uf3a0\uf3a6\uf3a7";
    private static String T = "\uf3ac\uf3a4\uf3b9\uf3bd\uf3b0\uf396\uf3ba\uf3ac\uf3aa\uf3bd\uf3a0\uf3a6\uf3a7";
    private static String U = "\uf3f4";
    private static String V = "\uf392";
    private static String W = "\uf394";
    
    public boolean b(final URL url, final String s) {
        return this._(url, s, true);
    }
    
    public boolean _(final URL url, final String s, final boolean o) {
        this.O = o;
        this.P = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(break.Q) && !trim.startsWith(break.R)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.O && this._(trim)) {
                        if (s2 != null) {
                            this.b(s2, vector);
                        }
                        s2 = this._(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.O && s2 == null) {
                            s2 = break.S;
                            vector = new Vector<String>();
                        }
                        vector.addElement(new String(trim));
                    }
                }
            }
            if (s2 != null) {
                this.b(s2, vector);
            }
        }
        catch (IOException ex) {
            return false;
        }
        finally {
            abstract._(bufferedReader);
        }
        return true;
    }
    
    private void b(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.P.put(s, break.T);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(break.U) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(break.U);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.P.put(s, hashtable);
            return;
        }
        this.P.put(s, array);
    }
    
    private boolean _(final String s) {
        return s.indexOf(break.V) != -1 && s.indexOf(break.W) != -1;
    }
    
    private String _(final String s) {
        return s.substring(s.indexOf(break.V) + 1, s.indexOf(break.W)).toLowerCase().trim();
    }
    
    public String a(final String s) {
        String s2 = null;
        if (!this.O) {
            final Hashtable<Object, String> value = this.P.get(break.S);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.P.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.P.get(keys.nextElement());
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
    
    public String a(final String s, final String s2) {
        final Hashtable<Object, String> value = this.P.get(s);
        if (value == null || value instanceof String[] || value == break.T) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] _(final String s) {
        final String[] value = this.P.get(s);
        if (value == null || value instanceof Hashtable || value == break.T) {
            return null;
        }
        return value;
    }
    
    public Hashtable b(final String s) {
        final Hashtable value = this.P.get(s);
        if (value == null || value instanceof String[] || value == break.T) {
            return null;
        }
        return value;
    }
    
    public int b() {
        return this.P.size();
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEF3C9);
        }
        return new String(array);
    }
    
    static {
        break.M = b(break.M);
        break.N = b(break.N);
        break.Q = b(break.Q);
        break.R = b(break.R);
        break.S = b(break.S);
        break.T = b(break.T);
        break.U = b(break.U);
        break.V = b(break.V);
        break.W = b(break.W);
    }
}
