// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

public class Frame
{
    protected static UninitializedObjectType _this;
    private LocalVariables locals;
    private OperandStack stack;
    
    public Frame(final int maxLocals, final int maxStack) {
        this.locals = new LocalVariables(maxLocals);
        this.stack = new OperandStack(maxStack);
    }
    
    public Frame(final LocalVariables locals, final OperandStack stack) {
        this.locals = locals;
        this.stack = stack;
    }
    
    protected Object clone() {
        final Frame f = new Frame(this.locals.getClone(), this.stack.getClone());
        return f;
    }
    
    public Frame getClone() {
        return (Frame)this.clone();
    }
    
    public LocalVariables getLocals() {
        return this.locals;
    }
    
    public OperandStack getStack() {
        return this.stack;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Frame)) {
            return false;
        }
        final Frame f = (Frame)o;
        return this.stack.equals(f.stack) && this.locals.equals(f.locals);
    }
    
    public String toString() {
        String s = "Local Variables:\n";
        s = String.valueOf(s) + this.locals;
        s = String.valueOf(s) + "OperandStack:\n";
        s = String.valueOf(s) + this.stack;
        return s;
    }
}
