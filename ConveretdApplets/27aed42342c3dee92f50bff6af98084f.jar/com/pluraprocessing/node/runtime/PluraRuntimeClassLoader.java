// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.runtime;

import java.util.Enumeration;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import com.pluraprocessing.common.domain.HttpResponse;
import java.io.InputStream;
import com.pluraprocessing.common.utility.PluraHttpConnectionUtility;
import com.pluraprocessing.common.utility.HttpMethod;
import java.util.ArrayList;
import java.util.LinkedList;
import java.net.MalformedURLException;
import java.security.cert.Certificate;
import java.security.CodeSource;
import java.security.AccessController;
import java.io.IOException;
import java.util.Map;
import java.security.AccessControlContext;
import com.pluraprocessing.node.domain.JarURL;
import java.net.URL;
import java.util.HashMap;
import java.net.URLClassLoader;

public class PluraRuntimeClassLoader extends URLClassLoader
{
    private static final String EXCEPTION_TEXT = "The use of this ClassLoader method is not permitted. Please use getResourceAsStream(String name):InputStream instead.  Method signature = ";
    private static final String COMMON_JAR_NAME = "common.jar";
    private HashMap<String, JarClassEntry> jarClassEntries;
    private HashMap<String, byte[]> resources;
    private HashMap<String, Class<?>> classes;
    private HashMap<URL, JarURL> bytesInJars;
    private AccessControlContext acc;
    private String pluraServer;
    private String protectionDomain;
    
    public PluraRuntimeClassLoader(final URL[] urls, final Map<URL, byte[]> bytes, final String pluraServer, final String protectionDomain) throws IOException {
        super(urls);
        this.jarClassEntries = new HashMap<String, JarClassEntry>();
        this.resources = new HashMap<String, byte[]>();
        this.classes = new HashMap<String, Class<?>>();
        this.bytesInJars = new HashMap<URL, JarURL>();
        this.init(urls, bytes, pluraServer, protectionDomain);
    }
    
    public PluraRuntimeClassLoader(final URL[] urls, final Map<URL, byte[]> bytes, final String pluraServer, final String protectionDomain, final ClassLoader parent) throws IOException {
        super(urls, parent);
        this.jarClassEntries = new HashMap<String, JarClassEntry>();
        this.resources = new HashMap<String, byte[]>();
        this.classes = new HashMap<String, Class<?>>();
        this.bytesInJars = new HashMap<URL, JarURL>();
        this.init(urls, bytes, pluraServer, protectionDomain);
    }
    
    private void init(final URL[] urls, final Map<URL, byte[]> bytes, final String pluraServer, final String protectionDomain) throws IOException {
        this.acc = AccessController.getContext();
        this.pluraServer = pluraServer;
        this.protectionDomain = protectionDomain;
        for (final URL url : urls) {
            this.gatherResourceNamesFromURL(url, bytes.get(url));
        }
        this.loadResourcesFromAllJars();
    }
    
    private CodeSource getCodeSourceForURL(final URL url) throws MalformedURLException {
        final String[] comp = url.toString().split("/");
        final String modifiedURL = url.toString().replace(comp[comp.length - 1], "common.jar");
        if (this.pluraServer != null && this.protectionDomain != null) {
            modifiedURL.replace(this.pluraServer, this.protectionDomain);
        }
        return new CodeSource(new URL(modifiedURL), new Certificate[0]);
    }
    
    private void gatherResourceNamesFromURL(final URL url, byte[] jarBytes) throws IOException {
        CodeSource currentCodeSource = this.getCodeSourceForURL(url);
        final LinkedList<byte[]> jarsToLoad = new LinkedList<byte[]>();
        final long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        if (jarBytes == null && url != null) {
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
                    currentCodeSource = this.getCodeSourceForURL(new URL(urlStr));
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
            endTime = System.currentTimeMillis();
        }
        jarsToLoad.push(jarBytes);
        this.bytesInJars.put(url, new JarURL(jarBytes, endTime - startTime));
        if (jarsToLoad.size() > 0) {
            this.gatherResourceNamesFromJars(jarsToLoad, currentCodeSource);
        }
    }
    
