// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

final class LayerIIIDecoder implements FrameDecoder
{
    public int[] scalefac_buffer;
    private int CheckSumHuff;
    private int[] is_1d;
    private float[][][] ro;
    private float[][][] lr;
    private float[] out_1d;
    private float[][] prevblck;
    private float[][] k;
    private int[] nonzero;
    private Bitstream stream;
    private Header header;
    private SynthesisFilter filter1;
    private SynthesisFilter filter2;
    private Obuffer buffer;
    private int which_channels;
    private BitReserve br;
    private III_side_info_t si;
    private temporaire2[] III_scalefac_t;
    private temporaire2[] scalefac;
    private int max_gr;
    private int frame_start;
    private int part2_start;
    private int channels;
    private int first_channel;
    private int last_channel;
    private int sfreq;
    private float[] samples1;
    private float[] samples2;
    private final int[] new_slen;
    int[] x;
    int[] y;
    int[] v;
    int[] w;
    int[] is_pos;
    float[] is_ratio;
    float[] tsOutCopy;
    float[] rawout;
    private int counter;
    private static final int SSLIMIT = 18;
    private static final int SBLIMIT = 32;
    private static final int[][] slen;
    public static final int[] pretab;
    private SBI[] sfBandIndex;
    public static final float[] two_to_negative_half_pow;
    public static final float[] t_43;
    public static final float[][] io;
    public static final float[] TAN12;
    private static int[][] reorder_table;
    private static final float[] cs;
    private static final float[] ca;
    public static final float[][] win;
    public Sftable sftable;
    public static final int[][][] nr_of_sfb_block;
    
