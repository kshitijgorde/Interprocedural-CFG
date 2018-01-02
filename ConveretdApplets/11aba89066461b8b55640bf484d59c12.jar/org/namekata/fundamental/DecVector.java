// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class DecVector
{
    private Frac value;
    private DecVector next;
    
    public DecVector(final Frac f) {
        this.value = null;
        this.next = null;
        this.value = f;
        this.next = null;
    }
    
    public DecVector() {
        this.value = null;
        this.next = null;
    }
    
    public DecVector add(final Frac f) {
        if (this.value == null) {
            this.value = f;
            this.next = null;
            return this;
        }
        final DecVector xx = new DecVector(f);
        final int len = this.getLength();
        switch (len) {
            case 1: {
                if (this.value.isLess(f)) {
                    xx.next = this;
                    return xx;
                }
                this.next = xx;
                return this;
            }
            default: {
                DecVector dec = this;
                DecVector prev = this;
                Frac x;
                int i;
                for (x = dec.value, i = 0; f.isLess(x); x = dec.value, ++i) {
                    prev = dec;
                    dec = dec.next;
                    if (dec == null) {
                        break;
                    }
                }
                if (i == 0) {
                    xx.next = this;
                    return xx;
                }
                final DecVector zz = prev.next;
                prev.next = xx;
                xx.next = zz;
                return this;
            }
        }
    }
    
    public boolean isGreater(final DecVector x) {
        if (x == null) {
            return false;
        }
        final int len = this.getLength();
        if (len != x.getLength()) {
            return false;
        }
        DecVector dec;
        DecVector y;
        int i;
        for (dec = this, y = x, i = 0; dec != null && dec.value.equals(y.value); dec = dec.next, y = y.next, ++i) {}
        return i != len && dec.value.isGreater(y.value);
    }
    
    public boolean equals(final DecVector x) {
        if (x == null) {
            return false;
        }
        final int len = this.getLength();
        if (len != x.getLength()) {
            return false;
        }
        for (DecVector dec = this, y = x; dec != null; dec = dec.next, y = y.next) {
            if (!dec.value.equals(y.value)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isLess(final DecVector x) {
        if (x == null) {
            return false;
        }
        final int len = this.getLength();
        if (len != x.getLength()) {
            return false;
        }
        DecVector dec;
        DecVector y;
        int i;
        for (dec = this, y = x, i = 0; dec != null && dec.value.equals(y.value); dec = dec.next, y = y.next, ++i) {}
        return i != len && dec.value.isLess(y.value);
    }
    
    public int getLength() {
        int ret = 1;
        for (DecVector dec = this; dec.next != null; dec = dec.next) {
            ++ret;
        }
        return ret;
    }
    
    public Frac[] getValues() {
        final Frac[] retval = new Frac[this.getLength()];
        int i = 0;
        if (this.value != null) {
            DecVector x = this;
            retval[i++] = x.value;
            while (x.next != null) {
                x = x.next;
                retval[i++] = x.value;
            }
        }
        return retval;
    }
    
    @Override
    public String toString() {
        String ret = this.value.toString();
        for (DecVector dec = this; dec.next != null; dec = dec.next, ret = String.valueOf(ret) + ", " + dec.value.toString()) {}
        return ret;
    }
}
