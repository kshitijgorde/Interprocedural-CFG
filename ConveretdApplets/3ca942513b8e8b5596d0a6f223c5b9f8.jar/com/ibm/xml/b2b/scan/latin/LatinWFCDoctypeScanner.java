// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class LatinWFCDoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        final int offset = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        if (!scanRequiredWhitespace(parsedEntity)) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 14);
            return false;
        }
        final int scanQName = latinWFCScannerSupport.scanQName(parsedEntity, rootElementType);
        if (scanQName == 0) {
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 15);
            return false;
        }
        int n;
        byte b;
        if (scanQName == 5) {
            n = parsedEntity.offset;
            do {
                b = bytes[++n];
            } while (b == 32 || b == 10 || b == 9);
            if (b != 91 && b != 62) {
                if (b == 80 && bytes[n + 1] == 85 && bytes[n + 2] == 66 && bytes[n + 3] == 76 && bytes[n + 4] == 73 && bytes[n + 5] == 67) {
                    parsedEntity.offset = n + 6;
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
                }
                else {
                    if (b != 83 || bytes[n + 1] != 89 || bytes[n + 2] != 83 || bytes[n + 3] != 84 || bytes[n + 4] != 69 || bytes[n + 5] != 77) {
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                        return false;
                    }
                    parsedEntity.offset = n + 6;
                }
                if (!scanRequiredWhitespace(parsedEntity)) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                    return false;
                }
                final int offset3 = parsedEntity.offset;
                final byte b3 = bytes[offset3];
                if (b3 != 34 && b3 != 39) {
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                    return false;
                }
                parsedEntity.offset = offset3 + 1;
                systemID = dtdParams.getSystemID();
                if (!latinWFCScannerSupport.scanSystemID(parsedEntity, b3, systemID)) {
                    return false;
                }
                for (n = parsedEntity.offset, b = bytes[n]; b == 32 || b == 10 || b == 9; b = bytes[++n]) {}
            }
        }
        else {
            n = parsedEntity.offset;
            b = bytes[n];
        }
        final boolean b4 = b == 91;
        doctypeEventHandler.doctype(rootElementType, publicID, systemID, b4);
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        if (b4) {
            parsedEntity.offset = n + 1;
            if (doctypeImplementationHandler != null) {
                if (!doctypeImplementationHandler.scanInternalSubset(parsedEntity)) {
                    return false;
                }
            }
            else if (!skipInternalSubset(doctypeEventHandler, latinWFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
            for (n = parsedEntity.offset, b = bytes[++n]; b == 32 || b == 10 || b == 9; b = bytes[++n]) {}
        }
        if (b != 62) {
            latinWFCScannerSupport.setParameter(0, rootElementType);
            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
            return false;
        }
        dtdParams.resetRootElementType();
        parsedEntity.offset = n + 1;
        return true;
    }
    
    private static boolean skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final LatinWFCScannerSupport latinWFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        byte b = bytes[n];
        while (true) {
            if (b == 32 || b == 10 || b == 9) {
                do {
                    b = bytes[++n];
                } while (b == 32 || b == 10 || b == 9);
            }
            else if (b == 60) {
                final byte b2 = bytes[++n];
                if (b2 == 33) {
                    if (bytes[++n] == 45) {
                        for (n += 2; bytes[n] != 45 || bytes[n + 1] != 45; ++n) {}
                        n += 3;
                    }
                    else {
                        final byte b3 = bytes[n + 1];
                        if (b3 == 76) {
                            n = skipMarkupDecl(bytes, n + 7);
                        }
                        else if (b3 == 84) {
                            n = skipMarkupDecl(bytes, n + 7);
                        }
                        else if (b3 == 78) {
                            n = skipMarkupDecl(bytes, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(bytes, n + 8);
                        }
                    }
                }
                else {
                    if (b2 != 63) {
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    ++n;
                    while (bytes[n] != 63 || bytes[n + 1] != 62) {
                        ++n;
                    }
                    n += 2;
                }
                b = bytes[n];
            }
            else {
                if (b == 93) {
                    parsedEntity.offset = n;
                    return true;
                }
                if (b != 37) {
                    parsedEntity.offset = n;
                    if (b == 0) {
                        if (n == parsedEntity.endOffset) {
                            latinWFCScannerSupport.setParameter(0, dtdParams.rootElementType());
                            latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                            return false;
                        }
                    }
                    else if (parsedEntity.skipValidCharacter()) {
                        latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    latinWFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    latinWFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
                    return false;
                }
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = n + 1;
                if (!latinWFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                    dtdParams.resetPEName();
                    return false;
                }
                n = parsedEntity.offset;
                dtdParams.resetPEName();
                b = bytes[n];
            }
        }
    }
    
    private static int skipMarkupDecl(final byte[] array, int n) {
        while (true) {
            final byte b = array[++n];
            if (b == 34) {
                while (array[++n] != 34) {}
            }
            else if (b == 39) {
                while (array[++n] != 39) {}
            }
            else {
                if (b == 62) {
                    break;
                }
                continue;
            }
        }
        return n + 1;
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
