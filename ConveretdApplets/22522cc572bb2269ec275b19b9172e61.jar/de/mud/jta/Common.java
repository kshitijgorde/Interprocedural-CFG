// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.awt.Menu;
import java.awt.Component;
import java.util.Enumeration;
import java.util.Vector;
import de.mud.jta.event.ConfigurationRequest;
import java.util.Properties;
import java.util.Hashtable;

public class Common extends PluginLoader
{
    private Hashtable plugins;
    private Hashtable components;
    private Hashtable menus;
    public static final String DEFAULT_PATH = "de.mud.jta.plugin";
    
    public Common(final Properties config) {
        super(getPluginPath(config.getProperty("pluginPath")));
        System.out.println("** The Java(tm) Telnet Application");
        System.out.println("** Version 2.0 for Java 1.1.x and Java 2");
        System.out.println("** Copyright (c) 1996-2000 Matthias L. Jugel, Marcus Meissner");
        try {
            final Version build = (Version)Class.forName("de.mud.jta.Build").newInstance();
            System.out.println("** Build: " + build.getDate());
        }
        catch (Exception e) {
            System.out.println("** Build: patched or selfmade, no date");
            System.err.println(e);
        }
        this.plugins = new Hashtable();
        this.components = new Hashtable();
        this.menus = new Hashtable();
        final Vector names = split(config.getProperty("plugins"), ',');
        if (names == null) {
            System.err.println("jta: no plugins found! aborting ...");
            return;
        }
        final Enumeration e2 = names.elements();
        while (e2.hasMoreElements()) {
            String name = e2.nextElement();
            String id = null;
            final int idx;
            if ((idx = name.indexOf("(")) > 1) {
                if (name.indexOf(")", idx) > idx) {
                    id = name.substring(idx + 1, name.indexOf(")", idx));
                }
                else {
                    System.err.println("jta: missing ')' for plugin '" + name + "'");
                }
                name = name.substring(0, idx);
            }
            System.out.println("jta: loading plugin '" + name + "'" + ((id != null && id.length() > 0) ? (", ID: '" + id + "'") : ""));
            final Plugin plugin = this.addPlugin(name, id);
            if (plugin == null) {
                System.err.println("jta: ignoring plugin '" + name + "'" + ((id != null && id.length() > 0) ? (", ID: '" + id + "'") : ""));
            }
            else {
                this.plugins.put(name, plugin);
                if (!(plugin instanceof VisualPlugin)) {
                    continue;
                }
                final Component c = ((VisualPlugin)plugin).getPluginVisual();
                if (c != null) {
                    this.components.put(name + ((id != null) ? ("(" + id + ")") : ""), c);
                }
                final Menu menu = ((VisualPlugin)plugin).getPluginMenu();
                if (menu == null) {
                    continue;
                }
                this.menus.put(name + ((id != null) ? ("(" + id + ")") : ""), menu);
            }
        }
        this.broadcast(new ConfigurationRequest(new PluginConfig(config)));
    }
    
    public Hashtable getPlugins() {
        return this.plugins;
    }
    
    public Hashtable getComponents() {
        return this.components;
    }
    
    public Hashtable getMenus() {
        return this.menus;
    }
    
    private static Vector getPluginPath(String path) {
        if (path == null) {
            path = "de.mud.jta.plugin";
        }
        return split(path, ':');
    }
    
    private static Vector split(final String s, final char separator) {
        if (s == null) {
            return null;
        }
        final Vector v = new Vector();
        int old = -1;
        for (int idx = s.indexOf(separator); idx >= 0; idx = s.indexOf(separator, old + 1)) {
            v.addElement(s.substring(old + 1, idx));
            old = idx;
        }
        v.addElement(s.substring(old + 1));
        return v;
    }
}
