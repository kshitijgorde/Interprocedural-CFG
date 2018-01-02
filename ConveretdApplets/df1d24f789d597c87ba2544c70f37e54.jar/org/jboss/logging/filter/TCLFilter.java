// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.filter;

import java.lang.reflect.Method;
import java.net.URL;
import org.apache.log4j.spi.LoggingEvent;
import org.jboss.util.collection.WeakSet;
import org.apache.log4j.spi.Filter;

public class TCLFilter extends Filter
{
    private WeakSet matchSet;
    private WeakSet missSet;
    private String deployURL;
    private boolean acceptOnMatch;
    
    public TCLFilter() {
        this.matchSet = new WeakSet();
        this.missSet = new WeakSet();
        this.acceptOnMatch = true;
    }
    
    public boolean isAcceptOnMatch() {
        return this.acceptOnMatch;
    }
    
    public void setAcceptOnMatch(final boolean acceptOnMatch) {
        this.acceptOnMatch = acceptOnMatch;
    }
    
    public String getDeployURL() {
        return this.deployURL;
    }
    
    public void setDeployURL(final String deployURL) {
        this.deployURL = deployURL;
    }
    
    public int decide(final LoggingEvent event) {
        int ok = 0;
        if (this.acceptOnMatch) {
            if (this.isMatchingTCL()) {
                ok = 1;
            }
        }
        else if (this.isMatchingTCL()) {
            ok = -1;
        }
        return ok;
    }
    
    private boolean isMatchingTCL() {
        final ClassLoader tcl = Thread.currentThread().getContextClassLoader();
        if (this.matchSet.contains((Object)tcl)) {
            return true;
        }
        if (this.missSet.contains((Object)tcl)) {
            return false;
        }
        ClassLoader cl = tcl;
        boolean match = false;
        while (cl != null) {
            final URL[] urls = getClassLoaderURLs(cl);
            for (int n = 0; n < urls.length; ++n) {
                final URL u = urls[n];
                final String file = u.getFile();
                if (file.indexOf(this.deployURL) > 0) {
                    match = true;
                    break;
                }
            }
            cl = cl.getParent();
        }
        if (match) {
            this.matchSet.add((Object)tcl);
        }
        else {
            this.missSet.add((Object)tcl);
        }
        return match;
    }
    
    private static URL[] getClassLoaderURLs(final ClassLoader cl) {
        URL[] urls = new URL[0];
        try {
            final Class returnType = urls.getClass();
            final Class[] parameterTypes = new Class[0];
            Method getURLs = cl.getClass().getMethod("getURLs", (Class<?>[])parameterTypes);
            if (returnType.isAssignableFrom(getURLs.getReturnType())) {
                final Object[] args = new Object[0];
                urls = (URL[])getURLs.invoke(cl, args);
            }
            if (urls == null || urls.length == 0) {
                getURLs = cl.getClass().getMethod("getClasspath", (Class<?>[])parameterTypes);
                if (returnType.isAssignableFrom(getURLs.getReturnType())) {
                    final Object[] args = new Object[0];
                    urls = (URL[])getURLs.invoke(cl, args);
                }
            }
        }
        catch (Exception ex) {}
        return urls;
    }
}
