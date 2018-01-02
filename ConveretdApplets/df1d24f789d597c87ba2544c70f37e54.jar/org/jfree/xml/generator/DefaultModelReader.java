// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import org.jfree.io.IOUtils;
import org.jfree.xml.generator.model.ManualMappingInfo;
import org.jfree.xml.generator.model.IgnoredPropertyInfo;
import java.beans.PropertyDescriptor;
import org.jfree.xml.generator.model.PropertyType;
import org.jfree.xml.generator.model.Comments;
import org.jfree.xml.generator.model.TypeInfo;
import org.jfree.xml.generator.model.PropertyInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import org.jfree.xml.util.ObjectDescriptionException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import org.jfree.xml.generator.model.MultiplexMappingInfo;
import java.net.URL;
import java.beans.BeanInfo;
import org.jfree.xml.generator.model.ClassDescription;
import org.jfree.xml.generator.model.DescriptionModel;
import org.jfree.xml.util.AbstractModelReader;

public class DefaultModelReader extends AbstractModelReader
{
    private DescriptionModel model;
    private ClassDescription currentClassDescription;
    private BeanInfo currentBeanInfo;
    private URL baseURL;
    private String source;
    private MultiplexMappingInfo multiplexInfo;
    private ArrayList multiplexTypeInfos;
    private ArrayList propertyList;
    private ArrayList constructorList;
    static /* synthetic */ Class class$java$lang$Object;
    
    public synchronized DescriptionModel load(final String s) throws IOException, ObjectDescriptionException {
        this.model = new DescriptionModel();
        this.parseXml(this.baseURL = new File(s).toURL());
        this.fillSuperClasses();
        return this.model;
    }
    
    protected void fillSuperClasses() {
        for (int i = 0; i < this.model.size(); ++i) {
            final ClassDescription value = this.model.get(i);
            final Class superclass = value.getObjectClass().getSuperclass();
            if (superclass != null) {
                final ClassDescription value2 = this.model.get(superclass);
                if (value2 != null) {
                    value.setSuperClass(value2.getObjectClass());
                }
            }
        }
    }
    
    protected boolean startObjectDefinition(final String s, final String registerKey, final boolean preserve) {
        final Class loadClass = this.loadClass(s);
        if (loadClass == null) {
            return false;
        }
        (this.currentClassDescription = new ClassDescription(loadClass)).setPreserve(preserve);
        this.currentClassDescription.setRegisterKey(registerKey);
        try {
            this.currentBeanInfo = Introspector.getBeanInfo(loadClass, (DefaultModelReader.class$java$lang$Object == null) ? (DefaultModelReader.class$java$lang$Object = class$("java.lang.Object")) : DefaultModelReader.class$java$lang$Object);
        }
        catch (IntrospectionException ex) {
            return false;
        }
        this.propertyList = new ArrayList();
        this.constructorList = new ArrayList();
        return true;
    }
    
    protected void endObjectDefinition() throws ObjectDescriptionException {
        this.currentClassDescription.setProperties(this.propertyList.toArray(new PropertyInfo[this.propertyList.size()]));
        this.currentClassDescription.setConstructorDescription(this.constructorList.toArray(new TypeInfo[this.constructorList.size()]));
        this.currentClassDescription.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        this.currentClassDescription.setSource(this.source);
        this.model.addClassDescription(this.currentClassDescription);
        this.propertyList = null;
        this.currentBeanInfo = null;
        this.currentClassDescription = null;
    }
    
