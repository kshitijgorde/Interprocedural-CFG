// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class ObjectRefHandler extends AbstractXmlReadHandler
{
    private Object object;
    private String propertyName;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        if (this.getTagName().equals("objectRef")) {
            final String value = attributes.getValue("source");
            if (value == null) {
                throw new SAXException("Source name is not defined.");
            }
            this.propertyName = attributes.getValue("property");
            if (this.propertyName == null) {
                throw new SAXException("Property name is not defined.");
            }
            this.object = this.getRootHandler().getObject(value);
            if (this.object == null) {
                throw new SAXException("Referenced object is undefined.");
            }
        }
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public Object getObject() {
        return this.object;
    }
}
