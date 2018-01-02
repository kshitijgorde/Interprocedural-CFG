// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.image.ImageObserver;
import ji.v1event.af;
import ji.document.ad;
import ji.v1event.d7;
import ji.v1event.a9;
import ji.util.i;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import ji.v1event.a6;
import ji.util.e;
import ji.util.d;
import java.awt.SystemColor;
import ji.v1event.a2;
import java.awt.Component;
import ji.v1event.ah;
import java.awt.event.MouseAdapter;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import ji.v1event.ak;
import ji.v1base.bz;

class t2 implements Runnable
{
    private final /* synthetic */ cz a;
    
    t2(final cz a) {
        this.a = a;
    }
    
    public void run() {
        this.a.b(this.a.q);
    }
}
