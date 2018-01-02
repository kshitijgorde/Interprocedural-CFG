// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.util;

import java.util.NoSuchElementException;
import java.util.Vector;
import jlog.$H4;
import java.util.Enumeration;

public class $OKD implements Enumeration, $H4
{
    private Object[] $IOD;
    private int $N5;
    
    public $OKD(final Object o) {
        (this.$IOD = new Object[1])[0] = o;
        this.$N5 = 0;
    }
    
    public $OKD(final Enumeration enumeration) {
        final Vector vector = new Vector<Object>();
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
        vector.copyInto(this.$IOD = new Object[vector.size()]);
        this.$N5 = 0;
    }
    
    public $OKD(final Object[] $iod) {
        this.$IOD = $iod;
        this.$N5 = 0;
    }
    
    public synchronized boolean hasMoreElements() {
        return this.$N5 != this.$IOD.length;
    }
    
    public synchronized Object nextElement() {
        if (this.$N5 == this.$IOD.length) {
            throw new NoSuchElementException();
        }
        return this.$IOD[this.$N5++];
    }
}
