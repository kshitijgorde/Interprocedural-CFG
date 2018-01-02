// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.MissingResourceException;
import java.util.Locale;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class NFResource
{
    private ResourceBundle a;
    private Hashtable b;
    private Hashtable c;
    
    public NFResource(final String s) {
        this.b = new Hashtable();
        this.c = new Hashtable();
        try {
            this.a = ResourceBundle.getBundle(s, Locale.getDefault());
        }
        catch (MissingResourceException ex) {
            System.err.println(s + ".properties not found");
            System.exit(1);
        }
    }
    
    public NFResource(final ResourceBundle a) {
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.a = a;
    }
    
    public String getString(final String s, final String s2) {
        String string;
        try {
            string = this.a.getString(s);
        }
        catch (MissingResourceException ex) {
            string = s2;
        }
        return string;
    }
    
    public String getString(final String s) {
        return this.getString(s, "");
    }
    
    public Color getColor(final String s, final Color color) {
        final Color color2 = this.b.get(s);
        if (color2 != null) {
            return color2;
        }
        final String string = this.getString(s);
        Color color3;
        if (string == null) {
            if (s.equals("ForegroundColor")) {
                color3 = SystemColor.text;
            }
            else {
                color3 = SystemColor.desktop;
            }
        }
        else {
            color3 = NFColor.get(string);
        }
        if (color3 == null) {
            color3 = color;
        }
        if (color3 != null) {
            this.b.put(s, color3);
        }
        return color3;
    }
    
    public Color getColor(final String s) {
        return this.getColor(s, SystemColor.desktop);
    }
    
    public Font getFont(final String s, final Font font) {
        Font font2 = this.c.get(s);
        if (font2 != null) {
            return font2;
        }
        final String string = this.getString(s);
        if (string != null && string.length() > 1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
            try {
                int n = 0;
                final String nextToken = stringTokenizer.nextToken();
                final int intValue = Integer.valueOf(stringTokenizer.nextToken());
                final String nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.indexOf("PLAIN") >= 0) {
                    n = 0;
                }
                if (nextToken2.indexOf("BOLD") >= 0) {
                    ++n;
                }
                if (nextToken2.indexOf("ITALIC") >= 0) {
                    n += 2;
                }
                font2 = new Font(nextToken, n, intValue);
            }
            catch (Exception ex) {
                NFDebug.print("Using default font: " + ex);
                ex.printStackTrace();
                font2 = null;
            }
        }
        if (string == null) {
            font2 = font;
        }
        if (font2 != null) {
            this.c.put(s, font2);
        }
        return font2;
    }
    
    public Font getFont(final String s) {
        return this.getFont(s, new Font("TimesRoman", 12, 0));
    }
    
    public URL getResource(final Object o, final String s) {
        final String string = this.getString(s);
        if (string != null) {
            return o.getClass().getResource(string);
        }
        return null;
    }
}
