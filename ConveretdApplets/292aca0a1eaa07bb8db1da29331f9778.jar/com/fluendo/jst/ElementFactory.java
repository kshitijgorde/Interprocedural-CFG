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
            final InputStream is = ((ElementFactory.class$com$fluendo$jst$ElementFactory == null) ? (ElementFactory.class$com$fluendo$jst$ElementFactory = class$("com.fluendo.jst.ElementFactory")) : ElementFactory.class$com$fluendo$jst$ElementFactory).getResourceAsStream("/plugins.ini");
            if (is != null) {
                final BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    final String str = br.readLine();
                    if (str == null) {
                        break;
                    }
                    final Class cl = Class.forName(str);
                    Debug.log(3, "registered plugin: " + str);
                    final Element pl = cl.newInstance();
                    ElementFactory.elements.addElement(pl);
                }
            }
            else {
                Debug.log(3, "could not register plugins");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static final Element dup(final Element element, final String name) {
        Element result = null;
        final Class cl = element.getClass();
        System.out.println("class-->" + cl);
        try {
            final com.fluendo.jst.Object obj = cl.newInstance();
            result = (Element)obj;
            result.setObjClass(obj);
            if (result != null && name != null) {
                result.setName(name);
            }
            Debug.log(3, "create element: " + result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("element.getClass()-->" + element.getClass());
        return result;
    }
    
    private static final Element findTypeFind(final byte[] data, final int offset, final int length) {
        int best = -1;
        Element result = null;
        final Enumeration e = ElementFactory.elements.elements();
        while (e.hasMoreElements()) {
            final Element element = e.nextElement();
            final int rank = element.typeFind(data, offset, length);
            if (rank > best) {
                best = rank;
                result = element;
            }
        }
        return result;
    }
    
    public static final String typeFindMime(final byte[] data, final int offset, final int length) {
        String result = null;
        final Element elem = findTypeFind(data, offset, length);
        if (elem != null) {
            result = elem.getMime();
        }
        return result;
    }
    
    public static final Element makeTypeFind(final byte[] data, final int offset, final int length, final String name) {
        Element result = null;
        result = findTypeFind(data, offset, length);
        if (result != null) {
            result = dup(result, name);
        }
        return result;
    }
    
    public static final Element makeByMime(final String mime, final String name) {
        Element result = null;
        final Enumeration e = ElementFactory.elements.elements();
        while (e.hasMoreElements()) {
            final Element element = e.nextElement();
            if (mime.equals(element.getMime())) {
                result = dup(element, name);
                break;
            }
        }
        return result;
    }
    
    public static final Element makeByName(final String name, final String elemName) {
        Element result = null;
        final Enumeration e = ElementFactory.elements.elements();
        while (e.hasMoreElements()) {
            final Element element = e.nextElement();
            if (name.equals(element.getFactoryName())) {
                result = dup(element, elemName);
                System.out.println("result-->" + result);
                System.out.println("name-->" + name);
                System.out.println("elemName-->" + elemName);
                break;
            }
        }
        return result;
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
        ElementFactory.elements = new Vector();
        loadElements();
    }
}
