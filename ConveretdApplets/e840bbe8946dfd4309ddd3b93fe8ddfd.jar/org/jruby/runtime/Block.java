// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProc;

public final class Block
{
    private RubyProc proc;
    public Type type;
    private final Binding binding;
    private final BlockBody body;
    private boolean[] escaped;
    public static final Block NULL_BLOCK;
    
    public Block(final BlockBody body, final Binding binding) {
        this.proc = null;
        this.type = Type.NORMAL;
        this.escaped = new boolean[] { false };
        this.body = body;
        this.binding = binding;
    }
    
    public Block(final BlockBody body) {
        this.proc = null;
        this.type = Type.NORMAL;
        this.escaped = new boolean[] { false };
        this.body = body;
        this.binding = null;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args) {
        return this.body.call(context, args, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return this.body.call(context, args, this.binding, this.type, block);
    }
    
    public IRubyObject call(final ThreadContext context) {
        return this.body.call(context, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final Block block) {
        return this.body.call(context, this.binding, this.type, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context) {
        return this.body.yieldSpecific(context, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0) {
        return this.body.call(context, arg0, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.body.call(context, arg0, this.binding, this.type, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0) {
        return this.body.yieldSpecific(context, arg0, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        return this.body.call(context, arg0, arg1, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.body.call(context, arg0, arg1, this.binding, this.type, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        return this.body.yieldSpecific(context, arg0, arg1, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return this.body.call(context, arg0, arg1, arg2, this.binding, this.type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.body.call(context, arg0, arg1, arg2, this.binding, this.type, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return this.body.yieldSpecific(context, arg0, arg1, arg2, this.binding, this.type);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value) {
        return this.body.yield(context, value, this.binding, this.type);
    }
    
    @Deprecated
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue) {
        return this.body.yield(context, value, self, klass, aValue, this.binding, this.type);
    }
    
    public IRubyObject yieldNonArray(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass) {
        return this.body.yield(context, value, self, klass, false, this.binding, this.type);
    }
    
    public IRubyObject yieldArray(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass) {
        return this.body.yield(context, value, self, klass, true, this.binding, this.type);
    }
    
    @Deprecated
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final boolean aValue) {
        return this.body.yield(context, value, null, null, aValue, this.binding, this.type);
    }
    
    @Deprecated
    public IRubyObject yield(final ThreadContext context, final boolean aValue) {
        return this.body.yield(context, null, null, null, aValue, this.binding, this.type);
    }
    
    public Block cloneBlock() {
        final Block newBlock = this.body.cloneBlock(this.binding);
        newBlock.type = this.type;
        newBlock.escaped = this.escaped;
        return newBlock;
    }
    
    public Arity arity() {
        return this.body.arity();
    }
    
    public RubyProc getProcObject() {
        return this.proc;
    }
    
    public void setProcObject(final RubyProc procObject) {
        this.proc = procObject;
    }
    
    public final boolean isGiven() {
        return this != Block.NULL_BLOCK;
    }
    
    public Binding getBinding() {
        return this.binding;
    }
    
    public BlockBody getBody() {
        return this.body;
    }
    
    public Frame getFrame() {
        return this.binding.getFrame();
    }
    
    public boolean isEscaped() {
        return this.escaped[0];
    }
    
    public void escape() {
        this.escaped[0] = true;
    }
    
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Block)) {
            return false;
        }
        final Block bOther = (Block)other;
        return this.binding.equals(bOther.binding) && this.body == bOther.body;
    }
    
    static {
        NULL_BLOCK = new Block(BlockBody.NULL_BODY);
    }
    
    public enum Type
    {
        NORMAL, 
        PROC, 
        LAMBDA, 
        THREAD;
    }
}
