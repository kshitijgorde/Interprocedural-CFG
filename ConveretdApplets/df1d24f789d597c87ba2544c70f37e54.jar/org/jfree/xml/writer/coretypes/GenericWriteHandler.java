// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer.coretypes;

import org.jfree.xml.writer.XMLWriterException;
import org.jfree.xml.writer.RootXmlWriteHandler;
import org.jfree.xml.util.PropertyDefinition;
import org.jfree.xml.util.AttributeDefinition;
import org.jfree.xml.util.ObjectDescriptionException;
import java.io.IOException;
import org.jfree.util.Log;
import java.util.ArrayList;
import org.jfree.xml.writer.AttributeList;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.util.GenericObjectFactory;
import org.jfree.xml.writer.AbstractXmlWriteHandler;

public class GenericWriteHandler extends AbstractXmlWriteHandler
{
    private GenericObjectFactory factory;
    
    public GenericWriteHandler(final GenericObjectFactory factory) {
        this.factory = factory;
    }
    
    public void write(final String s, final Object o, final XMLWriter xmlWriter, final String s2, final String s3) throws IOException, XMLWriterException {
        try {
            this.factory.readProperties(o);
            final AttributeList list = new AttributeList();
            if (s2 != null) {
                list.setAttribute(s2, s3);
            }
            final AttributeDefinition[] attributeDefinitions = this.factory.getAttributeDefinitions();
            final ArrayList<String> list2 = new ArrayList<String>();
            for (int i = 0; i < attributeDefinitions.length; ++i) {
                final AttributeDefinition attributeDefinition = attributeDefinitions[i];
                final String attributeName = attributeDefinition.getAttributeName();
                final Object property = this.factory.getProperty(attributeDefinition.getPropertyName());
                if (property != null) {
                    Log.debug("Here: " + this.factory.getBaseClass() + " -> " + attributeDefinition.getPropertyName());
                    final String attributeValue = attributeDefinition.getHandler().toAttributeValue(property);
                    if (attributeValue != null) {
                        list.setAttribute(attributeName, attributeValue);
                    }
                }
                list2.add(attributeDefinition.getPropertyName());
            }
            xmlWriter.writeTag(s, list, false);
            xmlWriter.startBlock();
            final PropertyDefinition[] propertyDefinitions = this.factory.getPropertyDefinitions();
            final RootXmlWriteHandler rootHandler = this.getRootHandler();
            for (int j = 0; j < propertyDefinitions.length; ++j) {
                final PropertyDefinition propertyDefinition = propertyDefinitions[j];
                final String elementName = propertyDefinition.getElementName();
                rootHandler.write(elementName, this.factory.getProperty(propertyDefinition.getPropertyName()), this.factory.getTypeForTagName(elementName), xmlWriter);
            }
            xmlWriter.endBlock();
            xmlWriter.writeCloseTag(s);
        }
        catch (ObjectDescriptionException ex) {
            Log.warn("Unable to write element", ex);
            throw new IOException(ex.getMessage());
        }
    }
}
