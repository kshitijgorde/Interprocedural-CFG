// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import de.mud.jta.event.TelnetCommandListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.event.SetWindowSizeListener;
import de.mud.jta.PluginListener;
import de.mud.jta.event.OnlineStatusListener;
import de.mud.jta.event.EndOfRecordRequest;
import de.mud.jta.event.LocalEchoRequest;
import de.mud.jta.event.WindowSizeRequest;
import java.awt.Dimension;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.TerminalTypeRequest;
import java.io.IOException;
import de.mud.jta.PluginBus;
import de.mud.telnet.TelnetProtocolHandler;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Telnet extends Plugin implements FilterPlugin
{
    protected FilterPlugin source;
    protected TelnetProtocolHandler handler;
    private static final int debug = 0;
    
    public Telnet(final PluginBus bus, final String id) {
        super(bus, id);
        this.handler = new TelnetProtocolHandler() {
            public String getTerminalType() {
                return (String)bus.broadcast(new TerminalTypeRequest());
            }
            
            public Dimension getWindowSize() {
                return (Dimension)bus.broadcast(new WindowSizeRequest());
            }
            
            public void setLocalEcho(final boolean echo) {
                bus.broadcast(new LocalEchoRequest(echo));
            }
            
            public void notifyEndOfRecord() {
                bus.broadcast(new EndOfRecordRequest());
            }
            
            public void write(final byte[] b) throws IOException {
                Telnet.this.source.write(b);
            }
        };
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                Telnet.this.handler.reset();
                try {
                    Telnet.this.handler.startup();
                }
                catch (IOException ex) {}
                bus.broadcast(new LocalEchoRequest(true));
            }
            
            public void offline() {
                Telnet.this.handler.reset();
                bus.broadcast(new LocalEchoRequest(true));
            }
        });
        bus.registerPluginListener(new SetWindowSizeListener() {
            public void setWindowSize(final int columns, final int rows) {
                try {
                    Telnet.this.handler.setWindowSize(columns, rows);
                }
                catch (IOException e) {
                    System.err.println("IO Exception in set window size");
                }
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                Telnet.this.configure(config);
            }
        });
        bus.registerPluginListener(new TelnetCommandListener() {
            public void sendTelnetCommand(final byte command) throws IOException {
                Telnet.this.handler.sendTelnetControl(command);
            }
        });
    }
    
    public void configure(final PluginConfig cfg) {
        final String crlf = cfg.getProperty("Telnet", this.id, "crlf");
        if (crlf != null) {
            this.handler.setCRLF(crlf);
        }
        final String cr = cfg.getProperty("Telnet", this.id, "cr");
        if (cr != null) {
            this.handler.setCR(cr);
        }
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        int n;
        do {
            n = this.handler.negotiate(b);
            if (n > 0) {
                return n;
            }
        } while (n == 0);
        n = this.source.read(b);
        if (n <= 0) {
            return n;
        }
        this.handler.inputfeed(b, n);
        n = 0;
        do {
            n = this.handler.negotiate(b);
            if (n > 0) {
                return n;
            }
        } while (n != -1);
        return 0;
    }
    
    public void write(final byte[] b) throws IOException {
        this.handler.transpose(b);
    }
}
