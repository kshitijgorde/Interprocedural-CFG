// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.Font;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class FontReadHandler extends AbstractXmlReadHandler
{
    private Font font;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.font = new Font(attributes.getValue("family"), this.getFontStyle(attributes.getValue("style")), Integer.parseInt(attributes.getValue("size")));
    }
    
    private int getFontStyle(final String s) {
        if ("bold-italic".equals(s)) {
            return 3;
        }
        if ("bold".equals(s)) {
            return 1;
        }
        if ("italic".equals(s)) {
            return 2;
        }
        return 0;
    }
    
    public Object getObject() {
        return this.font;
    }
}
