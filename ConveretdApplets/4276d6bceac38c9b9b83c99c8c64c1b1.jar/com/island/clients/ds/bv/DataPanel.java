// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Canvas;

public class DataPanel extends Canvas
{
    Vector v;
    
    public DataPanel() {
        this.v = new Vector();
    }
    
    public void add(final DataLabel datalabel) {
        this.v.addElement(datalabel);
    }
    
    public void paint(final Graphics g) {
        final Enumeration enumeration = this.v.elements();
        while (enumeration.hasMoreElements()) {
            final DataLabel datalabel = enumeration.nextElement();
            if (datalabel.visible) {
                datalabel.update(g);
            }
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
