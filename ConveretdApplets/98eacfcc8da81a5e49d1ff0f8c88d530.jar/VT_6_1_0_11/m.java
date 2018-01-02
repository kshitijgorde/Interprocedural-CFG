// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import com.hw.client.util.a;

final class m implements Runnable
{
    private am a;
    private int b;
    private final cJ c;
    
    m(final cJ c, final am a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        if (this.b != cJ.m(this.c)) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("DoorAudioManager2.AudioCBJob.run: skipping CB type=" + (int)this.a.a);
            }
            return;
        }
        final char a;
        if ((a = this.a.a) != '\u0086') {
            if (a == '\u0082') {
                cJ.b(this.c, this.a.b);
                return;
            }
            if (a == '\u0089') {
                C.a().a(false);
                C.a().f().setValue(this.a.g);
                C.a().a(true);
                if (cJ.a(this.c) == 0) {
                    cJ.b(this.c).c();
                    cJ.a(this.c, 1);
                    if (cJ.c(this.c) != null) {
                        cJ.c(this.c).k();
                    }
                }
                if (cJ.a(this.c) == 4) {
                    cJ.b(this.c).c();
                    cJ.a(this.c, 1);
                }
            }
            else if (a == '\u008a') {
                C.a().a(false);
                C.a().b().setValue(this.a.h);
                C.a().a(true);
                if (cJ.c(this.c) != null) {
                    return;
                }
            }
            else if (a == '\u008b') {
                if (cJ.c(this.c) != null) {
                    cJ.c(this.c).a(this.a.i);
                }
                if (cJ.a(this.c) == 3) {
                    cJ.b(this.c).c();
                    cJ.a(this.c, 1);
                }
            }
            else if (cJ.a(this.c) == 2) {
                if (a == '\u0085') {
                    if (cJ.f(this.c) == 2) {
                        cJ.b(this.c, (long)(cJ.n(this.c).g() * 20 / 1000));
                        try {
                            cJ.b(this.c, this.a.d);
                        }
                        catch (IOException ex) {
                            com.hw.client.util.a.b("DoorAudioManager2.processHWEvent: IOExc while calling appendAudio", ex);
                            throw new IllegalStateException("appendAudio should not throw an IOException in the way we use it");
                        }
                        if (cJ.c(this.c) != null && cJ.o(this.c) - this.c.c >= 0.4 * cJ.n(this.c).g()) {
                            cJ.c(this.c).b(cJ.o(this.c));
                            this.c.c = cJ.o(this.c);
                        }
                    }
                }
                else if (a == '\u0087') {
                    this.c.b = this.a.c;
                    if (com.hw.client.util.a.c()) {
                        com.hw.client.util.a.a("DoorAudioManager2.processCallback samplesInPlayQueue=" + this.c.b);
                    }
                    cJ.c(this.c, cJ.p(this.c) - this.c.b);
                    if (cJ.c(this.c) != null && cJ.q(this.c) - this.c.d >= 0.4 * cJ.h(this.c).g()) {
                        cJ.c(this.c).a(cJ.q(this.c));
                        this.c.d = cJ.q(this.c);
                    }
                }
                else if (a == '\u0088') {
                    C.a().a(false);
                    C.a().f().setValue(this.a.g);
                    C.a().b().setValue(this.a.h);
                    C.a().a(true);
                    final long currentTimeMillis;
                    if ((currentTimeMillis = System.currentTimeMillis()) - cJ.r(this.c) > 100L) {
                        cJ.d(this.c, currentTimeMillis);
                        if (cJ.c(this.c) != null) {
                            if (cJ.f(this.c) == 1) {
                                cJ.c(this.c).b(cJ.c(this.c, this.a.f));
                                return;
                            }
                            if (cJ.f(this.c) == 2) {
                                cJ.c(this.c).a(cJ.c(this.c, this.a.e));
                            }
                        }
                    }
                }
            }
            else if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("DoorAudioManager2: Received cb type=" + (int)a + ", state=" + cJ.a(this.c));
            }
        }
    }
}
