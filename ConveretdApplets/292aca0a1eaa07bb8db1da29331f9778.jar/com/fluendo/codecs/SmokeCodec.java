// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.codecs;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

public class SmokeCodec
{
    private static final int IDX_TYPE = 0;
    private static final int IDX_WIDTH = 1;
    private static final int IDX_HEIGHT = 3;
    private static final int IDX_FPS_NUM = 5;
    private static final int IDX_FPS_DENOM = 9;
    private static final int IDX_FLAGS = 13;
    private static final int IDX_NUM_BLOCKS = 14;
    private static final int IDX_SIZE = 16;
    private static final int IDX_BLOCKS = 18;
    private static final int OFFS_PICT = 18;
    private Image reference;
    private MediaTracker mt;
    private Component component;
    private Toolkit toolkit;
    public static final int KEYFRAME = 1;
    public int type;
    public int width;
    public int height;
    public int fps_num;
    public int fps_denom;
    public int flags;
    public int size;
    public int blocks;
    
    public SmokeCodec(final Component comp, final MediaTracker tracker) {
        this.component = comp;
        this.toolkit = comp.getToolkit();
        this.mt = tracker;
    }
    
    public int parseHeader(final byte[] in, final int offset, final int length) {
        this.type = in[0 + offset];
        short b1 = in[1 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        short b2 = in[2 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        this.width = (b1 << 8 | b2);
        b1 = in[3 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        b2 = in[4 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        this.height = (b1 << 8 | b2);
        b1 = in[5 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        b2 = in[6 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        short b3 = in[7 + offset];
        if (b3 < 0) {
            b3 += 256;
        }
        short b4 = in[8 + offset];
        if (b4 < 0) {
            b4 += 256;
        }
        this.fps_num = (b1 << 24 | b2 << 16 | b3 << 8 | b4);
        b1 = in[9 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        b2 = in[10 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        b3 = in[11 + offset];
        if (b3 < 0) {
            b3 += 256;
        }
        b4 = in[12 + offset];
        if (b4 < 0) {
            b4 += 256;
        }
        this.fps_denom = (b1 << 24 | b2 << 16 | b3 << 8 | b4);
        this.flags = in[13 + offset];
        b1 = in[16 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        b2 = in[16 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        this.size = (b1 << 8 | b2);
        b1 = in[14 + offset];
        if (b1 < 0) {
            b1 += 256;
        }
        b2 = in[15 + offset];
        if (b2 < 0) {
            b2 += 256;
        }
        this.blocks = (b1 << 8 | b2);
        return 0;
    }
    
    public Image decode(final byte[] in, int offset, final int length) {
        this.parseHeader(in, offset, length);
        final boolean keyframe = (this.flags & 0x1) != 0x0;
        if (this.reference == null && !keyframe) {
            return null;
        }
        final int imgoff = this.blocks * 2 + 18;
        Image src = null;
        try {
            src = this.toolkit.createImage(in, imgoff + offset, length - imgoff);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (src == null) {
            System.out.println("failed");
            return null;
        }
        try {
            this.mt.addImage(src, 0);
            this.mt.waitForID(0);
            this.mt.removeImage(src, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.reference == null || keyframe) {
            this.reference = src;
        }
        else if (this.blocks > 0) {
            final int src_w = src.getWidth(null);
            final int src_h = src.getHeight(null);
            int blockptr = 0;
            offset += 18;
            final Image newref = this.component.createImage(this.width, this.height);
            final Graphics refgfx = newref.getGraphics();
            refgfx.drawImage(this.reference, 0, 0, null);
            this.reference = newref;
            for (int i = 0; i < src_h; i += 16) {
                for (int j = 0; j < src_w; j += 16) {
                    int pos = blockptr * 2 + offset;
                    int b1 = in[pos];
                    if (b1 < 0) {
                        b1 += 256;
                    }
                    int b2 = in[pos + 1];
                    if (b2 < 0) {
                        b2 += 256;
                    }
                    pos = (b1 << 8 | b2);
                    final int x = pos % (this.width / 16) * 16;
                    final int y = pos / (this.width / 16) * 16;
                    refgfx.drawImage(src, x, y, x + 16, y + 16, j, i, j + 16, i + 16, null);
                    if (++blockptr >= this.blocks) {
                        break;
                    }
                }
            }
        }
        return this.reference;
    }
}
