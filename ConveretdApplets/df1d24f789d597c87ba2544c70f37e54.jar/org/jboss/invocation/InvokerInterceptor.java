// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.jboss.proxy.ClientContainer;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.io.IOException;
import org.jboss.remoting.serialization.SerializationStreamFactory;
import org.jboss.remoting.serialization.IMarshalledValue;
import javax.transaction.Transaction;
import java.lang.reflect.UndeclaredThrowableException;
import org.jboss.system.Registry;
import org.jboss.serial.objectmetamodel.safecloning.SafeClone;
import org.jboss.util.id.GUID;
import java.io.Externalizable;
import org.jboss.proxy.Interceptor;

public class InvokerInterceptor extends Interceptor implements Externalizable
{
    private static final long serialVersionUID = 2548120545997920357L;
    private GUID invokerID;
    protected Invoker remoteInvoker;
    protected static Invoker localInvoker;
    protected static Class invokerProxyHA;
    static final SafeClone safeToReuse;
    
    public static Invoker getLocal() {
        return InvokerInterceptor.localInvoker;
    }
    
    public static void setLocal(final Invoker invoker) {
        InvokerInterceptor.localInvoker = invoker;
    }
    
    public InvokerInterceptor() {
        this.invokerID = Invoker.ID;
    }
    
    public boolean isLocal() {
        return this.invokerID.equals(Invoker.ID);
    }
    
    public boolean isLocal(final Invocation invocation) {
        return InvokerInterceptor.localInvoker != null && (this.isLocal() || this.isClustered(invocation)) && this.hasLocalTarget(invocation);
    }
    
    public boolean isClustered(final Invocation invocation) {
        if (InvokerInterceptor.invokerProxyHA == null) {
            return false;
        }
        final InvocationContext ctx = invocation.getInvocationContext();
        final Invoker invoker = ctx.getInvoker();
        return invoker != null && InvokerInterceptor.invokerProxyHA.isAssignableFrom(invoker.getClass());
    }
    
    public boolean hasLocalTarget(final Invocation invocation) {
        return Registry.lookup(invocation.getObjectName()) != null;
    }
    
    public Object invoke(final Invocation invocation) throws Exception {
        if (this.isLocal(invocation)) {
            return this.invokeLocal(invocation);
        }
        return this.invokeInvoker(invocation);
    }
    
    protected Object invokeLocal(final Invocation invocation) throws Exception {
        return InvokerInterceptor.localInvoker.invoke(invocation);
    }
    
    protected Object invokeMarshalled(final Invocation invocation) throws Exception {
        final MarshalledInvocation mi = new MarshalledInvocation(invocation);
        final MarshalledValue copy = new MarshalledValue(mi);
        final Invocation invocationCopy = (Invocation)copy.get();
        final Transaction tx = invocation.getTransaction();
        invocationCopy.setTransaction(tx);
        try {
            final Object rtnValue = InvokerInterceptor.localInvoker.invoke(invocationCopy);
            final MarshalledValue mv = new MarshalledValue(rtnValue);
            return mv.get();
        }
        catch (Throwable t) {
            final MarshalledValue mv = new MarshalledValue(t);
            final Throwable t2 = (Throwable)mv.get();
            if (t2 instanceof Exception) {
                throw (Exception)t2;
            }
            throw new UndeclaredThrowableException(t2);
        }
    }
    
    protected IMarshalledValue createMarshalledValueForCallByValue(final Object value) throws IOException {
        return SerializationStreamFactory.getManagerInstance().createdMarshalledValue(value);
    }
    
    protected Object invokeLocalMarshalled(final Invocation invocation) throws Exception {
        final IMarshalledValue value = this.createMarshalledValueForCallByValue(invocation.getArguments());
        final MarshalledInvocation invocationCopy = this.createInvocationCopy(invocation, value);
        final Transaction tx = invocation.getTransaction();
        invocationCopy.setTransaction(tx);
        try {
            final Object rtnValue = InvokerInterceptor.localInvoker.invoke(invocationCopy);
            final IMarshalledValue mv = this.createMarshalledValueForCallByValue(rtnValue);
            return mv.get();
        }
        catch (Throwable t) {
            final IMarshalledValue mv = SerializationStreamFactory.getManagerInstance().createdMarshalledValue((Object)t);
            final Throwable t2 = (Throwable)mv.get();
            if (t2 instanceof Exception) {
                throw (Exception)t2;
            }
            throw new UndeclaredThrowableException(t2);
        }
    }
    
    private MarshalledInvocation createInvocationCopy(final Invocation invocation, final IMarshalledValue value) throws IOException, ClassNotFoundException {
        final MarshalledInvocation invocationCopy = new MarshalledInvocation(invocation);
        invocationCopy.setMethod(null);
        invocationCopy.setMethodHash(MarshalledInvocation.calculateHash(invocation.getMethod()));
        invocationCopy.setMarshalledArguments(value);
        invocationCopy.setArguments(null);
        InvocationContext copyContext = null;
        if (invocation.getInvocationContext() != null) {
            copyContext = (InvocationContext)this.createMarshalledValueForCallByValue(invocation.getInvocationContext()).get();
        }
        invocationCopy.setInvocationContext(copyContext);
        final Map payLoad = invocation.getPayload();
        final Map payloadCopy = new HashMap();
        if (payLoad != null && payLoad.size() != 0) {
            for (final Object currentKey : payLoad.keySet()) {
                final Object valueSource = payLoad.get(currentKey);
                payloadCopy.put(currentKey, this.createMarshalledValueForCallByValue(valueSource));
            }
        }
        invocationCopy.payload = payloadCopy;
        return invocationCopy;
    }
    
    protected Object invokeInvoker(final Invocation invocation) throws Exception {
        final InvocationContext ctx = invocation.getInvocationContext();
        final Invoker invoker = ctx.getInvoker();
        return invoker.invoke(invocation);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(this.invokerID);
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.invokerID = (GUID)in.readObject();
    }
    
    static {
        try {
            InvokerInterceptor.invokerProxyHA = Class.forName("org.jboss.invocation.InvokerProxyHA");
        }
        catch (Throwable t) {}
        try {
            Class.forName("org.jboss.invocation.unified.interfaces.JavaSerializationManager");
        }
        catch (Throwable t2) {}
        safeToReuse = (SafeClone)new SafeClone() {
            public boolean isSafeToReuse(final Object obj) {
                return obj != null && (obj instanceof ClientContainer || obj instanceof String || obj instanceof Number || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte || obj instanceof Double || obj instanceof Float || obj instanceof Integer || obj instanceof Long || obj instanceof Short);
            }
        };
    }
}
