// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import java.io.InputStream;
import com.fluendo.utils.Debug;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class ElementFactory
{
    private static Vector elements;
    static /* synthetic */ Class class$com$fluendo$jst$ElementFactory;
    
    public static void loadElements() {
        try {
            final InputStream resourceAsStream = ((ElementFactory.class$com$fluendo$jst$ElementFactory == null) ? (ElementFactory.class$com$fluendo$jst$ElementFactory = class$("com.fluendo.jst.ElementFactory")) : ElementFactory.class$com$fluendo$jst$ElementFactory).getResourceAsStream("/plugins.ini");
            if (resourceAsStream != null) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    try {
                        final Class<?> forName = Class.forName(line);
                        Debug.log(3, "registered plugin: " + line);
                        ElementFactory.elements.addElement(forName.newInstance());
                    }
                    catch (Throwable t) {
                        Debug.log(3, "Failed to register plugin: " + line);
                    }
                }
            }
            else {
                Debug.log(3, "could not register plugins");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final Element dup(final Element element, final String name) {
        com.fluendo.jst.Object object = null;
        final Class<? extends Element> class1 = element.getClass();
        try {
            object = (Element)class1.newInstance();
            if (object != null && name != null) {
                object.setName(name);
            }
            Debug.log(3, "create element: " + object);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return (Element)object;
    }
    
    private static final Element findTypeFind(final byte[] array, final int n, final int n2) {
        int n3 = -1;
        Element element = null;
        final Enumeration<Element> elements = (Enumeration<Element>)ElementFactory.elements.elements();
        while (elements.hasMoreElements()) {
            final Element element2 = elements.nextElement();
            final int typeFind = element2.typeFind(array, n, n2);
            if (typeFind > n3) {
                n3 = typeFind;
                element = element2;
            }
        }
        return element;
    }
    
    public static final String typeFindMime(final byte[] array, final int n, final int n2) {
        String mime = null;
        final Element typeFind = findTypeFind(array, n, n2);
        if (typeFind != null) {
            mime = typeFind.getMime();
        }
        return mime;
    }
    
    public static final Element makeTypeFind(final byte[] array, final int n, final int n2, final String s) {
        Element element = findTypeFind(array, n, n2);
        if (element != null) {
            element = dup(element, s);
        }
        return element;
    }
    
    public static final Element makeByMime(final String s, final String s2) {
        Element dup = null;
        final Enumeration<Element> elements = (Enumeration<Element>)ElementFactory.elements.elements();
        while (elements.hasMoreElements()) {
            final Element element = elements.nextElement();
            if (s.equals(element.getMime())) {
                dup = dup(element, s2);
                break;
            }
        }
        return dup;
    }
    
    public static final Element makeByName(final String s, final String s2) {
        Element dup = null;
        final Enumeration<Element> elements = (Enumeration<Element>)ElementFactory.elements.elements();
        while (elements.hasMoreElements()) {
            final Element element = elements.nextElement();
            if (s.equals(element.getFactoryName())) {
                dup = dup(element, s2);
                break;
            }
        }
        return dup;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ElementFactory.elements = new Vector();
        loadElements();
    }
}
