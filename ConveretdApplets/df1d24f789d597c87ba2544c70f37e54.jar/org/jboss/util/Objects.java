// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.lang.ref.Reference;
import java.io.IOException;
import org.jboss.util.stream.Streams;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import org.jboss.util.coerce.CoercionHandler;
import java.lang.reflect.Constructor;

public final class Objects
{
    public static Constructor getCompatibleConstructor(final Class type, final Class valueType) {
        try {
            return type.getConstructor(valueType);
        }
        catch (Exception ignore) {
            final Class[] types = type.getClasses();
            int i = 0;
            while (i < types.length) {
                try {
                    return type.getConstructor(types[i]);
                }
                catch (Exception ignore2) {
                    ++i;
                    continue;
                }
                break;
            }
            return null;
        }
    }
    
    public static Object coerce(final Object value, final Class type) throws CoercionException {
        final Class valueType = value.getClass();
        if (type.isAssignableFrom(valueType)) {
            return value;
        }
        if (value instanceof Coercible) {
            return ((Coercible)value).coerce(type);
        }
        if (CoercionHandler.isInstalled(type)) {
            final CoercionHandler handler = CoercionHandler.create(type);
            return handler.coerce(value, type);
        }
        final Constructor c = getCompatibleConstructor(type, valueType);
        if (c != null) {
            try {
                return c.newInstance(value);
            }
            catch (InvocationTargetException e) {
                final Throwable t = e.getTargetException();
                if (t instanceof CoercionException) {
                    throw (CoercionException)t;
                }
                throw new CoercionException(t);
            }
            catch (Exception e2) {
                if (e2 instanceof CoercionException) {
                    throw (CoercionException)e2;
                }
                throw new CoercionException(e2);
            }
        }
        if (type.isPrimitive()) {
            return coerce(value, Classes.getPrimitiveWrapper(type));
        }
        throw new NotCoercibleException(value);
    }
    
    public static Object coerce(final Object[] values, Class type) throws CoercionException {
        if (!type.isArray()) {
            return coerce(values[0], type);
        }
        type = type.getComponentType();
        final Object array = Array.newInstance(type, values.length);
        for (int i = 0; i < values.length; ++i) {
            if (values[i] == null) {
                throw new IllegalArgumentException("values[" + i + "] is null");
            }
            final Object coerced = coerce(values[i], type);
            Array.set(array, i, coerced);
        }
        return array;
    }
    
    public static Object copy(final Serializable obj) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Object copy = null;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            out.writeObject(obj);
            out.flush();
            final byte[] data = baos.toByteArray();
            final ByteArrayInputStream bais = new ByteArrayInputStream(data);
            in = new ObjectInputStream(bais);
            copy = in.readObject();
        }
        finally {
            Streams.close((OutputStream)out);
            Streams.close((InputStream)in);
        }
        return copy;
    }
    
    public static Object deref(final Object obj) {
        if (obj != null && obj instanceof Reference) {
            final Reference ref = (Reference)obj;
            return ref.get();
        }
        return obj;
    }
    
    public static boolean isArray(final Object obj) {
        return obj != null && obj.getClass().isArray();
    }
    
    public static Object[] toArray(final Object obj) {
        if (obj instanceof Object[]) {
            return (Object[])obj;
        }
        final Class type = obj.getClass();
        Object array;
        if (type.isArray()) {
            final int length = Array.getLength(obj);
            final Class componentType = type.getComponentType();
            array = Array.newInstance(componentType, length);
            for (int i = 0; i < length; ++i) {
                Array.set(array, i, Array.get(obj, i));
            }
        }
        else {
            array = Array.newInstance(type, 1);
            Array.set(array, 0, obj);
        }
        return (Object[])array;
    }
    
    public static boolean equals(final Object[] a, final Object[] b, final boolean deep) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; ++i) {
            final Object x = a[i];
            final Object y = b[i];
            if (x != y) {
                return false;
            }
            if (x == null || y == null) {
                return false;
            }
            if (deep) {
                if (!(x instanceof Object[]) || !(y instanceof Object[])) {
                    return false;
                }
                if (!equals((Object[])x, (Object[])y, true)) {
                    return false;
                }
            }
            if (!x.equals(y)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equals(final Object[] a, final Object[] b) {
        return equals(a, b, true);
    }
}
