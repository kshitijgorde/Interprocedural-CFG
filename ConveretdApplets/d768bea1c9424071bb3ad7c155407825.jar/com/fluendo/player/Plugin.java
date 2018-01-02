// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.io.IOException;
import java.awt.Component;
import java.util.Enumeration;
import java.io.InputStream;
import com.fluendo.utils.Debug;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public abstract class Plugin
{
    public static final int TYPE_AUDIO = 1;
    public static final int TYPE_VIDEO = 2;
    public static final int TYPE_DEMUX = 3;
    public static final int RANK_NONE = 0;
    public static final int RANK_MAYBE = 10;
    public static final int RANK_PRIMARY = 20;
    public int type;
    public long last_pts;
    public int fps_numerator;
    public int fps_denominator;
    public int aspect_numerator;
    public int aspect_denominator;
    public int channels;
    public int rate;
    private static Vector plugins;
    static /* synthetic */ Class class$com$fluendo$player$Plugin;
    
    public static void loadPlugins() {
        try {
            InputStream inputStream = ((Plugin.class$com$fluendo$player$Plugin == null) ? (Plugin.class$com$fluendo$player$Plugin = class$("com.fluendo.player.Plugin")) : Plugin.class$com$fluendo$player$Plugin).getResourceAsStream("plugins.ini");
            if (inputStream == null) {
                inputStream = ((Plugin.class$com$fluendo$player$Plugin == null) ? (Plugin.class$com$fluendo$player$Plugin = class$("com.fluendo.player.Plugin")) : Plugin.class$com$fluendo$player$Plugin).getResourceAsStream("/plugins.ini");
            }
            if (inputStream != null) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    final Class<?> forName = Class.forName(line);
                    Debug.log(3, "registered plugin: " + line);
                    Plugin.plugins.addElement(forName.newInstance());
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
    
    public Plugin(final int type) {
        this.type = type;
    }
    
    private static final Plugin dup(final Plugin plugin) {
        Plugin plugin2 = null;
        final Class<? extends Plugin> class1 = plugin.getClass();
        try {
            plugin2 = (Plugin)class1.newInstance();
            Debug.log(3, "create plugin: " + plugin);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return plugin2;
    }
    
    public static final Plugin makeTypeFind(final byte[] array, final int n, final int n2) {
        int n3 = -1;
        Plugin dup = null;
        final Enumeration<Plugin> elements = (Enumeration<Plugin>)Plugin.plugins.elements();
        while (elements.hasMoreElements()) {
            final Plugin plugin = elements.nextElement();
            final int typeFind = plugin.typeFind(array, n, n2);
            if (typeFind > n3) {
                n3 = typeFind;
                dup = plugin;
            }
        }
        if (dup != null) {
            dup = dup(dup);
        }
        return dup;
    }
    
    public static final Plugin makeByMime(final String s) {
        Plugin dup = null;
        final Enumeration<Plugin> elements = (Enumeration<Plugin>)Plugin.plugins.elements();
        while (elements.hasMoreElements()) {
            final Plugin plugin = elements.nextElement();
            if (s.equals(plugin.getMime())) {
                dup = dup(plugin);
                break;
            }
        }
        return dup;
    }
    
    public abstract String getMime();
    
    public abstract int typeFind(final byte[] p0, final int p1, final int p2);
    
    public long offsetToTime(final long n) {
        Debug.log(3, "offsetToTime not implemented");
        return -1L;
    }
    
    public void initDecoder(final Component component) {
        Debug.log(3, "plugin not decoder");
    }
    
    public void initDemuxer(final InputStream inputStream, final Component component, final DataConsumer dataConsumer, final DataConsumer dataConsumer2) {
        Debug.log(3, "plugin not demuxer");
    }
    
    public MediaBuffer decode(final MediaBuffer mediaBuffer) {
        Debug.log(3, "plugin not decoder");
        return null;
    }
    
    public boolean demux() throws IOException {
        Debug.log(3, "plugin not demuxer");
        return false;
    }
    
    public abstract void stop();
    
    public long getImageTime() {
        return -1L;
    }
    
    public boolean haveTzOffset() {
        return false;
    }
    
    public long getTzOffset() {
        return 0L;
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
        Plugin.plugins = new Vector();
        loadPlugins();
    }
}
