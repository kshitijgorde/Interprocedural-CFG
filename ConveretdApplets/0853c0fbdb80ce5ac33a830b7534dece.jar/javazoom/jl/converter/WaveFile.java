// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.converter;

public class WaveFile extends RiffFile
{
    public static final int MAX_WAVE_CHANNELS = 2;
    private WaveFormat_Chunk wave_format;
    private RiffChunkHeader pcm_data;
    private long pcm_data_offset;
    private int num_samples;
    
    public WaveFile() {
        this.pcm_data_offset = 0L;
        this.num_samples = 0;
        this.pcm_data = new RiffChunkHeader(this);
        this.wave_format = new WaveFormat_Chunk();
        this.pcm_data.ckID = RiffFile.FourCC("data");
        this.pcm_data.ckSize = 0;
        this.num_samples = 0;
    }
    
    public int OpenForWrite(final String s, final int n, final short n2, final short n3) {
        if (s == null || (n2 != 8 && n2 != 16) || n3 < 1 || n3 > 2) {
            return 4;
        }
        this.wave_format.data.Config(n, n2, n3);
        int n4 = this.Open(s, 1);
        if (n4 == 0) {
            n4 = this.Write(new byte[] { 87, 65, 86, 69 }, 4);
            if (n4 == 0) {
                this.Write(this.wave_format.header, 8);
                this.Write(this.wave_format.data.wFormatTag, 2);
                this.Write(this.wave_format.data.nChannels, 2);
                this.Write(this.wave_format.data.nSamplesPerSec, 4);
                this.Write(this.wave_format.data.nAvgBytesPerSec, 4);
                this.Write(this.wave_format.data.nBlockAlign, 2);
                n4 = this.Write(this.wave_format.data.nBitsPerSample, 2);
                if (n4 == 0) {
                    this.pcm_data_offset = this.CurrentFilePosition();
                    n4 = this.Write(this.pcm_data, 8);
                }
            }
        }
        return n4;
    }
    
    public int WriteData(final short[] array, final int n) {
        final int n2 = n * 2;
        final RiffChunkHeader pcm_data = this.pcm_data;
        pcm_data.ckSize += n2;
        return super.Write(array, n2);
    }
    
    public int Close() {
        int n = 0;
        if (this.fmode == 1) {
            n = this.Backpatch(this.pcm_data_offset, this.pcm_data, 8);
        }
        if (n == 0) {
            n = super.Close();
        }
        return n;
    }
    
    public int SamplingRate() {
        return this.wave_format.data.nSamplesPerSec;
    }
    
    public short BitsPerSample() {
        return this.wave_format.data.nBitsPerSample;
    }
    
    public short NumChannels() {
        return this.wave_format.data.nChannels;
    }
    
    public int NumSamples() {
        return this.num_samples;
    }
    
    public int OpenForWrite(final String s, final WaveFile waveFile) {
        return this.OpenForWrite(s, waveFile.SamplingRate(), waveFile.BitsPerSample(), waveFile.NumChannels());
    }
    
    public long CurrentFilePosition() {
        return super.CurrentFilePosition();
    }
    
    public class WaveFileSample
    {
        public short[] chan;
        
        public WaveFileSample() {
            this.chan = new short[2];
        }
    }
    
    class WaveFormat_Chunk
    {
        public RiffChunkHeader header;
        public WaveFormat_ChunkData data;
        
        public WaveFormat_Chunk() {
            this.header = new RiffChunkHeader(WaveFile.this);
            this.data = new WaveFormat_ChunkData();
            this.header.ckID = RiffFile.FourCC("fmt ");
            this.header.ckSize = 16;
        }
        
        public int VerifyValidity() {
            if (this.header.ckID == RiffFile.FourCC("fmt ") && (this.data.nChannels == 1 || this.data.nChannels == 2) && this.data.nAvgBytesPerSec == this.data.nChannels * this.data.nSamplesPerSec * this.data.nBitsPerSample / 8 && this.data.nBlockAlign == this.data.nChannels * this.data.nBitsPerSample / 8) {
                return 1;
            }
            return 0;
        }
    }
    
    class WaveFormat_ChunkData
    {
        public short wFormatTag;
        public short nChannels;
        public int nSamplesPerSec;
        public int nAvgBytesPerSec;
        public short nBlockAlign;
        public short nBitsPerSample;
        
        public WaveFormat_ChunkData() {
            this.wFormatTag = 0;
            this.nChannels = 0;
            this.nSamplesPerSec = 0;
            this.nAvgBytesPerSec = 0;
            this.nBlockAlign = 0;
            this.nBitsPerSample = 0;
            this.Config(44100, (short)16, this.wFormatTag = 1);
        }
        
        public void Config(final int nSamplesPerSec, final short nBitsPerSample, final short nChannels) {
            this.nSamplesPerSec = nSamplesPerSec;
            this.nChannels = nChannels;
            this.nBitsPerSample = nBitsPerSample;
            this.nAvgBytesPerSec = this.nChannels * this.nSamplesPerSec * this.nBitsPerSample / 8;
            this.nBlockAlign = (short)(this.nChannels * this.nBitsPerSample / 8);
        }
    }
}
