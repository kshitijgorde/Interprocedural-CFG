// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.DocumentImplementationHandler;
import com.ibm.xml.b2b.scan.DocumentEventHandler;

public final class UTF16DocumentScanner
{
    public static boolean scanDocument(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        int n = 0;
        while (true) {
            for (char c = chars[offset]; c != '<'; c = chars[++offset]) {}
            final char c2 = chars[offset + 1];
            if (c2 != '!') {
                if (c2 != '?') {
                    parsedEntity.offset = offset;
                    if (scanContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                        int n2 = parsedEntity.offset;
                    Block_9:
                        while (true) {
                            for (char c3 = chars[n2]; c3 != '<'; c3 = chars[++n2]) {
                                if (c3 == '\0') {
                                    break Block_9;
                                }
                            }
                            if (chars[++n2] == '?') {
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
                final char c4 = chars[offset + 2];
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
        final char[] chars = parsedEntity.chars;
        int n = parsedEntity.offset;
        while (true) {
            final int n2 = n;
            char c;
            for (c = chars[n]; c != '<' && c != '&' && c != '\0'; c = chars[++n]) {}
            if (n > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(chars, n2, n);
                documentEventHandler.characters(content);
            }
            if (c == '<') {
                final char c2 = chars[++n];
                if (c2 == '?') {
                    parsedEntity.offset = n + 1;
                    n = scanPI(documentEventHandler, documentEntityState, parsedEntity);
                }
                else if (c2 == '!') {
                    if (chars[++n] == '-') {
                        parsedEntity.offset = n + 2;
                        n = scanComment(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity);
                    }
                    else {
                        if (documentImplementationHandler != null) {
                            documentImplementationHandler.startCDATA();
                        }
                        n += 7;
                        final int n3 = n;
                        while (chars[n] != ']' || chars[n + 1] != ']' || chars[n + 2] != '>') {
                            ++n;
                        }
                        if (n > n3) {
                            final XMLString content2 = documentEntityState.content;
                            content2.setValues(chars, n3, n);
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
                    for (char c3 = chars[n]; c3 != '>'; c3 = chars[++n]) {}
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
                    elementType.setValues(chars, n, 0);
                    while (true) {
                        final char c4 = chars[++n];
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
                                c5 = chars[++n];
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
                        attributeName.setValues(chars, n, 0);
                        char c6;
                        while (true) {
                            c6 = chars[++n];
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
                            c6 = chars[++n];
                        }
                        char c7;
                        for (c7 = chars[++n]; c7 != '\'' && c7 != '\"'; c7 = chars[++n]) {}
                        final char c8 = c7;
                        ++n;
                        parsedEntity.offset = n;
                        scanAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity, c8);
                        char c9;
                        for (n = parsedEntity.offset, c9 = chars[n]; c9 == ' ' || c9 == '\n' || c9 == '\t'; c9 = chars[++n]) {}
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
                char c10 = chars[++n];
                if (c10 == '#') {
                    final char c11 = chars[++n];
                    int n4;
                    if (c11 == 'x') {
                        final char c12 = chars[++n];
                        final char c13;
                        n4 = c13 - (((c13 = c12) < 'A') ? 48 : (((c12 < 'a') ? 65 : 97) - 10));
                        while (true) {
                            final char c14 = chars[++n];
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
                            final char c16 = chars[++n];
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
                        c10 = chars[++n];
                    }
                    entityName.setValues(chars, n5, n);
                    ++n;
                    entityReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, entityName);
                }
            }
        }
    }
    
    public static boolean scanAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        char c;
        while (true) {
            final int n2 = offset;
            c = chars[offset];
            boolean b = true;
            while (c != n && c != '&') {
                if (c == '\t' || c == '\n' || c == '\r') {
                    b = false;
                }
                c = chars[++offset];
            }
            if (offset > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(chars, n2, offset);
                documentEntityState.attributeValueCharacters(content, b);
            }
            if (c == n) {
                break;
            }
            char c2 = chars[++offset];
            if (c2 == '#') {
                final char c3 = chars[++offset];
                int n3;
                if (c3 == 'x') {
                    final char c4 = chars[++offset];
                    final char c5;
                    n3 = c5 - (((c5 = c4) < 'A') ? 48 : (((c4 < 'a') ? 65 : 97) - 10));
                    while (true) {
                        final char c6 = chars[++offset];
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
                        final char c8 = chars[++offset];
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
                    c2 = chars[++offset];
                }
                entityName.setValues(chars, n4, offset);
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        for (char c = chars[offset]; c != '-' || chars[offset + 1] != '-'; c = chars[++offset]) {}
        content.setValues(chars, n, offset);
        if (documentImplementationHandler != null) {
            documentImplementationHandler.comment(content);
        }
        return offset + 3;
    }
    
    private static int scanPI(final DocumentEventHandler documentEventHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final XMLName target = documentEntityState.target;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        boolean b = false;
        final char c = chars[offset];
        while (true) {
            char c2;
            do {
                c2 = chars[++offset];
                if (c2 == '?') {
                    target.setValues(chars, n, offset);
                    int n2;
                    if (b) {
                        char c3;
                        do {
                            c3 = chars[++offset];
                        } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
                        n2 = offset;
                        while (c3 != '?' || chars[offset + 1] != '>') {
                            c3 = chars[++offset];
                        }
                    }
                    else {
                        n2 = offset;
                    }
                    content.setValues(chars, n2, offset);
                    documentEventHandler.processingInstruction(target, content);
                    return offset + 2;
                }
            } while (c2 != ' ' && c2 != '\n' && c2 != '\t');
            b = true;
            continue;
        }
    }
    
    private static int skipDoctypeDecl(final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        char c;
        do {
            c = chars[++offset];
            if (c == '[') {
                while (true) {
                    if (chars[++offset] == ']') {
                        do {
                            c = chars[++offset];
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
