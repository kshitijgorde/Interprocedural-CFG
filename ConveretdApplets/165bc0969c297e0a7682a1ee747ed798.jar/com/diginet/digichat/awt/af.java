// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Container;
import com.diginet.digichat.util.Finder;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

public class af extends ag
{
    private bj a;
    private a9 b;
    private a9 a9Shield;
    private aw c;
    private String d;
    
    public Component c(final int n) {
        if (this.a == null) {
            this.a = new bj(5, 7, 5, 7, n);
            this.c = new aw();
            this.b = new a9();
            this.a9Shield = new a9();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.b);
            this.a.add("Center", this.c);
            this.a.add("East", this.a9Shield);
            this.a.setFont(dw.c);
            this.c.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(dw.c);
            this.c.resize(80, fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    final Object[] component;
                    super.a = (Component)(component = Finder.findComponent(event, this))[0];
                    final String d;
                    if (!(d = (String)component[1]).equals(this.d)) {
                        this.d = d;
                        this.c.a(d);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && super.a == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.d == null || !this.d.equals(event.arg)) {
                        this.d = (String)event.arg;
                        this.c.a((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void a(final Image image) {
        this.b.b(image);
    }
    
    public void setShield(final Image image) {
        this.a9Shield.b(image);
    }
    
    public af(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
    
    public af(final Frame frame, final boolean b) {
        super(frame, b);
    }
}
