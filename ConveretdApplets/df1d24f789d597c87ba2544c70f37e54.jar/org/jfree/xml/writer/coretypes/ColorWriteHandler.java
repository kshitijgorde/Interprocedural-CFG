// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.AttributeList;
import java.awt.Color;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class ColorWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final Color color = (Color)o;
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        list.setAttribute("value", this.encodeColor(color));
        if (color.getAlpha() != 255) {
            list.setAttribute("alpha", String.valueOf(color.getAlpha()));
        }
        xmlWriter.writeTag(s, list, true);
    }
    
    private String encodeColor(final Color color) {
        return "#" + this.encodeInt(color.getRed()) + this.encodeInt(color.getGreen()) + this.encodeInt(color.getBlue());
    }
    
    private String encodeInt(final int n) {
        final String hexString = Integer.toHexString(n);
        if (hexString.length() == 1) {
            return "0" + hexString;
        }
        return hexString;
    }
}
