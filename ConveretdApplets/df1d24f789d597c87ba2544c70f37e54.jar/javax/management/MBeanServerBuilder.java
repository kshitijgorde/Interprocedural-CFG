// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.server.MBeanServerBuilderImpl;

public class MBeanServerBuilder
{
    MBeanServerBuilderImpl impl;
    
    public MBeanServerDelegate newMBeanServerDelegate() {
        if (this.impl == null) {
            this.impl = new MBeanServerBuilderImpl();
        }
        return this.impl.newMBeanServerDelegate();
    }
    
    public MBeanServer newMBeanServer(final String defaultDomain, final MBeanServer outer, final MBeanServerDelegate delegate) {
        if (this.impl == null) {
            this.impl = new MBeanServerBuilderImpl();
        }
        return this.impl.newMBeanServer(defaultDomain, outer, delegate);
    }
}
