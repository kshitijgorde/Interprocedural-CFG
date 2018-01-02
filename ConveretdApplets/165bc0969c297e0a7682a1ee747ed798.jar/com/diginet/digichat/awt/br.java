// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import com.diginet.digichat.util.Finder;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import com.diginet.digichat.util.Informer;

public class br extends bj implements Informer
{
    private bj a;
    private Component b;
    private String c;
    private a9 d;
    private a9 a9Shield;
    private aw e;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    final Object[] component;
                    this.b = (Component)(component = Finder.findComponent(event, this))[0];
                    final String c;
                    if (!(c = (String)component[1]).equals(this.c)) {
                        this.c = c;
                        this.e.a(c);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && this.b == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.c == null || !this.c.equals(event.arg)) {
                        this.c = (String)event.arg;
                        this.e.a((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void setInfo(final Component b, String c) {
        if (c == null) {
            c = "";
        }
        String c2;
        if ((c2 = this.c) == null) {
            c2 = "";
        }
        if (this.a != null && (this.b != b || !c.equals(c2))) {
            this.b = b;
            this.e.a(this.c = c);
        }
    }
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public Component a(final int n) {
        if (this.a == null) {
            this.a = new bj(5, 7, 5, 7, n);
            this.e = new aw();
            this.d = new a9();
            this.a9Shield = new a9();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.d);
            this.a.add("Center", this.e);
            this.a.add("East", this.a9Shield);
            this.a.setFont(dw.c);
            this.e.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(dw.c);
            this.e.resize(80, fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public void a(final Image image) {
        this.d.b(image);
    }
    
    public void a(final URL url) {
        this.d.a(url);
    }
    
    public void setShield(final Image image, final URL url) {
        this.a9Shield.b(image);
        this.a9Shield.a(url);
    }
    
    public void changeImage(final Image image, final URL url) {
        this.a.remove(this.d);
        final FontMetrics fontMetrics = this.getFontMetrics(dw.c);
        this.e.resize(this.e.size().width, fontMetrics.getHeight() + fontMetrics.getDescent());
        this.a.add("West", this.d = new a9());
        this.d.b(image);
        this.d.a(url);
    }
    
    public br() {
        this.b = null;
        this.setFont(dw.c);
        this.setBackground(cv.b);
    }
    
    public br(final int n) {
        super(n);
        this.b = null;
        this.setFont(dw.c);
        this.setBackground(cv.b);
    }
}
