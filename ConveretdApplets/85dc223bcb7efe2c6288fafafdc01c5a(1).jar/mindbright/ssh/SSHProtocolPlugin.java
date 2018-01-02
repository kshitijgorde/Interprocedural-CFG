// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class SSHProtocolPlugin
{
    static Hashtable plugins;
    
    public static SSHProtocolPlugin getPlugin(final String name) {
        final SSHProtocolPlugin plugin = SSHProtocolPlugin.plugins.get(name);
        return SSHProtocolPlugin.plugins.get(name);
    }
    
    public static void addPlugin(final String name, final SSHProtocolPlugin plugin) {
        SSHProtocolPlugin.plugins.put(name, plugin);
    }
    
    public static void initiateAll(final SSHClient client) {
        final Enumeration e = SSHProtocolPlugin.plugins.elements();
        while (e.hasMoreElements()) {
            final SSHProtocolPlugin plugin = e.nextElement();
            plugin.initiate(client);
        }
    }
    
    public void initiate(final SSHClient client) {
    }
    
    public SSHListenChannel localListener(final String localHost, final int localPort, final String remoteHost, final int remotePort, final SSHChannelController controller) throws IOException {
        return new SSHListenChannel(localHost, localPort, remoteHost, remotePort, controller);
    }
    
    public void remoteListener(final int remotePort, final String localHost, final int localPort, final SSHChannelController controller) {
    }
    
    static {
        SSHProtocolPlugin.plugins = new Hashtable();
        addPlugin("general", new SSHProtocolPlugin());
        try {
            addPlugin("ftp", new SSHFtpPlugin());
        }
        catch (Throwable e) {
            System.out.println("FTP plugin not found, disabled");
        }
    }
}
