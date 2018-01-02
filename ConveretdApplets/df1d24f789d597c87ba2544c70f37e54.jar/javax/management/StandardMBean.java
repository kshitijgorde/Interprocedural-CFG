// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.metadata.StandardMetaData;
import org.jboss.mx.loading.LoaderRepository;
import java.util.Iterator;
import java.lang.reflect.Method;
import org.jboss.mx.server.ExceptionHandler;
import org.jboss.logging.Logger;

public class StandardMBean implements DynamicMBean
{
    private static final Logger log;
    private Object implementation;
    private Class mbeanInterface;
    private MBeanInfo cachedMBeanInfo;
    
    public StandardMBean(final Object implementation, final Class mbeanInterface) throws NotCompliantMBeanException {
        this.implementation = implementation;
        this.mbeanInterface = mbeanInterface;
        final MBeanInfo info = this.buildMBeanInfo(implementation, mbeanInterface);
        this.cacheMBeanInfo(info);
    }
    
    protected StandardMBean(final Class mbeanInterface) throws NotCompliantMBeanException {
        this.implementation = this;
        this.mbeanInterface = mbeanInterface;
        final MBeanInfo info = this.buildMBeanInfo(this.implementation, mbeanInterface);
        this.cacheMBeanInfo(info);
    }
    
    public Object getImplementation() {
        return this.implementation;
    }
    
    public void setImplementation(final Object implementation) throws NotCompliantMBeanException {
        if (implementation == null) {
            throw new IllegalArgumentException("Null implementation");
        }
        this.implementation = implementation;
    }
    
    public Class getImplementationClass() {
        return this.implementation.getClass();
    }
    
    public final Class getMBeanInterface() {
        return this.mbeanInterface;
    }
    
    public Object getAttribute(final String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        try {
            final Method method = this.implementation.getClass().getMethod("get" + attribute, (Class<?>[])null);
            return method.invoke(this.implementation, new Object[0]);
        }
        catch (Exception e) {
            final JMException result = ExceptionHandler.handleException((Throwable)e);
            if (result instanceof AttributeNotFoundException) {
                throw (AttributeNotFoundException)result;
            }
            if (result instanceof MBeanException) {
                throw (MBeanException)result;
            }
            if (result instanceof ReflectionException) {
                throw (ReflectionException)result;
            }
            throw new MBeanException(e, "Cannot get attribute: " + attribute);
        }
    }
    
    public void setAttribute(final Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        try {
            Class[] clArr = null;
            if (attribute.getValue() != null) {
                clArr = new Class[] { attribute.getValue().getClass() };
            }
            final Method method = this.implementation.getClass().getMethod("set" + attribute.getName(), (Class<?>[])clArr);
            method.invoke(this.implementation, attribute.getValue());
        }
        catch (Exception e) {
            final JMException result = ExceptionHandler.handleException((Throwable)e);
            if (result instanceof AttributeNotFoundException) {
                throw (AttributeNotFoundException)result;
            }
            if (result instanceof InvalidAttributeValueException) {
                throw (InvalidAttributeValueException)result;
            }
            if (result instanceof MBeanException) {
                throw (MBeanException)result;
            }
            if (result instanceof ReflectionException) {
                throw (ReflectionException)result;
            }
            throw new MBeanException(e, "Cannot set attribute: " + attribute);
        }
    }
    
    public AttributeList getAttributes(final String[] attributes) {
        try {
            final AttributeList attrList = new AttributeList(attributes.length);
            for (int i = 0; i < attributes.length; ++i) {
                final String name = attributes[i];
                final Object value = this.getAttribute(name);
                attrList.add(new Attribute(name, value));
            }
            return attrList;
        }
        catch (Exception e) {
            final JMException result = ExceptionHandler.handleException((Throwable)e);
            throw new RuntimeException("Cannot get attributes", result);
        }
    }
    
    public AttributeList setAttributes(final AttributeList attributes) {
        try {
            final AttributeList attrList = new AttributeList(attributes.size());
            for (final Attribute attr : attributes) {
                this.setAttribute(attr);
                final String name = attr.getName();
                final Object value = this.getAttribute(name);
                attrList.add(new Attribute(name, value));
            }
            return attrList;
        }
        catch (Exception e) {
            final JMException result = ExceptionHandler.handleException((Throwable)e);
            throw new RuntimeException("Cannot set attributes", result);
        }
    }
    
    public Object invoke(final String actionName, final Object[] params, final String[] signature) throws MBeanException, ReflectionException {
        try {
            final Class[] sigcl = new Class[signature.length];
            for (int i = 0; i < signature.length; ++i) {
                sigcl[i] = this.loadClass(signature[i]);
            }
            final Method method = this.implementation.getClass().getMethod(actionName, (Class<?>[])sigcl);
            return method.invoke(this.implementation, params);
        }
        catch (Exception e) {
            final JMException result = ExceptionHandler.handleException((Throwable)e);
            if (result instanceof MBeanException) {
                throw (MBeanException)result;
            }
            if (result instanceof ReflectionException) {
                throw (ReflectionException)result;
            }
            throw new MBeanException(e, "Cannot invoke: " + actionName);
        }
    }
    
