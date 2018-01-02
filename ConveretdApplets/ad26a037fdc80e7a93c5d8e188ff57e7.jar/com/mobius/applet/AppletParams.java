// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import java.net.MalformedURLException;
import java.net.URL;
import com.mobius.utility.RGBColorConverter;
import java.awt.Color;
import java.io.PrintStream;
import java.applet.Applet;

public class AppletParams
{
    private final Applet applet;
    private final PrintStream errorPrintStream;
    
    public AppletParams(final Applet applet) {
        if (applet == null) {
            throw new IllegalArgumentException("Can't construct an AppletParams object with a null argument");
        }
        this.applet = applet;
        this.errorPrintStream = System.err;
    }
    
    public String getString(final String s) {
        return this.getString(s, "");
    }
    
    public String getString(final String s, final String s2) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        return (parameter == null) ? b : Boolean.valueOf(parameter);
    }
    
    public int getInt(final String s, final int n) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        int int1 = n;
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.errorPrintStream.println("Can't parse integer parameter [" + s + "]: [" + parameter + "] is not a valid integer. Using default value [" + n + "]");
                int1 = n;
            }
        }
        return int1;
    }
    
    public double getDouble(final String s, final double n) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        double double1 = n;
        if (parameter != null) {
            try {
                double1 = Double.parseDouble(parameter);
            }
            catch (NumberFormatException ex) {
                this.errorPrintStream.println("Can't parse double parameter [" + s + "]: [" + parameter + "] is not a valid double. Using default value [" + n + "]");
                double1 = n;
            }
        }
        return double1;
    }
    
    public Color getColor(final String s, final Color color) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        return (parameter == null) ? color : RGBColorConverter.getColorFromString(parameter, color);
    }
    
    public URL getURL(final String s) {
        return this.getURL(s, null);
    }
    
    public URL getURL(final String s, final URL url) {
        this.validateParamName(s);
        final String parameter = this.applet.getParameter(s);
        URL url2 = url;
        if (parameter != null) {
            try {
                url2 = new URL(parameter);
            }
            catch (MalformedURLException ex) {
                this.errorPrintStream.println("Can't parse URL parameter [" + s + "]: [" + parameter + "] is not a valid URL. Using default value [" + url + "]");
                url2 = url;
            }
        }
        return url2;
    }
    
    private void validateParamName(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("The parameter name can't be null");
        }
    }
}
