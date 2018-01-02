// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.Option;
import org.joni.constants.EncloseType;

public final class EncloseNode extends StateNode implements EncloseType
{
    public int type;
    public int regNum;
    public int option;
    public Node target;
    public int callAddr;
    public int minLength;
    public int maxLength;
    public int charLength;
    public int optCount;
    
    public EncloseNode(final int type) {
        this.type = type;
        this.callAddr = -1;
    }
    
    public EncloseNode(final int option, final boolean isNamed) {
        this(1);
        if (isNamed) {
            this.setNamedGroup();
        }
        this.option = option;
    }
    
    public EncloseNode(final int option, final int _) {
        this(2);
        this.option = option;
    }
    
    public int getType() {
        return 6;
    }
    
    protected void setChild(final Node newChild) {
        this.target = newChild;
    }
    
    protected Node getChild() {
        return this.target;
    }
    
    public void setTarget(final Node tgt) {
        this.target = tgt;
        tgt.parent = this;
    }
    
    public String getName() {
        return "Enclose";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  type: " + this.typeToString());
        value.append("\n  regNum: " + this.regNum);
        value.append("\n  option: " + Option.toString(this.option));
        value.append("\n  target: " + Node.pad(this.target, level + 1));
        value.append("\n  callAddr: " + this.callAddr);
        value.append("\n  minLength: " + this.minLength);
        value.append("\n  maxLength: " + this.maxLength);
        value.append("\n  charLength: " + this.charLength);
        value.append("\n  optCount: " + this.optCount);
        return value.toString();
    }
    
    public String typeToString() {
        final StringBuilder types = new StringBuilder();
        if (this.isStopBacktrack()) {
            types.append("STOP_BACKTRACK ");
        }
        if (this.isMemory()) {
            types.append("MEMORY ");
        }
        if (this.isOption()) {
            types.append("OPTION ");
        }
        return types.toString();
    }
    
    public void setEncloseStatus(final int flag) {
        this.state |= flag;
    }
    
    public void clearEncloseStatus(final int flag) {
        this.state &= ~flag;
    }
    
    public void clearMemory() {
        this.type &= 0xFFFFFFFE;
    }
    
    public void setMemory() {
        this.type |= 0x1;
    }
    
    public boolean isMemory() {
        return (this.type & 0x1) != 0x0;
    }
    
    public void clearOption() {
        this.type &= 0xFFFFFFFD;
    }
    
    public void setOption() {
        this.type |= 0x2;
    }
    
    public boolean isOption() {
        return (this.type & 0x2) != 0x0;
    }
    
    public void clearStopBacktrack() {
        this.type &= 0xFFFFFFFB;
    }
    
    public void setStopBacktrack() {
        this.type |= 0x4;
    }
    
    public boolean isStopBacktrack() {
        return (this.type & 0x4) != 0x0;
    }
}
