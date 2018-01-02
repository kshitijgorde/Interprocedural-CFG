// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.InputStream;
import org.apache.xerces.utils.StringPool;
import java.io.Reader;
import org.apache.xerces.framework.XMLErrorReporter;

public class XMLEntityReaderFactory
{
    private static final boolean USE_CHAR_READER_FOR_UTF8 = false;
    private static final boolean USE_BYTE_READER_FOR_UTF8 = true;
    
    public XMLEntityHandler.EntityReader createCharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final Reader reader, final StringPool stringPool) throws Exception {
        return new CharReader(xmlEntityHandler, xmlErrorReporter, b, reader, stringPool);
    }
    
    public XMLEntityHandler.EntityReader createUTF8Reader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final InputStream inputStream, final StringPool stringPool) throws Exception {
        return new UTF8Reader(xmlEntityHandler, xmlErrorReporter, b, inputStream, stringPool);
    }
    
    public XMLEntityHandler.EntityReader createStringReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final int n, final int n2, final int n3, final StringPool stringPool, final boolean b2) throws Exception {
        return StringReader.createStringReader(xmlEntityHandler, xmlErrorReporter, b, n, n2, n3, stringPool, b2);
    }
}
