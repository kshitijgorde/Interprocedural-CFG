// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;

class ServiceFinder
{
    private ClassLoader loader;
    private String serviceName;
    private HashSet<?> services;
    
    ServiceFinder(final ClassLoader loader, final String serviceName) throws IOException {
        this.loader = null;
        this.serviceName = serviceName;
        final Enumeration<URL> urls = this.findResources(loader);
        final List<String> classNames = this.getClassNames(urls);
        this.services = this.instantiateClasses(classNames);
    }
    
    HashSet<?> getServices() {
        return this.services;
    }
    
    private Enumeration<URL> findResources(final ClassLoader loader) throws IOException {
        Enumeration<URL> urls;
        if (loader == null) {
            this.loader = ClassLoader.getSystemClassLoader();
            urls = ClassLoader.getSystemResources(this.serviceName);
        }
        else {
            this.loader = loader;
            urls = loader.getResources(this.serviceName);
        }
        return urls;
    }
    
    private List<String> getClassNames(final Enumeration<URL> urls) {
        final String encoding = System.getProperty("file.encoding");
        final List<String> names = new ArrayList<String>();
        URL url = null;
        while (urls.hasMoreElements()) {
            try {
                url = urls.nextElement();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
                String line;
                while ((line = reader.readLine()) != null) {
                    if ((line = this.deleteComments(line)) != null) {
                        names.add(line);
                    }
                }
            }
            catch (IOException e) {
                System.err.println("Failed to get a class name from " + url.toString());
            }
        }
        return names;
    }
    
    private String deleteComments(final String line) {
        if (line.startsWith("#")) {
            return null;
        }
        if (line.length() < 1) {
            return null;
        }
        final StringTokenizer st = new StringTokenizer(line, "#");
        return ((String)st.nextElement()).trim();
    }
    
    private <T> HashSet<T> instantiateClasses(final List<String> names) {
        final HashSet<T> instances = new HashSet<T>();
        for (final String name : names) {
            try {
                final Class clazz = Class.forName(name, true, this.loader);
                final T instance = clazz.newInstance();
                instances.add(instance);
            }
            catch (ClassNotFoundException e) {
                System.err.println(name + " was not found");
            }
            catch (InstantiationException e2) {
                System.err.println(name + " was not instantiated");
            }
            catch (IllegalAccessException e3) {
                System.err.println(name + " committed illegal access");
            }
            catch (Throwable e4) {
                System.err.println("failed to instantiate " + name);
            }
        }
        return instances;
    }
}
