// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;

public final class Frame
{
    private RubyModule klazz;
    private IRubyObject self;
    private String name;
    private Block block;
    private boolean isBindingFrame;
    private Visibility visibility;
    private int jumpTarget;
    
    public Frame() {
        this.block = Block.NULL_BLOCK;
        this.isBindingFrame = false;
        this.visibility = Visibility.PUBLIC;
    }
    
    private Frame(final Frame frame) {
        this.block = Block.NULL_BLOCK;
        this.isBindingFrame = false;
        this.visibility = Visibility.PUBLIC;
        assert frame.block != null : "Block uses null object pattern.  It should NEVER be null";
        this.self = frame.self;
        this.name = frame.name;
        this.klazz = frame.klazz;
        this.block = frame.block;
        this.visibility = frame.visibility;
        this.isBindingFrame = frame.isBindingFrame;
        this.jumpTarget = frame.jumpTarget;
    }
    
    public void updateFrame() {
        this.updateFrame(null, null, null, Block.NULL_BLOCK, 0);
    }
    
    public void updateFrame(final String name) {
        this.name = name;
    }
    
    public void updateFrame(final Frame frame) {
        assert frame.block != null : "Block uses null object pattern.  It should NEVER be null";
        this.self = frame.self;
        this.name = frame.name;
        this.klazz = frame.klazz;
        this.block = frame.block;
        this.visibility = frame.visibility;
        this.isBindingFrame = frame.isBindingFrame;
        this.jumpTarget = frame.jumpTarget;
    }
    
    public void updateFrame(final RubyModule klazz, final IRubyObject self, final String name, final Block block, final int jumpTarget) {
        assert block != null : "Block uses null object pattern.  It should NEVER be null";
        this.self = self;
        this.name = name;
        this.klazz = klazz;
        this.block = block;
        this.visibility = Visibility.PUBLIC;
        this.isBindingFrame = false;
        this.jumpTarget = jumpTarget;
    }
    
    public void updateFrameForEval(final IRubyObject self, final int jumpTarget) {
        this.self = self;
        this.name = null;
        this.visibility = Visibility.PRIVATE;
        this.isBindingFrame = false;
        this.jumpTarget = jumpTarget;
    }
    
    public void clear() {
        this.self = null;
        this.klazz = null;
        this.block = Block.NULL_BLOCK;
    }
    
    public Frame duplicate() {
        return new Frame(this);
    }
    
    public Frame duplicateForBacktrace() {
        final Frame backtraceFrame = new Frame();
        backtraceFrame.name = this.name;
        backtraceFrame.isBindingFrame = this.isBindingFrame;
        return backtraceFrame;
    }
    
    public int getJumpTarget() {
        return this.jumpTarget;
    }
    
    public RubyModule getKlazz() {
        return this.klazz;
    }
    
    public void setKlazz(final RubyModule klazz) {
        this.klazz = klazz;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    IRubyObject getSelf() {
        return this.self;
    }
    
    public void setSelf(final IRubyObject self) {
        this.self = self;
    }
    
    public Visibility getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }
    
    public boolean isBindingFrame() {
        return this.isBindingFrame;
    }
    
    public void setIsBindingFrame(final boolean isBindingFrame) {
        this.isBindingFrame = isBindingFrame;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder(50);
        sb.append(this.klazz);
        if (this.name != null) {
            sb.append(" in ").append(this.name);
        }
        return sb.toString();
    }
}
