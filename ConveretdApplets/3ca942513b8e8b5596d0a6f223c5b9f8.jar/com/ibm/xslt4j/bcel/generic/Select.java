// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackProducer
{
    protected int[] match;
    protected int[] indices;
    protected InstructionHandle[] targets;
    protected int fixed_length;
    protected int match_length;
    protected int padding;
    
    Select() {
        this.padding = 0;
    }
    
    Select(final short opcode, final int[] match, final InstructionHandle[] targets, final InstructionHandle target) {
        super(opcode, target);
        this.padding = 0;
        this.targets = targets;
        for (int i = 0; i < targets.length; ++i) {
            BranchInstruction.notifyTarget(null, targets[i], this);
        }
        this.match = match;
        if ((this.match_length = match.length) != targets.length) {
            throw new ClassGenException("Match and target array have not the same length");
        }
        this.indices = new int[this.match_length];
    }
    
    protected int updatePosition(final int offset, final int max_offset) {
        super.position += offset;
        final short old_length = super.length;
        this.padding = (4 - (super.position + 1) % 4) % 4;
        super.length = (short)(this.fixed_length + this.padding);
        return super.length - old_length;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        for (int i = 0; i < this.padding; ++i) {
            out.writeByte(0);
        }
        out.writeInt(super.index = this.getTargetOffset());
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.padding = (4 - bytes.getIndex() % 4) % 4;
        for (int i = 0; i < this.padding; ++i) {
            final byte b;
            if ((b = bytes.readByte()) != 0) {
                throw new ClassGenException("Padding byte != 0: " + b);
            }
        }
        super.index = bytes.readInt();
    }
    
    public String toString(final boolean verbose) {
        final StringBuffer buf = new StringBuffer(super.toString(verbose));
        if (verbose) {
            for (int i = 0; i < this.match_length; ++i) {
                String s = "null";
                if (this.targets[i] != null) {
                    s = this.targets[i].getInstruction().toString();
                }
                buf.append("(" + this.match[i] + ", " + s + " = {" + this.indices[i] + "})");
            }
        }
        else {
            buf.append(" ...");
        }
        return buf.toString();
    }
    
    public void setTarget(final int i, final InstructionHandle target) {
        BranchInstruction.notifyTarget(this.targets[i], target, this);
        this.targets[i] = target;
    }
    
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (super.target == old_ih) {
            targeted = true;
            this.setTarget(new_ih);
        }
        for (int i = 0; i < this.targets.length; ++i) {
            if (this.targets[i] == old_ih) {
                targeted = true;
                this.setTarget(i, new_ih);
            }
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih);
        }
    }
    
    public boolean containsTarget(final InstructionHandle ih) {
        if (super.target == ih) {
            return true;
        }
        for (int i = 0; i < this.targets.length; ++i) {
            if (this.targets[i] == ih) {
                return true;
            }
        }
        return false;
    }
    
    void dispose() {
        super.dispose();
        for (int i = 0; i < this.targets.length; ++i) {
            this.targets[i].removeTargeter(this);
        }
    }
    
    public int[] getMatchs() {
        return this.match;
    }
    
    public int[] getIndices() {
        return this.indices;
    }
    
    public InstructionHandle[] getTargets() {
        return this.targets;
    }
}
