// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Reader;
import java.util.Iterator;
import java.util.HashMap;
import org.jdom.Element;
import org.xmodel.log.Log;
import java.util.Map;

class MMLEmulation extends Emulation
{
    State state;
    StringBuilder sb;
    final Map<String, State> states;
    static final Log log;
    
    static {
        log = Log.getLog("DeviceSimulator");
    }
    
    MMLEmulation(final Element root) {
        super(root.getParentElement());
        this.sb = new StringBuilder();
        this.states = new HashMap<String, State>();
        for (final Object o : root.getChildren("state", MMLEmulation.cnns)) {
            final State s = new State((Element)o, this);
            if (this.state == null) {
                this.state = s;
            }
            this.states.put(s.name, s);
        }
        this.validate();
    }
    
    void validate() {
        for (final State s : this.states.values()) {
            s.validate();
        }
    }
    
    @Override
    void start(final Simulator simulator) {
        super.start(simulator);
        this.sb = new StringBuilder();
        this.write(this.state.prompt);
    }
    
    @Override
    void read() {
        try {
            this.rdBfr.clear();
            final int n = this.simulator.getChannel().read(this.rdBfr);
            if (n == -1) {
                this.simulator.close();
                return;
            }
            this.rdBfr.flip();
            this.preprocess();
            this.sb.append(new String(this.rdBfr.array(), this.rdBfr.position(), this.rdBfr.limit()));
            while (true) {
                final String line = this.nextLine();
                if (line == null) {
                    break;
                }
                this.entered(line);
            }
        }
        catch (Exception e) {
            MMLEmulation.log.warn("Read Failed", e);
            this.simulator.close();
        }
    }
    
    void preprocess() throws Exception {
    }
    
    String nextLine() {
        String result = null;
        for (int i = 0; i < this.sb.length(); ++i) {
            final char c = this.sb.charAt(i);
            if (this.lineTerminator(c)) {
                result = this.sb.substring(0, i);
                this.sb.delete(0, i);
                this.trim();
                break;
            }
        }
        return result;
    }
    
    void trim() {
        while (this.sb.length() > 0) {
            final char c = this.sb.charAt(0);
            if (!this.lineTerminator(c)) {
                break;
            }
            this.sb.deleteCharAt(0);
        }
    }
    
    boolean lineTerminator(final char c) {
        return c == '\r' || c == '\n';
    }
    
    void entered(final String s) {
        final String cmd = s.trim();
        MMLEmulation.log.info("Read: [" + cmd + "]");
        try {
            final Expect exp = this.state.match(cmd);
            final Reader reply = exp.getReply().getContent();
            if (reply != null) {
                this.write(reply);
                reply.close();
            }
            final String next = exp.nextState();
            if (next != null) {
                this.state = this.states.get(next);
            }
            exp.getAction().perform(this, s);
        }
        catch (Exception ex) {}
        this.write(this.state.prompt);
    }
    
    boolean validateState(final String s) {
        return this.states.containsKey(s);
    }
}
