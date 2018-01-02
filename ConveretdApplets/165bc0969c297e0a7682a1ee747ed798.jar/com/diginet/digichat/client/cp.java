// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Panel;
import java.awt.Component;
import com.diginet.digichat.awt.cv;
import java.awt.Event;
import java.util.Vector;
import java.awt.Container;
import java.awt.Frame;
import com.diginet.digichat.network.v;
import com.diginet.digichat.exceptions.dm;
import com.diginet.digichat.util.ImagesListener;
import com.diginet.digichat.common.bp;
import com.esial.util.c;
import com.diginet.digichat.awt.r;
import java.awt.TextField;
import java.awt.Color;
import com.diginet.digichat.awt.cq;
import com.diginet.digichat.awt.ah;

public class cp extends ah
{
    private cq a;
    private Color clrName;
    private Color clrBack;
    private Color clrNameBase;
    private Color clrBackBase;
    private TextField b;
    private r btnName;
    private r btnBack;
    private h k;
    
    public String a(final Object o) {
        if (o == this.b) {
            return com.esial.util.c.a("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        return null;
    }
    
    public void c() {
        this.b.setText(this.k.x());
        this.a.a();
        this.k.aa.a(false);
        try {
            synchronized (this.k.aa) {
                boolean b = false;
                for (int i = 0; i < this.k.aa.b(); ++i) {
                    final bp bp = (bp)this.k.aa.d(i);
                    if (!bp.i(36) || this.k.i(36)) {
                        b = (this.k.checkIcon(bp) || b);
                        this.a.a(bp);
                    }
                }
                if (b) {
                    this.k.iconsLoader.addListener(this.a);
                }
            }
            // monitorexit(this.k.aa)
        }
        finally {
            this.k.aa.a();
        }
        this.a.a(this.k.bp);
    }
    
    public void b() throws dm {
        final String trim = this.b.getText().trim();
        if (trim.length() == 0) {
            this.b.setText(this.k.x());
            throw new dm(com.esial.util.c.a("You must enter a name.  Please re-enter this information."));
        }
        if (v.a(this.k.as, 47) && trim.length() > 35) {
            this.b.setText(trim.substring(0, 34));
            throw new dm(com.esial.util.c.a("Nickname must be 35 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.b.getText().trim();
        final int w = this.a.b().w();
        final int colorValue = this.k.colorValue(this.clrName);
        final int colorValue2 = this.k.colorValue(this.clrBack);
        if (w != this.k.bp || colorValue != this.k.colorValue(this.k.clrName) || colorValue2 != this.k.colorValue(this.k.clrBack) || !trim2.equals(this.k.x())) {
            final v v = new v(67334, 1);
            v.k = -1;
            v.j = -1;
            v.a(0, 0, this.k.w());
            v.a(0, 1, w);
            v.a(0, 3, colorValue);
            v.a(0, 4, colorValue2);
            v.a(0, 0, trim2);
            v.f(-1, 61);
            this.k.an(v);
        }
    }
    
    private int getColor(final String s, final Color color, final int n) {
        Container parent = this;
        do {
            if ((parent = parent.getParent()) == null) {
                parent = new Frame();
            }
        } while (!(parent instanceof Frame));
        final Vector vector;
        return ((vector = this.k.palettes[n]) == null) ? new ColorDialog((Frame)parent, this.k).getResult(com.esial.util.c.a(s), color) : new PaletteBox((Frame)parent, this.k, vector).getResult(com.esial.util.c.a(s), color);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.btnName) {
                final int color;
                if ((color = this.getColor("Color of text of nickname.", (this.clrName == null) ? this.clrNameBase : this.clrName, this.k.isMaster() ? 5 : 4)) != 0) {
                    this.btnName.setForeground(this.clrName = new Color(color));
                }
                return true;
            }
            if (event.target == this.btnBack) {
                final int color2;
                if ((color2 = this.getColor("Color of background of nickname.", (this.clrBack == null) ? this.clrBackBase : this.clrBack, this.k.isMaster() ? 7 : 6)) != 0) {
                    this.btnBack.setForeground(this.clrBack = new Color(color2));
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cp(final h k) {
        super(com.esial.util.c.a("Profile"), k);
        this.a = new cq();
        this.b = new TextField(25);
        this.clrNameBase = (k.i(59) ? bu.c : (((k.i(61) || k.i(62)) && !k.i(23) && !k.i(79)) ? bu.a : ((k.i(23) || k.i(79)) ? bu.e : Color.black)));
        this.clrBackBase = cv.c;
        final r btnName = new r(com.esial.util.c.a("Set Color"));
        this.btnName = btnName;
        final Color clrName = k.clrName;
        this.clrName = clrName;
        btnName.setForeground((clrName == null) ? this.clrNameBase : this.clrName);
        final r btnBack = new r(com.esial.util.c.a("Set Color"));
        this.btnBack = btnBack;
        final Color clrBack = k.clrBack;
        this.clrBack = clrBack;
        btnBack.setForeground((clrBack == null) ? this.clrBackBase : this.clrBack);
        this.k = k;
        this.a(com.esial.util.c.a("Nickname"), new Component[] { this.b, this.btnName });
        this.b.enable(k.i(31));
        this.b.setEditable(k.i(31));
        this.a(com.esial.util.c.a("Background color"), new Component[] { new Panel(), this.btnBack });
        if (!k.i(104)) {
            this.btnName.c();
            this.btnBack.c();
        }
        if (k.i(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
