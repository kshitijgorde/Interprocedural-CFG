// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.io.EOFException;
import netscape.security.PrivilegeManager;
import java.net.URLConnection;
import java.util.Stack;
import java.io.InputStream;
import java.util.Hashtable;

public class XMLParser
{
    private static final boolean USE_CHEATS = true;
    private static boolean bNeedsPriv;
    public static final int CONTENT_UNDECLARED = 0;
    public static final int CONTENT_ANY = 1;
    public static final int CONTENT_EMPTY = 2;
    public static final int CONTENT_MIXED = 3;
    public static final int CONTENT_ELEMENTS = 4;
    public static final int ENTITY_UNDECLARED = 0;
    public static final int ENTITY_INTERNAL = 1;
    public static final int ENTITY_NDATA = 2;
    public static final int ENTITY_TEXT = 3;
    public static final int ATTRIBUTE_UNDECLARED = 0;
    public static final int ATTRIBUTE_CDATA = 1;
    public static final int ATTRIBUTE_ID = 2;
    public static final int ATTRIBUTE_IDREF = 3;
    public static final int ATTRIBUTE_IDREFS = 4;
    public static final int ATTRIBUTE_ENTITY = 5;
    public static final int ATTRIBUTE_ENTITIES = 6;
    public static final int ATTRIBUTE_NMTOKEN = 7;
    public static final int ATTRIBUTE_NMTOKENS = 8;
    public static final int ATTRIBUTE_ENUMERATED = 9;
    public static final int ATTRIBUTE_NOTATION = 10;
    private static Hashtable attributeTypeHash;
    private static final int ENCODING_UTF_8 = 1;
    public static final int ATTRIBUTE_DEFAULT_UNDECLARED = 0;
    public static final int ATTRIBUTE_DEFAULT_SPECIFIED = 1;
    public static final int ATTRIBUTE_DEFAULT_IMPLIED = 2;
    public static final int ATTRIBUTE_DEFAULT_REQUIRED = 3;
    public static final int ATTRIBUTE_DEFAULT_FIXED = 4;
    private static final int INPUT_NONE = 0;
    private static final int INPUT_INTERNAL = 1;
    private static final int INPUT_EXTERNAL = 2;
    private static final int INPUT_STREAM = 3;
    private static final int INPUT_BUFFER = 4;
    private static final int LIT_CHAR_REF = 1;
    private static final int LIT_ENTITY_REF = 2;
    private static final int LIT_PE_REF = 4;
    private static final int LIT_NORMALIZE = 8;
    private static final int CONTEXT_NONE = 0;
    private static final int CONTEXT_DTD = 1;
    private static final int CONTEXT_ENTITYVALUE = 2;
    private static final int CONTEXT_ATTRIBUTEVALUE = 3;
    XMLHandler handler;
    private InputStream is;
    private int line;
    private int column;
    private int sourceType;
    private Stack inputStack;
    private URLConnection externalEntity;
    private int encoding;
    private int currentByteCount;
    private int errorCount;
    private static final int READ_BUFFER_MAX = 16384;
    private char[] readBuffer;
    private int readBufferPos;
    private int readBufferLength;
    private int readBufferOverflow;
    private byte[] rawReadBuffer;
    private static int DATA_BUFFER_INITIAL;
    private char[] dataBuffer;
    private int dataBufferPos;
    private static int NAME_BUFFER_INITIAL;
    private char[] nameBuffer;
    private int nameBufferPos;
    private Hashtable elementInfo;
    private Hashtable entityInfo;
    private Hashtable notationInfo;
    private String currentElement;
    private int currentElementContent;
    private String basePublicId;
    private String baseURI;
    private int baseEncoding;
    private InputStream baseInputStream;
    private char[] baseInputBuffer;
    private int baseInputBufferStart;
    private int baseInputBufferLength;
    private Stack entityStack;
    private int context;
    private Object[] symbolTable;
    private static final int SYMBOL_TABLE_LENGTH = 1087;
    private String[] tagAttributes;
    private int tagAttributePos;
    private boolean sawCR;
    
    public XMLParser() {
    }
    
    public XMLParser(final boolean bNeedsPriv) {
        XMLParser.bNeedsPriv = bNeedsPriv;
    }
    
    public void setHandler(final XMLHandler handler) {
        this.handler = handler;
    }
    
    public void parse(final String s, final String s2) throws Exception {
        this.parse(s, s2, null);
    }
    
