// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Color;
import java.util.Stack;

final class CWriteHtml extends CHtmlCommon
{
    private CTextPointer cTP;
    private CTextPointer cEnd;
    private CParaSettings cPS;
    private CFormatInfo cFI;
    private CFormatInfo cDefaultFI;
    private StringBuffer cSB;
    private int cChanged;
    private int cReallyChanged;
    private int cTags;
    private CWriteHtmlTagInfo cOpenPara;
    private CWriteHtmlTagInfo cOpenStyle;
    private CWriteHtmlTagInfo cOpenList;
    private boolean cEmptyPara;
    private int cIndentLevel;
    private boolean cIndentHack;
    private char cLastChar;
    private boolean bDidDiv;
    private int cListAlign;
    private boolean bDoListAlign;
    private boolean bRealListOpen;
    private static CWriteHtmlTagInfo cATag;
    private static CWriteHtmlTagInfo cBoldTag;
    private static CWriteHtmlTagInfo cItalicTag;
    private static CWriteHtmlTagInfo cUnderlineTag;
    private static CWriteHtmlTagInfo cStrikeThroughTag;
    private static CWriteHtmlTagInfo cFontTag;
    private static CWriteHtmlTagInfo cPTag;
    private static CWriteHtmlTagInfo cULTag;
    private static CWriteHtmlTagInfo cOLTag;
    private static CWriteHtmlTagInfo cDLTag;
    private static CWriteHtmlTagInfo cDDTag;
    private static CWriteHtmlTagInfo cDivTag;
    private static CWriteHtmlTagInfo cBQTag;
    private Stack cTagStack;
    
    CWriteHtml(final CRTEditor crtEditor, final CTextPointer cTextPointer, final CTextPointer cTextPointer2) {
        super(crtEditor);
        this.cOpenPara = null;
        this.cOpenStyle = null;
        this.cOpenList = null;
        this.cEmptyPara = true;
        this.bDidDiv = false;
        this.cListAlign = 0;
        this.bDoListAlign = false;
        this.bRealListOpen = false;
        this.cTagStack = new Stack();
        this.cTP = (CTextPointer)cTextPointer.clone();
        if (cTextPointer2 != null) {
            this.cEnd = (CTextPointer)cTextPointer2.clone();
        }
    }
    
    String getText() {
        char[] array = this.cTP.getData();
        boolean bop = true;
        boolean b = false;
        this.initExport();
        while (true) {
            if (bop) {
                array = this.cTP.getData();
                this.updateParaSettings();
                this.cEmptyPara = true;
                this.cLastChar = ' ';
            }
            int n;
            if (this.cEnd != null && this.cTP.isSamePara(this.cEnd)) {
                n = this.cTP.getRunDistance(this.cEnd);
                b = (this.cTP.getOffset() + n >= this.cEnd.getOffset());
            }
            else {
                n = this.cTP.getRunLength();
            }
            if (n > 0) {
                this.updateFormatInfo(false);
                this.appendData(array, this.cTP.getOffset(), n);
                this.cEmptyPara = false;
            }
            if (b || !this.cTP.nextRun()) {
                break;
            }
            bop = this.cTP.isBOP();
        }
        if (this.cOpenList != null && this.cEmptyPara) {
            this.cSB.append("&nbsp;");
        }
        this.finishExport();
        return this.cSB.toString();
    }
    
    private void initExport() {
        this.cFI = super.cParent.getParaStyleMgr().getStyle("Default").getFormatInfo();
        this.cPS = new CParaSettings();
        this.cSB = new StringBuffer();
    }
    
    private void finishExport() {
        this.cChanged = 18687;
        this.checkAndEndTag(CWriteHtml.cATag);
        this.checkAndEndTag(CWriteHtml.cStrikeThroughTag);
        this.checkAndEndTag(CWriteHtml.cUnderlineTag);
        this.checkAndEndTag(CWriteHtml.cItalicTag);
        this.checkAndEndTag(CWriteHtml.cBoldTag);
        this.checkAndEndTag(CWriteHtml.cFontTag);
        this.checkAndEndTag(this.cOpenPara);
        this.checkAndEndTag(this.cOpenStyle);
        this.checkAndEndTag(this.cOpenList);
    }
    
