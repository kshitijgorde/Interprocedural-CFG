import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Enumeration;
import sun.audio.AudioPlayer;
import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class BSonic extends InputStream implements Runnable
{
    public static boolean DEBUG;
    public static Vector soundObjects;
    public static final int SAMPLE_MAX = 127;
    public static final int SAMPLE_MIN = -127;
    public static final int SAMPLE_AMPLITUDE = 255;
    public static final int SAMPLING_RATE = 8000;
    public static final int DEFAULT_BUFFER_SIZE = 6000;
    public static final int MAX_SOUNDS = 24;
    public static float volume;
    static final int AU_FILE = 1;
    static final int WAV_FILE = 2;
    static final int MP3_FILE = 3;
    static final short BIAS = 132;
    static final int CLIP = 32635;
    static final int[] law_lut;
    static final int[] linear_lut;
    public boolean started;
    public boolean run;
    public boolean lite;
    private BSonic stream;
    private BApplet owner;
    Thread sonicThread;
    public BSound[] sounds;
    public int scount;
    public int[] samples;
    public int length;
    public byte[] buf;
    public int pos;
    public int mark;
    public int count;
    public int delay;
    public int posc;
    public int size;
    public boolean full;
    public int format;
    
    public void start(final BApplet owner) {
        this.owner = owner;
        if (this.started || this.lite) {
            if (BSonic.DEBUG) {
                System.out.println("not starting sonic thread");
            }
            return;
        }
        for (int i = 0; i < this.count; ++i) {
            this.buf[i] = -1;
        }
        if (this.sonicThread == null) {
            this.sonicThread = new Thread(this, "BSonic Thread");
            this.run = true;
        }
        if (BSonic.DEBUG) {
            System.out.println("starting audio player");
        }
        AudioPlayer.player.start(this);
        if (BSonic.DEBUG) {
            System.out.println("audio player started");
        }
        this.sonicThread.start();
        this.started = true;
    }
    
    public void stop() {
        this.run = false;
        if (this.sonicThread != null) {
            this.sonicThread = null;
        }
        if (BSonic.DEBUG) {
            System.out.println("stoping audio player");
        }
        AudioPlayer.player.stop(this);
        if (BSonic.DEBUG) {
            System.out.println("cleaning house for soundObjects");
        }
        if (BSonic.soundObjects != null) {
            final Enumeration<BSound> elements = BSonic.soundObjects.elements();
            while (elements.hasMoreElements()) {
                final BSound bSound = elements.nextElement();
                this.stop(bSound);
                if (BSonic.DEBUG) {
                    System.out.println("stopping " + bSound);
                }
            }
            BSonic.soundObjects = null;
        }
        else if (BSonic.DEBUG) {
            System.out.println("Bsonic.stop soundObjects was null");
        }
        if (BSonic.DEBUG) {
            System.out.println("BSonic.stop audio player stopped");
        }
    }
    
    public void run() {
        while (this.run) {
            if (BSonic.DEBUG) {
                System.out.println("BSonic.run()");
            }
            this.mixing();
            try {
                final Thread sonicThread = this.sonicThread;
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                if (!BSonic.DEBUG) {
                    continue;
                }
                System.out.println("sleep interrupted");
            }
        }
        if (BSonic.DEBUG) {
            System.out.println("exiting run() for BSonic");
        }
    }
    
    public synchronized void mixing() {
        if (this.full) {
            ++this.delay;
            if (BSonic.DEBUG) {
                System.out.println("\n+delay: " + this.delay);
            }
            return;
        }
        for (int i = 0; i < this.scount; ++i) {
            for (int j = 0; j < this.length; ++j) {
                if (i == 0) {
                    this.samples[j] = 0;
                }
                if (this.sounds[i].play) {
                    final int[] samples = this.samples;
                    final int n = j;
                    samples[n] += this.sounds[i].readSample();
                }
            }
        }
        if (BSonic.DEBUG) {
            System.out.print(" P:" + this.posc);
        }
        this.owner.soundEvent();
        this.clip();
        for (int k = 0; k < this.length; ++k) {
            if (this.posc > this.size) {
                if (BSonic.DEBUG) {
                    System.out.print("-" + this.size);
                }
                this.posc = 0;
                this.full = true;
                return;
            }
            if (this.posc < this.count) {
                if (BSonic.volume == 1.0f) {
                    this.buf[this.posc] = linear2ulaw(this.samples[k] << 8);
                }
                else {
                    this.buf[this.posc] = (byte)(linear2ulaw(this.samples[k] << 8) * BSonic.volume);
                }
            }
            ++this.posc;
        }
        if (BSonic.DEBUG) {
            System.out.print("-" + this.posc);
        }
    }
    
    public void clip() {
        for (int i = 0; i < this.length; ++i) {
            if (this.samples[i] > 127) {
                this.samples[i] = 127;
            }
            if (this.samples[i] < -127) {
                this.samples[i] = -127;
            }
        }
    }
    
    public void add(final BSound bSound) {
        if (this.scount < 23) {
            if (BSonic.DEBUG) {
                System.out.println("sound added: " + this.scount);
            }
            if (bSound.index < 0) {
                this.sounds[this.scount] = bSound;
                bSound.index = this.scount;
                ++this.scount;
            }
            else {
                this.sounds[bSound.index] = bSound;
            }
        }
    }
    
    public void play(final BSound bSound) {
        if (this.lite) {
            bSound.play = true;
            bSound.loop = false;
            if (bSound.position >= bSound.out) {
                bSound.position = bSound.in;
                AudioPlayer.player.stop(bSound);
            }
            if (BSonic.DEBUG) {
                System.out.println("BSonic.play playing lite sound");
            }
            AudioPlayer.player.start(bSound);
            if (BSonic.DEBUG) {
                System.out.println("BSonic.play adding sound object");
            }
            BSonic.soundObjects.addElement(bSound);
        }
        else {
            if (bSound.index < 0) {
                this.add(bSound);
            }
            bSound.play();
        }
    }
    
    public void play(final BSound bSound, final int n, final int n2) {
        bSound.borders(n, n2);
        this.play(bSound);
    }
    
    public void repeat(final BSound bSound) {
        if (this.lite) {
            bSound.play = true;
            bSound.loop = true;
            if (BSonic.DEBUG) {
                System.out.println("BSonic.repeat repeating lite sound");
            }
            AudioPlayer.player.start(bSound);
            if (BSonic.DEBUG) {
                System.out.println("BSonic.repeat adding sound object");
            }
            BSonic.soundObjects.addElement(bSound);
        }
        else {
            if (bSound.index < 0) {
                this.add(bSound);
            }
            bSound.repeat();
        }
    }
    
    public void repeat(final BSound bSound, final int n, final int n2) {
        bSound.borders(n, n2);
        this.repeat(bSound);
    }
    
    public void stop(final BSound bSound) {
        if (this.lite) {
            bSound.play = false;
            bSound.loop = false;
            bSound.position = 0;
            AudioPlayer.player.stop(bSound);
        }
        else {
            bSound.stop();
        }
    }
    
    public void pause(final BSound bSound) {
        if (this.lite) {
            bSound.play = false;
            AudioPlayer.player.stop(bSound);
            return;
        }
        bSound.pause();
    }
    
    public static void volume(final float volume) {
        BSonic.volume = volume;
    }
    
    public static void balance(final float n) {
    }
    
    public static void frequency(final float n) {
    }
    
    public synchronized int read() {
        if (BSonic.DEBUG) {
            System.out.println("reading audio");
        }
        return (this.pos < this.count) ? (this.buf[this.pos++] & 0xFF) : -1;
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) {
        if (BSonic.DEBUG) {
            System.out.println("\n========================");
        }
        if (BSonic.DEBUG) {
            System.out.println("PRE len: " + n2 + " count: " + this.count + " pos: " + this.pos + " b.length " + array.length + " size " + this.size);
        }
        this.size = ((this.size > n2) ? this.size : n2);
        if (!this.full) {
            this.delay -= 10;
            if (this.delay < 0) {
                this.delay = 10;
            }
            if (BSonic.DEBUG) {
                System.out.println("\n-delay: " + this.delay);
            }
        }
        if (n2 > this.count) {
            return 0;
        }
        System.arraycopy(this.buf, 0, array, n, n2);
        if (BSonic.DEBUG) {
            System.out.println("POS len: " + n2 + " count: " + this.count + " pos: " + this.pos + " b.length " + array.length + " size " + this.size);
        }
        if (BSonic.DEBUG) {
            System.out.println("\n========================");
        }
        this.posc = 0;
        this.full = false;
        return n2;
    }
    
    public synchronized long skip(long n) {
        if (this.pos + n > this.count) {
            n = this.count - this.pos;
        }
        if (n < 0L) {
            return 0L;
        }
        this.pos += (int)n;
        return n;
    }
    
    public synchronized int available() {
        return this.count - this.pos;
    }
    
    public boolean markSupported() {
        return false;
    }
    
    public void mark(final int n) {
    }
    
    public synchronized void reset() {
        this.pos = 0;
    }
    
    public BSound generate(final int n, final int n2) {
        return null;
    }
    
    public BSound microphone() {
        return null;
    }
    
    public BSound loadSound(final String s) {
        DataInputStream dataInputStream = null;
        int[] array = null;
        if (s.startsWith("http://")) {
            try {
                dataInputStream = new DataInputStream(new URL(s).openStream());
            }
            catch (IOException ex) {
                System.err.println("error loading sound from " + s);
                ex.printStackTrace();
                return null;
            }
        }
        else {
            InputStream inputStream = this.getClass().getResourceAsStream(s);
            if (inputStream == null) {
                inputStream = this.getClass().getResourceAsStream("data/" + s);
            }
            if (inputStream != null) {
                dataInputStream = new DataInputStream(inputStream);
            }
        }
        if (dataInputStream == null) {
            System.err.println("could not load sound file " + s);
            return null;
        }
        if (s.toLowerCase().endsWith(".au")) {
            this.format = 1;
            array = this.loadAU(dataInputStream);
        }
        if (s.toLowerCase().endsWith(".wav")) {
            this.format = 2;
            array = this.loadWAV(dataInputStream);
        }
        if (s.toLowerCase().endsWith(".mp3")) {
            this.format = 3;
            array = this.loadMP3(dataInputStream);
        }
        if (this.format == 0) {
            System.err.println("could not load sound file " + s + " - format unkown");
            return null;
        }
        if (array != null) {
            final BSound bSound = new BSound(array);
            if (!this.lite) {
                this.add(bSound);
            }
            return bSound;
        }
        System.err.println("could not load sound file " + s + " - error in file");
        return null;
    }
    
    public int[] loadAU(final DataInputStream dataInputStream) {
        final int n = 779316836;
        final int n2 = 779314176;
        final int n3 = 24;
        final boolean b = true;
        final int n4 = 2;
        final int n5 = 3;
        boolean b2 = false;
        int int1;
        int int2;
        int int3;
        int int4;
        int int5;
        int int6;
        try {
            int1 = dataInputStream.readInt();
            if (int1 != n && int1 != n2) {
                throw new Exception();
            }
            int2 = dataInputStream.readInt();
            if (int2 < n3) {
                throw new Exception();
            }
            int3 = dataInputStream.readInt();
            int4 = dataInputStream.readInt();
            if (int4 != (b ? 1 : 0) && int4 != n4 && int4 != n5) {
                throw new Exception();
            }
            int5 = dataInputStream.readInt();
            if (int5 != 8000) {
                b2 = true;
            }
            int6 = dataInputStream.readInt();
            if (int6 != 1 && int6 != 2) {
                throw new Exception();
            }
            dataInputStream.skipBytes(int2 - n3);
        }
        catch (Exception ex) {
            return null;
        }
        if (BSonic.DEBUG) {
            System.out.println("skip " + (int2 - n3) + " magic: " + int1 + " dataLocation: " + int2 + " dataSize: " + int3 + " dataFormat: " + int4 + " samplingRate: " + int5 + " channelCount: " + int6);
        }
        int n6 = int3 / int6;
        if (int4 == n5) {
            n6 /= 2;
        }
        final int[] array = new int[n6];
        if (BSonic.DEBUG) {
            System.out.println("new thesamples " + array.length);
        }
        for (int i = 0; i < n6; ++i) {
            try {
                if (int4 == (b ? 1 : 0)) {
                    array[i] = ulaw2linear(dataInputStream.read()) >> 8;
                    if (int6 == 2) {
                        dataInputStream.read();
                    }
                }
                else if (int4 == n4) {
                    array[i] = dataInputStream.readByte();
                    if (int6 == 2) {
                        dataInputStream.readByte();
                    }
                }
                else if (int4 == n5) {
                    array[i] = dataInputStream.readShort() >> 8;
                    if (int1 == n2) {
                        array[i] = reverseShort(array[i]);
                    }
                    if (int6 == 2) {
                        dataInputStream.readShort();
                    }
                }
            }
            catch (Exception ex2) {
                return null;
            }
        }
        if (b2) {
            return resample(array, int5, 8000);
        }
        return array;
    }
    
    public int[] loadWAV(final DataInputStream dataInputStream) {
        final int n = 1380533830;
        final int n2 = 1463899717;
        final int n3 = 1718449184;
        final int n4 = 1684108385;
        final boolean b = true;
        boolean b2 = false;
        boolean b3 = true;
        int n5;
        int reverseShort;
        int reverseShort2;
        int reverseInt2;
        int reverseShort3;
        int reverseInt3;
        try {
            if (dataInputStream.readInt() != n) {
                throw new Exception();
            }
            dataInputStream.readInt();
            if (dataInputStream.readInt() != n2) {
                throw new Exception();
            }
            n5 = dataInputStream.readInt();
            if (n5 != n3) {
                throw new Exception();
            }
            final int reverseInt = reverseInt(dataInputStream.readInt());
            reverseShort = reverseShort(dataInputStream.readUnsignedShort());
            if (reverseShort != (b ? 1 : 0)) {
                throw new Exception();
            }
            reverseShort2 = reverseShort(dataInputStream.readUnsignedShort());
            if (reverseShort2 != 1 && reverseShort2 != 2) {
                throw new Exception();
            }
            reverseInt2 = reverseInt(dataInputStream.readInt());
            if (reverseInt2 != 8000) {
                b2 = true;
            }
            dataInputStream.readInt();
            dataInputStream.readUnsignedShort();
            reverseShort3 = reverseShort(dataInputStream.readUnsignedShort());
            if (reverseShort3 != 8 && reverseShort3 != 16) {
                throw new Exception();
            }
            dataInputStream.skipBytes(reverseInt - 16);
            while (b3) {
                n5 = dataInputStream.readInt();
                if (n5 != n4) {
                    dataInputStream.skipBytes(reverseInt(dataInputStream.readInt()));
                }
                else {
                    b3 = false;
                }
            }
            reverseInt3 = reverseInt(dataInputStream.readInt());
        }
        catch (Exception ex) {
            return null;
        }
        if (BSonic.DEBUG) {
            System.out.println(" magic: " + n5 + " dataFormat: " + reverseShort + " dataSize: " + reverseInt3 + " samplingRate: " + reverseInt2 + " channelCount: " + reverseShort2 + " bitDepth: " + reverseShort3);
        }
        int n6 = reverseInt3 / reverseShort2;
        if (reverseShort3 == 16) {
            n6 /= 2;
        }
        final int[] array = new int[n6];
        if (BSonic.DEBUG) {
            System.out.println("new thesamples " + array.length);
        }
        for (int i = 0; i < n6; ++i) {
            try {
                if (reverseShort == (b ? 1 : 0)) {
                    if (reverseShort3 == 8) {
                        array[i] = dataInputStream.readUnsignedByte() - 128;
                        if (reverseShort2 == 2) {
                            dataInputStream.readUnsignedByte();
                        }
                    }
                    else if (reverseShort3 == 16) {
                        array[i] = reverseInt(dataInputStream.readUnsignedShort()) >> 24;
                        if (reverseShort2 == 2) {
                            dataInputStream.readUnsignedShort();
                        }
                    }
                }
            }
            catch (Exception ex2) {
                return null;
            }
        }
        if (b2) {
            return resample(array, reverseInt2, 8000);
        }
        return array;
    }
    
    public int[] loadMP3(final DataInputStream dataInputStream) {
        return null;
    }
    
    public static byte linear2ulaw(int n) {
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
        final int n3 = BSonic.law_lut[n >> 7 & 0xFF];
        byte b = (byte)~(n2 | n3 << 4 | (n >> n3 + 3 & 0xF));
        if (b == 0) {
            b = 2;
        }
        return b;
    }
    
    public static int ulaw2linear(int n) {
        n ^= -1;
        final int n2 = n & 0x80;
        final int n3 = n >> 4 & 0x7;
        int n4 = BSonic.linear_lut[n3] + ((n & 0xF) << n3 + 3);
        if (n2 != 0) {
            n4 = -n4;
        }
        return (short)n4;
    }
    
    private static final int reverseShort(final int n) {
        return (n >>> 8 & 0xFF) | (n << 8 & 0xFF00);
    }
    
    private static final int reverseInt(final int n) {
        return (n >> 24 & 0xFF) | (n >> 8 & 0xFF00) | (n << 8 & 0xFF0000) | (n << 24 & 0xFF000000);
    }
    
    private static final int[] resample(final int[] array, final int n, final int n2) {
        if (n > n2) {
            final float n3 = n / n2;
            final int n4 = (int)(array.length / n3);
            final int[] array2 = new int[n4];
            for (int i = 0; i < n4; ++i) {
                array2[i] = array[(int)(i * n3)];
            }
            return array2;
        }
        if (n > n2) {
            final float n5 = n2 / n;
            final int n6 = (int)(array.length * n5);
            final int[] array3 = new int[n6];
            for (int j = 0; j < n6; ++j) {
                array3[j] = array[(int)(j * n5)];
            }
            return array3;
        }
        if (BSonic.DEBUG) {
            System.out.println("Done...");
        }
        return array;
    }
    
    private final /* synthetic */ void this() {
        this.started = false;
        this.run = false;
        this.lite = false;
        this.scount = 0;
        this.mark = 0;
        this.full = false;
        this.format = 0;
    }
    
    public BSonic() {
        this.this();
        if (BSonic.DEBUG) {
            System.out.println("sonic lite constructor");
        }
        BSonic.soundObjects = new Vector();
        this.length = 0;
        this.posc = 0;
        this.size = this.length;
        this.pos = 0;
        this.count = 0;
        this.lite = true;
    }
    
    public BSonic(final int[] samples) {
        this.this();
        if (BSonic.DEBUG) {
            System.out.println("sonic constructor");
        }
        BSonic.soundObjects = new Vector();
        this.samples = samples;
        this.length = this.samples.length;
        this.delay = this.length * 1000 / 16000;
        this.posc = 0;
        this.size = this.length;
        this.buf = new byte[6000];
        this.pos = 0;
        this.count = this.buf.length;
        this.sounds = new BSound[24];
        if (BSonic.DEBUG) {
            System.out.println("length: " + this.length + " delay: " + this.delay);
        }
    }
    
    static {
        BSonic.DEBUG = false;
        BSonic.volume = 1.0f;
        law_lut = new int[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
        linear_lut = new int[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
    }
}
