// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Buffer;

public class Info
{
    public int width;
    public int height;
    public int frame_width;
    public int frame_height;
    public int offset_x;
    public int offset_y;
    public int fps_numerator;
    public int fps_denominator;
    public int aspect_numerator;
    public int aspect_denominator;
    public Colorspace colorspace;
    public int target_bitrate;
    public int quality;
    public int quick_p;
    public byte version_major;
    public byte version_minor;
    public byte version_subminor;
    public long keyframe_frequency_force;
    int[] QThreshTable;
    short[] DcScaleFactorTable;
    int MaxQMatrixIndex;
    short[] qmats;
    short[] Y_coeffs;
    short[] UV_coeffs;
    short[] Inter_coeffs;
    HuffEntry[] HuffRoot;
    byte[] LoopFilterLimitValues;
    
    public Info() {
        this.QThreshTable = new int[64];
        this.DcScaleFactorTable = new short[64];
        this.Y_coeffs = new short[64];
        this.UV_coeffs = new short[64];
        this.Inter_coeffs = new short[64];
        this.HuffRoot = new HuffEntry[80];
        this.LoopFilterLimitValues = new byte[64];
    }
    
    private static void _tp_readbuffer(final Buffer opb, final byte[] buf, final int len) {
        for (int i = 0; i < len; ++i) {
            buf[i] = (byte)opb.readB(8);
        }
    }
    
    private static int _tp_readlsbint(final Buffer opb) {
        int value = opb.readB(8);
        value |= opb.readB(8) << 8;
        value |= opb.readB(8) << 16;
        value |= opb.readB(8) << 24;
        return value;
    }
    
    private int unpackInfo(final Buffer opb) {
        this.version_major = (byte)opb.readB(8);
        this.version_minor = (byte)opb.readB(8);
        this.version_subminor = (byte)opb.readB(8);
        if (this.version_major != 3) {
            return -22;
        }
        if (this.version_minor > 2) {
            return -22;
        }
        this.width = opb.readB(16) << 4;
        this.height = opb.readB(16) << 4;
        this.frame_width = opb.readB(24);
        this.frame_height = opb.readB(24);
        this.offset_x = opb.readB(8);
        this.offset_y = opb.readB(8);
        this.fps_numerator = opb.readB(32);
        this.fps_denominator = opb.readB(32);
        this.aspect_numerator = opb.readB(24);
        this.aspect_denominator = opb.readB(24);
        this.colorspace = Colorspace.spaces[opb.readB(8)];
        this.target_bitrate = opb.readB(24);
        this.quality = opb.readB(6);
        this.keyframe_frequency_force = 1 << opb.readB(5);
        if (opb.readB(5) == -1) {
            return -20;
        }
        return 0;
    }
    
    static int unpackComment(final Comment tc, final Buffer opb) {
        int len = _tp_readlsbint(opb);
        if (len < 0) {
            return -20;
        }
        byte[] tmp = new byte[len];
        _tp_readbuffer(opb, tmp, len);
        tc.vendor = new String(tmp);
        final int comments = _tp_readlsbint(opb);
        if (comments < 0) {
            tc.clear();
            return -20;
        }
        tc.user_comments = new String[comments];
        for (int i = 0; i < comments; ++i) {
            len = _tp_readlsbint(opb);
            if (len < 0) {
                tc.clear();
                return -20;
            }
            tmp = new byte[len];
            _tp_readbuffer(opb, tmp, len);
            tc.user_comments[i] = new String(tmp);
        }
        return 0;
    }
    
    private int readFilterTables(final Buffer opb) {
        final int bits = opb.readB(3);
        for (int i = 0; i < 64; ++i) {
            final int value = opb.readB(bits);
            this.LoopFilterLimitValues[i] = (byte)value;
        }
        if (bits < 0) {
            return -20;
        }
        return 0;
    }
    
    private int unpackTables(final Buffer opb) {
        int ret = this.readFilterTables(opb);
        if (ret != 0) {
            return ret;
        }
        ret = Quant.readQTables(this, opb);
        if (ret != 0) {
            return ret;
        }
        ret = Huffman.readHuffmanTrees(this.HuffRoot, opb);
        if (ret != 0) {
            return ret;
        }
        return ret;
    }
    
    public void clear() {
        this.qmats = null;
        Huffman.clearHuffmanTrees(this.HuffRoot);
    }
    
    public int decodeHeader(final Comment cc, final Packet op) {
        final Buffer opb = new Buffer();
        opb.readinit(op.packet_base, op.packet, op.bytes);
        final byte[] id = new byte[6];
        final int typeflag = opb.readB(8);
        if ((typeflag & 0x80) == 0x0) {
            return -21;
        }
        _tp_readbuffer(opb, id, 6);
        if (!"theora".equals(new String(id))) {
            return -21;
        }
        switch (typeflag) {
            case 128: {
                if (op.b_o_s == 0) {
                    return -20;
                }
                if (this.version_major != 0) {
                    return -20;
                }
                final long ret = this.unpackInfo(opb);
                return (int)ret;
            }
            case 129: {
                if (this.version_major == 0) {
                    return -20;
                }
                final long ret = unpackComment(cc, opb);
                return (int)ret;
            }
            case 130: {
                if (this.version_major == 0 || cc.vendor == null) {
                    return -20;
                }
                final long ret = this.unpackTables(opb);
                return (int)ret;
            }
            default: {
                if (this.version_major == 0 || cc.vendor == null || this.HuffRoot[0] == null) {
                    return -20;
                }
                return -25;
            }
        }
    }
}
