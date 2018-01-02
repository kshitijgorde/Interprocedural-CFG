// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import javax.swing.border.Border;
import javax.swing.JComponent;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JApplet;

public class H extends JApplet
{
    private I e;
    private f c;
    private l d;
    private x b;
    private k a;
    
    public void init() {
        this.a();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.b, "Center");
        this.setSize(600, 500);
    }
    
    public void start() {
        this.b.b();
    }
    
    private void a() {
        final Border etchedBorder = BorderFactory.createEtchedBorder(Color.cyan, Color.blue);
        try {
            this.b = new v(this, new URL(this.getDocumentBase(), "images.zip"));
        }
        catch (MalformedURLException ex) {
            n.a(ex);
        }
        final URL url = null;
        this.e = new r(this);
        this.c = new z(this, url);
        this.d = new E(this);
        this.a = new D(this, this);
        this.b.setBorder(etchedBorder);
        this.e.setBorder(etchedBorder);
        this.c.setBorder(etchedBorder);
        this.d.setBorder(etchedBorder);
        this.a.setBorder(etchedBorder);
        this.a(this.b);
    }
    
    private void a(final JComponent component) {
        this.getContentPane().removeAll();
        this.getContentPane().add(component, "Center");
        component.requestFocus();
        this.validate();
        this.repaint();
    }
    
    static l d(final H h) {
        return h.d;
    }
    
    static void a(final H h, final JComponent component) {
        h.a(component);
    }
    
    static f c(final H h) {
        return h.c;
    }
    
    static I a(final H h) {
        return h.e;
    }
    
    static k b(final H h) {
        return h.a;
    }
}
