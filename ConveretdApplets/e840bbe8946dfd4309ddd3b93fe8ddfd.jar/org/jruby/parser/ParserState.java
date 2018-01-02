// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.lexer.yacc.RubyYaccLexer;

public interface ParserState
{
    Object execute(final ParserSupport p0, final RubyYaccLexer p1, final Object p2, final Object[] p3, final int p4);
}
