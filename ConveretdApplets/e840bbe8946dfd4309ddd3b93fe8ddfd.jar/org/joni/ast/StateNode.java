// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.constants.NodeStatus;

public abstract class StateNode extends Node implements NodeStatus
{
    protected int state;
    
    public String toString(final int level) {
        return "\n  state: " + this.stateToString();
    }
    
    public String stateToString() {
        final StringBuilder states = new StringBuilder();
        if (this.isMinFixed()) {
            states.append("MIN_FIXED ");
        }
        if (this.isMaxFixed()) {
            states.append("MAX_FIXED ");
        }
        if (this.isMark1()) {
            states.append("MARK1 ");
        }
        if (this.isMark2()) {
            states.append("MARK2 ");
        }
        if (this.isMemBackrefed()) {
            states.append("MEM_BACKREFED ");
        }
        if (this.isStopBtSimpleRepeat()) {
            states.append("STOP_BT_SIMPLE_REPEAT ");
        }
        if (this.isRecursion()) {
            states.append("RECURSION ");
        }
        if (this.isCalled()) {
            states.append("CALLED ");
        }
        if (this.isAddrFixed()) {
            states.append("ADDR_FIXED ");
        }
        if (this.isNamedGroup()) {
            states.append("NAMED_GROUP ");
        }
        if (this.isNameRef()) {
            states.append("NAME_REF ");
        }
        if (this.isInRepeat()) {
            states.append("IN_REPEAT ");
        }
        if (this.isNestLevel()) {
            states.append("NEST_LEVEL ");
        }
        if (this.isByNumber()) {
            states.append("BY_NUMBER ");
        }
        return states.toString();
    }
    
    public boolean isMinFixed() {
        return (this.state & 0x1) != 0x0;
    }
    
    public void setMinFixed() {
        this.state |= 0x1;
    }
    
    public void clearMinFixed() {
        this.state &= 0xFFFFFFFE;
    }
    
    public boolean isMaxFixed() {
        return (this.state & 0x2) != 0x0;
    }
    
    public void setMaxFixed() {
        this.state |= 0x2;
    }
    
    public void clearMaxFixed() {
        this.state &= 0xFFFFFFFD;
    }
    
    public boolean isCLenFixed() {
        return (this.state & 0x4) != 0x0;
    }
    
    public void setCLenFixed() {
        this.state |= 0x4;
    }
    
    public void clearCLenFixed() {
        this.state &= 0xFFFFFFFB;
    }
    
    public boolean isMark1() {
        return (this.state & 0x8) != 0x0;
    }
    
    public void setMark1() {
        this.state |= 0x8;
    }
    
    public void clearMark1() {
        this.state &= 0xFFFFFFF7;
    }
    
    public boolean isMark2() {
        return (this.state & 0x10) != 0x0;
    }
    
    public void setMark2() {
        this.state |= 0x10;
    }
    
    public void clearMark2() {
        this.state &= 0xFFFFFFEF;
    }
    
    public boolean isMemBackrefed() {
        return (this.state & 0x20) != 0x0;
    }
    
    public void setMemBackrefed() {
        this.state |= 0x20;
    }
    
    public void clearMemBackrefed() {
        this.state &= 0xFFFFFFDF;
    }
    
    public boolean isStopBtSimpleRepeat() {
        return (this.state & 0x40) != 0x0;
    }
    
    public void setStopBtSimpleRepeat() {
        this.state |= 0x40;
    }
    
    public void clearStopBtSimpleRepeat() {
        this.state &= 0xFFFFFFBF;
    }
    
    public boolean isRecursion() {
        return (this.state & 0x80) != 0x0;
    }
    
    public void setRecursion() {
        this.state |= 0x80;
    }
    
    public void clearRecursion() {
        this.state &= 0xFFFFFF7F;
    }
    
    public boolean isCalled() {
        return (this.state & 0x100) != 0x0;
    }
    
    public void setCalled() {
        this.state |= 0x100;
    }
    
    public void clearCAlled() {
        this.state &= 0xFFFFFEFF;
    }
    
    public boolean isAddrFixed() {
        return (this.state & 0x200) != 0x0;
    }
    
    public void setAddrFixed() {
        this.state |= 0x200;
    }
    
    public void clearAddrFixed() {
        this.state &= 0xFFFFFDFF;
    }
    
    public boolean isNamedGroup() {
        return (this.state & 0x400) != 0x0;
    }
    
    public void setNamedGroup() {
        this.state |= 0x400;
    }
    
    public void clearNamedGroup() {
        this.state &= 0xFFFFFBFF;
    }
    
    public boolean isNameRef() {
        return (this.state & 0x800) != 0x0;
    }
    
    public void setNameRef() {
        this.state |= 0x800;
    }
    
    public void clearNameRef() {
        this.state &= 0xFFFFF7FF;
    }
    
    public boolean isInRepeat() {
        return (this.state & 0x1000) != 0x0;
    }
    
    public void setInRepeat() {
        this.state |= 0x1000;
    }
    
    public void clearInRepeat() {
        this.state &= 0xFFFFEFFF;
    }
    
    public boolean isNestLevel() {
        return (this.state & 0x2000) != 0x0;
    }
    
    public void setNestLevel() {
        this.state |= 0x2000;
    }
    
    public void clearNestLevel() {
        this.state &= 0xFFFFDFFF;
    }
    
    public boolean isByNumber() {
        return (this.state & 0x4000) != 0x0;
    }
    
    public void setByNumber() {
        this.state |= 0x4000;
    }
    
    public void clearByNumber() {
        this.state &= 0xFFFFBFFF;
    }
}
