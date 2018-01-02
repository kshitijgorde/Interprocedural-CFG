// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.ReferenceType;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.ObjectType;
import java.util.ArrayList;

public class OperandStack
{
    private ArrayList stack;
    private int maxStack;
    
    public OperandStack(final int maxStack) {
        this.stack = new ArrayList();
        this.maxStack = maxStack;
    }
    
    public OperandStack(final int maxStack, final ObjectType obj) {
        this.stack = new ArrayList();
        this.maxStack = maxStack;
        this.push(obj);
    }
    
    protected Object clone() {
        final OperandStack newstack = new OperandStack(this.maxStack);
        newstack.stack = (ArrayList)this.stack.clone();
        return newstack;
    }
    
    public void clear() {
        this.stack = new ArrayList();
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof OperandStack)) {
            return false;
        }
        final OperandStack s = (OperandStack)o;
        return this.stack.equals(s.stack);
    }
    
    public OperandStack getClone() {
        return (OperandStack)this.clone();
    }
    
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
    
    public int maxStack() {
        return this.maxStack;
    }
    
    public Type peek() {
        return this.peek(0);
    }
    
    public Type peek(final int i) {
        return this.stack.get(this.size() - i - 1);
    }
    
    public Type pop() {
        final Type e = this.stack.remove(this.size() - 1);
        return e;
    }
    
    public Type pop(final int i) {
        for (int j = 0; j < i; ++j) {
            this.pop();
        }
        return null;
    }
    
    public void push(final Type type) {
        if (type == null) {
            throw new AssertionViolatedException("Cannot push NULL onto OperandStack.");
        }
        if (type == Type.BOOLEAN || type == Type.CHAR || type == Type.BYTE || type == Type.SHORT) {
            throw new AssertionViolatedException("The OperandStack does not know about '" + type + "'; use Type.INT instead.");
        }
        if (this.slotsUsed() >= this.maxStack) {
            throw new AssertionViolatedException("OperandStack too small, should have thrown proper Exception elsewhere. Stack: " + this);
        }
        this.stack.add(type);
    }
    
    int size() {
        return this.stack.size();
    }
    
    public int slotsUsed() {
        int slots = 0;
        for (int i = 0; i < this.stack.size(); ++i) {
            slots += this.peek(i).getSize();
        }
        return slots;
    }
    
    public String toString() {
        String s = "Slots used: " + this.slotsUsed() + " MaxStack: " + this.maxStack + ".\n";
        for (int i = 0; i < this.size(); ++i) {
            s = s + this.peek(i) + " (Size: " + this.peek(i).getSize() + ")\n";
        }
        return s;
    }
    
    public void merge(final OperandStack s) {
        if (this.slotsUsed() != s.slotsUsed() || this.size() != s.size()) {
            throw new StructuralCodeConstraintException("Cannot merge stacks of different size:\nOperandStack A:\n" + this + "\nOperandStack B:\n" + s);
        }
        for (int i = 0; i < this.size(); ++i) {
            if (!(this.stack.get(i) instanceof UninitializedObjectType) && s.stack.get(i) instanceof UninitializedObjectType) {
                throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
            }
            if (!this.stack.get(i).equals(s.stack.get(i)) && this.stack.get(i) instanceof UninitializedObjectType && !(s.stack.get(i) instanceof UninitializedObjectType)) {
                throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
            }
            if (this.stack.get(i) instanceof UninitializedObjectType && !(s.stack.get(i) instanceof UninitializedObjectType)) {
                this.stack.set(i, this.stack.get(i).getInitialized());
            }
            if (!this.stack.get(i).equals(s.stack.get(i))) {
                if (!(this.stack.get(i) instanceof ReferenceType) || !(s.stack.get(i) instanceof ReferenceType)) {
                    throw new StructuralCodeConstraintException("Cannot merge stacks of different types:\nStack A:\n" + this + "\nStack B:\n" + s);
                }
                this.stack.set(i, this.stack.get(i).firstCommonSuperclass(s.stack.get(i)));
            }
        }
    }
    
    public void initializeObject(final UninitializedObjectType u) {
        for (int i = 0; i < this.stack.size(); ++i) {
            if (this.stack.get(i) == u) {
                this.stack.set(i, u.getInitialized());
            }
        }
    }
}
