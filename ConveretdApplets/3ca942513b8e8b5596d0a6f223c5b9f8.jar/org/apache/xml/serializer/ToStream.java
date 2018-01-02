// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.Hashtable;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import java.util.StringTokenizer;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.apache.xml.serializer.utils.Utils;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xml.serializer.utils.WrappedRuntimeException;
import org.xml.sax.ContentHandler;
import org.w3c.dom.Node;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Properties;

public abstract class ToStream extends SerializerBase
{
    private static final String COMMENT_BEGIN = "<!--";
    private static final String COMMENT_END = "-->";
    protected BoolStack m_disableOutputEscapingStates;
    EncodingInfo m_encodingInfo;
    protected BoolStack m_preserves;
    protected boolean m_ispreserve;
    protected boolean m_isprevtext;
    protected char[] m_lineSep;
    protected boolean m_lineSepUse;
    protected int m_lineSepLen;
    protected CharInfo m_charInfo;
    boolean m_shouldFlush;
    protected boolean m_spaceBeforeClose;
    boolean m_startNewLine;
    protected boolean m_inDoctype;
    boolean m_isUTF8;
    protected Properties m_format;
    protected boolean m_cdataStartCalled;
    private boolean m_expandDTDEntities;
    private boolean m_escaping;
    
    public ToStream() {
        this.m_disableOutputEscapingStates = new BoolStack();
        this.m_encodingInfo = new EncodingInfo(null, null);
        this.m_preserves = new BoolStack();
        this.m_ispreserve = false;
        this.m_isprevtext = false;
        this.m_lineSep = System.getProperty("line.separator").toCharArray();
        this.m_lineSepUse = true;
        this.m_lineSepLen = this.m_lineSep.length;
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_inDoctype = false;
        this.m_isUTF8 = false;
        this.m_cdataStartCalled = false;
        this.m_expandDTDEntities = true;
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
            final TreeWalker walker = new TreeWalker(this);
            walker.traverse(node);
        }
        catch (SAXException se) {
            throw new WrappedRuntimeException(se);
        }
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
            this.DTDprolog();
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
            this.DTDprolog();
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
        final String sep = format.getProperty("{http://xml.apache.org/xalan}line-separator");
        if (sep != null) {
            this.setNewLine(sep.toCharArray());
        }
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
        String version = this.getVersion();
        if (null == version) {
            version = format.getProperty("version");
            this.setVersion(version);
        }
        String encoding = this.getEncoding();
        if (null == encoding) {
            encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
            this.setEncoding(encoding);
        }
        this.m_isUTF8 = encoding.equals("UTF-8");
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
            this.DTDprolog();
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
        try {
            this.DTDprolog();
            super.m_writer.write("<!ENTITY ");
            super.m_writer.write(name);
            if (publicId != null) {
                super.m_writer.write(" PUBLIC \"");
                super.m_writer.write(publicId);
            }
            else {
                super.m_writer.write(" SYSTEM \"");
                super.m_writer.write(systemId);
            }
            super.m_writer.write("\" >");
            super.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected boolean escapingNotNeeded(final char ch) {
        boolean ret;
        if (ch < '\u007f') {
            ret = (ch >= ' ' || '\n' == ch || '\r' == ch || '\t' == ch);
        }
        else {
            ret = this.m_encodingInfo.isInEncoding(ch);
        }
        return ret;
    }
    
    protected int writeUTF16Surrogate(final char c, final char[] ch, final int i, final int end) throws IOException {
        int codePoint = 0;
        if (i + 1 >= end) {
            throw new IOException(Utils.messages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) }));
        }
        final char low = ch[i + 1];
        if (!Encodings.isLowUTF16Surrogate(low)) {
            throw new IOException(Utils.messages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) + " " + Integer.toHexString(low) }));
        }
        final Writer writer = super.m_writer;
        if (this.m_encodingInfo.isInEncoding(c, low)) {
            writer.write(ch, i, 2);
        }
        else {
            final String encoding = this.getEncoding();
            if (encoding != null) {
                codePoint = Encodings.toCodePoint(c, low);
                writer.write(38);
                writer.write(35);
                writer.write(Integer.toString(codePoint));
                writer.write(59);
            }
            else {
                writer.write(ch, i, 2);
            }
        }
        return codePoint;
    }
    
    protected int accumDefaultEntity(final Writer writer, final char ch, final int i, final char[] chars, final int len, final boolean fromTextNode, final boolean escLF) throws IOException {
        if (!escLF && '\n' == ch) {
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        else {
            if ((!fromTextNode || !this.m_charInfo.isSpecialTextChar(ch)) && (fromTextNode || !this.m_charInfo.isSpecialAttrChar(ch))) {
                return i;
            }
            final String outputStringForChar = this.m_charInfo.getOutputStringForChar(ch);
            if (null == outputStringForChar) {
                return i;
            }
            writer.write(outputStringForChar);
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
                if (Encodings.isHighUTF16Surrogate(c)) {
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
            else if (Encodings.isHighUTF16Surrogate(c)) {
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
            throw new SAXException(Utils.messages.createMessage("ER_OIERROR", null), ioe);
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
        //     0: iload_3         /* length */
        //     1: ifeq            18
        //     4: aload_0         /* this */
        //     5: getfield        org/apache/xml/serializer/SerializerBase.m_inEntityRef:Z
        //     8: ifeq            19
        //    11: aload_0         /* this */
        //    12: getfield        org/apache/xml/serializer/ToStream.m_expandDTDEntities:Z
        //    15: ifne            19
        //    18: return         
        //    19: aload_0         /* this */
        //    20: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //    23: getfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //    26: ifeq            44
        //    29: aload_0         /* this */
        //    30: invokevirtual   org/apache/xml/serializer/ToStream.closeStartTag:()V
        //    33: aload_0         /* this */
        //    34: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //    37: iconst_0       
        //    38: putfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //    41: goto            55
        //    44: aload_0         /* this */
        //    45: getfield        org/apache/xml/serializer/SerializerBase.m_needToCallStartDocument:Z
        //    48: ifeq            55
        //    51: aload_0         /* this */
        //    52: invokevirtual   org/apache/xml/serializer/SerializerBase.startDocumentInternal:()V
        //    55: aload_0         /* this */
        //    56: getfield        org/apache/xml/serializer/ToStream.m_cdataStartCalled:Z
        //    59: ifne            72
        //    62: aload_0         /* this */
        //    63: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //    66: getfield        org/apache/xml/serializer/ElemContext.m_isCdataSection:Z
        //    69: ifeq            80
        //    72: aload_0         /* this */
        //    73: aload_1         /* chars */
        //    74: iload_2         /* start */
        //    75: iload_3         /* length */
        //    76: invokevirtual   org/apache/xml/serializer/ToStream.cdata:([CII)V
        //    79: return         
        //    80: aload_0         /* this */
        //    81: getfield        org/apache/xml/serializer/SerializerBase.m_cdataTagOpen:Z
        //    84: ifeq            91
        //    87: aload_0         /* this */
        //    88: invokevirtual   org/apache/xml/serializer/ToStream.closeCDATA:()V
        //    91: aload_0         /* this */
        //    92: getfield        org/apache/xml/serializer/ToStream.m_disableOutputEscapingStates:Lorg/apache/xml/serializer/ToStream$BoolStack;
        //    95: invokevirtual   org/apache/xml/serializer/ToStream$BoolStack.peekOrFalse:()Z
        //    98: ifne            108
        //   101: aload_0         /* this */
        //   102: getfield        org/apache/xml/serializer/ToStream.m_escaping:Z
        //   105: ifne            130
        //   108: aload_0         /* this */
        //   109: aload_1         /* chars */
        //   110: iload_2         /* start */
        //   111: iload_3         /* length */
        //   112: invokevirtual   org/apache/xml/serializer/ToStream.charactersRaw:([CII)V
        //   115: aload_0         /* this */
        //   116: getfield        org/apache/xml/serializer/SerializerBase.m_tracer:Lorg/apache/xml/serializer/SerializerTrace;
        //   119: ifnull          129
        //   122: aload_0         /* this */
        //   123: aload_1         /* chars */
        //   124: iload_2         /* start */
        //   125: iload_3         /* length */
        //   126: invokespecial   org/apache/xml/serializer/SerializerBase.fireCharEvent:([CII)V
        //   129: return         
        //   130: aload_0         /* this */
        //   131: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //   134: getfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //   137: ifeq            152
        //   140: aload_0         /* this */
        //   141: invokevirtual   org/apache/xml/serializer/ToStream.closeStartTag:()V
        //   144: aload_0         /* this */
        //   145: getfield        org/apache/xml/serializer/SerializerBase.m_elemContext:Lorg/apache/xml/serializer/ElemContext;
        //   148: iconst_0       
        //   149: putfield        org/apache/xml/serializer/ElemContext.m_startTagOpen:Z
        //   152: iload_2         /* start */
        //   153: iload_3         /* length */
        //   154: iadd           
        //   155: istore          end
        //   157: iload_2         /* start */
        //   158: iconst_1       
        //   159: isub           
        //   160: istore          lastDirty
        //   162: iload_2         /* start */
        //   163: istore          i
        //   165: goto            203
        //   168: aload_0         /* this */
        //   169: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   172: iload           5
        //   174: invokevirtual   org/apache/xml/serializer/CharInfo.isTextASCIIClean:(I)Z
        //   177: ifne            200
        //   180: aload_0         /* this */
        //   181: aload_1         /* chars */
        //   182: iload           end
        //   184: iload           i
        //   186: iload           5
        //   188: iload           lastDirty
        //   190: iconst_1       
        //   191: invokespecial   org/apache/xml/serializer/ToStream.processDirty:([CIICIZ)I
        //   194: istore          lastDirty
        //   196: iload           lastDirty
        //   198: istore          i
        //   200: iinc            i, 1
        //   203: iload           i
        //   205: iload           end
        //   207: if_icmpge       250
        //   210: aload_1         /* chars */
        //   211: iload           i
        //   213: caload         
        //   214: dup            
        //   215: istore          ch1
        //   217: bipush          32
        //   219: if_icmpeq       168
        //   222: iload           ch1
        //   224: bipush          10
        //   226: if_icmpne       236
        //   229: aload_0         /* this */
        //   230: getfield        org/apache/xml/serializer/ToStream.m_lineSepUse:Z
        //   233: ifne            168
        //   236: iload           ch1
        //   238: bipush          13
        //   240: if_icmpeq       168
        //   243: iload           ch1
        //   245: bipush          9
        //   247: if_icmpeq       168
        //   250: iload           i
        //   252: iload           end
        //   254: if_icmpge       262
        //   257: aload_0         /* this */
        //   258: iconst_1       
        //   259: putfield        org/apache/xml/serializer/ToStream.m_ispreserve:Z
        //   262: ldc             "1.0"
        //   264: aload_0         /* this */
        //   265: invokevirtual   org/apache/xml/serializer/SerializerBase.getVersion:()Ljava/lang/String;
        //   268: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   271: istore          isXML10
        //   273: goto            404
        //   276: goto            282
        //   279: iinc            i, 1
        //   282: iload           i
        //   284: iload           end
        //   286: if_icmpge       313
        //   289: aload_1         /* chars */
        //   290: iload           i
        //   292: caload         
        //   293: dup            
        //   294: istore          ch2
        //   296: bipush          127
        //   298: if_icmpge       313
        //   301: aload_0         /* this */
        //   302: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   305: iload           ch2
        //   307: invokevirtual   org/apache/xml/serializer/CharInfo.isTextASCIIClean:(I)Z
        //   310: ifne            279
        //   313: iload           i
        //   315: iload           end
        //   317: if_icmpne       323
        //   320: goto            411
        //   323: aload_1         /* chars */
        //   324: iload           i
        //   326: caload         
        //   327: istore          ch
        //   329: iload           ch
        //   331: invokestatic    org/apache/xml/serializer/ToStream.isCharacterInC0orC1Range:(C)Z
        //   334: ifne            371
        //   337: iload           isXML10
        //   339: ifne            350
        //   342: iload           ch
        //   344: invokestatic    org/apache/xml/serializer/ToStream.isNELorLSEPCharacter:(C)Z
        //   347: ifne            371
        //   350: aload_0         /* this */
        //   351: iload           ch
        //   353: invokevirtual   org/apache/xml/serializer/ToStream.escapingNotNeeded:(C)Z
        //   356: ifeq            371
        //   359: aload_0         /* this */
        //   360: getfield        org/apache/xml/serializer/ToStream.m_charInfo:Lorg/apache/xml/serializer/CharInfo;
        //   363: iload           ch
        //   365: invokevirtual   org/apache/xml/serializer/CharInfo.isSpecialTextChar:(I)Z
        //   368: ifeq            401
        //   371: bipush          34
        //   373: iload           ch
        //   375: if_icmpne       381
        //   378: goto            401
        //   381: aload_0         /* this */
        //   382: aload_1         /* chars */
        //   383: iload           end
        //   385: iload           i
        //   387: iload           ch
        //   389: iload           lastDirty
        //   391: iconst_1       
        //   392: invokespecial   org/apache/xml/serializer/ToStream.processDirty:([CIICIZ)I
        //   395: istore          lastDirty
        //   397: iload           lastDirty
        //   399: istore          i
        //   401: iinc            i, 1
        //   404: iload           i
        //   406: iload           end
        //   408: if_icmplt       282
        //   411: iload           lastDirty
        //   413: iconst_1       
        //   414: iadd           
        //   415: istore          startClean
        //   417: iload           i
        //   419: iload           startClean
        //   421: if_icmple       443
        //   424: iload           i
        //   426: iload           startClean
        //   428: isub           
        //   429: istore          lengthClean
        //   431: aload_0         /* this */
        //   432: getfield        org/apache/xml/serializer/SerializerBase.m_writer:Ljava/io/Writer;
        //   435: aload_1         /* chars */
        //   436: iload           startClean
        //   438: iload           lengthClean
        //   440: invokevirtual   java/io/Writer.write:([CII)V
        //   443: aload_0         /* this */
        //   444: iconst_1       
        //   445: putfield        org/apache/xml/serializer/ToStream.m_isprevtext:Z
        //   448: goto            463
        //   451: astore          e
        //   453: new             Lorg/xml/sax/SAXException;
        //   456: dup            
        //   457: aload           e
        //   459: invokespecial   org/xml/sax/SAXException.<init>:(Ljava/lang/Exception;)V
        //   462: athrow         
        //   463: aload_0         /* this */
        //   464: getfield        org/apache/xml/serializer/SerializerBase.m_tracer:Lorg/apache/xml/serializer/SerializerTrace;
        //   467: ifnull          477
        //   470: aload_0         /* this */
        //   471: aload_1         /* chars */
        //   472: iload_2         /* start */
        //   473: iload_3         /* length */
        //   474: invokespecial   org/apache/xml/serializer/SerializerBase.fireCharEvent:([CII)V
        //   477: return         
        //    Exceptions:
        //  throws org.xml.sax.SAXException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------
        //  0      478     0     this         Lorg/apache/xml/serializer/ToStream;
        //  0      478     1     chars        [C
        //  0      478     2     start        I
        //  0      478     3     length       I
        //  165    283     4     i            I
        //  217    231     5     ch1          C
        //  417    31      6     startClean   I
        //  157    291     7     end          I
        //  162    286     8     lastDirty    I
        //  273    175     9     isXML10      Z
        //  296    27      10    ch2          C
        //  329    72      10    ch           C
        //  431    12      10    lengthClean  I
        //  453    10      4     e            Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  152    448    451    463    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static boolean isCharacterInC0orC1Range(final char ch) {
        return ch != '\t' && ch != '\n' && ch != '\r' && ((ch >= '\u007f' && ch <= '\u009f') || (ch >= '\u0001' && ch <= '\u001f'));
    }
    
    private static boolean isNELorLSEPCharacter(final char ch) {
        return ch == '\u0085' || ch == '\u2028';
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
        if (super.m_inEntityRef && !this.m_expandDTDEntities) {
            return;
        }
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
            if (Encodings.isHighUTF16Surrogate(ch)) {
                int codePoint = 0;
                if (i + 1 >= len) {
                    throw new IOException(Utils.messages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) }));
                }
                final char next = chars[++i];
                if (!Encodings.isLowUTF16Surrogate(next)) {
                    throw new IOException(Utils.messages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) + " " + Integer.toHexString(next) }));
                }
                codePoint = Encodings.toCodePoint(ch, next);
                writer.write("&#");
                writer.write(Integer.toString(codePoint));
                writer.write(59);
                pos += 2;
            }
            else {
                if (isCharacterInC0orC1Range(ch) || ("1.1".equals(this.getVersion()) && isNELorLSEPCharacter(ch))) {
                    writer.write("&#");
                    writer.write(Integer.toString(ch));
                    writer.write(59);
                }
                else if ((!this.escapingNotNeeded(ch) || (fromTextNode && this.m_charInfo.isSpecialTextChar(ch)) || (!fromTextNode && this.m_charInfo.isSpecialAttrChar(ch))) && super.m_elemContext.m_currentElemDepth > 0) {
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
                this.addAttributeAlways("http://www.w3.org/2000/xmlns/", name, name, "CDATA", uri, false);
            }
            else if (!"".equals(uri)) {
                final String name = "xmlns:" + prefix;
                this.addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri, false);
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
        if (!this.m_expandDTDEntities && !super.m_inExternalDTD) {
            this.startNonEscaping();
            this.characters("&" + name + ';');
            this.endNonEscaping();
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
            final StringBuffer buf = new StringBuffer();
            for (int i = 0; i < l; ++i) {
                final char c = s.charAt(i);
                if (Character.isWhitespace(c)) {
                    if (!inCurly) {
                        if (buf.length() > 0) {
                            this.addCdataSectionElement(buf.toString(), v);
                            buf.setLength(0);
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
                buf.setLength(0);
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
                this.addAttribute("http://www.w3.org/2000/xmlns/", prefix, "xmlns:" + prefix, "CDATA", ns, false);
            }
            return prefix;
        }
        final String uri = super.m_prefixMap.lookupNamespace(prefixFromRawName);
        if (uri != null && uri.equals(ns)) {
            return null;
        }
        this.startPrefixMapping(prefixFromRawName, ns, false);
        this.addAttribute("http://www.w3.org/2000/xmlns/", prefixFromRawName, "xmlns:" + prefixFromRawName, "CDATA", ns, false);
        return prefixFromRawName;
    }
    
    void ensurePrefixIsDeclared(final String ns, final String rawName) throws SAXException {
        if (ns != null && ns.length() > 0) {
            final int index;
            final boolean no_prefix = (index = rawName.indexOf(":")) < 0;
            final String prefix = no_prefix ? "" : rawName.substring(0, index);
            if (null != prefix) {
                final String foundURI = super.m_prefixMap.lookupNamespace(prefix);
                if (null == foundURI || !foundURI.equals(ns)) {
                    this.startPrefixMapping(prefix, ns);
                    this.addAttributeAlways("http://www.w3.org/2000/xmlns/", no_prefix ? "xmlns" : prefix, no_prefix ? "xmlns" : ("xmlns:" + prefix), "CDATA", ns, false);
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
    
    public boolean addAttributeAlways(final String uri, final String localName, String rawName, final String type, final String value, final boolean xslAttribute) {
        int index;
        if (uri == null || localName == null || uri.length() == 0) {
            index = super.m_attributes.getIndex(rawName);
        }
        else {
            index = super.m_attributes.getIndex(uri, localName);
        }
        boolean was_added;
        if (index >= 0) {
            String old_value = null;
            if (super.m_tracer != null) {
                old_value = super.m_attributes.getValue(index);
                if (value.equals(old_value)) {
                    old_value = null;
                }
            }
            super.m_attributes.setValue(index, value);
            was_added = false;
            if (old_value != null) {
                this.firePseudoAttributes();
            }
        }
        else {
            if (xslAttribute) {
                final int colonIndex = rawName.indexOf(58);
                if (colonIndex > 0) {
                    String prefix = rawName.substring(0, colonIndex);
                    final NamespaceMappings.MappingRecord existing_mapping = super.m_prefixMap.getMappingFromPrefix(prefix);
                    if (existing_mapping != null && existing_mapping.m_declarationDepth == super.m_elemContext.m_currentElemDepth && !existing_mapping.m_uri.equals(uri)) {
                        prefix = super.m_prefixMap.lookupPrefix(uri);
                        if (prefix == null) {
                            prefix = super.m_prefixMap.generateNextPrefix();
                        }
                        rawName = prefix + ':' + localName;
                    }
                }
                try {
                    final String prefixUsed = this.ensureAttributesNamespaceIsDeclared(uri, localName, rawName);
                }
                catch (SAXException e) {
                    e.printStackTrace();
                }
            }
            super.m_attributes.addAttribute(uri, localName, rawName, type, value);
            was_added = true;
            if (super.m_tracer != null) {
                this.firePseudoAttributes();
            }
        }
        return was_added;
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
        this.m_cdataStartCalled = false;
        this.m_disableOutputEscapingStates.clear();
        this.m_escaping = true;
        this.m_inDoctype = false;
        this.m_ispreserve = false;
        this.m_ispreserve = false;
        this.m_isprevtext = false;
        this.m_isUTF8 = false;
        this.m_preserves.clear();
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_startNewLine = false;
        this.m_lineSepUse = true;
        this.m_expandDTDEntities = true;
    }
    
    public void setEncoding(final String encoding) {
        final String old = this.getEncoding();
        super.setEncoding(encoding);
        if (old == null || !old.equals(encoding)) {
            this.m_encodingInfo = Encodings.getEncodingInfo(encoding);
            if (encoding != null && this.m_encodingInfo.name == null) {
                final String msg = Utils.messages.createMessage("ER_ENCODING_NOT_SUPPORTED", new Object[] { encoding });
                try {
                    final Transformer tran = super.getTransformer();
                    if (tran != null) {
                        final ErrorListener errHandler = tran.getErrorListener();
                        if (null != errHandler && super.m_sourceLocator != null) {
                            errHandler.warning(new TransformerException(msg, super.m_sourceLocator));
                        }
                        else {
                            System.out.println(msg);
                        }
                    }
                    else {
                        System.out.println(msg);
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void notationDecl(final String name, final String pubID, final String sysID) throws SAXException {
        try {
            this.DTDprolog();
            super.m_writer.write("<!NOTATION ");
            super.m_writer.write(name);
            if (pubID != null) {
                super.m_writer.write(" PUBLIC \"");
                super.m_writer.write(pubID);
            }
            else {
                super.m_writer.write(" SYSTEM \"");
                super.m_writer.write(sysID);
            }
            super.m_writer.write("\" >");
            super.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void unparsedEntityDecl(final String name, final String pubID, final String sysID, final String notationName) throws SAXException {
        try {
            this.DTDprolog();
            super.m_writer.write("<!ENTITY ");
            super.m_writer.write(name);
            if (pubID != null) {
                super.m_writer.write(" PUBLIC \"");
                super.m_writer.write(pubID);
            }
            else {
                super.m_writer.write(" SYSTEM \"");
                super.m_writer.write(sysID);
            }
            super.m_writer.write("\" NDATA ");
            super.m_writer.write(notationName);
            super.m_writer.write(" >");
            super.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void DTDprolog() throws SAXException, IOException {
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
    }
    
    public void setDTDEntityExpansion(final boolean expand) {
        this.m_expandDTDEntities = expand;
    }
    
    public void setNewLine(final char[] eolChars) {
        this.m_lineSep = eolChars;
        this.m_lineSepLen = eolChars.length;
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
    
    static final class BoolStack
    {
        private boolean[] m_values;
        private int m_allocatedSize;
        private int m_index;
        
        public BoolStack() {
            this(32);
        }
        
        public BoolStack(final int size) {
            this.m_allocatedSize = size;
            this.m_values = new boolean[size];
            this.m_index = -1;
        }
        
        public final int size() {
            return this.m_index + 1;
        }
        
        public final void clear() {
            this.m_index = -1;
        }
        
        public final boolean push(final boolean val) {
            if (this.m_index == this.m_allocatedSize - 1) {
                this.grow();
            }
            return this.m_values[++this.m_index] = val;
        }
        
        public final boolean pop() {
            return this.m_values[this.m_index--];
        }
        
        public final boolean popAndTop() {
            --this.m_index;
            return this.m_index >= 0 && this.m_values[this.m_index];
        }
        
        public final void setTop(final boolean b) {
            this.m_values[this.m_index] = b;
        }
        
        public final boolean peek() {
            return this.m_values[this.m_index];
        }
        
        public final boolean peekOrFalse() {
            return this.m_index > -1 && this.m_values[this.m_index];
        }
        
        public final boolean peekOrTrue() {
            return this.m_index <= -1 || this.m_values[this.m_index];
        }
        
        public boolean isEmpty() {
            return this.m_index == -1;
        }
        
        private void grow() {
            this.m_allocatedSize *= 2;
            final boolean[] newVector = new boolean[this.m_allocatedSize];
            System.arraycopy(this.m_values, 0, newVector, 0, this.m_index + 1);
            this.m_values = newVector;
        }
    }
}
