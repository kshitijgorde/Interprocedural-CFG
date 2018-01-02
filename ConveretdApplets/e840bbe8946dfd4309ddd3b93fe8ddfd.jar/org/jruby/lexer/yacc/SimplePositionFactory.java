// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class SimplePositionFactory
{
    private LexerSource source;
    private ISourcePosition lastPosition;
    
    public SimplePositionFactory(final LexerSource source, final int line) {
        this.source = source;
        this.lastPosition = new SimpleSourcePosition(source.getFilename(), line);
    }
    
    public ISourcePosition getPosition(final ISourcePosition startPosition) {
        if (startPosition != null) {
            return this.lastPosition = startPosition;
        }
        return this.getPosition();
    }
    
    public ISourcePosition getPosition() {
        if (this.lastPosition.getStartLine() == this.source.getLine()) {
            return this.lastPosition;
        }
        return this.lastPosition = new SimpleSourcePosition(this.source.getFilename(), this.source.getLine());
    }
}
