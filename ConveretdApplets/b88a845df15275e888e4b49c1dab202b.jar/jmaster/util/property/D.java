// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property;

import java.lang.reflect.Method;
import javax.swing.ImageIcon;
import java.util.StringTokenizer;
import java.util.MissingResourceException;
import java.util.Properties;

public class D extends Properties
{
    private static final long D = 3257849878796383536L;
    public static final String C = "set";
    public static final Class[] A;
    public static final String B = ",";
    
    public D() {
    }
    
    public D(final Properties properties) {
        super(properties);
    }
    
    public static long J(String trim) {
        trim = trim.trim();
        long n;
        try {
            n = Long.parseLong(trim);
        }
        catch (NumberFormatException ex) {
            if (trim.startsWith("0x")) {
                n = Long.parseLong(trim.substring(2), 16);
            }
            else if (trim.startsWith("#")) {
                n = Long.parseLong(trim.substring(1), 16);
            }
            else {
                n = Long.parseLong(trim, 16);
            }
        }
        return n;
    }
    
    public static int R(final String s) {
        return (int)J(s);
    }
    
    public static short K(final String s) {
        return (short)J(s);
    }
    
    public static byte A(final String s) {
        return (byte)J(s);
    }
    
    public static double N(final String s) {
        return Double.parseDouble(s.trim());
    }
    
    public static double E(final String s) {
        return Float.parseFloat(s.trim());
    }
    
    public static boolean P(final String s) {
        return "true".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s);
    }
    
    public String Q(final String s) throws MissingResourceException {
        final String property;
        if ((property = this.getProperty(s)) == null) {
            throw new MissingResourceException("property not found, key=" + s, this.getClass().getName(), s);
        }
        return property;
    }
    
    public String C(final String s, final String s2) {
        return this.getProperty(s, s2);
    }
    
    public int D(final String s) throws MissingResourceException, NumberFormatException {
        return R(this.Q(s));
    }
    
    public int A(final String s, final int n) {
        try {
            return this.D(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public long B(final String s) throws MissingResourceException, NumberFormatException {
        return J(this.Q(s));
    }
    
    public long A(final String s, final long n) {
        try {
            return J(this.Q(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public double G(final String s) throws MissingResourceException, NumberFormatException {
        return N(this.Q(s));
    }
    
    public double A(final String s, final double n) {
        try {
            return N(this.Q(s));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public boolean C(final String s) throws MissingResourceException {
        return P(this.Q(s));
    }
    
    public boolean L(final String s) throws MissingResourceException {
        return this.Q(s).endsWith("%");
    }
    
    public double M(final String s) {
        final String q = this.Q(s);
        return N(q.substring(0, q.length() - 1));
    }
    
    public StringTokenizer S(final String s) throws MissingResourceException {
        return this.D(s, ",");
    }
    
    public StringTokenizer D(final String s, final String s2) throws MissingResourceException {
        return new StringTokenizer(this.Q(s), s2);
    }
    
    public String[] H(final String s) {
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
    
    public int[] I(final String s) {
        return this.A(s, ",");
    }
    
    public int[] A(final String s, final String s2) {
        final StringTokenizer d = this.D(s, s2);
        final int[] array = new int[d.countTokens()];
        int n = 0;
        while (d.hasMoreTokens()) {
            array[n] = R(d.nextToken());
            ++n;
        }
        return array;
    }
    
    public ImageIcon F(final String s) {
        return this.O(this.Q(s));
    }
    
    public ImageIcon O(final String s) {
        return new ImageIcon(this.getClass().getResource(s));
    }
    
    public String F(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        if (s != null) {
            sb.append(s);
        }
        if (s2 != null && s2.length() > 0) {
            if (sb.length() > 0) {
                sb.append('.');
            }
            sb.append(s2);
        }
        return sb.toString();
    }
    
    public boolean B(final String s, final String s2) {
        return this.containsKey(this.F(s, s2));
    }
    
    static {
        A = new Class[] { Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE };
    }
    
    protected class _A
    {
        String A;
        Class B;
        Method C;
    }
}
