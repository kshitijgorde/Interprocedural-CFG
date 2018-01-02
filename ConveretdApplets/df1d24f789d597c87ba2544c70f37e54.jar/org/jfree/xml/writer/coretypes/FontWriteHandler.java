// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import org.jfree.xml.writer.AttributeList;
import java.awt.Font;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class FontWriteHandler extends AbstractXmlWriteHandler
{
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        final Font font = (Font)o;
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        list.setAttribute("family", font.getFamily());
        list.setAttribute("size", String.valueOf(font.getSize()));
        list.setAttribute("style", String.valueOf(this.getFontStyle(font)));
        xmlWriter.writeTag(s, list, true);
    }
    
    private String getFontStyle(final Font font) {
        if (font.isBold() && font.isItalic()) {
            return "bold-italic";
        }
        if (font.isBold()) {
            return "bold";
        }
        if (font.isItalic()) {
            return "italic";
        }
        return "plain";
    }
}
