// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

public abstract class AbstractXmlWriteHandler implements XmlWriteHandler
{
    private RootXmlWriteHandler rootHandler;
    
    public RootXmlWriteHandler getRootHandler() {
        return this.rootHandler;
    }
    
    public void setRootHandler(final RootXmlWriteHandler rootHandler) {
        this.rootHandler = rootHandler;
    }
}
