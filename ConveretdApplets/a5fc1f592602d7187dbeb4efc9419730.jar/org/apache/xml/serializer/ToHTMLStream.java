// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import org.apache.xml.res.XMLMessages;
import org.xml.sax.Attributes;
import java.io.Writer;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Properties;
import org.apache.xml.utils.Trie;

public class ToHTMLStream extends ToStream
{
    protected boolean m_inDTD;
    private boolean m_inBlockElem;
    protected static final CharInfo m_htmlcharInfo;
    static final Trie m_elementFlags;
    private static final ElemDesc m_dummy;
    private boolean m_specialEscapeURLs;
    private boolean m_omitMetaTag;
    
    public void setSpecialEscapeURLs(final boolean bool) {
        this.m_specialEscapeURLs = bool;
    }
    
    public void setOmitMetaTag(final boolean bool) {
        this.m_omitMetaTag = bool;
    }
    
    public void setOutputFormat(final Properties format) {
        this.m_specialEscapeURLs = OutputPropertyUtils.getBooleanProperty("{http://xml.apache.org/xalan}use-url-escaping", format);
        this.m_omitMetaTag = OutputPropertyUtils.getBooleanProperty("{http://xml.apache.org/xalan}omit-meta-tag", format);
        super.setOutputFormat(format);
    }
    
    private final boolean getSpecialEscapeURLs() {
        return this.m_specialEscapeURLs;
    }
    
    private final boolean getOmitMetaTag() {
        return this.m_omitMetaTag;
    }
    
    public static final ElemDesc getElemDesc(final String name) {
        final Object obj = ToHTMLStream.m_elementFlags.get(name);
        if (null != obj) {
            return (ElemDesc)obj;
        }
        return ToHTMLStream.m_dummy;
    }
    
    public ToHTMLStream() {
        this.m_inDTD = false;
        this.m_inBlockElem = false;
        this.m_specialEscapeURLs = true;
        this.m_omitMetaTag = false;
        super.m_charInfo = ToHTMLStream.m_htmlcharInfo;
        super.m_prefixMap = new NamespaceMappings();
    }
    
    protected void startDocumentInternal() throws SAXException {
        super.startDocumentInternal();
        super.m_needToCallStartDocument = false;
        super.m_needToOutputDocTypeDecl = true;
        super.m_startNewLine = false;
        this.setOmitXMLDeclaration(true);
        if (super.m_needToOutputDocTypeDecl) {
            final String doctypeSystem = this.getDoctypeSystem();
            final String doctypePublic = this.getDoctypePublic();
            if (null != doctypeSystem || null != doctypePublic) {
                final Writer writer = super.m_writer;
                try {
                    writer.write("<!DOCTYPE HTML");
                    if (null != doctypePublic) {
                        writer.write(" PUBLIC \"");
                        writer.write(doctypePublic);
                        writer.write(34);
                    }
                    if (null != doctypeSystem) {
                        if (null == doctypePublic) {
                            writer.write(" SYSTEM \"");
                        }
                        else {
                            writer.write(34);
                        }
                        writer.write(doctypeSystem);
                        writer.write(34);
                    }
                    writer.write(62);
                    this.outputLineSep();
                }
                catch (IOException e) {
                    throw new SAXException(e);
                }
            }
        }
        super.m_needToOutputDocTypeDecl = false;
    }
    
