// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.util.Vector;

public class Tie
{
    protected Vector items;
    
    public Tie() {
        this.items = new Vector(2);
    }
    
    public Tie(final Tieable tieable) {
        this.items = new Vector(2);
        this.add(tieable);
    }
    
    public Tie(final Tieable tieable, final Tieable tieable2) {
        this.items = new Vector(2);
        this.add(tieable);
        this.add(tieable2);
    }
    
    public void add(final Tieable tieable) {
        if (tieable != null) {
            this.items.addElement(tieable);
            this.forcecheck();
        }
    }
    
    public void check() {
        final int size = this.items.size();
        if (size < 2) {
            return;
        }
        long serialNumber = this.items.elementAt(0).getSerialNumber();
        int n = 0;
        boolean b = false;
        for (int i = 1; i < size; ++i) {
            final long serialNumber2 = this.items.elementAt(i).getSerialNumber();
            if (serialNumber2 != serialNumber) {
                b = true;
            }
            if (serialNumber2 > serialNumber) {
                serialNumber = serialNumber2;
                n = i;
            }
        }
        if (!b) {
            return;
        }
        final Tieable tieable = this.items.elementAt(n);
        for (int j = 0; j < size; ++j) {
            ((Tieable)this.items.elementAt(j)).sync(this, tieable);
        }
    }
    
    private void forcecheck() {
        final int size = this.items.size();
        if (size < 2) {
            return;
        }
        long serialNumber = this.items.elementAt(0).getSerialNumber();
        int n = 0;
        for (int i = 1; i < size; ++i) {
            final long serialNumber2 = this.items.elementAt(i).getSerialNumber();
            if (serialNumber2 != serialNumber) {}
            if (serialNumber2 > serialNumber) {
                serialNumber = serialNumber2;
                n = i;
            }
        }
        final Tieable tieable = this.items.elementAt(n);
        for (int j = 0; j < size; ++j) {
            ((Tieable)this.items.elementAt(j)).sync(this, tieable);
        }
    }
}
