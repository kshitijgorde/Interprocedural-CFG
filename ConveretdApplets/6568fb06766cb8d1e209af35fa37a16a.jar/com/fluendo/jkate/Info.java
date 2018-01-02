// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.jcraft.jogg.Packet;
import java.io.UnsupportedEncodingException;
import com.fluendo.utils.Debug;
import com.jcraft.jogg.Buffer;

public class Info
{
    public int bitstream_version_major;
    public int bitstream_version_minor;
    public KateTextEncoding text_encoding;
    public KateTextDirectionality text_directionality;
    public int num_headers;
    public int granule_shift;
    public int gps_numerator;
    public int gps_denominator;
    public String language;
    public String category;
    public Region[] regions;
    public Style[] styles;
    public Curve[] curves;
    public Motion[] motions;
    public Palette[] palettes;
    public Bitmap[] bitmaps;
    public FontRange[] font_ranges;
    public FontMapping[] font_mappings;
    public KateMarkupType markup_type;
    public int original_canvas_width;
    public int original_canvas_height;
    private int probe;
    
    public Info() {
        this.bitstream_version_major = -1;
        this.bitstream_version_minor = -1;
        this.num_headers = 0;
        this.probe = 0;
    }
    
    private static int read_canvas_size(final Buffer buffer) {
        final int n = buffer.read(8) | buffer.read(8) << 8;
        return n >> 4 << (n & 0xF);
    }
    
    private int unpackInfo(final Buffer buffer) {
        this.bitstream_version_major = (byte)buffer.read(8);
        this.bitstream_version_minor = (byte)buffer.read(8);
        Debug.info("Kate bitstream v" + this.bitstream_version_major + "." + this.bitstream_version_minor);
        if (this.bitstream_version_major > 0) {
            return -9;
        }
        this.num_headers = buffer.read(8);
        if (this.num_headers < 1) {
            return -6;
        }
        final int read = buffer.read(8);
        if (read != 0) {
            return -6;
        }
        try {
            this.text_encoding = KateTextEncoding.CreateTextEncoding(read);
            this.text_directionality = KateTextDirectionality.CreateTextDirectionality(buffer.read(8));
        }
        catch (KateException ex) {
            return -6;
        }
        final int read2 = buffer.read(8);
        if (this.bitstream_version_major == 0 && this.bitstream_version_minor < 3 && read2 != 0) {
            return -6;
        }
        this.granule_shift = buffer.read(8);
        this.original_canvas_width = read_canvas_size(buffer);
        this.original_canvas_height = read_canvas_size(buffer);
        final int read3 = Bitwise.read32(buffer);
        if (this.bitstream_version_major == 0 && this.bitstream_version_minor < 3 && read3 != 0) {
            return -6;
        }
        this.gps_numerator = Bitwise.read32(buffer);
        this.gps_denominator = Bitwise.read32(buffer);
        if (this.granule_shift >= 64) {
            return -6;
        }
        if (this.gps_numerator == 0 || this.gps_denominator == 0) {
            return -6;
        }
        final byte[] array = new byte[16];
        this.language = "";
        Bitwise.readbuf(buffer, array, 16);
        if (array[15] != 0) {
            return -6;
        }
        int i;
        for (i = 0; i < 16; ++i) {
            if (array[i] == 0) {
                break;
            }
        }
        try {
            this.language = new String(array, 0, i, "US-ASCII");
        }
        catch (UnsupportedEncodingException ex2) {
            this.language = "";
        }
        this.category = "";
        Bitwise.readbuf(buffer, array, 16);
        if (array[15] != 0) {
            return -6;
        }
        int j;
        for (j = 0; j < 16; ++j) {
            if (array[j] == 0) {
                break;
            }
        }
        try {
            this.category = new String(array, 0, j, "US-ASCII");
        }
        catch (UnsupportedEncodingException ex3) {
            this.category = "";
        }
        if (buffer.read(1) != -1) {
            return -6;
        }
        return 0;
    }
    
    static int checkEOP(final Buffer buffer) {
        final int n = 0x7 & 8 - (buffer.bits() & 0x7);
        if (n > 0 && buffer.read(n) != 0) {
            return -6;
        }
        if (buffer.look1() != -1) {
            return -6;
        }
        return 0;
    }
    
