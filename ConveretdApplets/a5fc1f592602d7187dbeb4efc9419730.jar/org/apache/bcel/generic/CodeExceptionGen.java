// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.classfile.CodeException;

public final class CodeExceptionGen implements InstructionTargeter, Cloneable
{
    private InstructionHandle start_pc;
    private InstructionHandle end_pc;
    private InstructionHandle handler_pc;
    private ObjectType catch_type;
    
    public CodeExceptionGen(final InstructionHandle start_pc, final InstructionHandle end_pc, final InstructionHandle handler_pc, final ObjectType catch_type) {
        this.setStartPC(start_pc);
        this.setEndPC(end_pc);
        this.setHandlerPC(handler_pc);
        this.catch_type = catch_type;
    }
    
    public CodeException getCodeException(final ConstantPoolGen cp) {
        return new CodeException(this.start_pc.getPosition(), this.end_pc.getPosition() + this.end_pc.getInstruction().getLength(), this.handler_pc.getPosition(), (this.catch_type == null) ? 0 : cp.addClass(this.catch_type));
    }
    
    public void setStartPC(final InstructionHandle start_pc) {
        BranchInstruction.notifyTarget(this.start_pc, start_pc, this);
        this.start_pc = start_pc;
    }
    
    public void setEndPC(final InstructionHandle end_pc) {
        BranchInstruction.notifyTarget(this.end_pc, end_pc, this);
        this.end_pc = end_pc;
    }
    
    public void setHandlerPC(final InstructionHandle handler_pc) {
        BranchInstruction.notifyTarget(this.handler_pc, handler_pc, this);
        this.handler_pc = handler_pc;
    }
    
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (this.start_pc == old_ih) {
            targeted = true;
            this.setStartPC(new_ih);
        }
        if (this.end_pc == old_ih) {
            targeted = true;
            this.setEndPC(new_ih);
        }
        if (this.handler_pc == old_ih) {
            targeted = true;
            this.setHandlerPC(new_ih);
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih + ", but {" + this.start_pc + ", " + this.end_pc + ", " + this.handler_pc + "}");
        }
    }
    
    public boolean containsTarget(final InstructionHandle ih) {
        return this.start_pc == ih || this.end_pc == ih || this.handler_pc == ih;
    }
    
    public void setCatchType(final ObjectType catch_type) {
        this.catch_type = catch_type;
    }
    
    public ObjectType getCatchType() {
        return this.catch_type;
    }
    
    public InstructionHandle getStartPC() {
        return this.start_pc;
    }
    
    public InstructionHandle getEndPC() {
        return this.end_pc;
    }
    
    public InstructionHandle getHandlerPC() {
        return this.handler_pc;
    }
    
    public String toString() {
        return "CodeExceptionGen(" + this.start_pc + ", " + this.end_pc + ", " + this.handler_pc + ")";
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