    public LayerIIIDecoder(final Bitstream stream, final Header header, final SynthesisFilter filter1, final SynthesisFilter filter2, final Obuffer buffer, final int which_channels) {
        this.CheckSumHuff = 0;
        this.samples1 = new float[32];
        this.samples2 = new float[32];
        this.new_slen = new int[4];
        this.x = new int[] { 0 };
        this.y = new int[] { 0 };
        this.v = new int[] { 0 };
        this.w = new int[] { 0 };
        this.is_pos = new int[576];
        this.is_ratio = new float[576];
        this.tsOutCopy = new float[18];
        this.rawout = new float[36];
        this.counter = 0;
        huffcodetab.inithuff();
        this.is_1d = new int[580];
        this.ro = new float[2][32][18];
        this.lr = new float[2][32][18];
        this.out_1d = new float[576];
        this.prevblck = new float[2][576];
        this.k = new float[2][576];
        this.nonzero = new int[2];
        (this.III_scalefac_t = new temporaire2[2])[0] = new temporaire2();
        this.III_scalefac_t[1] = new temporaire2();
        this.scalefac = this.III_scalefac_t;
        this.sfBandIndex = new SBI[9];
        final int[] array = { 0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576 };
        final int[] array2 = { 0, 4, 8, 12, 18, 24, 32, 42, 56, 74, 100, 132, 174, 192 };
        final int[] array3 = { 0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 114, 136, 162, 194, 232, 278, 330, 394, 464, 540, 576 };
        final int[] array4 = { 0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 136, 180, 192 };
        final int[] array5 = { 0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576 };
        final int[] array6 = { 0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192 };
        final int[] array7 = { 0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 52, 62, 74, 90, 110, 134, 162, 196, 238, 288, 342, 418, 576 };
        final int[] array8 = { 0, 4, 8, 12, 16, 22, 30, 40, 52, 66, 84, 106, 136, 192 };
        final int[] array9 = { 0, 4, 8, 12, 16, 20, 24, 30, 36, 42, 50, 60, 72, 88, 106, 128, 156, 190, 230, 276, 330, 384, 576 };
        final int[] array10 = { 0, 4, 8, 12, 16, 22, 28, 38, 50, 64, 80, 100, 126, 192 };
        final int[] array11 = { 0, 4, 8, 12, 16, 20, 24, 30, 36, 44, 54, 66, 82, 102, 126, 156, 194, 240, 296, 364, 448, 550, 576 };
        final int[] array12 = { 0, 4, 8, 12, 16, 22, 30, 42, 58, 78, 104, 138, 180, 192 };
        final int[] array13 = { 0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576 };
        final int[] array14 = { 0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192 };
        final int[] array15 = { 0, 6, 12, 18, 24, 30, 36, 44, 54, 66, 80, 96, 116, 140, 168, 200, 238, 284, 336, 396, 464, 522, 576 };
        final int[] array16 = { 0, 4, 8, 12, 18, 26, 36, 48, 62, 80, 104, 134, 174, 192 };
        final int[] array17 = { 0, 12, 24, 36, 48, 60, 72, 88, 108, 132, 160, 192, 232, 280, 336, 400, 476, 566, 568, 570, 572, 574, 576 };
        final int[] array18 = { 0, 8, 16, 24, 36, 52, 72, 96, 124, 160, 162, 164, 166, 192 };
        this.sfBandIndex[0] = new SBI(array, array2);
        this.sfBandIndex[1] = new SBI(array3, array4);
        this.sfBandIndex[2] = new SBI(array5, array6);
        this.sfBandIndex[3] = new SBI(array7, array8);
        this.sfBandIndex[4] = new SBI(array9, array10);
        this.sfBandIndex[5] = new SBI(array11, array12);
        this.sfBandIndex[6] = new SBI(array13, array14);
        this.sfBandIndex[7] = new SBI(array15, array16);
        this.sfBandIndex[8] = new SBI(array17, array18);
        if (LayerIIIDecoder.reorder_table == null) {
            LayerIIIDecoder.reorder_table = new int[9][];
            for (int i = 0; i < 9; ++i) {
                LayerIIIDecoder.reorder_table[i] = reorder(this.sfBandIndex[i].s);
            }
        }
        this.sftable = new Sftable(new int[] { 0, 6, 11, 16, 21 }, new int[] { 0, 6, 12 });
        this.scalefac_buffer = new int[54];
        this.stream = stream;
        this.header = header;
        this.filter1 = filter1;
        this.filter2 = filter2;
        this.buffer = buffer;
        this.which_channels = which_channels;
        this.frame_start = 0;
        this.channels = ((this.header.mode() == 3) ? 1 : 2);
        this.max_gr = ((this.header.version() == 1) ? 2 : 1);
        this.sfreq = this.header.sample_frequency() + ((this.header.version() == 1) ? 3 : ((this.header.version() == 2) ? 6 : 0));
        if (this.channels == 2) {
            switch (this.which_channels) {
                case 1:
                case 3: {
                    final boolean b = false;
                    this.last_channel = (b ? 1 : 0);
                    this.first_channel = (b ? 1 : 0);
                    break;
                }
                case 2: {
                    final boolean b2 = true;
                    this.last_channel = (b2 ? 1 : 0);
                    this.first_channel = (b2 ? 1 : 0);
                    break;
                }
                default: {
                    this.first_channel = 0;
                    this.last_channel = 1;
                    break;
                }
            }
        }
        else {
            final boolean b3 = false;
            this.last_channel = (b3 ? 1 : 0);
            this.first_channel = (b3 ? 1 : 0);
        }
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 576; ++k) {
                this.prevblck[j][k] = 0.0f;
            }
        }
        this.nonzero[0] = (this.nonzero[1] = 576);
        this.br = new BitReserve();
        this.si = new III_side_info_t();
    }
    
    public void seek_notify() {
        this.frame_start = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 576; ++j) {
                this.prevblck[i][j] = 0.0f;
            }
        }
        this.br = new BitReserve();
    }
    
    public void decodeFrame() {
        this.decode();
    }
    
    public void decode() {
        final int slots = this.header.slots();
        this.get_side_info();
        for (int i = 0; i < slots; ++i) {
            this.br.hputbuf(this.stream.get_bits(8));
        }
        int n = this.br.hsstell() >>> 3;
        final int n2;
        if ((n2 = (this.br.hsstell() & 0x7)) != 0) {
            this.br.hgetbits(8 - n2);
            ++n;
        }
        int j = this.frame_start - n - this.si.main_data_begin;
        this.frame_start += slots;
        if (j < 0) {
            return;
        }
        if (n > 4096) {
            this.frame_start -= 4096;
            this.br.rewindNbytes(4096);
        }
        while (j > 0) {
            this.br.hgetbits(8);
            --j;
        }
        for (int k = 0; k < this.max_gr; ++k) {
            for (int l = 0; l < this.channels; ++l) {
                this.part2_start = this.br.hsstell();
                if (this.header.version() == 1) {
                    this.get_scale_factors(l, k);
                }
                else {
                    this.get_LSF_scale_factors(l, k);
                }
                this.huffman_decode(l, k);
                this.dequantize_sample(this.ro[l], l, k);
            }
            this.stereo(k);
            if (this.which_channels == 3 && this.channels > 1) {
                this.do_downmix();
            }
            for (int first_channel = this.first_channel; first_channel <= this.last_channel; ++first_channel) {
                this.reorder(this.lr[first_channel], first_channel, k);
                this.antialias(first_channel, k);
                this.hybrid(first_channel, k);
                for (int n3 = 18; n3 < 576; n3 += 36) {
                    for (int n4 = 1; n4 < 18; n4 += 2) {
                        this.out_1d[n3 + n4] = -this.out_1d[n3 + n4];
                    }
                }
                if (first_channel == 0 || this.which_channels == 2) {
                    for (int n5 = 0; n5 < 18; ++n5) {
                        int n6 = 0;
                        for (int n7 = 0; n7 < 576; n7 += 18) {
                            this.samples1[n6] = this.out_1d[n7 + n5];
                            ++n6;
                        }
                        this.filter1.input_samples(this.samples1);
                        this.filter1.calculate_pcm_samples(this.buffer);
                    }
                }
                else {
                    for (int n8 = 0; n8 < 18; ++n8) {
                        int n9 = 0;
                        for (int n10 = 0; n10 < 576; n10 += 18) {
                            this.samples2[n9] = this.out_1d[n10 + n8];
                            ++n9;
                        }
                        this.filter2.input_samples(this.samples2);
                        this.filter2.calculate_pcm_samples(this.buffer);
                    }
                }
            }
        }
        ++this.counter;
        this.buffer.write_buffer(1);
    }
    
    private boolean get_side_info() {
        if (this.header.version() == 1) {
            this.si.main_data_begin = this.stream.get_bits(9);
            if (this.channels == 1) {
                this.si.private_bits = this.stream.get_bits(5);
            }
            else {
                this.si.private_bits = this.stream.get_bits(3);
            }
            for (int i = 0; i < this.channels; ++i) {
                this.si.ch[i].scfsi[0] = this.stream.get_bits(1);
                this.si.ch[i].scfsi[1] = this.stream.get_bits(1);
                this.si.ch[i].scfsi[2] = this.stream.get_bits(1);
                this.si.ch[i].scfsi[3] = this.stream.get_bits(1);
            }
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < this.channels; ++k) {
                    this.si.ch[k].gr[j].part2_3_length = this.stream.get_bits(12);
                    this.si.ch[k].gr[j].big_values = this.stream.get_bits(9);
                    this.si.ch[k].gr[j].global_gain = this.stream.get_bits(8);
                    this.si.ch[k].gr[j].scalefac_compress = this.stream.get_bits(4);
                    this.si.ch[k].gr[j].window_switching_flag = this.stream.get_bits(1);
                    if (this.si.ch[k].gr[j].window_switching_flag != 0) {
                        this.si.ch[k].gr[j].block_type = this.stream.get_bits(2);
                        this.si.ch[k].gr[j].mixed_block_flag = this.stream.get_bits(1);
                        this.si.ch[k].gr[j].table_select[0] = this.stream.get_bits(5);
                        this.si.ch[k].gr[j].table_select[1] = this.stream.get_bits(5);
                        this.si.ch[k].gr[j].subblock_gain[0] = this.stream.get_bits(3);
                        this.si.ch[k].gr[j].subblock_gain[1] = this.stream.get_bits(3);
                        this.si.ch[k].gr[j].subblock_gain[2] = this.stream.get_bits(3);
                        if (this.si.ch[k].gr[j].block_type == 0) {
                            return false;
                        }
                        if (this.si.ch[k].gr[j].block_type == 2 && this.si.ch[k].gr[j].mixed_block_flag == 0) {
                            this.si.ch[k].gr[j].region0_count = 8;
                        }
                        else {
                            this.si.ch[k].gr[j].region0_count = 7;
                        }
                        this.si.ch[k].gr[j].region1_count = 20 - this.si.ch[k].gr[j].region0_count;
                    }
                    else {
                        this.si.ch[k].gr[j].table_select[0] = this.stream.get_bits(5);
                        this.si.ch[k].gr[j].table_select[1] = this.stream.get_bits(5);
                        this.si.ch[k].gr[j].table_select[2] = this.stream.get_bits(5);
                        this.si.ch[k].gr[j].region0_count = this.stream.get_bits(4);
                        this.si.ch[k].gr[j].region1_count = this.stream.get_bits(3);
                        this.si.ch[k].gr[j].block_type = 0;
                    }
                    this.si.ch[k].gr[j].preflag = this.stream.get_bits(1);
                    this.si.ch[k].gr[j].scalefac_scale = this.stream.get_bits(1);
                    this.si.ch[k].gr[j].count1table_select = this.stream.get_bits(1);
                }
            }
        }
        else {
            this.si.main_data_begin = this.stream.get_bits(8);
            if (this.channels == 1) {
                this.si.private_bits = this.stream.get_bits(1);
            }
            else {
                this.si.private_bits = this.stream.get_bits(2);
            }
            for (int l = 0; l < this.channels; ++l) {
                this.si.ch[l].gr[0].part2_3_length = this.stream.get_bits(12);
                this.si.ch[l].gr[0].big_values = this.stream.get_bits(9);
                this.si.ch[l].gr[0].global_gain = this.stream.get_bits(8);
                this.si.ch[l].gr[0].scalefac_compress = this.stream.get_bits(9);
                this.si.ch[l].gr[0].window_switching_flag = this.stream.get_bits(1);
                if (this.si.ch[l].gr[0].window_switching_flag != 0) {
                    this.si.ch[l].gr[0].block_type = this.stream.get_bits(2);
                    this.si.ch[l].gr[0].mixed_block_flag = this.stream.get_bits(1);
                    this.si.ch[l].gr[0].table_select[0] = this.stream.get_bits(5);
                    this.si.ch[l].gr[0].table_select[1] = this.stream.get_bits(5);
                    this.si.ch[l].gr[0].subblock_gain[0] = this.stream.get_bits(3);
                    this.si.ch[l].gr[0].subblock_gain[1] = this.stream.get_bits(3);
                    this.si.ch[l].gr[0].subblock_gain[2] = this.stream.get_bits(3);
                    if (this.si.ch[l].gr[0].block_type == 0) {
                        return false;
                    }
                    if (this.si.ch[l].gr[0].block_type == 2 && this.si.ch[l].gr[0].mixed_block_flag == 0) {
                        this.si.ch[l].gr[0].region0_count = 8;
                    }
                    else {
                        this.si.ch[l].gr[0].region0_count = 7;
                        this.si.ch[l].gr[0].region1_count = 20 - this.si.ch[l].gr[0].region0_count;
                    }
                }
                else {
                    this.si.ch[l].gr[0].table_select[0] = this.stream.get_bits(5);
                    this.si.ch[l].gr[0].table_select[1] = this.stream.get_bits(5);
                    this.si.ch[l].gr[0].table_select[2] = this.stream.get_bits(5);
                    this.si.ch[l].gr[0].region0_count = this.stream.get_bits(4);
                    this.si.ch[l].gr[0].region1_count = this.stream.get_bits(3);
                    this.si.ch[l].gr[0].block_type = 0;
                }
                this.si.ch[l].gr[0].scalefac_scale = this.stream.get_bits(1);
                this.si.ch[l].gr[0].count1table_select = this.stream.get_bits(1);
            }
        }
        return true;
    }
    
    private void get_scale_factors(final int n, final int n2) {
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        final int scalefac_compress = gr_info_s.scalefac_compress;
        final int n3 = LayerIIIDecoder.slen[0][scalefac_compress];
        final int n4 = LayerIIIDecoder.slen[1][scalefac_compress];
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
            if (gr_info_s.mixed_block_flag != 0) {
                for (int i = 0; i < 8; ++i) {
                    this.scalefac[n].l[i] = this.br.hgetbits(LayerIIIDecoder.slen[0][gr_info_s.scalefac_compress]);
                }
                for (int j = 3; j < 6; ++j) {
                    for (int k = 0; k < 3; ++k) {
                        this.scalefac[n].s[k][j] = this.br.hgetbits(LayerIIIDecoder.slen[0][gr_info_s.scalefac_compress]);
                    }
                }
                for (int l = 6; l < 12; ++l) {
                    for (int n5 = 0; n5 < 3; ++n5) {
                        this.scalefac[n].s[n5][l] = this.br.hgetbits(LayerIIIDecoder.slen[1][gr_info_s.scalefac_compress]);
                    }
                }
                final int n6 = 12;
                for (int n7 = 0; n7 < 3; ++n7) {
                    this.scalefac[n].s[n7][n6] = 0;
                }
            }
            else {
                this.scalefac[n].s[0][0] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][0] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][0] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][1] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][1] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][1] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][2] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][2] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][2] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][3] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][3] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][3] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][4] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][4] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][4] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][5] = this.br.hgetbits(n3);
                this.scalefac[n].s[1][5] = this.br.hgetbits(n3);
                this.scalefac[n].s[2][5] = this.br.hgetbits(n3);
                this.scalefac[n].s[0][6] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][6] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][6] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][7] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][7] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][7] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][8] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][8] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][8] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][9] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][9] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][9] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][10] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][10] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][10] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][11] = this.br.hgetbits(n4);
                this.scalefac[n].s[1][11] = this.br.hgetbits(n4);
                this.scalefac[n].s[2][11] = this.br.hgetbits(n4);
                this.scalefac[n].s[0][12] = 0;
                this.scalefac[n].s[1][12] = 0;
                this.scalefac[n].s[2][12] = 0;
            }
        }
        else {
            if (this.si.ch[n].scfsi[0] == 0 || n2 == 0) {
                this.scalefac[n].l[0] = this.br.hgetbits(n3);
                this.scalefac[n].l[1] = this.br.hgetbits(n3);
                this.scalefac[n].l[2] = this.br.hgetbits(n3);
                this.scalefac[n].l[3] = this.br.hgetbits(n3);
                this.scalefac[n].l[4] = this.br.hgetbits(n3);
                this.scalefac[n].l[5] = this.br.hgetbits(n3);
            }
            if (this.si.ch[n].scfsi[1] == 0 || n2 == 0) {
                this.scalefac[n].l[6] = this.br.hgetbits(n3);
                this.scalefac[n].l[7] = this.br.hgetbits(n3);
                this.scalefac[n].l[8] = this.br.hgetbits(n3);
                this.scalefac[n].l[9] = this.br.hgetbits(n3);
                this.scalefac[n].l[10] = this.br.hgetbits(n3);
            }
            if (this.si.ch[n].scfsi[2] == 0 || n2 == 0) {
                this.scalefac[n].l[11] = this.br.hgetbits(n4);
                this.scalefac[n].l[12] = this.br.hgetbits(n4);
                this.scalefac[n].l[13] = this.br.hgetbits(n4);
                this.scalefac[n].l[14] = this.br.hgetbits(n4);
                this.scalefac[n].l[15] = this.br.hgetbits(n4);
            }
            if (this.si.ch[n].scfsi[3] == 0 || n2 == 0) {
                this.scalefac[n].l[16] = this.br.hgetbits(n4);
                this.scalefac[n].l[17] = this.br.hgetbits(n4);
                this.scalefac[n].l[18] = this.br.hgetbits(n4);
                this.scalefac[n].l[19] = this.br.hgetbits(n4);
                this.scalefac[n].l[20] = this.br.hgetbits(n4);
            }
            this.scalefac[n].l[21] = 0;
            this.scalefac[n].l[22] = 0;
        }
    }
    
    private void get_LSF_scale_data(final int n, final int n2) {
        final int mode_extension = this.header.mode_extension();
        int n3 = 0;
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        final int scalefac_compress = gr_info_s.scalefac_compress;
        int n4;
        if (gr_info_s.block_type == 2) {
            if (gr_info_s.mixed_block_flag == 0) {
                n4 = 1;
            }
            else if (gr_info_s.mixed_block_flag == 1) {
                n4 = 2;
            }
            else {
                n4 = 0;
            }
        }
        else {
            n4 = 0;
        }
        if ((mode_extension != 1 && mode_extension != 3) || n != 1) {
            if (scalefac_compress < 400) {
                this.new_slen[0] = (scalefac_compress >>> 4) / 5;
                this.new_slen[1] = (scalefac_compress >>> 4) % 5;
                this.new_slen[2] = (scalefac_compress & 0xF) >>> 2;
                this.new_slen[3] = (scalefac_compress & 0x3);
                this.si.ch[n].gr[n2].preflag = 0;
                n3 = 0;
            }
            else if (scalefac_compress < 500) {
                this.new_slen[0] = (scalefac_compress - 400 >>> 2) / 5;
                this.new_slen[1] = (scalefac_compress - 400 >>> 2) % 5;
                this.new_slen[2] = (scalefac_compress - 400 & 0x3);
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n3 = 1;
            }
            else if (scalefac_compress < 512) {
                this.new_slen[0] = (scalefac_compress - 500) / 3;
                this.new_slen[1] = (scalefac_compress - 500) % 3;
                this.new_slen[2] = 0;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 1;
                n3 = 2;
            }
        }
        if ((mode_extension == 1 || mode_extension == 3) && n == 1) {
            final int n5 = scalefac_compress >>> 1;
            if (n5 < 180) {
                this.new_slen[0] = n5 / 36;
                this.new_slen[1] = n5 % 36 / 6;
                this.new_slen[2] = n5 % 36 % 6;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n3 = 3;
            }
            else if (n5 < 244) {
                this.new_slen[0] = (n5 - 180 & 0x3F) >>> 4;
                this.new_slen[1] = (n5 - 180 & 0xF) >>> 2;
                this.new_slen[2] = (n5 - 180 & 0x3);
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n3 = 4;
            }
            else if (n5 < 255) {
                this.new_slen[0] = (n5 - 244) / 3;
                this.new_slen[1] = (n5 - 244) % 3;
                this.new_slen[2] = 0;
                this.new_slen[3] = 0;
                this.si.ch[n].gr[n2].preflag = 0;
                n3 = 5;
            }
        }
        for (int i = 0; i < 45; ++i) {
            this.scalefac_buffer[i] = 0;
        }
        int n6 = 0;
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < LayerIIIDecoder.nr_of_sfb_block[n3][n4][j]; ++k) {
                this.scalefac_buffer[n6] = ((this.new_slen[j] == 0) ? 0 : this.br.hgetbits(this.new_slen[j]));
                ++n6;
            }
        }
    }
    
    private void get_LSF_scale_factors(final int n, final int n2) {
        int n3 = 0;
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        this.get_LSF_scale_data(n, n2);
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
            if (gr_info_s.mixed_block_flag != 0) {
                for (int i = 0; i < 8; ++i) {
                    this.scalefac[n].l[i] = this.scalefac_buffer[n3];
                    ++n3;
                }
                for (int j = 3; j < 12; ++j) {
                    for (int k = 0; k < 3; ++k) {
                        this.scalefac[n].s[k][j] = this.scalefac_buffer[n3];
                        ++n3;
                    }
                }
                for (int l = 0; l < 3; ++l) {
                    this.scalefac[n].s[l][12] = 0;
                }
            }
            else {
                for (int n4 = 0; n4 < 12; ++n4) {
                    for (int n5 = 0; n5 < 3; ++n5) {
                        this.scalefac[n].s[n5][n4] = this.scalefac_buffer[n3];
                        ++n3;
                    }
                }
                for (int n6 = 0; n6 < 3; ++n6) {
                    this.scalefac[n].s[n6][12] = 0;
                }
            }
        }
        else {
            for (int n7 = 0; n7 < 21; ++n7) {
                this.scalefac[n].l[n7] = this.scalefac_buffer[n3];
                ++n3;
            }
            this.scalefac[n].l[21] = 0;
            this.scalefac[n].l[22] = 0;
        }
    }
    
    private void huffman_decode(final int n, final int n2) {
        this.x[0] = 0;
        this.y[0] = 0;
        this.v[0] = 0;
        this.w[0] = 0;
        final int n3 = this.part2_start + this.si.ch[n].gr[n2].part2_3_length;
        int n4;
        int n5;
        if (this.si.ch[n].gr[n2].window_switching_flag != 0 && this.si.ch[n].gr[n2].block_type == 2) {
            n4 = ((this.sfreq == 8) ? 72 : 36);
            n5 = 576;
        }
        else {
            final int n6 = this.si.ch[n].gr[n2].region0_count + 1;
            int n7 = n6 + this.si.ch[n].gr[n2].region1_count + 1;
            if (n7 > this.sfBandIndex[this.sfreq].l.length - 1) {
                n7 = this.sfBandIndex[this.sfreq].l.length - 1;
            }
            n4 = this.sfBandIndex[this.sfreq].l[n6];
            n5 = this.sfBandIndex[this.sfreq].l[n7];
        }
        int i = 0;
        for (int j = 0; j < this.si.ch[n].gr[n2].big_values << 1; j += 2) {
            huffcodetab huffcodetab;
            if (j < n4) {
                huffcodetab = javazoom.jl.decoder.huffcodetab.ht[this.si.ch[n].gr[n2].table_select[0]];
            }
            else if (j < n5) {
                huffcodetab = javazoom.jl.decoder.huffcodetab.ht[this.si.ch[n].gr[n2].table_select[1]];
            }
            else {
                huffcodetab = javazoom.jl.decoder.huffcodetab.ht[this.si.ch[n].gr[n2].table_select[2]];
            }
            javazoom.jl.decoder.huffcodetab.huffman_decoder(huffcodetab, this.x, this.y, this.v, this.w, this.br);
            this.is_1d[i++] = this.x[0];
            this.is_1d[i++] = this.y[0];
            this.CheckSumHuff = this.CheckSumHuff + this.x[0] + this.y[0];
        }
        final huffcodetab huffcodetab2 = huffcodetab.ht[this.si.ch[n].gr[n2].count1table_select + 32];
        int n8;
        for (n8 = this.br.hsstell(); n8 < n3 && i < 576; this.is_1d[i++] = this.v[0], this.is_1d[i++] = this.w[0], this.is_1d[i++] = this.x[0], this.is_1d[i++] = this.y[0], this.CheckSumHuff = this.CheckSumHuff + this.v[0] + this.w[0] + this.x[0] + this.y[0], n8 = this.br.hsstell()) {
            huffcodetab.huffman_decoder(huffcodetab2, this.x, this.y, this.v, this.w, this.br);
        }
        if (n8 > n3) {
            this.br.rewindNbits(n8 - n3);
            i -= 4;
        }
        final int hsstell = this.br.hsstell();
        if (hsstell < n3) {
            this.br.hgetbits(n3 - hsstell);
        }
        if (i < 576) {
            this.nonzero[n] = i;
        }
        else {
            this.nonzero[n] = 576;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < 576) {
            this.is_1d[i] = 0;
            ++i;
        }
    }
    
    private void i_stereo_k_values(final int n, final int n2, final int n3) {
        if (n == 0) {
            this.k[0][n3] = 1.0f;
            this.k[1][n3] = 1.0f;
        }
        else if ((n & 0x1) != 0x0) {
            this.k[0][n3] = LayerIIIDecoder.io[n2][n + 1 >>> 1];
            this.k[1][n3] = 1.0f;
        }
        else {
            this.k[0][n3] = 1.0f;
            this.k[1][n3] = LayerIIIDecoder.io[n2][n >>> 1];
        }
    }
    
    private void dequantize_sample(final float[][] array, final int n, final int n2) {
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7;
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
            if (gr_info_s.mixed_block_flag != 0) {
                n7 = this.sfBandIndex[this.sfreq].l[1];
            }
            else {
                n5 = this.sfBandIndex[this.sfreq].s[1];
                n7 = (n5 << 2) - n5;
                n4 = 0;
            }
        }
        else {
            n7 = this.sfBandIndex[this.sfreq].l[1];
        }
        final float n8 = (float)Math.pow(2.0, 0.25 * (gr_info_s.global_gain - 210.0));
        for (int i = 0; i < this.nonzero[n]; ++i) {
            final int n9 = i % 18;
            final int n10 = (i - n9) / 18;
            if (this.is_1d[i] == 0) {
                array[n10][n9] = 0.0f;
            }
            else {
                final int n11 = this.is_1d[i];
                if (this.is_1d[i] > 0) {
                    array[n10][n9] = n8 * LayerIIIDecoder.t_43[n11];
                }
                else {
                    array[n10][n9] = -n8 * LayerIIIDecoder.t_43[-n11];
                }
            }
        }
        for (int j = 0; j < this.nonzero[n]; ++j) {
            final int n12 = j % 18;
            final int n13 = (j - n12) / 18;
            if (n6 == n7) {
                if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
                    if (gr_info_s.mixed_block_flag != 0) {
                        if (n6 == this.sfBandIndex[this.sfreq].l[8]) {
                            final int n14 = this.sfBandIndex[this.sfreq].s[4];
                            n7 = (n14 << 2) - n14;
                            n3 = 3;
                            n5 = this.sfBandIndex[this.sfreq].s[4] - this.sfBandIndex[this.sfreq].s[3];
                            final int n15 = this.sfBandIndex[this.sfreq].s[3];
                            n4 = (n15 << 2) - n15;
                        }
                        else if (n6 < this.sfBandIndex[this.sfreq].l[8]) {
                            n7 = this.sfBandIndex[this.sfreq].l[++n3 + 1];
                        }
                        else {
                            final int n16 = this.sfBandIndex[this.sfreq].s[++n3 + 1];
                            n7 = (n16 << 2) - n16;
                            final int n17 = this.sfBandIndex[this.sfreq].s[n3];
                            n5 = this.sfBandIndex[this.sfreq].s[n3 + 1] - n17;
                            n4 = (n17 << 2) - n17;
                        }
                    }
                    else {
                        final int n18 = this.sfBandIndex[this.sfreq].s[++n3 + 1];
                        n7 = (n18 << 2) - n18;
                        final int n19 = this.sfBandIndex[this.sfreq].s[n3];
                        n5 = this.sfBandIndex[this.sfreq].s[n3 + 1] - n19;
                        n4 = (n19 << 2) - n19;
                    }
                }
                else {
                    n7 = this.sfBandIndex[this.sfreq].l[++n3 + 1];
                }
            }
            if (gr_info_s.window_switching_flag != 0 && ((gr_info_s.block_type == 2 && gr_info_s.mixed_block_flag == 0) || (gr_info_s.block_type == 2 && gr_info_s.mixed_block_flag != 0 && j >= 36))) {
                final int n20 = (n6 - n4) / n5;
                final int n21 = (this.scalefac[n].s[n20][n3] << gr_info_s.scalefac_scale) + (gr_info_s.subblock_gain[n20] << 2);
                final float[] array2 = array[n13];
                final int n22 = n12;
                array2[n22] *= LayerIIIDecoder.two_to_negative_half_pow[n21];
            }
            else {
                int n23 = this.scalefac[n].l[n3];
                if (gr_info_s.preflag != 0) {
                    n23 += LayerIIIDecoder.pretab[n3];
                }
                final int n24 = n23 << gr_info_s.scalefac_scale;
                final float[] array3 = array[n13];
                final int n25 = n12;
                array3[n25] *= LayerIIIDecoder.two_to_negative_half_pow[n24];
            }
            ++n6;
        }
        for (int k = this.nonzero[n]; k < 576; ++k) {
            int n26 = k % 18;
            int n27 = (k - n26) / 18;
            if (n26 < 0) {
                n26 = 0;
            }
            if (n27 < 0) {
                n27 = 0;
            }
            array[n27][n26] = 0.0f;
        }
    }
    
    private void reorder(final float[][] array, final int n, final int n2) {
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
            for (int i = 0; i < 576; ++i) {
                this.out_1d[i] = 0.0f;
            }
            if (gr_info_s.mixed_block_flag != 0) {
                for (int j = 0; j < 36; ++j) {
                    final int n3 = j % 18;
                    this.out_1d[j] = array[(j - n3) / 18][n3];
                }
                int k = 3;
                int n4 = this.sfBandIndex[this.sfreq].s[3];
                int n5 = this.sfBandIndex[this.sfreq].s[4] - n4;
                while (k < 13) {
                    final int n6 = (n4 << 2) - n4;
                    for (int l = 0, n7 = 0; l < n5; ++l, n7 += 3) {
                        final int n8 = n6 + l;
                        int n9 = n6 + n7;
                        final int n10 = n8 % 18;
                        this.out_1d[n9] = array[(n8 - n10) / 18][n10];
                        final int n11 = n8 + n5;
                        ++n9;
                        final int n12 = n11 % 18;
                        this.out_1d[n9] = array[(n11 - n12) / 18][n12];
                        final int n13 = n11 + n5;
                        ++n9;
                        final int n14 = n13 % 18;
                        this.out_1d[n9] = array[(n13 - n14) / 18][n14];
                    }
                    ++k;
                    n4 = this.sfBandIndex[this.sfreq].s[k];
                    n5 = this.sfBandIndex[this.sfreq].s[k + 1] - n4;
                }
            }
            else {
                for (int n15 = 0; n15 < 576; ++n15) {
                    final int n16 = LayerIIIDecoder.reorder_table[this.sfreq][n15];
                    final int n17 = n16 % 18;
                    this.out_1d[n15] = array[(n16 - n17) / 18][n17];
                }
            }
        }
        else {
            for (int n18 = 0; n18 < 576; ++n18) {
                final int n19 = n18 % 18;
                this.out_1d[n18] = array[(n18 - n19) / 18][n19];
            }
        }
    }
    
    private void stereo(final int n) {
        if (this.channels == 1) {
            for (int i = 0; i < 32; ++i) {
                for (int j = 0; j < 18; j += 3) {
                    this.lr[0][i][j] = this.ro[0][i][j];
                    this.lr[0][i][j + 1] = this.ro[0][i][j + 1];
                    this.lr[0][i][j + 2] = this.ro[0][i][j + 2];
                }
            }
        }
        else {
            final gr_info_s gr_info_s = this.si.ch[0].gr[n];
            final int mode_extension = this.header.mode_extension();
            final boolean b = this.header.mode() == 1 && (mode_extension & 0x2) != 0x0;
            final boolean b2 = this.header.mode() == 1 && (mode_extension & 0x1) != 0x0;
            final boolean b3 = this.header.version() == 0 || this.header.version() == 2;
            final int n2 = gr_info_s.scalefac_compress & 0x1;
            for (int k = 0; k < 576; ++k) {
                this.is_pos[k] = 7;
                this.is_ratio[k] = 0.0f;
            }
            if (b2) {
                if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2) {
                    if (gr_info_s.mixed_block_flag != 0) {
                        int n3 = 0;
                        for (int l = 0; l < 3; ++l) {
                            int n4 = 2;
                            for (int n5 = 12; n5 >= 3; --n5) {
                                final int n6 = this.sfBandIndex[this.sfreq].s[n5];
                                for (int n7 = this.sfBandIndex[this.sfreq].s[n5 + 1] - n6, n8 = (n6 << 2) - n6 + (l + 1) * n7 - 1; n7 > 0; --n7, --n8) {
                                    if (this.ro[1][n8 / 18][n8 % 18] != 0.0f) {
                                        n4 = n5;
                                        n5 = -10;
                                        n7 = -10;
                                    }
                                }
                            }
                            int n9 = n4 + 1;
                            if (n9 > n3) {
                                n3 = n9;
                            }
                            while (n9 < 12) {
                                final int n10 = this.sfBandIndex[this.sfreq].s[n9];
                                int n11 = this.sfBandIndex[this.sfreq].s[n9 + 1] - n10;
                                int n12 = (n10 << 2) - n10 + l * n11;
                                while (n11 > 0) {
                                    this.is_pos[n12] = this.scalefac[1].s[l][n9];
                                    if (this.is_pos[n12] != 7) {
                                        if (b3) {
                                            this.i_stereo_k_values(this.is_pos[n12], n2, n12);
                                        }
                                        else {
                                            this.is_ratio[n12] = LayerIIIDecoder.TAN12[this.is_pos[n12]];
                                        }
                                    }
                                    ++n12;
                                    --n11;
                                }
                                ++n9;
                            }
                            final int n13 = this.sfBandIndex[this.sfreq].s[10];
                            final int n14 = (n13 << 2) - n13 + l * (this.sfBandIndex[this.sfreq].s[11] - n13);
                            final int n15 = this.sfBandIndex[this.sfreq].s[11];
                            int n16 = this.sfBandIndex[this.sfreq].s[12] - n15;
                            int n17 = (n15 << 2) - n15 + l * n16;
                            while (n16 > 0) {
                                this.is_pos[n17] = this.is_pos[n14];
                                if (b3) {
                                    this.k[0][n17] = this.k[0][n14];
                                    this.k[1][n17] = this.k[1][n14];
                                }
                                else {
                                    this.is_ratio[n17] = this.is_ratio[n14];
                                }
                                ++n17;
                                --n16;
                            }
                        }
                        if (n3 <= 3) {
                            int n18 = 2;
                            int n19 = 17;
                            int n20 = -1;
                            while (n18 >= 0) {
                                if (this.ro[1][n18][n19] != 0.0f) {
                                    n20 = (n18 << 4) + (n18 << 1) + n19;
                                    n18 = -1;
                                }
                                else {
                                    if (--n19 >= 0) {
                                        continue;
                                    }
                                    --n18;
                                    n19 = 17;
                                }
                            }
                            int n21;
                            for (n21 = 0; this.sfBandIndex[this.sfreq].l[n21] <= n20; ++n21) {}
                            int n22 = n21;
                            int n23 = this.sfBandIndex[this.sfreq].l[n21];
                            while (n22 < 8) {
                                for (int n24 = this.sfBandIndex[this.sfreq].l[n22 + 1] - this.sfBandIndex[this.sfreq].l[n22]; n24 > 0; --n24) {
                                    this.is_pos[n23] = this.scalefac[1].l[n22];
                                    if (this.is_pos[n23] != 7) {
                                        if (b3) {
                                            this.i_stereo_k_values(this.is_pos[n23], n2, n23);
                                        }
                                        else {
                                            this.is_ratio[n23] = LayerIIIDecoder.TAN12[this.is_pos[n23]];
                                        }
                                    }
                                    ++n23;
                                }
                                ++n22;
                            }
                        }
                    }
                    else {
                        for (int n25 = 0; n25 < 3; ++n25) {
                            int n26 = -1;
                            for (int n27 = 12; n27 >= 0; --n27) {
                                final int n28 = this.sfBandIndex[this.sfreq].s[n27];
                                for (int n29 = this.sfBandIndex[this.sfreq].s[n27 + 1] - n28, n30 = (n28 << 2) - n28 + (n25 + 1) * n29 - 1; n29 > 0; --n29, --n30) {
                                    if (this.ro[1][n30 / 18][n30 % 18] != 0.0f) {
                                        n26 = n27;
                                        n27 = -10;
                                        n29 = -10;
                                    }
                                }
                            }
                            for (int n31 = n26 + 1; n31 < 12; ++n31) {
                                final int n32 = this.sfBandIndex[this.sfreq].s[n31];
                                int n33 = this.sfBandIndex[this.sfreq].s[n31 + 1] - n32;
                                int n34 = (n32 << 2) - n32 + n25 * n33;
                                while (n33 > 0) {
                                    this.is_pos[n34] = this.scalefac[1].s[n25][n31];
                                    if (this.is_pos[n34] != 7) {
                                        if (b3) {
                                            this.i_stereo_k_values(this.is_pos[n34], n2, n34);
                                        }
                                        else {
                                            this.is_ratio[n34] = LayerIIIDecoder.TAN12[this.is_pos[n34]];
                                        }
                                    }
                                    ++n34;
                                    --n33;
                                }
                            }
                            final int n35 = this.sfBandIndex[this.sfreq].s[10];
                            final int n36 = this.sfBandIndex[this.sfreq].s[11];
                            final int n37 = (n35 << 2) - n35 + n25 * (n36 - n35);
                            int n38 = this.sfBandIndex[this.sfreq].s[12] - n36;
                            int n39 = (n36 << 2) - n36 + n25 * n38;
                            while (n38 > 0) {
                                this.is_pos[n39] = this.is_pos[n37];
                                if (b3) {
                                    this.k[0][n39] = this.k[0][n37];
                                    this.k[1][n39] = this.k[1][n37];
                                }
                                else {
                                    this.is_ratio[n39] = this.is_ratio[n37];
                                }
                                ++n39;
                                --n38;
                            }
                        }
                    }
                }
                else {
                    int n40 = 31;
                    int n41 = 17;
                    int n42 = 0;
                    while (n40 >= 0) {
                        if (this.ro[1][n40][n41] != 0.0f) {
                            n42 = (n40 << 4) + (n40 << 1) + n41;
                            n40 = -1;
                        }
                        else {
                            if (--n41 >= 0) {
                                continue;
                            }
                            --n40;
                            n41 = 17;
                        }
                    }
                    int n43;
                    for (n43 = 0; this.sfBandIndex[this.sfreq].l[n43] <= n42; ++n43) {}
                    int n44 = n43;
                    int n45 = this.sfBandIndex[this.sfreq].l[n43];
                    while (n44 < 21) {
                        for (int n46 = this.sfBandIndex[this.sfreq].l[n44 + 1] - this.sfBandIndex[this.sfreq].l[n44]; n46 > 0; --n46) {
                            this.is_pos[n45] = this.scalefac[1].l[n44];
                            if (this.is_pos[n45] != 7) {
                                if (b3) {
                                    this.i_stereo_k_values(this.is_pos[n45], n2, n45);
                                }
                                else {
                                    this.is_ratio[n45] = LayerIIIDecoder.TAN12[this.is_pos[n45]];
                                }
                            }
                            ++n45;
                        }
                        ++n44;
                    }
                    final int n47 = this.sfBandIndex[this.sfreq].l[20];
                    for (int n48 = 576 - this.sfBandIndex[this.sfreq].l[21]; n48 > 0 && n45 < 576; ++n45, --n48) {
                        this.is_pos[n45] = this.is_pos[n47];
                        if (b3) {
                            this.k[0][n45] = this.k[0][n47];
                            this.k[1][n45] = this.k[1][n47];
                        }
                        else {
                            this.is_ratio[n45] = this.is_ratio[n47];
                        }
                    }
                }
            }
            int n49 = 0;
            for (int n50 = 0; n50 < 32; ++n50) {
                for (int n51 = 0; n51 < 18; ++n51) {
                    if (this.is_pos[n49] == 7) {
                        if (b) {
                            this.lr[0][n50][n51] = (this.ro[0][n50][n51] + this.ro[1][n50][n51]) * 0.70710677f;
                            this.lr[1][n50][n51] = (this.ro[0][n50][n51] - this.ro[1][n50][n51]) * 0.70710677f;
                        }
                        else {
                            this.lr[0][n50][n51] = this.ro[0][n50][n51];
                            this.lr[1][n50][n51] = this.ro[1][n50][n51];
                        }
                    }
                    else if (b2) {
                        if (b3) {
                            this.lr[0][n50][n51] = this.ro[0][n50][n51] * this.k[0][n49];
                            this.lr[1][n50][n51] = this.ro[0][n50][n51] * this.k[1][n49];
                        }
                        else {
                            this.lr[1][n50][n51] = this.ro[0][n50][n51] / (1.0f + this.is_ratio[n49]);
                            this.lr[0][n50][n51] = this.lr[1][n50][n51] * this.is_ratio[n49];
                        }
                    }
                    ++n49;
                }
            }
        }
    }
    
    private void antialias(final int n, final int n2) {
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.block_type == 2 && gr_info_s.mixed_block_flag == 0) {
            return;
        }
        int n3;
        if (gr_info_s.window_switching_flag != 0 && gr_info_s.mixed_block_flag != 0 && gr_info_s.block_type == 2) {
            n3 = 18;
        }
        else {
            n3 = 558;
        }
        for (int i = 0; i < n3; i += 18) {
            for (int j = 0; j < 8; ++j) {
                final int n4 = i + 17 - j;
                final int n5 = i + 18 + j;
                final float n6 = this.out_1d[n4];
                final float n7 = this.out_1d[n5];
                this.out_1d[n4] = n6 * LayerIIIDecoder.cs[j] - n7 * LayerIIIDecoder.ca[j];
                this.out_1d[n5] = n7 * LayerIIIDecoder.cs[j] + n6 * LayerIIIDecoder.ca[j];
            }
        }
    }
    
    private void hybrid(final int n, final int n2) {
        final gr_info_s gr_info_s = this.si.ch[n].gr[n2];
        for (int i = 0; i < 576; i += 18) {
            final int n3 = (gr_info_s.window_switching_flag != 0 && gr_info_s.mixed_block_flag != 0 && i < 36) ? 0 : gr_info_s.block_type;
            final float[] out_1d = this.out_1d;
            for (int j = 0; j < 18; ++j) {
                this.tsOutCopy[j] = out_1d[j + i];
            }
            this.inv_mdct(this.tsOutCopy, this.rawout, n3);
            for (int k = 0; k < 18; ++k) {
                out_1d[k + i] = this.tsOutCopy[k];
            }
            final float[][] prevblck = this.prevblck;
            out_1d[0 + i] = this.rawout[0] + prevblck[n][i + 0];
            prevblck[n][i + 0] = this.rawout[18];
            out_1d[1 + i] = this.rawout[1] + prevblck[n][i + 1];
            prevblck[n][i + 1] = this.rawout[19];
            out_1d[2 + i] = this.rawout[2] + prevblck[n][i + 2];
            prevblck[n][i + 2] = this.rawout[20];
            out_1d[3 + i] = this.rawout[3] + prevblck[n][i + 3];
            prevblck[n][i + 3] = this.rawout[21];
            out_1d[4 + i] = this.rawout[4] + prevblck[n][i + 4];
            prevblck[n][i + 4] = this.rawout[22];
            out_1d[5 + i] = this.rawout[5] + prevblck[n][i + 5];
            prevblck[n][i + 5] = this.rawout[23];
            out_1d[6 + i] = this.rawout[6] + prevblck[n][i + 6];
            prevblck[n][i + 6] = this.rawout[24];
            out_1d[7 + i] = this.rawout[7] + prevblck[n][i + 7];
            prevblck[n][i + 7] = this.rawout[25];
            out_1d[8 + i] = this.rawout[8] + prevblck[n][i + 8];
            prevblck[n][i + 8] = this.rawout[26];
            out_1d[9 + i] = this.rawout[9] + prevblck[n][i + 9];
            prevblck[n][i + 9] = this.rawout[27];
            out_1d[10 + i] = this.rawout[10] + prevblck[n][i + 10];
            prevblck[n][i + 10] = this.rawout[28];
            out_1d[11 + i] = this.rawout[11] + prevblck[n][i + 11];
            prevblck[n][i + 11] = this.rawout[29];
            out_1d[12 + i] = this.rawout[12] + prevblck[n][i + 12];
            prevblck[n][i + 12] = this.rawout[30];
            out_1d[13 + i] = this.rawout[13] + prevblck[n][i + 13];
            prevblck[n][i + 13] = this.rawout[31];
            out_1d[14 + i] = this.rawout[14] + prevblck[n][i + 14];
            prevblck[n][i + 14] = this.rawout[32];
            out_1d[15 + i] = this.rawout[15] + prevblck[n][i + 15];
            prevblck[n][i + 15] = this.rawout[33];
            out_1d[16 + i] = this.rawout[16] + prevblck[n][i + 16];
            prevblck[n][i + 16] = this.rawout[34];
            out_1d[17 + i] = this.rawout[17] + prevblck[n][i + 17];
            prevblck[n][i + 17] = this.rawout[35];
        }
    }
    
    private void do_downmix() {
        for (int i = 0; i < 18; ++i) {
            for (int j = 0; j < 18; j += 3) {
                this.lr[0][i][j] = (this.lr[0][i][j] + this.lr[1][i][j]) * 0.5f;
                this.lr[0][i][j + 1] = (this.lr[0][i][j + 1] + this.lr[1][i][j + 1]) * 0.5f;
                this.lr[0][i][j + 2] = (this.lr[0][i][j + 2] + this.lr[1][i][j + 2]) * 0.5f;
            }
        }
    }
    
    public void inv_mdct(final float[] array, final float[] array2, final int n) {
        if (n == 2) {
            array2[1] = (array2[0] = 0.0f);
            array2[3] = (array2[2] = 0.0f);
            array2[5] = (array2[4] = 0.0f);
            array2[7] = (array2[6] = 0.0f);
            array2[9] = (array2[8] = 0.0f);
            array2[11] = (array2[10] = 0.0f);
            array2[13] = (array2[12] = 0.0f);
            array2[15] = (array2[14] = 0.0f);
            array2[17] = (array2[16] = 0.0f);
            array2[19] = (array2[18] = 0.0f);
            array2[21] = (array2[20] = 0.0f);
            array2[23] = (array2[22] = 0.0f);
            array2[25] = (array2[24] = 0.0f);
            array2[27] = (array2[26] = 0.0f);
            array2[29] = (array2[28] = 0.0f);
            array2[31] = (array2[30] = 0.0f);
            array2[33] = (array2[32] = 0.0f);
            array2[35] = (array2[34] = 0.0f);
            int n2 = 0;
            for (int i = 0; i < 3; ++i) {
                final int n3 = 15 + i;
                array[n3] += array[12 + i];
                final int n4 = 12 + i;
                array[n4] += array[9 + i];
                final int n5 = 9 + i;
                array[n5] += array[6 + i];
                final int n6 = 6 + i;
                array[n6] += array[3 + i];
                final int n7 = 3 + i;
                array[n7] += array[0 + i];
                final int n8 = 15 + i;
                array[n8] += array[9 + i];
                final int n9 = 9 + i;
                array[n9] += array[3 + i];
                final float n10 = array[12 + i] * 0.5f;
                final float n11 = array[6 + i] * 0.8660254f;
                final float n12 = array[0 + i] + n10;
                final float n13 = array[0 + i] - array[12 + i];
                final float n14 = n12 + n11;
                final float n15 = n12 - n11;
                final float n16 = array[15 + i] * 0.5f;
                final float n17 = array[9 + i] * 0.8660254f;
                final float n18 = array[3 + i] + n16;
                final float n19 = array[3 + i] - array[15 + i];
                final float n20 = n18 + n17;
                final float n21 = (n18 - n17) * 1.9318516f;
                final float n22 = n19 * 0.70710677f;
                final float n23 = n20 * 0.5176381f;
                final float n24 = n14;
                final float n25 = n14 + n23;
                final float n26 = n24 - n23;
                final float n27 = n13;
                final float n28 = n13 + n22;
                final float n29 = n27 - n22;
                final float n30 = n15;
                final float n31 = n15 + n21;
                final float n32 = n30 - n21;
                final float n33 = n25 * 0.5043145f;
                final float n34 = n28 * 0.5411961f;
                final float n35 = n31 * 0.6302362f;
                final float n36 = n32 * 0.8213398f;
                final float n37 = n29 * 1.306563f;
                final float n38 = n26 * 3.830649f;
                final float n39 = -n33 * 0.7933533f;
                final float n40 = -n33 * 0.6087614f;
                final float n41 = -n34 * 0.9238795f;
                final float n42 = -n34 * 0.38268343f;
                final float n43 = -n35 * 0.9914449f;
                final float n44 = -n35 * 0.13052619f;
                final float n45 = n36;
                final float n46 = n37 * 0.38268343f;
                final float n47 = n38 * 0.6087614f;
                final float n48 = -n38 * 0.7933533f;
                final float n49 = -n37 * 0.9238795f;
                final float n50 = -n45 * 0.9914449f;
                final float n51 = n45 * 0.13052619f;
                final int n52 = n2 + 6;
                array2[n52] += n51;
                final int n53 = n2 + 7;
                array2[n53] += n46;
                final int n54 = n2 + 8;
                array2[n54] += n47;
                final int n55 = n2 + 9;
                array2[n55] += n48;
                final int n56 = n2 + 10;
                array2[n56] += n49;
                final int n57 = n2 + 11;
                array2[n57] += n50;
                final int n58 = n2 + 12;
                array2[n58] += n43;
                final int n59 = n2 + 13;
                array2[n59] += n41;
                final int n60 = n2 + 14;
                array2[n60] += n39;
                final int n61 = n2 + 15;
                array2[n61] += n40;
                final int n62 = n2 + 16;
                array2[n62] += n42;
                final int n63 = n2 + 17;
                array2[n63] += n44;
                n2 += 6;
            }
        }
        else {
            final int n64 = 17;
            array[n64] += array[16];
            final int n65 = 16;
            array[n65] += array[15];
            final int n66 = 15;
            array[n66] += array[14];
            final int n67 = 14;
            array[n67] += array[13];
            final int n68 = 13;
            array[n68] += array[12];
            final int n69 = 12;
            array[n69] += array[11];
            final int n70 = 11;
            array[n70] += array[10];
            final int n71 = 10;
            array[n71] += array[9];
            final int n72 = 9;
            array[n72] += array[8];
            final int n73 = 8;
            array[n73] += array[7];
            final int n74 = 7;
            array[n74] += array[6];
            final int n75 = 6;
            array[n75] += array[5];
            final int n76 = 5;
            array[n76] += array[4];
            final int n77 = 4;
            array[n77] += array[3];
            final int n78 = 3;
            array[n78] += array[2];
            final int n79 = 2;
            array[n79] += array[1];
            final int n80 = 1;
            array[n80] += array[0];
            final int n81 = 17;
            array[n81] += array[15];
            final int n82 = 15;
            array[n82] += array[13];
            final int n83 = 13;
            array[n83] += array[11];
            final int n84 = 11;
            array[n84] += array[9];
            final int n85 = 9;
            array[n85] += array[7];
            final int n86 = 7;
            array[n86] += array[5];
            final int n87 = 5;
            array[n87] += array[3];
            final int n88 = 3;
            array[n88] += array[1];
            final float n89 = array[0] + array[0];
            final float n90 = n89 + array[12];
            final float n91 = n90 + array[4] * 1.8793852f + array[8] * 1.5320889f + array[16] * 0.34729636f;
            final float n92 = n89 + array[4] - array[8] - array[12] - array[12] - array[16];
            final float n93 = n90 - array[4] * 0.34729636f - array[8] * 1.8793852f + array[16] * 1.5320889f;
            final float n94 = n90 - array[4] * 1.5320889f + array[8] * 0.34729636f - array[16] * 1.8793852f;
            final float n95 = array[0] - array[4] + array[8] - array[12] + array[16];
            final float n96 = array[6] * 1.7320508f;
            final float n97 = array[2] * 1.9696155f + n96 + array[10] * 1.2855753f + array[14] * 0.6840403f;
            final float n98 = (array[2] - array[10] - array[14]) * 1.7320508f;
            final float n99 = array[2] * 1.2855753f - n96 - array[10] * 0.6840403f + array[14] * 1.9696155f;
            final float n100 = array[2] * 0.6840403f - n96 + array[10] * 1.9696155f - array[14] * 1.2855753f;
            final float n101 = array[1] + array[1];
            final float n102 = n101 + array[13];
            final float n103 = n102 + array[5] * 1.8793852f + array[9] * 1.5320889f + array[17] * 0.34729636f;
            final float n104 = n101 + array[5] - array[9] - array[13] - array[13] - array[17];
            final float n105 = n102 - array[5] * 0.34729636f - array[9] * 1.8793852f + array[17] * 1.5320889f;
            final float n106 = n102 - array[5] * 1.5320889f + array[9] * 0.34729636f - array[17] * 1.8793852f;
            final float n107 = (array[1] - array[5] + array[9] - array[13] + array[17]) * 0.70710677f;
            final float n108 = array[7] * 1.7320508f;
            final float n109 = array[3] * 1.9696155f + n108 + array[11] * 1.2855753f + array[15] * 0.6840403f;
            final float n110 = (array[3] - array[11] - array[15]) * 1.7320508f;
            final float n111 = array[3] * 1.2855753f - n108 - array[11] * 0.6840403f + array[15] * 1.9696155f;
            final float n112 = array[3] * 0.6840403f - n108 + array[11] * 1.9696155f - array[15] * 1.2855753f;
            final float n113 = n91 + n97;
            final float n114 = (n103 + n109) * 0.5019099f;
            final float n115 = n113 + n114;
            final float n116 = n113 - n114;
            final float n117 = n92 + n98;
            final float n118 = (n104 + n110) * 0.5176381f;
            final float n119 = n117 + n118;
            final float n120 = n117 - n118;
            final float n121 = n93 + n99;
            final float n122 = (n105 + n111) * 0.55168897f;
            final float n123 = n121 + n122;
            final float n124 = n121 - n122;
            final float n125 = n94 + n100;
            final float n126 = (n106 + n112) * 0.61038727f;
            final float n127 = n125 + n126;
            final float n128 = n125 - n126;
            final float n129 = n95 + n107;
            final float n130 = n95 - n107;
            final float n131 = n94 - n100;
            final float n132 = (n106 - n112) * 0.8717234f;
            final float n133 = n131 + n132;
            final float n134 = n131 - n132;
            final float n135 = n93 - n99;
            final float n136 = (n105 - n111) * 1.1831008f;
            final float n137 = n135 + n136;
            final float n138 = n135 - n136;
            final float n139 = n92 - n98;
            final float n140 = (n104 - n110) * 1.9318516f;
            final float n141 = n139 + n140;
            final float n142 = n139 - n140;
            final float n143 = n91 - n97;
            final float n144 = (n103 - n109) * 5.7368565f;
            final float n145 = n143 + n144;
            final float n146 = n143 - n144;
            final float[] array3 = LayerIIIDecoder.win[n];
            array2[0] = -n146 * array3[0];
            array2[1] = -n142 * array3[1];
            array2[2] = -n138 * array3[2];
            array2[3] = -n134 * array3[3];
            array2[4] = -n130 * array3[4];
            array2[5] = -n128 * array3[5];
            array2[6] = -n124 * array3[6];
            array2[7] = -n120 * array3[7];
            array2[8] = -n116 * array3[8];
            array2[9] = n116 * array3[9];
            array2[10] = n120 * array3[10];
            array2[11] = n124 * array3[11];
            array2[12] = n128 * array3[12];
            array2[13] = n130 * array3[13];
            array2[14] = n134 * array3[14];
            array2[15] = n138 * array3[15];
            array2[16] = n142 * array3[16];
            array2[17] = n146 * array3[17];
            array2[18] = n145 * array3[18];
            array2[19] = n141 * array3[19];
            array2[20] = n137 * array3[20];
            array2[21] = n133 * array3[21];
            array2[22] = n129 * array3[22];
            array2[23] = n127 * array3[23];
            array2[24] = n123 * array3[24];
            array2[25] = n119 * array3[25];
            array2[26] = n115 * array3[26];
            array2[27] = n115 * array3[27];
            array2[28] = n119 * array3[28];
            array2[29] = n123 * array3[29];
            array2[30] = n127 * array3[30];
            array2[31] = n129 * array3[31];
            array2[32] = n133 * array3[32];
            array2[33] = n137 * array3[33];
            array2[34] = n141 * array3[34];
            array2[35] = n145 * array3[35];
        }
    }
    
    private static float[] create_t_43() {
        final float[] array = new float[8192];
        for (int i = 0; i < 8192; ++i) {
            array[i] = (float)Math.pow(i, 1.3333333333333333);
        }
        return array;
    }
    
    static int[] reorder(final int[] array) {
        int n = 0;
        final int[] array2 = new int[576];
        for (int i = 0; i < 13; ++i) {
            final int n2 = array[i];
            final int n3 = array[i + 1];
            for (int j = 0; j < 3; ++j) {
                for (int k = n2; k < n3; ++k) {
                    array2[3 * k + j] = n++;
                }
            }
        }
        return array2;
    }
    
    static {
        slen = new int[][] { { 0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4 }, { 0, 1, 2, 3, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2, 3 } };
        pretab = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 0 };
        two_to_negative_half_pow = new float[] { 1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f, 1.5258789E-5f, 1.0789593E-5f, 7.6293945E-6f, 5.3947965E-6f, 3.8146973E-6f, 2.6973983E-6f, 1.9073486E-6f, 1.3486991E-6f, 9.536743E-7f, 6.7434956E-7f, 4.7683716E-7f, 3.3717478E-7f, 2.3841858E-7f, 1.6858739E-7f, 1.1920929E-7f, 8.4293696E-8f, 5.9604645E-8f, 4.2146848E-8f, 2.9802322E-8f, 2.1073424E-8f, 1.4901161E-8f, 1.0536712E-8f, 7.4505806E-9f, 5.268356E-9f, 3.7252903E-9f, 2.634178E-9f, 1.8626451E-9f, 1.317089E-9f, 9.313226E-10f, 6.585445E-10f, 4.656613E-10f, 3.2927225E-10f };
        t_43 = create_t_43();
        io = new float[][] { { 1.0f, 0.8408964f, 0.70710677f, 0.59460354f, 0.5f, 0.4204482f, 0.35355338f, 0.29730177f, 0.25f, 0.2102241f, 0.17677669f, 0.14865088f, 0.125f, 0.10511205f, 0.088388346f, 0.07432544f, 0.0625f, 0.052556027f, 0.044194173f, 0.03716272f, 0.03125f, 0.026278013f, 0.022097087f, 0.01858136f, 0.015625f, 0.013139007f, 0.011048543f, 0.00929068f, 0.0078125f, 0.0065695033f, 0.0055242716f, 0.00464534f }, { 1.0f, 0.70710677f, 0.5f, 0.35355338f, 0.25f, 0.17677669f, 0.125f, 0.088388346f, 0.0625f, 0.044194173f, 0.03125f, 0.022097087f, 0.015625f, 0.011048543f, 0.0078125f, 0.0055242716f, 0.00390625f, 0.0027621358f, 0.001953125f, 0.0013810679f, 9.765625E-4f, 6.9053395E-4f, 4.8828125E-4f, 3.4526698E-4f, 2.4414062E-4f, 1.7263349E-4f, 1.2207031E-4f, 8.6316744E-5f, 6.1035156E-5f, 4.3158372E-5f, 3.0517578E-5f, 2.1579186E-5f } };
        TAN12 = new float[] { 0.0f, 0.2679492f, 0.57735026f, 1.0f, 1.7320508f, 3.732051f, 9.9999998E10f, -3.732051f, -1.7320508f, -1.0f, -0.57735026f, -0.2679492f, 0.0f, 0.2679492f, 0.57735026f, 1.0f };
        cs = new float[] { 0.8574929f, 0.881742f, 0.94962865f, 0.9833146f, 0.9955178f, 0.9991606f, 0.9998992f, 0.99999315f };
        ca = new float[] { -0.51449573f, -0.47173196f, -0.31337744f, -0.1819132f, -0.09457419f, -0.040965583f, -0.014198569f, -0.0036999746f };
        win = new float[][] { { -0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f }, { -0.016141215f, -0.05360318f, -0.100707136f, -0.16280818f, -0.5f, -0.38388735f, -0.6206114f, -1.1659756f, -3.8720753f, -4.225629f, -1.519529f, -0.97416484f, -0.73744076f, -1.2071068f, -0.5163616f, -0.45426053f, -0.40715656f, -0.3696946f, -0.33908543f, -0.3151181f, -0.29642227f, -0.28184548f, -0.5411961f, -0.2621323f, -0.25387916f, -0.2329629f, -0.19852729f, -0.15233535f, -0.0964964f, -0.03342383f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f }, { -0.0483008f, -0.15715657f, -0.28325045f, -0.42953748f, -1.2071068f, -0.8242648f, -1.1451749f, -1.769529f, -4.5470223f, -3.489053f, -0.7329629f, -0.15076515f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.15076514f, -0.7329629f, -3.489053f, -4.5470223f, -1.769529f, -1.1451749f, -0.8313774f, -1.306563f, -0.54142016f, -0.46528974f, -0.4106699f, -0.3700468f, -0.3387627f, -0.31242222f, -0.28939587f, -0.26880082f, -0.5f, -0.23251417f, -0.21596715f, -0.20004979f, -0.18449493f, -0.16905846f, -0.15350361f, -0.13758625f, -0.12103922f, -0.20710678f, -0.084752575f, -0.06415752f, -0.041131172f, -0.014790705f } };
        nr_of_sfb_block = new int[][][] { { { 6, 5, 5, 5 }, { 9, 9, 9, 9 }, { 6, 9, 9, 9 } }, { { 6, 5, 7, 3 }, { 9, 9, 12, 6 }, { 6, 9, 12, 6 } }, { { 11, 10, 0, 0 }, { 18, 18, 0, 0 }, { 15, 18, 0, 0 } }, { { 7, 7, 7, 0 }, { 12, 12, 12, 0 }, { 6, 15, 12, 0 } }, { { 6, 6, 6, 3 }, { 12, 9, 9, 6 }, { 6, 12, 9, 6 } }, { { 8, 8, 5, 0 }, { 15, 12, 9, 0 }, { 6, 18, 9, 0 } } };
    }
    
    class Sftable
    {
        public int[] l;
        public int[] s;
        
        public Sftable() {
            this.l = new int[5];
            this.s = new int[3];
        }
        
        public Sftable(final int[] l, final int[] s) {
            this.l = l;
            this.s = s;
        }
    }
    
    static class temporaire2
    {
        public int[] l;
        public int[][] s;
        
        public temporaire2() {
            this.l = new int[23];
            this.s = new int[3][13];
        }
    }
    
    static class III_side_info_t
    {
        public int main_data_begin;
        public int private_bits;
        public temporaire[] ch;
        
        public III_side_info_t() {
            this.main_data_begin = 0;
            this.private_bits = 0;
            (this.ch = new temporaire[2])[0] = new temporaire();
            this.ch[1] = new temporaire();
        }
    }
    
    static class temporaire
    {
        public int[] scfsi;
        public gr_info_s[] gr;
        
        public temporaire() {
            this.scfsi = new int[4];
            (this.gr = new gr_info_s[2])[0] = new gr_info_s();
            this.gr[1] = new gr_info_s();
        }
    }
    
    static class gr_info_s
    {
        public int part2_3_length;
        public int big_values;
        public int global_gain;
        public int scalefac_compress;
        public int window_switching_flag;
        public int block_type;
        public int mixed_block_flag;
        public int[] table_select;
        public int[] subblock_gain;
        public int region0_count;
        public int region1_count;
        public int preflag;
        public int scalefac_scale;
        public int count1table_select;
        
        public gr_info_s() {
            this.part2_3_length = 0;
            this.big_values = 0;
            this.global_gain = 0;
            this.scalefac_compress = 0;
            this.window_switching_flag = 0;
            this.block_type = 0;
            this.mixed_block_flag = 0;
            this.region0_count = 0;
            this.region1_count = 0;
            this.preflag = 0;
            this.scalefac_scale = 0;
            this.count1table_select = 0;
            this.table_select = new int[3];
            this.subblock_gain = new int[3];
        }
    }
    
    static class SBI
    {
        public int[] l;
        public int[] s;
        
        public SBI() {
            this.l = new int[23];
            this.s = new int[14];
        }
        
        public SBI(final int[] l, final int[] s) {
            this.l = l;
            this.s = s;
        }
    }
}
