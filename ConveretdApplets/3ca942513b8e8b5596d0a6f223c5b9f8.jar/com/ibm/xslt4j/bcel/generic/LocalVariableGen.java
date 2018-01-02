// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.classfile.LocalVariable;

public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable
{
    private int index;
    private String name;
    private Type type;
    private InstructionHandle start;
    private InstructionHandle end;
    
    public LocalVariableGen(final int index, final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
        if (index < 0 || index > 65535) {
            throw new ClassGenException("Invalid index index: " + index);
        }
        this.name = name;
        this.type = type;
        this.index = index;
        this.setStart(start);
        this.setEnd(end);
    }
    
    public LocalVariable getLocalVariable(final ConstantPoolGen cp) {
        final int start_pc = this.start.getPosition();
        final int length = this.end.getPosition() - start_pc;
        final int name_index = cp.addUtf8(this.name);
        final int signature_index = cp.addUtf8(this.type.getSignature());
        return new LocalVariable(start_pc, length, name_index, signature_index, this.index, cp.getConstantPool());
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setType(final Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public InstructionHandle getStart() {
        return this.start;
    }
    
    public InstructionHandle getEnd() {
        return this.end;
    }
    
    public void setStart(final InstructionHandle start) {
        BranchInstruction.notifyTarget(this.start, start, this);
        this.start = start;
    }
    
    public void setEnd(final InstructionHandle end) {
        BranchInstruction.notifyTarget(this.end, end, this);
        this.end = end;
    }
    
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (this.start == old_ih) {
            targeted = true;
            this.setStart(new_ih);
        }
        if (this.end == old_ih) {
            targeted = true;
            this.setEnd(new_ih);
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih + ", but {" + this.start + ", " + this.end + "}");
        }
    }
    
    public boolean containsTarget(final InstructionHandle ih) {
        return this.start == ih || this.end == ih;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof LocalVariableGen)) {
            return false;
        }
        final LocalVariableGen l = (LocalVariableGen)o;
        return l.index == this.index && l.start == this.start && l.end == this.end;
    }
    
    public String toString() {
        return "LocalVariableGen(" + this.name + ", " + this.type + ", " + this.start + ", " + this.end + ")";
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }
}