    public synchronized void parse(final String baseURI, final String basePublicId, final InputStream baseInputStream) throws Exception {
        this.basePublicId = basePublicId;
        this.baseURI = baseURI;
        this.baseInputStream = baseInputStream;
        if (XMLParser.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.initializeVariables();
        this.setInternalEntity(this.intern("amp"), "&#38;");
        this.setInternalEntity(this.intern("lt"), "&#60;");
        this.setInternalEntity(this.intern("gt"), "&#62;");
        this.setInternalEntity(this.intern("apos"), "&#39;");
        this.setInternalEntity(this.intern("quot"), "&#34;");
        if (this.handler != null) {
            this.handler.startDocument();
        }
        this.pushURL("[document]", this.basePublicId, this.baseURI, this.baseInputStream);
        this.parseDocument();
        if (this.readBufferPos < this.readBufferLength || (this.is != null && this.is.available() > 0)) {
            this.error("end of XML document before end of input", null, null);
        }
        if (this.handler != null) {
            this.handler.endDocument();
        }
        this.cleanupVariables();
    }
    
    void error(String s, final String s2, final String s3) throws Exception {
        ++this.errorCount;
        if (s2 != null) {
            s = s + " (found \"" + s2 + "\")";
        }
        if (s3 != null) {
            s = s + " (expected \"" + s3 + "\")";
        }
        if (this.handler != null) {
            if (this.externalEntity != null) {
                this.handler.error(s, this.externalEntity.getURL().toString(), this.line, this.column);
            }
            else {
                this.handler.error(s, null, this.line, this.column);
            }
        }
    }
    
    void error(final String s, final char c, final String s2) throws Exception {
        this.error(s, new Character(c).toString(), s2);
    }
    
    void parseDocument() throws Exception {
        this.parseProlog();
        this.require('<');
        this.parseElement();
        try {
            this.parseMisc();
        }
        catch (EOFException ex) {}
    }
    
    void parseComment() throws Exception {
        this.skipUntil("-->");
    }
    
    void parsePI() throws Exception {
        final String nmtoken = this.readNmtoken(true);
        if (!this.tryRead("?>")) {
            this.requireWhitespace();
            this.parseUntil("?>");
        }
        if (this.handler != null) {
            this.handler.processingInstruction(nmtoken, this.dataBufferToString());
        }
    }
    
    void parseCDSect() throws Exception {
        this.parseUntil("]]>");
    }
    
    void parseProlog() throws Exception {
        this.parseMisc();
        if (this.tryRead("<!DOCTYPE")) {
            this.parseDoctypedecl();
            this.parseMisc();
        }
    }
    
    void parseXMLDecl(final boolean b) throws Exception {
        this.require("version");
        this.parseEq();
        final String literal = this.readLiteral(0);
        if (!literal.equals("1.0")) {
            this.error("unsupported XML version", literal, "1.0");
        }
        this.skipWhitespace();
        if (this.tryRead("encoding")) {
            this.parseEq();
            this.readLiteral(0);
        }
        this.skipWhitespace();
        if (this.tryRead("standalone")) {
            this.parseEq();
            this.readLiteral(0);
        }
        this.skipWhitespace();
        this.require("?>");
    }
    
    void parseTextDecl(final boolean b) throws Exception {
        if (this.tryRead("version")) {
            this.parseEq();
            final String literal = this.readLiteral(0);
            if (!literal.equals("1.0")) {
                this.error("unsupported XML version", literal, "1.0");
            }
            this.requireWhitespace();
        }
        this.require("encoding");
        this.parseEq();
        this.readLiteral(0);
        this.skipWhitespace();
        this.require("?>");
    }
    
    void parseMisc() throws Exception {
        while (true) {
            this.skipWhitespace();
            if (this.tryRead("<?")) {
                this.parsePI();
            }
            else {
                if (!this.tryRead("<!--")) {
                    break;
                }
                this.parseComment();
            }
        }
    }
    
    void parseDoctypedecl() throws Exception {
        this.requireWhitespace();
        final String nmtoken = this.readNmtoken(true);
        this.skipWhitespace();
        final String[] externalIds = this.readExternalIds(false);
        this.skipWhitespace();
        if (this.tryRead('[')) {
            while (true) {
                this.context = 1;
                this.skipWhitespace();
                this.context = 0;
                if (this.tryRead(']')) {
                    break;
                }
                this.context = 1;
                this.parseMarkupdecl();
                this.context = 0;
            }
        }
        if (externalIds[1] != null) {
            this.pushURL("[external subset]", externalIds[0], externalIds[1], null);
            while (true) {
                this.context = 1;
                this.skipWhitespace();
                this.context = 0;
                if (this.tryRead('>')) {
                    break;
                }
                this.context = 1;
                this.parseMarkupdecl();
                this.context = 0;
            }
        }
        else {
            this.skipWhitespace();
            this.require('>');
        }
        if (this.handler != null) {
            this.handler.doctypeDecl(nmtoken, externalIds[0], externalIds[1]);
        }
    }
    
    void parseMarkupdecl() throws Exception {
        if (this.tryRead("<!ELEMENT")) {
            this.parseElementdecl();
        }
        else if (this.tryRead("<!ATTLIST")) {
            this.parseAttlistDecl();
        }
        else if (this.tryRead("<!ENTITY")) {
            this.parseEntityDecl();
        }
        else if (this.tryRead("<!NOTATION")) {
            this.parseNotationDecl();
        }
        else if (this.tryRead("<?")) {
            this.parsePI();
        }
        else if (this.tryRead("<!--")) {
            this.parseComment();
        }
        else if (this.tryRead("<![")) {
            this.parseConditionalSect();
        }
        else {
            this.error("expected markup declaration", null, null);
        }
    }
    
    void parseElement() throws Exception {
        final int currentElementContent = this.currentElementContent;
        final String currentElement = this.currentElement;
        this.tagAttributePos = 0;
        final String nmtoken = this.readNmtoken(true);
        this.currentElement = nmtoken;
        this.currentElementContent = this.getElementContentType(nmtoken);
        if (this.currentElementContent == 0) {
            this.currentElementContent = 1;
        }
        if (nmtoken != null && this.handler != null) {
            this.handler.startElement(nmtoken);
        }
        this.skipWhitespace();
        char c;
        for (c = this.readCh(); c != '/' && c != '>'; c = this.readCh()) {
            this.unread(c);
            this.parseAttribute(nmtoken);
            this.skipWhitespace();
        }
        this.unread(c);
        final Enumeration declaredAttributes = this.declaredAttributes(nmtoken);
        if (declaredAttributes != null) {
        Label_0129:
            while (declaredAttributes.hasMoreElements()) {
                final String s = declaredAttributes.nextElement();
                for (int i = 0; i < this.tagAttributePos; ++i) {
                    if (this.tagAttributes[i] == s) {
                        continue Label_0129;
                    }
                }
                if (this.handler != null) {
                    this.handler.attribute(s, this.getAttributeExpandedValue(nmtoken, s), false);
                }
            }
        }
        switch (this.readCh()) {
            case '>': {
                this.parseContent();
                break;
            }
            case '/': {
                this.require('>');
                if (this.handler != null) {
                    this.handler.endElement(nmtoken);
                    break;
                }
                break;
            }
        }
        this.currentElement = currentElement;
        this.currentElementContent = currentElementContent;
    }
    
    void parseAttribute(final String s) throws Exception {
        final String intern = this.readNmtoken(true).intern();
        final int attributeDefaultValueType = this.getAttributeDefaultValueType(s, intern);
        this.parseEq();
        String s2;
        if (attributeDefaultValueType == 1 || attributeDefaultValueType == 0) {
            s2 = this.readLiteral(3);
        }
        else {
            s2 = this.readLiteral(11);
        }
        if (this.handler != null) {
            this.handler.attribute(intern, s2, true);
        }
        this.dataBufferPos = 0;
        if (this.tagAttributePos == this.tagAttributes.length) {
            final String[] tagAttributes = new String[this.tagAttributes.length * 2];
            System.arraycopy(this.tagAttributes, 0, tagAttributes, 0, this.tagAttributePos);
            this.tagAttributes = tagAttributes;
        }
        this.tagAttributes[this.tagAttributePos++] = intern;
    }
    
    void parseEq() throws Exception {
        this.skipWhitespace();
        this.require('=');
        this.skipWhitespace();
    }
    
    void parseETag() throws Exception {
        final String nmtoken = this.readNmtoken(true);
        if (nmtoken != this.currentElement) {
            this.error("mismatched end tag", nmtoken, this.currentElement);
        }
        this.skipWhitespace();
        this.require('>');
        if (this.handler != null) {
            this.handler.endElement(nmtoken);
        }
    }
    
    void parseContent() throws Exception {
    Label_0232:
        while (true) {
            switch (this.currentElementContent) {
                case 1:
                case 3: {
                    this.parsePCData();
                    break;
                }
                case 4: {
                    this.parseWhitespace();
                    break;
                }
            }
            switch (this.readCh()) {
                case '&': {
                    final char ch = this.readCh();
                    if (ch == '#') {
                        this.parseCharRef();
                        continue;
                    }
                    this.unread(ch);
                    this.parseEntityRef(true);
                    continue;
                }
                case '<': {
                    final char ch2 = this.readCh();
                    switch (ch2) {
                        case 33: {
                            final char ch3 = this.readCh();
                            switch (ch3) {
                                case 45: {
                                    this.require('-');
                                    this.parseComment();
                                    continue;
                                }
                                case 91: {
                                    this.require("CDATA[");
                                    this.parseCDSect();
                                    continue;
                                }
                                default: {
                                    this.error("expected comment or CDATA section", ch3, null);
                                    continue;
                                }
                            }
                            break;
                        }
                        case 63: {
                            this.dataBufferFlush();
                            this.parsePI();
                            continue;
                        }
                        case 47: {
                            break Label_0232;
                        }
                        default: {
                            this.dataBufferFlush();
                            this.unread(ch2);
                            this.parseElement();
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        this.dataBufferFlush();
        this.parseETag();
    }
    
    void parseElementdecl() throws Exception {
        this.requireWhitespace();
        final String nmtoken = this.readNmtoken(true);
        this.requireWhitespace();
        this.parseContentspec(nmtoken);
        this.skipWhitespace();
        this.require('>');
    }
    
    void parseContentspec(final String s) throws Exception {
        if (this.tryRead("EMPTY")) {
            this.setElement(s, 2, null, null);
            return;
        }
        if (this.tryRead("ANY")) {
            this.setElement(s, 1, null, null);
            return;
        }
        this.require('(');
        this.dataBufferAppend('(');
        this.skipWhitespace();
        if (this.tryRead("#PCDATA")) {
            this.dataBufferAppend("#PCDATA");
            this.parseMixed();
            this.setElement(s, 3, this.dataBufferToString(), null);
        }
        else {
            this.parseElements();
            this.setElement(s, 4, this.dataBufferToString(), null);
        }
    }
    
    void parseElements() throws Exception {
        this.skipWhitespace();
        this.parseCp();
        this.skipWhitespace();
        final char ch = this.readCh();
        switch (ch) {
            case 41: {
                this.dataBufferAppend(')');
                final char ch2 = this.readCh();
                switch (ch2) {
                    case 42:
                    case 43:
                    case 63: {
                        this.dataBufferAppend(ch2);
                        break;
                    }
                    default: {
                        this.unread(ch2);
                        break;
                    }
                }
            }
            case 44:
            case 124: {
                final char c = ch;
                this.dataBufferAppend(ch);
                while (true) {
                    this.skipWhitespace();
                    this.parseCp();
                    this.skipWhitespace();
                    final char ch3 = this.readCh();
                    if (ch3 == ')') {
                        this.dataBufferAppend(')');
                        final char ch4 = this.readCh();
                        switch (ch4) {
                            case 42:
                            case 43:
                            case 63: {
                                this.dataBufferAppend(ch4);
                                return;
                            }
                            default: {
                                this.unread(ch4);
                                return;
                            }
                        }
                    }
                    else {
                        if (ch3 != c) {
                            this.error("bad separator in content model", ch3, null);
                            return;
                        }
                        this.dataBufferAppend(ch3);
                    }
                }
                break;
            }
            default: {
                this.error("bad separator in content model", ch, null);
            }
        }
    }
    
    void parseCp() throws Exception {
        if (this.tryRead('(')) {
            this.dataBufferAppend('(');
            this.parseElements();
        }
        else {
            this.dataBufferAppend(this.readNmtoken(true));
            final char ch = this.readCh();
            switch (ch) {
                case 42:
                case 43:
                case 63: {
                    this.dataBufferAppend(ch);
                    break;
                }
                default: {
                    this.unread(ch);
                    break;
                }
            }
        }
    }
    
    void parseMixed() throws Exception {
        this.skipWhitespace();
        if (this.tryRead(')')) {
            this.dataBufferAppend(")*");
            this.tryRead('*');
            return;
        }
        this.skipWhitespace();
        while (!this.tryRead(")*")) {
            this.require('|');
            this.dataBufferAppend('|');
            this.skipWhitespace();
            this.dataBufferAppend(this.readNmtoken(true));
            this.skipWhitespace();
        }
        this.dataBufferAppend(")*");
    }
    
    void parseAttlistDecl() throws Exception {
        this.requireWhitespace();
        final String nmtoken = this.readNmtoken(true);
        this.requireWhitespace();
        while (!this.tryRead('>')) {
            this.parseAttDef(nmtoken);
            this.skipWhitespace();
        }
    }
    
    void parseAttDef(final String s) throws Exception {
        String dataBufferToString = null;
        final String nmtoken = this.readNmtoken(true);
        this.requireWhitespace();
        final int attType = this.readAttType();
        if (attType == 9 || attType == 10) {
            dataBufferToString = this.dataBufferToString();
        }
        this.requireWhitespace();
        this.parseDefault(s, nmtoken, attType, dataBufferToString);
    }
    
    int readAttType() throws Exception {
        if (this.tryRead('(')) {
            this.parseEnumeration();
            return 9;
        }
        final String nmtoken = this.readNmtoken(true);
        if (nmtoken.equals("NOTATION")) {
            this.parseNotationType();
        }
        final Integer n = XMLParser.attributeTypeHash.get(nmtoken);
        if (n == null) {
            this.error("illegal attribute type", nmtoken, null);
            return 0;
        }
        return n;
    }
    
    void parseEnumeration() throws Exception {
        this.dataBufferAppend('(');
        this.skipWhitespace();
        this.dataBufferAppend(this.readNmtoken(true));
        this.skipWhitespace();
        while (!this.tryRead(')')) {
            this.require('|');
            this.dataBufferAppend('|');
            this.skipWhitespace();
            this.dataBufferAppend(this.readNmtoken(true));
            this.skipWhitespace();
        }
        this.dataBufferAppend(')');
    }
    
    void parseNotationType() throws Exception {
        this.requireWhitespace();
        this.require('(');
        this.parseEnumeration();
    }
    
    void parseDefault(final String s, final String s2, final int n, final String s3) throws Exception {
        int n2 = 1;
        String s4 = null;
        if (this.tryRead('#')) {
            if (this.tryRead("FIXED")) {
                n2 = 4;
                this.requireWhitespace();
                this.context = 3;
                s4 = this.readLiteral(1);
                this.context = 1;
            }
            else if (this.tryRead("REQUIRED")) {
                n2 = 3;
            }
            else if (this.tryRead("IMPLIED")) {
                n2 = 2;
            }
            else {
                this.error("illegal keyword for attribute default value", null, null);
            }
        }
        else {
            this.context = 3;
            s4 = this.readLiteral(1);
            this.context = 1;
        }
        this.setAttribute(s, s2, n, s3, s4, n2);
    }
    
    void parseConditionalSect() throws Exception {
        this.skipWhitespace();
        if (this.tryRead("INCLUDE")) {
            this.skipWhitespace();
            this.require('[');
            this.skipWhitespace();
            while (!this.tryRead("]]>")) {
                this.parseMarkupdecl();
                this.skipWhitespace();
            }
        }
        else if (this.tryRead("IGNORE")) {
            this.skipWhitespace();
            this.require('[');
            int i = 1;
            while (i > 0) {
                switch (this.readCh()) {
                    case '<': {
                        if (this.tryRead("![")) {
                            ++i;
                        }
                    }
                    case ']': {
                        if (this.tryRead("]>")) {
                            --i;
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
        else {
            this.error("conditional section must begin with INCLUDE or IGNORE", null, null);
        }
    }
    
    void parseCharRef() throws Exception {
        int n = 0;
        Label_0401: {
            if (this.tryRead('x')) {
                while (true) {
                    final char ch = this.readCh();
                    switch (ch) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102: {
                            n = n * 16 + Integer.parseInt(new Character(ch).toString(), 16);
                            continue;
                        }
                        case 59: {
                            break Label_0401;
                        }
                        default: {
                            this.error("illegal character in character reference", ch, null);
                            break Label_0401;
                        }
                    }
                }
            }
            else {
                while (true) {
                    final char ch2 = this.readCh();
                    switch (ch2) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            n = n * 10 + Integer.parseInt(new Character(ch2).toString(), 10);
                            continue;
                        }
                        case 59: {
                            break Label_0401;
                        }
                        default: {
                            this.error("illegal character in character reference", ch2, null);
                            break Label_0401;
                        }
                    }
                }
            }
        }
        if (n <= 65535) {
            this.dataBufferAppend((char)n);
        }
        else if (n <= 1048575) {
            this.dataBufferAppend((char)(0xD8 | (n & 0xFFC00) >> 10));
            this.dataBufferAppend((char)(0xDC | (n & 0x3FF)));
        }
        else {
            this.error("character reference " + n + " is too large for UTF-16", new Integer(n).toString(), null);
        }
    }
    
    void parseEntityRef(final boolean b) throws Exception {
        final String nmtoken = this.readNmtoken(true);
        this.require(';');
        switch (this.getEntityType(nmtoken)) {
            case 0: {
                this.error("reference to undeclared entity", nmtoken, null);
                break;
            }
            case 1: {
                this.pushString(nmtoken, this.getEntityValue(nmtoken));
                break;
            }
            case 3: {
                if (b) {
                    this.pushURL(nmtoken, this.getEntityPublicId(nmtoken), this.getEntitySystemId(nmtoken), null);
                    break;
                }
                this.error("reference to external entity in attribute value.", nmtoken, null);
                break;
            }
            case 2: {
                if (b) {
                    this.error("data entity reference in content", nmtoken, null);
                    break;
                }
                this.error("reference to external entity in attribute value.", nmtoken, null);
                break;
            }
        }
    }
    
    void parsePEReference(final boolean b) throws Exception {
        final String string = "%" + this.readNmtoken(true);
        this.require(';');
        switch (this.getEntityType(string)) {
            case 0: {
                this.error("reference to undeclared parameter entity", string, null);
                break;
            }
            case 1: {
                if (b) {
                    this.pushString(string, this.getEntityValue(string));
                    break;
                }
                this.pushString(string, " " + this.getEntityValue(string) + ' ');
                break;
            }
            case 3: {
                if (b) {
                    this.pushString(null, " ");
                }
                this.pushURL(string, this.getEntityPublicId(string), this.getEntitySystemId(string), null);
                if (b) {
                    this.pushString(null, " ");
                    break;
                }
                break;
            }
        }
    }
    
    void parseEntityDecl() throws Exception {
        boolean b = false;
        this.requireWhitespace();
        if (this.tryRead('%')) {
            b = true;
            this.requireWhitespace();
        }
        String s = this.readNmtoken(true);
        if (b) {
            s = "%" + s;
        }
        this.requireWhitespace();
        final char ch = this.readCh();
        this.unread(ch);
        if (ch == '\"' || ch == '\'') {
            this.context = 2;
            final String literal = this.readLiteral(5);
            this.context = 1;
            this.setInternalEntity(s, literal);
        }
        else {
            final String[] externalIds = this.readExternalIds(false);
            if (externalIds[1] == null) {
                this.error("system identifer missing", s, null);
            }
            this.skipWhitespace();
            if (this.tryRead("NDATA")) {
                this.requireWhitespace();
                this.setExternalDataEntity(s, externalIds[0], externalIds[1], this.readNmtoken(true));
            }
            else {
                this.setExternalTextEntity(s, externalIds[0], externalIds[1]);
            }
        }
        this.skipWhitespace();
        this.require('>');
    }
    
    void parseNotationDecl() throws Exception {
        this.requireWhitespace();
        final String nmtoken = this.readNmtoken(true);
        this.requireWhitespace();
        final String[] externalIds = this.readExternalIds(true);
        if (externalIds[0] == null && externalIds[1] == null) {
            this.error("external identifer missing", nmtoken, null);
        }
        this.setNotation(nmtoken, externalIds[0], externalIds[1]);
        this.skipWhitespace();
        this.require('>');
    }
    
    void parsePCData() throws Exception {
        int n = 0;
        int column = 0;
        for (int i = this.readBufferPos; i < this.readBufferLength; ++i) {
            switch (this.readBuffer[i]) {
                case '\n': {
                    ++n;
                    column = 0;
                    break;
                }
                case '&':
                case '<': {
                    final int readBufferPos = this.readBufferPos;
                    ++column;
                    this.readBufferPos = i;
                    if (n > 0) {
                        this.line += n;
                        this.column = column;
                    }
                    else {
                        this.column += column;
                    }
                    this.dataBufferAppend(this.readBuffer, readBufferPos, i - readBufferPos);
                    return;
                }
                default: {
                    ++column;
                    break;
                }
            }
        }
        char ch = '\0';
    Label_0172:
        while (true) {
            ch = this.readCh();
            switch (ch) {
                case 38:
                case 60: {
                    break Label_0172;
                }
                default: {
                    this.dataBufferAppend(ch);
                    continue;
                }
            }
        }
        this.unread(ch);
    }
    
    void requireWhitespace() throws Exception {
        final char ch = this.readCh();
        if (this.isWhitespace(ch)) {
            this.skipWhitespace();
        }
        else {
            this.error("whitespace expected", ch, null);
        }
    }
    
    void parseWhitespace() throws Exception {
        char c;
        for (c = this.readCh(); this.isWhitespace(c); c = this.readCh()) {
            this.dataBufferAppend(c);
        }
        this.unread(c);
    }
    
    void skipWhitespace() throws Exception {
        int n = 0;
        int column = 0;
        int i = this.readBufferPos;
    Label_0149:
        while (i < this.readBufferLength) {
            Label_0105: {
                switch (this.readBuffer[i]) {
                    case '\t':
                    case '\r':
                    case ' ': {
                        ++column;
                        break;
                    }
                    case '\n': {
                        ++n;
                        column = 0;
                        break;
                    }
                    case '%': {
                        if (this.context == 1) {
                            break Label_0149;
                        }
                        if (this.context == 2) {
                            break Label_0149;
                        }
                        break Label_0105;
                    }
                }
                ++i;
                continue;
            }
            this.readBufferPos = i;
            if (n > 0) {
                this.line += n;
                this.column = column;
            }
            else {
                this.column += column;
            }
            return;
        }
        char c;
        for (c = this.readCh(); this.isWhitespace(c); c = this.readCh()) {}
        this.unread(c);
    }
    
    String readNmtoken(final boolean b) throws Exception {
        int i = this.readBufferPos;
    Label_0266:
        while (i < this.readBufferLength) {
            switch (this.readBuffer[i]) {
                case '%': {
                    if (this.context == 1) {
                        break Label_0266;
                    }
                    if (this.context == 2) {
                        break Label_0266;
                    }
                }
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                case '\"':
                case '#':
                case '&':
                case '\'':
                case ')':
                case '*':
                case '+':
                case ',':
                case '/':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '[':
                case '|': {
                    final int readBufferPos = this.readBufferPos;
                    if (i == readBufferPos) {
                        this.error("name expected", this.readBuffer[i], null);
                    }
                    this.readBufferPos = i;
                    return this.intern(this.readBuffer, readBufferPos, i - readBufferPos);
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
        this.nameBufferPos = 0;
        char ch = '\0';
    Label_0448:
        while (true) {
            ch = this.readCh();
            switch (ch) {
                case 9:
                case 10:
                case 13:
                case 32:
                case 34:
                case 37:
                case 38:
                case 39:
                case 41:
                case 42:
                case 43:
                case 44:
                case 47:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 91:
                case 124: {
                    break Label_0448;
                }
                default: {
                    (this.nameBuffer = (char[])this.extendArray(this.nameBuffer, this.nameBuffer.length, this.nameBufferPos))[this.nameBufferPos++] = ch;
                    continue;
                }
            }
        }
        this.unread(ch);
        if (this.nameBufferPos == 0) {
            this.error("name expected", null, null);
        }
        final String intern = this.intern(this.nameBuffer, 0, this.nameBufferPos);
        this.nameBufferPos = 0;
        return intern;
    }
    
    String readLiteral(final int n) throws Exception {
        final int line = this.line;
        final char ch = this.readCh();
        if (ch != '\"' && ch != '\'' && ch != '\0') {
            this.error("expected '\"' or \"'\"", ch, null);
            return null;
        }
        try {
            char c = this.readCh();
            while (c != ch) {
                switch (c) {
                    case '&': {
                        if ((n & 0x1) <= 0) {
                            break;
                        }
                        c = this.readCh();
                        if (c == '#') {
                            this.parseCharRef();
                            c = this.readCh();
                            continue;
                        }
                        if ((n & 0x2) > 0) {
                            this.unread(c);
                            this.parseEntityRef(false);
                            c = this.readCh();
                            continue;
                        }
                        this.dataBufferAppend('&');
                        break;
                    }
                }
                this.dataBufferAppend(c);
                c = this.readCh();
            }
        }
        catch (EOFException ex) {
            this.error("end of input while looking for delimiter (started on line " + line + ')', null, new Character(ch).toString());
        }
        if ((n & 0x8) > 0) {
            this.dataBufferNormalize();
        }
        return this.dataBufferToString();
    }
    
    String[] readExternalIds(final boolean b) throws Exception {
        final String[] array = new String[2];
        if (this.tryRead("PUBLIC")) {
            this.requireWhitespace();
            array[0] = this.readLiteral(8);
            if (b) {
                this.skipWhitespace();
                if (this.tryRead('\"') || this.tryRead('\'')) {
                    array[1] = this.readLiteral(0);
                }
            }
            else {
                this.requireWhitespace();
                array[1] = this.readLiteral(0);
            }
        }
        else if (this.tryRead("SYSTEM")) {
            this.requireWhitespace();
            array[1] = this.readLiteral(0);
        }
        array[0] = (array[1] = null);
        return array;
    }
    
    final boolean isWhitespace(final char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\r':
            case ' ': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    void dataBufferAppend(final char c) {
        (this.dataBuffer = (char[])this.extendArray(this.dataBuffer, this.dataBuffer.length, this.dataBufferPos))[this.dataBufferPos++] = c;
    }
    
    void dataBufferAppend(final String s) {
        this.dataBufferAppend(s.toCharArray(), 0, s.length());
    }
    
    void dataBufferAppend(final char[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.dataBuffer = (char[])this.extendArray(this.dataBuffer, this.dataBuffer.length, this.dataBufferPos + n2), this.dataBufferPos, n2);
        this.dataBufferPos += n2;
    }
    
    void dataBufferNormalize() {
        int dataBufferPos = 0;
        int i;
        int dataBufferPos2;
        for (i = 0, dataBufferPos2 = this.dataBufferPos; i < dataBufferPos2 && this.isWhitespace(this.dataBuffer[i]); ++i) {}
        while (dataBufferPos2 > i && this.isWhitespace(this.dataBuffer[dataBufferPos2 - 1])) {
            --dataBufferPos2;
        }
        while (i < dataBufferPos2) {
            final char c = this.dataBuffer[i++];
            if (this.isWhitespace(c)) {
                while (i < dataBufferPos2 && this.isWhitespace(this.dataBuffer[i++])) {}
                this.dataBuffer[dataBufferPos++] = ' ';
                this.dataBuffer[dataBufferPos++] = this.dataBuffer[i - 1];
            }
            else {
                this.dataBuffer[dataBufferPos++] = c;
            }
        }
        this.dataBufferPos = dataBufferPos;
    }
    
    String dataBufferToString() {
        final String s = new String(this.dataBuffer, 0, this.dataBufferPos);
        this.dataBufferPos = 0;
        return s;
    }
    
    void dataBufferFlush() throws Exception {
        if (this.dataBufferPos > 0) {
            switch (this.currentElementContent) {
                case 1:
                case 3: {
                    if (this.handler != null) {
                        this.handler.charData(this.dataBuffer, 0, this.dataBufferPos);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.handler != null) {
                        this.handler.ignorableWhitespace(this.dataBuffer, 0, this.dataBufferPos);
                        break;
                    }
                    break;
                }
            }
            this.dataBufferPos = 0;
        }
    }
    
    void require(final String s) throws Exception {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            this.require(charArray[i]);
        }
    }
    
    void require(final char c) throws Exception {
        final char ch = this.readCh();
        if (ch != c) {
            this.error("expected character", ch, new Character(c).toString());
        }
    }
    
    public String intern(final String s) {
        final char[] charArray = s.toCharArray();
        return this.intern(charArray, 0, charArray.length);
    }
    
    public String intern(final char[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = n; i < n + n2; ++i) {
            n3 = (n3 << 1 & 0xFFFFFF) + array[i];
        }
        final int n4 = n3 % 1087;
        Object o = this.symbolTable[n4];
        if (o == null) {
            o = (this.symbolTable[n4] = new Object[8]);
        }
        int j;
        for (j = 0; j < o.length; j += 2) {
            final char[] array2 = (char[])o[j];
            if (array2 == null) {
                break;
            }
            if (array2.length == n2) {
                for (int k = 0; k < array2.length; ++k) {
                    if (array[n + k] != array2[k]) {
                        break;
                    }
                    if (k == n2 - 1) {
                        return (String)o[j + 1];
                    }
                }
            }
        }
        final Object[] array3 = (Object[])this.extendArray(o, o.length, j);
        final String s = new String(array, n, n2);
        array3[j] = s.toCharArray();
        array3[j + 1] = s;
        this.symbolTable[n4] = array3;
        return s;
    }
    
    Object extendArray(final Object o, final int n, final int n2) {
        if (n2 < n) {
            return o;
        }
        Object o2 = null;
        if (n * 2 <= n2) {}
        if (o instanceof char[]) {
            o2 = new char[n * 2];
        }
        else if (o instanceof Object[]) {
            o2 = new Object[n * 2];
        }
        System.arraycopy(o, 0, o2, 0, n);
        return o2;
    }
    
    public Enumeration declaredElements() {
        return this.elementInfo.keys();
    }
    
    public int getElementContentType(final String s) {
        final Object[] array = this.elementInfo.get(s);
        if (array == null) {
            return 0;
        }
        return (int)array[0];
    }
    
    public String getElementContentModel(final String s) {
        final Object[] array = this.elementInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[1];
    }
    
    void setElement(final String s, final int n, final String s2, final Hashtable hashtable) throws Exception {
        Object[] array = this.elementInfo.get(s);
        if (array == null) {
            array = new Object[] { new Integer(0), null, null };
        }
        else if (n != 0 && (int)array[0] != 0) {
            this.error("multiple declarations for element type", s, null);
            return;
        }
        if (n != 0) {
            array[0] = new Integer(n);
        }
        if (s2 != null) {
            array[1] = s2;
        }
        if (hashtable != null) {
            array[2] = hashtable;
        }
        this.elementInfo.put(s, array);
    }
    
    Hashtable getElementAttributes(final String s) {
        final Object[] array = this.elementInfo.get(s);
        if (array == null) {
            return null;
        }
        return (Hashtable)array[2];
    }
    
    public Enumeration declaredAttributes(final String s) {
        final Hashtable elementAttributes = this.getElementAttributes(s);
        if (elementAttributes == null) {
            return null;
        }
        return elementAttributes.keys();
    }
    
    public int getAttributeType(final String s, final String s2) {
        final Object[] attribute = this.getAttribute(s, s2);
        if (attribute == null) {
            return 0;
        }
        return (int)attribute[0];
    }
    
    public String getAttributeEnumeration(final String s, final String s2) {
        final Object[] attribute = this.getAttribute(s, s2);
        if (attribute == null) {
            return null;
        }
        return (String)attribute[3];
    }
    
    public String getAttributeDefaultValue(final String s, final String s2) {
        final Object[] attribute = this.getAttribute(s, s2);
        if (attribute == null) {
            return null;
        }
        return (String)attribute[1];
    }
    
    public String getAttributeExpandedValue(final String s, final String s2) {
        final Object[] attribute = this.getAttribute(s, s2);
        if (attribute == null) {
            return null;
        }
        if (attribute[4] == null && attribute[1] != null) {
            try {
                this.pushString(null, '\0' + (String)attribute[1] + '\0');
                attribute[4] = this.readLiteral(11);
            }
            catch (Exception ex) {}
        }
        return (String)attribute[4];
    }
    
    public int getAttributeDefaultValueType(final String s, final String s2) {
        final Object[] attribute = this.getAttribute(s, s2);
        if (attribute == null) {
            return 0;
        }
        return (int)attribute[2];
    }
    
    void setAttribute(final String s, final String s2, final int n, final String s3, final String s4, final int n2) throws Exception {
        Hashtable<String, Object[]> elementAttributes = (Hashtable<String, Object[]>)this.getElementAttributes(s);
        if (elementAttributes == null) {
            elementAttributes = new Hashtable<String, Object[]>();
        }
        if (elementAttributes.get(s2) != null) {
            return;
        }
        elementAttributes.put(s2.intern(), new Object[] { new Integer(n), s4, new Integer(n2), s3, null });
        this.setElement(s, 0, null, elementAttributes);
    }
    
    Object[] getAttribute(final String s, final String s2) {
        final Hashtable elementAttributes = this.getElementAttributes(s);
        if (elementAttributes == null) {
            return null;
        }
        return elementAttributes.get(s2);
    }
    
    public Enumeration declaredEntities() {
        return this.entityInfo.keys();
    }
    
    public int getEntityType(final String s) {
        final Object[] array = this.entityInfo.get(s);
        if (array == null) {
            return 0;
        }
        return (int)array[0];
    }
    
    public String getEntityPublicId(final String s) {
        final Object[] array = this.entityInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[1];
    }
    
    public String getEntitySystemId(final String s) {
        final Object[] array = this.entityInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[2];
    }
    
    public String getEntityValue(final String s) {
        final Object[] array = this.entityInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[3];
    }
    
    public String getEntityNotationName(final String s) {
        final Object[] array = this.entityInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[4];
    }
    
    void setInternalEntity(final String s, final String s2) {
        this.setEntity(s, 1, null, null, s2, null);
    }
    
    void setExternalDataEntity(final String s, final String s2, final String s3, final String s4) {
        this.setEntity(s, 2, s2, s3, null, s4);
    }
    
    void setExternalTextEntity(final String s, final String s2, final String s3) {
        this.setEntity(s, 3, s2, s3, null, null);
    }
    
    void setEntity(final String s, final int n, final String s2, final String s3, final String s4, final String s5) {
        if (this.entityInfo.get(s) == null) {
            this.entityInfo.put(s, new Object[] { new Integer(n), s2, s3, s4, s5 });
        }
    }
    
    public Enumeration declaredNotations() {
        return this.notationInfo.keys();
    }
    
    public String getNotationPublicId(final String s) {
        final Object[] array = this.notationInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[0];
    }
    
    public String getNotationSystemId(final String s) {
        final Object[] array = this.notationInfo.get(s);
        if (array == null) {
            return null;
        }
        return (String)array[1];
    }
    
    void setNotation(final String s, final String s2, final String s3) throws Exception {
        if (this.notationInfo.get(s) == null) {
            this.notationInfo.put(s, new Object[] { s2, s3 });
        }
        else {
            this.error("multiple declarations of notation", s, null);
        }
    }
    
    char readCh() throws Exception {
        while (this.readBufferPos >= this.readBufferLength) {
            switch (this.sourceType) {
                case 2:
                case 3: {
                    this.readDataChunk();
                    while (this.readBufferLength < 1) {
                        this.popInput();
                    }
                    continue;
                }
                default: {
                    this.popInput();
                    continue;
                }
            }
        }
        final char c = this.readBuffer[this.readBufferPos++];
        if (c == '%' && (this.context == 1 || this.context == 2)) {
            final char ch = this.readCh();
            this.unread(ch);
            if (!this.isWhitespace(ch)) {
                this.parsePEReference(this.context == 2);
                return this.readCh();
            }
        }
        if (c == '\n') {
            ++this.line;
            this.column = 0;
        }
        else {
            ++this.column;
        }
        return c;
    }
    
    void unread(final char c) throws Exception {
        if (c == '\n') {
            --this.line;
            this.column = -1;
        }
        if (this.readBufferPos > 0) {
            this.readBuffer[--this.readBufferPos] = c;
        }
        else {
            this.pushString(null, new Character(c).toString());
        }
    }
    
    void unread(final char[] array, final int n) throws Exception {
        for (int i = 0; i < n; ++i) {
            if (array[i] == '\n') {
                --this.line;
                this.column = -1;
            }
        }
        if (n < this.readBufferPos) {
            this.readBufferPos -= n;
        }
        else {
            this.pushCharArray(null, array, 0, n);
            this.sourceType = 4;
        }
    }
    
    void pushURL(final String s, final String s2, String s3, final InputStream is) throws Exception {
        this.pushInput(s);
        this.currentByteCount = 0;
        this.dataBufferFlush();
        if (this.externalEntity != null) {
            s3 = new URL(this.externalEntity.getURL(), s3).toString();
        }
        else if (this.baseURI != null) {
            s3 = new URL(new URL(this.baseURI), s3).toString();
        }
        if (is == null) {
            this.sourceType = 2;
            if (this.handler != null) {
                s3 = this.handler.resolveEntity(s, s2, s3);
            }
            if (s3 == null) {
                this.popInput();
                return;
            }
            (this.externalEntity = new URL(s3).openConnection()).connect();
            this.is = this.externalEntity.getInputStream();
            if (this.handler != null) {
                this.handler.startExternalEntity(this.externalEntity.getURL().toString());
            }
        }
        else {
            this.sourceType = 3;
            if (this.baseURI != null && this.handler != null) {
                this.handler.startExternalEntity(this.baseURI);
            }
            this.is = is;
            this.externalEntity = null;
        }
        this.is = new BufferedInputStream(this.is);
        this.line = 1;
        this.readBuffer = new char[16388];
        this.readBufferPos = 0;
        this.readBufferLength = 0;
        this.readBufferOverflow = -1;
        this.encoding = 1;
    }
    
    void detectEncoding() throws Exception {
        this.encoding = 1;
    }
    
    void pushString(final String s, final String s2) throws Exception {
        final char[] charArray = s2.toCharArray();
        this.pushCharArray(s, charArray, 0, charArray.length);
    }
    
    void pushCharArray(final String s, final char[] readBuffer, final int readBufferPos, final int readBufferLength) throws Exception {
        this.pushInput(s);
        this.sourceType = 1;
        this.readBuffer = readBuffer;
        this.readBufferPos = readBufferPos;
        this.readBufferLength = readBufferLength;
        this.readBufferOverflow = -1;
    }
    
    void pushInput(final String s) throws Exception {
        final Object[] array = new Object[11];
        if (s != null) {
            final Enumeration elements = this.entityStack.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement() == s) {
                    this.error("recursive reference to entity", s, null);
                }
            }
        }
        this.entityStack.push(s);
        if (this.sourceType == 0) {
            return;
        }
        array[0] = new Integer(this.sourceType);
        array[1] = this.externalEntity;
        array[2] = this.readBuffer;
        array[3] = new Integer(this.readBufferPos);
        array[4] = new Integer(this.readBufferLength);
        array[5] = new Integer(this.line);
        array[6] = new Integer(this.encoding);
        array[7] = new Integer(this.readBufferOverflow);
        array[8] = this.is;
        array[9] = new Integer(this.currentByteCount);
        array[10] = new Integer(this.column);
        this.inputStack.push(array);
    }
    
    void popInput() throws Exception {
        if (this.sourceType == 2) {
            this.dataBufferFlush();
            if (this.handler != null && this.externalEntity != null) {
                this.handler.endExternalEntity(this.externalEntity.getURL().toString());
            }
        }
        else if (this.sourceType == 3 && this.baseURI != null && this.handler != null) {
            this.handler.endExternalEntity(this.baseURI);
        }
        if (this.inputStack.isEmpty()) {
            throw new EOFException();
        }
        final Object[] array = this.inputStack.pop();
        final String s = this.entityStack.pop();
        this.sourceType = (int)array[0];
        this.externalEntity = (URLConnection)array[1];
        this.readBuffer = (char[])array[2];
        this.readBufferPos = (int)array[3];
        this.readBufferLength = (int)array[4];
        this.line = (int)array[5];
        this.encoding = (int)array[6];
        this.readBufferOverflow = (int)array[7];
        this.is = (InputStream)array[8];
        this.currentByteCount = (int)array[9];
        this.column = (int)array[10];
    }
    
    boolean tryRead(final char c) throws Exception {
        final char ch = this.readCh();
        if (ch == c) {
            return true;
        }
        this.unread(ch);
        return false;
    }
    
    boolean tryRead(final String s) throws Exception {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[100];
        for (int i = 0; i < charArray.length; ++i) {
            array[i] = this.readCh();
            if (array[i] != charArray[i]) {
                if (i == 0) {
                    this.unread(array[0]);
                }
                else {
                    this.unread(array, i + 1);
                }
                return false;
            }
        }
        return true;
    }
    
    boolean tryWhitespace() throws Exception {
        final char ch = this.readCh();
        if (this.isWhitespace(ch)) {
            this.skipWhitespace();
            return true;
        }
        this.unread(ch);
        return false;
    }
    
    void parseUntil(final String s) throws Exception {
        final int line = this.line;
        try {
            while (!this.tryRead(s)) {
                this.dataBufferAppend(this.readCh());
            }
        }
        catch (EOFException ex) {
            this.error("end of input while looking for delimiter (started on line " + line + ')', null, s);
        }
    }
    
    void skipUntil(final String s) throws Exception {
        while (!this.tryRead(s)) {
            this.readCh();
        }
    }
    
    void readDataChunk() throws Exception {
        int n = 16384;
        int n2 = 0;
        int n3 = 0;
        for (int n4 = this.is.read(this.rawReadBuffer, 0, n); n4 != -1 && n > 0; n -= n4, n3 += n4, n4 = this.is.read(this.rawReadBuffer, n2, n)) {
            n2 += n4;
        }
        if (this.readBufferOverflow > -1) {
            this.readBuffer[0] = (char)this.readBufferOverflow;
            this.readBufferOverflow = -1;
            this.readBufferPos = 1;
            this.sawCR = true;
        }
        else {
            this.readBufferPos = 0;
            this.sawCR = false;
        }
        if (n3 > 0) {
            this.copyUtf8ReadBuffer(n3);
            if (this.sawCR) {
                this.filterCR();
                this.sawCR = false;
            }
            this.readBufferPos = 0;
            this.currentByteCount += n3;
        }
    }
    
    void filterCR() {
        this.readBufferOverflow = -1;
        int readBufferLength = 0;
    Label_0125:
        for (int i = 0; i < this.readBufferLength; ++i) {
            switch (this.readBuffer[i]) {
                case '\r': {
                    if (i == this.readBufferLength - 1) {
                        this.readBufferOverflow = 13;
                        --this.readBufferLength;
                        break Label_0125;
                    }
                    if (this.readBuffer[i + 1] == '\n') {
                        ++i;
                    }
                    this.readBuffer[readBufferLength] = '\n';
                    break;
                }
                default: {
                    this.readBuffer[readBufferLength] = this.readBuffer[i];
                    break;
                }
            }
            ++readBufferLength;
        }
        this.readBufferLength = readBufferLength;
    }
    
    void copyUtf8ReadBuffer(final int n) throws Exception {
        int i = 0;
        int readBufferPos = this.readBufferPos;
        while (i < n) {
            final byte b = this.rawReadBuffer[i++];
            if ((b & 0x80) == 0x0) {
                this.readBuffer[readBufferPos++] = (char)b;
            }
            else if ((b & 0xE0) == 0xC0) {
                this.readBuffer[readBufferPos++] = (char)((b & 0x1F) << 6 | this.getNextUtf8Byte(i++, n));
            }
            else if ((b & 0xF0) == 0xE0) {
                this.readBuffer[readBufferPos++] = (char)((b & 0xF) << 12 | this.getNextUtf8Byte(i++, n) << 6 | this.getNextUtf8Byte(i++, n));
            }
            else if ((b & 0xF8) == 0xF0) {
                final int nextUtf8Byte = this.getNextUtf8Byte(i++, n);
                final int nextUtf8Byte2 = this.getNextUtf8Byte(i++, n);
                final int nextUtf8Byte3 = this.getNextUtf8Byte(i++, n);
                this.readBuffer[readBufferPos++] = (char)(0xD800 | ((b & 0x7) << 2 | ((nextUtf8Byte & 0x30) >> 4) - 1) << 6 | (nextUtf8Byte & 0xF) << 2 | (nextUtf8Byte2 & 0x30) >> 4);
                this.readBuffer[readBufferPos++] = (char)(0xDC | (nextUtf8Byte2 & 0xF) << 6 | nextUtf8Byte3);
            }
            else {
                this.encodingError("bad start for UTF-8 multi-byte sequence", b, i);
            }
            if (this.readBuffer[readBufferPos - 1] == '\r') {
                this.sawCR = true;
            }
        }
        this.readBufferLength = readBufferPos;
    }
    
    int getNextUtf8Byte(final int n, final int n2) throws Exception {
        int read;
        if (n < n2) {
            read = this.rawReadBuffer[n];
        }
        else {
            read = this.is.read();
            if (read == -1) {
                this.encodingError("unfinished multi-byte UTF-8 sequence at EOF", -1, n);
            }
        }
        if ((read & 0xC0) != 0x80) {
            this.encodingError("bad continuation of multi-byte UTF-8 sequence", read, n + 1);
        }
        return read & 0x3F;
    }
    
    void encodingError(String string, final int n, final int n2) throws Exception {
        if (n >= 0) {
            string = string + " (byte value: 0x" + Integer.toHexString(n) + ')';
        }
        String s;
        if (this.externalEntity != null) {
            s = this.externalEntity.getURL().toString();
        }
        else {
            s = this.baseURI;
        }
        this.handler.error(string, s, -1, n2 + this.currentByteCount);
    }
    
    void initializeVariables() {
        this.errorCount = 0;
        this.line = 1;
        this.column = 0;
        this.dataBufferPos = 0;
        this.dataBuffer = new char[XMLParser.DATA_BUFFER_INITIAL];
        this.nameBufferPos = 0;
        this.nameBuffer = new char[XMLParser.NAME_BUFFER_INITIAL];
        this.elementInfo = new Hashtable();
        this.entityInfo = new Hashtable();
        this.notationInfo = new Hashtable();
        this.currentElement = null;
        this.currentElementContent = 0;
        this.sourceType = 0;
        this.inputStack = new Stack();
        this.entityStack = new Stack();
        this.externalEntity = null;
        this.tagAttributePos = 0;
        this.tagAttributes = new String[100];
        this.rawReadBuffer = new byte[16384];
        this.readBufferOverflow = -1;
        this.context = 0;
        this.symbolTable = new Object[1087];
    }
    
    void cleanupVariables() {
        this.errorCount = -1;
        this.line = -1;
        this.column = -1;
        this.dataBuffer = null;
        this.nameBuffer = null;
        this.currentElement = null;
        this.currentElementContent = 0;
        this.sourceType = 0;
        this.inputStack = null;
        this.externalEntity = null;
        this.entityStack = null;
    }
    
    static {
        XMLParser.bNeedsPriv = false;
        (XMLParser.attributeTypeHash = new Hashtable()).put("CDATA", new Integer(1));
        XMLParser.attributeTypeHash.put("ID", new Integer(2));
        XMLParser.attributeTypeHash.put("IDREF", new Integer(3));
        XMLParser.attributeTypeHash.put("IDREFS", new Integer(4));
        XMLParser.attributeTypeHash.put("ENTITY", new Integer(5));
        XMLParser.attributeTypeHash.put("ENTITIES", new Integer(6));
        XMLParser.attributeTypeHash.put("NMTOKEN", new Integer(7));
        XMLParser.attributeTypeHash.put("NMTOKENS", new Integer(8));
        XMLParser.attributeTypeHash.put("NOTATION", new Integer(10));
        XMLParser.DATA_BUFFER_INITIAL = 4096;
        XMLParser.NAME_BUFFER_INITIAL = 1024;
    }
}
