// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import javax.swing.JMenu;
import javax.swing.JComponent;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;
import java.util.Vector;
import de.mud.jta.event.ConfigurationRequest;
import java.util.Properties;

public class Common extends PluginLoader
{
    public static final String DEFAULT_PATH = "de.mud.jta.plugin";
    
    public Common(final Properties config) {
        super(getPluginPath(config.getProperty("pluginPath")));
        System.out.println("** JTA - Telnet/SSH for the JAVA(tm) platform");
        System.out.println("** Version 2.6 for Java 2+");
        System.out.println("** Copyright (c) 1996-2005 Matthias L. Jugel, Marcus Meissner");
        try {
            final Version build = (Version)Class.forName("de.mud.jta.Build").newInstance();
            System.out.println("** Build: " + build.getDate());
        }
        catch (Exception e) {
            System.out.println("** Build: patched or selfmade, no date");
            System.err.println(e);
        }
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
        }
        this.broadcast(new ConfigurationRequest(new PluginConfig(config)));
    }
    
    public Map getComponents() {
        final Map plugins = this.getPlugins();
        final Iterator pluginIt = plugins.keySet().iterator();
        final Map components = new HashMap();
        while (pluginIt.hasNext()) {
            final String name = pluginIt.next();
            final Plugin plugin = plugins.get(name);
            if (plugin instanceof VisualPlugin) {
                final JComponent c = ((VisualPlugin)plugin).getPluginVisual();
                if (c == null) {
                    continue;
                }
                final String id = plugin.getId();
                components.put(name + ((id != null) ? ("(" + id + ")") : ""), c);
            }
        }
        return components;
    }
    
    public Map getMenus() {
        final Map plugins = this.getPlugins();
        final Iterator pluginIt = plugins.keySet().iterator();
        final Map menus = new HashMap();
        while (pluginIt.hasNext()) {
            final String name = pluginIt.next();
            final Plugin plugin = plugins.get(name);
            if (plugin instanceof VisualPlugin) {
                final JMenu menu = ((VisualPlugin)plugin).getPluginMenu();
                if (menu == null) {
                    continue;
                }
                final String id = plugin.getId();
                menus.put(name + ((id != null) ? ("(" + id + ")") : ""), menu);
            }
        }
        return menus;
    }
    
    private static Vector getPluginPath(String path) {
        if (path == null) {
            path = "de.mud.jta.plugin";
        }
        return split(path, ':');
    }
    
    public static Vector split(final String s, final char separator) {
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
