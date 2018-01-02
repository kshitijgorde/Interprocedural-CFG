// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.adapters;

import org.jdom.DocType;
import java.io.InputStream;
import org.jdom.JDOMException;
import java.io.IOException;
import org.w3c.dom.Document;
import java.io.File;

public interface DOMAdapter
{
    Document getDocument(final File p0, final boolean p1) throws IOException, JDOMException;
    
    Document getDocument(final InputStream p0, final boolean p1) throws IOException, JDOMException;
    
    Document createDocument() throws JDOMException;
    
    Document createDocument(final DocType p0) throws JDOMException;
}
