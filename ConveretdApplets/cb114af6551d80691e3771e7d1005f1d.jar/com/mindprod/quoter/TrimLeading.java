// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.common11.ST;
import java.util.StringTokenizer;

final class TrimLeading extends TextProcessor
{
    String process(final String raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder stripped = new StringBuilder(raw.length());
        final StringTokenizer st = new StringTokenizer(raw, "\n", true);
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();
            if (token.equals("\n")) {
                stripped.append("\n");
            }
            else {
                stripped.append(ST.trimLeading(token));
            }
        }
        return stripped.toString();
    }
}
