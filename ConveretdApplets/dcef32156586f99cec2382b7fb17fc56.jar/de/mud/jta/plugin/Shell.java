// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.io.IOException;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.OnlineStatus;
import de.mud.jta.event.SocketListener;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Shell extends Plugin implements FilterPlugin
{
    protected String shellCommand;
    private HandlerPTY pty;
    
    public Shell(final PluginBus bus, final String id) {
        super(bus, id);
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig cfg) {
                final String tmp;
                if ((tmp = cfg.getProperty("Shell", id, "command")) != null) {
                    Shell.this.shellCommand = tmp;
                }
                else {
                    Shell.this.shellCommand = "/bin/sh";
                }
            }
        });
        bus.registerPluginListener(new SocketListener() {
            public void connect(final String host, final int port) {
                Shell.this.pty = new HandlerPTY();
                if (Shell.this.pty.start(Shell.this.shellCommand) == 0) {
                    bus.broadcast(new OnlineStatus(true));
                }
                else {
                    bus.broadcast(new OnlineStatus(false));
                }
            }
            
            public void disconnect() {
                bus.broadcast(new OnlineStatus(false));
                Shell.this.pty = null;
            }
        });
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
    }
    
    public FilterPlugin getFilterSource() {
        return null;
    }
    
    public int read(final byte[] b) throws IOException {
        if (this.pty == null) {
            return 0;
        }
        final int ret = this.pty.read(b);
        if (ret <= 0) {
            throw new IOException("EOF on PTY");
        }
        return ret;
    }
    
    public void write(final byte[] b) throws IOException {
        if (this.pty != null) {
            this.pty.write(b);
        }
    }
}
