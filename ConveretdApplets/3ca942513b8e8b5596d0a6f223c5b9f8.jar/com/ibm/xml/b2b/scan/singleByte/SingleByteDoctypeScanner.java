// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.singleByte;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class SingleByteDoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] byteToCharMap = ((SingleByteEncodingSupport)parsedEntity.encoding).byteToCharMap;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        for (char c = byteToCharMap[bytes[n] & 0xFF]; c == ' ' || c == '\n' || c == '\t'; c = byteToCharMap[bytes[++n] & 0xFF]) {}
        rootElementType.offset = n;
        char c2;
        do {
            c2 = byteToCharMap[bytes[++n] & 0xFF];
            if (c2 == ':') {
                rootElementType.sepOffset = n;
                c2 = byteToCharMap[bytes[++n] & 0xFF];
            }
        } while (c2 != ' ' && c2 != '>' && c2 != '[' && c2 != '\n' && c2 != '\t');
        rootElementType.endOffset = n;
        if (c2 == ' ' || c2 == '\n' || c2 == '\t') {
            do {
                c2 = byteToCharMap[bytes[++n] & 0xFF];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            if (c2 != '[' && c2 != '>') {
                if (c2 == 'P') {
                    char c3;
                    for (n += 6, c3 = byteToCharMap[bytes[n] & 0xFF]; c3 != '\"' && c3 != '\''; c3 = byteToCharMap[bytes[++n] & 0xFF]) {}
                    final char c4 = c3;
                    char c5 = byteToCharMap[bytes[++n] & 0xFF];
                    publicID = dtdParams.getPublicID();
                    publicID.offset = n;
                    while (c5 != c4) {
                        c5 = byteToCharMap[bytes[++n] & 0xFF];
                    }
                    publicID.endOffset = n;
                    ++n;
                }
                else {
                    n += 6;
                }
                char c6;
                for (c6 = byteToCharMap[bytes[n] & 0xFF]; c6 != '\"' && c6 != '\''; c6 = byteToCharMap[bytes[++n] & 0xFF]) {}
                final char c7 = c6;
                char c8 = byteToCharMap[bytes[++n] & 0xFF];
                systemID = dtdParams.getSystemID();
                systemID.offset = n;
                while (c8 != c7) {
                    c8 = byteToCharMap[bytes[++n] & 0xFF];
                }
                systemID.endOffset = n;
                do {
                    c2 = byteToCharMap[bytes[++n] & 0xFF];
                } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            }
        }
        final boolean b = c2 == '[';
        doctypeEventHandler.doctype(rootElementType, publicID, systemID, b);
        dtdParams.resetRootElementType();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        if (b) {
            ++n;
            if (doctypeImplementationHandler != null) {
                parsedEntity.offset = n;
                if (!doctypeImplementationHandler.scanInternalSubset(parsedEntity)) {
                    return false;
                }
                n = parsedEntity.offset;
            }
            else {
                n = skipInternalSubset(doctypeEventHandler, byteToCharMap, bytes, n);
            }
            for (char c9 = byteToCharMap[bytes[++n] & 0xFF]; c9 != '>'; c9 = byteToCharMap[bytes[++n] & 0xFF]) {}
        }
        parsedEntity.offset = ++n;
        return true;
    }
    
    private static int skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final char[] array, final byte[] array2, int n) {
        while (true) {
            char c = array[array2[n] & 0xFF];
            if (c == '<') {
                if (array[array2[++n] & 0xFF] == '!') {
                    if (array[array2[++n] & 0xFF] == '-') {
                        for (n += 2; array[array2[n] & 0xFF] != '-' || array[array2[n + 1] & 0xFF] != '-'; ++n) {}
                        n += 3;
                    }
                    else {
                        final char c2 = array[array2[n + 1] & 0xFF];
                        if (c2 == 'L') {
                            n = skipMarkupDecl(array, array2, n + 7);
                        }
                        else if (c2 == 'T') {
                            n = skipMarkupDecl(array, array2, n + 7);
                        }
                        else if (c2 == 'N') {
                            n = skipMarkupDecl(array, array2, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(array, array2, n + 8);
                        }
                    }
                }
                else {
                    ++n;
                    while (array[array2[n] & 0xFF] != '?' || array[array2[n + 1] & 0xFF] != '>') {
                        ++n;
                    }
                    n += 2;
                }
            }
            else if (c == ' ' || c == '\n' || c == '\t') {
                char c3;
                do {
                    c3 = array[array2[++n] & 0xFF];
                } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
            }
            else {
                if (c != '%') {
                    break;
                }
                while (c != ';') {
                    c = array[array2[++n] & 0xFF];
                }
                ++n;
            }
        }
        return n;
    }
    
    private static int skipMarkupDecl(final char[] array, final byte[] array2, int n) {
        while (true) {
            final char c = array[array2[++n] & 0xFF];
            if (c == '\"') {
                while (array[array2[++n] & 0xFF] != '\"') {}
            }
            else if (c == '\'') {
                while (array[array2[++n] & 0xFF] != '\'') {}
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
}
