// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.CompatVersion;

public class RubyParserPool
{
    private static RubyParserPool instance;
    
    public static RubyParserPool getInstance() {
        return RubyParserPool.instance;
    }
    
    public DefaultRubyParser borrowParser() {
        return new DefaultRubyParser();
    }
    
    public RubyParser borrowParser(final CompatVersion version) {
        if (version == CompatVersion.RUBY1_8) {
            return new DefaultRubyParser();
        }
        return new Ruby19Parser();
    }
    
    public void returnParser(final RubyParser parser) {
    }
    
    static {
        RubyParserPool.instance = new RubyParserPool();
    }
}
