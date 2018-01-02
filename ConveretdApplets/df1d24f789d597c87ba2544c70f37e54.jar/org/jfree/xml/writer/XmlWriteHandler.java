// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.io.IOException;

public interface XmlWriteHandler
{
    RootXmlWriteHandler getRootHandler();
    
    void setRootHandler(final RootXmlWriteHandler p0);
    
    void write(final String p0, final Object p1, final XMLWriter p2, final String p3, final String p4) throws IOException, XMLWriterException;
}
