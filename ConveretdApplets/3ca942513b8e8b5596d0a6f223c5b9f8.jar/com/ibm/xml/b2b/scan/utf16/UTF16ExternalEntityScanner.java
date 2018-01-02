// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

public final class UTF16ExternalEntityScanner
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        int i = 0;
        if (chars[offset] != '<' || chars[offset + 1] != '?' || chars[offset + 2] != 'x' || chars[offset + 3] != 'm' || chars[offset + 4] != 'l') {
            return true;
        }
        offset += 5;
        char c = chars[offset];
        if (c != ' ' && c != '\n' && c != '\t') {
            return true;
        }
        while (c == ' ' || c == '\n' || c == '\t') {
            c = chars[++offset];
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
            for (char c2 = chars[offset]; c2 != '='; c2 = chars[++offset]) {}
            do {
                c = chars[++offset];
            } while (c != '\"' && c != '\'');
            final char c3 = c;
            ++offset;
            switch (i) {
                case 1: {
                    externalEntityState.setVersion(chars, offset, offset + 3);
                    offset += 3;
                    c = chars[++offset];
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    do {
                        c = chars[++offset];
                    } while (c == ' ' || c == '\n' || c == '\t');
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 2: {
                    final XMLString setEncName = externalEntityState.setEncName(chars, offset, 0);
                    while (chars[++offset] != c3) {}
                    setEncName.endOffset = offset;
                    c = chars[++offset];
                    if (c == '?') {
                        i = 4;
                        continue;
                    }
                    if (b) {
                        do {
                            c = chars[++offset];
                        } while (c == ' ' || c == '\n' || c == '\t');
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 3: {
                    final XMLString setStandalone = externalEntityState.setStandalone(chars, offset, 0);
                    if (chars[offset] == 'y') {
                        offset += 3;
                    }
                    else {
                        offset += 2;
                    }
                    setStandalone.endOffset = offset;
                    do {
                        c = chars[++offset];
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
