// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.FileNotFoundException;
import java.io.Closeable;
import java.io.ByteArrayOutputStream;
import java.util.jar.JarInputStream;
import java.util.jar.JarEntry;
import java.io.ByteArrayInputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.jar.JarFile;
import java.io.InputStream;
import java.util.Map;
import java.io.IOException;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.List;
import java.net.URL;
import java.net.URLStreamHandler;

public class CompoundJarURLStreamHandler extends URLStreamHandler
{
    public static final String PROTOCOL = "compoundjar";
    private static final CompoundJarURLStreamHandler instance;
    
    public static URL createUrl(final URL base, final List<String> path) throws MalformedURLException {
        return createUrl(base, (String[])path.toArray(new String[0]));
    }
    
    public static URL createUrl(final URL base, final String... path) throws MalformedURLException {
        final StringBuilder pathBuilder = new StringBuilder();
        if (base.getProtocol().equals("jar")) {
            pathBuilder.append(URLUtil.getPath(base));
        }
        else {
            pathBuilder.append(base.toExternalForm());
        }
        for (final String entry : path) {
            pathBuilder.append("!");
            if (!entry.startsWith("/")) {
                pathBuilder.append("/");
            }
            pathBuilder.append(entry);
        }
        return new URL("compoundjar", null, -1, pathBuilder.toString(), CompoundJarURLStreamHandler.instance);
    }
    
    protected URLConnection openConnection(final URL url) throws IOException {
        return new CompoundJarURLConnection(url);
    }
    
    static {
        instance = new CompoundJarURLStreamHandler();
    }
    
    static class CompoundJarURLConnection extends URLConnection
    {
        private final URL baseJarUrl;
        private final String[] path;
        private static final Map<String, Map<String, byte[]>> cache;
        
        CompoundJarURLConnection(final URL url) throws MalformedURLException {
            super(url);
            final String spec = URLUtil.getPath(url);
            this.path = spec.split("\\!\\/");
            this.baseJarUrl = new URL(this.path[0]);
        }
        
        public void connect() throws IOException {
            this.connected = true;
        }
        
        private InputStream openEntryWithCache(final String[] path, final InputStream currentStream, final int currentDepth) throws IOException {
            final String localPath = path[currentDepth];
            if (currentDepth == 1 && path[0].indexOf(33) == -1 && path[0].startsWith("file:")) {
                final JarFile jarFile = new JarFile(path[0].substring(5));
                final JarEntry entry = jarFile.getJarEntry(localPath);
                if (entry != null) {
                    return this.returnOrRecurse(path, jarFile.getInputStream(entry), currentDepth);
                }
                return null;
            }
            else {
                final StringBuilder pathToHereBuffer = new StringBuilder();
                for (int i = 0; i < currentDepth; ++i) {
                    if (i > 0) {
                        pathToHereBuffer.append("!/");
                    }
                    pathToHereBuffer.append(path[i]);
                }
                final String pathToHere = pathToHereBuffer.toString();
                Map<String, byte[]> contents = CompoundJarURLConnection.cache.get(pathToHere);
                if (contents == null) {
                    CompoundJarURLConnection.cache.put(pathToHere, contents = new ConcurrentHashMap<String, byte[]>(16, 0.75f, 2));
                    this.cacheJarFrom(currentStream, contents);
                }
                final byte[] bytes = contents.get(localPath);
                if (bytes != null) {
                    return this.returnOrRecurse(path, new ByteArrayInputStream(bytes), currentDepth);
                }
                return null;
            }
        }
        
        private void cacheJarFrom(final InputStream currentStream, final Map<String, byte[]> contents) throws IOException {
            final JarInputStream currentJar = new JarInputStream(currentStream);
            for (JarEntry entry = currentJar.getNextJarEntry(); entry != null; entry = currentJar.getNextJarEntry()) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int size;
                while ((size = currentJar.read(bytes, 0, 1024)) != -1) {
                    baos.write(bytes, 0, size);
                }
                bytes = baos.toByteArray();
                contents.put(entry.getName(), bytes);
            }
        }
        
        private InputStream returnOrRecurse(final String[] path, final InputStream nextStream, final int currentDepth) throws IOException {
            if (currentDepth + 1 < path.length) {
                return this.openEntryWithCache(path, nextStream, currentDepth + 1);
            }
            return nextStream;
        }
        
        private static void close(final Closeable resource) {
            if (resource != null) {
                try {
                    resource.close();
                }
                catch (IOException ex) {}
            }
        }
        
        public InputStream getInputStream() throws IOException {
            final InputStream baseInputStream = this.baseJarUrl.openStream();
            InputStream result = null;
            Label_0047: {
                if (this.path.length > 1) {
                    try {
                        result = this.openEntryWithCache(this.path, baseInputStream, 1);
                        break Label_0047;
                    }
                    catch (IOException ex) {
                        close(baseInputStream);
                        throw ex;
                    }
                    catch (RuntimeException ex2) {
                        close(baseInputStream);
                        throw ex2;
                    }
                }
                result = baseInputStream;
            }
            if (result == null) {
                throw new FileNotFoundException(this.url.toExternalForm());
            }
            return result;
        }
        
        static {
            cache = new ConcurrentHashMap<String, Map<String, byte[]>>(16, 0.75f, 4);
        }
    }
}
