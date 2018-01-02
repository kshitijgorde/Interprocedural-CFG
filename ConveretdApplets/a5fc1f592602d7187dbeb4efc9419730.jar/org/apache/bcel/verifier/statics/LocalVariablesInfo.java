// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.statics;

import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
import org.apache.bcel.generic.Type;
import org.apache.bcel.verifier.exc.AssertionViolatedException;

public class LocalVariablesInfo
{
    private LocalVariableInfo[] localVariableInfos;
    private IntList instruction_offsets;
    
    LocalVariablesInfo(final int max_locals) {
        this.instruction_offsets = new IntList();
        this.localVariableInfos = new LocalVariableInfo[max_locals];
        for (int i = 0; i < max_locals; ++i) {
            this.localVariableInfos[i] = new LocalVariableInfo();
        }
    }
    
    public LocalVariableInfo getLocalVariableInfo(final int slot) {
        if (slot < 0 || slot >= this.localVariableInfos.length) {
            throw new AssertionViolatedException("Slot number for local variable information out of range.");
        }
        return this.localVariableInfos[slot];
    }
    
    public void add(final int slot, final String name, final int startpc, final int length, final Type t) throws LocalVariableInfoInconsistentException {
        if (slot < 0 || slot >= this.localVariableInfos.length) {
            throw new AssertionViolatedException("Slot number for local variable information out of range.");
        }
        this.localVariableInfos[slot].add(name, startpc, length, t);
        if (t == Type.LONG) {
            this.localVariableInfos[slot + 1].add(name, startpc, length, LONG_Upper.theInstance());
        }
        if (t == Type.DOUBLE) {
            this.localVariableInfos[slot + 1].add(name, startpc, length, DOUBLE_Upper.theInstance());
        }
    }
}
