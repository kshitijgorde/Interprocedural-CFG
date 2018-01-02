// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.scope;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.DynamicScope;
import org.jruby.parser.StaticScope;

public class DummyDynamicScope extends NoVarsDynamicScope
{
    private static final int SIZE = 0;
    private static final String GROW_ERROR = "DummyDynamicScope cannot be grown; use ManyVarsDynamicScope";
    
    public DummyDynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        super(staticScope, parent);
    }
    
    public DummyDynamicScope(final StaticScope staticScope) {
        super(staticScope);
    }
    
    public void growIfNeeded() {
        this.growIfNeeded(0, "DummyDynamicScope cannot be grown; use ManyVarsDynamicScope");
    }
    
    protected void growIfNeeded(final int size, final String message) {
        if (this.staticScope.getNumberOfVariables() != size) {
            throw new RuntimeException(message);
        }
    }
    
    public DynamicScope cloneScope() {
        return new DummyDynamicScope(this.staticScope, this.parent);
    }
    
    public IRubyObject getBackRef(final Ruby runtime) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.getBackRef(runtime);
        }
        if (runtime.getDebug().isTrue()) {
            runtime.getWarnings().warn("DummyDynamicScope should never be used for backref storage");
        }
        return runtime.getNil();
    }
    
    public IRubyObject setBackRef(final IRubyObject backref) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.setBackRef(backref);
        }
        if (backref.getRuntime().getDebug().isTrue()) {
            backref.getRuntime().getWarnings().warn("DummyDynamicScope should never be used for backref storage");
        }
        return backref;
    }
    
    public IRubyObject getLastLine(final Ruby runtime) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.getLastLine(runtime);
        }
        if (runtime.getDebug().isTrue()) {
            runtime.getWarnings().warn("DummyDynamicScope should never be used for lastline storage");
        }
        return runtime.getNil();
    }
    
    public IRubyObject setLastLine(final IRubyObject lastline) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.setLastLine(lastline);
        }
        if (lastline.getRuntime().getDebug().isTrue()) {
            lastline.getRuntime().getWarnings().warn("DummyDynamicScope should never be used for backref storage");
        }
        return lastline;
    }
}
