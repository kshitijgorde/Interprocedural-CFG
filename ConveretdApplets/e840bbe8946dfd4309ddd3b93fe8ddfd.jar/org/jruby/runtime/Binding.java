// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;
import org.jruby.runtime.backtrace.BacktraceElement;

public class Binding
{
    private final Frame frame;
    private final BacktraceElement backtrace;
    private final RubyModule klass;
    private Visibility visibility;
    private IRubyObject self;
    private final DynamicScope dynamicScope;
    private DynamicScope dummyScope;
    
    public Binding(final IRubyObject self, final Frame frame, final Visibility visibility, final RubyModule klass, final DynamicScope dynamicScope, final BacktraceElement backtrace) {
        this.self = self;
        this.frame = frame.duplicate();
        this.visibility = visibility;
        this.klass = klass;
        this.dynamicScope = dynamicScope;
        this.backtrace = backtrace;
    }
    
    public Binding(final Frame frame, final RubyModule bindingClass, final DynamicScope dynamicScope, final BacktraceElement backtrace) {
        this.self = frame.getSelf();
        this.frame = frame.duplicate();
        this.visibility = frame.getVisibility();
        this.klass = bindingClass;
        this.dynamicScope = dynamicScope;
        this.backtrace = backtrace;
    }
    
    public Binding clone() {
        return new Binding(this.self, this.frame, this.visibility, this.klass, this.dynamicScope, this.backtrace);
    }
    
    public Binding clone(final Visibility visibility) {
        return new Binding(this.self, this.frame, visibility, this.klass, this.dynamicScope, this.backtrace);
    }
    
    public Visibility getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }
    
    public IRubyObject getSelf() {
        return this.self;
    }
    
    public void setSelf(final IRubyObject self) {
        this.self = self;
    }
    
    public DynamicScope getDynamicScope() {
        return this.dynamicScope;
    }
    
    public DynamicScope getDummyScope(final StaticScope staticScope) {
        if (this.dummyScope == null || this.dummyScope.getStaticScope() != staticScope) {
            return this.dummyScope = DynamicScope.newDummyScope(staticScope, this.dynamicScope);
        }
        return this.dummyScope;
    }
    
    public Frame getFrame() {
        return this.frame;
    }
    
    public RubyModule getKlass() {
        return this.klass;
    }
    
    public String getFile() {
        return this.backtrace.filename;
    }
    
    public void setFile(final String file) {
        this.backtrace.filename = file;
    }
    
    public int getLine() {
        return this.backtrace.line;
    }
    
    public void setLine(final int line) {
        this.backtrace.line = line;
    }
    
    public String getMethod() {
        return this.backtrace.method;
    }
    
    public void setMethod(final String method) {
        this.backtrace.method = method;
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Binding)) {
            return false;
        }
        final Binding bOther = (Binding)other;
        return this.self == bOther.self && this.dynamicScope == bOther.dynamicScope;
    }
}
