// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.Iterator;
import org.jruby.ast.types.INameNode;
import java.util.ArrayList;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.util.ByteList;
import java.util.List;
import org.jruby.lexer.yacc.ISourcePositionHolder;

public abstract class Node implements ISourcePositionHolder
{
    static final List<Node> EMPTY_LIST;
    public static final ByteList EXPRESSION_BYTELIST;
    public static final ByteList ASSIGNMENT_BYTELIST;
    public static final ByteList GLOBAL_VARIABLE_BYTELIST;
    public static final ByteList METHOD_BYTELIST;
    public static final ByteList CLASS_VARIABLE_BYTELIST;
    public static final ByteList CONSTANT_BYTELIST;
    public static final ByteList LOCAL_VARIABLE_BYTELIST;
    public static final ByteList LOCAL_VARIABLE_IN_BLOCK_BYTELIST;
    public static final ByteList FALSE_BYTELIST;
    public static final ByteList INSTANCE_VARIABLE_BYTELIST;
    public static final ByteList NIL_BYTELIST;
    public static final ByteList SELF_BYTELIST;
    public static final ByteList SUPER_BYTELIST;
    public static final ByteList TRUE_BYTELIST;
    public static final ByteList YIELD_BYTELIST;
    private ISourcePosition position;
    
    public Node(final ISourcePosition position) {
        assert position != null;
        this.position = position;
    }
    
    public ISourcePosition getPosition() {
        return this.position;
    }
    
    public void setPosition(final ISourcePosition position) {
        this.position = position;
    }
    
    public abstract Object accept(final NodeVisitor p0);
    
    public abstract List<Node> childNodes();
    
    protected static List<Node> createList(final Node... nodes) {
        final ArrayList<Node> list = new ArrayList<Node>();
        for (final Node node : nodes) {
            if (node != null) {
                list.add(node);
            }
        }
        return list;
    }
    
    public String toString() {
        if (this instanceof InvisibleNode) {
            return "";
        }
        final StringBuilder builder = new StringBuilder(60);
        builder.append("(").append(this.getNodeName());
        if (this instanceof INameNode) {
            builder.append(":").append(((INameNode)this).getName());
        }
        builder.append(" ").append(this.getPosition().getStartLine());
        for (final Node child : this.childNodes()) {
            builder.append(", ").append(child);
        }
        builder.append(")");
        return builder.toString();
    }
    
    protected String getNodeName() {
        final String name = this.getClass().getName();
        final int i = name.lastIndexOf(46);
        final String nodeType = name.substring(i + 1);
        return nodeType;
    }
    
    public boolean isInvisible() {
        return this instanceof InvisibleNode;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be directly interpreted");
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        throw new RuntimeException("Invalid node encountered in interpreter: \"" + this.getClass().getName() + "\", please report this at www.jruby.org");
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        try {
            this.interpret(runtime, context, self, aBlock);
            return Node.EXPRESSION_BYTELIST;
        }
        catch (JumpException jumpExcptn) {
            return null;
        }
    }
    
    public abstract NodeType getNodeType();
    
    static {
        EMPTY_LIST = new ArrayList<Node>();
        EXPRESSION_BYTELIST = ByteList.create("expression");
        ASSIGNMENT_BYTELIST = ByteList.create("assignment");
        GLOBAL_VARIABLE_BYTELIST = ByteList.create("global-variable");
        METHOD_BYTELIST = ByteList.create("method");
        CLASS_VARIABLE_BYTELIST = ByteList.create("class variable");
        CONSTANT_BYTELIST = ByteList.create("constant");
        LOCAL_VARIABLE_BYTELIST = ByteList.create("local-variable");
        LOCAL_VARIABLE_IN_BLOCK_BYTELIST = ByteList.create("local-variable(in-block)");
        FALSE_BYTELIST = ByteList.create("false");
        INSTANCE_VARIABLE_BYTELIST = ByteList.create("instance-variable");
        NIL_BYTELIST = ByteList.create("nil");
        SELF_BYTELIST = ByteList.create("self");
        SUPER_BYTELIST = ByteList.create("super");
        TRUE_BYTELIST = ByteList.create("true");
        YIELD_BYTELIST = ByteList.create("yield");
    }
}
