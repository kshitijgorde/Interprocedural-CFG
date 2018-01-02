// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class JSR_W extends JsrInstruction
{
    JSR_W() {
    }
    
    public JSR_W(final InstructionHandle target) {
        super((short)201, target);
        super.length = 5;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.index = this.getTargetOffset();
        out.writeByte(super.opcode);
        out.writeInt(super.index);
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.index = bytes.readInt();
        super.length = 5;
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR_W(this);
    }
}
