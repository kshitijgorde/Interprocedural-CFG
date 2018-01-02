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
    private boolean need_keyframe;
    
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
    
    public int decodeInit(final Info info) {
        this.pbi = new Playback(info);
        this.dec = new Decode(this.pbi);
        this.granulepos = -1L;
        this.need_keyframe = true;
        return 0;
    }
    
    public int decodePacketin(final Packet packet) {
        this.pbi.DecoderErrorCode = 0;
        this.pbi.opb.readinit(packet.packet_base, packet.packet, packet.bytes);
        if (this.pbi.opb.readB(1) != 0L) {
            return -24;
        }
        if (this.need_keyframe) {
            if ((packet.packet_base[packet.packet] & 0x40) != 0x0) {
                System.out.println("wait keyframe");
                return 0;
            }
            this.need_keyframe = false;
        }
        final long n = this.dec.loadAndDecode();
        if (n != 0L) {
            return (int)n;
        }
        if (packet.granulepos > -1L) {
            this.granulepos = packet.granulepos;
        }
        else if (this.granulepos == -1L) {
            this.granulepos = 0L;
        }
        else if (this.pbi.FrameType == 0) {
            final long n2 = this.granulepos & (1 << this.pbi.keyframe_granule_shift) - 1;
            this.granulepos >>= this.pbi.keyframe_granule_shift;
            this.granulepos += n2 + 1L;
            this.granulepos <<= this.pbi.keyframe_granule_shift;
        }
        else {
            ++this.granulepos;
        }
        return 0;
    }
    
    public int decodeYUVout(final YUVBuffer yuvBuffer) {
        yuvBuffer.y_width = this.pbi.info.width;
        yuvBuffer.y_height = this.pbi.info.height;
        yuvBuffer.y_stride = this.pbi.YStride;
        yuvBuffer.uv_width = this.pbi.info.width / 2;
        yuvBuffer.uv_height = this.pbi.info.height / 2;
        yuvBuffer.uv_stride = this.pbi.UVStride;
        if (this.pbi.PostProcessingLevel != 0) {
            yuvBuffer.data = this.pbi.PostProcessBuffer;
        }
        else {
            yuvBuffer.data = this.pbi.LastFrameRecon;
        }
        yuvBuffer.y_offset = this.pbi.ReconYDataOffset;
        yuvBuffer.u_offset = this.pbi.ReconUDataOffset;
        yuvBuffer.v_offset = this.pbi.ReconVDataOffset;
        yuvBuffer.y_offset += yuvBuffer.y_stride * (yuvBuffer.y_height - 1);
        yuvBuffer.u_offset += yuvBuffer.uv_stride * (yuvBuffer.uv_height - 1);
        yuvBuffer.v_offset += yuvBuffer.uv_stride * (yuvBuffer.uv_height - 1);
        yuvBuffer.y_stride = -yuvBuffer.y_stride;
        yuvBuffer.uv_stride = -yuvBuffer.uv_stride;
        yuvBuffer.newPixels();
        return 0;
    }
    
    public double granuleTime(final long n) {
        if (n >= 0L) {
            final long n2 = n >> this.pbi.keyframe_granule_shift;
            return (n2 + (n - (n2 << this.pbi.keyframe_granule_shift))) * (this.pbi.info.fps_denominator / this.pbi.info.fps_numerator);
        }
        return -1.0;
    }
}
