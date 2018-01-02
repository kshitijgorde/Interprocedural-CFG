// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

class JavaRegexReplaceString extends JavaRegexReplace
{
    public String process(final String raw) {
        assert raw != null : "TextProcessor.process raw must not be null";
        return new ToJavaStringLiteral().process(super.process(raw));
    }
}
