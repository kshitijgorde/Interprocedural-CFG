// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.AttributeList;
import java.awt.geom.Rectangle2D;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class Rectangle2DWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final Rectangle2D rectangle2D = (Rectangle2D)o;
        final double x = rectangle2D.getX();
        final double y = rectangle2D.getY();
        final double width = rectangle2D.getWidth();
        final double height = rectangle2D.getHeight();
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        list.setAttribute("x", String.valueOf(x));
        list.setAttribute("y", String.valueOf(y));
        list.setAttribute("width", String.valueOf(width));
        list.setAttribute("height", String.valueOf(height));
        xmlWriter.writeTag(s, list, true);
    }
}
