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
    
    public boolean isType(final Packet op) {
        return this.typeFind(op.packet_base, op.packet, op.bytes) > 0;
    }
    
    public int takeHeader(final Packet op) {
        final int ret = this.ti.decodeHeader(this.tc, op);
        final byte header = op.packet_base[op.packet];
        if (header == -126) {
            this.ts.decodeInit(this.ti);
            this.haveDecoder = true;
        }
        return ret;
    }
    
    public boolean isHeader(final Packet op) {
        return (op.packet_base[op.packet] & 0x80) == 0x80;
    }
    
    public boolean isKeyFrame(final Packet op) {
        return this.ts.isKeyframe(op);
    }
    
    public long getFirstTs(final Vector packets) {
        final int len = packets.size();
        Buffer data = null;
        int i;
        for (i = 0; i < len; ++i) {
            data = packets.elementAt(i);
            if (data.time_offset != -1L) {
                break;
            }
        }
        if (i == packets.size()) {
            return -1L;
        }
        final long time = this.granuleToTime(data.time_offset);
        data = packets.elementAt(0);
        data.timestamp = time - (i + 1) * (1000000L * this.ti.fps_denominator / this.ti.fps_numerator);
        return time;
    }
    
    public long granuleToTime(final long gp) {
        if (gp < 0L || !this.haveDecoder) {
            return -1L;
        }
        final long res = (long)(this.ts.granuleTime(gp) * 1000000.0);
        return res;
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
                boolean result = false;
                switch (event.getType()) {
                    case 1: {
                        result = TheoraDec.this.srcPad.pushEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(4, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        result = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 3: {
                        Debug.log(3, "got EOS " + this);
                        result = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        result = TheoraDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return result;
            }
            
            protected int chainFunc(final Buffer buf) {
                TheoraDec.this.op.packet_base = buf.data;
                TheoraDec.this.op.packet = buf.offset;
                TheoraDec.this.op.bytes = buf.length;
                TheoraDec.this.op.b_o_s = ((TheoraDec.this.packet == 0) ? 1 : 0);
                TheoraDec.this.op.e_o_s = 0;
                TheoraDec.this.op.packetno = TheoraDec.this.packet;
                long timestamp = buf.timestamp;
                if (buf.isFlagSet(1)) {
                    Debug.log(3, "theora: got discont");
                    TheoraDec.this.needKeyframe = true;
                    TheoraDec.this.lastTs = -1L;
                }
                if (TheoraDec.this.packet < 3) {
                    if (TheoraDec.this.takeHeader(TheoraDec.this.op) < 0) {
                        buf.free();
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
                        (this.caps = new Caps("video/raw")).setFieldInt("width", TheoraDec.this.ti.frame_width);
                        this.caps.setFieldInt("height", TheoraDec.this.ti.frame_height);
                        this.caps.setFieldInt("aspect_x", TheoraDec.this.ti.aspect_numerator);
                        this.caps.setFieldInt("aspect_y", TheoraDec.this.ti.aspect_denominator);
                    }
                    buf.free();
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
                        final long add = 1000000L * TheoraDec.this.ti.fps_denominator / TheoraDec.this.ti.fps_numerator;
                        TheoraDec.this.lastTs += add;
                        timestamp = TheoraDec.this.lastTs;
                    }
                    int result;
                    if (!TheoraDec.this.needKeyframe) {
                        try {
                            if (TheoraDec.this.ts.decodePacketin(TheoraDec.this.op) != 0) {
                                buf.free();
                                Debug.log(1, "Error Decoding Theora.");
                                TheoraDec.this.postMessage(Message.newError(this, "Error decoding Theora"));
                                return -5;
                            }
                            if (TheoraDec.this.ts.decodeYUVout(TheoraDec.this.yuv) != 0) {
                                buf.free();
                                TheoraDec.this.postMessage(Message.newError(this, "Error getting the Theora picture"));
                                Debug.log(1, "Error getting the picture.");
                                return -5;
                            }
                            buf.object = TheoraDec.this.yuv.getObject(TheoraDec.this.ti.offset_x, TheoraDec.this.ti.offset_y, TheoraDec.this.ti.frame_width, TheoraDec.this.ti.frame_height);
                            buf.caps = this.caps;
                            buf.timestamp = timestamp;
                            result = TheoraDec.this.srcPad.push(buf);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            TheoraDec.this.postMessage(Message.newError(this, e.getMessage()));
                            result = -5;
                        }
                    }
                    else {
                        result = 0;
                        buf.free();
                    }
                    TheoraDec.this.packet++;
                    return result;
                }
            }
            
            protected boolean activateFunc(final int mode) {
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
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                this.lastTs = -1L;
                this.packet = 0;
                this.needKeyframe = true;
                break;
            }
        }
        final int res = super.changeState(transition);
        switch (transition) {
            case 33: {
                this.ti.clear();
                this.tc.clear();
                this.ts.clear();
                break;
            }
        }
        return res;
    }
    
    public String getFactoryName() {
        return "theoradec";
    }
    
    public String getMime() {
        return "video/x-theora";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        if (MemUtils.startsWith(data, offset, length, TheoraDec.signature)) {
            return 10;
        }
        return -1;
    }
    
    static {
        signature = new byte[] { -128, 116, 104, 101, 111, 114, 97 };
    }
}
