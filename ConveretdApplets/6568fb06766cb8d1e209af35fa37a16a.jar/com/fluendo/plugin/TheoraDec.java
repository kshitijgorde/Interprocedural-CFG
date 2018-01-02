// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Message;
import com.fluendo.jst.Caps;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.jst.Buffer;
import java.util.Vector;
import com.fluendo.jst.Pad;
import com.fluendo.jheora.YUVBuffer;
import com.jcraft.jogg.Packet;
import com.fluendo.jheora.State;
import com.fluendo.jheora.Comment;
import com.fluendo.jheora.Info;
import com.fluendo.jst.Element;

public class TheoraDec extends Element implements OggPayload
{
    private static final byte[] signature;
    private Info ti;
    private Comment tc;
    private State ts;
    private Packet op;
    private int packet;
    private YUVBuffer yuv;
    private long lastTs;
    private boolean needKeyframe;
    private boolean haveDecoder;
    private Pad srcPad;
    private Pad sinkPad;
    
    public boolean isType(final Packet packet) {
        return this.typeFind(packet.packet_base, packet.packet, packet.bytes) > 0;
    }
    
    public int takeHeader(final Packet packet) {
        final int decodeHeader = this.ti.decodeHeader(this.tc, packet);
        if (packet.packet_base[packet.packet] == -126) {
            this.ts.decodeInit(this.ti);
            this.haveDecoder = true;
        }
        return decodeHeader;
    }
    
    public boolean isHeader(final Packet packet) {
        return (packet.packet_base[packet.packet] & 0x80) == 0x80;
    }
    
    public boolean isKeyFrame(final Packet packet) {
        return this.ts.isKeyframe(packet);
    }
    
    public boolean isDiscontinuous() {
        return false;
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
        vector.elementAt(0).timestamp = granuleToTime - (i + 1) * (1000000L * this.ti.fps_denominator / this.ti.fps_numerator);
        return granuleToTime;
    }
    
    public long granuleToTime(final long n) {
        if (n < 0L || !this.haveDecoder) {
            return -1L;
        }
        return (long)(this.ts.granuleTime(n) * 1000000.0);
    }
    
