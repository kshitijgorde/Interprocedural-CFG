// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class UTF16InternalSubsetScanner extends UTF16MarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        return scanIntSubsetDecl(internalSubsetHandler, dtdParams, parsedEntity);
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int endOffset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = chars[endOffset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++endOffset]) {}
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
                    c = chars[++endOffset];
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        int n;
        if (chars[offset] == '!') {
            if (chars[++offset] == '-') {
                parsedEntity.offset = offset + 2;
                n = UTF16MarkupDeclScanner.scanComment(internalSubsetHandler, dtdParams, parsedEntity);
            }
            else {
                final char c = chars[offset + 1];
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
            n = UTF16MarkupDeclScanner.scanPI(internalSubsetHandler, dtdParams, parsedEntity);
        }
        return n;
    }
    
    private static int scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        char[] chars;
        int n;
        char c;
        for (chars = parsedEntity.chars, n = parsedEntity.offset, c = chars[n]; c == ' ' || c == '\n' || c == '\t'; c = chars[++n]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = n;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = chars[++n];
            if (c == ':') {
                elementType.sepOffset = n;
                c = chars[++n];
            }
        }
        elementType.endOffset = n;
        internalSubsetHandler.startElementDecl(elementType);
        while (c == ' ' || c == '\n' || c == '\t') {
            c = chars[++n];
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
            char c2 = chars[++n];
            internalSubsetHandler.contentModelStartGroup();
            while (c2 == ' ' || c2 == '\n' || c2 == '\t') {
                c2 = chars[++n];
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
        for (char c3 = chars[n]; c3 != '>'; c3 = chars[++n]) {}
        ++n;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return n;
    }
    
    private static int scanMixed(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        while (true) {
            char c;
            for (c = chars[offset]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = chars[++offset]) {}
            if (c == ')') {
                break;
            }
            char c2 = chars[++offset];
            internalSubsetHandler.contentModelSeparator(0);
            while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                c2 = chars[++offset];
            }
            parsedEntity.offset = offset;
            final QName contentModelElement = dtdParams.getContentModelElement();
            contentModelElement.offset = offset;
            char c3;
            do {
                c3 = chars[++offset];
                if (c3 == ':') {
                    contentModelElement.sepOffset = offset;
                    c3 = chars[++offset];
                }
            } while (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r' && c3 != '%' && c3 != '\0' && c3 != '|' && c3 != ')');
            contentModelElement.endOffset = offset;
            internalSubsetHandler.contentModelElement(contentModelElement);
            dtdParams.resetContentModelElement();
        }
        final char c4 = chars[++offset];
        internalSubsetHandler.contentModelEndGroup();
        if (c4 == '*') {
            ++offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        return offset;
    }
    
    private static int scanChildren(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final char[] chars = parsedEntity.chars;
        int scanCp;
        while (true) {
            char c;
            for (scanCp = scanCp(internalSubsetHandler, dtdParams, parsedEntity, n), c = chars[scanCp]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = chars[++scanCp]) {}
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
            for (char c2 = chars[scanCp]; c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r'; c2 = chars[++scanCp]) {}
            parsedEntity.offset = scanCp;
        }
        final char c3 = chars[scanCp];
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
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        if (chars[offset] == '(') {
            char c = chars[++offset];
            internalSubsetHandler.contentModelStartGroup();
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                c = chars[++offset];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        contentModelElement.offset = offset;
        char c2;
        do {
            c2 = chars[++offset];
            if (c2 == ':') {
                contentModelElement.sepOffset = offset;
                c2 = chars[++offset];
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
        char[] chars;
        int offset;
        char c;
        for (chars = parsedEntity.chars, offset = parsedEntity.offset, c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
        final QName elementType = dtdParams.getElementType();
        elementType.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t' && c != '>') {
            c = chars[++offset];
            if (c == ':') {
                elementType.sepOffset = offset;
                c = chars[++offset];
            }
        }
        elementType.endOffset = offset;
        internalSubsetHandler.startAttlistDecl(elementType);
        while (c == ' ' || c == '\n' || c == '\t') {
            c = chars[++offset];
        }
        while (c != '>') {
            final QName attributeName = dtdParams.getAttributeName();
            attributeName.offset = offset;
            while (c != ' ' && c != '\n' && c != '\t') {
                c = chars[++offset];
                if (c == ':') {
                    attributeName.sepOffset = offset;
                    c = chars[++offset];
                }
            }
            attributeName.endOffset = offset;
            while (c == ' ' || c == '\n' || c == '\t') {
                c = chars[++offset];
            }
            boolean b = c == '(';
            XMLString attributeType;
            if (!b) {
                b = (chars[offset + 1] == 'O');
                attributeType = dtdParams.getAttributeType();
                attributeType.offset = offset;
                while (c != ' ' && c != '\n' && c != '\t') {
                    c = chars[++offset];
                }
                attributeType.endOffset = offset;
                if (b) {
                    for (c = chars[++offset]; c != '('; c = chars[++offset]) {}
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
                    for (c2 = chars[++offset]; c2 == ' ' || c2 == '\n' || c2 == '\t'; c2 = chars[++offset]) {}
                    final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                    enumerationTypeToken.offset = offset;
                    while (c2 != ' ' && c2 != '\n' && c2 != '\t' && c2 != '|' && c2 != ')') {
                        c2 = chars[++offset];
                    }
                    enumerationTypeToken.endOffset = offset;
                    internalSubsetHandler.enumerationType(enumerationTypeToken);
                    dtdParams.resetEnumerationTypeToken();
                    while (c2 == ' ' || c2 == '\n' || c2 == '\t') {
                        c2 = chars[++offset];
                    }
                } while (c2 != ')');
                c = chars[++offset];
                internalSubsetHandler.endEnumerationType();
            }
            while (c == ' ' || c == '\n' || c == '\t') {
                c = chars[++offset];
            }
            boolean b2 = true;
            XMLString attributeDefaultType;
            if (c == '#') {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = offset;
                final char c3 = chars[++offset];
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
                for (c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
            }
            else {
                attributeDefaultType = null;
            }
            if (b2) {
                final char c4 = c;
                ++offset;
                internalSubsetHandler.startDefaultAttValue();
                parsedEntity.offset = offset;
                UTF16MarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, dtdParams, parsedEntity, c4);
                for (offset = parsedEntity.offset, c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
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
        char[] chars;
        int offset;
        char c;
        for (chars = parsedEntity.chars, offset = parsedEntity.offset, c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
        boolean b;
        if (c == '%') {
            b = true;
            for (c = chars[++offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
        }
        else {
            b = false;
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        entityDeclName.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = chars[++offset];
        }
        entityDeclName.endOffset = offset;
        while (c == ' ' || c == '\n' || c == '\t') {
            c = chars[++offset];
        }
        if (c == '\"' || c == '\'') {
            final char c2 = c;
            ++offset;
            internalSubsetHandler.startEntityValue();
            parsedEntity.offset = offset;
            UTF16MarkupDeclScanner.scanEntityValue(internalSubsetHandler, dtdParams, parsedEntity, c2);
            int offset2 = parsedEntity.offset;
            if (b) {
                internalSubsetHandler.internalPEDecl(entityDeclName);
            }
            else {
                internalSubsetHandler.internalEntityDecl(entityDeclName);
            }
            dtdParams.resetEntityDeclName();
            for (char c3 = chars[offset2]; c3 != '>'; c3 = chars[++offset2]) {}
            return ++offset2;
        }
        XMLString publicID;
        if (c == 'P') {
            char c4;
            for (offset += 6, c4 = chars[offset]; c4 != '\"' && c4 != '\''; c4 = chars[++offset]) {}
            final char c5 = c4;
            char c6 = chars[++offset];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (c6 != c5) {
                c6 = chars[++offset];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        char c7;
        for (c7 = chars[offset]; c7 != '\"' && c7 != '\''; c7 = chars[++offset]) {}
        final char c8 = c7;
        char c9 = chars[++offset];
        final XMLString systemID = dtdParams.getSystemID();
        systemID.offset = offset;
        while (c9 != c8) {
            c9 = chars[++offset];
        }
        systemID.endOffset = offset;
        char c10;
        for (c10 = chars[++offset]; c10 == ' ' || c10 == '\n' || c10 == '\t'; c10 = chars[++offset]) {}
        if (!b && c10 == 'N') {
            for (offset += 5, c10 = chars[offset]; c10 == ' ' || c10 == '\n' || c10 == '\t'; c10 = chars[++offset]) {}
            final XMLName notationName = dtdParams.getNotationName();
            notationName.offset = offset;
            while (c10 != ' ' && c10 != '\n' && c10 != '\t' && c10 != '>') {
                c10 = chars[++offset];
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
            c10 = chars[++offset];
        }
        return ++offset;
    }
    
    private static int scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        char[] chars;
        int offset;
        char c;
        for (chars = parsedEntity.chars, offset = parsedEntity.offset, c = chars[offset]; c == ' ' || c == '\n' || c == '\t'; c = chars[++offset]) {}
        final XMLName notationName = dtdParams.getNotationName();
        notationName.offset = offset;
        while (c != ' ' && c != '\n' && c != '\t') {
            c = chars[++offset];
        }
        notationName.endOffset = offset;
        while (c == ' ' || c == '\n' || c == '\t') {
            c = chars[++offset];
        }
        XMLString publicID;
        if (c == 'P') {
            char c2;
            for (offset += 6, c2 = chars[offset]; c2 == ' ' || c2 == '\n' || c2 == '\t'; c2 = chars[++offset]) {}
            final char c3 = c2;
            char c4 = chars[++offset];
            publicID = dtdParams.getPublicID();
            publicID.offset = offset;
            while (c4 != c3) {
                c4 = chars[++offset];
            }
            publicID.endOffset = offset;
            ++offset;
        }
        else {
            offset += 6;
            publicID = null;
        }
        char c5;
        for (c5 = chars[offset]; c5 == ' ' || c5 == '\n' || c5 == '\t'; c5 = chars[++offset]) {}
        XMLString systemID;
        if (c5 == '\'' || c5 == '\"') {
            final char c6 = c5;
            char c7 = chars[++offset];
            systemID = dtdParams.getSystemID();
            systemID.offset = offset;
            while (c7 != c6) {
                c7 = chars[++offset];
            }
            systemID.endOffset = offset;
            c5 = chars[++offset];
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
            c5 = chars[++offset];
        }
        return ++offset;
    }
}
