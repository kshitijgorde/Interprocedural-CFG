// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateMotionMapping
{
    public static final KateMotionMapping kmm_none;
    public static final KateMotionMapping kmm_frame;
    public static final KateMotionMapping kmm_window;
    public static final KateMotionMapping kmm_region;
    public static final KateMotionMapping kmm_event_duration;
    public static final KateMotionMapping kmm_bitmap_size;
    private static final KateMotionMapping[] list;
    
    public static KateMotionMapping CreateMotionMapping(final int n) throws KateException {
        if (n < 0 || n >= KateMotionMapping.list.length) {
            throw new KateException("Motion mapping " + n + " out of bounds");
        }
        return KateMotionMapping.list[n];
    }
    
    static {
        kmm_none = new KateMotionMapping();
        kmm_frame = new KateMotionMapping();
        kmm_window = new KateMotionMapping();
        kmm_region = new KateMotionMapping();
        kmm_event_duration = new KateMotionMapping();
        kmm_bitmap_size = new KateMotionMapping();
        list = new KateMotionMapping[] { KateMotionMapping.kmm_none, KateMotionMapping.kmm_frame, KateMotionMapping.kmm_window, KateMotionMapping.kmm_region, KateMotionMapping.kmm_event_duration, KateMotionMapping.kmm_bitmap_size };
    }
}
