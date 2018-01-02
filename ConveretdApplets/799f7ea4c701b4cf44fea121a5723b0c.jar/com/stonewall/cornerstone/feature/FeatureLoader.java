// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.io.FileFilter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Iterator;
import com.stonewall.cornerstone.utility.HashMultiMap;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.xmodel.log.Log;

public class FeatureLoader
{
    static final Log log;
    
    static {
        log = Log.getLog(FeatureLoader.class);
    }
    
    public Map<Class, IFeature> load() {
        final HashMultiMap<Type, URL> map = this.parsePath();
        final Map<Class, IFeature> features = new HashMap<Class, IFeature>();
        for (final Type type : map.keySet()) {
            final List<URL> urls = map.getList(type);
            FeatureManager manager = null;
            for (final URL url : urls) {
                try {
                    switch (type) {
                        case dir: {
                            manager = new DirManager(new File(url.getFile()));
                            break;
                        }
                        case jar: {
                            manager = new JarManager(url);
                            break;
                        }
                    }
                    final IFeature feature = manager.getFeature();
                    FeatureLoader.log.info("\nLoading Feature:" + feature.getClass().getName());
                    features.put(feature.getClass(), feature);
                }
                catch (Exception e) {
                    FeatureLoader.log.error("Exception loading url:" + url.getPath(), e);
                }
            }
        }
        return features;
    }
    
    public HashMultiMap<Type, URL> parsePath() {
        final HashMultiMap<Type, URL> urls = new HashMultiMap<Type, URL>();
        final String classpath = System.getProperty("cornerstone.feature.home");
        if (classpath == null) {
            return urls;
        }
        FeatureLoader.log.info("processing (cornerstone.feature.home): " + classpath);
        try {
            final StringTokenizer tokens = new StringTokenizer(classpath, File.pathSeparator);
            while (tokens.hasMoreTokens()) {
                final String path = tokens.nextToken();
                final File feature = new File(path);
                if (feature.isDirectory()) {
                    final File[] files = feature.listFiles(new JarFileFilter());
                    if (files.length > 0) {
                        File[] array;
                        for (int length = (array = files).length, i = 0; i < length; ++i) {
                            final File f = array[i];
                            urls.put(Type.jar, f.toURL());
                        }
                    }
                    else {
                        urls.put(Type.dir, feature.toURL());
                    }
                }
                else {
                    if (!feature.getName().contains(".jar")) {
                        continue;
                    }
                    urls.put(Type.jar, feature.toURL());
                }
            }
        }
        catch (Exception e) {
            FeatureLoader.log.error("Exception loading classpath", e);
        }
        return urls;
    }
    
    public enum Type
    {
        dir("dir", 0), 
        jar("jar", 1);
        
        private Type(final String s, final int n) {
        }
    }
    
    public class JarFileFilter implements FileFilter
    {
        @Override
        public boolean accept(final File pathname) {
            return pathname.getName().contains(".jar");
        }
    }
}
