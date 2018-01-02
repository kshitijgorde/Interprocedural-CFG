// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Message;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Event;
import com.fluendo.jst.Buffer;
import java.util.Vector;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Pad;
import com.jcraft.jogg.Packet;
import com.fluendo.jkate.State;
import com.fluendo.jkate.Comment;
import com.fluendo.jkate.Info;
import com.fluendo.jst.Element;

public class KateDec extends Element implements OggPayload
{
    private static final byte[] signature;
    private Info ki;
    private Comment kc;
    private State k;
    private Packet op;
    private int packetno;
    private long basetime;
    private long lastTs;
    private boolean haveDecoder;
    private Pad srcPad;
    private Pad sinkPad;
    
    public boolean isType(final Packet packet) {
        return this.typeFind(packet.packet_base, packet.packet, packet.bytes) > 0;
    }
    
    public boolean isKeyFrame(final Packet packet) {
        return true;
    }
    
    public boolean isDiscontinuous() {
        return true;
    }
    
    public int takeHeader(final Packet packet) {
        final int decodeHeader = this.ki.decodeHeader(this.kc, packet);
        if (decodeHeader > 0) {
            this.k.decodeInit(this.ki);
            Debug.debug("Kate decoder ready");
            this.haveDecoder = true;
        }
        return decodeHeader;
    }
    
    public boolean isHeader(final Packet packet) {
        return (packet.packet_base[packet.packet] & 0x80) == 0x80;
    }
    
    public long getFirstTs(final Vector vector) {
        final int size = vector.size();
        Buffer buffer = null;
        int i;
        for (i = 0; i < size; ++i) {
            buffer = vector.elementAt(i);
            if (buffer.time_offset != -1L) {
                break;
            }
        }
        if (i == vector.size()) {
            return -1L;
        }
        final long granuleToTime = this.granuleToTime(buffer.time_offset);
        vector.elementAt(0).timestamp = granuleToTime - (i + 1) * (1000000L * this.ki.gps_denominator / this.ki.gps_numerator);
        return granuleToTime;
    }
    
    public long granuleToTime(final long n) {
        if (n < 0L || !this.haveDecoder) {
            return -1L;
        }
        return (long)(this.k.granuleTime(n) * 1000000.0);
    }
    
    public long granuleToDuration(final long n) {
        if (n < 0L || !this.haveDecoder) {
            return -1L;
        }
        return (long)(this.k.granuleDuration(n) * 1000000.0);
    }
    
    public KateDec() {
        this.basetime = 0L;
        this.haveDecoder = false;
        this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return KateDec.this.sinkPad.pushEvent(event);
            }
        };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean b = false;
                switch (event.getType()) {
                    case 1: {
                        b = KateDec.this.srcPad.pushEvent(event);
                        synchronized (super.streamLock) {
                            Debug.log(4, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        b = KateDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 3: {
                        Debug.log(3, "got EOS " + this);
                        b = KateDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 4: {
                        KateDec.this.basetime = event.parseNewsegmentStart();
                        Debug.info("new segment: base time " + KateDec.this.basetime);
                        b = KateDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        b = KateDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return b;
            }
            
            protected int chainFunc(final Buffer buffer) {
                Debug.log(4, super.parent.getName() + " <<< " + buffer);
                KateDec.this.op.packet_base = buffer.data;
                KateDec.this.op.packet = buffer.offset;
                KateDec.this.op.bytes = buffer.length;
                KateDec.this.op.b_o_s = ((KateDec.this.packetno == 0) ? 1 : 0);
                KateDec.this.op.e_o_s = 0;
                KateDec.this.op.packetno = KateDec.this.packetno;
                final long timestamp = buffer.timestamp;
                Debug.log(4, "Kate chainFunc with packetno " + KateDec.this.packetno + ", haveDecoder " + KateDec.this.haveDecoder);
                if (!KateDec.this.haveDecoder) {
                    final int takeHeader = KateDec.this.takeHeader(KateDec.this.op);
                    if (takeHeader < 0) {
                        buffer.free();
                        Debug.log(1, "does not contain Kate data.");
                        return -5;
                    }
                    if (takeHeader > 0) {
                        Debug.log(4, "Kate initialized for decoding");
                        super.caps = new Caps("application/x-kate-event");
                    }
                    buffer.free();
                    KateDec.this.packetno++;
                    return 0;
                }
                else {
                    if ((KateDec.this.op.packet_base[KateDec.this.op.packet] & 0x80) == 0x80) {
                        Debug.log(4, "ignoring header");
                        buffer.free();
                        return 0;
                    }
                    if (timestamp != -1L) {
                        KateDec.this.lastTs = timestamp;
                    }
                    int push;
                    try {
                        if (KateDec.this.k.decodePacketin(KateDec.this.op) < 0) {
                            buffer.free();
                            Debug.log(1, "Error Decoding Kate.");
                            KateDec.this.postMessage(Message.newError(this, "Error decoding Kate"));
                            return -5;
                        }
                        final com.fluendo.jkate.Event decodeEventOut = KateDec.this.k.decodeEventOut();
                        if (decodeEventOut != null) {
                            buffer.object = decodeEventOut;
                            buffer.caps = super.caps;
                            buffer.timestamp = KateDec.this.granuleToDuration(decodeEventOut.start);
                            buffer.timestampEnd = buffer.timestamp + KateDec.this.granuleToDuration(decodeEventOut.duration);
                            Debug.log(4, super.parent.getName() + " >>> " + buffer);
                            Debug.debug("Got Kate text: " + new String(decodeEventOut.text) + " from " + buffer.timestamp + " to " + buffer.timestampEnd + ", basetime " + KateDec.this.basetime);
                            push = KateDec.this.srcPad.push(buffer);
                            Debug.log(4, "push returned " + push);
                        }
                        else {
                            Debug.debug("Got no event");
                            buffer.free();
                            push = 0;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        KateDec.this.postMessage(Message.newError(this, ex.getMessage()));
                        push = -5;
                    }
                    KateDec.this.packetno++;
                    return push;
                }
            }
            
            protected boolean activateFunc(final int n) {
                return true;
            }
        };
        this.ki = new Info();
        this.kc = new Comment();
        this.k = new State();
        this.op = new Packet();
        this.addPad(this.srcPad);
        this.addPad(this.sinkPad);
    }
    
    protected int changeState(final int n) {
        switch (n) {
            case 18: {
                this.lastTs = -1L;
                this.packetno = 0;
                break;
            }
        }
        final int changeState = super.changeState(n);
        switch (n) {
            case 33: {
                this.ki.clear();
                this.kc.clear();
                this.k.clear();
                break;
            }
        }
        return changeState;
    }
    
    public Object getProperty(final String s) {
        if (s.equals("language")) {
            return this.ki.language;
        }
        if (s.equals("category")) {
            return this.ki.category;
        }
        return super.getProperty(s);
    }
    
    public String getFactoryName() {
        return "katedec";
    }
    
    public String getMime() {
        return "application/x-kate";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        if (MemUtils.startsWith(array, n, n2, KateDec.signature)) {
            return 10;
        }
        return -1;
    }
    
    static {
        signature = new byte[] { -128, 107, 97, 116, 101, 0, 0, 0 };
    }
}
