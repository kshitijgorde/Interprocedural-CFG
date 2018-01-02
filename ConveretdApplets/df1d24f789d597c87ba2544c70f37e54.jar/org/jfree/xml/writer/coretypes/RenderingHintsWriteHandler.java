// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import java.lang.reflect.Field;
import org.jfree.util.Log;
import java.lang.reflect.Modifier;
import org.jfree.xml.writer.XMLWriterException;
import java.io.IOException;
import java.util.Iterator;
import org.jfree.xml.writer.AttributeList;
import java.awt.RenderingHints;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class RenderingHintsWriteHandler extends AbstractXmlWriteHandler
{
    static /* synthetic */ Class class$java$awt$RenderingHints;
    
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        xmlWriter.writeTag(s, s2, s3, false);
        xmlWriter.allowLineBreak();
        final RenderingHints renderingHints = (RenderingHints)o;
        for (final RenderingHints.Key key : renderingHints.keySet()) {
            final String hintFieldToString = this.hintFieldToString(key);
            final String hintFieldToString2 = this.hintFieldToString(renderingHints.get(key));
            final AttributeList list = new AttributeList();
            list.setAttribute("key", hintFieldToString);
            list.setAttribute("value", hintFieldToString2);
            xmlWriter.writeTag("entry", list, true);
            xmlWriter.allowLineBreak();
        }
        xmlWriter.writeCloseTag(s);
        xmlWriter.allowLineBreak();
    }
    
    private String hintFieldToString(final Object o) {
        final Field[] fields = ((RenderingHintsWriteHandler.class$java$awt$RenderingHints == null) ? (RenderingHintsWriteHandler.class$java$awt$RenderingHints = class$("java.awt.RenderingHints")) : RenderingHintsWriteHandler.class$java$awt$RenderingHints).getFields();
        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    if (o.equals(field.get(null))) {
                        return field.getName();
                    }
                }
                catch (Exception ex) {
                    Log.info("Unable to write RenderingHint", ex);
                }
            }
        }
        throw new IllegalArgumentException("Invalid value given");
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
