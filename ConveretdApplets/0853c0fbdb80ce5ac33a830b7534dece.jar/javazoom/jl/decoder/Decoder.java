// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public class Decoder implements DecoderErrors
{
    private static final Params DEFAULT_PARAMS;
    private Obuffer output;
    private SynthesisFilter filter1;
    private SynthesisFilter filter2;
    private LayerIIIDecoder l3decoder;
    private LayerIIDecoder l2decoder;
    private LayerIDecoder l1decoder;
    private int outputFrequency;
    private int outputChannels;
    private Equalizer equalizer;
    private Params params;
    private boolean initialized;
    
    public Decoder() {
        this(null);
    }
    
    public Decoder(Params default_PARAMS) {
        this.equalizer = new Equalizer();
        if (default_PARAMS == null) {
            default_PARAMS = Decoder.DEFAULT_PARAMS;
        }
        this.params = default_PARAMS;
        final Equalizer initialEqualizerSettings = this.params.getInitialEqualizerSettings();
        if (initialEqualizerSettings != null) {
            this.equalizer.setFrom(initialEqualizerSettings);
        }
    }
    
    public static Params getDefaultParams() {
        return (Params)Decoder.DEFAULT_PARAMS.clone();
    }
    
    public void setEqualizer(Equalizer pass_THRU_EQ) {
        if (pass_THRU_EQ == null) {
            pass_THRU_EQ = Equalizer.PASS_THRU_EQ;
        }
        this.equalizer.setFrom(pass_THRU_EQ);
        final float[] bandFactors = this.equalizer.getBandFactors();
        if (this.filter1 != null) {
            this.filter1.setEQ(bandFactors);
        }
        if (this.filter2 != null) {
            this.filter2.setEQ(bandFactors);
        }
    }
    
    public Obuffer decodeFrame(final Header header, final Bitstream bitstream) throws DecoderException {
        if (!this.initialized) {
            this.initialize(header);
        }
        final int layer = header.layer();
        this.output.clear_buffer();
        this.retrieveDecoder(header, bitstream, layer).decodeFrame();
        this.output.write_buffer(1);
        return this.output;
    }
    
    public void setOutputBuffer(final Obuffer output) {
        this.output = output;
    }
    
    public int getOutputFrequency() {
        return this.outputFrequency;
    }
    
    public int getOutputChannels() {
        return this.outputChannels;
    }
    
    public int getOutputBlockSize() {
        return 2304;
    }
    
    protected DecoderException newDecoderException(final int n) {
        return new DecoderException(n, null);
    }
    
    protected DecoderException newDecoderException(final int n, final Throwable t) {
        return new DecoderException(n, t);
    }
    
    protected FrameDecoder retrieveDecoder(final Header header, final Bitstream bitstream, final int n) throws DecoderException {
        FrameDecoder frameDecoder = null;
        switch (n) {
            case 3: {
                if (this.l3decoder == null) {
                    this.l3decoder = new LayerIIIDecoder(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l3decoder;
                break;
            }
            case 2: {
                if (this.l2decoder == null) {
                    (this.l2decoder = new LayerIIDecoder()).create(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l2decoder;
                break;
            }
            case 1: {
                if (this.l1decoder == null) {
                    (this.l1decoder = new LayerIDecoder()).create(bitstream, header, this.filter1, this.filter2, this.output, 0);
                }
                frameDecoder = this.l1decoder;
                break;
            }
        }
        if (frameDecoder == null) {
            throw this.newDecoderException(513, null);
        }
        return frameDecoder;
    }
    
    private void initialize(final Header header) throws DecoderException {
        final float n = 32700.0f;
        final int mode = header.mode();
        header.layer();
        final int outputChannels = (mode == 3) ? 1 : 2;
        if (this.output == null) {
            this.output = new SampleBuffer(header.frequency(), outputChannels);
        }
        final float[] bandFactors = this.equalizer.getBandFactors();
        this.filter1 = new SynthesisFilter(0, n, bandFactors);
        if (outputChannels == 2) {
            this.filter2 = new SynthesisFilter(1, n, bandFactors);
        }
        this.outputChannels = outputChannels;
        this.outputFrequency = header.frequency();
        this.initialized = true;
    }
    
    static {
        DEFAULT_PARAMS = new Params();
    }
    
    public static class Params implements Cloneable
    {
        private OutputChannels outputChannels;
        private Equalizer equalizer;
        
        public Params() {
            this.outputChannels = OutputChannels.BOTH;
            this.equalizer = new Equalizer();
        }
        
        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException ex) {
                throw new InternalError(this + ": " + ex);
            }
        }
        
        public void setOutputChannels(final OutputChannels outputChannels) {
            if (outputChannels == null) {
                throw new NullPointerException("out");
            }
            this.outputChannels = outputChannels;
        }
        
        public OutputChannels getOutputChannels() {
            return this.outputChannels;
        }
        
        public Equalizer getInitialEqualizerSettings() {
            return this.equalizer;
        }
    }
}
