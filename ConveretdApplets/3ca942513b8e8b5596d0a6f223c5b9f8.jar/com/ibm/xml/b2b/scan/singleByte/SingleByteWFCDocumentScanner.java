// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.DocumentImplementationHandler;
import com.ibm.xml.b2b.scan.DocumentEventHandler;

public final class SingleByteWFCDocumentScanner
{
    private static final byte[] markupMap;
    
    public static boolean scanDocument(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        int n2 = 0;
        while (true) {
            char c;
            for (c = byteToCharMap[bytes[n] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
            if (c == '<') {
                final char c2 = byteToCharMap[bytes[n + 1] & 0xFF];
                if (c2 != '!') {
                    if (c2 != '?') {
                        if (c2 == '\0') {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                            return false;
                        }
                        parsedEntity.offset = n;
                        if (!scanContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        int offset = parsedEntity.offset;
                        while (true) {
                            final char c3 = byteToCharMap[bytes[offset] & 0xFF];
                            if (c3 == '\0' && offset == parsedEntity.endOffset) {
                                return true;
                            }
                            if (c3 == ' ' || c3 == '\n' || c3 == '\t') {
                                ++offset;
                            }
                            else if (c3 == '<') {
                                final char c4 = byteToCharMap[bytes[++offset] & 0xFF];
                                if (c4 == '?') {
                                    parsedEntity.offset = offset + 1;
                                    if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = parsedEntity.offset;
                                }
                                else {
                                    if (c4 != '!') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                        return false;
                                    }
                                    if (byteToCharMap[bytes[++offset] & 0xFF] != '-') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                        return false;
                                    }
                                    if (byteToCharMap[bytes[offset + 1] & 0xFF] != '-') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                                        return false;
                                    }
                                    parsedEntity.offset = offset + 2;
                                    if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = parsedEntity.offset;
                                }
                            }
                            else {
                                if (c3 == '&') {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                    return false;
                                }
                                parsedEntity.offset = offset;
                                documentEntityState.setInvalidCharParameter(0, parsedEntity);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 3);
                                return false;
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = n + 2;
                        if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                }
                else {
                    final char c5 = byteToCharMap[bytes[n + 2] & 0xFF];
                    if (c5 == '-') {
                        if (byteToCharMap[bytes[n + 3] & 0xFF] != '-') {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                            return false;
                        }
                        parsedEntity.offset = n + 4;
                        if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                    else {
                        if (n2 != 0 || c5 != 'D' || byteToCharMap[bytes[n + 3] & 0xFF] != 'O' || byteToCharMap[bytes[n + 4] & 0xFF] != 'C' || byteToCharMap[bytes[n + 5] & 0xFF] != 'T' || byteToCharMap[bytes[n + 6] & 0xFF] != 'Y' || byteToCharMap[bytes[n + 7] & 0xFF] != 'P' || byteToCharMap[bytes[n + 8] & 0xFF] != 'E') {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                            return false;
                        }
                        n2 = 1;
                        parsedEntity.offset = n + 9;
                        if (documentImplementationHandler != null) {
                            if (!documentImplementationHandler.scanDoctypeDecl(parsedEntity)) {
                                return false;
                            }
                            if (!documentImplementationHandler.scanExternalSubset()) {
                                return false;
                            }
                        }
                        else if (!skipDoctypeDecl(documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                }
            }
            else {
                if (c == '&') {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                    return false;
                }
                if (c == '\0' && n == parsedEntity.endOffset) {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 0);
                    return false;
                }
                parsedEntity.offset = n;
                documentEntityState.setInvalidCharParameter(0, parsedEntity);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 4);
                return false;
            }
        }
    }
    
    public static boolean scanContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final byte[] contentMap = singleByteEncodingSupport.contentMap;
        final byte[] markupMap = SingleByteWFCDocumentScanner.markupMap;
        final int elementDepth = documentEntityState.elementDepth;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        if (elementDepth == 0) {
            final char c = byteToCharMap[bytes[offset + 1] & 0xFF];
            if (c >= '\u0080' || markupMap[c] == 1) {
                parsedEntity.offset = offset + 1;
                if (!scanStartElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                    return false;
                }
                if (documentEntityState.elementDepth == 0 && elementDepth == 0) {
                    return true;
                }
                offset = parsedEntity.offset;
            }
        }
        int n = offset;
    Label_0754:
        while (true) {
            final byte b = contentMap[bytes[offset] & 0xFF];
            if (b == 1) {
                ++offset;
            }
            else {
                if (offset > n) {
                    final XMLString content = documentEntityState.content;
                    content.setValues(bytes, n, offset, singleByteEncodingSupport);
                    documentEventHandler.characters(content);
                }
                switch (b) {
                    case 2: {
                        final char c2 = byteToCharMap[bytes[++offset] & 0xFF];
                        switch ((c2 < '\u0080') ? markupMap[c2] : 1) {
                            case 1: {
                                parsedEntity.offset = offset;
                                if (!scanStartElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                    return false;
                                }
                                break;
                            }
                            case 2: {
                                parsedEntity.offset = offset + 1;
                                if (documentEntityState.elementDepth == elementDepth) {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                    return false;
                                }
                                if (!scanEndElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                    return false;
                                }
                                if (documentEntityState.elementDepth == 0 && elementDepth == 0) {
                                    return true;
                                }
                                break;
                            }
                            case 3: {
                                parsedEntity.offset = offset + 1;
                                if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                                    return false;
                                }
                                break;
                            }
                            case 4: {
                                final char c3 = byteToCharMap[bytes[++offset] & 0xFF];
                                if (c3 == '-') {
                                    if (byteToCharMap[bytes[offset + 1] & 0xFF] != '-') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                                        return false;
                                    }
                                    parsedEntity.offset = offset + 2;
                                    if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    break;
                                }
                                else {
                                    if (c3 != '[' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'C' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'D' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'A' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'T' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'A' || byteToCharMap[bytes[offset + 6] & 0xFF] != '[') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                        return false;
                                    }
                                    parsedEntity.offset = offset + 7;
                                    if (!scanCDSect(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    break;
                                }
                                break;
                            }
                            default: {
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                return false;
                            }
                        }
                        offset = (n = parsedEntity.offset);
                        continue;
                    }
                    case 3: {
                        parsedEntity.offset = offset + 1;
                        if (!checkReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        offset = (n = parsedEntity.offset);
                        continue;
                    }
                    case 4: {
                        if (byteToCharMap[bytes[offset + 1] & 0xFF] == ']' && byteToCharMap[bytes[offset + 2] & 0xFF] == '>') {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 5);
                            return false;
                        }
                        n = offset++;
                        continue;
                    }
                    case 5: {
                        if (offset != parsedEntity.endOffset) {
                            break Label_0754;
                        }
                        if (elementDepth > 0) {
                            parsedEntity.offset = offset;
                            return true;
                        }
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                        break Label_0754;
                    }
                    default: {
                        break Label_0754;
                    }
                }
            }
        }
        parsedEntity.offset = offset;
        documentEntityState.setInvalidCharParameter(0, parsedEntity);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 2);
        return false;
    }
    
    public static boolean scanAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity, final int n) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n2;
        int offset = n2 = parsedEntity.offset;
    Label_0040:
        while (true) {
            while (true) {
                char c = byteToCharMap[bytes[offset] & 0xFF];
                boolean b = true;
                while (true) {
                    if (c < '\u0080') {
                        final byte b2 = attValueMap[c];
                        if (b2 == 1) {
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        }
                        else if (b2 == 6) {
                            b = false;
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        }
                        else {
                            if (offset > n2) {
                                final XMLString content = documentEntityState.content;
                                content.setValues(bytes, n2, offset, singleByteEncodingSupport);
                                documentEntityState.attributeValueCharacters(content, b);
                            }
                            switch (b2) {
                                case 4: {
                                    if (c == n) {
                                        parsedEntity.offset = offset + 1;
                                        return true;
                                    }
                                    n2 = offset++;
                                    continue Label_0040;
                                }
                                case 3: {
                                    parsedEntity.offset = offset + 1;
                                    if (!checkReferenceInAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = (n2 = parsedEntity.offset);
                                    continue Label_0040;
                                }
                                case 2: {
                                    documentEntityState.setParameter(0, documentEntityState.currentElement);
                                    documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 25);
                                    return false;
                                }
                                case 5: {
                                    if (offset != parsedEntity.endOffset) {
                                        break Label_0040;
                                    }
                                    if (n == 0) {
                                        parsedEntity.offset = offset;
                                        return true;
                                    }
                                    documentEntityState.setParameter(0, documentEntityState.currentElement);
                                    documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 26);
                                    return false;
                                }
                                default: {
                                    break Label_0040;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = offset;
                        if (!parsedEntity.skipValidCharacter()) {
                            documentEntityState.setParameter(0, documentEntityState.currentElement);
                            documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                            documentEntityState.setInvalidCharParameter(2, parsedEntity);
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
                            return false;
                        }
                        offset = parsedEntity.offset;
                        c = byteToCharMap[bytes[offset] & 0xFF];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = offset;
        documentEntityState.setParameter(0, documentEntityState.currentElement);
        documentEntityState.setParameter(1, documentEntityState.currentAttribute);
        documentEntityState.setInvalidCharParameter(2, parsedEntity);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
        return false;
    }
    
    private static boolean scanStartElement(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final QName elementType = documentEntityState.elementType;
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        final int scanQName = documentEntityState.scanQName(parsedEntity, elementType);
        int offset = parsedEntity.offset;
        boolean b = false;
        boolean b2 = false;
        switch (scanQName) {
            case 3: {
                b = false;
                b2 = true;
                break;
            }
            case 4: {
                b = false;
                b2 = false;
                break;
            }
            case 5: {
                char c;
                do {
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                } while (c == ' ' || c == '\n' || c == '\t' || c == '\r');
                b = (!(b2 = (c == '/')) && c != '>');
                break;
            }
            case 2:
            case 6: {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
            default: {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                return false;
            }
            case 1: {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 49);
                return false;
            }
        }
        documentEntityState.processElementType();
        while (b) {
            final QName attributeName = documentEntityState.attributeName;
            parsedEntity.offset = offset;
            final int scanQName2 = documentEntityState.scanQName(parsedEntity, attributeName);
            int offset2 = parsedEntity.offset;
            switch (scanQName2) {
                case 6: {
                    break;
                }
                case 5: {
                    char c2;
                    do {
                        c2 = byteToCharMap[bytes[++offset2] & 0xFF];
                    } while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r');
                    if (c2 == '=') {
                        break;
                    }
                }
                case 2:
                case 3:
                case 4: {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.setParameter(1, attributeName);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 16);
                    return false;
                }
                default: {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                    return false;
                }
                case 1: {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 49);
                    return false;
                }
            }
            documentEntityState.processAttributeName(attributeName, true);
            char c3 = byteToCharMap[bytes[++offset2] & 0xFF];
            if (c3 != '\'' && c3 != '\"') {
                while (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                    c3 = byteToCharMap[bytes[++offset2] & 0xFF];
                }
                if (c3 != '\'' && c3 != '\"') {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.setParameter(1, attributeName);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
            }
            final char c4 = c3;
            parsedEntity.offset = offset2 + 1;
            if (!scanAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity, c4)) {
                return false;
            }
            offset = parsedEntity.offset;
            char c5 = byteToCharMap[bytes[offset] & 0xFF];
            final boolean b3 = c5 == ' ' || c5 == '\n' || c5 == '\t' || c5 == '\r';
            if (b3) {
                do {
                    c5 = byteToCharMap[bytes[++offset] & 0xFF];
                } while (c5 == ' ' || c5 == '\n' || c5 == '\t' || c5 == '\r');
            }
            if ((b2 = (c5 == '/')) || c5 == '>') {
                documentEntityState.endOfSpecifiedAttributes();
                break;
            }
            if (!b3) {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
        }
        if (b2) {
            if (byteToCharMap[bytes[offset + 1] & 0xFF] != '>') {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
            parsedEntity.offset = offset + 2;
            documentEventHandler.startElementEvent(true);
            documentEntityState.endNamespacesScope();
        }
        else {
            parsedEntity.offset = offset + 1;
            documentEventHandler.startElementEvent(false);
            documentEntityState.pushElement();
        }
        return true;
    }
    
    private static boolean scanEndElement(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final QName popElement = documentEntityState.popElement();
        if (popElement == null) {
            documentEntityState.setParameter(0, documentEntityState.currentElement);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 20);
            return false;
        }
        if (popElement.bytes == bytes) {
            int i = popElement.offset;
            while (i < popElement.endOffset) {
                if (bytes[i++] != bytes[offset++]) {
                    final QName qName = new QName();
                    documentEntityState.scanQName(parsedEntity, qName);
                    documentEntityState.setParameter(0, qName);
                    documentEntityState.setParameter(1, popElement);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 14);
                    return false;
                }
            }
            char c = byteToCharMap[bytes[offset] & 0xFF];
            if (c != '>') {
                while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                }
                if (c != '>') {
                    documentEntityState.setParameter(0, popElement);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 18);
                    return false;
                }
            }
            parsedEntity.offset = offset + 1;
            documentEventHandler.endElementEvent();
            documentEntityState.endNamespacesScope();
            return true;
        }
        documentEntityState.setParameter(0, popElement);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 20);
        return false;
    }
    
