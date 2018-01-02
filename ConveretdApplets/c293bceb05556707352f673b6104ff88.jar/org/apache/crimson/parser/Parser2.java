// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import org.xml.sax.helpers.DefaultHandler;
import org.apache.crimson.util.MessageCatalog;
import org.apache.crimson.util.XmlNames;
import org.xml.sax.Attributes;
import java.util.Enumeration;
import org.apache.crimson.util.XmlChars;
import org.xml.sax.SAXParseException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.Locator;
import java.util.Locale;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import java.util.Hashtable;
import java.util.Vector;
import org.xml.sax.helpers.NamespaceSupport;

public class Parser2
{
    private InputEntity in;
    private AttributesExImpl attTmp;
    private StringBuffer strTmp;
    private char[] nameTmp;
    private NameCache nameCache;
    private char[] charTmp;
    private String[] namePartsTmp;
    private boolean seenNSDecl;
    private NamespaceSupport nsSupport;
    private Vector nsAttTmp;
    private boolean isValidating;
    private boolean fastStandalone;
    private boolean isInAttribute;
    private boolean namespaces;
    private boolean prefixes;
    private boolean inExternalPE;
    private boolean doLexicalPE;
    private boolean donePrologue;
    private boolean isStandalone;
    private String rootElementName;
    private boolean ignoreDeclarations;
    private SimpleHashtable elements;
    private SimpleHashtable params;
    Hashtable notations;
    SimpleHashtable entities;
    private ContentHandler contentHandler;
    private DTDHandler dtdHandler;
    private EntityResolver resolver;
    private ErrorHandler errHandler;
    private Locale locale;
    private Locator locator;
    private DeclHandler declHandler;
    private LexicalHandler lexicalHandler;
    private static final boolean supportValidation = true;
    static final String strANY = "ANY";
    static final String strEMPTY = "EMPTY";
    private static final NullHandler nullHandler;
    private static final String XmlLang = "xml:lang";
    static final Catalog messages;
    
    public Parser2() {
        this.charTmp = new char[2];
        this.namePartsTmp = new String[3];
        this.isValidating = false;
        this.fastStandalone = false;
        this.isInAttribute = false;
        this.elements = new SimpleHashtable(47);
        this.params = new SimpleHashtable(7);
        this.notations = new Hashtable(7);
        this.entities = new SimpleHashtable(17);
        this.locator = new DocLocator();
        this.setHandlers();
    }
    
    void setNamespaceFeatures(final boolean namespaces, final boolean prefixes) {
        this.namespaces = namespaces;
        this.prefixes = prefixes;
    }
    
    void setEntityResolver(final EntityResolver resolver) {
        this.resolver = resolver;
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        this.dtdHandler = handler;
    }
    
    void setContentHandler(final ContentHandler handler) {
        this.contentHandler = handler;
    }
    
    void setErrorHandler(final ErrorHandler handler) {
        this.errHandler = handler;
    }
    
    void setLexicalHandler(final LexicalHandler handler) {
        this.lexicalHandler = handler;
    }
    
    void setDeclHandler(final DeclHandler handler) {
        this.declHandler = handler;
    }
    
    public void setLocale(final Locale l) throws SAXException {
        if (l != null && !Parser2.messages.isLocaleSupported(l.toString())) {
            throw new SAXException(Parser2.messages.getMessage(this.locale, "P-078", new Object[] { l }));
        }
        this.locale = l;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public Locale chooseLocale(final String[] languages) throws SAXException {
        final Locale l = Parser2.messages.chooseLocale(languages);
        if (l != null) {
            this.setLocale(l);
        }
        return l;
    }
    
    public void parse(final InputSource in) throws SAXException, IOException {
        this.init();
        this.parseInternal(in);
    }
    
    public void setFastStandalone(final boolean value) {
        this.fastStandalone = (value && !this.isValidating);
    }
    
    public boolean isFastStandalone() {
        return this.fastStandalone;
    }
    
    public void pushInputBuffer(char[] buf, final int offset, final int len) throws SAXException {
        if (len <= 0) {
            return;
        }
        if (offset != 0 || len != buf.length) {
            final char[] tmp = new char[len];
            System.arraycopy(buf, offset, tmp, 0, len);
            buf = tmp;
        }
        this.pushReader(buf, null, false);
    }
    
    void setIsValidating(final boolean value) {
        this.isValidating = value;
        if (value) {
            this.fastStandalone = false;
        }
    }
    
    private void init() {
        this.in = null;
        this.attTmp = new AttributesExImpl();
        this.strTmp = new StringBuffer();
        this.nameTmp = new char[20];
        this.nameCache = new NameCache();
        if (this.namespaces) {
            this.nsSupport = new NamespaceSupport();
            if (this.isValidating && !this.prefixes) {
                this.nsAttTmp = new Vector();
            }
        }
        this.isStandalone = false;
        this.rootElementName = null;
        this.isInAttribute = false;
        this.inExternalPE = false;
        this.doLexicalPE = false;
        this.donePrologue = false;
        this.entities.clear();
        this.notations.clear();
        this.params.clear();
        this.elements.clear();
        this.ignoreDeclarations = false;
        this.builtin("amp", "&#38;");
        this.builtin("lt", "&#60;");
        this.builtin("gt", ">");
        this.builtin("quot", "\"");
        this.builtin("apos", "'");
        if (this.locale == null) {
            this.locale = Locale.getDefault();
        }
        if (this.resolver == null) {
            this.resolver = new Resolver();
        }
        this.setHandlers();
    }
    
    private void setHandlers() {
        if (this.contentHandler == null) {
            this.contentHandler = Parser2.nullHandler;
        }
        if (this.errHandler == null) {
            this.errHandler = Parser2.nullHandler;
        }
        if (this.dtdHandler == null) {
            this.dtdHandler = Parser2.nullHandler;
        }
        if (this.lexicalHandler == null) {
            this.lexicalHandler = Parser2.nullHandler;
        }
        if (this.declHandler == null) {
            this.declHandler = Parser2.nullHandler;
        }
    }
    
    private void builtin(final String entityName, final String entityValue) {
        final InternalEntity entity = new InternalEntity(entityName, entityValue.toCharArray());
        this.entities.put(entityName, entity);
    }
    
    private void parseInternal(final InputSource input) throws SAXException, IOException {
        while (true) {
            if (input == null) {
                this.fatal("P-000");
                try {
                    (this.in = InputEntity.getInputEntity(this.errHandler, this.locale)).init(input, null, null, false);
                    this.contentHandler.setDocumentLocator(this.locator);
                    this.contentHandler.startDocument();
                    this.maybeXmlDecl();
                    this.maybeMisc(false);
                    if (!this.maybeDoctypeDecl() && this.isValidating) {
                        this.warning("V-001", null);
                    }
                    this.maybeMisc(false);
                    this.donePrologue = true;
                    if (!this.in.peekc('<') || !this.maybeElement(null)) {
                        this.fatal("P-067");
                    }
                    this.afterRoot();
                    this.maybeMisc(true);
                    if (!this.in.isEOF()) {
                        this.fatal("P-001", new Object[] { Integer.toHexString(this.getc()) });
                    }
                    this.contentHandler.endDocument();
                }
                catch (EndOfInputException e) {
                    if (!this.in.isDocument()) {
                        final String name = this.in.getName();
                        do {
                            this.in = this.in.pop();
                        } while (this.in.isInternal());
                        this.fatal("P-002", new Object[] { name }, e);
                    }
                    else {
                        this.fatal("P-003", null, e);
                    }
                }
                catch (RuntimeException e2) {
                    throw new SAXParseException((e2.getMessage() != null) ? e2.getMessage() : e2.getClass().getName(), this.locator.getPublicId(), this.locator.getSystemId(), this.locator.getLineNumber(), this.locator.getColumnNumber(), e2);
                }
                finally {
                    this.strTmp = null;
                    this.attTmp = null;
                    this.nameTmp = null;
                    this.nameCache = null;
                    this.nsAttTmp = null;
                    if (this.in != null) {
                        this.in.close();
                        this.in = null;
                    }
                    this.params.clear();
                    this.entities.clear();
                    this.notations.clear();
                    this.elements.clear();
                    this.afterDocument();
                }
                return;
            }
            continue;
        }
    }
    
    void afterRoot() throws SAXException {
    }
    
    void afterDocument() {
    }
    
    private void whitespace(final String roleId) throws IOException, SAXException {
        if (!this.maybeWhitespace()) {
            this.fatal("P-004", new Object[] { Parser2.messages.getMessage(this.locale, roleId) });
        }
    }
    
    private boolean maybeWhitespace() throws IOException, SAXException {
        if (!this.inExternalPE || !this.doLexicalPE) {
            return this.in.maybeWhitespace();
        }
        char c = this.getc();
        boolean saw = false;
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
            saw = true;
            if (this.in.isEOF() && !this.in.isInternal()) {
                return saw;
            }
            c = this.getc();
        }
        this.ungetc();
        return saw;
    }
    
