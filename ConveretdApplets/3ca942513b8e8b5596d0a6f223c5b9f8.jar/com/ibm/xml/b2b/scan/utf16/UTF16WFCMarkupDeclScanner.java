// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;

public class UTF16WFCMarkupDeclScanner
{
    public static boolean scanDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final char[] chars = parsedEntity.chars;
        int offset2;
        int offset = offset2 = parsedEntity.offset;
    Label_0024:
        while (true) {
            while (true) {
                char c = chars[offset];
                while (true) {
                    if (c < '\u0080') {
                        final byte b = attValueMap[c];
                        if (b == 1 || b == 6) {
                            c = chars[++offset];
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
                                    continue Label_0024;
                                }
                                case 3: {
                                    parsedEntity.offset = offset + 1;
                                    if (!checkReferenceInAttValue(markupDeclHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                                        return false;
                                    }
                                    offset = (offset2 = parsedEntity.offset);
                                    continue Label_0024;
                                }
                                case 2: {
                                    utf16WFCScannerSupport.setAttValueErrorParameters();
                                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 25);
                                    return false;
                                }
                                case 5: {
                                    if (offset != parsedEntity.endOffset) {
                                        break Label_0024;
                                    }
                                    if (n == 0) {
                                        parsedEntity.offset = offset;
                                        return true;
                                    }
                                    utf16WFCScannerSupport.setAttValueErrorParameters();
                                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 26);
                                    return false;
                                }
                                default: {
                                    break Label_0024;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = offset;
                        if (!parsedEntity.skipValidCharacter()) {
                            utf16WFCScannerSupport.setAttValueErrorParameters();
                            utf16WFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
                            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
                            return false;
                        }
                        offset = parsedEntity.offset;
                        c = chars[offset];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = offset;
        utf16WFCScannerSupport.setAttValueErrorParameters();
        utf16WFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
        return false;
    }
    
    public static boolean scanEntityValue(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n, final boolean b) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final char[] chars = parsedEntity.chars;
        int offset;
        int n2 = offset = parsedEntity.offset;
        char c = chars[n2];
        while (true) {
            if (c < '\u0080') {
                Label_0219: {
                    switch (attValueMap[c]) {
                        case 1: {
                            if (c != '%') {
                                break Label_0219;
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
                            if (!utf16WFCScannerSupport.scanPEReference(parsedEntity, peReferenceName)) {
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            if (b) {
                                utf16WFCScannerSupport.setParameter(0, peReferenceName);
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 17);
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            n2 = parsedEntity.offset;
                            markupDeclHandler.peReferenceInEntityValue(peReferenceName);
                            dtdParams.resetPEReferenceName();
                            offset = n2;
                            c = chars[n2];
                            continue;
                        }
                        case 2:
                        case 6: {
                            c = chars[++n2];
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
                            if (!checkReferenceInEntityValue(markupDeclHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                                return false;
                            }
                            n2 = (offset = parsedEntity.offset);
                            c = chars[n2];
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
                            c = chars[++n2];
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
                                utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                                return false;
                            }
                            if (n == 0) {
                                return true;
                            }
                            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 13);
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
                            utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                            return false;
                        }
                    }
                }
            }
            else {
                parsedEntity.offset = n2;
                if (!parsedEntity.skipValidCharacter()) {
                    utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                    return false;
                }
                n2 = parsedEntity.offset;
                c = chars[n2];
            }
        }
    }
    
    protected static boolean scanComment(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLString content = dtdParams.getContent();
        if (utf16WFCScannerSupport.scanComment(parsedEntity, content)) {
            markupDeclHandler.comment(content);
            dtdParams.resetContent();
            return true;
        }
        dtdParams.resetContent();
        return false;
    }
    
    protected static boolean scanPI(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLName piTarget = dtdParams.getPITarget();
        boolean b;
        if (utf16WFCScannerSupport.scanPITarget(parsedEntity, piTarget)) {
            final XMLString content = dtdParams.getContent();
            if (utf16WFCScannerSupport.scanPIData(parsedEntity, content)) {
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
    
    private static boolean checkReferenceInAttValue(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (parsedEntity.chars[parsedEntity.offset] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = utf16WFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.defaultAttValueCharacter(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = utf16WFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                if (utf16WFCScannerSupport.entityReferenceInDefaultAttValue(markupDeclHandler, entityReferenceName)) {
                    dtdParams.resetEntityReferenceName();
                    return true;
                }
                dtdParams.resetEntityReferenceName();
                return false;
            }
            else {
                if (scanName == 0) {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                    dtdParams.resetEntityReferenceName();
                    return false;
                }
                utf16WFCScannerSupport.setParameter(0, entityReferenceName);
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
                dtdParams.resetEntityReferenceName();
                return false;
            }
        }
    }
    
    private static boolean checkReferenceInEntityValue(final MarkupDeclHandler markupDeclHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (parsedEntity.chars[parsedEntity.offset] == '#') {
            ++parsedEntity.offset;
            final int scanCharacterReference = utf16WFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.entityValueCharacter(scanCharacterReference);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = utf16WFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                markupDeclHandler.entityReferenceInEntityValue(entityReferenceName);
                dtdParams.resetEntityReferenceName();
                return true;
            }
            if (scanName == 0) {
                utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                dtdParams.resetEntityReferenceName();
                return false;
            }
            utf16WFCScannerSupport.setParameter(0, entityReferenceName);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            dtdParams.resetEntityReferenceName();
            return false;
        }
    }
}
