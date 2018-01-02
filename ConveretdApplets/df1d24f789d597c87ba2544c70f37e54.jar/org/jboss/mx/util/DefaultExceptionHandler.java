// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;
import javax.management.RuntimeOperationsException;
import javax.management.ReflectionException;
import javax.management.MBeanException;
import javax.management.InvalidAttributeValueException;
import javax.management.AttributeNotFoundException;
import java.lang.reflect.Method;
import javax.management.InstanceNotFoundException;

public class DefaultExceptionHandler implements ProxyExceptionHandler
{
    public Object handleInstanceNotFound(final ProxyContext ctx, final InstanceNotFoundException e, final Method m, final Object[] args) throws Exception {
        throw new RuntimeProxyException("Instance not found: " + e.toString());
    }
    
    public Object handleAttributeNotFound(final ProxyContext ctx, final AttributeNotFoundException e, final Method m, final Object[] args) throws Exception {
        throw new RuntimeProxyException("Attribute not found: " + e.toString());
    }
    
    public Object handleInvalidAttributeValue(final ProxyContext ctx, final InvalidAttributeValueException e, final Method m, final Object[] args) throws Exception {
        throw new RuntimeProxyException("Invalid attribute value: " + e.toString());
    }
    
    public Object handleMBeanException(final ProxyContext ctx, final MBeanException e, final Method m, final Object[] args) throws Exception {
        throw e.getTargetException();
    }
    
    public Object handleReflectionException(final ProxyContext ctx, final ReflectionException e, final Method m, final Object[] args) throws Exception {
        final Exception target = e.getTargetException();
        if (target instanceof RuntimeException) {
            throw target;
        }
        throw new RuntimeProxyException(target.toString());
    }
    
    public Object handleRuntimeOperationsException(final ProxyContext ctx, final RuntimeOperationsException e, final Method m, final Object[] args) throws Exception {
        throw e.getTargetException();
    }
    
    public Object handleRuntimeMBeanException(final ProxyContext ctx, final RuntimeMBeanException e, final Method m, final Object[] args) throws Exception {
        throw e.getTargetException();
    }
    
    public Object handleRuntimeError(final ProxyContext ctx, final RuntimeErrorException e, final Method m, final Object[] args) throws Exception {
        throw e.getTargetError();
    }
}
