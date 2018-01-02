// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Point;
import ji.annotate.dg;
import ji.v1base.bz;
import java.awt.Dimension;
import ji.util.m;
import ji.util.d;
import ji.document.ad;
import ji.image.cy;
import ji.util.i;
import java.awt.Component;
import ji.util.e;
import ji.v1event.d8;
import ji.v1base.jiPanel;
import java.awt.Color;
import ji.v1event.c9;

class wl implements Runnable
{
    private final /* synthetic */ c8 a;
    private final /* synthetic */ Object b;
    
    wl(final c8 a, final Object b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        this.a.b(this.b);
    }
}
