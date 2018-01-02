// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.console;

import java.util.Enumeration;
import soht.client.java.core.Proxy;
import soht.client.java.configuration.Host;
import soht.client.java.configuration.ConfigurationManager;

public class ConsoleProxy extends Thread
{
    private static void showUsage() {
        System.out.println("SOHT Java Client");
        System.out.println("The SOHT Java Client requires a properties file.  Either start");
        System.out.println("the application in the same directory as the soht.properties");
        System.out.println("file, or specify the file name on the command line: ");
        System.out.println("java -jar soht-cleint-<version>.jar c:\\soht.properties");
    }
    
    public static void main(final String[] args) throws Exception {
        System.out.println("Applet only now.\n");
    }
    
    public static void startTunnel(final String _url) throws Exception {
        ConfigurationManager configurationManager = null;
        try {
            configurationManager = new ConfigurationManager(_url);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            showUsage();
        }
        if (configurationManager != null) {
            final Enumeration hosts = configurationManager.getHosts().elements();
            while (hosts.hasMoreElements()) {
                final Host host = hosts.nextElement();
                new Proxy(configurationManager, host).startProxy();
                System.out.println("Mapped local port: " + host.getLocalPort() + " to remote host: " + host.getRemoteHost() + ":" + host.getRemotePort() + " using SOHT Server at: " + configurationManager.getServerURL());
            }
        }
    }
}
