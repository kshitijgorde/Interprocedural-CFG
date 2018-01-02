// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import org.xml.sax.SAXException;

class ElementValidator
{
    static final ElementValidator ANY;
    
    public void consume(final String type) throws SAXException {
    }
    
    public void text() throws SAXException {
    }
    
    public void done() throws SAXException {
    }
    
    static {
        ANY = new ElementValidator();
    }
}