    protected void handleAttributeDefinition(final String s, final String xmlName, final String xmlHandler) throws ObjectDescriptionException {
        final PropertyInfo simplePropertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo(this.getPropertyDescriptor(s));
        if (simplePropertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + s);
        }
        simplePropertyInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        simplePropertyInfo.setPropertyType(PropertyType.ATTRIBUTE);
        simplePropertyInfo.setXmlName(xmlName);
        simplePropertyInfo.setXmlHandler(xmlHandler);
        this.propertyList.add(simplePropertyInfo);
    }
    
    protected void handleConstructorDefinition(final String s, final String s2) throws ObjectDescriptionException {
        final Class loadClass = this.loadClass(s2);
        if (loadClass == null) {
            throw new ObjectDescriptionException("Failed to load class " + s2);
        }
        final TypeInfo typeInfo = new TypeInfo(s, loadClass);
        typeInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        this.constructorList.add(typeInfo);
    }
    
    protected void handleElementDefinition(final String s, final String xmlName) throws ObjectDescriptionException {
        final PropertyInfo simplePropertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo(this.getPropertyDescriptor(s));
        if (simplePropertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + s);
        }
        simplePropertyInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        simplePropertyInfo.setPropertyType(PropertyType.ELEMENT);
        simplePropertyInfo.setXmlName(xmlName);
        simplePropertyInfo.setXmlHandler(null);
        this.propertyList.add(simplePropertyInfo);
    }
    
    protected void handleLookupDefinition(final String s, final String xmlName) throws ObjectDescriptionException {
        final PropertyInfo simplePropertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo(this.getPropertyDescriptor(s));
        if (simplePropertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + s);
        }
        simplePropertyInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        simplePropertyInfo.setPropertyType(PropertyType.LOOKUP);
        simplePropertyInfo.setXmlName(xmlName);
        simplePropertyInfo.setXmlHandler(null);
        this.propertyList.add(simplePropertyInfo);
    }
    
    protected PropertyDescriptor getPropertyDescriptor(final String s) {
        final PropertyDescriptor[] propertyDescriptors = this.currentBeanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            if (propertyDescriptors[i].getName().equals(s)) {
                return propertyDescriptors[i];
            }
        }
        return null;
    }
    
    protected void handleIgnoredProperty(final String s) {
        final IgnoredPropertyInfo ignoredPropertyInfo = new IgnoredPropertyInfo(s);
        ignoredPropertyInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        this.propertyList.add(ignoredPropertyInfo);
    }
    
    protected boolean handleManualMapping(final String s, final String s2, final String s3) throws ObjectDescriptionException {
        final ManualMappingInfo manualMappingInfo = new ManualMappingInfo(this.loadClass(s), this.loadClass(s2), this.loadClass(s3));
        manualMappingInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        manualMappingInfo.setSource(this.source);
        this.model.getMappingModel().addManualMapping(manualMappingInfo);
        return true;
    }
    
    protected void startMultiplexMapping(final String s, final String s2) {
        (this.multiplexInfo = new MultiplexMappingInfo(this.loadClass(s), s2)).setSource(this.source);
        this.multiplexTypeInfos = new ArrayList();
    }
    
    protected void handleMultiplexMapping(final String s, final String s2) throws ObjectDescriptionException {
        final TypeInfo typeInfo = new TypeInfo(s, this.loadClass(s2));
        typeInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        this.multiplexTypeInfos.add(typeInfo);
    }
    
    protected void endMultiplexMapping() throws ObjectDescriptionException {
        final TypeInfo[] childClasses = this.multiplexTypeInfos.toArray(new TypeInfo[this.multiplexTypeInfos.size()]);
        this.multiplexInfo.setComments(new Comments(this.getOpenComment(), this.getCloseComment()));
        this.multiplexInfo.setChildClasses(childClasses);
        this.model.getMappingModel().addMultiplexMapping(this.multiplexInfo);
        this.multiplexInfo = null;
    }
    
    protected void startIncludeHandling(final URL url) {
        this.source = IOUtils.getInstance().createRelativeURL(url, this.baseURL);
        this.model.addSource(this.source);
        this.model.addIncludeComment(this.source, new Comments(this.getOpenComment(), this.getCloseComment()));
    }
    
    protected void endIncludeHandling() {
        this.source = "";
    }
    
    protected void startRootDocument() {
        this.source = "";
    }
    
    protected void endRootDocument() {
        this.model.setModelComments(new Comments(this.getOpenComment(), this.getCloseComment()));
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
