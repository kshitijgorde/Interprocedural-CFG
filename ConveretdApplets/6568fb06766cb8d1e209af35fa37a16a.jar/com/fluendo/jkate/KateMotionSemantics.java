// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateMotionSemantics
{
    public static final KateMotionSemantics kms_time;
    public static final KateMotionSemantics kms_z;
    public static final KateMotionSemantics kms_region_position;
    public static final KateMotionSemantics kms_region_size;
    public static final KateMotionSemantics kms_text_alignment_int;
    public static final KateMotionSemantics kms_text_alignment_ext;
    public static final KateMotionSemantics kms_text_position;
    public static final KateMotionSemantics kms_text_size;
    public static final KateMotionSemantics kms_marker1_position;
    public static final KateMotionSemantics kms_marker2_position;
    public static final KateMotionSemantics kms_marker3_position;
    public static final KateMotionSemantics kms_marker4_position;
    public static final KateMotionSemantics kms_glyph_pointer_1;
    public static final KateMotionSemantics kms_glyph_pointer_2;
    public static final KateMotionSemantics kms_glyph_pointer_3;
    public static final KateMotionSemantics kms_glyph_pointer_4;
    public static final KateMotionSemantics kms_text_color_rg;
    public static final KateMotionSemantics kms_text_color_ba;
    public static final KateMotionSemantics kms_background_color_rg;
    public static final KateMotionSemantics kms_background_color_ba;
    public static final KateMotionSemantics kms_draw_color_rg;
    public static final KateMotionSemantics kms_draw_color_ba;
    public static final KateMotionSemantics kms_style_morph;
    public static final KateMotionSemantics kms_text_path;
    public static final KateMotionSemantics kms_text_path_section;
    public static final KateMotionSemantics kms_draw;
    public static final KateMotionSemantics kms_text_visible_section;
    public static final KateMotionSemantics kms_horizontal_margins;
    public static final KateMotionSemantics kms_vertical_margins;
    public static final KateMotionSemantics kms_bitmap_position;
    public static final KateMotionSemantics kms_bitmap_size;
    public static final KateMotionSemantics kms_marker1_bitmap;
    public static final KateMotionSemantics kms_marker2_bitmap;
    public static final KateMotionSemantics kms_marker3_bitmap;
    public static final KateMotionSemantics kms_marker4_bitmap;
    public static final KateMotionSemantics kms_glyph_pointer_1_bitmap;
    public static final KateMotionSemantics kms_glyph_pointer_2_bitmap;
    public static final KateMotionSemantics kms_glyph_pointer_3_bitmap;
    public static final KateMotionSemantics kms_glyph_pointer_4_bitmap;
    public static final KateMotionSemantics kms_draw_width;
    private static final KateMotionSemantics[] list;
    
    public static KateMotionSemantics CreateMotionSemantics(final int n) throws KateException {
        if (n < 0 || n >= KateMotionSemantics.list.length) {
            throw new KateException("Motion semantics " + n + " out of bounds");
        }
        return KateMotionSemantics.list[n];
    }
    
    static {
        kms_time = new KateMotionSemantics();
        kms_z = new KateMotionSemantics();
        kms_region_position = new KateMotionSemantics();
        kms_region_size = new KateMotionSemantics();
        kms_text_alignment_int = new KateMotionSemantics();
        kms_text_alignment_ext = new KateMotionSemantics();
        kms_text_position = new KateMotionSemantics();
        kms_text_size = new KateMotionSemantics();
        kms_marker1_position = new KateMotionSemantics();
        kms_marker2_position = new KateMotionSemantics();
        kms_marker3_position = new KateMotionSemantics();
        kms_marker4_position = new KateMotionSemantics();
        kms_glyph_pointer_1 = new KateMotionSemantics();
        kms_glyph_pointer_2 = new KateMotionSemantics();
        kms_glyph_pointer_3 = new KateMotionSemantics();
        kms_glyph_pointer_4 = new KateMotionSemantics();
        kms_text_color_rg = new KateMotionSemantics();
        kms_text_color_ba = new KateMotionSemantics();
        kms_background_color_rg = new KateMotionSemantics();
        kms_background_color_ba = new KateMotionSemantics();
        kms_draw_color_rg = new KateMotionSemantics();
        kms_draw_color_ba = new KateMotionSemantics();
        kms_style_morph = new KateMotionSemantics();
        kms_text_path = new KateMotionSemantics();
        kms_text_path_section = new KateMotionSemantics();
        kms_draw = new KateMotionSemantics();
        kms_text_visible_section = new KateMotionSemantics();
        kms_horizontal_margins = new KateMotionSemantics();
        kms_vertical_margins = new KateMotionSemantics();
        kms_bitmap_position = new KateMotionSemantics();
        kms_bitmap_size = new KateMotionSemantics();
        kms_marker1_bitmap = new KateMotionSemantics();
        kms_marker2_bitmap = new KateMotionSemantics();
        kms_marker3_bitmap = new KateMotionSemantics();
        kms_marker4_bitmap = new KateMotionSemantics();
        kms_glyph_pointer_1_bitmap = new KateMotionSemantics();
        kms_glyph_pointer_2_bitmap = new KateMotionSemantics();
        kms_glyph_pointer_3_bitmap = new KateMotionSemantics();
        kms_glyph_pointer_4_bitmap = new KateMotionSemantics();
        kms_draw_width = new KateMotionSemantics();
        list = new KateMotionSemantics[] { KateMotionSemantics.kms_time, KateMotionSemantics.kms_z, KateMotionSemantics.kms_region_position, KateMotionSemantics.kms_region_size, KateMotionSemantics.kms_text_alignment_int, KateMotionSemantics.kms_text_alignment_ext, KateMotionSemantics.kms_text_position, KateMotionSemantics.kms_text_size, KateMotionSemantics.kms_marker1_position, KateMotionSemantics.kms_marker2_position, KateMotionSemantics.kms_marker3_position, KateMotionSemantics.kms_marker4_position, KateMotionSemantics.kms_glyph_pointer_1, KateMotionSemantics.kms_glyph_pointer_2, KateMotionSemantics.kms_glyph_pointer_3, KateMotionSemantics.kms_glyph_pointer_4, KateMotionSemantics.kms_text_color_rg, KateMotionSemantics.kms_text_color_ba, KateMotionSemantics.kms_background_color_rg, KateMotionSemantics.kms_background_color_ba, KateMotionSemantics.kms_draw_color_rg, KateMotionSemantics.kms_draw_color_ba, KateMotionSemantics.kms_style_morph, KateMotionSemantics.kms_text_path, KateMotionSemantics.kms_text_path_section, KateMotionSemantics.kms_draw, KateMotionSemantics.kms_text_visible_section, KateMotionSemantics.kms_horizontal_margins, KateMotionSemantics.kms_vertical_margins, KateMotionSemantics.kms_bitmap_position, KateMotionSemantics.kms_bitmap_size, KateMotionSemantics.kms_marker1_bitmap, KateMotionSemantics.kms_marker2_bitmap, KateMotionSemantics.kms_marker3_bitmap, KateMotionSemantics.kms_marker4_bitmap, KateMotionSemantics.kms_glyph_pointer_1_bitmap, KateMotionSemantics.kms_glyph_pointer_2_bitmap, KateMotionSemantics.kms_glyph_pointer_3_bitmap, KateMotionSemantics.kms_glyph_pointer_4_bitmap, KateMotionSemantics.kms_draw_width };
    }
}