    private String alignToString(final int n) {
        final String s = new String("");
        String s2;
        if (n == 0) {
            s2 = "left";
        }
        else if (n == 2) {
            s2 = "right";
        }
        else if (n == 3) {
            s2 = "top";
        }
        else if (n == 4) {
            s2 = "middle";
        }
        else {
            s2 = "bottom";
        }
        return s2;
    }
    
    private void appendData(final char[] array, final int n, final int n2) {
        String s = null;
        int n3 = 0;
        if (this.cFI.isUnkHtml()) {
            this.cSB.append(array, n, n2);
            return;
        }
        for (int i = n; i < n + n2; ++i) {
            char cLastChar = array[i];
            switch (cLastChar) {
                case 8232: {
                    final char c = array[++i];
                    final CImage imageData = super.cParent.cEscMgr.getImageData(array[++i]);
                    if (imageData != null) {
                        String s2 = "<IMG SRC=" + imageData.getImageURLStr() + " HSPACE=5 ";
                        final int alignment = imageData.getAlignment();
                        final String name = imageData.getName();
                        final String altText = imageData.getAltText();
                        if (alignment != -1) {
                            s2 = s2 + "ALIGN=" + this.alignToString(alignment);
                        }
                        if (name != null) {
                            s2 = s2 + " NAME=" + name;
                        }
                        if (altText != null) {
                            s2 = s2 + " ALT=\"" + altText + "\"";
                        }
                        s = s2 + ">";
                        n3 = 1;
                        break;
                    }
                    break;
                }
                case 32: {
                    if (this.cLastChar == ' ' || n3 != 0) {
                        s = "&nbsp;";
                        cLastChar = '\0';
                        n3 = 0;
                        break;
                    }
                    break;
                }
                case 60: {
                    s = "&lt;";
                    break;
                }
                case 62: {
                    s = "&gt;";
                    break;
                }
                case 38: {
                    s = "&amp;";
                    break;
                }
                case 34: {
                    s = "&quot;";
                    break;
                }
                case 3: {
                    s = "<BR>";
                    n3 = 1;
                    break;
                }
                default: {
                    if (cLastChar >= ' ' && cLastChar <= '\u00ff') {
                        s = '&' + CHtmlCommon.cEntities[cLastChar - ' '] + ';';
                        break;
                    }
                    break;
                }
            }
            if (s != null) {
                this.cSB.append(s);
            }
            else {
                this.cSB.append(cLastChar);
            }
            this.cLastChar = cLastChar;
            s = null;
        }
    }
    
    private void updateParaSettings() {
        this.cPS = this.cTP.getParaSettings();
        this.cChanged = 18687;
        this.handleParaChanges();
    }
    
    private void updateFormatInfo(final boolean b) {
        int n = 0;
        this.cReallyChanged = 0;
        final CFormatInfo localFormatInfo = this.cTP.getLocalFormatInfo();
        if (localFormatInfo != null) {
            final boolean unkHtml = localFormatInfo.isUnkHtml();
            if (localFormatInfo.isFaceNameOverridden() && !unkHtml) {
                n |= 0x1;
            }
            if (localFormatInfo.isSizeOverridden() && !unkHtml) {
                n |= 0x2;
            }
            if (localFormatInfo.isColorOverridden() && !unkHtml) {
                n |= 0x4;
            }
            if (localFormatInfo.isBoldOverridden() && localFormatInfo.isBold()) {
                n |= 0x8;
            }
            if (localFormatInfo.isItalicOverridden() && localFormatInfo.isItalic()) {
                n |= 0x10;
            }
            if (localFormatInfo.isUnderlineOverridden() && localFormatInfo.isUnderline()) {
                n |= 0x20;
            }
            if (localFormatInfo.isStrikeThroughOverridden() && localFormatInfo.isStrikeThrough()) {
                n |= 0x40;
            }
            if (localFormatInfo.isURLOverridden()) {
                n |= 0x800;
            }
        }
        final CFormatInfo formatInfo = this.cTP.getFormatInfo();
        final boolean unkHtml2 = formatInfo.isUnkHtml();
        if (!formatInfo.getFaceName().equals(this.cFI.getFaceName()) && !unkHtml2) {
            this.cReallyChanged |= 0x1;
        }
        if (formatInfo.getPointSize() != this.cFI.getPointSize() && !unkHtml2) {
            this.cReallyChanged |= 0x2;
        }
        if (!formatInfo.getColor().equals(this.cFI.getColor()) && !unkHtml2) {
            this.cReallyChanged |= 0x4;
        }
        if (formatInfo.isBold() != this.cFI.isBold()) {
            this.cReallyChanged |= 0x8;
        }
        if (formatInfo.isItalic() != this.cFI.isItalic()) {
            this.cReallyChanged |= 0x10;
        }
        if (formatInfo.isUnderline() != this.cFI.isUnderline()) {
            this.cReallyChanged |= 0x20;
        }
        if (formatInfo.isStrikeThrough() != this.cFI.isStrikeThrough()) {
            this.cReallyChanged |= 0x40;
        }
        if (formatInfo.getURL() != this.cFI.getURL() || (formatInfo.getURL() != null && !formatInfo.getURL().equals(this.cFI.getURL()))) {
            this.cReallyChanged |= 0x800;
        }
        this.cFI = formatInfo;
        if ((this.cReallyChanged & 0x7) != 0x0) {
            this.cChanged = (n | this.cReallyChanged);
        }
        else {
            this.cChanged = ((n & 0xFFFFFFF8) | this.cReallyChanged);
        }
        this.handleFormatChanges();
    }
    
