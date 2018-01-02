// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;

public class SingleByteWFCMarkupDeclScanner
{
    public static boolean scanDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset2;
        int offset = offset2 = parsedEntity.offset;
    Label_0040:
        while (true) {
            while (true) {
                char c = byteToCharMap[bytes[offset] & 0xFF];
                while (true) {
                    if (c < '\u0080') {
                        final byte b = attValueMap[c];
                        if (b == 1 || b == 6) {
                            c = byteToCharMap[bytes[++offset] & 0xFF];
                        }
                        else {
                            if (offset > offset2) {
                                final XMLString attValueCharacters = dtdParams.getAttValueCharacters();
                                attValueCharacters.offset = offset2;
                                attValueCharacters.endOffset = offset;
                                markupDeclHandler.defaultAttValueCharacters(attValueCharacters);
                                dtdParams.resetAttValueCharacters();
                            }
                            switch (b) {
                                case 4: {
                                    if (c == n) {
                                        parsedEntity.offset = offset + 1;
                                        return true;
                                    }
                                    offset2 = offset++;
                                    continue Label_0040;
                                }
                                case 3: {
                                    parsedEntity.offset = offset + 1;
                                    if (!checkReferenceInAttValue(markupDeclHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                                        return false;
                                    }
                                    offset = (offset2 = parsedEntity.offset);
                                    continue Label_0040;
                                }
                                case 2: {
                                    singleByteWFCScannerSupport.setAttValueErrorParameters();
                                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 25);
                                    return false;
                                }
                                case 5: {
                                    if (offset != parsedEntity.endOffset) {
                                        break Label_0040;
                                    }
                                    if (n == 0) {
                                        parsedEntity.offset = offset;
                                        return true;
                                    }
                                    singleByteWFCScannerSupport.setAttValueErrorParameters();
                                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 26);
                                    return false;
                                }
                                default: {
                                    break Label_0040;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = offset;
                        if (!parsedEntity.skipValidCharacter()) {
                            singleByteWFCScannerSupport.setAttValueErrorParameters();
                            singleByteWFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
                            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
                            return false;
                        }
                        offset = parsedEntity.offset;
                        c = byteToCharMap[bytes[offset] & 0xFF];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = offset;
        singleByteWFCScannerSupport.setAttValueErrorParameters();
        singleByteWFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
        singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
        return false;
    }
    
    public static boolean scanEntityValue(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n, final boolean b) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset;
        int n2 = offset = parsedEntity.offset;
        char c = byteToCharMap[bytes[n2] & 0xFF];
        while (true) {
            if (c < '\u0080') {
                Label_0246: {
                    switch (attValueMap[c]) {
                        case 1: {
                            if (c != '%') {
                                break Label_0246;
                            }
                            if (n2 > offset) {
                                final XMLString entityValueCharacters = dtdParams.getEntityValueCharacters();
                                entityValueCharacters.offset = offset;
                                entityValueCharacters.endOffset = n2;
                                markupDeclHandler.entityValueCharacters(entityValueCharacters);
                                dtdParams.resetEntityValueCharacters();
                            }
                            parsedEntity.offset = n2 + 1;
                            final XMLName peReferenceName = dtdParams.getPEReferenceName();
                            if (!singleByteWFCScannerSupport.scanPEReference(parsedEntity, peReferenceName)) {
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            if (b) {
                                singleByteWFCScannerSupport.setParameter(0, peReferenceName);
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 17);
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            n2 = parsedEntity.offset;
                            markupDeclHandler.peReferenceInEntityValue(peReferenceName);
                            dtdParams.resetPEReferenceName();
                            offset = n2;
                            c = byteToCharMap[bytes[n2] & 0xFF];
                            continue;
                        }
                        case 2:
                        case 6: {
                            c = byteToCharMap[bytes[++n2] & 0xFF];
                            continue;
                        }
                        case 3: {
                            if (n2 > offset) {
                                final XMLString entityValueCharacters2 = dtdParams.getEntityValueCharacters();
                                entityValueCharacters2.offset = offset;
                                entityValueCharacters2.endOffset = n2;
                                markupDeclHandler.entityValueCharacters(entityValueCharacters2);
                                dtdParams.resetEntityValueCharacters();
                            }
                            parsedEntity.offset = n2 + 1;
                            if (!checkReferenceInEntityValue(markupDeclHandler, singleByteWFCScannerSupport, dtdParams, parsedEntity)) {
                                return false;
                            }
                            n2 = (offset = parsedEntity.offset);
                            c = byteToCharMap[bytes[n2] & 0xFF];
                            continue;
                        }
                        case 4: {
                            if (c == n) {
                                if (n2 > offset) {
                                    final XMLString entityValueCharacters3 = dtdParams.getEntityValueCharacters();
                                    entityValueCharacters3.offset = offset;
                                    entityValueCharacters3.endOffset = n2;
                                    markupDeclHandler.entityValueCharacters(entityValueCharacters3);
                                    dtdParams.resetEntityValueCharacters();
                                }
                                parsedEntity.offset = n2 + 1;
                                return true;
                            }
                            c = byteToCharMap[bytes[++n2] & 0xFF];
                            continue;
                        }
                        case 5: {
                            if (n2 > offset) {
                                final XMLString entityValueCharacters4 = dtdParams.getEntityValueCharacters();
                                entityValueCharacters4.offset = offset;
                                entityValueCharacters4.endOffset = n2;
                                markupDeclHandler.entityValueCharacters(entityValueCharacters4);
                                dtdParams.resetEntityValueCharacters();
                            }
                            if ((parsedEntity.offset = n2) != parsedEntity.endOffset) {
                                singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                                return false;
                            }
                            if (n == 0) {
                                return true;
                            }
                            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 13);
                            return false;
                        }
                        default: {
                            if (n2 > offset) {
                                final XMLString entityValueCharacters5 = dtdParams.getEntityValueCharacters();
                                entityValueCharacters5.offset = offset;
                                entityValueCharacters5.endOffset = n2;
                                markupDeclHandler.entityValueCharacters(entityValueCharacters5);
                                dtdParams.resetEntityValueCharacters();
                            }
                            parsedEntity.offset = n2;
                            singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                            return false;
                        }
                    }
                }
            }
            else {
                parsedEntity.offset = n2;
                if (!parsedEntity.skipValidCharacter()) {
                    singleByteWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                    return false;
                }
                n2 = parsedEntity.offset;
                c = byteToCharMap[bytes[n2] & 0xFF];
            }
        }
    }
    
    protected static boolean scanComment(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLString content = dtdParams.getContent();
        if (singleByteWFCScannerSupport.scanComment(parsedEntity, content)) {
            markupDeclHandler.comment(content);
            dtdParams.resetContent();
            return true;
        }
        dtdParams.resetContent();
        return false;
    }
    
    protected static boolean scanPI(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLName piTarget = dtdParams.getPITarget();
        boolean b;
        if (singleByteWFCScannerSupport.scanPITarget(parsedEntity, piTarget)) {
            final XMLString content = dtdParams.getContent();
            if (singleByteWFCScannerSupport.scanPIData(parsedEntity, content)) {
                markupDeclHandler.processingInstruction(piTarget, content);
                b = true;
            }
            else {
                b = false;
            }
            dtdParams.resetContent();
        }
        else {
            b = false;
        }
        dtdParams.resetPITarget();
        return b;
    }
    
    private static boolean checkReferenceInAttValue(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap[parsedEntity.bytes[parsedEntity.offset] & 0xFF] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = singleByteWFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.defaultAttValueCharacter(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = singleByteWFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                if (singleByteWFCScannerSupport.entityReferenceInDefaultAttValue(markupDeclHandler, entityReferenceName)) {
                    dtdParams.resetEntityReferenceName();
                    return true;
                }
                dtdParams.resetEntityReferenceName();
                return false;
            }
            else {
                if (scanName == 0) {
                    singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                    dtdParams.resetEntityReferenceName();
                    return false;
                }
                singleByteWFCScannerSupport.setParameter(0, entityReferenceName);
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
                dtdParams.resetEntityReferenceName();
                return false;
            }
        }
    }
    
    private static boolean checkReferenceInEntityValue(final MarkupDeclHandler markupDeclHandler, final SingleByteWFCScannerSupport singleByteWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap[parsedEntity.bytes[parsedEntity.offset] & 0xFF] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = singleByteWFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.entityValueCharacter(scanCharacterReference);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = singleByteWFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                markupDeclHandler.entityReferenceInEntityValue(entityReferenceName);
                dtdParams.resetEntityReferenceName();
                return true;
            }
            if (scanName == 0) {
                singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                dtdParams.resetEntityReferenceName();
                return false;
            }
            singleByteWFCScannerSupport.setParameter(0, entityReferenceName);
            singleByteWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            dtdParams.resetEntityReferenceName();
            return false;
        }
    }
}
