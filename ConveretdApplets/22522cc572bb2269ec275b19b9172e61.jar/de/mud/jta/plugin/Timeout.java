// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import de.mud.jta.PluginMessage;
import de.mud.jta.event.SocketRequest;
import java.io.IOException;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginBus;
import de.mud.jta.event.SocketListener;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Timeout extends Plugin implements FilterPlugin, SocketListener, Runnable
{
    private static final int debug = 1;
    protected int timeout;
    protected String timeoutCommand;
    protected String timeoutWarning;
    protected Thread timeoutThread;
    private PluginBus pluginBus;
    FilterPlugin source;
    
    public Timeout(final PluginBus bus, final String id) {
        super(bus, id);
        this.timeout = 0;
        this.timeoutCommand = null;
        this.timeoutWarning = null;
        this.timeoutThread = null;
        bus.registerPluginListener(this);
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                final String tos = config.getProperty("Timeout", id, "seconds");
                if (tos != null) {
                    try {
                        Timeout.this.timeout = Integer.parseInt(tos);
                    }
                    catch (Exception e) {
                        Timeout.this.error("timeout (" + Timeout.this.timeout + ") " + "is not an integer, timeout disabled");
                    }
                    Timeout.this.timeoutCommand = config.getProperty("Timeout", id, "command");
                    Timeout.this.timeoutWarning = config.getProperty("Timeout", id, "warning");
                }
            }
        });
        this.pluginBus = bus;
    }
    
    public void run() {
        boolean ok = false;
        while (this.timeoutThread != null) {
            try {
                ok = false;
                final Thread timeoutThread = this.timeoutThread;
                Thread.sleep(1000 * this.timeout);
            }
            catch (InterruptedException e) {
                ok = true;
            }
            if (!ok) {
                this.error("data connection timeout, shutting down");
                if (this.timeoutCommand != null) {
                    this.error("sending graceful exit command ...");
                    try {
                        this.write(this.timeoutCommand.getBytes());
                    }
                    catch (IOException e2) {
                        this.error("could not send exit command");
                    }
                    this.timeoutThread = null;
                    final Thread grace = new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.currentThread();
                                Thread.sleep(1000 * Timeout.this.timeout);
                                Timeout.this.pluginBus.broadcast(new SocketRequest());
                            }
                            catch (InterruptedException ex) {}
                        }
                    });
                    grace.start();
                }
                else {
                    super.bus.broadcast(new SocketRequest());
                }
            }
        }
    }
    
    public void connect(final String host, final int port) throws IOException {
        if (this.timeout > 0) {
            (this.timeoutThread = new Thread(this)).start();
        }
    }
    
    public void disconnect() throws IOException {
        if (this.timeoutThread != null) {
            final Thread tmp = this.timeoutThread;
            this.timeoutThread = null;
            tmp.interrupt();
        }
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
        this.source = plugin;
    }
    
    public int read(final byte[] b) throws IOException {
        final int n = this.source.read(b);
        if (n > 0 && this.timeoutThread != null) {
            this.timeoutThread.interrupt();
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
        if (this.timeoutThread != null) {
            this.timeoutThread.interrupt();
        }
    }
}
