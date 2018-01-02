// 
// Decompiled by Procyon v0.5.30
// 

package uk.co.wilson.xml;

import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import java.util.Locale;
import java.net.URL;
import java.io.InputStreamReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.SAXParseException;
import java.util.EmptyStackException;
import java.io.Writer;
import org.xml.sax.AttributeList;
import java.util.Vector;
import java.io.Reader;
import java.util.Stack;
import org.xml.sax.ErrorHandler;
import uk.org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import uk.org.xml.sax.Parser;

public class MinML implements Parser, Locator, DocumentHandler, ErrorHandler
{
    public static final int endStartName = 0;
    public static final int emitStartElement = 1;
    public static final int emitEndElement = 2;
    public static final int emitCharacters = 3;
    public static final int emitCharactersSave = 4;
    public static final int saveAttributeName = 5;
    public static final int saveAttributeValue = 6;
    public static final int startComment = 7;
    public static final int endComment = 8;
    public static final int incLevel = 9;
    public static final int decLevel = 10;
    public static final int startCDATA = 11;
    public static final int endCDATA = 12;
    public static final int processCharRef = 13;
    public static final int writeCdata = 14;
    public static final int exitParser = 15;
    public static final int parseError = 16;
    public static final int discardAndChange = 17;
    public static final int discardSaveAndChange = 18;
    public static final int saveAndChange = 19;
    public static final int change = 20;
    public static final int inSkipping = 0;
    public static final int inSTag = 1;
    public static final int inPossiblyAttribute = 2;
    public static final int inNextAttribute = 3;
    public static final int inAttribute = 4;
    public static final int inAttribute1 = 5;
    public static final int inAttributeValue = 6;
    public static final int inAttributeQuoteValue = 7;
    public static final int inAttributeQuotesValue = 8;
    public static final int inETag = 9;
    public static final int inETag1 = 10;
    public static final int inMTTag = 11;
    public static final int inTag = 12;
    public static final int inTag1 = 13;
    public static final int inPI = 14;
    public static final int inPI1 = 15;
    public static final int inPossiblySkipping = 16;
    public static final int inCharData = 17;
    public static final int inCDATA = 18;
    public static final int inCDATA1 = 19;
    public static final int inComment = 20;
    public static final int inDTD = 21;
    private DocumentHandler extDocumentHandler;
    private org.xml.sax.DocumentHandler documentHandler;
    private ErrorHandler errorHandler;
    private final Stack tags;
    private int lineNumber;
    private int columnNumber;
    private final int initialBufferSize;
    private final int bufferIncrement;
    private static final byte[] charClasses;
    private static final String[] operands;
    
    public MinML(final int initialBufferSize, final int bufferIncrement) {
        this.extDocumentHandler = this;
        this.documentHandler = this;
        this.errorHandler = this;
        this.tags = new Stack();
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.initialBufferSize = initialBufferSize;
        this.bufferIncrement = bufferIncrement;
    }
    
    public MinML() {
        this(256, 128);
    }
    