    private String maybeGetName() throws IOException, SAXException {
        final NameCacheEntry entry = this.maybeGetNameCacheEntry();
        return (entry == null) ? null : entry.name;
    }
    
    private NameCacheEntry maybeGetNameCacheEntry() throws IOException, SAXException {
        final char c = this.getc();
        if (!XmlChars.isLetter(c) && c != ':' && c != '_') {
            this.ungetc();
            return null;
        }
        return this.nameCharString(c);
    }
    
    private String getNmtoken() throws SAXException, IOException {
        final char c = this.getc();
        if (!XmlChars.isNameChar(c)) {
            this.fatal("P-006", new Object[] { new Character(c) });
        }
        return this.nameCharString(c).name;
    }
    
    private NameCacheEntry nameCharString(char c) throws IOException, SAXException {
        int i = 1;
        this.nameTmp[0] = c;
        while ((c = this.in.getNameChar()) != '\0') {
            if (i >= this.nameTmp.length) {
                final char[] tmp = new char[this.nameTmp.length + 10];
                System.arraycopy(this.nameTmp, 0, tmp, 0, this.nameTmp.length);
                this.nameTmp = tmp;
            }
            this.nameTmp[i++] = c;
        }
        return this.nameCache.lookupEntry(this.nameTmp, i);
    }
    
