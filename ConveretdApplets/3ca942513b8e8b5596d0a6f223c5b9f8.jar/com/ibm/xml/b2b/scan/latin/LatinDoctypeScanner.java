// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;

public final class LatinDoctypeScanner
{
    public static boolean scanDoctypeDecl(final DoctypeEventHandler doctypeEventHandler, final DoctypeImplementationHandler doctypeImplementationHandler, final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        final QName rootElementType = dtdParams.getRootElementType();
        XMLString publicID = null;
        XMLString systemID = null;
        for (byte b = bytes[n]; b == 32 || b == 10 || b == 9; b = bytes[++n]) {}
        rootElementType.offset = n;
        byte b2;
        do {
            b2 = bytes[++n];
            if (b2 == 58) {
                rootElementType.sepOffset = n;
                b2 = bytes[++n];
            }
        } while (b2 != 32 && b2 != 62 && b2 != 91 && b2 != 10 && b2 != 9);
        rootElementType.endOffset = n;
        if (b2 == 32 || b2 == 10 || b2 == 9) {
            do {
                b2 = bytes[++n];
            } while (b2 == 32 || b2 == 10 || b2 == 9);
            if (b2 != 91 && b2 != 62) {
                if (b2 == 80) {
                    byte b3;
                    for (n += 6, b3 = bytes[n]; b3 != 34 && b3 != 39; b3 = bytes[++n]) {}
                    final byte b4 = b3;
                    byte b5 = bytes[++n];
                    publicID = dtdParams.getPublicID();
                    publicID.offset = n;
                    while (b5 != b4) {
                        b5 = bytes[++n];
                    }
                    publicID.endOffset = n;
                    ++n;
                }
                else {
                    n += 6;
                }
                byte b6;
                for (b6 = bytes[n]; b6 != 34 && b6 != 39; b6 = bytes[++n]) {}
                final byte b7 = b6;
                byte b8 = bytes[++n];
                systemID = dtdParams.getSystemID();
                systemID.offset = n;
                while (b8 != b7) {
                    b8 = bytes[++n];
                }
                systemID.endOffset = n;
                do {
                    b2 = bytes[++n];
                } while (b2 == 32 || b2 == 10 || b2 == 9);
            }
        }
        final boolean b9 = b2 == 91;
        doctypeEventHandler.doctype(rootElementType, publicID, systemID, b9);
        dtdParams.resetRootElementType();
        if (publicID != null) {
            dtdParams.resetPublicID();
        }
        if (systemID != null) {
            dtdParams.resetSystemID();
        }
        if (b9) {
            ++n;
            if (doctypeImplementationHandler != null) {
                parsedEntity.offset = n;
                if (!doctypeImplementationHandler.scanInternalSubset(parsedEntity)) {
                    return false;
                }
                n = parsedEntity.offset;
            }
            else {
                n = skipInternalSubset(doctypeEventHandler, bytes, n);
            }
            for (byte b10 = bytes[++n]; b10 != 62; b10 = bytes[++n]) {}
        }
        parsedEntity.offset = ++n;
        return true;
    }
    
    private static int skipInternalSubset(final DoctypeEventHandler doctypeEventHandler, final byte[] array, int n) {
        while (true) {
            byte b = array[n];
            if (b == 60) {
                if (array[++n] == 33) {
                    if (array[++n] == 45) {
                        for (n += 2; array[n] != 45 || array[n + 1] != 45; ++n) {}
                        n += 3;
                    }
                    else {
                        final byte b2 = array[n + 1];
                        if (b2 == 76) {
                            n = skipMarkupDecl(array, n + 7);
                        }
                        else if (b2 == 84) {
                            n = skipMarkupDecl(array, n + 7);
                        }
                        else if (b2 == 78) {
                            n = skipMarkupDecl(array, n + 6);
                        }
                        else {
                            n = skipMarkupDecl(array, n + 8);
                        }
                    }
                }
                else {
                    ++n;
                    while (array[n] != 63 || array[n + 1] != 62) {
                        ++n;
                    }
                    n += 2;
                }
            }
            else if (b == 32 || b == 10 || b == 9) {
                byte b3;
                do {
                    b3 = array[++n];
                } while (b3 == 32 || b3 == 10 || b3 == 9);
            }
            else {
                if (b != 37) {
                    break;
                }
                while (b != 59) {
                    b = array[++n];
                }
                ++n;
            }
        }
        return n;
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
}
