// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.Script;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.Node;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.PositionAware;

public class DefaultMethod extends DynamicMethod implements MethodArgs, PositionAware
{
    private DynamicMethodBox box;
    private final StaticScope staticScope;
    private final Node body;
    private final ArgsNode argsNode;
    private final ISourcePosition position;
    private final InterpretedMethod interpretedMethod;
    
    public DefaultMethod(final RubyModule implementationClass, final StaticScope staticScope, final Node body, final String name, final ArgsNode argsNode, final Visibility visibility, final ISourcePosition position) {
        super(implementationClass, visibility, CallConfiguration.FrameFullScopeFull, name);
        this.box = new DynamicMethodBox();
        this.interpretedMethod = DynamicMethodFactory.newInterpretedMethod(implementationClass.getRuntime(), implementationClass, staticScope, body, name, argsNode, visibility, position);
        this.interpretedMethod.serialNumber = this.serialNumber;
        this.box.actualMethod = this.interpretedMethod;
        this.argsNode = argsNode;
        this.body = body;
        this.staticScope = staticScope;
        this.position = position;
        assert argsNode != null;
    }
    
    public int getCallCount() {
        return this.box.callCount;
    }
    
    public int incrementCallCount() {
        return ++this.box.callCount;
    }
    
    public void setCallCount(final int callCount) {
        this.box.callCount = callCount;
    }
    
    public Node getBodyNode() {
        return this.body;
    }
    
    public ArgsNode getArgsNode() {
        return this.argsNode;
    }
    
    public StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public DynamicMethod getMethodForCaching() {
        if (!RubyInstanceConfig.DYNOPT_COMPILE_ENABLED) {
            final DynamicMethod method = this.box.actualMethod;
            if (method instanceof JittedMethod) {
                return method;
            }
        }
        return this;
    }
    
    public void switchToJitted(final Script jitCompiledScript, final CallConfiguration jitCallConfig) {
        this.box.actualMethod = DynamicMethodFactory.newJittedMethod(this.getImplementationClass().getRuntime(), this.getImplementationClass(), this.staticScope, jitCompiledScript, this.name, jitCallConfig, this.getVisibility(), this.argsNode.getArity(), this.position, this);
        this.box.actualMethod.serialNumber = this.serialNumber;
        this.box.callCount = -1;
        if (!RubyInstanceConfig.DYNOPT_COMPILE_ENABLED) {
            this.getImplementationClass().invalidateCacheDescendants();
        }
    }
    
    private DynamicMethod tryJitReturnMethod(final ThreadContext context) {
        context.getRuntime().getJITCompiler().tryJIT(this, context, this.name);
        return this.box.actualMethod;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, args, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, args, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, args);
        }
        return this.box.actualMethod.call(context, self, clazz, name, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name);
        }
        return this.box.actualMethod.call(context, self, clazz, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9, final Block block) {
        if (this.box.callCount >= 0) {
            return this.tryJitReturnMethod(context).call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, block);
        }
        return this.box.actualMethod.call(context, self, clazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, block);
    }
    
    public ISourcePosition getPosition() {
        return this.position;
    }
    
    public String getFile() {
        return this.position.getFile();
    }
    
    public int getLine() {
        return this.position.getLine();
    }
    
    public Arity getArity() {
        return this.argsNode.getArity();
    }
    
    public DynamicMethod dup() {
        final DefaultMethod newMethod = new DefaultMethod(this.getImplementationClass(), this.staticScope, this.body, this.name, this.argsNode, this.getVisibility(), this.position);
        newMethod.setIsBuiltin(this.builtin);
        newMethod.box = this.box;
        return newMethod;
    }
    
    public void setVisibility(final Visibility visibility) {
        final DynamicMethodBox newBox = new DynamicMethodBox();
        newBox.actualMethod = this.box.actualMethod.dup();
        newBox.callCount = this.box.callCount;
        this.box = newBox;
        super.setVisibility(visibility);
    }
    
    private static class DynamicMethodBox
    {
        public DynamicMethod actualMethod;
        public int callCount;
        
        private DynamicMethodBox() {
            this.callCount = 0;
        }
    }
}
