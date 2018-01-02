// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.loading;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Permission;

public class ContextClassLoaderSwitcher extends ContextClassLoader
{
    public static final RuntimePermission SETCONTEXTCLASSLOADER;
    public static final NewInstance INSTANTIATOR;
    
    private ContextClassLoaderSwitcher() {
        final SecurityManager manager = System.getSecurityManager();
        if (manager != null) {
            manager.checkPermission(ContextClassLoaderSwitcher.SETCONTEXTCLASSLOADER);
        }
    }
    
    public void setContextClassLoader(final ClassLoader cl) {
        this.setContextClassLoader(Thread.currentThread(), cl);
    }
    
    public void setContextClassLoader(final Thread thread, final ClassLoader cl) {
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            public Object run() {
                thread.setContextClassLoader(cl);
                return null;
            }
        });
    }
    
    public SwitchContext getSwitchContext() {
        return new SwitchContext();
    }
    
    public SwitchContext getSwitchContext(final ClassLoader cl) {
        return new SwitchContext(cl);
    }
    
    public SwitchContext getSwitchContext(final Class clazz) {
        return new SwitchContext(clazz.getClassLoader());
    }
    
    static {
        SETCONTEXTCLASSLOADER = new RuntimePermission("setContextClassLoader");
        INSTANTIATOR = new NewInstance();
    }
    
    public class SwitchContext
    {
        private ClassLoader origCL;
        private ClassLoader currentCL;
        private Thread currentThread;
        
        private SwitchContext() {
            this.currentThread = Thread.currentThread();
            this.origCL = ContextClassLoaderSwitcher.this.getContextClassLoader(this.currentThread);
            this.currentCL = this.origCL;
        }
        
        private SwitchContext(final ContextClassLoaderSwitcher this$0, final ClassLoader cl) {
            this(this$0);
            this.setClassLoader(cl);
        }
        
        public Thread getThread() {
            return this.currentThread;
        }
        
        public ClassLoader getOriginalClassLoader() {
            return this.origCL;
        }
        
        public ClassLoader getCurrentClassLoader() {
            return this.currentCL;
        }
        
        public void setClassLoader(final ClassLoader cl) {
            if (cl != null && cl != this.currentCL) {
                ContextClassLoaderSwitcher.this.setContextClassLoader(this.currentThread, cl);
                this.currentCL = cl;
            }
        }
        
        public void reset() {
            if (this.currentCL != null && this.currentCL != this.origCL) {
                ContextClassLoaderSwitcher.this.setContextClassLoader(this.currentThread, this.origCL);
            }
        }
        
        public void forceReset() {
            ContextClassLoaderSwitcher.this.setContextClassLoader(this.currentThread, this.origCL);
        }
    }
    
    private static class NewInstance implements PrivilegedAction
    {
        public Object run() {
            return new ContextClassLoaderSwitcher(null);
        }
    }
}
