// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;

public class UTF16MarkupDeclScanner
{
    public static boolean scanDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        char c;
        while (true) {
            final int offset2 = offset;
            for (c = chars[offset]; c != n && c != '&'; c = chars[++offset]) {}
            if (offset > offset2) {
                final XMLString attValueCharacters = dtdParams.getAttValueCharacters();
                attValueCharacters.offset = offset2;
                attValueCharacters.endOffset = offset;
                markupDeclHandler.defaultAttValueCharacters(attValueCharacters);
                dtdParams.resetAttValueCharacters();
            }
            if (c == n) {
                break;
            }
            char c2 = chars[++offset];
            if (c2 == '#') {
                final char c3 = chars[++offset];
                int n2;
                if (c3 == 'x') {
                    final char c4 = chars[++offset];
                    final char c5;
                    n2 = c5 - (((c5 = c4) < 'A') ? 48 : (((c4 < 'a') ? 65 : 97) - 10));
                    while (true) {
                        final char c6 = chars[++offset];
                        if (c6 == ';') {
                            break;
                        }
                        final char c7;
                        n2 = (n2 << 4) + (c7 - (((c7 = c6) < 'A') ? 48 : (((c6 < 'a') ? 65 : 97) - 10)));
                    }
                    ++offset;
                }
                else {
                    n2 = c3 - '0';
                    while (true) {
                        final char c8 = chars[++offset];
                        if (c8 == ';') {
                            break;
                        }
                        n2 = n2 * 10 + (c8 - '0');
                    }
                    ++offset;
                }
                markupDeclHandler.defaultAttValueCharacter(n2, false);
            }
            else {
                final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
                entityReferenceName.offset = offset;
                while (c2 != ';') {
                    c2 = chars[++offset];
                }
                entityReferenceName.endOffset = offset;
                ++offset;
                markupDeclHandler.entityReferenceInDefaultAttValue(entityReferenceName);
                dtdParams.resetEntityReferenceName();
            }
        }
        if (c != '\0') {
            ++offset;
        }
        parsedEntity.offset = offset;
        return true;
    }
    
    public static boolean scanEntityValue(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        char c;
        while (true) {
            final int offset2 = offset;
            for (c = chars[offset]; c != n && c != '&' && c != '%'; c = chars[++offset]) {}
            if (offset > offset2) {
                final XMLString entityValueCharacters = dtdParams.getEntityValueCharacters();
                entityValueCharacters.offset = offset2;
                entityValueCharacters.endOffset = offset;
                markupDeclHandler.entityValueCharacters(entityValueCharacters);
                dtdParams.resetEntityValueCharacters();
            }
            if (c == n) {
                break;
            }
            if (c == '%') {
                final XMLName peReferenceName = dtdParams.getPEReferenceName();
                peReferenceName.offset = ++offset;
                while (c != ';') {
                    c = chars[++offset];
                }
                peReferenceName.endOffset = offset;
                ++offset;
                markupDeclHandler.peReferenceInEntityValue(peReferenceName);
                dtdParams.resetPEReferenceName();
            }
            else {
                char c2 = chars[++offset];
                if (c2 == '#') {
                    final char c3 = chars[++offset];
                    int n2;
                    if (c3 == 'x') {
                        final char c4 = chars[++offset];
                        final char c5;
                        n2 = c5 - (((c5 = c4) < 'A') ? 48 : (((c4 < 'a') ? 65 : 97) - 10));
                        while (true) {
                            final char c6 = chars[++offset];
                            if (c6 == ';') {
                                break;
                            }
                            final char c7;
                            n2 = (n2 << 4) + (c7 - (((c7 = c6) < 'A') ? 48 : (((c6 < 'a') ? 65 : 97) - 10)));
                        }
                        ++offset;
                    }
                    else {
                        n2 = c3 - '0';
                        while (true) {
                            final char c8 = chars[++offset];
                            if (c8 == ';') {
                                break;
                            }
                            n2 = n2 * 10 + (c8 - '0');
                        }
                        ++offset;
                    }
                    markupDeclHandler.entityValueCharacter(n2);
                }
                else {
                    final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
                    entityReferenceName.offset = offset;
                    while (c2 != ';') {
                        c2 = chars[++offset];
                    }
                    entityReferenceName.endOffset = offset;
                    ++offset;
                    markupDeclHandler.entityReferenceInEntityValue(entityReferenceName);
                    dtdParams.resetEntityReferenceName();
                }
            }
        }
        if (c != '\0') {
            ++offset;
        }
        parsedEntity.offset = offset;
        return true;
    }
    
    protected static int scanComment(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final XMLString content = dtdParams.getContent();
        content.offset = offset;
        for (char c = chars[offset]; c != '-' || chars[offset + 1] != '-'; c = chars[++offset]) {}
        content.endOffset = offset;
        offset += 3;
        markupDeclHandler.comment(content);
        dtdParams.resetContent();
        return offset;
    }
    
    protected static int scanPI(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final XMLName piTarget = dtdParams.getPITarget();
        final XMLString content = dtdParams.getContent();
        boolean b = false;
        piTarget.offset = offset;
        final char c = chars[offset];
        while (true) {
            char c2;
            do {
                c2 = chars[++offset];
                if (c2 == '?') {
                    piTarget.endOffset = offset;
                    if (b) {
                        char c3;
                        do {
                            c3 = chars[++offset];
                        } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
                        content.offset = offset;
                        while (c3 != '?' || chars[offset + 1] != '>') {
                            c3 = chars[++offset];
                        }
                    }
                    else {
                        content.offset = offset;
                    }
                    content.endOffset = offset;
                    markupDeclHandler.processingInstruction(piTarget, content);
                    dtdParams.resetPITarget();
                    dtdParams.resetContent();
                    return offset + 2;
                }
            } while (c2 != ' ' && c2 != '\n' && c2 != '\t');
            b = true;
            continue;
        }
    }
}
