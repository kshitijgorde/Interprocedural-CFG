// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.ast.VCallNode;
import org.jruby.ast.LocalVarNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.ISourcePosition;

public class LocalStaticScope extends StaticScope
{
    private static final long serialVersionUID = 2204064248888411628L;
    private static final String[] NO_NAMES;
    
    public LocalStaticScope(final StaticScope enclosingScope) {
        this(enclosingScope, LocalStaticScope.NO_NAMES);
    }
    
    public LocalStaticScope(final StaticScope enclosingScope, final String[] names) {
        super(enclosingScope, names);
        this.setBackrefLastlineScope(true);
    }
    
    public StaticScope getLocalScope() {
        return this;
    }
    
    public int isDefined(final String name, final int depth) {
        return depth << 16 | this.exists(name);
    }
    
    public boolean isArgumentScope() {
        return true;
    }
    
    public void makeArgumentScope() {
    }
    
    public String[] getAllNamesInScope() {
        return this.getVariables();
    }
    
    public AssignableNode assign(final ISourcePosition position, final String name, final Node value, final StaticScope topScope, final int depth) {
        int slot = this.exists(name);
        if (slot >= 0) {
            if (depth > 0) {
                this.capture(slot);
            }
            return new LocalAsgnNode(position, name, depth << 16 | slot, value);
        }
        if (topScope == this) {
            slot = this.addVariable(name);
            return new LocalAsgnNode(position, name, slot, value);
        }
        return ((BlockStaticScope)topScope).addAssign(position, name, value);
    }
    
    public Node declare(final ISourcePosition position, final String name, final int depth) {
        final int slot = this.exists(name);
        if (slot >= 0) {
            if (depth > 0) {
                this.capture(slot);
            }
            return new LocalVarNode(position, depth << 16 | slot, name);
        }
        return new VCallNode(position, name);
    }
    
    public String toString() {
        return "LocalScope: " + super.toString();
    }
    
    static {
        NO_NAMES = new String[0];
    }
}
