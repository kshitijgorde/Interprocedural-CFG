// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

public enum RubyEvent
{
    LINE("line", 1), 
    CLASS("class", 1), 
    END("end", 1), 
    CALL("call", 1), 
    RETURN("return", 1), 
    C_CALL("c-call", 1), 
    C_RETURN("c-return", 1), 
    RAISE("raise", 1);
    
    private final String event_name;
    private final int line_number_offset;
    
    private RubyEvent(final String event_name, final int line_number_offset) {
        this.event_name = event_name;
        this.line_number_offset = line_number_offset;
    }
    
    public int getLineNumberOffset() {
        return this.line_number_offset;
    }
    
    public String getName() {
        return this.event_name;
    }
}
