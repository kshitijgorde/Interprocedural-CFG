// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.io.Serializable;
import java.security.Principal;
import javax.transaction.Transaction;
import java.lang.reflect.Method;
import java.util.Map;

public class Invocation
{
    public static final String[] INVOKE_SIGNATURE;
    public Map transient_payload;
    public Map as_is_payload;
    public Map payload;
    public InvocationContext invocationContext;
    public Object[] args;
    public Object objectName;
    public Method method;
    public InvocationType invocationType;
    
    public Invocation() {
    }
    
    public Invocation(final Object id, final Method m, final Object[] args, final Transaction tx, final Principal identity, final Object credential) {
        this.setId(id);
        this.setMethod(m);
        this.setArguments(args);
        this.setTransaction(tx);
        this.setPrincipal(identity);
        this.setCredential(credential);
    }
    
    public void setValue(final Object key, final Object value) {
        this.setValue(key, value, PayloadKey.PAYLOAD);
    }
    
    public void setValue(final Object key, final Object value, final PayloadKey type) {
        if (type == PayloadKey.TRANSIENT) {
            this.getTransientPayload().put(key, value);
        }
        else if (type == PayloadKey.AS_IS) {
            this.getAsIsPayload().put(key, value);
        }
        else {
            if (type != PayloadKey.PAYLOAD) {
                throw new IllegalArgumentException("Unknown PayloadKey: " + type);
            }
            this.getPayload().put(key, value);
        }
    }
    
    public Object getValue(final Object key) {
        Object rtn = this.getPayloadValue(key);
        if (rtn != null) {
            return rtn;
        }
        rtn = this.getAsIsValue(key);
        if (rtn != null) {
            return rtn;
        }
        rtn = this.getTransientValue(key);
        return rtn;
    }
    
    public Object getPayloadValue(final Object key) {
        if (this.payload == null) {
            return null;
        }
        return this.payload.get(key);
    }
    
    public Object getTransientValue(final Object key) {
        if (this.transient_payload == null) {
            return null;
        }
        return this.transient_payload.get(key);
    }
    
    public Object getAsIsValue(final Object key) {
        if (this.as_is_payload == null) {
            return null;
        }
        return this.as_is_payload.get(key);
    }
    
    public void setTransaction(final Transaction tx) {
        if (tx instanceof Serializable) {
            this.getAsIsPayload().put(InvocationKey.TRANSACTION, tx);
        }
        else {
            this.getTransientPayload().put(InvocationKey.TRANSACTION, tx);
        }
    }
    
    public Transaction getTransaction() {
        Transaction tx = this.getAsIsPayload().get(InvocationKey.TRANSACTION);
        if (tx == null) {
            tx = this.getTransientPayload().get(InvocationKey.TRANSACTION);
        }
        return tx;
    }
    
    public void setPrincipal(final Principal principal) {
        this.getAsIsPayload().put(InvocationKey.PRINCIPAL, principal);
    }
    
    public Principal getPrincipal() {
        return this.getAsIsPayload().get(InvocationKey.PRINCIPAL);
    }
    
    public void setCredential(final Object credential) {
        this.getPayload().put(InvocationKey.CREDENTIAL, credential);
    }
    
    public Object getCredential() {
        return this.getPayloadValue(InvocationKey.CREDENTIAL);
    }
    
    public void setObjectName(final Object objectName) {
        this.objectName = objectName;
    }
    
    public Object getObjectName() {
        return this.objectName;
    }
    
    public void setType(final InvocationType type) {
        this.invocationType = type;
    }
    
    public InvocationType getType() {
        if (this.invocationType == null) {
            return InvocationType.LOCAL;
        }
        return this.invocationType;
    }
    
    public void setId(final Object id) {
        this.getPayload().put(InvocationKey.CACHE_ID, id);
    }
    
    public Object getId() {
        return this.getPayloadValue(InvocationKey.CACHE_ID);
    }
    
    public void setMethod(final Method method) {
        this.method = method;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public void setArguments(final Object[] arguments) {
        this.args = arguments;
    }
    
    public Object[] getArguments() {
        return this.args;
    }
    
    public InvocationContext getInvocationContext() {
        return this.invocationContext;
    }
    
    public void setInvocationContext(final InvocationContext ctx) {
        this.invocationContext = ctx;
    }
    
    public void setEnterpriseContext(final Object ctx) {
        this.getTransientPayload().put(InvocationKey.ENTERPRISE_CONTEXT, ctx);
    }
    
    public Object getEnterpriseContext() {
        return this.getTransientPayload().get(InvocationKey.ENTERPRISE_CONTEXT);
    }
    
    public Map getTransientPayload() {
        if (this.transient_payload == null) {
            this.transient_payload = new HashMap();
        }
        return this.transient_payload;
    }
    
    public Map getAsIsPayload() {
        if (this.as_is_payload == null) {
            this.as_is_payload = new HashMap();
        }
        return this.as_is_payload;
    }
    
    public Map getPayload() {
        if (this.payload == null) {
            this.payload = new HashMap();
        }
        return this.payload;
    }
    
    public Object performCall(final Object instance, final Method m, final Object[] arguments) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, Exception {
        return m.invoke(instance, arguments);
    }
    
    public boolean isLocal() {
        final InvocationType type = this.getType();
        return type == InvocationType.LOCAL || type == InvocationType.LOCALHOME;
    }
    
    static {
        INVOKE_SIGNATURE = new String[] { "org.jboss.invocation.Invocation" };
    }
}
