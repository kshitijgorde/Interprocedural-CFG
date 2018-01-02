// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.ChunkyByteArray;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;

final class UCSRecognizer extends XMLDeclRecognizer
{
    public XMLEntityHandler.EntityReader recognize(final XMLEntityReaderFactory xmlEntityReaderFactory, final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final StringPool stringPool, final ChunkyByteArray chunkyByteArray, final boolean b2, final boolean b3) throws Exception {
        XMLEntityHandler.EntityReader entityReader = null;
        final byte byte1 = chunkyByteArray.byteAt(0);
        if (byte1 == 0) {
            final byte byte2 = chunkyByteArray.byteAt(1);
            if (byte2 == 0) {
                if (chunkyByteArray.byteAt(2) == 0 && chunkyByteArray.byteAt(3) == 60) {
                    entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 0, stringPool);
                }
            }
            else if (byte2 == 60 && chunkyByteArray.byteAt(2) == 0 && chunkyByteArray.byteAt(3) == 63) {
                entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 4, stringPool);
            }
        }
        else if (byte1 == 60) {
            if (chunkyByteArray.byteAt(1) == 0) {
                final byte byte3 = chunkyByteArray.byteAt(2);
                if (chunkyByteArray.byteAt(3) == 0) {
                    if (byte3 == 0) {
                        entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 1, stringPool);
                    }
                    else if (byte3 == 63) {
                        entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 5, stringPool);
                    }
                }
            }
        }
        else if (byte1 == -2) {
            if (chunkyByteArray.byteAt(1) == -1) {
                entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 2, stringPool);
            }
        }
        else if (byte1 == -1 && chunkyByteArray.byteAt(1) == -2) {
            entityReader = new UCSReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, 3, stringPool);
        }
        return entityReader;
    }
}
