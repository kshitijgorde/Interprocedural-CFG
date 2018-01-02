// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.v1event.g9;
import java.awt.event.ItemEvent;
import ji.v1event.fz;
import ji.annotate.df;
import java.awt.SystemColor;
import ji.v1event.b;
import ji.v1event.ae;
import ji.v1event.a6;
import java.awt.event.ItemListener;
import ji.awt.fu;
import ji.awt.ft;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Dimension;
import ji.v1event.fv;
import java.awt.event.ActionEvent;
import ji.v1event.a3;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Vector;
import ji.image.dv;
import ji.v1event.a9;
import ji.v1event.d7;
import ji.v1event.ag;
import java.net.URL;
import ji.res.z;
import ji.util.y;
import java.awt.Component;
import ji.v1event.as;
import ji.io.h;
import ji.util.d;
import ji.util.e;
import java.awt.Graphics;
import ji.util.i;
import ji.ext.v;
import ji.v1event.a2;
import ji.awt.fm;
import ji.net.a0;
import java.awt.Point;
import java.awt.Rectangle;
import ji.v1base.bz;
import ji.graphic.b3;
import ji.image.o7;
import java.awt.Cursor;
import ji.awt.bb;
import ji.image.cy;
import java.awt.Color;
import ji.image.dx;
import ji.awt.c;
import ji.filter.ck;
import ji.io.ac;
import ji.v1event.cx;
import ji.v1event.ak;
import java.awt.event.MouseMotionListener;
import ji.v1event.af;
import java.awt.event.MouseListener;
import ji.v1base.jiPanel;

class ws implements Runnable
{
    private final /* synthetic */ c7 a;
    
    ws(final c7 a) {
        this.a = a;
    }
    
    public void run() {
        this.a.b3();
    }
}
