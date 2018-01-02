// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.io.IOException;
import sun.audio.AudioPlayer;
import java.io.InputStream;
import java.lang.reflect.Method;

public class PSound
{
    public static final int SAMPLING_RATE = 8000;
    static final short BIAS = 132;
    static final int CLIP = 32635;
    static final int[] LINEAR_LUT;
    static final int[] LAW_LUT;
    PApplet parent;
    Method soundEventMethod;
    InputStream stream;
    boolean loop;
    float volume;
    int position;
    int[] data;
    static /* synthetic */ Class class$processing$core$PSound;
    
    public void play() {
        AudioPlayer.player.start(this.stream);
    }
    
    public void loop() {
        this.loop = true;
    }
    
    public void noLoop() {
        this.loop = false;
    }
    
    public void pause() {
        AudioPlayer.player.stop(this.stream);
    }
    
    public void stop() {
        AudioPlayer.player.stop(this.stream);
    }
    
    public float time() {
        return this.position / 8000.0f;
    }
    
    public float duration() {
        return 0.0f;
    }
    
    public void volume(final float volume) {
        this.volume = volume;
    }
    
    protected void error(final String s, final Exception ex) {
        this.parent.die("Error inside PSound." + s + "()", ex);
    }
    
    static byte linear2ulaw(int n) {
        if (n > 32767) {
            n = 32767;
        }
        else if (n < -32768) {
            n = -32768;
        }
        final int n2 = n >> 8 & 0x80;
        if (n2 != 0) {
            n = -n;
        }
        if (n > 32635) {
            n = 32635;
        }
        n += 132;
        final int n3 = PSound.LAW_LUT[n >> 7 & 0xFF];
        byte b = (byte)~(n2 | n3 << 4 | (n >> n3 + 3 & 0xF));
        if (b == 0) {
            b = 2;
        }
        return b;
    }
    
    static int ulaw2linear(int n) {
        n ^= -1;
        final int n2 = n & 0x80;
        final int n3 = n >> 4 & 0x7;
        final int n4 = PSound.LINEAR_LUT[n3] + ((n & 0xF) << n3 + 3);
        return (short)((n2 != 0) ? (-n4) : n4);
    }
    
    static int[] resample(final int[] array, final int n, final int n2) {
        if (n > n2) {
            final float n3 = n / n2;
            final int n4 = (int)(array.length / n3);
            final int[] array2 = new int[n4];
            for (int i = 0; i < n4; ++i) {
                array2[i] = array[(int)(i * n3)];
            }
            return array2;
        }
        if (n < n2) {
            final float n5 = n2 / n;
            final int n6 = (int)(array.length * n5);
            final int[] array3 = new int[n6];
            for (int j = 0; j < n6; ++j) {
                array3[j] = array[(int)(j * n5)];
            }
            return array3;
        }
        return array;
    }
    
    static /* synthetic */ Class class(final String s, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s);
            if (!b) {
                forName.getComponentType();
            }
            return forName;
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private final /* synthetic */ void this() {
        this.volume = 1.0f;
    }
    
    public PSound() {
        this.this();
    }
    
    public PSound(final PApplet parent, final InputStream inputStream) {
        this.this();
        (this.parent = parent).registerDispose(this);
        try {
            final Class<? extends PApplet> class1 = parent.getClass();
            final String s = "soundEvent";
            final Class[] array = { null };
            final int n = 0;
            Class class$processing$core$PSound;
            if ((class$processing$core$PSound = PSound.class$processing$core$PSound) == null) {
                class$processing$core$PSound = (PSound.class$processing$core$PSound = class("[Lprocessing.core.PSound;", false));
            }
            array[n] = class$processing$core$PSound;
            this.soundEventMethod = class1.getMethod(s, (Class[])array);
        }
        catch (Exception ex) {}
    }
    
    static {
        LINEAR_LUT = new int[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
        LAW_LUT = new int[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
    }
    
    class Stream extends InputStream
    {
        int index;
        
        public int available() throws IOException {
            return PSound.this.data.length - PSound.this.position;
        }
        
        public void close() throws IOException {
        }
        
        public synchronized void mark() {
        }
        
        public boolean markSupported() {
            return false;
        }
        
        public int read() throws IOException {
            return 0;
        }
        
        public int read(final byte[] array) throws IOException {
            return 0;
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            return 0;
        }
        
        public synchronized void reset() {
            PSound.this.position = 0;
        }
        
        public long skip(final long n) {
            PSound.this.position = (PSound.this.position + (int)n) % PSound.this.data.length;
            return n;
        }
    }
}
