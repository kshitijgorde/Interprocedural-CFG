// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.jfree.xml.util.PropertyDefinition;
import org.jfree.xml.util.ConstructorDefinition;
import org.jfree.xml.util.LookupDefinition;
import org.jfree.xml.parser.RootXmlReadHandler;
import org.jfree.xml.parser.XmlReaderException;
import org.jfree.util.Log;
import org.jfree.xml.parser.XmlReadHandler;
import org.jfree.xml.util.AttributeDefinition;
import org.jfree.xml.util.ObjectDescriptionException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.HashMap;
import java.util.ArrayList;
import org.jfree.xml.util.GenericObjectFactory;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class GenericReadHandler extends AbstractXmlReadHandler
{
    private Object object;
    private GenericObjectFactory objectFactory;
    private ArrayList objectRefHandlers;
    private HashMap createdHandler;
    
    public GenericReadHandler(final GenericObjectFactory objectFactory) {
        this.createdHandler = new HashMap();
        this.objectRefHandlers = new ArrayList();
        this.objectFactory = objectFactory;
    }
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        try {
            final AttributeDefinition[] attributeDefinitions = this.objectFactory.getAttributeDefinitions();
            for (int i = 0; i < attributeDefinitions.length; ++i) {
                final AttributeDefinition attributeDefinition = attributeDefinitions[i];
                final String value = attributes.getValue(attributeDefinition.getAttributeName());
                if (value != null) {
                    this.objectFactory.setProperty(attributeDefinition.getPropertyName(), attributeDefinition.getHandler().toPropertyValue(value));
                }
            }
        }
        catch (ObjectDescriptionException e) {
            throw new SAXException(e);
        }
    }
    
    protected XmlReadHandler getHandlerForChild(final String s, final Attributes attributes) throws SAXException {
        try {
            if (s.equals("objectRef")) {
                final ObjectRefHandler objectRefHandler = new ObjectRefHandler();
                this.objectRefHandlers.add(objectRefHandler);
                return objectRefHandler;
            }
            final XmlReadHandler handler = this.rootHandler.createHandler(this.objectFactory.getTypeForTagName(s), s, attributes);
            if (handler != null) {
                this.createdHandler.put(s, handler);
            }
            return handler;
        }
        catch (ObjectDescriptionException e) {
            Log.debug("Failed to get handler for child: ", e);
            throw new SAXException(e);
        }
    }
    
    public Object getObject() throws XmlReaderException {
        if (this.object != null) {
            return this.object;
        }
        final RootXmlReadHandler rootHandler = this.getRootHandler();
        try {
            for (int i = 0; i < this.objectRefHandlers.size(); ++i) {
                final ObjectRefHandler objectRefHandler = this.objectRefHandlers.get(i);
                this.objectFactory.setProperty(objectRefHandler.getPropertyName(), objectRefHandler.getObject());
            }
            final ArrayList list = new ArrayList<String>();
            final LookupDefinition[] lookupDefinitions = this.objectFactory.getLookupDefinitions();
            for (int j = 0; j < lookupDefinitions.length; ++j) {
                final LookupDefinition lookupDefinition = lookupDefinitions[j];
                list.add(lookupDefinition.getPropertyName());
                Log.debug("lookup object: " + lookupDefinition.getPropertyName());
                final Object object = rootHandler.getObject(lookupDefinition.getRegistryKey());
                if (object == null) {
                    Log.warn("Failed to lookup object: " + object);
                }
                else {
                    this.objectFactory.setProperty(lookupDefinition.getPropertyName(), object);
                }
            }
            final ConstructorDefinition[] constructorDefinitions = this.objectFactory.getConstructorDefinitions();
            for (int k = 0; k < constructorDefinitions.length; ++k) {
                final ConstructorDefinition constructorDefinition = constructorDefinitions[k];
                if (!list.contains(constructorDefinition.getPropertyName())) {
                    if (this.objectFactory.isPropertyDefinition(constructorDefinition.getPropertyName())) {
                        final PropertyDefinition propertyDefinitionByPropertyName = this.objectFactory.getPropertyDefinitionByPropertyName(constructorDefinition.getPropertyName());
                        final XmlReadHandler xmlReadHandler = this.createdHandler.get(propertyDefinitionByPropertyName.getElementName());
                        if (xmlReadHandler != null) {
                            this.objectFactory.setProperty(propertyDefinitionByPropertyName.getPropertyName(), xmlReadHandler.getObject());
                        }
                    }
                }
            }
            this.object = this.objectFactory.createObject();
            Object object2 = null;
            if (this.objectFactory.getRegisterName() != null) {
                object2 = rootHandler.getObject(this.objectFactory.getRegisterName());
                rootHandler.putObject(this.objectFactory.getRegisterName(), this.object);
            }
            final PropertyDefinition[] propertyDefinitions = this.objectFactory.getPropertyDefinitions();
            for (int l = 0; l < propertyDefinitions.length; ++l) {
                final PropertyDefinition propertyDefinition = propertyDefinitions[l];
                final XmlReadHandler xmlReadHandler2 = this.createdHandler.get(propertyDefinition.getElementName());
                if (xmlReadHandler2 != null) {
                    this.objectFactory.setProperty(propertyDefinition.getPropertyName(), xmlReadHandler2.getObject());
                }
            }
            this.objectFactory.writeObjectProperties(this.object);
            if (this.objectFactory.getRegisterName() != null) {
                rootHandler.putObject(this.objectFactory.getRegisterName(), object2);
            }
        }
        catch (ObjectDescriptionException ex) {
            Log.error("Unable to create object.", ex);
            throw new XmlReaderException("Unable to create object.", ex);
        }
        return this.object;
    }
}
