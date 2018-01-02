// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;

public class LatinMarkupDeclScanner
{
    public static boolean scanDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        byte b;
        while (true) {
            final int offset2 = offset;
            for (b = bytes[offset]; b != n && b != 38; b = bytes[++offset]) {}
            if (offset > offset2) {
                final XMLString attValueCharacters = dtdParams.getAttValueCharacters();
                attValueCharacters.offset = offset2;
                attValueCharacters.endOffset = offset;
                markupDeclHandler.defaultAttValueCharacters(attValueCharacters);
                dtdParams.resetAttValueCharacters();
            }
            if (b == n) {
                break;
            }
            byte b2 = bytes[++offset];
            if (b2 == 35) {
                final byte b3 = bytes[++offset];
                int n2;
                if (b3 == 120) {
                    final byte b4 = bytes[++offset];
                    final byte b5;
                    n2 = b5 - (((b5 = b4) < 65) ? 48 : (((b4 < 97) ? 65 : 97) - 10));
                    while (true) {
                        final byte b6 = bytes[++offset];
                        if (b6 == 59) {
                            break;
                        }
                        final byte b7;
                        n2 = (n2 << 4) + (b7 - (((b7 = b6) < 65) ? 48 : (((b6 < 97) ? 65 : 97) - 10)));
                    }
                    ++offset;
                }
                else {
                    n2 = b3 - 48;
                    while (true) {
                        final byte b8 = bytes[++offset];
                        if (b8 == 59) {
                            break;
                        }
                        n2 = n2 * 10 + (b8 - 48);
                    }
                    ++offset;
                }
                markupDeclHandler.defaultAttValueCharacter(n2, false);
            }
            else {
                final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
                entityReferenceName.offset = offset;
                while (b2 != 59) {
                    b2 = bytes[++offset];
                }
                entityReferenceName.endOffset = offset;
                ++offset;
                markupDeclHandler.entityReferenceInDefaultAttValue(entityReferenceName);
                dtdParams.resetEntityReferenceName();
            }
        }
        if (b != 0) {
            ++offset;
        }
        parsedEntity.offset = offset;
        return true;
    }
    
    public static boolean scanEntityValue(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        byte b;
        while (true) {
            final int offset2 = offset;
            for (b = bytes[offset]; b != n && b != 38 && b != 37; b = bytes[++offset]) {}
            if (offset > offset2) {
                final XMLString entityValueCharacters = dtdParams.getEntityValueCharacters();
                entityValueCharacters.offset = offset2;
                entityValueCharacters.endOffset = offset;
                markupDeclHandler.entityValueCharacters(entityValueCharacters);
                dtdParams.resetEntityValueCharacters();
            }
            if (b == n) {
                break;
            }
            if (b == 37) {
                final XMLName peReferenceName = dtdParams.getPEReferenceName();
                peReferenceName.offset = ++offset;
                while (b != 59) {
                    b = bytes[++offset];
                }
                peReferenceName.endOffset = offset;
                ++offset;
                markupDeclHandler.peReferenceInEntityValue(peReferenceName);
                dtdParams.resetPEReferenceName();
            }
            else {
                byte b2 = bytes[++offset];
                if (b2 == 35) {
                    final byte b3 = bytes[++offset];
                    int n2;
                    if (b3 == 120) {
                        final byte b4 = bytes[++offset];
                        final byte b5;
                        n2 = b5 - (((b5 = b4) < 65) ? 48 : (((b4 < 97) ? 65 : 97) - 10));
                        while (true) {
                            final byte b6 = bytes[++offset];
                            if (b6 == 59) {
                                break;
                            }
                            final byte b7;
                            n2 = (n2 << 4) + (b7 - (((b7 = b6) < 65) ? 48 : (((b6 < 97) ? 65 : 97) - 10)));
                        }
                        ++offset;
                    }
                    else {
                        n2 = b3 - 48;
                        while (true) {
                            final byte b8 = bytes[++offset];
                            if (b8 == 59) {
                                break;
                            }
                            n2 = n2 * 10 + (b8 - 48);
                        }
                        ++offset;
                    }
                    markupDeclHandler.entityValueCharacter(n2);
                }
                else {
                    final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
                    entityReferenceName.offset = offset;
                    while (b2 != 59) {
                        b2 = bytes[++offset];
                    }
                    entityReferenceName.endOffset = offset;
                    ++offset;
                    markupDeclHandler.entityReferenceInEntityValue(entityReferenceName);
                    dtdParams.resetEntityReferenceName();
                }
            }
        }
        if (b != 0) {
            ++offset;
        }
        parsedEntity.offset = offset;
        return true;
    }
    
    protected static int scanComment(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final XMLString content = dtdParams.getContent();
        content.offset = offset;
        for (byte b = bytes[offset]; b != 45 || bytes[offset + 1] != 45; b = bytes[++offset]) {}
        content.endOffset = offset;
        offset += 3;
        markupDeclHandler.comment(content);
        dtdParams.resetContent();
        return offset;
    }
    
    protected static int scanPI(final MarkupDeclHandler markupDeclHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final XMLName piTarget = dtdParams.getPITarget();
        final XMLString content = dtdParams.getContent();
        boolean b = false;
        piTarget.offset = offset;
        final byte b2 = bytes[offset];
        while (true) {
            byte b3;
            do {
                b3 = bytes[++offset];
                if (b3 == 63) {
                    piTarget.endOffset = offset;
                    if (b) {
                        byte b4;
                        do {
                            b4 = bytes[++offset];
                        } while (b4 == 32 || b4 == 10 || b4 == 9);
                        content.offset = offset;
                        while (b4 != 63 || bytes[offset + 1] != 62) {
                            b4 = bytes[++offset];
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
            } while (b3 != 32 && b3 != 10 && b3 != 9);
            b = true;
            continue;
        }
    }
}
