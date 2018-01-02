// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import java.net.URLClassLoader;
import java.util.Collection;
import java.io.File;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.net.URL;
import java.util.Properties;

public class ServerLoader
{
    public static final String DEFAULT_BOOT_LIBRARY_LIST = "log4j-boot.jar,jboss-common.jar,jboss-system.jar,jboss-xml-binding.jar";
    public static final String DEFAULT_SERVER_TYPE = "org.jboss.system.server.ServerImpl";
    protected Properties props;
    protected URL libraryURL;
    protected List extraClasspath;
    
    public ServerLoader(final Properties props) throws Exception {
        this.extraClasspath = new LinkedList();
        if (props == null) {
            throw new IllegalArgumentException("props is null");
        }
        this.props = props;
        final URL homeURL = this.getURL("jboss.home.url");
        if (homeURL == null) {
            throw new Exception("Missing configuration value for: jboss.home.url");
        }
        this.libraryURL = this.getURL("jboss.lib.url");
        if (this.libraryURL == null) {
            this.libraryURL = new URL(homeURL, "lib/");
        }
        if (homeURL.getProtocol().startsWith("http")) {
            this.addLibrary("webdavlib.jar");
            this.addLibrary("commons-httpclient.jar");
            this.addLibrary("commons-codec.jar");
            this.addLibrary("commons-logging.jar");
        }
    }
    
    public void addLibrary(final String filename) throws MalformedURLException {
        if (filename == null) {
            throw new IllegalArgumentException("filename is null");
        }
        final URL jarURL = new URL(this.libraryURL, filename);
        this.extraClasspath.add(jarURL);
    }
    
    public void addLibraries(final String filenames) throws MalformedURLException {
        if (filenames == null) {
            throw new IllegalArgumentException("filenames is null");
        }
        final StringTokenizer stok = new StringTokenizer(filenames, ",");
        while (stok.hasMoreElements()) {
            this.addLibrary(stok.nextToken().trim());
        }
    }
    
    public void addURL(final URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url is null");
        }
        this.extraClasspath.add(url);
    }
    
    public void addEndorsedJars() throws MalformedURLException {
        final File endorsedDir = new File(this.libraryURL.getPath() + "/endorsed");
        if (endorsedDir.exists()) {
            final String[] list = endorsedDir.list();
            for (int i = 0; list != null && i < list.length; ++i) {
                final String jarname = list[i];
                this.addLibrary("endorsed/" + jarname);
            }
        }
    }
    
    protected URL getURL(final String name) throws MalformedURLException {
        String value = this.props.getProperty(name, null);
        if (value != null) {
            if (!value.endsWith("/")) {
                value += "/";
            }
            return new URL(value);
        }
        return null;
    }
    
    protected URL[] getBootClasspath() throws MalformedURLException {
        final List list = new LinkedList();
        list.addAll(this.extraClasspath);
        final String value = this.props.getProperty("jboss.boot.library.list", "log4j-boot.jar,jboss-common.jar,jboss-system.jar,jboss-xml-binding.jar");
        final StringTokenizer stok = new StringTokenizer(value, ",");
        while (stok.hasMoreElements()) {
            final URL url = new URL(this.libraryURL, stok.nextToken().trim());
            list.add(url);
        }
        return list.toArray(new URL[list.size()]);
    }
    
    public Server load(final ClassLoader parent) throws Exception {
        final ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
        Server server;
        try {
            final URL[] urls = this.getBootClasspath();
            final URLClassLoader classLoader = (URLClassLoader)new NoAnnotationURLClassLoader(urls, parent);
            Thread.currentThread().setContextClassLoader(classLoader);
            final String typename = this.props.getProperty("jboss.server.type", "org.jboss.system.server.ServerImpl");
            server = this.createServer(typename, classLoader);
        }
        finally {
            Thread.currentThread().setContextClassLoader(oldCL);
        }
        return server;
    }
    
    protected Server createServer(final String typename, final ClassLoader classLoader) throws Exception {
        final Class type = classLoader.loadClass(typename);
        final Server server = type.newInstance();
        return server;
    }
}
