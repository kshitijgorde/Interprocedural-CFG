// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Caps;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.jst.Buffer;
import java.util.Vector;
import com.fluendo.jst.Pad;
import com.jcraft.jogg.Packet;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.Info;
import com.fluendo.jst.Element;

public class VorbisDec extends Element implements OggPayload
{
    private long packet;
    private long offset;
    private Info vi;
    private Comment vc;
    private DspState vd;
    private Block vb;
    private boolean discont;
    private Packet op;
    private float[][][] _pcmf;
    private int[] _index;
    private static final byte[] signature;
    private Pad srcPad;
    private Pad sinkPad;
    
    public boolean isType(final Packet op) {
        return this.typeFind(op.packet_base, op.packet, op.bytes) > 0;
    }
    
    public int takeHeader(final Packet op) {
        return this.vi.synthesis_headerin(this.vc, op);
    }
    
    public boolean isHeader(final Packet op) {
        return (op.packet_base[op.packet] & 0x1) == 0x1;
    }
    
    public boolean isKeyFrame(final Packet op) {
        return true;
    }
    
    public long getFirstTs(final Vector packets) {
        final int len = packets.size();
        long total = 0L;
        long prevSamples = 0L;
        final Packet p = new Packet();
        for (int i = 0; i < len; ++i) {
            Buffer buf = packets.elementAt(i);
            p.packet_base = buf.data;
            p.packet = buf.offset;
            p.bytes = buf.length;
            final long samples = this.vi.blocksize(p);
            if (samples <= 0L) {
                return -1L;
            }
            boolean ignore;
            if (prevSamples == 0L) {
                prevSamples = samples;
                ignore = true;
            }
            else {
                ignore = false;
            }
            final long temp = (samples + prevSamples) / 4L;
            prevSamples = samples;
            if (!ignore) {
                total += temp;
            }
            if (buf.time_offset != -1L) {
                total = buf.time_offset - total;
                final long result = this.granuleToTime(total);
                buf = packets.elementAt(0);
                return buf.timestamp = result;
            }
        }
        return -1L;
    }
    
    public long granuleToTime(final long gp) {
        if (gp < 0L) {
            return -1L;
        }
        return gp * 1000000L / this.vi.rate;
    }
    
    public VorbisDec() {
        this._pcmf = new float[1][][];
        this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return VorbisDec.this.sinkPad.pushEvent(event);
            }
        };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean result = false;
                switch (event.getType()) {
                    case 1: {
                        result = VorbisDec.this.srcPad.pushEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(4, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        result = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 3: {
                        Debug.log(3, "got EOS " + this);
                        result = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        result = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return result;
            }
            
            protected int chainFunc(final Buffer buf) {
                int result = 0;
                VorbisDec.this.op.packet_base = buf.data;
                VorbisDec.this.op.packet = buf.offset;
                VorbisDec.this.op.bytes = buf.length;
                VorbisDec.this.op.b_o_s = ((VorbisDec.this.packet == 0L) ? 1 : 0);
                VorbisDec.this.op.e_o_s = 0;
                VorbisDec.this.op.packetno = VorbisDec.this.packet;
                if (buf.isFlagSet(1)) {
                    VorbisDec.this.offset = -1L;
                    VorbisDec.this.discont = true;
                    Debug.log(3, "vorbis: got discont");
                    VorbisDec.this.vd.synthesis_init(VorbisDec.this.vi);
                }
                if (VorbisDec.this.packet < 3L) {
                    if (VorbisDec.this.vi.synthesis_headerin(VorbisDec.this.vc, VorbisDec.this.op) < 0) {
                        Debug.log(1, "This Ogg bitstream does not contain Vorbis audio data.");
                        return -5;
                    }
                    if (VorbisDec.this.packet == 2L) {
                        VorbisDec.this.vd.synthesis_init(VorbisDec.this.vi);
                        VorbisDec.this.vb.init(VorbisDec.this.vd);
                        Debug.log(3, "vorbis rate: " + VorbisDec.this.vi.rate);
                        Debug.log(3, "vorbis channels: " + VorbisDec.this.vi.channels);
                        VorbisDec.this._index = new int[VorbisDec.this.vi.channels];
                        (this.caps = new Caps("audio/raw")).setFieldInt("width", 16);
                        this.caps.setFieldInt("depth", 16);
                        this.caps.setFieldInt("rate", VorbisDec.this.vi.rate);
                        this.caps.setFieldInt("channels", VorbisDec.this.vi.channels);
                    }
                    buf.free();
                    VorbisDec.this.packet++;
                    return 0;
                }
                else {
                    if (VorbisDec.this.isHeader(VorbisDec.this.op)) {
                        Debug.log(3, "ignoring header");
                        return 0;
                    }
                    long timestamp = buf.timestamp;
                    if (timestamp != -1L) {
                        VorbisDec.this.offset = timestamp * VorbisDec.this.vi.rate / 1000000L;
                    }
                    else {
                        timestamp = VorbisDec.this.offset * 1000000L / VorbisDec.this.vi.rate;
                    }
                    if (VorbisDec.this.vb.synthesis(VorbisDec.this.op) == 0) {
                        VorbisDec.this.vd.synthesis_blockin(VorbisDec.this.vb);
                        int samples;
                        while ((samples = VorbisDec.this.vd.synthesis_pcmout(VorbisDec.this._pcmf, VorbisDec.this._index)) > 0) {
                            final float[][] pcmf = VorbisDec.this._pcmf[0];
                            final int numbytes = samples * 2 * VorbisDec.this.vi.channels;
                            int k = 0;
                            buf.ensureSize(numbytes);
                            buf.offset = 0;
                            buf.timestamp = timestamp;
                            buf.time_offset = VorbisDec.this.offset;
                            buf.length = numbytes;
                            buf.caps = this.caps;
                            buf.setFlag(1, VorbisDec.this.discont);
                            VorbisDec.this.discont = false;
                            for (int j = 0; j < samples; ++j) {
                                for (int i = 0; i < VorbisDec.this.vi.channels; ++i) {
                                    int val = (int)(pcmf[i][VorbisDec.this._index[i] + j] * 32767.0);
                                    if (val > 32767) {
                                        val = 32767;
                                    }
                                    else if (val < -32768) {
                                        val = -32768;
                                    }
                                    buf.data[k] = (byte)(val >> 8 & 0xFF);
                                    buf.data[k + 1] = (byte)(val & 0xFF);
                                    k += 2;
                                }
                            }
                            VorbisDec.this.vd.synthesis_read(samples);
                            VorbisDec.this.offset += samples;
                            if ((result = VorbisDec.this.srcPad.push(buf)) != 0) {
                                break;
                            }
                        }
                        VorbisDec.this.packet++;
                        return result;
                    }
                    Debug.log(1, "decoding error");
                    return -5;
                }
            }
        };
        this.vi = new Info();
        this.vc = new Comment();
        this.vd = new DspState();
        this.vb = new Block(this.vd);
        this.op = new Packet();
        this.addPad(this.srcPad);
        this.addPad(this.sinkPad);
    }
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                this.packet = 0L;
                this.offset = -1L;
                this.vi.init();
                this.vc.init();
                break;
            }
        }
        final int res = super.changeState(transition);
        return res;
    }
    
    public String getFactoryName() {
        return "vorbisdec";
    }
    
    public String getMime() {
        return "audio/x-vorbis";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        if (MemUtils.startsWith(data, offset, length, VorbisDec.signature)) {
            return 10;
        }
        return -1;
    }
    
    static {
        signature = new byte[] { 1, 118, 111, 114, 98, 105, 115 };
    }
}
