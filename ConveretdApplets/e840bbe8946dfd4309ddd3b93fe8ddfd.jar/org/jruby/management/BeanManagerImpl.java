// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import java.security.AccessControlException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import org.jruby.compiler.JITCompilerMBean;
import org.jruby.Ruby;

public class BeanManagerImpl implements BeanManager
{
    public final String base;
    private final boolean managementEnabled;
    
    public BeanManagerImpl(final Ruby ruby, final boolean managementEnabled) {
        this.managementEnabled = managementEnabled;
        this.base = "org.jruby:type=Runtime,name=" + ruby.hashCode() + ",";
    }
    
    public void register(final JITCompilerMBean jitCompiler) {
        if (this.managementEnabled) {
            this.register(this.base + "service=JITCompiler", jitCompiler);
        }
    }
    
    public void register(final ConfigMBean config) {
        if (this.managementEnabled) {
            this.register(this.base + "service=Config", config);
        }
    }
    
    public void register(final ParserStatsMBean parserStats) {
        if (this.managementEnabled) {
            this.register(this.base + "service=ParserStats", parserStats);
        }
    }
    
    public void register(final MethodCacheMBean methodCache) {
        if (this.managementEnabled) {
            this.register(this.base + "service=MethodCache", methodCache);
        }
    }
    
    public void register(final ClassCacheMBean classCache) {
        if (this.managementEnabled) {
            this.register(this.base + "service=ClassCache", classCache);
        }
    }
    
    public void register(final Runtime runtime) {
        if (this.managementEnabled) {
            this.register(this.base + "service=Runtime", runtime);
        }
    }
    
    public void unregisterCompiler() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=JITCompiler");
        }
    }
    
    public void unregisterConfig() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=Config");
        }
    }
    
    public void unregisterParserStats() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=ParserStats");
        }
    }
    
    public void unregisterClassCache() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=ClassCache");
        }
    }
    
    public void unregisterMethodCache() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=MethodCache");
        }
    }
    
    public void unregisterRuntime() {
        if (this.managementEnabled) {
            this.unregister(this.base + "service=Runtime");
        }
    }
    
    private void register(final String name, final Object bean) {
        try {
            final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            final ObjectName beanName = new ObjectName(name);
            mbs.registerMBean(bean, beanName);
        }
        catch (InstanceAlreadyExistsException ex5) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.WARNING, "mbean already registered: " + name);
        }
        catch (MBeanRegistrationException ex) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NotCompliantMBeanException ex2) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (MalformedObjectNameException ex3) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (NullPointerException ex4) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex4);
        }
        catch (AccessControlException ex6) {}
        catch (SecurityException ex7) {}
        catch (Error e) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.FINE, null, e);
        }
    }
    
    private void unregister(final String name) {
        try {
            final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            final ObjectName beanName = new ObjectName(name);
            mbs.unregisterMBean(beanName);
        }
        catch (InstanceNotFoundException ex4) {}
        catch (MBeanRegistrationException ex) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MalformedObjectNameException ex2) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (NullPointerException ex3) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (AccessControlException ex5) {}
        catch (SecurityException ex6) {}
        catch (Error e) {
            Logger.getLogger(BeanManagerImpl.class.getName()).log(Level.FINE, null, e);
        }
    }
}
