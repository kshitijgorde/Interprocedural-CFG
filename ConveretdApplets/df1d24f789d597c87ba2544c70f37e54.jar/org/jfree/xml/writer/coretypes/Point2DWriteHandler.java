// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.AttributeList;
import java.awt.geom.Point2D;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class Point2DWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final Point2D point2D = (Point2D)o;
        final double x = point2D.getX();
        final double y = point2D.getY();
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        list.setAttribute("x", String.valueOf(x));
        list.setAttribute("y", String.valueOf(y));
        xmlWriter.writeTag(s, list, true);
    }
}
