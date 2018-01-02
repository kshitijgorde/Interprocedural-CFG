// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.parser;

import org.apache.xerces.xni.XMLDocumentHandler;

public interface XMLDocumentSource
{
    void setDocumentHandler(final XMLDocumentHandler p0);
    
    XMLDocumentHandler getDocumentHandler();
}
