// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.lang.reflect.Method;
import java.security.Principal;
import javax.transaction.Transaction;

public class LocalEJBInvocation extends Invocation
{
    private Transaction tx;
    private Object credential;
    private Principal principal;
    private Object enterpriseContext;
    private Object id;
    
    public LocalEJBInvocation() {
    }
    
    public LocalEJBInvocation(final Object id, final Method m, final Object[] args, final Transaction tx, final Principal identity, final Object credential) {
        super(id, m, args, tx, identity, credential);
    }
    
    public void setTransaction(final Transaction tx) {
        this.tx = tx;
    }
    
    public Transaction getTransaction() {
        return this.tx;
    }
    
    public Object getCredential() {
        return this.credential;
    }
    
    public void setCredential(final Object credential) {
        this.credential = credential;
    }
    
    public Principal getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(final Principal principal) {
        this.principal = principal;
    }
    
    public Object getEnterpriseContext() {
        return this.enterpriseContext;
    }
    
    public void setEnterpriseContext(final Object enterpriseContext) {
        this.enterpriseContext = enterpriseContext;
    }
    
    public Object getId() {
        return this.id;
    }
    
    public void setId(final Object id) {
        this.id = id;
    }
}
