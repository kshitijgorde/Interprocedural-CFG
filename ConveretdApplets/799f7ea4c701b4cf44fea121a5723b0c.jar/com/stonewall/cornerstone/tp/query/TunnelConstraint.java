// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Link;
import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Constraint;

public class TunnelConstraint implements Constraint
{
    private Mode mode;
    
    TunnelConstraint() {
        this(Mode.Transport);
    }
    
    TunnelConstraint(final Mode mode) {
        this.mode = Mode.Transport;
        this.mode = mode;
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        final Link link = frame.link();
        if (link == null) {
            return result;
        }
        switch (this.mode) {
            case Transport: {
                result = link.tunnel();
                break;
            }
            case Tunnel: {
                if (frame.direction() != Direction.Inbound) {
                    break;
                }
                final Link tunnel = this.getTunnel(w, frame);
                if (tunnel != null) {
                    result = w.stackContains(tunnel.otherEnd(frame.endpoint()));
                    break;
                }
                break;
            }
        }
        return result;
    }
    
    private Link getTunnel(final BasicWalker w, final Frame frame) {
        Link result = null;
        final Link link = w.getGraph().inspector().linkContaining(frame.endpoint());
        if (link != null && link.tunnel() && link != frame.link()) {
            result = link;
        }
        return result;
    }
    
    enum Mode
    {
        Transport("Transport", 0), 
        Tunnel("Tunnel", 1);
        
        private Mode(final String s, final int n) {
        }
    }
}
