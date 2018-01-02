// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import org.jfree.xml.util.BasicTypeSupport;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.beans.IndexedPropertyDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import org.jfree.xml.generator.model.PropertyInfo;
import org.jfree.xml.generator.model.ClassDescription;
import org.jfree.xml.generator.model.PropertyType;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import org.jfree.xml.generator.model.TypeInfo;
import java.util.ArrayList;
import org.jfree.xml.generator.model.MultiplexMappingInfo;
import java.lang.reflect.Modifier;
import org.jfree.util.HashNMap;
import org.jfree.xml.generator.model.DescriptionModel;
import java.util.Map;
import java.util.Properties;

public final class ModelBuilder
{
    private static ModelBuilder instance;
    private Properties handlerMapping;
    static /* synthetic */ Class class$java$lang$Object;
    
    public static ModelBuilder getInstance() {
        if (ModelBuilder.instance == null) {
            ModelBuilder.instance = new ModelBuilder();
        }
        return ModelBuilder.instance;
    }
    
    private ModelBuilder() {
        this.handlerMapping = new Properties();
    }
    
    public void addAttributeHandlers(final Properties properties) {
        this.handlerMapping.putAll(properties);
    }
    
    public DescriptionModel buildModel(final SourceCollector sourceCollector, DescriptionModel descriptionModel) {
        Class[] array = sourceCollector.getClasses();
        if (descriptionModel == null) {
            descriptionModel = new DescriptionModel();
        }
        while (array.length != 0) {
            array = this.fillModel(array, descriptionModel);
        }
        this.fillSuperClasses(descriptionModel);
        final Class[] elementTypes = this.findElementTypes(descriptionModel);
        final HashNMap hashNMap = new HashNMap();
        for (int i = 0; i < elementTypes.length; ++i) {
            final Class clazz = elementTypes[i];
            for (int j = 0; j < elementTypes.length; ++j) {
                final Class clazz2 = elementTypes[j];
                if (!Modifier.isAbstract(clazz2.getModifiers())) {
                    if (clazz.isAssignableFrom(clazz2)) {
                        hashNMap.add(clazz, clazz2);
                    }
                }
            }
        }
        final Iterator keys = hashNMap.keys();
        while (keys.hasNext()) {
            final Class clazz3 = keys.next();
            final Class[] array2 = (Class[])hashNMap.toArray(clazz3, new Class[0]);
            if (array2.length < 2) {
                continue;
            }
            boolean b = false;
            MultiplexMappingInfo lookupMultiplexMapping = descriptionModel.getMappingModel().lookupMultiplexMapping(clazz3);
            ArrayList<TypeInfo> list;
            if (lookupMultiplexMapping == null) {
                lookupMultiplexMapping = new MultiplexMappingInfo(clazz3);
                list = new ArrayList<TypeInfo>();
                b = true;
            }
            else {
                list = new ArrayList<TypeInfo>(Arrays.asList(lookupMultiplexMapping.getChildClasses()));
            }
            for (int k = 0; k < array2.length; ++k) {
                final TypeInfo typeInfo = new TypeInfo(array2[k].getName(), array2[k]);
                if (!list.contains(typeInfo)) {
                    list.add(typeInfo);
                }
            }
            lookupMultiplexMapping.setChildClasses(list.toArray(new TypeInfo[0]));
            if (!b) {
                continue;
            }
            descriptionModel.getMappingModel().addMultiplexMapping(lookupMultiplexMapping);
        }
        return descriptionModel;
    }
    
    private Class[] findElementTypes(final DescriptionModel descriptionModel) {
        final ArrayList list = new ArrayList<Class>();
        for (int i = 0; i < descriptionModel.size(); ++i) {
            final ClassDescription value = descriptionModel.get(i);
            if (!list.contains(value.getObjectClass())) {
                list.add(value.getObjectClass());
            }
            final PropertyInfo[] properties = value.getProperties();
            for (int j = 0; j < properties.length; ++j) {
                if (properties[j].getPropertyType().equals(PropertyType.ELEMENT)) {
                    final Class type = properties[j].getType();
                    if (!list.contains(type)) {
                        if (!Modifier.isFinal(type.getModifiers())) {
                            list.add(type);
                        }
                    }
                }
            }
        }
        return list.toArray(new Class[list.size()]);
    }
    
    private void fillSuperClasses(final DescriptionModel descriptionModel) {
        for (int i = 0; i < descriptionModel.size(); ++i) {
            final ClassDescription value = descriptionModel.get(i);
            final Class superclass = value.getObjectClass().getSuperclass();
            if (superclass != null) {
                final ClassDescription value2 = descriptionModel.get(superclass);
                if (value2 != null) {
                    value.setSuperClass(value2.getObjectClass());
                }
            }
        }
    }
    
