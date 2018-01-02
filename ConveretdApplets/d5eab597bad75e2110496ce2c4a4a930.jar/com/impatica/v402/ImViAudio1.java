// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import sun.audio.AudioPlayer;
import java.io.InputStream;

public class ImViAudio1 extends InputStream implements ImVi
{
    int I;
    ImSound Z;
    long currentTimeMillis;
    
    public final void C() {
    }
    
    public final int I() {
        int h = (int)(System.currentTimeMillis() - this.currentTimeMillis) * 8 + this.I;
        if (this.Z.H < h) {
            h = this.Z.H;
        }
        return h;
    }
    
    public ImViAudio1(final ImSound z) {
        this.Z = z;
    }
    
    public final boolean Z() {
        return true;
    }
    
    public final boolean I(final ImIsys imIsys, final ImIstream imIstream) {
        return false;
    }
    
    public final void I(final boolean b) {
    }
    
    public final void I(final int n, final int n2, final int n3, final int n4) {
    }
    
    public final int read() {
        final ImSound z = this.Z;
        int a = z.A;
        if (a == z.E) {
            return 255;
        }
        int n = z.S[a] << 8 | (z.S[a + 1] & 0xFF);
        a += 2;
        if (a == 64000) {
            a = 0;
        }
        z.A = a;
        final ImSound imSound = z;
        ++imSound.H;
        if (z.N != 255) {
            n = n * z.N >> 8;
        }
        int n2;
        int n3;
        if (n < 0) {
            n2 = 132 - n;
            n3 = 127;
        }
        else {
            n2 = 132 + n;
            n3 = 255;
        }
        int n4;
        if (n2 <= 4095) {
            if (n2 <= 255) {
                n4 = ((n3 ^ (n2 >> 3 & 0xF)) & 0xFF);
            }
            else if (n2 <= 511) {
                n4 = ((n3 ^ 16 + (n2 >> 4 & 0xF)) & 0xFF);
            }
            else if (n2 <= 1023) {
                n4 = ((n3 ^ 32 + (n2 >> 5 & 0xF)) & 0xFF);
            }
            else if (n2 <= 2047) {
                n4 = ((n3 ^ 48 + (n2 >> 6 & 0xF)) & 0xFF);
            }
            else {
                n4 = ((n3 ^ 64 + (n2 >> 7 & 0xF)) & 0xFF);
            }
        }
        else if (n2 <= 8191) {
            n4 = ((n3 ^ 80 + (n2 >> 8 & 0xF)) & 0xFF);
        }
        else if (n2 <= 16383) {
            n4 = ((n3 ^ 96 + (n2 >> 9 & 0xF)) & 0xFF);
        }
        else if (n2 <= 32767) {
            n4 = ((n3 ^ 112 + (n2 >> 10 & 0xF)) & 0xFF);
        }
        else {
            n4 = ((n3 ^ 0x7F) & 0xFF);
        }
        return n4;
    }
    
    public final int read(final byte[] array, int n, final int n2) {
        int i;
        for (i = 0; i < n2; ++i) {
            final int read = this.read();
            if (read == -1) {
                ++i;
                break;
            }
            array[n++] = (byte)read;
        }
        return i;
    }
    
    public final void I(final int h) {
        this.Z.H = h;
    }
    
    public final void Z(final int n) {
    }
    
    public final void start() {
        if (!this.Z.M) {
            try {
                this.I = this.Z.H;
                AudioPlayer.player.start(this);
                this.currentTimeMillis = System.currentTimeMillis();
            }
            catch (Throwable t) {
                this.Z.M = true;
            }
        }
    }
    
    public final void stop() {
        if (!this.Z.M) {
            try {
                AudioPlayer.player.stop(this);
            }
            catch (Throwable t) {
                this.Z.M = true;
            }
        }
    }
    
    public final int I(final byte[] array, final int n, final int n2) {
        return 0;
    }
}
