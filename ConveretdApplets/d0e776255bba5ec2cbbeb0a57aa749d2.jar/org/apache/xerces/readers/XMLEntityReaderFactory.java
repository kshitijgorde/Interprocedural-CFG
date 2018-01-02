// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.InputStream;
import java.io.Reader;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.InputSource;
import org.apache.xerces.framework.XMLErrorReporter;

public interface XMLEntityReaderFactory
{
    void addRecognizer(final XMLDeclRecognizer p0);
    
    void setSendCharDataAsCharArray(final boolean p0);
    
    void setAllowJavaEncodingName(final boolean p0);
    
    boolean getAllowJavaEncodingName();
    
    XMLEntityHandler.EntityReader createReader(final XMLEntityHandler p0, final XMLErrorReporter p1, final InputSource p2, final String p3, final boolean p4, final StringPool p5) throws Exception;
    
    XMLEntityHandler.EntityReader createCharReader(final XMLEntityHandler p0, final XMLErrorReporter p1, final boolean p2, final Reader p3, final StringPool p4) throws Exception;
    
    XMLEntityHandler.EntityReader createUTF8Reader(final XMLEntityHandler p0, final XMLErrorReporter p1, final boolean p2, final InputStream p3, final StringPool p4) throws Exception;
    
    XMLEntityHandler.EntityReader createStringReader(final XMLEntityHandler p0, final XMLErrorReporter p1, final boolean p2, final int p3, final int p4, final int p5, final StringPool p6, final boolean p7) throws Exception;
}
