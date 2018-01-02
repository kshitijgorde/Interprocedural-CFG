// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.io.IOException;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import de.mud.jta.event.OnlineStatusListener;
import de.mud.jta.PluginBus;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class NoEcho extends Plugin implements FilterPlugin
{
    private final int debug = 0;
    private String strSuccess;
    private String strFail;
    protected FilterPlugin source;
    private boolean bDone;
    private byte[] matchSuccess;
    private byte[] matchFail;
    private int matchPos;
    
    public NoEcho(final PluginBus bus, final String id) {
        super(bus, id);
        this.bDone = false;
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                NoEcho.this.setup();
            }
            
            public void offline() {
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                NoEcho.this.strSuccess = config.getProperty("NoEcho", id, "success");
                if (NoEcho.this.strSuccess == null) {
                    NoEcho.this.strSuccess = "@GOOD";
                }
                NoEcho.this.strFail = config.getProperty("NoEcho", id, "failure");
                if (NoEcho.this.strFail == null) {
                    NoEcho.this.strFail = "@BAD";
                }
            }
        });
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        final int n = this.source.read(b);
        if (n > 0) {
            this.find(n, b);
        }
        if (this.bDone) {
            return n;
        }
        return 0;
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    public void setup() {
        this.matchSuccess = this.strSuccess.getBytes();
        this.matchFail = this.strFail.getBytes();
    }
    
    public void find(final int length, final byte[] s) throws IOException {
        for (int i = 0; !this.bDone && i < length; ++i) {
            if (s[i] == this.matchSuccess[this.matchPos] || s[i] == this.matchFail[this.matchPos]) {
                ++this.matchPos;
                if (this.matchPos >= this.matchSuccess.length || this.matchPos >= this.matchFail.length) {
                    this.bDone = true;
                }
            }
            else {
                this.reset();
            }
        }
    }
    
    public void reset() {
        this.matchPos = 0;
    }
}
