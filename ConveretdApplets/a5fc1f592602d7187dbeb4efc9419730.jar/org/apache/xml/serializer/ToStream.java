// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.Hashtable;
import javax.xml.transform.Transformer;
import java.util.StringTokenizer;
import org.apache.xml.utils.FastStringBuffer;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.apache.xml.res.XMLMessages;
import java.lang.reflect.InvocationTargetException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.utils.DOMHelper;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.TreeWalker;
import org.apache.xml.utils.DOM2Helper;
import org.w3c.dom.Node;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Properties;
import java.lang.reflect.Method;
import org.apache.xml.utils.BoolStack;

public abstract class ToStream extends SerializerBase
{
    private static final String COMMENT_BEGIN = "<!--";
    private static final String COMMENT_END = "-->";
    protected BoolStack m_disableOutputEscapingStates;
    boolean m_triedToGetConverter;
    Method m_canConvertMeth;
    Object m_charToByteConverter;
    protected BoolStack m_preserves;
    protected boolean m_ispreserve;
    protected boolean m_isprevtext;
    protected int m_maxCharacter;
    protected final char[] m_lineSep;
    protected boolean m_lineSepUse;
    protected final int m_lineSepLen;
    protected CharInfo m_charInfo;
    boolean m_shouldFlush;
    protected boolean m_spaceBeforeClose;
    boolean m_startNewLine;
    protected boolean m_inDoctype;
    boolean m_isUTF8;
    protected Properties m_format;
    protected boolean m_cdataStartCalled;
    private boolean m_escaping;
    
    public ToStream() {
        this.m_disableOutputEscapingStates = new BoolStack();
        this.m_triedToGetConverter = false;
        this.m_charToByteConverter = null;
        this.m_preserves = new BoolStack();
        this.m_ispreserve = false;
        this.m_isprevtext = false;
        this.m_maxCharacter = Encodings.getLastPrintable();
        this.m_lineSep = System.getProperty("line.separator").toCharArray();
        this.m_lineSepUse = true;
        this.m_lineSepLen = this.m_lineSep.length;
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_inDoctype = false;
        this.m_isUTF8 = false;
        this.m_cdataStartCalled = false;
        this.m_escaping = true;
    }
    
