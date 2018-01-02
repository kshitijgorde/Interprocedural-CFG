import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ParamParser
{
    public CustomParser parser;
    private Applet applet;
    
    public ParamParser(final Applet applet) {
        this.applet = applet;
        this.parser = new CustomParser();
    }
    
    public int parseInt(final String s, final int n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return Integer.parseInt(parameter);
        }
        return n;
    }
    
    public int[] parseInts(final String s, final String s2) {
        if (s != null) {
            final String[] strings = this.parser.parseStrings(s, s2);
            final int[] array = new int[strings.length];
            for (int i = 0; i < strings.length; ++i) {
                array[i] = Integer.parseInt(strings[i]);
            }
            return array;
        }
        return null;
    }
    
    public long parseLong(final String s, final long n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return Long.parseLong(parameter);
        }
        return n;
    }
    
    public Color parseColor(final String s, final Color color) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return new Color(Integer.parseInt(parameter, 16));
        }
        return color;
    }
    
    public Color parseColor(final String s, final String s2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return new Color(Integer.parseInt(parameter, 16));
        }
        return new Color(Integer.parseInt(s2, 16));
    }
    
    public Font parseFont(final String s, final String s2, final int n, final int n2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            final String[] strings = this.parser.parseStrings(parameter, "|");
            final int int1 = Integer.parseInt(strings[2]);
            int n3 = 0;
            if (strings[1].equalsIgnoreCase("PLAIN")) {
                n3 = 0;
            }
            if (strings[1].equalsIgnoreCase("BOLD")) {
                n3 = 1;
            }
            if (strings[1].equalsIgnoreCase("ITALIC")) {
                n3 = 2;
            }
            return new Font(strings[0], n3, int1);
        }
        return new Font(s2, n, n2);
    }
    
    public boolean parseBoolean(final String s, final boolean b) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return Boolean.valueOf(parameter);
        }
        return b;
    }
    
    public String parseString(final String s, final String s2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    public String[] parseStrings(final String s, final String s2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return this.parser.parseStrings(parameter, s2);
        }
        System.err.println("name=\"" + s + "\" not defined.");
        return null;
    }
    
    public String[] parseStrings(final String s, final String s2, final int n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            System.err.println("name=\"" + s + "\" not defined.");
            return null;
        }
        final String[] strings = this.parser.parseStrings(parameter, s2);
        if (strings.length == n) {
            return strings;
        }
        System.err.println("name=\"" + s + "\" format incorrect.");
        return null;
    }
}
