// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.ErrorReporter;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.util.QName;

public final class LatinWFCScannerSupport extends LatinWFCDocumentScannerSupport
{
    private QName fCurrentElementType;
    private QName fCurrentAttributeName;
    private int fEntityDepth;
    private static final byte[] fgNOTATION;
    private static final byte[] pubidCharMap;
    
    public LatinWFCScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer, final ErrorReporter errorReporter) {
        super(symbolTable, xmlStringBuffer, errorReporter);
        this.fCurrentElementType = new QName();
        this.fCurrentAttributeName = new QName();
    }
    
    public void reset(final boolean b) {
        super.reset(b);
        this.fCurrentElementType.clear();
        this.fCurrentAttributeName.clear();
        this.fEntityDepth = 0;
    }
    
    public boolean entityReferenceInDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final XMLName xmlName) {
        ++this.fEntityDepth;
        if (!markupDeclHandler.entityReferenceInDefaultAttValue(xmlName)) {
            return false;
        }
        --this.fEntityDepth;
        return true;
    }
    
    public void startDefaultAttValue(final QName values, final QName values2) {
        this.fCurrentElementType.setValues(values);
        this.fCurrentAttributeName.setValues(values2);
    }
    
    public void setAttValueErrorParameters() {
        this.setParameter(0, this.fCurrentElementType);
        this.setParameter(1, this.fCurrentAttributeName);
    }
    
    public int scanNmtoken(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] nameCharMap = DocumentEntityState.nameCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int endOffset = parsedEntity.offset;
        xmlString.offset = endOffset;
        final byte b = bytes[endOffset];
        if (b >= 0) {
            final byte b2 = nameCharMap[b];
            if ((b2 & 0x2) == 0x0 && b2 != 4) {
                return 0;
            }
            ++endOffset;
        }
        else {
            if (!parsedEntity.skipNameCharacter()) {
                return 0;
            }
            endOffset = parsedEntity.offset;
        }
        while (true) {
            final byte b3 = bytes[endOffset];
            if (b3 >= 0) {
                final byte b4 = nameCharMap[b3];
                if ((b4 & 0x2) == 0x0 && b4 != 4) {
                    xmlString.endOffset = endOffset;
                    parsedEntity.offset = endOffset;
                    return b4 >> 2;
                }
                ++endOffset;
            }
            else {
                parsedEntity.offset = endOffset;
                if (!parsedEntity.skipNameCharacter()) {
                    xmlString.endOffset = endOffset;
                    return 2;
                }
                endOffset = parsedEntity.offset;
            }
        }
    }
    
    public boolean scanPublicID(final ParsedEntity parsedEntity, final int n, final XMLString xmlString) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        xmlString.offset = offset;
        byte b = bytes[offset];
        while (b >= 0) {
            switch (LatinWFCScannerSupport.pubidCharMap[b]) {
                case 1: {
                    b = bytes[++offset];
                    continue;
                }
                case 2: {
                    if (b == n) {
                        xmlString.endOffset = offset;
                        parsedEntity.offset = offset + 1;
                        return true;
                    }
                    if (b == 39) {
                        b = bytes[++offset];
                        continue;
                    }
                }
                case 3: {
                    parsedEntity.offset = offset;
                    this.setInvalidCharParameter(0, parsedEntity);
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 12);
                    return false;
                }
                case 4: {
                    if (offset == parsedEntity.endOffset) {
                        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 11);
                        return false;
                    }
                    break;
                }
            }
            parsedEntity.offset = offset;
            this.setInvalidCharParameter(0, parsedEntity);
            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 3);
            return false;
        }
        parsedEntity.offset = offset;
        this.setInvalidCharParameter(0, parsedEntity);
        if (parsedEntity.skipValidCharacter()) {
            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 12);
            return false;
        }
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 3);
        return false;
    }
    
    public boolean scanSystemID(final ParsedEntity parsedEntity, final int n, final XMLString xmlString) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final byte[] bytes = parsedEntity.bytes;
        int n2 = parsedEntity.offset;
        boolean b = false;
        xmlString.offset = n2;
        byte b2 = bytes[n2];
        Label_0230: {
        Label_0180:
            while (true) {
                if (b2 >= 0) {
                    switch (attValueMap[b2]) {
                        case 1: {
                            if (b2 == 35) {
                                b = true;
                            }
                        }
                        case 2:
                        case 3:
                        case 6: {
                            b2 = bytes[++n2];
                            continue;
                        }
                        case 4: {
                            if (b2 == n) {
                                xmlString.endOffset = n2;
                                parsedEntity.offset = n2 + 1;
                                if (b) {
                                    this.setParameter(0, xmlString);
                                    this.reportRecoverableError("http://www.w3.org/TR/2000/REC-xml-20001006", 63);
                                }
                                return true;
                            }
                            b2 = bytes[++n2];
                            continue;
                        }
                        case 5: {
                            if (n2 == parsedEntity.endOffset) {
                                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 9);
                                return false;
                            }
                            break Label_0180;
                        }
                        default: {
                            break Label_0180;
                        }
                    }
                }
                else {
                    parsedEntity.offset = n2;
                    if (!parsedEntity.skipValidCharacter()) {
                        break Label_0230;
                    }
                    n2 = parsedEntity.offset;
                    b2 = bytes[n2];
                }
            }
            parsedEntity.offset = n2;
            this.setInvalidCharParameter(0, parsedEntity);
            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 2);
            return false;
        }
        this.setInvalidCharParameter(0, parsedEntity);
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 2);
        return false;
    }
    
    public boolean scanPEReference(final ParsedEntity parsedEntity, final XMLName xmlName) {
        final int scanName = this.scanName(parsedEntity, xmlName);
        if (scanName == 7) {
            ++parsedEntity.offset;
            return true;
        }
        if (scanName == 0) {
            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 48);
            return false;
        }
        this.setParameter(0, xmlName);
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 49);
        return false;
    }
    
    public boolean isNotationType(final XMLString xmlString) {
        int i = xmlString.offset;
        final int endOffset = xmlString.endOffset;
        if (i + 8 == endOffset) {
            final byte[] bytes = xmlString.bytes;
            int n = 0;
            while (i < endOffset) {
                if (bytes[i++] != LatinWFCScannerSupport.fgNOTATION[n++]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    static {
        fgNOTATION = new byte[] { 78, 79, 84, 65, 84, 73, 79, 78 };
        pubidCharMap = new byte[] { 4, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3 };
    }
}
