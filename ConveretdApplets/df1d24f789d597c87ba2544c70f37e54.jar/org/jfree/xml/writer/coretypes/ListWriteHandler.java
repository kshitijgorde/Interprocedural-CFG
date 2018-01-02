// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class ListWriteHandler extends AbstractXmlWriteHandler
{
    static /* synthetic */ Class class$java$lang$Object;
    
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        xmlWriter.writeTag(s, s2, s3, false);
        for (final Object next : (List)o) {
            if (next == null) {
                xmlWriter.writeTag("null", true);
            }
            else {
                this.getRootHandler().write("object", next, (ListWriteHandler.class$java$lang$Object == null) ? (ListWriteHandler.class$java$lang$Object = class$("java.lang.Object")) : ListWriteHandler.class$java$lang$Object, xmlWriter);
            }
        }
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
