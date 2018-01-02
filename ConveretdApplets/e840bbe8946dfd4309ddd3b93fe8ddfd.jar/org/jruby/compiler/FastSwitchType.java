// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.RubySymbol;
import org.jruby.RubyString;
import org.jruby.RubyFixnum;

public enum FastSwitchType
{
    FIXNUM((Class)RubyFixnum.class), 
    SINGLE_CHAR_STRING((Class)RubyString.class), 
    STRING((Class)RubyString.class), 
    SINGLE_CHAR_SYMBOL((Class)RubySymbol.class), 
    SYMBOL((Class)RubySymbol.class);
    
    private final Class associatedClass;
    
    private FastSwitchType(final Class associatedClass) {
        this.associatedClass = associatedClass;
    }
    
    public Class getAssociatedClass() {
        return this.associatedClass;
    }
}
