// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.applet.AudioClip;
import java.net.URL;

public class cs
{
    private TWViewer a;
    private boolean b;
    private URL c;
    private AudioClip d;
    private boolean e;
    private boolean f;
    
    public cs(final TWViewer a, final URL c) {
        this.b = false;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = true;
        this.a = a;
        this.c = c;
    }
    
    public void a() {
        final int q = h.q;
        cs cs = this;
        cs cs2 = this;
        if (q == 0) {
            if (this.a != null) {
                this.d = this.a.getAudioClip(this.c);
            }
            cs = this;
            cs2 = this;
        }
        final cs cs3 = cs2;
        synchronized (cs) {
            cs cs4 = this;
            Label_0090: {
                if (q == 0) {
                    Label_0089: {
                        if (this.e) {
                            cs4 = this;
                            if (q != 0) {
                                break Label_0090;
                            }
                            if (this.d != null) {
                                cs cs5 = this;
                                if (q == 0) {
                                    if (this.f) {
                                        this.d.loop();
                                        if (q == 0) {
                                            break Label_0089;
                                        }
                                    }
                                    cs5 = this;
                                }
                                cs5.d.play();
                            }
                        }
                    }
                    cs4 = cs3;
                }
            }
        }
        // monitorexit(cs4)
    }
    
    public boolean a(final cs cs) {
        final int q = h.q;
        URL c = null;
        cs cs2 = cs;
        Label_0019: {
            if (q == 0) {
                if (cs == null) {
                    break Label_0019;
                }
                cs2 = cs;
            }
            c = cs2.c;
        }
        final URL c2 = this.c;
        if (q == 0) {
            Label_0038: {
                if (c2 != null) {
                    final URL url = c;
                    if (q == 0) {
                        if (url == null) {
                            break Label_0038;
                        }
                        final URL c3 = this.c;
                    }
                    return url.equals(c);
                }
            }
            final URL c4 = this.c;
        }
        return c2 == c;
    }
    
    public synchronized void a(final boolean f) {
        this.e = true;
        this.f = f;
        if (this.d != null) {
            if (f) {
                this.d.loop();
                if (h.q == 0) {
                    return;
                }
            }
            this.d.play();
        }
    }
    
    public synchronized void stop() {
        this.e = false;
        if (this.d != null) {
            this.d.stop();
        }
    }
    
    public synchronized boolean b() {
        return this.e;
    }
    
    public void destroyResource() {
        if (this.d != null) {
            this.d.stop();
        }
        this.a = null;
    }
}
