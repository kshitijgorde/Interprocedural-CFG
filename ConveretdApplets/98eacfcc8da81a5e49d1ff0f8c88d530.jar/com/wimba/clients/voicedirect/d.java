// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import java.util.Enumeration;
import VT_6_1_0_11.bG;
import java.util.Vector;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;

public final class d extends AbstractListModel implements ListModel
{
    private Vector a;
    
    public d() {
        this.a = new Vector();
    }
    
    public final void a(final bG bg) {
        final int n = this.a.size() - 1;
        this.a.removeAllElements();
        if (bg.a() != null) {
            this.a.add(new m(bg.a(), 2));
        }
        final Enumeration<String> elements = (Enumeration<String>)bg.b().elements();
        while (elements.hasMoreElements()) {
            this.a.add(new m(elements.nextElement(), 1));
        }
        final Enumeration<String> elements2 = (Enumeration<String>)bg.c().elements();
        while (elements2.hasMoreElements()) {
            this.a.add(new m(elements2.nextElement(), 0));
        }
        this.fireContentsChanged(this, 0, n);
    }
    
    public final int getSize() {
        return this.a.size();
    }
    
    public final Object getElementAt(final int n) {
        return this.a.elementAt(n);
    }
}
