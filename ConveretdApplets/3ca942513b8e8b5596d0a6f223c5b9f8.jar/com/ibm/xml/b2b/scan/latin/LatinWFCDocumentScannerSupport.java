// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.ErrorReporter;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.scan.WFCDocumentScannerSupport;

public class LatinWFCDocumentScannerSupport extends WFCDocumentScannerSupport
{
    public LatinWFCDocumentScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer, final ErrorReporter errorReporter) {
        super(symbolTable, xmlStringBuffer, errorReporter);
    }
    
    public final int scanQName(final ParsedEntity parsedEntity, final QName qName) {
        final byte[] nameCharMap = DocumentEntityState.nameCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        int sepOffset = -1;
        final byte b = bytes[offset];
        if (b >= 0) {
            if ((nameCharMap[b] & 0x1) == 0x0) {
                return 0;
            }
            ++offset;
        }
        else {
            if (!parsedEntity.skipInitialNameCharacter()) {
                return 0;
            }
            offset = parsedEntity.offset;
        }
        int n;
        while (true) {
            final byte b2 = bytes[offset];
            if (b2 >= 0) {
                n = nameCharMap[b2];
                if ((n & 0x2) != 0x0) {
                    ++offset;
                }
                else {
                    if (n != 4 || sepOffset != -1) {
                        break;
                    }
                    sepOffset = offset++;
                    final byte b3 = bytes[offset];
                    if (b3 >= 0) {
                        if ((nameCharMap[b3] & 0x1) == 0x0) {
                            offset = sepOffset;
                            sepOffset = -1;
                            n = 8;
                            break;
                        }
                        ++offset;
                    }
                    else {
                        parsedEntity.offset = offset;
                        if (!parsedEntity.skipInitialNameCharacter()) {
                            offset = sepOffset;
                            sepOffset = -1;
                            n = 8;
                            break;
                        }
                        offset = parsedEntity.offset;
                    }
                }
            }
            else {
                parsedEntity.offset = offset;
                if (!parsedEntity.skipNameCharacter()) {
                    n = 8;
                    break;
                }
                offset = parsedEntity.offset;
            }
        }
        qName.setValues(bytes, offset2, offset, parsedEntity.encoding);
        if (sepOffset == -1) {
            super.fSymbolTable.addSymbol(qName);
            qName.prefix = XMLString.EMPTY_STRING;
            qName.localPart = qName.str;
            qName.localHandle = qName.handle;
        }
        else {
            qName.sepOffset = sepOffset;
            super.fSymbolTable.addQNameSymbols(qName);
        }
        parsedEntity.offset = offset;
        return n >> 2;
    }
    
    public final int scanName(final ParsedEntity parsedEntity, final XMLName xmlName) {
        final byte[] nameCharMap = DocumentEntityState.nameCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset;
        int n = offset = parsedEntity.offset;
        final byte b = bytes[n];
        if (b >= 0) {
            if ((nameCharMap[b] & 0x1) == 0x0) {
                return 0;
            }
            ++n;
        }
        else {
            if (!parsedEntity.skipInitialNameCharacter()) {
                return 0;
            }
            n = parsedEntity.offset;
        }
        int n2;
        while (true) {
            final byte b2 = bytes[n];
            if (b2 >= 0) {
                n2 = nameCharMap[b2];
                if ((n2 & 0x2) == 0x0) {
                    break;
                }
                ++n;
            }
            else {
                parsedEntity.offset = n;
                if (!parsedEntity.skipNameCharacter()) {
                    n2 = 8;
                    break;
                }
                n = parsedEntity.offset;
            }
        }
        xmlName.setValues(bytes, offset, n, parsedEntity.encoding);
        super.fSymbolTable.addSymbol(xmlName);
        parsedEntity.offset = n;
        return n2 >> 2;
    }
    
    public final boolean scanComment(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        byte b = bytes[offset];
        while (b != 45 || bytes[offset + 1] != 45) {
            if (b >= 0) {
                switch (contentMap[b]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        b = bytes[++offset];
                        continue;
                    }
                    case 5: {
                        if (offset == parsedEntity.endOffset) {
                            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 29);
                            return false;
                        }
                        break;
                    }
                }
                parsedEntity.offset = offset;
                this.setInvalidCharParameter(0, parsedEntity);
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 22);
                return false;
            }
            parsedEntity.offset = offset;
            if (!parsedEntity.skipValidCharacter()) {
                this.setInvalidCharParameter(0, parsedEntity);
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 22);
                return false;
            }
            offset = parsedEntity.offset;
            b = bytes[offset];
        }
        if (bytes[offset + 2] == 62) {
            xmlString.setValues(bytes, offset2, offset, parsedEntity.encoding);
            parsedEntity.offset = offset + 3;
            return true;
        }
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 28);
        return false;
    }
    
    public final boolean scanPITarget(final ParsedEntity parsedEntity, final XMLName xmlName) {
        final byte[] bytes = parsedEntity.bytes;
        final int scanName = this.scanName(parsedEntity, xmlName);
        if (scanName != 5) {
            if (scanName == 2) {
                final int offset = parsedEntity.offset;
                if (bytes[offset] == 63 && bytes[offset + 1] == 62) {
                    if (!reservedPITarget(xmlName)) {
                        return true;
                    }
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 33);
                    return false;
                }
            }
            else if (scanName == 0) {
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 30);
                return false;
            }
            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 31);
            return false;
        }
        if (!reservedPITarget(xmlName)) {
            int offset2 = parsedEntity.offset;
            byte b;
            do {
                b = bytes[++offset2];
            } while (b == 32 || b == 10 || b == 9);
            parsedEntity.offset = offset2;
            return true;
        }
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 33);
        return false;
    }
    
    public final boolean scanPIData(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        byte b = bytes[offset];
        while (b != 63 || bytes[offset + 1] != 62) {
            if (b >= 0) {
                switch (contentMap[b]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        b = bytes[++offset];
                        continue;
                    }
                    case 5: {
                        if (offset == parsedEntity.endOffset) {
                            this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 32);
                            return false;
                        }
                        break;
                    }
                }
                parsedEntity.offset = offset;
                this.setInvalidCharParameter(0, parsedEntity);
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 23);
                return false;
            }
            parsedEntity.offset = offset;
            if (!parsedEntity.skipValidCharacter()) {
                this.setInvalidCharParameter(0, parsedEntity);
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 23);
                return false;
            }
            offset = parsedEntity.offset;
            b = bytes[offset];
        }
        xmlString.setValues(bytes, offset2, offset, parsedEntity.encoding);
        parsedEntity.offset = offset + 2;
        return true;
    }
    
    public final int scanCharacterReference(final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        boolean b = false;
        final byte b2 = bytes[offset];
        int n;
        if (b2 == 120) {
            final byte b3 = bytes[++offset];
            byte b4;
            if (b3 >= 48 && b3 <= 57) {
                b4 = (byte)(b3 - 48);
            }
            else if (b3 >= 97 && b3 <= 102) {
                b4 = (byte)(10 + b3 - 97);
            }
            else {
                if (b3 < 65 || b3 > 70) {
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 36);
                    return -1;
                }
                b4 = (byte)(10 + b3 - 65);
            }
            n = b4;
            while (true) {
                final byte b5 = bytes[++offset];
                if (b5 == 59) {
                    ++offset;
                    break;
                }
                byte b6;
                if (b5 >= 48 && b5 <= 57) {
                    b6 = (byte)(b5 - 48);
                }
                else if (b5 >= 97 && b5 <= 102) {
                    b6 = (byte)(10 + b5 - 97);
                }
                else {
                    if (b5 < 65 || b5 > 70) {
                        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 37);
                        return -1;
                    }
                    b6 = (byte)(10 + b5 - 65);
                }
                n = (n << 4) + b6;
                if (n <= 1114111) {
                    continue;
                }
                b = true;
                n = 0;
            }
        }
        else {
            if (b2 < 48 || b2 > 57) {
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 35);
                return -1;
            }
            n = b2 - 48;
            while (true) {
                final byte b7 = bytes[++offset];
                if (b7 == 59) {
                    ++offset;
                    break;
                }
                if (b7 < 48 || b7 > 57) {
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 37);
                    return -1;
                }
                n = n * 10 + (b7 - 48);
                if (n <= 1114111) {
                    continue;
                }
                b = true;
                n = 0;
            }
        }
        if (!b) {
            boolean b8;
            if (n < 55296) {
                b8 = (n < 32 && n != 10 && n != 9 && n != 13);
            }
            else {
                b8 = (n < 57344 || (n >= 65534 && n < 65536) || n >= 1114112);
            }
            if (!b8) {
                parsedEntity.offset = offset;
                return n;
            }
        }
        super.content.setValues(bytes, offset2, offset, parsedEntity.encoding);
        this.setParameter(0, super.content);
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 38);
        return -1;
    }
    
    public final int checkPredefinedEntities(final XMLName xmlName) {
        final byte[] bytes = xmlName.bytes;
        final int offset = xmlName.offset;
        switch (bytes[offset] + (xmlName.endOffset - offset)) {
            case 110: {
                if (bytes[offset + 1] == 116) {
                    return 60;
                }
                break;
            }
            case 105: {
                if (bytes[offset + 1] == 116) {
                    return 62;
                }
                break;
            }
            case 100: {
                if (bytes[offset + 1] == 109 && bytes[offset + 2] == 112) {
                    return 38;
                }
                break;
            }
            case 101: {
                if (bytes[offset + 1] == 112 && bytes[offset + 2] == 111 && bytes[offset + 3] == 115) {
                    return 39;
                }
                break;
            }
            case 117: {
                if (bytes[offset + 1] == 117 && bytes[offset + 2] == 111 && bytes[offset + 3] == 116) {
                    return 34;
                }
                break;
            }
        }
        return -1;
    }
    
    private static boolean reservedPITarget(final XMLName xmlName) {
        final int offset = xmlName.offset;
        if (offset + 3 == xmlName.endOffset) {
            final byte[] bytes = xmlName.bytes;
            return (bytes[offset + 0] == 120 || bytes[offset + 0] == 88) && (bytes[offset + 1] == 109 || bytes[offset + 1] == 77) && (bytes[offset + 2] == 108 || bytes[offset + 2] == 76);
        }
        return false;
    }
}