    protected void closeCDATA() throws SAXException {
        try {
            super.m_writer.write("]]>");
            super.m_cdataTagOpen = false;
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void serialize(final Node node) throws IOException {
        try {
            final TreeWalker walker = new TreeWalker(this, new DOM2Helper());
            walker.traverse(node);
        }
        catch (SAXException se) {
            throw new WrappedRuntimeException(se);
        }
    }
    
    static final boolean isUTF16Surrogate(final char c) {
        return (c & '\ufc00') == '\ud800';
    }
    
    protected final void flushWriter() throws SAXException {
        final Writer writer = super.m_writer;
        if (null != writer) {
            try {
                if (writer instanceof WriterToUTF8Buffered) {
                    if (this.m_shouldFlush) {
                        ((WriterToUTF8Buffered)writer).flush();
                    }
                    else {
                        ((WriterToUTF8Buffered)writer).flushBuffer();
                    }
                }
                if (writer instanceof WriterToASCI) {
                    if (this.m_shouldFlush) {
                        writer.flush();
                    }
                }
                else {
                    writer.flush();
                }
            }
            catch (IOException ioe) {
                throw new SAXException(ioe);
            }
        }
    }
    
    public OutputStream getOutputStream() {
        if (super.m_writer instanceof WriterToUTF8Buffered) {
            return ((WriterToUTF8Buffered)super.m_writer).getOutputStream();
        }
        if (super.m_writer instanceof WriterToASCI) {
            return ((WriterToASCI)super.m_writer).getOutputStream();
        }
        return null;
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        if (super.m_inExternalDTD) {
            return;
        }
        try {
            final Writer writer = super.m_writer;
            if (super.m_needToOutputDocTypeDecl) {
                this.outputDocTypeDecl(super.m_elemContext.m_elementName, false);
                super.m_needToOutputDocTypeDecl = false;
            }
            if (this.m_inDoctype) {
                writer.write(" [");
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
                this.m_inDoctype = false;
            }
            writer.write("<!ELEMENT ");
            writer.write(name);
            writer.write(32);
            writer.write(model);
            writer.write(62);
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (super.m_inExternalDTD) {
            return;
        }
        try {
            if (super.m_needToOutputDocTypeDecl) {
                this.outputDocTypeDecl(super.m_elemContext.m_elementName, false);
                super.m_needToOutputDocTypeDecl = false;
            }
            if (this.m_inDoctype) {
                final Writer writer = super.m_writer;
                writer.write(" [");
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
                this.m_inDoctype = false;
            }
            this.outputEntityDecl(name, value);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    void outputEntityDecl(final String name, final String value) throws IOException {
        final Writer writer = super.m_writer;
        writer.write("<!ENTITY ");
        writer.write(name);
        writer.write(" \"");
        writer.write(value);
        writer.write("\">");
        writer.write(this.m_lineSep, 0, this.m_lineSepLen);
    }
    
    protected final void outputLineSep() throws IOException {
        super.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
    }
    
    public void setOutputFormat(final Properties format) {
        final boolean shouldFlush = this.m_shouldFlush;
        this.init(super.m_writer, format, false, false);
        this.m_shouldFlush = shouldFlush;
    }
    
    private synchronized void init(final Writer writer, final Properties format, final boolean defaultProperties, final boolean shouldFlush) {
        this.m_shouldFlush = shouldFlush;
        if (super.m_tracer != null && !(writer instanceof SerializerTraceWriter)) {
            super.m_writer = new SerializerTraceWriter(writer, super.m_tracer);
        }
        else {
            super.m_writer = writer;
        }
        this.setCdataSectionElements("cdata-section-elements", this.m_format = format);
        this.setIndentAmount(OutputPropertyUtils.getIntProperty("{http://xml.apache.org/xalan}indent-amount", format));
        this.setIndent(OutputPropertyUtils.getBooleanProperty("indent", format));
        final boolean shouldNotWriteXMLHeader = OutputPropertyUtils.getBooleanProperty("omit-xml-declaration", format);
        this.setOmitXMLDeclaration(shouldNotWriteXMLHeader);
        this.setDoctypeSystem(format.getProperty("doctype-system"));
        final String doctypePublic = format.getProperty("doctype-public");
        this.setDoctypePublic(doctypePublic);
        if (format.get("standalone") != null) {
            final String val = format.getProperty("standalone");
            if (defaultProperties) {
                this.setStandaloneInternal(val);
            }
            else {
                this.setStandalone(val);
            }
        }
        this.setMediaType(format.getProperty("media-type"));
        if (null != doctypePublic && doctypePublic.startsWith("-//W3C//DTD XHTML")) {
            this.m_spaceBeforeClose = true;
        }
        String encoding = this.getEncoding();
        if (null == encoding) {
            encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
            this.setEncoding(encoding);
        }
        this.m_isUTF8 = encoding.equals("UTF-8");
        this.m_maxCharacter = Encodings.getLastPrintable(encoding);
        final String entitiesFileName = ((Hashtable<K, String>)format).get("{http://xml.apache.org/xalan}entities");
        if (null != entitiesFileName) {
            final String method = ((Hashtable<K, String>)format).get("method");
            this.m_charInfo = CharInfo.getCharInfo(entitiesFileName, method);
        }
    }
    
    private synchronized void init(final Writer writer, final Properties format) {
        this.init(writer, format, false, false);
    }
    
    protected synchronized void init(final OutputStream output, final Properties format, final boolean defaultProperties) throws UnsupportedEncodingException {
        String encoding = this.getEncoding();
        if (encoding == null) {
            encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
            this.setEncoding(encoding);
        }
        if (encoding.equalsIgnoreCase("UTF-8")) {
            this.m_isUTF8 = true;
            this.init(new WriterToUTF8Buffered(output), format, defaultProperties, true);
        }
        else if (encoding.equals("WINDOWS-1250") || encoding.equals("US-ASCII") || encoding.equals("ASCII")) {
            this.init(new WriterToASCI(output), format, defaultProperties, true);
        }
        else {
            Writer osw;
            try {
                osw = Encodings.getWriter(output, encoding);
            }
            catch (UnsupportedEncodingException uee) {
                System.out.println("Warning: encoding \"" + encoding + "\" not supported" + ", using " + "UTF-8");
                encoding = "UTF-8";
                this.setEncoding(encoding);
                osw = Encodings.getWriter(output, encoding);
            }
            this.m_maxCharacter = Encodings.getLastPrintable(encoding);
            this.init(osw, format, defaultProperties, true);
        }
    }
    
    public Properties getOutputFormat() {
        return this.m_format;
    }
    
    public void setWriter(final Writer writer) {
        if (super.m_tracer != null && !(writer instanceof SerializerTraceWriter)) {
            super.m_writer = new SerializerTraceWriter(writer, super.m_tracer);
        }
        else {
            super.m_writer = writer;
        }
    }
    
    public boolean setLineSepUse(final boolean use_sytem_line_break) {
        final boolean oldValue = this.m_lineSepUse;
        this.m_lineSepUse = use_sytem_line_break;
        return oldValue;
    }
    
    public void setOutputStream(final OutputStream output) {
        try {
            Properties format;
            if (null == this.m_format) {
                format = OutputPropertiesFactory.getDefaultMethodProperties("xml");
            }
            else {
                format = this.m_format;
            }
            this.init(output, format, true);
        }
        catch (UnsupportedEncodingException ex) {}
    }
    
    public boolean setEscaping(final boolean escape) {
        final boolean temp = this.m_escaping;
        this.m_escaping = escape;
        return temp;
    }
    
    protected void indent(final int depth) throws IOException {
        if (this.m_startNewLine) {
            this.outputLineSep();
        }
        if (super.m_indentAmount > 0) {
            this.printSpace(depth * super.m_indentAmount);
        }
    }
    
    protected void indent() throws IOException {
        this.indent(super.m_elemContext.m_currentElemDepth);
    }
    
    private void printSpace(final int n) throws IOException {
        final Writer writer = super.m_writer;
        for (int i = 0; i < n; ++i) {
            writer.write(32);
        }
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        if (super.m_inExternalDTD) {
            return;
        }
        try {
            final Writer writer = super.m_writer;
            if (super.m_needToOutputDocTypeDecl) {
                this.outputDocTypeDecl(super.m_elemContext.m_elementName, false);
                super.m_needToOutputDocTypeDecl = false;
            }
            if (this.m_inDoctype) {
                writer.write(" [");
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
                this.m_inDoctype = false;
            }
            writer.write("<!ATTLIST ");
            writer.write(eName);
            writer.write(32);
            writer.write(aName);
            writer.write(32);
            writer.write(type);
            if (valueDefault != null) {
                writer.write(32);
                writer.write(valueDefault);
            }
            writer.write(62);
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public Writer getWriter() {
        return super.m_writer;
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    protected boolean escapingNotNeeded(final char ch) {
        if (ch < '\u007f') {
            return ch >= ' ' || '\n' == ch || '\r' == ch || '\t' == ch;
        }
        if (null == this.m_charToByteConverter && !this.m_triedToGetConverter) {
            this.m_triedToGetConverter = true;
            try {
                this.m_charToByteConverter = Encodings.getCharToByteConverter(this.getEncoding());
                if (null != this.m_charToByteConverter) {
                    final Class[] argsTypes = { Character.TYPE };
                    final Class convClass = this.m_charToByteConverter.getClass();
                    this.m_canConvertMeth = convClass.getMethod("canConvert", (Class[])argsTypes);
                }
            }
            catch (Exception e) {
                System.err.println("Warning: " + e.getMessage());
            }
        }
        if (null != this.m_charToByteConverter) {
            try {
                final Object[] args = { new Character(ch) };
                final Boolean bool = (Boolean)this.m_canConvertMeth.invoke(this.m_charToByteConverter, args);
                return bool && !Character.isISOControl(ch);
            }
            catch (InvocationTargetException ite) {
                System.err.println("Warning: InvocationTargetException in canConvert!");
            }
            catch (IllegalAccessException iae) {
                System.err.println("Warning: IllegalAccessException in canConvert!");
            }
        }
        return ch <= this.m_maxCharacter;
    }
    
    protected void writeUTF16Surrogate(final char c, final char[] ch, final int i, final int end) throws IOException {
        final int surrogateValue = this.getURF16SurrogateValue(c, ch, i, end);
        final Writer writer = super.m_writer;
        writer.write(38);
        writer.write(35);
        writer.write(Integer.toString(surrogateValue));
        writer.write(59);
    }
    
    int getURF16SurrogateValue(final char c, final char[] ch, int i, final int end) throws IOException {
        if (i + 1 >= end) {
            throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) }));
        }
        int next = ch[++i];
        if (56320 > next || next >= 57344) {
            throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) + " " + Integer.toHexString(next) }));
        }
        next = (c - '\ud800' << 10) + next - 56320 + 65536;
        return next;
    }
    
