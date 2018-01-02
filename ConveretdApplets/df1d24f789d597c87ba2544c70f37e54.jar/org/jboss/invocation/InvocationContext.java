// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InvocationContext implements Serializable
{
    private static final long serialVersionUID = 7679468692447241311L;
    public Map context;
    
    public InvocationContext() {
        this.context = new HashMap();
    }
    
    public InvocationContext(final Map context) {
        this.context = context;
    }
    
    public void setValue(final Object key, final Object value) {
        this.context.put(key, value);
    }
    
    public Object getValue(final Object key) {
        return this.context.get(key);
    }
    
    public void setObjectName(final Object objectName) {
        this.context.put(InvocationKey.OBJECT_NAME, objectName);
    }
    
    public Object getObjectName() {
        return this.context.get(InvocationKey.OBJECT_NAME);
    }
    
    public void setCacheId(final Object id) {
        this.context.put(InvocationKey.CACHE_ID, id);
    }
    
    public Object getCacheId() {
        return this.context.get(InvocationKey.CACHE_ID);
    }
    
    public void setInvoker(final Invoker invoker) {
        this.context.put(InvocationKey.INVOKER, invoker);
    }
    
    public Invoker getInvoker() {
        return this.context.get(InvocationKey.INVOKER);
    }
    
    public void setInvokerProxyBinding(final String binding) {
        this.context.put(InvocationKey.INVOKER_PROXY_BINDING, binding);
    }
    
    public String getInvokerProxyBinding() {
        return this.context.get(InvocationKey.INVOKER_PROXY_BINDING);
    }
}
