// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import java.util.Comparator;

class A implements Comparator<B>
{
    public int A(final B b, final B b2) {
        String s = null;
        if (b.A != null) {
            s = b.A.getClass().getName();
        }
        if (b.C != null) {
            s = b.C.getClass().getName();
        }
        if (b.B != null) {
            s = b.B.getClass().getName();
        }
        String s2 = null;
        if (b2.A != null) {
            s2 = b2.A.getClass().getName();
        }
        if (b2.C != null) {
            s2 = b2.C.getClass().getName();
        }
        if (b2.B != null) {
            s2 = b2.B.getClass().getName();
        }
        return s.compareTo(s2);
    }
}
