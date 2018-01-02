// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

import java.util.NoSuchElementException;
import java.net.URL;
import java.util.Enumeration;
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
    
    public Enumeration getResources(final ClassLoader classLoader, final String s) {
        return new Enumeration() {
            boolean more = true;
            private final /* synthetic */ URL val$urls = SecuritySupport.this.getResource(classLoader, s);
            
            public boolean hasMoreElements() {
                return this.more && this.val$urls != null;
            }
            
            public Object nextElement() {
                if (!this.hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                this.more = false;
                return this.val$urls;
            }
        };
    }
    
    private URL getResource(final ClassLoader classLoader, final String s) {
        URL url;
        if (classLoader == null) {
            url = ClassLoader.getSystemResource(s);
        }
        else {
            url = classLoader.getResource(s);
        }
        return url;
    }
    
    public boolean getFileExists(final File file) {
        return file.exists();
    }
    
    public long getLastModified(final File file) {
        return file.lastModified();
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
