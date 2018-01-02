// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Font;

class CFontCache
{
    static CMRUTable cFontCache;
    
    static final Font getFont(final CFormatInfo cFormatInfo) {
        Font font = (Font)CFontCache.cFontCache.get(cFormatInfo);
        if (font == null) {
            final int n = (cFormatInfo.isBold() ? 1 : 0) | (cFormatInfo.isItalic() ? 2 : 0);
            final int pointSize = cFormatInfo.getPointSize();
            font = new Font(translateFaceNameIn(cFormatInfo.getFaceName()), n, pointSize + pointSize / 4);
            CFontCache.cFontCache.put(cFormatInfo, font);
        }
        return font;
    }
    
    static final String translateFaceNameIn(final String s) {
        String s2 = s;
        if (s != null && s.length() > 0) {
            final String lowerCase = s.toLowerCase();
            if (s.indexOf("Times", 0) != -1 || lowerCase.equals("serif")) {
                s2 = "TimesRoman";
            }
            else if (lowerCase.indexOf("monospace", 0) != -1) {
                s2 = "Courier";
            }
            else if (lowerCase.equals("sansserif") || lowerCase.equals("sans serif")) {
                s2 = "Helvetica";
            }
        }
        return s2;
    }
    
    static {
        CFontCache.cFontCache = new CMRUTable(6);
    }
}
