// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import org.jfree.xml.attributehandlers.AttributeHandler;
import org.jfree.util.Log;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectFactoryLoader extends AbstractModelReader implements ObjectFactory
{
    private HashMap objectMappings;
    private HashMap manualMappings;
    private HashMap multiplexMappings;
    private Class target;
    private String registerName;
    private ArrayList propertyDefinition;
    private ArrayList attributeDefinition;
    private ArrayList constructorDefinition;
    private ArrayList lookupDefinitions;
    private ArrayList orderedNames;
    private String baseClass;
    private String attributeName;
    private ArrayList multiplexEntries;
    static /* synthetic */ Class class$java$lang$Object;
    
    public ObjectFactoryLoader(final URL url) throws ObjectDescriptionException {
        this.objectMappings = new HashMap();
        this.manualMappings = new HashMap();
        this.multiplexMappings = new HashMap();
        this.parseXml(url);
        this.rebuildSuperClasses();
    }
    
    private void rebuildSuperClasses() throws ObjectDescriptionException {
        this.propertyDefinition = new ArrayList();
        this.attributeDefinition = new ArrayList();
        this.constructorDefinition = new ArrayList();
        this.lookupDefinitions = new ArrayList();
        this.orderedNames = new ArrayList();
        final HashMap<Object, GenericObjectFactory> objectMappings = new HashMap<Object, GenericObjectFactory>();
        for (final Object next : this.objectMappings.keySet()) {
            final GenericObjectFactory genericObjectFactory = this.objectMappings.get(next);
            this.performSuperClassUpdate(genericObjectFactory);
            objectMappings.put(next, new GenericObjectFactory(genericObjectFactory.getBaseClass(), genericObjectFactory.getRegisterName(), this.constructorDefinition.toArray(new ConstructorDefinition[0]), this.propertyDefinition.toArray(new PropertyDefinition[0]), this.lookupDefinitions.toArray(new LookupDefinition[0]), this.attributeDefinition.toArray(new AttributeDefinition[0]), this.orderedNames.toArray(new String[0])));
            this.propertyDefinition.clear();
            this.attributeDefinition.clear();
            this.constructorDefinition.clear();
            this.lookupDefinitions.clear();
            this.orderedNames.clear();
        }
        this.objectMappings.clear();
        this.objectMappings = objectMappings;
        this.propertyDefinition = null;
        this.attributeDefinition = null;
        this.constructorDefinition = null;
        this.lookupDefinitions = null;
        this.orderedNames = null;
    }
    
    private void performSuperClassUpdate(final GenericObjectFactory genericObjectFactory) {
        final Class superclass = genericObjectFactory.getBaseClass().getSuperclass();
        if (superclass != null && !superclass.equals((ObjectFactoryLoader.class$java$lang$Object == null) ? (ObjectFactoryLoader.class$java$lang$Object = class$("java.lang.Object")) : ObjectFactoryLoader.class$java$lang$Object)) {
            final GenericObjectFactory genericObjectFactory2 = this.objectMappings.get(superclass);
            if (genericObjectFactory2 != null) {
                this.performSuperClassUpdate(genericObjectFactory2);
            }
        }
        this.propertyDefinition.addAll(Arrays.asList(genericObjectFactory.getPropertyDefinitions()));
        this.attributeDefinition.addAll(Arrays.asList(genericObjectFactory.getAttributeDefinitions()));
        this.constructorDefinition.addAll(Arrays.asList(genericObjectFactory.getConstructorDefinitions()));
        this.lookupDefinitions.addAll(Arrays.asList(genericObjectFactory.getLookupDefinitions()));
        this.orderedNames.addAll(Arrays.asList(genericObjectFactory.getOrderedPropertyNames()));
    }
    
    protected boolean startObjectDefinition(final String s, final String registerName, final boolean b) throws ObjectDescriptionException {
        if (b) {
            return false;
        }
        this.target = this.loadClass(s);
        if (this.target == null) {
            Log.warn(new Log.SimpleMessage("Failed to load class ", s));
            return false;
        }
        this.registerName = registerName;
        this.propertyDefinition = new ArrayList();
        this.attributeDefinition = new ArrayList();
        this.constructorDefinition = new ArrayList();
        this.lookupDefinitions = new ArrayList();
        this.orderedNames = new ArrayList();
        return true;
    }
    
    protected void handleAttributeDefinition(final String s, final String s2, final String s3) throws ObjectDescriptionException {
        final AttributeHandler loadAttributeHandler = this.loadAttributeHandler(s3);
        this.orderedNames.add(s);
        this.attributeDefinition.add(new AttributeDefinition(s, s2, loadAttributeHandler));
    }
    
    protected void handleElementDefinition(final String s, final String s2) throws ObjectDescriptionException {
        this.orderedNames.add(s);
        this.propertyDefinition.add(new PropertyDefinition(s, s2));
    }
    
    protected void handleLookupDefinition(final String s, final String s2) throws ObjectDescriptionException {
        final LookupDefinition lookupDefinition = new LookupDefinition(s, s2);
        this.orderedNames.add(s);
        this.lookupDefinitions.add(lookupDefinition);
    }
    
    protected void endObjectDefinition() throws ObjectDescriptionException {
        this.objectMappings.put(this.target, new GenericObjectFactory(this.target, this.registerName, this.constructorDefinition.toArray(new ConstructorDefinition[0]), this.propertyDefinition.toArray(new PropertyDefinition[0]), this.lookupDefinitions.toArray(new LookupDefinition[0]), this.attributeDefinition.toArray(new AttributeDefinition[0]), this.orderedNames.toArray(new String[0])));
    }
    
    protected void handleConstructorDefinition(final String s, final String s2) {
        final Class loadClass = this.loadClass(s2);
        this.orderedNames.add(s);
        this.constructorDefinition.add(new ConstructorDefinition(s, loadClass));
    }
    
    protected boolean handleManualMapping(final String s, final String s2, final String s3) throws ObjectDescriptionException {
        if (!this.manualMappings.containsKey(s)) {
            final Class loadClass = this.loadClass(s);
            this.manualMappings.put(loadClass, new ManualMappingDefinition(loadClass, s2, s3));
            return true;
        }
        return false;
    }
    
    protected void startMultiplexMapping(final String baseClass, final String attributeName) {
        this.baseClass = baseClass;
        this.attributeName = attributeName;
        this.multiplexEntries = new ArrayList();
    }
    
    protected void handleMultiplexMapping(final String s, final String s2) throws ObjectDescriptionException {
        this.multiplexEntries.add(new MultiplexMappingEntry(s, s2));
    }
    
    protected void endMultiplexMapping() throws ObjectDescriptionException {
        final MultiplexMappingEntry[] array = this.multiplexEntries.toArray(new MultiplexMappingEntry[0]);
        final Class loadClass = this.loadClass(this.baseClass);
        this.multiplexMappings.put(loadClass, new MultiplexMappingDefinition(loadClass, this.attributeName, array));
        this.multiplexEntries = null;
    }
    
    private AttributeHandler loadAttributeHandler(final String s) throws ObjectDescriptionException {
        final Class loadClass = this.loadClass(s);
        try {
            return loadClass.newInstance();
        }
        catch (Exception ex) {
            throw new ObjectDescriptionException("Invalid attribute handler specified: " + s);
        }
    }
    
    public boolean isGenericHandler(final Class clazz) {
        return this.objectMappings.containsKey(clazz);
    }
    
    public GenericObjectFactory getFactoryForClass(final Class clazz) {
        final GenericObjectFactory genericObjectFactory = this.objectMappings.get(clazz);
        if (genericObjectFactory == null) {
            return null;
        }
        return genericObjectFactory.getInstance();
    }
    
    public ManualMappingDefinition getManualMappingDefinition(final Class clazz) {
        return this.manualMappings.get(clazz);
    }
    
    public MultiplexMappingDefinition getMultiplexDefinition(final Class clazz) {
        return this.multiplexMappings.get(clazz);
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
