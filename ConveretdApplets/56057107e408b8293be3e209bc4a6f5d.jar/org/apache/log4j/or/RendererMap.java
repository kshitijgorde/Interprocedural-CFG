// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.or;

import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.RendererSupport;
import java.util.Hashtable;

public class RendererMap
{
    Hashtable map;
    static ObjectRenderer defaultRenderer;
    static /* synthetic */ Class class$org$apache$log4j$or$ObjectRenderer;
    
    public RendererMap() {
        this.map = new Hashtable();
    }
    
    public static void addRenderer(final RendererSupport rendererSupport, final String s, final String s2) {
        LogLog.debug("Rendering class: [" + s2 + "], Rendered class: [" + s + "].");
        final ObjectRenderer objectRenderer = (ObjectRenderer)OptionConverter.instantiateByClassName(s2, (RendererMap.class$org$apache$log4j$or$ObjectRenderer == null) ? (RendererMap.class$org$apache$log4j$or$ObjectRenderer = class$("org.apache.log4j.or.ObjectRenderer")) : RendererMap.class$org$apache$log4j$or$ObjectRenderer, null);
        if (objectRenderer == null) {
            LogLog.error("Could not instantiate renderer [" + s2 + "].");
            return;
        }
        try {
            rendererSupport.setRenderer(Loader.loadClass(s), objectRenderer);
        }
        catch (ClassNotFoundException ex) {
            LogLog.error("Could not find class [" + s + "].", ex);
        }
    }
    
    public String findAndRender(final Object o) {
        if (o == null) {
            return null;
        }
        return this.get(o.getClass()).doRender(o);
    }
    
    public ObjectRenderer get(final Object o) {
        if (o == null) {
            return null;
        }
        return this.get(o.getClass());
    }
    
    public ObjectRenderer get(final Class clazz) {
        for (Class superclass = clazz; superclass != null; superclass = superclass.getSuperclass()) {
            final ObjectRenderer objectRenderer = this.map.get(superclass);
            if (objectRenderer != null) {
                return objectRenderer;
            }
            final ObjectRenderer searchInterfaces = this.searchInterfaces(superclass);
            if (searchInterfaces != null) {
                return searchInterfaces;
            }
        }
        return RendererMap.defaultRenderer;
    }
    
    ObjectRenderer searchInterfaces(final Class clazz) {
        final ObjectRenderer objectRenderer = this.map.get(clazz);
        if (objectRenderer != null) {
            return objectRenderer;
        }
        final Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            final ObjectRenderer searchInterfaces = this.searchInterfaces(interfaces[i]);
            if (searchInterfaces != null) {
                return searchInterfaces;
            }
        }
        return null;
    }
    
    public ObjectRenderer getDefaultRenderer() {
        return RendererMap.defaultRenderer;
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public void put(final Class clazz, final ObjectRenderer objectRenderer) {
        this.map.put(clazz, objectRenderer);
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
        RendererMap.defaultRenderer = new DefaultRenderer();
    }
}
