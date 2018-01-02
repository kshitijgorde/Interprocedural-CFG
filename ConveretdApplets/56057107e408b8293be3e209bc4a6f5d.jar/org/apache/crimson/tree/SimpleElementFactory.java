// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Dictionary;

public class SimpleElementFactory implements ElementFactory
{
    private Dictionary defaultMapping;
    private ClassLoader defaultLoader;
    private String defaultNs;
    private Dictionary nsMappings;
    private Dictionary nsLoaders;
    private Locale locale;
    static /* synthetic */ Class class$org$apache$crimson$tree$ElementNode;
    
    public SimpleElementFactory() {
        this.locale = Locale.getDefault();
    }
    
    public void addMapping(final Dictionary dict, final ClassLoader loader) {
        if (dict == null) {
            throw new IllegalArgumentException();
        }
        this.defaultMapping = dict;
        this.defaultLoader = loader;
    }
    
    public void addMapping(final String namespace, final Dictionary dict, final ClassLoader loader) {
        if (namespace == null || dict == null) {
            throw new IllegalArgumentException();
        }
        if (this.nsMappings == null) {
            this.nsMappings = new Hashtable();
            this.nsLoaders = new Hashtable();
        }
        this.nsMappings.put(namespace, dict);
        if (loader != null) {
            this.nsLoaders.put(namespace, loader);
        }
    }
    
    public void setDefaultNamespace(final String ns) {
        this.defaultNs = ns;
    }
    
    private Class map2Class(final String key, final Dictionary node2class, final ClassLoader loader) {
        final Object mapResult = node2class.get(key);
        if (mapResult instanceof Class) {
            return (Class)mapResult;
        }
        if (mapResult == null) {
            return null;
        }
        if (mapResult instanceof String) {
            final String className = (String)mapResult;
            try {
                Class retval;
                if (loader == null) {
                    retval = Class.forName(className);
                }
                else {
                    retval = loader.loadClass(className);
                }
                if (!((SimpleElementFactory.class$org$apache$crimson$tree$ElementNode == null) ? (SimpleElementFactory.class$org$apache$crimson$tree$ElementNode = class$("org.apache.crimson.tree.ElementNode")) : SimpleElementFactory.class$org$apache$crimson$tree$ElementNode).isAssignableFrom(retval)) {
                    throw new IllegalArgumentException(this.getMessage("SEF-000", new Object[] { key, className }));
                }
                node2class.put(key, retval);
                return retval;
            }
            catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(this.getMessage("SEF-001", new Object[] { key, className, e.getMessage() }));
            }
        }
        throw new IllegalArgumentException(this.getMessage("SEF-002", new Object[] { key }));
    }
    
    private ElementNode doMap(final String tagName, final Dictionary node2class, final ClassLoader loader) {
        Class theClass = this.map2Class(tagName, node2class, loader);
        if (theClass == null) {
            theClass = this.map2Class("*Element", node2class, loader);
        }
        ElementNode retval;
        if (theClass == null) {
            retval = new ElementNode(tagName);
        }
        else {
            try {
                retval = theClass.newInstance();
            }
            catch (Exception e) {
                throw new IllegalArgumentException(this.getMessage("SEF-003", new Object[] { tagName, theClass.getName(), e.getMessage() }));
            }
        }
        return retval;
    }
    
    public ElementEx createElementEx(String namespace, final String localName) {
        Dictionary mapping = null;
        if (namespace == null) {
            namespace = this.defaultNs;
        }
        if (this.nsMappings != null) {
            mapping = this.nsMappings.get(namespace);
        }
        if (mapping == null) {
            return this.doMap(localName, this.defaultMapping, this.defaultLoader);
        }
        return this.doMap(localName, mapping, this.nsLoaders.get(namespace));
    }
    
    public ElementEx createElementEx(final String tag) {
        return this.doMap(tag, this.defaultMapping, this.defaultLoader);
    }
    
    String getMessage(final String messageId) {
        return this.getMessage(messageId, null);
    }
    
    String getMessage(final String messageId, final Object[] parameters) {
        return XmlDocument.catalog.getMessage(this.locale, messageId, parameters);
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
