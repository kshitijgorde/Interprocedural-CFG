// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyHash;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class HashNode extends Node
{
    private final ListNode listNode;
    
    public HashNode(final ISourcePosition position, final ListNode listNode) {
        super(position);
        this.listNode = listNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.HASHNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitHashNode(this);
    }
    
    public ListNode getListNode() {
        return this.listNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.listNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyHash hash = RubyHash.newHash(runtime);
        final ListNode list = this.listNode;
        if (list != null) {
            final int size = list.size();
            int i = 0;
            while (i < size) {
                final IRubyObject key = list.get(i++).interpret(runtime, context, self, aBlock);
                final IRubyObject value = list.get(i++).interpret(runtime, context, self, aBlock);
                this.aset(runtime, hash, key, value);
            }
        }
        return hash;
    }
    
    protected void aset(final Ruby runtime, final RubyHash hash, final IRubyObject key, final IRubyObject value) {
        hash.fastASetCheckString(runtime, key, value);
    }
}
