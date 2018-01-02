// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class SingleByteWFCDoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 14);
            return false;
        }
        final int scanQName = singleByteWFCScannerSupport.scanQName(parsedEntity, rootElementType);
        if (scanQName == 0) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 15);
            return false;
        }
        int n;
        char c;
        if (scanQName == 5) {
            n = parsedEntity.offset;
            do {
                c = byteToCharMap[bytes[++n] & 0xFF];
            } while (c == ' ' || c == '\n' || c == '\t');
            if (c != '[' && c != '>') {
                if (c == 'P' && byteToCharMap[bytes[n + 1] & 0xFF] == 'U' && byteToCharMap[bytes[n + 2] & 0xFF] == 'B' && byteToCharMap[bytes[n + 3] & 0xFF] == 'L' && byteToCharMap[bytes[n + 4] & 0xFF] == 'I' && byteToCharMap[bytes[n + 5] & 0xFF] == 'C') {
                    parsedEntity.offset = n + 6;
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                        return false;
                    }
                    final int offset2 = parsedEntity.offset;
                    final char c2 = byteToCharMap[bytes[offset2] & 0xFF];
                    if (c2 != '\"' && c2 != '\'') {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                        return false;
                    }
                    parsedEntity.offset = offset2 + 1;
                    publicID = dtdParams.getPublicID();
                    if (!singleByteWFCScannerSupport.scanPublicID(parsedEntity, c2, publicID)) {
                        return false;
                    }
                }
                else {
                    if (c != 'S' || byteToCharMap[bytes[n + 1] & 0xFF] != 'Y' || byteToCharMap[bytes[n + 2] & 0xFF] != 'S' || byteToCharMap[bytes[n + 3] & 0xFF] != 'T' || byteToCharMap[bytes[n + 4] & 0xFF] != 'E' || byteToCharMap[bytes[n + 5] & 0xFF] != 'M') {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                        return false;
                    }
                    parsedEntity.offset = n + 6;
                }
                if (!scanRequiredWhitespace(parsedEntity)) {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                    return false;
                }
                final int offset3 = parsedEntity.offset;
                final char c3 = byteToCharMap[bytes[offset3] & 0xFF];
                if (c3 != '\"' && c3 != '\'') {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                    return false;
                }
                parsedEntity.offset = offset3 + 1;
                systemID = dtdParams.getSystemID();
                if (!singleByteWFCScannerSupport.scanSystemID(parsedEntity, c3, systemID)) {
                    return false;
                }
                for (n = parsedEntity.offset, c = byteToCharMap[bytes[n] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
            }
        }
        else {
            n = parsedEntity.offset;
            c = byteToCharMap[bytes[n] & 0xFF];
        }
        final boolean b = c == '[';
        doctypeEventHandler.doctype(rootElementType, publicID, systemID, b);
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        if (b) {
            parsedEntity.offset = n + 1;
            if (doctypeImplementationHandler != null) {
                if (!doctypeImplementationHandler.scanInternalSubset(parsedEntity)) {
                    return false;
                }
            }
            else if (!skipInternalSubset(doctypeEventHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
            for (n = parsedEntity.offset, c = byteToCharMap[bytes[++n] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
        }
        if (c != '>') {
            singleByteWFCScannerSupport.setParameter(0, rootElementType);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
            return false;
        }
        dtdParams.resetRootElementType();
        parsedEntity.offset = n + 1;
        return true;
    }
    
    private static boolean skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        char c = byteToCharMap[bytes[n] & 0xFF];
        while (true) {
            if (c == ' ' || c == '\n' || c == '\t') {
                do {
                    c = byteToCharMap[bytes[++n] & 0xFF];
                } while (c == ' ' || c == '\n' || c == '\t');
            }
            else if (c == '<') {
                final char c2 = byteToCharMap[bytes[++n] & 0xFF];
                if (c2 == '!') {
                    if (byteToCharMap[bytes[++n] & 0xFF] == '-') {
                        for (n += 2; byteToCharMap[bytes[n] & 0xFF] != '-' || byteToCharMap[bytes[n + 1] & 0xFF] != '-'; ++n) {}
                        n += 3;
                    }
                    else {
                        final char c3 = byteToCharMap[bytes[n + 1] & 0xFF];
                        if (c3 == 'L') {
                            n = skipMarkupDecl(byteToCharMap, bytes, n + 7);
                        }
                        else if (c3 == 'T') {
                            n = skipMarkupDecl(byteToCharMap, bytes, n + 7);
                        }
                        else if (c3 == 'N') {
                            n = skipMarkupDecl(byteToCharMap, bytes, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(byteToCharMap, bytes, n + 8);
                        }
                    }
                }
                else {
                    if (c2 != '?') {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    ++n;
                    while (byteToCharMap[bytes[n] & 0xFF] != '?' || byteToCharMap[bytes[n + 1] & 0xFF] != '>') {
                        ++n;
                    }
                    n += 2;
                }
                c = byteToCharMap[bytes[n] & 0xFF];
            }
            else {
                if (c == ']') {
                    parsedEntity.offset = n;
                    return true;
                }
                if (c != '%') {
                    parsedEntity.offset = n;
                    if (c == '\0') {
                        if (n == parsedEntity.endOffset) {
                            singleByteWFCScannerSupport.setParameter(0, dtdParams.rootElementType());
                            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                            return false;
                        }
                    }
                    else if (parsedEntity.skipValidCharacter()) {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
                    return false;
                }
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = n + 1;
                if (!singleByteWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                    dtdParams.resetPEName();
                    return false;
                }
                n = parsedEntity.offset;
                dtdParams.resetPEName();
                c = byteToCharMap[bytes[n] & 0xFF];
            }
        }
    }
    
    private static int skipMarkupDecl(final char[] array, final byte[] array2, int n) {
        while (true) {
            final char c = array[array2[++n] & 0xFF];
            if (c == '\"') {
                while (array[array2[++n] & 0xFF] != '\"') {}
            }
            else if (c == '\'') {
                while (array[array2[++n] & 0xFF] != '\'') {}
            }
            else {
                if (c == '>') {
                    break;
                }
                continue;
            }
        }
        return n + 1;
    }
    
    private static boolean scanRequiredWhitespace(final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c == ' ' || c == '\n' || c == '\t') {
            char c2;
            do {
                c2 = byteToCharMap[bytes[++offset] & 0xFF];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            parsedEntity.offset = offset;
            return true;
        }
        return false;
    }
}
