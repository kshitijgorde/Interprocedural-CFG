// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public final class BranchHandle extends InstructionHandle
{
    private BranchInstruction bi;
    private static BranchHandle bh_list;
    
    static {
        BranchHandle.bh_list = null;
    }
    
    private BranchHandle(final BranchInstruction i) {
        super(i);
        this.bi = i;
    }
    
    static final BranchHandle getBranchHandle(final BranchInstruction i) {
        if (BranchHandle.bh_list == null) {
            return new BranchHandle(i);
        }
        final BranchHandle bh = BranchHandle.bh_list;
        BranchHandle.bh_list = (BranchHandle)bh.next;
        bh.setInstruction(i);
        return bh;
    }
    
    protected void addHandle() {
        super.next = BranchHandle.bh_list;
        BranchHandle.bh_list = this;
    }
    
    public int getPosition() {
        return this.bi.position;
    }
    
    void setPosition(final int pos) {
        this.bi.position = pos;
        super.i_position = pos;
    }
    
    protected int updatePosition(final int offset, final int max_offset) {
        final int x = this.bi.updatePosition(offset, max_offset);
        super.i_position = this.bi.position;
        return x;
    }
    
    public void setTarget(final InstructionHandle ih) {
        this.bi.setTarget(ih);
    }
    
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        this.bi.updateTarget(old_ih, new_ih);
    }
    
    public InstructionHandle getTarget() {
        return this.bi.getTarget();
    }
    
    public void setInstruction(final Instruction i) {
        super.setInstruction(i);
        if (!(i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning " + i + " to branch handle which is not a branch instruction");
        }
        this.bi = (BranchInstruction)i;
    }
}
