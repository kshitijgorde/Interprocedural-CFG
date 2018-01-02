// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.xml.sax.Attributes;
import org.apache.xalan.templates.OutputProperties;
import java.util.Properties;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.Trie;
import org.apache.xml.utils.BoolStack;

public class SerializerToHTML extends SerializerToXML
{
    private BoolStack m_isRawStack;
    private boolean m_inBlockElem;
    protected static CharInfo m_htmlcharInfo;
    static Trie m_elementFlags;
    private static ElemDesc m_dummy;
    private boolean m_specialEscapeURLs;
    private String m_currentElementName;
    
    static {
        SerializerToHTML.m_htmlcharInfo = new CharInfo(CharInfo.HTML_ENTITIES_RESOURCE);
        (SerializerToHTML.m_elementFlags = new Trie()).put("BASEFONT", new ElemDesc(2));
        SerializerToHTML.m_elementFlags.put("FRAME", new ElemDesc(10));
        SerializerToHTML.m_elementFlags.put("FRAMESET", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("NOFRAMES", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("ISINDEX", new ElemDesc(10));
        SerializerToHTML.m_elementFlags.put("APPLET", new ElemDesc(2097152));
        SerializerToHTML.m_elementFlags.put("CENTER", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("DIR", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("MENU", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("TT", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("I", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("B", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("BIG", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("SMALL", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("EM", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("STRONG", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("DFN", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("CODE", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("SAMP", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("KBD", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("VAR", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("CITE", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("ABBR", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("ACRONYM", new ElemDesc(8192));
        SerializerToHTML.m_elementFlags.put("SUP", new ElemDesc(98304));
        SerializerToHTML.m_elementFlags.put("SUB", new ElemDesc(98304));
        SerializerToHTML.m_elementFlags.put("SPAN", new ElemDesc(98304));
        SerializerToHTML.m_elementFlags.put("BDO", new ElemDesc(98304));
        SerializerToHTML.m_elementFlags.put("BR", new ElemDesc(98314));
        SerializerToHTML.m_elementFlags.put("BODY", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("ADDRESS", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("DIV", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("A", new ElemDesc(32768));
        SerializerToHTML.m_elementFlags.put("MAP", new ElemDesc(98312));
        SerializerToHTML.m_elementFlags.put("AREA", new ElemDesc(10));
        SerializerToHTML.m_elementFlags.put("LINK", new ElemDesc(131082));
        SerializerToHTML.m_elementFlags.put("IMG", new ElemDesc(2195458));
        SerializerToHTML.m_elementFlags.put("OBJECT", new ElemDesc(2326528));
        SerializerToHTML.m_elementFlags.put("PARAM", new ElemDesc(2));
        SerializerToHTML.m_elementFlags.put("HR", new ElemDesc(58));
        SerializerToHTML.m_elementFlags.put("P", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("H1", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("H2", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("H3", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("H4", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("H5", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("H6", new ElemDesc(262152));
        SerializerToHTML.m_elementFlags.put("PRE", new ElemDesc(1048584));
        SerializerToHTML.m_elementFlags.put("Q", new ElemDesc(98304));
        SerializerToHTML.m_elementFlags.put("BLOCKQUOTE", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("INS", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("DEL", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("DL", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("DT", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("DD", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("OL", new ElemDesc(524296));
        SerializerToHTML.m_elementFlags.put("UL", new ElemDesc(524296));
        SerializerToHTML.m_elementFlags.put("LI", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("FORM", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("LABEL", new ElemDesc(16384));
        SerializerToHTML.m_elementFlags.put("INPUT", new ElemDesc(18434));
        SerializerToHTML.m_elementFlags.put("SELECT", new ElemDesc(18432));
        SerializerToHTML.m_elementFlags.put("OPTGROUP", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("OPTION", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("TEXTAREA", new ElemDesc(18432));
        SerializerToHTML.m_elementFlags.put("FIELDSET", new ElemDesc(24));
        SerializerToHTML.m_elementFlags.put("LEGEND", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("BUTTON", new ElemDesc(18432));
        SerializerToHTML.m_elementFlags.put("TABLE", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("CAPTION", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("THEAD", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("TFOOT", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("TBODY", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("COLGROUP", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("COL", new ElemDesc(10));
        SerializerToHTML.m_elementFlags.put("TR", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("TH", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("TD", new ElemDesc(0));
        SerializerToHTML.m_elementFlags.put("HEAD", new ElemDesc(4194312));
        SerializerToHTML.m_elementFlags.put("TITLE", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("BASE", new ElemDesc(10));
        SerializerToHTML.m_elementFlags.put("META", new ElemDesc(131082));
        SerializerToHTML.m_elementFlags.put("STYLE", new ElemDesc(131336));
        SerializerToHTML.m_elementFlags.put("SCRIPT", new ElemDesc(229632));
        SerializerToHTML.m_elementFlags.put("NOSCRIPT", new ElemDesc(56));
        SerializerToHTML.m_elementFlags.put("HTML", new ElemDesc(8));
        SerializerToHTML.m_elementFlags.put("FONT", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("S", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("STRIKE", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("U", new ElemDesc(4096));
        SerializerToHTML.m_elementFlags.put("NOBR", new ElemDesc(4096));
        ElemDesc elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("BASE");
        elemDesc.setAttr("HREF", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("BLOCKQUOTE");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("Q");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("INS");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("DEL");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("A");
        elemDesc.setAttr("HREF", 2);
        elemDesc.setAttr("NAME", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("INPUT");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc.setAttr("CHECKED", 4);
        elemDesc.setAttr("DISABLED", 4);
        elemDesc.setAttr("READONLY", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("SELECT");
        elemDesc.setAttr("READONLY", 4);
        elemDesc.setAttr("MULTIPLE", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("OPTGROUP");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("OPTION");
        elemDesc.setAttr("SELECTED", 4);
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("TEXTAREA");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc.setAttr("READONLY", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("BUTTON");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("SCRIPT");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("FOR", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("IMG");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("LONGDESC", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("OBJECT");
        elemDesc.setAttr("CLASSID", 2);
        elemDesc.setAttr("CODEBASE", 2);
        elemDesc.setAttr("DATA", 2);
        elemDesc.setAttr("ARCHIVE", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("FORM");
        elemDesc.setAttr("ACTION", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("HEAD");
        elemDesc.setAttr("PROFILE", 2);
        elemDesc = (ElemDesc)SerializerToHTML.m_elementFlags.get("FRAME");
        elemDesc.setAttr("SRC", 2);
        SerializerToHTML.m_dummy = new ElemDesc(8);
    }
    
    public SerializerToHTML() {
        this.m_isRawStack = new BoolStack();
        this.m_inBlockElem = false;
        this.m_specialEscapeURLs = true;
        this.m_currentElementName = null;
        super.m_charInfo = SerializerToHTML.m_htmlcharInfo;
    }
    
    public void cdata(final char[] ch, final int start, final int length) throws SAXException {
        Label_0084: {
            if (this.m_currentElementName != null) {
                if (!this.m_currentElementName.equalsIgnoreCase("SCRIPT")) {
                    if (!this.m_currentElementName.equalsIgnoreCase("STYLE")) {
                        break Label_0084;
                    }
                }
                try {
                    this.writeParentTagEnd();
                    super.m_ispreserve = true;
                    if (this.shouldIndent()) {
                        this.indent(super.m_currentIndent);
                    }
                    this.writeNormalizedChars(ch, start, length, true);
                    return;
                }
                catch (IOException ioe) {
                    throw new SAXException(XSLMessages.createXPATHMessage(66, null), ioe);
                }
            }
        }
        super.cdata(ch, start, length);
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        if (this.m_isRawStack.peekOrFalse()) {
            try {
                this.writeParentTagEnd();
                super.m_ispreserve = true;
                if (this.shouldIndent()) {
                    this.indent(super.m_currentIndent);
                }
                this.writeNormalizedChars(chars, start, length, false);
                return;
            }
            catch (IOException ioe) {
                throw new SAXException(XSLMessages.createXPATHMessage(66, null), ioe);
            }
        }
        super.characters(chars, start, length);
    }
    
    private int copyEntityIntoBuf(final String s, final int pos) throws SAXException {
        final int l = s.length();
        this.accum('&');
        for (int i = 0; i < l; ++i) {
            this.accum(s.charAt(i));
        }
        this.accum(';');
        return pos;
    }
    
    public void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            super.endElement(namespaceURI, localName, name);
            return;
        }
        super.m_currentIndent -= super.m_indentAmount;
        final boolean hasChildNodes = this.childNodesWereAdded();
        this.m_isRawStack.pop();
        final ElemDesc elemDesc = this.getElemDesc(name);
        final boolean isBlockElement = elemDesc.is(8);
        boolean shouldIndent = false;
        if (super.m_ispreserve) {
            super.m_ispreserve = false;
        }
        else if (super.m_doIndent && (!this.m_inBlockElem || isBlockElement)) {
            super.m_startNewLine = true;
            shouldIndent = true;
        }
        this.m_inBlockElem = (isBlockElement ^ true);
        if (hasChildNodes) {
            if (shouldIndent) {
                this.indent(super.m_currentIndent);
            }
            this.accum("</");
            this.accum(name);
            this.accum('>');
            this.m_currentElementName = name;
        }
        else if (!elemDesc.is(2)) {
            this.accum('>');
            this.accum('<');
            this.accum('/');
            this.accum(name);
            this.accum('>');
        }
        else {
            this.accum('>');
        }
        if (elemDesc.is(2097152)) {
            super.m_ispreserve = true;
        }
        if (hasChildNodes && !super.m_preserves.isEmpty()) {
            super.m_preserves.pop();
        }
        super.m_isprevtext = false;
        super.m_cdataSectionStates.pop();
    }
    
    public void entityReference(final String name) throws SAXException {
        this.accum("&");
        this.accum(name);
        this.accum(";");
    }
    
    ElemDesc getElemDesc(final String name) {
        if (name != null) {
            final Object obj = SerializerToHTML.m_elementFlags.get(name);
            if (obj != null) {
                return (ElemDesc)obj;
            }
        }
        return SerializerToHTML.m_dummy;
    }
    
    public boolean getSpecialEscapeURLs() {
        return this.m_specialEscapeURLs;
    }
    
    private boolean isASCIIDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private String makeHHString(final int i) {
        String s = Integer.toHexString(i).toUpperCase();
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
    
    protected void processAttribute(final String name, final ElemDesc elemDesc, final String value) throws SAXException {
        this.accum(' ');
        if ((value.length() == 0 || value.equalsIgnoreCase(name)) && elemDesc.isAttrFlagSet(name, 4)) {
            this.accum(name);
        }
        else {
            this.accum(name);
            this.accum('=');
            this.accum('\"');
            if (elemDesc.isAttrFlagSet(name, 2)) {
                this.writeAttrURI(value, this.m_specialEscapeURLs);
            }
            else {
                this.writeAttrString(value, super.m_encoding);
            }
            this.accum('\"');
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (target.equals("javax.xml.transform.disable-output-escaping")) {
            this.startNonEscaping();
        }
        else if (target.equals("javax.xml.transform.enable-output-escaping")) {
            this.endNonEscaping();
        }
        else {
            this.writeParentTagEnd();
            if (this.shouldIndent()) {
                this.indent(super.m_currentIndent);
            }
            this.accum("<?" + target);
            if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0))) {
                this.accum(" ");
            }
            this.accum(String.valueOf(data) + ">");
            if (super.m_elemStack.isEmpty()) {
                this.outputLineSep();
            }
            super.m_startNewLine = true;
        }
    }
    
    public void setOutputFormat(final Properties format) {
        this.m_specialEscapeURLs = OutputProperties.getBooleanProperty(OutputProperties.S_USE_URL_ESCAPING, format);
        super.setOutputFormat(format);
    }
    
    public void setSpecialEscapeURLs(final boolean bool) {
        this.m_specialEscapeURLs = bool;
    }
    
    public void startDocument() throws SAXException {
        super.m_needToOutputDocTypeDecl = true;
        super.m_startNewLine = false;
        super.m_shouldNotWriteXMLHeader = true;
        if (super.m_needToOutputDocTypeDecl && (super.m_doctypeSystem != null || super.m_doctypePublic != null)) {
            this.accum("<!DOCTYPE HTML");
            if (super.m_doctypePublic != null) {
                this.accum(" PUBLIC \"");
                this.accum(super.m_doctypePublic);
                this.accum("\"");
            }
            if (super.m_doctypeSystem != null) {
                if (super.m_doctypePublic == null) {
                    this.accum(" SYSTEM \"");
                }
                else {
                    this.accum(" \"");
                }
                this.accum(super.m_doctypeSystem);
                this.accum("\"");
            }
            this.accum(">");
            this.accum(super.m_lineSep);
        }
        super.m_needToOutputDocTypeDecl = false;
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            super.startElement(namespaceURI, localName, name, atts);
            return;
        }
        final boolean savedDoIndent = super.m_doIndent;
        this.writeParentTagEnd();
        this.pushState(namespaceURI, localName, super.m_cdataSectionNames, super.m_cdataSectionStates);
        final ElemDesc elemDesc = this.getElemDesc(name);
        final boolean isBlockElement = elemDesc.is(8);
        final boolean isHeadElement = elemDesc.is(4194304);
        if (super.m_ispreserve) {
            super.m_ispreserve = false;
        }
        else if (super.m_doIndent && this.m_currentElementName != null && (!this.m_inBlockElem || isBlockElement)) {
            super.m_startNewLine = true;
            this.indent(super.m_currentIndent);
        }
        this.m_inBlockElem = (isBlockElement ^ true);
        this.m_isRawStack.push(elemDesc.is(256));
        this.m_currentElementName = name;
        this.accum('<');
        this.accum(name);
        for (int nAttrs = atts.getLength(), i = 0; i < nAttrs; ++i) {
            this.processAttribute(atts.getQName(i), elemDesc, atts.getValue(i));
        }
        this.openElementForChildren();
        super.m_currentIndent += super.m_indentAmount;
        super.m_isprevtext = false;
        super.m_doIndent = savedDoIndent;
        if (isHeadElement) {
            this.writeParentTagEnd();
            if (super.m_doIndent) {
                this.indent(super.m_currentIndent);
            }
            this.accum("<META http-equiv=\"Content-Type\" content=\"text/html; charset=");
            final String encoding = Encodings.getMimeEncoding(super.m_encoding);
            this.accum(encoding);
            this.accum('\"');
            this.accum('>');
        }
    }
    
    public void writeAttrString(final String string, final String encoding) throws SAXException {
        final char[] chars = string.toCharArray();
        for (int strLen = chars.length, i = 0; i < strLen; ++i) {
            final char ch = chars[i];
            if (ch < super.m_maxCharacter && !super.m_charInfo.isSpecial(ch)) {
                this.accum(ch);
            }
            else if (ch == '<' || ch == '>') {
                this.accum(ch);
            }
            else if (ch == '&' && i + 1 < strLen && chars[i + 1] == '{') {
                this.accum(ch);
            }
            else {
                final int pos = this.accumDefaultEntity(ch, i, chars, strLen, false);
                if (i != pos) {
                    i = pos - 1;
                }
                else {
                    if (SerializerToXML.isUTF16Surrogate(ch)) {
                        try {
                            i = this.writeUTF16Surrogate(ch, chars, i, strLen);
                        }
                        catch (IOException ioe) {
                            throw new SAXException(ioe);
                        }
                    }
                    final String entityName = super.m_charInfo.getEntityNameForChar(ch);
                    if (entityName != null) {
                        this.accum('&');
                        this.accum(entityName);
                        this.accum(';');
                    }
                    else if (ch < super.m_maxCharacter) {
                        this.accum(ch);
                    }
                    else if (ch < super.m_maxCharacter) {
                        this.accum(ch);
                    }
                    else {
                        this.accum("&#");
                        this.accum(Integer.toString(ch));
                        this.accum(';');
                    }
                }
            }
        }
    }
    
    public void writeAttrURI(final String string, final boolean doURLEscaping) throws SAXException {
        final char[] stringArray = string.toCharArray();
        for (int len = stringArray.length, i = 0; i < len; ++i) {
            char ch = stringArray[i];
            if (ch < '!' || ch > '~') {
                if (doURLEscaping) {
                    if (ch <= '\u007f') {
                        this.accum('%');
                        this.accum(this.makeHHString(ch));
                    }
                    else if (ch <= '\u07ff') {
                        final int high = ch >> 6 | '\u00c0';
                        final int low = (ch & '?') | '\u0080';
                        this.accum('%');
                        this.accum(this.makeHHString(high));
                        this.accum('%');
                        this.accum(this.makeHHString(low));
                    }
                    else if (SerializerToXML.isUTF16Surrogate(ch)) {
                        final int highSurrogate = ch & '\u03ff';
                        final int wwww = (highSurrogate & 0x3C0) >> 6;
                        final int uuuuu = wwww + 1;
                        final int zzzz = (highSurrogate & 0x3C) >> 2;
                        int yyyyyy = (highSurrogate & 0x3) << 4 & 0x30;
                        ch = stringArray[++i];
                        final int lowSurrogate = ch & '\u03ff';
                        yyyyyy |= (lowSurrogate & 0x3C0) >> 6;
                        final int xxxxxx = lowSurrogate & 0x3F;
                        final int byte1 = 0xF0 | uuuuu >> 2;
                        final int byte2 = 0x80 | ((uuuuu & 0x3) << 4 & 0x30) | zzzz;
                        final int byte3 = 0x80 | yyyyyy;
                        final int byte4 = 0x80 | xxxxxx;
                        this.accum('%');
                        this.accum(this.makeHHString(byte1));
                        this.accum('%');
                        this.accum(this.makeHHString(byte2));
                        this.accum('%');
                        this.accum(this.makeHHString(byte3));
                        this.accum('%');
                        this.accum(this.makeHHString(byte4));
                    }
                    else {
                        final int high = ch >> 12 | '\u00e0';
                        final int middle = (ch & '\u0fc0') >> 6 | '\u0080';
                        final int low2 = (ch & '?') | '\u0080';
                        this.accum('%');
                        this.accum(this.makeHHString(high));
                        this.accum('%');
                        this.accum(this.makeHHString(middle));
                        this.accum('%');
                        this.accum(this.makeHHString(low2));
                    }
                }
                else if (ch < super.m_maxCharacter) {
                    this.accum(ch);
                }
                else {
                    this.accum("&#");
                    this.accum(Integer.toString(ch));
                    this.accum(';');
                }
            }
            else if (ch == '%') {
                if (i + 2 < len && this.isASCIIDigit(stringArray[i + 1]) && this.isASCIIDigit(stringArray[i + 2])) {
                    this.accum(ch);
                }
                else if (doURLEscaping) {
                    this.accum('%');
                    this.accum(this.makeHHString(ch));
                }
                else {
                    this.accum(ch);
                }
            }
            else if (ch == '\"') {
                if (doURLEscaping) {
                    this.accum("%22");
                }
                else {
                    this.accum("&quot;");
                }
            }
            else {
                this.accum(ch);
            }
        }
    }
}
