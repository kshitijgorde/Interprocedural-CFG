// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.ObjectInput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.Externalizable;

public class ByValueInvokerInterceptor extends InvokerInterceptor implements Externalizable
{
    private static final long serialVersionUID = -6402069656713307195L;
    
    public boolean isLocal(final Invocation invocation) {
        final InvocationType type = invocation.getType();
        return type == InvocationType.LOCAL || type == InvocationType.LOCALHOME;
    }
    
    public Object invoke(final Invocation invocation) throws Exception {
        if (this.isLocal(invocation)) {
            return ByValueInvokerInterceptor.localInvoker.invoke(invocation);
        }
        return invocation.getInvocationContext().getInvoker().invoke(invocation);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
    }
}
