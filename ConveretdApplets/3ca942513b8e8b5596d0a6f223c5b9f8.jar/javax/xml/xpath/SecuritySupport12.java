// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

import java.net.URL;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Enumeration;
import java.security.PrivilegedActionException;
import java.io.FileNotFoundException;
import java.security.PrivilegedExceptionAction;
import java.io.FileInputStream;
import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

class SecuritySupport12 extends SecuritySupport
{
    public ClassLoader getContextClassLoader() {
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
    
    public String getSystemProperty(final String s) {
        return AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
            public Object run() {
                return System.getProperty(s);
            }
        });
    }
    
    public FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
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
    
    public Enumeration getResources(final ClassLoader classLoader, final String s) {
        return AccessController.doPrivileged((PrivilegedAction<Enumeration>)new PrivilegedAction() {
            public Object run() {
                try {
                    Enumeration<URL> enumeration;
                    if (classLoader == null) {
                        enumeration = ClassLoader.getSystemResources(s);
                    }
                    else {
                        enumeration = classLoader.getResources(s);
                    }
                    return enumeration;
                }
                catch (IOException ex) {
                    return new Enumeration() {
                        public boolean hasMoreElements() {
                            return false;
                        }
                        
                        public Object nextElement() {
                            throw new NoSuchElementException();
                        }
                    };
                }
            }
        });
    }
    
    public boolean getFileExists(final File file) {
        return AccessController.doPrivileged((PrivilegedAction<Boolean>)new PrivilegedAction() {
            public Object run() {
                return new Boolean(file.exists());
            }
        });
    }
    
    public long getLastModified(final File file) {
        return AccessController.doPrivileged((PrivilegedAction<Long>)new PrivilegedAction() {
            public Object run() {
                return new Long(file.lastModified());
            }
        });
    }
}
