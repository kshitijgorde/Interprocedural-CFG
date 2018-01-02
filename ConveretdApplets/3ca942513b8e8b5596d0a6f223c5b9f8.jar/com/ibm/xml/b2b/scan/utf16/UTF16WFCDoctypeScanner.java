// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class UTF16WFCDoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        final int offset = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        if (!scanRequiredWhitespace(parsedEntity)) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 14);
            return false;
        }
        final int scanQName = utf16WFCScannerSupport.scanQName(parsedEntity, rootElementType);
        if (scanQName == 0) {
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 15);
            return false;
        }
        int n;
        char c;
        if (scanQName == 5) {
            n = parsedEntity.offset;
            do {
                c = chars[++n];
            } while (c == ' ' || c == '\n' || c == '\t');
            if (c != '[' && c != '>') {
                if (c == 'P' && chars[n + 1] == 'U' && chars[n + 2] == 'B' && chars[n + 3] == 'L' && chars[n + 4] == 'I' && chars[n + 5] == 'C') {
                    parsedEntity.offset = n + 6;
                    if (!scanRequiredWhitespace(parsedEntity)) {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                        return false;
                    }
                    final int offset2 = parsedEntity.offset;
                    final char c2 = chars[offset2];
                    if (c2 != '\"' && c2 != '\'') {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                        return false;
                    }
                    parsedEntity.offset = offset2 + 1;
                    publicID = dtdParams.getPublicID();
                    if (!utf16WFCScannerSupport.scanPublicID(parsedEntity, c2, publicID)) {
                        return false;
                    }
                }
                else {
                    if (c != 'S' || chars[n + 1] != 'Y' || chars[n + 2] != 'S' || chars[n + 3] != 'T' || chars[n + 4] != 'E' || chars[n + 5] != 'M') {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                        return false;
                    }
                    parsedEntity.offset = n + 6;
                }
                if (!scanRequiredWhitespace(parsedEntity)) {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                    return false;
                }
                final int offset3 = parsedEntity.offset;
                final char c3 = chars[offset3];
                if (c3 != '\"' && c3 != '\'') {
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                    return false;
                }
                parsedEntity.offset = offset3 + 1;
                systemID = dtdParams.getSystemID();
                if (!utf16WFCScannerSupport.scanSystemID(parsedEntity, c3, systemID)) {
                    return false;
                }
                for (n = parsedEntity.offset, c = chars[n]; c == ' ' || c == '\n' || c == '\t'; c = chars[++n]) {}
            }
        }
        else {
            n = parsedEntity.offset;
            c = chars[n];
        }
        final boolean b = c == '[';
        doctypeEventHandler.doctype(rootElementType, publicID, systemID, b);
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        if (b) {
            parsedEntity.offset = n + 1;
            if (doctypeImplementationHandler != null) {
                if (!doctypeImplementationHandler.scanInternalSubset(parsedEntity)) {
                    return false;
                }
            }
            else if (!skipInternalSubset(doctypeEventHandler, utf16WFCScannerSupport, dtdParams, parsedEntity)) {
                return false;
            }
            for (n = parsedEntity.offset, c = chars[++n]; c == ' ' || c == '\n' || c == '\t'; c = chars[++n]) {}
        }
        if (c != '>') {
            utf16WFCScannerSupport.setParameter(0, rootElementType);
            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
            return false;
        }
        dtdParams.resetRootElementType();
        parsedEntity.offset = n + 1;
        return true;
    }
    
    private static boolean skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final UTF16WFCScannerSupport utf16WFCScannerSupport, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int n = parsedEntity.offset;
        char c = chars[n];
        while (true) {
            if (c == ' ' || c == '\n' || c == '\t') {
                do {
                    c = chars[++n];
                } while (c == ' ' || c == '\n' || c == '\t');
            }
            else if (c == '<') {
                final char c2 = chars[++n];
                if (c2 == '!') {
                    if (chars[++n] == '-') {
                        for (n += 2; chars[n] != '-' || chars[n + 1] != '-'; ++n) {}
                        n += 3;
                    }
                    else {
                        final char c3 = chars[n + 1];
                        if (c3 == 'L') {
                            n = skipMarkupDecl(chars, n + 7);
                        }
                        else if (c3 == 'T') {
                            n = skipMarkupDecl(chars, n + 7);
                        }
                        else if (c3 == 'N') {
                            n = skipMarkupDecl(chars, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(chars, n + 8);
                        }
                    }
                }
                else {
                    if (c2 != '?') {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    ++n;
                    while (chars[n] != '?' || chars[n + 1] != '>') {
                        ++n;
                    }
                    n += 2;
                }
                c = chars[n];
            }
            else {
                if (c == ']') {
                    parsedEntity.offset = n;
                    return true;
                }
                if (c != '%') {
                    parsedEntity.offset = n;
                    if (c == '\0') {
                        if (n == parsedEntity.endOffset) {
                            utf16WFCScannerSupport.setParameter(0, dtdParams.rootElementType());
                            utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 16);
                            return false;
                        }
                    }
                    else if (parsedEntity.skipValidCharacter()) {
                        utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    utf16WFCScannerSupport.setInvalidCharParameter(0, parsedEntity);
                    utf16WFCScannerSupport.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 5);
                    return false;
                }
                final XMLName peName = dtdParams.getPEName();
                parsedEntity.offset = n + 1;
                if (!utf16WFCScannerSupport.scanPEReference(parsedEntity, peName)) {
                    dtdParams.resetPEName();
                    return false;
                }
                n = parsedEntity.offset;
                dtdParams.resetPEName();
                c = chars[n];
            }
        }
    }
    
    private static int skipMarkupDecl(final char[] array, int n) {
        while (true) {
            final char c = array[++n];
            if (c == '\"') {
                while (array[++n] != '\"') {}
            }
            else if (c == '\'') {
                while (array[++n] != '\'') {}
            }
            else {
                if (c == '>') {
                    break;
                }
                continue;
            }
        }
        return n + 1;
    }
    
    private static boolean scanRequiredWhitespace(final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int offset = parsedEntity.offset;
        final char c = chars[offset];
        if (c == ' ' || c == '\n' || c == '\t') {
            char c2;
            do {
                c2 = chars[++offset];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            parsedEntity.offset = offset;
            return true;
        }
        return false;
    }
}
