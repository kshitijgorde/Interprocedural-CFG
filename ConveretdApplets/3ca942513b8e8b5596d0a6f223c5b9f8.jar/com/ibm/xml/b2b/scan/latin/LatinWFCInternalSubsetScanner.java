// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;

public final class LatinWFCInternalSubsetScanner extends LatinWFCMarkupDeclScanner
{
    public static boolean scanInternalSubset(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        byte b = bytes[offset];
        while (b >= 0) {
            switch (DocumentEntityState.contentMap[b]) {
                case 2: {
                    parsedEntity.offset = offset + 1;
                    if (!scanMarkupDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                        return false;
                    }
                    offset = parsedEntity.offset;
                    b = bytes[offset];
                    continue;
                }
                case 1: {
                    if (b == 32 || b == 10 || b == 9) {
                        do {
                            b = bytes[++offset];
                        } while (b == 32 || b == 10 || b == 9);
                        continue;
                    }
                    if (b != 37) {
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    final XMLName peName = dtdParams.getPEName();
                    parsedEntity.offset = offset + 1;
                    if (!latinWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                        return false;
                    }
                    internalSubsetHandler.internalSubsetPEReference(peName);
                    dtdParams.resetPEName();
                    offset = parsedEntity.offset;
                    b = bytes[offset];
                    continue;
                }
                case 3: {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                case 4: {
                    parsedEntity.offset = offset;
                    return true;
                }
                case 5: {
                    if (offset == parsedEntity.endOffset) {
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                        return false;
                    }
                    break;
                }
            }
            parsedEntity.offset = offset;
            latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
            return false;
        }
        parsedEntity.offset = offset;
        if (parsedEntity.skipValidCharacter()) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
            return false;
        }
        latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
        return false;
    }
    
    public static boolean scanIntSubsetDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        while (true) {
            byte b;
            for (b = bytes[offset]; b == 32 || b == 10 || b == 9; b = bytes[++offset]) {}
            if (b == 60) {
                parsedEntity.offset = offset + 1;
                if (!scanMarkupDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
                offset = parsedEntity.offset;
            }
            else if (b == 37) {
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = offset + 1;
                if (!latinWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                    return false;
                }
                internalSubsetHandler.internalSubsetPEReference(peName);
                dtdParams.resetPEName();
                offset = parsedEntity.offset;
            }
            else {
                parsedEntity.offset = offset;
                if (b == 0 && offset == parsedEntity.endOffset) {
                    return true;
                }
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
        }
    }
    
    private static boolean scanMarkupDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final byte b = bytes[offset];
        if (b == 33) {
            final byte b2 = bytes[++offset];
            if (b2 == 69 && bytes[offset + 1] == 76 && bytes[offset + 2] == 69 && bytes[offset + 3] == 77 && bytes[offset + 4] == 69 && bytes[offset + 5] == 78 && bytes[offset + 6] == 84) {
                parsedEntity.offset = offset + 7;
                if (!scanElementDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (b2 == 65 && bytes[offset + 1] == 84 && bytes[offset + 2] == 84 && bytes[offset + 3] == 76 && bytes[offset + 4] == 73 && bytes[offset + 5] == 83 && bytes[offset + 6] == 84) {
                parsedEntity.offset = offset + 7;
                if (!scanAttlistDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (b2 == 69 && bytes[offset + 1] == 78 && bytes[offset + 2] == 84 && bytes[offset + 3] == 73 && bytes[offset + 4] == 84 && bytes[offset + 5] == 89) {
                parsedEntity.offset = offset + 6;
                if (!scanEntityDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else if (b2 == 78 && bytes[offset + 1] == 79 && bytes[offset + 2] == 84 && bytes[offset + 3] == 65 && bytes[offset + 4] == 84 && bytes[offset + 5] == 73 && bytes[offset + 6] == 79 && bytes[offset + 7] == 78) {
                parsedEntity.offset = offset + 8;
                if (!scanNotationDecl(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                if (b2 != 45) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                if (bytes[offset + 1] != 45) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                    return false;
                }
                parsedEntity.offset = offset + 2;
                if (!LatinWFCMarkupDeclScanner.scanComment(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
        }
        else {
            if (b != 63) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                return false;
            }
            parsedEntity.offset = offset + 1;
            if (!LatinWFCMarkupDeclScanner.scanPI(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean scanElementDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        final int offset = parsedEntity.offset;
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 21);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (latinWFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 22);
            return false;
        }
        internalSubsetHandler.startElementDecl(elementType);
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.setParameter(0, elementType);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 23);
            return false;
        }
        int offset2 = parsedEntity.offset;
        final byte b = bytes[offset2];
        if (b == 65 && bytes[offset2 + 1] == 78 && bytes[offset2 + 2] == 89) {
            offset2 += 3;
            internalSubsetHandler.contentModelANY();
        }
        else if (b == 69 && bytes[offset2 + 1] == 77 && bytes[offset2 + 2] == 80 && bytes[offset2 + 3] == 84 && bytes[offset2 + 4] == 89) {
            offset2 += 5;
            internalSubsetHandler.contentModelEMPTY();
        }
        else {
            if (b != 40) {
                latinWFCScannerSupport.setParameter(0, elementType);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 24);
                return false;
            }
            byte b2 = bytes[++offset2];
            internalSubsetHandler.contentModelStartGroup();
            while (b2 == 32 || b2 == 10 || b2 == 9 || b2 == 13) {
                b2 = bytes[++offset2];
            }
            if (b2 == 35 && bytes[offset2 + 1] == 80 && bytes[offset2 + 2] == 67 && bytes[offset2 + 3] == 68 && bytes[offset2 + 4] == 65 && bytes[offset2 + 5] == 84 && bytes[offset2 + 6] == 65) {
                parsedEntity.offset = offset2 + 7;
                internalSubsetHandler.contentModelPCDATA();
                if (!scanMixed(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                    return false;
                }
            }
            else {
                parsedEntity.offset = offset2;
                if (!scanChildren(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity, 1)) {
                    return false;
                }
            }
            offset2 = parsedEntity.offset;
        }
        byte b3;
        for (b3 = bytes[offset2]; b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13; b3 = bytes[++offset2]) {}
        if (b3 != 62) {
            latinWFCScannerSupport.setParameter(0, elementType);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 25);
            return false;
        }
        parsedEntity.offset = offset2 + 1;
        internalSubsetHandler.endElementDecl();
        dtdParams.resetElementType();
        return true;
    }
    
    private static boolean scanMixed(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        int n2 = 0;
        while (true) {
            byte b;
            for (b = bytes[n]; b == 32 || b == 10 || b == 9 || b == 13; b = bytes[++n]) {}
            if (b != 124) {
                if (b != 41) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 29);
                    return false;
                }
                final byte b2 = bytes[++n];
                internalSubsetHandler.contentModelEndGroup();
                if (b2 == 42) {
                    ++n;
                    internalSubsetHandler.contentModelOccurrence(1);
                }
                else if (n2 != 0) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 30);
                    return false;
                }
                parsedEntity.offset = n;
                return true;
            }
            else {
                byte b3 = bytes[++n];
                internalSubsetHandler.contentModelSeparator(0);
                n2 = 1;
                while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13) {
                    b3 = bytes[++n];
                }
                parsedEntity.offset = n;
                final QName contentModelElement = dtdParams.getContentModelElement();
                if (latinWFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 28);
                    return false;
                }
                internalSubsetHandler.contentModelElement(contentModelElement);
                dtdParams.resetContentModelElement();
                n = parsedEntity.offset;
            }
        }
    }
    
    private static boolean scanChildren(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int n2 = -1;
        while (scanCp(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity, n)) {
            int offset;
            byte b;
            for (offset = parsedEntity.offset, b = bytes[offset]; b == 32 || b == 10 || b == 9 || b == 13; b = bytes[++offset]) {}
            if (b == 41) {
                final byte b2 = bytes[++offset];
                internalSubsetHandler.contentModelEndGroup();
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
                parsedEntity.offset = offset;
                return true;
            }
            if (b == 124) {
                if (n2 == 1) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 0;
            }
            else {
                if (b != 44) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                if (n2 == 0) {
                    latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 1;
            }
            byte b3 = bytes[++offset];
            internalSubsetHandler.contentModelSeparator(n2);
            while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13) {
                b3 = bytes[++offset];
            }
            parsedEntity.offset = offset;
        }
        return false;
    }
    
    private static boolean scanCp(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        if (bytes[offset] == 40) {
            byte b = bytes[++offset];
            internalSubsetHandler.contentModelStartGroup();
            while (b == 32 || b == 10 || b == 9 || b == 13) {
                b = bytes[++offset];
            }
            parsedEntity.offset = offset;
            return scanChildren(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity, n + 1);
        }
        final QName contentModelElement = dtdParams.getContentModelElement();
        if (latinWFCScannerSupport.scanQName(parsedEntity, contentModelElement) == 0) {
            latinWFCScannerSupport.setParameter(0, dtdParams.elementType());
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 26);
            return false;
        }
        internalSubsetHandler.contentModelElement(contentModelElement);
        dtdParams.resetContentModelElement();
        final byte b2 = bytes[parsedEntity.offset];
        if (b2 == 63) {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(0);
        }
        else if (b2 == 42) {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(1);
        }
        else if (b2 == 43) {
            ++parsedEntity.offset;
            internalSubsetHandler.contentModelOccurrence(2);
        }
        return true;
    }
    
    private static boolean scanAttlistDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 31);
            return false;
        }
        final QName elementType = dtdParams.getElementType();
        if (latinWFCScannerSupport.scanQName(parsedEntity, elementType) == 0) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 32);
            return false;
        }
        internalSubsetHandler.startAttlistDecl(elementType);
        int n = parsedEntity.offset;
        byte b = bytes[n];
        while (true) {
            final boolean b2 = b == 32 || b == 10 || b == 9 || b == 13;
            if (b2) {
                do {
                    b = bytes[++n];
                } while (b == 32 || b == 10 || b == 9 || b == 13);
            }
            if (b == 62) {
                parsedEntity.offset = n + 1;
                internalSubsetHandler.endAttlistDecl();
                dtdParams.resetElementType();
                return true;
            }
            if (!b2) {
                latinWFCScannerSupport.setParameter(0, elementType);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 33);
                return false;
            }
            parsedEntity.offset = n;
            final QName attributeName = dtdParams.getAttributeName();
            if (latinWFCScannerSupport.scanQName(parsedEntity, attributeName) == 0) {
                latinWFCScannerSupport.setParameter(0, elementType);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 34);
                return false;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.setParameter(0, elementType);
                latinWFCScannerSupport.setParameter(1, attributeName);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 35);
                return false;
            }
            int n2 = parsedEntity.offset;
            int n3 = (bytes[n2] == 40) ? 1 : 0;
            XMLString attributeType;
            if (n3 == 0) {
                attributeType = dtdParams.getAttributeType();
                if (!scanAttType(parsedEntity, attributeType)) {
                    latinWFCScannerSupport.setParameter(0, elementType);
                    latinWFCScannerSupport.setParameter(1, attributeName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 36);
                    return false;
                }
                n2 = parsedEntity.offset;
                final byte b3 = bytes[n2];
                if (latinWFCScannerSupport.isNotationType(attributeType)) {
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        latinWFCScannerSupport.setParameter(0, elementType);
                        latinWFCScannerSupport.setParameter(1, attributeName);
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 39);
                        return false;
                    }
                    n2 = parsedEntity.offset;
                    if (bytes[n2] != 40) {
                        latinWFCScannerSupport.setParameter(0, elementType);
                        latinWFCScannerSupport.setParameter(1, attributeName);
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 40);
                        return false;
                    }
                    n3 = 1;
                }
            }
            else {
                attributeType = null;
            }
            internalSubsetHandler.startAttDef(attributeName, attributeType);
            if (n3 != 0) {
                internalSubsetHandler.startEnumerationType();
                while (true) {
                    final byte b4 = bytes[++n2];
                    if (b4 != 32 && b4 != 10 && b4 != 9 && b4 != 13) {
                        parsedEntity.offset = n2;
                        if (attributeType != null) {
                            final XMLName enumerationTypeName = dtdParams.getEnumerationTypeName();
                            if (latinWFCScannerSupport.scanName(parsedEntity, enumerationTypeName) == 0) {
                                latinWFCScannerSupport.setParameter(0, elementType);
                                latinWFCScannerSupport.setParameter(1, attributeName);
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 41);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeName);
                            dtdParams.resetEnumerationTypeName();
                        }
                        else {
                            final XMLString enumerationTypeToken = dtdParams.getEnumerationTypeToken();
                            if (latinWFCScannerSupport.scanNmtoken(parsedEntity, enumerationTypeToken) == 0) {
                                latinWFCScannerSupport.setParameter(0, elementType);
                                latinWFCScannerSupport.setParameter(1, attributeName);
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 43);
                                return false;
                            }
                            internalSubsetHandler.enumerationType(enumerationTypeToken);
                            dtdParams.resetEnumerationTypeToken();
                        }
                        byte b5;
                        for (n2 = parsedEntity.offset, b5 = bytes[n2]; b5 == 32 || b5 == 10 || b5 == 9 || b5 == 13; b5 = bytes[++n2]) {}
                        if (b5 == 124) {
                            continue;
                        }
                        if (b5 != 41) {
                            latinWFCScannerSupport.setParameter(0, elementType);
                            latinWFCScannerSupport.setParameter(1, attributeName);
                            if (attributeType != null) {
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 42);
                            }
                            else {
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 44);
                            }
                            return false;
                        }
                        ++n2;
                        internalSubsetHandler.endEnumerationType();
                        break;
                    }
                }
            }
            parsedEntity.offset = n2;
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.setParameter(0, elementType);
                latinWFCScannerSupport.setParameter(1, attributeName);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 37);
                return false;
            }
            n = parsedEntity.offset;
            b = bytes[n];
            XMLString attributeDefaultType;
            boolean b7;
            if (b == 35) {
                attributeDefaultType = dtdParams.getAttributeDefaultType();
                attributeDefaultType.offset = n;
                final byte b6 = bytes[++n];
                if (b6 == 70 && bytes[n + 1] == 73 && bytes[n + 2] == 88 && bytes[n + 3] == 69 && bytes[n + 4] == 68) {
                    n += 5;
                    attributeDefaultType.endOffset = n;
                    parsedEntity.offset = n;
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        latinWFCScannerSupport.setParameter(0, elementType);
                        latinWFCScannerSupport.setParameter(1, attributeName);
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 45);
                        return false;
                    }
                    n = parsedEntity.offset;
                    b7 = true;
                    b = bytes[n];
                }
                else {
                    if (b6 == 73 && bytes[n + 1] == 77 && bytes[n + 2] == 80 && bytes[n + 3] == 76 && bytes[n + 4] == 73 && bytes[n + 5] == 69 && bytes[n + 6] == 68) {
                        n += 7;
                    }
                    else {
                        if (b6 != 82 || bytes[n + 1] != 69 || bytes[n + 2] != 81 || bytes[n + 3] != 85 || bytes[n + 4] != 73 || bytes[n + 5] != 82 || bytes[n + 6] != 69 || bytes[n + 7] != 68) {
                            latinWFCScannerSupport.setParameter(0, elementType);
                            latinWFCScannerSupport.setParameter(1, attributeName);
                            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 38);
                            return false;
                        }
                        n += 8;
                    }
                    attributeDefaultType.endOffset = n;
                    b7 = false;
                    b = bytes[n];
                }
            }
            else {
                attributeDefaultType = null;
                b7 = true;
            }
            if (b7) {
                final byte b8 = b;
                if (b8 != 39 && b8 != 34) {
                    latinWFCScannerSupport.setParameter(0, elementType);
                    latinWFCScannerSupport.setParameter(1, attributeName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
                ++n;
                internalSubsetHandler.startDefaultAttValue();
                latinWFCScannerSupport.startDefaultAttValue(elementType, attributeName);
                parsedEntity.offset = n;
                if (!LatinWFCMarkupDeclScanner.scanDefaultAttValue(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity, b8)) {
                    return false;
                }
                n = parsedEntity.offset;
                b = bytes[n];
            }
            internalSubsetHandler.endAttDef(attributeDefaultType);
            dtdParams.resetAttributeName();
            if (attributeType != null) {
                dtdParams.resetAttributeType();
            }
            if (attributeDefaultType == null) {
                continue;
            }
            dtdParams.resetAttributeDefaultType();
        }
    }
    
    private static boolean scanAttType(final ParsedEntity parsedEntity, final XMLString xmlString) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        xmlString.offset = offset;
        final byte b = bytes[offset];
        if (b == 67 && bytes[offset + 1] == 68 && bytes[offset + 2] == 65 && bytes[offset + 3] == 84 && bytes[offset + 4] == 65) {
            offset += 5;
        }
        else if (b == 73 && bytes[offset + 1] == 68) {
            if (bytes[offset + 2] == 82 && bytes[offset + 3] == 69 && bytes[offset + 4] == 70) {
                if (bytes[offset + 5] == 83) {
                    offset += 6;
                }
                else {
                    offset += 5;
                }
            }
            else {
                offset += 2;
            }
        }
        else if (b == 69 && bytes[offset + 1] == 78 && bytes[offset + 2] == 84 && bytes[offset + 3] == 73 && bytes[offset + 4] == 84) {
            if (bytes[offset + 5] == 89) {
                offset += 6;
            }
            else {
                if (bytes[offset + 5] != 73 || bytes[offset + 6] != 69 || bytes[offset + 7] != 83) {
                    return false;
                }
                offset += 8;
            }
        }
        else if (b == 78 && bytes[offset + 1] == 77 && bytes[offset + 2] == 84 && bytes[offset + 3] == 79 && bytes[offset + 4] == 75 && bytes[offset + 5] == 69 && bytes[offset + 6] == 78) {
            if (bytes[offset + 7] == 83) {
                offset += 8;
            }
            else {
                offset += 7;
            }
        }
        else {
            if (b != 78 || bytes[offset + 1] != 79 || bytes[offset + 2] != 84 || bytes[offset + 3] != 65 || bytes[offset + 4] != 84 || bytes[offset + 5] != 73 || bytes[offset + 6] != 79 || bytes[offset + 7] != 78) {
                return false;
            }
            offset += 8;
        }
        xmlString.endOffset = offset;
        parsedEntity.offset = offset;
        return true;
    }
    
    private static boolean scanEntityDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 50);
            return false;
        }
        final int offset = parsedEntity.offset;
        final boolean b = bytes[offset] == 37;
        if (b) {
            parsedEntity.offset = offset + 1;
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 52);
                return false;
            }
        }
        final XMLName entityDeclName = dtdParams.getEntityDeclName();
        if (latinWFCScannerSupport.scanName(parsedEntity, entityDeclName) == 0) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 53);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.setParameter(0, entityDeclName);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 54);
            return false;
        }
        final int offset2 = parsedEntity.offset;
        final byte b2 = bytes[offset2];
        if (b2 == 34 || b2 == 39) {
            final byte b3 = b2;
            parsedEntity.offset = offset2 + 1;
            internalSubsetHandler.startEntityValue();
            if (!LatinWFCMarkupDeclScanner.scanEntityValue(internalSubsetHandler, latinWFCScannerSupport, dtdParams, parsedEntity, b3, true)) {
                return false;
            }
            int offset3;
            byte b4;
            for (offset3 = parsedEntity.offset, b4 = bytes[offset3]; b4 == 32 || b4 == 10 || b4 == 9 || b4 == 13; b4 = bytes[++offset3]) {}
            if (b4 != 62) {
                latinWFCScannerSupport.setParameter(0, entityDeclName);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                return false;
            }
            parsedEntity.offset = offset3 + 1;
            if (b) {
                internalSubsetHandler.internalPEDecl(entityDeclName);
            }
            else {
                internalSubsetHandler.internalEntityDecl(entityDeclName);
            }
            dtdParams.resetEntityDeclName();
            return true;
        }
        else {
            XMLString publicID;
            if (b2 == 80 && bytes[offset2 + 1] == 85 && bytes[offset2 + 2] == 66 && bytes[offset2 + 3] == 76 && bytes[offset2 + 4] == 73 && bytes[offset2 + 5] == 67) {
                parsedEntity.offset = offset2 + 6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                    return false;
                }
                final int offset4 = parsedEntity.offset;
                final byte b5 = bytes[offset4];
                if (b5 != 34 && b5 != 39) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                    return false;
                }
                parsedEntity.offset = offset4 + 1;
                publicID = dtdParams.getPublicID();
                if (!latinWFCScannerSupport.scanPublicID(parsedEntity, b5, publicID)) {
                    return false;
                }
            }
            else {
                if (b2 != 83 || bytes[offset2 + 1] != 89 || bytes[offset2 + 2] != 83 || bytes[offset2 + 3] != 84 || bytes[offset2 + 4] != 69 || bytes[offset2 + 5] != 77) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                    return false;
                }
                parsedEntity.offset = offset2 + 6;
                publicID = null;
            }
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            final int offset5 = parsedEntity.offset;
            final byte b6 = bytes[offset5];
            if (b6 != 34 && b6 != 39) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                return false;
            }
            parsedEntity.offset = offset5 + 1;
            final XMLString systemID = dtdParams.getSystemID();
            if (!latinWFCScannerSupport.scanSystemID(parsedEntity, b6, systemID)) {
                return false;
            }
            int offset6 = parsedEntity.offset;
            byte b7 = bytes[offset6];
            final boolean b8 = b7 == 32 || b7 == 10 || b7 == 9 || b7 == 13;
            if (b8) {
                do {
                    b7 = bytes[++offset6];
                } while (b7 == 32 || b7 == 10 || b7 == 9 || b7 == 13);
            }
            if (b7 == 62) {
                parsedEntity.offset = offset6 + 1;
                if (b) {
                    internalSubsetHandler.externalPEDecl(entityDeclName, publicID, systemID);
                }
                else {
                    internalSubsetHandler.externalEntityDecl(entityDeclName, publicID, systemID);
                }
            }
            else {
                if (b || !b8) {
                    latinWFCScannerSupport.setParameter(0, entityDeclName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                if (b7 != 78 || bytes[offset6 + 1] != 68 || bytes[offset6 + 2] != 65 || bytes[offset6 + 3] != 84 || bytes[offset6 + 4] != 65) {
                    latinWFCScannerSupport.setParameter(0, entityDeclName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                offset6 += 5;
                parsedEntity.offset = offset6;
                if (!scanRequiredWhitespace(parsedEntity)) {
                    latinWFCScannerSupport.setParameter(0, entityDeclName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 56);
                    return false;
                }
                final XMLName notationName = dtdParams.getNotationName();
                if (latinWFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
                    latinWFCScannerSupport.setParameter(0, entityDeclName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 57);
                    return false;
                }
                int offset7;
                byte b9;
                for (offset7 = parsedEntity.offset, b9 = bytes[offset7]; b9 == 32 || b9 == 10 || b9 == 9 || b9 == 13; b9 = bytes[++offset7]) {}
                if (b9 != 62) {
                    latinWFCScannerSupport.setParameter(0, entityDeclName);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                parsedEntity.offset = offset7 + 1;
                internalSubsetHandler.unparsedEntityDecl(entityDeclName, publicID, systemID, notationName);
                dtdParams.resetNotationName();
            }
            dtdParams.resetEntityDeclName();
            if (publicID != null) {
                dtdParams.resetPublicID();
            }
            dtdParams.resetSystemID();
            return true;
        }
    }
    
    private static boolean scanNotationDecl(final InternalSubsetHandler internalSubsetHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 64);
            return false;
        }
        final XMLName notationName = dtdParams.getNotationName();
        if (latinWFCScannerSupport.scanName(parsedEntity, notationName) == 0) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 65);
            return false;
        }
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.setParameter(0, notationName);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 66);
            return false;
        }
        int offset = parsedEntity.offset;
        final byte b = bytes[offset];
        XMLString publicID;
        if (b == 80 && bytes[offset + 1] == 85 && bytes[offset + 2] == 66 && bytes[offset + 3] == 76 && bytes[offset + 4] == 73 && bytes[offset + 5] == 67) {
            parsedEntity.offset = offset + 6;
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                return false;
            }
            final int offset2 = parsedEntity.offset;
            final byte b2 = bytes[offset2];
            if (b2 != 34 && b2 != 39) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                return false;
            }
            parsedEntity.offset = offset2 + 1;
            publicID = dtdParams.getPublicID();
            if (!latinWFCScannerSupport.scanPublicID(parsedEntity, b2, publicID)) {
                return false;
            }
            offset = parsedEntity.offset;
        }
        else {
            if (b != 83 || bytes[offset + 1] != 89 || bytes[offset + 2] != 83 || bytes[offset + 3] != 84 || bytes[offset + 4] != 69 || bytes[offset + 5] != 77) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                return false;
            }
            offset += 6;
            publicID = null;
        }
        byte b3 = bytes[offset];
        if (b3 != 62) {
            parsedEntity.offset = offset;
            if (!scanRequiredWhitespace(parsedEntity)) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            offset = parsedEntity.offset;
            b3 = bytes[offset];
        }
        XMLString systemID;
        if (b3 == 34 || b3 == 39) {
            final byte b4 = b3;
            parsedEntity.offset = offset + 1;
            systemID = dtdParams.getSystemID();
            if (!latinWFCScannerSupport.scanSystemID(parsedEntity, b4, systemID)) {
                return false;
            }
            offset = parsedEntity.offset;
            b3 = bytes[offset];
        }
        else {
            systemID = null;
        }
        while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13) {
            b3 = bytes[++offset];
        }
        if (b3 != 62) {
            latinWFCScannerSupport.setParameter(0, notationName);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 67);
            return false;
        }
        parsedEntity.offset = offset + 1;
        internalSubsetHandler.notationDecl(notationName, publicID, systemID);
        dtdParams.resetNotationName();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        return true;
    }
    
    private static boolean scanRequiredWhitespace(final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final byte b = bytes[offset];
        if (b == 32 || b == 10 || b == 9) {
            byte b2;
            do {
                b2 = bytes[++offset];
            } while (b2 == 32 || b2 == 10 || b2 == 9);
            parsedEntity.offset = offset;
            return true;
        }
        return false;
    }
}
