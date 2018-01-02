// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.util.Hashtable;
import java.applet.Applet;

public class TrendResource
{
    public static final String KEY_LOADING_TREND_GRAPH = "Loading_trend_graph";
    public static final String KEY_LOADING_TREND_DATA = "Loading_trend_data";
    public static final String KEY_ADD_SUBGRAPHS_FOR_TREND = "Add_subgraphs_for_trends";
    public static final String KEY_ADD_DATASERIES_TO_SUBGRAPH = "Add_dataseries_to_subgraph";
    public static final String KEY_TREND_GRAPH_ERROR = "Trend_Graph_Error";
    public static final String KEY_LOG_DISABLED = "Log_Disabled";
    public static final String KEY_LOG_ENABLED = "Log_Enabled";
    public static final String KEY_AND_BUFFER_PURGED = "and_Buffer_purged";
    public static final String KEY_TIME_SYNCRONIZATION = "Time_Synchronization";
    public static final String KEY_SEC = "sec";
    public static final String KEY_IN_ALARM = "In_Alarm";
    public static final String KEY_OVERRIDDEN = "Overridden";
    public static final String KEY_IN_FAULT = "In_Fault";
    public static final String KEY_OUT_OF_SERVICE = "Out_Of_Service";
    public static final String KEY_HISTORIAN_DISABLED = "Historian_Disabled";
    public static final String KEY_HISTORIAN_ENABLED = "Historian_Enabled";
    public static final String KEY_FAILURE = "Failure";
    public static final String KEY_TREND_SOURCE_CHANGED = "Trend_source_changed";
    public static final String KEY_FATAL_ERR_TREND_APPLET = "Fatal_Error_Trend_Applet";
    Applet displayTextResource;
    Hashtable resourceOverride;
    
    public TrendResource() {
    }
    
    public TrendResource(final Applet applet) {
        this.displayTextResource = applet;
    }
    
    public void setOverride(final Hashtable override) {
        this.resourceOverride = override;
    }
    
    public String get(final String key) {
        String text = null;
        if (this.resourceOverride != null && key != null) {
            text = this.resourceOverride.get(key);
        }
        else if (this.displayTextResource != null) {
            try {
                text = this.displayTextResource.getParameter(key);
            }
            catch (NullPointerException ex) {}
        }
        if (text != null) {
            return text;
        }
        if (TrendPlot.traceLevel >= 1) {
            TrendPlot.trace(this.getClass(), "Missing resource for: " + key);
        }
        return "??" + key + "??";
    }
}
