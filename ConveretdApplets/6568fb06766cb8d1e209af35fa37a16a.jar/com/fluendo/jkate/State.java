// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.fluendo.utils.Debug;
import com.jcraft.jogg.Packet;

public class State
{
    long granulepos;
    private Decode dec;
    private Event ev;
    
    public State() {
        this.ev = null;
    }
    
    public void clear() {
    }
    
    public int decodeInit(final Info info) {
        this.dec = new Decode(info);
        this.granulepos = -1L;
        return 0;
    }
    
    public int decodePacketin(final Packet packet) {
        this.ev = null;
        this.dec.opb.readinit(packet.packet_base, packet.packet, packet.bytes);
        final byte b = (byte)this.dec.opb.read(8);
        if ((b & 0x80) != 0x0) {
            return 0;
        }
        switch (b) {
            case 0: {
                this.ev = new Event(this.dec.info);
                final long n = this.dec.decodeTextPacket(this.ev);
                if (n < 0L) {
                    this.ev = null;
                    return (int)n;
                }
                if (packet.granulepos > -1L) {
                    this.granulepos = packet.granulepos;
                }
                else if (this.granulepos == -1L) {
                    this.granulepos = 0L;
                }
                return 0;
            }
            case 1: {
                return 0;
            }
            case 127: {
                return 1;
            }
            default: {
                Debug.debug("Kate packet type " + b + " ignored");
                return 0;
            }
        }
    }
    
    public Event decodeEventOut() {
        return this.ev;
    }
    
    public double granuleTime(final long n) {
        return this.dec.granuleTime(n);
    }
    
    public double granuleDuration(final long n) {
        return this.dec.granuleDuration(n);
    }
}
