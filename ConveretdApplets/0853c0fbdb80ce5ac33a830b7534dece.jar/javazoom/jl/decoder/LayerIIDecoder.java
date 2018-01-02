// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

class LayerIIDecoder extends LayerIDecoder implements FrameDecoder
{
    protected void createSubbands() {
        if (this.mode == 3) {
            for (int i = 0; i < this.num_subbands; ++i) {
                this.subbands[i] = new SubbandLayer2(i);
            }
        }
        else if (this.mode == 1) {
            int j;
            for (j = 0; j < this.header.intensity_stereo_bound(); ++j) {
                this.subbands[j] = new SubbandLayer2Stereo(j);
            }
            while (j < this.num_subbands) {
                this.subbands[j] = new SubbandLayer2IntensityStereo(j);
                ++j;
            }
        }
        else {
            for (int k = 0; k < this.num_subbands; ++k) {
                this.subbands[k] = new SubbandLayer2Stereo(k);
            }
        }
    }
    
    protected void readScaleFactorSelection() {
        for (int i = 0; i < this.num_subbands; ++i) {
            ((SubbandLayer2)this.subbands[i]).read_scalefactor_selection(this.stream, this.crc);
        }
    }
    
    static class SubbandLayer2Stereo extends SubbandLayer2
    {
        protected int channel2_allocation;
        protected int channel2_scfsi;
        protected float channel2_scalefactor1;
        protected float channel2_scalefactor2;
        protected float channel2_scalefactor3;
        protected int[] channel2_codelength;
        protected float[] channel2_factor;
        protected float[] channel2_samples;
        protected float[] channel2_c;
        protected float[] channel2_d;
        
        public SubbandLayer2Stereo(final int n) {
            super(n);
            this.channel2_codelength = new int[] { 0 };
            this.channel2_factor = new float[] { 0.0f };
            this.channel2_c = new float[] { 0.0f };
            this.channel2_d = new float[] { 0.0f };
            this.channel2_samples = new float[3];
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            final int get_allocationlength = this.get_allocationlength(header);
            this.allocation = bitstream.get_bits(get_allocationlength);
            this.channel2_allocation = bitstream.get_bits(get_allocationlength);
            if (crc16 != null) {
                crc16.add_bits(this.allocation, get_allocationlength);
                crc16.add_bits(this.channel2_allocation, get_allocationlength);
            }
        }
        
