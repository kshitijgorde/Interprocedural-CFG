import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class DirectiveManager
{
    public static final String VERSION = "1.2";
    public static final String[] directives;
    private TextScroll applet;
    
    public DirectiveManager(final TextScroll applet) {
        this.applet = applet;
    }
    
    private static Color getColorFromString(final String s) {
        if (s == null) {
            return null;
        }
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(s.substring(0, s.indexOf(",")).trim());
            int2 = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(",")).trim());
            int3 = Integer.parseInt(s.substring(s.lastIndexOf(",") + 1).trim());
        }
        catch (NumberFormatException ex) {
            return null;
        }
        try {
            return new Color(int1, int2, int3);
        }
        catch (IllegalArgumentException ex2) {
            return null;
        }
    }
    
    public boolean performDirective(String substring) {
        if (substring.startsWith("^^")) {
            substring = substring.substring(2, substring.length());
        }
        if (substring.indexOf(40) < 0) {
            System.err.println("Error: not a directive: " + substring);
            return false;
        }
        if (substring.indexOf(41) < 0) {
            System.err.println("Error: not a directive: " + substring);
            return false;
        }
        final String trim = substring.substring(0, substring.indexOf(40)).trim();
        if (!supportedDirective(trim)) {
            return false;
        }
        final String substring2 = substring.substring(substring.indexOf(40) + 1, substring.indexOf(41));
        if (trim.equalsIgnoreCase("setLeftBackgroundColor")) {
            this.applet.setLeftBackgroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftForegroundColor")) {
            this.applet.setLeftForegroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightBackgroundColor")) {
            this.applet.setRightBackgroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightForegroundColor")) {
            this.applet.setRightForegroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setBackgroundColor")) {
            this.applet.setBackgroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setForegroundColor")) {
            this.applet.setForegroundColor(substring2);
        }
        else if (trim.equalsIgnoreCase("setSpeed")) {
            this.applet.setSpeed(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftText")) {
            this.applet.setLeftText(substring2);
        }
        else if (trim.equalsIgnoreCase("pause")) {
            if (substring2.length() > 0) {
                this.applet.pause(substring2);
            }
            else {
                this.applet.pause();
            }
        }
        else if (trim.equalsIgnoreCase("setLeftFontFace")) {
            this.applet.setLeftFontFace(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightFontFace")) {
            this.applet.setRightFontFace(substring2);
        }
        else if (trim.equalsIgnoreCase("setFontFace")) {
            this.applet.setFontFace(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftFontSize")) {
            this.applet.setLeftFontSize(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightFontSize")) {
            this.applet.setRightFontSize(substring2);
        }
        else if (trim.equalsIgnoreCase("setFontSize")) {
            this.applet.setFontSize(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftCenter")) {
            this.applet.setLeftCenter(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightCenter")) {
            this.applet.setRightCenter(substring2);
        }
        else if (trim.equalsIgnoreCase("setCenter")) {
            this.applet.setCenter(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftBold")) {
            this.applet.setLeftBold(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightBold")) {
            this.applet.setRightBold(substring2);
        }
        else if (trim.equalsIgnoreCase("setBold")) {
            this.applet.setBold(substring2);
        }
        else if (trim.equalsIgnoreCase("setLeftItalic")) {
            this.applet.setLeftItalic(substring2);
        }
        else if (trim.equalsIgnoreCase("setRightItalic")) {
            this.applet.setRightItalic(substring2);
        }
        else if (trim.equalsIgnoreCase("setItalic")) {
            this.applet.setItalic(substring2);
        }
        else if (trim.equalsIgnoreCase("setInset")) {
            this.applet.setInset(substring2);
        }
        else {
            if (!trim.equalsIgnoreCase("setURL")) {
                return false;
            }
            this.applet.setURL(substring2);
        }
        return true;
    }
    
    public static boolean supportedDirective(final String s) {
        for (int i = 0; i < DirectiveManager.directives.length; ++i) {
            if (DirectiveManager.directives[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        directives = new String[] { "setLeftBackgroundColor", "setLeftForegroundColor", "setRightBackgroundColor", "setRightForegroundColor", "setBackgroundColor", "setForegroundColor", "setLeftText", "setSpeed", "pause", "setRightFontFace", "setLeftFontFace", "setFontFace", "setLeftFontSize", "setRightFontSize", "setFontSize", "setLeftCenter", "setRightCenter", "setCenter", "setLeftBold", "setRightBold", "setBold", "setLeftItalic", "setRightItalic", "setItalic", "setInset", "setURL" };
    }
}