    protected int accumDefaultEntity(final Writer writer, final char ch, final int i, final char[] chars, final int len, final boolean fromTextNode, final boolean escLF) throws IOException {
        if (!escLF && '\n' == ch) {
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        else {
            if ((!fromTextNode || !this.m_charInfo.isSpecialTextChar(ch)) && (fromTextNode || !this.m_charInfo.isSpecialAttrChar(ch))) {
                return i;
            }
            final String entityRef = this.m_charInfo.getEntityNameForChar(ch);
            if (null == entityRef) {
                return i;
            }
            writer.write(38);
            writer.write(entityRef);
            writer.write(59);
        }
        return i + 1;
    }
    
    void writeNormalizedChars(final char[] ch, final int start, final int length, final boolean isCData, final boolean useSystemLineSeparator) throws IOException, SAXException {
        final Writer writer = super.m_writer;
        for (int end = start + length, i = start; i < end; ++i) {
            final char c = ch[i];
            if ('\n' == c && useSystemLineSeparator) {
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
            }
            else if (isCData && !this.escapingNotNeeded(c)) {
                if (super.m_cdataTagOpen) {
                    this.closeCDATA();
                }
                if (isUTF16Surrogate(c)) {
                    this.writeUTF16Surrogate(c, ch, i, end);
                    ++i;
                }
                else {
                    writer.write("&#");
                    final String intStr = Integer.toString(c);
                    writer.write(intStr);
                    writer.write(59);
                }
            }
            else if (isCData && i < end - 2 && ']' == c && ']' == ch[i + 1] && '>' == ch[i + 2]) {
                writer.write("]]]]><![CDATA[>");
                i += 2;
            }
            else if (this.escapingNotNeeded(c)) {
                if (isCData && !super.m_cdataTagOpen) {
                    writer.write("<![CDATA[");
                    super.m_cdataTagOpen = true;
                }
                writer.write(c);
            }
            else if (isUTF16Surrogate(c)) {
                if (super.m_cdataTagOpen) {
                    this.closeCDATA();
                }
                this.writeUTF16Surrogate(c, ch, i, end);
                ++i;
            }
            else {
                if (super.m_cdataTagOpen) {
                    this.closeCDATA();
                }
                writer.write("&#");
                final String intStr = Integer.toString(c);
                writer.write(intStr);
                writer.write(59);
            }
        }
    }
    
    public void endNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.pop();
    }
    
