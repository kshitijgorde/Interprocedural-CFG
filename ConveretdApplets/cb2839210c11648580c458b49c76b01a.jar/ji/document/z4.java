// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.image.jiJ2Interface;
import java.io.OutputStream;
import ji.graphic.jiImageButton;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Frame;
import java.awt.PrintJob;
import ji.sec.f;
import ji.clip.gz;
import java.awt.Insets;
import ji.v1event.ob;
import ji.v1event.a9;
import java.util.Enumeration;
import ji.awt.ax;
import ji.wang.ej;
import ji.annotate.b8;
import ji.burn.jiBurnerListener;
import ji.font.ct;
import ji.annotate.gj;
import ji.image.ev;
import ji.annotate.df;
import ji.annotate.dg;
import ji.filter.r9;
import ji.image.ds;
import ji.filter.ew;
import ji.filter.fx;
import ji.res.ay;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ji.v1event.c9;
import java.util.EventObject;
import java.io.IOException;
import ji.v1event.a6;
import ji.util.bh;
import java.util.Properties;
import ji.v1event.ao;
import ji.v1event.fz;
import ji.image.dv;
import ji.image.em;
import ji.adjustment.fi;
import ji.adjustment.eh;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import ji.v1event.a3;
import ji.v1event.d7;
import java.awt.Point;
import java.awt.Image;
import java.net.URL;
import ji.util.i;
import java.awt.Cursor;
import java.awt.LayoutManager;
import ji.v1event.b;
import java.awt.Component;
import ji.util.e;
import ji.io.h;
import ji.util.d;
import java.awt.Dimension;
import ji.ext.v;
import ji.v1event.a2;
import ji.io.p;
import ji.v1event.fv;
import ji.image.dx;
import ji.ext.fo;
import ji.util.jiPrinti;
import ji.awt.bb;
import ji.io.at;
import ji.io.q;
import ji.graphic.dq;
import java.awt.Rectangle;
import ji.image.cy;
import ji.net.a0;
import ji.awt.c;
import ji.filter.ck;
import ji.io.ac;
import ji.image.o7;
import ji.v1event.cx;
import ji.v1event.ak;
import java.awt.event.ActionListener;
import ji.v1event.ah;
import ji.v1event.ag;
import ji.v1event.ae;
import ji.v1event.af;
import ji.v1base.jiPanel;

class z4 implements Runnable
{
    private final /* synthetic */ cw a;
    
    z4(final cw a) {
        this.a = a;
    }
    
    public void run() {
        this.a.fz();
        this.a.bb(true);
        this.a.d5();
    }
}
