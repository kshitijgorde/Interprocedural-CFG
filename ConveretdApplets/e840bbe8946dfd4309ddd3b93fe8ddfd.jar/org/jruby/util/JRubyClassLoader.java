// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Set;
import java.net.URL;
import java.util.Map;
import java.security.ProtectionDomain;
import java.net.URLClassLoader;

public class JRubyClassLoader extends URLClassLoader implements ClassDefiningClassLoader
{
    private static final ProtectionDomain DEFAULT_DOMAIN;
    private final Map<URL, Set<String>> jarIndexes;
    private Runnable unloader;
    
    public JRubyClassLoader(final ClassLoader parent) {
        super(new URL[0], parent);
        this.jarIndexes = new LinkedHashMap<URL, Set<String>>();
    }
    
    public void addURL(final URL url) {
        super.addURL(url);
        this.indexJarContents(url);
    }
    
    public void tearDown(final boolean debug) {
        try {
            this.getJDBCDriverUnloader().run();
        }
        catch (Exception e) {
            if (debug) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    public synchronized Runnable getJDBCDriverUnloader() {
        if (this.unloader == null) {
            try {
                final InputStream unloaderStream = this.getClass().getResourceAsStream("/org/jruby/util/JDBCDriverUnloader.class");
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final byte[] buf = new byte[4096];
                int bytesRead;
                while ((bytesRead = unloaderStream.read(buf)) != -1) {
                    baos.write(buf, 0, bytesRead);
                }
                final Class unloaderClass = this.defineClass("org.jruby.util.JDBCDriverUnloader", baos.toByteArray());
                this.unloader = unloaderClass.newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return this.unloader;
    }
    
    public Class<?> defineClass(final String name, final byte[] bytes) {
        return super.defineClass(name, bytes, 0, bytes.length, JRubyClassLoader.DEFAULT_DOMAIN);
    }
    
    public Class<?> defineClass(final String name, final byte[] bytes, final ProtectionDomain domain) {
        return super.defineClass(name, bytes, 0, bytes.length, domain);
    }
    
    protected Class<?> findClass(final String className) throws ClassNotFoundException {
        try {
            return super.findClass(className);
        }
        catch (ClassNotFoundException ex) {
            final String resourceName = className.replace('.', '/').concat(".class");
            URL classUrl = null;
            synchronized (this.jarIndexes) {
                for (final URL jarUrl : this.jarIndexes.keySet()) {
                    if (this.jarIndexes.get(jarUrl).contains(resourceName)) {
                        try {
                            classUrl = CompoundJarURLStreamHandler.createUrl(jarUrl, resourceName);
                            break;
                        }
                        catch (IOException ex2) {}
                    }
                }
            }
            if (classUrl != null) {
                try {
                    final InputStream input = classUrl.openStream();
                    try {
                        final byte[] buffer = new byte[4096];
                        final ByteArrayOutputStream output = new ByteArrayOutputStream();
                        for (int count = input.read(buffer); count > 0; count = input.read(buffer)) {
                            output.write(buffer, 0, count);
                        }
                        final byte[] data = output.toByteArray();
                        return this.defineClass(className, data, 0, data.length);
                    }
                    finally {
                        close(input);
                    }
                }
                catch (IOException ex3) {}
            }
            throw ex;
        }
    }
    
    public URL findResource(final String resourceName) {
        final URL result = super.findResource(resourceName);
        if (result == null) {
            synchronized (this.jarIndexes) {
                for (final URL jarUrl : this.jarIndexes.keySet()) {
                    if (this.jarIndexes.get(jarUrl).contains(resourceName)) {
                        try {
                            return CompoundJarURLStreamHandler.createUrl(jarUrl, resourceName);
                        }
                        catch (IOException ex) {}
                    }
                }
            }
        }
        return result;
    }
    
    public Enumeration<URL> findResources(final String resourceName) throws IOException {
        final List<URL> embeddedUrls = new ArrayList<URL>();
        synchronized (this.jarIndexes) {
            for (final URL jarUrl : this.jarIndexes.keySet()) {
                if (this.jarIndexes.get(jarUrl).contains(resourceName)) {
                    try {
                        embeddedUrls.add(CompoundJarURLStreamHandler.createUrl(jarUrl, resourceName));
                    }
                    catch (IOException ex) {}
                }
            }
        }
        if (embeddedUrls.isEmpty()) {
            return super.findResources(resourceName);
        }
        final Enumeration<URL> originalResult = super.findResources(resourceName);
        return new Enumeration<URL>() {
            private Iterator<URL> extendedResult;
            
            public URL nextElement() {
                if (this.extendedResult == null) {
                    return originalResult.nextElement();
                }
                return this.extendedResult.next();
            }
            
            public boolean hasMoreElements() {
                if (this.extendedResult == null) {
                    boolean result = originalResult.hasMoreElements();
                    if (!result) {
                        this.extendedResult = embeddedUrls.iterator();
                        result = this.extendedResult.hasNext();
                    }
                    return result;
                }
                return this.extendedResult.hasNext();
            }
        };
    }
    
    private void indexJarContents(final URL jarUrl) {
        final String proto = jarUrl.getProtocol();
        if (proto.equals("jar") || proto.equals("compoundjar")) {
            synchronized (this.jarIndexes) {
                final Set<String> entries = new HashSet<String>();
                this.jarIndexes.put(jarUrl, entries);
                try {
                    final InputStream baseInputStream = jarUrl.openStream();
                    try {
                        final JarInputStream baseJar = new JarInputStream(baseInputStream);
                        for (JarEntry entry = baseJar.getNextJarEntry(); entry != null; entry = baseJar.getNextJarEntry()) {
                            entries.add(entry.getName());
                        }
                    }
                    finally {
                        close(baseInputStream);
                    }
                }
                catch (IOException ex) {}
            }
        }
    }
    
    private static void close(final Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            }
            catch (IOException ex) {}
        }
    }
    
    static {
        DEFAULT_DOMAIN = JRubyClassLoader.class.getProtectionDomain();
    }
}
