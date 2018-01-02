// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.io.InputStream;
import java.security.PrivilegedActionException;
import java.io.FileNotFoundException;
import java.security.PrivilegedExceptionAction;
import java.io.FileInputStream;
import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

final class SecuritySupport
{
    private static final SecuritySupport securitySupport;
    
    static SecuritySupport getInstance() {
        return SecuritySupport.securitySupport;
    }
    
    ClassLoader getContextClassLoader() {
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                Object contextClassLoader = null;
                try {
                    contextClassLoader = Thread.currentThread().getContextClassLoader();
                }
                catch (SecurityException ex) {}
                return contextClassLoader;
            }
        });
    }
    
    ClassLoader getSystemClassLoader() {
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                Object systemClassLoader = null;
                try {
                    systemClassLoader = ClassLoader.getSystemClassLoader();
                }
                catch (SecurityException ex) {}
                return systemClassLoader;
            }
        });
    }
    
    ClassLoader getParentClassLoader(final ClassLoader classLoader) {
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                ClassLoader parent = null;
                try {
                    parent = classLoader.getParent();
                }
                catch (SecurityException ex) {}
                return (parent == classLoader) ? null : parent;
            }
        });
    }
    
    String getSystemProperty(final String s) {
        return AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
            public Object run() {
                return System.getProperty(s);
            }
        });
    }
    
    FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<FileInputStream>)new PrivilegedExceptionAction() {
                public Object run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            });
        }
        catch (PrivilegedActionException ex) {
            throw (FileNotFoundException)ex.getException();
        }
    }
    
    InputStream getResourceAsStream(final ClassLoader classLoader, final String s) {
        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new PrivilegedAction() {
            public Object run() {
                InputStream inputStream;
                if (classLoader == null) {
                    inputStream = ClassLoader.getSystemResourceAsStream(s);
                }
                else {
                    inputStream = classLoader.getResourceAsStream(s);
                }
                return inputStream;
            }
        });
    }
    
    boolean getFileExists(final File file) {
        return AccessController.doPrivileged((PrivilegedAction<Boolean>)new PrivilegedAction() {
            public Object run() {
                return new Boolean(file.exists());
            }
        });
    }
    
    long getLastModified(final File file) {
        return AccessController.doPrivileged((PrivilegedAction<Long>)new PrivilegedAction() {
            public Object run() {
                return new Long(file.lastModified());
            }
        });
    }
    
    static {
        securitySupport = new SecuritySupport();
    }
}
