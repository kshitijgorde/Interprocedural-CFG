// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

public class MarshallingInvokerInterceptor extends InvokerInterceptor
{
    private static final long serialVersionUID = -6473336704093435358L;
    
    public Object invoke(final Invocation invocation) throws Exception {
        if (this.isLocal(invocation)) {
            return this.invokeLocalMarshalled(invocation);
        }
        return this.invokeInvoker(invocation);
    }
}
