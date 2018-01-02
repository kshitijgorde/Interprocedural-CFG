// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.jaxp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory
{
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        final DocumentBuilderImpl db = new DocumentBuilderImpl(this);
        return db;
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        throw new IllegalArgumentException("No attributes are implemented");
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        throw new IllegalArgumentException("No attributes are implemented");
    }
}
