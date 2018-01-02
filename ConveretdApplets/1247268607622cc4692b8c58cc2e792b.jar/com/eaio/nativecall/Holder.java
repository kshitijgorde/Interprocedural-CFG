// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

public class Holder
{
    private Object o;
    
    public final Object get() {
        return this.o;
    }
    
    public int hashCode() {
        final int hashCode = this.getClass().getName().hashCode();
        int hashCode2 = 0;
        if (this.o != null) {
            hashCode2 = this.o.hashCode();
        }
        return hashCode ^ hashCode2;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Holder)) {
            return false;
        }
        final Holder h = (Holder)obj;
        boolean equals;
        if (this.o == null) {
            equals = false;
            if (h.o == null) {
                equals = true;
            }
        }
        else {
            equals = this.o.equals(h.o);
        }
        return equals;
    }
    
    public final String toString() {
        return this.toStringBuffer(null).toString();
    }
    
    public StringBuffer toStringBuffer(StringBuffer in) {
        if (in == null) {
            in = new StringBuffer(32);
        }
        else {
            in.ensureCapacity(in.length() + 32);
        }
        in.append("{ Holder: o = ");
        in.append(this.o);
        in.append(" }");
        return in;
    }
    
    public Holder(Object o) {
        if (o instanceof Holder) {
            throw new ClassCastException();
        }
        if (o == null) {
            o = new Integer(0);
        }
        this.o = o;
    }
}
