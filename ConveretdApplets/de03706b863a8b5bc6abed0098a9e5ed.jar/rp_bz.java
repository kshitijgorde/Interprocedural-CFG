import java.util.Hashtable;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_bz
{
    private Properties a;
    
    rp_bz(final rp_fK rp_fK, final Vector vector) {
        this.a = null;
        final Properties a;
        if ((a = a(rp_fK, null, "eEPdefault.properties")) == null) {
            throw new FileNotFoundException("Fatal Error: eEPdefault.properties file not found");
        }
        Properties a2 = a(rp_fK, a, "eEP.properties");
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                a2 = a(rp_fK, a2, vector.elementAt(i));
            }
        }
        this.a = a2;
    }
    
    public final String c(final String s) {
        return this.a.getProperty(s);
    }
    
    public final String a(final String s, final String s2) {
        return this.a.getProperty(s, s2);
    }
    
    public final double a(final String s) {
        final String property = this.a.getProperty(s);
        try {
            return Double.valueOf(property);
        }
        catch (NumberFormatException ex) {
            System.out.println("ERR: Property not found (" + s + ")");
            return 0.0;
        }
    }
    
    public final int a(final String s) {
        final String property = this.a.getProperty(s);
        try {
            return rp_C.a(property);
        }
        catch (NumberFormatException ex) {
            System.out.println("ERR: Property not found (" + s + ")");
            return 0;
        }
    }
    
    public final int a(final String s, final int n) {
        final String property = this.a.getProperty(s);
        try {
            return rp_C.a(property);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public final Color a(String a, Color a2) {
        final String property;
        if ((property = this.a.getProperty(s, "")).length() == 0) {
            return color;
        }
        a = (String)0;
        a2 = (Color)0;
        int a3 = 0;
        try {
            final int index = property.indexOf(59);
            final int index2 = property.indexOf(59, index + 1);
            a = (String)rp_C.a(property.substring(0, index));
            a2 = (Color)rp_C.a(property.substring(index + 1, index2));
            a3 = rp_C.a(property.substring(index2 + 1));
        }
        catch (Exception ex) {}
        return new Color((int)a, (int)a2, a3);
    }
    
    final int[] a(final String s) {
        final StringTokenizer stringTokenizer;
        final int[] array = new int[(stringTokenizer = new StringTokenizer(this.a.getProperty(s), ";")).countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = rp_C.a(stringTokenizer.nextToken());
            ++n;
        }
        return array;
    }
    
    private static Properties a(final rp_fK rp_fK, final Properties properties, final String s) {
        InputStream b = null;
        try {
            b = rp_fK.b(s);
        }
        catch (Exception ex) {
            System.out.println("addPropertiesFile:" + ex);
        }
        if (b == null) {
            return properties;
        }
        final Properties properties2 = new Properties(properties);
        try {
            properties2.load(b);
        }
        catch (IOException ex2) {
            System.out.println("addPropertiesFile:" + ex2);
            return properties;
        }
        return properties2;
    }
    
    public final Object a(final String s, final String s2) {
        if (s2 == null) {
            return ((Hashtable<K, Object>)this.a).remove(s);
        }
        return ((Hashtable<String, String>)this.a).put(s, s2);
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Enumeration<?> propertyNames = this.a.propertyNames();
        int n = 1;
        while (propertyNames.hasMoreElements()) {
            if (n == 0) {
                sb.append("\n");
            }
            n = 0;
            final String s = (String)propertyNames.nextElement();
            sb.append(s + " = " + this.a.getProperty(s));
        }
        return sb.toString();
    }
}
