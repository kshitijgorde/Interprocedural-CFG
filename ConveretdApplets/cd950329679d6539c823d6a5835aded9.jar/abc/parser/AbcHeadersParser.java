// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.TokenType;
import scanner.Set;

public class AbcHeadersParser extends AbcFileParser
{
    public AbcHeadersParser() {
        this.FIRST_ABC_LINE = new Set(AbcTokenType.TEXT);
    }
    
    protected void parseAbcLine(final Set follow) {
        final Set current = new Set(AbcTokenType.LINE_FEED);
        this.accept(AbcTokenType.TEXT, current, follow);
        current.remove(AbcTokenType.LINE_FEED);
        this.accept(AbcTokenType.LINE_FEED, current, follow);
    }
}
