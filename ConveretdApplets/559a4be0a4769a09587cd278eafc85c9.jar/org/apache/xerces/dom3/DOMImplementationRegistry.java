// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import org.w3c.dom.DOMImplementation;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class DOMImplementationRegistry
{
    public static final String PROPERTY = "org.w3c.dom.DOMImplementationSourceList";
    private Hashtable sources;
    static /* synthetic */ Class class$java$lang$Thread;
    static /* synthetic */ Class class$org$apache$xerces$dom3$DOMImplementationRegistry;
    
    private DOMImplementationRegistry() {
    }
    
    private DOMImplementationRegistry(final Hashtable srcs) {
        this.sources = srcs;
    }
    
    public static DOMImplementationRegistry newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final Hashtable sources = new Hashtable();
        final String p = System.getProperty("org.w3c.dom.DOMImplementationSourceList");
        if (p != null) {
            final StringTokenizer st = new StringTokenizer(p);
            while (st.hasMoreTokens()) {
                final String sourceName = st.nextToken();
                final Object source = getClass(sourceName).newInstance();
                sources.put(sourceName, source);
            }
        }
        return new DOMImplementationRegistry(sources);
    }
    
    public DOMImplementation getDOMImplementation(final String features) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ClassCastException {
        final Enumeration names = this.sources.keys();
        String name = null;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            final DOMImplementationSource source = this.sources.get(name);
            final DOMImplementation impl = source.getDOMImplementation(features);
            if (impl != null) {
                return impl;
            }
        }
        return null;
    }
    
    public void addSource(final DOMImplementationSource s) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String sourceName = s.getClass().getName();
        this.sources.put(sourceName, s);
    }
    
    private static Class getClass(final String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Method m = null;
        ClassLoader cl = null;
        try {
            m = ((DOMImplementationRegistry.class$java$lang$Thread == null) ? (DOMImplementationRegistry.class$java$lang$Thread = class$("java.lang.Thread")) : DOMImplementationRegistry.class$java$lang$Thread).getMethod("getContextClassLoader", (Class[])null);
        }
        catch (NoSuchMethodException e3) {
            cl = ((DOMImplementationRegistry.class$org$apache$xerces$dom3$DOMImplementationRegistry == null) ? (DOMImplementationRegistry.class$org$apache$xerces$dom3$DOMImplementationRegistry = class$("org.apache.xerces.dom3.DOMImplementationRegistry")) : DOMImplementationRegistry.class$org$apache$xerces$dom3$DOMImplementationRegistry).getClassLoader();
        }
        if (cl == null) {
            try {
                cl = (ClassLoader)m.invoke(Thread.currentThread(), (Object[])null);
            }
            catch (IllegalAccessException e) {
                throw new UnknownError(e.getMessage());
            }
            catch (InvocationTargetException e2) {
                throw new UnknownError(e2.getMessage());
            }
        }
        if (cl == null) {
            return Class.forName(className);
        }
        try {
            return cl.loadClass(className);
        }
        catch (ClassNotFoundException e4) {
            return Class.forName(className);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
