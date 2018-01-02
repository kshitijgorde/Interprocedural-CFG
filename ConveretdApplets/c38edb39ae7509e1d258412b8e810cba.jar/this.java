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

public class this
{
    public static String ua = "\ud603\ud618\ud601\ud601\ud632\ud61e\ud608\ud60e\ud619\ud604\ud602\ud603";
    public static String va = "\ud608\ud600\ud61d\ud619\ud614\ud632\ud61e\ud608\ud60e\ud619\ud604\ud602\ud603";
    private boolean wa;
    private Hashtable xa;
    private static String ya = "";
    private static String za = "\ud642\ud642";
    private static String Aa = "\ud603\ud618\ud601\ud601\ud632\ud61e\ud608\ud60e\ud619\ud604\ud602\ud603";
    private static String Ba = "\ud608\ud600\ud61d\ud619\ud614\ud632\ud61e\ud608\ud60e\ud619\ud604\ud602\ud603";
    private static String Ca = "\ud650";
    private static String Da = "\ud636";
    private static String Ea = "\ud630";
    
    public boolean a(final URL url, final String s) {
        return this._(url, s, true);
    }
    
    public boolean _(final URL url, final String s, final boolean wa) {
        this.wa = wa;
        this.xa = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(this.ya) && !trim.startsWith(this.za)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.wa && this._(trim)) {
                        if (s2 != null) {
                            this.b(s2, vector);
                        }
                        s2 = this.a(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.wa && s2 == null) {
                            s2 = this.Aa;
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
            switch.a(bufferedReader);
        }
        return true;
    }
    
    private void b(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.xa.put(s, this.Ba);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(this.Ca) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(this.Ca);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.xa.put(s, hashtable);
            return;
        }
        this.xa.put(s, array);
    }
    
    private boolean _(final String s) {
        return s.indexOf(this.Da) != -1 && s.indexOf(this.Ea) != -1;
    }
    
    private String a(final String s) {
        return s.substring(s.indexOf(this.Da) + 1, s.indexOf(this.Ea)).toLowerCase().trim();
    }
    
    public String b(final String s) {
        String s2 = null;
        if (!this.wa) {
            final Hashtable<Object, String> value = this.xa.get(this.Aa);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.xa.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.xa.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.xa.get(s);
        if (value == null || value instanceof String[] || value == this.Ba) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] _(final String s) {
        final String[] value = this.xa.get(s);
        if (value == null || value instanceof Hashtable || value == this.Ba) {
            return null;
        }
        return value;
    }
    
    public Hashtable b(final String s) {
        final Hashtable value = this.xa.get(s);
        if (value == null || value instanceof String[] || value == this.Ba) {
            return null;
        }
        return value;
    }
    
    public int j() {
        return this.xa.size();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ud66d');
        }
        return new String(array);
    }
    
    static {
        this.ua = _(this.ua);
        this.va = _(this.va);
        this.ya = _(this.ya);
        this.za = _(this.za);
        this.Aa = _(this.Aa);
        this.Ba = _(this.Ba);
        this.Ca = _(this.Ca);
        this.Da = _(this.Da);
        this.Ea = _(this.Ea);
    }
}
