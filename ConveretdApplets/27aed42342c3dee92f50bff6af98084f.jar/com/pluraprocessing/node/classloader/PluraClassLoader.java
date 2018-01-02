// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.classloader;

import java.util.Enumeration;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.io.ByteArrayOutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import com.pluraprocessing.common.domain.HttpResponse;
import java.io.InputStream;
import com.pluraprocessing.common.utility.PluraHttpConnectionUtility;
import com.pluraprocessing.common.utility.HttpMethod;
import java.util.ArrayList;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.security.AccessController;
import java.io.IOException;
import java.security.AccessControlContext;
import java.security.CodeSource;
import java.util.LinkedList;
import java.net.URL;
import java.util.HashMap;
import java.net.URLClassLoader;

@Deprecated
public class PluraClassLoader extends URLClassLoader
{
    private static final String EXCEPTION_TEXT = "The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = ";
    private HashMap<String, byte[]> jarClassEntries;
    private HashMap<String, byte[]> resources;
    private HashMap<String, Class<?>> classes;
    private HashMap<URL, Boolean> loadedURLs;
    private LinkedList<byte[]> jarsToLoad;
    private CodeSource currentCodeSource;
    private AccessControlContext acc;
    
    public PluraClassLoader(final URL[] urls, final Boolean[] loadUrlNowPairs) throws IOException {
        super(urls);
        this.jarClassEntries = new HashMap<String, byte[]>();
        this.resources = new HashMap<String, byte[]>();
        this.classes = new HashMap<String, Class<?>>();
        this.loadedURLs = new HashMap<URL, Boolean>();
        this.jarsToLoad = new LinkedList<byte[]>();
        this.setup(urls, loadUrlNowPairs);
    }
    
    public PluraClassLoader(final URL[] urls, final Boolean[] loadUrlNowPairs, final ClassLoader parent) throws IOException {
        super(urls, parent);
        this.jarClassEntries = new HashMap<String, byte[]>();
        this.resources = new HashMap<String, byte[]>();
        this.classes = new HashMap<String, Class<?>>();
        this.loadedURLs = new HashMap<URL, Boolean>();
        this.jarsToLoad = new LinkedList<byte[]>();
        this.setup(urls, loadUrlNowPairs);
    }
    
    private void setup(final URL[] urls, final Boolean[] loadUrlNowPairs) throws IOException {
        this.acc = AccessController.getContext();
        if (loadUrlNowPairs != null) {
            for (int i = 0; i < loadUrlNowPairs.length; ++i) {
                if (loadUrlNowPairs[i]) {
                    this.loadClassesInURL(urls[i]);
                    this.loadedURLs.put(urls[i], true);
                }
                else {
                    this.loadedURLs.put(urls[i], false);
                }
            }
        }
    }
    
    public void loadAllClassesInUnloadedURLs() throws IOException {
        for (final URL url : this.loadedURLs.keySet()) {
            if (!this.loadedURLs.get(url)) {
                this.loadClassesInURL(url);
                this.loadedURLs.put(url, true);
            }
        }
    }
    
    private synchronized void loadClassesInURL(final URL url) throws IOException {
        byte[] jarBytes = null;
        this.currentCodeSource = new CodeSource(url, new Certificate[0]);
        try {
            if (url != null) {
                if (url.getProtocol().startsWith("file") || url.getProtocol().startsWith("jar")) {
                    final InputStream in = url.openStream();
                    final ArrayList<Byte> bytes = new ArrayList<Byte>();
                    int b;
                    while ((b = in.read()) != -1) {
                        bytes.add((byte)b);
                    }
                    jarBytes = new byte[bytes.size()];
                    for (int i = 0; i < bytes.size(); ++i) {
                        jarBytes[i] = bytes.get(i);
                    }
                    if (url.getProtocol().startsWith("jar")) {
                        String urlStr = url.toString();
                        urlStr = urlStr.replace("jar:", "");
                        urlStr = urlStr.split("!")[0];
                        this.currentCodeSource = new CodeSource(new URL(urlStr), new Certificate[0]);
                    }
                }
                else {
                    String uri = url.getPath();
                    if (url.getQuery() != null && !url.getQuery().trim().equals("")) {
                        uri = String.valueOf(uri) + "?" + url.getQuery();
                    }
                    final int port = (url.getPort() == -1) ? 80 : url.getPort();
                    final HttpResponse response = PluraHttpConnectionUtility.httpRequestByDomain("http://" + url.getHost(), port, uri, "", null, HttpMethod.GET);
                    jarBytes = response.getResponseBody();
                }
            }
            this.jarsToLoad.push(jarBytes);
        }
        finally {
            this.currentCodeSource = null;
        }
        this.currentCodeSource = null;
        while (this.jarsToLoad.size() > 0) {
            try {
                this.loadClassesFromJarBytes(this.jarsToLoad.pop());
            }
            catch (IOException ex) {}
        }
        this.jarClassEntries = new HashMap<String, byte[]>();
        this.jarsToLoad = new LinkedList<byte[]>();
    }
    
