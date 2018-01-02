// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.ast.DVarNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.ISourcePosition;

public class BlockStaticScope extends StaticScope
{
    private static final long serialVersionUID = -3882063260379968149L;
    private boolean isArgumentScope;
    
    public BlockStaticScope(final StaticScope parentScope) {
        super(parentScope, new String[0]);
        this.isArgumentScope = false;
    }
    
    public BlockStaticScope(final StaticScope parentScope, final String[] names) {
        super(parentScope, names);
        this.isArgumentScope = false;
    }
    
    public StaticScope getLocalScope() {
        return this.enclosingScope.getLocalScope();
    }
    
    public int isDefined(final String name, final int depth) {
        final int slot = this.exists(name);
        if (slot >= 0) {
            return depth << 16 | slot;
        }
        return this.enclosingScope.isDefined(name, depth + 1);
    }
    
    public boolean isArgumentScope() {
        return this.isArgumentScope;
    }
    
    public void makeArgumentScope() {
        this.isArgumentScope = true;
    }
    
    public String[] getAllNamesInScope() {
        final String[] variables = this.enclosingScope.getAllNamesInScope();
        final String[] ourVariables = this.getVariables();
        final int newSize = variables.length + ourVariables.length;
        final String[] names = new String[newSize];
        System.arraycopy(variables, 0, names, 0, variables.length);
        System.arraycopy(ourVariables, 0, names, variables.length, ourVariables.length);
        return names;
    }
    
    protected AssignableNode assign(final ISourcePosition position, final String name, final Node value, final StaticScope topScope, final int depth) {
        final int slot = this.exists(name);
        if (slot >= 0) {
            if (depth > 0) {
                this.capture(slot);
            }
            return new DAsgnNode(position, name, depth << 16 | slot, value);
        }
        return this.enclosingScope.assign(position, name, value, topScope, depth + 1);
    }
    
    public AssignableNode addAssign(final ISourcePosition position, final String name, final Node value) {
        final int slot = this.addVariable(name);
        return new DAsgnNode(position, name, slot, value);
    }
    
    public Node declare(final ISourcePosition position, final String name, final int depth) {
        final int slot = this.exists(name);
        if (slot >= 0) {
            if (depth > 0) {
                this.capture(slot);
            }
            return new DVarNode(position, depth << 16 | slot, name);
        }
        return this.enclosingScope.declare(position, name, depth + 1);
    }
    
    public String toString() {
        return "BlockScope: " + super.toString() + "\n" + this.getEnclosingScope();
    }
}