    private void handleFormatChanges() {
        if ((this.cChanged & 0x487F) != 0x0) {
            this.endFormatTags();
            this.startFormatTags();
        }
    }
    
    private void endFormatTags() {
        this.checkAndEndTag(CWriteHtml.cATag);
        this.checkAndEndTag(CWriteHtml.cStrikeThroughTag);
        this.checkAndEndTag(CWriteHtml.cUnderlineTag);
        this.checkAndEndTag(CWriteHtml.cItalicTag);
        this.checkAndEndTag(CWriteHtml.cBoldTag);
        this.checkAndEndTag(CWriteHtml.cFontTag);
    }
    
    private void startFormatTags() {
        this.checkAndStartFontTag();
        this.checkAndStartTag(CWriteHtml.cBoldTag);
        this.checkAndStartTag(CWriteHtml.cItalicTag);
        this.checkAndStartTag(CWriteHtml.cUnderlineTag);
        this.checkAndStartTag(CWriteHtml.cStrikeThroughTag);
        this.checkAndStartTag(CWriteHtml.cATag);
    }
    
    private void handleParaChanges() {
        final CParaStyle style = this.cTP.getStyle();
        final CWriteHtmlTagInfo styleTagInfo = this.getStyleTagInfo(style.getName());
        final int styleIndex = styleTagInfo.getStyleIndex();
        int round = Math.round((this.cPS.getFirstIndent() - style.getParaSettings().getFirstIndent()) / 200.0f);
        if (round < 0) {
            round = 0;
        }
        if (this.cEmptyPara && this.cOpenPara != null) {
            this.cSB.append("&nbsp;");
        }
        this.checkAndEndTag(this.cOpenPara);
        this.cOpenPara = null;
        final boolean handleSpanningStyles = this.handleSpanningStyles(styleIndex, round);
        if (!handleSpanningStyles) {
            if (this.cEmptyPara && this.cOpenStyle != null) {
                this.cSB.append("&nbsp;");
            }
            if (this.cOpenStyle != null && this.cOpenStyle.getStyleIndex() == 9) {
                this.checkAndEndTag(this.cOpenStyle);
                for (int i = this.cIndentLevel; i > 0; --i) {
                    this.popTag();
                }
                this.cIndentLevel = 0;
            }
            if (this.cIndentHack) {
                this.checkAndEndTag(CWriteHtml.cDDTag);
                for (int j = this.cIndentLevel; j > 0; --j) {
                    this.checkAndEndTag(CWriteHtml.cDLTag);
                }
                this.cIndentLevel = 0;
                this.cIndentHack = false;
            }
            else if (this.cOpenStyle != null && this.cOpenStyle.getName().equals("LI")) {
                if (this.bRealListOpen) {
                    this.checkAndEndTag(this.cOpenStyle);
                }
            }
            else {
                this.checkAndEndTag(this.cOpenStyle);
            }
            this.cOpenStyle = styleTagInfo;
        }
        if (styleIndex == -1) {
            final CFormatInfo formatInfo = style.getFormatInfo();
            this.cDefaultFI = formatInfo;
            this.cFI = formatInfo;
        }
        else {
            final CFormatInfo formatInfo2 = super.cParent.getParaStyleMgr().getStyle(CHtmlCommon.cStyles[styleIndex]).getFormatInfo();
            this.cDefaultFI = formatInfo2;
            this.cFI = formatInfo2;
        }
        this.endFormatTags();
        final boolean handleListTypeStyles = this.handleListTypeStyles(styleIndex, round);
        if (!handleSpanningStyles || styleIndex != 7) {
            if (!handleSpanningStyles && styleIndex == 9) {
                for (int k = round + 1; k > 0; --k) {
                    this.pushTag(styleTagInfo, null);
                }
                this.cIndentLevel = round;
            }
            else if (!handleListTypeStyles && round > 0 && styleIndex != 9) {
                for (int l = round; l > 0; --l) {
                    this.pushTag(CWriteHtml.cDLTag, null);
                }
                this.pushTag(CWriteHtml.cDDTag, null);
                this.cIndentLevel = round;
                this.cIndentHack = true;
            }
            else if (styleTagInfo.getName().equals("LI")) {
                if (this.cPS.getBullet() != '\0') {
                    final int alignment = this.cPS.getAlignment();
                    if (alignment != this.cListAlign || this.bDoListAlign) {
                        if (this.bDidDiv) {
                            if ((this.cChanged & 0x80) == 0x0 || (this.cTags & 0x80) == 0x0) {
                                this.cChanged |= 0x80;
                                this.cTags |= 0x80;
                            }
                            this.checkAndEndTag(CWriteHtml.cDivTag);
                            this.bDidDiv = false;
                        }
                        if (alignment != 0) {
                            this.pushTag(CWriteHtml.cDivTag, this.getParaParams(14));
                            this.bDidDiv = true;
                        }
                        this.bDoListAlign = false;
                        this.cListAlign = alignment;
                    }
                    this.updateFormatInfo(false);
                    this.pushTag(handleSpanningStyles ? this.cOpenPara : styleTagInfo, this.getParaParams(styleIndex));
                }
            }
            else {
                this.pushTag(handleSpanningStyles ? this.cOpenPara : styleTagInfo, this.getParaParams(styleIndex));
            }
        }
    }
    
