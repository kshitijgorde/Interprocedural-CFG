// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.entities.DeEntifyStrings;

final class StripXMLEntities extends TextProcessor
{
    public String process(final String raw) {
        return DeEntifyStrings.deEntifyXML(raw);
    }
}
