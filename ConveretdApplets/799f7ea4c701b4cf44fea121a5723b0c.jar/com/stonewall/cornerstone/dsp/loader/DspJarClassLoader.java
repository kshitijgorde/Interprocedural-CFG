// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

import java.net.URL;
import java.util.jar.Attributes;
import java.io.ByteArrayInputStream;
import org.jdom.input.SAXBuilder;
import java.util.Enumeration;
import java.util.jar.JarInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.jar.Manifest;
import org.jdom.Document;
import java.util.Map;
import java.security.SecureClassLoader;

public class DspJarClassLoader extends SecureClassLoader
{
    private Map<String, Document> documents;
    private Manifest manifest;
    private Map<String, Integer> entrySizes;
    private Map<String, byte[]> entryContents;
    static final Log log;
    
    static {
        log = Log.getLog(DspJarClassLoader.class);
    }
    
    protected DspJarClassLoader() {
        super(DspJarClassLoader.class.getClassLoader());
    }
    
    protected void init(final String jarFileName) throws Exception {
        this.documents = new HashMap<String, Document>();
        this.entrySizes = new HashMap<String, Integer>();
        this.entryContents = new HashMap<String, byte[]>();
        final JarFile jar = new JarFile(jarFileName);
        this.manifest = jar.getManifest();
        final Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements()) {
            final JarEntry entry = e.nextElement();
            this.entrySizes.put(entry.getName(), new Integer((int)entry.getSize()));
        }
        jar.close();
        final FileInputStream fis = new FileInputStream(jarFileName);
        final BufferedInputStream bis = new BufferedInputStream(fis);
        final JarInputStream jis = new JarInputStream(bis);
        JarEntry je = null;
        while ((je = jis.getNextJarEntry()) != null) {
            if (je.isDirectory()) {
                continue;
            }
            int size = (int)je.getSize();
            if (size == -1) {
                size = this.entrySizes.get(je.getName());
            }
            final byte[] b = new byte[size];
            for (int rb = 0, chunk = 0; size - rb > 0; rb += chunk) {
                chunk = jis.read(b, rb, size - rb);
                if (chunk == -1) {
                    break;
                }
            }
            this.entryContents.put(je.getName(), b);
        }
    }
    
    protected Document getDocument(final String path) throws Exception {
        Document d = this.documents.get(path);
        if (d != null) {
            return d;
        }
        final InputStream istr = this.getResourceAsStream(path);
        final SAXBuilder sax = new SAXBuilder();
        d = sax.build(istr);
        this.documents.put(path, d);
        return d;
    }
    
    @Override
    public InputStream getResourceAsStream(final String name) {
        final byte[] buffer = this.entryContents.get(name);
        if (buffer != null) {
            return new ByteArrayInputStream(buffer);
        }
        return null;
    }
    
    @Override
    protected Class findClass(final String name) throws ClassNotFoundException {
        final String path = name.replace('.', '/').concat(".class");
        try {
            final byte[] buffer = this.entryContents.get(path);
            this.definePackage(name);
            return this.defineClass(name, buffer, 0, buffer.length);
        }
        catch (Exception e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }
    
    private void definePackage(final String className) {
        String pkgName = className;
        final int index = className.lastIndexOf(46);
        if (-1 != index) {
            pkgName = className.substring(0, index);
        }
        if (this.manifest == null || this.getPackage(pkgName) != null) {
            return;
        }
        final Attributes attr = this.manifest.getMainAttributes();
        if (attr != null) {
            final String specTitle = attr.getValue(Attributes.Name.SPECIFICATION_TITLE);
            final String specVersion = attr.getValue(Attributes.Name.SPECIFICATION_VERSION);
            final String specVendor = attr.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            final String implTitle = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            final String implVersion = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            final String implVendor = attr.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            this.definePackage(pkgName, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, null);
        }
    }
}
