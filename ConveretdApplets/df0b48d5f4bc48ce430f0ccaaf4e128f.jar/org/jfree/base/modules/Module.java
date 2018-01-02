// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

public interface Module extends ModuleInfo
{
    void configure(final SubSystem p0);
    
    String getDescription();
    
    String getName();
    
    ModuleInfo[] getOptionalModules();
    
    String getProducer();
    
    ModuleInfo[] getRequiredModules();
    
    String getSubSystem();
    
    void initialize(final SubSystem p0) throws ModuleInitializeException;
}
