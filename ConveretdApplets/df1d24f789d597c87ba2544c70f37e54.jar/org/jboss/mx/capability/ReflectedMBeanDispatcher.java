// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.capability;

import javax.management.MBeanNotificationInfo;
import java.util.Iterator;
import javax.management.AttributeList;
import javax.management.InvalidAttributeValueException;
import javax.management.Attribute;
import javax.management.ReflectionException;
import java.lang.reflect.InvocationTargetException;
import javax.management.RuntimeErrorException;
import javax.management.MBeanException;
import javax.management.RuntimeMBeanException;
import javax.management.AttributeNotFoundException;
import javax.management.RuntimeOperationsException;
import javax.management.NotificationBroadcaster;
import javax.management.MBeanInfo;
import java.lang.reflect.Method;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import org.jboss.mx.metadata.AttributeOperationResolver;
import javax.management.DynamicMBean;

public class ReflectedMBeanDispatcher implements DynamicMBean
{
    private Object resource;
    private AttributeOperationResolver resolver;
    private MBeanConstructorInfo[] constructorInfo;
    private MBeanAttributeInfo[] attributeInfo;
    private MBeanOperationInfo[] operationInfo;
    private Method[] operations;
    private MethodPair[] attributes;
    private boolean isBroadcaster;
    private String resourceClassName;
    private String resourceDescription;
    
    public ReflectedMBeanDispatcher(final MBeanInfo mbinfo, final AttributeOperationResolver resolver, final Object resource) {
        this.resource = null;
        this.resolver = null;
        this.constructorInfo = null;
        this.attributeInfo = null;
        this.operationInfo = null;
        this.operations = null;
        this.attributes = null;
        this.isBroadcaster = false;
        this.resourceClassName = null;
        this.resourceDescription = null;
        if (null == resource) {
            throw new IllegalArgumentException("resource cannot be null");
        }
        if (null == mbinfo) {
            throw new IllegalArgumentException("MBeanInfo cannot be null");
        }
        if (null == resolver) {
            throw new IllegalArgumentException("AOresolver cannot be null");
        }
        if (resource instanceof NotificationBroadcaster) {
            this.isBroadcaster = true;
        }
        this.resource = resource;
        this.resolver = resolver;
        this.resourceClassName = mbinfo.getClassName();
        this.resourceDescription = mbinfo.getDescription();
        this.constructorInfo = mbinfo.getConstructors();
        this.attributeInfo = mbinfo.getAttributes();
        this.operationInfo = mbinfo.getOperations();
        this.operations = new Method[this.operationInfo.length];
        this.attributes = new MethodPair[this.attributeInfo.length];
    }
    
    public void bindOperationAt(final int position, final Method method) {
        try {
            this.operations[position] = method;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("position out of bounds: " + position);
        }
    }
    
    public void bindAttributeAt(final int position, final Method getter, final Method setter) {
        try {
            this.attributes[position] = new MethodPair(getter, setter);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("position out of bounds: " + position);
        }
    }
    
    public String getResourceClassName() {
        return this.resourceClassName;
    }
    
