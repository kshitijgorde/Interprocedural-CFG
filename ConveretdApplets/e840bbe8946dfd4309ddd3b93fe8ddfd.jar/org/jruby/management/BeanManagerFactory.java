// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import org.jruby.compiler.JITCompilerMBean;
import org.jruby.Ruby;
import java.lang.reflect.Constructor;

public class BeanManagerFactory
{
    private static final Class BeanManagerImpl;
    private static final Constructor BeanManagerImpl_constructor;
    
    public static BeanManager create(final Ruby runtime, final boolean managementEnabled) {
        if (BeanManagerFactory.BeanManagerImpl_constructor != null) {
            try {
                return BeanManagerFactory.BeanManagerImpl_constructor.newInstance(runtime, managementEnabled);
            }
            catch (Exception ex) {}
        }
        return new DummyBeanManager();
    }
    
    static {
        Class bm = null;
        Constructor bmc = null;
        try {
            bm = Class.forName("org.jruby.management.BeanManagerImpl");
            bmc = bm.getConstructor(Ruby.class, Boolean.TYPE);
        }
        catch (Exception ex) {}
        BeanManagerImpl = bm;
        BeanManagerImpl_constructor = bmc;
    }
    
    private static class DummyBeanManager implements BeanManager
    {
        public void register(final JITCompilerMBean jitCompiler) {
        }
        
        public void register(final ConfigMBean config) {
        }
        
        public void register(final ParserStatsMBean parserStats) {
        }
        
        public void register(final MethodCacheMBean methodCache) {
        }
        
        public void register(final ClassCacheMBean classCache) {
        }
        
        public void register(final Runtime runtime) {
        }
        
        public void unregisterClassCache() {
        }
        
        public void unregisterCompiler() {
        }
        
        public void unregisterConfig() {
        }
        
        public void unregisterMethodCache() {
        }
        
        public void unregisterParserStats() {
        }
        
        public void unregisterRuntime() {
        }
    }
}
