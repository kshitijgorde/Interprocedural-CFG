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
    short[][][][] dequant_tables;
    int[] AcScaleFactorTable;
    short[] DcScaleFactorTable;
    int MaxQMatrixIndex;
    short[] qmats;
    HuffEntry[] HuffRoot;
    byte[] LoopFilterLimitValues;
    
    public Info() {
        this.dequant_tables = new short[2][3][64][64];
        this.AcScaleFactorTable = new int[64];
        this.DcScaleFactorTable = new short[64];
        this.HuffRoot = new HuffEntry[80];
        this.LoopFilterLimitValues = new byte[64];
    }
    
    private static void _tp_readbuffer(final Buffer buffer, final byte[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)buffer.readB(8);
        }
    }
    
    private static int _tp_readlsbint(final Buffer buffer) {
        return buffer.readB(8) | buffer.readB(8) << 8 | buffer.readB(8) << 16 | buffer.readB(8) << 24;
    }
    
    private int unpackInfo(final Buffer buffer) {
        this.version_major = (byte)buffer.readB(8);
        this.version_minor = (byte)buffer.readB(8);
        this.version_subminor = (byte)buffer.readB(8);
        if (this.version_major != 3) {
            return -22;
        }
        if (this.version_minor > 2) {
            return -22;
        }
        this.width = buffer.readB(16) << 4;
        this.height = buffer.readB(16) << 4;
        this.frame_width = buffer.readB(24);
        this.frame_height = buffer.readB(24);
        this.offset_x = buffer.readB(8);
        this.offset_y = buffer.readB(8);
        this.offset_y = this.height - this.frame_height - this.offset_y;
        this.fps_numerator = buffer.readB(32);
        this.fps_denominator = buffer.readB(32);
        this.aspect_numerator = buffer.readB(24);
        this.aspect_denominator = buffer.readB(24);
        this.colorspace = Colorspace.spaces[buffer.readB(8)];
        this.target_bitrate = buffer.readB(24);
        this.quality = buffer.readB(6);
        this.keyframe_frequency_force = 1 << buffer.readB(5);
        if (buffer.readB(5) == -1) {
            return -20;
        }
        return 0;
    }
    
    static int unpackComment(final Comment comment, final Buffer buffer) {
        final int tp_readlsbint = _tp_readlsbint(buffer);
        if (tp_readlsbint < 0) {
            return -20;
        }
        final byte[] array = new byte[tp_readlsbint];
        _tp_readbuffer(buffer, array, tp_readlsbint);
        comment.vendor = new String(array);
        final int tp_readlsbint2 = _tp_readlsbint(buffer);
        if (tp_readlsbint2 < 0) {
            comment.clear();
            return -20;
        }
        comment.user_comments = new String[tp_readlsbint2];
        for (int i = 0; i < tp_readlsbint2; ++i) {
            final int tp_readlsbint3 = _tp_readlsbint(buffer);
            if (tp_readlsbint3 < 0) {
                comment.clear();
                return -20;
            }
            final byte[] array2 = new byte[tp_readlsbint3];
            _tp_readbuffer(buffer, array2, tp_readlsbint3);
            comment.user_comments[i] = new String(array2);
        }
        return 0;
    }
    
    private int readFilterTables(final Buffer buffer) {
        final int b = buffer.readB(3);
        for (int i = 0; i < 64; ++i) {
            this.LoopFilterLimitValues[i] = (byte)buffer.readB(b);
        }
        if (b < 0) {
            return -20;
        }
        return 0;
    }
    
    private int unpackTables(final Buffer buffer) {
        final int filterTables = this.readFilterTables(buffer);
        if (filterTables != 0) {
            return filterTables;
        }
        final int qTables = Quant.readQTables(this, buffer);
        if (qTables != 0) {
            return qTables;
        }
        final int huffmanTrees = Huffman.readHuffmanTrees(this.HuffRoot, buffer);
        if (huffmanTrees != 0) {
            return huffmanTrees;
        }
        return huffmanTrees;
    }
    
    public void clear() {
        this.qmats = null;
        Huffman.clearHuffmanTrees(this.HuffRoot);
    }
    
    public int decodeHeader(final Comment comment, final Packet packet) {
        final Buffer buffer = new Buffer();
        buffer.readinit(packet.packet_base, packet.packet, packet.bytes);
        final byte[] array = new byte[6];
        final int b = buffer.readB(8);
        if ((b & 0x80) == 0x0) {
            return -21;
        }
        _tp_readbuffer(buffer, array, 6);
        if (!"theora".equals(new String(array))) {
            return -21;
        }
        switch (b) {
            case 128: {
                if (packet.b_o_s == 0) {
                    return -20;
                }
                if (this.version_major != 0) {
                    return -20;
                }
                return this.unpackInfo(buffer);
            }
            case 129: {
                if (this.version_major == 0) {
                    return -20;
                }
                return unpackComment(comment, buffer);
            }
            case 130: {
                if (this.version_major == 0 || comment.vendor == null) {
                    return -20;
                }
                return this.unpackTables(buffer);
            }
            default: {
                if (this.version_major == 0 || comment.vendor == null || this.HuffRoot[0] == null) {
                    return -20;
                }
                return -25;
            }
        }
    }
}
