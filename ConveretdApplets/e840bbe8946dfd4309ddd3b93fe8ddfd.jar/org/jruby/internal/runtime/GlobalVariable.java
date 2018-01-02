// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import org.jruby.runtime.ThreadContext;
import org.jruby.RubyProc;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.ArrayList;
import org.jruby.runtime.IAccessor;

public final class GlobalVariable
{
    private IAccessor accessor;
    private ArrayList<IRubyObject> traces;
    private boolean tracing;
    
    public GlobalVariable(final IAccessor accessor) {
        this.traces = null;
        this.accessor = accessor;
    }
    
    public static GlobalVariable newUndefined(final Ruby runtime, final String name) {
        final GlobalVariable variable = new GlobalVariable(null);
        variable.setAccessor(new UndefinedAccessor(runtime, variable, name));
        return variable;
    }
    
    public IAccessor getAccessor() {
        return this.accessor;
    }
    
    public ArrayList getTraces() {
        return this.traces;
    }
    
    public void addTrace(final RubyProc command) {
        if (this.traces == null) {
            this.traces = new ArrayList<IRubyObject>();
        }
        this.traces.add(command);
    }
    
    public boolean removeTrace(final IRubyObject command) {
        if (this.traces == null || !this.traces.contains(command)) {
            return false;
        }
        this.traces.remove(command);
        return true;
    }
    
    public void removeTraces() {
        this.traces = null;
    }
    
    public void setAccessor(final IAccessor accessor) {
        this.accessor = accessor;
    }
    
    public boolean isTracing() {
        return this.tracing;
    }
    
    public void trace(final IRubyObject value) {
        if (this.traces == null) {
            return;
        }
        final ThreadContext context = value.getRuntime().getCurrentContext();
        if (context.isWithinTrace()) {
            return;
        }
        try {
            context.setWithinTrace(true);
            for (int i = 0; i < this.traces.size(); ++i) {
                this.traces.get(i).call(context, new IRubyObject[] { value });
            }
        }
        finally {
            context.setWithinTrace(false);
        }
    }
}
