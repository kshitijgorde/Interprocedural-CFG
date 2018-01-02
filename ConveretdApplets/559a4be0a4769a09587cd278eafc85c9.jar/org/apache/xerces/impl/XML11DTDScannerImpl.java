// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;

public class XML11DTDScannerImpl extends XMLDTDScannerImpl
{
    private String[] fStrings;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private XMLStringBuffer fStringBuffer3;
    
    public XML11DTDScannerImpl() {
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
    }
    
    public XML11DTDScannerImpl(final SymbolTable symbolTable, final XMLErrorReporter errorReporter, final XMLEntityManager entityManager) {
        super(symbolTable, errorReporter, entityManager);
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
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
