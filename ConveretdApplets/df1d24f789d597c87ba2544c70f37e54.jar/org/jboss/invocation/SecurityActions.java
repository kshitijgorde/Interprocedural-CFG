// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.security.AccessController;
import java.security.PrivilegedAction;

class SecurityActions
{
    static ClassLoader getContextClassLoader() {
        return TCLAction.UTIL.getContextClassLoader();
    }
    
    interface TCLAction
    {
        public static final TCLAction NON_PRIVILEGED = new TCLAction() {
            public ClassLoader getContextClassLoader() {
                return Thread.currentThread().getContextClassLoader();
            }
            
            public ClassLoader getContextClassLoader(final Thread thread) {
                return thread.getContextClassLoader();
            }
            
            public void setContextClassLoader(final ClassLoader cl) {
                Thread.currentThread().setContextClassLoader(cl);
            }
            
            public void setContextClassLoader(final Thread thread, final ClassLoader cl) {
                thread.setContextClassLoader(cl);
            }
        };
        public static final TCLAction PRIVILEGED = new TCLAction() {
            private final PrivilegedAction getTCLPrivilegedAction = new PrivilegedAction() {
                public Object run() {
                    return Thread.currentThread().getContextClassLoader();
                }
            };
            
            public ClassLoader getContextClassLoader() {
                return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)this.getTCLPrivilegedAction);
            }
            
            public ClassLoader getContextClassLoader(final Thread thread) {
                return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
                    public Object run() {
                        return thread.getContextClassLoader();
                    }
                });
            }
            
            public void setContextClassLoader(final ClassLoader cl) {
                AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                    public Object run() {
                        Thread.currentThread().setContextClassLoader(cl);
                        return null;
                    }
                });
            }
            
            public void setContextClassLoader(final Thread thread, final ClassLoader cl) {
                AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                    public Object run() {
                        thread.setContextClassLoader(cl);
                        return null;
                    }
                });
            }
        };
        
        ClassLoader getContextClassLoader();
        
        ClassLoader getContextClassLoader(final Thread p0);
        
        void setContextClassLoader(final ClassLoader p0);
        
        void setContextClassLoader(final Thread p0, final ClassLoader p1);
        
        public static class UTIL
        {
            static TCLAction getTCLAction() {
                return (System.getSecurityManager() == null) ? TCLAction.NON_PRIVILEGED : TCLAction.PRIVILEGED;
            }
            
            static ClassLoader getContextClassLoader() {
                return getTCLAction().getContextClassLoader();
            }
            
            static ClassLoader getContextClassLoader(final Thread thread) {
                return getTCLAction().getContextClassLoader(thread);
            }
            
            static void setContextClassLoader(final ClassLoader cl) {
                getTCLAction().setContextClassLoader(cl);
            }
            
            static void setContextClassLoader(final Thread thread, final ClassLoader cl) {
                getTCLAction().setContextClassLoader(thread, cl);
            }
        }
    }
}
