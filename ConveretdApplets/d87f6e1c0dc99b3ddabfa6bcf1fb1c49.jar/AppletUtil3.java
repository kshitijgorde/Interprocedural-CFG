import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class AppletUtil3
{
    Applet ap;
    
    AppletUtil3(final Applet ap) {
        this.ap = ap;
    }
    
    AppletUtil3() {
    }
    
    public Image getImage(final String s) {
        if (s != null) {
            try {
                final Image image = this.ap.getImage(new URL(this.ap.getDocumentBase(), s));
                if (image != null) {
                    final MediaTracker mediaTracker = new MediaTracker(this.ap);
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForID(0);
                    return image;
                }
                return null;
            }
            catch (Exception ex) {
                System.out.println(ex);
                return null;
            }
            return null;
        }
        return null;
    }
    
    public Font makeFont(final String s, final String s2, final String s3) {
        final String s4 = (s == null) ? "Helvetica" : s;
        int n = 0;
        if (s2 != null) {
            if (s2.toLowerCase().indexOf("bold") != -1) {
                ++n;
            }
            if (s2.toLowerCase().indexOf("italic") != -1) {
                n += 2;
            }
        }
        return new Font(s4, n, (s3 != null) ? this.getRandom(s3) : 12);
    }
    
    public Font getFont() {
        return this.makeFont(this.ap.getParameter("FONTNAME"), this.ap.getParameter("FONTSTYLE"), this.ap.getParameter("FONTSIZE"));
    }
    
    public int getRandom(final String s) {
        if (s == null || s.trim().equals("")) {
            return 0;
        }
        if (s.indexOf(":") != -1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
            for (int n = (int)(Math.random() * stringTokenizer.countTokens()), i = 0; i < n; ++i) {
                stringTokenizer.nextToken();
            }
            return this.getRandom(stringTokenizer.nextToken().trim());
        }
        if (s.indexOf("-") != -1) {
            return (int)(Math.random() * this.getRandom((s.indexOf("-") != -1) ? s.substring(s.indexOf("-") + "-".length()) : s) + this.getRandom((s.indexOf("-") != -1) ? s.substring(0, s.indexOf("-")) : s));
        }
        return Integer.parseInt(s.trim());
    }
    
    public String pre(final String s, final String s2) {
        if (s.indexOf(s2) != -1) {
            return s.substring(0, s.indexOf(s2));
        }
        return s;
    }
    
    public String post(final String s, final String s2) {
        if (s.indexOf(s2) != -1) {
            return s.substring(s.indexOf(s2) + s2.length());
        }
        return s;
    }
    
    public String replaceAll(String string, final String s, final String s2) {
        if (string.indexOf(s) != -1) {
            string = ((string.indexOf(s) != -1) ? string.substring(0, string.indexOf(s)) : string) + s2 + this.replaceAll((string.indexOf(s) != -1) ? string.substring(string.indexOf(s) + s.length()) : string, s, s2);
            return string;
        }
        return string;
    }
    
    public Color makeColor(final String s) {
        return this.makeColor(s, Color.black);
    }
    
    public Color makeColor(final String s, final Color color) {
        if (s != null && s.indexOf(",") != -1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,");
            return new Color(this.getRandom(stringTokenizer.nextToken().trim()), this.getRandom(stringTokenizer.nextToken().trim()), this.getRandom(stringTokenizer.nextToken().trim()));
        }
        return color;
    }
}
