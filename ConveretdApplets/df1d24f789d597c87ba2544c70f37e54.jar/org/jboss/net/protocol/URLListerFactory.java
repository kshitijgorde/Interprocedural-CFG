// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class URLListerFactory
{
    private static HashMap defaultClasses;
    private HashMap classes;
    
    public URLListerFactory() {
        this.classes = (HashMap)URLListerFactory.defaultClasses.clone();
    }
    
    public URLLister createURLLister(final URL url) throws MalformedURLException {
        return this.createURLLister(url.getProtocol());
    }
    
    public URLLister createURLLister(final String protocol) throws MalformedURLException {
        try {
            final String className = this.classes.get(protocol);
            if (className == null) {
                throw new MalformedURLException("No lister class defined for protocol " + protocol);
            }
            final Class clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            return clazz.newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new MalformedURLException(e.getMessage());
        }
        catch (InstantiationException e2) {
            throw new MalformedURLException(e2.getMessage());
        }
        catch (IllegalAccessException e3) {
            throw new MalformedURLException(e3.getMessage());
        }
    }
    
    public void registerListener(final String protocol, final String className) {
        this.classes.put(protocol, className);
    }
    
    static {
        (URLListerFactory.defaultClasses = new HashMap()).put("file", "org.jboss.net.protocol.file.FileURLLister");
        URLListerFactory.defaultClasses.put("http", "org.jboss.net.protocol.http.DavURLLister");
        URLListerFactory.defaultClasses.put("https", "org.jboss.net.protocol.http.DavURLLister");
    }
}
