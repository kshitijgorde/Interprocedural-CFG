// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.RootXmlWriteHandler;
import java.awt.GradientPaint;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class GradientPaintWriteHandler extends AbstractXmlWriteHandler
{
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final GradientPaint gradientPaint = (GradientPaint)o;
        xmlWriter.writeTag(s, s2, s3, false);
        xmlWriter.startBlock();
        final RootXmlWriteHandler rootHandler = this.getRootHandler();
        rootHandler.write("color1", gradientPaint.getColor1(), (GradientPaintWriteHandler.class$java$awt$Color == null) ? (GradientPaintWriteHandler.class$java$awt$Color = class$("java.awt.Color")) : GradientPaintWriteHandler.class$java$awt$Color, xmlWriter);
        xmlWriter.allowLineBreak();
        rootHandler.write("color2", gradientPaint.getColor2(), (GradientPaintWriteHandler.class$java$awt$Color == null) ? (GradientPaintWriteHandler.class$java$awt$Color = class$("java.awt.Color")) : GradientPaintWriteHandler.class$java$awt$Color, xmlWriter);
        xmlWriter.allowLineBreak();
        rootHandler.write("point1", gradientPaint.getPoint1(), (GradientPaintWriteHandler.class$java$awt$geom$Point2D == null) ? (GradientPaintWriteHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : GradientPaintWriteHandler.class$java$awt$geom$Point2D, xmlWriter);
        xmlWriter.allowLineBreak();
        rootHandler.write("point2", gradientPaint.getPoint2(), (GradientPaintWriteHandler.class$java$awt$geom$Point2D == null) ? (GradientPaintWriteHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : GradientPaintWriteHandler.class$java$awt$geom$Point2D, xmlWriter);
        xmlWriter.endBlock();
        xmlWriter.writeCloseTag(s);
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
