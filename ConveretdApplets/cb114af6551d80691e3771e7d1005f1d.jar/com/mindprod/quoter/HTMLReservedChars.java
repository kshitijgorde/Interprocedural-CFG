// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.entities.EntifyStrings;

public final class HTMLReservedChars extends Translator
{
    public String process(final String raw) {
        assert raw != null : "HTMLReservedChars.process raw must not be null";
        return EntifyStrings.entifyHTML(raw);
    }
}
