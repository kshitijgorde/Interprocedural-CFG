// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class Event
{
    public Info ki;
    public long start;
    public long duration;
    public long backlink;
    public double start_time;
    public double end_time;
    public int id;
    public KateTextEncoding text_encoding;
    public KateTextDirectionality text_directionality;
    public KateMarkupType markup_type;
    public byte[] language;
    public Region kr;
    public Style ks;
    public Style ks2;
    public Motion[] motions;
    public Palette palette;
    public Bitmap bitmap;
    public FontMapping font_mapping;
    public byte[] text;
    
    public Event(final Info ki) {
        this.ki = null;
        this.id = -1;
        this.kr = null;
        this.ks = null;
        this.ks2 = null;
        this.motions = null;
        this.palette = null;
        this.bitmap = null;
        this.font_mapping = null;
        this.ki = ki;
        this.id = -1;
        this.kr = null;
        this.ks = null;
        this.ks2 = null;
        this.motions = null;
        this.palette = null;
        this.bitmap = null;
        this.text = null;
        this.font_mapping = null;
        this.text_encoding = ki.text_encoding;
        this.text_directionality = ki.text_directionality;
        this.markup_type = ki.markup_type;
        this.language = null;
    }
}
