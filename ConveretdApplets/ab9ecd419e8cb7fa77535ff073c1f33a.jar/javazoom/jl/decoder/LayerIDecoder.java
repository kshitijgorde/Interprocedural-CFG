// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

class LayerIDecoder implements FrameDecoder
{
    protected Bitstream stream;
    protected Header header;
    protected SynthesisFilter filter1;
    protected SynthesisFilter filter2;
    protected Obuffer buffer;
    protected int which_channels;
    protected int mode;
    protected int num_subbands;
    protected Subband[] subbands;
    protected Crc16 crc;
    
    public LayerIDecoder() {
        this.crc = null;
        this.crc = new Crc16();
    }
    
    public void create(final Bitstream stream, final Header header, final SynthesisFilter filter1, final SynthesisFilter filter2, final Obuffer buffer, final int which_channels) {
        this.stream = stream;
        this.header = header;
        this.filter1 = filter1;
        this.filter2 = filter2;
        this.buffer = buffer;
        this.which_channels = which_channels;
    }
    
    public void decodeFrame() {
        this.num_subbands = this.header.number_of_subbands();
        this.subbands = new Subband[32];
        this.mode = this.header.mode();
        this.createSubbands();
        this.readAllocation();
        this.readScaleFactorSelection();
        if (this.crc != null || this.header.checksum_ok()) {
            this.readScaleFactors();
            this.readSampleData();
        }
    }
    
    protected void createSubbands() {
        if (this.mode == 3) {
            for (int i = 0; i < this.num_subbands; ++i) {
                this.subbands[i] = new SubbandLayer1(i);
            }
        }
        else if (this.mode == 1) {
            int j;
            for (j = 0; j < this.header.intensity_stereo_bound(); ++j) {
                this.subbands[j] = new SubbandLayer1Stereo(j);
            }
            while (j < this.num_subbands) {
                this.subbands[j] = new SubbandLayer1IntensityStereo(j);
                ++j;
            }
        }
        else {
            for (int k = 0; k < this.num_subbands; ++k) {
                this.subbands[k] = new SubbandLayer1Stereo(k);
            }
        }
    }
    
    protected void readAllocation() {
        for (int i = 0; i < this.num_subbands; ++i) {
            this.subbands[i].read_allocation(this.stream, this.header, this.crc);
        }
    }
    
    protected void readScaleFactorSelection() {
    }
    
    protected void readScaleFactors() {
        for (int i = 0; i < this.num_subbands; ++i) {
            this.subbands[i].read_scalefactor(this.stream, this.header);
        }
    }
    
    protected void readSampleData() {
        boolean read_sampledata = false;
        boolean put_next_sample = false;
        final int mode = this.header.mode();
        do {
            for (int i = 0; i < this.num_subbands; ++i) {
                read_sampledata = this.subbands[i].read_sampledata(this.stream);
            }
            do {
                for (int j = 0; j < this.num_subbands; ++j) {
                    put_next_sample = this.subbands[j].put_next_sample(this.which_channels, this.filter1, this.filter2);
                }
                this.filter1.calculate_pcm_samples(this.buffer);
                if (this.which_channels == 0 && mode != 3) {
                    this.filter2.calculate_pcm_samples(this.buffer);
                }
            } while (!put_next_sample);
        } while (!read_sampledata);
    }
    
    static class SubbandLayer1Stereo extends SubbandLayer1
    {
        protected int channel2_allocation;
        protected float channel2_scalefactor;
        protected int channel2_samplelength;
        protected float channel2_sample;
        protected float channel2_factor;
        protected float channel2_offset;
        
        public SubbandLayer1Stereo(final int n) {
            super(n);
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            super.allocation = bitstream.get_bits(4);
            this.channel2_allocation = bitstream.get_bits(4);
            if (crc16 != null) {
                crc16.add_bits(super.allocation, 4);
                crc16.add_bits(this.channel2_allocation, 4);
            }
            if (super.allocation != 0) {
                super.samplelength = super.allocation + 1;
                super.factor = SubbandLayer1.table_factor[super.allocation];
                super.offset = SubbandLayer1.table_offset[super.allocation];
            }
            if (this.channel2_allocation != 0) {
                this.channel2_samplelength = this.channel2_allocation + 1;
                this.channel2_factor = SubbandLayer1.table_factor[this.channel2_allocation];
                this.channel2_offset = SubbandLayer1.table_offset[this.channel2_allocation];
            }
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            if (super.allocation != 0) {
                super.scalefactor = Subband.scalefactors[bitstream.get_bits(6)];
            }
            if (this.channel2_allocation != 0) {
                this.channel2_scalefactor = Subband.scalefactors[bitstream.get_bits(6)];
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            final boolean read_sampledata = super.read_sampledata(bitstream);
            if (this.channel2_allocation != 0) {
                this.channel2_sample = bitstream.get_bits(this.channel2_samplelength);
            }
            return read_sampledata;
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            super.put_next_sample(n, synthesisFilter, synthesisFilter2);
            if (this.channel2_allocation != 0 && n != 1) {
                final float n2 = (this.channel2_sample * this.channel2_factor + this.channel2_offset) * this.channel2_scalefactor;
                if (n == 0) {
                    synthesisFilter2.input_sample(n2, super.subbandnumber);
                }
                else {
                    synthesisFilter.input_sample(n2, super.subbandnumber);
                }
            }
            return true;
        }
    }
    
