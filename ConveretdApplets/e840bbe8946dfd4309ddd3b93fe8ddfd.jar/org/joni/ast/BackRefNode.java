// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.exception.ValueException;
import org.joni.ScanEnvironment;

public final class BackRefNode extends StateNode
{
    public int backNum;
    public int[] back;
    public int nestLevel;
    
    public BackRefNode(final int backNum, final int[] backRefs, final boolean byName, final ScanEnvironment env) {
        this.backNum = backNum;
        if (byName) {
            this.setNameRef();
        }
        for (int i = 0; i < backNum; ++i) {
            if (backRefs[i] <= env.numMem && env.memNodes[backRefs[i]] == null) {
                this.setRecursion();
                break;
            }
        }
        System.arraycopy(backRefs, 0, this.back = new int[backNum], 0, backNum);
    }
    
    public BackRefNode(final int backNum, final int[] backRefs, final boolean byName, final boolean existLevel, final int nestLevel, final ScanEnvironment env) {
        this(backNum, backRefs, byName, env);
        if (existLevel) {
            this.setNestLevel();
            this.nestLevel = nestLevel;
        }
    }
    
    public int getType() {
        return 4;
    }
    
    public String getName() {
        return "Back Ref";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  backNum: " + this.backNum);
        String backs = "";
        for (int i = 0; i < this.back.length; ++i) {
            backs = backs + this.back[i] + ", ";
        }
        value.append("\n  back: " + backs);
        value.append("\n  nextLevel: " + this.nestLevel);
        return value.toString();
    }
    
    public void renumber(final int[] map) {
        if (!this.isNameRef()) {
            throw new ValueException("numbered backref/call is not allowed. (use name)");
        }
        final int oldNum = this.backNum;
        int pos = 0;
        for (int i = 0; i < oldNum; ++i) {
            final int n = map[this.back[i]];
            if (n > 0) {
                this.back[pos] = n;
                ++pos;
            }
        }
        this.backNum = pos;
    }
}
