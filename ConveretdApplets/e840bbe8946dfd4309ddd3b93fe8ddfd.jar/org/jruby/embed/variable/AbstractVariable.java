// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.parser.StaticScope;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;

abstract class AbstractVariable implements BiVariable
{
    protected final IRubyObject receiver;
    protected String name;
    protected Object javaObject;
    protected Class javaType;
    protected IRubyObject irubyObject;
    protected boolean fromRuby;
    
    protected AbstractVariable(final RubyObject receiver, final String name, final boolean fromRuby) {
        this.javaObject = null;
        this.javaType = null;
        this.irubyObject = null;
        this.receiver = receiver;
        this.name = name;
        this.fromRuby = fromRuby;
    }
    
    protected void updateByJavaObject(final Ruby runtime, final Object... values) {
        assert values != null;
        this.javaObject = values[0];
        if (this.javaObject == null) {
            this.javaType = null;
        }
        else if (values.length > 1) {
            this.javaType = (Class)values[1];
        }
        else {
            this.javaType = this.javaObject.getClass();
        }
        this.irubyObject = JavaEmbedUtils.javaToRuby(runtime, this.javaObject);
        this.fromRuby = false;
    }
    
    protected AbstractVariable(final IRubyObject receiver, final String name, final boolean fromRuby, final IRubyObject rubyObject) {
        this.javaObject = null;
        this.javaType = null;
        this.irubyObject = null;
        this.receiver = receiver;
        this.name = name;
        this.fromRuby = fromRuby;
        this.irubyObject = rubyObject;
    }
    
    protected void updateRubyObject(final IRubyObject rubyObject) {
        if (rubyObject == null) {
            return;
        }
        this.irubyObject = rubyObject;
    }
    
    public IRubyObject getReceiver() {
        return this.receiver;
    }
    
    public boolean isReceiverIdentical(final RubyObject recv) {
        return this.receiver == recv;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getJavaObject() {
        if (this.irubyObject == null) {
            return this.javaObject;
        }
        final Ruby rt = this.irubyObject.getRuntime();
        if (this.javaType != null) {
            this.javaObject = this.javaType.cast(this.irubyObject.toJava(this.javaType));
        }
        else {
            this.javaObject = this.irubyObject.toJava(Object.class);
            if (this.javaObject != null) {
                this.javaType = this.javaObject.getClass();
            }
        }
        return this.javaObject;
    }
    
    public void setJavaObject(final Ruby runtime, final Object javaObject) {
        this.updateByJavaObject(runtime, javaObject);
    }
    
    public IRubyObject getRubyObject() {
        return this.irubyObject;
    }
    
    public void setRubyObject(final IRubyObject rubyObject) {
        this.updateRubyObject(rubyObject);
    }
    
    protected RubyModule getRubyClass(final Ruby runtime) {
        final ThreadContext context = runtime.getCurrentContext();
        final StaticScope scope = context.getCurrentScope().getStaticScope();
        final RubyModule rubyClass = scope.getModule();
        return rubyClass;
    }
    
    protected static boolean isValidName(final String pattern, final Object name) {
        return name instanceof String && ((String)name).matches(pattern);
    }
}
