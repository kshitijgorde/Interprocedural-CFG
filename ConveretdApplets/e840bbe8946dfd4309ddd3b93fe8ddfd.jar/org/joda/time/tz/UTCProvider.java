// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.tz;

import java.util.Collections;
import java.util.Set;
import org.joda.time.DateTimeZone;

public final class UTCProvider implements Provider
{
    public DateTimeZone getZone(final String s) {
        if ("UTC".equalsIgnoreCase(s)) {
            return DateTimeZone.UTC;
        }
        return null;
    }
    
    public Set getAvailableIDs() {
        return Collections.singleton("UTC");
    }
}
