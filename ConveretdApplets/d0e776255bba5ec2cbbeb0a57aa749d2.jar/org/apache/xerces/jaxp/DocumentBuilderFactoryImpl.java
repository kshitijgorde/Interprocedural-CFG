// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory
{
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        return new DocumentBuilderImpl(this);
    }
    
    public void setAttribute(final String s, final Object o) throws IllegalArgumentException {
        throw new IllegalArgumentException("No attributes are implemented");
    }
    
    public Object getAttribute(final String s) throws IllegalArgumentException {
        throw new IllegalArgumentException("No attributes are implemented");
    }
}
