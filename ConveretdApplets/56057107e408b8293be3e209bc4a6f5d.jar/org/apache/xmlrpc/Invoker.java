// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

class Invoker implements XmlRpcHandler
{
    private Object invokeTarget;
    private Class targetClass;
    static /* synthetic */ Class class$java$lang$Object;
    
    public Invoker(final Object invokeTarget) {
        this.invokeTarget = invokeTarget;
        this.targetClass = ((this.invokeTarget instanceof Class) ? ((Class)this.invokeTarget) : this.invokeTarget.getClass());
        if (XmlRpc.debug) {
            System.err.println("Target object is " + this.targetClass);
        }
    }
    
    public Object execute(final String s, final Vector vector) throws Exception {
        Class[] array = null;
        Object[] array2 = null;
        if (vector != null) {
            array = new Class[vector.size()];
            array2 = new Object[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                array2[i] = vector.elementAt(i);
                if (array2[i] instanceof Integer) {
                    array[i] = Integer.TYPE;
                }
                else if (array2[i] instanceof Double) {
                    array[i] = Double.TYPE;
                }
                else if (array2[i] instanceof Boolean) {
                    array[i] = Boolean.TYPE;
                }
                else {
                    array[i] = array2[i].getClass();
                }
            }
        }
        if (XmlRpc.debug) {
            System.err.println("Searching for method: " + s);
            for (int j = 0; j < array.length; ++j) {
                System.err.println("Parameter " + j + ": " + array[j] + " = " + array2[j]);
            }
        }
        Method method;
        try {
            method = this.targetClass.getMethod(s, array);
        }
        catch (NoSuchMethodException ex) {
            throw ex;
        }
        catch (SecurityException ex2) {
            throw ex2;
        }
        if (method.getDeclaringClass() == ((Invoker.class$java$lang$Object == null) ? (Invoker.class$java$lang$Object = class$("java.lang.Object")) : Invoker.class$java$lang$Object)) {
            throw new XmlRpcException(0, "Invoker can't call methods defined in java.lang.Object");
        }
        Object invoke;
        try {
            invoke = method.invoke(this.invokeTarget, array2);
        }
        catch (IllegalAccessException ex3) {
            throw ex3;
        }
        catch (IllegalArgumentException ex4) {
            throw ex4;
        }
        catch (InvocationTargetException ex5) {
            if (XmlRpc.debug) {
                ex5.getTargetException().printStackTrace();
            }
            final Throwable targetException = ex5.getTargetException();
            if (targetException instanceof XmlRpcException) {
                throw (XmlRpcException)targetException;
            }
            throw new Exception(targetException.toString());
        }
        return invoke;
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