    static class SubbandLayer1 extends Subband
    {
        public static final float[] table_factor;
        public static final float[] table_offset;
        protected int subbandnumber;
        protected int samplenumber;
        protected int allocation;
        protected float scalefactor;
        protected int samplelength;
        protected float sample;
        protected float factor;
        protected float offset;
        
        public SubbandLayer1(final int subbandnumber) {
            this.subbandnumber = subbandnumber;
            this.samplenumber = 0;
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            final int get_bits = bitstream.get_bits(4);
            this.allocation = get_bits;
            if (get_bits == 15) {}
            if (crc16 != null) {
                crc16.add_bits(this.allocation, 4);
            }
            if (this.allocation != 0) {
                this.samplelength = this.allocation + 1;
                this.factor = SubbandLayer1.table_factor[this.allocation];
                this.offset = SubbandLayer1.table_offset[this.allocation];
            }
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            if (this.allocation != 0) {
                this.scalefactor = Subband.scalefactors[bitstream.get_bits(6)];
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            if (this.allocation != 0) {
                this.sample = bitstream.get_bits(this.samplelength);
            }
            if (++this.samplenumber == 12) {
                this.samplenumber = 0;
                return true;
            }
            return false;
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            if (this.allocation != 0 && n != 2) {
                synthesisFilter.input_sample((this.sample * this.factor + this.offset) * this.scalefactor, this.subbandnumber);
            }
            return true;
        }
        
        static {
            table_factor = new float[] { 0.0f, 0.6666667f, 0.2857143f, 0.13333334f, 0.06451613f, 0.031746034f, 0.015748031f, 0.007843138f, 0.0039138943f, 0.0019550342f, 9.770396E-4f, 4.884005E-4f, 2.4417043E-4f, 1.2207776E-4f, 6.103702E-5f };
            table_offset = new float[] { 0.0f, -0.6666667f, -0.8571429f, -0.9333334f, -0.9677419f, -0.98412704f, -0.992126f, -0.9960785f, -0.99804306f, -0.9990225f, -0.9995115f, -0.99975586f, -0.9998779f, -0.99993896f, -0.9999695f };
        }
    }
    
    abstract static class Subband
    {
        public static final float[] scalefactors;
        
        public abstract void read_allocation(final Bitstream p0, final Header p1, final Crc16 p2);
        
        public abstract void read_scalefactor(final Bitstream p0, final Header p1);
        
        public abstract boolean read_sampledata(final Bitstream p0);
        
        public abstract boolean put_next_sample(final int p0, final SynthesisFilter p1, final SynthesisFilter p2);
        
        static {
            scalefactors = new float[] { 2.0f, 1.587401f, 1.2599211f, 1.0f, 0.7937005f, 0.62996054f, 0.5f, 0.39685026f, 0.31498027f, 0.25f, 0.19842513f, 0.15749013f, 0.125f, 0.099212565f, 0.07874507f, 0.0625f, 0.049606282f, 0.039372534f, 0.03125f, 0.024803141f, 0.019686267f, 0.015625f, 0.012401571f, 0.009843133f, 0.0078125f, 0.0062007853f, 0.0049215667f, 0.00390625f, 0.0031003926f, 0.0024607833f, 0.001953125f, 0.0015501963f, 0.0012303917f, 9.765625E-4f, 7.7509816E-4f, 6.1519584E-4f, 4.8828125E-4f, 3.8754908E-4f, 3.0759792E-4f, 2.4414062E-4f, 1.9377454E-4f, 1.5379896E-4f, 1.2207031E-4f, 9.688727E-5f, 7.689948E-5f, 6.1035156E-5f, 4.8443635E-5f, 3.844974E-5f, 3.0517578E-5f, 2.4221818E-5f, 1.922487E-5f, 1.5258789E-5f, 1.2110909E-5f, 9.612435E-6f, 7.6293945E-6f, 6.0554544E-6f, 4.8062175E-6f, 3.8146973E-6f, 3.0277272E-6f, 2.4031087E-6f, 1.9073486E-6f, 1.5138636E-6f, 1.2015544E-6f, 0.0f };
        }
    }
    
    static class SubbandLayer1IntensityStereo extends SubbandLayer1
    {
        protected float channel2_scalefactor;
        
        public SubbandLayer1IntensityStereo(final int n) {
            super(n);
        }
        
        public void read_allocation(final Bitstream bitstream, final Header header, final Crc16 crc16) {
            super.read_allocation(bitstream, header, crc16);
        }
        
        public void read_scalefactor(final Bitstream bitstream, final Header header) {
            if (super.allocation != 0) {
                super.scalefactor = Subband.scalefactors[bitstream.get_bits(6)];
                this.channel2_scalefactor = Subband.scalefactors[bitstream.get_bits(6)];
            }
        }
        
        public boolean read_sampledata(final Bitstream bitstream) {
            return super.read_sampledata(bitstream);
        }
        
        public boolean put_next_sample(final int n, final SynthesisFilter synthesisFilter, final SynthesisFilter synthesisFilter2) {
            if (super.allocation != 0) {
                super.sample = super.sample * super.factor + super.offset;
                if (n == 0) {
                    final float n2 = super.sample * super.scalefactor;
                    final float n3 = super.sample * this.channel2_scalefactor;
                    synthesisFilter.input_sample(n2, super.subbandnumber);
                    synthesisFilter2.input_sample(n3, super.subbandnumber);
                }
                else if (n == 1) {
                    synthesisFilter.input_sample(super.sample * super.scalefactor, super.subbandnumber);
                }
                else {
                    synthesisFilter.input_sample(super.sample * this.channel2_scalefactor, super.subbandnumber);
                }
            }
            return true;
        }
    }
}
