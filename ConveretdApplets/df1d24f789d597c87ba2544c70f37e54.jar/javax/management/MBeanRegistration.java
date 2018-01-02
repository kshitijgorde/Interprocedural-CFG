// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public interface MBeanRegistration
{
    ObjectName preRegister(final MBeanServer p0, final ObjectName p1) throws Exception;
    
    void postRegister(final Boolean p0);
    
    void preDeregister() throws Exception;
    
    void postDeregister();
}
