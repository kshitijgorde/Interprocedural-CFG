// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.common;

import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.Ruby;
import org.joni.WarnCallback;

public class RubyWarnings implements IRubyWarnings, WarnCallback
{
    private final Ruby runtime;
    
    public RubyWarnings(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public void warn(final String message) {
        this.warn(ID.MISCELLANEOUS, message, new Object[0]);
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public void warn(final ID id, final ISourcePosition position, final String message, final Object... data) {
        this.warn(id, position.getFile(), position.getStartLine(), message, data);
    }
    
    public void warn(final ID id, final String fileName, final int lineNumber, final String message, final Object... data) {
        if (!this.runtime.warningsEnabled()) {
            return;
        }
        final StringBuilder buffer = new StringBuilder(100);
        buffer.append(fileName).append(':').append(lineNumber + 1).append(' ');
        buffer.append("warning: ").append(message).append('\n');
        final IRubyObject errorStream = this.runtime.getGlobalVariables().get("$stderr");
        errorStream.callMethod(this.runtime.getCurrentContext(), "write", this.runtime.newString(buffer.toString()));
    }
    
    public boolean isVerbose() {
        return this.runtime.isVerbose();
    }
    
    public void warn(final ID id, final String message, final Object... data) {
        final ThreadContext context = this.runtime.getCurrentContext();
        this.warn(id, context.getFile(), context.getLine(), message, data);
    }
    
    public void warning(final String message, final Object... data) {
        this.warning(ID.MISCELLANEOUS, message, data);
    }
    
    public void warning(final ID id, final String message, final Object... data) {
        final ThreadContext context = this.runtime.getCurrentContext();
        this.warning(id, context.getFile(), context.getLine(), message, data);
    }
    
    public void warning(final ID id, final ISourcePosition position, final String message, final Object... data) {
        this.warning(id, position.getFile(), position.getStartLine(), message, data);
    }
    
    public void warning(final ID id, final String fileName, final int lineNumber, final String message, final Object... data) {
        assert this.isVerbose();
        this.warn(id, fileName, lineNumber, message, data);
    }
}