    public final void endDocument() throws SAXException {
        this.flushPending();
        if (super.m_doIndent && !super.m_isprevtext) {
            try {
                this.outputLineSep();
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
        }
        this.flushWriter();
        if (super.m_tracer != null) {
            super.fireEndDoc();
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        ElemContext elemContext = super.m_elemContext;
        if (elemContext.m_startTagOpen) {
            this.closeStartTag();
            elemContext.m_startTagOpen = false;
        }
        else if (super.m_cdataTagOpen) {
            this.closeCDATA();
            super.m_cdataTagOpen = false;
        }
        else if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        if (null != namespaceURI && namespaceURI.length() > 0) {
            super.startElement(namespaceURI, localName, name, atts);
            return;
        }
        try {
            final ElemDesc elemDesc = getElemDesc(name);
            final int elemFlags = elemDesc.getFlags();
            if (super.m_doIndent) {
                final boolean isBlockElement = (elemFlags & 0x8) != 0x0;
                if (super.m_ispreserve) {
                    super.m_ispreserve = false;
                }
                else if (null != elemContext.m_elementName && (!this.m_inBlockElem || isBlockElement)) {
                    super.m_startNewLine = true;
                    this.indent();
                }
                this.m_inBlockElem = !isBlockElement;
            }
            if (atts != null) {
                this.addAttributes(atts);
            }
            super.m_isprevtext = false;
            final Writer writer = super.m_writer;
            writer.write(60);
            writer.write(name);
            if (super.m_tracer != null) {
                this.firePseudoAttributes();
            }
            if ((elemFlags & 0x2) != 0x0) {
                super.m_elemContext = elemContext.push();
                super.m_elemContext.m_elementName = name;
                super.m_elemContext.m_elementDesc = elemDesc;
                return;
            }
            elemContext = elemContext.push(namespaceURI, localName, name);
            super.m_elemContext = elemContext;
            elemContext.m_elementDesc = elemDesc;
            elemContext.m_isRaw = ((elemFlags & 0x100) != 0x0);
            if ((elemFlags & 0x400000) != 0x0) {
                this.closeStartTag();
                elemContext.m_startTagOpen = false;
                if (!this.m_omitMetaTag) {
                    if (super.m_doIndent) {
                        this.indent();
                    }
                    writer.write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=");
                    final String encoding = this.getEncoding();
                    final String encode = Encodings.getMimeEncoding(encoding);
                    writer.write(encode);
                    writer.write("\">");
                }
            }
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public final void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
        if (super.m_cdataTagOpen) {
            this.closeCDATA();
        }
        if (null != namespaceURI && namespaceURI.length() > 0) {
            super.endElement(namespaceURI, localName, name);
            return;
        }
        try {
            final ElemContext elemContext = super.m_elemContext;
            final ElemDesc elemDesc = elemContext.m_elementDesc;
            final int elemFlags = elemDesc.getFlags();
            final boolean elemEmpty = (elemFlags & 0x2) != 0x0;
            if (super.m_doIndent) {
                final boolean isBlockElement = (elemFlags & 0x8) != 0x0;
                boolean shouldIndent = false;
                if (super.m_ispreserve) {
                    super.m_ispreserve = false;
                }
                else if (super.m_doIndent && (!this.m_inBlockElem || isBlockElement)) {
                    super.m_startNewLine = true;
                    shouldIndent = true;
                }
                if (!elemContext.m_startTagOpen && shouldIndent) {
                    this.indent(elemContext.m_currentElemDepth - 1);
                }
                this.m_inBlockElem = !isBlockElement;
            }
            final Writer writer = super.m_writer;
            if (!elemContext.m_startTagOpen) {
                writer.write("</");
                writer.write(name);
                writer.write(62);
            }
            else {
                if (super.m_tracer != null) {
                    super.fireStartElem(name);
                }
                final int nAttrs = super.m_attributes.getLength();
                if (nAttrs > 0) {
                    this.processAttributes(super.m_writer, nAttrs);
                    super.m_attributes.clear();
                }
                if (!elemEmpty) {
                    writer.write("></");
                    writer.write(name);
                    writer.write(62);
                }
                else {
                    writer.write(62);
                }
            }
            if ((elemFlags & 0x200000) != 0x0) {
                super.m_ispreserve = true;
            }
            super.m_isprevtext = false;
            if (super.m_tracer != null) {
                super.fireEndElem(name);
            }
            if (elemEmpty) {
                super.m_elemContext = elemContext.m_prev;
                return;
            }
            if (!elemContext.m_startTagOpen && super.m_doIndent && !super.m_preserves.isEmpty()) {
                super.m_preserves.pop();
            }
            super.m_elemContext = elemContext.m_prev;
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    protected void processAttribute(final Writer writer, final String name, final String value, final ElemDesc elemDesc) throws IOException {
        writer.write(32);
        if ((value.length() == 0 || value.equalsIgnoreCase(name)) && elemDesc != null && elemDesc.isAttrFlagSet(name, 4)) {
            writer.write(name);
        }
        else {
            writer.write(name);
            writer.write("=\"");
            if (elemDesc != null && elemDesc.isAttrFlagSet(name, 2)) {
                this.writeAttrURI(writer, value, this.m_specialEscapeURLs);
            }
            else {
                this.writeAttrString(writer, value, this.getEncoding());
            }
            writer.write(34);
        }
    }
    
    private boolean isASCIIDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private static String makeHHString(final int i) {
        String s = Integer.toHexString(i).toUpperCase();
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
    
    private boolean isHHSign(final String str) {
        boolean sign = true;
        try {
            final char r = (char)Integer.parseInt(str, 16);
        }
        catch (NumberFormatException e) {
            sign = false;
        }
        return sign;
    }
    
    public void writeAttrURI(final Writer writer, final String string, final boolean doURLEscaping) throws IOException {
        final int end = string.length();
        if (end > super.m_attrBuff.length) {
            super.m_attrBuff = new char[end * 2 + 1];
        }
        string.getChars(0, end, super.m_attrBuff, 0);
        final char[] chars = super.m_attrBuff;
        int cleanStart = 0;
        int cleanLength = 0;
        char ch = '\0';
        for (int i = 0; i < end; ++i) {
            ch = chars[i];
            if (ch < ' ' || ch > '~') {
                if (cleanLength > 0) {
                    writer.write(chars, cleanStart, cleanLength);
                    cleanLength = 0;
                }
                if (doURLEscaping) {
                    if (ch <= '\u007f') {
                        writer.write(37);
                        writer.write(makeHHString(ch));
                    }
                    else if (ch <= '\u07ff') {
                        final int high = ch >> 6 | '\u00c0';
                        final int low = (ch & '?') | '\u0080';
                        writer.write(37);
                        writer.write(makeHHString(high));
                        writer.write(37);
                        writer.write(makeHHString(low));
                    }
                    else if (ToStream.isUTF16Surrogate(ch)) {
                        final int highSurrogate = ch & '\u03ff';
                        final int wwww = (highSurrogate & 0x3C0) >> 6;
                        final int uuuuu = wwww + 1;
                        final int zzzz = (highSurrogate & 0x3C) >> 2;
                        int yyyyyy = (highSurrogate & 0x3) << 4 & 0x30;
                        ch = chars[++i];
                        final int lowSurrogate = ch & '\u03ff';
                        yyyyyy |= (lowSurrogate & 0x3C0) >> 6;
                        final int xxxxxx = lowSurrogate & 0x3F;
                        final int byte1 = 0xF0 | uuuuu >> 2;
                        final int byte2 = 0x80 | ((uuuuu & 0x3) << 4 & 0x30) | zzzz;
                        final int byte3 = 0x80 | yyyyyy;
                        final int byte4 = 0x80 | xxxxxx;
                        writer.write(37);
                        writer.write(makeHHString(byte1));
                        writer.write(37);
                        writer.write(makeHHString(byte2));
                        writer.write(37);
                        writer.write(makeHHString(byte3));
                        writer.write(37);
                        writer.write(makeHHString(byte4));
                    }
                    else {
                        final int high = ch >> 12 | '\u00e0';
                        final int middle = (ch & '\u0fc0') >> 6 | '\u0080';
                        final int low2 = (ch & '?') | '\u0080';
                        writer.write(37);
                        writer.write(makeHHString(high));
                        writer.write(37);
                        writer.write(makeHHString(middle));
                        writer.write(37);
                        writer.write(makeHHString(low2));
                    }
                }
                else if (this.escapingNotNeeded(ch)) {
                    writer.write(ch);
                }
                else {
                    writer.write("&#");
                    writer.write(Integer.toString(ch));
                    writer.write(59);
                }
                cleanStart = i + 1;
            }
            else if (ch == '\"') {
                if (cleanLength > 0) {
                    writer.write(chars, cleanStart, cleanLength);
                    cleanLength = 0;
                }
                if (doURLEscaping) {
                    writer.write("%22");
                }
                else {
                    writer.write("&quot;");
                }
                cleanStart = i + 1;
            }
            else {
                ++cleanLength;
            }
        }
        if (cleanLength > 1) {
            if (cleanStart == 0) {
                writer.write(string);
            }
            else {
                writer.write(chars, cleanStart, cleanLength);
            }
        }
        else if (cleanLength == 1) {
            writer.write(ch);
        }
    }
    
    public void writeAttrString(final Writer writer, final String string, final String encoding) throws IOException {
        final int end = string.length();
        if (end > super.m_attrBuff.length) {
            super.m_attrBuff = new char[end * 2 + 1];
        }
        string.getChars(0, end, super.m_attrBuff, 0);
        final char[] chars = super.m_attrBuff;
        int cleanStart = 0;
        int cleanLength = 0;
        char ch = '\0';
        for (int i = 0; i < end; ++i) {
            ch = chars[i];
            if (this.escapingNotNeeded(ch) && !super.m_charInfo.isSpecialAttrChar(ch)) {
                ++cleanLength;
            }
            else if ('<' == ch || '>' == ch) {
                ++cleanLength;
            }
            else if ('&' == ch && i + 1 < end && '{' == chars[i + 1]) {
                ++cleanLength;
            }
            else {
                if (cleanLength > 0) {
                    writer.write(chars, cleanStart, cleanLength);
                    cleanLength = 0;
                }
                final int pos = this.accumDefaultEntity(writer, ch, i, chars, end, false, false);
                if (i != pos) {
                    i = pos - 1;
                }
                else {
                    if (ToStream.isUTF16Surrogate(ch)) {
                        this.writeUTF16Surrogate(ch, chars, i, end);
                        ++i;
                    }
                    final String entityName = super.m_charInfo.getEntityNameForChar(ch);
                    if (null != entityName) {
                        writer.write(38);
                        writer.write(entityName);
                        writer.write(59);
                    }
                    else if (this.escapingNotNeeded(ch)) {
                        writer.write(ch);
                    }
                    else {
                        writer.write("&#");
                        writer.write(Integer.toString(ch));
                        writer.write(59);
                    }
                }
                cleanStart = i + 1;
            }
        }
        if (cleanLength > 1) {
            if (cleanStart == 0) {
                writer.write(string);
            }
            else {
                writer.write(chars, cleanStart, cleanLength);
            }
        }
        else if (cleanLength == 1) {
            writer.write(ch);
        }
    }
    
    public final void characters(final char[] chars, final int start, final int length) throws SAXException {
        if (super.m_elemContext.m_isRaw) {
            try {
                if (super.m_elemContext.m_startTagOpen) {
                    this.closeStartTag();
                    super.m_elemContext.m_startTagOpen = false;
                }
                super.m_ispreserve = true;
                this.writeNormalizedChars(chars, start, length, false, super.m_lineSepUse);
                if (super.m_tracer != null) {
                    super.fireCharEvent(chars, start, length);
                }
                return;
            }
            catch (IOException ioe) {
                throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe);
            }
        }
        super.characters(chars, start, length);
    }
    
    public final void cdata(final char[] ch, final int start, final int length) throws SAXException {
        Label_0115: {
            if (null != super.m_elemContext.m_elementName) {
                if (!super.m_elemContext.m_elementName.equalsIgnoreCase("SCRIPT")) {
                    if (!super.m_elemContext.m_elementName.equalsIgnoreCase("STYLE")) {
                        break Label_0115;
                    }
                }
                try {
                    if (super.m_elemContext.m_startTagOpen) {
                        this.closeStartTag();
                        super.m_elemContext.m_startTagOpen = false;
                    }
                    super.m_ispreserve = true;
                    if (this.shouldIndent()) {
                        this.indent();
                    }
                    this.writeNormalizedChars(ch, start, length, true, super.m_lineSepUse);
                }
                catch (IOException ioe) {
                    throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe);
                }
                return;
            }
        }
        super.cdata(ch, start, length);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushPending();
        if (target.equals("javax.xml.transform.disable-output-escaping")) {
            this.startNonEscaping();
        }
        else if (target.equals("javax.xml.transform.enable-output-escaping")) {
            this.endNonEscaping();
        }
        else {
            try {
                if (super.m_elemContext.m_startTagOpen) {
                    this.closeStartTag();
                    super.m_elemContext.m_startTagOpen = false;
                }
                else if (super.m_needToCallStartDocument) {
                    this.startDocumentInternal();
                }
                if (this.shouldIndent()) {
                    this.indent();
                }
                final Writer writer = super.m_writer;
                writer.write("<?");
                writer.write(target);
                if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0))) {
                    writer.write(32);
                }
                writer.write(data);
                writer.write(62);
                if (super.m_elemContext.m_currentElemDepth <= 0) {
                    this.outputLineSep();
                }
                super.m_startNewLine = true;
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
        }
        if (super.m_tracer != null) {
            super.fireEscapingEvent(target, data);
        }
    }
    
    public final void entityReference(final String name) throws SAXException {
        try {
            final Writer writer = super.m_writer;
            writer.write(38);
            writer.write(name);
            writer.write(59);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public final void endElement(final String elemName) throws SAXException {
        this.endElement(null, null, elemName);
    }
    
    public void processAttributes(final Writer writer, final int nAttrs) throws IOException, SAXException {
        for (int i = 0; i < nAttrs; ++i) {
            this.processAttribute(writer, super.m_attributes.getQName(i), super.m_attributes.getValue(i), super.m_elemContext.m_elementDesc);
        }
    }
    
    protected void closeStartTag() throws SAXException {
        try {
            if (super.m_tracer != null) {
                super.fireStartElem(super.m_elemContext.m_elementName);
            }
            final int nAttrs = super.m_attributes.getLength();
            if (nAttrs > 0) {
                this.processAttributes(super.m_writer, nAttrs);
                super.m_attributes.clear();
            }
            super.m_writer.write(62);
            if (super.m_cdataSectionElements != null) {
                super.m_elemContext.m_isCdataSection = this.isCdataSection();
            }
            if (super.m_doIndent) {
                super.m_isprevtext = false;
                super.m_preserves.push(super.m_ispreserve);
            }
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    protected synchronized void init(final OutputStream output, Properties format) throws UnsupportedEncodingException {
        if (null == format) {
            format = OutputPropertiesFactory.getDefaultMethodProperties("html");
        }
        super.init(output, format, false);
    }
    
    public void setOutputStream(final OutputStream output) {
        try {
            Properties format;
            if (null == super.m_format) {
                format = OutputPropertiesFactory.getDefaultMethodProperties("html");
            }
            else {
                format = super.m_format;
            }
            this.init(output, format, true);
        }
        catch (UnsupportedEncodingException ex) {}
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
        if (super.m_elemContext.m_elementURI == null) {
            final String prefix2 = SerializerBase.getPrefixPart(super.m_elemContext.m_elementName);
            if (prefix2 == null && "".equals(prefix)) {
                super.m_elemContext.m_elementURI = uri;
            }
        }
        this.startPrefixMapping(prefix, uri, false);
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        this.m_inDTD = true;
        super.startDTD(name, publicId, systemId);
    }
    
    public void endDTD() throws SAXException {
        this.m_inDTD = false;
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    public void addUniqueAttribute(final String name, final String value, final int flags) throws SAXException {
        try {
            final Writer writer = super.m_writer;
            if ((flags & 0x1) > 0 && ToHTMLStream.m_htmlcharInfo.onlyQuotAmpLtGt) {
                writer.write(32);
                writer.write(name);
                writer.write("=\"");
                writer.write(value);
                writer.write(34);
            }
            else if ((flags & 0x2) > 0 && (value.length() == 0 || value.equalsIgnoreCase(name))) {
                writer.write(32);
                writer.write(name);
            }
            else {
                writer.write(32);
                writer.write(name);
                writer.write("=\"");
                if ((flags & 0x4) > 0) {
                    this.writeAttrURI(writer, value, this.m_specialEscapeURLs);
                }
                else {
                    this.writeAttrString(writer, value, this.getEncoding());
                }
                writer.write(34);
            }
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this.m_inDTD) {
            return;
        }
        super.comment(ch, start, length);
    }
    
    public boolean reset() {
        final boolean ret = super.reset();
        if (!ret) {
            return false;
        }
        this.initToHTMLStream();
        return true;
    }
    
    private void initToHTMLStream() {
        this.m_inBlockElem = false;
        this.m_inDTD = false;
        this.m_omitMetaTag = false;
        this.m_specialEscapeURLs = true;
    }
    
    static {
        m_htmlcharInfo = CharInfo.getCharInfo(CharInfo.HTML_ENTITIES_RESOURCE, "html");
        (m_elementFlags = new Trie()).put("BASEFONT", new ElemDesc(2));
        ToHTMLStream.m_elementFlags.put("FRAME", new ElemDesc(10));
        ToHTMLStream.m_elementFlags.put("FRAMESET", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("NOFRAMES", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("ISINDEX", new ElemDesc(10));
        ToHTMLStream.m_elementFlags.put("APPLET", new ElemDesc(2097152));
        ToHTMLStream.m_elementFlags.put("CENTER", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("DIR", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("MENU", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("TT", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("I", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("B", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("BIG", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("SMALL", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("EM", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("STRONG", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("DFN", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("CODE", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("SAMP", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("KBD", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("VAR", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("CITE", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("ABBR", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("ACRONYM", new ElemDesc(8192));
        ToHTMLStream.m_elementFlags.put("SUP", new ElemDesc(98304));
        ToHTMLStream.m_elementFlags.put("SUB", new ElemDesc(98304));
        ToHTMLStream.m_elementFlags.put("SPAN", new ElemDesc(98304));
        ToHTMLStream.m_elementFlags.put("BDO", new ElemDesc(98304));
        ToHTMLStream.m_elementFlags.put("BR", new ElemDesc(98314));
        ToHTMLStream.m_elementFlags.put("BODY", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("ADDRESS", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("DIV", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("A", new ElemDesc(32768));
        ToHTMLStream.m_elementFlags.put("MAP", new ElemDesc(98312));
        ToHTMLStream.m_elementFlags.put("AREA", new ElemDesc(10));
        ToHTMLStream.m_elementFlags.put("LINK", new ElemDesc(131082));
        ToHTMLStream.m_elementFlags.put("IMG", new ElemDesc(2195458));
        ToHTMLStream.m_elementFlags.put("OBJECT", new ElemDesc(2326528));
        ToHTMLStream.m_elementFlags.put("PARAM", new ElemDesc(2));
        ToHTMLStream.m_elementFlags.put("HR", new ElemDesc(58));
        ToHTMLStream.m_elementFlags.put("P", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("H1", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("H2", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("H3", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("H4", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("H5", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("H6", new ElemDesc(262152));
        ToHTMLStream.m_elementFlags.put("PRE", new ElemDesc(1048584));
        ToHTMLStream.m_elementFlags.put("Q", new ElemDesc(98304));
        ToHTMLStream.m_elementFlags.put("BLOCKQUOTE", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("INS", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("DEL", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("DL", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("DT", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("DD", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("OL", new ElemDesc(524296));
        ToHTMLStream.m_elementFlags.put("UL", new ElemDesc(524296));
        ToHTMLStream.m_elementFlags.put("LI", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("FORM", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("LABEL", new ElemDesc(16384));
        ToHTMLStream.m_elementFlags.put("INPUT", new ElemDesc(18434));
        ToHTMLStream.m_elementFlags.put("SELECT", new ElemDesc(18432));
        ToHTMLStream.m_elementFlags.put("OPTGROUP", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("OPTION", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("TEXTAREA", new ElemDesc(18432));
        ToHTMLStream.m_elementFlags.put("FIELDSET", new ElemDesc(24));
        ToHTMLStream.m_elementFlags.put("LEGEND", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("BUTTON", new ElemDesc(18432));
        ToHTMLStream.m_elementFlags.put("TABLE", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("CAPTION", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("THEAD", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("TFOOT", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("TBODY", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("COLGROUP", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("COL", new ElemDesc(10));
        ToHTMLStream.m_elementFlags.put("TR", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("TH", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("TD", new ElemDesc(0));
        ToHTMLStream.m_elementFlags.put("HEAD", new ElemDesc(4194312));
        ToHTMLStream.m_elementFlags.put("TITLE", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("BASE", new ElemDesc(10));
        ToHTMLStream.m_elementFlags.put("META", new ElemDesc(131082));
        ToHTMLStream.m_elementFlags.put("STYLE", new ElemDesc(131336));
        ToHTMLStream.m_elementFlags.put("SCRIPT", new ElemDesc(229632));
        ToHTMLStream.m_elementFlags.put("NOSCRIPT", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("HTML", new ElemDesc(8));
        ToHTMLStream.m_elementFlags.put("FONT", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("S", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("STRIKE", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("U", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("NOBR", new ElemDesc(4096));
        ToHTMLStream.m_elementFlags.put("IFRAME", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("LAYER", new ElemDesc(56));
        ToHTMLStream.m_elementFlags.put("ILAYER", new ElemDesc(56));
        ElemDesc elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("AREA");
        elemDesc.setAttr("HREF", 2);
        elemDesc.setAttr("NOHREF", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("BASE");
        elemDesc.setAttr("HREF", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("BLOCKQUOTE");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("Q");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("INS");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("DEL");
        elemDesc.setAttr("CITE", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("A");
        elemDesc.setAttr("HREF", 2);
        elemDesc.setAttr("NAME", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("LINK");
        elemDesc.setAttr("HREF", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("INPUT");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc.setAttr("CHECKED", 4);
        elemDesc.setAttr("DISABLED", 4);
        elemDesc.setAttr("ISMAP", 4);
        elemDesc.setAttr("READONLY", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("SELECT");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc.setAttr("MULTIPLE", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("OPTGROUP");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("OPTION");
        elemDesc.setAttr("SELECTED", 4);
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("TEXTAREA");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc.setAttr("READONLY", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("BUTTON");
        elemDesc.setAttr("DISABLED", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("SCRIPT");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("FOR", 2);
        elemDesc.setAttr("DEFER", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("IMG");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("LONGDESC", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc.setAttr("ISMAP", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("OBJECT");
        elemDesc.setAttr("CLASSID", 2);
        elemDesc.setAttr("CODEBASE", 2);
        elemDesc.setAttr("DATA", 2);
        elemDesc.setAttr("ARCHIVE", 2);
        elemDesc.setAttr("USEMAP", 2);
        elemDesc.setAttr("DECLARE", 4);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("FORM");
        elemDesc.setAttr("ACTION", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("HEAD");
        elemDesc.setAttr("PROFILE", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("FRAME");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("LONGDESC", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("IFRAME");
        elemDesc.setAttr("SRC", 2);
        elemDesc.setAttr("LONGDESC", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("LAYER");
        elemDesc.setAttr("SRC", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("ILAYER");
        elemDesc.setAttr("SRC", 2);
        elemDesc = (ElemDesc)ToHTMLStream.m_elementFlags.get("DIV");
        elemDesc.setAttr("SRC", 2);
        m_dummy = new ElemDesc(8);
    }
}
