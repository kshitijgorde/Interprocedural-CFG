// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public interface PersistentMBean
{
    void load() throws MBeanException, RuntimeOperationsException, InstanceNotFoundException;
    
    void store() throws MBeanException, RuntimeOperationsException, InstanceNotFoundException;
}
