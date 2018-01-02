// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class SingleByteWFCExternalEntityScanner
{
    private static final int STATE_START = 0;
    private static final int STATE_VERSION = 1;
    private static final int STATE_ENCODING = 2;
    private static final int STATE_STANDALONE = 3;
    private static final int STATE_FINISHED = 4;
    private static final byte[] encNameCharMap;
    
    public static boolean scanXMLDecl(final ExternalEntityHandler externalEntityHandler, final DocumentEntityState documentEntityState, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity) {
        return scanXMLDeclOrTextDecl(externalEntityHandler, documentEntityState, externalEntityState, parsedEntity, false);
    }
    
    public static boolean scanTextDecl(final ExternalEntityHandler externalEntityHandler, final DocumentEntityState documentEntityState, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity) {
        return scanXMLDeclOrTextDecl(externalEntityHandler, documentEntityState, externalEntityState, parsedEntity, true);
    }
    
    private static boolean scanXMLDeclOrTextDecl(final ExternalEntityHandler externalEntityHandler, final DocumentEntityState documentEntityState, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity, final boolean b) {
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int n = 0;
        if (byteToCharMap[bytes[offset] & 0xFF] != '<' || byteToCharMap[bytes[offset + 1] & 0xFF] != '?' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'x' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'm' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'l') {
            return true;
        }
        offset += 5;
        final char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c != ' ' && c != '\n' && c != '\t') {
            return true;
        }
        externalEntityState.reset();
        char c2 = '\0';
        Label_2120: {
            XMLString setStandalone = null;
            Label_1949: {
                XMLString setVersion = null;
                Label_1360: {
                    int n2;
                    int n3;
                    char c4;
                    while (true) {
                        c2 = byteToCharMap[bytes[++offset] & 0xFF];
                        if (c2 != ' ' && c2 != '\n' && c2 != '\t') {
                            n2 = offset;
                            if (b) {
                                if (n == 0 && c2 == 'v' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'e' && byteToCharMap[bytes[offset + 2] & 0xFF] == 'r' && byteToCharMap[bytes[offset + 3] & 0xFF] == 's' && byteToCharMap[bytes[offset + 4] & 0xFF] == 'i' && byteToCharMap[bytes[offset + 5] & 0xFF] == 'o' && byteToCharMap[bytes[offset + 6] & 0xFF] == 'n') {
                                    offset += 7;
                                    n = 1;
                                }
                                else {
                                    if (c2 != 'e' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'c' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'o' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'd' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'i' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 7] & 0xFF] != 'g') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 45);
                                        return false;
                                    }
                                    offset += 8;
                                    n = 2;
                                }
                            }
                            else if (n == 0) {
                                if (c2 != 'v' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'e' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'r' || byteToCharMap[bytes[offset + 3] & 0xFF] != 's' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'i' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'o' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'n') {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 10);
                                    return false;
                                }
                                offset += 7;
                                n = 1;
                            }
                            else if (n != 1 || c2 != 'e' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'c' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'o' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'd' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'i' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 7] & 0xFF] != 'g') {
                                if (c2 != 's' || byteToCharMap[bytes[offset + 1] & 0xFF] != 't' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'a' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'd' || byteToCharMap[bytes[offset + 5] & 0xFF] != 'a' || byteToCharMap[bytes[offset + 6] & 0xFF] != 'l' || byteToCharMap[bytes[offset + 7] & 0xFF] != 'o' || byteToCharMap[bytes[offset + 8] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 9] & 0xFF] != 'e') {
                                    break Label_2120;
                                }
                                offset += 10;
                                n = 3;
                            }
                            else {
                                offset += 8;
                                n = 2;
                            }
                            n3 = offset;
                            char c3;
                            for (c3 = byteToCharMap[bytes[offset] & 0xFF]; c3 == ' ' || c3 == '\n' || c3 == '\t'; c3 = byteToCharMap[bytes[++offset] & 0xFF]) {}
                            if (c3 != '=') {
                                final int n4 = b ? 41 : 7;
                                final XMLString content = documentEntityState.content;
                                content.setValues(bytes, n2, n3, singleByteEncodingSupport);
                                documentEntityState.setParameter(0, content);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n4);
                                return false;
                            }
                            do {
                                c2 = byteToCharMap[bytes[++offset] & 0xFF];
                            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                            c4 = c2;
                            if (c4 != '\'' && c4 != '\"') {
                                final int n5 = b ? 42 : 8;
                                final XMLString content2 = documentEntityState.content;
                                content2.setValues(bytes, n2, n3, singleByteEncodingSupport);
                                documentEntityState.setParameter(0, content2);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n5);
                                return false;
                            }
                            ++offset;
                            switch (n) {
                                case 1: {
                                    setVersion = externalEntityState.setVersion(bytes, offset, 0, singleByteEncodingSupport);
                                    if (byteToCharMap[bytes[offset] & 0xFF] != '1' || byteToCharMap[bytes[offset + 1] & 0xFF] != '.' || byteToCharMap[bytes[offset + 2] & 0xFF] != '0' || byteToCharMap[bytes[offset + 3] & 0xFF] != c4) {
                                        break Label_1360;
                                    }
                                    offset += 3;
                                    setVersion.endOffset = offset;
                                    c2 = byteToCharMap[bytes[++offset] & 0xFF];
                                    if (c2 == ' ' || c2 == '\n' || c2 == '\t') {
                                        break;
                                    }
                                    if (b) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 43);
                                        return false;
                                    }
                                    n = 4;
                                    break;
                                }
                                case 2: {
                                    final XMLString setEncName = externalEntityState.setEncName(bytes, offset, 0, singleByteEncodingSupport);
                                    char c5;
                                    do {
                                        c5 = byteToCharMap[bytes[++offset] & 0xFF];
                                        if (c5 == '\0' && offset == parsedEntity.endOffset) {
                                            final int n6 = b ? 42 : 8;
                                            final XMLString content3 = documentEntityState.content;
                                            content3.setValues(bytes, n2, n3, singleByteEncodingSupport);
                                            documentEntityState.setParameter(0, content3);
                                            parsedEntity.offset = offset;
                                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n6);
                                            return false;
                                        }
                                    } while (c5 != c4);
                                    setEncName.endOffset = offset;
                                    parsedEntity.offset = offset;
                                    if (!validEncName(setEncName)) {
                                        documentEntityState.setParameter(0, setEncName);
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 46);
                                        return false;
                                    }
                                    c2 = byteToCharMap[bytes[++offset] & 0xFF];
                                    if (c2 != ' ' && c2 != '\n' && c2 != '\t') {
                                        n = 4;
                                        break;
                                    }
                                    if (b) {
                                        do {
                                            c2 = byteToCharMap[bytes[++offset] & 0xFF];
                                        } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                                        n = 4;
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    setStandalone = externalEntityState.setStandalone(bytes, offset, 0, singleByteEncodingSupport);
                                    if (byteToCharMap[bytes[offset] & 0xFF] == 'y' && byteToCharMap[bytes[offset + 1] & 0xFF] == 'e' && byteToCharMap[bytes[offset + 2] & 0xFF] == 's' && byteToCharMap[bytes[offset + 3] & 0xFF] == c4) {
                                        offset += 3;
                                        setStandalone.endOffset = offset;
                                    }
                                    else {
                                        if (byteToCharMap[bytes[offset] & 0xFF] != 'n' || byteToCharMap[bytes[offset + 1] & 0xFF] != 'o' || byteToCharMap[bytes[offset + 2] & 0xFF] != c4) {
                                            break Label_1949;
                                        }
                                        offset += 2;
                                        setStandalone.endOffset = offset;
                                    }
                                    do {
                                        c2 = byteToCharMap[bytes[++offset] & 0xFF];
                                    } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                                    n = 4;
                                    break;
                                }
                            }
                            if (n == 4) {
                                break Label_2120;
                            }
                            continue;
                        }
                    }
                    char c6;
                    do {
                        c6 = byteToCharMap[bytes[++offset] & 0xFF];
                        if (c6 == '\0' && offset == parsedEntity.endOffset) {
                            final int n7 = b ? 42 : 8;
                            final XMLString content4 = documentEntityState.content;
                            content4.setValues(bytes, n2, n3, singleByteEncodingSupport);
                            documentEntityState.setParameter(0, content4);
                            parsedEntity.offset = offset;
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n7);
                            return false;
                        }
                    } while (c6 != c4);
                }
                setVersion.endOffset = offset;
                documentEntityState.setParameter(0, setVersion);
                parsedEntity.offset = offset;
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 34);
                return false;
                char c4 = '\0';
                char c7;
                do {
                    c7 = byteToCharMap[bytes[++offset] & 0xFF];
                    if (c7 == '\0' && offset == parsedEntity.endOffset) {
                        final int n8 = b ? 42 : 8;
                        final XMLString content5 = documentEntityState.content;
                        final int n2;
                        final int n3;
                        content5.setValues(bytes, n2, n3, singleByteEncodingSupport);
                        documentEntityState.setParameter(0, content5);
                        parsedEntity.offset = offset;
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n8);
                        return false;
                    }
                } while (c7 != c4);
            }
            setStandalone.endOffset = offset;
            documentEntityState.setParameter(0, setStandalone);
            parsedEntity.offset = offset;
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 13);
            return false;
        }
        if (c2 != '?' || byteToCharMap[bytes[offset + 1] & 0xFF] != '>') {
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", b ? 44 : 9);
            return false;
        }
        parsedEntity.offset = offset + 2;
        if (b) {
            externalEntityHandler.textDeclEvent();
        }
        else {
            externalEntityHandler.xmlDeclEvent();
        }
        return true;
    }
    
    private static boolean validEncName(final XMLString xmlString) {
        int i = xmlString.offset;
        final int endOffset = xmlString.endOffset;
        if (i < endOffset) {
            final char[] byteToCharMap = ((SingleByteEncodingSupport)xmlString.encoding).byteToCharMap;
            final byte[] bytes = xmlString.bytes;
            final char c = byteToCharMap[bytes[i++] & 0xFF];
            if (c < '\u0080' && SingleByteWFCExternalEntityScanner.encNameCharMap[c] == 1) {
                while (i < endOffset) {
                    final char c2 = byteToCharMap[bytes[i++] & 0xFF];
                    if (c2 >= '\u0080' || SingleByteWFCExternalEntityScanner.encNameCharMap[c2] == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    static {
        encNameCharMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
}
