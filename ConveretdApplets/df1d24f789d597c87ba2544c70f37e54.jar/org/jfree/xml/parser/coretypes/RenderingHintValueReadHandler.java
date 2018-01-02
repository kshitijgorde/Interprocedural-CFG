// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.jfree.xml.parser.XmlReaderException;
import java.lang.reflect.Field;
import org.jfree.util.Log;
import java.lang.reflect.Modifier;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class RenderingHintValueReadHandler extends AbstractXmlReadHandler
{
    private Object key;
    private Object value;
    static /* synthetic */ Class class$java$awt$RenderingHints;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        final String value = attributes.getValue("key");
        final String value2 = attributes.getValue("value");
        this.key = this.stringToHintField(value);
        this.value = this.stringToHintField(value2);
    }
    
    private Object stringToHintField(final String s) {
        final Field[] fields = ((RenderingHintValueReadHandler.class$java$awt$RenderingHints == null) ? (RenderingHintValueReadHandler.class$java$awt$RenderingHints = class$("java.awt.RenderingHints")) : RenderingHintValueReadHandler.class$java$awt$RenderingHints).getFields();
        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                try {
                    if (field.getName().equals(s)) {
                        return field.get(null);
                    }
                }
                catch (Exception ex) {
                    Log.info("Unable to write RenderingHint", ex);
                }
            }
        }
        throw new IllegalArgumentException("Invalid value given");
    }
    
    public Object getObject() throws XmlReaderException {
        return new Object[] { this.key, this.value };
    }
    
    public Object getKey() {
        return this.key;
    }
    
    public Object getValue() {
        return this.value;
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
