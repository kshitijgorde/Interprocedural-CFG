// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.scan.DocumentScannerSupport;

public final class UTF16DocumentScannerSupport extends DocumentScannerSupport
{
    public UTF16DocumentScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer) {
        super(symbolTable, xmlStringBuffer);
    }
    
    public int checkPredefinedEntities(final XMLName xmlName) {
        switch (xmlName.chars[xmlName.offset] + (xmlName.endOffset - xmlName.offset)) {
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
        throw new RuntimeException("UTF16DocumentScannerSupport#scanQName()");
    }
    
    public int scanName(final ParsedEntity parsedEntity, final XMLName xmlName) {
        throw new RuntimeException("UTF16DocumentScannerSupport#scanName()");
    }
    
    public boolean scanComment(final ParsedEntity parsedEntity, final XMLString xmlString) {
        throw new RuntimeException("UTF16DocumentScannerSupport#scanComment()");
    }
    
    public boolean scanPITarget(final ParsedEntity parsedEntity, final XMLName xmlName) {
        throw new RuntimeException("UTF16DocumentScannerSupport#scanPITarget()");
    }
    
    public boolean scanPIData(final ParsedEntity parsedEntity, final XMLString xmlString) {
        throw new RuntimeException("UTF16DocumentScannerSupport#scanPIData()");
    }
    
    public int scanCharacterReference(final ParsedEntity parsedEntity) {
        throw new RuntimeException("UTF16DocumentScannerSupport#scanCharacterReference()");
    }
    
    public void setParameter(final int n, final XMLString xmlString) {
        throw new RuntimeException("UTF16DocumentScannerSupport#setParameter()");
    }
    
    public void setInvalidCharParameter(final int n, final ParsedEntity parsedEntity) {
        throw new RuntimeException("UTF16DocumentScannerSupport#setInvalidCharParameter()");
    }
    
    public void reportFatalError(final String s, final int n) {
        throw new RuntimeException("UTF16DocumentScannerSupport#reportFatalError()");
    }
}
