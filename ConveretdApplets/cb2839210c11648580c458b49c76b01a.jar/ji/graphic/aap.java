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
import ji.v1base.jiPanel;
import java.awt.Color;
import ji.v1event.c9;
import ji.v1event.d8;

class aap implements Runnable
{
    private final /* synthetic */ c8 a;
    private final /* synthetic */ d8 b;
    
    aap(final c8 a, final d8 b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        this.a.c(this.b);
    }
}
