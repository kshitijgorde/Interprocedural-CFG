// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.capability;

import javax.management.MBeanOperationInfo;
import javax.management.Descriptor;
import java.lang.reflect.Method;
import javax.management.MBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import org.jboss.mx.util.PropertyAccess;
import org.jboss.mx.metadata.MethodMapper;
import javax.management.IntrospectionException;
import org.jboss.mx.metadata.AttributeOperationResolver;
import javax.management.DynamicMBean;
import javax.management.MBeanInfo;
import org.jboss.mx.server.ServerConstants;

public class DispatcherFactory implements ServerConstants
{
    public static DynamicMBean create(final MBeanInfo info, final Object resource) throws IntrospectionException {
        return create(info, resource, new AttributeOperationResolver(info));
    }
    
    public static DynamicMBean create(final MBeanInfo info, final Object resource, final AttributeOperationResolver resolver) throws IntrospectionException {
        if (null == info) {
            throw new IllegalArgumentException("info cannot be null");
        }
        if (null == resolver) {
            throw new IllegalArgumentException("resolver cannot be null");
        }
        if (null == resource) {
            throw new IllegalArgumentException("resource cannot be null");
        }
        final MethodMapper mmap = new MethodMapper((Class)resource.getClass());
        ReflectedMBeanDispatcher dispatcher = new ReflectedMBeanDispatcher(info, resolver, resource);
        final String flag = PropertyAccess.getProperty("jbossmx.optimized.dispatcher", "false");
        if (flag.equalsIgnoreCase("true")) {
            dispatcher = OptimizedMBeanDispatcher.create(info, resource);
        }
        final MBeanAttributeInfo[] attributes = info.getAttributes();
        for (int i = 0; i < attributes.length; ++i) {
            final MBeanAttributeInfo attribute = attributes[i];
            Method getter = null;
            Method setter = null;
            if (attribute.isReadable()) {
                if (attribute instanceof ModelMBeanAttributeInfo) {
                    final ModelMBeanAttributeInfo mmbAttribute = (ModelMBeanAttributeInfo)attribute;
                    final Descriptor desc = mmbAttribute.getDescriptor();
                    if (desc != null && desc.getFieldValue("getMethod") != null) {
                        getter = mmap.lookupGetter(mmbAttribute);
                        if (getter == null) {
                            throw new IntrospectionException("no getter method found for attribute: " + attribute.getName());
                        }
                    }
                }
                else {
                    getter = mmap.lookupGetter(attribute);
                    if (getter == null) {
                        throw new IntrospectionException("no getter method found for attribute: " + attribute.getName());
                    }
                }
            }
            if (attribute.isWritable()) {
                if (attribute instanceof ModelMBeanAttributeInfo) {
                    final ModelMBeanAttributeInfo mmbAttribute = (ModelMBeanAttributeInfo)attribute;
                    final Descriptor desc = mmbAttribute.getDescriptor();
                    if (desc != null && desc.getFieldValue("setMethod") != null) {
                        setter = mmap.lookupSetter(mmbAttribute);
                        if (setter == null) {
                            throw new IntrospectionException("no setter method found for attribute: " + attribute.getName());
                        }
                    }
                }
                else {
                    setter = mmap.lookupSetter(attribute);
                    if (setter == null) {
                        throw new IntrospectionException("no setter method found for attribute: " + attribute.getName());
                    }
                }
            }
            dispatcher.bindAttributeAt(i, getter, setter);
        }
        final MBeanOperationInfo[] operations = info.getOperations();
        for (int j = 0; j < operations.length; ++j) {
            final MBeanOperationInfo operation = operations[j];
            final Method method = mmap.lookupOperation(operation);
            if (method == null) {
                throw new IntrospectionException("no method found for operation: " + operation.getName());
            }
            dispatcher.bindOperationAt(j, method);
        }
        return dispatcher;
    }
}
