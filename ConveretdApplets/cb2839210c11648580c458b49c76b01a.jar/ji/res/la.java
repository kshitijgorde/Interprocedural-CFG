// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import java.io.RandomAccessFile;
import java.io.File;
import ji.util.d;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.io.IOException;
import sun.misc.Resource;
import sun.misc.URLClassPath;
import java.net.URL;
import java.util.Hashtable;
import java.security.ProtectionDomain;
import java.net.URLClassLoader;

public class la extends URLClassLoader
{
    String a;
    ProtectionDomain b;
    ClassLoader c;
    Hashtable d;
    URL e;
    boolean f;
    private URLClassPath g;
    
    public void a() {
        this.c = null;
        this.e = null;
        this.g = null;
        while (this.d.size() > 0) {
            this.d.remove(this.d.keys().nextElement());
        }
    }
    
    public la(final Class clazz, final URL[] array, final String a) {
        super(array);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new Hashtable(200);
        this.e = null;
        this.f = false;
        this.g = null;
        this.c = Thread.currentThread().getContextClassLoader();
        this.g = new URLClassPath(array);
        this.b = clazz.getProtectionDomain();
        if (a.endsWith("/") || a.endsWith("\\")) {
            this.a = a;
        }
        else {
            this.a = String.valueOf(String.valueOf(a)).concat("/");
        }
    }
    
    public final void a(final boolean f) {
        this.f = f;
    }
    
    public synchronized Class loadClass(final String s) throws ClassNotFoundException {
        Class<?> clazz;
        if (!this.f || s.toLowerCase().startsWith("java")) {
            clazz = super.loadClass(s);
        }
        else {
            this.f = false;
            clazz = this.d.get(s);
            if (clazz == null) {
                if (clazz == null) {
                    clazz = (Class<?>)this.findClass(s);
                }
                if (clazz == null) {
                    clazz = super.loadClass(s);
                }
                if (clazz != null) {
                    this.d.put(s, clazz);
                }
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(s);
        }
        return clazz;
    }
    
    public Class findClass(final String s) throws ClassNotFoundException {
        Class<?> clazz = null;
        final byte[] a = this.a(s);
        if (a != null) {
            clazz = this.defineClass(s, a, 0, a.length, this.b);
        }
        else if (this.g != null) {
            final Resource resource = this.g.getResource(s.replace('.', '/').concat(".class"), false);
            if (resource != null) {
                clazz = (Class<?>)this.defineClass(s, resource);
            }
        }
        return clazz;
    }
    
    private Class defineClass(final String s, final Resource resource) throws ClassNotFoundException {
        try {
            final int lastIndex = s.lastIndexOf(46);
            final URL codeSourceURL = resource.getCodeSourceURL();
            if (lastIndex != -1) {
                final String substring = s.substring(0, lastIndex);
                final Package package1 = this.getPackage(substring);
                final Manifest manifest = resource.getManifest();
                if (package1 != null) {
                    if (package1.isSealed()) {
                        if (!package1.isSealed(codeSourceURL)) {
                            throw new SecurityException(String.valueOf(String.valueOf(new StringBuffer("sealing violation: package ").append(substring).append(" is sealed"))));
                        }
                    }
                    else if (manifest != null && this.isSealed(substring, manifest)) {
                        throw new SecurityException(String.valueOf(String.valueOf(new StringBuffer("sealing violation: can't seal package ").append(substring).append(": already loaded"))));
                    }
                }
                else if (manifest != null) {
                    this.definePackage(substring, manifest, codeSourceURL);
                }
                else {
                    this.definePackage(substring, null, null, null, null, null, null, null);
                }
            }
            final byte[] bytes = resource.getBytes();
            return this.defineClass(s, bytes, 0, bytes.length, this.b);
        }
        catch (SecurityException ex) {
            return null;
        }
        catch (IllegalArgumentException ex2) {
            return null;
        }
        catch (IOException ex3) {
            return null;
        }
    }
    
    private boolean isSealed(final String s, final Manifest manifest) {
        final Attributes attributes = manifest.getAttributes(s.replace('.', '/').concat("/"));
        String s2 = null;
        if (attributes != null) {
            s2 = attributes.getValue(Attributes.Name.SEALED);
        }
        final Attributes mainAttributes;
        if (s2 == null && (mainAttributes = manifest.getMainAttributes()) != null) {
            s2 = mainAttributes.getValue(Attributes.Name.SEALED);
        }
        return "true".equalsIgnoreCase(s2);
    }
    
    byte[] a(final String s) {
        byte[] array = null;
        try {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append(ji.util.d.b(s, ".", "/")).append(".class")));
            if (new File(value).exists()) {
                final RandomAccessFile randomAccessFile = new RandomAccessFile(value, "r");
                array = new byte[(int)randomAccessFile.length()];
                randomAccessFile.read(array);
                randomAccessFile.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
}
