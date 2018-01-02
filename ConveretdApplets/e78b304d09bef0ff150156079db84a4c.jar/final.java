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

public class final
{
    public static String mb = "\u859e\u8585\u859c\u859c\u85af\u8583\u8595\u8593\u8584\u8599\u859f\u859e";
    public static String nb = "\u8595\u859d\u8580\u8584\u8589\u85af\u8583\u8595\u8593\u8584\u8599\u859f\u859e";
    private boolean ob;
    private Hashtable pb;
    private static String qb = "";
    private static String rb = "\u85df\u85df";
    private static String sb = "\u859e\u8585\u859c\u859c\u85af\u8583\u8595\u8593\u8584\u8599\u859f\u859e";
    private static String tb = "\u8595\u859d\u8580\u8584\u8589\u85af\u8583\u8595\u8593\u8584\u8599\u859f\u859e";
    private static String _ = "\u85cd";
    private static String a = "\u85ab";
    private static String b = "\u85ad";
    
    public boolean _(final URL url, final String s) {
        return this.a(url, s, true);
    }
    
    public boolean a(final URL url, final String s, final boolean ob) {
        this.ob = ob;
        this.pb = new Hashtable();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url, s).openStream()));
            String s2 = null;
            Vector<String> vector = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(final.qb) && !trim.startsWith(final.rb)) {
                    if (trim.charAt(0) == '\u00ff') {
                        continue;
                    }
                    if (this.ob && this.a(trim)) {
                        if (s2 != null) {
                            this.b(s2, vector);
                        }
                        s2 = this.b(trim);
                        vector = new Vector<String>();
                    }
                    else {
                        if (!this.ob && s2 == null) {
                            s2 = final.sb;
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
            default.b(bufferedReader);
        }
        return true;
    }
    
    private void b(final String s, final Vector vector) {
        final int size = vector.size();
        if (size == 0) {
            this.pb.put(s, final.tb);
            return;
        }
        int n = 1;
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (n != 0 && array[i].indexOf(final._) == -1) {
                n = 0;
            }
        }
        if (n != 0) {
            final Hashtable hashtable = new Hashtable<String, String>(array.length);
            for (int j = 0; j < array.length; ++j) {
                final int index = array[j].indexOf(final._);
                hashtable.put(array[j].substring(0, index).trim(), array[j].substring(index + 1).trim());
            }
            this.pb.put(s, hashtable);
        }
        else {
            this.pb.put(s, array);
        }
    }
    
    private boolean a(final String s) {
        return s.indexOf(final.a) != -1 && s.indexOf(final.b) != -1;
    }
    
    private String b(final String s) {
        return s.substring(s.indexOf(final.a) + 1, s.indexOf(final.b)).toLowerCase().trim();
    }
    
    public String _(final String s) {
        String s2 = null;
        if (!this.ob) {
            final Hashtable<Object, String> value = this.pb.get(final.sb);
            if (value instanceof Hashtable) {
                s2 = ((Hashtable<K, String>)value).get(s);
            }
        }
        else {
            final Enumeration<String> keys = this.pb.keys();
            while (keys.hasMoreElements()) {
                final Object value2 = this.pb.get(keys.nextElement());
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
        final Hashtable<Object, String> value = this.pb.get(s);
        if (value == null || value instanceof String[] || value == final.tb) {
            return null;
        }
        return ((Hashtable<K, String>)value).get(s2);
    }
    
    public String[] a(final String s) {
        final String[] value = this.pb.get(s);
        if (value == null || value instanceof Hashtable || value == final.tb) {
            return null;
        }
        return value;
    }
    
    public Hashtable _(final String s) {
        final Hashtable value = this.pb.get(s);
        if (value == null || value instanceof String[] || value == final.tb) {
            return null;
        }
        return value;
    }
    
    public int b() {
        return this.pb.size();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE85F0);
        }
        return new String(array);
    }
    
    static {
        final.mb = a(final.mb);
        final.nb = a(final.nb);
        final.qb = a(final.qb);
        final.rb = a(final.rb);
        final.sb = a(final.sb);
        final.tb = a(final.tb);
        final._ = a(final._);
        final.a = a(final.a);
        final.b = a(final.b);
    }
}
