// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.WeakHashMap;
import java.util.HashMap;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class MethodHashing
{
    static Map hashMap;
    
    public static Method findMethodByHash(final Class clazz, final long hash) throws Exception {
        final Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            if (methodHash(methods[i]) == hash) {
                return methods[i];
            }
        }
        if (clazz.getSuperclass() != null) {
            return findMethodByHash(clazz.getSuperclass(), hash);
        }
        return null;
    }
    
    public static Constructor findConstructorByHash(final Class clazz, final long hash) throws Exception {
        final Constructor[] cons = clazz.getDeclaredConstructors();
        for (int i = 0; i < cons.length; ++i) {
            if (constructorHash(cons[i]) == hash) {
                return cons[i];
            }
        }
        if (clazz.getSuperclass() != null) {
            return findConstructorByHash(clazz.getSuperclass(), hash);
        }
        return null;
    }
    
    public static long methodHash(final Method method) throws Exception {
        final Class[] parameterTypes = method.getParameterTypes();
        String methodDesc = method.getName() + "(";
        for (int j = 0; j < parameterTypes.length; ++j) {
            methodDesc += getTypeString(parameterTypes[j]);
        }
        methodDesc = methodDesc + ")" + getTypeString(method.getReturnType());
        long hash = 0L;
        final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(512);
        final MessageDigest messagedigest = MessageDigest.getInstance("SHA");
        final DataOutputStream dataoutputstream = new DataOutputStream(new DigestOutputStream(bytearrayoutputstream, messagedigest));
        dataoutputstream.writeUTF(methodDesc);
        dataoutputstream.flush();
        final byte[] abyte0 = messagedigest.digest();
        for (int i = 0; i < Math.min(8, abyte0.length); ++i) {
            hash += (abyte0[i] & 0xFF) << i * 8;
        }
        return hash;
    }
    
    public static long constructorHash(final Constructor method) throws Exception {
        final Class[] parameterTypes = method.getParameterTypes();
        String methodDesc = method.getName() + "(";
        for (int j = 0; j < parameterTypes.length; ++j) {
            methodDesc += getTypeString(parameterTypes[j]);
        }
        methodDesc += ")";
        long hash = 0L;
        final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(512);
        final MessageDigest messagedigest = MessageDigest.getInstance("SHA");
        final DataOutputStream dataoutputstream = new DataOutputStream(new DigestOutputStream(bytearrayoutputstream, messagedigest));
        dataoutputstream.writeUTF(methodDesc);
        dataoutputstream.flush();
        final byte[] abyte0 = messagedigest.digest();
        for (int i = 0; i < Math.min(8, abyte0.length); ++i) {
            hash += (abyte0[i] & 0xFF) << i * 8;
        }
        return hash;
    }
    
    public static Map getInterfaceHashes(final Class intf) {
        final Method[] methods = intf.getDeclaredMethods();
        final HashMap map = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            try {
                final long hash = methodHash(method);
                map.put(method.toString(), new Long(hash));
            }
            catch (Exception ex) {}
        }
        return map;
    }
    
    static String getTypeString(final Class cl) {
        if (cl == Byte.TYPE) {
            return "B";
        }
        if (cl == Character.TYPE) {
            return "C";
        }
        if (cl == Double.TYPE) {
            return "D";
        }
        if (cl == Float.TYPE) {
            return "F";
        }
        if (cl == Integer.TYPE) {
            return "I";
        }
        if (cl == Long.TYPE) {
            return "J";
        }
        if (cl == Short.TYPE) {
            return "S";
        }
        if (cl == Boolean.TYPE) {
            return "Z";
        }
        if (cl == Void.TYPE) {
            return "V";
        }
        if (cl.isArray()) {
            return "[" + getTypeString(cl.getComponentType());
        }
        return "L" + cl.getName().replace('.', '/') + ";";
    }
    
    public static long calculateHash(final Method method) {
        Map methodHashes = MethodHashing.hashMap.get(method.getDeclaringClass());
        if (methodHashes == null) {
            methodHashes = getInterfaceHashes(method.getDeclaringClass());
            final WeakHashMap newHashMap = new WeakHashMap();
            newHashMap.putAll(MethodHashing.hashMap);
            newHashMap.put(method.getDeclaringClass(), methodHashes);
            MethodHashing.hashMap = newHashMap;
        }
        return methodHashes.get(method.toString());
    }
    
    static {
        MethodHashing.hashMap = new WeakHashMap();
    }
}
