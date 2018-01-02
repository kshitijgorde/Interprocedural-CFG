// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.ErrorReporter;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.scan.WFCDocumentScannerSupport;

public class SingleByteWFCDocumentScannerSupport extends WFCDocumentScannerSupport
{
    public SingleByteWFCDocumentScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer, final ErrorReporter errorReporter) {
        super(symbolTable, xmlStringBuffer, errorReporter);
    }
    
    public final int scanQName(final ParsedEntity parsedEntity, final QName qName) {
        final byte[] nameCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).nameCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        int sepOffset = -1;
        if ((nameCharMap[bytes[offset] & 0xFF] & 0x1) != 0x0) {
            ++offset;
            int n;
            while (true) {
                n = nameCharMap[bytes[offset] & 0xFF];
                if ((n & 0x2) != 0x0) {
                    ++offset;
                }
                else {
                    if (n != 4 || sepOffset != -1) {
                        break;
                    }
                    sepOffset = offset++;
                    if ((nameCharMap[bytes[offset] & 0xFF] & 0x1) == 0x0) {
                        offset = sepOffset;
                        sepOffset = -1;
                        n = 8;
                        break;
                    }
                    ++offset;
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
        return 0;
    }
    
    public final int scanName(final ParsedEntity parsedEntity, final XMLName xmlName) {
        final byte[] nameCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).nameCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        if ((nameCharMap[bytes[offset] & 0xFF] & 0x1) != 0x0) {
            ++offset;
            byte b;
            while (true) {
                b = nameCharMap[bytes[offset] & 0xFF];
                if ((b & 0x2) == 0x0) {
                    break;
                }
                ++offset;
            }
            xmlName.setValues(bytes, offset2, offset, parsedEntity.encoding);
            super.fSymbolTable.addSymbol(xmlName);
            parsedEntity.offset = offset;
            return b >> 2;
        }
        return 0;
    }
    
    public final boolean scanComment(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        char c = byteToCharMap[bytes[offset] & 0xFF];
        while (c != '-' || byteToCharMap[bytes[offset + 1] & 0xFF] != '-') {
            if (c < '\u0080') {
                switch (contentMap[c]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        c = byteToCharMap[bytes[++offset] & 0xFF];
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
            c = byteToCharMap[bytes[offset] & 0xFF];
        }
        if (byteToCharMap[bytes[offset + 2] & 0xFF] == '>') {
            xmlString.setValues(bytes, offset2, offset, parsedEntity.encoding);
            parsedEntity.offset = offset + 3;
            return true;
        }
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 28);
        return false;
    }
    
    public final boolean scanPITarget(final ParsedEntity parsedEntity, final XMLName xmlName) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int scanName = this.scanName(parsedEntity, xmlName);
        if (scanName != 5) {
            if (scanName == 2) {
                final int offset = parsedEntity.offset;
                if (byteToCharMap[bytes[offset] & 0xFF] == '?' && byteToCharMap[bytes[offset + 1] & 0xFF] == '>') {
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
            char c;
            do {
                c = byteToCharMap[bytes[++offset2] & 0xFF];
            } while (c == ' ' || c == '\n' || c == '\t');
            parsedEntity.offset = offset2;
            return true;
        }
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 33);
        return false;
    }
    
    public final boolean scanPIData(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        char c = byteToCharMap[bytes[offset] & 0xFF];
        while (c != '?' || byteToCharMap[bytes[offset + 1] & 0xFF] != '>') {
            if (c < '\u0080') {
                switch (contentMap[c]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        c = byteToCharMap[bytes[++offset] & 0xFF];
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
            c = byteToCharMap[bytes[offset] & 0xFF];
        }
        xmlString.setValues(bytes, offset2, offset, parsedEntity.encoding);
        parsedEntity.offset = offset + 2;
        return true;
    }
    
    public final int scanCharacterReference(final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset2;
        int offset = offset2 = parsedEntity.offset;
        boolean b = false;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        int n;
        if (c == 'x') {
            final char c2 = byteToCharMap[bytes[++offset] & 0xFF];
            char c3;
            if (c2 >= '0' && c2 <= '9') {
                c3 = (char)(c2 - '0');
            }
            else if (c2 >= 'a' && c2 <= 'f') {
                c3 = (char)('\n' + c2 - 'a');
            }
            else {
                if (c2 < 'A' || c2 > 'F') {
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 36);
                    return -1;
                }
                c3 = (char)('\n' + c2 - 'A');
            }
            n = c3;
            while (true) {
                final char c4 = byteToCharMap[bytes[++offset] & 0xFF];
                if (c4 == ';') {
                    ++offset;
                    break;
                }
                char c5;
                if (c4 >= '0' && c4 <= '9') {
                    c5 = (char)(c4 - '0');
                }
                else if (c4 >= 'a' && c4 <= 'f') {
                    c5 = (char)('\n' + c4 - 'a');
                }
                else {
                    if (c4 < 'A' || c4 > 'F') {
                        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 37);
                        return -1;
                    }
                    c5 = (char)('\n' + c4 - 'A');
                }
                n = (n << 4) + c5;
                if (n <= 1114111) {
                    continue;
                }
                b = true;
                n = 0;
            }
        }
        else {
            if (c < '0' || c > '9') {
                this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 35);
                return -1;
            }
            n = c - '0';
            while (true) {
                final char c6 = byteToCharMap[bytes[++offset] & 0xFF];
                if (c6 == ';') {
                    ++offset;
                    break;
                }
                if (c6 < '0' || c6 > '9') {
                    this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 37);
                    return -1;
                }
                n = n * 10 + (c6 - '0');
                if (n <= 1114111) {
                    continue;
                }
                b = true;
                n = 0;
            }
        }
        if (!b) {
            boolean b2;
            if (n < 55296) {
                b2 = (n < 32 && n != 10 && n != 9 && n != 13);
            }
            else {
                b2 = (n < 57344 || (n >= 65534 && n < 65536) || n >= 1114112);
            }
            if (!b2) {
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
        final char[] byteToCharMap = ((SingleByteEncodingSupport)xmlName.encoding).byteToCharMap;
        final byte[] bytes = xmlName.bytes;
        final int offset = xmlName.offset;
        switch (byteToCharMap[bytes[offset] & 0xFF] + (xmlName.endOffset - offset)) {
            case 110: {
                if (byteToCharMap[bytes[offset + 1] & 0xFF] == 't') {
                    return 60;
                }
                break;
            }
            case 105: {
                if (byteToCharMap[bytes[offset + 1] & 0xFF] == 't') {
                    return 62;
                }
                break;
            }
            case 100: {
                if (byteToCharMap[bytes[offset + 1] & 0xFF] == 'm' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'p') {
                    return 38;
                }
                break;
            }
            case 101: {
                if (byteToCharMap[bytes[offset + 1] & 0xFF] == 'p' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'o' && byteToCharMap[bytes[offset + 3] & 0xFF] == 's') {
                    return 39;
                }
                break;
            }
            case 117: {
                if (byteToCharMap[bytes[offset + 1] & 0xFF] == 'u' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'o' && byteToCharMap[bytes[offset + 3] & 0xFF] == 't') {
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
            final char[] byteToCharMap = ((SingleByteEncodingSupport)xmlName.encoding).byteToCharMap;
            final byte[] bytes = xmlName.bytes;
            return (byteToCharMap[bytes[offset + 0] & 0xFF] == 'x' || byteToCharMap[bytes[offset + 0] & 0xFF] == 'X') && (byteToCharMap[bytes[offset + 1] & 0xFF] == 'm' || byteToCharMap[bytes[offset + 1] & 0xFF] == 'M') && (byteToCharMap[bytes[offset + 2] & 0xFF] == 'l' || byteToCharMap[bytes[offset + 2] & 0xFF] == 'L');
        }
        return false;
    }
}
