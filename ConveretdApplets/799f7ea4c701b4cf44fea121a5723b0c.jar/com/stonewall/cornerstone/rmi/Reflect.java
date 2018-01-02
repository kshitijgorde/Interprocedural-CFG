// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.util.List;

public class Reflect
{
    static Method locateMethod(final Class target, final String name, final List<Object> args) throws RMIException {
        final Class[] classes = new Class[args.size()];
        int cindex = 0;
        for (final Object o : args) {
            classes[cindex++] = o.getClass();
        }
        try {
            return target.getMethod(name, (Class[])classes);
        }
        catch (NoSuchMethodException nsme) {
            final Method[] methods = target.getMethods();
            Class[] newClasses = null;
            for (int i = 0; i < methods.length; ++i) {
                final Method method = methods[i];
                if (method.getName().equals(name)) {
                    final Class[] params = method.getParameterTypes();
                    newClasses = findParameters(classes, params);
                    if (newClasses != null) {
                        break;
                    }
                }
            }
            try {
                return target.getMethod(name, (Class[])newClasses);
            }
            catch (NoSuchMethodException nsme2) {
                final StringBuffer buf = new StringBuffer();
                for (int j = 0; j < classes.length; ++j) {
                    buf.append(classes[j].getName());
                    buf.append(",");
                }
                throw new RMIException("Cannot locate method: " + name + " for target: " + target + " args: " + buf.toString());
            }
        }
    }
    
    static Constructor locateConstructor(final Class target, final List<Object> args) throws RMIException {
        final Class[] classes = new Class[args.size()];
        int cindex = 0;
        for (final Object o : args) {
            classes[cindex++] = o.getClass();
        }
        try {
            return target.getConstructor((Class[])classes);
        }
        catch (NoSuchMethodException nsme) {
            final Constructor[] cons = target.getDeclaredConstructors();
            Class[] newClasses = null;
            for (int i = 0; i < cons.length; ++i) {
                final Constructor con = cons[i];
                final Class[] params = con.getParameterTypes();
                newClasses = findParameters(classes, params);
                if (newClasses != null) {
                    break;
                }
            }
            try {
                return target.getConstructor((Class[])newClasses);
            }
            catch (NoSuchMethodException nsme2) {
                final StringBuffer buf = new StringBuffer();
                for (int j = 0; j < classes.length; ++j) {
                    buf.append(classes[j].getName());
                    buf.append(",");
                }
                throw new RMIException("Cannot locate constructor for target: " + target + " args: " + buf.toString());
            }
        }
    }
    
    private static Class[] findParameters(final Class[] classes, final Class[] params) {
        final List<Class> found = new ArrayList<Class>();
        if (params.length != classes.length) {
            return null;
        }
        for (int j = 0; j < classes.length; ++j) {
            final Class c = classes[j];
            final Class newClass = locateClass(c, params[j]);
            if (newClass == null) {
                break;
            }
            found.add(newClass);
        }
        if (classes.length == found.size()) {
            final Class[] classArray = new Class[classes.length];
            return found.toArray(classArray);
        }
        return null;
    }
    
    private static Class locateClass(final Class c, final Class p) {
        for (Class newClass = c; newClass != null; newClass = newClass.getSuperclass()) {
            if (newClass.equals(p)) {
                return newClass;
            }
        }
        final Class[] intfs = c.getInterfaces();
        for (int i = 0; i < intfs.length; ++i) {
            if (intfs[i].equals(p)) {
                return intfs[i];
            }
        }
        if (c.equals(Boolean.class)) {
            return Boolean.TYPE;
        }
        if (c.equals(Integer.class)) {
            return Integer.TYPE;
        }
        return null;
    }
}
