// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.io.File;
import java.net.URL;
import org.xmodel.log.Log;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class JarManager implements FeatureManager
{
    private JarFile jar;
    private URLClassLoader loader;
    static final Log log;
    
    static {
        log = Log.getLog(JarManager.class);
    }
    
    public JarManager(final URL url) throws Exception {
        final File file = new File(url.toURI());
        this.jar = new JarFile(file, false, 1);
        this.loader = new URLClassLoader(new URL[] { url }, (ClassLoader)null);
    }
    
    @Override
    public IFeature getFeature() {
        String name = null;
        try {
            final Enumeration<JarEntry> e = this.jar.entries();
            while (e.hasMoreElements()) {
                final JarEntry entry = e.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                if (!entry.getName().contains("Feature")) {
                    continue;
                }
                name = entry.getName();
                name = name.replace("/", ".");
            }
            if (name != null) {
                name = name.substring(0, name.indexOf(".class"));
                final Class c = ClassLoader.getSystemClassLoader().loadClass(name);
                final Constructor con = c.getDeclaredConstructor(FeatureManager.class);
                return con.newInstance(this);
            }
        }
        catch (Exception e2) {
            JarManager.log.error(this, e2);
        }
        return null;
    }
    
    @Override
    public List<URL> getResourcesInDirectory(final String dir, final List<String> extensions) {
        final List<URL> resources = new ArrayList<URL>();
        try {
            final Enumeration<JarEntry> e = this.jar.entries();
            while (e.hasMoreElements()) {
                final JarEntry entry = e.nextElement();
                if (entry.getName().contains(dir)) {
                    if (entry.isDirectory()) {
                        continue;
                    }
                    for (final String extension : extensions) {
                        if (entry.getName().endsWith(extension)) {
                            final URL url = this.loader.getResource(entry.getName());
                            resources.add(url);
                        }
                    }
                }
            }
        }
        catch (Exception e2) {
            JarManager.log.error(this, e2);
        }
        return resources;
    }
    
    @Override
    public URL getResource(final String name) {
        try {
            final Enumeration<JarEntry> e = this.jar.entries();
            while (e.hasMoreElements()) {
                final JarEntry entry = e.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                if (entry.getName().equals(name)) {
                    return this.loader.getResource(entry.getName());
                }
            }
        }
        catch (Exception e2) {
            JarManager.log.error(this, e2);
        }
        return null;
    }
}
