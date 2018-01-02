// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;

class SecuritySupport
{
    private static final Object securitySupport;
    
    public static SecuritySupport getInstance() {
        return (SecuritySupport)SecuritySupport.securitySupport;
    }
    
    public ClassLoader getContextClassLoader() {
        return null;
    }
    
    public String getSystemProperty(final String propName) {
        return System.getProperty(propName);
    }
    
    public FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
    
    public InputStream getResourceAsStream(final ClassLoader cl, final String name) {
        InputStream ris;
        if (cl == null) {
            ris = ClassLoader.getSystemResourceAsStream(name);
        }
        else {
            ris = cl.getResourceAsStream(name);
        }
        return ris;
    }
    
    static {
        SecuritySupport ss = null;
        try {
            final Class c = Class.forName("java.security.AccessController");
            ss = new SecuritySupport12();
        }
        catch (Exception ex) {}
        finally {
            if (ss == null) {
                ss = new SecuritySupport();
            }
            securitySupport = ss;
        }
    }
}
