// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.util.Enumeration;
import javax.swing.SwingUtilities;
import java.util.Vector;

public class e
{
    private Vector a;
    
    public e() {
        this.a = new Vector();
    }
    
    public final void a(final d d) {
        synchronized (this.a) {
            if (d != null && !this.a.contains(d)) {
                this.a.addElement(d);
            }
        }
    }
    
    public final void b(final d d) {
        synchronized (this.a) {
            if (this.a != null) {
                this.a.removeElement(d);
            }
        }
    }
    
    public final void a() {
        synchronized (this.a) {
            this.a.removeAllElements();
        }
    }
    
    public final void a(final f f, final boolean b) {
        if (b) {
            SwingUtilities.invokeLater(f);
            return;
        }
        synchronized (this.a) {
            final Enumeration<d> elements = this.a.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(f);
            }
        }
    }
}
