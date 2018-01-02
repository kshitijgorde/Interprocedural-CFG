// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.util.WeakHashMap;
import java.io.ObjectInput;
import org.jboss.remoting.serialization.SerializationStreamFactory;
import java.io.IOException;
import java.util.Iterator;
import java.io.ObjectOutput;
import org.jboss.remoting.serialization.IMarshalledValue;
import java.security.Principal;
import javax.transaction.Transaction;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.security.PrivilegedAction;
import java.security.AccessController;
import java.lang.reflect.Method;
import java.util.Map;
import java.io.Externalizable;

public class MarshalledInvocation extends Invocation implements Externalizable
{
    static final long serialVersionUID = -718723094688127810L;
    static boolean useFullHashMode;
    static Map hashMap;
    protected Object tpc;
    protected transient Map methodMap;
    protected transient long methodHash;
    protected transient Object marshalledArgs;
    
    public long getMethodHash() {
        return this.methodHash;
    }
    
    public void setMethodHash(final long methodHash) {
        this.methodHash = methodHash;
    }
    
    public static boolean getUseFullHashMode() {
        return MarshalledInvocation.useFullHashMode;
    }
    
    public static void setUseFullHashMode(final boolean flag) {
        MarshalledInvocation.useFullHashMode = flag;
    }
    