    private Class loadClass(final String className) throws ClassNotFoundException {
        Class clazz = LoaderRepository.getNativeClassForName(className);
        if (clazz == null) {
            final ClassLoader cl = this.getClass().getClassLoader();
            clazz = cl.loadClass(className);
        }
        return clazz;
    }
    
    public MBeanInfo getMBeanInfo() {
        MBeanInfo info = this.getCachedMBeanInfo();
        if (info == null) {
            try {
                info = this.buildMBeanInfo(this.implementation, this.mbeanInterface);
                this.cacheMBeanInfo(info);
            }
            catch (NotCompliantMBeanException e) {
                StandardMBean.log.error("Unexcepted exception", e);
                throw new IllegalStateException("Unexcepted exception " + e.toString());
            }
        }
        return info;
    }
    
    protected String getClassName(final MBeanInfo info) {
        return info.getClassName();
    }
    
    protected String getDescription(final MBeanInfo info) {
        return info.getDescription();
    }
    
    protected String getDescription(final MBeanFeatureInfo info) {
        return info.getDescription();
    }
    
    protected String getDescription(final MBeanAttributeInfo info) {
        return this.getDescription((MBeanFeatureInfo)info);
    }
    
    protected String getDescription(final MBeanConstructorInfo info) {
        return this.getDescription((MBeanFeatureInfo)info);
    }
    
    protected String getDescription(final MBeanOperationInfo info) {
        return this.getDescription((MBeanFeatureInfo)info);
    }
    
    protected String getDescription(final MBeanConstructorInfo info, final MBeanParameterInfo param, final int sequence) {
        return param.getDescription();
    }
    
    protected String getDescription(final MBeanOperationInfo info, final MBeanParameterInfo param, final int sequence) {
        return param.getDescription();
    }
    
    protected String getParameterName(final MBeanConstructorInfo info, final MBeanParameterInfo param, final int sequence) {
        return param.getName();
    }
    
    protected String getParameterName(final MBeanOperationInfo info, final MBeanParameterInfo param, final int sequence) {
        return param.getName();
    }
    
    protected int getImpact(final MBeanOperationInfo info) {
        return info.getImpact();
    }
    
    protected MBeanConstructorInfo[] getConstructors(final MBeanConstructorInfo[] constructors, final Object implementation) {
        if (implementation == this) {
            return constructors;
        }
        return null;
    }
    
    protected MBeanInfo getCachedMBeanInfo() {
        return this.cachedMBeanInfo;
    }
    
    protected void cacheMBeanInfo(final MBeanInfo info) {
        this.cachedMBeanInfo = info;
    }
    
    private final MBeanInfo buildMBeanInfo(final Object implementation, final Class mbeanInterface) throws NotCompliantMBeanException {
        if (implementation == null) {
            throw new IllegalArgumentException("Null implementation");
        }
        final StandardMetaData metaData = new StandardMetaData(implementation, mbeanInterface);
        this.mbeanInterface = metaData.getMBeanInterface();
        MBeanInfo info = metaData.build();
        final String className = this.getClassName(info);
        final String mainDescription = this.getDescription(info);
        final MBeanAttributeInfo[] attributes = info.getAttributes();
        MBeanConstructorInfo[] constructors = info.getConstructors();
        final MBeanOperationInfo[] operations = info.getOperations();
        final MBeanNotificationInfo[] notifications = info.getNotifications();
        for (int i = 0; i < attributes.length; ++i) {
            final MBeanAttributeInfo attribute = attributes[i];
            final String description = this.getDescription(attribute);
            attributes[i] = new MBeanAttributeInfo(attribute.getName(), attribute.getType(), description, attribute.isReadable(), attribute.isWritable(), attribute.isIs());
        }
        if (implementation == this) {
            constructors = this.getConstructors(constructors, this);
            for (int i = 0; i < constructors.length; ++i) {
                final MBeanConstructorInfo constructor = constructors[i];
                final MBeanParameterInfo[] parameters = constructor.getSignature();
                for (int j = 0; j < parameters.length; ++j) {
                    final MBeanParameterInfo param = parameters[j];
                    final String description2 = this.getDescription(constructor, param, j);
                    final String name = this.getParameterName(constructor, param, j);
                    parameters[j] = new MBeanParameterInfo(name, param.getType(), description2);
                }
                final String description3 = this.getDescription(constructor);
                constructors[i] = new MBeanConstructorInfo(constructor.getName(), description3, parameters);
            }
        }
        else {
            constructors = new MBeanConstructorInfo[0];
        }
        for (int i = 0; i < operations.length; ++i) {
            final MBeanOperationInfo operation = operations[i];
            final MBeanParameterInfo[] parameters = operation.getSignature();
            for (int j = 0; j < parameters.length; ++j) {
                final MBeanParameterInfo param = parameters[j];
                final String description2 = this.getDescription(operation, param, j);
                final String name = this.getParameterName(operation, param, j);
                parameters[j] = new MBeanParameterInfo(name, param.getType(), description2);
            }
            final String description3 = this.getDescription(operation);
            final int impact = this.getImpact(operation);
            operations[i] = new MBeanOperationInfo(operation.getName(), description3, parameters, operation.getReturnType(), impact);
        }
        info = new MBeanInfo(className, mainDescription, attributes, constructors, operations, notifications);
        return info;
    }
    
    static {
        log = Logger.getLogger(StandardMBean.class);
    }
}
