// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;

public class ConfigurationImpl implements Configuration
{
    static final String MISSED_SOURCE = "Internet Anagram Server";
    static final String MISSED_TARGET = "I, Rearrangement Servant";
    private final ConfigurationGate myGate;
    private AnagramStringsSupplier myStringsSupplier;
    
    public ConfigurationImpl(final ConfigurationGate myGate) {
        this.myGate = myGate;
    }
    
    public Color getBackgroundColor() {
        return parseColor(this.safeGetParameter("backgroundColor"), Color.WHITE);
    }
    
    public String getFontName() {
        return this.safeGetParameter("font");
    }
    
    public int getFontSize() {
        return parseNotNegativeInteger(this.safeGetParameter("fontSize"), 32);
    }
    
    public int getPauseInMillisecs() {
        return parseNotNegativeInteger(this.safeGetParameter("pauseTimeMS"), 1000);
    }
    
    public Color getShadowColor() {
        return parseColor(this.safeGetParameter("shadowColor"), Color.GRAY);
    }
    
    public AnagramStringsSupplier getStringSupplier() {
        synchronized (this) {
            if (this.myStringsSupplier == null) {
                this.myStringsSupplier = this.createStringsSupplier();
            }
        }
        return this.myStringsSupplier;
    }
    
    public int getSymbolAnimationTimeMillis() {
        return parseNotNegativeInteger(this.safeGetParameter("symbolTimeMS"), 1000);
    }
    
    public int getSymbolDelayMillis() {
        return parseNotNegativeInteger(this.safeGetParameter("symbolDelayMS"), 50);
    }
    
    public int getSymbolFramesCount() {
        return parseNotNegativeInteger(this.safeGetParameter("symbolFrames"), 20);
    }
    
    public int getBannerTimeMS() {
        return 2000;
    }
    
    public Color getTextColor() {
        return parseColor(this.safeGetParameter("textColor"), Color.BLACK);
    }
    
    public boolean hasShadow() {
        return Boolean.valueOf(this.safeGetParameter("shadow"));
    }
    
    public int getRoundedCornersRadius() {
        return parseNotNegativeInteger(this.safeGetParameter("roundedCornersRadius"), 0);
    }
    
    public boolean isBold() {
        return this.getBooleanParameter("bold", false);
    }
    
    public boolean isItalic() {
        return this.getBooleanParameter("italic", false);
    }
    
    public boolean isEndless() {
        return this.getBooleanParameter("endless", true);
    }
    
    public boolean isSiteAuthorized(final URL url) {
        if (url == null) {
            return false;
        }
        final String string = url.toString();
        boolean b = false;
        for (int n = 0; !b && n < Configuration.ALLOWED_DOCUMENT_URL_PREFIXES.length; b = (string.indexOf(Configuration.ALLOWED_DOCUMENT_URL_PREFIXES[n]) > -1), ++n) {}
        return b;
    }
    
    public URL getRedirectOnClickURL() {
        URL url = null;
        if ("http://wordsmith.org/anagram" != null) {
            try {
                url = new URL("http://wordsmith.org/anagram");
            }
            catch (MalformedURLException ex) {
                url = null;
            }
        }
        return url;
    }
    
    public boolean shouldReverse() {
        return true;
    }
    
    public double getVerticalAnimationGap() {
        return parseNotNegativeDouble(this.safeGetParameter("verticalGap"), 0.5);
    }
    
    private static Color parseColor(String s, final Color color) {
        s = stripQuotes(s);
        if (s == null) {
            return color;
        }
        if (s.startsWith("#")) {
            s = "0x" + s.substring("#".length());
        }
        try {
            return Color.decode(s);
        }
        catch (NumberFormatException ex) {
            return color;
        }
    }
    
    private static int parseNotNegativeInteger(String stripQuotes, final int n) {
        stripQuotes = stripQuotes(stripQuotes);
        int int1;
        try {
            int1 = Integer.parseInt(stripQuotes);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return (int1 >= 0) ? int1 : n;
    }
    
    private static double parseNotNegativeDouble(String stripQuotes, final double n) {
        stripQuotes = stripQuotes(stripQuotes);
        if (stripQuotes == null) {
            return n;
        }
        double double1;
        try {
            double1 = Double.parseDouble(stripQuotes);
        }
        catch (NumberFormatException ex) {
            double1 = n;
        }
        catch (NullPointerException ex2) {
            double1 = n;
        }
        return (double1 >= 0.0) ? double1 : n;
    }
    
    private String safeGetParameter(final String s) {
        return stripQuotes(this.myGate.getParameter(s));
    }
    
    private static String stripQuotes(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.startsWith("\"")) {
            s = s.substring("\"".length()).trim();
        }
        if (s.endsWith("\"")) {
            s = s.substring(0, s.length() - "\"".length()).trim();
        }
        return s;
    }
    
    private boolean getBooleanParameter(final String s, final boolean b) {
        final String safeGetParameter = this.safeGetParameter(s);
        return (safeGetParameter == null) ? b : Boolean.valueOf(safeGetParameter);
    }
    
    private AnagramStringsSupplier createStringsSupplier() {
        final boolean booleanParameter = this.getBooleanParameter("remoteAnagram", false);
        System.err.println("IsRemote: " + booleanParameter);
        return booleanParameter ? new RemoteAnagramsLoader() : new AnagramStringsSupplier.Adapter(this._getSourceString(), this._getTargetString());
    }
    
    private String _getSourceString() {
        final String safeGetParameter = this.safeGetParameter("sourceString");
        return (safeGetParameter == null) ? "Internet Anagram Server" : safeGetParameter;
    }
    
    private String _getTargetString() {
        String s = this.safeGetParameter("targetString");
        if (s == null) {
            s = this.safeGetParameter("sourceString");
        }
        return (s == null) ? "I, Rearrangement Servant" : s;
    }
}
