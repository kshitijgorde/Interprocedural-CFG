// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

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
    
    public String getSystemProperty(final String s) {
        return System.getProperty(s);
    }
    
    public FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
    
    public InputStream getResourceAsStream(final ClassLoader classLoader, final String s) {
        InputStream inputStream;
        if (classLoader == null) {
            inputStream = ClassLoader.getSystemResourceAsStream(s);
        }
        else {
            inputStream = classLoader.getResourceAsStream(s);
        }
        return inputStream;
    }
    
    static {
        SecuritySupport securitySupport2 = null;
        try {
            Class.forName("java.security.AccessController");
            securitySupport2 = new SecuritySupport12();
        }
        catch (Exception ex) {}
        finally {
            if (securitySupport2 == null) {
                securitySupport2 = new SecuritySupport();
            }
            securitySupport = securitySupport2;
        }
    }
}
