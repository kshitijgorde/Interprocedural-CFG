// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.HashMap;

public final class GenericObjectFactory
{
    private final ConstructorDefinition[] constructorDefinitions;
    private final PropertyDefinition[] propertyDefinitions;
    private final LookupDefinition[] lookupDefinitions;
    private final AttributeDefinition[] attributeDefinitions;
    private final String[] orderedPropertyNames;
    private final HashMap propertyInfos;
    private final HashMap propertyValues;
    private final Class baseClass;
    private final String registerName;
    static /* synthetic */ Class class$java$lang$Object;
    
    public GenericObjectFactory(final Class baseClass, final String registerName, final ConstructorDefinition[] constructorDefinitions, final PropertyDefinition[] propertyDefinitions, final LookupDefinition[] lookupDefinitions, final AttributeDefinition[] attributeDefinitions, final String[] orderedPropertyNames) throws ObjectDescriptionException {
        if (baseClass == null) {
            throw new NullPointerException("BaseClass cannot be null.");
        }
        this.baseClass = baseClass;
        this.registerName = registerName;
        this.propertyInfos = new HashMap();
        this.propertyValues = new HashMap();
        this.constructorDefinitions = constructorDefinitions;
        this.propertyDefinitions = propertyDefinitions;
        this.lookupDefinitions = lookupDefinitions;
        this.attributeDefinitions = attributeDefinitions;
        this.orderedPropertyNames = orderedPropertyNames;
        try {
            final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(baseClass, (GenericObjectFactory.class$java$lang$Object == null) ? (GenericObjectFactory.class$java$lang$Object = class$("java.lang.Object")) : GenericObjectFactory.class$java$lang$Object).getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                this.propertyInfos.put(propertyDescriptors[i].getName(), propertyDescriptors[i]);
            }
        }
        catch (IntrospectionException ex) {
            throw new ObjectDescriptionException("This is an ugly solution right now ... dirty hack attack");
        }
    }
    
    private GenericObjectFactory(final GenericObjectFactory genericObjectFactory) {
        this.baseClass = genericObjectFactory.baseClass;
        this.propertyValues = new HashMap();
        this.orderedPropertyNames = genericObjectFactory.orderedPropertyNames;
        this.constructorDefinitions = genericObjectFactory.constructorDefinitions;
        this.propertyDefinitions = genericObjectFactory.propertyDefinitions;
        this.attributeDefinitions = genericObjectFactory.attributeDefinitions;
        this.propertyInfos = genericObjectFactory.propertyInfos;
        this.registerName = genericObjectFactory.registerName;
        this.lookupDefinitions = genericObjectFactory.lookupDefinitions;
    }
    
    public GenericObjectFactory getInstance() {
        return new GenericObjectFactory(this);
    }
    
    public String getRegisterName() {
        return this.registerName;
    }
    
    private PropertyDescriptor getPropertyDescriptor(final String s) {
        return this.propertyInfos.get(s);
    }
    
    public Class getTypeForTagName(final String s) throws ObjectDescriptionException {
        final PropertyDefinition propertyDefinitionByTagName = this.getPropertyDefinitionByTagName(s);
        final PropertyDescriptor propertyDescriptor = this.getPropertyDescriptor(propertyDefinitionByTagName.getPropertyName());
        if (propertyDescriptor == null) {
            throw new ObjectDescriptionException("Invalid Definition: " + propertyDefinitionByTagName.getPropertyName());
        }
        return propertyDescriptor.getPropertyType();
    }
    
    public boolean isPropertyDefinition(final String s) {
        for (int i = 0; i < this.propertyDefinitions.length; ++i) {
            if (this.propertyDefinitions[i].getPropertyName().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public PropertyDefinition getPropertyDefinitionByPropertyName(final String s) throws ObjectDescriptionException {
        for (int i = 0; i < this.propertyDefinitions.length; ++i) {
            final PropertyDefinition propertyDefinition = this.propertyDefinitions[i];
            if (propertyDefinition.getPropertyName().equals(s)) {
                return propertyDefinition;
            }
        }
        throw new ObjectDescriptionException("This property is not defined for this kind of object. : " + s);
    }
    
    public PropertyDefinition getPropertyDefinitionByTagName(final String s) throws ObjectDescriptionException {
        for (int i = 0; i < this.propertyDefinitions.length; ++i) {
            final PropertyDefinition propertyDefinition = this.propertyDefinitions[i];
            if (propertyDefinition.getElementName().equals(s)) {
                return propertyDefinition;
            }
        }
        throw new ObjectDescriptionException("This tag is not defined for this kind of object. : " + s);
    }
    
    public ConstructorDefinition[] getConstructorDefinitions() {
        return this.constructorDefinitions;
    }
    
    public AttributeDefinition[] getAttributeDefinitions() {
        return this.attributeDefinitions;
    }
    
    public PropertyDefinition[] getPropertyDefinitions() {
        return this.propertyDefinitions;
    }
    
    public String[] getOrderedPropertyNames() {
        return this.orderedPropertyNames;
    }
    
    public LookupDefinition[] getLookupDefinitions() {
        return this.lookupDefinitions;
    }
    
    public Object getProperty(final String s) {
        return this.propertyValues.get(s);
    }
    
    public Object createObject() throws ObjectDescriptionException {
        final Class[] array = new Class[this.constructorDefinitions.length];
        final Object[] array2 = new Object[this.constructorDefinitions.length];
        for (int i = 0; i < array.length; ++i) {
            final ConstructorDefinition constructorDefinition = this.constructorDefinitions[i];
            array[i] = constructorDefinition.getType();
            if (constructorDefinition.isNull()) {
                array2[i] = null;
            }
            else {
                array2[i] = this.getProperty(constructorDefinition.getPropertyName());
            }
        }
        try {
            return this.baseClass.getConstructor((Class<?>[])array).newInstance(array2);
        }
        catch (Exception ex) {
            throw new ObjectDescriptionException("Ugh! Constructor made a buuuh!", ex);
        }
    }
    
    public void setProperty(final String s, final Object o) throws ObjectDescriptionException {
        final PropertyDescriptor propertyDescriptor = this.getPropertyDescriptor(s);
        if (propertyDescriptor == null) {
            throw new ObjectDescriptionException("Unknown property " + s);
        }
        if (!this.isAssignableOrPrimitive(propertyDescriptor.getPropertyType(), o.getClass())) {
            throw new ObjectDescriptionException("Invalid value: " + propertyDescriptor.getPropertyType() + " vs. " + o.getClass());
        }
        this.propertyValues.put(s, o);
    }
    
    private boolean isAssignableOrPrimitive(final Class clazz, final Class clazz2) {
        return BasicTypeSupport.isBasicDataType(clazz) || clazz.isAssignableFrom(clazz2);
    }
    
    private boolean isConstructorProperty(final String s) {
        for (int i = 0; i < this.constructorDefinitions.length; ++i) {
            if (s.equals(this.constructorDefinitions[i].getPropertyName())) {
                return true;
            }
        }
        return false;
    }
    
    public void writeObjectProperties(final Object o) throws ObjectDescriptionException {
        for (int i = 0; i < this.orderedPropertyNames.length; ++i) {
            try {
                final String s = this.orderedPropertyNames[i];
                if (!this.isConstructorProperty(s)) {
                    final Object property = this.getProperty(s);
                    if (property != null) {
                        this.getPropertyDescriptor(s).getWriteMethod().invoke(o, property);
                    }
                }
            }
            catch (Exception ex) {
                throw new ObjectDescriptionException("Failed to set properties." + this.getBaseClass(), ex);
            }
        }
    }
    
    public void readProperties(final Object o) throws ObjectDescriptionException {
        for (int i = 0; i < this.orderedPropertyNames.length; ++i) {
            try {
                final String s = this.orderedPropertyNames[i];
                final PropertyDescriptor propertyDescriptor = this.getPropertyDescriptor(s);
                if (propertyDescriptor == null) {
                    throw new IllegalStateException("No property defined: " + s);
                }
                final Object invoke = propertyDescriptor.getReadMethod().invoke(o, new Object[0]);
                if (invoke != null) {
                    this.setProperty(s, invoke);
                }
            }
            catch (Exception ex) {
                throw new ObjectDescriptionException("Failed to set properties.", ex);
            }
        }
    }
    
    public Class getBaseClass() {
        return this.baseClass;
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
