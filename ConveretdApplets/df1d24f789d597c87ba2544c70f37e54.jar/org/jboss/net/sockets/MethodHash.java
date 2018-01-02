// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.util.WeakHashMap;
import java.lang.reflect.Method;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MethodHash
{
    static Map hashMap;
    
    public static Map getInterfaceHashes(final Class intf) {
        final Method[] methods = intf.getMethods();
        final HashMap map = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            final Class[] parameterTypes = method.getParameterTypes();
            String methodDesc = method.getName() + "(";
            for (int j = 0; j < parameterTypes.length; ++j) {
                methodDesc += getTypeString(parameterTypes[j]);
            }
            methodDesc = methodDesc + ")" + getTypeString(method.getReturnType());
            try {
                long hash = 0L;
                final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(512);
                final MessageDigest messagedigest = MessageDigest.getInstance("SHA");
                final DataOutputStream dataoutputstream = new DataOutputStream(new DigestOutputStream(bytearrayoutputstream, messagedigest));
                dataoutputstream.writeUTF(methodDesc);
                dataoutputstream.flush();
                final byte[] abyte0 = messagedigest.digest();
                for (int k = 0; k < Math.min(8, abyte0.length); ++k) {
                    hash += (abyte0[k] & 0xFF) << k * 8;
                }
                map.put(method.toString(), new Long(hash));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
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
        Map methodHashes = MethodHash.hashMap.get(method.getDeclaringClass());
        if (methodHashes == null) {
            methodHashes = getInterfaceHashes(method.getDeclaringClass());
            final WeakHashMap newHashMap = new WeakHashMap();
            newHashMap.putAll(MethodHash.hashMap);
            newHashMap.put(method.getDeclaringClass(), methodHashes);
            MethodHash.hashMap = newHashMap;
        }
        return methodHashes.get(method.toString());
    }
    
    static {
        MethodHash.hashMap = new WeakHashMap();
    }
}
