// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import java.util.Iterator;
import com.stonewall.cornerstone.utility.HashMultiMap;
import com.stonewall.cornerstone.feature.FeatureLoader;
import java.util.StringTokenizer;
import java.io.File;
import java.net.URL;
import org.xmodel.log.Log;
import java.net.URLClassLoader;

public class ClassLoader extends URLClassLoader
{
    static final Log log;
    
    static {
        log = Log.getLog(ClassLoader.class);
    }
    
    public ClassLoader(final java.lang.ClassLoader parent) {
        super(new URL[0], parent.getParent());
        this.addSystemClasses();
        this.addFeatureClasses();
    }
    
    private void addSystemClasses() {
        try {
            final StringTokenizer tokens = new StringTokenizer(System.getProperty("java.class.path"), File.pathSeparator);
            while (tokens.hasMoreTokens()) {
                final String path = tokens.nextToken();
                final File f = new File(path);
                this.addURL(f.toURI().toURL());
            }
        }
        catch (Exception e) {
            ClassLoader.log.error("Exception loading system classpath", e);
        }
    }
    
    private void addFeatureClasses() {
        final FeatureLoader loader = new FeatureLoader();
        final HashMultiMap<FeatureLoader.Type, URL> map = loader.parsePath();
        for (final FeatureLoader.Type type : map.keySet()) {
            for (final URL url : map.getList(type)) {
                try {
                    switch (type) {
                        case dir: {
                            final File f = new File(String.valueOf(url.getFile()) + File.separator + "bin");
                            this.addURL(f.toURI().toURL());
                            this.addURL(url);
                            continue;
                        }
                        case jar: {
                            this.addURL(url);
                            continue;
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Exception while loading:" + url.getFile());
                    e.printStackTrace();
                }
            }
        }
    }
}
