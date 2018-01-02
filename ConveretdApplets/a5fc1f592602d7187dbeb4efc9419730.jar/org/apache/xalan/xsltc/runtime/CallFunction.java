// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.Vector;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public final class CallFunction
{
    public static String className;
    public static String methodName;
    public static int nArgs;
    public static Class clazz;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    
    public static String invokeMethod(final String _className, final String _methodName, final Object[] _arguments) {
        CallFunction.className = _className;
        CallFunction.methodName = _methodName;
        final int size = _arguments.length - 1;
        final Object[] arguments = new Object[size];
        final Object object = _arguments[0];
        CallFunction.clazz = null;
        try {
            CallFunction.clazz = ObjectFactory.findProviderClass(CallFunction.className, ObjectFactory.findClassLoader(), true);
            if (CallFunction.clazz == null) {
                throw new RuntimeException("Couldn't load the class");
            }
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load the class");
        }
        for (int i = 0, j = 1; i < size; ++i, ++j) {
            arguments[i] = _arguments[j];
        }
        CallFunction.nArgs = size;
        if (CallFunction.methodName != null) {
            final Method method;
            if ((method = findMethods(arguments)) == null) {
                throw new RuntimeException("Method not found");
            }
            try {
                final Object obj = method.invoke(object, arguments);
                return obj.toString();
            }
            catch (IllegalAccessException e2) {
                throw new RuntimeException("Error: Method is inaccessible");
            }
            catch (IllegalArgumentException e3) {
                throw new RuntimeException("Error: Number of actual and formal argument differ ");
            }
            catch (InvocationTargetException e4) {
                throw new RuntimeException("Error: underlying constructor throws an exception ");
            }
        }
        final Constructor constructor;
        if ((constructor = findConstructor(arguments)) == null) {
            throw new RuntimeException("Constructor not found");
        }
        try {
            final Object obs = constructor.newInstance(arguments);
            return obs.toString();
        }
        catch (InvocationTargetException e5) {
            throw new RuntimeException("Error: constructor throws an exception ");
        }
        catch (IllegalAccessException e6) {
            throw new RuntimeException("Error: constructor is inaccessible");
        }
        catch (IllegalArgumentException e7) {
            throw new RuntimeException("Error: Number of actual and formal argument differ ");
        }
        catch (InstantiationException e8) {
            throw new RuntimeException("Error: Class that declares the underlying constructor represents an abstract class");
        }
    }
    
    private static Constructor findConstructor(final Object[] arguments) {
        Vector constructors = null;
        final Constructor[] c_constructors = CallFunction.clazz.getConstructors();
        for (int i = 0; i < c_constructors.length; ++i) {
            final int mods = c_constructors[i].getModifiers();
            if (Modifier.isPublic(mods) && c_constructors[i].getParameterTypes().length == CallFunction.nArgs) {
                if (constructors == null) {
                    constructors = new Vector();
                }
                constructors.addElement(c_constructors[i]);
            }
        }
        if (constructors == null) {
            throw new RuntimeException("CONSTRUCTOR_NOT_FOUND_ERR" + CallFunction.className + ":" + CallFunction.methodName);
        }
        final int nConstructors = constructors.size();
        boolean accept = false;
        for (int j = 0; j < nConstructors; ++j) {
            final Constructor constructor = constructors.elementAt(j);
            final Class[] paramTypes = constructor.getParameterTypes();
            for (int k = 0; k < CallFunction.nArgs; ++k) {
                final Class argumentClass = arguments[k].getClass();
                if (argumentClass == paramTypes[k]) {
                    accept = true;
                }
                else {
                    if (!argumentClass.isAssignableFrom(paramTypes[k])) {
                        accept = false;
                        break;
                    }
                    accept = true;
                }
            }
            if (accept) {
                return constructor;
            }
        }
        return null;
    }
    
    private static Method findMethods(final Object[] arguments) {
        Vector methods = null;
        final Method[] m_methods = CallFunction.clazz.getMethods();
        for (int i = 0; i < m_methods.length; ++i) {
            final int mods = m_methods[i].getModifiers();
            if (Modifier.isPublic(mods) && m_methods[i].getName().equals(CallFunction.methodName) && m_methods[i].getParameterTypes().length == CallFunction.nArgs) {
                if (methods == null) {
                    methods = new Vector();
                }
                methods.addElement(m_methods[i]);
            }
        }
        if (methods == null) {
            throw new RuntimeException("METHOD_NOT_FOUND_ERR" + CallFunction.className + ":" + CallFunction.methodName);
        }
        final int nMethods = methods.size();
        boolean accept = false;
        for (int j = 0; j < nMethods; ++j) {
            final Method method = methods.elementAt(j);
            final Class[] paramTypes = method.getParameterTypes();
            for (int k = 0; k < CallFunction.nArgs; ++k) {
                final Class argumentClass = arguments[k].getClass();
                if (argumentClass == paramTypes[k]) {
                    accept = true;
                }
                else if (argumentClass.isAssignableFrom(paramTypes[k])) {
                    accept = true;
                }
                else {
                    if (!paramTypes[k].isPrimitive()) {
                        accept = false;
                        break;
                    }
                    arguments[k] = isPrimitive(paramTypes[k], arguments[k]);
                    accept = true;
                }
            }
            if (accept) {
                return method;
            }
        }
        return null;
    }
    
    public static Object isPrimitive(final Class paramType, final Object argument) {
        if (argument.getClass() == ((CallFunction.class$java$lang$Integer == null) ? (CallFunction.class$java$lang$Integer = class$("java.lang.Integer")) : CallFunction.class$java$lang$Integer)) {
            return typeCast(paramType, (Integer)argument);
        }
        if (argument.getClass() == ((CallFunction.class$java$lang$Float == null) ? (CallFunction.class$java$lang$Float = class$("java.lang.Float")) : CallFunction.class$java$lang$Float)) {
            return typeCast(paramType, (Float)argument);
        }
        if (argument.getClass() == ((CallFunction.class$java$lang$Double == null) ? (CallFunction.class$java$lang$Double = class$("java.lang.Double")) : CallFunction.class$java$lang$Double)) {
            return typeCast(paramType, (Double)argument);
        }
        if (argument.getClass() == ((CallFunction.class$java$lang$Long == null) ? (CallFunction.class$java$lang$Long = class$("java.lang.Long")) : CallFunction.class$java$lang$Long)) {
            return typeCast(paramType, (Long)argument);
        }
        if (argument.getClass() == ((CallFunction.class$java$lang$Boolean == null) ? (CallFunction.class$java$lang$Boolean = class$("java.lang.Boolean")) : CallFunction.class$java$lang$Boolean)) {
            return argument;
        }
        if (argument.getClass() == ((CallFunction.class$java$lang$Byte == null) ? (CallFunction.class$java$lang$Byte = class$("java.lang.Byte")) : CallFunction.class$java$lang$Byte)) {
            return argument;
        }
        return null;
    }
    
    static Object typeCast(final Class paramType, final Double object) {
        if (paramType == Long.TYPE) {
            return new Long((long)(Object)object);
        }
        if (paramType == Integer.TYPE) {
            return new Integer((int)(Object)object);
        }
        if (paramType == Float.TYPE) {
            return new Float((float)(Object)object);
        }
        if (paramType == Short.TYPE) {
            return new Short((short)(Object)object);
        }
        if (paramType == Byte.TYPE) {
            return new Byte((byte)(Object)object);
        }
        return object;
    }
    
    static Object typeCast(final Class paramType, final Long object) {
        if (paramType == Integer.TYPE) {
            return new Integer((int)(Object)object);
        }
        if (paramType == Float.TYPE) {
            return new Float(object);
        }
        if (paramType == Short.TYPE) {
            return new Short((short)(Object)object);
        }
        if (paramType == Byte.TYPE) {
            return new Byte((byte)(Object)object);
        }
        return object;
    }
    
    static Object typeCast(final Class paramType, final Integer object) {
        if (paramType == Double.TYPE) {
            return new Double(object);
        }
        if (paramType == Float.TYPE) {
            return new Float(object);
        }
        if (paramType == Short.TYPE) {
            return new Short((short)(Object)object);
        }
        if (paramType == Byte.TYPE) {
            return new Byte((byte)(Object)object);
        }
        return object;
    }
    
    static Object typeCast(final Class paramType, final Float object) {
        if (paramType == Double.TYPE) {
            return new Double(object);
        }
        if (paramType == Integer.TYPE) {
            return new Float((int)(Object)object);
        }
        if (paramType == Short.TYPE) {
            return new Short((short)(Object)object);
        }
        if (paramType == Byte.TYPE) {
            return new Byte((byte)(Object)object);
        }
        return object;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
