// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import java.util.Arrays;
import com.screencastomatic.play.stream.b;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import com.screencastomatic.play.stream.r;

public class g implements j, r
{
    private SourceDataLine a;
    private n b;
    private Long c;
    private Thread d;
    private k e;
    private Thread f;
    private s g;
    private Thread h;
    private a i;
    private Thread j;
    private f k;
    private boolean l;
    private AudioFormat m;
    private int n;
    
    public g(final n b, final AudioFormat m) {
        this.l = false;
        this.n = 0;
        this.b = b;
        this.m = m;
        (this.a = AudioSystem.getSourceDataLine(m)).open(m);
        this.g = new o(this, this.b.i());
        this.e = new com.screencastomatic.play.a.r(this, this.b.i());
        this.k = new q(this, this.b.i());
        this.i = new a(this.a);
        this.h = new Thread(this.i);
        this.f = new Thread(this.g);
        this.j = new Thread(this.k);
        this.d = new Thread(this.e);
        this.h.start();
        this.f.start();
        this.j.start();
        this.d.start();
    }
    
    public void a(final int n) {
        this.n = n;
        this.i.a(this.n);
    }
    
    public int a() {
        if (this.i.b() == 0) {
            return 0;
        }
        if (this.i.c() >= 10) {
            return -1;
        }
        int n = this.m.getFrameSize() * (int)this.m.getFrameRate();
        if (this.n > 0) {
            n *= this.n;
        }
        else if (this.n < 0) {
            n /= -this.n;
        }
        return this.i.b() / n;
    }
    
    public void a(final byte[] array, final com.screencastomatic.play.stream.k k, List list, final List list2) {
        try {
            if (this.c == null) {
                synchronized (this) {
                    this.c = k.b();
                    System.out.println("First position is now: " + this.c);
                    if (!list.isEmpty()) {
                        list = Arrays.asList(list.get(list.size() - 1));
                    }
                    this.b.i().a(k);
                    if (!list.isEmpty()) {
                        this.b.i().a(list.get(0));
                    }
                }
            }
            if (this.l && this.i.c() >= 10) {
                System.out.println("Blocking audio/frame reads since we're still paused.");
                this.b.a((c)null);
            }
            if (!this.b.c() || this.b.d()) {
                if (!this.l) {
                    System.out.println("Pausing audio/frame reader");
                    this.i.a(true);
                    if (this.a != null && this.a.isOpen()) {
                        this.a.stop();
                    }
                }
                this.l = true;
            }
            else {
                if (this.l) {
                    System.out.println("UnPausing audio/frame reader");
                    this.i.a(false);
                    if (this.a != null && this.a.isOpen()) {
                        this.a.start();
                    }
                }
                this.l = false;
            }
            this.i.a(array);
            this.g.a(k);
            this.e.a(list);
            this.k.a(list2);
            System.out.println("Num waiting frames:" + this.g.b() + " audio:" + this.i.c() + " webcam:" + this.k.b());
            while (!this.l && this.a.isOpen() && this.i.c() > 10) {
                if (this.a.getBufferSize() - this.a.available() == 0) {
                    System.out.println("Not waiting any more for frames to play since we don't have any more audio.");
                    break;
                }
                Thread.sleep(10L);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Audio and Frame reader interrupted.");
        }
    }
    
    public void a(final Runnable runnable) {
        System.out.println("Killing the frames, mouse, and webcam slowly.");
        this.g.a(false);
        this.k.a(false);
        this.e.a(false);
        this.i.a(runnable);
    }
    
    public synchronized long b() {
        if (this.c == null) {
            return this.b.j();
        }
        return this.e();
    }
    
    private long e() {
        return this.c + (long)(this.i.a() / (this.m.getFrameSize() * (int)this.m.getFrameRate() / 1000.0f)) + this.a.getMicrosecondPosition() / 1000L;
    }
    
    public synchronized void c() {
        System.out.println("Start audio/frame reader");
        if (this.a != null) {
            this.a.start();
        }
    }
    
    public synchronized void d() {
        if (this.a != null) {
            this.a.stop();
            this.a.close();
        }
        this.i.d();
        this.g.a(true);
        this.k.a(true);
        this.e.a(true);
        try {
            this.h.interrupt();
        }
        catch (SecurityException ex) {
            System.out.println("Security Exception when interrupting audio thread.");
        }
        try {
            this.f.interrupt();
        }
        catch (SecurityException ex2) {
            System.out.println("Security Exception when interrupting frame thread.");
        }
        try {
            this.j.interrupt();
        }
        catch (SecurityException ex3) {
            System.out.println("Security Exception when interrupting webcam thread.");
        }
        try {
            this.d.interrupt();
        }
        catch (SecurityException ex4) {
            System.out.println("Security Exception when interrupting mouse thread.");
        }
        com.screencastomatic.play.stream.q.a(this.h, "Audio thread");
        com.screencastomatic.play.stream.q.a(this.f, "Frame thread");
        com.screencastomatic.play.stream.q.a(this.j, "WebCam thread");
        com.screencastomatic.play.stream.q.a(this.d, "Mouse thread");
    }
}
