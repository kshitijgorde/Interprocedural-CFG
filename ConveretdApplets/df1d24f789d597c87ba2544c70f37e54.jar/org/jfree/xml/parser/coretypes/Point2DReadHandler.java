// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.geom.Point2D;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class Point2DReadHandler extends AbstractXmlReadHandler
{
    private Point2D point;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.point = new Point2D.Double(Double.parseDouble(attributes.getValue("x")), Double.parseDouble(attributes.getValue("y")));
    }
    
    public Point2D getPoint2D() {
        return this.point;
    }
    
    public Object getObject() {
        return this.point;
    }
}
