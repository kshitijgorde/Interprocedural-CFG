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
import java.io.OutputStream;
import java.io.InputStream;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Shell extends Plugin implements FilterPlugin
{
    private static final int debug = 1;
    protected String shellCommand;
    protected InputStream in;
    protected InputStream err;
    protected OutputStream out;
    protected Process p;
    private byte[] buffer;
    private int pos;
    
    public Shell(final PluginBus bus, final String id) {
        super(bus, id);
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig cfg) {
                final String tmp;
                if ((tmp = cfg.getProperty("Shell", id, "command")) != null) {
                    Shell.this.shellCommand = tmp;
                }
            }
        });
        bus.registerPluginListener(new SocketListener() {
            public void connect(final String host, final int port) {
                if (Shell.this.p == null) {
                    Shell.this.execute(host);
                }
            }
            
            public void disconnect() {
                if (Shell.this.p != null) {
                    Shell.this.p.destroy();
                    Shell.this.p = null;
                    Shell.this.in = null;
                    Shell.this.out = null;
                }
                else {
                    Shell.this.execute(null);
                }
            }
        });
    }
    
    private void execute(final String command) {
        this.shellCommand = ((command != null) ? command : this.shellCommand);
        if (this.shellCommand == null) {
            return;
        }
        final Runtime rt = Runtime.getRuntime();
        try {
            this.p = rt.exec(this.shellCommand);
            this.in = this.p.getInputStream();
            this.out = this.p.getOutputStream();
            this.err = this.p.getErrorStream();
        }
        catch (Exception e) {
            this.error("error: " + e);
            e.printStackTrace();
        }
        super.bus.broadcast(new OnlineStatus(true));
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
    }
    
    private byte[] transpose(final byte[] buf) {
        int nbufptr = 0;
        final byte[] nbuf = new byte[buf.length * 2];
        for (int i = 0; i < buf.length; ++i) {
            switch (buf[i]) {
                case 10: {
                    nbuf[nbufptr++] = 13;
                    nbuf[nbufptr++] = 10;
                    break;
                }
                default: {
                    nbuf[nbufptr++] = buf[i];
                    break;
                }
            }
        }
        final byte[] xbuf = new byte[nbufptr];
        System.arraycopy(nbuf, 0, xbuf, 0, nbufptr);
        return xbuf;
    }
    
    public int read(final byte[] b) throws IOException {
        if (this.buffer != null) {
            final int amount = (this.buffer.length - this.pos <= b.length) ? (this.buffer.length - this.pos) : b.length;
            System.arraycopy(this.buffer, this.pos, b, 0, amount);
            if (this.pos + amount < this.buffer.length) {
                this.pos += amount;
            }
            else {
                this.buffer = null;
            }
            return amount;
        }
        int n = (this.err.available() > 0) ? this.err.read(b) : this.in.read(b);
        if (n > 0) {
            final byte[] tmp = new byte[n];
            System.arraycopy(b, 0, tmp, 0, n);
            this.buffer = this.transpose(tmp);
            if (this.buffer == null || this.buffer.length <= 0) {
                return 0;
            }
            final int amount2 = (this.buffer.length <= b.length) ? this.buffer.length : b.length;
            System.arraycopy(this.buffer, 0, b, 0, amount2);
            n = (this.pos = amount2);
            if (amount2 == this.buffer.length) {
                this.buffer = null;
                this.pos = 0;
            }
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
        this.out.flush();
    }
}
