// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.scan.DocumentScannerSupport;

public final class LatinDocumentScannerSupport extends DocumentScannerSupport
{
    public LatinDocumentScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer) {
        super(symbolTable, xmlStringBuffer);
    }
    
    public int checkPredefinedEntities(final XMLName xmlName) {
        switch (xmlName.bytes[xmlName.offset] + (xmlName.endOffset - xmlName.offset)) {
            case 110: {
                return 60;
            }
            case 105: {
                return 62;
            }
            case 100: {
                return 38;
            }
            case 101: {
                return 39;
            }
            case 117: {
                return 34;
            }
            default: {
                return -1;
            }
        }
    }
    
    public int scanQName(final ParsedEntity parsedEntity, final QName qName) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanQName()");
    }
    
    public int scanName(final ParsedEntity parsedEntity, final XMLName xmlName) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanName()");
    }
    
    public boolean scanComment(final ParsedEntity parsedEntity, final XMLString xmlString) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanComment()");
    }
    
    public boolean scanPITarget(final ParsedEntity parsedEntity, final XMLName xmlName) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanPITarget()");
    }
    
    public boolean scanPIData(final ParsedEntity parsedEntity, final XMLString xmlString) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanPIData()");
    }
    
    public int scanCharacterReference(final ParsedEntity parsedEntity) {
        throw new RuntimeException("LatinDocumentScannerSupport#scanCharacterReference()");
    }
    
    public void setParameter(final int n, final XMLString xmlString) {
        throw new RuntimeException("LatinDocumentScannerSupport#setParameter()");
    }
    
    public void setInvalidCharParameter(final int n, final ParsedEntity parsedEntity) {
        throw new RuntimeException("LatinDocumentScannerSupport#setInvalidCharParameter()");
    }
    
    public void reportFatalError(final String s, final int n) {
        throw new RuntimeException("LatinDocumentScannerSupport#reportFatalError()");
    }
}
