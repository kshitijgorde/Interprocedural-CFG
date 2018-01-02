// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import prefuse.util.io.IOLib;
import java.util.logging.Logger;
import java.util.Properties;

public class PrefuseConfig extends Properties
{
    private static final Logger s_logger;
    private static final PrefuseConfig s_config;
    
    public static PrefuseConfig getConfig() {
        return PrefuseConfig.s_config;
    }
    
    private PrefuseConfig() {
        this.setDefaults();
        String property;
        try {
            property = System.getProperty("prefuse.config");
        }
        catch (Exception ex2) {
            property = null;
        }
        if (property == null) {
            property = "prefuse.conf";
        }
        try {
            this.load(IOLib.streamFromString(property));
            PrefuseConfig.s_logger.info("Loaded config file: " + property);
        }
        catch (Exception ex3) {}
        final String property2 = this.getProperty("util.logdir");
        final String property3 = this.getProperty("util.logfile");
        if (property2 != null) {
            try {
                final Logger logger = Logger.getLogger("prefuse");
                logger.setUseParentHandlers(false);
                final FileHandler fileHandler = new FileHandler(property2 + "/" + property3);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String get(final String s) {
        return PrefuseConfig.s_config.getProperty(s);
    }
    
    public static int getInt(final String s) {
        final String property = PrefuseConfig.s_config.getProperty(s);
        try {
            return Integer.parseInt(property);
        }
        catch (NumberFormatException ex) {
            return Integer.MIN_VALUE;
        }
    }
    
    public static long getLong(final String s) {
        final String property = PrefuseConfig.s_config.getProperty(s);
        try {
            return Long.parseLong(property);
        }
        catch (NumberFormatException ex) {
            return Long.MIN_VALUE;
        }
    }
    
    public static float getFloat(final String s) {
        final String property = PrefuseConfig.s_config.getProperty(s);
        try {
            return Float.parseFloat(property);
        }
        catch (NumberFormatException ex) {
            return Float.NaN;
        }
    }
    
    public static double getDouble(final String s) {
        final String property = PrefuseConfig.s_config.getProperty(s);
        try {
            return Double.parseDouble(property);
        }
        catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }
    
    public static boolean getBoolean(final String s) {
        return "true".equalsIgnoreCase(PrefuseConfig.s_config.getProperty(s));
    }
    
    private void setDefaults() {
        this.setProperty("size.scale2D", "0.5");
        this.setProperty("activity.threadPriority", "6");
        this.setProperty("data.delimiter", ".");
        this.setProperty("data.graph.nodeGroup", "nodes");
        this.setProperty("data.graph.edgeGroup", "edges");
        this.setProperty("data.visual.fieldPrefix", "_");
        this.setProperty("data.io.worker.threadPriority", String.valueOf(5));
        this.setProperty("data.filter.optimizeThreshold", "300");
        this.setProperty("data.graph.sourceKey", "source");
        this.setProperty("data.graph.targetKey", "target");
        this.setProperty("data.tree.sourceKey", "parent");
        this.setProperty("data.tree.targetKey", "child");
        this.setProperty("visualization.allItems", "_all_");
        this.setProperty("visualization.focusItems", "_focus_");
        this.setProperty("visualization.selectedItems", "_selected_");
        this.setProperty("visualization.searchItems", "_search_");
        this.setProperty("util.logfile", "prefuse_log_%g.txt");
    }
    
    static {
        s_logger = Logger.getLogger(PrefuseConfig.class.getName());
        s_config = new PrefuseConfig();
    }
}
