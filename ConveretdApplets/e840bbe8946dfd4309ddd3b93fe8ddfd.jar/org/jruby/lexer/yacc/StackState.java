// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class StackState implements Cloneable
{
    private long stack;
    
    public StackState() {
        this.stack = 0L;
    }
    
    public void reset() {
        this.reset(0L);
    }
    
    public void reset(final long backup) {
        this.stack = backup;
    }
    
    public long begin() {
        final long old = this.stack;
        this.stack <<= 1;
        this.stack |= 0x1L;
        return old;
    }
    
    public void end() {
        this.stack >>= 1;
    }
    
    public void stop() {
        this.stack <<= 1;
    }
    
    public void restart() {
        this.stack |= (this.stack & 0x1L) << 1;
        this.stack >>= 1;
    }
    
    public boolean isInState() {
        return (this.stack & 0x1L) != 0x0L;
    }
}
