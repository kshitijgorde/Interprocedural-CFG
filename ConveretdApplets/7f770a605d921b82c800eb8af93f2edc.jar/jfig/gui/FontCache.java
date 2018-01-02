// 
// Decompiled by Procyon v0.5.30
// 

package jfig.gui;

import java.io.InputStream;
import java.awt.FontMetrics;
import java.awt.Font;
import jfig.utils.SetupManager;
import java.util.Hashtable;
import java.awt.Toolkit;

public class FontCache
{
    private static FontCache _fontCache;
    private static Toolkit toolkit;
    public static final String[] postscriptFontNames;
    public static final String[] cmFontNames;
    public static final float[] postscriptFontMetrics;
    public static final String[] texFontNames;
    public static String[][] defaultFontMapping;
    private Hashtable fontTable;
    private Hashtable fontMetricsTable;
    private Hashtable cmFontsTable;
    private ComputerModernFontsManager cmfManager;
    private boolean debug;
    private String[] fontDescriptions;
    private String[] fontNames;
    private int[] fontStyles;
    private static /* synthetic */ Class class$Ljfig$gui$FontCache;
    
    public static FontCache getFontCache() {
        if (FontCache._fontCache == null) {
            FontCache._fontCache = new FontCache();
        }
        return FontCache._fontCache;
    }
    
    public void flushFontCache() {
        this.fontTable = new Hashtable();
        this.fontMetricsTable = new Hashtable();
    }
    
    public void set_debug(final boolean debug) {
        this.debug = debug;
    }
    
    public void initializeFontDescriptions() {
        for (int i = 0; i < FontCache.defaultFontMapping.length; ++i) {
            this.fontDescriptions[i] = SetupManager.getProperty(FontCache.defaultFontMapping[i][0], FontCache.defaultFontMapping[i][1]);
        }
    }
    
    public void dumpFontDescriptions() {
        System.out.println("-#- jfig.gui.FontCache.dumpFontAliasNames: ");
        for (int i = 0; i < this.fontDescriptions.length; ++i) {
            System.out.println("" + i + " " + this.fontDescriptions[i] + " " + this.fontNames[i] + " " + this.fontStyles[i]);
        }
    }
    
    public void parseFontDescriptions() {
        for (int i = 0; i < this.fontDescriptions.length; ++i) {
            try {
                final String trim = this.fontDescriptions[i].trim();
                if (trim.indexOf(44) > 0) {
                    this.fontNames[i] = trim.substring(0, trim.indexOf(44));
                }
                else {
                    this.fontNames[i] = trim;
                }
                if (trim.indexOf(",bolditalic") > 0) {
                    this.fontStyles[i] = 3;
                }
                else if (trim.indexOf(",italic") > 0) {
                    this.fontStyles[i] = 2;
                }
                else if (trim.indexOf(",bold") > 0) {
                    this.fontStyles[i] = 1;
                }
                else {
                    this.fontStyles[i] = 0;
                }
            }
            catch (Exception ex) {
                System.err.println("-E- could not parse font description: '" + this.fontDescriptions[i] + "', substituting Courier");
                this.fontNames[i] = "Courier";
                this.fontStyles[i] = 0;
            }
        }
    }
    
    public int getFontIndex(final String s) {
        for (int i = 0; i < this.fontNames.length; ++i) {
            if (this.fontNames[i].equals(s)) {
                return i;
            }
        }
        return 12;
    }
    
    public void put(final Object o, final int n, final int n2) {
        Font derivedFont;
        try {
            final Font value = this.cmFontsTable.get(this.fontNames[n]);
            if (value != null) {
                derivedFont = this.cmfManager.getDerivedFont(value, n2);
            }
            else {
                derivedFont = new Font(this.fontNames[n], this.fontStyles[n], n2);
            }
            if (this.debug) {
                System.out.println("-#- FontCache.put: " + n + " " + this.fontNames[n] + " " + this.fontStyles[n] + " " + n2 + " " + derivedFont.toString());
            }
        }
        catch (Exception ex) {
            derivedFont = new Font("Courier", 0, n2);
            System.err.println("-E- FontCache.put: " + this.fontNames[n] + " not found, using Courier instead.");
        }
        this.fontTable.put(o, derivedFont);
        this.fontMetricsTable.put(o, this.getFontMetrics(derivedFont));
    }
    
