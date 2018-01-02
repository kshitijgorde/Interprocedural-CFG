// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import de.mud.jta.PluginListener;
import de.mud.jta.event.OnlineStatusListener;
import java.io.IOException;
import de.mud.jta.event.EndOfRecordRequest;
import de.mud.jta.event.LocalEchoRequest;
import de.mud.jta.event.WindowSizeRequest;
import java.awt.Dimension;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.TerminalTypeRequest;
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
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        int n = this.source.read(b);
        if (n > 0) {
            n = this.handler.negotiate(b, n);
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.handler.transpose(b);
    }
}