    public TheoraDec() {
        this.haveDecoder = false;
        this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return TheoraDec.this.sinkPad.pushEvent(event);
            }
        };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean b = false;
                switch (event.getType()) {
                    case 1: {
                        b = TheoraDec.this.srcPad.pushEvent(event);
                        synchronized (super.streamLock) {
                            Debug.log(4, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        b = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 3: {
                        Debug.log(3, "got EOS " + this);
                        b = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        b = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return b;
            }
            
            protected int chainFunc(final Buffer buffer) {
                Debug.log(4, super.parent.getName() + " <<< " + buffer);
                TheoraDec.this.op.packet_base = buffer.data;
                TheoraDec.this.op.packet = buffer.offset;
                TheoraDec.this.op.bytes = buffer.length;
                TheoraDec.this.op.b_o_s = ((TheoraDec.this.packet == 0) ? 1 : 0);
                TheoraDec.this.op.e_o_s = 0;
                TheoraDec.this.op.packetno = TheoraDec.this.packet;
                long timestamp = buffer.timestamp;
                if (buffer.isFlagSet(1)) {
                    Debug.log(3, "theora: got discont");
                    TheoraDec.this.needKeyframe = true;
                    TheoraDec.this.lastTs = -1L;
                }
                if (TheoraDec.this.packet < 3) {
                    if (TheoraDec.this.takeHeader(TheoraDec.this.op) < 0) {
                        buffer.free();
                        Debug.log(1, "does not contain Theora video data.");
                        return -5;
                    }
                    if (TheoraDec.this.packet == 2) {
                        TheoraDec.this.ts.decodeInit(TheoraDec.this.ti);
                        Debug.log(3, "theora dimension: " + TheoraDec.this.ti.width + "x" + TheoraDec.this.ti.height);
                        if (TheoraDec.this.ti.aspect_denominator == 0) {
                            TheoraDec.this.ti.aspect_numerator = 1;
                            TheoraDec.this.ti.aspect_denominator = 1;
                        }
                        Debug.log(3, "theora offset: " + TheoraDec.this.ti.offset_x + "," + TheoraDec.this.ti.offset_y);
                        Debug.log(3, "theora frame: " + TheoraDec.this.ti.frame_width + "," + TheoraDec.this.ti.frame_height);
                        Debug.log(3, "theora aspect: " + TheoraDec.this.ti.aspect_numerator + "/" + TheoraDec.this.ti.aspect_denominator);
                        Debug.log(3, "theora framerate: " + TheoraDec.this.ti.fps_numerator + "/" + TheoraDec.this.ti.fps_denominator);
                        (super.caps = new Caps("video/raw")).setFieldInt("width", TheoraDec.this.ti.frame_width);
                        super.caps.setFieldInt("height", TheoraDec.this.ti.frame_height);
                        super.caps.setFieldInt("aspect_x", TheoraDec.this.ti.aspect_numerator);
                        super.caps.setFieldInt("aspect_y", TheoraDec.this.ti.aspect_denominator);
                    }
                    buffer.free();
                    TheoraDec.this.packet++;
                    return 0;
                }
                else {
                    if ((TheoraDec.this.op.packet_base[TheoraDec.this.op.packet] & 0x80) == 0x80) {
                        Debug.log(3, "ignoring header");
                        return 0;
                    }
                    if (TheoraDec.this.needKeyframe && TheoraDec.this.ts.isKeyframe(TheoraDec.this.op)) {
                        TheoraDec.this.needKeyframe = false;
                    }
                    if (timestamp != -1L) {
                        TheoraDec.this.lastTs = timestamp;
                    }
                    else if (TheoraDec.this.lastTs != -1L) {
                        TheoraDec.this.lastTs += 1000000L * TheoraDec.this.ti.fps_denominator / TheoraDec.this.ti.fps_numerator;
                        timestamp = TheoraDec.this.lastTs;
                    }
                    int push;
                    if (!TheoraDec.this.needKeyframe) {
                        try {
                            if (TheoraDec.this.ts.decodePacketin(TheoraDec.this.op) != 0) {
                                Debug.log(1, "Bad Theora packet. Most likely not fatal, hoping for better luck next packet.");
                            }
                            if (TheoraDec.this.ts.decodeYUVout(TheoraDec.this.yuv) != 0) {
                                buffer.free();
                                TheoraDec.this.postMessage(Message.newError(this, "Error getting the Theora picture"));
                                Debug.log(1, "Error getting the picture.");
                                return -5;
                            }
                            buffer.object = TheoraDec.this.yuv.getObject(TheoraDec.this.ti.offset_x, TheoraDec.this.ti.offset_y, TheoraDec.this.ti.frame_width, TheoraDec.this.ti.frame_height);
                            buffer.caps = super.caps;
                            buffer.timestamp = timestamp;
                            Debug.log(4, super.parent.getName() + " >>> " + buffer);
                            push = TheoraDec.this.srcPad.push(buffer);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            TheoraDec.this.postMessage(Message.newError(this, ex.getMessage()));
                            push = -5;
                        }
                    }
                    else {
                        push = 0;
                        buffer.free();
                    }
                    TheoraDec.this.packet++;
                    return push;
                }
            }
            
            protected boolean activateFunc(final int n) {
                return true;
            }
        };
        this.ti = new Info();
        this.tc = new Comment();
        this.ts = new State();
        this.yuv = new YUVBuffer();
        this.op = new Packet();
        this.addPad(this.srcPad);
        this.addPad(this.sinkPad);
    }
    
    protected int changeState(final int n) {
        switch (n) {
            case 18: {
                this.lastTs = -1L;
                this.packet = 0;
                this.needKeyframe = true;
                break;
            }
        }
        final int changeState = super.changeState(n);
        switch (n) {
            case 33: {
                this.ti.clear();
                this.tc.clear();
                this.ts.clear();
                break;
            }
        }
        return changeState;
    }
    
    public String getFactoryName() {
        return "theoradec";
    }
    
    public String getMime() {
        return "video/x-theora";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        if (MemUtils.startsWith(array, n, n2, TheoraDec.signature)) {
            return 10;
        }
        return -1;
    }
    
    static {
        signature = new byte[] { -128, 116, 104, 101, 111, 114, 97 };
    }
}
