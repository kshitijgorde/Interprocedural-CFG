// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Image;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Stack;
import java.awt.Color;

final class CReadHtml extends CHtmlCommon
{
    private CTextPointer cTP;
    private CStringReader cSR;
    private StringBuffer cCollectedText;
    private static final Color cMaroon;
    private static final Color cGreen;
    private static final Color cLime;
    private static final Color cTeal;
    private static final Color cOlive;
    private static final Color cAqua;
    private static final Color cNavy;
    private static final Color cPurple;
    private static final Color cFuchsia;
    private static final Color cSilver;
    private String[] cFaces;
    private Stack cFaceNameStack;
    private Stack cSizeStack;
    private Stack cStyleSizeStack;
    private Stack cColorStack;
    private Stack cBoldStack;
    private Stack cItalicStack;
    private Stack cUnderlineStack;
    private Stack cStrikeThroughStack;
    private Stack cAlignStack;
    private Stack cBulletStack;
    private Stack cStyleStack;
    private Stack cUnkHtmlStack;
    private CHtmlTagStack cTagStack;
    private String cURL;
    private int cLeftIndent;
    private int cRightIndent;
    private int cListStyle;
    private boolean cAtStart;
    private boolean cParaInsertPending;
    private boolean cPreParaInsertPending;
    private boolean cInLI;
    private int cLIParas;
    private boolean bInBlockQuote;
    private CHtmlTag cBehaviorTag;
    private boolean cInTable;
    private boolean cDidRowspan;
    private static String SPECIAL_TABLE;
    
    CReadHtml(final CRTEditor crtEditor, final CParagraph cParagraph) {
        super(crtEditor);
        this.cListStyle = 39;
        this.cAtStart = true;
        this.cParaInsertPending = false;
        this.cPreParaInsertPending = false;
        this.cInLI = false;
        this.cLIParas = 0;
        this.bInBlockQuote = false;
        this.cInTable = false;
        this.cDidRowspan = false;
        this.cTP = new CTextPointer(cParagraph, 0);
        this.cBehaviorTag = new CHtmlTag(crtEditor.getBehavior());
    }
    
    void setText(final String s) {
        this.initImport(s);
        this.parseText();
        this.finishImport();
    }
    
    private void initImport(final String s) {
        this.createHtmlStyles();
        final CParaStyle default1 = super.cParent.getParaStyleMgr().getDefault();
        final CFormatInfo formatInfo = default1.getFormatInfo();
        final CParaSettings paraSettings = default1.getParaSettings();
        this.cSR = new CStringReader(s, "<& \r\n\t");
        this.cCollectedText = new StringBuffer();
        this.cFaces = super.cParent.getParent().getToolkit().getFontList();
        this.cFaceNameStack = new Stack();
        this.cSizeStack = new Stack();
        this.cStyleSizeStack = new Stack();
        this.cColorStack = new Stack();
        this.cBoldStack = new Stack();
        this.cItalicStack = new Stack();
        this.cUnderlineStack = new Stack();
        this.cStrikeThroughStack = new Stack();
        this.cAlignStack = new Stack();
        this.cBulletStack = new Stack();
        this.cStyleStack = new Stack();
        this.cUnkHtmlStack = new Stack();
        this.cTagStack = new CHtmlTagStack();
        this.cFaceNameStack.push(formatInfo.getFaceName());
        this.cSizeStack.push(new Integer(this.getClosestSize(formatInfo.getPointSize())));
        this.cColorStack.push(formatInfo.getColor());
        this.cBoldStack.push(this.getBoolean(formatInfo.isBold()));
        this.cItalicStack.push(this.getBoolean(formatInfo.isItalic()));
        this.cUnderlineStack.push(this.getBoolean(formatInfo.isUnderline()));
        this.cStrikeThroughStack.push(this.getBoolean(formatInfo.isStrikeThrough()));
        this.cAlignStack.push(new Integer(paraSettings.getAlignment()));
        this.cStyleStack.push(new Integer(0));
        this.cUnkHtmlStack.push(this.getBoolean(false));
        this.cBulletStack.push(new Character(super.cParent.getParaStyleMgr().getStyle(CHtmlCommon.cStyles[12]).getParaSettings().getBullet()));
        this.handleStyle(0);
    }
    
