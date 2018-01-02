// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.AttributeList;
import java.awt.BasicStroke;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class BasicStrokeWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final BasicStroke basicStroke = (BasicStroke)o;
        final float[] dashArray = basicStroke.getDashArray();
        final float dashPhase = basicStroke.getDashPhase();
        final int endCap = basicStroke.getEndCap();
        final int lineJoin = basicStroke.getLineJoin();
        final float lineWidth = basicStroke.getLineWidth();
        final float miterLimit = basicStroke.getMiterLimit();
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        list.setAttribute("type", "basic");
        list.setAttribute("endCap", String.valueOf(endCap));
        list.setAttribute("lineJoin", String.valueOf(lineJoin));
        list.setAttribute("lineWidth", String.valueOf(lineWidth));
        list.setAttribute("miterLimit", String.valueOf(miterLimit));
        if (dashArray != null) {
            list.setAttribute("dashArray", this.toString(dashArray));
            list.setAttribute("dashPhase", String.valueOf(dashPhase));
        }
        xmlWriter.writeTag(s, list, true);
    }
    
    private String toString(final float[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            final float n = array[i];
            if (i != 0) {
                sb.append(',');
            }
            sb.append(n);
        }
        return sb.toString();
    }
}
