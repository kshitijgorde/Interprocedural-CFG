// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import java.io.IOException;
import java.io.DataOutputStream;

public class JSR extends JsrInstruction implements VariableLengthInstruction
{
    JSR() {
    }
    
    public JSR(final InstructionHandle target) {
        super((short)168, target);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.index = this.getTargetOffset();
        if (super.opcode == 168) {
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
            super.opcode = 201;
            super.length = 5;
            return 2;
        }
        return 0;
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitVariableLengthInstruction(this);
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR(this);
    }
}
