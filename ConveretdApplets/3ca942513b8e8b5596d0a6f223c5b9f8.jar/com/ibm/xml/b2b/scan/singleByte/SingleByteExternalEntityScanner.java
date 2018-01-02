// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class SingleByteExternalEntityScanner
{
    private static final int STATE_START = 0;
    private static final int STATE_VERSION = 1;
    private static final int STATE_ENCODING = 2;
    private static final int STATE_STANDALONE = 3;
    private static final int STATE_FINISHED = 4;
    
    public static boolean scanXMLDecl(final ExternalEntityHandler externalEntityHandler, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity) {
        return scanXMLDeclOrTextDecl(externalEntityHandler, externalEntityState, parsedEntity, false);
    }
    
    public static boolean scanTextDecl(final ExternalEntityHandler externalEntityHandler, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity) {
        return scanXMLDeclOrTextDecl(externalEntityHandler, externalEntityState, parsedEntity, true);
    }
    
    private static boolean scanXMLDeclOrTextDecl(final ExternalEntityHandler externalEntityHandler, final ExternalEntityState externalEntityState, final ParsedEntity parsedEntity, final boolean b) {
        final SingleByteEncodingSupport singleByteEncodingSupport = (SingleByteEncodingSupport)parsedEntity.encoding;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int i = 0;
        if (byteToCharMap[bytes[offset] & 0xFF] != '<' || byteToCharMap[bytes[offset + 1] & 0xFF] != '?' || byteToCharMap[bytes[offset + 2] & 0xFF] != 'x' || byteToCharMap[bytes[offset + 3] & 0xFF] != 'm' || byteToCharMap[bytes[offset + 4] & 0xFF] != 'l') {
            return true;
        }
        offset += 5;
        char c = byteToCharMap[bytes[offset] & 0xFF];
        if (c != ' ' && c != '\n' && c != '\t') {
            return true;
        }
        while (c == ' ' || c == '\n' || c == '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        externalEntityState.reset();
        do {
            if (b) {
                if (i == 0 && c == 'v') {
                    offset += 7;
                    i = 1;
                }
                else {
                    offset += 8;
                    i = 2;
                }
            }
            else if (i == 0) {
                offset += 7;
                i = 1;
            }
            else if (i != 1 || c != 'e') {
                if (c != 's') {
                    break;
                }
                offset += 10;
                i = 3;
            }
            else {
                offset += 8;
                i = 2;
            }
            for (char c2 = byteToCharMap[bytes[offset] & 0xFF]; c2 != '='; c2 = byteToCharMap[bytes[++offset] & 0xFF]) {}
            do {
                c = byteToCharMap[bytes[++offset] & 0xFF];
            } while (c != '\"' && c != '\'');
            final char c3 = c;
            ++offset;
            switch (i) {
                case 1: {
                    externalEntityState.setVersion(bytes, offset, offset + 3, singleByteEncodingSupport);
                    offset += 3;
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    do {
                        c = byteToCharMap[bytes[++offset] & 0xFF];
                    } while (c == ' ' || c == '\n' || c == '\t');
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 2: {
                    final XMLString setEncName = externalEntityState.setEncName(bytes, offset, 0, singleByteEncodingSupport);
                    while (byteToCharMap[bytes[++offset] & 0xFF] != c3) {}
                    setEncName.endOffset = offset;
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    if (b) {
                        do {
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        } while (c == ' ' || c == '\n' || c == '\t');
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 3: {
                    final XMLString setStandalone = externalEntityState.setStandalone(bytes, offset, 0, singleByteEncodingSupport);
                    if (byteToCharMap[bytes[offset] & 0xFF] == 'y') {
                        offset += 3;
                    }
                    else {
                        offset += 2;
                    }
                    setStandalone.endOffset = offset;
                    do {
                        c = byteToCharMap[bytes[++offset] & 0xFF];
                    } while (c == ' ' || c == '\n' || c == '\t');
                    i = 4;
                }
                default: {
                    continue;
                }
            }
        } while (i != 4);
        parsedEntity.offset = offset + 2;
        if (b) {
            externalEntityHandler.textDeclEvent();
        }
        else {
            externalEntityHandler.xmlDeclEvent();
        }
        return true;
    }
}
