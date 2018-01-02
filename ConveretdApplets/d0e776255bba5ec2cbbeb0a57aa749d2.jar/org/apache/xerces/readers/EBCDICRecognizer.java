// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.xerces.utils.ChunkyByteArray;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;

final class EBCDICRecognizer extends XMLDeclRecognizer
{
    public XMLEntityHandler.EntityReader recognize(final XMLEntityReaderFactory xmlEntityReaderFactory, final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final StringPool stringPool, final ChunkyByteArray chunkyByteArray, final boolean b2, final boolean b3) throws Exception {
        XMLEntityHandler.EntityReader charReader = null;
        final byte byte1 = chunkyByteArray.byteAt(0);
        final byte byte2 = chunkyByteArray.byteAt(1);
        final byte byte3 = chunkyByteArray.byteAt(2);
        final byte byte4 = chunkyByteArray.byteAt(3);
        final boolean b4 = false;
        if (byte1 != 76 || byte2 != 111 || byte3 != -89 || byte4 != -108) {
            return charReader;
        }
        final int prescanXMLDeclOrTextDecl = this.prescanXMLDeclOrTextDecl(xmlEntityReaderFactory.createCharReader(xmlEntityHandler, xmlErrorReporter, b, new InputStreamReader(chunkyByteArray, "CP037"), stringPool), b2);
        if (prescanXMLDeclOrTextDecl == -1) {
            chunkyByteArray.rewind();
            throw new UnsupportedEncodingException((String)null);
        }
        final String upperCase = stringPool.orphanString(prescanXMLDeclOrTextDecl).toUpperCase();
        if ("ISO-10646-UCS-2".equals(upperCase)) {
            throw new UnsupportedEncodingException(upperCase);
        }
        if ("ISO-10646-UCS-4".equals(upperCase)) {
            throw new UnsupportedEncodingException(upperCase);
        }
        if ("UTF-16".equals(upperCase)) {
            throw new UnsupportedEncodingException(upperCase);
        }
        String convert = MIME2Java.convert(upperCase);
        if (null == convert) {
            if (!b3) {
                throw new UnsupportedEncodingException(upperCase);
            }
            convert = upperCase;
        }
        try {
            chunkyByteArray.rewind();
            charReader = xmlEntityReaderFactory.createCharReader(xmlEntityHandler, xmlErrorReporter, b, new InputStreamReader(chunkyByteArray, convert), stringPool);
        }
        catch (UnsupportedEncodingException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            if (b4) {
                ex2.printStackTrace();
            }
        }
        return charReader;
    }
}