    public FontMetrics getFontMetrics(final int n, final int n2) {
        final Integer n3 = new Integer((n << 16) + n2);
        final FontMetrics fontMetrics = this.fontMetricsTable.get(n3);
        if (fontMetrics != null) {
            return fontMetrics;
        }
        this.put(n3, n, n2);
        return (FontMetrics)this.fontMetricsTable.get(n3);
    }
    
    public FontMetrics getFontMetrics(final Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font);
    }
    
    public Font getFont(final int n, final int n2) {
        final Integer n3 = new Integer((n << 16) + n2);
        final Font font = this.fontTable.get(n3);
        if (font != null) {
            return font;
        }
        this.put(n3, n, n2);
        return (Font)this.fontTable.get(n3);
    }
    
    public void loadComputerModernFonts() {
        if (this == null) {
            throw null;
        }
        (this.cmfManager = new ComputerModernFontsManager()).loadComputerModernFonts();
    }
    
    public static void main(final String[] array) {
        System.out.println("FontCache test started...");
        SetupManager.loadGlobalProperties("jfig/jfig.cnf");
        final FontCache fontCache = getFontCache();
        System.out.println("Font examples: " + fontCache.getFont(2, 12) + "," + fontCache.getFont(12, 24) + "," + fontCache.getFont(17, 17));
        fontCache.dumpFontDescriptions();
        System.out.println("cmmi10 font index: " + fontCache.getFontIndex("cmmi10"));
        System.out.println("cmsy10 font index: " + fontCache.getFontIndex("cmsy10"));
        System.exit(0);
    }
    
    static /* synthetic */ void access$0(final Class class$Ljfig$gui$FontCache) {
        FontCache.class$Ljfig$gui$FontCache = class$Ljfig$gui$FontCache;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private FontCache() {
        this.cmfManager = null;
        this.debug = false;
        if (this.debug) {
            System.out.println("-#- FontCache<init>...");
        }
        this.fontTable = new Hashtable();
        this.fontMetricsTable = new Hashtable();
        this.cmFontsTable = new Hashtable();
        this.fontDescriptions = new String[FontCache.defaultFontMapping.length];
        this.fontNames = new String[FontCache.defaultFontMapping.length];
        this.fontStyles = new int[FontCache.defaultFontMapping.length];
        this.initializeFontDescriptions();
        this.parseFontDescriptions();
        if (System.getProperty("java.version").compareTo("1.3") >= 0) {
            this.loadComputerModernFonts();
        }
    }
    
    static {
        FontCache._fontCache = null;
        FontCache.toolkit = Toolkit.getDefaultToolkit();
        postscriptFontNames = new String[] { "Times Roman", "Times Italic", "Times Bold", "Times Bold Italic", "AvantGarde Book", "AvantGarde Book Oblique", "AvantGarde Demi", "AvantGarde Demi Oblique", "Bookman Light", "Bookman Light Italic", "Bookman Demi", "Bookman Demi Italic", "Courier", "Courier Oblique", "Courier Bold", "Courier Bold Oblique", "Helvetica", "Helvetica Oblique", "Helvetica Bold", "Helvetica Bold Oblique", "Helvetica Narrow", "Helvetica Narrow Oblique", "Helvetica Narrow Bold", "Helvetica Narrow Bold Oblique", "New Century Schoolbook Roman", "New Century Schoolbook Italic", "New Century Schoolbook Bold", "New Century Schoolbook Bold Italic", "Palatino Roman", "Palatino Italic", "Palatino Bold", "Palatino Bold Italic", "Symbol", "Zapf Chancery Medium Italic", "Zapf Dingbats" };
        cmFontNames = new String[] { "cmr", "cmti", "cmbx", "cmsl", "cmss", "cmssi", "cmtt", "cmitt", "cmsy", "cmbsy", "cmmi", "cmmib", "cmex", "msam", "msbm" };
        postscriptFontMetrics = new float[] { 7.4f, 7.25f, 7.8f, 7.45f, 8.68f, 8.68f, 8.72f, 8.72f, 8.8f, 8.6f, 9.45f, 9.6f, 10.5f, 10.5f, 10.5f, 10.5f, 8.0f, 8.0f, 8.6f, 8.6f, 6.52f, 6.52f, 7.05f, 7.05f, 8.3f, 8.17f, 9.4f, 9.1f, 8.1f, 7.25f, 8.37f, 8.0f, 8.6f, 6.4f, 9.3f };
        texFontNames = new String[] { "Default font", "Roman", "Bold", "Italic", "Sans Serif", "Typewriter" };
        final String[][] defaultFontMapping = new String[49][];
        defaultFontMapping[0] = new String[] { "jfig.gui.FontCache.Times-Roman", "TimesRoman,plain" };
        defaultFontMapping[1] = new String[] { "jfig.gui.FontCache.Times-Italic", "TimesRoman,italic" };
        defaultFontMapping[2] = new String[] { "jfig.gui.FontCache.Times-Bold", "TimesRoman,bold" };
        defaultFontMapping[3] = new String[] { "jfig.gui.FontCache.Times-BoldItalic", "TimesRoman,bolditalic" };
        defaultFontMapping[4] = new String[] { "jfig.gui.FontCache.AvantGarde-Book", "Helvetica,plain" };
        defaultFontMapping[5] = new String[] { "jfig.gui.FontCache.AvantGarde-BookOblique", "Helvetica,italic" };
        defaultFontMapping[6] = new String[] { "jfig.gui.FontCache.AvantGarde-Demi", "Helvetica,bold" };
        defaultFontMapping[7] = new String[] { "jfig.gui.FontCache.AvantGarde-DemiOblique", "Helvetica,bolditalic" };
        defaultFontMapping[8] = new String[] { "jfig.gui.FontCache.Bookman-Light", "TimesRoman,plain" };
        defaultFontMapping[9] = new String[] { "jfig.gui.FontCache.Bookman-LightItalic", "TimesRoman,italic" };
        defaultFontMapping[10] = new String[] { "jfig.gui.FontCache.Bookman-Demi", "TimesRoman,bold" };
        defaultFontMapping[11] = new String[] { "jfig.gui.FontCache.Bookman-DemiItalic", "TimesRoman,bolditalic" };
        defaultFontMapping[12] = new String[] { "jfig.gui.FontCache.Courier", "Courier,plain" };
        defaultFontMapping[13] = new String[] { "jfig.gui.FontCache.Courier-Oblique", "Courier,italic" };
        defaultFontMapping[14] = new String[] { "jfig.gui.FontCache.Courier-Bold", "Courier,bold" };
        defaultFontMapping[15] = new String[] { "jfig.gui.FontCache.Courier-BoldOblique", "Courier,bolditalic" };
        defaultFontMapping[16] = new String[] { "jfig.gui.FontCache.Helvetica", "Helvetica,plain" };
        defaultFontMapping[17] = new String[] { "jfig.gui.FontCache.Helvetica-Oblique", "Helvetica,italic" };
        defaultFontMapping[18] = new String[] { "jfig.gui.FontCache.Helvetica-Bold", "Helvetica,bold" };
        defaultFontMapping[19] = new String[] { "jfig.gui.FontCache.Helvetica-BoldOblique", "Helvetica,bolditalic" };
        defaultFontMapping[20] = new String[] { "jfig.gui.FontCache.Helvetica-Narrow", "Helvetica,plain" };
        defaultFontMapping[21] = new String[] { "jfig.gui.FontCache.Helvetica-Narrow-Oblique", "Helvetica,italic" };
        defaultFontMapping[22] = new String[] { "jfig.gui.FontCache.Helvetica-Narrow-Bold", "Helvetica,bold" };
        defaultFontMapping[23] = new String[] { "jfig.gui.FontCache.Helvetica-Narrow-BoldOblique", "Helvetica,bolditalic" };
        defaultFontMapping[24] = new String[] { "jfig.gui.FontCache.NewCenturySchlbk-Roman", "TimesRoman,plain" };
        defaultFontMapping[25] = new String[] { "jfig.gui.FontCache.NewCenturySchlbk-Italic", "TimesRoman,italic" };
        defaultFontMapping[26] = new String[] { "jfig.gui.FontCache.NewCenturySchlbk-Bold", "TimesRoman,bold" };
        defaultFontMapping[27] = new String[] { "jfig.gui.FontCache.NewCenturySchlbk-BoldItalic", "TimesRoman,bolditalic" };
        defaultFontMapping[28] = new String[] { "jfig.gui.FontCache.Palatino-Roman", "TimesRoman,plain" };
        defaultFontMapping[29] = new String[] { "jfig.gui.FontCache.Palatino-Italic", "TimesRoman,italic" };
        defaultFontMapping[30] = new String[] { "jfig.gui.FontCache.Palatino-Bold", "TimesRoman,bold" };
        defaultFontMapping[31] = new String[] { "jfig.gui.FontCache.Palatino-BoldItalic", "TimesRoman,bolditalic" };
        defaultFontMapping[32] = new String[] { "jfig.gui.FontCache.Symbol", "SansSerif,plain" };
        defaultFontMapping[33] = new String[] { "jfig.gui.FontCache.ZapfChancery-MediumItalic", "TimesRoman,italic" };
        defaultFontMapping[34] = new String[] { "jfig.gui.FontCache.ZapfDingbats", "Courier,plain" };
        defaultFontMapping[35] = new String[] { "jfig.gui.FontCache.cmr10", "cmr10" };
        defaultFontMapping[36] = new String[] { "jfig.gui.FontCache.cmti10", "cmti10" };
        defaultFontMapping[37] = new String[] { "jfig.gui.FontCache.cmbx10", "cmbx10" };
        defaultFontMapping[38] = new String[] { "jfig.gui.FontCache.cmss10", "cmss10" };
        defaultFontMapping[39] = new String[] { "jfig.gui.FontCache.cmsl10", "cmsl10" };
        defaultFontMapping[40] = new String[] { "jfig.gui.FontCache.cmtt10", "cmtt10" };
        defaultFontMapping[41] = new String[] { "jfig.gui.FontCache.cmitt10", "cmtt10" };
        defaultFontMapping[42] = new String[] { "jfig.gui.FontCache.cmmi10", "cmmi10" };
        defaultFontMapping[43] = new String[] { "jfig.gui.FontCache.cmmib10", "cmmib10" };
        defaultFontMapping[44] = new String[] { "jfig.gui.FontCache.cmsy10", "cmsy10" };
        defaultFontMapping[45] = new String[] { "jfig.gui.FontCache.cmbsy10", "cmbsy10" };
        defaultFontMapping[46] = new String[] { "jfig.gui.FontCache.cmex10", "cmex10" };
        defaultFontMapping[47] = new String[] { "jfig.gui.FontCache.msam10", "msam10" };
        defaultFontMapping[48] = new String[] { "jfig.gui.FontCache.msbm10", "msbm10" };
        FontCache.defaultFontMapping = defaultFontMapping;
    }
    
    class ComputerModernFontsManager
    {
        public void loadComputerModernFonts() {
            for (int i = 0; i < FontCache.cmFontNames.length; ++i) {
                try {
                    final String string = "/jfig/fonts/" + FontCache.cmFontNames[i] + "10.ttf";
                    Class clazz;
                    if (FontCache.class$Ljfig$gui$FontCache != null) {
                        clazz = FontCache.class$Ljfig$gui$FontCache;
                    }
                    else {
                        FontCache.access$0(clazz = FontCache.class$("jfig.gui.FontCache"));
                    }
                    final InputStream resourceAsStream = clazz.getResourceAsStream(string);
                    if (resourceAsStream != null) {
                        final Font font = Font.createFont(0, resourceAsStream);
                        FontCache.this.cmFontsTable.put(FontCache.cmFontNames[i] + "10", font);
                        if (FontCache.this.debug) {
                            System.out.println("-#- got CM font: " + font);
                        }
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        
        public Font getDerivedFont(final Font font, final int n) {
            return font.deriveFont(1.0f * n);
        }
    }
}
