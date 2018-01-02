// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class LatinInternalSubsetScanner extends LatinMarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        return scanIntSubsetDecl(internalSubsetHandler, dtdParams, parsedEntity);
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int endOffset = parsedEntity.offset;
        while (true) {
            byte b;
            for (b = bytes[endOffset]; b == 32 || b == 10 || b == 9; b = bytes[++endOffset]) {}
            if (b == 60) {
                parsedEntity.offset = endOffset + 1;
                endOffset = scanMarkupDecl(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                if (b != 37) {
                    break;
                }
                final XMLName peName = dtdParams.getPEName();
                peName.offset = ++endOffset;
                while (b != 59) {
                    b = bytes[++endOffset];
                }
                peName.endOffset = endOffset;
                parsedEntity.offset = endOffset + 1;
                internalSubsetHandler.internalSubsetPEReference(peName);
                endOffset = parsedEntity.offset;
                dtdParams.resetPEName();
            }
        }
        return true;
    }
    
    private static int scanMarkupDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int n;
        if (bytes[offset] == 33) {
            if (bytes[++offset] == 45) {
                parsedEntity.offset = offset + 2;
                n = LatinMarkupDeclScanner.scanComment(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                final byte b = bytes[offset + 1];
                if (b == 76) {
                    parsedEntity.offset = offset + 7;
                    n = scanElementDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
                else if (b == 84) {
                    parsedEntity.offset = offset + 7;
                    n = scanAttlistDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
                else if (b == 78) {
                    parsedEntity.offset = offset + 6;
                    n = scanEntityDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
                else {
                    parsedEntity.offset = offset + 8;
                    n = scanNotationDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
            }
        }
        else {
            parsedEntity.offset = offset + 1;
            n = LatinMarkupDeclScanner.scanPI(internalSubsetHandler, dtdParams, parsedEntity);
        }
        return n;
    }
    
    private static int scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        byte[] bytes;
        int n;
        byte b;
        for (bytes = parsedEntity.bytes, n = parsedEntity.offset, b = bytes[n]; b == 32 || b == 10 || b == 9; b = bytes[++n]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = n;
        while (b != 32 && b != 10 && b != 9) {
            b = bytes[++n];
            if (b == 58) {
                elementType.sepOffset = n;
                b = bytes[++n];
            }
        }
        elementType.endOffset = n;
        internalSubsetHandler.startElementDecl(elementType);
        while (b == 32 || b == 10 || b == 9) {
            b = bytes[++n];
        }
        if (b == 65) {
            n += 3;
            internalSubsetHandler.contentModelANY();
        }
        else if (b == 69) {
            n += 5;
            internalSubsetHandler.contentModelEMPTY();
        }
        else {
            byte b2 = bytes[++n];
            internalSubsetHandler.contentModelStartGroup();
            while (b2 == 32 || b2 == 10 || b2 == 9) {
                b2 = bytes[++n];
            }
            if (b2 == 35) {
                parsedEntity.offset = n + 7;
                internalSubsetHandler.contentModelPCDATA();
                n = scanMixed(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                parsedEntity.offset = n;
                n = scanChildren(internalSubsetHandler, dtdParams, parsedEntity, 1);
            }
        }
        for (byte b3 = bytes[n]; b3 != 62; b3 = bytes[++n]) {}
        ++n;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return n;
    }
    
    private static int scanMixed(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        while (true) {
            byte b;
            for (b = bytes[offset]; b == 32 || b == 10 || b == 9 || b == 13; b = bytes[++offset]) {}
            if (b == 41) {
                break;
            }
            byte b2 = bytes[++offset];
            internalSubsetHandler.contentModelSeparator(0);
            while (b2 == 32 || b2 == 10 || b2 == 9 || b2 == 13) {
                b2 = bytes[++offset];
            }
            parsedEntity.offset = offset;
            final QName contentModelElement = dtdParams.getContentModelElement();
            contentModelElement.offset = offset;
            byte b3;
            do {
                b3 = bytes[++offset];
                if (b3 == 58) {
                    contentModelElement.sepOffset = offset;
                    b3 = bytes[++offset];
                }
            } while (b3 != 32 && b3 != 10 && b3 != 9 && b3 != 13 && b3 != 37 && b3 != 0 && b3 != 124 && b3 != 41);
            contentModelElement.endOffset = offset;
            internalSubsetHandler.contentModelElement(contentModelElement);
            dtdParams.resetContentModelElement();
        }
        final byte b4 = bytes[++offset];
        internalSubsetHandler.contentModelEndGroup();
        if (b4 == 42) {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        return offset;
    }
    
    private static int scanChildren(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int scanCp;
        while (true) {
            byte b;
            for (scanCp = scanCp(internalSubsetHandler, dtdParams, parsedEntity, n), b = bytes[scanCp]; b == 32 || b == 10 || b == 9 || b == 13; b = bytes[++scanCp]) {}
            ++scanCp;
            if (b == 124) {
                internalSubsetHandler.contentModelSeparator(0);
            }
            else {
                if (b != 44) {
                    break;
                }
                internalSubsetHandler.contentModelSeparator(1);
            }
            for (byte b2 = bytes[scanCp]; b2 == 32 || b2 == 10 || b2 == 9 || b2 == 13; b2 = bytes[++scanCp]) {}
            parsedEntity.offset = scanCp;
        }
        final byte b3 = bytes[scanCp];
        internalSubsetHandler.contentModelEndGroup();
        if (b3 == 63) {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (b3 == 42) {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (b3 == 43) {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return scanCp;
    }
    
    private static int scanCp(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        if (bytes[offset] == 40) {
            byte b = bytes[++offset];
            internalSubsetHandler.contentModelStartGroup();
            while (b == 32 || b == 10 || b == 9 || b == 13) {
                b = bytes[++offset];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        contentModelElement.offset = offset;
        byte b2;
        do {
            b2 = bytes[++offset];
            if (b2 == 58) {
                contentModelElement.sepOffset = offset;
                b2 = bytes[++offset];
            }
        } while (b2 != 32 && b2 != 10 && b2 != 9 && b2 != 13 && b2 != 37 && b2 != 0 && b2 != 124 && b2 != 44 && b2 != 41 && b2 != 63 && b2 != 42 && b2 != 43);
        contentModelElement.endOffset = offset;
        internalSubsetHandler.contentModelElement(contentModelElement);
        dtdParams.resetContentModelElement();
        if (b2 == 63) {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (b2 == 42) {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (b2 == 43) {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return offset;
    }
    
    private static int scanAttlistDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        byte[] bytes;
        int offset;
        byte b;
        for (bytes = parsedEntity.bytes, offset = parsedEntity.offset, b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = offset;
        while (b != 32 && b != 10 && b != 9 && b != 62) {
            b = bytes[++offset];
            if (b == 58) {
                elementType.sepOffset = offset;
                b = bytes[++offset];
            }
        }
        elementType.endOffset = offset;
        internalSubsetHandler.startAttlistDecl(elementType);
        while (b == 32 || b == 10 || b == 9) {
            b = bytes[++offset];
        }
        while (b != 62) {
            final QName attributeName = dtdParams.getAttributeName();
            attributeName.offset = offset;
            while (b != 32 && b != 10 && b != 9) {
                b = bytes[++offset];
                if (b == 58) {
                    attributeName.sepOffset = offset;
                    b = bytes[++offset];
                }
            }
            attributeName.endOffset = offset;
            while (b == 32 || b == 10 || b == 9) {
                b = bytes[++offset];
            }
            boolean b2 = b == 40;
            XMLString attributeType;
            if (!b2) {
                b2 = (bytes[offset + 1] == 79);
                attributeType = dtdParams.getAttributeType();
                attributeType.offset = offset;
                while (b != 32 && b != 10 && b != 9) {
                    b = bytes[++offset];
                }
                attributeType.endOffset = offset;
                if (b2) {
                    for (b = bytes[++offset]; b != 40; b = bytes[++offset]) {}
                }
            }
            else {
                attributeType = null;
            }
            internalSubsetHandler.startAttDef(attributeName, attributeType);
            if (b2) {
                internalSubsetHandler.startEnumerationType();
                byte b3;
                do {
                    for (b3 = bytes[++offset]; b3 == 32 || b3 == 10 || b3 == 9; b3 = bytes[++offset]) {}
                    final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                    enumerationTypeToken.offset = offset;
                    while (b3 != 32 && b3 != 10 && b3 != 9 && b3 != 124 && b3 != 41) {
                        b3 = bytes[++offset];
                    }
                    enumerationTypeToken.endOffset = offset;
                    internalSubsetHandler.enumerationType(enumerationTypeToken);
                    dtdParams.resetEnumerationTypeToken();
                    while (b3 == 32 || b3 == 10 || b3 == 9) {
                        b3 = bytes[++offset];
                    }
                } while (b3 != 41);
                b = bytes[++offset];
                internalSubsetHandler.endEnumerationType();
            }
            while (b == 32 || b == 10 || b == 9) {
                b = bytes[++offset];
            }
            boolean b4 = true;
            XMLString attributeDefaultType;
            if (b == 35) {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = offset;
                final byte b5 = bytes[++offset];
                if (b5 == 70) {
                    offset += 5;
                }
                else if (b5 == 73) {
                    offset += 7;
                    b4 = false;
                }
                else {
                    offset += 8;
                    b4 = false;
                }
                attributeDefaultType.endOffset = offset;
                for (b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
            }
            else {
                attributeDefaultType = null;
            }
            if (b4) {
                final byte b6 = b;
                ++offset;
                internalSubsetHandler.startDefaultAttValue();
                parsedEntity.offset = offset;
                LatinMarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, dtdParams, parsedEntity, b6);
                for (offset = parsedEntity.offset, b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
            }
            internalSubsetHandler.endAttDef(attributeDefaultType);
            dtdParams.resetAttributeName();
            if (attributeType != null) {
                dtdParams.resetAttributeType();
            }
            if (attributeDefaultType != null) {
                dtdParams.resetAttributeDefaultType();
            }
        }
        ++offset;
        internalSubsetHandler.endAttlistDecl();
        dtdParams.resetElementType();
        return offset;
    }
    
    private static int scanEntityDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        byte[] bytes;
        int offset;
        byte b;
        for (bytes = parsedEntity.bytes, offset = parsedEntity.offset, b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
        boolean b2;
        if (b == 37) {
            b2 = true;
            for (b = bytes[++offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
        }
        else {
            b2 = false;
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        entityDeclName.offset = offset;
        while (b != 32 && b != 10 && b != 9) {
            b = bytes[++offset];
        }
        entityDeclName.endOffset = offset;
        while (b == 32 || b == 10 || b == 9) {
            b = bytes[++offset];
        }
        if (b == 34 || b == 39) {
            final byte b3 = b;
            ++offset;
            internalSubsetHandler.startEntityValue();
            parsedEntity.offset = offset;
            LatinMarkupDeclScanner.scanEntityValue(internalSubsetHandler, dtdParams, parsedEntity, b3);
            int offset2 = parsedEntity.offset;
            if (b2) {
                internalSubsetHandler.internalPEDecl(entityDeclName);
            }
            else {
                internalSubsetHandler.internalEntityDecl(entityDeclName);
            }
            dtdParams.resetEntityDeclName();
            for (byte b4 = bytes[offset2]; b4 != 62; b4 = bytes[++offset2]) {}
            return ++offset2;
        }
        XMLString publicID;
        if (b == 80) {
            byte b5;
            for (offset += 6, b5 = bytes[offset]; b5 != 34 && b5 != 39; b5 = bytes[++offset]) {}
            final byte b6 = b5;
            byte b7 = bytes[++offset];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (b7 != b6) {
                b7 = bytes[++offset];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        byte b8;
        for (b8 = bytes[offset]; b8 != 34 && b8 != 39; b8 = bytes[++offset]) {}
        final byte b9 = b8;
        byte b10 = bytes[++offset];
        final XMLString systemID = dtdParams.getSystemID();
        systemID.offset = offset;
        while (b10 != b9) {
            b10 = bytes[++offset];
        }
        systemID.endOffset = offset;
        byte b11;
        for (b11 = bytes[++offset]; b11 == 32 || b11 == 10 || b11 == 9; b11 = bytes[++offset]) {}
        if (!b2 && b11 == 78) {
            for (offset += 5, b11 = bytes[offset]; b11 == 32 || b11 == 10 || b11 == 9; b11 = bytes[++offset]) {}
            final XMLName notationName = dtdParams.getNotationName();
            notationName.offset = offset;
            while (b11 != 32 && b11 != 10 && b11 != 9 && b11 != 62) {
                b11 = bytes[++offset];
            }
            notationName.endOffset = offset;
            internalSubsetHandler.unparsedEntityDecl(entityDeclName, publicID, systemID, notationName);
            dtdParams.resetNotationName();
        }
        else if (b2) {
            internalSubsetHandler.externalPEDecl(entityDeclName, publicID, systemID);
        }
        else {
            internalSubsetHandler.externalEntityDecl(entityDeclName, publicID, systemID);
        }
        dtdParams.resetEntityDeclName();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        dtdParams.resetSystemID();
        while (b11 != 62) {
            b11 = bytes[++offset];
        }
        return ++offset;
    }
    
    private static int scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        byte[] bytes;
        int offset;
        byte b;
        for (bytes = parsedEntity.bytes, offset = parsedEntity.offset, b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
        final XMLName notationName = dtdParams.getNotationName();
        notationName.offset = offset;
        while (b != 32 && b != 10 && b != 9) {
            b = bytes[++offset];
        }
        notationName.endOffset = offset;
        while (b == 32 || b == 10 || b == 9) {
            b = bytes[++offset];
        }
        XMLString publicID;
        if (b == 80) {
            byte b2;
            for (offset += 6, b2 = bytes[offset]; b2 == 32 || b2 == 10 || b2 == 9; b2 = bytes[++offset]) {}
            final byte b3 = b2;
            byte b4 = bytes[++offset];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (b4 != b3) {
                b4 = bytes[++offset];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        byte b5;
        for (b5 = bytes[offset]; b5 == 32 || b5 == 10 || b5 == 9; b5 = bytes[++offset]) {}
        XMLString systemID;
        if (b5 == 39 || b5 == 34) {
            final byte b6 = b5;
            byte b7 = bytes[++offset];
            systemID = dtdParams.getSystemID();
            systemID.offset = offset;
            while (b7 != b6) {
                b7 = bytes[++offset];
            }
            systemID.endOffset = offset;
            b5 = bytes[++offset];
        }
        else {
            systemID = null;
        }
        internalSubsetHandler.notationDecl(notationName, publicID, systemID);
        dtdParams.resetNotationName();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        while (b5 != 62) {
            b5 = bytes[++offset];
        }
        return ++offset;
    }
}
