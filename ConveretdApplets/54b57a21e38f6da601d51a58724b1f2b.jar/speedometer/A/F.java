// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import javax.swing.ImageIcon;
import java.util.StringTokenizer;
import java.util.MissingResourceException;
import java.util.Properties;

public class F extends Properties
{
    private static final long D = 3257849878796383536L;
    public static final String C = "true";
    public static final String A = "false";
    public static final String B = ",";
    
    public long H(String trim) {
        trim = trim.trim();
        long n;
        try {
            n = Long.parseLong(trim);
        }
        catch (NumberFormatException ex) {
            if (trim.startsWith("0x")) {
                n = Long.parseLong(trim.substring(2), 16);
            }
            else {
                n = Long.parseLong(trim, 16);
            }
        }
        return n;
    }
    
    public double M(String trim) {
        trim = trim.trim();
        return Double.parseDouble(trim);
    }
    
    public int O(final String s) {
        return (int)this.H(s);
    }
    
    public String N(final String s) throws MissingResourceException {
        final String property;
        if ((property = this.getProperty(s)) == null) {
            throw new MissingResourceException("property not found, key=" + s, this.getClass().getName(), s);
        }
        return property;
    }
    
    public String B(final String s, final String s2) {
        return this.getProperty(s, s2);
    }
    
    public String L(final String s) throws MissingResourceException {
        return this.N(s).trim();
    }
    
    public String C(final String s, final String s2) {
        String s3 = this.B(s, s2);
        if (s3 != null) {
            s3 = s3.trim();
        }
        return s3;
    }
    
    public int B(final String s) throws MissingResourceException, NumberFormatException {
        return this.O(this.N(s));
    }
    
    public int A(final String s, final int n) {
        try {
            return this.B(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public long A(final String s) throws MissingResourceException, NumberFormatException {
        return this.H(this.N(s));
    }
    
    public long A(final String s, final long n) {
        try {
            return this.H(this.N(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public double E(final String s) throws MissingResourceException, NumberFormatException {
        return this.M(this.N(s));
    }
    
    public double A(final String s, final double n) {
        try {
            return this.M(this.N(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public boolean I(final String s) throws MissingResourceException {
        return "true".equals(this.L(s));
    }
    
    public boolean C(final String s) throws MissingResourceException {
        return "false".equals(this.L(s));
    }
    
    public boolean J(final String s) throws MissingResourceException {
        return this.L(s).endsWith("%");
    }
    
    public double K(final String s) {
        final String l = this.L(s);
        return this.M(l.substring(0, l.length() - 1));
    }
    
    public StringTokenizer P(final String s) throws MissingResourceException {
        return this.D(s, ",");
    }
    
    public StringTokenizer D(final String s, final String s2) throws MissingResourceException {
        return new StringTokenizer(this.L(s), s2);
    }
    
    public String[] F(final String s) {
        return this.E(s, ",");
    }
    
    public String[] E(final String s, final String s2) {
        final StringTokenizer d = this.D(s, s2);
        final String[] array = new String[d.countTokens()];
        int n = 0;
        while (d.hasMoreTokens()) {
            array[n] = d.nextToken();
            ++n;
        }
        return array;
    }
    
    public int[] G(final String s) {
        return this.A(s, ",");
    }
    
    public int[] A(final String s, final String s2) {
        final StringTokenizer d = this.D(s, s2);
        final int[] array = new int[d.countTokens()];
        int n = 0;
        while (d.hasMoreTokens()) {
            array[n] = this.O(d.nextToken());
            ++n;
        }
        return array;
    }
    
    public ImageIcon D(final String s) {
        return new ImageIcon(this.getClass().getResource(this.N(s)));
    }
}
