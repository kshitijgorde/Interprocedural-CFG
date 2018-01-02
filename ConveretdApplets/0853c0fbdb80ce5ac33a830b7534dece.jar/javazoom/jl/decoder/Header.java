// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public final class Header
{
    public static final int[][] frequencies;
    public static final int MPEG2_LSF = 0;
    public static final int MPEG25_LSF = 2;
    public static final int MPEG1 = 1;
    public static final int STEREO = 0;
    public static final int JOINT_STEREO = 1;
    public static final int DUAL_CHANNEL = 2;
    public static final int SINGLE_CHANNEL = 3;
    public static final int FOURTYFOUR_POINT_ONE = 0;
    public static final int FOURTYEIGHT = 1;
    public static final int THIRTYTWO = 2;
    private int h_layer;
    private int h_protection_bit;
    private int h_bitrate_index;
    private int h_padding_bit;
    private int h_mode_extension;
    private int h_version;
    private int h_mode;
    private int h_sample_frequency;
    private int h_number_of_subbands;
    private int h_intensity_stereo_bound;
    private boolean h_copyright;
    private boolean h_original;
    private double[] h_vbr_time_per_frame;
    private boolean h_vbr;
    private int h_vbr_frames;
    private int h_vbr_scale;
    private int h_vbr_bytes;
    private byte[] h_vbr_toc;
    private byte syncmode;
    private Crc16 crc;
    public short checksum;
    public int framesize;
    public int nSlots;
    private int _headerstring;
    public static final int[][][] bitrates;
    public static final String[][][] bitrate_str;
    
    Header() {
        this.h_vbr_time_per_frame = new double[] { -1.0, 384.0, 1152.0, 1152.0 };
        this.syncmode = Bitstream.INITIAL_SYNC;
        this._headerstring = -1;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(200);
        sb.append("Layer ");
        sb.append(this.layer_string());
        sb.append(" frame ");
        sb.append(this.mode_string());
        sb.append(' ');
        sb.append(this.version_string());
        if (!this.checksums()) {
            sb.append(" no");
        }
        sb.append(" checksums");
        sb.append(' ');
        sb.append(this.sample_frequency_string());
        sb.append(',');
        sb.append(' ');
        sb.append(this.bitrate_string());
        return sb.toString();
    }
    
    void read_header(final Bitstream bitstream, final Crc16[] array) throws BitstreamException {
        boolean b = false;
        int syncHeader;
        do {
            syncHeader = bitstream.syncHeader(this.syncmode);
            this._headerstring = syncHeader;
            if (this.syncmode == Bitstream.INITIAL_SYNC) {
                this.h_version = (syncHeader >>> 19 & 0x1);
                if ((syncHeader >>> 20 & 0x1) == 0x0) {
                    if (this.h_version != 0) {
                        throw bitstream.newBitstreamException(256);
                    }
                    this.h_version = 2;
                }
                if ((this.h_sample_frequency = (syncHeader >>> 10 & 0x3)) == 3) {
                    throw bitstream.newBitstreamException(256);
                }
            }
            this.h_layer = (4 - (syncHeader >>> 17) & 0x3);
            this.h_protection_bit = (syncHeader >>> 16 & 0x1);
            this.h_bitrate_index = (syncHeader >>> 12 & 0xF);
            this.h_padding_bit = (syncHeader >>> 9 & 0x1);
            this.h_mode = (syncHeader >>> 6 & 0x3);
            this.h_mode_extension = (syncHeader >>> 4 & 0x3);
            if (this.h_mode == 1) {
                this.h_intensity_stereo_bound = (this.h_mode_extension << 2) + 4;
            }
            else {
                this.h_intensity_stereo_bound = 0;
            }
            if ((syncHeader >>> 3 & 0x1) == 0x1) {
                this.h_copyright = true;
            }
            if ((syncHeader >>> 2 & 0x1) == 0x1) {
                this.h_original = true;
            }
            if (this.h_layer == 1) {
                this.h_number_of_subbands = 32;
            }
            else {
                int h_bitrate_index = this.h_bitrate_index;
                if (this.h_mode != 3) {
                    if (h_bitrate_index == 4) {
                        h_bitrate_index = 1;
                    }
                    else {
                        h_bitrate_index -= 4;
                    }
                }
                if (h_bitrate_index == 1 || h_bitrate_index == 2) {
                    if (this.h_sample_frequency == 2) {
                        this.h_number_of_subbands = 12;
                    }
                    else {
                        this.h_number_of_subbands = 8;
                    }
                }
                else if (this.h_sample_frequency == 1 || (h_bitrate_index >= 3 && h_bitrate_index <= 5)) {
                    this.h_number_of_subbands = 27;
                }
                else {
                    this.h_number_of_subbands = 30;
                }
            }
            if (this.h_intensity_stereo_bound > this.h_number_of_subbands) {
                this.h_intensity_stereo_bound = this.h_number_of_subbands;
            }
            this.calculate_framesize();
            final int read_frame_data = bitstream.read_frame_data(this.framesize);
            if (this.framesize >= 0 && read_frame_data != this.framesize) {
                throw bitstream.newBitstreamException(261);
            }
            if (bitstream.isSyncCurrentPosition(this.syncmode)) {
                if (this.syncmode == Bitstream.INITIAL_SYNC) {
                    this.syncmode = Bitstream.STRICT_SYNC;
                    bitstream.set_syncword(syncHeader & 0xFFF80CC0);
                }
                b = true;
            }
            else {
                bitstream.unreadFrame();
            }
        } while (!b);
        bitstream.parse_frame();
        if (this.h_protection_bit == 0) {
            this.checksum = (short)bitstream.get_bits(16);
            if (this.crc == null) {
                this.crc = new Crc16();
            }
            this.crc.add_bits(syncHeader, 16);
            array[0] = this.crc;
        }
        else {
            array[0] = null;
        }
        if (this.h_sample_frequency == 0) {}
    }
    
    void parseVBR(final byte[] array) throws BitstreamException {
        final String s = "Xing";
        final byte[] array2 = new byte[4];
        int n;
        if (this.h_version == 1) {
            if (this.h_mode == 3) {
                n = 17;
            }
            else {
                n = 32;
            }
        }
        else if (this.h_mode == 3) {
            n = 9;
        }
        else {
            n = 17;
        }
        try {
            System.arraycopy(array, n, array2, 0, 4);
            if (s.equals(new String(array2))) {
                this.h_vbr = true;
                this.h_vbr_frames = -1;
                this.h_vbr_bytes = -1;
                this.h_vbr_scale = -1;
                this.h_vbr_toc = new byte[100];
                final int n2 = 4;
                final byte[] array3 = new byte[4];
                System.arraycopy(array, n + n2, array3, 0, array3.length);
                int n3 = n2 + array3.length;
                if ((array3[3] & 0x1) != 0x0) {
                    System.arraycopy(array, n + n3, array2, 0, array2.length);
                    this.h_vbr_frames = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                    n3 += 4;
                }
                if ((array3[3] & 0x2) != 0x0) {
                    System.arraycopy(array, n + n3, array2, 0, array2.length);
                    this.h_vbr_bytes = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                    n3 += 4;
                }
                if ((array3[3] & 0x4) != 0x0) {
                    System.arraycopy(array, n + n3, this.h_vbr_toc, 0, this.h_vbr_toc.length);
                    n3 += this.h_vbr_toc.length;
                }
                if ((array3[3] & 0x8) != 0x0) {
                    System.arraycopy(array, n + n3, array2, 0, array2.length);
                    this.h_vbr_scale = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                    n3 += 4;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new BitstreamException("XingVBRHeader Corrupted", ex);
        }
        final String s2 = "VBRI";
        final int n4 = 32;
        try {
            System.arraycopy(array, n4, array2, 0, 4);
            if (s2.equals(new String(array2))) {
                this.h_vbr = true;
                this.h_vbr_frames = -1;
                this.h_vbr_bytes = -1;
                this.h_vbr_scale = -1;
                this.h_vbr_toc = new byte[100];
                int n5 = 10;
                System.arraycopy(array, n4 + n5, array2, 0, array2.length);
                this.h_vbr_bytes = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                n5 += 4;
                System.arraycopy(array, n4 + n5, array2, 0, array2.length);
                this.h_vbr_frames = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                n5 += 4;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            throw new BitstreamException("VBRIVBRHeader Corrupted", ex2);
        }
    }
    
    public int version() {
        return this.h_version;
    }
    
    public int layer() {
        return this.h_layer;
    }
    
    public int bitrate_index() {
        return this.h_bitrate_index;
    }
    
    public int sample_frequency() {
        return this.h_sample_frequency;
    }
    
    public int frequency() {
        return Header.frequencies[this.h_version][this.h_sample_frequency];
    }
    
    public int mode() {
        return this.h_mode;
    }
    
    public boolean checksums() {
        return this.h_protection_bit == 0;
    }
    
    public boolean copyright() {
        return this.h_copyright;
    }
    
    public boolean original() {
        return this.h_original;
    }
    
    public boolean vbr() {
        return this.h_vbr;
    }
    
    public int vbr_scale() {
        return this.h_vbr_scale;
    }
    
    public byte[] vbr_toc() {
        return this.h_vbr_toc;
    }
    
    public boolean checksum_ok() {
        return this.checksum == this.crc.checksum();
    }
    
    public boolean padding() {
        return this.h_padding_bit != 0;
    }
    
    public int slots() {
        return this.nSlots;
    }
    
    public int mode_extension() {
        return this.h_mode_extension;
    }
    
    public int calculate_framesize() {
        if (this.h_layer == 1) {
            this.framesize = 12 * Header.bitrates[this.h_version][0][this.h_bitrate_index] / Header.frequencies[this.h_version][this.h_sample_frequency];
            if (this.h_padding_bit != 0) {
                ++this.framesize;
            }
            this.framesize <<= 2;
            this.nSlots = 0;
        }
        else {
            this.framesize = 144 * Header.bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index] / Header.frequencies[this.h_version][this.h_sample_frequency];
            if (this.h_version == 0 || this.h_version == 2) {
                this.framesize >>= 1;
            }
            if (this.h_padding_bit != 0) {
                ++this.framesize;
            }
            if (this.h_layer == 3) {
                if (this.h_version == 1) {
                    this.nSlots = this.framesize - ((this.h_mode == 3) ? 17 : 32) - ((this.h_protection_bit != 0) ? 0 : 2) - 4;
                }
                else {
                    this.nSlots = this.framesize - ((this.h_mode == 3) ? 9 : 17) - ((this.h_protection_bit != 0) ? 0 : 2) - 4;
                }
            }
            else {
                this.nSlots = 0;
            }
        }
        return this.framesize -= 4;
    }
    
    public int max_number_of_frames(final int n) {
        if (this.h_vbr) {
            return this.h_vbr_frames;
        }
        if (this.framesize + 4 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 4 - this.h_padding_bit);
    }
    
    public int min_number_of_frames(final int n) {
        if (this.h_vbr) {
            return this.h_vbr_frames;
        }
        if (this.framesize + 5 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 5 - this.h_padding_bit);
    }
    
    public float ms_per_frame() {
        if (this.h_vbr) {
            double n = this.h_vbr_time_per_frame[this.layer()] / this.frequency();
            if (this.h_version == 0 || this.h_version == 2) {
                n /= 2.0;
            }
            return (float)(n * 1000.0);
        }
        return (new float[][] { { 8.707483f, 8.0f, 12.0f }, { 26.12245f, 24.0f, 36.0f }, { 26.12245f, 24.0f, 36.0f } })[this.h_layer - 1][this.h_sample_frequency];
    }
    
    public float total_ms(final int n) {
        return this.max_number_of_frames(n) * this.ms_per_frame();
    }
    
    public int getSyncHeader() {
        return this._headerstring;
    }
    
    public String layer_string() {
        switch (this.h_layer) {
            case 1: {
                return "I";
            }
            case 2: {
                return "II";
            }
            case 3: {
                return "III";
            }
            default: {
                return null;
            }
        }
    }
    
    public String bitrate_string() {
        if (this.h_vbr) {
            return Integer.toString(this.bitrate() / 1000) + " kb/s";
        }
        return Header.bitrate_str[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }
    
    public int bitrate() {
        if (this.h_vbr) {
            return (int)(this.h_vbr_bytes * 8 / (this.ms_per_frame() * this.h_vbr_frames)) * 1000;
        }
        return Header.bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }
    
    public int bitrate_instant() {
        return Header.bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
    }
    
    public String sample_frequency_string() {
        switch (this.h_sample_frequency) {
            case 2: {
                if (this.h_version == 1) {
                    return "32 kHz";
                }
                if (this.h_version == 0) {
                    return "16 kHz";
                }
                return "8 kHz";
            }
            case 0: {
                if (this.h_version == 1) {
                    return "44.1 kHz";
                }
                if (this.h_version == 0) {
                    return "22.05 kHz";
                }
                return "11.025 kHz";
            }
            case 1: {
                if (this.h_version == 1) {
                    return "48 kHz";
                }
                if (this.h_version == 0) {
                    return "24 kHz";
                }
                return "12 kHz";
            }
            default: {
                return null;
            }
        }
    }
    
    public String mode_string() {
        switch (this.h_mode) {
            case 0: {
                return "Stereo";
            }
            case 1: {
                return "Joint stereo";
            }
            case 2: {
                return "Dual channel";
            }
            case 3: {
                return "Single channel";
            }
            default: {
                return null;
            }
        }
    }
    
    public String version_string() {
        switch (this.h_version) {
            case 1: {
                return "MPEG-1";
            }
            case 0: {
                return "MPEG-2 LSF";
            }
            case 2: {
                return "MPEG-2.5 LSF";
            }
            default: {
                return null;
            }
        }
    }
    
    public int number_of_subbands() {
        return this.h_number_of_subbands;
    }
    
    public int intensity_stereo_bound() {
        return this.h_intensity_stereo_bound;
    }
    
    static {
        frequencies = new int[][] { { 22050, 24000, 16000, 1 }, { 44100, 48000, 32000, 1 }, { 11025, 12000, 8000, 1 } };
        bitrates = new int[][][] { { { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 } }, { { 0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0 }, { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0 }, { 0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0 } }, { { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 } } };
        bitrate_str = new String[][][] { { { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" } }, { { "free format", "32 kbit/s", "64 kbit/s", "96 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "288 kbit/s", "320 kbit/s", "352 kbit/s", "384 kbit/s", "416 kbit/s", "448 kbit/s", "forbidden" }, { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "384 kbit/s", "forbidden" }, { "free format", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "forbidden" } }, { { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" } } };
    }
}
