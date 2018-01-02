import java.awt.Image;
import java.io.InputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class btnplayer_pcffont
{
    int fgColor;
    int bgColor;
    int ascent;
    int descent;
    int maxAscent;
    int maxDescent;
    int charSpacing;
    int firstRow;
    int lastRow;
    static final int jisxOffset = 32;
    static final char[] jisx;
    btnplayer_charinfo[] charInfo;
    int nbitmaps;
    int max_char;
    int[] char_map;
    byte[] bits;
    int bitmap_format;
    int defaultBitmap;
    int pos;
    DataInputStream data;
    int num_tables;
    btnplayer_pcftablerec[] table;
    static final int default_format = 0;
    static final int inkbounds = 512;
    static final int accel_w_inkbounds = 256;
    static final int compressed_metrics = 256;
    static final int format_mask = -256;
    static final int PCF_PROPERTIES = 1;
    static final int PCF_ACCELERATORS = 2;
    static final int PCF_METRICS = 4;
    static final int PCF_BITMAPS = 8;
    static final int PCF_INK_METRICS = 16;
    static final int PCF_BDF_ENCODINGS = 32;
    static final int PCF_SWIDTHS = 64;
    static final int PCF_GLYPH_NAMES = 128;
    static final int PCF_BDF_ACCELERATORS = 256;
    static final int max_tables = 9;
    static final int glyph_pad_mask = 3;
    static final int byte_mask = 4;
    static final int MSBFirst = 1;
    static final int LSBFirst = 0;
    static final int bit_mask = 8;
    static final int scan_unit_mask = 48;
    static final int GLYPHPADOPTIONS = 4;
    static final int magic = 1885562369;
    btnplayer_pcfmetrics maxbounds;
    
    void blitGlyph(final int n, final int[] array, final int n2, final int n3) {
        if (n == this.defaultBitmap) {
            return;
        }
        msb_bit_order(this.bitmap_format);
        msb_byte_order(this.bitmap_format);
        final int glyph_pad = glyph_pad(this.bitmap_format);
        scan_unit(this.bitmap_format);
        final int n4 = msb_bit_order(this.bitmap_format) ? 7 : 0;
        final int n5 = (msb_bit_order(this.bitmap_format) == msb_byte_order(this.bitmap_format)) ? 0 : (scan_unit(this.bitmap_format) - 1);
        final int n6 = this.charInfo[n].metrics.ascent + this.charInfo[n].metrics.descent;
        final int n7 = -this.charInfo[n].metrics.leftSideBearing + this.charInfo[n].metrics.rightSideBearing;
        if (n6 < 0 || n7 < 0) {
            System.out.println("Negative height/width for " + n);
            return;
        }
        final int offset = this.charInfo[n].offset;
        final int n8 = (n7 + 7 >> 3) + glyph_pad - 1 & ~(glyph_pad - 1);
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n7; ++j) {
                if ((this.bits[offset + ((j >> 3) + i * n8 ^ n5)] & 1 << ((j & 0x7) ^ n4)) != 0x0) {
                    array[n2 + i * n3 + j] = this.fgColor;
                }
            }
        }
    }
    
    public int getAscent() {
        return this.ascent;
    }
    
    public int getDescent() {
        return this.descent;
    }
    
    int findTableOffset(final int n) {
        for (int i = 0; i < this.num_tables; ++i) {
            if (this.table[i].type == n) {
                return this.table[i].offset;
            }
        }
        return -1;
    }
    
    static int glyph_pad(final int n) {
        return 1 << glyph_pad_index(n);
    }
    
    public void drawString(final Graphics graphics, final String s, final int n, final int n2) {
        final btnplayer_metricsimage stringImageMI = this.stringImageMI(s);
        graphics.drawImage(stringImageMI.image, n - ((stringImageMI.metrics.leftSideBearing < 0) ? (-stringImageMI.metrics.leftSideBearing) : 0), n2 - stringImageMI.metrics.ascent, null);
    }
    
    int getint32(final int n) throws IOException {
        this.pos += 4;
        final int int1 = this.data.readInt();
        if (msb_byte_order(n)) {
            return int1;
        }
        return (int1 & 0xFF000000) >>> 24 | (int1 & 0xFF0000) >> 8 | (int1 & 0xFF00) << 8 | (int1 & 0xFF) << 24;
    }
    
    static int scan_unit(final int n) {
        return 1 << ((n & 0x30) >> 4);
    }
    
    int getBitmap(final int n) {
        if (n < 0 || n >= this.max_char) {
            return this.defaultBitmap;
        }
        return this.char_map[n];
    }
    
    public void setFgColor(final Color color) {
        if (color == null) {
            this.fgColor = 0;
            return;
        }
        this.fgColor = color.getRGB();
    }
    
    public void setBgColor(final Color color) {
        if (color == null) {
            this.bgColor = 0;
            return;
        }
        this.bgColor = color.getRGB();
    }
    
    public btnplayer_pcfmetrics stringMetrics(final String s) {
        final btnplayer_pcfmetrics btnplayer_pcfmetrics = new btnplayer_pcfmetrics();
        btnplayer_pcfmetrics.ascent = 0;
        btnplayer_pcfmetrics.descent = 0;
        btnplayer_pcfmetrics.leftSideBearing = 0;
        btnplayer_pcfmetrics.rightSideBearing = 0;
        btnplayer_pcfmetrics.characterWidth = 0;
        for (int i = 0; i < s.length(); ++i) {
            final int bitmap = this.getBitmap(s.charAt(i));
            if (this.charInfo[bitmap].metrics.ascent > btnplayer_pcfmetrics.ascent) {
                btnplayer_pcfmetrics.ascent = this.charInfo[bitmap].metrics.ascent;
            }
            if (this.charInfo[bitmap].metrics.descent > btnplayer_pcfmetrics.descent) {
                btnplayer_pcfmetrics.descent = this.charInfo[bitmap].metrics.descent;
            }
            if (btnplayer_pcfmetrics.characterWidth + this.charInfo[bitmap].metrics.leftSideBearing < btnplayer_pcfmetrics.leftSideBearing) {
                btnplayer_pcfmetrics.leftSideBearing = btnplayer_pcfmetrics.characterWidth + this.charInfo[bitmap].metrics.leftSideBearing;
            }
            if (btnplayer_pcfmetrics.characterWidth + this.charInfo[bitmap].metrics.rightSideBearing > btnplayer_pcfmetrics.rightSideBearing) {
                btnplayer_pcfmetrics.rightSideBearing = btnplayer_pcfmetrics.characterWidth + this.charInfo[bitmap].metrics.rightSideBearing;
            }
            final btnplayer_pcfmetrics btnplayer_pcfmetrics2 = btnplayer_pcfmetrics;
            btnplayer_pcfmetrics2.characterWidth += this.charInfo[bitmap].metrics.characterWidth;
            final btnplayer_pcfmetrics btnplayer_pcfmetrics3 = btnplayer_pcfmetrics;
            btnplayer_pcfmetrics3.characterWidth += this.charSpacing;
        }
        final btnplayer_pcfmetrics btnplayer_pcfmetrics4 = btnplayer_pcfmetrics;
        btnplayer_pcfmetrics4.characterWidth -= this.charSpacing;
        return btnplayer_pcfmetrics;
    }
    
    btnplayer_metricsimage stringImageMI(final String s) {
        new String(s);
        final btnplayer_pcfmetrics btnplayer_pcfmetrics = new btnplayer_pcfmetrics();
        final btnplayer_pcfmetrics stringMetrics = this.stringMetrics(s);
        final int n = (stringMetrics.leftSideBearing < 0) ? (-stringMetrics.leftSideBearing) : 0;
        int n2 = n + stringMetrics.rightSideBearing;
        int n3 = stringMetrics.ascent + stringMetrics.descent;
        if (n2 == 0) {
            n2 = 1;
        }
        if (n3 == 0) {
            n3 = 1;
        }
        final int[] array = new int[n2 * n3];
        if (this.bgColor != 0) {
            for (int i = 0; i < n2 * n3; ++i) {
                array[i] = this.bgColor;
            }
        }
        this.drawStringBuffer(s, array, n, stringMetrics.ascent, n2);
        return new btnplayer_metricsimage(stringMetrics, Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n2, n3, array, 0, n2)));
    }
    
    public int getFirstPage() {
        return this.firstRow;
    }
    
    void skipTo(final int pos) throws IOException {
        if (pos - this.pos < 0) {
            throw new IOException("Backwards skipTo");
        }
        this.data.skipBytes(pos - this.pos);
        this.pos = pos;
    }
    
    byte[] readbytes(int i, final byte[] array) throws IOException {
        int n = 0;
        while (i > 0) {
            final int read = this.data.read(array, n, i);
            if (read <= 0) {
                throw new IOException("Read hit EOF");
            }
            this.pos += read;
            n += read;
            i -= read;
        }
        return array;
    }
    
    btnplayer_pcfmetrics getMetric(final int n) throws IOException {
        final btnplayer_pcfmetrics btnplayer_pcfmetrics = new btnplayer_pcfmetrics();
        btnplayer_pcfmetrics.leftSideBearing = this.getint16(n);
        btnplayer_pcfmetrics.rightSideBearing = this.getint16(n);
        btnplayer_pcfmetrics.characterWidth = this.getint16(n);
        btnplayer_pcfmetrics.ascent = this.getint16(n);
        btnplayer_pcfmetrics.descent = this.getint16(n);
        this.getint16(n);
        return btnplayer_pcfmetrics;
    }
    
    public void setCharSpacing(final int charSpacing) {
        this.charSpacing = charSpacing;
    }
    
    public int charWidth(final char c) {
        return this.stringWidth(String.valueOf(c));
    }
    
    public static String toUnicode(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            char char1 = s.charAt(i);
            if (char1 == '\\' && s.length() - i >= 6 && s.charAt(i + 1) == 'u') {
                char1 = (char)Integer.parseInt(s.substring(i + 2, i + 6), 16);
                i += 5;
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    boolean getAccelerators(final int n) throws IOException {
        if (!this.seek(n)) {
            return false;
        }
        final int getlsb32 = this.getlsb32();
        if (!format_match(getlsb32, 0) && !format_match(getlsb32, 256)) {
            throw new IOException("Bad format for accelerator");
        }
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.getint8(getlsb32);
        this.ascent = this.getint32(getlsb32);
        this.descent = this.getint32(getlsb32);
        this.getint32(getlsb32);
        this.getMetric(getlsb32);
        this.maxbounds = this.getMetric(getlsb32);
        final btnplayer_pcfmetrics maxbounds = this.maxbounds;
        if (format_match(getlsb32, 256)) {
            this.getMetric(getlsb32);
            this.getMetric(getlsb32);
        }
        this.maxAscent = this.maxbounds.ascent;
        this.maxDescent = this.maxbounds.descent;
        return true;
    }
    
    int getint16(final int n) throws IOException {
        this.pos += 2;
        final int n2 = this.data.readByte() & 0xFF;
        final int n3 = this.data.readByte() & 0xFF;
        if (msb_byte_order(n)) {
            return n2 << 8 | n3;
        }
        return n3 << 8 | n2;
    }
    
    static int glyph_pad_index(final int n) {
        return n & 0x3;
    }
    
    void getBitmaps() throws IOException {
        if (!this.seek(8)) {
            throw new IOException("No metrics");
        }
        final int getlsb32 = this.getlsb32();
        if (!format_match(getlsb32, 0)) {
            throw new IOException("bitmaps: bad bitmap format");
        }
        this.bitmap_format = getlsb32;
        if (this.nbitmaps != this.getint32(getlsb32)) {
            throw new IOException("bitmaps and metrics mismatch");
        }
        for (int i = 0; i < this.nbitmaps; ++i) {
            this.charInfo[i].offset = this.getint32(getlsb32);
        }
        final int[] array = new int[4];
        int n = 0;
        do {
            array[n] = this.getint32(getlsb32);
        } while (++n < 4);
        final int n2 = array[glyph_pad_index(getlsb32)];
        this.bits = new byte[n2 + scan_unit(this.bitmap_format)];
        this.bits = this.readbytes(n2, this.bits);
    }
    
    public static String toJISX(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        boolean b = false;
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 == 27 && i < s.length() - 2) {
                if (s.charAt(i + 1) == '(' && (s.charAt(i + 2) == 'B' || s.charAt(i + 2) == 'J')) {
                    b = false;
                    i += 2;
                    continue;
                }
                if (s.charAt(i + 1) == '$' && (s.charAt(i + 2) == '@' || s.charAt(i + 2) == 'B')) {
                    b = true;
                    i += 2;
                    continue;
                }
            }
            if (b && i < s.length() - 1) {
                char1 = (char)(char1 << 8 | s.charAt(i + 1));
                ++i;
            }
            else if (char1 > 128 && i < s.length() - 1) {
                char1 -= 128;
                char1 = (char)(char1 << 8 | s.charAt(i + 1) - '\u0080');
                ++i;
            }
            final int n = char1 - 32;
            if (n >= 0 && n < btnplayer_pcffont.jisx.length && btnplayer_pcffont.jisx[n] > '\0') {
                char1 = btnplayer_pcffont.jisx[n];
            }
            sb.append((char)char1);
        }
        return sb.toString();
    }
    
    void getTable() throws IOException {
        final int getlsb32 = this.getlsb32();
        if (getlsb32 != 1885562369) {
            throw new IOException("Bad version " + getlsb32);
        }
        final int getlsb33 = this.getlsb32();
        if (getlsb33 > 9) {
            throw new IOException("table count " + getlsb33 + " too big");
        }
        this.table = new btnplayer_pcftablerec[getlsb33];
        this.num_tables = 0;
        while (this.num_tables < getlsb33) {
            this.table[this.num_tables] = new btnplayer_pcftablerec();
            this.table[this.num_tables].type = this.getlsb32();
            this.table[this.num_tables].format = this.getlsb32();
            this.table[this.num_tables].size = this.getlsb32();
            this.table[this.num_tables].offset = this.getlsb32();
            ++this.num_tables;
        }
    }
    
    int getlsb32() throws IOException {
        this.pos += 4;
        final int int1 = this.data.readInt();
        return (int1 & 0xFF000000) >>> 24 | (int1 & 0xFF0000) >> 8 | (int1 & 0xFF00) << 8 | (int1 & 0xFF) << 24;
    }
    
    btnplayer_pcfmetrics getCompressedMetric(final int n) throws IOException {
        final btnplayer_pcfmetrics btnplayer_pcfmetrics = new btnplayer_pcfmetrics();
        btnplayer_pcfmetrics.leftSideBearing = this.getint8(n) - 128;
        btnplayer_pcfmetrics.rightSideBearing = this.getint8(n) - 128;
        btnplayer_pcfmetrics.characterWidth = this.getint8(n) - 128;
        btnplayer_pcfmetrics.ascent = this.getint8(n) - 128;
        btnplayer_pcfmetrics.descent = this.getint8(n) - 128;
        return btnplayer_pcfmetrics;
    }
    
    public int getMaxAscent() {
        return this.maxAscent;
    }
    
    public int getMaxDescent() {
        return this.maxDescent;
    }
    
    public boolean defined(final char c) {
        return this.getBitmap(c) != this.defaultBitmap;
    }
    
    public btnplayer_pcffont(final InputStream inputStream) throws IOException {
        this.pos = 0;
        this.num_tables = 0;
        this.table = null;
        this.setFgColor(Color.black);
        this.setBgColor(null);
        this.data = new DataInputStream(inputStream);
        this.getTable();
        boolean b = this.getAccelerators(2);
        this.getMetrics();
        this.getBitmaps();
        this.getEncodings();
        if (!b) {
            b = this.getAccelerators(256);
        }
        if (!b) {
            throw new IOException("No accelerators");
        }
        inputStream.close();
        this.table = null;
    }
    
    public btnplayer_pcfmetrics fontMetrics() {
        return this.maxbounds;
    }
    
    public Image stringImage(final String s) {
        return this.stringImageMI(s).image;
    }
    
    public int getLastPage() {
        return this.lastRow;
    }
    
    public boolean is2Byte() {
        return this.firstRow > 0;
    }
    
    boolean seek(final int n) throws IOException {
        final int tableOffset = this.findTableOffset(n);
        if (tableOffset == -1) {
            return false;
        }
        this.skipTo(tableOffset);
        return true;
    }
    
    static {
        jisx = new char[] { '\u2121', '\u212a', '\u216d', '\u2174', '\u2170', '\u2173', '\u2175', '\u216c', '\u214a', '\u214b', '\u2176', '\u215c', '\u2124', '\u215d', '\u2125', '\u213f', '\u2330', '\u2331', '\u2332', '\u2333', '\u2334', '\u2335', '\u2336', '\u2337', '\u2338', '\u2339', '\u2127', '\u2128', '\u2163', '\u2161', '\u2164', '\u2129', '\u2177', '\u2341', '\u2342', '\u2343', '\u2344', '\u2345', '\u2346', '\u2347', '\u2348', '\u2349', '\u234a', '\u234b', '\u234c', '\u234d', '\u234e', '\u234f', '\u2350', '\u2351', '\u2352', '\u2353', '\u2354', '\u2355', '\u2356', '\u2357', '\u2358', '\u2359', '\u235a', '\u214e', '\u2140', '\u214f', '\u2130', '\u2132', '\u2129', '\u2361', '\u2362', '\u2363', '\u2364', '\u2365', '\u2366', '\u2367', '\u2368', '\u2369', '\u236a', '\u236b', '\u236c', '\u236d', '\u236e', '\u236f', '\u2370', '\u2371', '\u2372', '\u2373', '\u2374', '\u2375', '\u2376', '\u2377', '\u2378', '\u2379', '\u237a', '\u2150', '\u2143', '\u2151', '\u2141', '\u2121' };
    }
    
    public int stringWidth(final String s) {
        final btnplayer_pcfmetrics stringMetrics = this.stringMetrics(s);
        return ((stringMetrics.leftSideBearing < 0) ? (-stringMetrics.leftSideBearing) : 0) + stringMetrics.rightSideBearing;
    }
    
    int getint8(final int n) throws IOException {
        ++this.pos;
        return this.data.readByte() & 0xFF;
    }
    
    void getEncodings() throws IOException {
        if (!this.seek(32)) {
            throw new IOException("No metrics");
        }
        final int getlsb32 = this.getlsb32();
        if (!format_match(getlsb32, 0)) {
            throw new IOException("encodings: bad bitmap format");
        }
        final int getint16 = this.getint16(getlsb32);
        final int getint17 = this.getint16(getlsb32);
        this.firstRow = this.getint16(getlsb32);
        this.lastRow = this.getint16(getlsb32);
        final int getint18 = this.getint16(getlsb32);
        if (this.lastRow > 0) {
            this.max_char = 65536;
        }
        else {
            this.max_char = 256;
        }
        this.char_map = new int[this.max_char];
        for (int i = 0; i < this.max_char; ++i) {
            this.char_map[i] = 65535;
        }
        for (int j = this.firstRow; j <= this.lastRow; ++j) {
            for (int k = getint16; k <= getint17; ++k) {
                final int getint19 = this.getint16(getlsb32);
                if (getint19 != 65535) {
                    this.char_map[j << 8 | k] = getint19;
                }
            }
        }
        if (getint18 >= this.char_map.length || this.char_map[getint18] == 65535) {
            this.defaultBitmap = 0;
        }
        else {
            this.defaultBitmap = this.char_map[getint18];
        }
        for (int l = 0; l < this.max_char; ++l) {
            if (this.char_map[l] == 65535) {
                this.char_map[l] = this.defaultBitmap;
            }
        }
    }
    
    public void drawStringBuffer(final String s, final int[] array, int n, final int n2, final int n3) {
        for (int i = 0; i < s.length(); ++i) {
            final int bitmap = this.getBitmap(s.charAt(i));
            this.blitGlyph(bitmap, array, n + this.charInfo[bitmap].metrics.leftSideBearing + (n2 - this.charInfo[bitmap].metrics.ascent) * n3, n3);
            n += this.charInfo[bitmap].metrics.characterWidth + this.charSpacing;
        }
    }
    
    static boolean msb_bit_order(final int n) {
        return (n & 0x8) != 0x0;
    }
    
    public int getHeight() {
        return this.ascent + this.descent + 1;
    }
    
    static boolean msb_byte_order(final int n) {
        return (n & 0x4) != 0x0;
    }
    
    static boolean format_match(final int n, final int n2) {
        return (n & 0xFFFFFF00) == (n2 & 0xFFFFFF00);
    }
    
    void getMetrics() throws IOException {
        if (!this.seek(4)) {
            throw new IOException("No metrics");
        }
        final int getlsb32 = this.getlsb32();
        if (format_match(getlsb32, 0)) {
            this.nbitmaps = this.getint32(getlsb32);
        }
        else {
            if (!format_match(getlsb32, 256)) {
                throw new IOException("bad metrics format");
            }
            this.nbitmaps = this.getint16(getlsb32);
        }
        this.charInfo = new btnplayer_charinfo[this.nbitmaps];
        for (int i = 0; i < this.nbitmaps; ++i) {
            this.charInfo[i] = new btnplayer_charinfo();
            if (format_match(getlsb32, 0)) {
                this.charInfo[i].metrics = this.getMetric(getlsb32);
            }
            else {
                this.charInfo[i].metrics = this.getCompressedMetric(getlsb32);
            }
        }
    }
}
