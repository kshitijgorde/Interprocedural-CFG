// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.AWTEvent;
import java.awt.Component;
import flaxchat.e.g;
import java.awt.Graphics;
import flaxchat.i.b;
import flaxchat.i.a;
import java.applet.Applet;

public class j extends Applet
{
    public int a(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (this.b(parameter)) {
            try {
                return Integer.valueOf(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        final String a = flaxchat.i.a.a(s);
        try {
            return Integer.valueOf(a);
        }
        catch (NumberFormatException ex2) {
            final String c = b.c(s);
            try {
                return Integer.valueOf(c);
            }
            catch (NumberFormatException ex3) {
                return n;
            }
        }
    }
    
    public String a(final String s) {
        final String parameter = this.getParameter(s);
        if (this.b(parameter)) {
            return parameter;
        }
        return a.a(s);
    }
    
    public boolean a(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (this.b(parameter)) {
            return this.b(parameter, b);
        }
        return this.b(a.a(s), b);
    }
    
    private boolean b(final String s, final boolean b) {
        try {
            return Boolean.valueOf(s);
        }
        catch (RuntimeException ex) {
            return b;
        }
    }
    
    public boolean b(final String s) {
        return s != null && s.trim().length() != 0;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        g.a(graphics, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        super.processEvent(awtEvent);
    }
}
