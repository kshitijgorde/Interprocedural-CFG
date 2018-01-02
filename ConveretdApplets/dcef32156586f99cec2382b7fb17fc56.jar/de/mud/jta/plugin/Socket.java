// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.io.IOException;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.OnlineStatus;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginBus;
import java.io.OutputStream;
import java.io.InputStream;
import de.mud.jta.event.SocketListener;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Socket extends Plugin implements FilterPlugin, SocketListener
{
    private static final int debug = 0;
    protected java.net.Socket socket;
    protected InputStream in;
    protected OutputStream out;
    protected String relay;
    protected int relayPort;
    private String error;
    
    public Socket(final PluginBus bus, final String id) {
        super(bus, id);
        this.relay = null;
        this.relayPort = 31415;
        this.error = null;
        bus.registerPluginListener(this);
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                final Socket this$0 = Socket.this;
                final String property = config.getProperty("Socket", id, "relay");
                this$0.relay = property;
                if (property != null && config.getProperty("Socket", id, "relayPort") != null) {
                    try {
                        Socket.this.relayPort = Integer.parseInt(config.getProperty("Socket", id, "relayPort"));
                    }
                    catch (NumberFormatException e) {
                        Socket.this.error("relayPort is not a number");
                    }
                }
            }
        });
    }
    
    public void connect(final String host, final int port) throws IOException {
        if (host == null) {
            return;
        }
        try {
            if (this.relay == null) {
                this.socket = new java.net.Socket(host, port);
            }
            else {
                this.socket = new java.net.Socket(this.relay, this.relayPort);
            }
            this.in = this.socket.getInputStream();
            this.out = this.socket.getOutputStream();
            if (this.relay != null) {
                this.write(("relay " + host + " " + port + "\n").getBytes());
            }
        }
        catch (Exception e) {
            this.error = "Sorry, Could not connect to: " + host + " " + port + "\r\n" + "Reason: " + e + "\r\n\r\n";
            this.error("can't connect: " + e);
        }
        this.bus.broadcast(new OnlineStatus(true));
    }
    
    public void disconnect() throws IOException {
        this.bus.broadcast(new OnlineStatus(false));
        if (this.socket != null) {
            this.socket.close();
            this.in = null;
            this.out = null;
        }
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
    }
    
    public FilterPlugin getFilterSource() {
        return null;
    }
    
    public int read(final byte[] b) throws IOException {
        if (this.error != null && this.error.length() > 0) {
            final int n = (this.error.length() < b.length) ? this.error.length() : b.length;
            System.arraycopy(this.error.getBytes(), 0, b, 0, n);
            this.error = this.error.substring(n);
            return n;
        }
        if (this.in == null) {
            this.disconnect();
            return -1;
        }
        final int n = this.in.read(b);
        if (n < 0) {
            this.disconnect();
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        if (this.out == null) {
            return;
        }
        try {
            this.out.write(b);
        }
        catch (IOException e) {
            this.disconnect();
        }
    }
}
