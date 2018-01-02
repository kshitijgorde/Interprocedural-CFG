// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.classfile.LineNumber;

public class LineNumberGen implements InstructionTargeter, Cloneable
{
    private InstructionHandle ih;
    private int src_line;
    
    public LineNumberGen(final InstructionHandle ih, final int src_line) {
        this.setInstruction(ih);
        this.setSourceLine(src_line);
    }
    
    public boolean containsTarget(final InstructionHandle ih) {
        return this.ih == ih;
    }
    
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        if (old_ih != this.ih) {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + this.ih + "}");
        }
        this.setInstruction(new_ih);
    }
    
    public LineNumber getLineNumber() {
        return new LineNumber(this.ih.getPosition(), this.src_line);
    }
    
    public void setInstruction(final InstructionHandle ih) {
        BranchInstruction.notifyTarget(this.ih, ih, this);
        this.ih = ih;
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
    
    public InstructionHandle getInstruction() {
        return this.ih;
    }
    
    public void setSourceLine(final int src_line) {
        this.src_line = src_line;
    }
    
    public int getSourceLine() {
        return this.src_line;
    }
}