        public void read_scalefactor_selection(final Bitstream bitstream, final Crc16 crc16) {
            if (this.allocation != 0) {
                this.scfsi = bitstream.get_bits(2);
                if (crc16 != null) {
                    crc16.add_bits(this.scfsi, 2);
                }
            }
            if (this.channel2_allocation != 0) {
                this.channel2_scfsi = bitstream.get_bits(2);
                if (crc16 != null) {
                    crc16.add_bits(this.channel2_scfsi, 2);
                }
            }
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            super.read_scalefactor(bitstream, header);
            if (this.channel2_allocation != 0) {
                switch (this.channel2_scfsi) {
                    case 0: {
                        this.channel2_scalefactor1 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor2 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 1: {
                        final float n = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor2 = n;
                        this.channel2_scalefactor1 = n;
                        this.channel2_scalefactor3 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 2: {
                        final float channel2_scalefactor1 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = channel2_scalefactor1;
                        this.channel2_scalefactor2 = channel2_scalefactor1;
                        this.channel2_scalefactor1 = channel2_scalefactor1;
                        break;
                    }
                    case 3: {
                        this.channel2_scalefactor1 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        final float n2 = SubbandLayer2Stereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = n2;
                        this.channel2_scalefactor2 = n2;
                        break;
                    }
                }
                this.prepare_sample_reading(header, this.channel2_allocation, 1, this.channel2_factor, this.channel2_codelength, this.channel2_c, this.channel2_d);
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            final boolean read_sampledata = super.read_sampledata(bitstream);
            if (this.channel2_allocation != 0) {
                if (this.groupingtable[1] != null) {
                    final int get_bits = bitstream.get_bits(this.channel2_codelength[0]);
                    final int n = get_bits + (get_bits << 1);
                    final float[] channel2_samples = this.channel2_samples;
                    final float[] array = this.groupingtable[1];
                    int n2 = 0;
                    int n3 = n;
                    channel2_samples[n2] = array[n3];
                    ++n3;
                    ++n2;
                    channel2_samples[n2] = array[n3];
                    ++n3;
                    ++n2;
                    channel2_samples[n2] = array[n3];
                }
                else {
                    this.channel2_samples[0] = (float)(bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
                    this.channel2_samples[1] = (float)(bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
                    this.channel2_samples[2] = (float)(bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
                }
            }
            return read_sampledata;
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            final boolean put_next_sample = super.put_next_sample(n, synthesisFilter, synthesisFilter2);
            if (this.channel2_allocation != 0 && n != 1) {
                float n2 = this.channel2_samples[this.samplenumber - 1];
                if (this.groupingtable[1] == null) {
                    n2 = (n2 + this.channel2_d[0]) * this.channel2_c[0];
                }
                float n3;
                if (this.groupnumber <= 4) {
                    n3 = n2 * this.channel2_scalefactor1;
                }
                else if (this.groupnumber <= 8) {
                    n3 = n2 * this.channel2_scalefactor2;
                }
                else {
                    n3 = n2 * this.channel2_scalefactor3;
                }
                if (n == 0) {
                    synthesisFilter2.input_sample(n3, this.subbandnumber);
                }
                else {
                    synthesisFilter.input_sample(n3, this.subbandnumber);
                }
            }
            return put_next_sample;
        }
    }
    
    static class SubbandLayer2 extends Subband
    {
        public static final float[] grouping_5bits;
        public static final float[] grouping_7bits;
        public static final float[] grouping_10bits;
        public static final int[] table_ab1_codelength;
        public static final float[][] table_ab1_groupingtables;
        public static final float[] table_ab1_factor;
        public static final float[] table_ab1_c;
        public static final float[] table_ab1_d;
        public static final float[][] table_ab234_groupingtables;
        public static final int[] table_ab2_codelength;
        public static final float[] table_ab2_factor;
        public static final float[] table_ab2_c;
        public static final float[] table_ab2_d;
        public static final int[] table_ab3_codelength;
        public static final float[] table_ab3_factor;
        public static final float[] table_ab3_c;
        public static final float[] table_ab3_d;
        public static final int[] table_ab4_codelength;
        public static final float[] table_ab4_factor;
        public static final float[] table_ab4_c;
        public static final float[] table_ab4_d;
        public static final int[] table_cd_codelength;
        public static final float[][] table_cd_groupingtables;
        public static final float[] table_cd_factor;
        public static final float[] table_cd_c;
        public static final float[] table_cd_d;
        protected int subbandnumber;
        protected int allocation;
        protected int scfsi;
        protected float scalefactor1;
        protected float scalefactor2;
        protected float scalefactor3;
        protected int[] codelength;
        protected float[][] groupingtable;
        protected float[] factor;
        protected int groupnumber;
        protected int samplenumber;
        protected float[] samples;
        protected float[] c;
        protected float[] d;
        
        public SubbandLayer2(final int subbandnumber) {
            this.codelength = new int[] { 0 };
            this.groupingtable = new float[2][];
            this.factor = new float[] { 0.0f };
            this.samples = new float[3];
            this.c = new float[] { 0.0f };
            this.d = new float[] { 0.0f };
            this.subbandnumber = subbandnumber;
            final boolean b = false;
            this.samplenumber = (b ? 1 : 0);
            this.groupnumber = (b ? 1 : 0);
        }
        
        protected int get_allocationlength(final Header header) {
            if (header.version() == 1) {
                int bitrate_index = header.bitrate_index();
                if (header.mode() != 3) {
                    if (bitrate_index == 4) {
                        bitrate_index = 1;
                    }
                    else {
                        bitrate_index -= 4;
                    }
                }
                if (bitrate_index == 1 || bitrate_index == 2) {
                    if (this.subbandnumber <= 1) {
                        return 4;
                    }
                    return 3;
                }
                else {
                    if (this.subbandnumber <= 10) {
                        return 4;
                    }
                    if (this.subbandnumber <= 22) {
                        return 3;
                    }
                    return 2;
                }
            }
            else {
                if (this.subbandnumber <= 3) {
                    return 4;
                }
                if (this.subbandnumber <= 10) {
                    return 3;
                }
                return 2;
            }
        }
        
        protected void prepare_sample_reading(final Header header, final int n, final int n2, final float[] array, final int[] array2, final float[] array3, final float[] array4) {
            int bitrate_index = header.bitrate_index();
            if (header.mode() != 3) {
                if (bitrate_index == 4) {
                    bitrate_index = 1;
                }
                else {
                    bitrate_index -= 4;
                }
            }
            if (bitrate_index == 1 || bitrate_index == 2) {
                this.groupingtable[n2] = SubbandLayer2.table_cd_groupingtables[n];
                array[0] = SubbandLayer2.table_cd_factor[n];
                array2[0] = SubbandLayer2.table_cd_codelength[n];
                array3[0] = SubbandLayer2.table_cd_c[n];
                array4[0] = SubbandLayer2.table_cd_d[n];
            }
            else if (this.subbandnumber <= 2) {
                this.groupingtable[n2] = SubbandLayer2.table_ab1_groupingtables[n];
                array[0] = SubbandLayer2.table_ab1_factor[n];
                array2[0] = SubbandLayer2.table_ab1_codelength[n];
                array3[0] = SubbandLayer2.table_ab1_c[n];
                array4[0] = SubbandLayer2.table_ab1_d[n];
            }
            else {
                this.groupingtable[n2] = SubbandLayer2.table_ab234_groupingtables[n];
                if (this.subbandnumber <= 10) {
                    array[0] = SubbandLayer2.table_ab2_factor[n];
                    array2[0] = SubbandLayer2.table_ab2_codelength[n];
                    array3[0] = SubbandLayer2.table_ab2_c[n];
                    array4[0] = SubbandLayer2.table_ab2_d[n];
                }
                else if (this.subbandnumber <= 22) {
                    array[0] = SubbandLayer2.table_ab3_factor[n];
                    array2[0] = SubbandLayer2.table_ab3_codelength[n];
                    array3[0] = SubbandLayer2.table_ab3_c[n];
                    array4[0] = SubbandLayer2.table_ab3_d[n];
                }
                else {
                    array[0] = SubbandLayer2.table_ab4_factor[n];
                    array2[0] = SubbandLayer2.table_ab4_codelength[n];
                    array3[0] = SubbandLayer2.table_ab4_c[n];
                    array4[0] = SubbandLayer2.table_ab4_d[n];
                }
            }
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            final int get_allocationlength = this.get_allocationlength(header);
            this.allocation = bitstream.get_bits(get_allocationlength);
            if (crc16 != null) {
                crc16.add_bits(this.allocation, get_allocationlength);
            }
        }
        
        public void read_scalefactor_selection(final Bitstream bitstream, final Crc16 crc16) {
            if (this.allocation != 0) {
                this.scfsi = bitstream.get_bits(2);
                if (crc16 != null) {
                    crc16.add_bits(this.scfsi, 2);
                }
            }
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            if (this.allocation != 0) {
                switch (this.scfsi) {
                    case 0: {
                        this.scalefactor1 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        this.scalefactor2 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        this.scalefactor3 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 1: {
                        final float n = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        this.scalefactor2 = n;
                        this.scalefactor1 = n;
                        this.scalefactor3 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 2: {
                        final float scalefactor1 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        this.scalefactor3 = scalefactor1;
                        this.scalefactor2 = scalefactor1;
                        this.scalefactor1 = scalefactor1;
                        break;
                    }
                    case 3: {
                        this.scalefactor1 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        final float n2 = SubbandLayer2.scalefactors[bitstream.get_bits(6)];
                        this.scalefactor3 = n2;
                        this.scalefactor2 = n2;
                        break;
                    }
                }
                this.prepare_sample_reading(header, this.allocation, 0, this.factor, this.codelength, this.c, this.d);
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            if (this.allocation != 0) {
                if (this.groupingtable[0] != null) {
                    final int get_bits = bitstream.get_bits(this.codelength[0]);
                    final int n = get_bits + (get_bits << 1);
                    final float[] samples = this.samples;
                    final float[] array = this.groupingtable[0];
                    int n2 = 0;
                    int n3 = n;
                    if (n3 > array.length - 3) {
                        n3 = array.length - 3;
                    }
                    samples[n2] = array[n3];
                    ++n3;
                    ++n2;
                    samples[n2] = array[n3];
                    ++n3;
                    ++n2;
                    samples[n2] = array[n3];
                }
                else {
                    this.samples[0] = (float)(bitstream.get_bits(this.codelength[0]) * this.factor[0] - 1.0);
                    this.samples[1] = (float)(bitstream.get_bits(this.codelength[0]) * this.factor[0] - 1.0);
                    this.samples[2] = (float)(bitstream.get_bits(this.codelength[0]) * this.factor[0] - 1.0);
                }
            }
            this.samplenumber = 0;
            return ++this.groupnumber == 12;
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            if (this.allocation != 0 && n != 2) {
                float n2 = this.samples[this.samplenumber];
                if (this.groupingtable[0] == null) {
                    n2 = (n2 + this.d[0]) * this.c[0];
                }
                float n3;
                if (this.groupnumber <= 4) {
                    n3 = n2 * this.scalefactor1;
                }
                else if (this.groupnumber <= 8) {
                    n3 = n2 * this.scalefactor2;
                }
                else {
                    n3 = n2 * this.scalefactor3;
                }
                synthesisFilter.input_sample(n3, this.subbandnumber);
            }
            return ++this.samplenumber == 3;
        }
        
        static {
            grouping_5bits = new float[] { -0.6666667f, -0.6666667f, -0.6666667f, 0.0f, -0.6666667f, -0.6666667f, 0.6666667f, -0.6666667f, -0.6666667f, -0.6666667f, 0.0f, -0.6666667f, 0.0f, 0.0f, -0.6666667f, 0.6666667f, 0.0f, -0.6666667f, -0.6666667f, 0.6666667f, -0.6666667f, 0.0f, 0.6666667f, -0.6666667f, 0.6666667f, 0.6666667f, -0.6666667f, -0.6666667f, -0.6666667f, 0.0f, 0.0f, -0.6666667f, 0.0f, 0.6666667f, -0.6666667f, 0.0f, -0.6666667f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6666667f, 0.0f, 0.0f, -0.6666667f, 0.6666667f, 0.0f, 0.0f, 0.6666667f, 0.0f, 0.6666667f, 0.6666667f, 0.0f, -0.6666667f, -0.6666667f, 0.6666667f, 0.0f, -0.6666667f, 0.6666667f, 0.6666667f, -0.6666667f, 0.6666667f, -0.6666667f, 0.0f, 0.6666667f, 0.0f, 0.0f, 0.6666667f, 0.6666667f, 0.0f, 0.6666667f, -0.6666667f, 0.6666667f, 0.6666667f, 0.0f, 0.6666667f, 0.6666667f, 0.6666667f, 0.6666667f, 0.6666667f };
            grouping_7bits = new float[] { -0.8f, -0.8f, -0.8f, -0.4f, -0.8f, -0.8f, 0.0f, -0.8f, -0.8f, 0.4f, -0.8f, -0.8f, 0.8f, -0.8f, -0.8f, -0.8f, -0.4f, -0.8f, -0.4f, -0.4f, -0.8f, 0.0f, -0.4f, -0.8f, 0.4f, -0.4f, -0.8f, 0.8f, -0.4f, -0.8f, -0.8f, 0.0f, -0.8f, -0.4f, 0.0f, -0.8f, 0.0f, 0.0f, -0.8f, 0.4f, 0.0f, -0.8f, 0.8f, 0.0f, -0.8f, -0.8f, 0.4f, -0.8f, -0.4f, 0.4f, -0.8f, 0.0f, 0.4f, -0.8f, 0.4f, 0.4f, -0.8f, 0.8f, 0.4f, -0.8f, -0.8f, 0.8f, -0.8f, -0.4f, 0.8f, -0.8f, 0.0f, 0.8f, -0.8f, 0.4f, 0.8f, -0.8f, 0.8f, 0.8f, -0.8f, -0.8f, -0.8f, -0.4f, -0.4f, -0.8f, -0.4f, 0.0f, -0.8f, -0.4f, 0.4f, -0.8f, -0.4f, 0.8f, -0.8f, -0.4f, -0.8f, -0.4f, -0.4f, -0.4f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.4f, -0.4f, -0.4f, 0.8f, -0.4f, -0.4f, -0.8f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.4f, 0.0f, -0.4f, 0.8f, 0.0f, -0.4f, -0.8f, 0.4f, -0.4f, -0.4f, 0.4f, -0.4f, 0.0f, 0.4f, -0.4f, 0.4f, 0.4f, -0.4f, 0.8f, 0.4f, -0.4f, -0.8f, 0.8f, -0.4f, -0.4f, 0.8f, -0.4f, 0.0f, 0.8f, -0.4f, 0.4f, 0.8f, -0.4f, 0.8f, 0.8f, -0.4f, -0.8f, -0.8f, 0.0f, -0.4f, -0.8f, 0.0f, 0.0f, -0.8f, 0.0f, 0.4f, -0.8f, 0.0f, 0.8f, -0.8f, 0.0f, -0.8f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.4f, -0.4f, 0.0f, 0.8f, -0.4f, 0.0f, -0.8f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.8f, 0.0f, 0.0f, -0.8f, 0.4f, 0.0f, -0.4f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.4f, 0.4f, 0.0f, 0.8f, 0.4f, 0.0f, -0.8f, 0.8f, 0.0f, -0.4f, 0.8f, 0.0f, 0.0f, 0.8f, 0.0f, 0.4f, 0.8f, 0.0f, 0.8f, 0.8f, 0.0f, -0.8f, -0.8f, 0.4f, -0.4f, -0.8f, 0.4f, 0.0f, -0.8f, 0.4f, 0.4f, -0.8f, 0.4f, 0.8f, -0.8f, 0.4f, -0.8f, -0.4f, 0.4f, -0.4f, -0.4f, 0.4f, 0.0f, -0.4f, 0.4f, 0.4f, -0.4f, 0.4f, 0.8f, -0.4f, 0.4f, -0.8f, 0.0f, 0.4f, -0.4f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.4f, 0.0f, 0.4f, 0.8f, 0.0f, 0.4f, -0.8f, 0.4f, 0.4f, -0.4f, 0.4f, 0.4f, 0.0f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 0.8f, 0.4f, 0.4f, -0.8f, 0.8f, 0.4f, -0.4f, 0.8f, 0.4f, 0.0f, 0.8f, 0.4f, 0.4f, 0.8f, 0.4f, 0.8f, 0.8f, 0.4f, -0.8f, -0.8f, 0.8f, -0.4f, -0.8f, 0.8f, 0.0f, -0.8f, 0.8f, 0.4f, -0.8f, 0.8f, 0.8f, -0.8f, 0.8f, -0.8f, -0.4f, 0.8f, -0.4f, -0.4f, 0.8f, 0.0f, -0.4f, 0.8f, 0.4f, -0.4f, 0.8f, 0.8f, -0.4f, 0.8f, -0.8f, 0.0f, 0.8f, -0.4f, 0.0f, 0.8f, 0.0f, 0.0f, 0.8f, 0.4f, 0.0f, 0.8f, 0.8f, 0.0f, 0.8f, -0.8f, 0.4f, 0.8f, -0.4f, 0.4f, 0.8f, 0.0f, 0.4f, 0.8f, 0.4f, 0.4f, 0.8f, 0.8f, 0.4f, 0.8f, -0.8f, 0.8f, 0.8f, -0.4f, 0.8f, 0.8f, 0.0f, 0.8f, 0.8f, 0.4f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f };
            grouping_10bits = new float[] { -0.8888889f, -0.8888889f, -0.8888889f, -0.6666667f, -0.8888889f, -0.8888889f, -0.44444445f, -0.8888889f, -0.8888889f, -0.22222222f, -0.8888889f, -0.8888889f, 0.0f, -0.8888889f, -0.8888889f, 0.22222222f, -0.8888889f, -0.8888889f, 0.44444445f, -0.8888889f, -0.8888889f, 0.6666667f, -0.8888889f, -0.8888889f, 0.8888889f, -0.8888889f, -0.8888889f, -0.8888889f, -0.6666667f, -0.8888889f, -0.6666667f, -0.6666667f, -0.8888889f, -0.44444445f, -0.6666667f, -0.8888889f, -0.22222222f, -0.6666667f, -0.8888889f, 0.0f, -0.6666667f, -0.8888889f, 0.22222222f, -0.6666667f, -0.8888889f, 0.44444445f, -0.6666667f, -0.8888889f, 0.6666667f, -0.6666667f, -0.8888889f, 0.8888889f, -0.6666667f, -0.8888889f, -0.8888889f, -0.44444445f, -0.8888889f, -0.6666667f, -0.44444445f, -0.8888889f, -0.44444445f, -0.44444445f, -0.8888889f, -0.22222222f, -0.44444445f, -0.8888889f, 0.0f, -0.44444445f, -0.8888889f, 0.22222222f, -0.44444445f, -0.8888889f, 0.44444445f, -0.44444445f, -0.8888889f, 0.6666667f, -0.44444445f, -0.8888889f, 0.8888889f, -0.44444445f, -0.8888889f, -0.8888889f, -0.22222222f, -0.8888889f, -0.6666667f, -0.22222222f, -0.8888889f, -0.44444445f, -0.22222222f, -0.8888889f, -0.22222222f, -0.22222222f, -0.8888889f, 0.0f, -0.22222222f, -0.8888889f, 0.22222222f, -0.22222222f, -0.8888889f, 0.44444445f, -0.22222222f, -0.8888889f, 0.6666667f, -0.22222222f, -0.8888889f, 0.8888889f, -0.22222222f, -0.8888889f, -0.8888889f, 0.0f, -0.8888889f, -0.6666667f, 0.0f, -0.8888889f, -0.44444445f, 0.0f, -0.8888889f, -0.22222222f, 0.0f, -0.8888889f, 0.0f, 0.0f, -0.8888889f, 0.22222222f, 0.0f, -0.8888889f, 0.44444445f, 0.0f, -0.8888889f, 0.6666667f, 0.0f, -0.8888889f, 0.8888889f, 0.0f, -0.8888889f, -0.8888889f, 0.22222222f, -0.8888889f, -0.6666667f, 0.22222222f, -0.8888889f, -0.44444445f, 0.22222222f, -0.8888889f, -0.22222222f, 0.22222222f, -0.8888889f, 0.0f, 0.22222222f, -0.8888889f, 0.22222222f, 0.22222222f, -0.8888889f, 0.44444445f, 0.22222222f, -0.8888889f, 0.6666667f, 0.22222222f, -0.8888889f, 0.8888889f, 0.22222222f, -0.8888889f, -0.8888889f, 0.44444445f, -0.8888889f, -0.6666667f, 0.44444445f, -0.8888889f, -0.44444445f, 0.44444445f, -0.8888889f, -0.22222222f, 0.44444445f, -0.8888889f, 0.0f, 0.44444445f, -0.8888889f, 0.22222222f, 0.44444445f, -0.8888889f, 0.44444445f, 0.44444445f, -0.8888889f, 0.6666667f, 0.44444445f, -0.8888889f, 0.8888889f, 0.44444445f, -0.8888889f, -0.8888889f, 0.6666667f, -0.8888889f, -0.6666667f, 0.6666667f, -0.8888889f, -0.44444445f, 0.6666667f, -0.8888889f, -0.22222222f, 0.6666667f, -0.8888889f, 0.0f, 0.6666667f, -0.8888889f, 0.22222222f, 0.6666667f, -0.8888889f, 0.44444445f, 0.6666667f, -0.8888889f, 0.6666667f, 0.6666667f, -0.8888889f, 0.8888889f, 0.6666667f, -0.8888889f, -0.8888889f, 0.8888889f, -0.8888889f, -0.6666667f, 0.8888889f, -0.8888889f, -0.44444445f, 0.8888889f, -0.8888889f, -0.22222222f, 0.8888889f, -0.8888889f, 0.0f, 0.8888889f, -0.8888889f, 0.22222222f, 0.8888889f, -0.8888889f, 0.44444445f, 0.8888889f, -0.8888889f, 0.6666667f, 0.8888889f, -0.8888889f, 0.8888889f, 0.8888889f, -0.8888889f, -0.8888889f, -0.8888889f, -0.6666667f, -0.6666667f, -0.8888889f, -0.6666667f, -0.44444445f, -0.8888889f, -0.6666667f, -0.22222222f, -0.8888889f, -0.6666667f, 0.0f, -0.8888889f, -0.6666667f, 0.22222222f, -0.8888889f, -0.6666667f, 0.44444445f, -0.8888889f, -0.6666667f, 0.6666667f, -0.8888889f, -0.6666667f, 0.8888889f, -0.8888889f, -0.6666667f, -0.8888889f, -0.6666667f, -0.6666667f, -0.6666667f, -0.6666667f, -0.6666667f, -0.44444445f, -0.6666667f, -0.6666667f, -0.22222222f, -0.6666667f, -0.6666667f, 0.0f, -0.6666667f, -0.6666667f, 0.22222222f, -0.6666667f, -0.6666667f, 0.44444445f, -0.6666667f, -0.6666667f, 0.6666667f, -0.6666667f, -0.6666667f, 0.8888889f, -0.6666667f, -0.6666667f, -0.8888889f, -0.44444445f, -0.6666667f, -0.6666667f, -0.44444445f, -0.6666667f, -0.44444445f, -0.44444445f, -0.6666667f, -0.22222222f, -0.44444445f, -0.6666667f, 0.0f, -0.44444445f, -0.6666667f, 0.22222222f, -0.44444445f, -0.6666667f, 0.44444445f, -0.44444445f, -0.6666667f, 0.6666667f, -0.44444445f, -0.6666667f, 0.8888889f, -0.44444445f, -0.6666667f, -0.8888889f, -0.22222222f, -0.6666667f, -0.6666667f, -0.22222222f, -0.6666667f, -0.44444445f, -0.22222222f, -0.6666667f, -0.22222222f, -0.22222222f, -0.6666667f, 0.0f, -0.22222222f, -0.6666667f, 0.22222222f, -0.22222222f, -0.6666667f, 0.44444445f, -0.22222222f, -0.6666667f, 0.6666667f, -0.22222222f, -0.6666667f, 0.8888889f, -0.22222222f, -0.6666667f, -0.8888889f, 0.0f, -0.6666667f, -0.6666667f, 0.0f, -0.6666667f, -0.44444445f, 0.0f, -0.6666667f, -0.22222222f, 0.0f, -0.6666667f, 0.0f, 0.0f, -0.6666667f, 0.22222222f, 0.0f, -0.6666667f, 0.44444445f, 0.0f, -0.6666667f, 0.6666667f, 0.0f, -0.6666667f, 0.8888889f, 0.0f, -0.6666667f, -0.8888889f, 0.22222222f, -0.6666667f, -0.6666667f, 0.22222222f, -0.6666667f, -0.44444445f, 0.22222222f, -0.6666667f, -0.22222222f, 0.22222222f, -0.6666667f, 0.0f, 0.22222222f, -0.6666667f, 0.22222222f, 0.22222222f, -0.6666667f, 0.44444445f, 0.22222222f, -0.6666667f, 0.6666667f, 0.22222222f, -0.6666667f, 0.8888889f, 0.22222222f, -0.6666667f, -0.8888889f, 0.44444445f, -0.6666667f, -0.6666667f, 0.44444445f, -0.6666667f, -0.44444445f, 0.44444445f, -0.6666667f, -0.22222222f, 0.44444445f, -0.6666667f, 0.0f, 0.44444445f, -0.6666667f, 0.22222222f, 0.44444445f, -0.6666667f, 0.44444445f, 0.44444445f, -0.6666667f, 0.6666667f, 0.44444445f, -0.6666667f, 0.8888889f, 0.44444445f, -0.6666667f, -0.8888889f, 0.6666667f, -0.6666667f, -0.6666667f, 0.6666667f, -0.6666667f, -0.44444445f, 0.6666667f, -0.6666667f, -0.22222222f, 0.6666667f, -0.6666667f, 0.0f, 0.6666667f, -0.6666667f, 0.22222222f, 0.6666667f, -0.6666667f, 0.44444445f, 0.6666667f, -0.6666667f, 0.6666667f, 0.6666667f, -0.6666667f, 0.8888889f, 0.6666667f, -0.6666667f, -0.8888889f, 0.8888889f, -0.6666667f, -0.6666667f, 0.8888889f, -0.6666667f, -0.44444445f, 0.8888889f, -0.6666667f, -0.22222222f, 0.8888889f, -0.6666667f, 0.0f, 0.8888889f, -0.6666667f, 0.22222222f, 0.8888889f, -0.6666667f, 0.44444445f, 0.8888889f, -0.6666667f, 0.6666667f, 0.8888889f, -0.6666667f, 0.8888889f, 0.8888889f, -0.6666667f, -0.8888889f, -0.8888889f, -0.44444445f, -0.6666667f, -0.8888889f, -0.44444445f, -0.44444445f, -0.8888889f, -0.44444445f, -0.22222222f, -0.8888889f, -0.44444445f, 0.0f, -0.8888889f, -0.44444445f, 0.22222222f, -0.8888889f, -0.44444445f, 0.44444445f, -0.8888889f, -0.44444445f, 0.6666667f, -0.8888889f, -0.44444445f, 0.8888889f, -0.8888889f, -0.44444445f, -0.8888889f, -0.6666667f, -0.44444445f, -0.6666667f, -0.6666667f, -0.44444445f, -0.44444445f, -0.6666667f, -0.44444445f, -0.22222222f, -0.6666667f, -0.44444445f, 0.0f, -0.6666667f, -0.44444445f, 0.22222222f, -0.6666667f, -0.44444445f, 0.44444445f, -0.6666667f, -0.44444445f, 0.6666667f, -0.6666667f, -0.44444445f, 0.8888889f, -0.6666667f, -0.44444445f, -0.8888889f, -0.44444445f, -0.44444445f, -0.6666667f, -0.44444445f, -0.44444445f, -0.44444445f, -0.44444445f, -0.44444445f, -0.22222222f, -0.44444445f, -0.44444445f, 0.0f, -0.44444445f, -0.44444445f, 0.22222222f, -0.44444445f, -0.44444445f, 0.44444445f, -0.44444445f, -0.44444445f, 0.6666667f, -0.44444445f, -0.44444445f, 0.8888889f, -0.44444445f, -0.44444445f, -0.8888889f, -0.22222222f, -0.44444445f, -0.6666667f, -0.22222222f, -0.44444445f, -0.44444445f, -0.22222222f, -0.44444445f, -0.22222222f, -0.22222222f, -0.44444445f, 0.0f, -0.22222222f, -0.44444445f, 0.22222222f, -0.22222222f, -0.44444445f, 0.44444445f, -0.22222222f, -0.44444445f, 0.6666667f, -0.22222222f, -0.44444445f, 0.8888889f, -0.22222222f, -0.44444445f, -0.8888889f, 0.0f, -0.44444445f, -0.6666667f, 0.0f, -0.44444445f, -0.44444445f, 0.0f, -0.44444445f, -0.22222222f, 0.0f, -0.44444445f, 0.0f, 0.0f, -0.44444445f, 0.22222222f, 0.0f, -0.44444445f, 0.44444445f, 0.0f, -0.44444445f, 0.6666667f, 0.0f, -0.44444445f, 0.8888889f, 0.0f, -0.44444445f, -0.8888889f, 0.22222222f, -0.44444445f, -0.6666667f, 0.22222222f, -0.44444445f, -0.44444445f, 0.22222222f, -0.44444445f, -0.22222222f, 0.22222222f, -0.44444445f, 0.0f, 0.22222222f, -0.44444445f, 0.22222222f, 0.22222222f, -0.44444445f, 0.44444445f, 0.22222222f, -0.44444445f, 0.6666667f, 0.22222222f, -0.44444445f, 0.8888889f, 0.22222222f, -0.44444445f, -0.8888889f, 0.44444445f, -0.44444445f, -0.6666667f, 0.44444445f, -0.44444445f, -0.44444445f, 0.44444445f, -0.44444445f, -0.22222222f, 0.44444445f, -0.44444445f, 0.0f, 0.44444445f, -0.44444445f, 0.22222222f, 0.44444445f, -0.44444445f, 0.44444445f, 0.44444445f, -0.44444445f, 0.6666667f, 0.44444445f, -0.44444445f, 0.8888889f, 0.44444445f, -0.44444445f, -0.8888889f, 0.6666667f, -0.44444445f, -0.6666667f, 0.6666667f, -0.44444445f, -0.44444445f, 0.6666667f, -0.44444445f, -0.22222222f, 0.6666667f, -0.44444445f, 0.0f, 0.6666667f, -0.44444445f, 0.22222222f, 0.6666667f, -0.44444445f, 0.44444445f, 0.6666667f, -0.44444445f, 0.6666667f, 0.6666667f, -0.44444445f, 0.8888889f, 0.6666667f, -0.44444445f, -0.8888889f, 0.8888889f, -0.44444445f, -0.6666667f, 0.8888889f, -0.44444445f, -0.44444445f, 0.8888889f, -0.44444445f, -0.22222222f, 0.8888889f, -0.44444445f, 0.0f, 0.8888889f, -0.44444445f, 0.22222222f, 0.8888889f, -0.44444445f, 0.44444445f, 0.8888889f, -0.44444445f, 0.6666667f, 0.8888889f, -0.44444445f, 0.8888889f, 0.8888889f, -0.44444445f, -0.8888889f, -0.8888889f, -0.22222222f, -0.6666667f, -0.8888889f, -0.22222222f, -0.44444445f, -0.8888889f, -0.22222222f, -0.22222222f, -0.8888889f, -0.22222222f, 0.0f, -0.8888889f, -0.22222222f, 0.22222222f, -0.8888889f, -0.22222222f, 0.44444445f, -0.8888889f, -0.22222222f, 0.6666667f, -0.8888889f, -0.22222222f, 0.8888889f, -0.8888889f, -0.22222222f, -0.8888889f, -0.6666667f, -0.22222222f, -0.6666667f, -0.6666667f, -0.22222222f, -0.44444445f, -0.6666667f, -0.22222222f, -0.22222222f, -0.6666667f, -0.22222222f, 0.0f, -0.6666667f, -0.22222222f, 0.22222222f, -0.6666667f, -0.22222222f, 0.44444445f, -0.6666667f, -0.22222222f, 0.6666667f, -0.6666667f, -0.22222222f, 0.8888889f, -0.6666667f, -0.22222222f, -0.8888889f, -0.44444445f, -0.22222222f, -0.6666667f, -0.44444445f, -0.22222222f, -0.44444445f, -0.44444445f, -0.22222222f, -0.22222222f, -0.44444445f, -0.22222222f, 0.0f, -0.44444445f, -0.22222222f, 0.22222222f, -0.44444445f, -0.22222222f, 0.44444445f, -0.44444445f, -0.22222222f, 0.6666667f, -0.44444445f, -0.22222222f, 0.8888889f, -0.44444445f, -0.22222222f, -0.8888889f, -0.22222222f, -0.22222222f, -0.6666667f, -0.22222222f, -0.22222222f, -0.44444445f, -0.22222222f, -0.22222222f, -0.22222222f, -0.22222222f, -0.22222222f, 0.0f, -0.22222222f, -0.22222222f, 0.22222222f, -0.22222222f, -0.22222222f, 0.44444445f, -0.22222222f, -0.22222222f, 0.6666667f, -0.22222222f, -0.22222222f, 0.8888889f, -0.22222222f, -0.22222222f, -0.8888889f, 0.0f, -0.22222222f, -0.6666667f, 0.0f, -0.22222222f, -0.44444445f, 0.0f, -0.22222222f, -0.22222222f, 0.0f, -0.22222222f, 0.0f, 0.0f, -0.22222222f, 0.22222222f, 0.0f, -0.22222222f, 0.44444445f, 0.0f, -0.22222222f, 0.6666667f, 0.0f, -0.22222222f, 0.8888889f, 0.0f, -0.22222222f, -0.8888889f, 0.22222222f, -0.22222222f, -0.6666667f, 0.22222222f, -0.22222222f, -0.44444445f, 0.22222222f, -0.22222222f, -0.22222222f, 0.22222222f, -0.22222222f, 0.0f, 0.22222222f, -0.22222222f, 0.22222222f, 0.22222222f, -0.22222222f, 0.44444445f, 0.22222222f, -0.22222222f, 0.6666667f, 0.22222222f, -0.22222222f, 0.8888889f, 0.22222222f, -0.22222222f, -0.8888889f, 0.44444445f, -0.22222222f, -0.6666667f, 0.44444445f, -0.22222222f, -0.44444445f, 0.44444445f, -0.22222222f, -0.22222222f, 0.44444445f, -0.22222222f, 0.0f, 0.44444445f, -0.22222222f, 0.22222222f, 0.44444445f, -0.22222222f, 0.44444445f, 0.44444445f, -0.22222222f, 0.6666667f, 0.44444445f, -0.22222222f, 0.8888889f, 0.44444445f, -0.22222222f, -0.8888889f, 0.6666667f, -0.22222222f, -0.6666667f, 0.6666667f, -0.22222222f, -0.44444445f, 0.6666667f, -0.22222222f, -0.22222222f, 0.6666667f, -0.22222222f, 0.0f, 0.6666667f, -0.22222222f, 0.22222222f, 0.6666667f, -0.22222222f, 0.44444445f, 0.6666667f, -0.22222222f, 0.6666667f, 0.6666667f, -0.22222222f, 0.8888889f, 0.6666667f, -0.22222222f, -0.8888889f, 0.8888889f, -0.22222222f, -0.6666667f, 0.8888889f, -0.22222222f, -0.44444445f, 0.8888889f, -0.22222222f, -0.22222222f, 0.8888889f, -0.22222222f, 0.0f, 0.8888889f, -0.22222222f, 0.22222222f, 0.8888889f, -0.22222222f, 0.44444445f, 0.8888889f, -0.22222222f, 0.6666667f, 0.8888889f, -0.22222222f, 0.8888889f, 0.8888889f, -0.22222222f, -0.8888889f, -0.8888889f, 0.0f, -0.6666667f, -0.8888889f, 0.0f, -0.44444445f, -0.8888889f, 0.0f, -0.22222222f, -0.8888889f, 0.0f, 0.0f, -0.8888889f, 0.0f, 0.22222222f, -0.8888889f, 0.0f, 0.44444445f, -0.8888889f, 0.0f, 0.6666667f, -0.8888889f, 0.0f, 0.8888889f, -0.8888889f, 0.0f, -0.8888889f, -0.6666667f, 0.0f, -0.6666667f, -0.6666667f, 0.0f, -0.44444445f, -0.6666667f, 0.0f, -0.22222222f, -0.6666667f, 0.0f, 0.0f, -0.6666667f, 0.0f, 0.22222222f, -0.6666667f, 0.0f, 0.44444445f, -0.6666667f, 0.0f, 0.6666667f, -0.6666667f, 0.0f, 0.8888889f, -0.6666667f, 0.0f, -0.8888889f, -0.44444445f, 0.0f, -0.6666667f, -0.44444445f, 0.0f, -0.44444445f, -0.44444445f, 0.0f, -0.22222222f, -0.44444445f, 0.0f, 0.0f, -0.44444445f, 0.0f, 0.22222222f, -0.44444445f, 0.0f, 0.44444445f, -0.44444445f, 0.0f, 0.6666667f, -0.44444445f, 0.0f, 0.8888889f, -0.44444445f, 0.0f, -0.8888889f, -0.22222222f, 0.0f, -0.6666667f, -0.22222222f, 0.0f, -0.44444445f, -0.22222222f, 0.0f, -0.22222222f, -0.22222222f, 0.0f, 0.0f, -0.22222222f, 0.0f, 0.22222222f, -0.22222222f, 0.0f, 0.44444445f, -0.22222222f, 0.0f, 0.6666667f, -0.22222222f, 0.0f, 0.8888889f, -0.22222222f, 0.0f, -0.8888889f, 0.0f, 0.0f, -0.6666667f, 0.0f, 0.0f, -0.44444445f, 0.0f, 0.0f, -0.22222222f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.22222222f, 0.0f, 0.0f, 0.44444445f, 0.0f, 0.0f, 0.6666667f, 0.0f, 0.0f, 0.8888889f, 0.0f, 0.0f, -0.8888889f, 0.22222222f, 0.0f, -0.6666667f, 0.22222222f, 0.0f, -0.44444445f, 0.22222222f, 0.0f, -0.22222222f, 0.22222222f, 0.0f, 0.0f, 0.22222222f, 0.0f, 0.22222222f, 0.22222222f, 0.0f, 0.44444445f, 0.22222222f, 0.0f, 0.6666667f, 0.22222222f, 0.0f, 0.8888889f, 0.22222222f, 0.0f, -0.8888889f, 0.44444445f, 0.0f, -0.6666667f, 0.44444445f, 0.0f, -0.44444445f, 0.44444445f, 0.0f, -0.22222222f, 0.44444445f, 0.0f, 0.0f, 0.44444445f, 0.0f, 0.22222222f, 0.44444445f, 0.0f, 0.44444445f, 0.44444445f, 0.0f, 0.6666667f, 0.44444445f, 0.0f, 0.8888889f, 0.44444445f, 0.0f, -0.8888889f, 0.6666667f, 0.0f, -0.6666667f, 0.6666667f, 0.0f, -0.44444445f, 0.6666667f, 0.0f, -0.22222222f, 0.6666667f, 0.0f, 0.0f, 0.6666667f, 0.0f, 0.22222222f, 0.6666667f, 0.0f, 0.44444445f, 0.6666667f, 0.0f, 0.6666667f, 0.6666667f, 0.0f, 0.8888889f, 0.6666667f, 0.0f, -0.8888889f, 0.8888889f, 0.0f, -0.6666667f, 0.8888889f, 0.0f, -0.44444445f, 0.8888889f, 0.0f, -0.22222222f, 0.8888889f, 0.0f, 0.0f, 0.8888889f, 0.0f, 0.22222222f, 0.8888889f, 0.0f, 0.44444445f, 0.8888889f, 0.0f, 0.6666667f, 0.8888889f, 0.0f, 0.8888889f, 0.8888889f, 0.0f, -0.8888889f, -0.8888889f, 0.22222222f, -0.6666667f, -0.8888889f, 0.22222222f, -0.44444445f, -0.8888889f, 0.22222222f, -0.22222222f, -0.8888889f, 0.22222222f, 0.0f, -0.8888889f, 0.22222222f, 0.22222222f, -0.8888889f, 0.22222222f, 0.44444445f, -0.8888889f, 0.22222222f, 0.6666667f, -0.8888889f, 0.22222222f, 0.8888889f, -0.8888889f, 0.22222222f, -0.8888889f, -0.6666667f, 0.22222222f, -0.6666667f, -0.6666667f, 0.22222222f, -0.44444445f, -0.6666667f, 0.22222222f, -0.22222222f, -0.6666667f, 0.22222222f, 0.0f, -0.6666667f, 0.22222222f, 0.22222222f, -0.6666667f, 0.22222222f, 0.44444445f, -0.6666667f, 0.22222222f, 0.6666667f, -0.6666667f, 0.22222222f, 0.8888889f, -0.6666667f, 0.22222222f, -0.8888889f, -0.44444445f, 0.22222222f, -0.6666667f, -0.44444445f, 0.22222222f, -0.44444445f, -0.44444445f, 0.22222222f, -0.22222222f, -0.44444445f, 0.22222222f, 0.0f, -0.44444445f, 0.22222222f, 0.22222222f, -0.44444445f, 0.22222222f, 0.44444445f, -0.44444445f, 0.22222222f, 0.6666667f, -0.44444445f, 0.22222222f, 0.8888889f, -0.44444445f, 0.22222222f, -0.8888889f, -0.22222222f, 0.22222222f, -0.6666667f, -0.22222222f, 0.22222222f, -0.44444445f, -0.22222222f, 0.22222222f, -0.22222222f, -0.22222222f, 0.22222222f, 0.0f, -0.22222222f, 0.22222222f, 0.22222222f, -0.22222222f, 0.22222222f, 0.44444445f, -0.22222222f, 0.22222222f, 0.6666667f, -0.22222222f, 0.22222222f, 0.8888889f, -0.22222222f, 0.22222222f, -0.8888889f, 0.0f, 0.22222222f, -0.6666667f, 0.0f, 0.22222222f, -0.44444445f, 0.0f, 0.22222222f, -0.22222222f, 0.0f, 0.22222222f, 0.0f, 0.0f, 0.22222222f, 0.22222222f, 0.0f, 0.22222222f, 0.44444445f, 0.0f, 0.22222222f, 0.6666667f, 0.0f, 0.22222222f, 0.8888889f, 0.0f, 0.22222222f, -0.8888889f, 0.22222222f, 0.22222222f, -0.6666667f, 0.22222222f, 0.22222222f, -0.44444445f, 0.22222222f, 0.22222222f, -0.22222222f, 0.22222222f, 0.22222222f, 0.0f, 0.22222222f, 0.22222222f, 0.22222222f, 0.22222222f, 0.22222222f, 0.44444445f, 0.22222222f, 0.22222222f, 0.6666667f, 0.22222222f, 0.22222222f, 0.8888889f, 0.22222222f, 0.22222222f, -0.8888889f, 0.44444445f, 0.22222222f, -0.6666667f, 0.44444445f, 0.22222222f, -0.44444445f, 0.44444445f, 0.22222222f, -0.22222222f, 0.44444445f, 0.22222222f, 0.0f, 0.44444445f, 0.22222222f, 0.22222222f, 0.44444445f, 0.22222222f, 0.44444445f, 0.44444445f, 0.22222222f, 0.6666667f, 0.44444445f, 0.22222222f, 0.8888889f, 0.44444445f, 0.22222222f, -0.8888889f, 0.6666667f, 0.22222222f, -0.6666667f, 0.6666667f, 0.22222222f, -0.44444445f, 0.6666667f, 0.22222222f, -0.22222222f, 0.6666667f, 0.22222222f, 0.0f, 0.6666667f, 0.22222222f, 0.22222222f, 0.6666667f, 0.22222222f, 0.44444445f, 0.6666667f, 0.22222222f, 0.6666667f, 0.6666667f, 0.22222222f, 0.8888889f, 0.6666667f, 0.22222222f, -0.8888889f, 0.8888889f, 0.22222222f, -0.6666667f, 0.8888889f, 0.22222222f, -0.44444445f, 0.8888889f, 0.22222222f, -0.22222222f, 0.8888889f, 0.22222222f, 0.0f, 0.8888889f, 0.22222222f, 0.22222222f, 0.8888889f, 0.22222222f, 0.44444445f, 0.8888889f, 0.22222222f, 0.6666667f, 0.8888889f, 0.22222222f, 0.8888889f, 0.8888889f, 0.22222222f, -0.8888889f, -0.8888889f, 0.44444445f, -0.6666667f, -0.8888889f, 0.44444445f, -0.44444445f, -0.8888889f, 0.44444445f, -0.22222222f, -0.8888889f, 0.44444445f, 0.0f, -0.8888889f, 0.44444445f, 0.22222222f, -0.8888889f, 0.44444445f, 0.44444445f, -0.8888889f, 0.44444445f, 0.6666667f, -0.8888889f, 0.44444445f, 0.8888889f, -0.8888889f, 0.44444445f, -0.8888889f, -0.6666667f, 0.44444445f, -0.6666667f, -0.6666667f, 0.44444445f, -0.44444445f, -0.6666667f, 0.44444445f, -0.22222222f, -0.6666667f, 0.44444445f, 0.0f, -0.6666667f, 0.44444445f, 0.22222222f, -0.6666667f, 0.44444445f, 0.44444445f, -0.6666667f, 0.44444445f, 0.6666667f, -0.6666667f, 0.44444445f, 0.8888889f, -0.6666667f, 0.44444445f, -0.8888889f, -0.44444445f, 0.44444445f, -0.6666667f, -0.44444445f, 0.44444445f, -0.44444445f, -0.44444445f, 0.44444445f, -0.22222222f, -0.44444445f, 0.44444445f, 0.0f, -0.44444445f, 0.44444445f, 0.22222222f, -0.44444445f, 0.44444445f, 0.44444445f, -0.44444445f, 0.44444445f, 0.6666667f, -0.44444445f, 0.44444445f, 0.8888889f, -0.44444445f, 0.44444445f, -0.8888889f, -0.22222222f, 0.44444445f, -0.6666667f, -0.22222222f, 0.44444445f, -0.44444445f, -0.22222222f, 0.44444445f, -0.22222222f, -0.22222222f, 0.44444445f, 0.0f, -0.22222222f, 0.44444445f, 0.22222222f, -0.22222222f, 0.44444445f, 0.44444445f, -0.22222222f, 0.44444445f, 0.6666667f, -0.22222222f, 0.44444445f, 0.8888889f, -0.22222222f, 0.44444445f, -0.8888889f, 0.0f, 0.44444445f, -0.6666667f, 0.0f, 0.44444445f, -0.44444445f, 0.0f, 0.44444445f, -0.22222222f, 0.0f, 0.44444445f, 0.0f, 0.0f, 0.44444445f, 0.22222222f, 0.0f, 0.44444445f, 0.44444445f, 0.0f, 0.44444445f, 0.6666667f, 0.0f, 0.44444445f, 0.8888889f, 0.0f, 0.44444445f, -0.8888889f, 0.22222222f, 0.44444445f, -0.6666667f, 0.22222222f, 0.44444445f, -0.44444445f, 0.22222222f, 0.44444445f, -0.22222222f, 0.22222222f, 0.44444445f, 0.0f, 0.22222222f, 0.44444445f, 0.22222222f, 0.22222222f, 0.44444445f, 0.44444445f, 0.22222222f, 0.44444445f, 0.6666667f, 0.22222222f, 0.44444445f, 0.8888889f, 0.22222222f, 0.44444445f, -0.8888889f, 0.44444445f, 0.44444445f, -0.6666667f, 0.44444445f, 0.44444445f, -0.44444445f, 0.44444445f, 0.44444445f, -0.22222222f, 0.44444445f, 0.44444445f, 0.0f, 0.44444445f, 0.44444445f, 0.22222222f, 0.44444445f, 0.44444445f, 0.44444445f, 0.44444445f, 0.44444445f, 0.6666667f, 0.44444445f, 0.44444445f, 0.8888889f, 0.44444445f, 0.44444445f, -0.8888889f, 0.6666667f, 0.44444445f, -0.6666667f, 0.6666667f, 0.44444445f, -0.44444445f, 0.6666667f, 0.44444445f, -0.22222222f, 0.6666667f, 0.44444445f, 0.0f, 0.6666667f, 0.44444445f, 0.22222222f, 0.6666667f, 0.44444445f, 0.44444445f, 0.6666667f, 0.44444445f, 0.6666667f, 0.6666667f, 0.44444445f, 0.8888889f, 0.6666667f, 0.44444445f, -0.8888889f, 0.8888889f, 0.44444445f, -0.6666667f, 0.8888889f, 0.44444445f, -0.44444445f, 0.8888889f, 0.44444445f, -0.22222222f, 0.8888889f, 0.44444445f, 0.0f, 0.8888889f, 0.44444445f, 0.22222222f, 0.8888889f, 0.44444445f, 0.44444445f, 0.8888889f, 0.44444445f, 0.6666667f, 0.8888889f, 0.44444445f, 0.8888889f, 0.8888889f, 0.44444445f, -0.8888889f, -0.8888889f, 0.6666667f, -0.6666667f, -0.8888889f, 0.6666667f, -0.44444445f, -0.8888889f, 0.6666667f, -0.22222222f, -0.8888889f, 0.6666667f, 0.0f, -0.8888889f, 0.6666667f, 0.22222222f, -0.8888889f, 0.6666667f, 0.44444445f, -0.8888889f, 0.6666667f, 0.6666667f, -0.8888889f, 0.6666667f, 0.8888889f, -0.8888889f, 0.6666667f, -0.8888889f, -0.6666667f, 0.6666667f, -0.6666667f, -0.6666667f, 0.6666667f, -0.44444445f, -0.6666667f, 0.6666667f, -0.22222222f, -0.6666667f, 0.6666667f, 0.0f, -0.6666667f, 0.6666667f, 0.22222222f, -0.6666667f, 0.6666667f, 0.44444445f, -0.6666667f, 0.6666667f, 0.6666667f, -0.6666667f, 0.6666667f, 0.8888889f, -0.6666667f, 0.6666667f, -0.8888889f, -0.44444445f, 0.6666667f, -0.6666667f, -0.44444445f, 0.6666667f, -0.44444445f, -0.44444445f, 0.6666667f, -0.22222222f, -0.44444445f, 0.6666667f, 0.0f, -0.44444445f, 0.6666667f, 0.22222222f, -0.44444445f, 0.6666667f, 0.44444445f, -0.44444445f, 0.6666667f, 0.6666667f, -0.44444445f, 0.6666667f, 0.8888889f, -0.44444445f, 0.6666667f, -0.8888889f, -0.22222222f, 0.6666667f, -0.6666667f, -0.22222222f, 0.6666667f, -0.44444445f, -0.22222222f, 0.6666667f, -0.22222222f, -0.22222222f, 0.6666667f, 0.0f, -0.22222222f, 0.6666667f, 0.22222222f, -0.22222222f, 0.6666667f, 0.44444445f, -0.22222222f, 0.6666667f, 0.6666667f, -0.22222222f, 0.6666667f, 0.8888889f, -0.22222222f, 0.6666667f, -0.8888889f, 0.0f, 0.6666667f, -0.6666667f, 0.0f, 0.6666667f, -0.44444445f, 0.0f, 0.6666667f, -0.22222222f, 0.0f, 0.6666667f, 0.0f, 0.0f, 0.6666667f, 0.22222222f, 0.0f, 0.6666667f, 0.44444445f, 0.0f, 0.6666667f, 0.6666667f, 0.0f, 0.6666667f, 0.8888889f, 0.0f, 0.6666667f, -0.8888889f, 0.22222222f, 0.6666667f, -0.6666667f, 0.22222222f, 0.6666667f, -0.44444445f, 0.22222222f, 0.6666667f, -0.22222222f, 0.22222222f, 0.6666667f, 0.0f, 0.22222222f, 0.6666667f, 0.22222222f, 0.22222222f, 0.6666667f, 0.44444445f, 0.22222222f, 0.6666667f, 0.6666667f, 0.22222222f, 0.6666667f, 0.8888889f, 0.22222222f, 0.6666667f, -0.8888889f, 0.44444445f, 0.6666667f, -0.6666667f, 0.44444445f, 0.6666667f, -0.44444445f, 0.44444445f, 0.6666667f, -0.22222222f, 0.44444445f, 0.6666667f, 0.0f, 0.44444445f, 0.6666667f, 0.22222222f, 0.44444445f, 0.6666667f, 0.44444445f, 0.44444445f, 0.6666667f, 0.6666667f, 0.44444445f, 0.6666667f, 0.8888889f, 0.44444445f, 0.6666667f, -0.8888889f, 0.6666667f, 0.6666667f, -0.6666667f, 0.6666667f, 0.6666667f, -0.44444445f, 0.6666667f, 0.6666667f, -0.22222222f, 0.6666667f, 0.6666667f, 0.0f, 0.6666667f, 0.6666667f, 0.22222222f, 0.6666667f, 0.6666667f, 0.44444445f, 0.6666667f, 0.6666667f, 0.6666667f, 0.6666667f, 0.6666667f, 0.8888889f, 0.6666667f, 0.6666667f, -0.8888889f, 0.8888889f, 0.6666667f, -0.6666667f, 0.8888889f, 0.6666667f, -0.44444445f, 0.8888889f, 0.6666667f, -0.22222222f, 0.8888889f, 0.6666667f, 0.0f, 0.8888889f, 0.6666667f, 0.22222222f, 0.8888889f, 0.6666667f, 0.44444445f, 0.8888889f, 0.6666667f, 0.6666667f, 0.8888889f, 0.6666667f, 0.8888889f, 0.8888889f, 0.6666667f, -0.8888889f, -0.8888889f, 0.8888889f, -0.6666667f, -0.8888889f, 0.8888889f, -0.44444445f, -0.8888889f, 0.8888889f, -0.22222222f, -0.8888889f, 0.8888889f, 0.0f, -0.8888889f, 0.8888889f, 0.22222222f, -0.8888889f, 0.8888889f, 0.44444445f, -0.8888889f, 0.8888889f, 0.6666667f, -0.8888889f, 0.8888889f, 0.8888889f, -0.8888889f, 0.8888889f, -0.8888889f, -0.6666667f, 0.8888889f, -0.6666667f, -0.6666667f, 0.8888889f, -0.44444445f, -0.6666667f, 0.8888889f, -0.22222222f, -0.6666667f, 0.8888889f, 0.0f, -0.6666667f, 0.8888889f, 0.22222222f, -0.6666667f, 0.8888889f, 0.44444445f, -0.6666667f, 0.8888889f, 0.6666667f, -0.6666667f, 0.8888889f, 0.8888889f, -0.6666667f, 0.8888889f, -0.8888889f, -0.44444445f, 0.8888889f, -0.6666667f, -0.44444445f, 0.8888889f, -0.44444445f, -0.44444445f, 0.8888889f, -0.22222222f, -0.44444445f, 0.8888889f, 0.0f, -0.44444445f, 0.8888889f, 0.22222222f, -0.44444445f, 0.8888889f, 0.44444445f, -0.44444445f, 0.8888889f, 0.6666667f, -0.44444445f, 0.8888889f, 0.8888889f, -0.44444445f, 0.8888889f, -0.8888889f, -0.22222222f, 0.8888889f, -0.6666667f, -0.22222222f, 0.8888889f, -0.44444445f, -0.22222222f, 0.8888889f, -0.22222222f, -0.22222222f, 0.8888889f, 0.0f, -0.22222222f, 0.8888889f, 0.22222222f, -0.22222222f, 0.8888889f, 0.44444445f, -0.22222222f, 0.8888889f, 0.6666667f, -0.22222222f, 0.8888889f, 0.8888889f, -0.22222222f, 0.8888889f, -0.8888889f, 0.0f, 0.8888889f, -0.6666667f, 0.0f, 0.8888889f, -0.44444445f, 0.0f, 0.8888889f, -0.22222222f, 0.0f, 0.8888889f, 0.0f, 0.0f, 0.8888889f, 0.22222222f, 0.0f, 0.8888889f, 0.44444445f, 0.0f, 0.8888889f, 0.6666667f, 0.0f, 0.8888889f, 0.8888889f, 0.0f, 0.8888889f, -0.8888889f, 0.22222222f, 0.8888889f, -0.6666667f, 0.22222222f, 0.8888889f, -0.44444445f, 0.22222222f, 0.8888889f, -0.22222222f, 0.22222222f, 0.8888889f, 0.0f, 0.22222222f, 0.8888889f, 0.22222222f, 0.22222222f, 0.8888889f, 0.44444445f, 0.22222222f, 0.8888889f, 0.6666667f, 0.22222222f, 0.8888889f, 0.8888889f, 0.22222222f, 0.8888889f, -0.8888889f, 0.44444445f, 0.8888889f, -0.6666667f, 0.44444445f, 0.8888889f, -0.44444445f, 0.44444445f, 0.8888889f, -0.22222222f, 0.44444445f, 0.8888889f, 0.0f, 0.44444445f, 0.8888889f, 0.22222222f, 0.44444445f, 0.8888889f, 0.44444445f, 0.44444445f, 0.8888889f, 0.6666667f, 0.44444445f, 0.8888889f, 0.8888889f, 0.44444445f, 0.8888889f, -0.8888889f, 0.6666667f, 0.8888889f, -0.6666667f, 0.6666667f, 0.8888889f, -0.44444445f, 0.6666667f, 0.8888889f, -0.22222222f, 0.6666667f, 0.8888889f, 0.0f, 0.6666667f, 0.8888889f, 0.22222222f, 0.6666667f, 0.8888889f, 0.44444445f, 0.6666667f, 0.8888889f, 0.6666667f, 0.6666667f, 0.8888889f, 0.8888889f, 0.6666667f, 0.8888889f, -0.8888889f, 0.8888889f, 0.8888889f, -0.6666667f, 0.8888889f, 0.8888889f, -0.44444445f, 0.8888889f, 0.8888889f, -0.22222222f, 0.8888889f, 0.8888889f, 0.0f, 0.8888889f, 0.8888889f, 0.22222222f, 0.8888889f, 0.8888889f, 0.44444445f, 0.8888889f, 0.8888889f, 0.6666667f, 0.8888889f, 0.8888889f, 0.8888889f, 0.8888889f, 0.8888889f };
            table_ab1_codelength = new int[] { 0, 5, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
            table_ab1_groupingtables = new float[][] { null, SubbandLayer2.grouping_5bits, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
            table_ab1_factor = new float[] { 0.0f, 0.5f, 0.25f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 1.2207031E-4f, 6.1035156E-5f, 3.0517578E-5f };
            table_ab1_c = new float[] { 0.0f, 1.3333334f, 1.1428572f, 1.0666667f, 1.032258f, 1.0158731f, 1.007874f, 1.0039216f, 1.0019569f, 1.0009775f, 1.0004885f, 1.0002443f, 1.0001221f, 1.000061f, 1.0000305f, 1.0000153f };
            table_ab1_d = new float[] { 0.0f, 0.5f, 0.25f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 1.2207031E-4f, 6.103516E-5f, 3.051758E-5f };
            table_ab234_groupingtables = new float[][] { null, SubbandLayer2.grouping_5bits, SubbandLayer2.grouping_7bits, null, SubbandLayer2.grouping_10bits, null, null, null, null, null, null, null, null, null, null, null };
            table_ab2_codelength = new int[] { 0, 5, 7, 3, 10, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16 };
            table_ab2_factor = new float[] { 0.0f, 0.5f, 0.25f, 0.25f, 0.125f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 3.0517578E-5f };
            table_ab2_c = new float[] { 0.0f, 1.3333334f, 1.6f, 1.1428572f, 1.7777778f, 1.0666667f, 1.032258f, 1.0158731f, 1.007874f, 1.0039216f, 1.0019569f, 1.0009775f, 1.0004885f, 1.0002443f, 1.0001221f, 1.0000153f };
            table_ab2_d = new float[] { 0.0f, 0.5f, 0.5f, 0.25f, 0.5f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 3.051758E-5f };
            table_ab3_codelength = new int[] { 0, 5, 7, 3, 10, 4, 5, 16 };
            table_ab3_factor = new float[] { 0.0f, 0.5f, 0.25f, 0.25f, 0.125f, 0.125f, 0.0625f, 3.0517578E-5f };
            table_ab3_c = new float[] { 0.0f, 1.3333334f, 1.6f, 1.1428572f, 1.7777778f, 1.0666667f, 1.032258f, 1.0000153f };
            table_ab3_d = new float[] { 0.0f, 0.5f, 0.5f, 0.25f, 0.5f, 0.125f, 0.0625f, 3.051758E-5f };
            table_ab4_codelength = new int[] { 0, 5, 7, 16 };
            table_ab4_factor = new float[] { 0.0f, 0.5f, 0.25f, 3.0517578E-5f };
            table_ab4_c = new float[] { 0.0f, 1.3333334f, 1.6f, 1.0000153f };
            table_ab4_d = new float[] { 0.0f, 0.5f, 0.5f, 3.051758E-5f };
            table_cd_codelength = new int[] { 0, 5, 7, 10, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
            table_cd_groupingtables = new float[][] { null, SubbandLayer2.grouping_5bits, SubbandLayer2.grouping_7bits, SubbandLayer2.grouping_10bits, null, null, null, null, null, null, null, null, null, null, null, null };
            table_cd_factor = new float[] { 0.0f, 0.5f, 0.25f, 0.125f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 1.2207031E-4f, 6.1035156E-5f };
            table_cd_c = new float[] { 0.0f, 1.3333334f, 1.6f, 1.7777778f, 1.0666667f, 1.032258f, 1.0158731f, 1.007874f, 1.0039216f, 1.0019569f, 1.0009775f, 1.0004885f, 1.0002443f, 1.0001221f, 1.000061f, 1.0000305f };
            table_cd_d = new float[] { 0.0f, 0.5f, 0.5f, 0.5f, 0.125f, 0.0625f, 0.03125f, 0.015625f, 0.0078125f, 0.00390625f, 0.001953125f, 9.765625E-4f, 4.8828125E-4f, 2.4414062E-4f, 1.2207031E-4f, 6.103516E-5f };
        }
    }
    
    static class SubbandLayer2IntensityStereo extends SubbandLayer2
    {
        protected int channel2_scfsi;
        protected float channel2_scalefactor1;
        protected float channel2_scalefactor2;
        protected float channel2_scalefactor3;
        
        public SubbandLayer2IntensityStereo(final int n) {
            super(n);
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            super.read_allocation(bitstream, header, crc16);
        }
        
        public void read_scalefactor_selection(final Bitstream bitstream, final Crc16 crc16) {
            if (this.allocation != 0) {
                this.scfsi = bitstream.get_bits(2);
                this.channel2_scfsi = bitstream.get_bits(2);
                if (crc16 != null) {
                    crc16.add_bits(this.scfsi, 2);
                    crc16.add_bits(this.channel2_scfsi, 2);
                }
            }
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            if (this.allocation != 0) {
                super.read_scalefactor(bitstream, header);
                switch (this.channel2_scfsi) {
                    case 0: {
                        this.channel2_scalefactor1 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor2 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 1: {
                        final float n = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor2 = n;
                        this.channel2_scalefactor1 = n;
                        this.channel2_scalefactor3 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        break;
                    }
                    case 2: {
                        final float channel2_scalefactor1 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = channel2_scalefactor1;
                        this.channel2_scalefactor2 = channel2_scalefactor1;
                        this.channel2_scalefactor1 = channel2_scalefactor1;
                        break;
                    }
                    case 3: {
                        this.channel2_scalefactor1 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        final float n2 = SubbandLayer2IntensityStereo.scalefactors[bitstream.get_bits(6)];
                        this.channel2_scalefactor3 = n2;
                        this.channel2_scalefactor2 = n2;
                        break;
                    }
                }
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            return super.read_sampledata(bitstream);
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            if (this.allocation != 0) {
                float n2 = this.samples[this.samplenumber];
                if (this.groupingtable[0] == null) {
                    n2 = (n2 + this.d[0]) * this.c[0];
                }
                if (n == 0) {
                    final float n3 = n2;
                    float n4;
                    float n5;
                    if (this.groupnumber <= 4) {
                        n4 = n2 * this.scalefactor1;
                        n5 = n3 * this.channel2_scalefactor1;
                    }
                    else if (this.groupnumber <= 8) {
                        n4 = n2 * this.scalefactor2;
                        n5 = n3 * this.channel2_scalefactor2;
                    }
                    else {
                        n4 = n2 * this.scalefactor3;
                        n5 = n3 * this.channel2_scalefactor3;
                    }
                    synthesisFilter.input_sample(n4, this.subbandnumber);
                    synthesisFilter2.input_sample(n5, this.subbandnumber);
                }
                else if (n == 1) {
                    float n6;
                    if (this.groupnumber <= 4) {
                        n6 = n2 * this.scalefactor1;
                    }
                    else if (this.groupnumber <= 8) {
                        n6 = n2 * this.scalefactor2;
                    }
                    else {
                        n6 = n2 * this.scalefactor3;
                    }
                    synthesisFilter.input_sample(n6, this.subbandnumber);
                }
                else {
                    float n7;
                    if (this.groupnumber <= 4) {
                        n7 = n2 * this.channel2_scalefactor1;
                    }
                    else if (this.groupnumber <= 8) {
                        n7 = n2 * this.channel2_scalefactor2;
                    }
                    else {
                        n7 = n2 * this.channel2_scalefactor3;
                    }
                    synthesisFilter.input_sample(n7, this.subbandnumber);
                }
            }
            return ++this.samplenumber == 3;
        }
    }
}
