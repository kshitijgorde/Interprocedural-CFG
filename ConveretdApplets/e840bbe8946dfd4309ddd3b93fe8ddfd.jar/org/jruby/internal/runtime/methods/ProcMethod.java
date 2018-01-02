// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.RubyProc;
import org.jruby.runtime.PositionAware;

public class ProcMethod extends DynamicMethod implements PositionAware
{
    private RubyProc proc;
    
    public ProcMethod(final RubyModule implementationClass, final RubyProc proc, final Visibility visibility) {
        super(implementationClass, visibility, null);
        this.proc = proc;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        return this.proc.call(context, args, self, block);
    }
    
    public DynamicMethod dup() {
        return new ProcMethod(this.getImplementationClass(), this.proc, this.getVisibility());
    }
    
    public boolean isSame(final DynamicMethod method) {
        return method instanceof ProcMethod && ((ProcMethod)method).proc == this.proc;
    }
    
    public Arity getArity() {
        return this.proc.getBlock().arity();
    }
    
    public String getFile() {
        return this.proc.getBlock().getBody().getFile();
    }
    
    public int getLine() {
        return this.proc.getBlock().getBody().getLine();
    }
}
