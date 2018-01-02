// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.apache.xml.utils.TreeWalker;
import org.w3c.dom.Node;
import org.apache.xml.utils.QName;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.utils.SystemIDResolver;
import java.io.UnsupportedEncodingException;
import org.apache.xalan.templates.OutputProperties;
import org.apache.xalan.res.XSLMessages;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.xml.utils.BoolStack;
import java.io.OutputStream;
import java.io.Writer;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class SerializerToXML implements ContentHandler, LexicalHandler, Serializer, DOMSerializer
{
    protected Writer m_writer;
    boolean m_shouldFlush;
    protected OutputStream m_outputStream;
    private boolean m_bytesEqualChars;
    protected String m_encoding;
    static boolean javaEncodingIsISO;
    public boolean m_shouldNotWriteXMLHeader;
    public String m_version;
    protected BoolStack m_elemStack;
    protected BoolStack m_disableOutputEscapingStates;
    protected BoolStack m_cdataSectionStates;
    protected Vector m_cdataSectionNames;
    protected boolean m_inCData;
    protected final String m_lineSep;
    protected final int m_lineSepLen;
    protected boolean m_ispreserve;
    protected BoolStack m_preserves;
    protected boolean m_isprevtext;
    protected boolean m_doIndent;
    protected int m_currentIndent;
    public int m_indentAmount;
    protected int level;
    boolean m_startNewLine;
    boolean m_needToOutputDocTypeDecl;
    String m_doctypeSystem;
    String m_doctypePublic;
    boolean m_standalone;
    boolean m_standaloneWasSpecified;
    String m_mediatype;
    protected boolean m_inEntityRef;
    protected static CharInfo m_xmlcharInfo;
    protected CharInfo m_charInfo;
    private static Hashtable m_charInfos;
    boolean m_isUTF8;
    int m_maxCharacter;
    public boolean m_spaceBeforeClose;
    protected Properties m_format;
    private static final boolean DEBUG = false;
    static final int MAXCHARBUF = 4096;
    static final int NUMBERBYTESTOWRITEDIRECT = 1024;
    protected char[] m_charBuf;
    protected byte[] m_byteBuf;
    protected int m_pos;
    
    static {
        SerializerToXML.javaEncodingIsISO = false;
        SerializerToXML.m_xmlcharInfo = new CharInfo(CharInfo.XML_ENTITIES_RESOURCE);
        SerializerToXML.m_charInfos = null;
    }
    
    public SerializerToXML() {
        this.m_writer = null;
        this.m_shouldFlush = true;
        this.m_outputStream = System.out;
        this.m_bytesEqualChars = false;
        this.m_encoding = null;
        this.m_shouldNotWriteXMLHeader = false;
        this.m_version = null;
        this.m_elemStack = new BoolStack();
        this.m_disableOutputEscapingStates = new BoolStack();
        this.m_cdataSectionStates = new BoolStack();
        this.m_cdataSectionNames = null;
        this.m_inCData = false;
        this.m_lineSep = System.getProperty("line.separator");
        this.m_lineSepLen = this.m_lineSep.length();
        this.m_ispreserve = false;
        this.m_preserves = new BoolStack();
        this.m_isprevtext = false;
        this.m_doIndent = false;
        this.m_currentIndent = 0;
        this.m_indentAmount = 0;
        this.level = 0;
        this.m_needToOutputDocTypeDecl = true;
        this.m_standalone = false;
        this.m_standaloneWasSpecified = false;
        this.m_inEntityRef = false;
        this.m_maxCharacter = Encodings.getLastPrintable();
        this.m_spaceBeforeClose = false;
        this.m_charBuf = new char[4096];
        this.m_byteBuf = new byte[4096];
        this.m_pos = 0;
        this.m_charInfo = SerializerToXML.m_xmlcharInfo;
    }
    
    public void CopyFrom(final SerializerToXML xmlListener) {
        this.m_writer = xmlListener.m_writer;
        this.m_outputStream = xmlListener.m_outputStream;
        this.m_bytesEqualChars = xmlListener.m_bytesEqualChars;
        this.m_encoding = xmlListener.m_encoding;
        SerializerToXML.javaEncodingIsISO = SerializerToXML.javaEncodingIsISO;
        this.m_shouldNotWriteXMLHeader = xmlListener.m_shouldNotWriteXMLHeader;
        this.m_shouldNotWriteXMLHeader = xmlListener.m_shouldNotWriteXMLHeader;
        this.m_elemStack = xmlListener.m_elemStack;
        this.m_ispreserve = xmlListener.m_ispreserve;
        this.m_preserves = xmlListener.m_preserves;
        this.m_isprevtext = xmlListener.m_isprevtext;
        this.m_doIndent = xmlListener.m_doIndent;
        this.m_currentIndent = xmlListener.m_currentIndent;
        this.m_indentAmount = xmlListener.m_indentAmount;
        this.level = xmlListener.level;
        this.m_startNewLine = xmlListener.m_startNewLine;
        this.m_needToOutputDocTypeDecl = xmlListener.m_needToOutputDocTypeDecl;
        this.m_doctypeSystem = xmlListener.m_doctypeSystem;
        this.m_doctypePublic = xmlListener.m_doctypePublic;
        this.m_standalone = xmlListener.m_standalone;
        this.m_mediatype = xmlListener.m_mediatype;
        this.m_maxCharacter = xmlListener.m_maxCharacter;
        this.m_spaceBeforeClose = xmlListener.m_spaceBeforeClose;
        this.m_inCData = xmlListener.m_inCData;
        this.m_charBuf = xmlListener.m_charBuf;
        this.m_byteBuf = xmlListener.m_byteBuf;
        this.m_pos = 0;
    }
    
    protected final void accum(final byte b) throws SAXException {
        if (this.m_bytesEqualChars) {
            this.m_byteBuf[this.m_pos++] = b;
            if (this.m_pos >= 4096) {
                this.flushBytes();
            }
        }
        else {
            this.m_charBuf[this.m_pos++] = (char)b;
            if (this.m_pos >= 4096) {
                this.flushChars();
            }
        }
    }
    
    protected final void accum(final char b) throws SAXException {
        if (this.m_bytesEqualChars) {
            this.m_byteBuf[this.m_pos++] = (byte)b;
            if (this.m_pos >= 4096) {
                this.flushBytes();
            }
        }
        else {
            this.m_charBuf[this.m_pos++] = b;
            if (this.m_pos >= 4096) {
                this.flushChars();
            }
        }
    }
    
    protected final void accum(final String s) throws SAXException {
        final int n = s.length();
        if (this.m_bytesEqualChars) {
            final char[] chars = s.toCharArray();
            for (int i = 0; i < n; ++i) {
                this.m_byteBuf[this.m_pos++] = (byte)chars[i];
                if (this.m_pos >= 4096) {
                    this.flushBytes();
                }
            }
        }
        else {
            if (n >= 1024) {
                if (this.m_pos != 0) {
                    this.flushChars();
                }
                try {
                    this.m_writer.write(s);
                    return;
                }
                catch (IOException ioe) {
                    throw new SAXException(ioe);
                }
            }
            for (int j = 0; j < n; ++j) {
                this.m_charBuf[this.m_pos++] = s.charAt(j);
                if (this.m_pos >= 4096) {
                    this.flushChars();
                }
            }
        }
    }
    
    protected final void accum(final char[] chars, final int start, final int length) throws SAXException {
        final int n = start + length;
        if (this.m_bytesEqualChars) {
            for (int i = start; i < n; ++i) {
                this.m_byteBuf[this.m_pos++] = (byte)chars[i];
                if (this.m_pos >= 4096) {
                    this.flushBytes();
                }
            }
        }
        else {
            if (length >= 1024) {
                if (this.m_pos != 0) {
                    this.flushChars();
                }
                try {
                    this.m_writer.write(chars, start, length);
                    return;
                }
                catch (IOException ioe) {
                    throw new SAXException(ioe);
                }
            }
            if (this.m_pos + length >= 4096) {
                this.flushChars();
            }
            System.arraycopy(chars, start, this.m_charBuf, this.m_pos, length);
            this.m_pos += length;
        }
    }
    
    final int accumDefaultEntity(final char ch, final int i, final char[] chars, final int len, final boolean escLF) throws SAXException {
        if (!escLF && CharInfo.S_LINEFEED == ch) {
            this.outputLineSep();
        }
        else {
            if (!this.m_charInfo.isSpecial(ch)) {
                return i;
            }
            final String entityRef = this.m_charInfo.getEntityNameForChar(ch);
            if (entityRef == null) {
                return i;
            }
            this.accum('&');
            this.accum(entityRef);
            this.accum(';');
        }
        return i + 1;
    }
    
    final int accumDefaultEscape(final char ch, int i, final char[] chars, final int len, final boolean escLF) throws SAXException {
        int pos = this.accumDefaultEntity(ch, i, chars, len, escLF);
        if (i == pos) {
            ++pos;
            if (ch >= '\ud800' && ch < '\udc00') {
                if (i + 1 >= len) {
                    throw new SAXException(XSLMessages.createXPATHMessage(65, new Object[] { Integer.toHexString(ch) }));
                }
                int next = chars[++i];
                if (next < 56320 || next >= 57344) {
                    throw new SAXException(XSLMessages.createXPATHMessage(65, new Object[] { String.valueOf(Integer.toHexString(ch)) + " " + Integer.toHexString(next) }));
                }
                next = (ch - '\ud800' << 10) + next - 56320 + 65536;
                this.accum("&#");
                this.accum(Integer.toString(next));
                this.accum(";");
            }
            else if (ch > this.m_maxCharacter || this.m_charInfo.isSpecial(ch)) {
                this.accum("&#");
                this.accum(Integer.toString(ch));
                this.accum(";");
            }
            else {
                this.accum(ch);
            }
        }
        return pos;
    }
    
    public ContentHandler asContentHandler() throws IOException {
        return this;
    }
    
    public DOMSerializer asDOMSerializer() throws IOException {
        return this;
    }
    
    public void cdata(final char[] ch, final int start, final int length) throws SAXException {
        try {
            this.writeParentTagEnd();
            this.m_ispreserve = true;
            if (this.shouldIndent()) {
                this.indent(this.m_currentIndent);
            }
            final boolean writeCDataBrackets = length >= 1 && ch[start] <= this.m_maxCharacter;
            if (writeCDataBrackets) {
                this.accum("<![CDATA[");
            }
            if (this.isEscapingDisabled()) {
                this.charactersRaw(ch, start, length);
            }
            else {
                this.writeNormalizedChars(ch, start, length, true);
            }
            if (writeCDataBrackets) {
                this.accum("]]>");
            }
        }
        catch (IOException e) {
            throw new SAXException(XSLMessages.createXPATHMessage(66, null), e);
        }
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        if (length == 0) {
            return;
        }
        if (this.isCDataSection()) {
            this.cdata(chars, start, length);
            return;
        }
        if (this.isEscapingDisabled()) {
            this.charactersRaw(chars, start, length);
            return;
        }
        this.writeParentTagEnd();
        int startClean = start;
        int lengthClean = 0;
        final int end = start + length;
        boolean checkWhite = true;
        for (int i = start; i < end; ++i) {
            final char ch = chars[i];
            if (checkWhite && !Character.isWhitespace(ch)) {
                this.m_ispreserve = true;
                checkWhite = false;
            }
            if (ch < this.m_maxCharacter && !this.m_charInfo.isSpecial(ch)) {
                ++lengthClean;
            }
            else if (ch == '\"') {
                ++lengthClean;
            }
            else {
                if (lengthClean > 0) {
                    this.accum(chars, startClean, lengthClean);
                    lengthClean = 0;
                }
                startClean = this.accumDefaultEscape(ch, i, chars, end, false);
                i = startClean - 1;
            }
        }
        if (lengthClean > 0) {
            this.accum(chars, startClean, lengthClean);
        }
        this.m_isprevtext = true;
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        this.writeParentTagEnd();
        this.m_ispreserve = true;
        this.accum(ch, start, length);
    }
    
    protected boolean childNodesWereAdded() {
        return !this.m_elemStack.isEmpty() && this.m_elemStack.pop();
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        this.writeParentTagEnd();
        if (this.shouldIndent()) {
            this.indent(this.m_currentIndent);
        }
        this.accum("<!--");
        this.accum(ch, start, length);
        this.accum("-->");
        this.m_startNewLine = true;
    }
    
    public void endCDATA() throws SAXException {
        this.m_inCData = false;
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        if (this.m_doIndent && !this.m_isprevtext) {
            this.outputLineSep();
        }
        this.flush();
        this.flushWriter();
    }
    
    public void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        this.m_currentIndent -= this.m_indentAmount;
        final boolean hasChildNodes = this.childNodesWereAdded();
        if (hasChildNodes) {
            if (this.shouldIndent()) {
                this.indent(this.m_currentIndent);
            }
            this.accum('<');
            this.accum('/');
            this.accum(name);
            this.accum('>');
        }
        else if (this.m_spaceBeforeClose) {
            this.accum(" />");
        }
        else {
            this.accum("/>");
        }
        if (hasChildNodes) {
            this.m_ispreserve = (!this.m_preserves.isEmpty() && this.m_preserves.pop());
        }
        this.m_isprevtext = false;
        this.m_cdataSectionStates.pop();
    }
    
    public void endEntity(final String name) throws SAXException {
        this.m_inEntityRef = false;
    }
    
    public void endNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.pop();
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void endPreserving() throws SAXException {
        this.m_ispreserve = (!this.m_preserves.isEmpty() && this.m_preserves.pop());
    }
    
    public void entityReference(final String name) throws SAXException {
        this.writeParentTagEnd();
        if (this.shouldIndent()) {
            this.indent(this.m_currentIndent);
        }
        this.accum("&");
        this.accum(name);
        this.accum(";");
    }
    
    public final void flush() throws SAXException {
        if (this.m_bytesEqualChars) {
            this.flushBytes();
        }
        else {
            this.flushChars();
        }
    }
    
    private final void flushBytes() throws SAXException {
        try {
            this.m_outputStream.write(this.m_byteBuf, 0, this.m_pos);
            this.m_pos = 0;
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    private final void flushChars() throws SAXException {
        try {
            this.m_writer.write(this.m_charBuf, 0, this.m_pos);
            this.m_pos = 0;
        }
        catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
    
    public final void flushWriter() throws SAXException {
        if (this.m_shouldFlush && this.m_writer != null) {
            try {
                this.m_writer.flush();
            }
            catch (IOException ioe) {
                throw new SAXException(ioe);
            }
        }
    }
    
    public Properties getOutputFormat() {
        return this.m_format;
    }
    
    public OutputStream getOutputStream() {
        return this.m_outputStream;
    }
    
    int getURF16SurrogateValue(final char c, final char[] ch, int i, final int end) throws SAXException {
        if (i + 1 >= end) {
            throw new SAXException(XSLMessages.createXPATHMessage(65, new Object[] { Integer.toHexString(c) }));
        }
        int next = ch[++i];
        if (next < 56320 || next >= 57344) {
            throw new SAXException(XSLMessages.createXPATHMessage(65, new Object[] { String.valueOf(Integer.toHexString(c)) + " " + Integer.toHexString(next) }));
        }
        next = (c - '\ud800' << 10) + next - 56320 + 65536;
        return next;
    }
    
    public Writer getWriter() {
        return this.m_writer;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (length == 0) {
            return;
        }
        this.characters(ch, start, length);
    }
    
    public void indent(final int n) throws SAXException {
        if (this.m_startNewLine) {
            this.outputLineSep();
        }
        if (this.m_doIndent) {
            this.printSpace(n);
        }
    }
    
    public synchronized void init(final OutputStream output, Properties format) throws UnsupportedEncodingException {
        if (format == null) {
            final OutputProperties op = new OutputProperties("xml");
            format = op.getProperties();
        }
        this.m_encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
        if (this.m_encoding.equals("WINDOWS-1250") || this.m_encoding.equals("US-ASCII") || this.m_encoding.equals("ASCII")) {
            this.m_bytesEqualChars = true;
            this.m_outputStream = output;
            this.init(null, format, true);
        }
        else {
            Writer osw;
            try {
                osw = Encodings.getWriter(output, this.m_encoding);
            }
            catch (UnsupportedEncodingException ex) {
                System.out.println("Warning: encoding \"" + this.m_encoding + "\" not supported" + ", using " + "UTF-8");
                this.m_encoding = "UTF-8";
                osw = Encodings.getWriter(output, this.m_encoding);
            }
            this.m_maxCharacter = Encodings.getLastPrintable(this.m_encoding);
            this.init(osw, format, true);
        }
    }
    
    public synchronized void init(final Writer writer, final Properties format) {
        this.init(writer, format, false);
    }
    
    private synchronized void init(final Writer writer, final Properties format, final boolean shouldFlush) {
        this.m_shouldFlush = shouldFlush;
        this.m_writer = writer;
        this.m_format = format;
        this.m_cdataSectionNames = OutputProperties.getQNameProperties("cdata-section-elements", format);
        this.m_indentAmount = OutputProperties.getIntProperty(OutputProperties.S_KEY_INDENT_AMOUNT, format);
        this.m_doIndent = OutputProperties.getBooleanProperty("indent", format);
        this.m_shouldNotWriteXMLHeader = OutputProperties.getBooleanProperty("omit-xml-declaration", format);
        this.m_doctypeSystem = format.getProperty("doctype-system");
        this.m_doctypePublic = format.getProperty("doctype-public");
        this.m_standaloneWasSpecified = (format.get("standalone") != null);
        this.m_standalone = OutputProperties.getBooleanProperty("standalone", format);
        this.m_mediatype = format.getProperty("media-type");
        if (this.m_doctypePublic != null && this.m_doctypePublic.startsWith("-//W3C//DTD XHTML")) {
            this.m_spaceBeforeClose = true;
        }
        if (this.m_encoding == null) {
            this.m_encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
        }
        this.m_isUTF8 = this.m_encoding.equals("UTF-8");
        this.m_maxCharacter = Encodings.getLastPrintable(this.m_encoding);
        final String entitiesFileName = ((Hashtable<K, String>)format).get(OutputProperties.S_KEY_ENTITIES);
        if (entitiesFileName != null) {
            try {
                this.m_charInfo = null;
                Label_0259: {
                    if (SerializerToXML.m_charInfos == null) {
                        synchronized (SerializerToXML.m_xmlcharInfo) {
                            if (SerializerToXML.m_charInfos == null) {
                                SerializerToXML.m_charInfos = new Hashtable();
                            }
                            // monitorexit(SerializerToXML.m_xmlcharInfo)
                            break Label_0259;
                        }
                    }
                    this.m_charInfo = SerializerToXML.m_charInfos.get(entitiesFileName);
                }
                if (this.m_charInfo == null) {
                    String absoluteEntitiesFileName;
                    if (entitiesFileName.indexOf(58) < 0) {
                        absoluteEntitiesFileName = SystemIDResolver.getAbsoluteURIFromRelative(entitiesFileName);
                    }
                    else {
                        absoluteEntitiesFileName = SystemIDResolver.getAbsoluteURI(entitiesFileName, null);
                    }
                    this.m_charInfo = new CharInfo(absoluteEntitiesFileName);
                    SerializerToXML.m_charInfos.put(entitiesFileName, this.m_charInfo);
                }
            }
            catch (TransformerException te) {
                throw new WrappedRuntimeException(te);
            }
        }
    }
    
    protected boolean isCDataSection() {
        return this.m_inCData || this.m_cdataSectionStates.peekOrFalse();
    }
    
    protected boolean isEscapingDisabled() {
        return this.m_disableOutputEscapingStates.peekOrFalse();
    }
    
    static final boolean isUTF16Surrogate(final char c) {
        return (c & '\ufc00') == '\ud800';
    }
    
    protected void openElementForChildren() {
        this.m_elemStack.push(false);
    }
    
    void outputDocTypeDecl(final String name) throws SAXException {
        this.accum("<!DOCTYPE ");
        this.accum(name);
        if (this.m_doctypePublic != null) {
            this.accum(" PUBLIC \"");
            this.accum(this.m_doctypePublic);
            this.accum("\"");
        }
        if (this.m_doctypePublic == null) {
            this.accum(" SYSTEM \"");
        }
        else {
            this.accum(" \"");
        }
        this.accum(this.m_doctypeSystem);
        this.accum("\">");
        this.outputLineSep();
    }
    
    protected final void outputLineSep() throws SAXException {
        for (int z = 0; z < this.m_lineSepLen; ++z) {
            this.accum(this.m_lineSep.charAt(z));
        }
    }
    
    public void printSpace(final int n) throws SAXException {
        for (int i = 0; i < n; ++i) {
            this.accum(' ');
        }
    }
    
    protected void processAttribute(final String name, final String value) throws SAXException {
        this.accum(' ');
        this.accum(name);
        this.accum("=\"");
        this.writeAttrString(value, this.m_encoding);
        this.accum('\"');
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        if (target.equals("javax.xml.transform.disable-output-escaping")) {
            this.startNonEscaping();
        }
        else if (target.equals("javax.xml.transform.enable-output-escaping")) {
            this.endNonEscaping();
        }
        else {
            this.writeParentTagEnd();
            if (this.shouldIndent()) {
                this.indent(this.m_currentIndent);
            }
            this.accum('<');
            this.accum('?');
            this.accum(target);
            if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0))) {
                this.accum(' ');
            }
            final int indexOfQLT = data.indexOf("?>");
            if (indexOfQLT >= 0) {
                if (indexOfQLT > 0) {
                    this.accum(data.substring(0, indexOfQLT));
                }
                this.accum("? >");
                if (indexOfQLT + 2 < data.length()) {
                    this.accum(data.substring(indexOfQLT + 2));
                }
            }
            else {
                this.accum(data);
            }
            this.accum('?');
            this.accum('>');
            if (this.m_elemStack.isEmpty()) {
                this.outputLineSep();
            }
            this.m_startNewLine = true;
        }
    }
    
    protected void pushState(String namespaceURI, final String localName, final Vector qnames, final BoolStack state) {
        boolean b;
        if (qnames != null) {
            b = false;
            if (namespaceURI != null && namespaceURI.length() == 0) {
                namespaceURI = null;
            }
            for (int nElems = qnames.size(), i = 0; i < nElems; ++i) {
                final QName q = qnames.elementAt(i);
                if (q.getLocalName().equals(localName) && subPartMatch(namespaceURI, q.getNamespaceURI())) {
                    b = true;
                    break;
                }
            }
        }
        else {
            b = state.peekOrFalse();
        }
        state.push(b);
    }
    
    public boolean reset() {
        return false;
    }
    
    public void serialize(final Node node) throws IOException {
        try {
            final TreeWalker walker = new TreeWalker(this);
            walker.traverse(node);
        }
        catch (SAXException e) {
            throw new WrappedRuntimeException(e);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void setOutputFormat(final Properties format) {
        final boolean shouldFlush = this.m_shouldFlush;
        this.init(this.m_writer, format, false);
        this.m_shouldFlush = shouldFlush;
    }
    
    public void setOutputStream(final OutputStream output) {
        try {
            this.init(output, this.m_format);
        }
        catch (UnsupportedEncodingException ex) {}
    }
    
    public void setWriter(final Writer writer) {
        this.m_writer = writer;
    }
    
    protected boolean shouldIndent() {
        return this.m_doIndent && !this.m_ispreserve && !this.m_isprevtext;
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
        this.m_inCData = true;
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        this.m_doctypeSystem = systemId;
        this.m_doctypePublic = publicId;
        if (this.m_needToOutputDocTypeDecl && this.m_doctypeSystem != null) {
            this.outputDocTypeDecl(name);
        }
        this.m_needToOutputDocTypeDecl = false;
    }
    
    public void startDocument() throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        this.m_needToOutputDocTypeDecl = true;
        this.m_startNewLine = false;
        if (!this.m_shouldNotWriteXMLHeader) {
            final String encoding = Encodings.getMimeEncoding(this.m_encoding);
            final String version = (this.m_version == null) ? "1.0" : this.m_version;
            String standalone;
            if (this.m_standaloneWasSpecified) {
                standalone = " standalone=\"" + (this.m_standalone ? "yes" : "no") + "\"";
            }
            else {
                standalone = "";
            }
            this.accum("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"" + standalone + "?>");
            this.outputLineSep();
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        if (this.m_inEntityRef) {
            return;
        }
        if (this.m_needToOutputDocTypeDecl && this.m_doctypeSystem != null) {
            this.outputDocTypeDecl(name);
        }
        this.m_needToOutputDocTypeDecl = false;
        this.writeParentTagEnd();
        this.pushState(namespaceURI, localName, this.m_cdataSectionNames, this.m_cdataSectionStates);
        this.m_ispreserve = false;
        if (this.shouldIndent() && this.m_startNewLine) {
            this.indent(this.m_currentIndent);
        }
        this.m_startNewLine = true;
        this.accum('<');
        this.accum(name);
        for (int nAttrs = atts.getLength(), i = 0; i < nAttrs; ++i) {
            this.processAttribute(atts.getQName(i), atts.getValue(i));
        }
        this.openElementForChildren();
        this.m_currentIndent += this.m_indentAmount;
        this.m_isprevtext = false;
    }
    
    public void startEntity(final String name) throws SAXException {
        this.m_inEntityRef = true;
    }
    
    public void startNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.push(true);
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
    
    public void startPreserving() throws SAXException {
        this.m_preserves.push(true);
        this.m_ispreserve = true;
    }
    
    protected static final boolean subPartMatch(final String p, final String t) {
        return p == t || (p != null && p.equals(t));
    }
    
    public void writeAttrString(final String string, final String encoding) throws SAXException {
        final char[] stringChars = string.toCharArray();
        for (int len = stringChars.length, i = 0; i < len; ++i) {
            char ch = stringChars[i];
            if (ch < this.m_maxCharacter && !this.m_charInfo.isSpecial(ch)) {
                this.accum(ch);
            }
            else {
                if (CharInfo.S_CARRIAGERETURN == ch && i + 1 < len && CharInfo.S_LINEFEED == stringChars[i + 1]) {
                    ++i;
                    ch = CharInfo.S_LINEFEED;
                }
                this.accumDefaultEscape(ch, i, stringChars, len, true);
            }
        }
    }
    
    void writeNormalizedChars(final char[] ch, final int start, final int length, final boolean isCData) throws IOException, SAXException {
        for (int end = start + length, i = start; i < end; ++i) {
            final char c = ch[i];
            if (CharInfo.S_LINEFEED == c) {
                this.outputLineSep();
            }
            else if (isCData && c > this.m_maxCharacter) {
                if (i != 0) {
                    this.accum("]]>");
                }
                if (isUTF16Surrogate(c)) {
                    i = this.writeUTF16Surrogate(c, ch, i, end);
                }
                else {
                    this.accum("&#");
                    final String intStr = Integer.toString(c);
                    this.accum(intStr);
                    this.accum(';');
                }
                if (i != 0 && i < end - 1) {
                    this.accum("<![CDATA[");
                }
            }
            else if (isCData && i < end - 2 && c == ']' && ch[i + 1] == ']' && ch[i + 2] == '>') {
                this.accum("]]]]><![CDATA[>");
                i += 2;
            }
            else if (c <= this.m_maxCharacter) {
                this.accum(c);
            }
            else if (isUTF16Surrogate(c)) {
                i = this.writeUTF16Surrogate(c, ch, i, end);
            }
            else {
                this.accum("&#");
                final String intStr = Integer.toString(c);
                this.accum(intStr);
                this.accum(';');
            }
        }
    }
    
    protected void writeParentTagEnd() throws SAXException {
        if (!this.m_elemStack.isEmpty() && !this.m_elemStack.peek()) {
            this.accum('>');
            this.m_isprevtext = false;
            this.m_elemStack.pop();
            this.m_elemStack.push(true);
            this.m_preserves.push(this.m_ispreserve);
        }
    }
    
    protected int writeUTF16Surrogate(final char c, final char[] ch, int i, final int end) throws IOException, SAXException {
        final int surrogateValue = this.getURF16SurrogateValue(c, ch, i, end);
        ++i;
        this.accum('&');
        this.accum('#');
        this.accum(Integer.toString(surrogateValue));
        this.accum(';');
        return i;
    }
}
