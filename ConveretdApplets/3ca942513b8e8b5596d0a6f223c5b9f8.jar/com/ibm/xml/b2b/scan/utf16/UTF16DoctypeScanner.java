// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class UTF16DoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final char[] chars = parsedEntity.chars;
        int n = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        for (char c = chars[n]; c == ' ' || c == '\n' || c == '\t'; c = chars[++n]) {}
        rootElementType.offset = n;
        char c2;
        do {
            c2 = chars[++n];
            if (c2 == ':') {
                rootElementType.sepOffset = n;
                c2 = chars[++n];
            }
        } while (c2 != ' ' && c2 != '>' && c2 != '[' && c2 != '\n' && c2 != '\t');
        rootElementType.endOffset = n;
        if (c2 == ' ' || c2 == '\n' || c2 == '\t') {
            do {
                c2 = chars[++n];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t');
            if (c2 != '[' && c2 != '>') {
                if (c2 == 'P') {
                    char c3;
                    for (n += 6, c3 = chars[n]; c3 != '\"' && c3 != '\''; c3 = chars[++n]) {}
                    final char c4 = c3;
                    char c5 = chars[++n];
                    publicID = dtdParams.getPublicID();
                    publicID.offset = n;
                    while (c5 != c4) {
                        c5 = chars[++n];
                    }
                    publicID.endOffset = n;
                    ++n;
                }
                else {
                    n += 6;
                }
                char c6;
                for (c6 = chars[n]; c6 != '\"' && c6 != '\''; c6 = chars[++n]) {
                    if (c6 == '\0') {
                        return false;
                    }
                }
                final char c7 = c6;
                char c8 = chars[++n];
                systemID = dtdParams.getSystemID();
                systemID.offset = n;
                while (c8 != c7) {
                    c8 = chars[++n];
                }
                systemID.endOffset = n;
                do {
                    c2 = chars[++n];
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
                n = skipInternalSubset(doctypeEventHandler, chars, n);
            }
            for (char c9 = chars[++n]; c9 != '>'; c9 = chars[++n]) {}
        }
        parsedEntity.offset = ++n;
        return true;
    }
    
    private static int skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final char[] array, int n) {
        while (true) {
            char c = array[n];
            if (c == '<') {
                if (array[++n] == '!') {
                    if (array[++n] == '-') {
                        for (n += 2; array[n] != '-' || array[n + 1] != '-'; ++n) {}
                        n += 3;
                    }
                    else {
                        final char c2 = array[n + 1];
                        if (c2 == 'L') {
                            n = skipMarkupDecl(array, n + 7);
                        }
                        else if (c2 == 'T') {
                            n = skipMarkupDecl(array, n + 7);
                        }
                        else if (c2 == 'N') {
                            n = skipMarkupDecl(array, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(array, n + 8);
                        }
                    }
                }
                else {
                    ++n;
                    while (array[n] != '?' || array[n + 1] != '>') {
                        ++n;
                    }
                    n += 2;
                }
            }
            else if (c == ' ' || c == '\n' || c == '\t') {
                char c3;
                do {
                    c3 = array[++n];
                } while (c3 == ' ' || c3 == '\n' || c3 == '\t');
            }
            else {
                if (c != '%') {
                    break;
                }
                while (c != ';') {
                    c = array[++n];
                }
                ++n;
            }
        }
        return n;
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
}
