// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.AttributeList;
import java.lang.reflect.Proxy;
import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;
import javax.management.RuntimeOperationsException;
import javax.management.MBeanException;
import javax.management.InvalidAttributeValueException;
import javax.management.AttributeNotFoundException;
import javax.management.Attribute;
import javax.management.DynamicMBean;
import java.lang.reflect.Method;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.ReflectionException;
import javax.management.IntrospectionException;
import javax.management.InstanceNotFoundException;
import java.util.HashMap;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;

public class JMXInvocationHandler implements ProxyContext, InvocationHandler, Serializable
{
    private static final long serialVersionUID = 3714728148040623702L;
    protected MBeanServer server;
    protected ObjectName objectName;
    private ProxyExceptionHandler handler;
    private HashMap attributeMap;
    private boolean delegateToStringToResource;
    private boolean delegateEqualsToResource;
    private boolean delegateHashCodeToResource;
    
    public JMXInvocationHandler(final MBeanServer server, final ObjectName name) throws MBeanProxyCreationException {
        this.server = null;
        this.objectName = null;
        this.handler = new DefaultExceptionHandler();
        this.attributeMap = new HashMap();
        this.delegateToStringToResource = false;
        this.delegateEqualsToResource = false;
        this.delegateHashCodeToResource = false;
        try {
            if (server == null) {
                throw new MBeanProxyCreationException("null agent reference");
            }
            this.server = server;
            this.objectName = name;
            final MBeanInfo info = server.getMBeanInfo(this.objectName);
            final MBeanAttributeInfo[] attributes = info.getAttributes();
            final MBeanOperationInfo[] operations = info.getOperations();
            for (int i = 0; i < attributes.length; ++i) {
                this.attributeMap.put(attributes[i].getName(), attributes[i]);
            }
            for (int i = 0; i < operations.length; ++i) {
                if (operations[i].getName().equals("toString") && operations[i].getReturnType().equals("java.lang.String") && operations[i].getSignature().length == 0) {
                    this.delegateToStringToResource = true;
                }
                else if (operations[i].getName().equals("equals") && operations[i].getReturnType().equals(Boolean.TYPE.getName()) && operations[i].getSignature().length == 1 && operations[i].getSignature()[0].getType().equals("java.lang.Object")) {
                    this.delegateEqualsToResource = true;
                }
                else if (operations[i].getName().equals("hashCode") && operations[i].getReturnType().equals(Integer.TYPE.getName()) && operations[i].getSignature().length == 0) {
                    this.delegateHashCodeToResource = true;
                }
            }
        }
        catch (InstanceNotFoundException e) {
            throw new MBeanProxyCreationException("Object name " + name + " not found: " + e.toString());
        }
        catch (IntrospectionException e2) {
            throw new MBeanProxyCreationException(e2.toString());
        }
        catch (ReflectionException e3) {
            throw new MBeanProxyCreationException(e3.toString());
        }
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Exception {
        final Class declaringClass = method.getDeclaringClass();
        if (declaringClass == Object.class) {
            return this.handleObjectMethods(method, args);
        }
        if (declaringClass == ProxyContext.class) {
            return method.invoke(this, args);
        }
        if (declaringClass == DynamicMBean.class) {
            return this.handleDynamicMBeanInvocation(method, args);
        }
        try {
            final String methodName = method.getName();
            if (methodName.startsWith("get") && args == null) {
                final String attrName = methodName.substring(3, methodName.length());
                final MBeanAttributeInfo info = this.attributeMap.get(attrName);
                if (info != null) {
                    final String retType = method.getReturnType().getName();
                    if (retType.equals(info.getType())) {
                        return this.server.getAttribute(this.objectName, attrName);
                    }
                }
            }
            else if (methodName.startsWith("is") && args == null) {
                final String attrName = methodName.substring(2, methodName.length());
                final MBeanAttributeInfo info = this.attributeMap.get(attrName);
                if (info != null && info.isIs()) {
                    final Class retType2 = method.getReturnType();
                    if (retType2.equals(Boolean.class) || retType2.equals(Boolean.TYPE)) {
                        return this.server.getAttribute(this.objectName, attrName);
                    }
                }
            }
            else if (methodName.startsWith("set") && args != null && args.length == 1) {
                final String attrName = methodName.substring(3, methodName.length());
                final MBeanAttributeInfo info = this.attributeMap.get(attrName);
                if (info != null && method.getReturnType().equals(Void.TYPE)) {
                    final ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    Class signatureClass = null;
                    final String classType = info.getType();
                    if (this.isPrimitive(classType)) {
                        signatureClass = this.getPrimitiveClass(classType);
                    }
                    else {
                        signatureClass = cl.loadClass(info.getType());
                    }
                    if (signatureClass.isAssignableFrom(args[0].getClass())) {
                        this.server.setAttribute(this.objectName, new Attribute(attrName, args[0]));
                        return null;
                    }
                }
            }
            String[] signature = null;
            if (args != null) {
                signature = new String[args.length];
                final Class[] sign = method.getParameterTypes();
                for (int i = 0; i < sign.length; ++i) {
                    signature[i] = sign[i].getName();
                }
            }
            return this.server.invoke(this.objectName, methodName, args, signature);
        }
        catch (InstanceNotFoundException e) {
            return this.getExceptionHandler().handleInstanceNotFound(this, e, method, args);
        }
        catch (AttributeNotFoundException e2) {
            return this.getExceptionHandler().handleAttributeNotFound(this, e2, method, args);
        }
        catch (InvalidAttributeValueException e3) {
            return this.getExceptionHandler().handleInvalidAttributeValue(this, e3, method, args);
        }
        catch (MBeanException e4) {
            return this.getExceptionHandler().handleMBeanException(this, e4, method, args);
        }
        catch (ReflectionException e5) {
            return this.getExceptionHandler().handleReflectionException(this, e5, method, args);
        }
        catch (RuntimeOperationsException e6) {
            return this.getExceptionHandler().handleRuntimeOperationsException(this, e6, method, args);
        }
        catch (RuntimeMBeanException e7) {
            return this.getExceptionHandler().handleRuntimeMBeanException(this, e7, method, args);
        }
        catch (RuntimeErrorException e8) {
            return this.getExceptionHandler().handleRuntimeError(this, e8, method, args);
        }
    }
    
    public ProxyExceptionHandler getExceptionHandler() {
        return this.handler;
    }
    
    public void setExceptionHandler(final ProxyExceptionHandler handler) {
        this.handler = handler;
    }
    
    public MBeanServer getMBeanServer() {
        return this.server;
    }
    
    public ObjectName getObjectName() {
        return this.objectName;
    }
    
    public String toString() {
        return "MBeanProxy for " + this.objectName + " (Agent ID: " + AgentID.get(this.server) + ")";
    }
    
    private Object handleObjectMethods(final Method method, final Object[] args) throws InstanceNotFoundException, ReflectionException, IntrospectionException, MBeanException {
        if (method.getName().equals("toString")) {
            if (this.delegateToStringToResource) {
                return this.server.invoke(this.objectName, "toString", null, null);
            }
            return this.toString();
        }
        else if (method.getName().equals("equals")) {
            if (this.delegateEqualsToResource) {
                return this.server.invoke(this.objectName, "equals", new Object[] { args[0] }, new String[] { "java.lang.Object" });
            }
            if (Proxy.isProxyClass(args[0].getClass())) {
                final Proxy prxy = (Proxy)args[0];
                return new Boolean(this.equals(Proxy.getInvocationHandler(prxy)));
            }
            return new Boolean(this.equals(args[0]));
        }
        else {
            if (!method.getName().equals("hashCode")) {
                throw new Error("Unexpected method invocation!");
            }
            if (this.delegateHashCodeToResource) {
                return this.server.invoke(this.objectName, "hashCode", null, null);
            }
            return new Integer(this.hashCode());
        }
    }
    
    private Object handleDynamicMBeanInvocation(final Method method, final Object[] args) throws InstanceNotFoundException, ReflectionException, IntrospectionException, MBeanException, AttributeNotFoundException, InvalidAttributeValueException {
        final String methodName = method.getName();
        if (methodName.equals("setAttribute")) {
            this.server.setAttribute(this.objectName, (Attribute)args[0]);
            return null;
        }
        if (methodName.equals("setAttributes")) {
            return this.server.setAttributes(this.objectName, (AttributeList)args[0]);
        }
        if (methodName.equals("getAttribute")) {
            return this.server.getAttribute(this.objectName, (String)args[0]);
        }
        if (methodName.equals("getAttributes")) {
            return this.server.getAttributes(this.objectName, (String[])args[0]);
        }
        if (methodName.equals("invoke")) {
            return this.server.invoke(this.objectName, (String)args[0], (Object[])args[1], (String[])args[2]);
        }
        if (methodName.equals("getMBeanInfo")) {
            return this.server.getMBeanInfo(this.objectName);
        }
        throw new Error("Unexpected method invocation!");
    }
    
    private boolean isPrimitive(final String type) {
        return type.equals(Integer.TYPE.getName()) || type.equals(Long.TYPE.getName()) || type.equals(Boolean.TYPE.getName()) || type.equals(Byte.TYPE.getName()) || type.equals(Character.TYPE.getName()) || type.equals(Short.TYPE.getName()) || type.equals(Float.TYPE.getName()) || type.equals(Double.TYPE.getName()) || type.equals(Void.TYPE.getName());
    }
    
    private Class getPrimitiveClass(final String type) {
        if (type.equals(Integer.TYPE.getName())) {
            return Integer.TYPE;
        }
        if (type.equals(Long.TYPE.getName())) {
            return Long.TYPE;
        }
        if (type.equals(Boolean.TYPE.getName())) {
            return Boolean.TYPE;
        }
        if (type.equals(Byte.TYPE.getName())) {
            return Byte.TYPE;
        }
        if (type.equals(Character.TYPE.getName())) {
            return Character.TYPE;
        }
        if (type.equals(Short.TYPE.getName())) {
            return Short.TYPE;
        }
        if (type.equals(Float.TYPE.getName())) {
            return Float.TYPE;
        }
        if (type.equals(Double.TYPE.getName())) {
            return Double.TYPE;
        }
        if (type.equals(Void.TYPE.getName())) {
            return Void.TYPE;
        }
        return null;
    }
}