    public Object getAttribute(final String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (null == attribute) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attribute cannot be null"));
        }
        Method m = null;
        try {
            m = this.attributes[this.resolver.lookup(attribute)].getter;
            return m.invoke(this.resource, new Object[0]);
        }
        catch (NullPointerException e4) {
            throw new AttributeNotFoundException("Readable attribute '" + attribute + "' not found");
        }
        catch (ArrayIndexOutOfBoundsException e5) {
            throw new AttributeNotFoundException("Readable attribute '" + attribute + "' not found");
        }
        catch (InvocationTargetException e) {
            final Throwable t = e.getTargetException();
            if (t instanceof RuntimeException) {
                throw new RuntimeMBeanException((RuntimeException)t, "RuntimeException in MBean when getting attribute '" + attribute + "'");
            }
            if (t instanceof Exception) {
                throw new MBeanException((Exception)t, "Exception in MBean when getting attribute '" + attribute + "'");
            }
            throw new RuntimeErrorException((Error)t, "Error in MBean when getting attribute '" + attribute + "'");
        }
        catch (IllegalArgumentException e6) {
            throw new AttributeNotFoundException("Readable attribute '" + attribute + "' not found");
        }
        catch (Exception e2) {
            throw new ReflectionException(e2, "Exception in AttributeProvider for getting '" + attribute + "'");
        }
        catch (Error e3) {
            throw new RuntimeErrorException(e3, "Error in AttributeProvider for getting '" + attribute + "'");
        }
    }
    
    public void setAttribute(final Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        if (null == attribute) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attribute cannot be null"));
        }
        Method m = null;
        try {
            m = this.attributes[this.resolver.lookup(attribute.getName())].setter;
            m.invoke(this.resource, attribute.getValue());
        }
        catch (NullPointerException e4) {
            throw new AttributeNotFoundException("Writable attribute '" + attribute.getName() + "' not found");
        }
        catch (ArrayIndexOutOfBoundsException e5) {
            throw new AttributeNotFoundException("Writable attribute '" + attribute.getName() + "' not found");
        }
        catch (InvocationTargetException e) {
            final Throwable t = e.getTargetException();
            if (t instanceof RuntimeException) {
                throw new RuntimeMBeanException((RuntimeException)t, "RuntimeException in MBean when setting attribute '" + attribute.getName() + "'");
            }
            if (t instanceof Exception) {
                throw new MBeanException((Exception)t, "Exception in MBean when setting attribute '" + attribute.getName() + "'");
            }
            throw new RuntimeErrorException((Error)t, "Error in MBean when setting attribute '" + attribute.getName() + "'");
        }
        catch (IllegalArgumentException e6) {
            final String valueType = (null == attribute.getValue()) ? "<null value>" : attribute.getValue().getClass().getName();
            throw new InvalidAttributeValueException("Attribute value mismatch while setting '" + attribute.getName() + "': " + valueType);
        }
        catch (Exception e2) {
            throw new ReflectionException(e2, "Exception in AttributeProvider for setting '" + attribute.getName() + "'");
        }
        catch (Error e3) {
            throw new RuntimeErrorException(e3, "Error in AttributeProvider for setting '" + attribute.getName() + "'");
        }
    }
    
    public AttributeList getAttributes(final String[] attributes) {
        if (null == attributes) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attributes array cannot be null"));
        }
        final AttributeList list = new AttributeList();
        for (int i = 0; i < attributes.length; ++i) {
            try {
                list.add(new Attribute(attributes[i], this.getAttribute(attributes[i])));
            }
            catch (Throwable t) {}
        }
        return list;
    }
    
    public AttributeList setAttributes(final AttributeList attributes) {
        if (null == attributes) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attribute list cannot be null"));
        }
        final AttributeList list = new AttributeList();
        for (final Attribute toSet : attributes) {
            try {
                this.setAttribute(toSet);
                list.add(toSet);
            }
            catch (Throwable t) {}
        }
        return list;
    }
    
    public Object invoke(final String actionName, final Object[] params, final String[] signature) throws MBeanException, ReflectionException {
        Method m = null;
        try {
            m = this.operations[this.resolver.lookup(actionName, signature)];
            return m.invoke(this.resource, params);
        }
        catch (NullPointerException e4) {
            throw new ReflectionException(new NoSuchMethodException("Unable to locate MBean operation for: " + this.opKeyString(actionName, signature)));
        }
        catch (ArrayIndexOutOfBoundsException e5) {
            throw new ReflectionException(new NoSuchMethodException("Unable to locate MBean operation for: " + this.opKeyString(actionName, signature)));
        }
        catch (InvocationTargetException e) {
            final Throwable t = e.getTargetException();
            if (t instanceof RuntimeException) {
                throw new RuntimeMBeanException((RuntimeException)t, "RuntimeException in MBean operation '" + this.opKeyString(actionName, signature) + "'");
            }
            if (t instanceof Exception) {
                throw new MBeanException((Exception)t, "Exception in MBean operation '" + this.opKeyString(actionName, signature) + "'");
            }
            throw new RuntimeErrorException((Error)t, "Error in MBean operation '" + this.opKeyString(actionName, signature) + "'");
        }
        catch (Exception e2) {
            throw new ReflectionException(e2, "Exception when calling method for '" + this.opKeyString(actionName, signature) + "'");
        }
        catch (Error e3) {
            throw new RuntimeErrorException(e3, "Error when calling method for '" + this.opKeyString(actionName, signature) + "'");
        }
    }
    
    public MBeanInfo getMBeanInfo() {
        return new MBeanInfo(this.resourceClassName, this.resourceDescription, this.attributeInfo, this.constructorInfo, this.operationInfo, this.isBroadcaster ? this.getNotificationInfo() : new MBeanNotificationInfo[0]);
    }
    
    protected MBeanNotificationInfo[] getNotificationInfo() {
        if (this.isBroadcaster) {
            return ((NotificationBroadcaster)this.resource).getNotificationInfo();
        }
        throw new RuntimeOperationsException(new UnsupportedOperationException("resource is not a NotificationBroadcaster"));
    }
    
    protected Object getResourceObject() {
        return this.resource;
    }
    
    protected final String opKeyString(final String name, final String[] signature) {
        final StringBuffer buf = new StringBuffer(name).append('(');
        if (null != signature) {
            for (int i = 0; i < signature.length - 1; ++i) {
                buf.append(signature[i]).append(',');
            }
            if (signature.length > 0) {
                buf.append(signature[signature.length - 1]);
            }
        }
        return buf.append(')').toString();
    }
    
    public static class MethodPair
    {
        public Method getter;
        public Method setter;
        
        public MethodPair(final Method getter, final Method setter) {
            this.getter = null;
            this.setter = null;
            this.getter = getter;
            this.setter = setter;
        }
    }
}