    private boolean handleSpanningStyles(final int n, final int n2) {
        if ((n == 9 || n == 7 || n == 10 || n == 11) && this.cOpenStyle != null && n == this.cOpenStyle.getStyleIndex() && n2 == this.cIndentLevel) {
            this.cOpenPara = ((n == 7) ? null : CWriteHtml.cPTag);
            return true;
        }
        return false;
    }
    
    private boolean handleListTypeStyles(final int n, final int cIndentLevel) {
        if (n == 12 || n == 13 || n == 10 || n == 11) {
            boolean b = false;
            CWriteHtmlTagInfo cOpenList;
            if (n == 12) {
                cOpenList = CWriteHtml.cULTag;
            }
            else if (n == 13) {
                cOpenList = CWriteHtml.cOLTag;
            }
            else {
                cOpenList = CWriteHtml.cDLTag;
            }
            final boolean bRealListOpen = this.cPS.getBullet() != '\0';
            if ((cOpenList == CWriteHtml.cULTag || cOpenList == CWriteHtml.cOLTag) && this.cOpenList == cOpenList && !bRealListOpen) {
                b = true;
            }
            if (this.cOpenList != cOpenList || this.cIndentLevel != cIndentLevel || bRealListOpen != this.bRealListOpen || b) {
                if (this.bDidDiv) {
                    if ((this.cChanged & 0x80) == 0x0 || (this.cTags & 0x80) == 0x0) {
                        this.cChanged |= 0x80;
                        this.cTags |= 0x80;
                    }
                    this.checkAndEndTag(CWriteHtml.cDivTag);
                    this.bDidDiv = false;
                }
                if (this.cOpenList == CWriteHtml.cULTag || this.cOpenList == CWriteHtml.cOLTag) {
                    for (int i = this.cIndentLevel + 1; i > 1; --i) {
                        this.cChanged |= 0x80;
                        this.cTags |= 0x80;
                        this.checkAndEndTag(this.cOpenList);
                    }
                    if (this.cOpenList != cOpenList || bRealListOpen != this.bRealListOpen || b) {
                        this.checkAndEndTag(this.cOpenList);
                    }
                }
                else {
                    for (int j = this.cIndentLevel + 1; j > 0; --j) {
                        this.checkAndEndTag(this.cOpenList);
                    }
                }
                this.cListAlign = this.cPS.getAlignment();
                if (this.cListAlign != 0) {
                    this.bDoListAlign = true;
                }
                if (this.cOpenList == CWriteHtml.cULTag || this.cOpenList == CWriteHtml.cOLTag) {
                    for (int k = cIndentLevel + 1; k > 1; --k) {
                        this.pushTag(cOpenList, null);
                    }
                    if (this.cOpenList != cOpenList || bRealListOpen != this.bRealListOpen || b) {
                        this.pushTag(cOpenList, null);
                    }
                }
                else {
                    for (int l = cIndentLevel + 1; l > 0; --l) {
                        this.pushTag(cOpenList, null);
                    }
                }
                this.bRealListOpen = bRealListOpen;
                if (this.cListAlign != 0 && !this.bRealListOpen) {
                    this.pushTag(CWriteHtml.cDivTag, this.getParaParams(14));
                    this.bDidDiv = true;
                }
                this.cOpenList = cOpenList;
                this.cIndentLevel = cIndentLevel;
            }
            return true;
        }
        if (this.cOpenList != null) {
            if (this.bDidDiv) {
                if ((this.cChanged & 0x80) == 0x0 || (this.cTags & 0x80) == 0x0) {
                    this.cChanged |= 0x80;
                    this.cTags |= 0x80;
                }
                this.checkAndEndTag(CWriteHtml.cDivTag);
                this.bDidDiv = false;
            }
            if (this.cOpenList == CWriteHtml.cULTag || this.cOpenList == CWriteHtml.cOLTag) {
                for (int n2 = this.cIndentLevel + 1; n2 > 1; --n2) {
                    this.cChanged |= 0x80;
                    this.cTags |= 0x80;
                    this.checkAndEndTag(this.cOpenList);
                }
                this.checkAndEndTag(this.cOpenList);
            }
            else {
                for (int n3 = this.cIndentLevel + 1; n3 > 0; --n3) {
                    this.checkAndEndTag(this.cOpenList);
                }
            }
            this.cOpenList = null;
            this.cIndentLevel = 0;
        }
        return false;
    }
    
