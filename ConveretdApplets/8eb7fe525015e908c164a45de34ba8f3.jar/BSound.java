import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class BSound extends InputStream
{
    public int[] samples;
    public int index;
    public boolean play;
    public boolean loop;
    public float volume;
    public float speed;
    public int position;
    public int length;
    public int in;
    public int out;
    private float posf;
    
    public int readSample() {
        this.posf += this.speed;
        this.position = (int)this.posf;
        if (this.position >= this.out) {
            this.position = this.in;
            this.posf = this.in;
            if (!this.loop) {
                this.play = false;
            }
        }
        if (this.position < this.in) {
            this.position = this.in;
            this.posf = this.in;
        }
        return (int)(this.samples[this.position] * this.volume);
    }
    
    public void volume(final float volume) {
        if (volume >= 0.0f) {
            this.volume = volume;
        }
        else {
            this.volume = 0.0f;
        }
    }
    
    public void speed(final float speed) {
        this.speed = speed;
    }
    
    public void play() {
        if (this.samples == null) {
            return;
        }
        if (!this.play) {
            this.play = true;
            BSonic.soundObjects.addElement(this);
        }
    }
    
    public void repeat() {
        if (this.samples == null) {
            return;
        }
        this.loop = true;
        if (!this.play) {
            this.play = true;
            BSonic.soundObjects.addElement(this);
        }
    }
    
    public void pause() {
        this.play = false;
    }
    
    public void stop() {
        this.play = false;
        this.position = this.in;
    }
    
    public void jump(int abs) {
        abs = Math.abs(abs);
        this.posf = abs;
        this.position = (int)this.posf;
        if (this.position >= this.out) {
            this.posf = this.out - 1;
        }
        if (this.position < this.in) {
            this.posf = this.in;
        }
        this.position = (int)this.posf;
    }
    
    public boolean playing() {
        return this.play;
    }
    
    public void in(int abs) {
        this.position = (int)this.posf;
        abs = Math.abs(abs);
        if (abs > 0) {
            if (abs < this.out) {
                this.in = abs;
            }
            else {
                this.in = this.out;
            }
        }
        else {
            this.in = 0;
        }
        if (this.position < this.in) {
            this.posf = this.in;
        }
    }
    
    public void out(int abs) {
        this.position = (int)this.posf;
        abs = Math.abs(abs);
        if (abs < this.length) {
            if (abs > this.in) {
                this.out = abs;
            }
            else {
                this.out = this.in;
            }
        }
        else {
            this.out = this.length;
        }
        if (this.position >= this.out) {
            this.posf = this.out - 1;
        }
    }
    
    public void borders(int abs, int out) {
        this.position = (int)this.posf;
        abs = Math.abs(abs);
        out = Math.abs(out);
        if (out < abs) {
            final int n = abs;
            abs = out;
            out = n;
        }
        if (out == abs) {
            ++out;
            --abs;
        }
        if (out > this.length) {
            out = this.length;
        }
        if (abs < 0) {
            abs = 0;
        }
        this.in = abs;
        this.out = out;
        if (this.position < this.in) {
            this.position = this.in;
        }
        if (this.position >= this.out) {
            this.position = this.out - 1;
        }
    }
    
    public synchronized int read() {
        if (BSonic.DEBUG) {
            System.out.println("BSound.read() single");
        }
        this.position = (int)this.posf;
        byte b = (byte)((this.position < this.out) ? BSonic.linear2ulaw(this.samples[this.position++]) : -1);
        if (this.position >= this.out && this.loop) {
            this.position = this.in;
            b = BSonic.linear2ulaw(this.samples[this.position++]);
        }
        else if (!this.loop) {
            if (this.volume == 1.0f) {
                b = BSonic.linear2ulaw(this.samples[this.position++] << 8);
            }
            else {
                b = (byte)(BSonic.linear2ulaw(this.samples[this.position++] << 8) * this.volume);
            }
        }
        return b;
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) {
        if (BSonic.DEBUG) {
            System.out.println("BSound.read() block");
        }
        if (!this.play) {
            if (BSonic.DEBUG) {
                System.out.println("BSound.read no play for you");
            }
            return -1;
        }
        if (BSonic.DEBUG) {
            System.out.println("BSound.read still playing");
        }
        if (!this.loop) {
            return this.sread(array, n, n2);
        }
        int i = 0;
        while (i < n2) {
            final int sread = this.sread(array, n + i, n2 - i);
            if (sread >= 0) {
                i += sread;
            }
            else {
                this.posf = this.in;
            }
        }
        return i;
    }
    
    public synchronized int sread(final byte[] array, final int n, int n2) {
        this.position = (int)this.posf;
        if (this.position >= this.out) {
            return -1;
        }
        if (this.position + n2 > this.out) {
            n2 = this.out - this.position;
        }
        if (n2 <= 0) {
            return 0;
        }
        for (int i = 0; i < n2; ++i) {
            array[n + i] = BSonic.linear2ulaw(this.readSample() << 8);
        }
        this.position += n2;
        return n2;
    }
    
    public synchronized long skip(long n) {
        if (this.position + n > this.out) {
            n = this.out - this.position;
        }
        if (n < 0L) {
            return 0L;
        }
        this.posf += n;
        return n;
    }
    
    public synchronized int available() {
        return this.out - this.position;
    }
    
    public boolean markSupported() {
        return false;
    }
    
    public void mark(final int n) {
    }
    
    public synchronized void reset() {
        this.posf = this.in;
    }
    
    private final /* synthetic */ void this() {
        this.samples = null;
        this.index = -1;
        this.play = false;
        this.loop = false;
        this.volume = 1.0f;
        this.speed = 1.0f;
        this.posf = 0.0f;
    }
    
    public BSound(int n) {
        this.this();
        if (n < 1) {
            n = 1;
        }
        this.samples = new int[n];
        this.length = this.samples.length;
        this.in = 0;
        this.out = this.length;
        this.position = 0;
        for (int i = 0; i < this.length; ++i) {
            this.samples[i] = 0;
        }
    }
    
    public BSound(final int[] samples) {
        this.this();
        this.samples = samples;
        this.length = this.samples.length;
        this.in = 0;
        this.out = this.length;
        this.position = 0;
    }
}
