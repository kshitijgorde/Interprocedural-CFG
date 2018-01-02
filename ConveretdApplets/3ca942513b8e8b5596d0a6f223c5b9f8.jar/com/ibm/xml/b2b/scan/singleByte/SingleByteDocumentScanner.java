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

public final class SingleByteDocumentScanner
{
    public static boolean scanDocument(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int n = 0;
        while (true) {
            for (char c = byteToCharMap[bytes[offset] & 0xFF]; c != '<'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
            final char c2 = byteToCharMap[bytes[offset + 1] & 0xFF];
            if (c2 != '!') {
                if (c2 != '?') {
                    parsedEntity.offset = offset;
                    if (scanContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                        int n2 = parsedEntity.offset;
                    Block_9:
                        while (true) {
                            for (char c3 = byteToCharMap[bytes[n2] & 0xFF]; c3 != '<'; c3 = byteToCharMap[bytes[++n2] & 0xFF]) {
                                if (c3 == '\0') {
                                    break Block_9;
                                }
                            }
                            if (byteToCharMap[bytes[++n2] & 0xFF] == '?') {
                                parsedEntity.offset = n2 + 1;
                                n2 = scanPI(documentEventHandler, documentEntityState, parsedEntity);
                            }
                            else {
                                parsedEntity.offset = n2 + 3;
                                n2 = scanComment(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity);
                            }
                        }
                        return true;
                    }
                    return false;
                }
                else {
                    parsedEntity.offset = offset + 2;
                    offset = scanPI(documentEventHandler, documentEntityState, parsedEntity);
                }
            }
            else {
                final char c4 = byteToCharMap[bytes[offset + 2] & 0xFF];
                if (n != 0 || c4 == '-') {
                    parsedEntity.offset = offset + 4;
                    offset = scanComment(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity);
                }
                else {
                    n = 1;
                    parsedEntity.offset = offset + 9;
                    if (documentImplementationHandler != null) {
                        if (!documentImplementationHandler.scanDoctypeDecl(parsedEntity)) {
                            return false;
                        }
                        if (!documentImplementationHandler.scanExternalSubset()) {
                            return false;
                        }
                        offset = parsedEntity.offset;
                    }
                    else {
                        offset = skipDoctypeDecl(parsedEntity);
                    }
                }
            }
        }
    }
    
    public static boolean scanContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        while (true) {
            final int n2 = n;
            char c;
            for (c = byteToCharMap[bytes[n] & 0xFF]; c != '<' && c != '&' && c != '\0'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
            if (n > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(bytes, n2, n, singleByteEncodingSupport);
                documentEventHandler.characters(content);
            }
            if (c == '<') {
                final char c2 = byteToCharMap[bytes[++n] & 0xFF];
                if (c2 == '?') {
                    parsedEntity.offset = n + 1;
                    n = scanPI(documentEventHandler, documentEntityState, parsedEntity);
                }
                else if (c2 == '!') {
                    if (byteToCharMap[bytes[++n] & 0xFF] == '-') {
                        parsedEntity.offset = n + 2;
                        n = scanComment(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity);
                    }
                    else {
                        if (documentImplementationHandler != null) {
                            documentImplementationHandler.startCDATA();
                        }
                        n += 7;
                        final int n3 = n;
                        while (byteToCharMap[bytes[n] & 0xFF] != ']' || byteToCharMap[bytes[n + 1] & 0xFF] != ']' || byteToCharMap[bytes[n + 2] & 0xFF] != '>') {
                            ++n;
                        }
                        if (n > n3) {
                            final XMLString content2 = documentEntityState.content;
                            content2.setValues(bytes, n3, n, singleByteEncodingSupport);
                            documentEventHandler.characters(content2);
                        }
                        if (documentImplementationHandler != null) {
                            documentImplementationHandler.endCDATA();
                        }
                        n += 3;
                    }
                }
                else if (c2 == '/') {
                    ++n;
                    final QName popElement = documentEntityState.popElement();
                    n += popElement.endOffset - popElement.offset;
                    for (char c3 = byteToCharMap[bytes[n] & 0xFF]; c3 != '>'; c3 = byteToCharMap[bytes[++n] & 0xFF]) {}
                    ++n;
                    documentEventHandler.endElementEvent();
                    documentEntityState.endNamespacesScope();
                    if (documentEntityState.elementDepth == 0) {
                        parsedEntity.offset = n;
                        return true;
                    }
                    continue;
                }
                else {
                    boolean b = false;
                    boolean b2 = false;
                    final QName elementType = documentEntityState.elementType;
                    elementType.setValues(bytes, n, 0, singleByteEncodingSupport);
                    while (true) {
                        final char c4 = byteToCharMap[bytes[++n] & 0xFF];
                        if (c4 == '>') {
                            elementType.endOffset = n;
                            ++n;
                            break;
                        }
                        if (c4 == '/') {
                            elementType.endOffset = n;
                            n += 2;
                            b = true;
                            break;
                        }
                        if (c4 == ' ' || c4 == '\n' || c4 == '\t') {
                            elementType.endOffset = n;
                            char c5;
                            do {
                                c5 = byteToCharMap[bytes[++n] & 0xFF];
                            } while (c5 == ' ' || c5 == '\n' || c5 == '\t');
                            if (c5 == '>') {
                                ++n;
                                break;
                            }
                            if (c5 == '/') {
                                n += 2;
                                b = true;
                                break;
                            }
                            b2 = true;
                            break;
                        }
                        else {
                            if (c4 != ':') {
                                continue;
                            }
                            elementType.sepOffset = n;
                        }
                    }
                    documentEntityState.createQNameSymbols(documentEntityState.elementType);
                    documentEntityState.processElementType();
                    while (b2) {
                        final QName attributeName = documentEntityState.attributeName;
                        attributeName.setValues(bytes, n, 0, singleByteEncodingSupport);
                        char c6;
                        while (true) {
                            c6 = byteToCharMap[bytes[++n] & 0xFF];
                            if (c6 == '=' || c6 == ' ' || c6 == '\n' || c6 == '\t') {
                                break;
                            }
                            if (c6 != ':') {
                                continue;
                            }
                            attributeName.sepOffset = n;
                        }
                        attributeName.endOffset = n;
                        documentEntityState.createQNameSymbols(documentEntityState.attributeName);
                        documentEntityState.processAttributeName(attributeName, true);
                        while (c6 != '=') {
                            c6 = byteToCharMap[bytes[++n] & 0xFF];
                        }
                        char c7;
                        for (c7 = byteToCharMap[bytes[++n] & 0xFF]; c7 != '\'' && c7 != '\"'; c7 = byteToCharMap[bytes[++n] & 0xFF]) {}
                        final char c8 = c7;
                        ++n;
                        parsedEntity.offset = n;
                        scanAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity, c8);
                        char c9;
                        for (n = parsedEntity.offset, c9 = byteToCharMap[bytes[n] & 0xFF]; c9 == ' ' || c9 == '\n' || c9 == '\t'; c9 = byteToCharMap[bytes[++n] & 0xFF]) {}
                        if (c9 == '>' || c9 == '/') {
                            b = (c9 == '/');
                            documentEntityState.endOfSpecifiedAttributes();
                            if (b) {
                                n += 2;
                                break;
                            }
                            ++n;
                            break;
                        }
                    }
                    if (b) {
                        documentEventHandler.startElementEvent(true);
                        documentEntityState.endNamespacesScope();
                        if (documentEntityState.elementDepth == 0) {
                            parsedEntity.offset = n;
                            return true;
                        }
                        continue;
                    }
                    else {
                        documentEventHandler.startElementEvent(false);
                        documentEntityState.pushElement();
                    }
                }
            }
            else {
                if (c != '&') {
                    parsedEntity.offset = n;
                    return true;
                }
                char c10 = byteToCharMap[bytes[++n] & 0xFF];
                if (c10 == '#') {
                    final char c11 = byteToCharMap[bytes[++n] & 0xFF];
                    int n4;
                    if (c11 == 'x') {
                        final char c12 = byteToCharMap[bytes[++n] & 0xFF];
                        final char c13;
                        n4 = c13 - (((c13 = c12) < 'A') ? 48 : (((c12 < 'a') ? 65 : 97) - 10));
                        while (true) {
                            final char c14 = byteToCharMap[bytes[++n] & 0xFF];
                            if (c14 == ';') {
                                break;
                            }
                            final char c15;
                            n4 = (n4 << 4) + (c15 - (((c15 = c14) < 'A') ? 48 : (((c14 < 'a') ? 65 : 97) - 10)));
                        }
                        ++n;
                    }
                    else {
                        n4 = c11 - '0';
                        while (true) {
                            final char c16 = byteToCharMap[bytes[++n] & 0xFF];
                            if (c16 == ';') {
                                break;
                            }
                            n4 = n4 * 10 + (c16 - '0');
                        }
                        ++n;
                    }
                    documentEventHandler.character(n4, false);
                }
                else {
                    final XMLName entityName = documentEntityState.entityName;
                    final int n5 = n;
                    while (c10 != ';') {
                        c10 = byteToCharMap[bytes[++n] & 0xFF];
                    }
                    entityName.setValues(bytes, n5, n, singleByteEncodingSupport);
                    ++n;
                    entityReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, entityName);
                }
            }
        }
    }
    
    public static boolean scanAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity, final int n) {
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        char c;
        while (true) {
            final int n2 = offset;
            c = byteToCharMap[bytes[offset] & 0xFF];
            boolean b = true;
            while (c != n && c != '&') {
                if (c == '\t' || c == '\n' || c == '\r') {
                    b = false;
                }
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
            if (offset > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(bytes, n2, offset, singleByteEncodingSupport);
                documentEntityState.attributeValueCharacters(content, b);
            }
            if (c == n) {
                break;
            }
            char c2 = byteToCharMap[bytes[++offset] & 0xFF];
            if (c2 == '#') {
                final char c3 = byteToCharMap[bytes[++offset] & 0xFF];
                int n3;
                if (c3 == 'x') {
                    final char c4 = byteToCharMap[bytes[++offset] & 0xFF];
                    final char c5;
                    n3 = c5 - (((c5 = c4) < 'A') ? 48 : (((c4 < 'a') ? 65 : 97) - 10));
                    while (true) {
                        final char c6 = byteToCharMap[bytes[++offset] & 0xFF];
                        if (c6 == ';') {
                            break;
                        }
                        final char c7;
                        n3 = (n3 << 4) + (c7 - (((c7 = c6) < 'A') ? 48 : (((c6 < 'a') ? 65 : 97) - 10)));
                    }
                    ++offset;
                }
                else {
                    n3 = c3 - '0';
                    while (true) {
                        final char c8 = byteToCharMap[bytes[++offset] & 0xFF];
                        if (c8 == ';') {
                            break;
                        }
                        n3 = n3 * 10 + (c8 - '0');
                    }
                    ++offset;
                }
                documentEntityState.attributeValueCharacter(n3, false);
            }
            else {
                final XMLName entityName = documentEntityState.entityName;
                final int n4 = offset;
                while (c2 != ';') {
                    c2 = byteToCharMap[bytes[++offset] & 0xFF];
                }
                entityName.setValues(bytes, n4, offset, singleByteEncodingSupport);
                ++offset;
                entityReferenceInAttValue(documentImplementationHandler, documentEntityState, entityName);
            }
        }
        if (c != '\0') {
            parsedEntity.offset = offset + 1;
        }
        else {
            parsedEntity.offset = offset;
        }
        return true;
    }
    
    private static int scanComment(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        for (char c = byteToCharMap[bytes[offset] & 0xFF]; c != '-' || byteToCharMap[bytes[offset + 1] & 0xFF] != '-'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
        content.setValues(bytes, n, offset, parsedEntity.encoding);
        if (documentImplementationHandler != null) {
            documentImplementationHandler.comment(content);
        }
        return offset + 3;
    }
    
    private static int scanPI(final DocumentEventHandler documentEventHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final XMLName target = documentEntityState.target;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        boolean b = false;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        while (true) {
            char c2;
            do {
                c2 = byteToCharMap[bytes[++offset] & 0xFF];
                if (c2 == '?') {
                    target.setValues(bytes, n, offset, parsedEntity.encoding);
                    int n2;
                    if (b) {
                        char c3;
                        do {
                            c3 = byteToCharMap[bytes[++offset] & 0xFF];
                        } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
                        n2 = offset;
                        while (c3 != '?' || byteToCharMap[bytes[offset + 1] & 0xFF] != '>') {
                            c3 = byteToCharMap[bytes[++offset] & 0xFF];
                        }
                    }
                    else {
                        n2 = offset;
                    }
                    content.setValues(bytes, n2, offset, parsedEntity.encoding);
                    documentEventHandler.processingInstruction(target, content);
                    return offset + 2;
                }
            } while (c2 != ' ' && c2 != '\n' && c2 != '\t');
            b = true;
            continue;
        }
    }
    
    private static int skipDoctypeDecl(final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        char c;
        do {
            c = byteToCharMap[bytes[++offset] & 0xFF];
            if (c == '[') {
                while (true) {
                    if (byteToCharMap[bytes[++offset] & 0xFF] == ']') {
                        do {
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        } while (c == ' ' || c == '\n' || c == '\t');
                        if (c == '>') {
                            break;
                        }
                        continue;
                    }
                }
            }
        } while (c != '>');
        return ++offset;
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
}
