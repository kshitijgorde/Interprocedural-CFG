// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class UTF16WFCExternalEntityScanner
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        int n = 0;
        if (chars[offset] != '<' || chars[offset + 1] != '?' || chars[offset + 2] != 'x' || chars[offset + 3] != 'm' || chars[offset + 4] != 'l') {
            return true;
        }
        offset += 5;
        final char c = chars[offset];
        if (c != ' ' && c != '\n' && c != '\t') {
            return true;
        }
        externalEntityState.reset();
        char c2 = '\0';
        Label_1646: {
            XMLString setStandalone = null;
            Label_1491: {
                XMLString setVersion = null;
                Label_0994: {
                    int n2;
                    int n3;
                    char c4;
                    while (true) {
                        c2 = chars[++offset];
                        if (c2 != ' ' && c2 != '\n' && c2 != '\t') {
                            n2 = offset;
                            if (b) {
                                if (n == 0 && c2 == 'v' && chars[offset + 1] == 'e' && chars[offset + 2] == 'r' && chars[offset + 3] == 's' && chars[offset + 4] == 'i' && chars[offset + 5] == 'o' && chars[offset + 6] == 'n') {
                                    offset += 7;
                                    n = 1;
                                }
                                else {
                                    if (c2 != 'e' || chars[offset + 1] != 'n' || chars[offset + 2] != 'c' || chars[offset + 3] != 'o' || chars[offset + 4] != 'd' || chars[offset + 5] != 'i' || chars[offset + 6] != 'n' || chars[offset + 7] != 'g') {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 45);
                                        return false;
                                    }
                                    offset += 8;
                                    n = 2;
                                }
                            }
                            else if (n == 0) {
                                if (c2 != 'v' || chars[offset + 1] != 'e' || chars[offset + 2] != 'r' || chars[offset + 3] != 's' || chars[offset + 4] != 'i' || chars[offset + 5] != 'o' || chars[offset + 6] != 'n') {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 10);
                                    return false;
                                }
                                offset += 7;
                                n = 1;
                            }
                            else if (n != 1 || c2 != 'e' || chars[offset + 1] != 'n' || chars[offset + 2] != 'c' || chars[offset + 3] != 'o' || chars[offset + 4] != 'd' || chars[offset + 5] != 'i' || chars[offset + 6] != 'n' || chars[offset + 7] != 'g') {
                                if (c2 != 's' || chars[offset + 1] != 't' || chars[offset + 2] != 'a' || chars[offset + 3] != 'n' || chars[offset + 4] != 'd' || chars[offset + 5] != 'a' || chars[offset + 6] != 'l' || chars[offset + 7] != 'o' || chars[offset + 8] != 'n' || chars[offset + 9] != 'e') {
                                    break Label_1646;
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
                            for (c3 = chars[offset]; c3 == ' ' || c3 == '\n' || c3 == '\t'; c3 = chars[++offset]) {}
                            if (c3 != '=') {
                                final int n4 = b ? 41 : 7;
                                final XMLString content = documentEntityState.content;
                                content.setValues(chars, n2, n3);
                                documentEntityState.setParameter(0, content);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n4);
                                return false;
                            }
                            do {
                                c2 = chars[++offset];
                            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                            c4 = c2;
                            if (c4 != '\'' && c4 != '\"') {
                                final int n5 = b ? 42 : 8;
                                final XMLString content2 = documentEntityState.content;
                                content2.setValues(chars, n2, n3);
                                documentEntityState.setParameter(0, content2);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n5);
                                return false;
                            }
                            ++offset;
                            switch (n) {
                                case 1: {
                                    setVersion = externalEntityState.setVersion(chars, offset, 0);
                                    if (chars[offset] != '1' || chars[offset + 1] != '.' || chars[offset + 2] != '0' || chars[offset + 3] != c4) {
                                        break Label_0994;
                                    }
                                    offset += 3;
                                    setVersion.endOffset = offset;
                                    c2 = chars[++offset];
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
                                    final XMLString setEncName = externalEntityState.setEncName(chars, offset, 0);
                                    char c5;
                                    do {
                                        c5 = chars[++offset];
                                        if (c5 == '\0' && offset == parsedEntity.endOffset) {
                                            final int n6 = b ? 42 : 8;
                                            final XMLString content3 = documentEntityState.content;
                                            content3.setValues(chars, n2, n3);
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
                                    c2 = chars[++offset];
                                    if (c2 != ' ' && c2 != '\n' && c2 != '\t') {
                                        n = 4;
                                        break;
                                    }
                                    if (b) {
                                        do {
                                            c2 = chars[++offset];
                                        } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                                        n = 4;
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    setStandalone = externalEntityState.setStandalone(chars, offset, 0);
                                    if (chars[offset] == 'y' && chars[offset + 1] == 'e' && chars[offset + 2] == 's' && chars[offset + 3] == c4) {
                                        offset += 3;
                                        setStandalone.endOffset = offset;
                                    }
                                    else {
                                        if (chars[offset] != 'n' || chars[offset + 1] != 'o' || chars[offset + 2] != c4) {
                                            break Label_1491;
                                        }
                                        offset += 2;
                                        setStandalone.endOffset = offset;
                                    }
                                    do {
                                        c2 = chars[++offset];
                                    } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
                                    n = 4;
                                    break;
                                }
                            }
                            if (n == 4) {
                                break Label_1646;
                            }
                            continue;
                        }
                    }
                    char c6;
                    do {
                        c6 = chars[++offset];
                        if (c6 == '\0' && offset == parsedEntity.endOffset) {
                            final int n7 = b ? 42 : 8;
                            final XMLString content4 = documentEntityState.content;
                            content4.setValues(chars, n2, n3);
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
                    c7 = chars[++offset];
                    if (c7 == '\0' && offset == parsedEntity.endOffset) {
                        final int n8 = b ? 42 : 8;
                        final XMLString content5 = documentEntityState.content;
                        final int n2;
                        final int n3;
                        content5.setValues(chars, n2, n3);
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
        if (c2 != '?' || chars[offset + 1] != '>') {
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
            final char[] chars = xmlString.chars;
            final char c = chars[i++];
            if (c < '\u0080' && UTF16WFCExternalEntityScanner.encNameCharMap[c] == 1) {
                while (i < endOffset) {
                    final char c2 = chars[i++];
                    if (c2 >= '\u0080' || UTF16WFCExternalEntityScanner.encNameCharMap[c2] == 0) {
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
