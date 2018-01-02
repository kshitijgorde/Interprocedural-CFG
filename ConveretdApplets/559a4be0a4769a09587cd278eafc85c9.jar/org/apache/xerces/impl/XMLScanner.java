// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLComponent;

public abstract class XMLScanner implements XMLComponent
{
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;
    protected boolean fValidation;
    protected boolean fNotifyCharRefs;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityManager fEntityManager;
    protected XMLEntityScanner fEntityScanner;
    protected int fEntityDepth;
    protected String fCharRefLiteral;
    protected boolean fScanningAttribute;
    protected boolean fReportEntity;
    protected static final String fVersionSymbol;
    protected static final String fEncodingSymbol;
    protected static final String fStandaloneSymbol;
    protected static final String fAmpSymbol;
    protected static final String fLtSymbol;
    protected static final String fGtSymbol;
    protected static final String fQuotSymbol;
    protected static final String fAposSymbol;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private XMLStringBuffer fStringBuffer3;
    protected XMLResourceIdentifierImpl fResourceIdentifier;
    
    public XMLScanner() {
        this.fValidation = false;
        this.fNotifyCharRefs = false;
        this.fCharRefLiteral = null;
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fSymbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter)componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntityManager = (XMLEntityManager)componentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
        this.init();
        try {
            this.fValidation = componentManager.getFeature("http://xml.org/sax/features/validation");
        }
        catch (XMLConfigurationException e) {
            this.fValidation = false;
        }
        try {
            this.fNotifyCharRefs = componentManager.getFeature("http://apache.org/xml/features/scanner/notify-char-refs");
        }
        catch (XMLConfigurationException e) {
            this.fNotifyCharRefs = false;
        }
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/symbol-table")) {
                this.fSymbolTable = (SymbolTable)value;
            }
            else if (property.equals("internal/error-reporter")) {
                this.fErrorReporter = (XMLErrorReporter)value;
            }
            else if (property.equals("internal/entity-manager")) {
                this.fEntityManager = (XMLEntityManager)value;
            }
        }
    }
    
    public void setFeature(final String featureId, final boolean value) throws XMLConfigurationException {
        if ("http://xml.org/sax/features/validation".equals(featureId)) {
            this.fValidation = value;
        }
        else if ("http://apache.org/xml/features/scanner/notify-char-refs".equals(featureId)) {
            this.fNotifyCharRefs = value;
        }
    }
    
    public boolean getFeature(final String featureId) throws XMLConfigurationException {
        if ("http://xml.org/sax/features/validation".equals(featureId)) {
            return this.fValidation;
        }
        if ("http://apache.org/xml/features/scanner/notify-char-refs".equals(featureId)) {
            return this.fNotifyCharRefs;
        }
        throw new XMLConfigurationException((short)0, featureId);
    }
    
    protected void reset() {
        this.init();
        this.fValidation = true;
        this.fNotifyCharRefs = false;
    }
    
    protected void scanXMLDeclOrTextDecl(final boolean scanningTextDecl, final String[] pseudoAttributeValues) throws IOException, XNIException {
        String version = null;
        String encoding = null;
        String standalone = null;
        final int STATE_VERSION = 0;
        final int STATE_ENCODING = 1;
        final int STATE_STANDALONE = 2;
        final int STATE_DONE = 3;
        int state = 0;
        boolean dataFoundForTarget = false;
        boolean sawSpace = this.fEntityScanner.skipSpaces();
        while (this.fEntityScanner.peekChar() != 63) {
            dataFoundForTarget = true;
            final String name = this.scanPseudoAttribute(scanningTextDecl, this.fString);
            switch (state) {
                case 0: {
                    if (name == XMLScanner.fVersionSymbol) {
                        if (!sawSpace) {
                            this.reportFatalError(scanningTextDecl ? "SpaceRequiredBeforeVersionInTextDecl" : "SpaceRequiredBeforeVersionInXMLDecl", null);
                        }
                        version = this.fString.toString();
                        state = 1;
                        if (!version.equals("1.0")) {
                            this.reportFatalError("VersionNotSupported", new Object[] { version });
                            break;
                        }
                        break;
                    }
                    else {
                        if (name == XMLScanner.fEncodingSymbol) {
                            if (!scanningTextDecl) {
                                this.reportFatalError("VersionInfoRequired", null);
                            }
                            if (!sawSpace) {
                                this.reportFatalError(scanningTextDecl ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                            }
                            encoding = this.fString.toString();
                            state = (scanningTextDecl ? 3 : 2);
                            break;
                        }
                        if (scanningTextDecl) {
                            this.reportFatalError("EncodingDeclRequired", null);
                            break;
                        }
                        this.reportFatalError("VersionInfoRequired", null);
                        break;
                    }
                    break;
                }
                case 1: {
                    if (name == XMLScanner.fEncodingSymbol) {
                        if (!sawSpace) {
                            this.reportFatalError(scanningTextDecl ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                        }
                        encoding = this.fString.toString();
                        state = (scanningTextDecl ? 3 : 2);
                        break;
                    }
                    if (scanningTextDecl || name != XMLScanner.fStandaloneSymbol) {
                        this.reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        this.reportFatalError("SpaceRequiredBeforeStandalone", null);
                    }
                    standalone = this.fString.toString();
                    state = 3;
                    if (!standalone.equals("yes") && !standalone.equals("no")) {
                        this.reportFatalError("SDDeclInvalid", null);
                        break;
                    }
                    break;
                }
                case 2: {
                    if (name != XMLScanner.fStandaloneSymbol) {
                        this.reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        this.reportFatalError("SpaceRequiredBeforeStandalone", null);
                    }
                    standalone = this.fString.toString();
                    state = 3;
                    if (!standalone.equals("yes") && !standalone.equals("no")) {
                        this.reportFatalError("SDDeclInvalid", null);
                        break;
                    }
                    break;
                }
                default: {
                    this.reportFatalError("NoMorePseudoAttributes", null);
                    break;
                }
            }
            sawSpace = this.fEntityScanner.skipSpaces();
        }
        if (scanningTextDecl && state != 3) {
            this.reportFatalError("MorePseudoAttributes", null);
        }
        if (scanningTextDecl) {
            if (!dataFoundForTarget && encoding == null) {
                this.reportFatalError("EncodingDeclRequired", null);
            }
        }
        else if (!dataFoundForTarget && version == null) {
            this.reportFatalError("VersionInfoRequired", null);
        }
        if (!this.fEntityScanner.skipChar(63)) {
            this.reportFatalError("XMLDeclUnterminated", null);
        }
        if (!this.fEntityScanner.skipChar(62)) {
            this.reportFatalError("XMLDeclUnterminated", null);
        }
        pseudoAttributeValues[0] = version;
        pseudoAttributeValues[1] = encoding;
        pseudoAttributeValues[2] = standalone;
    }
    
    public String scanPseudoAttribute(final boolean scanningTextDecl, final XMLString value) throws IOException, XNIException {
        final String name = this.fEntityScanner.scanName();
        if (name == null) {
            this.reportFatalError("PseudoAttrNameExpected", null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            this.reportFatalError(scanningTextDecl ? "EqRequiredInTextDecl" : "EqRequiredInXMLDecl", new Object[] { name });
        }
        this.fEntityScanner.skipSpaces();
        final int quote = this.fEntityScanner.peekChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError(scanningTextDecl ? "QuoteRequiredInTextDecl" : "QuoteRequiredInXMLDecl", new Object[] { name });
        }
        this.fEntityScanner.scanChar();
        int c = this.fEntityScanner.scanLiteral(quote, value);
        if (c != quote) {
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer2.append(value);
                if (c != -1) {
                    if (c == 38 || c == 37 || c == 60 || c == 93) {
                        this.fStringBuffer2.append((char)this.fEntityScanner.scanChar());
                    }
                    else if (XMLChar.isHighSurrogate(c)) {
                        this.scanSurrogates(this.fStringBuffer2);
                    }
                    else if (XMLChar.isInvalid(c)) {
                        final String key = scanningTextDecl ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl";
                        this.reportFatalError(key, new Object[] { Integer.toString(c, 16) });
                        this.fEntityScanner.scanChar();
                    }
                }
                c = this.fEntityScanner.scanLiteral(quote, value);
            } while (c != quote);
            this.fStringBuffer2.append(value);
            value.setValues(this.fStringBuffer2);
        }
        if (!this.fEntityScanner.skipChar(quote)) {
            this.reportFatalError(scanningTextDecl ? "CloseQuoteMissingInTextDecl" : "CloseQuoteMissingInXMLDecl", new Object[] { name });
        }
        return name;
    }
    
    protected void scanPI() throws IOException, XNIException {
        this.fReportEntity = false;
        final String target = this.fEntityScanner.scanName();
        if (target == null) {
            this.reportFatalError("PITargetRequired", null);
        }
        this.scanPIData(target, this.fString);
        this.fReportEntity = true;
    }
    
    protected void scanPIData(final String target, final XMLString data) throws IOException, XNIException {
        if (target.length() == 3) {
            final char c0 = Character.toLowerCase(target.charAt(0));
            final char c2 = Character.toLowerCase(target.charAt(1));
            final char c3 = Character.toLowerCase(target.charAt(2));
            if (c0 == 'x' && c2 == 'm' && c3 == 'l') {
                this.reportFatalError("ReservedPITarget", null);
            }
        }
        if (!this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipString("?>")) {
                data.clear();
                return;
            }
            this.reportFatalError("SpaceRequiredInPI", null);
        }
        this.fStringBuffer.clear();
        if (this.fEntityScanner.scanData("?>", this.fStringBuffer)) {
            do {
                final int c4 = this.fEntityScanner.peekChar();
                if (c4 != -1) {
                    if (XMLChar.isHighSurrogate(c4)) {
                        this.scanSurrogates(this.fStringBuffer);
                    }
                    else {
                        if (!XMLChar.isInvalid(c4)) {
                            continue;
                        }
                        this.reportFatalError("InvalidCharInPI", new Object[] { Integer.toHexString(c4) });
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanData("?>", this.fStringBuffer));
        }
        data.setValues(this.fStringBuffer);
    }
    
    protected void scanComment(final XMLStringBuffer text) throws IOException, XNIException {
        text.clear();
        while (this.fEntityScanner.scanData("--", text)) {
            final int c = this.fEntityScanner.peekChar();
            if (c != -1) {
                if (XMLChar.isHighSurrogate(c)) {
                    this.scanSurrogates(text);
                }
                if (!XMLChar.isInvalid(c)) {
                    continue;
                }
                this.reportFatalError("InvalidCharInComment", new Object[] { Integer.toHexString(c) });
                this.fEntityScanner.scanChar();
            }
        }
        if (!this.fEntityScanner.skipChar(62)) {
            this.reportFatalError("DashDashInComment", null);
        }
    }
    
    protected void scanAttributeValue(final XMLString value, final XMLString nonNormalizedValue, final String atName, final XMLAttributes attributes, final int attrIndex, final boolean checkEntities) throws IOException, XNIException {
        final int quote = this.fEntityScanner.peekChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError("OpenQuoteExpected", new Object[] { atName });
        }
        this.fEntityScanner.scanChar();
        final int entityDepth = this.fEntityDepth;
        int c = this.fEntityScanner.scanLiteral(quote, value);
        this.fStringBuffer2.clear();
        this.fStringBuffer2.append(value);
        this.normalizeWhitespace(value);
        if (c != quote) {
            this.fScanningAttribute = true;
            this.fStringBuffer.clear();
            do {
                this.fStringBuffer.append(value);
                if (c == 38) {
                    this.fEntityScanner.skipChar(38);
                    if (entityDepth == this.fEntityDepth) {
                        this.fStringBuffer2.append('&');
                    }
                    if (this.fEntityScanner.skipChar(35)) {
                        if (entityDepth == this.fEntityDepth) {
                            this.fStringBuffer2.append('#');
                        }
                        final int ch = this.scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                        if (ch != -1) {}
                    }
                    else {
                        final String entityName = this.fEntityScanner.scanName();
                        if (entityName == null) {
                            this.reportFatalError("NameRequiredInReference", null);
                        }
                        else if (entityDepth == this.fEntityDepth) {
                            this.fStringBuffer2.append(entityName);
                        }
                        if (!this.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInReference", new Object[] { entityName });
                        }
                        else if (entityDepth == this.fEntityDepth) {
                            this.fStringBuffer2.append(';');
                        }
                        if (entityName == XMLScanner.fAmpSymbol) {
                            this.fStringBuffer.append('&');
                        }
                        else if (entityName == XMLScanner.fAposSymbol) {
                            this.fStringBuffer.append('\'');
                        }
                        else if (entityName == XMLScanner.fLtSymbol) {
                            this.fStringBuffer.append('<');
                        }
                        else if (entityName == XMLScanner.fGtSymbol) {
                            this.fStringBuffer.append('>');
                        }
                        else if (entityName == XMLScanner.fQuotSymbol) {
                            this.fStringBuffer.append('\"');
                        }
                        else if (this.fEntityManager.isExternalEntity(entityName)) {
                            this.reportFatalError("ReferenceToExternalEntity", new Object[] { entityName });
                        }
                        else {
                            if (!this.fEntityManager.isDeclaredEntity(entityName)) {
                                if (checkEntities) {
                                    if (this.fValidation) {
                                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { entityName }, (short)1);
                                    }
                                }
                                else {
                                    this.reportFatalError("EntityNotDeclared", new Object[] { entityName });
                                }
                            }
                            this.fEntityManager.startEntity(entityName, true);
                        }
                    }
                }
                else if (c == 60) {
                    this.reportFatalError("LessthanInAttValue", new Object[] { null, atName });
                    this.fEntityScanner.scanChar();
                    if (entityDepth == this.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                else if (c == 37 || c == 93) {
                    this.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char)c);
                    if (entityDepth == this.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                else if (c == 10 || c == 13) {
                    this.fEntityScanner.scanChar();
                    this.fStringBuffer.append(' ');
                    if (entityDepth == this.fEntityDepth) {
                        this.fStringBuffer2.append('\n');
                    }
                }
                else if (c != -1 && XMLChar.isHighSurrogate(c)) {
                    if (this.scanSurrogates(this.fStringBuffer3)) {
                        this.fStringBuffer.append(this.fStringBuffer3);
                        if (entityDepth == this.fEntityDepth) {
                            this.fStringBuffer2.append(this.fStringBuffer3);
                        }
                    }
                }
                else if (c != -1 && XMLChar.isInvalid(c)) {
                    this.reportFatalError("InvalidCharInAttValue", new Object[] { Integer.toString(c, 16) });
                    this.fEntityScanner.scanChar();
                    if (entityDepth == this.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                c = this.fEntityScanner.scanLiteral(quote, value);
                if (entityDepth == this.fEntityDepth) {
                    this.fStringBuffer2.append(value);
                }
                this.normalizeWhitespace(value);
            } while (c != quote || entityDepth != this.fEntityDepth);
            this.fStringBuffer.append(value);
            value.setValues(this.fStringBuffer);
            this.fScanningAttribute = false;
        }
        nonNormalizedValue.setValues(this.fStringBuffer2);
        final int cquote = this.fEntityScanner.scanChar();
        if (cquote != quote) {
            this.reportFatalError("CloseQuoteExpected", new Object[] { atName });
        }
    }
    
    protected void scanExternalID(final String[] identifiers, final boolean optionalSystemId) throws IOException, XNIException {
        String systemId = null;
        String publicId = null;
        if (this.fEntityScanner.skipString("PUBLIC")) {
            if (!this.fEntityScanner.skipSpaces()) {
                this.reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            this.scanPubidLiteral(this.fString);
            publicId = this.fString.toString();
            if (!this.fEntityScanner.skipSpaces() && !optionalSystemId) {
                this.reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
        }
        if (publicId != null || this.fEntityScanner.skipString("SYSTEM")) {
            if (publicId == null && !this.fEntityScanner.skipSpaces()) {
                this.reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            final int quote = this.fEntityScanner.peekChar();
            if (quote != 39 && quote != 34) {
                if (publicId != null && optionalSystemId) {
                    identifiers[0] = null;
                    identifiers[1] = publicId;
                    return;
                }
                this.reportFatalError("QuoteRequiredInSystemID", null);
            }
            this.fEntityScanner.scanChar();
            XMLString ident = this.fString;
            if (this.fEntityScanner.scanLiteral(quote, ident) != quote) {
                this.fStringBuffer.clear();
                do {
                    this.fStringBuffer.append(ident);
                    final int c = this.fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(c) || c == 93) {
                        this.fStringBuffer.append((char)this.fEntityScanner.scanChar());
                    }
                } while (this.fEntityScanner.scanLiteral(quote, ident) != quote);
                this.fStringBuffer.append(ident);
                ident = this.fStringBuffer;
            }
            systemId = ident.toString();
            if (!this.fEntityScanner.skipChar(quote)) {
                this.reportFatalError("SystemIDUnterminated", null);
            }
        }
        identifiers[0] = systemId;
        identifiers[1] = publicId;
    }
    
    protected boolean scanPubidLiteral(final XMLString literal) throws IOException, XNIException {
        final int quote = this.fEntityScanner.scanChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }
        this.fStringBuffer.clear();
        boolean skipSpace = true;
        boolean dataok = true;
        while (true) {
            final int c = this.fEntityScanner.scanChar();
            if (c == 32 || c == 10 || c == 13) {
                if (skipSpace) {
                    continue;
                }
                this.fStringBuffer.append(' ');
                skipSpace = true;
            }
            else {
                if (c == quote) {
                    if (skipSpace) {
                        final XMLStringBuffer fStringBuffer = this.fStringBuffer;
                        --fStringBuffer.length;
                    }
                    literal.setValues(this.fStringBuffer);
                    return dataok;
                }
                if (XMLChar.isPubid(c)) {
                    this.fStringBuffer.append((char)c);
                    skipSpace = false;
                }
                else {
                    if (c == -1) {
                        this.reportFatalError("PublicIDUnterminated", null);
                        return false;
                    }
                    dataok = false;
                    this.reportFatalError("InvalidCharInPublicID", new Object[] { Integer.toHexString(c) });
                }
            }
        }
    }
    
    protected void normalizeWhitespace(final XMLString value) {
        for (int end = value.offset + value.length, i = value.offset; i < end; ++i) {
            final int c = value.ch[i];
            if (XMLChar.isSpace(c)) {
                value.ch[i] = ' ';
            }
        }
    }
    
    public void startEntity(final String name, final XMLResourceIdentifier identifier, final String encoding) throws XNIException {
        ++this.fEntityDepth;
    }
    
    public void endEntity(final String name) throws XNIException {
        --this.fEntityDepth;
    }
    
    protected int scanCharReferenceValue(final XMLStringBuffer buf, final XMLStringBuffer buf2) throws IOException, XNIException {
        boolean hex = false;
        if (this.fEntityScanner.skipChar(120)) {
            if (buf2 != null) {
                buf2.append('x');
            }
            hex = true;
            this.fStringBuffer3.clear();
            boolean digit = true;
            do {
                final int c = this.fEntityScanner.peekChar();
                digit = ((c >= 48 && c <= 57) || (c >= 97 && c <= 102) || (c >= 65 && c <= 70));
                if (digit) {
                    if (buf2 != null) {
                        buf2.append((char)c);
                    }
                    this.fEntityScanner.scanChar();
                    this.fStringBuffer3.append((char)c);
                }
            } while (digit);
        }
        else {
            this.fStringBuffer3.clear();
            boolean digit = true;
            do {
                final int c = this.fEntityScanner.peekChar();
                digit = (c >= 48 && c <= 57);
                if (digit) {
                    if (buf2 != null) {
                        buf2.append((char)c);
                    }
                    this.fEntityScanner.scanChar();
                    this.fStringBuffer3.append((char)c);
                }
            } while (digit);
        }
        if (!this.fEntityScanner.skipChar(59)) {
            this.reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (buf2 != null) {
            buf2.append(';');
        }
        int value = -1;
        try {
            value = Integer.parseInt(this.fStringBuffer3.toString(), hex ? 16 : 10);
        }
        catch (NumberFormatException ex) {}
        if (!XMLChar.isValid(value)) {
            this.reportFatalError("InvalidCharRef", new Object[] { Integer.toString(value, 16) });
        }
        if (!XMLChar.isSupplemental(value)) {
            buf.append((char)value);
        }
        else {
            buf.append(XMLChar.highSurrogate(value));
            buf.append(XMLChar.lowSurrogate(value));
        }
        if (this.fNotifyCharRefs && value != -1) {
            final String literal = "#" + (hex ? "x" : "") + this.fStringBuffer3.toString();
            if (!this.fScanningAttribute) {
                this.fCharRefLiteral = literal;
            }
        }
        return value;
    }
    
    protected boolean scanSurrogates(final XMLStringBuffer buf) throws IOException, XNIException {
        final int high = this.fEntityScanner.scanChar();
        final int low = this.fEntityScanner.peekChar();
        if (!XMLChar.isLowSurrogate(low)) {
            this.reportFatalError("InvalidCharInContent", new Object[] { Integer.toString(high, 16) });
            return false;
        }
        this.fEntityScanner.scanChar();
        final int c = XMLChar.supplemental((char)high, (char)low);
        if (!XMLChar.isValid(c)) {
            this.reportFatalError("InvalidCharInContent", new Object[] { Integer.toString(c, 16) });
            return false;
        }
        buf.append((char)high);
        buf.append((char)low);
        return true;
    }
    
    protected void reportFatalError(final String msgId, final Object[] args) throws XNIException {
        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", msgId, args, (short)2);
    }
    
    private void init() {
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityDepth = 0;
        this.fReportEntity = true;
        this.fResourceIdentifier.clear();
    }
    
    public abstract Object getPropertyDefault(final String p0);
    
    public abstract Boolean getFeatureDefault(final String p0);
    
    public abstract String[] getRecognizedProperties();
    
    public abstract String[] getRecognizedFeatures();
    
    static {
        fVersionSymbol = "version".intern();
        fEncodingSymbol = "encoding".intern();
        fStandaloneSymbol = "standalone".intern();
        fAmpSymbol = "amp".intern();
        fLtSymbol = "lt".intern();
        fGtSymbol = "gt".intern();
        fQuotSymbol = "quot".intern();
        fAposSymbol = "apos".intern();
    }
}
