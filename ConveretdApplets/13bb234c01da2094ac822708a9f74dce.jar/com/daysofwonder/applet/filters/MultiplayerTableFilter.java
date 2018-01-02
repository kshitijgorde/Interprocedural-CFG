// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet.filters;

import com.daysofwonder.tt.m;
import com.daysofwonder.applet.ar;

public class MultiplayerTableFilter implements TableFilter
{
    public boolean a(final ar ar) {
        if (ar.l) {
            return ar.o > 2 && ((m)ar.q).e() > 2;
        }
        return ar.c.length > 2;
    }
}
