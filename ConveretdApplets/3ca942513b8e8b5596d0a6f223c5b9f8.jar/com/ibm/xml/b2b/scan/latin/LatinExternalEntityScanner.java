// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class LatinExternalEntityScanner
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
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        int i = 0;
        if (bytes[offset] != 60 || bytes[offset + 1] != 63 || bytes[offset + 2] != 120 || bytes[offset + 3] != 109 || bytes[offset + 4] != 108) {
            return true;
        }
        offset += 5;
        byte b2 = bytes[offset];
        if (b2 != 32 && b2 != 10 && b2 != 9) {
            return true;
        }
        while (b2 == 32 || b2 == 10 || b2 == 9) {
            b2 = bytes[++offset];
        }
        externalEntityState.reset();
        do {
            if (b) {
                if (i == 0 && b2 == 118) {
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
            else if (i != 1 || b2 != 101) {
                if (b2 != 115) {
                    break;
                }
                offset += 10;
                i = 3;
            }
            else {
                offset += 8;
                i = 2;
            }
            for (byte b3 = bytes[offset]; b3 != 61; b3 = bytes[++offset]) {}
            do {
                b2 = bytes[++offset];
            } while (b2 != 34 && b2 != 39);
            final byte b4 = b2;
            ++offset;
            switch (i) {
                case 1: {
                    externalEntityState.setVersion(bytes, offset, offset + 3, encoding);
                    offset += 3;
                    b2 = bytes[++offset];
                    if (b2 == 63) {
                        i = 4;
                        continue;
                    }
                    do {
                        b2 = bytes[++offset];
                    } while (b2 == 32 || b2 == 10 || b2 == 9);
                    if (b2 == 63) {
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 2: {
                    final XMLString setEncName = externalEntityState.setEncName(bytes, offset, 0, encoding);
                    while (bytes[++offset] != b4) {}
                    setEncName.endOffset = offset;
                    b2 = bytes[++offset];
                    if (b2 == 63) {
                        i = 4;
                        continue;
                    }
                    if (b) {
                        do {
                            b2 = bytes[++offset];
                        } while (b2 == 32 || b2 == 10 || b2 == 9);
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 3: {
                    final XMLString setStandalone = externalEntityState.setStandalone(bytes, offset, 0, encoding);
                    if (bytes[offset] == 121) {
                        offset += 3;
                    }
                    else {
                        offset += 2;
                    }
                    setStandalone.endOffset = offset;
                    do {
                        b2 = bytes[++offset];
                    } while (b2 == 32 || b2 == 10 || b2 == 9);
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
