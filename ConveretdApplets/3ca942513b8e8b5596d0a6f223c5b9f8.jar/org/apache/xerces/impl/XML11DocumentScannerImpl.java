// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XML11Char;
import org.apache.xerces.util.XMLChar;
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
        XMLString xmlString = this.fString;
        int scanContent = super.fEntityScanner.scanContent(xmlString);
        if (scanContent == 13 || scanContent == 133 || scanContent == 8232) {
            super.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer.append((char)scanContent);
            xmlString = this.fStringBuffer;
            scanContent = -1;
        }
        if (super.fDocumentHandler != null && xmlString.length > 0) {
            super.fDocumentHandler.characters(xmlString, null);
        }
        if (scanContent == 93 && this.fString.length == 0) {
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
            scanContent = -1;
        }
        return scanContent;
    }
    
    protected boolean scanAttributeValue(final XMLString values, final XMLString xmlString, final String s, final boolean b, final String s2) throws IOException, XNIException {
        final int peekChar = super.fEntityScanner.peekChar();
        if (peekChar != 39 && peekChar != 34) {
            this.reportFatalError("OpenQuoteExpected", new Object[] { s2, s });
        }
        super.fEntityScanner.scanChar();
        final int fEntityDepth = super.fEntityDepth;
        int n = super.fEntityScanner.scanLiteral(peekChar, values);
        int unchangedByNormalization = 0;
        if (n == peekChar && (unchangedByNormalization = this.isUnchangedByNormalization(values)) == -1) {
            xmlString.setValues(values);
            if (super.fEntityScanner.scanChar() != peekChar) {
                this.reportFatalError("CloseQuoteExpected", new Object[] { s2, s });
            }
            return true;
        }
        this.fStringBuffer2.clear();
        this.fStringBuffer2.append(values);
        this.normalizeWhitespace(values, unchangedByNormalization);
        if (n != peekChar) {
            super.fScanningAttribute = true;
            this.fStringBuffer.clear();
            do {
                this.fStringBuffer.append(values);
                if (n == 38) {
                    super.fEntityScanner.skipChar(38);
                    if (fEntityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append('&');
                    }
                    if (super.fEntityScanner.skipChar(35)) {
                        if (fEntityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append('#');
                        }
                        if (this.scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2) != -1) {}
                    }
                    else {
                        final String scanName = super.fEntityScanner.scanName();
                        if (scanName == null) {
                            this.reportFatalError("NameRequiredInReference", null);
                        }
                        else if (fEntityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append(scanName);
                        }
                        if (!super.fEntityScanner.skipChar(59)) {
                            this.reportFatalError("SemicolonRequiredInReference", new Object[] { scanName });
                        }
                        else if (fEntityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append(';');
                        }
                        if (scanName == XMLScanner.fAmpSymbol) {
                            this.fStringBuffer.append('&');
                        }
                        else if (scanName == XMLScanner.fAposSymbol) {
                            this.fStringBuffer.append('\'');
                        }
                        else if (scanName == XMLScanner.fLtSymbol) {
                            this.fStringBuffer.append('<');
                        }
                        else if (scanName == XMLScanner.fGtSymbol) {
                            this.fStringBuffer.append('>');
                        }
                        else if (scanName == XMLScanner.fQuotSymbol) {
                            this.fStringBuffer.append('\"');
                        }
                        else if (super.fEntityManager.isExternalEntity(scanName)) {
                            this.reportFatalError("ReferenceToExternalEntity", new Object[] { scanName });
                        }
                        else {
                            if (!super.fEntityManager.isDeclaredEntity(scanName)) {
                                if (b) {
                                    if (super.fValidation) {
                                        super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[] { scanName }, (short)1);
                                    }
                                }
                                else {
                                    this.reportFatalError("EntityNotDeclared", new Object[] { scanName });
                                }
                            }
                            super.fEntityManager.startEntity(scanName, true);
                        }
                    }
                }
                else if (n == 60) {
                    this.reportFatalError("LessthanInAttValue", new Object[] { s2, s });
                    super.fEntityScanner.scanChar();
                    if (fEntityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)n);
                    }
                }
                else if (n == 37 || n == 93) {
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char)n);
                    if (fEntityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)n);
                    }
                }
                else if (n == 10 || n == 13 || n == 133 || n == 8232) {
                    super.fEntityScanner.scanChar();
                    this.fStringBuffer.append(' ');
                    if (fEntityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append('\n');
                    }
                }
                else if (n != -1 && XMLChar.isHighSurrogate(n)) {
                    this.fStringBuffer3.clear();
                    if (this.scanSurrogates(this.fStringBuffer3)) {
                        this.fStringBuffer.append(this.fStringBuffer3);
                        if (fEntityDepth == super.fEntityDepth) {
                            this.fStringBuffer2.append(this.fStringBuffer3);
                        }
                    }
                }
                else if (n != -1 && this.isInvalidLiteral(n)) {
                    this.reportFatalError("InvalidCharInAttValue", new Object[] { s2, s, Integer.toString(n, 16) });
                    super.fEntityScanner.scanChar();
                    if (fEntityDepth == super.fEntityDepth) {
                        this.fStringBuffer2.append((char)n);
                    }
                }
                n = super.fEntityScanner.scanLiteral(peekChar, values);
                if (fEntityDepth == super.fEntityDepth) {
                    this.fStringBuffer2.append(values);
                }
                this.normalizeWhitespace(values);
            } while (n != peekChar || fEntityDepth != super.fEntityDepth);
            this.fStringBuffer.append(values);
            values.setValues(this.fStringBuffer);
            super.fScanningAttribute = false;
        }
        xmlString.setValues(this.fStringBuffer2);
        if (super.fEntityScanner.scanChar() != peekChar) {
            this.reportFatalError("CloseQuoteExpected", new Object[] { s2, s });
        }
        return xmlString.equals(values.ch, values.offset, values.length);
    }
    
    protected boolean scanPubidLiteral(final XMLString xmlString) throws IOException, XNIException {
        final int scanChar = super.fEntityScanner.scanChar();
        if (scanChar != 39 && scanChar != 34) {
            this.reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }
        this.fStringBuffer.clear();
        int n = 1;
        boolean b = true;
        while (true) {
            final int scanChar2 = super.fEntityScanner.scanChar();
            if (scanChar2 == 32 || scanChar2 == 10 || scanChar2 == 13 || scanChar2 == 133 || scanChar2 == 8232) {
                if (n != 0) {
                    continue;
                }
                this.fStringBuffer.append(' ');
                n = 1;
            }
            else {
                if (scanChar2 == scanChar) {
                    if (n != 0) {
                        final XMLStringBuffer fStringBuffer = this.fStringBuffer;
                        --fStringBuffer.length;
                    }
                    xmlString.setValues(this.fStringBuffer);
                    return b;
                }
                if (XMLChar.isPubid(scanChar2)) {
                    this.fStringBuffer.append((char)scanChar2);
                    n = 0;
                }
                else {
                    if (scanChar2 == -1) {
                        this.reportFatalError("PublicIDUnterminated", null);
                        return false;
                    }
                    b = false;
                    this.reportFatalError("InvalidCharInPublicID", new Object[] { Integer.toHexString(scanChar2) });
                }
            }
        }
    }
    
    protected void normalizeWhitespace(final XMLString xmlString) {
        for (int n = xmlString.offset + xmlString.length, i = xmlString.offset; i < n; ++i) {
            if (XMLChar.isSpace(xmlString.ch[i])) {
                xmlString.ch[i] = ' ';
            }
        }
    }
    
    protected void normalizeWhitespace(final XMLString xmlString, final int n) {
        for (int n2 = xmlString.offset + xmlString.length, i = xmlString.offset + n; i < n2; ++i) {
            if (XMLChar.isSpace(xmlString.ch[i])) {
                xmlString.ch[i] = ' ';
            }
        }
    }
    
    protected int isUnchangedByNormalization(final XMLString xmlString) {
        for (int n = xmlString.offset + xmlString.length, i = xmlString.offset; i < n; ++i) {
            if (XMLChar.isSpace(xmlString.ch[i])) {
                return i - xmlString.offset;
            }
        }
        return -1;
    }
    
    protected boolean isInvalid(final int n) {
        return XML11Char.isXML11Invalid(n);
    }
    
    protected boolean isInvalidLiteral(final int n) {
        return !XML11Char.isXML11ValidLiteral(n);
    }
    
    protected boolean isValidNameChar(final int n) {
        return XML11Char.isXML11Name(n);
    }
    
    protected boolean isValidNameStartChar(final int n) {
        return XML11Char.isXML11NameStart(n);
    }
    
    protected boolean isValidNCName(final int n) {
        return XML11Char.isXML11NCName(n);
    }
    
    protected boolean isValidNameStartHighSurrogate(final int n) {
        return XML11Char.isXML11NameHighSurrogate(n);
    }
    
    protected boolean versionSupported(final String s) {
        return s.equals("1.1") || s.equals("1.0");
    }
    
    protected String getVersionNotSupportedKey() {
        return "VersionNotSupported11";
    }
}
