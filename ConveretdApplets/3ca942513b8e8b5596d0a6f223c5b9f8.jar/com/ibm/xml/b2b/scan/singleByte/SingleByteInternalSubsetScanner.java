// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class SingleByteInternalSubsetScanner extends SingleByteMarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        return scanIntSubsetDecl(internalSubsetHandler, dtdParams, parsedEntity);
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int endOffset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = byteToCharMap[bytes[endOffset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++endOffset] & 0xFF]) {}
            if (c == '<') {
                parsedEntity.offset = endOffset + 1;
                endOffset = scanMarkupDecl(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                if (c != '%') {
                    break;
                }
                final XMLName peName = dtdParams.getPEName();
                peName.offset = ++endOffset;
                while (c != ';') {
                    c = byteToCharMap[bytes[++endOffset] & 0xFF];
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
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        int n;
        if (byteToCharMap[bytes[offset] & 0xFF] == '!') {
            if (byteToCharMap[bytes[++offset] & 0xFF] == '-') {
                parsedEntity.offset = offset + 2;
                n = SingleByteMarkupDeclScanner.scanComment(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                final char c = byteToCharMap[bytes[offset + 1] & 0xFF];
                if (c == 'L') {
                    parsedEntity.offset = offset + 7;
                    n = scanElementDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
                else if (c == 'T') {
                    parsedEntity.offset = offset + 7;
                    n = scanAttlistDecl(internalSubsetHandler, dtdParams, parsedEntity);
                }
                else if (c == 'N') {
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
            n = SingleByteMarkupDeclScanner.scanPI(internalSubsetHandler, dtdParams, parsedEntity);
        }
        return n;
    }
    
    private static int scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        char[] byteToCharMap;
        byte[] bytes;
        int n;
        char c;
        for (byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap, bytes = parsedEntity.bytes, n = parsedEntity.offset, c = byteToCharMap[bytes[n] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = n;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = byteToCharMap[bytes[++n] & 0xFF];
            if (c == ':') {
                elementType.sepOffset = n;
                c = byteToCharMap[bytes[++n] & 0xFF];
            }
        }
        elementType.endOffset = n;
        internalSubsetHandler.startElementDecl(elementType);
        while (c == ' ' || c == '\n' || c == '\t') {
            c = byteToCharMap[bytes[++n] & 0xFF];
        }
        if (c == 'A') {
            n += 3;
            internalSubsetHandler.contentModelANY();
        }
        else if (c == 'E') {
            n += 5;
            internalSubsetHandler.contentModelEMPTY();
        }
        else {
            char c2 = byteToCharMap[bytes[++n] & 0xFF];
            internalSubsetHandler.contentModelStartGroup();
            while (c2 == ' ' || c2 == '\n' || c2 == '\t') {
                c2 = byteToCharMap[bytes[++n] & 0xFF];
            }
            if (c2 == '#') {
                parsedEntity.offset = n + 7;
                internalSubsetHandler.contentModelPCDATA();
                n = scanMixed(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                parsedEntity.offset = n;
                n = scanChildren(internalSubsetHandler, dtdParams, parsedEntity, 1);
            }
        }
        for (char c3 = byteToCharMap[bytes[n] & 0xFF]; c3 != '>'; c3 = byteToCharMap[bytes[++n] & 0xFF]) {}
        ++n;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return n;
    }
    
    private static int scanMixed(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
            if (c == ')') {
                break;
            }
            char c2 = byteToCharMap[bytes[++offset] & 0xFF];
            internalSubsetHandler.contentModelSeparator(0);
            while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                c2 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            parsedEntity.offset = offset;
            final QName contentModelElement = dtdParams.getContentModelElement();
            contentModelElement.offset = offset;
            char c3;
            do {
                c3 = byteToCharMap[bytes[++offset] & 0xFF];
                if (c3 == ':') {
                    contentModelElement.sepOffset = offset;
                    c3 = byteToCharMap[bytes[++offset] & 0xFF];
                }
            } while (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r' && c3 != '%' && c3 != '\0' && c3 != '|' && c3 != ')');
            contentModelElement.endOffset = offset;
            internalSubsetHandler.contentModelElement(contentModelElement);
            dtdParams.resetContentModelElement();
        }
        final char c4 = byteToCharMap[bytes[++offset] & 0xFF];
        internalSubsetHandler.contentModelEndGroup();
        if (c4 == '*') {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        return offset;
    }
    
    private static int scanChildren(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int scanCp;
        while (true) {
            char c;
            for (scanCp = scanCp(internalSubsetHandler, dtdParams, parsedEntity, n), c = byteToCharMap[bytes[scanCp] & 0xFF]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = byteToCharMap[bytes[++scanCp] & 0xFF]) {}
            ++scanCp;
            if (c == '|') {
                internalSubsetHandler.contentModelSeparator(0);
            }
            else {
                if (c != ',') {
                    break;
                }
                internalSubsetHandler.contentModelSeparator(1);
            }
            for (char c2 = byteToCharMap[bytes[scanCp] & 0xFF]; c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r'; c2 = byteToCharMap[bytes[++scanCp] & 0xFF]) {}
            parsedEntity.offset = scanCp;
        }
        final char c3 = byteToCharMap[bytes[scanCp] & 0xFF];
        internalSubsetHandler.contentModelEndGroup();
        if (c3 == '?') {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (c3 == '*') {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (c3 == '+') {
            ++scanCp;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return scanCp;
    }
    
    private static int scanCp(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        if (byteToCharMap[bytes[offset] & 0xFF] == '(') {
            char c = byteToCharMap[bytes[++offset] & 0xFF];
            internalSubsetHandler.contentModelStartGroup();
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        contentModelElement.offset = offset;
        char c2;
        do {
            c2 = byteToCharMap[bytes[++offset] & 0xFF];
            if (c2 == ':') {
                contentModelElement.sepOffset = offset;
                c2 = byteToCharMap[bytes[++offset] & 0xFF];
            }
        } while (c2 != ' ' && c2 != '\n' && c2 != '\t' && c2 != '\r' && c2 != '%' && c2 != '\0' && c2 != '|' && c2 != ',' && c2 != ')' && c2 != '?' && c2 != '*' && c2 != '+');
        contentModelElement.endOffset = offset;
        internalSubsetHandler.contentModelElement(contentModelElement);
        dtdParams.resetContentModelElement();
        if (c2 == '?') {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (c2 == '*') {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (c2 == '+') {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return offset;
    }
    
    private static int scanAttlistDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        char[] byteToCharMap;
        byte[] bytes;
        int offset;
        char c;
        for (byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap, bytes = parsedEntity.bytes, offset = parsedEntity.offset, c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t' && c != '>') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
            if (c == ':') {
                elementType.sepOffset = offset;
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
        }
        elementType.endOffset = offset;
        internalSubsetHandler.startAttlistDecl(elementType);
        while (c == ' ' || c == '\n' || c == '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        while (c != '>') {
            final QName attributeName = dtdParams.getAttributeName();
            attributeName.offset = offset;
            while (c != ' ' && c != '\n' && c != '\t') {
                c = byteToCharMap[bytes[++offset] & 0xFF];
                if (c == ':') {
                    attributeName.sepOffset = offset;
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                }
            }
            attributeName.endOffset = offset;
            while (c == ' ' || c == '\n' || c == '\t') {
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
            boolean b = c == '(';
            XMLString attributeType;
            if (!b) {
                b = (byteToCharMap[bytes[offset + 1] & 0xFF] == 'O');
                attributeType = dtdParams.getAttributeType();
                attributeType.offset = offset;
                while (c != ' ' && c != '\n' && c != '\t') {
                    c = byteToCharMap[bytes[++offset] & 0xFF];
                }
                attributeType.endOffset = offset;
                if (b) {
                    for (c = byteToCharMap[bytes[++offset] & 0xFF]; c != '('; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
                }
            }
            else {
                attributeType = null;
            }
            internalSubsetHandler.startAttDef(attributeName, attributeType);
            if (b) {
                internalSubsetHandler.startEnumerationType();
                char c2;
                do {
                    for (c2 = byteToCharMap[bytes[++offset] & 0xFF]; c2 == ' ' || c2 == '\n' || c2 == '\t'; c2 = byteToCharMap[bytes[++offset] & 0xFF]) {}
                    final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                    enumerationTypeToken.offset = offset;
                    while (c2 != ' ' && c2 != '\n' && c2 != '\t' && c2 != '|' && c2 != ')') {
                        c2 = byteToCharMap[bytes[++offset] & 0xFF];
                    }
                    enumerationTypeToken.endOffset = offset;
                    internalSubsetHandler.enumerationType(enumerationTypeToken);
                    dtdParams.resetEnumerationTypeToken();
                    while (c2 == ' ' || c2 == '\n' || c2 == '\t') {
                        c2 = byteToCharMap[bytes[++offset] & 0xFF];
                    }
                } while (c2 != ')');
                c = byteToCharMap[bytes[++offset] & 0xFF];
                internalSubsetHandler.endEnumerationType();
            }
            while (c == ' ' || c == '\n' || c == '\t') {
                c = byteToCharMap[bytes[++offset] & 0xFF];
            }
            boolean b2 = true;
            XMLString attributeDefaultType;
            if (c == '#') {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = offset;
                final char c3 = byteToCharMap[bytes[++offset] & 0xFF];
                if (c3 == 'F') {
                    offset += 5;
                }
                else if (c3 == 'I') {
                    offset += 7;
                    b2 = false;
                }
                else {
                    offset += 8;
                    b2 = false;
                }
                attributeDefaultType.endOffset = offset;
                for (c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
            }
            else {
                attributeDefaultType = null;
            }
            if (b2) {
                final char c4 = c;
                ++offset;
                internalSubsetHandler.startDefaultAttValue();
                parsedEntity.offset = offset;
                SingleByteMarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, dtdParams, parsedEntity, c4);
                for (offset = parsedEntity.offset, c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
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
        char[] byteToCharMap;
        byte[] bytes;
        int offset;
        char c;
        for (byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap, bytes = parsedEntity.bytes, offset = parsedEntity.offset, c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
        boolean b;
        if (c == '%') {
            b = true;
            for (c = byteToCharMap[bytes[++offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
        }
        else {
            b = false;
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        entityDeclName.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        entityDeclName.endOffset = offset;
        while (c == ' ' || c == '\n' || c == '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        if (c == '\"' || c == '\'') {
            final char c2 = c;
            ++offset;
            internalSubsetHandler.startEntityValue();
            parsedEntity.offset = offset;
            SingleByteMarkupDeclScanner.scanEntityValue(internalSubsetHandler, dtdParams, parsedEntity, c2);
            int offset2 = parsedEntity.offset;
            if (b) {
                internalSubsetHandler.internalPEDecl(entityDeclName);
            }
            else {
                internalSubsetHandler.internalEntityDecl(entityDeclName);
            }
            dtdParams.resetEntityDeclName();
            for (char c3 = byteToCharMap[bytes[offset2] & 0xFF]; c3 != '>'; c3 = byteToCharMap[bytes[++offset2] & 0xFF]) {}
            return ++offset2;
        }
        XMLString publicID;
        if (c == 'P') {
            char c4;
            for (offset += 6, c4 = byteToCharMap[bytes[offset] & 0xFF]; c4 != '\"' && c4 != '\''; c4 = byteToCharMap[bytes[++offset] & 0xFF]) {}
            final char c5 = c4;
            char c6 = byteToCharMap[bytes[++offset] & 0xFF];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (c6 != c5) {
                c6 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        char c7;
        for (c7 = byteToCharMap[bytes[offset] & 0xFF]; c7 != '\"' && c7 != '\''; c7 = byteToCharMap[bytes[++offset] & 0xFF]) {}
        final char c8 = c7;
        char c9 = byteToCharMap[bytes[++offset] & 0xFF];
        final XMLString systemID = dtdParams.getSystemID();
        systemID.offset = offset;
        while (c9 != c8) {
            c9 = byteToCharMap[bytes[++offset] & 0xFF];
        }
        systemID.endOffset = offset;
        char c10;
        for (c10 = byteToCharMap[bytes[++offset] & 0xFF]; c10 == ' ' || c10 == '\n' || c10 == '\t'; c10 = byteToCharMap[bytes[++offset] & 0xFF]) {}
        if (!b && c10 == 'N') {
            for (offset += 5, c10 = byteToCharMap[bytes[offset] & 0xFF]; c10 == ' ' || c10 == '\n' || c10 == '\t'; c10 = byteToCharMap[bytes[++offset] & 0xFF]) {}
            final XMLName notationName = dtdParams.getNotationName();
            notationName.offset = offset;
            while (c10 != ' ' && c10 != '\n' && c10 != '\t' && c10 != '>') {
                c10 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            notationName.endOffset = offset;
            internalSubsetHandler.unparsedEntityDecl(entityDeclName, publicID, systemID, notationName);
            dtdParams.resetNotationName();
        }
        else if (b) {
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
        while (c10 != '>') {
            c10 = byteToCharMap[bytes[++offset] & 0xFF];
        }
        return ++offset;
    }
    
    private static int scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        char[] byteToCharMap;
        byte[] bytes;
        int offset;
        char c;
        for (byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap, bytes = parsedEntity.bytes, offset = parsedEntity.offset, c = byteToCharMap[bytes[offset] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++offset] & 0xFF]) {}
        final XMLName notationName = dtdParams.getNotationName();
        notationName.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        notationName.endOffset = offset;
        while (c == ' ' || c == '\n' || c == '\t') {
            c = byteToCharMap[bytes[++offset] & 0xFF];
        }
        XMLString publicID;
        if (c == 'P') {
            char c2;
            for (offset += 6, c2 = byteToCharMap[bytes[offset] & 0xFF]; c2 == ' ' || c2 == '\n' || c2 == '\t'; c2 = byteToCharMap[bytes[++offset] & 0xFF]) {}
            final char c3 = c2;
            char c4 = byteToCharMap[bytes[++offset] & 0xFF];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (c4 != c3) {
                c4 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        char c5;
        for (c5 = byteToCharMap[bytes[offset] & 0xFF]; c5 == ' ' || c5 == '\n' || c5 == '\t'; c5 = byteToCharMap[bytes[++offset] & 0xFF]) {}
        XMLString systemID;
        if (c5 == '\'' || c5 == '\"') {
            final char c6 = c5;
            char c7 = byteToCharMap[bytes[++offset] & 0xFF];
            systemID = dtdParams.getSystemID();
            systemID.offset = offset;
            while (c7 != c6) {
                c7 = byteToCharMap[bytes[++offset] & 0xFF];
            }
            systemID.endOffset = offset;
            c5 = byteToCharMap[bytes[++offset] & 0xFF];
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
        while (c5 != '>') {
            c5 = byteToCharMap[bytes[++offset] & 0xFF];
        }
        return ++offset;
    }
}
