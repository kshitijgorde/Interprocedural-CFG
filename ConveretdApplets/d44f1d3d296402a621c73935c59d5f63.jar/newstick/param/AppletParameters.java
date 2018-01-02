// 
// Decompiled by Procyon v0.5.30
// 

package newstick.param;

import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public class AppletParameters
{
    private Applet applet;
    
    public URL paramToURL(final String s, final String s2) {
        URL url;
        try {
            url = new URL(s2);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return this.paramToURL(s, url);
    }
    
    public URL paramToURL(final String s, final URL url) {
        final String parameter = this.applet.getParameter(s);
        URL url2 = url;
        if (parameter != null) {
            try {
                url2 = new URL(parameter);
            }
            catch (MalformedURLException ex) {}
        }
        return url2;
    }
    
    public boolean paramToBoolean(final String s, final String s2) {
        return this.paramToBoolean(s, this.toBoolean(s2));
    }
    
    public boolean paramToBoolean(final String s, final boolean b) {
        boolean b2 = b;
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            b2 = (parameter.toLowerCase().compareTo("true") == 0 || parameter.toLowerCase().compareTo("yes") == 0);
        }
        return b2;
    }
    
    private int toInt(final String s) {
        return Integer.parseInt(s);
    }
    
    public Font paramToFont(final String s, final String s2) {
        return this.paramToFont(s, this.toFont(s2, ","));
    }
    
    public Font paramToFont(final String s, final Font font) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return this.toFont(parameter, ",");
        }
        return font;
    }
    
    public AppletParameters(final Applet applet) {
        this.applet = applet;
    }
    
    private boolean toBoolean(final String s) {
        return new Boolean(s);
    }
    
    public Font toFont(final String s, final String s2) {
        String trim = "Tahoma";
        int n = 0;
        int int1 = 10;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.hasMoreTokens()) {
            trim = stringTokenizer.nextToken().trim();
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), " ");
                int n2 = 1;
                while (stringTokenizer2.hasMoreTokens()) {
                    if (n2 != 0) {
                        n = 0;
                        n2 = 0;
                    }
                    final String nextToken = stringTokenizer2.nextToken();
                    if (nextToken.trim().toLowerCase().compareTo("bold") == 0) {
                        ++n;
                    }
                    else {
                        if (nextToken.trim().toLowerCase().compareTo("italic") != 0) {
                            continue;
                        }
                        n += 2;
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    int1 = this.toInt(stringTokenizer.nextToken().trim());
                }
            }
        }
        return new Font(trim, n, int1);
    }
    
    public Color paramToColor(final String s, final String s2) {
        return this.paramToColor(s, this.toColor(s2));
    }
    
    public Color paramToColor(final String s, final Color color) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null && parameter.length() == 7) {
            return this.toColor(parameter.substring(1));
        }
        return color;
    }
    
    public int paramToInt(final String s, final String s2) {
        return this.paramToInt(s, this.toInt(s2));
    }
    
    public int paramToInt(final String s, final int n) {
        final String parameter = this.applet.getParameter(s);
        int int1 = n;
        if (parameter != null) {
            try {
                int1 = this.toInt(parameter);
            }
            catch (Exception ex) {}
        }
        return int1;
    }
    
    public String paramToString(final String s, final String s2) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    private Color toColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
}
