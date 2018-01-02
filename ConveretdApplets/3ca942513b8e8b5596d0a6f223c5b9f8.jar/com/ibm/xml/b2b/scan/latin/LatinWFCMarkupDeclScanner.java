// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;

public class LatinWFCMarkupDeclScanner
{
    public static boolean scanDefaultAttValue(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final byte[] bytes = parsedEntity.bytes;
        int n2 = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        int offset = n2;
    Label_0030:
        while (true) {
            while (true) {
                byte b = bytes[n2];
                while (true) {
                    if (b >= 0) {
                        final byte b2 = attValueMap[b];
                        if (b2 == 1 || b2 == 6) {
                            b = bytes[++n2];
                        }
                        else {
                            if (n2 > offset) {
                                final XMLString attValueCharacters = dtdParams.getAttValueCharacters();
                                attValueCharacters.offset = offset;
                                attValueCharacters.endOffset = n2;
                                markupDeclHandler.defaultAttValueCharacters(attValueCharacters);
                                dtdParams.resetAttValueCharacters();
                            }
                            switch (b2) {
                                case 4: {
                                    if (b == n) {
                                        parsedEntity.offset = n2 + 1;
                                        return true;
                                    }
                                    offset = n2++;
                                    continue Label_0030;
                                }
                                case 3: {
                                    parsedEntity.offset = n2 + 1;
                                    if (!checkReferenceInAttValue(markupDeclHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                                        return false;
                                    }
                                    n2 = (offset = parsedEntity.offset);
                                    continue Label_0030;
                                }
                                case 2: {
                                    latinWFCScannerSupport.setAttValueErrorParameters();
                                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 25);
                                    return false;
                                }
                                case 5: {
                                    if (n2 != parsedEntity.endOffset) {
                                        break Label_0030;
                                    }
                                    if (n == 0) {
                                        parsedEntity.offset = n2;
                                        return true;
                                    }
                                    latinWFCScannerSupport.setAttValueErrorParameters();
                                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 26);
                                    return false;
                                }
                                default: {
                                    break Label_0030;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = n2;
                        if (!parsedEntity.skipValidCharacter()) {
                            latinWFCScannerSupport.setAttValueErrorParameters();
                            latinWFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
                            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
                            return false;
                        }
                        n2 = parsedEntity.offset;
                        b = bytes[n2];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = n2;
        latinWFCScannerSupport.setAttValueErrorParameters();
        latinWFCScannerSupport.setInvalidCharParameter(2, parsedEntity);
        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
        return false;
    }
    
    public static boolean scanEntityValue(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity, final int n, final boolean b) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset;
        int n2 = offset = parsedEntity.offset;
        byte b2 = bytes[n2];
        while (true) {
            if (b2 >= 0) {
                Label_0215: {
                    switch (attValueMap[b2]) {
                        case 1: {
                            if (b2 != 37) {
                                break Label_0215;
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
                            if (!latinWFCScannerSupport.scanPEReference(parsedEntity, peReferenceName)) {
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            if (b) {
                                latinWFCScannerSupport.setParameter(0, peReferenceName);
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 17);
                                dtdParams.resetPEReferenceName();
                                return false;
                            }
                            n2 = parsedEntity.offset;
                            markupDeclHandler.peReferenceInEntityValue(peReferenceName);
                            dtdParams.resetPEReferenceName();
                            offset = n2;
                            b2 = bytes[n2];
                            continue;
                        }
                        case 2:
                        case 6: {
                            b2 = bytes[++n2];
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
                            if (!checkReferenceInEntityValue(markupDeclHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                                return false;
                            }
                            n2 = (offset = parsedEntity.offset);
                            b2 = bytes[n2];
                            continue;
                        }
                        case 4: {
                            if (b2 == n) {
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
                            b2 = bytes[++n2];
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
                                latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                                return false;
                            }
                            if (n == 0) {
                                return true;
                            }
                            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 13);
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
                            latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                            return false;
                        }
                    }
                }
            }
            else {
                parsedEntity.offset = n2;
                if (!parsedEntity.skipValidCharacter()) {
                    latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 1);
                    return false;
                }
                n2 = parsedEntity.offset;
                b2 = bytes[n2];
            }
        }
    }
    
    protected static boolean scanComment(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLString content = dtdParams.getContent();
        if (latinWFCScannerSupport.scanComment(parsedEntity, content)) {
            markupDeclHandler.comment(content);
            dtdParams.resetContent();
            return true;
        }
        dtdParams.resetContent();
        return false;
    }
    
    protected static boolean scanPI(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final XMLName piTarget = dtdParams.getPITarget();
        boolean b;
        if (latinWFCScannerSupport.scanPITarget(parsedEntity, piTarget)) {
            final XMLString content = dtdParams.getContent();
            if (latinWFCScannerSupport.scanPIData(parsedEntity, content)) {
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
    
    private static boolean checkReferenceInAttValue(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (parsedEntity.bytes[parsedEntity.offset] == 35) {
            ++parsedEntity.offset;
            final int scanCharacterReference = latinWFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.defaultAttValueCharacter(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = latinWFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                if (latinWFCScannerSupport.entityReferenceInDefaultAttValue(markupDeclHandler, entityReferenceName)) {
                    dtdParams.resetEntityReferenceName();
                    return true;
                }
                dtdParams.resetEntityReferenceName();
                return false;
            }
            else {
                if (scanName == 0) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                    dtdParams.resetEntityReferenceName();
                    return false;
                }
                latinWFCScannerSupport.setParameter(0, entityReferenceName);
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
                dtdParams.resetEntityReferenceName();
                return false;
            }
        }
    }
    
    private static boolean checkReferenceInEntityValue(final MarkupDeclHandler markupDeclHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        if (parsedEntity.bytes[parsedEntity.offset] == 35) {
            ++parsedEntity.offset;
            final int scanCharacterReference = latinWFCScannerSupport.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                markupDeclHandler.entityValueCharacter(scanCharacterReference);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityReferenceName = dtdParams.getEntityReferenceName();
            final int scanName = latinWFCScannerSupport.scanName(parsedEntity, entityReferenceName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                markupDeclHandler.entityReferenceInEntityValue(entityReferenceName);
                dtdParams.resetEntityReferenceName();
                return true;
            }
            if (scanName == 0) {
                latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                dtdParams.resetEntityReferenceName();
                return false;
            }
            latinWFCScannerSupport.setParameter(0, entityReferenceName);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            dtdParams.resetEntityReferenceName();
            return false;
        }
    }
}
