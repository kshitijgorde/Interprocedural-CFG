// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.jcraft.jogg.Buffer;

public final class Decode
{
    Buffer opb;
    Info info;
    
    public Decode(final Info info) {
        this.opb = new Buffer();
        this.info = info;
    }
    
    int decodeTextPacket(final Event event) {
        event.ki = this.info;
        event.start = Bitwise.read64(this.opb);
        event.duration = Bitwise.read64(this.opb);
        event.backlink = Bitwise.read64(this.opb);
        event.start_time = this.granuleDuration(event.start);
        event.end_time = event.start_time + this.granuleDuration(event.duration);
        final int read32 = Bitwise.read32(this.opb);
        event.text = new byte[read32];
        Bitwise.readbuf(this.opb, event.text, read32);
        event.id = -1;
        if (this.opb.read1() != 0) {
            event.id = Bitwise.read32v(this.opb);
        }
        event.motions = null;
        if (this.opb.read1() != 0) {
            final int read32v = Bitwise.read32v(this.opb);
            if (read32v < 0) {
                return -1;
            }
            event.motions = new Motion[read32v];
            for (int i = 0; i < read32v; ++i) {
                if (this.opb.read1() != 0) {
                    final int read32v2 = Bitwise.read32v(this.opb);
                    if (read32v2 < 0 || read32v2 >= this.info.motions.length) {
                        return -1;
                    }
                    event.motions[i] = this.info.motions[read32v2];
                }
                else {
                    try {
                        event.motions[i] = this.info.unpackMotion(this.opb);
                    }
                    catch (KateException ex) {
                        return -6;
                    }
                }
            }
        }
        if (this.opb.read1() != 0) {
            try {
                if (this.opb.read1() != 0) {
                    event.text_encoding = KateTextEncoding.CreateTextEncoding(this.opb.read(8));
                }
                if (this.opb.read1() != 0) {
                    event.text_directionality = KateTextDirectionality.CreateTextDirectionality(this.opb.read(8));
                }
            }
            catch (KateException ex2) {
                return -6;
            }
            if (this.opb.read1() != 0) {
                final int read32v3 = Bitwise.read32v(this.opb);
                if (read32v3 < 0) {
                    return -6;
                }
                if (read32v3 > 0) {
                    event.language = new byte[read32v3];
                    Bitwise.readbuf(this.opb, event.language, read32v3);
                }
            }
            if (this.opb.read1() != 0) {
                final int read32v4 = Bitwise.read32v(this.opb);
                if (read32v4 < 0 || read32v4 >= this.info.regions.length) {
                    return -6;
                }
                event.kr = this.info.regions[read32v4];
            }
            if (this.opb.read1() != 0) {
                try {
                    event.kr = this.info.unpackRegion(this.opb);
                }
                catch (KateException ex3) {
                    return -6;
                }
            }
            if (this.opb.read1() != 0) {
                final int read32v5 = Bitwise.read32v(this.opb);
                if (read32v5 < 0 || read32v5 >= this.info.styles.length) {
                    return -6;
                }
                event.ks = this.info.styles[read32v5];
            }
            if (this.opb.read1() != 0) {
                try {
                    event.ks = this.info.unpackStyle(this.opb);
                }
                catch (KateException ex4) {
                    return -6;
                }
            }
            if (this.opb.read1() != 0) {
                final int read32v6 = Bitwise.read32v(this.opb);
                if (read32v6 < 0 || read32v6 >= this.info.styles.length) {
                    return -6;
                }
                event.ks2 = this.info.styles[read32v6];
            }
            if (this.opb.read1() != 0) {
                try {
                    event.ks2 = this.info.unpackStyle(this.opb);
                }
                catch (KateException ex5) {
                    return -6;
                }
            }
            if (this.opb.read1() != 0) {
                final int read32v7 = Bitwise.read32v(this.opb);
                if (read32v7 < 0 || read32v7 >= this.info.font_mappings.length) {
                    return -6;
                }
                event.font_mapping = this.info.font_mappings[read32v7];
            }
        }
        if ((this.info.bitstream_version_major << 8 | this.info.bitstream_version_minor) >= 2) {
            Bitwise.read32v(this.opb);
            if (this.opb.read1() != 0) {
                if (this.opb.read1() != 0) {
                    final int read32v8 = Bitwise.read32v(this.opb);
                    if (read32v8 < 0 || read32v8 >= this.info.palettes.length) {
                        return -6;
                    }
                    event.palette = this.info.palettes[read32v8];
                }
                if (this.opb.read1() != 0) {
                    try {
                        event.palette = this.info.unpackPalette(this.opb);
                    }
                    catch (KateException ex6) {
                        return -6;
                    }
                }
                if (this.opb.read1() != 0) {
                    final int read32v9 = Bitwise.read32v(this.opb);
                    if (read32v9 < 0 || read32v9 >= this.info.bitmaps.length) {
                        return -6;
                    }
                    event.bitmap = this.info.bitmaps[read32v9];
                }
                if (this.opb.read1() != 0) {
                    try {
                        event.bitmap = this.info.unpackBitmap(this.opb);
                    }
                    catch (KateException ex7) {
                        return -6;
                    }
                }
                if (this.opb.read1() != 0) {
                    try {
                        event.markup_type = KateMarkupType.CreateMarkupType(this.opb.read(8));
                    }
                    catch (KateException ex8) {
                        return -6;
                    }
                }
            }
        }
        return 0;
    }
    
    public double granuleTime(final long n) {
        if (n >= 0L) {
            final long n2 = n >> this.info.granule_shift;
            return (n2 + (n - (n2 << this.info.granule_shift))) * (this.info.gps_denominator / this.info.gps_numerator);
        }
        return -1.0;
    }
    
    public double granuleDuration(final long n) {
        if (n >= 0L) {
            return n * (this.info.gps_denominator / this.info.gps_numerator);
        }
        return -1.0;
    }
}
