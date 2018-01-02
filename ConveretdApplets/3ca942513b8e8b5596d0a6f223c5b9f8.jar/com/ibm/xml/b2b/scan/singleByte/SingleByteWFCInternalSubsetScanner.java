// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class SingleByteWFCInternalSubsetScanner extends SingleByteWFCMarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        char c = byteToCharMap[bytes[offset] & 0xFF];
        while (c < '\u0080') {
            switch (DocumentEntityState.contentMap[c]) {
                case 2: {
                    parsedEntity.offset = offset + 1;
                    if (!scanMarkupDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                        return false;
                    }
                    offset = parsedEntity.offset;
                    c = byteToCharMap[bytes[offset] & 0xFF];
                    continue;
                }
                case 1: {
                    if (c == ' ' || c == '\n' || c == '\t') {
                        do {
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        } while (c == ' ' || c == '\n' || c == '\t');
                        continue;
                    }
                    if (c != '%') {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    final XMLName peName = dtdParams.getPEName();
                    parsedEntity.offset = offset + 1;
                    if (!singleByteWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                        return false;
                    }
                    internalSubsetHandler.internalSubsetPEReference(peName);
                    dtdParams.resetPEName();
                    offset = parsedEntity.offset;
                    c = byteToCharMap[bytes[offset] & 0xFF];
                    continue;
                }
                case 3: {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                case 4: {
                    parsedEntity.offset = offset;
                    return true;
                }
                case 5: {
                    if (offset == parsedEntity.endOffset) {
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                        return false;
                    }
                    break;
                }
            }
            parsedEntity.offset = offset;
            singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
            return false;
        }
        parsedEntity.offset = offset;
        if (parsedEntity.skipValidCharacter()) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
            return false;
        }
        singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
        return false;
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
            if (c == '<') {
                parsedEntity.offset = offset + 1;
                if (!scanMarkupDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
                offset = parsedEntity.offset;
            }
            else if (c == '%') {
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = offset + 1;
                if (!singleByteWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                    return false;
                }
                internalSubsetHandler.internalSubsetPEReference(peName);
                dtdParams.resetPEName();
                offset = parsedEntity.offset;
            }
            else {
                parsedEntity.offset = offset;
                if (c == '\0' && offset == parsedEntity.endOffset) {
                    return true;
                }
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
        }
    }
    
    private static boolean scanMarkupDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c == '!') {
            final char c2 = byteToCharMap[bytes[++offset] & 0xFF];
            if (c2 == 'E' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'L' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'E' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'M' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'E' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'N' && byteToCharMap[bytes[offset + 6] & 0xFF] == 'T') {
                parsedEntity.offset = offset + 7;
                if (!scanElementDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'A' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'L' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'I' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'S' && byteToCharMap[bytes[offset + 6] & 0xFF] == 'T') {
                parsedEntity.offset = offset + 7;
                if (!scanAttlistDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'E' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'N' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'I' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'Y') {
                parsedEntity.offset = offset + 6;
                if (!scanEntityDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'N' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'O' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'A' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'I' && byteToCharMap[bytes[offset + 6] & 0xFF] == 'O' && byteToCharMap[bytes[offset + 7] & 0xFF] == 'N') {
                parsedEntity.offset = offset + 8;
                if (!scanNotationDecl(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                if (c2 != '-') {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                if (byteToCharMap[bytes[offset + 1] & 0xFF] != '-') {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                    return false;
                }
                parsedEntity.offset = offset + 2;
                if (!SingleByteWFCMarkupDeclScanner.scanComment(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
        }
        else {
            if (c != '?') {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
            parsedEntity.offset = offset + 1;
            if (!SingleByteWFCMarkupDeclScanner.scanPI(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int offset = parsedEntity.offset;
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 21);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (singleByteWFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 22);
            return false;
        }
        internalSubsetHandler.startElementDecl(elementType);
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.setParameter(0, elementType);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 23);
            return false;
        }
        int offset2 = parsedEntity.offset;
        final char c = byteToCharMap[bytes[offset2] & 0xFF];
        if (c == 'A' && byteToCharMap[bytes[offset2 + 1] & 0xFF] == 'N' && byteToCharMap[bytes[offset2 + 2] & 0xFF] == 'Y') {
            offset2 += 3;
            internalSubsetHandler.contentModelANY();
        }
        else if (c == 'E' && byteToCharMap[bytes[offset2 + 1] & 0xFF] == 'M' && byteToCharMap[bytes[offset2 + 2] & 0xFF] == 'P' && byteToCharMap[bytes[offset2 + 3] & 0xFF] == 'T' && byteToCharMap[bytes[offset2 + 4] & 0xFF] == 'Y') {
            offset2 += 5;
            internalSubsetHandler.contentModelEMPTY();
        }
        else {
            if (c != '(') {
                singleByteWFCScannerSupport.setParameter(0, elementType);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 24);
                return false;
            }
            char c2 = byteToCharMap[bytes[++offset2] & 0xFF];
            internalSubsetHandler.contentModelStartGroup();
            while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                c2 = byteToCharMap[bytes[++offset2] & 0xFF];
            }
            if (c2 == '#' && byteToCharMap[bytes[offset2 + 1] & 0xFF] == 'P' && byteToCharMap[bytes[offset2 + 2] & 0xFF] == 'C' && byteToCharMap[bytes[offset2 + 3] & 0xFF] == 'D' && byteToCharMap[bytes[offset2 + 4] & 0xFF] == 'A' && byteToCharMap[bytes[offset2 + 5] & 0xFF] == 'T' && byteToCharMap[bytes[offset2 + 6] & 0xFF] == 'A') {
                parsedEntity.offset = offset2 + 7;
                internalSubsetHandler.contentModelPCDATA();
                if (!scanMixed(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                parsedEntity.offset = offset2;
                if (!scanChildren(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity, 1)) {
                    return false;
                }
            }
            offset2 = parsedEntity.offset;
        }
        char c3;
        for (c3 = byteToCharMap[bytes[offset2] & 0xFF]; c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r'; c3 = byteToCharMap[bytes[++offset2] & 0xFF]) {}
        if (c3 != '>') {
            singleByteWFCScannerSupport.setParameter(0, elementType);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 25);
            return false;
        }
        parsedEntity.offset = offset2 + 1;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return true;
    }
    
    private static boolean scanMixed(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        int n2 = 0;
        while (true) {
            char c;
            for (c = byteToCharMap[bytes[n] & 0xFF]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
            if (c != '|') {
                if (c != ')') {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 29);
                    return false;
                }
                final char c2 = byteToCharMap[bytes[++n] & 0xFF];
                internalSubsetHandler.contentModelEndGroup();
                if (c2 == '*') {
                    ++n;
                    internalSubsetHandler.contentModelOccurrence(1);
                }
                else if (n2 != 0) {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 30);
                    return false;
                }
                parsedEntity.offset = n;
                return true;
            }
            else {
                char c3 = byteToCharMap[bytes[++n] & 0xFF];
                internalSubsetHandler.contentModelSeparator(0);
                n2 = 1;
                while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                    c3 = byteToCharMap[bytes[++n] & 0xFF];
                }
                parsedEntity.offset = n;
                final QName contentModelElement = dtdParams.getContentModelElement();
                if (singleByteWFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 28);
                    return false;
                }
                internalSubsetHandler.contentModelElement(contentModelElement);
                dtdParams.resetContentModelElement();
                n = parsedEntity.offset;
            }
        }
    }
    
    private static boolean scanChildren(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n2 = -1;
        while (scanCp(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity, n)) {
            int offset;
            char c;
            for (offset = parsedEntity.offset, c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
            if (c == ')') {
                final char c2 = byteToCharMap[bytes[++offset] & 0xFF];
                internalSubsetHandler.contentModelEndGroup();
                if (c2 == '?') {
                    ++offset;
                    internalSubsetHandler.contentModelOccurrence(0);
                }
                else if (c2 == '*') {
                    ++offset;
                    internalSubsetHandler.contentModelOccurrence(1);
                }
                else if (c2 == '+') {
                    ++offset;
                    internalSubsetHandler.contentModelOccurrence(2);
                }
                parsedEntity.offset = offset;
                return true;
            }
            if (c == '|') {
                if (n2 == 1) {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 0;
            }
            else {
                if (c != ',') {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                if (n2 == 0) {
                    singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 1;
            }
            char c3 = byteToCharMap[bytes[++offset] & 0xFF];
            internalSubsetHandler.contentModelSeparator(n2);
            while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                c3 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            parsedEntity.offset = offset;
        }
        return false;
    }
    
    private static boolean scanCp(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        if (byteToCharMap[bytes[offset] & 0xFF] == '(') {
            char c = byteToCharMap[bytes[++offset] & 0xFF];
            internalSubsetHandler.contentModelStartGroup();
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        if (singleByteWFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
            singleByteWFCScannerSupport.setParameter(0, dtdParams.elementType());
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 26);
            return false;
        }
        internalSubsetHandler.contentModelElement(contentModelElement);
        dtdParams.resetContentModelElement();
        final char c2 = byteToCharMap[bytes[parsedEntity.offset] & 0xFF];
        if (c2 == '?') {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (c2 == '*') {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (c2 == '+') {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return true;
    }
    
    private static boolean scanAttlistDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 31);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (singleByteWFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 32);
            return false;
        }
        internalSubsetHandler.startAttlistDecl(elementType);
        int n = parsedEntity.offset;
        char c = byteToCharMap[bytes[n] & 0xFF];
        while (true) {
            final boolean b = c == ' ' || c == '\n' || c == '\t' || c == '\r';
            if (b) {
                do {
                    c = byteToCharMap[bytes[++n] & 0xFF];
                } while (c == ' ' || c == '\n' || c == '\t' || c == '\r');
            }
            if (c == '>') {
                parsedEntity.offset = n + 1;
                internalSubsetHandler.endAttlistDecl();
                dtdParams.resetElementType();
                return true;
            }
            if (!b) {
                singleByteWFCScannerSupport.setParameter(0, elementType);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 33);
                return false;
            }
            parsedEntity.offset = n;
            final QName attributeName = dtdParams.getAttributeName();
            if (singleByteWFCScannerSupport.scanQName(parsedEntity, attributeName) == 0) {
                singleByteWFCScannerSupport.setParameter(0, elementType);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 34);
                return false;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                singleByteWFCScannerSupport.setParameter(0, elementType);
                singleByteWFCScannerSupport.setParameter(1, attributeName);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 35);
                return false;
            }
            int n2 = parsedEntity.offset;
            int n3 = (byteToCharMap[bytes[n2] & 0xFF] == '(') ? 1 : 0;
            XMLString attributeType;
            if (n3 == 0) {
                attributeType = dtdParams.getAttributeType();
                if (!scanAttType(parsedEntity, attributeType)) {
                    singleByteWFCScannerSupport.setParameter(0, elementType);
                    singleByteWFCScannerSupport.setParameter(1, attributeName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 36);
                    return false;
                }
                n2 = parsedEntity.offset;
                final char c2 = byteToCharMap[bytes[n2] & 0xFF];
                if (singleByteWFCScannerSupport.isNotationType(attributeType)) {
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        singleByteWFCScannerSupport.setParameter(0, elementType);
                        singleByteWFCScannerSupport.setParameter(1, attributeName);
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 39);
                        return false;
                    }
                    n2 = parsedEntity.offset;
                    if (byteToCharMap[bytes[n2] & 0xFF] != '(') {
                        singleByteWFCScannerSupport.setParameter(0, elementType);
                        singleByteWFCScannerSupport.setParameter(1, attributeName);
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 40);
                        return false;
                    }
                    n3 = 1;
                }
            }
            else {
                attributeType = null;
            }
            internalSubsetHandler.startAttDef(attributeName, attributeType);
            if (n3 != 0) {
                internalSubsetHandler.startEnumerationType();
                while (true) {
                    final char c3 = byteToCharMap[bytes[++n2] & 0xFF];
                    if (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r') {
                        parsedEntity.offset = n2;
                        if (attributeType != null) {
                            final XMLName enumerationTypeName = dtdParams.getEnumerationTypeName();
                            if (singleByteWFCScannerSupport.scanName(parsedEntity, enumerationTypeName) == 0) {
                                singleByteWFCScannerSupport.setParameter(0, elementType);
                                singleByteWFCScannerSupport.setParameter(1, attributeName);
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 41);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeName);
                            dtdParams.resetEnumerationTypeName();
                        }
                        else {
                            final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                            if (singleByteWFCScannerSupport.scanNmtoken(parsedEntity, enumerationTypeToken) == 0) {
                                singleByteWFCScannerSupport.setParameter(0, elementType);
                                singleByteWFCScannerSupport.setParameter(1, attributeName);
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 43);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeToken);
                            dtdParams.resetEnumerationTypeToken();
                        }
                        char c4;
                        for (n2 = parsedEntity.offset, c4 = byteToCharMap[bytes[n2] & 0xFF]; c4 == ' ' || c4 == '\n' || c4 == '\t' || c4 == '\r'; c4 = byteToCharMap[bytes[++n2] & 0xFF]) {}
                        if (c4 == '|') {
                            continue;
                        }
                        if (c4 != ')') {
                            singleByteWFCScannerSupport.setParameter(0, elementType);
                            singleByteWFCScannerSupport.setParameter(1, attributeName);
                            if (attributeType != null) {
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 42);
                            }
                            else {
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 44);
                            }
                            return false;
                        }
                        ++n2;
                        internalSubsetHandler.endEnumerationType();
                        break;
                    }
                }
            }
            parsedEntity.offset = n2;
            if (!scanRequiredWhitespace(parsedEntity)) {
                singleByteWFCScannerSupport.setParameter(0, elementType);
                singleByteWFCScannerSupport.setParameter(1, attributeName);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 37);
                return false;
            }
            n = parsedEntity.offset;
            c = byteToCharMap[bytes[n] & 0xFF];
            XMLString attributeDefaultType;
            boolean b2;
            if (c == '#') {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = n;
                final char c5 = byteToCharMap[bytes[++n] & 0xFF];
                if (c5 == 'F' && byteToCharMap[bytes[n + 1] & 0xFF] == 'I' && byteToCharMap[bytes[n + 2] & 0xFF] == 'X' && byteToCharMap[bytes[n + 3] & 0xFF] == 'E' && byteToCharMap[bytes[n + 4] & 0xFF] == 'D') {
                    n += 5;
                    attributeDefaultType.endOffset = n;
                    parsedEntity.offset = n;
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        singleByteWFCScannerSupport.setParameter(0, elementType);
                        singleByteWFCScannerSupport.setParameter(1, attributeName);
                        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 45);
                        return false;
                    }
                    n = parsedEntity.offset;
                    b2 = true;
                    c = byteToCharMap[bytes[n] & 0xFF];
                }
                else {
                    if (c5 == 'I' && byteToCharMap[bytes[n + 1] & 0xFF] == 'M' && byteToCharMap[bytes[n + 2] & 0xFF] == 'P' && byteToCharMap[bytes[n + 3] & 0xFF] == 'L' && byteToCharMap[bytes[n + 4] & 0xFF] == 'I' && byteToCharMap[bytes[n + 5] & 0xFF] == 'E' && byteToCharMap[bytes[n + 6] & 0xFF] == 'D') {
                        n += 7;
                    }
                    else {
                        if (c5 != 'R' || byteToCharMap[bytes[n + 1] & 0xFF] != 'E' || byteToCharMap[bytes[n + 2] & 0xFF] != 'Q' || byteToCharMap[bytes[n + 3] & 0xFF] != 'U' || byteToCharMap[bytes[n + 4] & 0xFF] != 'I' || byteToCharMap[bytes[n + 5] & 0xFF] != 'R' || byteToCharMap[bytes[n + 6] & 0xFF] != 'E' || byteToCharMap[bytes[n + 7] & 0xFF] != 'D') {
                            singleByteWFCScannerSupport.setParameter(0, elementType);
                            singleByteWFCScannerSupport.setParameter(1, attributeName);
                            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 38);
                            return false;
                        }
                        n += 8;
                    }
                    attributeDefaultType.endOffset = n;
                    b2 = false;
                    c = byteToCharMap[bytes[n] & 0xFF];
                }
            }
            else {
                attributeDefaultType = null;
                b2 = true;
            }
            if (b2) {
                final char c6 = c;
                if (c6 != '\'' && c6 != '\"') {
                    singleByteWFCScannerSupport.setParameter(0, elementType);
                    singleByteWFCScannerSupport.setParameter(1, attributeName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
                ++n;
                internalSubsetHandler.startDefaultAttValue();
                singleByteWFCScannerSupport.startDefaultAttValue(elementType, attributeName);
                parsedEntity.offset = n;
                if (!SingleByteWFCMarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity, c6)) {
                    return false;
                }
                n = parsedEntity.offset;
                c = byteToCharMap[bytes[n] & 0xFF];
            }
            internalSubsetHandler.endAttDef(attributeDefaultType);
            dtdParams.resetAttributeName();
            if (attributeType != null) {
                dtdParams.resetAttributeType();
            }
            if (attributeDefaultType == null) {
                continue;
            }
            dtdParams.resetAttributeDefaultType();
        }
    }
    
    private static boolean scanAttType(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        xmlString.offset = offset;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c == 'C' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'D' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'A' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'A') {
            offset += 5;
        }
        else if (c == 'I' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'D') {
            if (byteToCharMap[bytes[offset + 2] & 0xFF] == 'R' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'E' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'F') {
                if (byteToCharMap[bytes[offset + 5] & 0xFF] == 'S') {
                    offset += 6;
                }
                else {
                    offset += 5;
                }
            }
            else {
                offset += 2;
            }
        }
        else if (c == 'E' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'N' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'I' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'T') {
            if (byteToCharMap[bytes[offset + 5] & 0xFF] == 'Y') {
                offset += 6;
            }
            else {
                if (byteToCharMap[bytes[offset + 5] & 0xFF] != 'I' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'E' || byteToCharMap[bytes[offset + 7] & 0xFF] != 'S') {
                    return false;
                }
                offset += 8;
            }
        }
        else if (c == 'N' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'M' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'T' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'O' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'K' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'E' && byteToCharMap[bytes[offset + 6] & 0xFF] == 'N') {
            if (byteToCharMap[bytes[offset + 7] & 0xFF] == 'S') {
                offset += 8;
            }
            else {
                offset += 7;
            }
        }
        else {
            if (c != 'N' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'O' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'T' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'A' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'T' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'I' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'O' || byteToCharMap[bytes[offset + 7] & 0xFF] != 'N') {
                return false;
            }
            offset += 8;
        }
        xmlString.endOffset = offset;
        parsedEntity.offset = offset;
        return true;
    }
    
    private static boolean scanEntityDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 50);
            return false;
        }
        final int offset = parsedEntity.offset;
        final boolean b = byteToCharMap[bytes[offset] & 0xFF] == '%';
        if (b) {
            parsedEntity.offset = offset + 1;
            if (!scanRequiredWhitespace(parsedEntity)) {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 52);
                return false;
            }
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        if (singleByteWFCScannerSupport.scanName(parsedEntity, entityDeclName) == 0) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 53);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.setParameter(0, entityDeclName);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 54);
            return false;
        }
        final int offset2 = parsedEntity.offset;
        final char c = byteToCharMap[bytes[offset2] & 0xFF];
        if (c == '\"' || c == '\'') {
            final char c2 = c;
            parsedEntity.offset = offset2 + 1;
            internalSubsetHandler.startEntityValue();
            if (!SingleByteWFCMarkupDeclScanner.scanEntityValue(internalSubsetHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity, c2, true)) {
                return false;
            }
            int offset3;
            char c3;
            for (offset3 = parsedEntity.offset, c3 = byteToCharMap[bytes[offset3] & 0xFF]; c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r'; c3 = byteToCharMap[bytes[++offset3] & 0xFF]) {}
            if (c3 != '>') {
                singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                return false;
            }
            parsedEntity.offset = offset3 + 1;
            if (b) {
                internalSubsetHandler.internalPEDecl(entityDeclName);
            }
            else {
                internalSubsetHandler.internalEntityDecl(entityDeclName);
            }
            dtdParams.resetEntityDeclName();
            return true;
        }
        else {
            XMLString publicID;
            if (c == 'P' && byteToCharMap[bytes[offset2 + 1] & 0xFF] == 'U' && byteToCharMap[bytes[offset2 + 2] & 0xFF] == 'B' && byteToCharMap[bytes[offset2 + 3] & 0xFF] == 'L' && byteToCharMap[bytes[offset2 + 4] & 0xFF] == 'I' && byteToCharMap[bytes[offset2 + 5] & 0xFF] == 'C') {
                parsedEntity.offset = offset2 + 6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                    return false;
                }
                final int offset4 = parsedEntity.offset;
                final char c4 = byteToCharMap[bytes[offset4] & 0xFF];
                if (c4 != '\"' && c4 != '\'') {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                    return false;
                }
                parsedEntity.offset = offset4 + 1;
                publicID = dtdParams.getPublicID();
                if (!singleByteWFCScannerSupport.scanPublicID(parsedEntity, c4, publicID)) {
                    return false;
                }
            }
            else {
                if (c != 'S' || byteToCharMap[bytes[offset2 + 1] & 0xFF] != 'Y' || byteToCharMap[bytes[offset2 + 2] & 0xFF] != 'S' || byteToCharMap[bytes[offset2 + 3] & 0xFF] != 'T' || byteToCharMap[bytes[offset2 + 4] & 0xFF] != 'E' || byteToCharMap[bytes[offset2 + 5] & 0xFF] != 'M') {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                    return false;
                }
                parsedEntity.offset = offset2 + 6;
                publicID = null;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            final int offset5 = parsedEntity.offset;
            final char c5 = byteToCharMap[bytes[offset5] & 0xFF];
            if (c5 != '\"' && c5 != '\'') {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                return false;
            }
            parsedEntity.offset = offset5 + 1;
            final XMLString systemID = dtdParams.getSystemID();
            if (!singleByteWFCScannerSupport.scanSystemID(parsedEntity, c5, systemID)) {
                return false;
            }
            int offset6 = parsedEntity.offset;
            char c6 = byteToCharMap[bytes[offset6] & 0xFF];
            final boolean b2 = c6 == ' ' || c6 == '\n' || c6 == '\t' || c6 == '\r';
            if (b2) {
                do {
                    c6 = byteToCharMap[bytes[++offset6] & 0xFF];
                } while (c6 == ' ' || c6 == '\n' || c6 == '\t' || c6 == '\r');
            }
            if (c6 == '>') {
                parsedEntity.offset = offset6 + 1;
                if (b) {
                    internalSubsetHandler.externalPEDecl(entityDeclName, publicID, systemID);
                }
                else {
                    internalSubsetHandler.externalEntityDecl(entityDeclName, publicID, systemID);
                }
            }
            else {
                if (b || !b2) {
                    singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                if (c6 != 'N' || byteToCharMap[bytes[offset6 + 1] & 0xFF] != 'D' || byteToCharMap[bytes[offset6 + 2] & 0xFF] != 'A' || byteToCharMap[bytes[offset6 + 3] & 0xFF] != 'T' || byteToCharMap[bytes[offset6 + 4] & 0xFF] != 'A') {
                    singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                offset6 += 5;
                parsedEntity.offset = offset6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 56);
                    return false;
                }
                final XMLName notationName = dtdParams.getNotationName();
                if (singleByteWFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
                    singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 57);
                    return false;
                }
                int offset7;
                char c7;
                for (offset7 = parsedEntity.offset, c7 = byteToCharMap[bytes[offset7] & 0xFF]; c7 == ' ' || c7 == '\n' || c7 == '\t' || c7 == '\r'; c7 = byteToCharMap[bytes[++offset7] & 0xFF]) {}
                if (c7 != '>') {
                    singleByteWFCScannerSupport.setParameter(0, entityDeclName);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                parsedEntity.offset = offset7 + 1;
                internalSubsetHandler.unparsedEntityDecl(entityDeclName, publicID, systemID, notationName);
                dtdParams.resetNotationName();
            }
            dtdParams.resetEntityDeclName();
            if (publicID != null) {
                dtdParams.resetPublicID();
            }
            dtdParams.resetSystemID();
            return true;
        }
    }
    
    private static boolean scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 64);
            return false;
        }
        final XMLName notationName = dtdParams.getNotationName();
        if (singleByteWFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 65);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            singleByteWFCScannerSupport.setParameter(0, notationName);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 66);
            return false;
        }
        int offset = parsedEntity.offset;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        XMLString publicID;
        if (c == 'P' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'U' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'B' && byteToCharMap[bytes[offset + 3] & 0xFF] == 'L' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'I' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'C') {
            parsedEntity.offset = offset + 6;
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
            offset = parsedEntity.offset;
        }
        else {
            if (c != 'S' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'Y' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'S' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'T' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'E' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'M') {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                return false;
            }
            offset += 6;
            publicID = null;
        }
        char c3 = byteToCharMap[bytes[offset] & 0xFF];
        if (c3 != '>') {
            parsedEntity.offset = offset;
            if (!scanRequiredWhitespace(parsedEntity)) {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            offset = parsedEntity.offset;
            c3 = byteToCharMap[bytes[offset] & 0xFF];
        }
        XMLString systemID;
        if (c3 == '\"' || c3 == '\'') {
            final char c4 = c3;
            parsedEntity.offset = offset + 1;
            systemID = dtdParams.getSystemID();
            if (!singleByteWFCScannerSupport.scanSystemID(parsedEntity, c4, systemID)) {
                return false;
            }
            offset = parsedEntity.offset;
            c3 = byteToCharMap[bytes[offset] & 0xFF];
        }
        else {
            systemID = null;
        }
        while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
            c3 = byteToCharMap[bytes[++offset] & 0xFF];
        }
        if (c3 != '>') {
            singleByteWFCScannerSupport.setParameter(0, notationName);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 67);
            return false;
        }
        parsedEntity.offset = offset + 1;
        internalSubsetHandler.notationDecl(notationName, publicID, systemID);
        dtdParams.resetNotationName();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        return true;
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