    private synchronized void gatherResourceNamesFromJars(final LinkedList<byte[]> jarsToLoad, final CodeSource currentCodeSource) {
        try {
            byte[] jarBytes = null;
            do {
                if (jarsToLoad != null && !jarsToLoad.isEmpty()) {
                    jarBytes = jarsToLoad.pop();
                }
                else {
                    jarBytes = null;
                }
                if (jarBytes != null) {
                    final JarInputStream jarStream = new JarInputStream(new BufferedInputStream(new ByteArrayInputStream(jarBytes)));
                    JarEntry entry;
                    while ((entry = jarStream.getNextJarEntry()) != null) {
                        if (!entry.isDirectory()) {
                            final String className = this.formatClassName(entry.getName());
                            if (entry.getName().toLowerCase().endsWith(".class")) {
                                this.jarClassEntries.put(className, new JarClassEntry(this.readBytesFromJarStream(jarStream), currentCodeSource));
                            }
                            else if (entry.getName().toLowerCase().endsWith(".jar")) {
                                jarsToLoad.push(this.readBytesFromJarStream(jarStream));
                            }
                            else {
                                this.resources.put(className, this.readBytesFromJarStream(jarStream));
                            }
                        }
                    }
                }
            } while (jarBytes != null);
        }
        catch (IOException ex) {}
    }
    
    private synchronized void loadResourcesFromAllJars() {
        for (final String key : this.jarClassEntries.keySet()) {
            try {
                this.loadClass(key);
            }
            catch (Throwable t) {}
        }
        this.jarClassEntries = new HashMap<String, JarClassEntry>();
    }
    
    private byte[] readBytesFromJarStream(final JarInputStream jarStream) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] bytesRead = new byte[1024];
        int i = -1;
        while ((i = jarStream.read(bytesRead)) != -1) {
            out.write(bytesRead, 0, i);
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
                    final String className = PluraRuntimeClassLoader.this.formatClassName(name);
                    if (PluraRuntimeClassLoader.this.classes != null) {
                        final Class<?> result = PluraRuntimeClassLoader.this.classes.get(className);
                        if (result != null) {
                            return result;
                        }
                    }
                    if (PluraRuntimeClassLoader.this.jarClassEntries != null && PluraRuntimeClassLoader.this.jarClassEntries.get(className) != null) {
                        final Class<?> c = PluraRuntimeClassLoader.this.defineClass(className, PluraRuntimeClassLoader.this.jarClassEntries.get(className).getJarBytes(), 0, PluraRuntimeClassLoader.this.jarClassEntries.get(className).getJarBytes().length, PluraRuntimeClassLoader.this.jarClassEntries.get(className).getCodeSource());
                        PluraRuntimeClassLoader.this.classes.put(className, c);
                        return c;
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
                final byte[] bytes = PluraRuntimeClassLoader.this.resources.get(PluraRuntimeClassLoader.this.formatClassName(name));
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
    
    public HashMap<URL, JarURL> getBytesInJars() {
        return this.bytesInJars;
    }
    
    public void setBytesInJars(final HashMap<URL, JarURL> bytesInJars) {
        this.bytesInJars = bytesInJars;
    }
    
    private class JarClassEntry
    {
        private byte[] jarBytes;
        private CodeSource codeSource;
        
        public JarClassEntry(final byte[] jarBytes, final CodeSource codeSource) {
            this.jarBytes = jarBytes;
            this.codeSource = codeSource;
        }
        
        public byte[] getJarBytes() {
            return this.jarBytes;
        }
        
        public CodeSource getCodeSource() {
            return this.codeSource;
        }
    }
}
