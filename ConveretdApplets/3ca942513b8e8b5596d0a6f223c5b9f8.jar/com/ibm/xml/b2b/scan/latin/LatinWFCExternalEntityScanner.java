// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class LatinWFCExternalEntityScanner
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
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        int n = 0;
        if (bytes[offset] != 60 || bytes[offset + 1] != 63 || bytes[offset + 2] != 120 || bytes[offset + 3] != 109 || bytes[offset + 4] != 108) {
            return true;
        }
        offset += 5;
        final byte b2 = bytes[offset];
        if (b2 != 32 && b2 != 10 && b2 != 9) {
            return true;
        }
        externalEntityState.reset();
        byte b3 = 0;
        Label_1666: {
            XMLString setStandalone = null;
            Label_1509: {
                XMLString setVersion = null;
                Label_1004: {
                    int n2;
                    int n3;
                    byte b5;
                    while (true) {
                        b3 = bytes[++offset];
                        if (b3 != 32 && b3 != 10 && b3 != 9) {
                            n2 = offset;
                            if (b) {
                                if (n == 0 && b3 == 118 && bytes[offset + 1] == 101 && bytes[offset + 2] == 114 && bytes[offset + 3] == 115 && bytes[offset + 4] == 105 && bytes[offset + 5] == 111 && bytes[offset + 6] == 110) {
                                    offset += 7;
                                    n = 1;
                                }
                                else {
                                    if (b3 != 101 || bytes[offset + 1] != 110 || bytes[offset + 2] != 99 || bytes[offset + 3] != 111 || bytes[offset + 4] != 100 || bytes[offset + 5] != 105 || bytes[offset + 6] != 110 || bytes[offset + 7] != 103) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 45);
                                        return false;
                                    }
                                    offset += 8;
                                    n = 2;
                                }
                            }
                            else if (n == 0) {
                                if (b3 != 118 || bytes[offset + 1] != 101 || bytes[offset + 2] != 114 || bytes[offset + 3] != 115 || bytes[offset + 4] != 105 || bytes[offset + 5] != 111 || bytes[offset + 6] != 110) {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 10);
                                    return false;
                                }
                                offset += 7;
                                n = 1;
                            }
                            else if (n != 1 || b3 != 101 || bytes[offset + 1] != 110 || bytes[offset + 2] != 99 || bytes[offset + 3] != 111 || bytes[offset + 4] != 100 || bytes[offset + 5] != 105 || bytes[offset + 6] != 110 || bytes[offset + 7] != 103) {
                                if (b3 != 115 || bytes[offset + 1] != 116 || bytes[offset + 2] != 97 || bytes[offset + 3] != 110 || bytes[offset + 4] != 100 || bytes[offset + 5] != 97 || bytes[offset + 6] != 108 || bytes[offset + 7] != 111 || bytes[offset + 8] != 110 || bytes[offset + 9] != 101) {
                                    break Label_1666;
                                }
                                offset += 10;
                                n = 3;
                            }
                            else {
                                offset += 8;
                                n = 2;
                            }
                            n3 = offset;
                            byte b4;
                            for (b4 = bytes[offset]; b4 == 32 || b4 == 10 || b4 == 9; b4 = bytes[++offset]) {}
                            if (b4 != 61) {
                                final int n4 = b ? 41 : 7;
                                final XMLString content = documentEntityState.content;
                                content.setValues(bytes, n2, n3, encoding);
                                documentEntityState.setParameter(0, content);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n4);
                                return false;
                            }
                            do {
                                b3 = bytes[++offset];
                            } while (b3 == 32 || b3 == 10 || b3 == 9);
                            b5 = b3;
                            if (b5 != 39 && b5 != 34) {
                                final int n5 = b ? 42 : 8;
                                final XMLString content2 = documentEntityState.content;
                                content2.setValues(bytes, n2, n3, encoding);
                                documentEntityState.setParameter(0, content2);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n5);
                                return false;
                            }
                            ++offset;
                            switch (n) {
                                case 1: {
                                    setVersion = externalEntityState.setVersion(bytes, offset, 0, encoding);
                                    if (bytes[offset] != 49 || bytes[offset + 1] != 46 || bytes[offset + 2] != 48 || bytes[offset + 3] != b5) {
                                        break Label_1004;
                                    }
                                    offset += 3;
                                    setVersion.endOffset = offset;
                                    b3 = bytes[++offset];
                                    if (b3 == 32 || b3 == 10 || b3 == 9) {
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
                                    final XMLString setEncName = externalEntityState.setEncName(bytes, offset, 0, encoding);
                                    byte b6;
                                    do {
                                        b6 = bytes[++offset];
                                        if (b6 == 0 && offset == parsedEntity.endOffset) {
                                            final int n6 = b ? 42 : 8;
                                            final XMLString content3 = documentEntityState.content;
                                            content3.setValues(bytes, n2, n3, encoding);
                                            documentEntityState.setParameter(0, content3);
                                            parsedEntity.offset = offset;
                                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n6);
                                            return false;
                                        }
                                    } while (b6 != b5);
                                    setEncName.endOffset = offset;
                                    parsedEntity.offset = offset;
                                    if (!validEncName(setEncName)) {
                                        documentEntityState.setParameter(0, setEncName);
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 46);
                                        return false;
                                    }
                                    b3 = bytes[++offset];
                                    if (b3 != 32 && b3 != 10 && b3 != 9) {
                                        n = 4;
                                        break;
                                    }
                                    if (b) {
                                        do {
                                            b3 = bytes[++offset];
                                        } while (b3 == 32 || b3 == 10 || b3 == 9);
                                        n = 4;
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    setStandalone = externalEntityState.setStandalone(bytes, offset, 0, encoding);
                                    if (bytes[offset] == 121 && bytes[offset + 1] == 101 && bytes[offset + 2] == 115 && bytes[offset + 3] == b5) {
                                        offset += 3;
                                        setStandalone.endOffset = offset;
                                    }
                                    else {
                                        if (bytes[offset] != 110 || bytes[offset + 1] != 111 || bytes[offset + 2] != b5) {
                                            break Label_1509;
                                        }
                                        offset += 2;
                                        setStandalone.endOffset = offset;
                                    }
                                    do {
                                        b3 = bytes[++offset];
                                    } while (b3 == 32 || b3 == 10 || b3 == 9);
                                    n = 4;
                                    break;
                                }
                            }
                            if (n == 4) {
                                break Label_1666;
                            }
                            continue;
                        }
                    }
                    byte b7;
                    do {
                        b7 = bytes[++offset];
                        if (b7 == 0 && offset == parsedEntity.endOffset) {
                            final int n7 = b ? 42 : 8;
                            final XMLString content4 = documentEntityState.content;
                            content4.setValues(bytes, n2, n3, encoding);
                            documentEntityState.setParameter(0, content4);
                            parsedEntity.offset = offset;
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n7);
                            return false;
                        }
                    } while (b7 != b5);
                }
                setVersion.endOffset = offset;
                documentEntityState.setParameter(0, setVersion);
                parsedEntity.offset = offset;
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 34);
                return false;
                byte b5 = 0;
                byte b8;
                do {
                    b8 = bytes[++offset];
                    if (b8 == 0 && offset == parsedEntity.endOffset) {
                        final int n8 = b ? 42 : 8;
                        final XMLString content5 = documentEntityState.content;
                        final int n2;
                        final int n3;
                        content5.setValues(bytes, n2, n3, encoding);
                        documentEntityState.setParameter(0, content5);
                        parsedEntity.offset = offset;
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", n8);
                        return false;
                    }
                } while (b8 != b5);
            }
            setStandalone.endOffset = offset;
            documentEntityState.setParameter(0, setStandalone);
            parsedEntity.offset = offset;
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 13);
            return false;
        }
        if (b3 != 63 || bytes[offset + 1] != 62) {
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
            final byte[] bytes = xmlString.bytes;
            final byte b = bytes[i++];
            if (b >= 0 && LatinWFCExternalEntityScanner.encNameCharMap[b] == 1) {
                while (i < endOffset) {
                    final byte b2 = bytes[i++];
                    if (b2 < 0 || LatinWFCExternalEntityScanner.encNameCharMap[b2] == 0) {
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