    public synchronized void loadClassesFromJarBytes(final byte[] jarBytes) throws IOException {
        if (jarBytes != null) {
            final JarInputStream jarStream = new JarInputStream(new BufferedInputStream(new ByteArrayInputStream(jarBytes)));
            JarEntry entry;
            while ((entry = jarStream.getNextJarEntry()) != null) {
                if (!entry.isDirectory()) {
                    final String className = this.formatClassName(entry.getName());
                    if (entry.getName().toLowerCase().endsWith(".class")) {
                        this.jarClassEntries.put(className, this.readBytesFromJarStream(jarStream));
                    }
                    else if (entry.getName().toLowerCase().endsWith(".jar")) {
                        this.jarsToLoad.push(this.readBytesFromJarStream(jarStream));
                    }
                    else {
                        this.resources.put(className, this.readBytesFromJarStream(jarStream));
                    }
                }
            }
        }
        for (final String key : this.jarClassEntries.keySet()) {
            try {
                this.loadClass(key);
            }
            catch (Throwable t) {}
        }
    }
    
    private byte[] readBytesFromJarStream(final JarInputStream jarStream) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        int byteRead;
        while ((byteRead = jarStream.read()) != -1) {
            out.write(byteRead);
        }
        return out.toByteArray();
    }
    
    private String formatClassName(String className) {
        if (className.startsWith("/")) {
            className = className.replaceFirst("/", "");
        }
        return className.replace(".class", "").replace('/', '.');
    }
    
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Class<?>>)new PrivilegedExceptionAction<Class<?>>() {
                @Override
                public Class<?> run() throws ClassNotFoundException {
                    final String className = PluraClassLoader.this.formatClassName(name);
                    if (PluraClassLoader.this.classes != null) {
                        final Class<?> result = PluraClassLoader.this.classes.get(className);
                        if (result != null) {
                            return result;
                        }
                    }
                    if (PluraClassLoader.this.jarClassEntries != null) {
                        final byte[] entry = PluraClassLoader.this.jarClassEntries.get(className);
                        if (entry != null) {
                            final Class<?> c = PluraClassLoader.this.defineClass(className, entry, 0, entry.length, PluraClassLoader.this.currentCodeSource);
                            PluraClassLoader.this.classes.put(className, c);
                            return c;
                        }
                    }
                    throw new ClassNotFoundException(name);
                }
            }, this.acc);
        }
        catch (PrivilegedActionException pae) {
            throw (ClassNotFoundException)pae.getException();
        }
    }
    
    @Override
    public InputStream getResourceAsStream(final String name) {
        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new PrivilegedAction<InputStream>() {
            @Override
            public InputStream run() {
                final byte[] bytes = PluraClassLoader.this.resources.get(PluraClassLoader.this.formatClassName(name));
                if (bytes != null) {
                    return new ByteArrayInputStream(bytes);
                }
                return null;
            }
        }, this.acc);
    }
    
    @Override
    public URL findResource(final String name) {
        throw new SecurityException("The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = findResource(String):URL");
    }
    
    @Override
    public Enumeration<URL> findResources(final String name) throws IOException {
        throw new SecurityException("The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = findResources(String):Enumeration<URL>");
    }
    
    @Override
    public URL getResource(final String name) {
        throw new SecurityException("The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = getResource(String):URL");
    }
    
    @Override
    public Enumeration<URL> getResources(final String name) throws IOException {
        throw new SecurityException("The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = getResources(String):Enumeration<URL>");
    }
}
