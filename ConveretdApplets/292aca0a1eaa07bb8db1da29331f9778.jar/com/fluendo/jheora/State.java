// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Packet;

public class State
{
    long granulepos;
    private Playback pbi;
    private Decode dec;
    
    public void clear() {
        if (this.pbi != null) {
            this.pbi.info.clear();
            this.pbi.clearHuffmanSet();
            FrInit.ClearFragmentInfo(this.pbi);
            FrInit.ClearFrameInfo(this.pbi);
            this.pbi.clear();
        }
        this.pbi = null;
    }
    
    public int decodeInit(final Info ci) {
        this.pbi = new Playback(ci);
        this.dec = new Decode(this.pbi);
        this.granulepos = -1L;
        return 0;
    }
    
    public boolean isKeyframe(final Packet op) {
        return (op.packet_base[op.packet] & 0x40) == 0x0;
    }
    
    public int decodePacketin(final Packet op) {
        this.pbi.DecoderErrorCode = 0;
        this.pbi.opb.readinit(op.packet_base, op.packet, op.bytes);
        long ret = this.pbi.opb.readB(1);
        if (ret != 0L) {
            return -24;
        }
        ret = this.dec.loadAndDecode();
        if (ret != 0L) {
            return (int)ret;
        }
        if (op.granulepos > -1L) {
            this.granulepos = op.granulepos;
        }
        else if (this.granulepos == -1L) {
            this.granulepos = 0L;
        }
        else if (this.pbi.FrameType == 0) {
            final long frames = this.granulepos & (1 << this.pbi.keyframe_granule_shift) - 1;
            this.granulepos >>= this.pbi.keyframe_granule_shift;
            this.granulepos += frames + 1L;
            this.granulepos <<= this.pbi.keyframe_granule_shift;
        }
        else {
            ++this.granulepos;
        }
        return 0;
    }
    
    public int decodeYUVout(final YUVBuffer yuv) {
        yuv.y_width = this.pbi.info.width;
        yuv.y_height = this.pbi.info.height;
        yuv.y_stride = this.pbi.YStride;
        yuv.uv_width = this.pbi.info.width / 2;
        yuv.uv_height = this.pbi.info.height / 2;
        yuv.uv_stride = this.pbi.UVStride;
        if (this.pbi.PostProcessingLevel != 0) {
            yuv.data = this.pbi.PostProcessBuffer;
        }
        else {
            yuv.data = this.pbi.LastFrameRecon;
        }
        yuv.y_offset = this.pbi.ReconYDataOffset;
        yuv.u_offset = this.pbi.ReconUDataOffset;
        yuv.v_offset = this.pbi.ReconVDataOffset;
        yuv.y_offset += yuv.y_stride * (yuv.y_height - 1);
        yuv.u_offset += yuv.uv_stride * (yuv.uv_height - 1);
        yuv.v_offset += yuv.uv_stride * (yuv.uv_height - 1);
        yuv.y_stride = -yuv.y_stride;
        yuv.uv_stride = -yuv.uv_stride;
        yuv.newPixels();
        return 0;
    }
    
    public double granuleTime(final long granulepos) {
        if (granulepos >= 0L) {
            final long iframe = granulepos >> this.pbi.keyframe_granule_shift;
            final long pframe = granulepos - (iframe << this.pbi.keyframe_granule_shift);
            return (iframe + pframe) * (this.pbi.info.fps_denominator / this.pbi.info.fps_numerator);
        }
        return -1.0;
    }
}
