// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.Node;
import org.jruby.parser.StaticScope;

public class TraceableInterpretedMethod extends InterpretedMethod
{
    private StaticScope staticScope;
    private Node body;
    private ArgsNode argsNode;
    private ISourcePosition position;
    
    public TraceableInterpretedMethod(final RubyModule implementationClass, final StaticScope staticScope, final Node body, final String name, final ArgsNode argsNode, final Visibility visibility, final ISourcePosition position) {
        super(implementationClass, staticScope, body, name, argsNode, visibility, position);
        this.body = body;
        this.staticScope = staticScope;
        this.argsNode = argsNode;
        this.position = position;
        assert argsNode != null;
    }
    
    protected boolean isTraceable() {
        return true;
    }
    
    public DynamicMethod dup() {
        return new TraceableInterpretedMethod(this.getImplementationClass(), this.staticScope, this.body, this.name, this.argsNode, this.getVisibility(), this.position);
    }
}
