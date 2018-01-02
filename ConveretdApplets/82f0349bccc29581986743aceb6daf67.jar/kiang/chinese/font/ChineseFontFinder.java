// 
// Decompiled by Procyon v0.5.30
// 

package kiang.chinese.font;

import java.util.List;
import java.util.ArrayList;
import java.awt.GraphicsEnvironment;
import java.awt.Font;

public class ChineseFontFinder
{
    private static final String[] PREFERRED_FONTS;
    private static final String SIMPLIFIED_CHARACTERS = "\u8fd9\u6765\u56fd\u4e2a\u6c49";
    private static final String TRADITIONAL_CHARACTERS = "\u9019\u4f86\u570b\u500b\u6f22";
    public static final int FONT_SIZE = 24;
    
    static {
        PREFERRED_FONTS = new String[] { "SimSun", "STHeiti", "Bitstream Cyberbit" };
    }
    
    public static Font getChineseFont() {
        Font currentChoice = null;
        boolean currentIsSimplified = false;
        boolean currentIsTraditional = false;
        final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for (int i = 0; i < allFonts.length; ++i) {
            final Font candidateFont = allFonts[i];
            boolean useCandidate = false;
            final boolean candidateIsSimplified = isSimplifiedFont(candidateFont);
            final boolean candidateIsTraditional = isTraditionalFont(candidateFont);
            if (isPreferredFont(candidateFont)) {
                currentChoice = candidateFont;
                break;
            }
            if (currentChoice == null) {
                if (candidateIsSimplified || candidateIsTraditional) {
                    useCandidate = true;
                }
            }
            else if (candidateIsTraditional && candidateIsSimplified && (!currentIsTraditional || !currentIsSimplified)) {
                useCandidate = true;
            }
            if (useCandidate) {
                currentChoice = candidateFont;
                currentIsSimplified = candidateIsSimplified;
                currentIsTraditional = candidateIsTraditional;
            }
        }
        if (currentChoice != null) {
            currentChoice = currentChoice.deriveFont(0, 24.0f);
        }
        return currentChoice;
    }
    
    public static Font[] getAllChineseFonts() {
        final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        final List chineseFontList = new ArrayList();
        for (int i = 0; i < allFonts.length; ++i) {
            if (isTraditionalFont(allFonts[i]) || isSimplifiedFont(allFonts[i])) {
                chineseFontList.add(allFonts[i]);
            }
        }
        final Font[] chineseFonts = new Font[chineseFontList.size()];
        return chineseFontList.toArray(chineseFonts);
    }
    
    public static boolean isSimplifiedFont(final Font font) {
        return canDisplay(font, "\u8fd9\u6765\u56fd\u4e2a\u6c49");
    }
    
    public static boolean isTraditionalFont(final Font font) {
        return canDisplay(font, "\u9019\u4f86\u570b\u500b\u6f22");
    }
    
    private static boolean canDisplay(final Font font, final String testString) {
        try {
            return -1 == font.canDisplayUpTo(testString);
        }
        catch (Exception e) {
            System.out.println("JDK error with " + font.getName());
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean isPreferredFont(final Font font) {
        for (int i = 0; i < ChineseFontFinder.PREFERRED_FONTS.length; ++i) {
            if (ChineseFontFinder.PREFERRED_FONTS[i].equals(font.getFontName()) && isSimplifiedFont(font) && isTraditionalFont(font)) {
                return true;
            }
        }
        return false;
    }
}
