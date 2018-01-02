// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.util.Iterator;
import java.util.Date;
import java.io.IOException;
import com.bullionvault.chart.a.r;
import java.util.HashSet;
import java.util.Set;

public final class i
{
    public static Set a(final String s) {
        final HashSet<r> set = new HashSet<r>();
        final b b = new b(s);
        try {
            b.a();
        }
        catch (IOException ex) {
            return set;
        }
        final f b2 = b.b();
        System.out.println("feed: " + b2.a);
        for (final c c : b2.a) {
            set.add(new r(new Date(Date.parse(c.c)), c.a));
        }
        return set;
    }
}
