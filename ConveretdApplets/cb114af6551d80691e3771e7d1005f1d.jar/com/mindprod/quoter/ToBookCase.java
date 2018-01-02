// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.common11.ST;

final class ToBookCase extends TextProcessor
{
    String process(final String raw) {
        if (raw == null) {
            return null;
        }
        return ST.toBookTitleCase(raw);
    }
}