    public static Map getInterfaceHashes(final Class intf) {
        Method[] methods = null;
        if (System.getSecurityManager() != null) {
            final DeclaredMethodsAction action = new DeclaredMethodsAction(intf);
            methods = AccessController.doPrivileged((PrivilegedAction<Method[]>)action);
        }
        else {
            methods = intf.getDeclaredMethods();
        }
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
    
    public static Map getFullInterfaceHashes(final Class intf) {
        Method[] methods = null;
        if (System.getSecurityManager() != null) {
            final DeclaredMethodsAction action = new DeclaredMethodsAction(intf);
            methods = AccessController.doPrivileged((PrivilegedAction<Method[]>)action);
        }
        else {
            methods = intf.getDeclaredMethods();
        }
        final HashMap map = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            final String methodDesc = method.toString();
            try {
                long hash = 0L;
                final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(512);
                final MessageDigest messagedigest = MessageDigest.getInstance("SHA");
                final DataOutputStream dataoutputstream = new DataOutputStream(new DigestOutputStream(bytearrayoutputstream, messagedigest));
                dataoutputstream.writeUTF(methodDesc);
                dataoutputstream.flush();
                final byte[] abyte0 = messagedigest.digest();
                for (int j = 0; j < Math.min(8, abyte0.length); ++j) {
                    hash += (abyte0[j] & 0xFF) << j * 8;
                }
                map.put(method.toString(), new Long(hash));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    
    public static Map methodToHashesMap(final Class c) {
        Method[] methods = null;
        if (System.getSecurityManager() != null) {
            final DeclaredMethodsAction action = new DeclaredMethodsAction(c);
            methods = AccessController.doPrivileged((PrivilegedAction<Method[]>)action);
        }
        else {
            methods = c.getDeclaredMethods();
        }
        final HashMap map = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            final String methodDesc = method.toString();
            try {
                long hash = 0L;
                final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(512);
                final MessageDigest messagedigest = MessageDigest.getInstance("SHA");
                final DataOutputStream dataoutputstream = new DataOutputStream(new DigestOutputStream(bytearrayoutputstream, messagedigest));
                dataoutputstream.writeUTF(methodDesc);
                dataoutputstream.flush();
                final byte[] abyte0 = messagedigest.digest();
                for (int j = 0; j < Math.min(8, abyte0.length); ++j) {
                    hash += (abyte0[j] & 0xFF) << j * 8;
                }
                map.put(new Long(hash), method);
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
        Map methodHashes = MarshalledInvocation.hashMap.get(method.getDeclaringClass());
        if (methodHashes == null) {
            if (MarshalledInvocation.useFullHashMode) {
                methodHashes = getFullInterfaceHashes(method.getDeclaringClass());
            }
            else {
                methodHashes = getInterfaceHashes(method.getDeclaringClass());
            }
            synchronized (MarshalledInvocation.hashMap) {
                MarshalledInvocation.hashMap.put(method.getDeclaringClass(), methodHashes);
            }
        }
        final Long hash = methodHashes.get(method.toString());
        return hash;
    }
    
    public static void removeHashes(final Class declaringClass) {
        synchronized (MarshalledInvocation.hashMap) {
            MarshalledInvocation.hashMap.remove(declaringClass);
        }
    }
    
    public MarshalledInvocation() {
        this.methodHash = 0L;
        this.marshalledArgs = null;
    }
    
    public MarshalledInvocation(final Invocation invocation) {
        this.methodHash = 0L;
        this.marshalledArgs = null;
        this.payload = invocation.payload;
        this.as_is_payload = invocation.as_is_payload;
        this.method = invocation.getMethod();
        this.objectName = invocation.getObjectName();
        this.args = invocation.getArguments();
        this.invocationType = invocation.getType();
        this.transient_payload = invocation.transient_payload;
        this.invocationContext = invocation.invocationContext;
    }
    
    public MarshalledInvocation(final Object id, final Method m, final Object[] args, final Transaction tx, final Principal identity, final Object credential) {
        super(id, m, args, tx, identity, credential);
        this.methodHash = 0L;
        this.marshalledArgs = null;
    }
    
    public Method getMethod() {
        if (this.method != null) {
            return this.method;
        }
        this.method = this.methodMap.get(new Long(this.methodHash));
        if (this.method == null) {
            throw new IllegalStateException("Failed to find method for hash:" + this.methodHash + " available=" + this.methodMap);
        }
        return this.method;
    }
    
    public void setMethodMap(final Map methods) {
        this.methodMap = methods;
    }
    
    public void setTransactionPropagationContext(final Object tpc) {
        this.tpc = tpc;
    }
    
    public Object getTransactionPropagationContext() {
        return this.tpc;
    }
    
    public Object getValue(final Object key) {
        Object value = super.getValue(key);
        if (value instanceof IMarshalledValue) {
            try {
                final IMarshalledValue mv = (IMarshalledValue)value;
                value = mv.get();
            }
            catch (Exception e) {
                final JBossLazyUnmarshallingException ise = new JBossLazyUnmarshallingException("getValue failed");
                ise.initCause(e);
                throw ise;
            }
        }
        return value;
    }
    
    public Object getPayloadValue(final Object key) {
        Object value = this.getPayload().get(key);
        if (value instanceof MarshalledValue) {
            try {
                final MarshalledValue mv = (MarshalledValue)value;
                value = mv.get();
                return value;
            }
            catch (Exception e) {
                final JBossLazyUnmarshallingException ise = new JBossLazyUnmarshallingException("getPayloadValue failed");
                ise.initCause(e);
                throw ise;
            }
        }
        if (value instanceof IMarshalledValue) {
            try {
                final IMarshalledValue mv2 = (IMarshalledValue)value;
                value = mv2.get();
            }
            catch (Exception e) {
                final JBossLazyUnmarshallingException ise = new JBossLazyUnmarshallingException("getPayloadValue failed");
                ise.initCause(e);
                throw ise;
            }
        }
        return value;
    }
    
    public Object[] getArguments() {
        if (this.args == null) {
            if (this.marshalledArgs instanceof MarshalledValue) {
                try {
                    this.args = (Object[])((MarshalledValue)this.marshalledArgs).get();
                    return this.args;
                }
                catch (Exception e) {
                    final JBossLazyUnmarshallingException ise = new JBossLazyUnmarshallingException("getArguments failed");
                    ise.initCause(e);
                    throw ise;
                }
            }
            if (this.marshalledArgs instanceof IMarshalledValue) {
                try {
                    this.args = (Object[])((IMarshalledValue)this.marshalledArgs).get();
                }
                catch (Exception e) {
                    final JBossLazyUnmarshallingException ise = new JBossLazyUnmarshallingException("getArguments failed");
                    ise.initCause(e);
                    throw ise;
                }
            }
        }
        return this.args;
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        this.getAsIsPayload().put(InvocationKey.TYPE, this.invocationType);
        out.writeObject(this.tpc);
        long methodHash = this.methodHash;
        if (methodHash == 0L) {
            methodHash = calculateHash(this.method);
        }
        out.writeLong(methodHash);
        out.writeObject(this.objectName);
        String serializationType = null;
        if (this.invocationContext != null) {
            serializationType = (String)this.invocationContext.getValue("SERIALIZATION_TYPE");
        }
        if (this.args == null && this.marshalledArgs != null) {
            out.writeObject(this.marshalledArgs);
        }
        else {
            out.writeObject(this.createMarshalledValue(serializationType, this.args));
        }
        if (this.payload == null) {
            out.writeInt(0);
        }
        else {
            out.writeInt(this.payload.size());
            for (final Object currentKey : this.payload.keySet()) {
                out.writeObject(currentKey);
                Object value = this.payload.get(currentKey);
                if (!(value instanceof MarshalledValue)) {
                    value = this.createMarshalledValue(serializationType, value);
                }
                out.writeObject(value);
            }
        }
        if (this.as_is_payload == null) {
            out.writeInt(0);
        }
        else {
            out.writeInt(this.as_is_payload.size());
            for (final Object currentKey : this.as_is_payload.keySet()) {
                out.writeObject(currentKey);
                out.writeObject(this.as_is_payload.get(currentKey));
            }
        }
    }
    
    private Object createMarshalledValue(final String serializationType, final Object valueToBeMarshalled) throws IOException {
        if (serializationType != null) {
            return SerializationStreamFactory.getManagerInstance(serializationType).createdMarshalledValue(valueToBeMarshalled);
        }
        return new MarshalledValue(valueToBeMarshalled);
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.tpc = in.readObject();
        this.methodHash = in.readLong();
        this.objectName = in.readObject();
        this.marshalledArgs = in.readObject();
        final int payloadSize = in.readInt();
        if (payloadSize > 0) {
            this.payload = new HashMap();
            for (int i = 0; i < payloadSize; ++i) {
                final Object key = in.readObject();
                final Object value = in.readObject();
                this.payload.put(key, value);
            }
        }
        final int as_is_payloadSize = in.readInt();
        if (as_is_payloadSize > 0) {
            this.as_is_payload = new HashMap();
            for (int j = 0; j < as_is_payloadSize; ++j) {
                final Object key2 = in.readObject();
                final Object value2 = in.readObject();
                this.as_is_payload.put(key2, value2);
            }
        }
        this.invocationType = (InvocationType)this.getAsIsValue(InvocationKey.TYPE);
    }
    
    public void setMarshalledArguments(final IMarshalledValue marshalledValue) {
        this.marshalledArgs = marshalledValue;
    }
    
    static {
        try {
            Class.forName("org.jboss.invocation.unified.interfaces.JavaSerializationManager");
        }
        catch (Exception ex) {}
        MarshalledInvocation.useFullHashMode = true;
        MarshalledInvocation.hashMap = new WeakHashMap();
    }
    
    private static class DeclaredMethodsAction implements PrivilegedAction
    {
        Class c;
        
        DeclaredMethodsAction(final Class c) {
            this.c = c;
        }
        
        public Object run() {
            final Method[] methods = this.c.getDeclaredMethods();
            this.c = null;
            return methods;
        }
    }
}
