// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.CallType;
import org.jruby.runtime.MethodFactory;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import org.jruby.RubyModule;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.PositionAware;

public abstract class CompiledMethod extends JavaMethod implements Cloneable, PositionAware, MethodArgs2
{
    protected Object $scriptObject;
    protected ISourcePosition position;
    protected String[] parameterList;
    
    protected void init(final RubyModule implementationClass, final Arity arity, final Visibility visibility, final StaticScope staticScope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        this.$scriptObject = scriptObject;
        this.position = position;
        this.parameterList = parameterDesc.split(";");
        super.init(implementationClass, arity, visibility, staticScope, callConfig);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        return this.call(context, self, clazz, name, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
        return this.call(context, self, clazz, name, arg, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        return this.call(context, self, clazz, name, arg1, arg2, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.call(context, self, clazz, name, arg1, arg2, arg3, Block.NULL_BLOCK);
    }
    
    public DynamicMethod dup() {
        try {
            final CompiledMethod msm = (CompiledMethod)this.clone();
            return msm;
        }
        catch (CloneNotSupportedException cnse) {
            return null;
        }
    }
    
    public boolean isNative() {
        return false;
    }
    
    public String getFile() {
        return this.position.getFile();
    }
    
    public int getLine() {
        return this.position.getStartLine();
    }
    
    public String[] getParameterList() {
        return this.parameterList;
    }
    
    public Object getScriptObject() {
        return this.$scriptObject;
    }
    
    public static class LazyCompiledMethod extends DynamicMethod implements Cloneable, PositionAware, MethodArgs2
    {
        private final String method;
        private final Arity arity;
        private final StaticScope scope;
        private final Object scriptObject;
        private MethodFactory factory;
        private DynamicMethod compiledMethod;
        private final ISourcePosition position;
        private final String parameterDesc;
        private final String[] parameterList;
        
        public LazyCompiledMethod(final RubyModule implementationClass, final String method, final Arity arity, final Visibility visibility, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc, final MethodFactory factory) {
            super(implementationClass, visibility, callConfig);
            this.method = method;
            this.arity = arity;
            this.scope = scope;
            this.scriptObject = scriptObject;
            this.factory = factory;
            this.position = position;
            this.parameterDesc = parameterDesc;
            this.parameterList = parameterDesc.split(";");
        }
        
        private synchronized void initializeMethod() {
            if (this.compiledMethod != null) {
                return;
            }
            this.compiledMethod = this.factory.getCompiledMethod(this.implementationClass, this.method, this.arity, this.visibility, this.scope, this.scriptObject, this.callConfig, this.position, this.parameterDesc);
            this.factory = null;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0, arg1);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, args);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, block);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0, block);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0, arg1, block);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, arg0, arg1, arg2, block);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.call(context, self, clazz, name, args, block);
        }
        
        public Arity getArity() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getArity();
        }
        
        public CallConfiguration getCallConfig() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getCallConfig();
        }
        
        public RubyModule getImplementationClass() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getImplementationClass();
        }
        
        protected RubyModule getProtectedClass() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getProtectedClass();
        }
        
        public DynamicMethod getRealMethod() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getRealMethod();
        }
        
        public Visibility getVisibility() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.getVisibility();
        }
        
        public boolean isCallableFrom(final IRubyObject caller, final CallType callType) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.isCallableFrom(caller, callType);
        }
        
        public boolean isNative() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.isNative();
        }
        
        public void setCallConfig(final CallConfiguration callConfig) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            this.compiledMethod.setCallConfig(callConfig);
        }
        
        public void setImplementationClass(final RubyModule implClass) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            this.compiledMethod.setImplementationClass(implClass);
        }
        
        public void setVisibility(final Visibility visibility) {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            this.compiledMethod.setVisibility(visibility);
        }
        
        public DynamicMethod dup() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.compiledMethod.dup();
        }
        
        public String getFile() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.position.getFile();
        }
        
        public int getLine() {
            if (this.compiledMethod == null) {
                this.initializeMethod();
            }
            return this.position.getStartLine();
        }
        
        public String[] getParameterList() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
