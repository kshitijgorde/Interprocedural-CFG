// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.component.ProductDescriptor;
import org.jdom.Document;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.HashMap;
import java.io.File;
import org.xmodel.log.Log;
import java.util.Map;

public class Loader extends ClassLoader
{
    private static Map<String, Manifest> manifests;
    private String hardware;
    private String software;
    static final Log log;
    
    static {
        log = Log.getLog(Loader.class);
    }
    
    public static void init(final File[] files) throws Exception {
        Loader.manifests = new HashMap<String, Manifest>();
        for (int i = 0; i < files.length; ++i) {
            final File f = files[i];
            if (f.getName().endsWith(".jar")) {
                final JarFile jar = new JarFile(f, false, 1);
                final Enumeration<JarEntry> e = jar.entries();
                while (e.hasMoreElements()) {
                    final JarEntry entry = e.nextElement();
                    if (entry.getName().contains("manifest")) {
                        final Manifest manifest = new Manifest();
                        manifest.init(f, entry.getName());
                        Loader.manifests.put(manifest.getHardware(), manifest);
                        break;
                    }
                }
            }
        }
        Loader.log.info("Manifests loaded");
    }
    
    public static Class<?> loadClass(final String software, final String hardware, final String name) throws LoaderException {
        try {
            return Loader.manifests.get(hardware).loadClass(software, name);
        }
        catch (ClassNotFoundException e) {
            Loader.log.debug("Failed to load class=" + name + " software=" + software + " hardware=" + hardware, e);
            throw new LoaderException(e);
        }
    }
    
    public static Object newInstance(final String software, final String hardware, final String name, final Object... constructorArgs) throws LoaderException {
        try {
            return Loader.manifests.get(hardware).newInstance(software, name, constructorArgs);
        }
        catch (Exception e) {
            final String error = "Failed to create instance of class=" + name + " software=" + software + " hardware=" + hardware + " args=" + constructorArgs;
            Loader.log.debug(error, e);
            throw new LoaderException(error);
        }
    }
    
    public static InputStream getResourceAsStream(final String software, final String hardware, final String name) throws LoaderException {
        final InputStream stream = Loader.manifests.get(hardware).getResourceAsStream(software, name);
        if (stream == null) {
            Loader.log.debug("Cannot load resource name=" + name + " for software=" + software + " and hardware=" + hardware);
            throw new LoaderException("Cannot load resource name=" + name + " for software=" + software + " and hardware=" + hardware);
        }
        return stream;
    }
    
    public static Document getDocument(final String software, final String hardware, final String name) throws LoaderException {
        try {
            return Loader.manifests.get(hardware).getDocument(software, name);
        }
        catch (Exception e) {
            Loader.log.debug("Cannot load document name=" + name + " for software=" + software + " and hardware=" + hardware);
            throw new LoaderException(e);
        }
    }
    
    public static ProductDescriptor getProductDescriptor(final String hardware) {
        return Loader.manifests.get(hardware).getProductDescriptor();
    }
    
    public Loader(final String software, final String hardware) {
        this.software = software;
        this.hardware = hardware;
    }
    
    public Loader(final Device device) {
        this.software = device.getSoftware();
        this.hardware = device.getHardware();
    }
    
    @Override
    public Class<?> loadClass(final String name) throws ClassNotFoundException {
        try {
            return loadClass(this.software, this.hardware, name);
        }
        catch (LoaderException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }
    
    @Override
    public InputStream getResourceAsStream(final String name) {
        try {
            return getResourceAsStream(this.software, this.hardware, name);
        }
        catch (LoaderException e) {
            Loader.log.debug("Failed to load resource=" + name + " software=" + this.software + " hardware=" + this.hardware);
            return null;
        }
    }
    
    public Object newInstance(final String name, final Object... constructorArgs) throws LoaderException {
        return newInstance(this.software, this.hardware, name, constructorArgs);
    }
    
    public String getPath(final String name) {
        return Loader.manifests.get(this.hardware).determinePath(this.software, name);
    }
    
    public String getSoftware() {
        return this.software;
    }
    
    public String getHardware() {
        return this.hardware;
    }
}
