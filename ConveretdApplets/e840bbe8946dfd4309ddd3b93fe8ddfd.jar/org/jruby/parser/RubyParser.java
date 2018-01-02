// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.common.IRubyWarnings;
import java.io.IOException;
import org.jruby.lexer.yacc.LexerSource;

public interface RubyParser
{
    RubyParserResult parse(final ParserConfiguration p0, final LexerSource p1) throws IOException;
    
    void setWarnings(final IRubyWarnings p0);
}
