// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.shared;

import java.io.InputStream;
import java.util.Properties;

public class AppID
{
    private static AppID m_appID;
    private Properties m_props;
    
    private AppID() {
        this.m_props = null;
        this.m_props = new Properties();
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/application.properties");
            this.m_props.load(resourceAsStream);
            resourceAsStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static synchronized AppID getAppID() {
        if (AppID.m_appID == null) {
            AppID.m_appID = new AppID();
        }
        return AppID.m_appID;
    }
    
    public String getProperty(final String s) {
        return this.m_props.getProperty(s, "<" + s + ">");
    }
    
    public String getAppNameVersion() {
        return this.getAppName() + " V" + this.getFullVersion();
    }
    
    public String getAppName() {
        return this.getProperty("app.name");
    }
    
    public String getBuildDate() {
        return this.getProperty("build.date");
    }
    
    public String getBuildTick() {
        return this.getProperty("build.tick");
    }
    
    public String getVersion() {
        return this.getProperty("app.version");
    }
    
    public String getFullVersion() {
        return this.getVersion() + "-" + this.getBuildTick();
    }
    
    public String getCopyright() {
        return this.getProperty("app.copyright");
    }
    
    public String getLocURL() {
        return this.getProperty("app.locURL");
    }
    
    public String getHelpURL() {
        return this.getProperty("app.helpURL");
    }
    
    static {
        AppID.m_appID = null;
    }
}