    public void parse(final Reader reader) throws SAXException, IOException {
        final Vector<String> vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        final AttributeList attributeList = new AttributeList() {
            private final /* synthetic */ Vector val$attributeNames = vector;
            private final /* synthetic */ Vector val$attributeValues = vector2;
            
            public int getLength() {
                return this.val$attributeNames.size();
            }
            
            public String getName(final int n) {
                return this.val$attributeNames.elementAt(n);
            }
            
            public String getType(final int n) {
                return "CDATA";
            }
            
            public String getValue(final int n) {
                return this.val$attributeValues.elementAt(n);
            }
            
            public String getType(final String s) {
                return "CDATA";
            }
            
            public String getValue(final String s) {
                final int index = this.val$attributeNames.indexOf(s);
                return (index == -1) ? null : ((String)this.val$attributeValues.elementAt(index));
            }
        };
        final MinMLBuffer minMLBuffer = new MinMLBuffer(reader);
        int n = 0;
        int n2 = 0;
        int n3 = -1;
        String s = null;
        String s2 = MinML.operands[0];
        this.lineNumber = 1;
        this.columnNumber = 0;
        try {
        Label_1179:
            while (true) {
                ++n;
                int n4 = (minMLBuffer.nextIn == minMLBuffer.lastIn) ? minMLBuffer.read() : minMLBuffer.chars[minMLBuffer.nextIn++];
                char c;
                if (n4 > 93) {
                    c = s2.charAt(14);
                }
                else {
                    final byte b = MinML.charClasses[n4 + 1];
                    if (b == -1) {
                        this.fatalError("Document contains illegal control character with value " + n4, this.lineNumber, this.columnNumber);
                    }
                    if (b == 12) {
                        if (n4 == 13) {
                            n4 = 10;
                            n = -1;
                        }
                        if (n4 == 10) {
                            if (n == 0) {
                                continue;
                            }
                            if (n != -1) {
                                n = 0;
                            }
                            ++this.lineNumber;
                            this.columnNumber = 0;
                        }
                    }
                    c = s2.charAt(b);
                }
                ++this.columnNumber;
                String s3 = MinML.operands[c >>> 8];
                Label_0480: {
                    switch (c & '\u00ff') {
                        case '\0': {
                            s = minMLBuffer.getString();
                            if (n4 != 62 && n4 != 47) {
                                break;
                            }
                        }
                        case '\u0001': {
                            minMLBuffer.pushWriter(this.extDocumentHandler.startElement(s, attributeList, this.tags.empty() ? this.extDocumentHandler.startDocument(minMLBuffer) : minMLBuffer.getWriter()));
                            this.tags.push(s);
                            vector2.removeAllElements();
                            vector.removeAllElements();
                            if (n3 != -1) {
                                ++n3;
                            }
                            if (n4 != 47) {
                                break;
                            }
                            break Label_0480;
                        }
                        case '\u0002': {
                            try {
                                final String s4 = this.tags.pop();
                                minMLBuffer.popWriter();
                                s = minMLBuffer.getString();
                                if (n4 != 47 && !s.equals(s4)) {
                                    this.fatalError("end tag </" + s + "> does not match begin tag <" + s4 + ">", this.lineNumber, this.columnNumber);
                                }
                                else {
                                    this.documentHandler.endElement(s4);
                                    if (this.tags.empty()) {
                                        this.documentHandler.endDocument();
                                        return;
                                    }
                                }
                            }
                            catch (EmptyStackException ex) {
                                this.fatalError("end tag at begining of document", this.lineNumber, this.columnNumber);
                            }
                            if (n3 != -1 && --n3 == 0) {
                                s3 = MinML.operands[17];
                                break;
                            }
                            break;
                        }
                        case '\u0003': {
                            minMLBuffer.flush();
                            break;
                        }
                        case '\u0004': {
                            if (n3 == -1) {
                                n3 = 0;
                            }
                            minMLBuffer.flush();
                            minMLBuffer.saveChar((char)n4);
                            break;
                        }
                        case '\u0005': {
                            vector.addElement(minMLBuffer.getString());
                            break;
                        }
                        case '\u0006': {
                            vector2.addElement(minMLBuffer.getString());
                            break;
                        }
                        case '\u0007': {
                            if (minMLBuffer.read() != 45) {
                                continue;
                            }
                            break;
                        }
                        case '\b': {
                            if (minMLBuffer.read() != 45) {
                                continue;
                            }
                            int read;
                            while ((read = minMLBuffer.read()) == 45) {}
                            if (read == 62) {
                                break;
                            }
                            continue;
                        }
                        case '\t': {
                            ++n2;
                            break;
                        }
                        case '\n': {
                            if (n2 == 0) {
                                break;
                            }
                            --n2;
                            continue;
                        }
                        case '\u000b': {
                            if (minMLBuffer.read() != 67) {
                                continue;
                            }
                            if (minMLBuffer.read() != 68) {
                                continue;
                            }
                            if (minMLBuffer.read() != 65) {
                                continue;
                            }
                            if (minMLBuffer.read() != 84) {
                                continue;
                            }
                            if (minMLBuffer.read() != 65) {
                                continue;
                            }
                            if (minMLBuffer.read() != 91) {
                                continue;
                            }
                            break;
                        }
                        case '\f': {
                            int n5;
                            if ((n5 = minMLBuffer.read()) == 93) {
                                while ((n5 = minMLBuffer.read()) == 93) {
                                    minMLBuffer.write(93);
                                }
                                if (n5 == 62) {
                                    break;
                                }
                                minMLBuffer.write(93);
                            }
                            minMLBuffer.write(93);
                            minMLBuffer.write(n5);
                            continue;
                        }
                        case '\r': {
                            int char1 = 0;
                            int n6 = minMLBuffer.read();
                            while (true) {
                                if ("#amp;&pos;'quot;\"gt;>lt;<".charAt(char1) == n6) {
                                    ++char1;
                                    if (n6 == ';') {
                                        minMLBuffer.write("#amp;&pos;'quot;\"gt;>lt;<".charAt(char1));
                                        break Label_0480;
                                    }
                                    if (n6 == '#') {
                                        int n7 = minMLBuffer.read();
                                        int n8;
                                        if (n7 == 120) {
                                            n8 = 16;
                                            n7 = minMLBuffer.read();
                                        }
                                        else {
                                            n8 = 10;
                                        }
                                        int digit = Character.digit((char)n7, n8);
                                        while (true) {
                                            n6 = minMLBuffer.read();
                                            final int digit2 = Character.digit((char)n6, n8);
                                            if (digit2 == -1) {
                                                break;
                                            }
                                            digit = (char)(digit * n8 + digit2);
                                        }
                                        if (n6 == ';' && digit != -1) {
                                            minMLBuffer.write(digit);
                                            break Label_0480;
                                        }
                                        this.fatalError("invalid Character Entitiy", this.lineNumber, this.columnNumber);
                                    }
                                    else {
                                        n6 = minMLBuffer.read();
                                    }
                                }
                                else {
                                    char1 = "\u0001\u000b\u0006\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u0011\u00ff\u00ff\u00ff\u00ff\u00ff\u0015\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff".charAt(char1);
                                    if (char1 != 255) {
                                        continue;
                                    }
                                    this.fatalError("invalid Character Entitiy", this.lineNumber, this.columnNumber);
                                }
                            }
                            break;
                        }
                        case '\u0010': {
                            this.fatalError(s3, this.lineNumber, this.columnNumber);
                        }
                        case '\u000f': {
                            break Label_1179;
                        }
                        case '\u000e': {
                            minMLBuffer.write(n4);
                            break;
                        }
                        case '\u0011': {
                            minMLBuffer.reset();
                            break;
                        }
                        case '\u0012': {
                            minMLBuffer.reset();
                        }
                        case '\u0013': {
                            minMLBuffer.saveChar((char)n4);
                            break;
                        }
                    }
                }
                s2 = s3;
            }
        }
        catch (IOException e) {
            this.errorHandler.fatalError(new SAXParseException(e.toString(), null, null, this.lineNumber, this.columnNumber, e));
        }
        finally {
            this.errorHandler = this;
            this.extDocumentHandler = this;
            this.documentHandler = this;
            this.tags.removeAllElements();
        }
    }
    
