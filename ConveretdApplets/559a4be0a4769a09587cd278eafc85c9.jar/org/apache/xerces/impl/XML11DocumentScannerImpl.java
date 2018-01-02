// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;

public class XML11DocumentScannerImpl extends XMLDocumentScannerImpl
{
    private String[] fStrings;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private XMLStringBuffer fStringBuffer3;
    
    public XML11DocumentScannerImpl() {
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
    }
    
    protected int scanContent() throws IOException, XNIException {
        XMLString content = this.fString;
        int c = super.fEntityScanner.scanContent(content);
        if (c == 13 || c == 133 || c == 8232) {
            super.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer.append((char)c);
            content = this.fStringBuffer;
            c = -1;
        }
        if (super.fDocumentHandler != null && content.length > 0) {
            super.fDocumentHandler.characters(content, null);
        }
        if (c == 93 && this.fString.length == 0) {
            this.fStringBuffer.clear();
            this.fStringBuffer.append((char)super.fEntityScanner.scanChar());
            super.fInScanContent = true;
            if (super.fEntityScanner.skipChar(93)) {
                this.fStringBuffer.append(']');
                while (super.fEntityScanner.skipChar(93)) {
                    this.fStringBuffer.append(']');
                }
                if (super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("CDEndInContent", null);
                }
            }
            if (super.fDocumentHandler != null && this.fStringBuffer.length != 0) {
                super.fDocumentHandler.characters(this.fStringBuffer, null);
            }
            super.fInScanContent = false;
            c = -1;
        }
        return c;
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
        boolean sawSpace = super.fEntityScanner.skipSpaces();
        while (super.fEntityScanner.peekChar() != 63) {
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
                        if (!version.equals("1.0") || !version.equals("1.1")) {
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
            sawSpace = super.fEntityScanner.skipSpaces();
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
        if (!super.fEntityScanner.skipChar(63)) {
            this.reportFatalError("XMLDeclUnterminated", null);
        }
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("XMLDeclUnterminated", null);
        }
        pseudoAttributeValues[0] = version;
        pseudoAttributeValues[1] = encoding;
        pseudoAttributeValues[2] = standalone;
    }
    
    protected void scanAttributeValue(final XMLString value, final XMLString nonNormalizedValue, final String atName, final XMLAttributes attributes, final int attrIndex, final boolean checkEntities) throws IOException, XNIException {
        final int quote = super.fEntityScanner.peekChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError("OpenQuoteExpected", new Object[] { atName });
        }
        super.fEntityScanner.scanChar();
        final int entityDepth = super.fEntityDepth;
        int c = super.fEntityScanner.scanLiteral(quote, value);
        this.fStringBuffer2.clear();
        this.fStringBuffer2.append(value);
        this.normalizeWhitespace(value);
        if (c != quote) {
            super.fScanningAttribute = true;
            this.fStringBuffer.clear();
            do {
                this.fStringBuffer.append(value);
                if (c == 38) {
                    super.fEntityScanner.skipChar(38);
                    if (entityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append('&');
                    }
                    if (super.fEntityScanner.skipChar(35)) {
                        if (entityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append('#');
                        }
                        final int ch = this.scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                        if (ch != -1) {}
                    }
                    else {
                        final String entityName = super.fEntityScanner.scanName();
                        if (entityName == null) {
                            this.reportFatalError("NameRequiredInReference", null);
                        }
                        else if (entityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append(entityName);
                        }
                        if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInReference", new Object[] { entityName });
                        }
                        else if (entityDepth == super.fEntityDepth) {
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
                        else if (super.fEntityManager.isExternalEntity(entityName)) {
                            this.reportFatalError("ReferenceToExternalEntity", new Object[] { entityName });
                        }
                        else {
                            if (!super.fEntityManager.isDeclaredEntity(entityName)) {
                                if (checkEntities) {
                                    if (super.fValidation) {
                                        super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { entityName }, (short)1);
                                    }
                                }
                                else {
                                    this.reportFatalError("EntityNotDeclared", new Object[] { entityName });
                                }
                            }
                            super.fEntityManager.startEntity(entityName, true);
                        }
                    }
                }
                else if (c == 60) {
                    this.reportFatalError("LessthanInAttValue", new Object[] { null, atName });
                    super.fEntityScanner.scanChar();
                    if (entityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                else if (c == 37 || c == 93) {
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char)c);
                    if (entityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                else if (c == 10 || c == 13 || c == 133 || c == 8232) {
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append(' ');
                    if (entityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append('\n');
                    }
                }
                else if (c != -1 && XMLChar.isHighSurrogate(c)) {
                    if (this.scanSurrogates(this.fStringBuffer3)) {
                        this.fStringBuffer.append(this.fStringBuffer3);
                        if (entityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append(this.fStringBuffer3);
                        }
                    }
                }
                else if (c != -1 && XMLChar.isInvalid(c)) {
                    this.reportFatalError("InvalidCharInAttValue", new Object[] { Integer.toString(c, 16) });
                    super.fEntityScanner.scanChar();
                    if (entityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)c);
                    }
                }
                c = super.fEntityScanner.scanLiteral(quote, value);
                if (entityDepth == super.fEntityDepth) {
                    this.fStringBuffer2.append(value);
                }
                this.normalizeWhitespace(value);
            } while (c != quote || entityDepth != super.fEntityDepth);
            this.fStringBuffer.append(value);
            value.setValues(this.fStringBuffer);
            super.fScanningAttribute = false;
        }
        nonNormalizedValue.setValues(this.fStringBuffer2);
        final int cquote = super.fEntityScanner.scanChar();
        if (cquote != quote) {
            this.reportFatalError("CloseQuoteExpected", new Object[] { atName });
        }
    }
    
    protected boolean scanPubidLiteral(final XMLString literal) throws IOException, XNIException {
        final int quote = super.fEntityScanner.scanChar();
        if (quote != 39 && quote != 34) {
            this.reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }
        this.fStringBuffer.clear();
        boolean skipSpace = true;
        boolean dataok = true;
        while (true) {
            final int c = super.fEntityScanner.scanChar();
            if (c == 32 || c == 10 || c == 13 || c == 133 || c == 8232) {
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
            if (XMLChar.isXML11Space(c)) {
                value.ch[i] = ' ';
            }
        }
    }
}
