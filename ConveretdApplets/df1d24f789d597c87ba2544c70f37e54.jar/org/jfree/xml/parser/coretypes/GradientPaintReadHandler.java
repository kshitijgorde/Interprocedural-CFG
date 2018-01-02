// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import java.awt.Color;
import java.awt.geom.Point2D;
import org.jfree.xml.parser.XmlReaderException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.jfree.xml.parser.XmlReadHandler;
import java.awt.GradientPaint;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class GradientPaintReadHandler extends AbstractXmlReadHandler
{
    private GradientPaint gradient;
    private XmlReadHandler color1Handler;
    private XmlReadHandler color2Handler;
    private XmlReadHandler point1Handler;
    private XmlReadHandler point2Handler;
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    
    public Object getObject() {
        return this.gradient;
    }
    
    protected XmlReadHandler getHandlerForChild(final String s, final Attributes attributes) throws SAXException, XmlReaderException {
        if ("color1".equals(s)) {
            return this.color1Handler = this.getRootHandler().createHandler((GradientPaintReadHandler.class$java$awt$Color == null) ? (GradientPaintReadHandler.class$java$awt$Color = class$("java.awt.Color")) : GradientPaintReadHandler.class$java$awt$Color, s, attributes);
        }
        if ("color2".equals(s)) {
            return this.color2Handler = this.getRootHandler().createHandler((GradientPaintReadHandler.class$java$awt$Color == null) ? (GradientPaintReadHandler.class$java$awt$Color = class$("java.awt.Color")) : GradientPaintReadHandler.class$java$awt$Color, s, attributes);
        }
        if ("point1".equals(s)) {
            return this.point1Handler = this.getRootHandler().createHandler((GradientPaintReadHandler.class$java$awt$geom$Point2D == null) ? (GradientPaintReadHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : GradientPaintReadHandler.class$java$awt$geom$Point2D, s, attributes);
        }
        if ("point2".equals(s)) {
            return this.point2Handler = this.getRootHandler().createHandler((GradientPaintReadHandler.class$java$awt$geom$Point2D == null) ? (GradientPaintReadHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : GradientPaintReadHandler.class$java$awt$geom$Point2D, s, attributes);
        }
        return null;
    }
    
    protected void doneParsing() throws XmlReaderException {
        if (this.point1Handler == null || this.point2Handler == null || this.color1Handler == null || this.color2Handler == null) {
            throw new XmlReaderException("Not all required subelements are defined.");
        }
        this.gradient = new GradientPaint((Point2D)this.point1Handler.getObject(), (Color)this.color1Handler.getObject(), (Point2D)this.point2Handler.getObject(), (Color)this.color2Handler.getObject());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
