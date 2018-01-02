// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.ArrayList;
import java.net.URL;
import java.util.List;
import java.lang.reflect.Constructor;
import org.xmodel.log.Log;
import java.io.File;

public class DirManager implements FeatureManager
{
    private File basedir;
    static final Log log;
    
    static {
        log = Log.getLog(DirManager.class);
    }
    
    public DirManager(final File file) throws Exception {
        this.basedir = file;
    }
    
    @Override
    public IFeature getFeature() {
        try {
            final File classdir = new File(String.valueOf(this.basedir.getAbsolutePath()) + File.separator + "bin");
            final File result = this.searchDirectory(classdir, "Feature.class", false);
            if (result != null) {
                String name = result.getAbsolutePath();
                final String dirname = classdir.getAbsolutePath();
                name = name.substring(dirname.length() + 1, name.length());
                name = name.replace(File.separator, ".");
                if (name != null) {
                    name = name.substring(0, name.indexOf(".class"));
                    final Class c = ClassLoader.getSystemClassLoader().loadClass(name);
                    final Constructor con = c.getDeclaredConstructor(FeatureManager.class);
                    return con.newInstance(this);
                }
            }
        }
        catch (Exception e) {
            DirManager.log.error(this, e);
        }
        return null;
    }
    
    private File searchDirectory(final File dir, final String name, final boolean fullyQualified) {
        final File[] files = dir.listFiles();
        File[] array;
        for (int length = (array = files).length, i = 0; i < length; ++i) {
            final File file = array[i];
            if (file.isDirectory()) {
                final File result = this.searchDirectory(file, name, fullyQualified);
                if (result != null) {
                    return result;
                }
            }
            final String filename = file.getName();
            int index = 0;
            if (fullyQualified) {
                index = filename.lastIndexOf(File.separatorChar) + 1;
            }
            if (filename.substring(index, filename.length()).equals(name)) {
                return file;
            }
        }
        return null;
    }
    
    @Override
    public List<URL> getResourcesInDirectory(final String dir, final List<String> extensions) {
        final List<URL> resources = new ArrayList<URL>();
        try {
            final File file = new File(this.basedir + File.separator + dir);
            final File[] files = file.listFiles();
            File[] array;
            for (int length = (array = files).length, i = 0; i < length; ++i) {
                final File f = array[i];
                for (final String extension : extensions) {
                    if (f.getName().endsWith(extension)) {
                        resources.add(f.toURL());
                    }
                }
            }
        }
        catch (Exception e) {
            DirManager.log.error(this, e);
        }
        return resources;
    }
    
    @Override
    public URL getResource(final String name) {
        final File file = new File(this.basedir + File.separator + name);
        try {
            return file.toURL();
        }
        catch (MalformedURLException e) {
            DirManager.log.error(this, e);
            return null;
        }
    }
}