    public void parse(final InputSource inputSource) throws SAXException, IOException {
        if (inputSource.getCharacterStream() != null) {
            this.parse(inputSource.getCharacterStream());
        }
        else if (inputSource.getByteStream() != null) {
            this.parse(new InputStreamReader(inputSource.getByteStream()));
        }
        else {
            this.parse(new InputStreamReader(new URL(inputSource.getSystemId()).openStream()));
        }
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        this.parse(new InputSource(systemId));
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        throw new SAXException("Not supported");
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
    }
    
    public void setDocumentHandler(final org.xml.sax.DocumentHandler documentHandler) {
        this.documentHandler = ((documentHandler == null) ? this : documentHandler);
        this.extDocumentHandler = this;
    }
    
    public void setDocumentHandler(final DocumentHandler documentHandler) {
        DocumentHandler documentHandler2;
        DocumentHandler extDocumentHandler;
        if (documentHandler == null) {
            documentHandler2 = this;
            extDocumentHandler = this;
        }
        else {
            documentHandler2 = documentHandler;
            extDocumentHandler = documentHandler;
        }
        this.extDocumentHandler = extDocumentHandler;
        (this.documentHandler = documentHandler2).setDocumentLocator(this);
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = ((errorHandler == null) ? this : errorHandler);
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public Writer startDocument(final Writer writer) throws SAXException {
        this.documentHandler.startDocument();
        return writer;
    }
    
    public void endDocument() throws SAXException {
    }
    
    public void startElement(final String s, final AttributeList list) throws SAXException {
    }
    
    public Writer startElement(final String s, final AttributeList list, final Writer writer) throws SAXException {
        this.documentHandler.startElement(s, list);
        return writer;
    }
    
    public void endElement(final String s) throws SAXException {
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
    }
    
    public void warning(final SAXParseException ex) throws SAXException {
    }
    
    public void error(final SAXParseException ex) throws SAXException {
    }
    
    public void fatalError(final SAXParseException ex) throws SAXException {
        throw ex;
    }
    
    public String getPublicId() {
        return "";
    }
    
    public String getSystemId() {
        return "";
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
    
    private void fatalError(final String message, final int lineNumber, final int columnNumber) throws SAXException {
        this.errorHandler.fatalError(new SAXParseException(message, null, null, lineNumber, columnNumber));
    }
    
    static {
        charClasses = new byte[] { 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, 12, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, 8, 7, 14, 14, 14, 3, 6, 14, 14, 14, 14, 14, 11, 14, 2, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 5, 1, 4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 9, 14, 10 };
        operands = new String[] { "\u0d14\u1610\u1610\u1610\u1610\u1610\u1610\u1610\u1610\u1610\u1610\u1610\u0014\u000f\u1610", "\u1710\u1000\u0b00\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u0113\u0200\u1810\u0113", "\u1710\u1001\u0b01\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u0214\u1810\u0413", "\u1710\u1001\u0b01\u1710\u1910\u1910\u1910\u1910\u1910\u1910\u1910\u1910\u0314\u1810\u0413", "\u1910\u1910\u1910\u1910\u1910\u0605\u1910\u1910\u1910\u1910\u1910\u0413\u0514\u1810\u0413", "\u1910\u1910\u1910\u1910\u1910\u0605\u1910\u1910\u1910\u1910\u1910\u1910\u0514\u1810\u1910", "\u1a10\u1a10\u1a10\u1a10\u1a10\u1a10\u0714\u0814\u1a10\u1a10\u1a10\u1a10\u0614\u1810\u1a10", "\u0713\u0713\u0713\u070d\u0713\u0713\u0306\u0713\u0713\u0713\u0713\u0713\u0713\u1810\u0713", "\u0813\u0813\u0813\u080d\u0813\u0813\u0813\u0306\u0813\u0813\u0813\u0813\u0813\u1810\u0813", "\u1710\u1002\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u0913\u0914\u1810\u0913", "\u1b10\u1b10\u0903\u1b10\u1b10\u1b10\u1b10\u1b10\u1214\u1b10\u1b10\u1b10\u1b10\u1810\u0104", "\u1710\u1014\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1710\u1810\u1710", "\u1710\u1c10\u0911\u1710\u0e11\u1710\u1710\u1710\u1211\u1710\u1710\u1710\u1710\u1810\u0112", "\u1710\u1c10\u0911\u1710\u0e11\u1710\u1710\u1710\u1211\u1710\u1710\u1710\u1710\u1810\u0112", "\u0e14\u0e14\u0e14\u0e14\u0f14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u1810\u0e14", "\u0e14\u0014\u0e14\u0e14\u0f14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u0e14\u1810\u0e14", "\u0c14\u110e\u110e\u110d\u110e\u110e\u110e\u110e\u110e\u110e\u110e\u110e\u1013\u1810\u110e", "\u0a14\u110e\u110e\u110d\u110e\u110e\u110e\u110e\u110e\u110e\u110e\u110e\u110e\u1810\u110e", "\u1d10\u1d10\u1d10\u1d10\u1d10\u1d10\u1d10\u1d10\u1d10\u130b\u1d10\u1407\u1d10\u1810\u1514", "\u130e\u130e\u130e\u130e\u130e\u130e\u130e\u130e\u130e\u130e\u110c\u130e\u130e\u1810\u130e", "\u1414\u1414\u1414\u1414\u1414\u1414\u1414\u1414\u1414\u1414\u1414\b\u1414\u1810\u1414", "\u1509\n\u1514\u1514\u1514\u1514\u1514\u1514\u1514\u1514\u1514\u1514\u1514\u1810\u1514", "expected Element", "unexpected character in tag", "unexpected end of file found", "attribute name not followed by '='", "invalid attribute value", "expecting end tag", "empty tag", "unexpected character after <!" };
    }
    
    private class MinMLBuffer extends Writer
    {
        private int nextIn;
        private int lastIn;
        private char[] chars;
        private final Reader in;
        private int count;
        private Writer writer;
        private boolean flushed;
        private boolean written;
        
        public MinMLBuffer(final Reader in) {
            this.nextIn = 0;
            this.lastIn = 0;
            this.chars = new char[MinML.this.initialBufferSize];
            this.count = 0;
            this.writer = this;
            this.flushed = false;
            this.written = false;
            this.in = in;
        }
        
        public void close() throws IOException {
            this.flush();
        }
        
        public void flush() throws IOException {
            try {
                this._flush();
                if (this.writer != this) {
                    this.writer.flush();
                }
            }
            finally {
                this.flushed = true;
            }
        }
        
        public void write(final int n) throws IOException {
            this.written = true;
            this.chars[this.count++] = (char)n;
        }
        
        public void write(final char[] array, final int n, final int n2) throws IOException {
            this.written = true;
            System.arraycopy(array, n, this.chars, this.count, n2);
            this.count += n2;
        }
        
        public void saveChar(final char c) {
            this.written = false;
            this.chars[this.count++] = c;
        }
        
        public void pushWriter(final Writer writer) {
            MinML.this.tags.push(this.writer);
            this.writer = ((writer == null) ? this : writer);
            final boolean b = false;
            this.written = b;
            this.flushed = b;
        }
        
        public Writer getWriter() {
            return this.writer;
        }
        
        public void popWriter() throws IOException {
            try {
                if (!this.flushed && this.writer != this) {
                    this.writer.flush();
                }
            }
            finally {
                this.writer = MinML.this.tags.pop();
                final boolean b = false;
                this.written = b;
                this.flushed = b;
            }
        }
        
        public String getString() {
            final String s = new String(this.chars, 0, this.count);
            this.count = 0;
            return s;
        }
        
        public void reset() {
            this.count = 0;
        }
        
        public int read() throws IOException {
            if (this.nextIn == this.lastIn) {
                if (this.count != 0) {
                    if (this.written) {
                        this._flush();
                    }
                    else if (this.count >= this.chars.length - MinML.this.bufferIncrement) {
                        final char[] chars = new char[this.chars.length + MinML.this.bufferIncrement];
                        System.arraycopy(this.chars, 0, chars, 0, this.count);
                        this.chars = chars;
                    }
                }
                final int read = this.in.read(this.chars, this.count, this.chars.length - this.count);
                if (read == -1) {
                    return -1;
                }
                this.nextIn = this.count;
                this.lastIn = this.count + read;
            }
            return this.chars[this.nextIn++];
        }
        
        private void _flush() throws IOException {
            if (this.count != 0) {
                try {
                    if (this.writer == this) {
                        try {
                            MinML.this.documentHandler.characters(this.chars, 0, this.count);
                            return;
                        }
                        catch (SAXException ex) {
                            throw new IOException(ex.toString());
                        }
                    }
                    this.writer.write(this.chars, 0, this.count);
                }
                finally {
                    this.count = 0;
                }
            }
        }
    }
}
