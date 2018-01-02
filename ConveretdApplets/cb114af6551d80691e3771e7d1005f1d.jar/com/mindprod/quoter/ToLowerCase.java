// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class ToLowerCase extends TextProcessor
{
    String process(final String raw) {
        if (raw == null) {
            return null;
        }
        return raw.toLowerCase();
    }
}
