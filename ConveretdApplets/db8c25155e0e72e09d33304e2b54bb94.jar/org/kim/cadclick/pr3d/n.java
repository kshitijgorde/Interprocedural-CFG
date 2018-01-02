// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Vector;
import java.util.Hashtable;

abstract class n implements o, Cloneable
{
    protected String aB;
    protected int ay;
    protected Hashtable aA;
    protected n ax;
    protected Prove3d az;
    
    protected n(final String ab, final int ay, final Prove3d az) {
        this.aB = null;
        this.ax = null;
        this.aA = new Hashtable();
        this.aB = ab;
        this.ay = ay;
        this.az = az;
    }
    
    protected void for() {
        this.az.fieldEventOcurred = true;
    }
    
    protected void a(final String s, final d d) {
        this.aA.put(s, d);
    }
    
    protected void a(final float[] array, final float[] array2) {
    }
    
    public d do(final String s) throws Stv_X3DException {
        d d = this.aA.get(s);
        if (d == null) {
            if (s.startsWith("set_")) {
                d = this.aA.get(s.substring(4));
            }
            else if (s.endsWith("_changed")) {
                d = this.aA.get(s.substring(0, s.length() - 8));
            }
        }
        if (d == null) {
            throw new Stv_X3DException();
        }
        return d;
    }
    
    protected n do() {
        try {
            return (n)this.clone();
        }
        catch (Exception ex) {
            System.out.println("parsing: error while cloning");
            return null;
        }
    }
    
    protected void if(final String s) {
    }
    
    protected boolean if(final float[] array, final float[] array2) {
        return false;
    }
    
    protected void a(final Vector vector) {
    }
    
    protected int a(final Vector vector, final float n, final int n2) {
        return n2;
    }
}
