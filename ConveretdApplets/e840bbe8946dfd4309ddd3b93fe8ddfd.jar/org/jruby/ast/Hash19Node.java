// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class Hash19Node extends HashNode
{
    public Hash19Node(final ISourcePosition position, final ListNode listNode) {
        super(position, listNode);
    }
    
    protected void aset(final Ruby runtime, final RubyHash hash, final IRubyObject key, final IRubyObject value) {
        hash.fastASetCheckString19(runtime, key, value);
    }
}