    private Class[] fillModel(final Class[] array, final DescriptionModel descriptionModel) {
        final ArrayList list = new ArrayList<Class<?>>();
        for (int i = 0; i < array.length; ++i) {
            Class superclass = array[i].getSuperclass();
            if (superclass != null) {
                if (!((ModelBuilder.class$java$lang$Object == null) ? (ModelBuilder.class$java$lang$Object = class$("java.lang.Object")) : ModelBuilder.class$java$lang$Object).equals(superclass) && !this.contains(array, superclass) && !list.contains(superclass)) {
                    list.add(superclass);
                }
            }
            else {
                superclass = ((ModelBuilder.class$java$lang$Object == null) ? (ModelBuilder.class$java$lang$Object = class$("java.lang.Object")) : ModelBuilder.class$java$lang$Object);
            }
            try {
                final ClassDescription classDescription = this.createClassDescription(Introspector.getBeanInfo(array[i], superclass), descriptionModel.get(array[i]));
                if (classDescription != null) {
                    descriptionModel.addClassDescription(classDescription);
                }
            }
            catch (IntrospectionException ex) {}
        }
        return list.toArray(new Class[0]);
    }
    
    private ClassDescription createClassDescription(final BeanInfo beanInfo, final ClassDescription classDescription) {
        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        final ArrayList list = new ArrayList<PropertyInfo>();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            final PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
            if (classDescription != null) {
                final PropertyInfo property = classDescription.getProperty(propertyDescriptor.getName());
                if (property != null) {
                    list.add(property);
                    continue;
                }
            }
            if (!(propertyDescriptors[i] instanceof IndexedPropertyDescriptor)) {
                final PropertyInfo simplePropertyInfo = this.createSimplePropertyInfo(propertyDescriptors[i]);
                if (simplePropertyInfo != null) {
                    list.add(simplePropertyInfo);
                }
            }
        }
        final PropertyInfo[] properties = list.toArray(new PropertyInfo[list.size()]);
        ClassDescription classDescription2;
        if (classDescription != null) {
            classDescription2 = classDescription;
        }
        else {
            classDescription2 = new ClassDescription(beanInfo.getBeanDescriptor().getBeanClass());
            classDescription2.setDescription(beanInfo.getBeanDescriptor().getShortDescription());
        }
        classDescription2.setProperties(properties);
        return classDescription2;
    }
    
    public static boolean isValidMethod(final Method method) {
        return method != null && Modifier.isPublic(method.getModifiers());
    }
    
    public PropertyInfo createSimplePropertyInfo(final PropertyDescriptor propertyDescriptor) {
        final boolean validMethod = isValidMethod(propertyDescriptor.getReadMethod());
        final boolean validMethod2 = isValidMethod(propertyDescriptor.getWriteMethod());
        if (!validMethod2 || !validMethod) {
            return null;
        }
        final PropertyInfo propertyInfo = new PropertyInfo(propertyDescriptor.getName(), propertyDescriptor.getPropertyType());
        propertyInfo.setConstrained(propertyDescriptor.isConstrained());
        propertyInfo.setDescription(propertyDescriptor.getShortDescription());
        propertyInfo.setNullable(true);
        propertyInfo.setPreserve(false);
        propertyInfo.setReadMethodAvailable(validMethod);
        propertyInfo.setWriteMethodAvailable(validMethod2);
        propertyInfo.setXmlName(propertyDescriptor.getName());
        if (this.isAttributeProperty(propertyDescriptor.getPropertyType())) {
            propertyInfo.setPropertyType(PropertyType.ATTRIBUTE);
            propertyInfo.setXmlHandler(this.getHandlerClass(propertyDescriptor.getPropertyType()));
        }
        else {
            propertyInfo.setPropertyType(PropertyType.ELEMENT);
        }
        return propertyInfo;
    }
    
    private boolean isAttributeProperty(final Class clazz) {
        return BasicTypeSupport.isBasicDataType(clazz) || this.handlerMapping.containsKey(clazz.getName());
    }
    
    private String getHandlerClass(final Class clazz) {
        if (BasicTypeSupport.isBasicDataType(clazz)) {
            final String handlerClass = BasicTypeSupport.getHandlerClass(clazz);
            if (handlerClass != null) {
                return handlerClass;
            }
        }
        return this.handlerMapping.getProperty(clazz.getName());
    }
    
    private boolean contains(final Class[] array, final Class clazz) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(clazz)) {
                return true;
            }
        }
        return false;
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
