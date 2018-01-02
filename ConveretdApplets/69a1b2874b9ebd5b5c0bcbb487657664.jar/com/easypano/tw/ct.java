// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.applet.AudioClip;
import java.net.URL;

public class ct
{
    private TWViewer a;
    private boolean b;
    private URL c;
    private AudioClip d;
    private boolean e;
    private boolean f;
    
    public ct(final TWViewer a, final URL c) {
        this.b = false;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = true;
        this.a = a;
        this.c = c;
    }
    
    public void a() {
        final boolean q = h.q;
        ct ct = this;
        ct ct2 = this;
        if (!q) {
            if (this.a != null) {
                this.d = this.a.getAudioClip(this.c);
            }
            ct = this;
            ct2 = this;
        }
        final ct ct3 = ct2;
        synchronized (ct) {
            ct ct4 = this;
            Label_0090: {
                if (!q) {
                    Label_0089: {
                        if (this.e) {
                            ct4 = this;
                            if (q) {
                                break Label_0090;
                            }
                            if (this.d != null) {
                                ct ct5 = this;
                                if (!q) {
                                    if (this.f) {
                                        this.d.loop();
                                        if (!q) {
                                            break Label_0089;
                                        }
                                    }
                                    ct5 = this;
                                }
                                ct5.d.play();
                            }
                        }
                    }
                    ct4 = ct3;
                }
            }
        }
        // monitorexit(ct4)
    }
    
    public boolean a(final ct ct) {
        final boolean q = h.q;
        URL c = null;
        ct ct2 = ct;
        Label_0019: {
            if (!q) {
                if (ct == null) {
                    break Label_0019;
                }
                ct2 = ct;
            }
            c = ct2.c;
        }
        final URL c2 = this.c;
        if (!q) {
            Label_0038: {
                if (c2 != null) {
                    final URL url = c;
                    if (!q) {
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
                if (!h.q) {
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
