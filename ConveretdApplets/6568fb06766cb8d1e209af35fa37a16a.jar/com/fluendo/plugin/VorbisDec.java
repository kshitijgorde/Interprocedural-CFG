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
    
    public boolean isType(final Packet packet) {
        return this.typeFind(packet.packet_base, packet.packet, packet.bytes) > 0;
    }
    
    public int takeHeader(final Packet packet) {
        return this.vi.synthesis_headerin(this.vc, packet);
    }
    
    public boolean isHeader(final Packet packet) {
        return (packet.packet_base[packet.packet] & 0x1) == 0x1;
    }
    
    public boolean isKeyFrame(final Packet packet) {
        return true;
    }
    
    public boolean isDiscontinuous() {
        return false;
    }
    
    public long getFirstTs(final Vector vector) {
        final int size = vector.size();
        long n = 0L;
        long n2 = 0L;
        final Packet packet = new Packet();
        for (int i = 0; i < size; ++i) {
            final Buffer buffer = vector.elementAt(i);
            packet.packet_base = buffer.data;
            packet.packet = buffer.offset;
            packet.bytes = buffer.length;
            final long n3 = this.vi.blocksize(packet);
            if (n3 <= 0L) {
                return -1L;
            }
            boolean b;
            if (n2 == 0L) {
                n2 = n3;
                b = true;
            }
            else {
                b = false;
            }
            final long n4 = (n3 + n2) / 4L;
            n2 = n3;
            if (!b) {
                n += n4;
            }
            if (buffer.time_offset != -1L) {
                return vector.elementAt(0).timestamp = this.granuleToTime(buffer.time_offset - n);
            }
        }
        return -1L;
    }
    
    public long granuleToTime(final long n) {
        if (n < 0L) {
            return -1L;
        }
        return n * 1000000L / this.vi.rate;
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
                boolean b = false;
                switch (event.getType()) {
                    case 1: {
                        b = VorbisDec.this.srcPad.pushEvent(event);
                        synchronized (super.streamLock) {
                            Debug.log(4, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        b = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    case 3: {
                        Debug.log(3, "got EOS " + this);
                        b = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        b = VorbisDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return b;
            }
            
            protected int chainFunc(final Buffer buffer) {
                int push = 0;
                VorbisDec.this.op.packet_base = buffer.data;
                VorbisDec.this.op.packet = buffer.offset;
                VorbisDec.this.op.bytes = buffer.length;
                VorbisDec.this.op.b_o_s = ((VorbisDec.this.packet == 0L) ? 1 : 0);
                VorbisDec.this.op.e_o_s = 0;
                VorbisDec.this.op.packetno = VorbisDec.this.packet;
                if (buffer.isFlagSet(1)) {
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
                        (super.caps = new Caps("audio/raw")).setFieldInt("width", 16);
                        super.caps.setFieldInt("depth", 16);
                        super.caps.setFieldInt("rate", VorbisDec.this.vi.rate);
                        super.caps.setFieldInt("channels", VorbisDec.this.vi.channels);
                    }
                    buffer.free();
                    VorbisDec.this.packet++;
                    return 0;
                }
                else {
                    if (VorbisDec.this.isHeader(VorbisDec.this.op)) {
                        Debug.log(3, "ignoring header");
                        return 0;
                    }
                    long timestamp = buffer.timestamp;
                    if (timestamp != -1L) {
                        VorbisDec.this.offset = timestamp * VorbisDec.this.vi.rate / 1000000L;
                    }
                    else {
                        timestamp = VorbisDec.this.offset * 1000000L / VorbisDec.this.vi.rate;
                    }
                    if (VorbisDec.this.vb.synthesis(VorbisDec.this.op) == 0) {
                        VorbisDec.this.vd.synthesis_blockin(VorbisDec.this.vb);
                        int synthesis_pcmout;
                        while ((synthesis_pcmout = VorbisDec.this.vd.synthesis_pcmout(VorbisDec.this._pcmf, VorbisDec.this._index)) > 0) {
                            final float[][] array = VorbisDec.this._pcmf[0];
                            final int length = synthesis_pcmout * 2 * VorbisDec.this.vi.channels;
                            int n = 0;
                            buffer.ensureSize(length);
                            buffer.offset = 0;
                            buffer.timestamp = timestamp;
                            buffer.time_offset = VorbisDec.this.offset;
                            buffer.length = length;
                            buffer.caps = super.caps;
                            buffer.setFlag(1, VorbisDec.this.discont);
                            VorbisDec.this.discont = false;
                            for (int i = 0; i < synthesis_pcmout; ++i) {
                                for (int j = 0; j < VorbisDec.this.vi.channels; ++j) {
                                    int n2 = (int)(array[j][VorbisDec.this._index[j] + i] * 32767.0);
                                    if (n2 > 32767) {
                                        n2 = 32767;
                                    }
                                    else if (n2 < -32768) {
                                        n2 = -32768;
                                    }
                                    buffer.data[n] = (byte)(n2 >> 8 & 0xFF);
                                    buffer.data[n + 1] = (byte)(n2 & 0xFF);
                                    n += 2;
                                }
                            }
                            VorbisDec.this.vd.synthesis_read(synthesis_pcmout);
                            VorbisDec.this.offset += synthesis_pcmout;
                            if ((push = VorbisDec.this.srcPad.push(buffer)) != 0) {
                                break;
                            }
                        }
                        VorbisDec.this.packet++;
                        return push;
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
    
    protected int changeState(final int n) {
        switch (n) {
            case 18: {
                this.packet = 0L;
                this.offset = -1L;
                this.vi.init();
                this.vc.init();
                break;
            }
        }
        return super.changeState(n);
    }
    
    public String getFactoryName() {
        return "vorbisdec";
    }
    
    public String getMime() {
        return "audio/x-vorbis";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        if (MemUtils.startsWith(array, n, n2, VorbisDec.signature)) {
            return 10;
        }
        return -1;
    }
    
    static {
        signature = new byte[] { 1, 118, 111, 114, 98, 105, 115 };
    }
}
