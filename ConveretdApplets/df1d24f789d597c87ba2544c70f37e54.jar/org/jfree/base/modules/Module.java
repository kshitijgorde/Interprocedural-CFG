// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

public interface Module extends ModuleInfo
{
    ModuleInfo[] getRequiredModules();
    
    ModuleInfo[] getOptionalModules();
    
    void initialize(final SubSystem p0) throws ModuleInitializeException;
    
    void configure(final SubSystem p0);
    
    String getDescription();
    
    String getProducer();
    
    String getName();
    
    String getSubSystem();
}
