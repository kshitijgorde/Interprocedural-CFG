// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import java.awt.Insets;
import org.jfree.xml.writer.AttributeList;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class InsetsWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        final Insets insets = (Insets)o;
        list.setAttribute("top", String.valueOf(insets.top));
        list.setAttribute("left", String.valueOf(insets.left));
        list.setAttribute("bottom", String.valueOf(insets.bottom));
        list.setAttribute("right", String.valueOf(insets.right));
        xmlWriter.writeTag(s, list, true);
    }
}
