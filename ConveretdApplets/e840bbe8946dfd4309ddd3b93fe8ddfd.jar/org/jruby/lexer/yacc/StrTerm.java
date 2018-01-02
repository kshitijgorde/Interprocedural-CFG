// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.IOException;

public abstract class StrTerm
{
    public abstract int parseString(final RubyYaccLexer p0, final LexerSource p1) throws IOException;
}
