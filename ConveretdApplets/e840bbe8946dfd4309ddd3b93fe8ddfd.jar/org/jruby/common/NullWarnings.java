// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.common;

import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.Ruby;

public class NullWarnings implements IRubyWarnings
{
    private Ruby runtime;
    
    public NullWarnings(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public boolean isVerbose() {
        return false;
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public void warn(final ID id, final String message, final Object... data) {
    }
    
    public void warning(final ID id, final String message, final Object... data) {
    }
    
    public void warn(final ID id, final ISourcePosition position, final String message, final Object... data) {
    }
    
    public void warn(final ID id, final String fileName, final int lineNumber, final String message, final Object... data) {
    }
    
    public void warning(final ID id, final ISourcePosition position, final String message, final Object... data) {
    }
    
    public void warning(final ID id, final String fileName, final int lineNumber, final String message, final Object... data) {
    }
}
