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
    private byte syncmode;
    private Crc16 crc;
    public short checksum;
    public int framesize;
    public int nSlots;
    private int _headerstring;
    public static final int[][][] bitrates;
    public static final String[][][] bitrate_str;
    
    Header() {
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
            bitstream.read_frame_data(this.framesize);
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
        if (this.framesize + 4 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 4 - this.h_padding_bit);
    }
    
    public int min_number_of_frames(final int n) {
        if (this.framesize + 5 - this.h_padding_bit == 0) {
            return 0;
        }
        return n / (this.framesize + 5 - this.h_padding_bit);
    }
    
    public float ms_per_frame() {
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
        return Header.bitrate_str[this.h_version][this.h_layer - 1][this.h_bitrate_index];
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
