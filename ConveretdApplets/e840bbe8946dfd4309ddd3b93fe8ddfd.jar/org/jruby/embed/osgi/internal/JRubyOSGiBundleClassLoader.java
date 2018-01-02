// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.net.URL;
import java.util.Enumeration;
import java.io.IOException;
import org.jruby.embed.osgi.utils.OSGiBundleClassLoaderHelper;
import org.osgi.framework.FrameworkUtil;
import org.jruby.embed.ScriptingContainer;
import org.osgi.framework.Bundle;
import java.util.LinkedHashMap;
import org.osgi.framework.BundleReference;

public class JRubyOSGiBundleClassLoader extends ClassLoader implements BundleReference
{
    private boolean _lookInOsgiFirst;
    private LinkedHashMap<Bundle, ClassLoader> _libraries;
    
    public JRubyOSGiBundleClassLoader() {
        super(ScriptingContainer.class.getClassLoader());
        this._lookInOsgiFirst = true;
        this._libraries = new LinkedHashMap<Bundle, ClassLoader>();
    }
    
    public JRubyOSGiBundleClassLoader(final Bundle creator) {
        this();
        this.addBundle(creator);
    }
    
    public void addBundle(final Class<?> classInOsgiBundle) {
        final Bundle b = FrameworkUtil.getBundle((Class)classInOsgiBundle);
        if (b == null) {
            throw new IllegalArgumentException(classInOsgiBundle + " is not loaded by a bundle. Its classloader is " + classInOsgiBundle.getClassLoader() + " does not implement " + "org.osgi.framework.BundleReference");
        }
        this._libraries.put(b, classInOsgiBundle.getClassLoader());
    }
    
    public boolean addBundle(final Bundle bundle) {
        return this._libraries.put(bundle, OSGiBundleClassLoaderHelper.getBundleClassLoader(bundle)) != null;
    }
    
    public boolean removeBundle(final Bundle bundle) throws IOException {
        return this._libraries.remove(bundle) != null;
    }
    
    public Bundle getBundle() {
        return this._libraries.keySet().iterator().next();
    }
    
    public Enumeration<URL> getResources(final String name) throws IOException {
        final LinkedList<Enumeration<URL>> enums = new LinkedList<Enumeration<URL>>();
        for (final ClassLoader cl : this._libraries.values()) {
            enums.add(cl.getResources(name));
        }
        final Enumeration<URL> urls = super.getResources(name);
        if (this._lookInOsgiFirst) {
            enums.addFirst(urls);
        }
        else {
            enums.addLast(urls);
        }
        return Collections.enumeration(this.toList(enums));
    }
    
    public URL getResource(final String name) {
        if (this._lookInOsgiFirst) {
            URL url = null;
            for (final ClassLoader cl : this._libraries.values()) {
                url = cl.getResource(name);
                if (url != null) {
                    return url;
                }
            }
            return super.getResource(name);
        }
        URL url = super.getResource(name);
        if (url != null) {
            return url;
        }
        for (final ClassLoader cl : this._libraries.values()) {
            url = cl.getResource(name);
            if (url != null) {
                return url;
            }
        }
        return null;
    }
    
    private List<URL> toList(final List<Enumeration<URL>> l) {
        final List<URL> list = new LinkedList<URL>();
        for (final Enumeration<URL> e : l) {
            while (e != null && e.hasMoreElements()) {
                list.add(e.nextElement());
            }
        }
        return list;
    }
    
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        if (this._lookInOsgiFirst) {
            for (final ClassLoader cl : this._libraries.values()) {
                try {
                    return cl.loadClass(name);
                }
                catch (ClassNotFoundException cne2) {
                    continue;
                }
                break;
            }
            return super.findClass(name);
        }
        try {
            return super.findClass(name);
        }
        catch (ClassNotFoundException cne) {
            for (final ClassLoader cl2 : this._libraries.values()) {
                try {
                    return cl2.loadClass(name);
                }
                catch (ClassNotFoundException cnfe) {
                    continue;
                }
                break;
            }
            throw cne;
        }
    }
}
