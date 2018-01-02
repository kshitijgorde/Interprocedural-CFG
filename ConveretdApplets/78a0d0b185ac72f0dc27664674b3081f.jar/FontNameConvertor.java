import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class FontNameConvertor
{
    public static String sansSerif() {
        if (fontNameExists("SansSerif")) {
            return "SansSerif";
        }
        if (fontNameExists("Arial")) {
            return "Arial";
        }
        if (fontNameExists("Helvetica")) {
            return "Helvetica";
        }
        if (fontNameExists("Geneva")) {
            return "Geneva";
        }
        return "Dialog";
    }
    
    public static String serif() {
        if (fontNameExists("Serif")) {
            return "Serif";
        }
        if (fontNameExists("TimesRoman")) {
            return "TimesRoman";
        }
        if (fontNameExists("Times Roman")) {
            return "Times Roman";
        }
        return "Dialog";
    }
    
    public static String monospaced() {
        if (fontNameExists("Monospaced")) {
            return "Monospaced";
        }
        if (fontNameExists("Courier")) {
            return "Courier";
        }
        if (fontNameExists("Monaco")) {
            return "Monaco";
        }
        return "Dialog";
    }
    
    public static String symbol() {
        if (fontNameExists("Symbol")) {
            return "Symbol";
        }
        return "Dialog";
    }
    
    protected static boolean fontNameExists(final String fontName) {
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        for (int j = 0; j < fontList.length; ++j) {
            if (fontName.equals(fontList[j])) {
                return true;
            }
        }
        return false;
    }
}
