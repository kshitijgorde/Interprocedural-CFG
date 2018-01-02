// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class TABLESWITCH extends Select
{
    TABLESWITCH() {
    }
    
    public TABLESWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle target) {
        super((short)170, match, targets, target);
        super.length = (short)(13 + super.match_length * 4);
        super.fixed_length = super.length;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        final int low = (super.match_length > 0) ? super.match[0] : 0;
        out.writeInt(low);
        final int high = (super.match_length > 0) ? super.match[super.match_length - 1] : 0;
        out.writeInt(high);
        for (int i = 0; i < super.match_length; ++i) {
            out.writeInt(super.indices[i] = this.getTargetOffset(super.targets[i]));
        }
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        final int low = bytes.readInt();
        final int high = bytes.readInt();
        super.match_length = high - low + 1;
        super.fixed_length = (short)(13 + super.match_length * 4);
        super.length = (short)(super.fixed_length + super.padding);
        super.match = new int[super.match_length];
        super.indices = new int[super.match_length];
        super.targets = new InstructionHandle[super.match_length];
        for (int i = low; i <= high; ++i) {
            super.match[i - low] = i;
        }
        for (int i = 0; i < super.match_length; ++i) {
            super.indices[i] = bytes.readInt();
        }
    }
    
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitStackProducer(this);
        v.visitBranchInstruction(this);
        v.visitSelect(this);
        v.visitTABLESWITCH(this);
    }
}