    private String getParaParams(final int n) {
        StringBuffer sb = null;
        if (n == 0 || n == 8 || n == 14 || (n >= 1 && n <= 6)) {
            final int alignment = this.cPS.getAlignment();
            if (alignment != 0) {
                sb = new StringBuffer();
                sb.append(" ALIGN=");
                sb.append((alignment == 1) ? "CENTER" : "RIGHT");
            }
        }
        if (n == 12 || n == 13) {
            final char bullet = this.cPS.getBullet();
            if (bullet == '\u25a0' || bullet == '\u25cb') {
                sb = new StringBuffer();
                sb.append(" TYPE=");
                sb.append((bullet == '\u25a0') ? "SQUARE" : "CIRCLE");
            }
            else if (bullet != '1' && bullet != '\u25cf') {
                sb = new StringBuffer();
                sb.append(" TYPE=");
                sb.append(bullet);
            }
        }
        return (sb == null) ? null : sb.toString();
    }
    
    private void pushTag(final CWriteHtmlTagInfo cWriteHtmlTagInfo, final String s) {
        this.cTagStack.push(cWriteHtmlTagInfo);
        this.cTags |= cWriteHtmlTagInfo.getFlag();
        this.cSB.append('<' + cWriteHtmlTagInfo.getName());
        if (s != null) {
            this.cSB.append(s);
        }
        this.cSB.append('>');
    }
    
