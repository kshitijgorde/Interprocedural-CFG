// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.DocumentImplementationHandler;
import com.ibm.xml.b2b.scan.DocumentEventHandler;

public final class LatinDocumentScanner
{
    public static boolean scanDocument(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int n = 0;
        while (true) {
            for (byte b = bytes[offset]; b != 60; b = bytes[++offset]) {}
            final byte b2 = bytes[offset + 1];
            if (b2 != 33) {
                if (b2 != 63) {
                    parsedEntity.offset = offset;
                    if (scanContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                        int n2 = parsedEntity.offset;
                    Block_9:
                        while (true) {
                            for (byte b3 = bytes[n2]; b3 != 60; b3 = bytes[++n2]) {
                                if (b3 == 0) {
                                    break Block_9;
                                }
                            }
                            if (bytes[++n2] == 63) {
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
                final byte b4 = bytes[offset + 2];
                if (n != 0 || b4 == 45) {
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
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        while (true) {
            final int n2 = n;
            byte b;
            for (b = bytes[n]; b != 60 && b != 38 && b != 0; b = bytes[++n]) {}
            if (n > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(bytes, n2, n, encoding);
                documentEventHandler.characters(content);
            }
            if (b == 60) {
                final byte b2 = bytes[++n];
                if (b2 == 63) {
                    parsedEntity.offset = n + 1;
                    n = scanPI(documentEventHandler, documentEntityState, parsedEntity);
                }
                else if (b2 == 33) {
                    if (bytes[++n] == 45) {
                        parsedEntity.offset = n + 2;
                        n = scanComment(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity);
                    }
                    else {
                        if (documentImplementationHandler != null) {
                            documentImplementationHandler.startCDATA();
                        }
                        n += 7;
                        final int n3 = n;
                        while (bytes[n] != 93 || bytes[n + 1] != 93 || bytes[n + 2] != 62) {
                            ++n;
                        }
                        if (n > n3) {
                            final XMLString content2 = documentEntityState.content;
                            content2.setValues(bytes, n3, n, encoding);
                            documentEventHandler.characters(content2);
                        }
                        if (documentImplementationHandler != null) {
                            documentImplementationHandler.endCDATA();
                        }
                        n += 3;
                    }
                }
                else if (b2 == 47) {
                    ++n;
                    final QName popElement = documentEntityState.popElement();
                    n += popElement.endOffset - popElement.offset;
                    for (byte b3 = bytes[n]; b3 != 62; b3 = bytes[++n]) {}
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
                    boolean b4 = false;
                    boolean b5 = false;
                    final QName elementType = documentEntityState.elementType;
                    elementType.setValues(bytes, n, 0, encoding);
                    while (true) {
                        final byte b6 = bytes[++n];
                        if (b6 == 62) {
                            elementType.endOffset = n;
                            ++n;
                            break;
                        }
                        if (b6 == 47) {
                            elementType.endOffset = n;
                            n += 2;
                            b4 = true;
                            break;
                        }
                        if (b6 == 32 || b6 == 10 || b6 == 9) {
                            elementType.endOffset = n;
                            byte b7;
                            do {
                                b7 = bytes[++n];
                            } while (b7 == 32 || b7 == 10 || b7 == 9);
                            if (b7 == 62) {
                                ++n;
                                break;
                            }
                            if (b7 == 47) {
                                n += 2;
                                b4 = true;
                                break;
                            }
                            b5 = true;
                            break;
                        }
                        else {
                            if (b6 != 58) {
                                continue;
                            }
                            elementType.sepOffset = n;
                        }
                    }
                    documentEntityState.createQNameSymbols(documentEntityState.elementType);
                    documentEntityState.processElementType();
                    while (b5) {
                        final QName attributeName = documentEntityState.attributeName;
                        attributeName.setValues(bytes, n, 0, encoding);
                        byte b8;
                        while (true) {
                            b8 = bytes[++n];
                            if (b8 == 61 || b8 == 32 || b8 == 10 || b8 == 9) {
                                break;
                            }
                            if (b8 != 58) {
                                continue;
                            }
                            attributeName.sepOffset = n;
                        }
                        attributeName.endOffset = n;
                        documentEntityState.createQNameSymbols(documentEntityState.attributeName);
                        documentEntityState.processAttributeName(attributeName, true);
                        while (b8 != 61) {
                            b8 = bytes[++n];
                        }
                        byte b9;
                        for (b9 = bytes[++n]; b9 != 39 && b9 != 34; b9 = bytes[++n]) {}
                        final byte b10 = b9;
                        ++n;
                        parsedEntity.offset = n;
                        scanAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity, b10);
                        byte b11;
                        for (n = parsedEntity.offset, b11 = bytes[n]; b11 == 32 || b11 == 10 || b11 == 9; b11 = bytes[++n]) {}
                        if (b11 == 62 || b11 == 47) {
                            b4 = (b11 == 47);
                            documentEntityState.endOfSpecifiedAttributes();
                            if (b4) {
                                n += 2;
                                break;
                            }
                            ++n;
                            break;
                        }
                    }
                    if (b4) {
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
                if (b != 38) {
                    parsedEntity.offset = n;
                    return true;
                }
                byte b12 = bytes[++n];
                if (b12 == 35) {
                    final byte b13 = bytes[++n];
                    int n4;
                    if (b13 == 120) {
                        final byte b14 = bytes[++n];
                        final byte b15;
                        n4 = b15 - (((b15 = b14) < 65) ? 48 : (((b14 < 97) ? 65 : 97) - 10));
                        while (true) {
                            final byte b16 = bytes[++n];
                            if (b16 == 59) {
                                break;
                            }
                            final byte b17;
                            n4 = (n4 << 4) + (b17 - (((b17 = b16) < 65) ? 48 : (((b16 < 97) ? 65 : 97) - 10)));
                        }
                        ++n;
                    }
                    else {
                        n4 = b13 - 48;
                        while (true) {
                            final byte b18 = bytes[++n];
                            if (b18 == 59) {
                                break;
                            }
                            n4 = n4 * 10 + (b18 - 48);
                        }
                        ++n;
                    }
                    documentEventHandler.character(n4, false);
                }
                else {
                    final XMLName entityName = documentEntityState.entityName;
                    final int n5 = n;
                    while (b12 != 59) {
                        b12 = bytes[++n];
                    }
                    entityName.setValues(bytes, n5, n, encoding);
                    ++n;
                    entityReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, entityName);
                }
            }
        }
    }
    
    public static boolean scanAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        byte b;
        while (true) {
            final int n2 = offset;
            b = bytes[offset];
            boolean b2 = true;
            while (b != n && b != 38) {
                if (b == 9 || b == 10 || b == 13) {
                    b2 = false;
                }
                b = bytes[++offset];
            }
            if (offset > n2) {
                final XMLString content = documentEntityState.content;
                content.setValues(bytes, n2, offset, encoding);
                documentEntityState.attributeValueCharacters(content, b2);
            }
            if (b == n) {
                break;
            }
            byte b3 = bytes[++offset];
            if (b3 == 35) {
                final byte b4 = bytes[++offset];
                int n3;
                if (b4 == 120) {
                    final byte b5 = bytes[++offset];
                    final byte b6;
                    n3 = b6 - (((b6 = b5) < 65) ? 48 : (((b5 < 97) ? 65 : 97) - 10));
                    while (true) {
                        final byte b7 = bytes[++offset];
                        if (b7 == 59) {
                            break;
                        }
                        final byte b8;
                        n3 = (n3 << 4) + (b8 - (((b8 = b7) < 65) ? 48 : (((b7 < 97) ? 65 : 97) - 10)));
                    }
                    ++offset;
                }
                else {
                    n3 = b4 - 48;
                    while (true) {
                        final byte b9 = bytes[++offset];
                        if (b9 == 59) {
                            break;
                        }
                        n3 = n3 * 10 + (b9 - 48);
                    }
                    ++offset;
                }
                documentEntityState.attributeValueCharacter(n3, false);
            }
            else {
                final XMLName entityName = documentEntityState.entityName;
                final int n4 = offset;
                while (b3 != 59) {
                    b3 = bytes[++offset];
                }
                entityName.setValues(bytes, n4, offset, encoding);
                ++offset;
                entityReferenceInAttValue(documentImplementationHandler, documentEntityState, entityName);
            }
        }
        if (b != 0) {
            parsedEntity.offset = offset + 1;
        }
        else {
            parsedEntity.offset = offset;
        }
        return true;
    }
    
    private static int scanComment(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        for (byte b = bytes[offset]; b != 45 || bytes[offset + 1] != 45; b = bytes[++offset]) {}
        content.setValues(bytes, n, offset, encoding);
        if (documentImplementationHandler != null) {
            documentImplementationHandler.comment(content);
        }
        return offset + 3;
    }
    
    private static int scanPI(final DocumentEventHandler documentEventHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        final XMLName target = documentEntityState.target;
        final XMLString content = documentEntityState.content;
        final int n = offset;
        boolean b = false;
        final byte b2 = bytes[offset];
        while (true) {
            byte b3;
            do {
                b3 = bytes[++offset];
                if (b3 == 63) {
                    target.setValues(bytes, n, offset, encoding);
                    int n2;
                    if (b) {
                        byte b4;
                        do {
                            b4 = bytes[++offset];
                        } while (b4 == 32 || b4 == 10 || b4 == 9);
                        n2 = offset;
                        while (b4 != 63 || bytes[offset + 1] != 62) {
                            b4 = bytes[++offset];
                        }
                    }
                    else {
                        n2 = offset;
                    }
                    content.setValues(bytes, n2, offset, encoding);
                    documentEventHandler.processingInstruction(target, content);
                    return offset + 2;
                }
            } while (b3 != 32 && b3 != 10 && b3 != 9);
            b = true;
            continue;
        }
    }
    
    private static int skipDoctypeDecl(final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        byte b;
        do {
            b = bytes[++offset];
            if (b == 91) {
                while (true) {
                    if (bytes[++offset] == 93) {
                        do {
                            b = bytes[++offset];
                        } while (b == 32 || b == 10 || b == 9);
                        if (b == 62) {
                            break;
                        }
                        continue;
                    }
                }
            }
        } while (b != 62);
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
