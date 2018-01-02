// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.jfree.xml.parser.XmlReaderException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.Insets;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class InsetsReadHandler extends AbstractXmlReadHandler
{
    private Insets insets;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.insets = new Insets(Integer.parseInt(attributes.getValue("top")), Integer.parseInt(attributes.getValue("left")), Integer.parseInt(attributes.getValue("bottom")), Integer.parseInt(attributes.getValue("right")));
    }
    
    public Object getObject() throws XmlReaderException {
        return this.insets;
    }
}