    private int unpackComment(final Comment comment, final Buffer buffer) {
        final int read32 = Bitwise.read32(buffer);
        if (read32 < 0) {
            return -6;
        }
        final byte[] array = new byte[read32];
        Bitwise.readbuf(buffer, array, read32);
        comment.vendor = new String(array);
        final int read33 = Bitwise.read32(buffer);
        if (read33 < 0) {
            comment.clear();
            return -6;
        }
        comment.user_comments = new String[read33];
        for (int i = 0; i < read33; ++i) {
            final int read34 = Bitwise.read32(buffer);
            if (read34 < 0) {
                comment.clear();
                return -6;
            }
            final byte[] array2 = new byte[read34];
            Bitwise.readbuf(buffer, array2, read34);
            comment.user_comments[i] = new String(array2);
        }
        return 0;
    }
    
    public Region unpackRegion(final Buffer buffer) throws KateException {
        final Region region = new Region();
        region.metric = KateSpaceMetric.CreateSpaceMetric(buffer.read(8));
        region.x = Bitwise.read32v(buffer);
        region.y = Bitwise.read32v(buffer);
        region.w = Bitwise.read32v(buffer);
        region.h = Bitwise.read32v(buffer);
        region.style = Bitwise.read32v(buffer);
        if ((this.bitstream_version_major << 8 | this.bitstream_version_minor) >= 2) {
            Bitwise.read32v(buffer);
            region.clip = (buffer.read1() != 0);
        }
        else {
            region.clip = false;
        }
        Bitwise.skipWarp(buffer);
        return region;
    }
    
