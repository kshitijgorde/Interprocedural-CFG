// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

public class bw
{
    private TWViewer a;
    private bx b;
    private boolean c;
    private boolean d;
    private boolean e;
    private ct f;
    private ct g;
    
    public bw(final TWViewer a) {
        this.b = null;
        this.c = false;
        this.d = true;
        this.e = true;
        this.f = null;
        this.g = null;
        this.a = a;
    }
    
    public void a(final bx b) {
        this.b = b;
    }
    
    public void destroyResource() {
        this.stopAudioClip();
        this.f = null;
        this.g = null;
        this.b = null;
    }
    
    void a(final int n, final boolean b, final boolean b2) {
        this.a(this.b.a(n), b, b2);
    }
    
    void a(final ct ct, final boolean b, final boolean b2) {
        final boolean q = com.easypano.tw.g.q;
        boolean e = b2;
        bw bw2 = null;
        Label_0144: {
            if (!q) {
                if (b2) {
                    bw bw = this;
                    Label_0074: {
                        if (!q) {
                            if (this.d == b) {
                                bw = this;
                                if (q) {
                                    break Label_0074;
                                }
                                if (this.g != null) {
                                    bw = this;
                                    if (q) {
                                        break Label_0074;
                                    }
                                    if (this.g.a(ct)) {
                                        return;
                                    }
                                }
                            }
                            this.d = b;
                            this.stopAudioClip(this.g);
                            this.g = ct;
                            bw = this;
                        }
                    }
                    bw.a(ct, b);
                    if (!q) {
                        return;
                    }
                }
                bw2 = this;
                if (q) {
                    break Label_0144;
                }
                e = this.e;
            }
            if (e == b) {
                bw2 = this;
                if (q) {
                    break Label_0144;
                }
                if (this.f != null) {
                    bw2 = this;
                    if (q) {
                        break Label_0144;
                    }
                    if (this.f.a(ct)) {
                        return;
                    }
                }
            }
            this.e = b;
            this.stopAudioClip(this.f);
            this.f = ct;
            bw2 = this;
        }
        bw2.a(ct, b);
    }
    
    void a(final ct ct, final boolean b) {
        if (!this.c) {
            ct ct2 = ct;
            if (!com.easypano.tw.g.q) {
                if (ct == null) {
                    return;
                }
                ct2 = ct;
            }
            ct2.a(b);
        }
    }
    
    void stopAudioClip(final ct ct) {
        if (ct != null) {
            ct.stop();
        }
    }
    
    void a() {
        final boolean q = com.easypano.tw.g.q;
        final boolean c = this.c;
        if (!q && !c) {}
        this.c = c;
        bw bw = this;
        if (!q) {
            if (this.c) {
                this.stopAudioClip();
                if (!q) {
                    return;
                }
            }
            this.a(this.g, this.d);
            bw = this;
        }
        bw.a(this.f, this.e);
    }
    
    void stopAudioClip() {
        this.stopAudioClip(this.f);
        this.stopAudioClip(this.g);
    }
    
    void stopAudioClip(final boolean b) {
        if (b) {
            this.stopAudioClip(this.g);
        }
        else {
            this.stopAudioClip(this.f);
        }
    }
}
