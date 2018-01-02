// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import javax.sound.sampled.AudioFormat;
import com.screencastomatic.play.stream.e;
import com.screencastomatic.play.stream.r;
import java.io.EOFException;

class b implements Runnable
{
    final /* synthetic */ n a;
    
    private b(final n a) {
        this.a = a;
    }
    
    public void run() {
        System.out.println("PlayerRun is starting...");
        try {
            while (this.a.d == Thread.currentThread() && this.a.h() != this.a.j.b()) {
                this.a.j.a(this.a.h);
            }
            System.out.println("PlayerRun is done NO EXCEPTION... (pos: " + this.a.h() + " dur: " + this.a.j.b() + ")");
            if (this.a.h() == this.a.j.b()) {
                this.a.h.a(new l(this));
            }
        }
        catch (EOFException ex2) {
            System.out.println("PlayerRun is done... (pos: " + this.a.h() + " dur: " + this.a.j.b() + ")");
            this.a.h.a(new m(this));
        }
        catch (Exception ex) {
            System.out.println("PlayerRun caught exception and will die!");
            ex.printStackTrace();
        }
        finally {
            if (this.a.j != null) {
                this.a.j.c();
            }
        }
    }
}