    public void startNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.push(true);
    }
    
    protected void cdata(final char[] ch, final int start, final int length) throws SAXException {
        try {
            if (super.m_elemContext.m_startTagOpen) {
                this.closeStartTag();
                super.m_elemContext.m_startTagOpen = false;
            }
            this.m_ispreserve = true;
            if (this.shouldIndent()) {
                this.indent();
            }
            final boolean writeCDataBrackets = length >= 1 && this.escapingNotNeeded(ch[start]);
            if (writeCDataBrackets && !super.m_cdataTagOpen) {
                super.m_writer.write("<![CDATA[");
                super.m_cdataTagOpen = true;
            }
            if (this.isEscapingDisabled()) {
                this.charactersRaw(ch, start, length);
            }
            else {
                this.writeNormalizedChars(ch, start, length, true, this.m_lineSepUse);
            }
            if (writeCDataBrackets && ch[start + length - 1] == ']') {
                this.closeCDATA();
            }
            if (super.m_tracer != null) {
                super.fireCDATAEvent(ch, start, length);
            }
        }
        catch (IOException ioe) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe);
        }
    }
    
    private boolean isEscapingDisabled() {
        return this.m_disableOutputEscapingStates.peekOrFalse();
    }
    
    protected void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        if (super.m_inEntityRef) {
            return;
        }
        try {
            if (super.m_elemContext.m_startTagOpen) {
                this.closeStartTag();
                super.m_elemContext.m_startTagOpen = false;
            }
            this.m_ispreserve = true;
            super.m_writer.write(ch, start, length);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //     4: getfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //     7: ifeq            25
        //    10: aload_0         /* this */
        //    11: invokevirtual   org/apache/xml/serializer/ToStream.closeStartTag:()V
        //    14: aload_0         /* this */
        //    15: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //    18: iconst_0       
        //    19: putfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //    22: goto            36
        //    25: aload_0         /* this */
        //    26: getfield        org/apache/xml/serializer/SerializerBase.m_needToCallStartDocument:Z
        //    29: ifeq            36
        //    32: aload_0         /* this */
        //    33: invokevirtual   org/apache/xml/serializer/SerializerBase.startDocumentInternal:()V
        //    36: aload_0         /* this */
        //    37: getfield        org/apache/xml/serializer/ToStream.m_cdataStartCalled:Z
        //    40: ifne            53
        //    43: aload_0         /* this */
        //    44: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //    47: getfield        org/apache/xml/serializer/ElemContext.m_isCdataSection:Z
        //    50: ifeq            61
        //    53: aload_0         /* this */
        //    54: aload_1         /* chars */
        //    55: iload_2         /* start */
        //    56: iload_3         /* length */
        //    57: invokevirtual   org/apache/xml/serializer/ToStream.cdata:([CII)V
        //    60: return         
        //    61: aload_0         /* this */
        //    62: getfield        org/apache/xml/serializer/SerializerBase.m_cdataTagOpen:Z
        //    65: ifeq            72
        //    68: aload_0         /* this */
        //    69: invokevirtual   org/apache/xml/serializer/ToStream.closeCDATA:()V
        //    72: aload_0         /* this */
        //    73: getfield        org/apache/xml/serializer/ToStream.m_disableOutputEscapingStates:Lorg/apache/xml/utils/BoolStack;
        //    76: invokevirtual   org/apache/xml/utils/BoolStack.peekOrFalse:()Z
        //    79: ifne            89
        //    82: aload_0         /* this */
        //    83: getfield        org/apache/xml/serializer/ToStream.m_escaping:Z
        //    86: ifne            111
        //    89: aload_0         /* this */
        //    90: aload_1         /* chars */
        //    91: iload_2         /* start */
        //    92: iload_3         /* length */
        //    93: invokevirtual   org/apache/xml/serializer/ToStream.charactersRaw:([CII)V
        //    96: aload_0         /* this */
        //    97: getfield        org/apache/xml/serializer/SerializerBase.m_tracer:Lorg/apache/xml/serializer/SerializerTrace;
        //   100: ifnull          110
        //   103: aload_0         /* this */
        //   104: aload_1         /* chars */
        //   105: iload_2         /* start */
        //   106: iload_3         /* length */
        //   107: invokespecial   org/apache/xml/serializer/SerializerBase.fireCharEvent:([CII)V
        //   110: return         
        //   111: aload_0         /* this */
        //   112: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //   115: getfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //   118: ifeq            133
        //   121: aload_0         /* this */
        //   122: invokevirtual   org/apache/xml/serializer/ToStream.closeStartTag:()V
        //   125: aload_0         /* this */
        //   126: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //   129: iconst_0       
        //   130: putfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //   133: iload_2         /* start */
        //   134: iload_3         /* length */
        //   135: iadd           
        //   136: istore          end
        //   138: iload_2         /* start */
        //   139: iconst_1       
        //   140: isub           
        //   141: istore          lastDirty
        //   143: iload_2         /* start */
        //   144: istore          i
        //   146: goto            184
        //   149: aload_0         /* this */
        //   150: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   153: iload           5
        //   155: invokevirtual   org/apache/xml/serializer/CharInfo.isTextASCIIClean:(I)Z
        //   158: ifne            181
        //   161: aload_0         /* this */
        //   162: aload_1         /* chars */
        //   163: iload           end
        //   165: iload           i
        //   167: iload           5
        //   169: iload           lastDirty
        //   171: iconst_1       
        //   172: invokespecial   org/apache/xml/serializer/ToStream.processDirty:([CIICIZ)I
        //   175: istore          lastDirty
        //   177: iload           lastDirty
        //   179: istore          i
        //   181: iinc            i, 1
        //   184: iload           i
        //   186: iload           end
        //   188: if_icmpge       231
        //   191: aload_1         /* chars */
        //   192: iload           i
        //   194: caload         
        //   195: dup            
        //   196: istore          ch1
        //   198: bipush          32
        //   200: if_icmpeq       149
        //   203: iload           ch1
        //   205: bipush          10
        //   207: if_icmpne       217
        //   210: aload_0         /* this */
        //   211: getfield        org/apache/xml/serializer/ToStream.m_lineSepUse:Z
        //   214: ifne            149
        //   217: iload           ch1
        //   219: bipush          13
        //   221: if_icmpeq       149
        //   224: iload           ch1
        //   226: bipush          9
        //   228: if_icmpeq       149
        //   231: iload           i
        //   233: iload           end
        //   235: if_icmpge       353
        //   238: aload_0         /* this */
        //   239: iconst_1       
        //   240: putfield        org/apache/xml/serializer/ToStream.m_ispreserve:Z
        //   243: goto            353
        //   246: goto            252
        //   249: iinc            i, 1
        //   252: iload           i
        //   254: iload           end
        //   256: if_icmpge       283
        //   259: aload_1         /* chars */
        //   260: iload           i
        //   262: caload         
        //   263: dup            
        //   264: istore          ch2
        //   266: bipush          127
        //   268: if_icmpge       283
        //   271: aload_0         /* this */
        //   272: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   275: iload           ch2
        //   277: invokevirtual   org/apache/xml/serializer/CharInfo.isTextASCIIClean:(I)Z
        //   280: ifne            249
        //   283: iload           i
        //   285: iload           end
        //   287: if_icmpne       293
        //   290: goto            360
        //   293: aload_1         /* chars */
        //   294: iload           i
        //   296: caload         
        //   297: istore          ch
        //   299: aload_0         /* this */
        //   300: iload           ch
        //   302: invokevirtual   org/apache/xml/serializer/ToStream.escapingNotNeeded:(C)Z
        //   305: ifeq            320
        //   308: aload_0         /* this */
        //   309: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   312: iload           ch
        //   314: invokevirtual   org/apache/xml/serializer/CharInfo.isSpecialTextChar:(I)Z
        //   317: ifeq            350
        //   320: bipush          34
        //   322: iload           ch
        //   324: if_icmpne       330
        //   327: goto            350
        //   330: aload_0         /* this */
        //   331: aload_1         /* chars */
        //   332: iload           end
        //   334: iload           i
        //   336: iload           ch
        //   338: iload           lastDirty
        //   340: iconst_1       
        //   341: invokespecial   org/apache/xml/serializer/ToStream.processDirty:([CIICIZ)I
        //   344: istore          lastDirty
        //   346: iload           lastDirty
        //   348: istore          i
        //   350: iinc            i, 1
        //   353: iload           i
        //   355: iload           end
        //   357: if_icmplt       252
        //   360: iload           lastDirty
        //   362: iconst_1       
        //   363: iadd           
        //   364: istore          startClean
        //   366: iload           i
        //   368: iload           startClean
        //   370: if_icmple       392
        //   373: iload           i
        //   375: iload           startClean
        //   377: isub           
        //   378: istore          lengthClean
        //   380: aload_0         /* this */
        //   381: getfield        org/apache/xml/serializer/SerializerBase.m_writer:Ljava/io/Writer;
        //   384: aload_1         /* chars */
        //   385: iload           startClean
        //   387: iload           lengthClean
        //   389: invokevirtual   java/io/Writer.write:([CII)V
        //   392: aload_0         /* this */
        //   393: iconst_1       
        //   394: putfield        org/apache/xml/serializer/ToStream.m_isprevtext:Z
        //   397: goto            412
        //   400: astore          e
        //   402: new             Lorg/xml/sax/SAXException;
        //   405: dup            
        //   406: aload           e
        //   408: invokespecial   org/xml/sax/SAXException.<init>:(Ljava/lang/Exception;)V
        //   411: athrow         
        //   412: aload_0         /* this */
        //   413: getfield        org/apache/xml/serializer/SerializerBase.m_tracer:Lorg/apache/xml/serializer/SerializerTrace;
        //   416: ifnull          426
        //   419: aload_0         /* this */
        //   420: aload_1         /* chars */
        //   421: iload_2         /* start */
        //   422: iload_3         /* length */
        //   423: invokespecial   org/apache/xml/serializer/SerializerBase.fireCharEvent:([CII)V
        //   426: return         
        //    Exceptions:
        //  throws org.xml.sax.SAXException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------
        //  0      427     0     this         Lorg/apache/xml/serializer/ToStream;
        //  0      427     1     chars        [C
        //  0      427     2     start        I
        //  0      427     3     length       I
        //  146    251     4     i            I
        //  198    199     5     ch1          C
        //  366    31      6     startClean   I
        //  138    259     7     end          I
        //  143    254     8     lastDirty    I
        //  266    27      9     ch2          C
        //  299    51      9     ch           C
        //  380    12      9     lengthClean  I
        //  402    10      4     e            Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  133    397    400    412    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private int processDirty(final char[] chars, final int end, int i, final char ch, final int lastDirty, final boolean fromTextNode) throws IOException {
        int startClean = lastDirty + 1;
        if (i > startClean) {
            final int lengthClean = i - startClean;
            super.m_writer.write(chars, startClean, lengthClean);
        }
        if ('\n' == ch && fromTextNode) {
            super.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        else {
            startClean = this.accumDefaultEscape(super.m_writer, ch, i, chars, end, fromTextNode, false);
            i = startClean - 1;
        }
        return i;
    }
    
    public void characters(final String s) throws SAXException {
        final int length = s.length();
        if (length > super.m_charsBuff.length) {
            super.m_charsBuff = new char[length * 2 + 1];
        }
        s.getChars(0, length, super.m_charsBuff, 0);
        this.characters(super.m_charsBuff, 0, length);
    }
    
    protected int accumDefaultEscape(final Writer writer, final char ch, int i, final char[] chars, final int len, final boolean fromTextNode, final boolean escLF) throws IOException {
        int pos = this.accumDefaultEntity(writer, ch, i, chars, len, fromTextNode, escLF);
        if (i == pos) {
            if ('\ud800' <= ch && ch < '\udc00') {
                if (i + 1 >= len) {
                    throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) }));
                }
                int next = chars[++i];
                if (56320 > next || next >= 57344) {
                    throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) + " " + Integer.toHexString(next) }));
                }
                next = (ch - '\ud800' << 10) + next - 56320 + 65536;
                writer.write("&#");
                writer.write(Integer.toString(next));
                writer.write(59);
                pos += 2;
            }
            else {
                if (!this.escapingNotNeeded(ch) || (fromTextNode && this.m_charInfo.isSpecialTextChar(ch)) || (!fromTextNode && this.m_charInfo.isSpecialAttrChar(ch))) {
                    writer.write("&#");
                    writer.write(Integer.toString(ch));
                    writer.write(59);
                }
                else {
                    writer.write(ch);
                }
                ++pos;
            }
        }
        return pos;
    }
    
    public void startElement(final String namespaceURI, final String localName, final String name, final Attributes atts) throws SAXException {
        if (super.m_inEntityRef) {
            return;
        }
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        else if (super.m_cdataTagOpen) {
            this.closeCDATA();
        }
        try {
            if (super.m_needToOutputDocTypeDecl && null != this.getDoctypeSystem()) {
                this.outputDocTypeDecl(name, true);
            }
            super.m_needToOutputDocTypeDecl = false;
            if (super.m_elemContext.m_startTagOpen) {
                this.closeStartTag();
                super.m_elemContext.m_startTagOpen = false;
            }
            if (namespaceURI != null) {
                this.ensurePrefixIsDeclared(namespaceURI, name);
            }
            this.m_ispreserve = false;
            if (this.shouldIndent() && this.m_startNewLine) {
                this.indent();
            }
            this.m_startNewLine = true;
            final Writer writer = super.m_writer;
            writer.write(60);
            writer.write(name);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
        if (atts != null) {
            this.addAttributes(atts);
        }
        super.m_elemContext = super.m_elemContext.push(namespaceURI, localName, name);
        this.m_isprevtext = false;
        if (super.m_tracer != null) {
            this.firePseudoAttributes();
        }
    }
    
    public void startElement(final String elementNamespaceURI, final String elementLocalName, final String elementName) throws SAXException {
        this.startElement(elementNamespaceURI, elementLocalName, elementName, null);
    }
    
    public void startElement(final String elementName) throws SAXException {
        this.startElement(null, null, elementName, null);
    }
    
    void outputDocTypeDecl(final String name, boolean closeDecl) throws SAXException {
        if (super.m_cdataTagOpen) {
            this.closeCDATA();
        }
        try {
            final Writer writer = super.m_writer;
            writer.write("<!DOCTYPE ");
            writer.write(name);
            final String doctypePublic = this.getDoctypePublic();
            if (null != doctypePublic) {
                writer.write(" PUBLIC \"");
                writer.write(doctypePublic);
                writer.write(34);
            }
            final String doctypeSystem = this.getDoctypeSystem();
            if (null != doctypeSystem) {
                if (null == doctypePublic) {
                    writer.write(" SYSTEM \"");
                }
                else {
                    writer.write(" \"");
                }
                writer.write(doctypeSystem);
                if (closeDecl) {
                    writer.write("\">");
                    writer.write(this.m_lineSep, 0, this.m_lineSepLen);
                    closeDecl = false;
                }
                else {
                    writer.write(34);
                }
            }
            final boolean dothis = false;
            if (dothis && closeDecl) {
                writer.write(62);
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
            }
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void processAttributes(final Writer writer, final int nAttrs) throws IOException, SAXException {
        final String encoding = this.getEncoding();
        for (int i = 0; i < nAttrs; ++i) {
            final String name = super.m_attributes.getQName(i);
            final String value = super.m_attributes.getValue(i);
            writer.write(32);
            writer.write(name);
            writer.write("=\"");
            this.writeAttrString(writer, value, encoding);
            writer.write(34);
        }
    }
    
    public void writeAttrString(final Writer writer, final String string, final String encoding) throws IOException {
        final int len = string.length();
        if (len > super.m_attrBuff.length) {
            super.m_attrBuff = new char[len * 2 + 1];
        }
        string.getChars(0, len, super.m_attrBuff, 0);
        final char[] stringChars = super.m_attrBuff;
        for (int i = 0; i < len; ++i) {
            final char ch = stringChars[i];
            if (this.escapingNotNeeded(ch) && !this.m_charInfo.isSpecialAttrChar(ch)) {
                writer.write(ch);
            }
            else {
                this.accumDefaultEscape(writer, ch, i, stringChars, len, false, true);
            }
        }
    }
    
    public void endElement(final String namespaceURI, final String localName, final String name) throws SAXException {
        if (super.m_inEntityRef) {
            return;
        }
        super.m_prefixMap.popNamespaces(super.m_elemContext.m_currentElemDepth, null);
        try {
            final Writer writer = super.m_writer;
            if (super.m_elemContext.m_startTagOpen) {
                if (super.m_tracer != null) {
                    super.fireStartElem(super.m_elemContext.m_elementName);
                }
                final int nAttrs = super.m_attributes.getLength();
                if (nAttrs > 0) {
                    this.processAttributes(super.m_writer, nAttrs);
                    super.m_attributes.clear();
                }
                if (this.m_spaceBeforeClose) {
                    writer.write(" />");
                }
                else {
                    writer.write("/>");
                }
            }
            else {
                if (super.m_cdataTagOpen) {
                    this.closeCDATA();
                }
                if (this.shouldIndent()) {
                    this.indent(super.m_elemContext.m_currentElemDepth - 1);
                }
                writer.write(60);
                writer.write(47);
                writer.write(name);
                writer.write(62);
            }
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
        if (!super.m_elemContext.m_startTagOpen && super.m_doIndent) {
            this.m_ispreserve = (!this.m_preserves.isEmpty() && this.m_preserves.pop());
        }
        this.m_isprevtext = false;
        if (super.m_tracer != null) {
            super.fireEndElem(name);
        }
        super.m_elemContext = super.m_elemContext.m_prev;
    }
    
    public void endElement(final String name) throws SAXException {
        this.endElement(null, null, name);
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.startPrefixMapping(prefix, uri, true);
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        int pushDepth;
        if (shouldFlush) {
            this.flushPending();
            pushDepth = super.m_elemContext.m_currentElemDepth + 1;
        }
        else {
            pushDepth = super.m_elemContext.m_currentElemDepth;
        }
        final boolean pushed = super.m_prefixMap.pushNamespace(prefix, uri, pushDepth);
        if (pushed) {
            if ("".equals(prefix)) {
                final String name = "xmlns";
                this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
            }
            else if (!"".equals(uri)) {
                final String name = "xmlns:" + prefix;
                this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
            }
        }
        return pushed;
    }
    
    public void comment(final char[] ch, int start, final int length) throws SAXException {
        final int start_old = start;
        if (super.m_inEntityRef) {
            return;
        }
        if (super.m_elemContext.m_startTagOpen) {
            this.closeStartTag();
            super.m_elemContext.m_startTagOpen = false;
        }
        else if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        try {
            if (this.shouldIndent()) {
                this.indent();
            }
            final int limit = start + length;
            boolean wasDash = false;
            if (super.m_cdataTagOpen) {
                this.closeCDATA();
            }
            final Writer writer = super.m_writer;
            writer.write("<!--");
            for (int i = start; i < limit; ++i) {
                if (wasDash && ch[i] == '-') {
                    writer.write(ch, start, i - start);
                    writer.write(" -");
                    start = i + 1;
                }
                wasDash = (ch[i] == '-');
            }
            if (length > 0) {
                final int remainingChars = limit - start;
                if (remainingChars > 0) {
                    writer.write(ch, start, remainingChars);
                }
                if (ch[limit - 1] == '-') {
                    writer.write(32);
                }
            }
            writer.write("-->");
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
        this.m_startNewLine = true;
        if (super.m_tracer != null) {
            super.fireCommentEvent(ch, start_old, length);
        }
    }
    
    public void endCDATA() throws SAXException {
        if (super.m_cdataTagOpen) {
            this.closeCDATA();
        }
        this.m_cdataStartCalled = false;
    }
    
    public void endDTD() throws SAXException {
        try {
            if (super.m_needToOutputDocTypeDecl) {
                this.outputDocTypeDecl(super.m_elemContext.m_elementName, false);
                super.m_needToOutputDocTypeDecl = false;
            }
            final Writer writer = super.m_writer;
            if (!this.m_inDoctype) {
                writer.write("]>");
            }
            else {
                writer.write(62);
            }
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (0 == length) {
            return;
        }
        this.characters(ch, start, length);
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
        this.m_cdataStartCalled = true;
    }
    
    public void startEntity(final String name) throws SAXException {
        if (name.equals("[dtd]")) {
            super.m_inExternalDTD = true;
        }
        super.m_inEntityRef = true;
    }
    
    protected void closeStartTag() throws SAXException {
        if (super.m_elemContext.m_startTagOpen) {
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
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
            if (super.m_cdataSectionElements != null) {
                super.m_elemContext.m_isCdataSection = this.isCdataSection();
            }
            if (super.m_doIndent) {
                this.m_isprevtext = false;
                this.m_preserves.push(this.m_ispreserve);
            }
        }
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        this.setDoctypeSystem(systemId);
        this.setDoctypePublic(publicId);
        super.m_elemContext.m_elementName = name;
        this.m_inDoctype = true;
    }
    
    public int getIndentAmount() {
        return super.m_indentAmount;
    }
    
    public void setIndentAmount(final int m_indentAmount) {
        super.m_indentAmount = m_indentAmount;
    }
    
    protected boolean shouldIndent() {
        return super.m_doIndent && !this.m_ispreserve && !this.m_isprevtext;
    }
    
    private void setCdataSectionElements(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (null != s) {
            final Vector v = new Vector();
            final int l = s.length();
            boolean inCurly = false;
            final FastStringBuffer buf = new FastStringBuffer();
            for (int i = 0; i < l; ++i) {
                final char c = s.charAt(i);
                if (Character.isWhitespace(c)) {
                    if (!inCurly) {
                        if (buf.length() > 0) {
                            this.addCdataSectionElement(buf.toString(), v);
                            buf.reset();
                        }
                        continue;
                    }
                }
                else if ('{' == c) {
                    inCurly = true;
                }
                else if ('}' == c) {
                    inCurly = false;
                }
                buf.append(c);
            }
            if (buf.length() > 0) {
                this.addCdataSectionElement(buf.toString(), v);
                buf.reset();
            }
            this.setCdataSectionElements(v);
        }
    }
    
    private void addCdataSectionElement(final String URI_and_localName, final Vector v) {
        final StringTokenizer tokenizer = new StringTokenizer(URI_and_localName, "{}", false);
        final String s1 = tokenizer.nextToken();
        final String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        if (null == s2) {
            v.addElement(null);
            v.addElement(s1);
        }
        else {
            v.addElement(s1);
            v.addElement(s2);
        }
    }
    
    public void setCdataSectionElements(final Vector URI_and_localNames) {
        super.m_cdataSectionElements = URI_and_localNames;
    }
    
    protected String ensureAttributesNamespaceIsDeclared(final String ns, final String localName, final String rawName) throws SAXException {
        if (ns == null || ns.length() <= 0) {
            return null;
        }
        int index = 0;
        final String prefixFromRawName = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
        if (index <= 0) {
            String prefix = super.m_prefixMap.lookupPrefix(ns);
            if (prefix == null) {
                prefix = super.m_prefixMap.generateNextPrefix();
                this.startPrefixMapping(prefix, ns, false);
                this.addAttribute("http://www.w3.org/2000/xmlns/", prefix, "xmlns:" + prefix, "CDATA", ns);
            }
            return prefix;
        }
        final String uri = super.m_prefixMap.lookupNamespace(prefixFromRawName);
        if (uri != null && uri.equals(ns)) {
            return null;
        }
        this.startPrefixMapping(prefixFromRawName, ns, false);
        this.addAttribute("http://www.w3.org/2000/xmlns/", prefixFromRawName, "xmlns:" + prefixFromRawName, "CDATA", ns);
        return prefixFromRawName;
    }
    
    void ensurePrefixIsDeclared(final String ns, final String rawName) throws SAXException {
        if (ns != null && ns.length() > 0) {
            final int index;
            final String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
            if (null != prefix) {
                final String foundURI = super.m_prefixMap.lookupNamespace(prefix);
                if (null == foundURI || !foundURI.equals(ns)) {
                    this.startPrefixMapping(prefix, ns);
                    this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, "xmlns" + ((prefix.length() == 0) ? "" : ":") + prefix, "CDATA", ns);
                }
            }
        }
    }
    
    public void flushPending() throws SAXException {
        if (super.m_needToCallStartDocument) {
            this.startDocumentInternal();
            super.m_needToCallStartDocument = false;
        }
        if (super.m_elemContext.m_startTagOpen) {
            this.closeStartTag();
            super.m_elemContext.m_startTagOpen = false;
        }
        if (super.m_cdataTagOpen) {
            this.closeCDATA();
            super.m_cdataTagOpen = false;
        }
    }
    
    public void setContentHandler(final ContentHandler ch) {
    }
    
    public void addAttributeAlways(final String uri, final String localName, final String rawName, final String type, final String value) {
        final int index = super.m_attributes.getIndex(rawName);
        if (index >= 0) {
            String old_value = null;
            if (super.m_tracer != null) {
                old_value = super.m_attributes.getValue(index);
                if (value.equals(old_value)) {
                    old_value = null;
                }
            }
            super.m_attributes.setValue(index, value);
            if (old_value != null) {
                this.firePseudoAttributes();
            }
        }
        else {
            super.m_attributes.addAttribute(uri, localName, rawName, type, value);
            if (super.m_tracer != null) {
                this.firePseudoAttributes();
            }
        }
    }
    
    protected void firePseudoAttributes() {
        if (super.m_tracer != null) {
            try {
                super.m_writer.flush();
                final StringBuffer sb = new StringBuffer();
                final int nAttrs = super.m_attributes.getLength();
                if (nAttrs > 0) {
                    final Writer writer = new WritertoStringBuffer(sb);
                    this.processAttributes(writer, nAttrs);
                }
                sb.append('>');
                final char[] ch = sb.toString().toCharArray();
                super.m_tracer.fireGenerateEvent(11, ch, 0, ch.length);
            }
            catch (IOException ioe) {}
            catch (SAXException ex) {}
        }
    }
    
    public void setTransformer(final Transformer transformer) {
        super.setTransformer(transformer);
        if (super.m_tracer != null && !(super.m_writer instanceof SerializerTraceWriter)) {
            super.m_writer = new SerializerTraceWriter(super.m_writer, super.m_tracer);
        }
    }
    
    public boolean reset() {
        boolean wasReset = false;
        if (super.reset()) {
            this.resetToStream();
            wasReset = true;
        }
        return wasReset;
    }
    
    private void resetToStream() {
        this.m_canConvertMeth = null;
        this.m_cdataStartCalled = false;
        this.m_charToByteConverter = null;
        this.m_disableOutputEscapingStates.clear();
        this.m_escaping = true;
        this.m_inDoctype = false;
        this.m_ispreserve = false;
        this.m_ispreserve = false;
        this.m_isprevtext = false;
        this.m_isUTF8 = false;
        this.m_maxCharacter = Encodings.getLastPrintable();
        this.m_preserves.clear();
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_startNewLine = false;
        this.m_triedToGetConverter = false;
        this.m_lineSepUse = true;
    }
    
    private class WritertoStringBuffer extends Writer
    {
        private final StringBuffer m_stringbuf;
        
        WritertoStringBuffer(final StringBuffer sb) {
            this.m_stringbuf = sb;
        }
        
        public void write(final char[] arg0, final int arg1, final int arg2) throws IOException {
            this.m_stringbuf.append(arg0, arg1, arg2);
        }
        
        public void flush() throws IOException {
        }
        
        public void close() throws IOException {
        }
        
        public void write(final int i) {
            this.m_stringbuf.append((char)i);
        }
        
        public void write(final String s) {
            this.m_stringbuf.append(s);
        }
    }
}