    private static boolean scanComment(final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final XMLString content = documentEntityState.content;
        if (documentEntityState.scanComment(parsedEntity, content)) {
            if (documentImplementationHandler != null) {
                documentImplementationHandler.comment(content);
            }
            return true;
        }
        return false;
    }
    
    private static boolean scanPI(final DocumentEventHandler documentEventHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final XMLName target = documentEntityState.target;
        final XMLString content = documentEntityState.content;
        if (documentEntityState.scanPITarget(parsedEntity, target) && documentEntityState.scanPIData(parsedEntity, content)) {
            documentEventHandler.processingInstruction(target, content);
            return true;
        }
        return false;
    }
    
    private static boolean scanCDSect(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final XMLString content = documentEntityState.content;
        if (documentImplementationHandler != null) {
            documentImplementationHandler.startCDATA();
        }
        final int n = offset;
        char c = byteToCharMap[bytes[offset] & 0xFF];
        while (true) {
            if (c < '\u0080') {
                switch (contentMap[c]) {
                    case 1:
                    case 2:
                    case 3: {
                        c = byteToCharMap[bytes[++offset] & 0xFF];
                        continue;
                    }
                    case 4: {
                        if (byteToCharMap[bytes[offset + 1] & 0xFF] == ']' && byteToCharMap[bytes[offset + 2] & 0xFF] == '>') {
                            if (offset > n) {
                                content.setValues(bytes, n, offset, singleByteEncodingSupport);
                                documentEventHandler.characters(content);
                            }
                            if (documentImplementationHandler != null) {
                                documentImplementationHandler.endCDATA();
                            }
                            parsedEntity.offset = offset + 3;
                            return true;
                        }
                        c = byteToCharMap[bytes[++offset] & 0xFF];
                        continue;
                    }
                    case 5: {
                        if (offset > n) {
                            content.setValues(bytes, n, offset, singleByteEncodingSupport);
                            documentEventHandler.characters(content);
                        }
                        if (offset == parsedEntity.endOffset) {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 6);
                            return false;
                        }
                        parsedEntity.offset = offset;
                        documentEntityState.setInvalidCharParameter(0, parsedEntity);
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                        return false;
                    }
                    default: {
                        if (offset > n) {
                            content.setValues(bytes, n, offset, singleByteEncodingSupport);
                            documentEventHandler.characters(content);
                        }
                        parsedEntity.offset = offset;
                        documentEntityState.setInvalidCharParameter(0, parsedEntity);
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                        return false;
                    }
                }
            }
            else {
                parsedEntity.offset = offset;
                if (!parsedEntity.skipValidCharacter()) {
                    documentEntityState.setInvalidCharParameter(0, parsedEntity);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                    return false;
                }
                offset = parsedEntity.offset;
                c = byteToCharMap[bytes[offset] & 0xFF];
            }
        }
    }
    
    public static boolean skipDoctypeDecl(final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final QName elementType = documentEntityState.elementType;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c != ' ' && c != '\n' && c != '\t') {
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
            return false;
        }
        char c2;
        do {
            c2 = byteToCharMap[bytes[++offset] & 0xFF];
        } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
        parsedEntity.offset = offset;
        final int scanQName = documentEntityState.scanQName(parsedEntity, elementType);
        int offset2 = parsedEntity.offset;
        if (scanQName == 0) {
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
            return false;
        }
        while (c2 != '[') {
            if (c2 == '>') {
                parsedEntity.offset = offset2 + 1;
                return true;
            }
            if (c2 == '\0') {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                return false;
            }
            c2 = byteToCharMap[bytes[++offset2] & 0xFF];
        }
        char c3;
        do {
            c3 = byteToCharMap[bytes[++offset2] & 0xFF];
            if (c3 == ']') {
                do {
                    c3 = byteToCharMap[bytes[++offset2] & 0xFF];
                } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
                if (c3 == '>') {
                    parsedEntity.offset = offset2 + 1;
                    return true;
                }
                continue;
            }
        } while (c3 != '\0');
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
        return false;
    }
    
    private static boolean checkReferenceInContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        if (((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap[parsedEntity.bytes[parsedEntity.offset] & 0xFF] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = documentEntityState.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                documentEventHandler.character(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityName = documentEntityState.entityName;
            final int scanName = documentEntityState.scanName(parsedEntity, entityName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                return entityReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, entityName);
            }
            if (scanName == 0) {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                return false;
            }
            documentEntityState.setParameter(0, entityName);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            return false;
        }
    }
    
    private static boolean checkReferenceInAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        if (((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap[parsedEntity.bytes[parsedEntity.offset] & 0xFF] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = documentEntityState.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                documentEntityState.attributeValueCharacter(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityName = documentEntityState.entityName;
            final int scanName = documentEntityState.scanName(parsedEntity, entityName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                return entityReferenceInAttValue(documentImplementationHandler, documentEntityState, entityName);
            }
            if (scanName == 0) {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                return false;
            }
            documentEntityState.setParameter(0, entityName);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            return false;
        }
    }
    
    private static boolean entityReferenceInContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final XMLName xmlName) {
        if (documentImplementationHandler != null) {
            return documentImplementationHandler.entityReferenceInContent(xmlName);
        }
        final int checkPredefinedEntities = documentEntityState.checkPredefinedEntities(xmlName);
        if (checkPredefinedEntities != -1) {
            documentEventHandler.character(checkPredefinedEntities, true);
            return true;
        }
        documentEntityState.undeclaredEntityInContent(xmlName);
        return false;
    }
    
    private static boolean entityReferenceInAttValue(final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final XMLName xmlName) {
        if (documentImplementationHandler != null) {
            return documentImplementationHandler.entityReferenceInAttValue(xmlName);
        }
        final int checkPredefinedEntities = documentEntityState.checkPredefinedEntities(xmlName);
        if (checkPredefinedEntities != -1) {
            documentEntityState.attributeValueCharacter(checkPredefinedEntities, true);
            return true;
        }
        documentEntityState.undeclaredEntityInAttValue(xmlName);
        return false;
    }
    
    static {
        markupMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
}
