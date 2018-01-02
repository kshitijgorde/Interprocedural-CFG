// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import com.daysofwonder.util.y;
import java.awt.FontMetrics;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.awt.Color;
import java.awt.Font;
import com.daysofwonder.util.K;

public class ab extends am implements C
{
    protected K a;
    protected String b;
    protected Font c;
    protected Color d;
    protected int e;
    protected int f;
    protected boolean g;
    protected int h;
    private static final int[] i;
    private static final String[] j;
    
    public ab(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.e = -1;
        this.f = -1;
        this.g = true;
        this.h = 0;
        if (uiProperties.a(s + ".text") != null) {
            this.b = uiProperties2.a(uiProperties.a(s + ".text"));
        }
        this.d = aL.b(uiProperties, s + ".color");
        if (uiProperties.a(s + ".size") != null) {
            this.e = Integer.parseInt(uiProperties.a(s + ".size"));
        }
        if (uiProperties.a(s + ".style") != null) {
            final String a = uiProperties.a(s + ".style");
            for (int i = 0; i < ab.j.length; ++i) {
                if (ab.j[i].equalsIgnoreCase(a)) {
                    this.h = ab.i[i];
                    break;
                }
            }
        }
        if (uiProperties.a(s + ".align") != null) {
            final String a2 = uiProperties.a(s + ".align");
            if (a2.equalsIgnoreCase("LEFT")) {
                this.f = 0;
            }
            else if (a2.equalsIgnoreCase("RIGHT")) {
                this.f = 2;
            }
            else if (a2.equalsIgnoreCase("CENTER")) {
                this.f = 1;
            }
        }
        if (uiProperties.a(s + ".font") != null) {
            this.c = new Font(uiProperties.a(s + ".font"), this.h, this.e);
        }
    }
    
    public void a() {
    }
    
    public void a(final String b) {
        this.b = b;
        this.g = true;
    }
    
    public void a(final a a) {
        if (this.K && this.b != null) {
            final Color c = a.c();
            if (this.d != null) {
                a.a(this.d);
            }
            final Font b = a.b();
            if (this.c != null) {
                a.a(this.c);
            }
            else if (this.e != -1) {
                a.a(new Font(b.getName(), b.getStyle(), this.e));
            }
            else if (this.h != 0) {
                a.a(new Font(b.getName(), this.h, b.getSize()));
            }
            else if (this.h != 0 && this.e != -1) {
                a.a(new Font(b.getName(), this.h, this.e));
            }
            final FontMetrics d = a.d();
            if (this.f == -1 && (this.a == null || this.g)) {
                this.a = aL.a(this.b, this.G.width - 15, d, false);
                this.g = false;
            }
            if (this.f != -1) {
                aL.a(a, this.G, this.b, a.b(), this.f);
            }
            else {
                int n = this.G.y + d.getAscent();
                final int n2 = d.getDescent() + d.getAscent() + 1;
                if (this.a != null) {
                    final y e = this.a.e();
                    while (e.a()) {
                        a.a((String)e.b(), this.G.x + 1, n);
                        n += n2;
                    }
                }
            }
            if (this.c != null || this.h != 0 || this.e != -1) {
                a.a(b);
            }
            if (this.d != null) {
                a.a(c);
            }
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    static {
        i = new int[] { 0, 1, 2 };
        j = new String[] { "plain", "bold", "italic" };
    }
}