    private CWriteHtmlTagInfo popTag() {
        CWriteHtmlTagInfo cWriteHtmlTagInfo;
        try {
            cWriteHtmlTagInfo = this.cTagStack.pop();
            this.cTags &= ~cWriteHtmlTagInfo.getFlag();
            if (cWriteHtmlTagInfo.needsEndTag()) {
                this.cSB.append("</" + cWriteHtmlTagInfo.getName() + '>');
            }
            if (cWriteHtmlTagInfo.getName() == "P") {
                this.checkEndParaBR();
            }
        }
        catch (Exception ex) {
            cWriteHtmlTagInfo = null;
        }
        return cWriteHtmlTagInfo;
    }
    
    private void checkEndParaBR() {
        CParagraph cParagraph = null;
        if (this.cTP.isBOP()) {
            cParagraph = this.cTP.getPara();
        }
        if (cParagraph != null) {
            cParagraph = (CParagraph)cParagraph.getPrevious();
        }
        else if (this.cTP.isLastPara()) {
            cParagraph = this.cTP.getPara();
        }
        if (cParagraph != null && (cParagraph.getNeedsClear() || cParagraph.isSingleImagePara())) {
            this.cSB.append("<BR CLEAR=all>");
        }
    }
    
    private void checkAndStartTag(final CWriteHtmlTagInfo cWriteHtmlTagInfo) {
        StringBuffer sb = null;
        final int flag = cWriteHtmlTagInfo.getFlag();
        if ((this.cChanged & flag) != 0x0 && (this.cTags & flag) == 0x0) {
            if (cWriteHtmlTagInfo == CWriteHtml.cATag) {
                sb = new StringBuffer();
                sb.append(" HREF=");
                sb.append(this.cFI.getURL());
            }
            this.pushTag(cWriteHtmlTagInfo, (sb == null) ? null : sb.toString());
        }
    }
    
    private void checkAndStartFontTag() {
        if ((this.cChanged & 0x7) != 0x0) {
            final StringBuffer sb = new StringBuffer();
            if ((this.cChanged & 0x2) != 0x0 && this.cFI.getPointSize() != this.cDefaultFI.getPointSize()) {
                sb.append(" SIZE=");
                sb.append(this.getClosestSize(this.cFI.getPointSize()));
                sb.append(" STYLE=font-size:");
                sb.append(this.cFI.getPointSize());
                sb.append("pt");
            }
            if ((this.cChanged & 0x4) != 0x0 || !this.cFI.getColor().equals(this.cDefaultFI.getColor())) {
                sb.append(" COLOR=");
                sb.append(this.colorToValue(this.cFI.getColor()));
            }
            if ((this.cChanged & 0x1) != 0x0 && !this.cFI.getFaceName().equals(this.cDefaultFI.getFaceName())) {
                final String faceName = this.cFI.getFaceName();
                sb.append(" FACE=");
                if (super.cParent.getBehavior() == 0) {
                    sb.append("\"");
                }
                sb.append(faceName);
                if (faceName.equalsIgnoreCase("serif")) {
                    sb.append(",Times");
                }
                else if (faceName.equalsIgnoreCase("sansserif") || faceName.equalsIgnoreCase("sans serif")) {
                    sb.append(",Helvetica");
                }
                else if (faceName.indexOf("monospace") != -1 || faceName.indexOf("Monospace") != -1) {
                    sb.append(",Courier");
                }
                if (super.cParent.getBehavior() == 0) {
                    sb.append('\"');
                }
            }
            if (sb.length() > 0) {
                this.pushTag(CWriteHtml.cFontTag, sb.toString());
            }
        }
    }
    
    private void checkAndEndTag(final CWriteHtmlTagInfo cWriteHtmlTagInfo) {
        if (cWriteHtmlTagInfo != null) {
            final int flag = cWriteHtmlTagInfo.getFlag();
            if ((flag == 0 || (this.cChanged & flag) != 0x0) && (flag == 0 || (this.cTags & flag) != 0x0)) {
                CWriteHtmlTagInfo popTag;
                while ((popTag = this.popTag()) != cWriteHtmlTagInfo && popTag != null) {
                    this.cChanged |= popTag.getFlag();
                }
                if (cWriteHtmlTagInfo != CWriteHtml.cFontTag && (this.cReallyChanged & flag) != 0x0) {
                    this.cChanged &= ~flag;
                }
            }
        }
    }
    
