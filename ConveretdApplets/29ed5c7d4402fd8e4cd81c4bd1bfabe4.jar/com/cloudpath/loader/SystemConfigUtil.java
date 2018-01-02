// 
// Decompiled by Procyon v0.5.30
// 

package com.cloudpath.loader;

import java.applet.Applet;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

public class SystemConfigUtil
{
    protected static final String ACTION = "action";
    protected static final String SYSTEM_CONFIG_FILE = "system_config.xml";
    protected static final String APPLICATION_LOADED_PAGE = "page5.html";
    protected static final String MANUAL_DOWNLOAD_PAGE = "page4_download.html";
    protected static final String WINDOWS_APPLET_PAGE = "page4_applet.html";
    protected static final String WINDOWS_DEFAULT_ACTION = "xpc";
    private static final String[][] GENERIC_NAMES;
    private static final String[][] GENERIC_VERSIONS;
    
    protected static final String getGenericOsName() {
        final String property = System.getProperty("os.name");
        if (property == null) {
            return "";
        }
        final String lowerCase = property.toLowerCase();
        for (int i = 0; i < SystemConfigUtil.GENERIC_NAMES.length; ++i) {
            if (lowerCase.startsWith(SystemConfigUtil.GENERIC_NAMES[i][0])) {
                return SystemConfigUtil.GENERIC_NAMES[i][1];
            }
        }
        return lowerCase.replace(' ', '_');
    }
    
    protected static final String getGenericOsVersion() {
        final String property = System.getProperty("os.version");
        if (property == null) {
            return "";
        }
        final String lowerCase = property.toLowerCase();
        for (int i = 0; i < SystemConfigUtil.GENERIC_VERSIONS.length; ++i) {
            if (lowerCase.startsWith(SystemConfigUtil.GENERIC_VERSIONS[i][0])) {
                return SystemConfigUtil.GENERIC_VERSIONS[i][1];
            }
        }
        return lowerCase.replace(' ', '_');
    }
    
    protected static final String getKeyForOs(final Properties properties, String replace, String s, String s2) {
        final ArrayList list = new ArrayList<String>();
        if (replace == null) {
            replace = "";
        }
        if (s == null) {
            s = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        replace = replace.replace(' ', '_');
        s = s.replace(' ', '_');
        while (s.length() > 0) {
            list.add(replace + "-" + s + "-" + s2);
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex >= 0) {
                s = s.substring(0, lastIndex);
            }
            else {
                s = "";
            }
        }
        list.add(replace + "-" + s2);
        list.add(s2);
        String s3 = "";
        String s4 = "";
        String property = null;
        for (int n = 0; (property == null || property.length() == 0) && n < list.size(); ++n) {
            s3 = list.get(n);
            property = properties.getProperty(s3);
            if (s4.length() > 0) {
                s4 += ", ";
            }
            s4 += s3;
        }
        if (property == null || property.length() == 0) {
            log("Unable to find key.  Tried the following: " + s4);
        }
        else {
            log("Key '" + s3 + "' = '" + property + "'");
        }
        return property;
    }
    
    public static Properties getSystemConfig(final URL url) throws Exception {
        final URL url2 = new URL(url, "system_config.xml");
        log("URL is " + url2);
        final InputStream inputStream = url2.openConnection().getInputStream();
        final Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }
    
    protected static final URL getNextPageForOs(final URL url, final Properties properties) throws Exception {
        final String keyForOs = getKeyForOs(properties, getGenericOsName(), getGenericOsVersion(), "action");
        if (keyForOs == null) {
            return new URL(url, "page4_download.html");
        }
        if (keyForOs.length() == 0) {
            log("Action for system is blank.  Will forward to manual download page.");
            return new URL(url, "page4_download.html");
        }
        if (keyForOs.toLowerCase().equals("xpc")) {
            log("Action is default, will forward to the signed applet.");
            return new URL(url, "page4_applet.html");
        }
        URL url2;
        if (keyForOs.startsWith("/")) {
            log("User will be redirected to relative url '" + keyForOs + "' based on '" + url + "'.");
            url2 = new URL(url, keyForOs.substring(1));
        }
        else {
            log("User will be redirected to absolute url '" + keyForOs + "'.");
            url2 = new URL(keyForOs);
        }
        return url2;
    }
    
    protected static final void printSystemProperties(final Applet applet) {
        log("General Properties");
        log("Application Version: 3.1.70");
        log("Applet Info: " + applet.getAppletInfo());
        log("Code Base Url: " + applet.getCodeBase());
        log("Document Base Url: " + applet.getDocumentBase());
        log("OS Version: " + System.getProperty("os.version"));
        log("OS Name: " + System.getProperty("os.name"));
        log("OS Generic Name: " + getGenericOsName());
        log("OS Generic Version: " + getGenericOsVersion());
        log("Java Version: " + System.getProperty("java.version"));
        log("Java Vendor: " + System.getProperty("java.vendor"));
        log("Java VM Version: " + System.getProperty("java.vm.version"));
        log("Java VM Vendor: " + System.getProperty("java.vm.vendor"));
    }
    
    private static void log(final String s) {
        LightLog.log(((SystemConfigUtil$1.class$com$cloudpath$loader$SystemConfigUtil == null) ? (SystemConfigUtil$1.class$com$cloudpath$loader$SystemConfigUtil = SystemConfigUtil$1.class$("com.cloudpath.loader.SystemConfigUtil")) : SystemConfigUtil$1.class$com$cloudpath$loader$SystemConfigUtil).getName() + "- " + s);
    }
    
    static {
        GENERIC_NAMES = new String[][] { { "windows", "windows" }, { "mac os x", "mac" } };
        GENERIC_VERSIONS = new String[][] { { "6.0", "6.0" }, { "5.1", "5.1" }, { "5.0", "5.0" }, { "10.4", "10.4" } };
    }
}
