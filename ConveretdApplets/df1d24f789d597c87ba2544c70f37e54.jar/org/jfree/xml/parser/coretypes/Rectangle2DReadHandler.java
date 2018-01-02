// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.geom.Rectangle2D;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class Rectangle2DReadHandler extends AbstractXmlReadHandler
{
    private Rectangle2D rectangle;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        (this.rectangle = this.createRect(attributes.getValue("type"))).setRect(Double.parseDouble(attributes.getValue("x")), Double.parseDouble(attributes.getValue("y")), Double.parseDouble(attributes.getValue("width")), Double.parseDouble(attributes.getValue("height")));
    }
    
    private Rectangle2D createRect(final String s) {
        if ("float".equals(s)) {
            return new Rectangle2D.Float();
        }
        return new Rectangle2D.Double();
    }
    
    public Object getObject() {
        return this.rectangle;
    }
}