    private void parseLiteral(final boolean isEntityValue) throws IOException, SAXException {
        final boolean savedLexicalPE = this.doLexicalPE;
        final char quote = this.getc();
        final InputEntity source = this.in;
        if (quote != '\'' && quote != '\"') {
            this.fatal("P-007");
        }
        this.isInAttribute = !isEntityValue;
        this.strTmp = new StringBuffer();
        while (true) {
            if (this.in != source && this.in.isEOF()) {
                this.in = this.in.pop();
            }
            else {
                char c;
                if ((c = this.getc()) == quote && this.in == source) {
                    break;
                }
                if (c == '&') {
                    final String entityName = this.maybeGetName();
                    if (entityName != null) {
                        this.nextChar(';', "F-020", entityName);
                        if (isEntityValue) {
                            this.strTmp.append('&');
                            this.strTmp.append(entityName);
                            this.strTmp.append(';');
                        }
                        else {
                            this.expandEntityInLiteral(entityName, this.entities, isEntityValue);
                        }
                    }
                    else if ((c = this.getc()) == '#') {
                        int tmp = this.parseCharNumber();
                        if (tmp > 65535) {
                            tmp = this.surrogatesToCharTmp(tmp);
                            this.strTmp.append(this.charTmp[0]);
                            if (tmp != 2) {
                                continue;
                            }
                            this.strTmp.append(this.charTmp[1]);
                        }
                        else {
                            this.strTmp.append((char)tmp);
                        }
                    }
                    else {
                        this.fatal("P-009");
                    }
                }
                else {
                    if (c == '%' && isEntityValue) {
                        final String entityName = this.maybeGetName();
                        if (entityName != null) {
                            this.nextChar(';', "F-021", entityName);
                            if (this.inExternalPE) {
                                this.expandEntityInLiteral(entityName, this.params, isEntityValue);
                                continue;
                            }
                            this.fatal("P-010", new Object[] { entityName });
                            continue;
                        }
                        else {
                            this.fatal("P-011");
                        }
                    }
                    if (!isEntityValue) {
                        if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                            this.strTmp.append(' ');
                            continue;
                        }
                        if (c == '<') {
                            this.fatal("P-012");
                        }
                    }
                    this.strTmp.append(c);
                }
            }
        }
        this.isInAttribute = false;
    }
    
    private void expandEntityInLiteral(final String name, final SimpleHashtable table, final boolean isEntityValue) throws SAXException, IOException {
        final Object entity = table.get(name);
        if (entity instanceof InternalEntity) {
            final InternalEntity value = (InternalEntity)entity;
            if (this.isValidating && this.isStandalone && !value.isFromInternalSubset) {
                this.error("V-002", new Object[] { name });
            }
            this.pushReader(value.buf, name, !value.isPE);
        }
        else if (entity instanceof ExternalEntity) {
            if (!isEntityValue) {
                this.fatal("P-013", new Object[] { name });
            }
            this.pushReader((ExternalEntity)entity);
        }
        else if (entity == null) {
            this.fatal((table == this.params) ? "V-022" : "P-014", new Object[] { name });
        }
    }
    
    private String getQuotedString(final String type, final String extra) throws IOException, SAXException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/crimson/parser/Parser2.in:Lorg/apache/crimson/parser/InputEntity;
        //     4: invokevirtual   org/apache/crimson/parser/InputEntity.getc:()C
        //     7: istore_3        /* quote */
        //     8: iload_3         /* quote */
        //     9: bipush          39
        //    11: if_icmpeq       52
        //    14: iload_3         /* quote */
        //    15: bipush          34
        //    17: if_icmpeq       52
        //    20: aload_0         /* this */
        //    21: ldc             "P-015"
        //    23: iconst_1       
        //    24: anewarray       Ljava/lang/Object;
        //    27: dup            
        //    28: iconst_0       
        //    29: getstatic       org/apache/crimson/parser/Parser2.messages:Lorg/apache/crimson/parser/Parser2$Catalog;
        //    32: aload_0         /* this */
        //    33: getfield        org/apache/crimson/parser/Parser2.locale:Ljava/util/Locale;
        //    36: aload_1         /* type */
        //    37: iconst_1       
        //    38: anewarray       Ljava/lang/Object;
        //    41: dup            
        //    42: iconst_0       
        //    43: aload_2         /* extra */
        //    44: aastore        
        //    45: invokevirtual   org/apache/crimson/util/MessageCatalog.getMessage:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    48: aastore        
        //    49: invokespecial   org/apache/crimson/parser/Parser2.fatal:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    52: aload_0         /* this */
        //    53: new             Ljava/lang/StringBuffer;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuffer.<init>:()V
        //    60: putfield        org/apache/crimson/parser/Parser2.strTmp:Ljava/lang/StringBuffer;
        //    63: goto            76
        //    66: aload_0         /* this */
        //    67: getfield        org/apache/crimson/parser/Parser2.strTmp:Ljava/lang/StringBuffer;
        //    70: iload           4
        //    72: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    75: pop            
        //    76: aload_0         /* this */
        //    77: getfield        org/apache/crimson/parser/Parser2.in:Lorg/apache/crimson/parser/InputEntity;
        //    80: invokevirtual   org/apache/crimson/parser/InputEntity.getc:()C
        //    83: dup            
        //    84: istore          c
        //    86: iload_3         /* quote */
        //    87: if_icmpne       66
        //    90: aload_0         /* this */
        //    91: getfield        org/apache/crimson/parser/Parser2.strTmp:Ljava/lang/StringBuffer;
        //    94: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    97: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //  throws org.xml.sax.SAXException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  -----------------------------------
        //  0      98      0     this   Lorg/apache/crimson/parser/Parser2;
        //  0      98      1     type   Ljava/lang/String;
        //  0      98      2     extra  Ljava/lang/String;
        //  8      90      3     quote  C
        //  86     12      4     c      C
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private String parsePublicId() throws IOException, SAXException {
        final String retval = this.getQuotedString("F-033", null);
        for (int i = 0; i < retval.length(); ++i) {
            final char c = retval.charAt(i);
            if (" \r\n-'()+,./:=?;!*#@$_%0123456789".indexOf(c) == -1 && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                this.fatal("P-016", new Object[] { new Character(c) });
            }
        }
        (this.strTmp = new StringBuffer()).append(retval);
        return this.normalize(false);
    }
    
    private boolean maybeComment(final boolean skipStart) throws IOException, SAXException {
        if (!this.in.peek(skipStart ? "!--" : "<!--", null)) {
            return false;
        }
        final boolean savedLexicalPE = this.doLexicalPE;
        this.doLexicalPE = false;
        final boolean saveCommentText = this.lexicalHandler != Parser2.nullHandler;
    Label_0071:
        while (true) {
            if (saveCommentText) {
                this.strTmp = new StringBuffer();
                break Label_0071;
            }
            break Label_0071;
            while (true) {
                try {
                    while (true) {
                        int c = this.getc();
                        if (c == 45) {
                            c = this.getc();
                            if (c == 45) {
                                break;
                            }
                            if (saveCommentText) {
                                this.strTmp.append('-');
                            }
                            this.ungetc();
                        }
                        else {
                            if (!saveCommentText) {
                                continue;
                            }
                            this.strTmp.append((char)c);
                        }
                    }
                    this.nextChar('>', "F-022", null);
                    break;
                }
                catch (EndOfInputException e) {
                    if (this.inExternalPE || (!this.donePrologue && this.in.isInternal())) {
                        if (this.isValidating) {
                            this.error("V-021", null);
                        }
                        this.in = this.in.pop();
                        continue;
                    }
                    this.fatal("P-017");
                    continue;
                }
                continue Label_0071;
            }
            break;
        }
        this.doLexicalPE = savedLexicalPE;
        if (saveCommentText) {
            final int length = this.strTmp.length();
            final char[] charArray = new char[length];
            if (length != 0) {
                this.strTmp.getChars(0, length, charArray, 0);
            }
            this.lexicalHandler.comment(charArray, 0, length);
        }
        return true;
    }
    
    private boolean maybePI(final boolean skipStart) throws IOException, SAXException {
        final boolean savedLexicalPE = this.doLexicalPE;
        if (!this.in.peek(skipStart ? "?" : "<?", null)) {
            return false;
        }
        this.doLexicalPE = false;
        final String target = this.maybeGetName();
        if (target == null) {
            this.fatal("P-018");
        }
        if ("xml".equals(target)) {
            this.fatal("P-019");
        }
        if ("xml".equalsIgnoreCase(target)) {
            this.fatal("P-020", new Object[] { target });
        }
        if (this.maybeWhitespace()) {
            this.strTmp = new StringBuffer();
            try {
                while (true) {
                    final char c = this.in.getc();
                    if (c == '?' && this.in.peekc('>')) {
                        break;
                    }
                    this.strTmp.append(c);
                }
            }
            catch (EndOfInputException e) {
                this.fatal("P-021");
            }
            this.contentHandler.processingInstruction(target, this.strTmp.toString());
        }
        else {
            if (!this.in.peek("?>", null)) {
                this.fatal("P-022");
            }
            this.contentHandler.processingInstruction(target, "");
        }
        this.doLexicalPE = savedLexicalPE;
        return true;
    }
    
    private void maybeXmlDecl() throws IOException, SAXException {
        if (!this.in.isXmlDeclOrTextDeclPrefix()) {
            return;
        }
        this.peek("<?xml");
        this.readVersion(true, "1.0");
        this.readEncoding(false);
        this.readStandalone();
        this.maybeWhitespace();
        if (!this.peek("?>")) {
            final char c = this.getc();
            this.fatal("P-023", new Object[] { Integer.toHexString(c), new Character(c) });
        }
    }
    
    private String maybeReadAttribute(final String name, final boolean must) throws IOException, SAXException {
        if (!this.maybeWhitespace()) {
            if (!must) {
                return null;
            }
            this.fatal("P-024", new Object[] { name });
        }
        if (!this.peek(name)) {
            if (!must) {
                this.ungetc();
                return null;
            }
            this.fatal("P-024", new Object[] { name });
        }
        this.maybeWhitespace();
        this.nextChar('=', "F-023", null);
        this.maybeWhitespace();
        return this.getQuotedString("F-035", name);
    }
    
    private void readVersion(final boolean must, final String versionNum) throws IOException, SAXException {
        final String value = this.maybeReadAttribute("version", must);
        if (must && value == null) {
            this.fatal("P-025", new Object[] { versionNum });
        }
        if (value != null) {
            for (int length = value.length(), i = 0; i < length; ++i) {
                final char c = value.charAt(i);
                if ((c < '0' || c > '9') && c != '_' && c != '.' && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && c != ':' && c != '-') {
                    this.fatal("P-026", new Object[] { value });
                }
            }
        }
        if (value != null && !value.equals(versionNum)) {
            this.error("P-027", new Object[] { versionNum, value });
        }
    }
    
    private void maybeMisc(final boolean eofOK) throws IOException, SAXException {
        while ((!eofOK || !this.in.isEOF()) && (this.maybeComment(false) || this.maybePI(false) || this.maybeWhitespace())) {}
    }
    
    private String getMarkupDeclname(final String roleId, final boolean qname) throws IOException, SAXException {
        this.whitespace(roleId);
        final String name = this.maybeGetName();
        if (name == null) {
            this.fatal("P-005", new Object[] { Parser2.messages.getMessage(this.locale, roleId) });
        }
        return name;
    }
    
    private boolean maybeDoctypeDecl() throws IOException, SAXException {
        if (!this.peek("<!DOCTYPE")) {
            return false;
        }
        ExternalEntity externalSubset = null;
        this.rootElementName = this.getMarkupDeclname("F-014", true);
        if (this.maybeWhitespace() && (externalSubset = this.maybeExternalID()) != null) {
            this.lexicalHandler.startDTD(this.rootElementName, externalSubset.publicId, externalSubset.verbatimSystemId);
            this.maybeWhitespace();
        }
        else {
            this.lexicalHandler.startDTD(this.rootElementName, null, null);
        }
        if (this.in.peekc('[')) {
            while (true) {
                if (this.in.isEOF() && !this.in.isDocument()) {
                    this.in = this.in.pop();
                }
                else {
                    if (this.maybeMarkupDecl() || this.maybePEReference()) {
                        continue;
                    }
                    if (this.maybeWhitespace()) {
                        continue;
                    }
                    if (!this.peek("<![")) {
                        break;
                    }
                    this.fatal("P-028");
                }
            }
            this.nextChar(']', "F-024", null);
            this.maybeWhitespace();
        }
        this.nextChar('>', "F-025", null);
        if (externalSubset != null) {
            externalSubset.name = "[dtd]";
            externalSubset.isPE = true;
            this.externalParameterEntity(externalSubset);
        }
        this.params.clear();
        this.lexicalHandler.endDTD();
        final Vector v = new Vector();
        final Enumeration e = this.notations.keys();
        while (e.hasMoreElements()) {
            final String name = e.nextElement();
            final Object value = this.notations.get(name);
            if (value == Boolean.TRUE) {
                if (this.isValidating) {
                    this.error("V-003", new Object[] { name });
                }
                v.addElement(name);
            }
            else {
                if (!(value instanceof String)) {
                    continue;
                }
                if (this.isValidating) {
                    this.error("V-004", new Object[] { name });
                }
                v.addElement(name);
            }
        }
        while (!v.isEmpty()) {
            final Object name2 = v.firstElement();
            v.removeElement(name2);
            this.notations.remove(name2);
        }
        return true;
    }
    
    private boolean maybeMarkupDecl() throws IOException, SAXException {
        return this.maybeElementDecl() || this.maybeAttlistDecl() || this.maybeEntityDecl() || this.maybeNotationDecl() || this.maybePI(false) || this.maybeComment(false);
    }
    
    private void readStandalone() throws IOException, SAXException {
        final String value = this.maybeReadAttribute("standalone", false);
        if (value == null || "no".equals(value)) {
            return;
        }
        if ("yes".equals(value)) {
            this.isStandalone = true;
            return;
        }
        this.fatal("P-029", new Object[] { value });
    }
    
    private boolean isXmlLang(final String value) {
        if (value.length() < 2) {
            return false;
        }
        char c = value.charAt(1);
        int nextSuffix;
        if (c == '-') {
            c = value.charAt(0);
            if (c != 'i' && c != 'I' && c != 'x' && c != 'X') {
                return false;
            }
            nextSuffix = 1;
        }
        else {
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                return false;
            }
            c = value.charAt(0);
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                return false;
            }
            nextSuffix = 2;
        }
        while (nextSuffix < value.length()) {
            c = value.charAt(nextSuffix);
            if (c != '-') {
                break;
            }
            while (++nextSuffix < value.length()) {
                c = value.charAt(nextSuffix);
                if (c < 'a' || c > 'z') {
                    if (c < 'A') {
                        break;
                    }
                    if (c > 'Z') {
                        break;
                    }
                    continue;
                }
            }
        }
        return value.length() == nextSuffix && c != '-';
    }
    
    private boolean maybeElement(ElementValidator validator) throws IOException, SAXException {
        boolean haveAttributes = false;
        boolean hasContent = true;
        final NameCacheEntry name = this.maybeGetNameCacheEntry();
        if (name == null) {
            return false;
        }
        if (validator != null) {
            validator.consume(name.name);
        }
        ElementDecl element = (ElementDecl)this.elements.get(name.name);
        if (this.isValidating) {
            if (element == null || element.contentType == null) {
                this.error("V-005", new Object[] { name.name });
                element = new ElementDecl(name.name);
                element.contentType = "ANY";
                this.elements.put(name.name, element);
            }
            if (validator == null && this.rootElementName != null && !this.rootElementName.equals(name.name)) {
                this.error("V-006", new Object[] { name.name, this.rootElementName });
            }
        }
        final int startLine = this.in.getLineNumber();
        boolean sawWhite = this.in.maybeWhitespace();
        Vector exceptions = null;
        if (this.namespaces) {
            this.nsSupport.pushContext();
            this.seenNSDecl = false;
        }
        while (true) {
            while (!this.in.peekc('>')) {
                if (this.in.peekc('/')) {
                    hasContent = false;
                    if (element != null) {
                        this.attTmp.setIdAttributeName(element.id);
                    }
                    if (element != null && element.attributes.size() != 0) {
                        haveAttributes = (this.defaultAttributes(element) || haveAttributes);
                    }
                    if (this.seenNSDecl) {
                        for (int length = this.attTmp.getLength(), i = 0; i < length; ++i) {
                            final String attQName = this.attTmp.getQName(i);
                            if (attQName.startsWith("xmlns")) {
                                if (attQName.length() == 5) {
                                    continue;
                                }
                                if (attQName.charAt(5) == ':') {
                                    continue;
                                }
                            }
                            final String[] attName = this.processName(attQName, true, false);
                            this.attTmp.setURI(i, attName[0]);
                            this.attTmp.setLocalName(i, attName[1]);
                        }
                    }
                    else if (exceptions != null && this.errHandler != null) {
                        for (int j = 0; j < exceptions.size(); ++j) {
                            this.errHandler.error(exceptions.elementAt(j));
                        }
                    }
                    if (this.namespaces) {
                        final String[] parts = this.processName(name.name, false, false);
                        this.contentHandler.startElement(parts[0], parts[1], parts[2], this.attTmp);
                    }
                    else {
                        this.contentHandler.startElement("", "", name.name, this.attTmp);
                    }
                    if (haveAttributes) {
                        this.attTmp.clear();
                        if (this.isValidating && this.namespaces && !this.prefixes) {
                            this.nsAttTmp.removeAllElements();
                        }
                    }
                    validator = this.newValidator(element);
                    if (hasContent) {
                        this.content(element, false, validator);
                        if (!this.in.peek(name.name, name.chars)) {
                            this.fatal("P-034", new Object[] { name.name, new Integer(startLine) });
                        }
                        this.in.maybeWhitespace();
                    }
                    this.nextChar('>', "F-027", name.name);
                    validator.done();
                    if (this.namespaces) {
                        final String[] parts = this.processName(name.name, false, false);
                        this.contentHandler.endElement(parts[0], parts[1], parts[2]);
                        final Enumeration prefixes = this.nsSupport.getDeclaredPrefixes();
                        while (prefixes.hasMoreElements()) {
                            final String prefix = prefixes.nextElement();
                            this.contentHandler.endPrefixMapping(prefix);
                        }
                        this.nsSupport.popContext();
                    }
                    else {
                        this.contentHandler.endElement("", "", name.name);
                    }
                    return true;
                }
                if (!sawWhite) {
                    this.fatal("P-030");
                }
                final String attQName2 = this.maybeGetName();
                if (attQName2 == null) {
                    this.fatal("P-031", new Object[] { new Character(this.getc()) });
                }
                if (this.attTmp.getValue(attQName2) != null) {
                    this.fatal("P-032", new Object[] { attQName2 });
                }
                this.in.maybeWhitespace();
                this.nextChar('=', "F-026", attQName2);
                this.in.maybeWhitespace();
                this.parseLiteral(this.doLexicalPE = false);
                sawWhite = this.in.maybeWhitespace();
                final AttributeDecl info = (element == null) ? null : ((AttributeDecl)element.attributes.get(attQName2));
                String value;
                if (info == null) {
                    if (this.isValidating) {
                        this.error("V-007", new Object[] { attQName2, name.name });
                    }
                    value = this.strTmp.toString();
                }
                else {
                    if (!"CDATA".equals(info.type)) {
                        value = this.normalize(!info.isFromInternalSubset);
                        if (this.isValidating) {
                            this.validateAttributeSyntax(info, value);
                        }
                    }
                    else {
                        value = this.strTmp.toString();
                    }
                    if (this.isValidating && info.isFixed && !value.equals(info.defaultValue)) {
                        this.error("V-008", new Object[] { attQName2, name.name, info.defaultValue });
                    }
                }
                if ("xml:lang".equals(attQName2) && !this.isXmlLang(value)) {
                    this.error("P-033", new Object[] { value });
                }
                final String type = (info == null) ? "CDATA" : info.type;
                final String defaultValue = (info == null) ? null : info.defaultValue;
                if (this.namespaces) {
                    exceptions = this.processAttributeNS(attQName2, type, value, defaultValue, true, false, exceptions);
                }
                else {
                    this.attTmp.addAttribute("", "", attQName2, type, value, defaultValue, true);
                }
                haveAttributes = true;
            }
            continue;
        }
    }
    
    private Vector processAttributeNS(final String attQName, final String type, final String value, final String defaultValue, final boolean isSpecified, final boolean isDefaulting, Vector exceptions) throws SAXException {
        if (attQName.startsWith("xmlns")) {
            final boolean defaultNSDecl = attQName.length() == 5;
            if (defaultNSDecl || attQName.charAt(5) == ':') {
                String prefix;
                if (defaultNSDecl) {
                    prefix = "";
                }
                else {
                    prefix = attQName.substring(6);
                }
                if (!this.nsSupport.declarePrefix(prefix, value)) {
                    this.error("P-083", new Object[] { prefix });
                }
                this.contentHandler.startPrefixMapping(prefix, value);
                if (this.prefixes) {
                    this.attTmp.addAttribute("", prefix, attQName.intern(), type, value, defaultValue, isSpecified);
                }
                else if (this.isValidating && !isDefaulting) {
                    this.nsAttTmp.addElement(attQName);
                }
                this.seenNSDecl = true;
                return exceptions;
            }
        }
        try {
            final String[] attName = this.processName(attQName, true, true);
            this.attTmp.addAttribute(attName[0], attName[1], attName[2], type, value, defaultValue, isSpecified);
        }
        catch (SAXException e) {
            if (exceptions == null) {
                exceptions = new Vector<SAXException>();
            }
            exceptions.addElement(e);
            this.attTmp.addAttribute("", attQName, attQName, type, value, defaultValue, isSpecified);
        }
        return exceptions;
    }
    
    private String[] processName(final String qName, final boolean isAttribute, final boolean useException) throws SAXException {
        String[] parts = this.nsSupport.processName(qName, this.namePartsTmp, isAttribute);
        if (parts == null) {
            parts = new String[] { "", null, null };
            final String localName = XmlNames.getLocalPart(qName);
            parts[1] = ((localName != null) ? localName.intern() : "");
            parts[2] = qName.intern();
            final String messageId = "P-084";
            final Object[] parameters = { qName };
            if (useException) {
                throw new SAXParseException(Parser2.messages.getMessage(this.locale, messageId, parameters), this.locator);
            }
            this.error(messageId, parameters);
        }
        return parts;
    }
    
    ElementValidator newValidator(final ElementDecl element) {
        return ElementValidator.ANY;
    }
    
    void validateAttributeSyntax(final AttributeDecl attr, final String value) throws SAXException {
    }
    
    private boolean defaultAttributes(final ElementDecl element) throws SAXException {
        boolean didDefault = false;
        final Enumeration e = element.attributes.keys();
        while (e.hasMoreElements()) {
            final String declAttName = e.nextElement();
            if (this.attTmp.getValue(declAttName) != null) {
                continue;
            }
            final AttributeDecl info = (AttributeDecl)element.attributes.get(declAttName);
            if (this.isValidating && info.isRequired) {
                if (this.namespaces && !this.prefixes && this.nsAttTmp.contains(declAttName)) {
                    continue;
                }
                this.error("V-009", new Object[] { declAttName });
            }
            final String defaultValue = info.defaultValue;
            if (defaultValue == null) {
                continue;
            }
            if (this.isValidating && this.isStandalone && !info.isFromInternalSubset) {
                this.error("V-010", new Object[] { declAttName });
            }
            if (this.namespaces) {
                this.processAttributeNS(declAttName, info.type, defaultValue, defaultValue, false, true, null);
            }
            else {
                this.attTmp.addAttribute("", "", declAttName, info.type, defaultValue, defaultValue, false);
            }
            didDefault = true;
        }
        return didDefault;
    }
    
    private void content(final ElementDecl element, final boolean allowEOF, final ElementValidator validator) throws IOException, SAXException {
        while (true) {
            if (this.in.peekc('<')) {
                if (this.maybeElement(validator)) {
                    continue;
                }
                if (this.in.peekc('/')) {
                    return;
                }
                if (this.maybeComment(true)) {
                    continue;
                }
                if (this.maybePI(true)) {
                    continue;
                }
                if (this.in.peek("![CDATA[", null)) {
                    this.lexicalHandler.startCDATA();
                    this.in.unparsedContent(this.contentHandler, validator, element != null && element.ignoreWhitespace, (this.isStandalone && this.isValidating && !element.isFromInternalSubset) ? "V-023" : null);
                    this.lexicalHandler.endCDATA();
                    continue;
                }
                final char c = this.getc();
                this.fatal("P-079", new Object[] { Integer.toHexString(c), new Character(c) });
            }
            if (element != null && element.ignoreWhitespace && this.in.ignorableWhitespace(this.contentHandler)) {
                if (!this.isValidating || !this.isStandalone || element.isFromInternalSubset) {
                    continue;
                }
                this.error("V-011", new Object[] { element.name });
            }
            else {
                if (this.in.parsedContent(this.contentHandler, validator)) {
                    continue;
                }
                if (this.in.isEOF()) {
                    if (!allowEOF) {
                        this.fatal("P-035");
                    }
                    return;
                }
                if (!this.maybeReferenceInContent(element, validator)) {
                    throw new InternalError();
                }
                continue;
            }
        }
    }
    
    private boolean maybeElementDecl() throws IOException, SAXException {
        final InputEntity start = this.peekDeclaration("!ELEMENT");
        if (start == null) {
            return false;
        }
        final String name = this.getMarkupDeclname("F-015", true);
        ElementDecl element = (ElementDecl)this.elements.get(name);
        boolean declEffective = false;
        if (element != null) {
            if (element.contentType != null) {
                if (this.isValidating && element.contentType != null) {
                    this.error("V-012", new Object[] { name });
                }
                element = new ElementDecl(name);
            }
        }
        else {
            element = new ElementDecl(name);
            if (!this.ignoreDeclarations) {
                this.elements.put(element.name, element);
                declEffective = true;
            }
        }
        element.isFromInternalSubset = !this.inExternalPE;
        this.whitespace("F-000");
        if (this.peek("EMPTY")) {
            element.contentType = "EMPTY";
            element.ignoreWhitespace = true;
        }
        else if (this.peek("ANY")) {
            element.contentType = "ANY";
            element.ignoreWhitespace = false;
        }
        else {
            element.contentType = this.getMixedOrChildren(element);
        }
        this.maybeWhitespace();
        final char c = this.getc();
        if (c != '>') {
            this.fatal("P-036", new Object[] { name, new Character(c) });
        }
        if (this.isValidating && start != this.in) {
            this.error("V-013", null);
        }
        if (declEffective) {
            this.declHandler.elementDecl(element.name, element.contentType);
        }
        return true;
    }
    
    private String getMixedOrChildren(final ElementDecl element) throws IOException, SAXException {
        this.strTmp = new StringBuffer();
        this.nextChar('(', "F-028", element.name);
        final InputEntity start = this.in;
        this.maybeWhitespace();
        this.strTmp.append('(');
        if (this.peek("#PCDATA")) {
            this.strTmp.append("#PCDATA");
            this.getMixed(element.name, start);
            element.ignoreWhitespace = false;
        }
        else {
            element.model = this.getcps(element.name, start);
            element.ignoreWhitespace = true;
        }
        return this.strTmp.toString();
    }
    
    ContentModel newContentModel(final String tag) {
        return null;
    }
    
    ContentModel newContentModel(final char type, final ContentModel next) {
        return null;
    }
    
    private ContentModel getcps(final String element, final InputEntity start) throws IOException, SAXException {
        boolean decided = false;
        char type = '\0';
        ContentModel temp;
        ContentModel retval;
        ContentModel current = retval = (temp = null);
        do {
            final String tag = this.maybeGetName();
            if (tag != null) {
                this.strTmp.append(tag);
                temp = this.getFrequency(this.newContentModel(tag));
            }
            else if (this.peek("(")) {
                final InputEntity next = this.in;
                this.strTmp.append('(');
                this.maybeWhitespace();
                temp = this.getFrequency(this.getcps(element, next));
            }
            else {
                this.fatal((type == '\0') ? "P-039" : ((type == ',') ? "P-037" : "P-038"), new Object[] { new Character(this.getc()) });
            }
            this.maybeWhitespace();
            if (decided) {
                final char c = this.getc();
                if (current != null) {
                    current.next = this.newContentModel(type, temp);
                    current = current.next;
                }
                if (c == type) {
                    this.strTmp.append(type);
                    this.maybeWhitespace();
                    continue;
                }
                if (c == ')') {
                    this.ungetc();
                    continue;
                }
                this.fatal((type == '\0') ? "P-041" : "P-040", new Object[] { new Character(c), new Character(type) });
            }
            else {
                type = this.getc();
                if (type != '|' && type != ',') {
                    current = (retval = temp);
                    this.ungetc();
                    continue;
                }
                decided = true;
                current = (retval = this.newContentModel(type, temp));
                this.strTmp.append(type);
            }
            this.maybeWhitespace();
        } while (!this.peek(")"));
        if (this.isValidating && this.in != start) {
            this.error("V-014", new Object[] { element });
        }
        this.strTmp.append(')');
        return this.getFrequency(retval);
    }
    
    private ContentModel getFrequency(final ContentModel original) throws IOException, SAXException {
        final char c = this.getc();
        if (c != '?' && c != '+' && c != '*') {
            this.ungetc();
            return original;
        }
        this.strTmp.append(c);
        if (original == null) {
            return null;
        }
        if (original.type == '\0') {
            original.type = c;
            return original;
        }
        return this.newContentModel(c, original);
    }
    
    private void getMixed(final String element, final InputEntity start) throws IOException, SAXException {
        this.maybeWhitespace();
        if (this.peek(")*") || this.peek(")")) {
            if (this.isValidating && this.in != start) {
                this.error("V-014", new Object[] { element });
            }
            this.strTmp.append(')');
            return;
        }
        Vector v = null;
        if (this.isValidating) {
            v = new Vector();
        }
        while (this.peek("|")) {
            this.strTmp.append('|');
            this.maybeWhitespace();
            final String name = this.maybeGetName();
            if (name == null) {
                this.fatal("P-042", new Object[] { element, Integer.toHexString(this.getc()) });
            }
            if (this.isValidating) {
                if (v.contains(name)) {
                    this.error("V-015", new Object[] { name });
                }
                else {
                    v.addElement(name);
                }
            }
            this.strTmp.append(name);
            this.maybeWhitespace();
        }
        if (!this.peek(")*")) {
            this.fatal("P-043", new Object[] { element, new Character(this.getc()) });
        }
        if (this.isValidating && this.in != start) {
            this.error("V-014", new Object[] { element });
        }
        this.strTmp.append(')');
    }
    
    private boolean maybeAttlistDecl() throws IOException, SAXException {
        final InputEntity start = this.peekDeclaration("!ATTLIST");
        if (start == null) {
            return false;
        }
        String name = this.getMarkupDeclname("F-016", true);
        ElementDecl element = (ElementDecl)this.elements.get(name);
        if (element == null) {
            element = new ElementDecl(name);
            if (!this.ignoreDeclarations) {
                this.elements.put(name, element);
            }
        }
        this.maybeWhitespace();
        while (!this.peek(">")) {
            name = this.maybeGetName();
            if (name == null) {
                this.fatal("P-044", new Object[] { new Character(this.getc()) });
            }
            this.whitespace("F-001");
            final AttributeDecl a = new AttributeDecl(name);
            a.isFromInternalSubset = !this.inExternalPE;
            if (this.peek("CDATA")) {
                a.type = "CDATA";
            }
            else if (this.peek("IDREFS")) {
                a.type = "IDREFS";
            }
            else if (this.peek("IDREF")) {
                a.type = "IDREF";
            }
            else if (this.peek("ID")) {
                a.type = "ID";
                if (element.id != null) {
                    if (this.isValidating) {
                        this.error("V-016", new Object[] { element.id });
                    }
                }
                else {
                    element.id = name;
                }
            }
            else if (this.peek("ENTITY")) {
                a.type = "ENTITY";
            }
            else if (this.peek("ENTITIES")) {
                a.type = "ENTITIES";
            }
            else if (this.peek("NMTOKENS")) {
                a.type = "NMTOKENS";
            }
            else if (this.peek("NMTOKEN")) {
                a.type = "NMTOKEN";
            }
            else if (this.peek("NOTATION")) {
                a.type = "NOTATION";
                this.whitespace("F-002");
                this.nextChar('(', "F-029", null);
                this.maybeWhitespace();
                final Vector v = new Vector();
                do {
                    if ((name = this.maybeGetName()) == null) {
                        this.fatal("P-068");
                    }
                    if (this.isValidating && this.notations.get(name) == null) {
                        this.notations.put(name, name);
                    }
                    v.addElement(name);
                    this.maybeWhitespace();
                    if (this.peek("|")) {
                        this.maybeWhitespace();
                    }
                } while (!this.peek(")"));
                a.values = new String[v.size()];
                for (int i = 0; i < v.size(); ++i) {
                    a.values[i] = v.elementAt(i);
                }
            }
            else if (this.peek("(")) {
                a.type = "ENUMERATION";
                this.maybeWhitespace();
                final Vector v = new Vector();
                do {
                    name = this.getNmtoken();
                    v.addElement(name);
                    this.maybeWhitespace();
                    if (this.peek("|")) {
                        this.maybeWhitespace();
                    }
                } while (!this.peek(")"));
                a.values = new String[v.size()];
                for (int i = 0; i < v.size(); ++i) {
                    a.values[i] = v.elementAt(i);
                }
            }
            else {
                this.fatal("P-045", new Object[] { name, new Character(this.getc()) });
            }
            this.whitespace("F-003");
            if (this.peek("#REQUIRED")) {
                a.valueDefault = "#REQUIRED";
                a.isRequired = true;
            }
            else if (this.peek("#FIXED")) {
                if (this.isValidating && a.type == "ID") {
                    this.error("V-017", new Object[] { a.name });
                }
                a.valueDefault = "#FIXED";
                a.isFixed = true;
                this.whitespace("F-004");
                this.parseLiteral(this.doLexicalPE = false);
                this.doLexicalPE = true;
                if (a.type != "CDATA") {
                    a.defaultValue = this.normalize(false);
                }
                else {
                    a.defaultValue = this.strTmp.toString();
                }
                if (a.type != "CDATA") {
                    this.validateAttributeSyntax(a, a.defaultValue);
                }
            }
            else if (this.peek("#IMPLIED")) {
                a.valueDefault = "#IMPLIED";
            }
            else {
                if (this.isValidating && a.type == "ID") {
                    this.error("V-018", new Object[] { a.name });
                }
                this.parseLiteral(this.doLexicalPE = false);
                this.doLexicalPE = true;
                if (a.type != "CDATA") {
                    a.defaultValue = this.normalize(false);
                }
                else {
                    a.defaultValue = this.strTmp.toString();
                }
                if (a.type != "CDATA") {
                    this.validateAttributeSyntax(a, a.defaultValue);
                }
            }
            if ("xml:lang".equals(a.name) && a.defaultValue != null && !this.isXmlLang(a.defaultValue)) {
                this.error("P-033", new Object[] { a.defaultValue });
            }
            if (!this.ignoreDeclarations && element.attributes.get(a.name) == null) {
                element.attributes.put(a.name, a);
                String saxType;
                if (a.type == "ENUMERATION" || a.type == "NOTATION") {
                    final StringBuffer fullType = new StringBuffer();
                    if (a.type == "NOTATION") {
                        fullType.append(a.type);
                        fullType.append(" ");
                    }
                    if (a.values.length > 1) {
                        fullType.append("(");
                    }
                    for (int j = 0; j < a.values.length; ++j) {
                        fullType.append(a.values[j]);
                        if (j + 1 < a.values.length) {
                            fullType.append("|");
                        }
                    }
                    if (a.values.length > 1) {
                        fullType.append(")");
                    }
                    saxType = fullType.toString();
                }
                else {
                    saxType = a.type;
                }
                this.declHandler.attributeDecl(element.name, a.name, saxType, a.valueDefault, a.defaultValue);
            }
            this.maybeWhitespace();
        }
        if (this.isValidating && start != this.in) {
            this.error("V-013", null);
        }
        return true;
    }
    
    private String normalize(final boolean invalidIfNeeded) throws SAXException {
        String s = this.strTmp.toString();
        String s2 = s.trim();
        boolean didStrip = false;
        if (s != s2) {
            s = s2;
            s2 = null;
            didStrip = true;
        }
        this.strTmp = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (!XmlChars.isSpace(c)) {
                this.strTmp.append(c);
            }
            else {
                this.strTmp.append(' ');
                while (++i < s.length() && XmlChars.isSpace(s.charAt(i))) {
                    didStrip = true;
                }
                --i;
            }
        }
        if (this.isValidating && this.isStandalone && invalidIfNeeded && (s2 == null || didStrip)) {
            this.error("V-019", null);
        }
        if (didStrip) {
            return this.strTmp.toString();
        }
        return s;
    }
    
    private boolean maybeConditionalSect() throws IOException, SAXException {
        if (!this.peek("<![")) {
            return false;
        }
        final InputEntity start = this.in;
        this.maybeWhitespace();
        final String keyword;
        if ((keyword = this.maybeGetName()) == null) {
            this.fatal("P-046");
        }
        this.maybeWhitespace();
        this.nextChar('[', "F-030", null);
        if ("INCLUDE".equals(keyword)) {
            while (true) {
                if (!this.in.isEOF() || this.in == start) {
                    if (this.in.isEOF()) {
                        if (this.isValidating) {
                            this.error("V-020", null);
                        }
                        this.in = this.in.pop();
                    }
                    if (this.peek("]]>")) {
                        break;
                    }
                    this.doLexicalPE = false;
                    if (this.maybeWhitespace()) {
                        continue;
                    }
                    if (this.maybePEReference()) {
                        continue;
                    }
                    this.doLexicalPE = true;
                    if (this.maybeMarkupDecl()) {
                        continue;
                    }
                    if (this.maybeConditionalSect()) {
                        continue;
                    }
                    this.fatal("P-047");
                }
                else {
                    this.in = this.in.pop();
                }
            }
        }
        else if ("IGNORE".equals(keyword)) {
            int nestlevel = 1;
            this.doLexicalPE = false;
            while (nestlevel > 0) {
                final char c = this.getc();
                if (c == '<') {
                    if (!this.peek("![")) {
                        continue;
                    }
                    ++nestlevel;
                }
                else {
                    if (c != ']' || !this.peek("]>")) {
                        continue;
                    }
                    --nestlevel;
                }
            }
        }
        else {
            this.fatal("P-048", new Object[] { keyword });
        }
        return true;
    }
    
    private boolean maybeReferenceInContent(final ElementDecl element, final ElementValidator validator) throws IOException, SAXException {
        if (!this.in.peekc('&')) {
            return false;
        }
        if (!this.in.peekc('#')) {
            final String name = this.maybeGetName();
            if (name == null) {
                this.fatal("P-009");
            }
            this.nextChar(';', "F-020", name);
            this.expandEntityInContent(element, name, validator);
            return true;
        }
        validator.text();
        this.contentHandler.characters(this.charTmp, 0, this.surrogatesToCharTmp(this.parseCharNumber()));
        return true;
    }
    
    private int parseCharNumber() throws SAXException, IOException {
        int retval = 0;
        if (this.getc() != 'x') {
            this.ungetc();
            while (true) {
                final char c = this.getc();
                if (c >= '0' && c <= '9') {
                    retval *= 10;
                    retval += c - '0';
                }
                else {
                    if (c == ';') {
                        break;
                    }
                    this.fatal("P-049");
                }
            }
            return retval;
        }
        while (true) {
            final char c = this.getc();
            if (c >= '0' && c <= '9') {
                retval <<= 4;
                retval += c - '0';
            }
            else if (c >= 'a' && c <= 'f') {
                retval <<= 4;
                retval += '\n' + (c - 'a');
            }
            else if (c >= 'A' && c <= 'F') {
                retval <<= 4;
                retval += '\n' + (c - 'A');
            }
            else {
                if (c == ';') {
                    break;
                }
                this.fatal("P-050");
            }
        }
        return retval;
    }
    
    private int surrogatesToCharTmp(int ucs4) throws SAXException {
        if (ucs4 <= 65535) {
            if (XmlChars.isChar(ucs4)) {
                this.charTmp[0] = (char)ucs4;
                return 1;
            }
        }
        else if (ucs4 <= 1114111) {
            ucs4 -= 65536;
            this.charTmp[0] = (char)(0xD800 | (ucs4 >> 10 & 0x3FF));
            this.charTmp[1] = (char)(0xDC00 | (ucs4 & 0x3FF));
            return 2;
        }
        this.fatal("P-051", new Object[] { Integer.toHexString(ucs4) });
        return -1;
    }
    
    private void expandEntityInContent(final ElementDecl element, final String name, final ElementValidator validator) throws SAXException, IOException {
        final Object entity = this.entities.get(name);
        final InputEntity last = this.in;
        if (entity == null) {
            this.fatal("P-014", new Object[] { name });
        }
        if (entity instanceof InternalEntity) {
            final InternalEntity e = (InternalEntity)entity;
            if (this.isValidating && this.isStandalone && !e.isFromInternalSubset) {
                this.error("V-002", new Object[] { name });
            }
            this.pushReader(e.buf, name, true);
            this.content(element, true, validator);
            if (this.in != last && !this.in.isEOF()) {
                while (this.in.isInternal()) {
                    this.in = this.in.pop();
                }
                this.fatal("P-052", new Object[] { name });
            }
            this.lexicalHandler.endEntity(name);
            this.in = this.in.pop();
        }
        else {
            if (!(entity instanceof ExternalEntity)) {
                throw new InternalError(name);
            }
            final ExternalEntity e2 = (ExternalEntity)entity;
            if (e2.notation != null) {
                this.fatal("P-053", new Object[] { name });
            }
            if (this.isValidating && this.isStandalone && !e2.isFromInternalSubset) {
                this.error("V-002", new Object[] { name });
            }
            this.externalParsedEntity(element, e2, validator);
        }
    }
    
    private boolean maybePEReference() throws IOException, SAXException {
        if (!this.in.peekc('%')) {
            return false;
        }
        final String name = this.maybeGetName();
        if (name == null) {
            this.fatal("P-011");
        }
        this.nextChar(';', "F-021", name);
        final Object entity = this.params.get(name);
        if (entity instanceof InternalEntity) {
            final InternalEntity value = (InternalEntity)entity;
            this.pushReader(value.buf, name, false);
        }
        else if (entity instanceof ExternalEntity) {
            this.externalParameterEntity((ExternalEntity)entity);
        }
        else if (entity == null) {
            this.ignoreDeclarations = true;
            if (this.isValidating) {
                this.error("V-022", new Object[] { name });
            }
            else {
                this.warning("V-022", new Object[] { name });
            }
        }
        return true;
    }
    
    private boolean maybeEntityDecl() throws IOException, SAXException {
        final InputEntity start = this.peekDeclaration("!ENTITY");
        if (start == null) {
            return false;
        }
        this.doLexicalPE = false;
        this.whitespace("F-005");
        SimpleHashtable defns;
        if (this.in.peekc('%')) {
            this.whitespace("F-006");
            defns = this.params;
        }
        else {
            defns = this.entities;
        }
        this.ungetc();
        this.doLexicalPE = true;
        String entityName = this.getMarkupDeclname("F-017", false);
        this.whitespace("F-007");
        final ExternalEntity externalId = this.maybeExternalID();
        boolean doStore = defns.get(entityName) == null;
        if (!doStore && defns == this.entities) {
            this.warning("P-054", new Object[] { entityName });
        }
        doStore &= !this.ignoreDeclarations;
        if (externalId == null) {
            this.doLexicalPE = false;
            this.parseLiteral(true);
            this.doLexicalPE = true;
            if (doStore) {
                final char[] value = new char[this.strTmp.length()];
                if (value.length != 0) {
                    this.strTmp.getChars(0, value.length, value, 0);
                }
                final InternalEntity entity = new InternalEntity(entityName, value);
                entity.isPE = (defns == this.params);
                entity.isFromInternalSubset = !this.inExternalPE;
                defns.put(entityName, entity);
                if (defns == this.params) {
                    entityName = "%" + entityName;
                }
                this.declHandler.internalEntityDecl(entityName, new String(value));
            }
        }
        else {
            if (defns == this.entities && this.maybeWhitespace() && this.peek("NDATA")) {
                externalId.notation = this.getMarkupDeclname("F-018", false);
                if (this.isValidating && this.notations.get(externalId.notation) == null) {
                    this.notations.put(externalId.notation, Boolean.TRUE);
                }
            }
            externalId.name = entityName;
            externalId.isPE = (defns == this.params);
            externalId.isFromInternalSubset = !this.inExternalPE;
            if (doStore) {
                defns.put(entityName, externalId);
                if (externalId.notation != null) {
                    this.dtdHandler.unparsedEntityDecl(entityName, externalId.publicId, externalId.systemId, externalId.notation);
                }
                else {
                    if (defns == this.params) {
                        entityName = "%" + entityName;
                    }
                    this.declHandler.externalEntityDecl(entityName, externalId.publicId, externalId.systemId);
                }
            }
        }
        this.maybeWhitespace();
        this.nextChar('>', "F-031", entityName);
        if (this.isValidating && start != this.in) {
            this.error("V-013", null);
        }
        return true;
    }
    
    private ExternalEntity maybeExternalID() throws IOException, SAXException {
        String temp = null;
        if (this.peek("PUBLIC")) {
            this.whitespace("F-009");
            temp = this.parsePublicId();
        }
        else if (!this.peek("SYSTEM")) {
            return null;
        }
        final ExternalEntity retval = new ExternalEntity(this.in);
        retval.publicId = temp;
        this.whitespace("F-008");
        retval.verbatimSystemId = this.getQuotedString("F-034", null);
        retval.systemId = this.resolveURI(retval.verbatimSystemId);
        return retval;
    }
    
    private String parseSystemId() throws IOException, SAXException {
        final String uri = this.getQuotedString("F-034", null);
        return this.resolveURI(uri);
    }
    
    private String resolveURI(String uri) throws SAXException {
        final int temp = uri.indexOf(58);
        if (temp == -1 || uri.indexOf(47) < temp) {
            String baseURI = this.in.getSystemId();
            if (baseURI == null) {
                this.fatal("P-055", new Object[] { uri });
            }
            if (uri.length() == 0) {
                uri = ".";
            }
            baseURI = baseURI.substring(0, baseURI.lastIndexOf(47) + 1);
            if (uri.charAt(0) != '/') {
                uri = baseURI + uri;
            }
            else {
                final int colonIndex = baseURI.indexOf(58);
                String baseURIScheme;
                if (colonIndex == -1) {
                    baseURIScheme = "file:";
                }
                else {
                    baseURIScheme = baseURI.substring(0, colonIndex + 1);
                }
                uri = baseURIScheme + uri;
            }
        }
        if (uri.indexOf(35) != -1) {
            this.error("P-056", new Object[] { uri });
        }
        return uri;
    }
    
    private void maybeTextDecl() throws IOException, SAXException {
        if (!this.in.isXmlDeclOrTextDeclPrefix()) {
            return;
        }
        this.peek("<?xml");
        this.readVersion(false, "1.0");
        this.readEncoding(true);
        this.maybeWhitespace();
        if (!this.peek("?>")) {
            this.fatal("P-057");
        }
    }
    
    private boolean externalParsedEntity(final ElementDecl element, final ExternalEntity next, final ElementValidator validator) throws IOException, SAXException {
        if (!this.pushReader(next)) {
            if (!this.isInAttribute) {
                this.lexicalHandler.endEntity(next.name);
            }
            return false;
        }
        this.maybeTextDecl();
        this.content(element, true, validator);
        if (!this.in.isEOF()) {
            this.fatal("P-058", new Object[] { next.name });
        }
        this.in = this.in.pop();
        if (!this.isInAttribute) {
            this.lexicalHandler.endEntity(next.name);
        }
        return true;
    }
    
    private void externalParameterEntity(final ExternalEntity next) throws IOException, SAXException {
        if (this.isStandalone && this.fastStandalone) {
            return;
        }
        this.inExternalPE = true;
        try {
            this.pushReader(next);
        }
        catch (IOException e) {
            this.fatal("P-082", new Object[] { next.systemId }, e);
        }
        final InputEntity pe = this.in;
        try {
            this.maybeTextDecl();
        }
        catch (IOException e) {
            this.in = this.in.pop();
            this.fatal("P-082", new Object[] { next.systemId }, e);
        }
        while (!pe.isEOF()) {
            if (this.in.isEOF()) {
                this.in = this.in.pop();
            }
            else {
                this.doLexicalPE = false;
                if (this.maybeWhitespace()) {
                    continue;
                }
                if (this.maybePEReference()) {
                    continue;
                }
                this.doLexicalPE = true;
                if (this.maybeMarkupDecl() || this.maybeConditionalSect()) {
                    continue;
                }
                break;
            }
        }
        if (!pe.isEOF()) {
            this.fatal("P-059", new Object[] { this.in.getName() });
        }
        this.in = this.in.pop();
        this.inExternalPE = !this.in.isDocument();
        this.doLexicalPE = false;
    }
    
    private void readEncoding(final boolean must) throws IOException, SAXException {
        final String name = this.maybeReadAttribute("encoding", must);
        if (name == null) {
            return;
        }
        for (int i = 0; i < name.length(); ++i) {
            final char c = name.charAt(i);
            if (c < 'A' || c > 'Z') {
                if (c < 'a' || c > 'z') {
                    if (i != 0) {
                        if ((c >= '0' && c <= '9') || c == '-' || c == '_') {
                            continue;
                        }
                        if (c == '.') {
                            continue;
                        }
                    }
                    this.fatal("P-060", new Object[] { new Character(c) });
                }
            }
        }
        final String currentEncoding = this.in.getEncoding();
        if (currentEncoding != null && !name.equalsIgnoreCase(currentEncoding)) {
            this.warning("P-061", new Object[] { name, currentEncoding });
        }
    }
    
    private boolean maybeNotationDecl() throws IOException, SAXException {
        final InputEntity start = this.peekDeclaration("!NOTATION");
        if (start == null) {
            return false;
        }
        final String name = this.getMarkupDeclname("F-019", false);
        final ExternalEntity entity = new ExternalEntity(this.in);
        this.whitespace("F-011");
        if (this.peek("PUBLIC")) {
            this.whitespace("F-009");
            entity.publicId = this.parsePublicId();
            if (this.maybeWhitespace()) {
                if (!this.peek(">")) {
                    entity.systemId = this.parseSystemId();
                }
                else {
                    this.ungetc();
                }
            }
        }
        else if (this.peek("SYSTEM")) {
            this.whitespace("F-008");
            entity.systemId = this.parseSystemId();
        }
        else {
            this.fatal("P-062");
        }
        this.maybeWhitespace();
        this.nextChar('>', "F-032", name);
        if (this.isValidating && start != this.in) {
            this.error("V-013", null);
        }
        if (entity.systemId != null && entity.systemId.indexOf(35) != -1) {
            this.error("P-056", new Object[] { entity.systemId });
        }
        final Object value = this.notations.get(name);
        if (value != null && value instanceof ExternalEntity) {
            this.warning("P-063", new Object[] { name });
        }
        else if (!this.ignoreDeclarations) {
            this.notations.put(name, entity);
            this.dtdHandler.notationDecl(name, entity.publicId, entity.systemId);
        }
        return true;
    }
    
    private char getc() throws IOException, SAXException {
        if (!this.inExternalPE || !this.doLexicalPE) {
            final char c = this.in.getc();
            if (c == '%' && this.doLexicalPE) {
                this.fatal("P-080");
            }
            return c;
        }
        while (this.in.isEOF()) {
            if (this.in.isInternal() || (this.doLexicalPE && !this.in.isDocument())) {
                this.in = this.in.pop();
            }
            else {
                this.fatal("P-064", new Object[] { this.in.getName() });
            }
        }
        char c;
        if ((c = this.in.getc()) == '%' && this.doLexicalPE) {
            final String name = this.maybeGetName();
            if (name == null) {
                this.fatal("P-011");
            }
            this.nextChar(';', "F-021", name);
            final Object entity = this.params.get(name);
            this.pushReader(" ".toCharArray(), null, false);
            if (entity instanceof InternalEntity) {
                this.pushReader(((InternalEntity)entity).buf, name, false);
            }
            else if (entity instanceof ExternalEntity) {
                this.pushReader((ExternalEntity)entity);
            }
            else {
                if (entity != null) {
                    throw new InternalError();
                }
                this.fatal("V-022");
            }
            this.pushReader(" ".toCharArray(), null, false);
            return this.in.getc();
        }
        return c;
    }
    
    private void ungetc() {
        this.in.ungetc();
    }
    
    private boolean peek(final String s) throws IOException, SAXException {
        return this.in.peek(s, null);
    }
    
    private InputEntity peekDeclaration(final String s) throws IOException, SAXException {
        if (!this.in.peekc('<')) {
            return null;
        }
        final InputEntity start = this.in;
        if (this.in.peek(s, null)) {
            return start;
        }
        this.in.ungetc();
        return null;
    }
    
    private void nextChar(final char c, final String location, final String near) throws IOException, SAXException {
        while (this.in.isEOF() && !this.in.isDocument()) {
            this.in = this.in.pop();
        }
        if (!this.in.peekc(c)) {
            this.fatal("P-008", new Object[] { new Character(c), Parser2.messages.getMessage(this.locale, location), (near == null) ? "" : ('\"' + near + '\"') });
        }
    }
    
    private void pushReader(final char[] buf, final String name, final boolean isGeneral) throws SAXException {
        if (isGeneral && !this.isInAttribute) {
            this.lexicalHandler.startEntity(name);
        }
        final InputEntity r = InputEntity.getInputEntity(this.errHandler, this.locale);
        r.init(buf, name, this.in, !isGeneral);
        this.in = r;
    }
    
    private boolean pushReader(final ExternalEntity next) throws SAXException, IOException {
        if (!next.isPE && !this.isInAttribute) {
            this.lexicalHandler.startEntity(next.name);
        }
        final InputEntity r = InputEntity.getInputEntity(this.errHandler, this.locale);
        final InputSource s = next.getInputSource(this.resolver);
        r.init(s, next.name, this.in, next.isPE);
        this.in = r;
        return true;
    }
    
    private void warning(final String messageId, final Object[] parameters) throws SAXException {
        final SAXParseException x = new SAXParseException(Parser2.messages.getMessage(this.locale, messageId, parameters), this.locator);
        this.errHandler.warning(x);
    }
    
    void error(final String messageId, final Object[] parameters) throws SAXException {
        final SAXParseException x = new SAXParseException(Parser2.messages.getMessage(this.locale, messageId, parameters), this.locator);
        this.errHandler.error(x);
    }
    
    private void fatal(final String message) throws SAXException {
        this.fatal(message, null, null);
    }
    
    private void fatal(final String message, final Object[] parameters) throws SAXException {
        this.fatal(message, parameters, null);
    }
    
    private void fatal(final String messageId, final Object[] parameters, final Exception e) throws SAXException {
        final SAXParseException x = new SAXParseException(Parser2.messages.getMessage(this.locale, messageId, parameters), this.locator, e);
        this.errHandler.fatalError(x);
        throw x;
    }
    
    static {
        nullHandler = new NullHandler(null);
        messages = new Catalog();
    }
    
    class DocLocator implements Locator
    {
        public String getPublicId() {
            return (Parser2.this.in == null) ? null : Parser2.this.in.getPublicId();
        }
        
        public String getSystemId() {
            return (Parser2.this.in == null) ? null : Parser2.this.in.getSystemId();
        }
        
        public int getLineNumber() {
            return (Parser2.this.in == null) ? -1 : Parser2.this.in.getLineNumber();
        }
        
        public int getColumnNumber() {
            return (Parser2.this.in == null) ? -1 : Parser2.this.in.getColumnNumber();
        }
    }
    
    static class NameCache
    {
        NameCacheEntry[] hashtable;
        
        NameCache() {
            this.hashtable = new NameCacheEntry[541];
        }
        
        String lookup(final char[] value, final int len) {
            return this.lookupEntry(value, len).name;
        }
        
        NameCacheEntry lookupEntry(final char[] value, final int len) {
            int index = 0;
            for (int i = 0; i < len; ++i) {
                index = index * 31 + value[i];
            }
            index &= Integer.MAX_VALUE;
            index %= this.hashtable.length;
            for (NameCacheEntry entry = this.hashtable[index]; entry != null; entry = entry.next) {
                if (entry.matches(value, len)) {
                    return entry;
                }
            }
            NameCacheEntry entry = new NameCacheEntry();
            System.arraycopy(value, 0, entry.chars = new char[len], 0, len);
            entry.name = new String(entry.chars);
            entry.name = entry.name.intern();
            entry.next = this.hashtable[index];
            return this.hashtable[index] = entry;
        }
    }
    
    static class NameCacheEntry
    {
        String name;
        char[] chars;
        NameCacheEntry next;
        
        boolean matches(final char[] value, final int len) {
            if (this.chars.length != len) {
                return false;
            }
            for (int i = 0; i < len; ++i) {
                if (value[i] != this.chars[i]) {
                    return false;
                }
            }
            return true;
        }
    }
    
    static final class Catalog extends MessageCatalog
    {
        static /* synthetic */ Class class$org$apache$crimson$parser$Parser2;
        
        Catalog() {
            super((Catalog.class$org$apache$crimson$parser$Parser2 == null) ? (Catalog.class$org$apache$crimson$parser$Parser2 = class$("org.apache.crimson.parser.Parser2")) : Catalog.class$org$apache$crimson$parser$Parser2);
        }
        
        static /* synthetic */ Class class$(final String x0) {
            try {
                return Class.forName(x0);
            }
            catch (ClassNotFoundException x) {
                throw new NoClassDefFoundError(x.getMessage());
            }
        }
    }
    
    private static class NullHandler extends DefaultHandler implements LexicalHandler, DeclHandler
    {
        public void startDTD(final String name, final String publicId, final String systemId) {
        }
        
        public void endDTD() {
        }
        
        public void startEntity(final String name) {
        }
        
        public void endEntity(final String name) {
        }
        
        public void startCDATA() {
        }
        
        public void endCDATA() {
        }
        
        public void comment(final char[] ch, final int start, final int length) {
        }
        
        public void elementDecl(final String name, final String model) {
        }
        
        public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) {
        }
        
        public void internalEntityDecl(final String name, final String value) {
        }
        
        public void externalEntityDecl(final String name, final String publicId, final String systemId) {
        }
    }
}
