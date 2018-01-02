// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.net.URLStreamHandler;
import java.net.URL;
import java.util.Map;
import org.jboss.logging.Logger;

public class URLStreamHandlerFactory implements java.net.URLStreamHandlerFactory
{
    private static final Logger log;
    public static final String PACKAGE_PREFIX = "org.jboss.net.protocol";
    private static Map handlerMap;
    private static ThreadLocal createURLStreamHandlerProtocol;
    private String[] handlerPkgs;
    private String lastHandlerPkgs;
    public static final String[] PROTOCOLS;
    static /* synthetic */ Class class$org$jboss$net$protocol$URLStreamHandlerFactory;
    
    public URLStreamHandlerFactory() {
        this.handlerPkgs = new String[] { "org.jboss.net.protocol" };
        this.lastHandlerPkgs = "org.jboss.net.protocol";
    }
    
    public static void preload() {
        for (int i = 0; i < URLStreamHandlerFactory.PROTOCOLS.length; ++i) {
            try {
                final URL url = new URL(URLStreamHandlerFactory.PROTOCOLS[i], "", -1, "");
                URLStreamHandlerFactory.log.trace("Loaded protocol: " + URLStreamHandlerFactory.PROTOCOLS[i]);
            }
            catch (Exception e) {
                URLStreamHandlerFactory.log.warn("Failed to load protocol: " + URLStreamHandlerFactory.PROTOCOLS[i], e);
            }
        }
    }
    
    public static void clear() {
        URLStreamHandlerFactory.handlerMap.clear();
    }
    
    public URLStreamHandler createURLStreamHandler(final String protocol) {
        URLStreamHandler handler = URLStreamHandlerFactory.handlerMap.get(protocol);
        if (handler != null) {
            return handler;
        }
        final String prevProtocol = URLStreamHandlerFactory.createURLStreamHandlerProtocol.get();
        if (prevProtocol != null && prevProtocol.equals(protocol)) {
            return null;
        }
        URLStreamHandlerFactory.createURLStreamHandlerProtocol.set(protocol);
        this.checkHandlerPkgs();
        final ClassLoader ctxLoader = Thread.currentThread().getContextClassLoader();
        for (int p = 0; p < this.handlerPkgs.length; ++p) {
            try {
                final String classname = this.handlerPkgs[p] + "." + protocol + ".Handler";
                Class type = null;
                try {
                    type = ctxLoader.loadClass(classname);
                }
                catch (ClassNotFoundException e) {
                    type = Class.forName(classname);
                }
                if (type != null) {
                    handler = type.newInstance();
                    URLStreamHandlerFactory.handlerMap.put(protocol, handler);
                    URLStreamHandlerFactory.log.trace("Found protocol:" + protocol + " handler:" + handler);
                }
            }
            catch (Throwable t) {}
        }
        URLStreamHandlerFactory.createURLStreamHandlerProtocol.set(null);
        return handler;
    }
    
    private synchronized void checkHandlerPkgs() {
        final String handlerPkgsProp = System.getProperty("java.protocol.handler.pkgs");
        if (handlerPkgsProp != null && !handlerPkgsProp.equals(this.lastHandlerPkgs)) {
            final StringTokenizer tokeninzer = new StringTokenizer(handlerPkgsProp, "|");
            final ArrayList tmp = new ArrayList();
            while (tokeninzer.hasMoreTokens()) {
                final String pkg = tokeninzer.nextToken().intern();
                if (!tmp.contains(pkg)) {
                    tmp.add(pkg);
                }
            }
            if (!tmp.contains("org.jboss.net.protocol")) {
                tmp.add("org.jboss.net.protocol");
            }
            tmp.toArray(this.handlerPkgs = new String[tmp.size()]);
            this.lastHandlerPkgs = handlerPkgsProp;
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        log = Logger.getLogger((URLStreamHandlerFactory.class$org$jboss$net$protocol$URLStreamHandlerFactory == null) ? (URLStreamHandlerFactory.class$org$jboss$net$protocol$URLStreamHandlerFactory = class$("org.jboss.net.protocol.URLStreamHandlerFactory")) : URLStreamHandlerFactory.class$org$jboss$net$protocol$URLStreamHandlerFactory);
        URLStreamHandlerFactory.handlerMap = Collections.synchronizedMap(new HashMap<Object, Object>());
        URLStreamHandlerFactory.createURLStreamHandlerProtocol = new ThreadLocal();
        PROTOCOLS = new String[] { "resource", "file" };
    }
}
