// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import java.io.IOException;
import java.io.DataOutputStream;

public class GOTO extends GotoInstruction implements VariableLengthInstruction
{
    GOTO() {
    }
    
    public GOTO(final InstructionHandle target) {
        super((short)167, target);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.index = this.getTargetOffset();
        if (super.opcode == 167) {
            super.dump(out);
        }
        else {
            super.index = this.getTargetOffset();
            out.writeByte(super.opcode);
            out.writeInt(super.index);
        }
    }
    
    protected int updatePosition(final int offset, final int max_offset) {
        final int i = this.getTargetOffset();
        super.position += offset;
        if (Math.abs(i) >= 32767 - max_offset) {
            super.opcode = 200;
            super.length = 5;
            return 2;
        }
        return 0;
    }
    
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO(this);
    }
}
