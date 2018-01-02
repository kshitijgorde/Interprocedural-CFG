// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.util.StringTokenizer;
import java.util.Properties;

public class ProxyConfig
{
    public static String strProxySettings;
    public static boolean useProxy;
    public static String proxyHostnamePort;
    public static String proxyHostname;
    public static String proxyPort;
    
    static {
        ProxyConfig.useProxy = false;
    }
    
    public static void readConfiguration() {
        final Properties proxySettings = new Properties();
        ProxyConfig.strProxySettings = System.getProperty("javaplugin.proxy.settings");
        debug("ProxyConfig() strProxySettings=" + ProxyConfig.strProxySettings);
        if (ProxyConfig.strProxySettings != null) {
            ProxyConfig.useProxy = true;
            final StringTokenizer st = new StringTokenizer(ProxyConfig.strProxySettings, ";,");
            while (st.hasMoreTokens()) {
                final String strKey = st.nextToken();
                final String strValue = st.nextToken();
                final StringTokenizer st2 = new StringTokenizer(strKey, "=");
                final String strProtocol = st2.nextToken();
                if (strProtocol.equalsIgnoreCase(Configurator.getActionURL().getProtocol())) {
                    debug("ProxyConfig() proxy found for protocol " + strProtocol + " in java plugin proxy settings");
                    ProxyConfig.proxyHostnamePort = st2.nextToken();
                    final StringTokenizer st3 = new StringTokenizer(ProxyConfig.proxyHostnamePort, ":");
                    ProxyConfig.proxyHostname = st3.nextToken();
                    ProxyConfig.proxyPort = st3.nextToken();
                }
                else {
                    debug("ProxyConfig() do not need proxy for protocol " + strKey);
                }
            }
        }
        ProxyConfig.strProxySettings = System.getProperty("javaplugin.proxy.config.list");
        debug("ProxyConfig() strProxySettings=" + ProxyConfig.strProxySettings);
        if (ProxyConfig.strProxySettings != null) {
            ProxyConfig.useProxy = true;
            final StringTokenizer st = new StringTokenizer(ProxyConfig.strProxySettings, ",;");
            do {
                final String strKey = st.nextToken();
                if (!st.hasMoreTokens()) {
                    break;
                }
                final String strValue = st.nextToken();
                final StringTokenizer st2 = new StringTokenizer(strKey, "=");
                final String strProtocol = st2.nextToken();
                if (strProtocol.equalsIgnoreCase(Configurator.getActionURL().getProtocol())) {
                    debug("ProxyConfig() proxy found for protocol " + strProtocol + " in java plugin config list");
                    ProxyConfig.proxyHostnamePort = st2.nextToken();
                    final StringTokenizer st3 = new StringTokenizer(ProxyConfig.proxyHostnamePort, ":");
                    ProxyConfig.proxyHostname = st3.nextToken();
                    ProxyConfig.proxyPort = st3.nextToken();
                }
                else {
                    debug("ProxyConfig() do not need proxy for protocol " + strKey);
                }
            } while (st.hasMoreTokens());
        }
        final String strHttpProxyHost = System.getProperty("http.proxyHost");
        if (strHttpProxyHost != null && !strHttpProxyHost.equalsIgnoreCase("")) {
            debug("ProxyConfig() proxy settings found in http.* configuration (appletviewer)");
            ProxyConfig.useProxy = true;
            ProxyConfig.proxyHostname = System.getProperty("http.proxyHost");
            ProxyConfig.proxyPort = System.getProperty("http.proxyPort");
        }
        debug("ProxyConfig() current settings:");
        debug("ProxyConfig()   proxyHostname=" + ProxyConfig.proxyHostname);
        debug("ProxyConfig()   proxyPort=" + ProxyConfig.proxyPort);
    }
    
    private static void debug(final String string) {
        if (Configurator.getDebug()) {
            System.out.println(string);
        }
    }
}
