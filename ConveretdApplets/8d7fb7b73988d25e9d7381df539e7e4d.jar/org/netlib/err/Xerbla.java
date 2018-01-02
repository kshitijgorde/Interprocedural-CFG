// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.err;

public final class Xerbla
{
    public static void xerbla(final String s, final int n) {
        System.out.println(" ** On entry to " + s + " " + " parameter number " + n + " " + " had " + "an illegal value");
        System.exit(0);
    }
}