    private int unpackRegions(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.regions = new Region[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.regions[i] = this.unpackRegion(buffer);
            }
            catch (KateException ex) {
                this.regions = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    private Color unpackColor(final Buffer buffer) {
        final Color color = new Color();
        color.r = (byte)buffer.read(8);
        color.g = (byte)buffer.read(8);
        color.b = (byte)buffer.read(8);
        color.a = (byte)buffer.read(8);
        return color;
    }
    
    public Style unpackStyle(final Buffer buffer) throws KateException {
        final Style style = new Style();
        final double[][] floats = Bitwise.readFloats(buffer, 8, 1);
        int n = 0;
        style.halign = floats[0][n++];
        style.valign = floats[0][n++];
        style.font_width = floats[0][n++];
        style.font_height = floats[0][n++];
        style.left_margin = floats[0][n++];
        style.top_margin = floats[0][n++];
        style.right_margin = floats[0][n++];
        style.bottom_margin = floats[0][n++];
        style.text_color = this.unpackColor(buffer);
        style.background_color = this.unpackColor(buffer);
        style.draw_color = this.unpackColor(buffer);
        style.font_metric = KateSpaceMetric.CreateSpaceMetric(buffer.read(8));
        style.margin_metric = KateSpaceMetric.CreateSpaceMetric(buffer.read(8));
        style.bold = (buffer.read1() != 0);
        style.italics = (buffer.read1() != 0);
        style.underline = (buffer.read1() != 0);
        style.strike = (buffer.read1() != 0);
        if ((this.bitstream_version_major << 8 | this.bitstream_version_minor) >= 2) {
            Bitwise.read32v(buffer);
            style.justify = (buffer.read1() != 0);
            final int read32v = Bitwise.read32v(buffer);
            if (read32v < 0) {
                throw new KateBadPacketException();
            }
            final byte[] array = new byte[read32v];
            Bitwise.readbuf(buffer, array, read32v);
            style.font = new String(array);
        }
        else {
            style.justify = false;
            style.font = null;
        }
        if ((this.bitstream_version_major << 8 | this.bitstream_version_minor) >= 4) {
            Bitwise.read32v(buffer);
            style.wrap_mode = KateWrapMode.CreateWrapMode(Bitwise.read32v(buffer));
        }
        else {
            style.wrap_mode = KateWrapMode.kate_wrap_word;
        }
        Bitwise.skipWarp(buffer);
        return style;
    }
    
    private int unpackStyles(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.styles = new Style[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.styles[i] = this.unpackStyle(buffer);
            }
            catch (KateException ex) {
                this.styles = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    private Curve unpackCurve(final Buffer buffer) throws KateException {
        final Curve curve = new Curve();
        curve.type = KateCurveType.CreateCurveType(buffer.read(8));
        curve.npts = Bitwise.read32v(buffer);
        if (curve.npts < 0) {
            throw new KateBadPacketException();
        }
        Bitwise.skipWarp(buffer);
        curve.pts = Bitwise.readFloats(buffer, curve.npts, 2);
        return curve;
    }
    
    private int unpackCurves(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.curves = new Curve[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.curves[i] = this.unpackCurve(buffer);
            }
            catch (KateException ex) {
                this.curves = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    public Motion unpackMotion(final Buffer buffer) throws KateException {
        final Motion motion = new Motion();
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            throw new KateBadPacketException();
        }
        motion.curves = new Curve[read32v];
        for (int i = 0; i < read32v; ++i) {
            if (buffer.read1() != 0) {
                final int read32v2 = Bitwise.read32v(buffer);
                if (read32v2 < 0 || read32v2 >= this.curves.length) {
                    throw new KateBadPacketException();
                }
                motion.curves[i] = this.curves[read32v2];
            }
            else {
                motion.curves[i] = this.unpackCurve(buffer);
            }
        }
        final double[][] floats = Bitwise.readFloats(buffer, read32v, 1);
        motion.durations = new double[read32v];
        for (int j = 0; j < read32v; ++j) {
            motion.durations[j] = floats[0][j];
        }
        motion.x_mapping = KateMotionMapping.CreateMotionMapping(buffer.read(8));
        motion.y_mapping = KateMotionMapping.CreateMotionMapping(buffer.read(8));
        motion.semantics = KateMotionSemantics.CreateMotionSemantics(buffer.read(8));
        motion.periodic = (buffer.read1() != 0);
        Bitwise.skipWarp(buffer);
        return motion;
    }
    
    private int unpackMotions(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.motions = new Motion[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.motions[i] = this.unpackMotion(buffer);
            }
            catch (KateException ex) {
                this.motions = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    public Palette unpackPalette(final Buffer buffer) throws KateException {
        final Palette palette = new Palette();
        final int n = buffer.read(8) + 1;
        palette.colors = new Color[n];
        for (int i = 0; i < n; ++i) {
            palette.colors[i] = this.unpackColor(buffer);
        }
        Bitwise.skipWarp(buffer);
        return palette;
    }
    
    private int unpackPalettes(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.palettes = new Palette[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.palettes[i] = this.unpackPalette(buffer);
            }
            catch (KateException ex) {
                this.palettes = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    public Bitmap unpackBitmap(final Buffer buffer) throws KateException {
        final Bitmap bitmap = new Bitmap();
        bitmap.width = Bitwise.read32v(buffer);
        bitmap.height = Bitwise.read32v(buffer);
        bitmap.bpp = buffer.read(8);
        if (bitmap.width < 0 || bitmap.height < 0 || bitmap.bpp < 0 || bitmap.bpp > 8) {
            throw new KateBadPacketException();
        }
        if (bitmap.bpp == 0) {
            bitmap.type = KateBitmapType.CreateBitmapType(buffer.read(8));
            bitmap.palette = -1;
            if (bitmap.type == KateBitmapType.kate_bitmap_type_paletted) {
                switch (buffer.read(8)) {
                    case 1: {
                        bitmap.bpp = Bitwise.read32v(buffer);
                        bitmap.palette = Bitwise.read32v(buffer);
                        bitmap.pixels = RLE.decodeRLE(buffer, bitmap.width, bitmap.height, bitmap.bpp);
                        break;
                    }
                    default: {
                        Debug.warning("Unsupported bitmap type");
                        throw new KateBadPacketException();
                    }
                }
            }
            else {
                if (bitmap.type != KateBitmapType.kate_bitmap_type_png) {
                    Debug.warning("Unsupported bitmap type");
                    throw new KateBadPacketException();
                }
                bitmap.size = Bitwise.read32(buffer);
                Bitwise.readbuf(buffer, bitmap.pixels = new byte[bitmap.size], bitmap.size);
            }
        }
        else {
            bitmap.type = KateBitmapType.kate_bitmap_type_paletted;
            bitmap.palette = Bitwise.read32v(buffer);
            final int n = bitmap.width * bitmap.height;
            bitmap.pixels = new byte[n];
            for (int i = 0; i < n; ++i) {
                bitmap.pixels[i] = (byte)buffer.read(bitmap.bpp);
            }
        }
        if ((this.bitstream_version_major << 8 | this.bitstream_version_minor) >= 4) {
            Bitwise.read32v(buffer);
            bitmap.x_offset = Bitwise.read32v(buffer);
            bitmap.y_offset = Bitwise.read32v(buffer);
        }
        else {
            bitmap.x_offset = 0;
            bitmap.y_offset = 0;
        }
        Bitwise.skipWarp(buffer);
        return bitmap;
    }
    
    private int unpackBitmaps(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        this.bitmaps = new Bitmap[read32v];
        for (int i = 0; i < read32v; ++i) {
            try {
                this.bitmaps[i] = this.unpackBitmap(buffer);
            }
            catch (KateException ex) {
                this.bitmaps = null;
                return -6;
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    private FontRange unpackFontRange(final Buffer buffer) {
        final FontRange fontRange = new FontRange();
        fontRange.first_code_point = Bitwise.read32v(buffer);
        fontRange.last_code_point = Bitwise.read32v(buffer);
        fontRange.first_bitmap = Bitwise.read32v(buffer);
        Bitwise.skipWarp(buffer);
        return fontRange;
    }
    
    private int unpackFontMappings(final Buffer buffer) {
        final int read32v = Bitwise.read32v(buffer);
        if (read32v < 0) {
            return -6;
        }
        if (read32v > 0) {
            this.font_ranges = new FontRange[read32v];
            for (int i = 0; i < read32v; ++i) {
                this.font_ranges[i] = this.unpackFontRange(buffer);
            }
        }
        final int read32v2 = Bitwise.read32v(buffer);
        if (read32v2 < 0) {
            return -6;
        }
        if (read32v2 > 0) {
            this.font_mappings = new FontMapping[read32v2];
            for (int j = 0; j < read32v2; ++j) {
                final int read32v3 = Bitwise.read32v(buffer);
                if (read32v3 < 0) {
                    return -6;
                }
                if (read32v3 > 0) {
                    final FontRange[] ranges = new FontRange[read32v3];
                    for (int k = 0; k < read32v3; ++k) {
                        if (buffer.read1() != 0) {
                            final int read32v4 = Bitwise.read32v(buffer);
                            if (read32v4 < 0 || read32v4 >= this.font_ranges.length) {
                                return -6;
                            }
                            ranges[k] = this.font_ranges[read32v4];
                        }
                        else {
                            ranges[k] = this.unpackFontRange(buffer);
                        }
                    }
                    this.font_mappings[j].ranges = ranges;
                }
                else {
                    this.font_mappings[j] = null;
                }
            }
        }
        Bitwise.skipWarp(buffer);
        return checkEOP(buffer);
    }
    
    public void clear() {
        this.num_headers = 0;
        this.regions = null;
        this.styles = null;
        this.curves = null;
        this.motions = null;
        this.probe = 0;
    }
    
    public int decodeHeader(final Comment comment, final Packet packet) {
        final Buffer buffer = new Buffer();
        buffer.readinit(packet.packet_base, packet.packet, packet.bytes);
        final byte[] array = new byte[7];
        final int read = buffer.read(8);
        if ((read & 0x80) == 0x0) {
            return -6;
        }
        Bitwise.readbuf(buffer, array, 7);
        if (!"kate\u0000\u0000\u0000".equals(new String(array))) {
            return -10;
        }
        if (packet.packetno < this.num_headers && this.probe != packet.packetno) {
            return -6;
        }
        if (buffer.read(8) != 0) {
            return -6;
        }
        Debug.debug("decodeHeader: packet type " + read + ", probe " + this.probe);
        long n = 0L;
        switch (this.probe) {
            case 0: {
                if (packet.b_o_s == 0) {
                    return -6;
                }
                n = this.unpackInfo(buffer);
                break;
            }
            case 1: {
                n = this.unpackComment(comment, buffer);
                break;
            }
            case 2: {
                n = this.unpackRegions(buffer);
                break;
            }
            case 3: {
                n = this.unpackStyles(buffer);
                break;
            }
            case 4: {
                n = this.unpackCurves(buffer);
                break;
            }
            case 5: {
                n = this.unpackMotions(buffer);
                break;
            }
            case 6: {
                n = this.unpackPalettes(buffer);
                break;
            }
            case 7: {
                n = this.unpackBitmaps(buffer);
                break;
            }
            case 8: {
                n = this.unpackFontMappings(buffer);
                if (n == 0L) {
                    Debug.debug("Found last known header, returning 1");
                    n = 1L;
                    break;
                }
                break;
            }
            default: {
                n = 0L;
                break;
            }
        }
        if (n >= 0L) {
            ++this.probe;
        }
        return (int)n;
    }
}
