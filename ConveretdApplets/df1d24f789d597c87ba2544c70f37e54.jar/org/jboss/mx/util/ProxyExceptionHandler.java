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

public interface ProxyExceptionHandler
{
    Object handleInstanceNotFound(final ProxyContext p0, final InstanceNotFoundException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleAttributeNotFound(final ProxyContext p0, final AttributeNotFoundException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleInvalidAttributeValue(final ProxyContext p0, final InvalidAttributeValueException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleMBeanException(final ProxyContext p0, final MBeanException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleReflectionException(final ProxyContext p0, final ReflectionException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleRuntimeOperationsException(final ProxyContext p0, final RuntimeOperationsException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleRuntimeMBeanException(final ProxyContext p0, final RuntimeMBeanException p1, final Method p2, final Object[] p3) throws Exception;
    
    Object handleRuntimeError(final ProxyContext p0, final RuntimeErrorException p1, final Method p2, final Object[] p3) throws Exception;
}
