// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.JMRuntimeException;
import javax.management.JMException;
import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;
import javax.management.RuntimeOperationsException;
import javax.management.ReflectionException;
import javax.management.MBeanException;

public class JMXExceptionDecoder
{
    public static Throwable decode(final Throwable t) {
        Throwable result = t;
        while (true) {
            if (result instanceof MBeanException) {
                result = ((MBeanException)result).getTargetException();
            }
            else if (result instanceof ReflectionException) {
                result = ((ReflectionException)result).getTargetException();
            }
            else if (result instanceof RuntimeOperationsException) {
                result = ((RuntimeOperationsException)result).getTargetException();
            }
            else if (result instanceof RuntimeMBeanException) {
                result = ((RuntimeMBeanException)result).getTargetException();
            }
            else {
                if (!(result instanceof RuntimeErrorException)) {
                    break;
                }
                result = ((RuntimeErrorException)result).getTargetError();
            }
        }
        return result;
    }
    
    public static Throwable decodeToJMXException(final Throwable ex) {
        Throwable jmxEx = ex;
        Throwable lastJmxEx = ex;
        while (jmxEx instanceof JMException || jmxEx instanceof JMRuntimeException) {
            lastJmxEx = jmxEx;
            jmxEx = decode(jmxEx);
            if (jmxEx == lastJmxEx) {
                break;
            }
        }
        return lastJmxEx;
    }
    
    public static void rethrow(final Exception e) throws Exception {
        final Throwable t = decode(e);
        if (t instanceof Exception) {
            throw (Exception)t;
        }
        throw (Error)t;
    }
}
