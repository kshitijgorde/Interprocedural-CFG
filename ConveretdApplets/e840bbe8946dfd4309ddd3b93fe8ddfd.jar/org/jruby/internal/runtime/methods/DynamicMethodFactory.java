// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.ast.executable.Script;
import org.jruby.RubyInstanceConfig;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Visibility;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.Node;
import org.jruby.parser.StaticScope;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class DynamicMethodFactory
{
    public static DynamicMethod newDefaultMethod(final Ruby runtime, final RubyModule container, final String name, final StaticScope scope, final Node body, final ArgsNode argsNode, final Visibility visibility, final ISourcePosition position) {
        if (runtime.getInstanceConfig().getCompileMode() != RubyInstanceConfig.CompileMode.OFF) {
            return new DefaultMethod(container, scope, body, name, argsNode, visibility, position);
        }
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            return new TraceableInterpretedMethod(container, scope, body, name, argsNode, visibility, position);
        }
        return new InterpretedMethod(container, scope, body, name, argsNode, visibility, position);
    }
    
    public static InterpretedMethod newInterpretedMethod(final Ruby runtime, final RubyModule container, final StaticScope scope, final Node body, final String name, final ArgsNode argsNode, final Visibility visibility, final ISourcePosition position) {
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            return new TraceableInterpretedMethod(container, scope, body, name, argsNode, visibility, position);
        }
        return new InterpretedMethod(container, scope, body, name, argsNode, visibility, position);
    }
    
    public static DynamicMethod newJittedMethod(final Ruby runtime, final RubyModule container, final StaticScope scope, final Script script, final String name, final CallConfiguration config, final Visibility visibility, final Arity arity, final ISourcePosition position, final DefaultMethod defaultMethod) {
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            return new TraceableJittedMethod(container, scope, script, name, config, visibility, arity, position, defaultMethod);
        }
        return new JittedMethod(container, scope, script, name, config, visibility, arity, position, defaultMethod);
    }
}
