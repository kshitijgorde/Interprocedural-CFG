// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Collections;
import org.jdom.Element;
import org.jdom.JDOMException;
import java.util.Iterator;
import org.jdom.Namespace;
import java.util.List;
import java.util.Map;
import org.jdom.xpath.XPath;
import org.xmodel.log.Log;

public class XPathCache
{
    static final NsList namespaces;
    static final Cache cache;
    static final Log log;
    
    static {
        namespaces = new NsList();
        cache = new Cache();
        log = Log.getLog(XPathCache.class);
    }
    
    public static XPath add(final String expr) throws JDOMException {
        XPath path = XPathCache.cache.get().get(expr);
        if (path == null) {
            path = XPath.newInstance(expr);
        }
        for (final Namespace ns : XPathCache.namespaces.get()) {
            path.addNamespace(ns);
        }
        XPathCache.cache.get().put(expr, path);
        return path;
    }
    
    public static void add(final Namespace ns) {
        if (((ThreadLocal<List>)XPathCache.namespaces).get().contains(ns)) {
            return;
        }
        XPathCache.namespaces.get().add(ns);
    }
    
    public static XPath get(final String expr) {
        return XPathCache.cache.get().get(expr);
    }
    
    public static XPath get(final String expr, final boolean create) {
        XPath result = get(expr);
        if (result == null && create) {
            result = silentAdd(expr);
        }
        return result;
    }
    
    public static <T> T select(final Element e, final String expr) {
        T result = null;
        try {
            result = (T)get(expr, true).selectSingleNode((Object)e);
        }
        catch (Exception ex) {
            XPathCache.log.error(expr, ex);
        }
        return result;
    }
    
    public static <T> List<T> selectNodes(final Element e, final String expr) {
        List<T> result = Collections.emptyList();
        try {
            result = (List<T>)get(expr, true).selectNodes((Object)e);
        }
        catch (Exception ex) {
            XPathCache.log.error(expr, ex);
        }
        return result;
    }
    
    static XPath silentAdd(final String expr) {
        try {
            return add(expr);
        }
        catch (Exception e) {
            XPathCache.log.error(expr, e);
            return null;
        }
    }
}
