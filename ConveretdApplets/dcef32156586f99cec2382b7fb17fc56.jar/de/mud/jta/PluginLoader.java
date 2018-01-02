// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Vector;

public class PluginLoader implements PluginBus
{
    public static final String ID = "$Id: PluginLoader.java 499 2005-09-29 08:24:54Z leo $";
    private static final int debug = 0;
    private Vector PATH;
    private List filter;
    private Map plugins;
    private Vector listener;
    static /* synthetic */ Class class$de$mud$jta$PluginBus;
    static /* synthetic */ Class class$java$lang$String;
    
    public PluginLoader() {
        this(null);
    }
    
    public PluginLoader(final Vector path) {
        this.PATH = null;
        this.filter = new ArrayList();
        this.listener = new Vector();
        this.plugins = new HashMap();
        if (path == null) {
            (this.PATH = new Vector()).addElement("de.mud.jta.plugin");
        }
        else {
            this.PATH = path;
        }
    }
    
    public Plugin addPlugin(final String name, final String id) {
        Plugin plugin = this.loadPlugin(name, id);
        if (plugin == null) {
            plugin = this.loadPlugin(null, name, id);
        }
        if (plugin == null) {
            System.err.println("plugin loader: plugin '" + name + "' was not found!");
            return null;
        }
        if (plugin instanceof FilterPlugin) {
            if (this.filter.size() > 0) {
                ((FilterPlugin)plugin).setFilterSource(this.filter.get(this.filter.size() - 1));
            }
            this.filter.add(plugin);
        }
        this.plugins.put(name + ((id == null) ? "" : ("(" + id + ")")), plugin);
        return plugin;
    }
    
    public Plugin replacePlugin(final String name, final String id) {
        final Plugin plugin = this.loadPlugin(name, id);
        if (plugin != null) {
            final Plugin oldPlugin = this.plugins.get(name + ((id == null) ? "" : ("(" + id + ")")));
            if (this.filter.contains(oldPlugin)) {
                final int index = this.filter.indexOf(oldPlugin);
                this.filter.set(index, plugin);
                ((FilterPlugin)plugin).setFilterSource(((FilterPlugin)oldPlugin).getFilterSource());
                if (index < this.filter.size() - 1) {
                    this.filter.get(index + 1).setFilterSource((FilterPlugin)plugin);
                }
            }
        }
        return plugin;
    }
    
    private Plugin loadPlugin(final String name, final String id) {
        Plugin plugin = null;
        final Enumeration pathList = this.PATH.elements();
        while (pathList.hasMoreElements()) {
            final String path = pathList.nextElement();
            plugin = this.loadPlugin(path, name, id);
            if (plugin != null) {
                return plugin;
            }
        }
        plugin = this.loadPlugin(null, name, id);
        return plugin;
    }
    
    private Plugin loadPlugin(final String path, final String name, final String id) {
        Plugin plugin = null;
        final String fullClassName = (path == null) ? name : (path + "." + name);
        try {
            final Class c = Class.forName(fullClassName);
            final Constructor cc = c.getConstructor((PluginLoader.class$de$mud$jta$PluginBus == null) ? (PluginLoader.class$de$mud$jta$PluginBus = class$("de.mud.jta.PluginBus")) : PluginLoader.class$de$mud$jta$PluginBus, (PluginLoader.class$java$lang$String == null) ? (PluginLoader.class$java$lang$String = class$("java.lang.String")) : PluginLoader.class$java$lang$String);
            plugin = cc.newInstance(this, id);
            return plugin;
        }
        catch (ClassNotFoundException ce) {}
        catch (Exception e) {
            System.err.println("plugin loader: can't load plugin: " + fullClassName);
            e.printStackTrace();
        }
        return null;
    }
    
    public void registerPluginListener(final PluginListener l) {
        this.listener.addElement(l);
    }
    
    public Object broadcast(final PluginMessage message) {
        if (message == null || this.listener == null) {
            return null;
        }
        Enumeration e;
        Object res;
        for (e = this.listener.elements(), res = null; res == null && e.hasMoreElements(); res = message.firePluginMessage(e.nextElement())) {}
        return res;
    }
    
    public Map getPlugins() {
        return this.plugins;
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
