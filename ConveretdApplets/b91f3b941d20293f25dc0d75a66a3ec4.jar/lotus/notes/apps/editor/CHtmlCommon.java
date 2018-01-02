// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CHtmlCommon
{
    CRTEditor cParent;
    protected static final String[] cEntities;
    protected static final String[] cStyles;
    protected CFormatInfo[] cCannedFormat;
    protected int[] cPresetSizes;
    private static final int[] cHaikuSizes;
    private static final int[] cV5Sizes;
    static final int cBaseIndent = 200;
    static final int cSpaceAbove = 150;
    
    CHtmlCommon(final CRTEditor cParent) {
        this.cParent = cParent;
        if (this.cParent.getBehavior() == 1) {
            this.cPresetSizes = CHtmlCommon.cHaikuSizes;
        }
        else {
            this.cPresetSizes = CHtmlCommon.cV5Sizes;
        }
        this.cCannedFormat = new CFormatInfo[15];
        for (int i = 0; i < 15; ++i) {
            this.cCannedFormat[i] = new CFormatInfo();
        }
        for (int j = 1; j <= 6; ++j) {
            this.cCannedFormat[j].setPointSize(this.cPresetSizes[6 - j]);
            this.cCannedFormat[j].setBold(true);
        }
        this.cCannedFormat[8].setPointSize(this.cPresetSizes[2]);
        this.cCannedFormat[8].setItalic(true);
        this.cCannedFormat[7].setFaceName("Courier");
    }
    
    static final int getBaseIndent() {
        return 200;
    }
    
    static final String[] getStyles() {
        return CHtmlCommon.cStyles;
    }
    
    int getClosestSize(final int n) {
        if (this.cParent.getBehavior() == 0) {
            int n2;
            for (n2 = 0; n2 < this.cPresetSizes.length - 1 && n > this.cPresetSizes[n2]; ++n2) {}
            return ++n2;
        }
        if (n <= this.cPresetSizes[0]) {
            return 1;
        }
        int i = 0;
        while (i < this.cPresetSizes.length - 1) {
            if (n >= this.cPresetSizes[i] && n <= this.cPresetSizes[i + 1]) {
                if (n - this.cPresetSizes[i] <= this.cPresetSizes[i + 1] - n) {
                    return i + 1;
                }
                return i + 2;
            }
            else {
                ++i;
            }
        }
        return this.cPresetSizes.length;
    }
    
    private CParaStyle createStyle(final CParaStyleMgr cParaStyleMgr, final String s) {
        if (cParaStyleMgr.getStyle(s) == null) {
            return cParaStyleMgr.create(s, CHtmlCommon.cStyles[0]);
        }
        return null;
    }
    
    void createHtmlStyles() {
        final CParaStyleMgr paraStyleMgr = this.cParent.getParaStyleMgr();
        this.cParent.init();
        CParaStyle cParaStyle = paraStyleMgr.getStyle(CHtmlCommon.cStyles[0]);
        if (cParaStyle == null) {
            cParaStyle = paraStyleMgr.create(CHtmlCommon.cStyles[0], null);
        }
        final CParaSettings cParaSettings = new CParaSettings();
        cParaSettings.setSpaceAbove(150);
        cParaStyle.apply(cParaSettings);
        for (int i = 1; i <= 6; ++i) {
            final CParaStyle style = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[1 + i - 1]);
            if (style != null) {
                style.apply(this.cCannedFormat[i]);
            }
        }
        final CParaStyle style2 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[8]);
        if (style2 != null) {
            style2.apply(this.cCannedFormat[8]);
        }
        final CParaStyle style3 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[12]);
        if (style3 != null) {
            final CParaSettings cParaSettings2 = new CParaSettings();
            cParaSettings2.setBullet('\u25cf');
            cParaSettings2.setFirstIndent(400);
            cParaSettings2.setRestIndent(400);
            cParaSettings2.setSpaceAbove(0);
            style3.apply(cParaSettings2);
        }
        final CParaStyle style4 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[13]);
        if (style4 != null) {
            final CParaSettings cParaSettings3 = new CParaSettings();
            cParaSettings3.setBullet('1');
            cParaSettings3.setFirstIndent(400);
            cParaSettings3.setRestIndent(400);
            cParaSettings3.setSpaceAbove(0);
            style4.apply(cParaSettings3);
        }
        this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[10]);
        final CParaStyle style5 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[11]);
        if (style5 != null) {
            final CParaSettings cParaSettings4 = new CParaSettings();
            cParaSettings4.setFirstIndent(200);
            cParaSettings4.setRestIndent(200);
            style5.apply(cParaSettings4);
        }
        final CParaStyle style6 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[7]);
        if (style6 != null) {
            final CParaSettings cParaSettings5 = new CParaSettings();
            cParaSettings5.setSpaceAbove(0);
            style6.apply(cParaSettings5);
            style6.apply(this.cCannedFormat[7]);
        }
        final CParaStyle style7 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[9]);
        if (style7 != null) {
            final CParaSettings cParaSettings6 = new CParaSettings();
            cParaSettings6.setFirstIndent(200);
            cParaSettings6.setRestIndent(200);
            cParaSettings6.setRightIndent(200);
            style7.apply(cParaSettings6);
        }
        final CParaStyle style8 = this.createStyle(paraStyleMgr, CHtmlCommon.cStyles[14]);
        if (style8 != null) {
            final CParaSettings cParaSettings7 = new CParaSettings();
            cParaSettings7.setSpaceAbove(0);
            style8.apply(cParaSettings7);
        }
    }
    
    static {
        cEntities = new String[] { "nbsp", "iexcl", "cent", "pound", "curren", "yen", "brvbar", "sect", "uml", "copy", "ordf", "laquo", "not", "shy", "reg", "macr", "deg", "plusmn", "sup2", "sup3", "acute", "micro", "para", "middot", "cedil", "sup1", "ordm", "raquo", "frac14", "frac12", "frac34", "iquest", "Agrave", "Aacute", "Acirc", "Atilde", "Auml", "Aring", "AElig", "Ccedil", "Egrave", "Eacute", "Ecirc", "Euml", "Igrave", "Iacute", "Icirc", "Iuml", "ETH", "Ntilde", "Ograve", "Oacute", "Ocirc", "Otilde", "Ouml", "times", "Oslash", "Ugrave", "Uacute", "Ucirc", "Uuml", "Yacute", "THORN", "szlig", "agrave", "aacute", "acirc", "atilde", "auml", "aring", "aelig", "ccedil", "egrave", "eacute", "ecirc", "euml", "igrave", "iacute", "icirc", "iuml", "eth", "ntilde", "ograve", "oacute", "ocirc", "otilde", "ouml", "divide", "oslash", "ugrave", "uacute", "ucirc", "uuml", "yacute", "thorn", "yuml" };
        cStyles = new String[] { "Default", "Heading 1 (H1)", "Heading 2 (H2)", "Heading 3 (H3)", "Heading 4 (H4)", "Heading 5 (H5)", "Heading 6 (H6)", "Preformatted (PRE)", "Address (ADDRESS)", "Blockquote (BLOCKQUOTE)", "Def Term (DT)", "Def Desc (DD)", "Unordered List (UL)", "Ordered List (OL)", "Division (DIV)" };
        cHaikuSizes = new int[] { 8, 10, 12, 14, 18, 24, 36 };
        cV5Sizes = new int[] { 7, 9, 11, 13, 17, 23, 36 };
    }
}
