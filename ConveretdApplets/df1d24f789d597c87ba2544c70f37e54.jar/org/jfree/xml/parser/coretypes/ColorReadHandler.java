// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.Color;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class ColorReadHandler extends AbstractXmlReadHandler
{
    private Color color;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.color = Color.decode(attributes.getValue("value"));
        if (attributes.getValue("alpha") != null) {
            this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), Integer.parseInt(attributes.getValue("alpha")));
        }
    }
    
    public Object getObject() {
        return this.color;
    }
}
