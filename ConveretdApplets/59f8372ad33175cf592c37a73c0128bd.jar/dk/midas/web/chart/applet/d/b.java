// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.d;

import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.io.UnsupportedEncodingException;

public abstract class b implements c, d
{
    private String else(final String s) {
        final String a = this.a();
        String s2 = null;
        if (a == null) {
            return null;
        }
        final int index = a.indexOf(s + ",");
        if (index >= 0) {
            final int index2 = a.indexOf(",", index);
            final int index3 = a.indexOf("|", index2);
            s2 = ((index3 > 0) ? a.substring(index2 + 1, index3) : a.substring(index2 + 1));
        }
        return s2;
    }
    
    public String if(final String s, final String s2) {
        String s3 = this.else(s);
        if (s3 == null) {
            s3 = this.a(s);
        }
        return (s3 == null) ? s2 : s3;
    }
    
    public int a(final String s, final int n) {
        String s2 = this.else(s);
        if (s2 == null) {
            s2 = this.a(s);
        }
        int int1;
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex) {
                int1 = n;
            }
        }
        else {
            int1 = n;
        }
        return int1;
    }
    
    public String a(final String s) {
        return this.a(s, null);
    }
    
    public String a(final String s, final String s2) {
        final String parameter = super.getParameter(s);
        if (parameter != null) {
            try {
                return new String(parameter.getBytes("UTF8"), "UTF8");
            }
            catch (UnsupportedEncodingException ex) {
                final String message = ex.getMessage();
                int n = 0;
                ++n;
                System.out.println(message);
            }
        }
        return s2;
    }
    
    public boolean a(final String s, final boolean b) {
        final String parameter = super.getParameter(s);
        return (parameter != null) ? parameter.equalsIgnoreCase("true") : b;
    }
    
    public int if(final String s, final int n) {
        final String parameter = super.getParameter(s);
        if (parameter != null) {
            try {
                return Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        return n;
    }
    
    public Color for(final String s, final String s2) {
        final String parameter = super.getParameter(s);
        if (parameter != null) {
            try {
                return byte(parameter);
            }
            catch (Exception ex) {}
        }
        return (s2 != null) ? byte(s2) : null;
    }
    
    public Font do(final String s, final String s2) {
        final String parameter = super.getParameter(s);
        if (parameter != null) {
            try {
                return char(parameter);
            }
            catch (Exception ex) {}
        }
        return (s2 != null) ? char(s2) : null;
    }
    
    public String a() {
        return super.getParameter("persist");
    }
    
    public int if(final int n) {
        final String if1 = this.if("Analyse_" + n, null);
        if (if1 == null) {
            return -1;
        }
        if (n == 0) {
            return 4;
        }
        try {
            return Integer.parseInt(if1);
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    public Object[] a(final int n) {
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.if(((n == 0) ? "Analyse_" : "Initialize_") + n, null), ";");
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final Object[] array = new Object[vector.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
        }
        vector.removeAllElements();
        return array;
    }
    
    public static Color byte(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public static Font char(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final String nextToken = stringTokenizer.nextToken();
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        if (!stringTokenizer.hasMoreTokens()) {
            return new Font(nextToken, 0, int1);
        }
        final String nextToken2 = stringTokenizer.nextToken();
        int n = 0;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken2, "+");
        while (stringTokenizer2.hasMoreTokens()) {
            n += case(stringTokenizer2.nextToken());
        }
        return new Font(nextToken, n, int1);
    }
    
    protected static int case(final String s) {
        if (s.equals("BOLD")) {
            return 1;
        }
        if (s.equals("ITALIC")) {
            return 2;
        }
        return 0;
    }
}