    private CWriteHtmlTagInfo getStyleTagInfo(final String s) {
        boolean b = true;
        int n;
        for (n = CHtmlCommon.cStyles.length - 1; n >= 0 && !CHtmlCommon.cStyles[n].equals(s); --n) {}
        String s2 = null;
        switch (n) {
            case 1: {
                s2 = "H1";
                break;
            }
            case 2: {
                s2 = "H2";
                break;
            }
            case 3: {
                s2 = "H3";
                break;
            }
            case 4: {
                s2 = "H4";
                break;
            }
            case 5: {
                s2 = "H5";
                break;
            }
            case 6: {
                s2 = "H6";
                break;
            }
            case 7: {
                s2 = "PRE";
                break;
            }
            case 8: {
                s2 = "ADDRESS";
                break;
            }
            case 9: {
                s2 = "BLOCKQUOTE";
                break;
            }
            case 10: {
                s2 = "DT";
                b = false;
                break;
            }
            case 11: {
                s2 = "DD";
                b = false;
                break;
            }
            case 12:
            case 13: {
                s2 = "LI";
                b = false;
                break;
            }
            case 14: {
                s2 = "DIV";
                break;
            }
            default: {
                s2 = "P";
                break;
            }
        }
        return new CWriteHtmlTagInfo(128, s2, n, b);
    }
    
    private String colorToValue(final Color color) {
        String string = null;
        switch (color.getRGB() & 0xFFFFFF) {
            case 0: {
                string = "BLACK";
                break;
            }
            case 8421504: {
                string = "GRAY";
                break;
            }
            case 16777215: {
                string = "WHITE";
                break;
            }
            case 16711680: {
                string = "RED";
                break;
            }
            case 16776960: {
                string = "YELLOW";
                break;
            }
            case 8388608: {
                string = "MAROON";
                break;
            }
            case 32768: {
                string = "GREEN";
                break;
            }
            case 65280: {
                string = "LIME";
                break;
            }
            case 32896: {
                string = "TEAL";
                break;
            }
            case 8421376: {
                string = "OLIVE";
                break;
            }
            case 65535: {
                string = "AQUA";
                break;
            }
            case 128: {
                string = "NAVY";
                break;
            }
            case 255: {
                string = "BLUE";
                break;
            }
            case 8388736: {
                string = "PURPLE";
                break;
            }
            case 16711935: {
                string = "FUCHSIA";
                break;
            }
            case 12632256: {
                string = "SILVER";
                break;
            }
            default: {
                final StringBuffer sb = new StringBuffer("#");
                sb.append(Integer.toString(color.getRGB() & 0xFFFFFF, 16));
                if (sb.length() < 7) {
                    sb.insert(1, "000000".substring(0, 7 - sb.length()));
                }
                string = sb.toString();
                break;
            }
        }
        return string;
    }
    
    static {
        CWriteHtml.cATag = new CWriteHtmlTagInfo(2048, "A");
        CWriteHtml.cBoldTag = new CWriteHtmlTagInfo(8, "B");
        CWriteHtml.cItalicTag = new CWriteHtmlTagInfo(16, "I");
        CWriteHtml.cUnderlineTag = new CWriteHtmlTagInfo(32, "U");
        CWriteHtml.cStrikeThroughTag = new CWriteHtmlTagInfo(64, "STRIKE");
        CWriteHtml.cFontTag = new CWriteHtmlTagInfo(7, "FONT");
        CWriteHtml.cPTag = new CWriteHtmlTagInfo(0, "P");
        CWriteHtml.cULTag = new CWriteHtmlTagInfo(0, "UL");
        CWriteHtml.cOLTag = new CWriteHtmlTagInfo(0, "OL");
        CWriteHtml.cDLTag = new CWriteHtmlTagInfo(0, "DL");
        CWriteHtml.cDDTag = new CWriteHtmlTagInfo(0, "DD", -1, false);
        CWriteHtml.cDivTag = new CWriteHtmlTagInfo(128, "DIV");
        CWriteHtml.cBQTag = new CWriteHtmlTagInfo(128, "BLOCKQUOTE");
    }
}
