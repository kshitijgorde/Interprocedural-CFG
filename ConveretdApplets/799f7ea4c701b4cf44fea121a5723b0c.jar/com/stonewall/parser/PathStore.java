// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import org.xmodel.log.Log;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import java.util.Map;

public class PathStore
{
    float hitCount;
    private Map<String, XPath> paths;
    private Map<String, Namespace> namespaces;
    static Log log;
    
    static {
        PathStore.log = Log.getLog(PathStore.class);
    }
    
    public PathStore() {
        this.hitCount = 0.0f;
        this.paths = new HashMap<String, XPath>();
        this.namespaces = new HashMap<String, Namespace>();
    }
    
    public XPath get(final String p) throws Exception {
        XPath result = this.paths.get(p);
        if (result == null) {
            result = XPath.newInstance(p);
            for (final Namespace ns : this.namespaces.values()) {
                result.addNamespace(ns);
            }
            this.paths.put(p, result);
        }
        else {
            ++this.hitCount;
        }
        return result;
    }
    
    public XPath get(final String p, final Map<String, String> r) throws Exception {
        final XPath result = this.get(p);
        for (final String k : r.keySet()) {
            if (!k.startsWith("$")) {
                if (k.startsWith("#")) {
                    continue;
                }
                result.setVariable(k, (Object)r.get(k));
            }
        }
        return result;
    }
    
    public void add(final Namespace ns) {
        this.namespaces.put(ns.getPrefix(), ns);
    }
    
    public void add(final Collection<Namespace> list) {
        for (final Namespace ns : list) {
            this.namespaces.put(ns.getPrefix(), ns);
        }
    }
    
    public Namespace getNamespace(final String prefix) {
        return this.namespaces.get(prefix);
    }
    
    public Collection<Namespace> namespaces() {
        return this.namespaces.values();
    }
    
    public float hitRatio() {
        final float n = this.paths.size() + this.hitCount;
        return (n > 0.0f) ? (this.hitCount / n * 100.0f) : 0.0f;
    }
    
    public void clearStatistics() {
        this.hitCount = 0.0f;
    }
}
