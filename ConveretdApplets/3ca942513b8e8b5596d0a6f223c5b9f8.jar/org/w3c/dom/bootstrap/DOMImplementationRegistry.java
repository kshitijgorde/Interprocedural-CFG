// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.bootstrap;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementation;
import java.util.StringTokenizer;
import org.w3c.dom.DOMImplementationSource;
import java.util.Vector;

public final class DOMImplementationRegistry
{
    public static final String PROPERTY = "org.w3c.dom.DOMImplementationSourceList";
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static final String DEFAULT_DOM_IMPLEMENTATION_SOURCE = "org.apache.xerces.dom.DOMXSImplementationSourceImpl";
    private Vector sources;
    static /* synthetic */ Class class$org$w3c$dom$bootstrap$DOMImplementationRegistry;
    
    private DOMImplementationRegistry(final Vector sources) {
        this.sources = sources;
    }
    
    public static DOMImplementationRegistry newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ClassCastException {
        final Vector<DOMImplementationSource> vector = new Vector<DOMImplementationSource>();
        final ClassLoader classLoader = getClassLoader();
        String s = getSystemProperty("org.w3c.dom.DOMImplementationSourceList");
        if (s == null) {
            s = getServiceValue(classLoader);
        }
        if (s == null) {
            s = "org.apache.xerces.dom.DOMXSImplementationSourceImpl";
        }
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                Class<?> clazz;
                if (classLoader != null) {
                    clazz = classLoader.loadClass(nextToken);
                }
                else {
                    clazz = Class.forName(nextToken);
                }
                vector.addElement((DOMImplementationSource)clazz.newInstance());
            }
        }
        return new DOMImplementationRegistry(vector);
    }
    
    public DOMImplementation getDOMImplementation(final String s) {
        for (int size = this.sources.size(), i = 0; i < size; ++i) {
            final DOMImplementation domImplementation = this.sources.elementAt(i).getDOMImplementation(s);
            if (domImplementation != null) {
                return domImplementation;
            }
        }
        return null;
    }
    
    public DOMImplementationList getDOMImplementationList(final String s) {
        final Vector<DOMImplementation> vector = new Vector<DOMImplementation>();
        for (int size = this.sources.size(), i = 0; i < size; ++i) {
            final DOMImplementationList domImplementationList = this.sources.elementAt(i).getDOMImplementationList(s);
            for (int j = 0; j < domImplementationList.getLength(); ++j) {
                vector.addElement(domImplementationList.item(j));
            }
        }
        return new DOMImplementationList() {
            private final /* synthetic */ Vector val$implementations = vector;
            
            public DOMImplementation item(final int n) {
                if (n >= 0 && n < this.val$implementations.size()) {
                    try {
                        return this.val$implementations.elementAt(n);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        return null;
                    }
                }
                return null;
            }
            
            public int getLength() {
                return this.val$implementations.size();
            }
        };
    }
    
    public void addSource(final DOMImplementationSource domImplementationSource) {
        if (domImplementationSource == null) {
            throw new NullPointerException();
        }
        if (!this.sources.contains(domImplementationSource)) {
            this.sources.addElement(domImplementationSource);
        }
    }
    
    private static ClassLoader getClassLoader() {
        try {
            final ClassLoader contextClassLoader = getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
        }
        catch (Exception ex) {
            return ((DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry == null) ? (DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry = class$("org.w3c.dom.bootstrap.DOMImplementationRegistry")) : DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry).getClassLoader();
        }
        return ((DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry == null) ? (DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry = class$("org.w3c.dom.bootstrap.DOMImplementationRegistry")) : DOMImplementationRegistry.class$org$w3c$dom$bootstrap$DOMImplementationRegistry).getClassLoader();
    }
    
    private static String getServiceValue(final ClassLoader classLoader) {
        final String s = "META-INF/services/org.w3c.dom.DOMImplementationSourceList";
        try {
            final InputStream resourceAsStream = getResourceAsStream(classLoader, s);
            if (resourceAsStream != null) {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"), 80);
                }
                catch (UnsupportedEncodingException ex) {
                    bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream), 80);
                }
                String line = null;
                try {
                    line = bufferedReader.readLine();
                }
                finally {
                    bufferedReader.close();
                }
                if (line != null && line.length() > 0) {
                    return line;
                }
            }
        }
        catch (Exception ex2) {
            return null;
        }
        return null;
    }
    
    private static boolean isJRE11() {
        try {
            Class.forName("java.security.AccessController");
            return false;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    private static ClassLoader getContextClassLoader() {
        return isJRE11() ? null : AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                Object contextClassLoader = null;
                try {
                    contextClassLoader = Thread.currentThread().getContextClassLoader();
                }
                catch (SecurityException ex) {}
                return contextClassLoader;
            }
        });
    }
    
    private static String getSystemProperty(final String s) {
        return isJRE11() ? System.getProperty(s) : AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
            public Object run() {
                return System.getProperty(s);
            }
        });
    }
    
    private static InputStream getResourceAsStream(final ClassLoader classLoader, final String s) {
        if (isJRE11()) {
            InputStream inputStream;
            if (classLoader == null) {
                inputStream = ClassLoader.getSystemResourceAsStream(s);
            }
            else {
                inputStream = classLoader.getResourceAsStream(s);
            }
            return inputStream;
        }
        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new PrivilegedAction() {
            public Object run() {
                InputStream inputStream;
                if (classLoader == null) {
                    inputStream = ClassLoader.getSystemResourceAsStream(s);
                }
                else {
                    inputStream = classLoader.getResourceAsStream(s);
                }
                return inputStream;
            }
        });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
