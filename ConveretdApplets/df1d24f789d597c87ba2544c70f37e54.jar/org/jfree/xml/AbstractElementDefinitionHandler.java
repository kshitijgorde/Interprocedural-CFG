// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public abstract class AbstractElementDefinitionHandler implements ElementDefinitionHandler
{
    private Parser parser;
    
    public AbstractElementDefinitionHandler(final Parser parser) {
        this.parser = parser;
    }
    
    public void startElement(final String s, final Attributes attributes) throws SAXException {
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void endElement(final String s) throws SAXException {
    }
    
    public Parser getParser() {
        return this.parser;
    }
}
