// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.fluendo.utils.Debug;
import java.awt.Dimension;

public final class Tracker
{
    private Dimension window;
    private Dimension frame;
    public Event ev;
    public boolean[] has;
    public static final int has_region = 0;
    public static final int has_text_alignment_int = 1;
    public static final int has_text_alignment_ext = 2;
    public float region_x;
    public float region_y;
    public float region_w;
    public float region_h;
    
    public Tracker(final Event ev) {
        this.ev = null;
        this.has = new boolean[64];
        this.ev = ev;
    }
    
    public boolean update(final double n, final Dimension window, final Dimension frame) {
        this.window = window;
        this.frame = frame;
        final Region kr = this.ev.kr;
        if (this.ev.ks == null && kr != null && kr.style >= 0) {
            final Style style = this.ev.ki.styles[kr.style];
        }
        for (int i = 0; i < this.has.length; ++i) {
            this.has[i] = false;
        }
        if (kr != null) {
            if (kr.metric == KateSpaceMetric.kate_metric_percentage) {
                this.region_x = kr.x * frame.width / 100.0f;
                this.region_y = kr.y * frame.height / 100.0f;
                this.region_w = kr.w * frame.width / 100.0f;
                this.region_h = kr.h * frame.height / 100.0f;
            }
            else if (kr.metric == KateSpaceMetric.kate_metric_millionths) {
                this.region_x = kr.x * frame.width / 1000000.0f;
                this.region_y = kr.y * frame.height / 1000000.0f;
                this.region_w = kr.w * frame.width / 1000000.0f;
                this.region_h = kr.h * frame.height / 1000000.0f;
            }
            else {
                if (kr.metric != KateSpaceMetric.kate_metric_pixels) {
                    Debug.debug("Invalid metrics");
                    return false;
                }
                this.region_x = kr.x;
                this.region_y = kr.y;
                this.region_w = kr.w;
                this.region_h = kr.h;
            }
            this.has[0] = true;
        }
        return true;
    }
}
