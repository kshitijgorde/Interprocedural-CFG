// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Vector;
import java.awt.Component;

public class u
{
    private Component a;
    private Vector b;
    private Vector c;
    private boolean d;
    
    public u(final Component a) {
        this.b = new Vector();
        this.c = new Vector();
        this.a = a;
    }
    
    public synchronized void a(final Image image) {
        this.b.addElement(image);
        this.a.prepareImage(image, this.a);
    }
    
    public synchronized void b(final Image image) {
        if (this.b.contains(image)) {
            this.c.addElement(image);
            this.b.removeElement(image);
            this.notifyAll();
        }
    }
    
    public synchronized boolean a() {
        while (!this.d && this.b.size() > 0) {
            try {
                this.wait();
                continue;
            }
            catch (Exception ex) {
                return false;
            }
            break;
        }
        return this.c.size() == 0;
    }
    
    public synchronized boolean a(final c c) {
        int n2;
        final int n = n2 = this.b.size();
        if (c != null) {
            c.a(0, n);
        }
        while (!this.d && this.b.size() > 0) {
            try {
                this.wait();
                if (this.b.size() == n2) {
                    continue;
                }
                if (c != null) {
                    c.a(n - this.b.size(), n);
                }
                n2 = this.b.size();
                continue;
            }
            catch (Exception ex) {
                return false;
            }
            break;
        }
        return this.c.size() == 0;
    }
    
    public synchronized void c(final Image image) {
        this.b.removeElement(image);
        this.notifyAll();
    }
    
    public synchronized boolean d(final Image image) {
        return this.c.contains(image);
    }
}
