// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

public class bv
{
    private TWViewer a;
    private bw b;
    private boolean c;
    private boolean d;
    private boolean e;
    private cs f;
    private cs g;
    
    public bv(final TWViewer a) {
        this.b = null;
        this.c = false;
        this.d = true;
        this.e = true;
        this.f = null;
        this.g = null;
        this.a = a;
    }
    
    public void a(final bw b) {
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
    
    void a(final cs cs, final boolean b, final boolean b2) {
        final boolean q = com.easypano.tw.g.q;
        boolean e = b2;
        bv bv2 = null;
        Label_0144: {
            if (!q) {
                if (b2) {
                    bv bv = this;
                    Label_0074: {
                        if (!q) {
                            if (this.d == b) {
                                bv = this;
                                if (q) {
                                    break Label_0074;
                                }
                                if (this.g != null) {
                                    bv = this;
                                    if (q) {
                                        break Label_0074;
                                    }
                                    if (this.g.a(cs)) {
                                        return;
                                    }
                                }
                            }
                            this.d = b;
                            this.stopAudioClip(this.g);
                            this.g = cs;
                            bv = this;
                        }
                    }
                    bv.a(cs, b);
                    if (!q) {
                        return;
                    }
                }
                bv2 = this;
                if (q) {
                    break Label_0144;
                }
                e = this.e;
            }
            if (e == b) {
                bv2 = this;
                if (q) {
                    break Label_0144;
                }
                if (this.f != null) {
                    bv2 = this;
                    if (q) {
                        break Label_0144;
                    }
                    if (this.f.a(cs)) {
                        return;
                    }
                }
            }
            this.e = b;
            this.stopAudioClip(this.f);
            this.f = cs;
            bv2 = this;
        }
        bv2.a(cs, b);
    }
    
    void a(final cs cs, final boolean b) {
        if (!this.c) {
            cs cs2 = cs;
            if (!com.easypano.tw.g.q) {
                if (cs == null) {
                    return;
                }
                cs2 = cs;
            }
            cs2.a(b);
        }
    }
    
    void stopAudioClip(final cs cs) {
        if (cs != null) {
            cs.stop();
        }
    }
    
    void a() {
        final boolean q = com.easypano.tw.g.q;
        final boolean c = this.c;
        if (!q && !c) {}
        this.c = c;
        bv bv = this;
        if (!q) {
            if (this.c) {
                this.stopAudioClip();
                if (!q) {
                    return;
                }
            }
            this.a(this.g, this.d);
            bv = this;
        }
        bv.a(this.f, this.e);
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
