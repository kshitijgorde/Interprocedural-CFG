// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class UTF16WFCInternalSubsetScanner extends UTF16WFCMarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        char c = chars[offset];
        while (c < '\u0080') {
            switch (DocumentEntityState.contentMap[c]) {
                case 2: {
                    parsedEntity.offset = offset + 1;
                    if (!scanMarkupDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                        return false;
                    }
                    offset = parsedEntity.offset;
                    c = chars[offset];
                    continue;
                }
                case 1: {
                    if (c == ' ' || c == '\n' || c == '\t') {
                        do {
                            c = chars[++offset];
                        } while (c == ' ' || c == '\n' || c == '\t');
                        continue;
                    }
                    if (c != '%') {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    final XMLName peName = dtdParams.getPEName();
                    parsedEntity.offset = offset + 1;
                    if (!utf16WFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                        return false;
                    }
                    internalSubsetHandler.internalSubsetPEReference(peName);
                    dtdParams.resetPEName();
                    offset = parsedEntity.offset;
                    c = chars[offset];
                    continue;
                }
                case 3: {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                case 4: {
                    parsedEntity.offset = offset;
                    return true;
                }
                case 5: {
                    if (offset == parsedEntity.endOffset) {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                        return false;
                    }
                    break;
                }
            }
            parsedEntity.offset = offset;
            utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
            return false;
        }
        parsedEntity.offset = offset;
        if (parsedEntity.skipValidCharacter()) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
            return false;
        }
        utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
        return false;
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
            if (c == '<') {
                parsedEntity.offset = offset + 1;
                if (!scanMarkupDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
                offset = parsedEntity.offset;
            }
            else if (c == '%') {
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = offset + 1;
                if (!utf16WFCScannerSupport.scanPEReference(parsedEntity, peName)) {
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
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
        }
    }
    
    private static boolean scanMarkupDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final char c = chars[offset];
        if (c == '!') {
            final char c2 = chars[++offset];
            if (c2 == 'E' && chars[offset + 1] == 'L' && chars[offset + 2] == 'E' && chars[offset + 3] == 'M' && chars[offset + 4] == 'E' && chars[offset + 5] == 'N' && chars[offset + 6] == 'T') {
                parsedEntity.offset = offset + 7;
                if (!scanElementDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'A' && chars[offset + 1] == 'T' && chars[offset + 2] == 'T' && chars[offset + 3] == 'L' && chars[offset + 4] == 'I' && chars[offset + 5] == 'S' && chars[offset + 6] == 'T') {
                parsedEntity.offset = offset + 7;
                if (!scanAttlistDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'E' && chars[offset + 1] == 'N' && chars[offset + 2] == 'T' && chars[offset + 3] == 'I' && chars[offset + 4] == 'T' && chars[offset + 5] == 'Y') {
                parsedEntity.offset = offset + 6;
                if (!scanEntityDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (c2 == 'N' && chars[offset + 1] == 'O' && chars[offset + 2] == 'T' && chars[offset + 3] == 'A' && chars[offset + 4] == 'T' && chars[offset + 5] == 'I' && chars[offset + 6] == 'O' && chars[offset + 7] == 'N') {
                parsedEntity.offset = offset + 8;
                if (!scanNotationDecl(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                if (c2 != '-') {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                if (chars[offset + 1] != '-') {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                    return false;
                }
                parsedEntity.offset = offset + 2;
                if (!UTF16WFCMarkupDeclScanner.scanComment(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
        }
        else {
            if (c != '?') {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
            parsedEntity.offset = offset + 1;
            if (!UTF16WFCMarkupDeclScanner.scanPI(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        final int offset = parsedEntity.offset;
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 21);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (utf16WFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 22);
            return false;
        }
        internalSubsetHandler.startElementDecl(elementType);
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.setParameter(0, elementType);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 23);
            return false;
        }
        int offset2 = parsedEntity.offset;
        final char c = chars[offset2];
        if (c == 'A' && chars[offset2 + 1] == 'N' && chars[offset2 + 2] == 'Y') {
            offset2 += 3;
            internalSubsetHandler.contentModelANY();
        }
        else if (c == 'E' && chars[offset2 + 1] == 'M' && chars[offset2 + 2] == 'P' && chars[offset2 + 3] == 'T' && chars[offset2 + 4] == 'Y') {
            offset2 += 5;
            internalSubsetHandler.contentModelEMPTY();
        }
        else {
            if (c != '(') {
                utf16WFCScannerSupport.setParameter(0, elementType);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 24);
                return false;
            }
            char c2 = chars[++offset2];
            internalSubsetHandler.contentModelStartGroup();
            while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                c2 = chars[++offset2];
            }
            if (c2 == '#' && chars[offset2 + 1] == 'P' && chars[offset2 + 2] == 'C' && chars[offset2 + 3] == 'D' && chars[offset2 + 4] == 'A' && chars[offset2 + 5] == 'T' && chars[offset2 + 6] == 'A') {
                parsedEntity.offset = offset2 + 7;
                internalSubsetHandler.contentModelPCDATA();
                if (!scanMixed(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                parsedEntity.offset = offset2;
                if (!scanChildren(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity, 1)) {
                    return false;
                }
            }
            offset2 = parsedEntity.offset;
        }
        char c3;
        for (c3 = chars[offset2]; c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r'; c3 = chars[++offset2]) {}
        if (c3 != '>') {
            utf16WFCScannerSupport.setParameter(0, elementType);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 25);
            return false;
        }
        parsedEntity.offset = offset2 + 1;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return true;
    }
    
    private static boolean scanMixed(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int n = parsedEntity.offset;
        int n2 = 0;
        while (true) {
            char c;
            for (c = chars[n]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = chars[++n]) {}
            if (c != '|') {
                if (c != ')') {
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 29);
                    return false;
                }
                final char c2 = chars[++n];
                internalSubsetHandler.contentModelEndGroup();
                if (c2 == '*') {
                    ++n;
                    internalSubsetHandler.contentModelOccurrence(1);
                }
                else if (n2 != 0) {
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 30);
                    return false;
                }
                parsedEntity.offset = n;
                return true;
            }
            else {
                char c3 = chars[++n];
                internalSubsetHandler.contentModelSeparator(0);
                n2 = 1;
                while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                    c3 = chars[++n];
                }
                parsedEntity.offset = n;
                final QName contentModelElement = dtdParams.getContentModelElement();
                if (utf16WFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 28);
                    return false;
                }
                internalSubsetHandler.contentModelElement(contentModelElement);
                dtdParams.resetContentModelElement();
                n = parsedEntity.offset;
            }
        }
    }
    
    private static boolean scanChildren(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int n2 = -1;
        while (scanCp(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity, n)) {
            int offset;
            char c;
            for (offset = parsedEntity.offset, c = chars[offset]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = chars[++offset]) {}
            if (c == ')') {
                final char c2 = chars[++offset];
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
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 0;
            }
            else {
                if (c != ',') {
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                if (n2 == 0) {
                    utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 1;
            }
            char c3 = chars[++offset];
            internalSubsetHandler.contentModelSeparator(n2);
            while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                c3 = chars[++offset];
            }
            parsedEntity.offset = offset;
        }
        return false;
    }
    
    private static boolean scanCp(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        if (chars[offset] == '(') {
            char c = chars[++offset];
            internalSubsetHandler.contentModelStartGroup();
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                c = chars[++offset];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        if (utf16WFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
            utf16WFCScannerSupport.setParameter(0, dtdParams.elementType());
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 26);
            return false;
        }
        internalSubsetHandler.contentModelElement(contentModelElement);
        dtdParams.resetContentModelElement();
        final char c2 = chars[parsedEntity.offset];
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
    
    private static boolean scanAttlistDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 31);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (utf16WFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 32);
            return false;
        }
        internalSubsetHandler.startAttlistDecl(elementType);
        int n = parsedEntity.offset;
        char c = chars[n];
        while (true) {
            final boolean b = c == ' ' || c == '\n' || c == '\t' || c == '\r';
            if (b) {
                do {
                    c = chars[++n];
                } while (c == ' ' || c == '\n' || c == '\t' || c == '\r');
            }
            if (c == '>') {
                parsedEntity.offset = n + 1;
                internalSubsetHandler.endAttlistDecl();
                dtdParams.resetElementType();
                return true;
            }
            if (!b) {
                utf16WFCScannerSupport.setParameter(0, elementType);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 33);
                return false;
            }
            parsedEntity.offset = n;
            final QName attributeName = dtdParams.getAttributeName();
            if (utf16WFCScannerSupport.scanQName(parsedEntity, attributeName) == 0) {
                utf16WFCScannerSupport.setParameter(0, elementType);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 34);
                return false;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                utf16WFCScannerSupport.setParameter(0, elementType);
                utf16WFCScannerSupport.setParameter(1, attributeName);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 35);
                return false;
            }
            int n2 = parsedEntity.offset;
            int n3 = (chars[n2] == '(') ? 1 : 0;
            XMLString attributeType;
            if (n3 == 0) {
                attributeType = dtdParams.getAttributeType();
                if (!scanAttType(parsedEntity, attributeType)) {
                    utf16WFCScannerSupport.setParameter(0, elementType);
                    utf16WFCScannerSupport.setParameter(1, attributeName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 36);
                    return false;
                }
                n2 = parsedEntity.offset;
                final char c2 = chars[n2];
                if (utf16WFCScannerSupport.isNotationType(attributeType)) {
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        utf16WFCScannerSupport.setParameter(0, elementType);
                        utf16WFCScannerSupport.setParameter(1, attributeName);
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 39);
                        return false;
                    }
                    n2 = parsedEntity.offset;
                    if (chars[n2] != '(') {
                        utf16WFCScannerSupport.setParameter(0, elementType);
                        utf16WFCScannerSupport.setParameter(1, attributeName);
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 40);
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
                    final char c3 = chars[++n2];
                    if (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r') {
                        parsedEntity.offset = n2;
                        if (attributeType != null) {
                            final XMLName enumerationTypeName = dtdParams.getEnumerationTypeName();
                            if (utf16WFCScannerSupport.scanName(parsedEntity, enumerationTypeName) == 0) {
                                utf16WFCScannerSupport.setParameter(0, elementType);
                                utf16WFCScannerSupport.setParameter(1, attributeName);
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 41);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeName);
                            dtdParams.resetEnumerationTypeName();
                        }
                        else {
                            final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                            if (utf16WFCScannerSupport.scanNmtoken(parsedEntity, enumerationTypeToken) == 0) {
                                utf16WFCScannerSupport.setParameter(0, elementType);
                                utf16WFCScannerSupport.setParameter(1, attributeName);
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 43);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeToken);
                            dtdParams.resetEnumerationTypeToken();
                        }
                        char c4;
                        for (n2 = parsedEntity.offset, c4 = chars[n2]; c4 == ' ' || c4 == '\n' || c4 == '\t' || c4 == '\r'; c4 = chars[++n2]) {}
                        if (c4 == '|') {
                            continue;
                        }
                        if (c4 != ')') {
                            utf16WFCScannerSupport.setParameter(0, elementType);
                            utf16WFCScannerSupport.setParameter(1, attributeName);
                            if (attributeType != null) {
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 42);
                            }
                            else {
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 44);
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
                utf16WFCScannerSupport.setParameter(0, elementType);
                utf16WFCScannerSupport.setParameter(1, attributeName);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 37);
                return false;
            }
            n = parsedEntity.offset;
            c = chars[n];
            XMLString attributeDefaultType;
            boolean b2;
            if (c == '#') {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = n;
                final char c5 = chars[++n];
                if (c5 == 'F' && chars[n + 1] == 'I' && chars[n + 2] == 'X' && chars[n + 3] == 'E' && chars[n + 4] == 'D') {
                    n += 5;
                    attributeDefaultType.endOffset = n;
                    parsedEntity.offset = n;
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        utf16WFCScannerSupport.setParameter(0, elementType);
                        utf16WFCScannerSupport.setParameter(1, attributeName);
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 45);
                        return false;
                    }
                    n = parsedEntity.offset;
                    b2 = true;
                    c = chars[n];
                }
                else {
                    if (c5 == 'I' && chars[n + 1] == 'M' && chars[n + 2] == 'P' && chars[n + 3] == 'L' && chars[n + 4] == 'I' && chars[n + 5] == 'E' && chars[n + 6] == 'D') {
                        n += 7;
                    }
                    else {
                        if (c5 != 'R' || chars[n + 1] != 'E' || chars[n + 2] != 'Q' || chars[n + 3] != 'U' || chars[n + 4] != 'I' || chars[n + 5] != 'R' || chars[n + 6] != 'E' || chars[n + 7] != 'D') {
                            utf16WFCScannerSupport.setParameter(0, elementType);
                            utf16WFCScannerSupport.setParameter(1, attributeName);
                            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 38);
                            return false;
                        }
                        n += 8;
                    }
                    attributeDefaultType.endOffset = n;
                    b2 = false;
                    c = chars[n];
                }
            }
            else {
                attributeDefaultType = null;
                b2 = true;
            }
            if (b2) {
                final char c6 = c;
                if (c6 != '\'' && c6 != '\"') {
                    utf16WFCScannerSupport.setParameter(0, elementType);
                    utf16WFCScannerSupport.setParameter(1, attributeName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
                ++n;
                internalSubsetHandler.startDefaultAttValue();
                utf16WFCScannerSupport.startDefaultAttValue(elementType, attributeName);
                parsedEntity.offset = n;
                if (!UTF16WFCMarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity, c6)) {
                    return false;
                }
                n = parsedEntity.offset;
                c = chars[n];
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        xmlString.offset = offset;
        final char c = chars[offset];
        if (c == 'C' && chars[offset + 1] == 'D' && chars[offset + 2] == 'A' && chars[offset + 3] == 'T' && chars[offset + 4] == 'A') {
            offset += 5;
        }
        else if (c == 'I' && chars[offset + 1] == 'D') {
            if (chars[offset + 2] == 'R' && chars[offset + 3] == 'E' && chars[offset + 4] == 'F') {
                if (chars[offset + 5] == 'S') {
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
        else if (c == 'E' && chars[offset + 1] == 'N' && chars[offset + 2] == 'T' && chars[offset + 3] == 'I' && chars[offset + 4] == 'T') {
            if (chars[offset + 5] == 'Y') {
                offset += 6;
            }
            else {
                if (chars[offset + 5] != 'I' || chars[offset + 6] != 'E' || chars[offset + 7] != 'S') {
                    return false;
                }
                offset += 8;
            }
        }
        else if (c == 'N' && chars[offset + 1] == 'M' && chars[offset + 2] == 'T' && chars[offset + 3] == 'O' && chars[offset + 4] == 'K' && chars[offset + 5] == 'E' && chars[offset + 6] == 'N') {
            if (chars[offset + 7] == 'S') {
                offset += 8;
            }
            else {
                offset += 7;
            }
        }
        else {
            if (c != 'N' || chars[offset + 1] != 'O' || chars[offset + 2] != 'T' || chars[offset + 3] != 'A' || chars[offset + 4] != 'T' || chars[offset + 5] != 'I' || chars[offset + 6] != 'O' || chars[offset + 7] != 'N') {
                return false;
            }
            offset += 8;
        }
        xmlString.endOffset = offset;
        parsedEntity.offset = offset;
        return true;
    }
    
    private static boolean scanEntityDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 50);
            return false;
        }
        final int offset = parsedEntity.offset;
        final boolean b = chars[offset] == '%';
        if (b) {
            parsedEntity.offset = offset + 1;
            if (!scanRequiredWhitespace(parsedEntity)) {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 52);
                return false;
            }
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        if (utf16WFCScannerSupport.scanName(parsedEntity, entityDeclName) == 0) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 53);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.setParameter(0, entityDeclName);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 54);
            return false;
        }
        final int offset2 = parsedEntity.offset;
        final char c = chars[offset2];
        if (c == '\"' || c == '\'') {
            final char c2 = c;
            parsedEntity.offset = offset2 + 1;
            internalSubsetHandler.startEntityValue();
            if (!UTF16WFCMarkupDeclScanner.scanEntityValue(internalSubsetHandler, utf16WFCScannerSupport, dtdParams, parsedEntity, c2, true)) {
                return false;
            }
            int offset3;
            char c3;
            for (offset3 = parsedEntity.offset, c3 = chars[offset3]; c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r'; c3 = chars[++offset3]) {}
            if (c3 != '>') {
                utf16WFCScannerSupport.setParameter(0, entityDeclName);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
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
            if (c == 'P' && chars[offset2 + 1] == 'U' && chars[offset2 + 2] == 'B' && chars[offset2 + 3] == 'L' && chars[offset2 + 4] == 'I' && chars[offset2 + 5] == 'C') {
                parsedEntity.offset = offset2 + 6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                    return false;
                }
                final int offset4 = parsedEntity.offset;
                final char c4 = chars[offset4];
                if (c4 != '\"' && c4 != '\'') {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                    return false;
                }
                parsedEntity.offset = offset4 + 1;
                publicID = dtdParams.getPublicID();
                if (!utf16WFCScannerSupport.scanPublicID(parsedEntity, c4, publicID)) {
                    return false;
                }
            }
            else {
                if (c != 'S' || chars[offset2 + 1] != 'Y' || chars[offset2 + 2] != 'S' || chars[offset2 + 3] != 'T' || chars[offset2 + 4] != 'E' || chars[offset2 + 5] != 'M') {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                    return false;
                }
                parsedEntity.offset = offset2 + 6;
                publicID = null;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            final int offset5 = parsedEntity.offset;
            final char c5 = chars[offset5];
            if (c5 != '\"' && c5 != '\'') {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                return false;
            }
            parsedEntity.offset = offset5 + 1;
            final XMLString systemID = dtdParams.getSystemID();
            if (!utf16WFCScannerSupport.scanSystemID(parsedEntity, c5, systemID)) {
                return false;
            }
            int offset6 = parsedEntity.offset;
            char c6 = chars[offset6];
            final boolean b2 = c6 == ' ' || c6 == '\n' || c6 == '\t' || c6 == '\r';
            if (b2) {
                do {
                    c6 = chars[++offset6];
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
                    utf16WFCScannerSupport.setParameter(0, entityDeclName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                if (c6 != 'N' || chars[offset6 + 1] != 'D' || chars[offset6 + 2] != 'A' || chars[offset6 + 3] != 'T' || chars[offset6 + 4] != 'A') {
                    utf16WFCScannerSupport.setParameter(0, entityDeclName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                offset6 += 5;
                parsedEntity.offset = offset6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    utf16WFCScannerSupport.setParameter(0, entityDeclName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 56);
                    return false;
                }
                final XMLName notationName = dtdParams.getNotationName();
                if (utf16WFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
                    utf16WFCScannerSupport.setParameter(0, entityDeclName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 57);
                    return false;
                }
                int offset7;
                char c7;
                for (offset7 = parsedEntity.offset, c7 = chars[offset7]; c7 == ' ' || c7 == '\n' || c7 == '\t' || c7 == '\r'; c7 = chars[++offset7]) {}
                if (c7 != '>') {
                    utf16WFCScannerSupport.setParameter(0, entityDeclName);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
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
    
    private static boolean scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 64);
            return false;
        }
        final XMLName notationName = dtdParams.getNotationName();
        if (utf16WFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 65);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.setParameter(0, notationName);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 66);
            return false;
        }
        int offset = parsedEntity.offset;
        final char c = chars[offset];
        XMLString publicID;
        if (c == 'P' && chars[offset + 1] == 'U' && chars[offset + 2] == 'B' && chars[offset + 3] == 'L' && chars[offset + 4] == 'I' && chars[offset + 5] == 'C') {
            parsedEntity.offset = offset + 6;
            if (!scanRequiredWhitespace(parsedEntity)) {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                return false;
            }
            final int offset2 = parsedEntity.offset;
            final char c2 = chars[offset2];
            if (c2 != '\"' && c2 != '\'') {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                return false;
            }
            parsedEntity.offset = offset2 + 1;
            publicID = dtdParams.getPublicID();
            if (!utf16WFCScannerSupport.scanPublicID(parsedEntity, c2, publicID)) {
                return false;
            }
            offset = parsedEntity.offset;
        }
        else {
            if (c != 'S' || chars[offset + 1] != 'Y' || chars[offset + 2] != 'S' || chars[offset + 3] != 'T' || chars[offset + 4] != 'E' || chars[offset + 5] != 'M') {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                return false;
            }
            offset += 6;
            publicID = null;
        }
        char c3 = chars[offset];
        if (c3 != '>') {
            parsedEntity.offset = offset;
            if (!scanRequiredWhitespace(parsedEntity)) {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            offset = parsedEntity.offset;
            c3 = chars[offset];
        }
        XMLString systemID;
        if (c3 == '\"' || c3 == '\'') {
            final char c4 = c3;
            parsedEntity.offset = offset + 1;
            systemID = dtdParams.getSystemID();
            if (!utf16WFCScannerSupport.scanSystemID(parsedEntity, c4, systemID)) {
                return false;
            }
            offset = parsedEntity.offset;
            c3 = chars[offset];
        }
        else {
            systemID = null;
        }
        while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
            c3 = chars[++offset];
        }
        if (c3 != '>') {
            utf16WFCScannerSupport.setParameter(0, notationName);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 67);
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final char c = chars[offset];
        if (c == ' ' || c == '\n' || c == '\t') {
            char c2;
            do {
                c2 = chars[++offset];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            parsedEntity.offset = offset;
            return true;
        }
        return false;
    }
}
