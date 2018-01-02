import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class ParameterUtilities
{
    private Applet applet;
    
    ParameterUtilities(final Applet applet) {
        this.applet = applet;
    }
    
    Color getColorParameter(final String s, final Color color) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            return color;
        }
        if (parameter.equalsIgnoreCase("random")) {
            return new Color((int)(Math.random() * 1.6777216E7));
        }
        if (parameter.equalsIgnoreCase("dark random")) {
            return new Color((int)(Math.random() * 1.6777216E7)).darker();
        }
        if (parameter.equalsIgnoreCase("light random")) {
            return new Color((int)(Math.random() * 1.6777216E7)).brighter();
        }
        return new Color(Integer.parseInt(parameter, 16));
    }
    
    int getStringArrayParameter(final String s, final String[] array, final int n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            return n;
        }
        if (parameter.equalsIgnoreCase("random")) {
            return (int)(Math.random() * array.length);
        }
        for (int i = 0; i < array.length; ++i) {
            if (parameter.equalsIgnoreCase(array[i])) {
                return i;
            }
        }
        return n;
    }
    
    int getIntegerParameter(final String s, final int n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            return n;
        }
        return Integer.parseInt(parameter, 10);
    }
}
