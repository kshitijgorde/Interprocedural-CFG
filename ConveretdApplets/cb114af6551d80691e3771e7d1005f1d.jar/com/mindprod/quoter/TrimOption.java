// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

public enum TrimOption
{
    TRIM((TextProcessor)new Trim(), "trim both leading and trailing spaces"), 
    TRIM_TRAILING((TextProcessor)new TrimTrailing(), "trim trailing spaces"), 
    KEEP_SPACES((TextProcessor)null, "keep spaces"), 
    TRIM_LEADING((TextProcessor)new TrimLeading(), "trim leading spaces");
    
    private final String desc;
    private final TextProcessor processor;
    
    public TextProcessor getProcessor() {
        return this.processor;
    }
    
    public String toString() {
        return this.desc;
    }
    
    private TrimOption(final TextProcessor processor, final String desc) {
        this.processor = processor;
        this.desc = desc;
    }
}
