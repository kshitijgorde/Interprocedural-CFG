// 
// Decompiled by Procyon v0.5.30
// 

class ZHeader5 extends ZHeader
{
    static final int INTERP_NUMBER = 30;
    static final int INTERP_VERSION = 31;
    static final int SCREEN_HEIGHT_LINES = 32;
    static final int SCREEN_WIDTH_CHARACTERS = 33;
    static final int SCREEN_HEIGHT_UNITS = 34;
    static final int SCREEN_WIDTH_UNITS = 36;
    static final int FONT_HEIGHT_UNITS = 38;
    static final int FONT_WIDTH_UNITS = 39;
    static final int DEFAULT_BACKGROUND_COLOR = 44;
    static final int DEFAULT_FOREGROUND_COLOR = 45;
    static final int FILE_LENGTH_FACTOR = 4;
    static final int INTERP_DEC = 1;
    static final int INTERP_APPLEIIE = 2;
    static final int INTERP_MAC = 3;
    static final int INTERP_AMIGA = 4;
    static final int INTERP_ATARIST = 5;
    static final int INTERP_MSDOS = 6;
    static final int INTERP_C128 = 7;
    static final int INTERP_C64 = 8;
    static final int INTERP_APPLEIIC = 9;
    static final int INTERP_APPLEIIGS = 10;
    static final int INTERP_COCO = 11;
    
    ZHeader5(final byte[] memory_image) {
        super.memory_image = memory_image;
    }
    
    void set_colors_available(final boolean avail) {
        if (avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x1;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)254;
        }
    }
    
    void set_bold_available(final boolean avail) {
        if (avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x4;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)251;
        }
    }
    
    void set_italic_available(final boolean avail) {
        if (avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x8;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)247;
        }
    }
    
    void set_fixed_font_available(final boolean avail) {
        if (avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= 0x10;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= (byte)239;
        }
    }
    
    void set_timed_input_available(final boolean avail) {
        if (avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 1;
            memory_image[n] |= (byte)128;
        }
        else {
            final byte[] memory_image2 = super.memory_image;
            final int n2 = 1;
            memory_image2[n2] &= 0x7F;
        }
    }
    
    boolean graphics_font_wanted() {
        return (super.memory_image[17] & 0x8) != 0x0;
    }
    
    void set_graphics_font_available(final boolean avail) {
        if (!avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 17;
            memory_image[n] &= (byte)247;
        }
    }
    
    boolean undo_wanted() {
        return (super.memory_image[17] & 0x10) != 0x0;
    }
    
    void set_undo_available(final boolean avail) {
        if (!avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 17;
            memory_image[n] &= (byte)239;
        }
    }
    
    boolean mouse_wanted() {
        return (super.memory_image[17] & 0x20) != 0x0;
    }
    
    void set_mouse_available(final boolean avail) {
        if (!avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 17;
            memory_image[n] &= (byte)223;
        }
    }
    
    boolean colors_wanted() {
        return (super.memory_image[17] & 0x40) != 0x0;
    }
    
    boolean sound_wanted() {
        return (super.memory_image[17] & 0x80) != 0x0;
    }
    
    void set_sound_available(final boolean avail) {
        if (!avail) {
            final byte[] memory_image = super.memory_image;
            final int n = 17;
            memory_image[n] &= 0x7F;
        }
    }
    
    void set_interpreter_number(final int number) {
        super.memory_image[30] = (byte)number;
    }
    
    void set_interpreter_version(final int version) {
        super.memory_image[31] = (byte)version;
    }
    
    void set_screen_height_lines(final int lines) {
        super.memory_image[32] = (byte)lines;
    }
    
    void set_screen_width_characters(final int characters) {
        super.memory_image[33] = (byte)characters;
    }
    
    void set_screen_height_units(final int units) {
        super.memory_image[34] = (byte)(units >> 8);
        super.memory_image[35] = (byte)(units & 0xFF);
    }
    
    void set_screen_width_units(final int units) {
        super.memory_image[36] = (byte)(units >> 8);
        super.memory_image[37] = (byte)(units & 0xFF);
    }
    
    void set_font_height_units(final int units) {
        super.memory_image[38] = (byte)units;
    }
    
    void set_font_width_units(final int units) {
        super.memory_image[39] = (byte)units;
    }
    
    int default_background_color() {
        return super.memory_image[44];
    }
    
    int default_foreground_color() {
        return super.memory_image[45];
    }
    
    void set_default_background_color(final int color) {
        super.memory_image[44] = (byte)color;
    }
    
    void set_default_foreground_color(final int color) {
        super.memory_image[45] = (byte)color;
    }
    
    public int file_length() {
        final int packed_length = (super.memory_image[26] & 0xFF) << 8 | (super.memory_image[27] & 0xFF);
        return packed_length * 4;
    }
}
