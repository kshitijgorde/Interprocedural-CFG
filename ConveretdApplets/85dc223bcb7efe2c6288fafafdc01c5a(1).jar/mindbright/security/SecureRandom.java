// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.util.Random;

public class SecureRandom extends Random
{
    MD5 md5;
    private int t;
    public Thread updater;
    public static int secureLevel;
    byte[] unused;
    int unusedPos;
    Object unusedLock;
    int poolSweep;
    
    public SecureRandom() {
        this.md5 = new MD5();
        this.poolSweep = 0;
        this.t = Spinner.guessTime(1024);
        for (int paranoia = (SecureRandom.secureLevel > 0) ? 2 : 1, i = 0; i < paranoia; ++i) {
            for (int j = this.md5.buffer.length - 1; j >= 0; --j) {
                this.md5.buffer[j] = (byte)Spinner.spin(this.t);
                if (SecureRandom.secureLevel < 2) {
                    this.md5.buffer[--j] = (byte)System.currentTimeMillis();
                }
            }
            this.md5.transform(this.md5.buffer, 0);
        }
        this.unused = new byte[16];
        this.unusedPos = 16;
        this.unusedLock = new Object();
    }
    
    public SecureRandom(final byte[] seed) {
        this.md5 = new MD5();
        this.poolSweep = 0;
        try {
            final MD5 md5 = new MD5();
            md5.update(seed);
            this.md5 = md5;
        }
        catch (Exception e) {
            System.out.println("Can't operate, MD5 not available...");
        }
        this.t = Spinner.guessTime(1024);
        this.unused = new byte[16];
        this.unusedPos = 16;
        this.unusedLock = new Object();
    }
    
    protected synchronized void update() {
        if (SecureRandom.secureLevel > 1) {
            final byte[] buffer = this.md5.buffer;
            final int n = this.poolSweep++;
            buffer[n] += (byte)(Spinner.spin(this.t) + 1);
            final byte[] buffer2 = this.md5.buffer;
            final int n2 = this.poolSweep++;
            buffer2[n2] += (byte)(Spinner.spin(this.t) + 1);
        }
        else {
            final byte[] buffer3 = this.md5.buffer;
            final int n3 = this.poolSweep++;
            buffer3[n3] += (byte)Spinner.bogusSpin();
            final byte[] buffer4 = this.md5.buffer;
            final int poolSweep = this.poolSweep;
            buffer4[poolSweep] += this.md5.buffer[this.poolSweep - 1];
            ++this.poolSweep;
        }
        this.poolSweep %= 64;
        final byte[] newUnused = new byte[16];
        this.md5.transform(this.md5.buffer, 0);
        writeBytes(this.md5.hash[0], newUnused, 0, 4);
        writeBytes(this.md5.hash[1], newUnused, 4, 4);
        writeBytes(this.md5.hash[2], newUnused, 8, 4);
        writeBytes(this.md5.hash[3], newUnused, 12, 4);
        synchronized (this.unusedLock) {
            this.unused = newUnused;
            this.unusedPos = 0;
        }
    }
    
    protected synchronized int next(final int bits) {
        int r = 0;
        synchronized (this.unusedLock) {
            for (int b = 0; b < bits; b += 8) {
                if (this.unusedPos == 16) {
                    this.update();
                }
                r = (r << 8) + this.unused[this.unusedPos++];
            }
        }
        return r;
    }
    
    public synchronized void startUpdater() {
        if (this.updater != null) {
            return;
        }
        (this.updater = new Thread(new Runnable() {
            public void run() {
                SecureRandom.this.updater.setPriority(SecureRandom.this.updater.getPriority() - 1);
                while (true) {
                    try {
                        Thread.sleep(10000L);
                    }
                    catch (InterruptedException ex) {}
                    SecureRandom.this.update();
                }
            }
        })).start();
    }
    
    public void nextPadBytes(final byte[] bytes, final int len) {
        this.nextPadBytes(bytes, 0, len);
    }
    
    public void nextPadBytes(final byte[] bytes, final int off, final int len) {
        synchronized (this.unusedLock) {
            for (int i = 0; i < len; ++i) {
                this.unusedPos %= 16;
                bytes[off + i] = this.unused[this.unusedPos++];
            }
        }
    }
    
    public static final void writeBytes(long a, final byte[] dest, final int i, final int length) {
        for (int j = i + length - 1; j >= i; --j) {
            dest[j] = (byte)a;
            a >>>= 8;
        }
    }
    
    static {
        SecureRandom.secureLevel = 0;
    }
}
