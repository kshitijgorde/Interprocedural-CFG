// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

import org.jdom.Document;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.HashMap;
import java.io.File;
import org.xmodel.log.Log;
import java.util.Map;
import com.stonewall.cornerstone.component.ProductDescriptor;

public class Manifest
{
    private String hardware;
    private String vendor;
    private ProductDescriptor productDescriptor;
    private DspURLClassLoader cl;
    private Map<String, String> manifest;
    static final Log log;
    
    static {
        log = Log.getLog(Manifest.class);
    }
    
    protected void init(final File jarFile, final String manifestName) throws Exception {
        (this.cl = new DspURLClassLoader()).init(jarFile.toURL());
        this.manifest = new HashMap<String, String>();
        final ModelBuilder builder = new ModelBuilder();
        final IModelObject root = builder.buildModel(this.cl.getResourceAsStream(manifestName));
        this.hardware = Xlate.get(root.getFirstChild("dsp:hardware"), (String)null);
        this.vendor = Xlate.get(root.getFirstChild("dsp:vendor"), (String)null);
        final List<IModelObject> entries = root.getChildren("dsp:entry");
        for (final IModelObject entry : entries) {
            final String software = Xlate.get(entry, "software", (String)null);
            final List<IModelObject> resources = entry.getChildren("dsp:resource");
            for (final IModelObject resource : resources) {
                final String key = String.valueOf(software) + resource.getID();
                this.manifest.put(key, Xlate.get(resource, (String)null));
            }
        }
    }
    
    protected Class<?> loadClass(final String software, final String name) throws ClassNotFoundException {
        final String path = this.determinePath(software, name);
        if (path != null) {
            return this.cl.loadClass(path);
        }
        Manifest.log.error("Cannot load class:" + software + name);
        return null;
    }
    
    protected Object newInstance(final String software, final String name, final Object... constructorArgs) throws Exception {
        final String path = this.determinePath(software, name);
        if (path != null) {
            final Class c = this.cl.loadClass(path);
            final Class[] constClasses = new Class[constructorArgs.length];
            for (int i = 0; i < constructorArgs.length; ++i) {
                constClasses[i] = constructorArgs[i].getClass();
            }
            final Constructor con = c.getDeclaredConstructor((Class[])constClasses);
            return con.newInstance(constructorArgs);
        }
        Manifest.log.error("Cannot create instance:" + software + name);
        return null;
    }
    
    protected InputStream getResourceAsStream(final String software, final String name) {
        final String path = this.determinePath(software, name);
        if (path != null) {
            return this.cl.getResourceAsStream(path);
        }
        Manifest.log.error("Cannot load resource:" + software + name);
        return null;
    }
    
    protected Document getDocument(final String software, final String name) throws Exception {
        final String path = this.determinePath(software, name);
        if (path != null) {
            return this.cl.getDocument(path);
        }
        Manifest.log.error("Cannot load document:" + software + name);
        return null;
    }
    
    protected String getHardware() {
        return this.hardware;
    }
    
    protected String getVendor() {
        return this.vendor;
    }
    
    protected ProductDescriptor getProductDescriptor() {
        return this.productDescriptor;
    }
    
    public String determinePath(final String software, final String name) {
        final String key = String.valueOf(software) + name;
        return this.manifest.get(key);
    }
}
