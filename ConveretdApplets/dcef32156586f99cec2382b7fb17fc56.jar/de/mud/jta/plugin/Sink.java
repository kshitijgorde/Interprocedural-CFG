// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.io.IOException;
import de.mud.jta.PluginListener;
import de.mud.jta.event.OnlineStatusListener;
import de.mud.jta.PluginBus;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Sink extends Plugin implements FilterPlugin, Runnable
{
    private static final int debug = 0;
    private Thread reader;
    protected FilterPlugin source;
    
    public Sink(final PluginBus bus, final String id) {
        super(bus, id);
        this.reader = null;
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                if (Sink.this.reader == null) {
                    Sink.this.reader = new Thread();
                    Sink.this.reader.start();
                }
            }
            
            public void offline() {
                if (Sink.this.reader != null) {
                    Sink.this.reader = null;
                }
            }
        });
    }
    
    public void run() {
        final byte[] b = new byte[256];
        int n = 0;
        while (n >= 0) {
            try {
                n = this.read(b);
                continue;
            }
            catch (IOException e) {
                this.reader = null;
            }
            break;
        }
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
}
