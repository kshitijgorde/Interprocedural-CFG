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
import java.util.Vector;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Script extends Plugin implements FilterPlugin
{
    private static final int debug = 0;
    private Vector savedScript;
    protected FilterPlugin source;
    private int matchPos;
    private Vector script;
    private byte[] match;
    private boolean done;
    
    public Script(final PluginBus bus, final String id) {
        super(bus, id);
        this.done = true;
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                Script.this.setup(Script.this.savedScript);
            }
            
            public void offline() {
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                Script.this.savedScript = new Vector();
                final String s = config.getProperty("Script", id, "script");
                if (s != null) {
                    if (s.charAt(0) == '@') {
                        Script.this.error("@file not implemented yet");
                    }
                    String[] pair = null;
                    int old = -1;
                    for (int idx = s.indexOf(124); idx >= 0; idx = s.indexOf(124, old + 1)) {
                        if (pair == null) {
                            pair = new String[] { s.substring(old + 1, idx), null };
                        }
                        else {
                            pair[1] = s.substring(old + 1, idx) + "\n";
                            Script.this.savedScript.addElement(pair);
                            pair = null;
                        }
                        old = idx;
                    }
                    if (pair != null) {
                        pair[1] = s.substring(old + 1) + "\n";
                        Script.this.savedScript.addElement(pair);
                    }
                    else {
                        Script.this.error("unmatched pairs of script elements");
                    }
                }
            }
        });
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
        this.source = plugin;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        final int n = this.source.read(b);
        if (n > 0) {
            this.match(b, n);
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    private void setup(final Vector script) {
        this.script = (Vector)script.clone();
        this.match = ((String[])this.script.firstElement())[0].getBytes();
        if (this.match.length == 0) {
            try {
                this.write(this.found());
            }
            catch (Exception ex) {}
        }
        this.reset();
        this.done = false;
    }
    
    private void match(final byte[] s, final int length) throws IOException {
        for (int i = 0; !this.done && i < length; ++i) {
            if (s[i] == this.match[this.matchPos]) {
                if (++this.matchPos >= this.match.length) {
                    this.write(this.found());
                }
            }
            else {
                this.reset();
            }
        }
    }
    
    private byte[] found() {
        final byte[] answer = ((String[])this.script.firstElement())[1].getBytes();
        this.script.removeElementAt(0);
        if (!this.script.isEmpty()) {
            this.match = ((String[])this.script.firstElement())[0].getBytes();
            this.reset();
        }
        else {
            this.done = true;
        }
        return answer;
    }
    
    private void reset() {
        this.matchPos = 0;
    }
}
