// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.utils;

import java.net.URLConnection;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import java.net.URL;
import org.osgi.framework.Bundle;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Method;

public class OSGiFileLocator
{
    public static final String JRUBY_SYMBOLIC_NAME = "org.jruby.jruby";
    private static Method BUNDLE_URL_CONNECTION_getLocalURL;
    private static Method BUNDLE_URL_CONNECTION_getFileURL;
    
    public static File getJRubyHomeFolder() throws IOException {
        return getFileInBundle("org.jruby.jruby", "/META-INF/jruby.home");
    }
    
    public static File getFileInBundle(final String symbolicName, final String path) throws IOException {
        final Bundle bundle = getBundle(symbolicName);
        if (bundle == null) {
            throw new IOException("Unable to find the bundle " + symbolicName);
        }
        return getFileInBundle(bundle, path);
    }
    
    public static File getFileInBundle(final Bundle bundle, final String path) throws IOException {
        URL url = null;
        try {
            url = getFileURL(bundle.getEntry(path));
            return new File(url.toURI());
        }
        catch (NullPointerException ne) {
            throw new IOException("Unable to find the " + path + " folder in the bundle '" + bundle.getSymbolicName() + "'; is the org.jruby.jruby bundle unzipped? ");
        }
        catch (Exception e) {
            final IOException exception = new IOException("Unable to find the " + path + " folder in the bundle '" + bundle.getSymbolicName() + "'");
            exception.initCause(e);
            throw exception;
        }
    }
    
    public static Bundle getBundle(final String symbolicName) {
        BundleContext bc = FrameworkUtil.getBundle((Class)OSGiFileLocator.class).getBundleContext();
        if (bc == null) {
            try {
                FrameworkUtil.getBundle((Class)OSGiFileLocator.class).start();
            }
            catch (BundleException e) {
                throw new IllegalStateException("Could not start the bundle " + FrameworkUtil.getBundle((Class)OSGiFileLocator.class).getSymbolicName());
            }
            bc = FrameworkUtil.getBundle((Class)OSGiFileLocator.class).getBundleContext();
            if (bc == null) {
                throw new IllegalStateException("The bundle " + FrameworkUtil.getBundle((Class)OSGiFileLocator.class).getSymbolicName() + " is not activated.");
            }
        }
        for (final Bundle b : FrameworkUtil.getBundle((Class)OSGiFileLocator.class).getBundleContext().getBundles()) {
            if (b.getSymbolicName().equals(symbolicName)) {
                return b;
            }
        }
        return null;
    }
    
    public static URL getLocalURL(final URL url) {
        if (!"bundleresource".equals(url.getProtocol())) {
            if (!"bundleentry".equals(url.getProtocol())) {
                return url;
            }
        }
        try {
            final URLConnection conn = url.openConnection();
            if (OSGiFileLocator.BUNDLE_URL_CONNECTION_getLocalURL == null && conn.getClass().getName().equals("org.eclipse.osgi.framework.internal.core.BundleURLConnection")) {
                (OSGiFileLocator.BUNDLE_URL_CONNECTION_getLocalURL = conn.getClass().getMethod("getLocalURL", (Class<?>[])new Class[0])).setAccessible(true);
            }
            if (OSGiFileLocator.BUNDLE_URL_CONNECTION_getLocalURL != null) {
                return (URL)OSGiFileLocator.BUNDLE_URL_CONNECTION_getLocalURL.invoke(conn, new Object[0]);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return url;
    }
    
    public static URL getFileURL(final URL url) {
        if (!"bundleresource".equals(url.getProtocol())) {
            if (!"bundleentry".equals(url.getProtocol())) {
                return url;
            }
        }
        try {
            final URLConnection conn = url.openConnection();
            if (OSGiFileLocator.BUNDLE_URL_CONNECTION_getFileURL == null && conn.getClass().getName().equals("org.eclipse.osgi.framework.internal.core.BundleURLConnection")) {
                (OSGiFileLocator.BUNDLE_URL_CONNECTION_getFileURL = conn.getClass().getMethod("getFileURL", (Class<?>[])new Class[0])).setAccessible(true);
            }
            if (OSGiFileLocator.BUNDLE_URL_CONNECTION_getFileURL != null) {
                return (URL)OSGiFileLocator.BUNDLE_URL_CONNECTION_getFileURL.invoke(conn, new Object[0]);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return url;
    }
    
    static {
        OSGiFileLocator.BUNDLE_URL_CONNECTION_getLocalURL = null;
        OSGiFileLocator.BUNDLE_URL_CONNECTION_getFileURL = null;
    }
}
