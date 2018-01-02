// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.marshal;

import java.io.IOException;
import org.jruby.RubySymbol;
import java.util.HashMap;
import java.util.IdentityHashMap;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;

public class MarshalCache
{
    private final Map<IRubyObject, Integer> linkCache;
    private final Map<String, Integer> symbolCache;
    
    public MarshalCache() {
        this.linkCache = new IdentityHashMap<IRubyObject, Integer>();
        this.symbolCache = new HashMap<String, Integer>();
    }
    
    public boolean isRegistered(final IRubyObject value) {
        if (value instanceof RubySymbol) {
            return this.isSymbolRegistered(((RubySymbol)value).asJavaString());
        }
        return this.linkCache.containsKey(value);
    }
    
    public boolean isSymbolRegistered(final String sym) {
        return this.symbolCache.containsKey(sym);
    }
    
    public void register(final IRubyObject value) {
        if (value instanceof RubySymbol) {
            this.registerSymbol(value.asJavaString());
        }
        else {
            this.linkCache.put(value, this.linkCache.size());
        }
    }
    
    public void registerSymbol(final String sym) {
        this.symbolCache.put(sym, this.symbolCache.size());
    }
    
    public void writeLink(final MarshalStream output, final IRubyObject value) throws IOException {
        if (value instanceof RubySymbol) {
            this.writeSymbolLink(output, ((RubySymbol)value).asJavaString());
        }
        else {
            output.write(64);
            output.writeInt(this.registeredIndex(value));
        }
    }
    
    public void writeSymbolLink(final MarshalStream output, final String sym) throws IOException {
        output.write(59);
        output.writeInt(this.registeredSymbolIndex(sym));
    }
    
    private int registeredIndex(final IRubyObject value) {
        if (value instanceof RubySymbol) {
            return this.registeredSymbolIndex(value.asJavaString());
        }
        return this.linkCache.get(value);
    }
    
    private int registeredSymbolIndex(final String sym) {
        return this.symbolCache.get(sym);
    }
}
