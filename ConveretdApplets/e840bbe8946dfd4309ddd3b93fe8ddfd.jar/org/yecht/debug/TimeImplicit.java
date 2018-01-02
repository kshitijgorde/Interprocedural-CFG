// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.debug;

import org.yecht.ImplicitScanner2;
import org.yecht.Pointer;

public class TimeImplicit
{
    public static void main(final String[] args) throws Exception {
        final Pointer ptr = Pointer.create("one little thing");
        final Pointer ptr2 = Pointer.create("13455");
        final int times = 10000000;
        final long before = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            ImplicitScanner2.matchImplicit(ptr, 16);
            ImplicitScanner2.matchImplicit(ptr2, 5);
        }
        final long after = System.currentTimeMillis();
        System.err.println("implicit a string and a number " + times + " times took " + (after - before) + "ms");
    }
}
