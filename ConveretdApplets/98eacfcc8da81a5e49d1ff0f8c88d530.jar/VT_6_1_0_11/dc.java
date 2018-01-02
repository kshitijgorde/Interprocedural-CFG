// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import com.hw.client.util.a;

final class dc implements Runnable
{
    private Thread a;
    private boolean b;
    private int c;
    private byte[] d;
    private final cJ e;
    
    public dc(final cJ e) {
        this.e = e;
        this.b = false;
        this.c = 0;
        this.d = null;
        (this.a = new Thread(this, "HW-AudioFiller")).setPriority(10);
        this.b = true;
        this.a.start();
    }
    
    public final void a() {
        this.b = false;
        this.a.interrupt();
    }
    
    public final void run() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("\n\n\nDoorAudioManager2.AudioFiller.run:  samplesPerPacket=" + cJ.d(this.e) + ", samplesInFullQueue=" + cJ.e(this.e));
        }
        while (this.b) {
            if (this.d == null && this.c != -1) {
                try {
                    final int d;
                    if ((d = cJ.h(this.e).d()) == 1) {
                        this.b();
                    }
                    else if (d == 2) {
                        this.b();
                        cJ.a(this.e, this.d);
                    }
                    else if (d == 62) {
                        this.c();
                    }
                    else {
                        if (d != 42 && d != 41) {
                            throw new IllegalStateException("DoorAudioManager2.readAudioPacket - Unsupported encoding=" + d);
                        }
                        this.d();
                    }
                }
                catch (IOException ex) {
                    com.hw.client.util.a.b("AudioFiller.run: ioexc while reading packet", ex);
                    if (this.b) {
                        SwingUtilities.invokeLater(new bx(this));
                    }
                    return;
                }
            }
            if (!this.b) {
                return;
            }
            if (this.e.l() == 2 && cJ.f(this.e) == 1) {
                if (this.c == -1) {
                    if (com.hw.client.util.a.a()) {
                        com.hw.client.util.a.b("DoorAudioManager2.AudioFiller.run: r=-1 getSamplesInPlayQueue=" + this.e.b);
                    }
                    if (this.e.b == 0) {
                        SwingUtilities.invokeLater(new bA(this));
                        return;
                    }
                    this.e.a(new byte[0]);
                }
                else if (!cJ.g(this.e) && this.e.b < cJ.e(this.e)) {
                    if (this.e.b == 0) {
                        com.hw.client.util.a.d("DoorAudioManager2: No audio in agent - starving");
                    }
                    else if (this.e.b < cJ.e(this.e) / 4 && com.hw.client.util.a.b()) {
                        com.hw.client.util.a.c("DoorAudioManager2: samples in play queue<0.25*full =" + this.e.b);
                    }
                    if (com.hw.client.util.a.c()) {
                        com.hw.client.util.a.a("DoorAudioManager2.AudioFiller.run: sending audio packet");
                    }
                    final cJ e = this.e;
                    e.b += cJ.d(this.e);
                    this.e.a(this.d);
                    if (this.d.length != 0) {
                        cJ.a(this.e, (long)cJ.d(this.e));
                    }
                    this.d = null;
                }
                else if (!cJ.g(this.e)) {
                    this.e.a(new byte[0]);
                }
            }
            if (this.d != null) {
                try {
                    Thread.sleep(60L);
                }
                catch (InterruptedException ex2) {}
            }
            else {
                if (this.c != -1) {
                    continue;
                }
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    private void b() {
        this.d = new byte[cJ.d(this.e) << 1];
        int n = 0;
        this.c = 0;
        while (this.b && n != this.d.length && (this.c = cJ.i(this.e).read(this.d, n, this.d.length - n)) != -1) {
            n += this.c;
        }
    }
    
    private void c() {
        try {
            if (!cJ.j(this.e)) {
                cJ.b(this.e, true);
                cJ.k(this.e).a();
            }
            this.d = cJ.k(this.e).c().b();
            cJ.k(this.e).b();
        }
        catch (EOFException ex) {
            this.c = -1;
        }
    }
    
    private void d() {
        this.d = new byte[33];
        int n = 0;
        this.c = 0;
        while (this.b && n != this.d.length && (this.c = cJ.i(this.e).read(this.d, n, this.d.length - n)) != -1) {
            n += this.c;
        }
    }
    
    static cJ a(final dc dc) {
        return dc.e;
    }
}