    private final Boolean getBoolean(final boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
    
    private void finishImport() {
    }
    
    private void parseText() {
        int n = 0;
        while (!this.cSR.atEnd()) {
            try {
                String s = this.cSR.nextToken();
                final char char1 = s.charAt(0);
                switch (char1) {
                    case 9:
                    case 10:
                    case 13:
                    case 32: {
                        if (!this.isPreformatted()) {
                            if (!this.cAtStart && n != 0 && char1 == ' ') {
                                this.collectText(" ");
                                n = 0;
                            }
                            this.cSR.skipDelimiters(" \n\r\t");
                            continue;
                        }
                        switch (char1) {
                            case 9:
                            case 32: {
                                this.collectText(" ");
                                continue;
                            }
                            case 13: {
                                if (this.cAtStart) {
                                    this.cAtStart = false;
                                    continue;
                                }
                                this.insertCollectedText();
                                if (this.cPreParaInsertPending) {
                                    this.insertPara();
                                }
                                this.cPreParaInsertPending = true;
                                continue;
                            }
                            default: {
                                continue;
                            }
                        }
                        break;
                    }
                    case 60: {
                        if (" \n\r\t".indexOf(this.cSR.peekChar()) == -1) {
                            this.insertCollectedText();
                            this.handleTag(this.parseTag());
                            this.cSR.setDelimiters("<& \r\n\t");
                            continue;
                        }
                        break;
                    }
                }
                if (char1 == '&' && !Character.isWhitespace(this.cSR.peekChar())) {
                    s = this.parseEntity();
                }
                this.collectText(s);
                n = 1;
            }
            catch (Exception ex) {
                this.cSR.setDelimiters("<& \r\n\t");
            }
        }
        this.insertCollectedText();
    }
    
    private String parseEntity() {
        char c = '\0';
        final String nextToken = this.cSR.nextToken(" \n\r;");
        if (nextToken.charAt(0) == '#') {
            try {
                final int intValue = Integer.valueOf(nextToken.substring(1));
                if (intValue >= 0 && intValue <= 65535) {
                    c = (char)intValue;
                }
            }
            catch (NumberFormatException ex) {
                c = '\0';
            }
        }
        else {
            for (int i = 1; i < CHtmlCommon.cEntities.length; ++i) {
                if (CHtmlCommon.cEntities[i].equals(nextToken)) {
                    c = (char)(i + 160);
                    break;
                }
            }
            if (c == '\0') {
                if (nextToken.equals("quot")) {
                    c = '\"';
                }
                else if (nextToken.equals("amp")) {
                    c = '&';
                }
                else if (nextToken.equals("lt")) {
                    c = '<';
                }
                else if (nextToken.equals("gt")) {
                    c = '>';
                }
                else if (nextToken.equals("nbsp")) {
                    c = ' ';
                }
            }
        }
        this.cSR.skipDelimiters(";");
        if (c != '\0') {
            return new Character(c).toString();
        }
        return nextToken;
    }
    
    private CHtmlTag parseTag() {
        final CHtmlTag cHtmlTag = new CHtmlTag();
        this.cSR.setDelimiters("> \r\n\t'\"=");
        String tag = this.cSR.nextToken();
        if (tag.charAt(0) == '/') {
            cHtmlTag.setEndTag(true);
            tag = tag.substring(1, tag.length());
        }
        cHtmlTag.setTag(tag);
        String s = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (!this.cSR.atEnd() && n3 == 0) {
            final String nextToken = this.cSR.nextToken();
            switch (nextToken.charAt(0)) {
                case '\t':
                case '\n':
                case '\r':
                case ' ': {
                    continue;
                }
                case '>': {
                    n3 = 1;
                    continue;
                }
                case '=': {
                    if (n != 0) {
                        final char peekChar = this.cSR.peekChar();
                        if (peekChar == '\"' || peekChar == '\'') {
                            this.cSR.setDelimiters(">'\"");
                        }
                        else {
                            this.cSR.setDelimiters("> \r\n\t'\"");
                        }
                        n2 = 1;
                        continue;
                    }
                    continue;
                }
                case '\"':
                case '\'': {
                    final String nextToken2 = this.cSR.nextToken(nextToken, true);
                    if (n2 != 0) {
                        cHtmlTag.addParam(s, nextToken2);
                        n2 = 0;
                        n = 0;
                        this.cSR.setDelimiters("> \r\n\t'\"=");
                    }
                    this.cSR.skipChar();
                    continue;
                }
                default: {
                    if (n != 0) {
                        if (n2 != 0) {
                            cHtmlTag.addParam(s, nextToken);
                            n2 = 0;
                            n = 0;
                            this.cSR.setDelimiters("> \r\n\t'\"=");
                            continue;
                        }
                        cHtmlTag.addParam(s, null);
                    }
                    s = nextToken;
                    n = 1;
                    continue;
                }
            }
        }
        if (n != 0) {
            cHtmlTag.addParam(s, null);
        }
        return cHtmlTag;
    }
    
    private void handleTag(final CHtmlTag cHtmlTag) {
        final int id = cHtmlTag.getID();
        if (cHtmlTag.isEndTag()) {
            if (id == 0 || (id >= 47 && !this.cInTable)) {
                this.handleUnkTag(cHtmlTag, true);
            }
            else {
                this.handleEndTag(id);
            }
            return;
        }
        boolean b = true;
        int n = 0;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        char charValue = '\0';
        switch (id) {
            case 44: {
                this.cURL = cHtmlTag.getParamValue("HREF");
                if (cHtmlTag.getParamValue("TARGET") != null) {
                    this.cURL = this.cURL + " TARGET=" + cHtmlTag.getParamValue("TARGET");
                }
                n |= 0x800;
                break;
            }
            case 46: {
                super.cParent.setBaseURL(cHtmlTag.getParamValue("HREF"));
                break;
            }
            case 2:
            case 36: {
                this.cBoldStack.push(Boolean.TRUE);
                n |= 0x8;
                break;
            }
            case 8:
            case 11:
            case 16:
            case 24:
            case 40: {
                this.cItalicStack.push(Boolean.TRUE);
                n |= 0x10;
                break;
            }
            case 38: {
                this.cUnderlineStack.push(Boolean.TRUE);
                n |= 0x20;
                break;
            }
            case 35: {
                this.cStrikeThroughStack.push(Boolean.TRUE);
                n |= 0x40;
                break;
            }
            case 17:
            case 42: {
                n |= this.handleFontTag(cHtmlTag);
                break;
            }
            case 3: {
                n |= this.handleFontTag(new CHtmlTag("FONT", "SIZE", "+1"));
                break;
            }
            case 34: {
                n |= this.handleFontTag(new CHtmlTag("FONT", "SIZE", "-1"));
                break;
            }
            case 9:
            case 25:
            case 27:
            case 33:
            case 37:
            case 41: {
                String s = "Monospace";
                if (super.cParent.getBehavior() == 1) {
                    s = "Courier";
                }
                n |= this.handleFontTag(new CHtmlTag("FONT", "FACE", s));
                break;
            }
            case 5: {
                final Color valueToColor = this.valueToColor(cHtmlTag.getParamValue("BGCOLOR"));
                if (valueToColor != null) {
                    super.cParent.setBackground(valueToColor);
                }
                final Color valueToColor2 = this.valueToColor(cHtmlTag.getParamValue("LINK"));
                if (valueToColor2 != null) {
                    super.cParent.setLinkColor(valueToColor2);
                }
                n |= this.handleFontTag(new CHtmlTag("FONT", "COLOR", cHtmlTag.getParamValue("TEXT")));
                break;
            }
            case 6: {
                if (cHtmlTag.getParamValue("CLEAR") == null && (this.cDidRowspan || !this.cInTable)) {
                    this.cTP.insertLineBreak(this.createFormatInfo());
                    b = false;
                    break;
                }
                break;
            }
            case 13: {
                if (this.cInTable) {
                    break;
                }
                b2 = true;
            }
            case 7:
            case 30: {
                if (this.cInTable) {
                    if (id == 30) {
                        this.cTP.insertLineBreak(this.createFormatInfo());
                        break;
                    }
                    break;
                }
                else {
                    this.handleEndTag(30);
                    b3 = true;
                    if (!this.bInBlockQuote) {
                        b2 = true;
                    }
                    if (id == 7) {
                        this.cAlignStack.push(new Integer(1));
                        n |= 0x200;
                        break;
                    }
                    n |= this.handleAlignment(cHtmlTag);
                    break;
                }
                break;
            }
            case 1:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23: {
                this.handleEndTag(30);
                b2 = true;
                b3 = true;
                n |= this.handleAlignment(cHtmlTag);
                break;
            }
            case 26: {
                this.handleEndTag(26);
                this.cInLI = true;
                this.cLIParas = 0;
                n |= this.handleBullet(cHtmlTag);
                charValue = this.cBulletStack.peek();
                this.handleEndTag(30);
                this.cTP.applyRange(this.createFormatInfo(), this.cTP);
                b2 = true;
                b3 = true;
                break;
            }
            case 10:
            case 15: {
                if (id == 15) {
                    this.handleEndTag(10);
                }
                if (id == 10) {
                    this.handleEndTag(15);
                }
            }
            case 4: {
                if (id == 4) {
                    ++this.cLeftIndent;
                    ++this.cRightIndent;
                    n |= 0x3000;
                    this.bInBlockQuote = true;
                }
            }
            case 32: {
                b4 = true;
            }
            case 31: {
                this.handleEndTag(30);
                b2 = true;
                b3 = true;
                break;
            }
            case 14:
            case 28:
            case 29:
            case 39: {
                ++this.cLeftIndent;
                n |= 0x1000;
                this.handleEndTag(30);
                this.handleEndTag(26);
                if (id == 39 || id == 29) {
                    this.cListStyle = id;
                    n |= this.handleBullet(cHtmlTag);
                    b3 = true;
                    b2 = true;
                    break;
                }
                break;
            }
            case 45: {
                if (!this.cInTable) {
                    this.insertImage(cHtmlTag.getParamValue("SRC"), cHtmlTag.getParamValue("ALIGN"), cHtmlTag.getParamValue("NAME"), cHtmlTag.getParamValue("ALT"), b4);
                    b = false;
                    break;
                }
                this.cTP.insert(' ', this.createFormatInfo());
                break;
            }
            case 47: {
                final String paramValue = cHtmlTag.getParamValue(CReadHtml.SPECIAL_TABLE);
                if (paramValue != null && paramValue.length() > 0 && paramValue.equalsIgnoreCase("true")) {
                    this.cInTable = true;
                    b3 = true;
                    b2 = true;
                }
                if (!this.cInTable) {
                    this.handleUnkTag(cHtmlTag, false);
                    b = false;
                    break;
                }
                break;
            }
            case 48: {
                if (!this.cInTable) {
                    this.handleUnkTag(cHtmlTag, false);
                    b = false;
                    break;
                }
                break;
            }
            case 49:
            case 50: {
                if (!this.cInTable) {
                    this.handleUnkTag(cHtmlTag, false);
                    b = false;
                    break;
                }
                if (this.cDidRowspan) {
                    final CFormatInfo formatInfo = this.createFormatInfo();
                    this.cTP.insertLineBreak(formatInfo);
                    this.cTP.insertLineBreak(formatInfo);
                }
                final String paramValue2 = cHtmlTag.getParamValue("ROWSPAN");
                if (paramValue2 != null && paramValue2.length() > 0) {
                    this.cDidRowspan = true;
                    break;
                }
                this.cDidRowspan = false;
                break;
            }
            default: {
                if (!this.cInTable) {
                    this.handleUnkTag(cHtmlTag, false);
                }
                return;
            }
        }
        if (b3) {
            this.insertPara();
            if (b2) {
                final int styleIndex = this.getStyleIndex(id);
                final int n2 = n | this.handleStyle(styleIndex);
                this.cStyleStack.push(new Integer(styleIndex));
                n = (n2 | 0x80);
                this.setStyle();
            }
            this.setParaSettings((char)((this.cInLI && this.cLIParas <= 1) ? charValue : false), b4);
            n |= 0x100;
        }
        if (b) {
            this.cTagStack.push(new CHtmlTagStackElement(id, n));
        }
    }
    
    private void handleEndTag(final int n) {
        final int indexOfLastID = this.cTagStack.findIndexOfLastID(n);
        if (indexOfLastID != -1) {
            switch (n) {
                case 26: {
                    this.cInLI = false;
                    break;
                }
                case 14: {
                    if (this.cTagStack.findIndexOfLastID(10) > indexOfLastID) {
                        this.handleEndTag(10);
                        break;
                    }
                    break;
                }
                case 28:
                case 29:
                case 39: {
                    if (this.cTagStack.findIndexOfLastID(26) > indexOfLastID) {
                        this.handleEndTag(26);
                        break;
                    }
                    break;
                }
                case 32: {
                    this.cPreParaInsertPending = false;
                    break;
                }
                case 4: {
                    this.bInBlockQuote = false;
                    break;
                }
                case 48: {
                    if (this.cInTable) {
                        this.cDidRowspan = false;
                        this.cTP.insertLineBreak(this.createFormatInfo());
                        break;
                    }
                    break;
                }
                case 47: {
                    if (this.cInTable) {
                        final int indexOfLastID2 = this.cTagStack.findIndexOfLastID(48);
                        if (indexOfLastID2 != -1) {
                            this.cTagStack.removeElementAt(indexOfLastID2);
                        }
                        this.cInTable = false;
                        this.cDidRowspan = false;
                        break;
                    }
                    break;
                }
            }
            final int changed = this.cTagStack.elementAt(indexOfLastID).getChanged();
            if (changed != 0) {
                if ((changed & 0x1) != 0x0) {
                    this.cFaceNameStack.pop();
                }
                if ((changed & 0x2) != 0x0) {
                    this.cSizeStack.pop();
                }
                if ((changed & 0x16000) != 0x0) {
                    this.cStyleSizeStack.pop();
                }
                if ((changed & 0x4) != 0x0) {
                    this.cColorStack.pop();
                }
                if ((changed & 0x8) != 0x0) {
                    this.cBoldStack.pop();
                }
                if ((changed & 0x10) != 0x0) {
                    this.cItalicStack.pop();
                }
                if ((changed & 0x20) != 0x0) {
                    this.cUnderlineStack.pop();
                }
                if ((changed & 0x40) != 0x0) {
                    this.cStrikeThroughStack.pop();
                }
                if ((changed & 0x200) != 0x0) {
                    this.cAlignStack.pop();
                }
                if ((changed & 0x400) != 0x0) {
                    this.cBulletStack.pop();
                }
                if ((changed & 0x80) != 0x0) {
                    this.cStyleStack.pop();
                }
                if ((changed & 0x100) != 0x0 && !this.cAtStart) {
                    this.cParaInsertPending = true;
                    this.cAtStart = true;
                }
                if ((changed & 0x800) != 0x0) {
                    this.cURL = null;
                }
                if ((changed & 0x1000) != 0x0) {
                    --this.cLeftIndent;
                }
                if ((changed & 0x2000) != 0x0) {
                    --this.cRightIndent;
                }
            }
            this.cTagStack.removeElementAt(indexOfLastID);
        }
    }
    
    private void handleUnkTag(final CHtmlTag cHtmlTag, final boolean b) {
        cHtmlTag.setID(0);
        String string = "<";
        if (b) {
            string += "/";
        }
        String s = string + cHtmlTag.getTag();
        final Enumeration params = cHtmlTag.getParams();
        while (params.hasMoreElements()) {
            final CHtmlTagParam cHtmlTagParam = params.nextElement();
            s = s + " " + cHtmlTagParam.getParam() + "=\"" + cHtmlTagParam.getValue() + "\"";
        }
        this.collectText(s + ">");
        this.cUnkHtmlStack.push(this.getBoolean(true));
        this.insertCollectedText();
        this.cUnkHtmlStack.pop();
    }
    
    private int handleFontTag(final CHtmlTag cHtmlTag) {
        int n = 0;
        final Enumeration params = cHtmlTag.getParams();
        while (params.hasMoreElements()) {
            final CHtmlTagParam cHtmlTagParam = params.nextElement();
            final String param = cHtmlTagParam.getParam();
            final String value = cHtmlTagParam.getValue();
            if (param.equals("FACE") && value != null && value.length() > 0) {
                String s = null;
                final StringTokenizer stringTokenizer = new StringTokenizer(value, ",");
                String s3;
                final String s2 = s3 = stringTokenizer.nextToken();
                while (s == null) {
                    for (int i = 0; i < this.cFaces.length; ++i) {
                        if (s3.equalsIgnoreCase(this.cFaces[i])) {
                            s = s3;
                            break;
                        }
                    }
                    if (!stringTokenizer.hasMoreElements()) {
                        break;
                    }
                    s3 = stringTokenizer.nextToken();
                }
                final String s4 = (s == null) ? s2 : s;
                this.cFaceNameStack.push((s == null) ? s2 : s);
                n |= 0x1;
            }
            else if (param.equals("SIZE")) {
                final int valueToSize = this.valueToSize(value);
                if (valueToSize == -1) {
                    continue;
                }
                this.cSizeStack.push(new Integer(valueToSize));
                n |= 0x2;
            }
            else if (param.equals("COLOR")) {
                final Color valueToColor = this.valueToColor(value);
                if (valueToColor == null) {
                    continue;
                }
                this.cColorStack.push(valueToColor);
                n |= 0x4;
            }
            else {
                if (!param.equals("STYLE")) {
                    continue;
                }
                final String valueToFontSize = this.valueToFontSize(value);
                if (valueToFontSize == null) {
                    continue;
                }
                this.cStyleSizeStack.push(new String(valueToFontSize));
                n |= 0x16000;
            }
        }
        return n;
    }
    
    private int handleAlignment(final CHtmlTag cHtmlTag) {
        final String paramValue = cHtmlTag.getParamValue("ALIGN");
        if (paramValue != null) {
            this.cAlignStack.push(new Integer(this.valueToAlignment(paramValue)));
            return 512;
        }
        return 0;
    }
    
    private int handleBullet(final CHtmlTag cHtmlTag) {
        final String paramValue = cHtmlTag.getParamValue("TYPE");
        final int id = cHtmlTag.getID();
        char c;
        if (paramValue != null) {
            c = this.valueToBullet(paramValue, id);
        }
        else if (id == 26) {
            c = this.cBulletStack.peek();
        }
        else if (id == 39) {
            c = '\u25cf';
        }
        else {
            c = '1';
        }
        this.cBulletStack.push(new Character(c));
        return 1024;
    }
    
    private int handleStyle(final int n) {
        int n2 = 0;
        final int intValue = this.cStyleStack.peek();
        if (super.cParent.getParaStyleMgr().getStyle(CHtmlCommon.cStyles[n]) == null || n == intValue) {
            return 0;
        }
        final CFormatInfo cFormatInfo = super.cCannedFormat[n];
        if (cFormatInfo.isBoldOverridden()) {
            this.cBoldStack.push(new Boolean(cFormatInfo.isBold()));
            n2 |= 0x8;
        }
        if (cFormatInfo.isItalicOverridden()) {
            this.cItalicStack.push(new Boolean(cFormatInfo.isItalic()));
            n2 |= 0x10;
        }
        if (cFormatInfo.isSizeOverridden()) {
            this.cSizeStack.push(new Integer(this.getClosestSize(cFormatInfo.getPointSize())));
            n2 |= 0x2;
        }
        if (cFormatInfo.isFaceNameOverridden()) {
            this.cFaceNameStack.push(new String(cFormatInfo.getFaceName()));
            n2 |= 0x1;
        }
        return n2;
    }
    
    private int valueToSize(String substring) {
        if (substring == null) {
            return -1;
        }
        final boolean b = substring.charAt(0) == '-';
        final boolean b2 = b || substring.charAt(0) == '+';
        if (b2) {
            substring = substring.substring(1);
        }
        int intValue;
        try {
            intValue = Integer.valueOf(substring);
        }
        catch (NumberFormatException ex) {
            return -1;
        }
        if (b2) {
            if (b) {
                intValue = -intValue;
            }
            intValue += this.cSizeStack.peek();
        }
        if (intValue < 1) {
            intValue = 1;
        }
        else if (intValue > 7) {
            intValue = 7;
        }
        return intValue;
    }
    
    private Color valueToColor(String upperCase) {
        if (upperCase == null) {
            return null;
        }
        Color color = null;
        if (upperCase.charAt(0) == '#') {
            color = new Color(Integer.parseInt(upperCase.substring(1, upperCase.length()), 16));
        }
        else {
            upperCase = upperCase.toUpperCase();
            switch (upperCase.charAt(0)) {
                case 'A': {
                    if (upperCase.equals("AQUA")) {
                        color = CReadHtml.cAqua;
                        break;
                    }
                    break;
                }
                case 'B': {
                    if (upperCase.equals("BLUE")) {
                        color = Color.blue;
                        break;
                    }
                    if (upperCase.equals("BLACK")) {
                        color = Color.black;
                        break;
                    }
                    break;
                }
                case 'F': {
                    if (upperCase.equals("FUCHSIA")) {
                        color = CReadHtml.cFuchsia;
                        break;
                    }
                    break;
                }
                case 'G': {
                    if (upperCase.equals("GRAY")) {
                        color = Color.gray;
                        break;
                    }
                    if (upperCase.equals("GREEN")) {
                        color = CReadHtml.cGreen;
                        break;
                    }
                    break;
                }
                case 'L': {
                    if (upperCase.equals("LIME")) {
                        color = CReadHtml.cLime;
                        break;
                    }
                    break;
                }
                case 'M': {
                    if (upperCase.equals("MAROON")) {
                        color = CReadHtml.cMaroon;
                        break;
                    }
                    break;
                }
                case 'N': {
                    if (upperCase.equals("NAVY")) {
                        color = CReadHtml.cNavy;
                        break;
                    }
                    break;
                }
                case 'O': {
                    if (upperCase.equals("OLIVE")) {
                        color = CReadHtml.cOlive;
                        break;
                    }
                    break;
                }
                case 'P': {
                    if (upperCase.equals("PURPLE")) {
                        color = CReadHtml.cPurple;
                        break;
                    }
                    break;
                }
                case 'R': {
                    if (upperCase.equals("RED")) {
                        color = Color.red;
                        break;
                    }
                    break;
                }
                case 'S': {
                    if (upperCase.equals("SILVER")) {
                        color = CReadHtml.cSilver;
                        break;
                    }
                    break;
                }
                case 'T': {
                    if (upperCase.equals("TEAL")) {
                        color = CReadHtml.cTeal;
                        break;
                    }
                    break;
                }
                case 'W': {
                    if (upperCase.equals("WHITE")) {
                        color = Color.white;
                        break;
                    }
                    break;
                }
                case 'Y': {
                    if (upperCase.equals("YELLOW")) {
                        color = Color.yellow;
                        break;
                    }
                    break;
                }
            }
        }
        if (color == null) {
            color = new Color(Integer.parseInt(upperCase.substring(0, upperCase.length()), 16));
        }
        return color;
    }
    
    private int valueToAlignment(String upperCase) {
        if (upperCase == null) {
            return 0;
        }
        upperCase = upperCase.toUpperCase();
        if (upperCase.equals("CENTER")) {
            return 1;
        }
        if (upperCase.equals("RIGHT")) {
            return 2;
        }
        return 0;
    }
    
    private char valueToBullet(final String s, final int n) {
        char c = '\0';
        if (s != null) {
            if (n == 39 || this.cListStyle == 39) {
                if (s.equalsIgnoreCase("SQUARE")) {
                    c = '\u25a0';
                }
                else if (s.equalsIgnoreCase("CIRCLE")) {
                    c = '\u25cb';
                }
                else {
                    c = '\u25cf';
                }
            }
            else if (s.equals("A")) {
                c = 'A';
            }
            else if (s.equals("a")) {
                c = 'a';
            }
            else if (s.equals("I")) {
                c = 'I';
            }
            else if (s.equals("i")) {
                c = 'i';
            }
            else {
                c = '1';
            }
        }
        return c;
    }
    
    private String valueToFontSize(final String s) {
        String s2 = null;
        try {
            final String lowerCase = s.toLowerCase();
            final int index = lowerCase.indexOf("font-size:");
            if (s != null && index != -1) {
                final String substring = lowerCase.substring(index, lowerCase.length());
                if (substring.indexOf(";") != -1) {
                    s2 = new StringTokenizer(substring, ";").nextToken();
                }
                s2 = substring.substring(index + 10, substring.length());
            }
        }
        catch (Exception ex) {}
        return s2.trim();
    }
    
    private void setParaSettings(final char bullet, final boolean b) {
        final CParaSettings paraSettings = this.cTP.getParaSettings();
        final CParaSettings cParaSettings = new CParaSettings();
        final CParaSettings paraSettings2 = super.cParent.getParaStyleMgr().getStyle(CHtmlCommon.cStyles[this.cStyleStack.peek()]).getParaSettings();
        final int intValue = this.cAlignStack.peek();
        if (paraSettings.getAlignment() != intValue) {
            cParaSettings.setAlignment(intValue);
        }
        if (paraSettings.getBullet() != bullet) {
            cParaSettings.setBullet(bullet);
        }
        if (this.cLeftIndent > 0) {
            final int n = (this.cLeftIndent - 1) * 200 + paraSettings2.getFirstIndent();
            if (paraSettings.getFirstIndent() != n) {
                cParaSettings.setFirstIndent(n);
            }
            if (paraSettings.getRestIndent() != n) {
                cParaSettings.setRestIndent(n);
            }
        }
        if (this.cRightIndent > 0) {
            final int rightIndent = this.cRightIndent * 200;
            if (paraSettings.getRightIndent() != rightIndent) {
                cParaSettings.setRightIndent(rightIndent);
            }
        }
        if (b) {
            cParaSettings.setSpaceAbove(150);
        }
        this.cTP.apply(cParaSettings);
    }
    
    private CFormatInfo createFormatInfo() {
        final boolean booleanValue = this.cBoldStack.peek();
        final boolean booleanValue2 = this.cItalicStack.peek();
        final boolean booleanValue3 = this.cUnderlineStack.peek();
        final boolean booleanValue4 = this.cStrikeThroughStack.peek();
        final String faceName = this.cFaceNameStack.peek();
        final Color color = this.cColorStack.peek();
        int int1 = 12;
        try {
            final String peek = this.cStyleSizeStack.peek();
            if (peek instanceof String) {
                final String s = peek;
                final int index = s.indexOf("pt");
                if (index > 0) {
                    int1 = Integer.parseInt(s.substring(0, index));
                }
            }
            else {
                int1 = super.cPresetSizes[this.cSizeStack.peek() - 1];
            }
        }
        catch (Exception ex) {
            int1 = super.cPresetSizes[this.cSizeStack.peek() - 1];
        }
        final boolean booleanValue5 = this.cUnkHtmlStack.peek();
        final CFormatInfo formatInfo = this.cTP.getStyle().getFormatInfo();
        final CFormatInfo cFormatInfo = new CFormatInfo();
        if (formatInfo.isBold() != booleanValue) {
            cFormatInfo.setBold(booleanValue);
        }
        if (formatInfo.isItalic() != booleanValue2) {
            cFormatInfo.setItalic(booleanValue2);
        }
        if (formatInfo.isUnderline() != booleanValue3) {
            cFormatInfo.setUnderline(booleanValue3);
        }
        if (formatInfo.isStrikeThrough() != booleanValue4) {
            cFormatInfo.setStrikeThrough(booleanValue4);
        }
        if (!formatInfo.getFaceName().equals(faceName)) {
            cFormatInfo.setFaceName(faceName);
        }
        if (!formatInfo.getColor().equals(color)) {
            cFormatInfo.setColor(color);
        }
        if (formatInfo.getPointSize() != int1) {
            cFormatInfo.setPointSize(int1);
        }
        if (this.cURL != null) {
            cFormatInfo.setURL(this.cURL);
        }
        if (formatInfo.isUnkHtml() != booleanValue5) {
            cFormatInfo.setUnkHtml(booleanValue5);
            if (booleanValue5) {
                cFormatInfo.setColor(CFormatInfo.COLOR_UNK);
            }
        }
        return cFormatInfo;
    }
    
    private int getStyleIndex(final int n) {
        switch (n) {
            case 7:
            case 30:
            case 47: {
                return 0;
            }
            case 18: {
                return 1;
            }
            case 19: {
                return 2;
            }
            case 20: {
                return 3;
            }
            case 21: {
                return 4;
            }
            case 22: {
                return 5;
            }
            case 23: {
                return 6;
            }
            case 31:
            case 32: {
                return 7;
            }
            case 1: {
                return 8;
            }
            case 4: {
                return 9;
            }
            case 15: {
                return 10;
            }
            case 10: {
                return 11;
            }
            case 26: {
                if (this.cListStyle == 29) {
                    return 13;
                }
                return 12;
            }
            case 39: {
                return 12;
            }
            case 29: {
                return 13;
            }
            case 13: {
                return 14;
            }
            default: {
                return 0;
            }
        }
    }
    
    private void setStyle() {
        this.cTP.setStyle(super.cParent.getParaStyleMgr().getStyle(CHtmlCommon.cStyles[this.cStyleStack.peek()]));
    }
    
    private void collectText(final String s) {
        this.cCollectedText.append(s);
        this.cAtStart = false;
    }
    
    private boolean isPreformatted() {
        return this.cStyleStack.peek() == 7;
    }
    
    private void doInsertPara() {
        this.cTP.insertPara();
        if (this.cInLI) {
            ++this.cLIParas;
            this.cTP.applyRange(this.createFormatInfo(), this.cTP);
        }
    }
    
    private void insertPara() {
        this.insertCollectedText();
        if (!this.cAtStart || this.cParaInsertPending) {
            this.cParaInsertPending = false;
            this.doInsertPara();
            this.setStyle();
            if (!this.isPreformatted()) {
                this.cAtStart = true;
            }
        }
    }
    
    private void insertCollectedText() {
        if (this.cCollectedText.length() > 0) {
            if (this.cCollectedText.charAt(0) == ' ' && this.cCollectedText.length() == 1) {
                this.cCollectedText = new StringBuffer();
                return;
            }
            if (this.cParaInsertPending || this.cPreParaInsertPending) {
                this.doInsertPara();
                this.setStyle();
                this.setParaSettings((this.cInLI && this.cLIParas <= 1) ? ((char)this.cBulletStack.peek()) : '\0', false);
                final boolean b = false;
                this.cPreParaInsertPending = b;
                this.cParaInsertPending = b;
            }
            this.cTP.insert(this.cCollectedText.toString(), this.createFormatInfo());
            this.cCollectedText = new StringBuffer();
        }
    }
    
    private void insertImage(final String s, final String s2, final String s3, final String s4, final boolean b) {
        int n = 2;
        final Image imageFromString = super.cParent.getImageFromString(s);
        if (imageFromString == null) {
            return;
        }
        if (s3 != null && s3.indexOf("Limerick") != -1) {
            n = 3;
        }
        this.collectText(super.cParent.createEscapeData(s, s2, s3, s4, imageFromString, n));
    }
    
    static {
        cMaroon = new Color(8388608);
        cGreen = new Color(32768);
        cLime = new Color(65280);
        cTeal = new Color(32896);
        cOlive = new Color(8421376);
        cAqua = new Color(65535);
        cNavy = new Color(128);
        cPurple = new Color(8388736);
        cFuchsia = new Color(16711935);
        cSilver = new Color(12632256);
        CReadHtml.SPECIAL_TABLE = "V5DOTBL";
    }
}
