// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.marshal;

import java.io.IOException;
import java.util.ArrayList;
import org.jruby.RubySymbol;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.List;
import org.jruby.Ruby;

public class UnmarshalCache
{
    private final Ruby runtime;
    private final List<IRubyObject> links;
    private final List<RubySymbol> symbols;
    
    public UnmarshalCache(final Ruby runtime) {
        this.links = new ArrayList<IRubyObject>();
        this.symbols = new ArrayList<RubySymbol>();
        this.runtime = runtime;
    }
    
    public void register(final IRubyObject value) {
        this.selectCache(value).add(value);
    }
    
    private List selectCache(final IRubyObject value) {
        return (value instanceof RubySymbol) ? this.symbols : this.links;
    }
    
    public boolean isLinkType(final int c) {
        return c == 59 || c == 64;
    }
    
    public IRubyObject readLink(final UnmarshalStream input, final int type) throws IOException {
        final int i = input.unmarshalInt();
        if (type == 64) {
            return this.linkedByIndex(i);
        }
        assert type == 59;
        return this.symbolByIndex(i);
    }
    
    private IRubyObject linkedByIndex(final int index) {
        try {
            return this.links.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw this.runtime.newArgumentError("dump format error (unlinked, index: " + index + ")");
        }
    }
    
    private RubySymbol symbolByIndex(final int index) {
        try {
            return this.symbols.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw this.runtime.newTypeError("bad symbol");
        }
    }
}
