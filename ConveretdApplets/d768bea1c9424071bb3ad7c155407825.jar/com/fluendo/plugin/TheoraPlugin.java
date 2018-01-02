// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.Debug;
import com.fluendo.player.MediaBuffer;
import com.fluendo.jheora.YUVBuffer;
import com.jcraft.jogg.Packet;
import com.fluendo.jheora.State;
import com.fluendo.jheora.Comment;
import com.fluendo.jheora.Info;
import java.awt.Toolkit;
import java.awt.Component;
import com.fluendo.player.Plugin;

public class TheoraPlugin extends Plugin
{
    private Component component;
    private Toolkit toolkit;
    private Info ti;
    private Comment tc;
    private State ts;
    private Packet op;
    private int packet;
    private YUVBuffer yuv;
    private long baseTime;
    private boolean bTzOffset;
    private long tzOffset;
    
    public TheoraPlugin() {
        super(2);
    }
    
    public String getMime() {
        return "video/x-theora";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        if (array[n + 1] == 116) {
            return 20;
        }
        return 0;
    }
    
    public void initDecoder(final Component component) {
        this.component = component;
        this.toolkit = this.component.getToolkit();
        this.ti = new Info();
        this.tc = new Comment();
        this.ts = new State();
        this.yuv = new YUVBuffer();
        this.op = new Packet();
        this.packet = 0;
    }
    
    public long offsetToTime(final long n) {
        return (long)(this.ts.granuleTime(n) * 1000.0);
    }
    
    public MediaBuffer decode(MediaBuffer mediaBuffer) {
        this.op.packet_base = mediaBuffer.data;
        this.op.packet = mediaBuffer.offset;
        this.op.bytes = mediaBuffer.length;
        this.op.b_o_s = ((this.packet == 0) ? 1 : 0);
        this.op.e_o_s = 0;
        this.op.packetno = this.packet;
        if (this.packet < 3) {
            if (this.ti.decodeHeader(this.tc, this.op) < 0) {
                mediaBuffer.free();
                Debug.log(1, "does not contain Theora video data.");
                return null;
            }
            if (this.packet == 2) {
                this.ts.decodeInit(this.ti);
                Debug.log(3, "theora dimension: " + this.ti.width + "x" + this.ti.height);
                if (this.ti.aspect_denominator == 0) {
                    this.ti.aspect_numerator = 1;
                    this.ti.aspect_denominator = 1;
                }
                Debug.log(3, "theora offset: " + this.ti.offset_x + "," + this.ti.offset_y);
                Debug.log(3, "theora frame: " + this.ti.frame_width + "," + this.ti.frame_height);
                Debug.log(3, "theora aspect: " + this.ti.aspect_numerator + "/" + this.ti.aspect_denominator);
                Debug.log(3, "theora framerate: " + this.ti.fps_numerator + "/" + this.ti.fps_denominator);
                super.fps_numerator = this.ti.fps_numerator;
                super.fps_denominator = this.ti.fps_denominator;
                super.aspect_numerator = this.ti.aspect_numerator;
                super.aspect_denominator = this.ti.aspect_denominator;
                Debug.log(4, "Comments: " + this.tc.toString());
                try {
                    this.tzOffset = new Integer(this.tc.getComment(1)) * 1000L;
                    this.bTzOffset = true;
                }
                catch (NumberFormatException ex) {
                    this.tzOffset = 0L;
                    this.bTzOffset = false;
                }
                if (!this.bTzOffset) {
                    Debug.log(3, "TZoffset unused: " + this.baseTime);
                    try {
                        this.baseTime = new Integer(this.tc.getComment(0)) * 1000L;
                    }
                    catch (NumberFormatException ex2) {
                        this.baseTime = 0L;
                    }
                }
                else {
                    this.baseTime = System.currentTimeMillis() + this.tzOffset;
                    Debug.log(3, "TZoffset used: " + this.baseTime + " " + this.tzOffset);
                }
            }
            mediaBuffer.free();
            mediaBuffer = null;
        }
        else {
            if (this.ts.decodePacketin(this.op) != 0) {
                mediaBuffer.free();
                Debug.log(1, "Error Decoding Theora.");
                return null;
            }
            if (this.ts.decodeYUVout(this.yuv) != 0) {
                mediaBuffer.free();
                Debug.log(1, "Error getting the picture.");
                return null;
            }
            mediaBuffer.object = this.yuv.getObject(this.ti.offset_x, this.ti.offset_y, this.ti.frame_width, this.ti.frame_height);
        }
        ++this.packet;
        return mediaBuffer;
    }
    
    public void stop() {
    }
    
    public long getImageTime() {
        return this.baseTime;
    }
    
    public boolean haveTzOffset() {
        return this.bTzOffset;
    }
    
    public long getTzOffset() {
        return this.tzOffset;
    }
}
