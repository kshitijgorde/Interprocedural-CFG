// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

class JavaRegexSearchCSV extends JavaRegexSearch
{
    public String process(final String raw) {
        assert raw != null : "TextProcessor.process raw must not be null";
        return new ToCSV().process(super.process(raw));
    }
}
