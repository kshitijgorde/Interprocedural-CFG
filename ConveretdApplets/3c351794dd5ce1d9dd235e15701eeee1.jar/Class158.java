import java.awt.Component;
import com.ms.com.ComFailException;
import com.ms.directX.DirectSoundBuffer;
import com.ms.directX.DSBufferDesc;
import com.ms.directX.DirectSound;
import com.ms.directX.WaveFormatEx;
import com.ms.directX.DSCursors;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Class158 implements Interface11
{
    private int anInt3361;
    private DSCursors[] aDSCursorsArray3362;
    private int anInt3363;
    private int[] anIntArray3364;
    private WaveFormatEx aWaveFormatEx3365;
    private int[] anIntArray3366;
    private int[] anIntArray3367;
    private DirectSound aDirectSound3368;
    private int anInt3369;
    private DSBufferDesc[] aDSBufferDescArray3370;
    private DirectSoundBuffer[] aDirectSoundBufferArray3371;
    private boolean[] aBooleanArray3372;
    private byte[][] aByteArrayArray3373;
    
    @Override
    public final void method32(final int n, final boolean b, final int n2) throws Exception {
        try {
            if (~this.anInt3363 == -1 || this.aDirectSoundBufferArray3371[n2] != null) {
                throw new IllegalStateException();
            }
            final int bufferBytes = 65536 * this.anInt3369;
            if (this.aByteArrayArray3373[n2] == null || bufferBytes != this.aByteArrayArray3373[n2].length) {
                this.aByteArrayArray3373[n2] = new byte[bufferBytes];
                this.aDSBufferDescArray3370[n2].bufferBytes = bufferBytes;
            }
            this.aDirectSoundBufferArray3371[n2] = this.aDirectSound3368.createSoundBuffer(this.aDSBufferDescArray3370[n2], this.aWaveFormatEx3365);
            this.aBooleanArray3372[n2] = b;
            this.anIntArray3364[n2] = 0;
            this.anIntArray3366[n2] = n;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method31(final int n, final int n2) {
        try {
            if (n < 84) {
                this.method34((byte)(-82), -40);
            }
            if (this.aDirectSoundBufferArray3371[n2] != null) {
                try {
                    this.aDirectSoundBufferArray3371[n2].stop();
                }
                catch (ComFailException ex2) {}
                this.aDirectSoundBufferArray3371[n2] = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final int method34(final byte b, final int n) {
        try {
            if (!this.aBooleanArray3372[n]) {
                return 0;
            }
            this.aDirectSoundBufferArray3371[n].getCurrentPosition(this.aDSCursorsArray3362[n]);
            final int n2 = this.aDSCursorsArray3362[n].write / this.anInt3369;
            int n3 = this.anIntArray3364[n] - n2 & 0xFFFF;
            if (this.anIntArray3366[n] < n3) {
                for (int i = -this.anIntArray3364[n] + n2 & 0xFFFF; i > 0; i -= 256) {
                    this.method33(n, this.anIntArray3367);
                }
                n3 = (-n2 + this.anIntArray3364[n] & 0xFFFF);
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method33(final int n, final int[] array) {
        final int length = array.length;
        if (length != 256 * this.anInt3361) {
            throw new IllegalArgumentException();
        }
        final int n2 = this.anIntArray3364[n] * this.anInt3369;
        for (int i = 0; i < length; ++i) {
            int n3 = array[i];
            if ((n3 + 8388608 & 0xFF000000) != 0x0) {
                n3 = (0x7FFFFF ^ n3 >> 31);
            }
            this.aByteArrayArray3373[n][n2 + i * 2] = (byte)(n3 >> 8);
            this.aByteArrayArray3373[n][n2 + i * 2 + 1] = (byte)(n3 >> 16);
        }
        this.aDirectSoundBufferArray3371[n].writeBuffer(n2, length * 2, this.aByteArrayArray3373[n], 0);
        this.anIntArray3364[n] = (this.anIntArray3364[n] + length / this.anInt3361 & 0xFFFF);
        if (!this.aBooleanArray3372[n]) {
            this.aDirectSoundBufferArray3371[n].play(1);
            this.aBooleanArray3372[n] = true;
        }
    }
    
    @Override
    public final void method29(final boolean b, final int n, final Component component, final int n2) throws Exception {
        try {
            if (this.anInt3363 == 0) {
                if (n2 < 8000 || n2 > 48000) {
                    throw new IllegalArgumentException();
                }
                this.anInt3369 = (b ? 4 : 2);
                this.anInt3361 = (b ? 2 : 1);
                this.anIntArray3367 = new int[this.anInt3361 * 256];
                this.aDirectSound3368.initialize(null);
                this.aDirectSound3368.setCooperativeLevel(component, 2);
                for (int i = 0; i < 2; ++i) {
                    this.aDSBufferDescArray3370[i].flags = 16384;
                }
                this.aWaveFormatEx3365.bitsPerSample = 16;
                this.aWaveFormatEx3365.channels = this.anInt3361;
                this.aWaveFormatEx3365.formatTag = 1;
                this.aWaveFormatEx3365.avgBytesPerSec = n2 * this.anInt3369;
                this.aWaveFormatEx3365.blockAlign = this.anInt3369;
                this.aWaveFormatEx3365.samplesPerSec = n2;
                this.anInt3363 = n2;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method30(final int n, final byte b) {
        try {
            try {
                this.aDirectSoundBufferArray3371[n].stop();
            }
            catch (ComFailException ex) {
                ex.printStackTrace();
            }
            this.aBooleanArray3372[n] = false;
            this.aDirectSoundBufferArray3371[n].setCurrentPosition(0);
            this.anIntArray3364[n] = 0;
            if (b >= -117) {
                this.aDirectSound3368 = null;
            }
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
    }
    
    public Class158() throws Exception {
        this.aDSCursorsArray3362 = new DSCursors[2];
        this.anIntArray3366 = new int[2];
        this.aDirectSoundBufferArray3371 = new DirectSoundBuffer[2];
        this.aBooleanArray3372 = new boolean[2];
        this.aByteArrayArray3373 = new byte[2][];
        this.aDSBufferDescArray3370 = new DSBufferDesc[2];
        this.anIntArray3364 = new int[2];
        try {
            this.aDirectSound3368 = new DirectSound();
            this.aWaveFormatEx3365 = new WaveFormatEx();
            for (int n = 0; ~n > -3; ++n) {
                this.aDSBufferDescArray3370[n] = new DSBufferDesc();
            }
            for (int i = 0; i < 2; ++i) {
                this.aDSCursorsArray3362[i] = new DSCursors();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
