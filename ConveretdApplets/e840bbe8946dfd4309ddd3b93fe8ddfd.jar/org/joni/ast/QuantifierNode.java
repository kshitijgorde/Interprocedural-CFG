// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.ScanEnvironment;
import org.joni.constants.Reduce;

public final class QuantifierNode extends StateNode
{
    public Node target;
    public int lower;
    public int upper;
    public boolean greedy;
    public int targetEmptyInfo;
    public Node headExact;
    public Node nextHeadExact;
    public boolean isRefered;
    public int combExpCheckNum;
    public static final int REPEAT_INFINITE = -1;
    
    public QuantifierNode(final int lower, final int upper, final boolean byNumber) {
        this.lower = lower;
        this.upper = upper;
        this.greedy = true;
        this.targetEmptyInfo = 0;
        if (byNumber) {
            this.setByNumber();
        }
    }
    
    public int getType() {
        return 5;
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
    
    public StringNode convertToString() {
        final StringNode sn = new StringNode();
        sn.flag = ((StringNode)this.target).flag;
        sn.swap(this);
        return sn;
    }
    
    public String getName() {
        return "Quantifier";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  target: " + Node.pad(this.target, level + 1));
        value.append("\n  lower: " + this.lower);
        value.append("\n  upper: " + this.upper);
        value.append("\n  greedy: " + this.greedy);
        value.append("\n  targetEmptyInfo: " + this.targetEmptyInfo);
        value.append("\n  headExact: " + Node.pad(this.headExact, level + 1));
        value.append("\n  nextHeadExact: " + Node.pad(this.nextHeadExact, level + 1));
        value.append("\n  isRefered: " + this.isRefered);
        value.append("\n  combExpCheckNum: " + this.combExpCheckNum);
        return value.toString();
    }
    
    public boolean isAnyCharStar() {
        return this.greedy && isRepeatInfinite(this.upper) && this.target.getType() == 3;
    }
    
    protected int popularNum() {
        if (this.greedy) {
            if (this.lower == 0) {
                if (this.upper == 1) {
                    return 0;
                }
                if (isRepeatInfinite(this.upper)) {
                    return 1;
                }
            }
            else if (this.lower == 1 && isRepeatInfinite(this.upper)) {
                return 2;
            }
        }
        else if (this.lower == 0) {
            if (this.upper == 1) {
                return 3;
            }
            if (isRepeatInfinite(this.upper)) {
                return 4;
            }
        }
        else if (this.lower == 1 && isRepeatInfinite(this.upper)) {
            return 5;
        }
        return -1;
    }
    
    protected void set(final QuantifierNode other) {
        this.setTarget(other.target);
        other.target = null;
        this.lower = other.lower;
        this.upper = other.upper;
        this.greedy = other.greedy;
        this.targetEmptyInfo = other.targetEmptyInfo;
        this.headExact = other.headExact;
        this.nextHeadExact = other.nextHeadExact;
        this.isRefered = other.isRefered;
        this.combExpCheckNum = other.combExpCheckNum;
    }
    
    public void reduceNestedQuantifier(final QuantifierNode other) {
        final int pnum = this.popularNum();
        final int cnum = other.popularNum();
        if (pnum < 0 || cnum < 0) {
            return;
        }
        switch (Reduce.REDUCE_TABLE[cnum][pnum]) {
            case DEL: {
                this.set(other);
                break;
            }
            case A: {
                this.setTarget(other.target);
                this.lower = 0;
                this.upper = -1;
                this.greedy = true;
                break;
            }
            case AQ: {
                this.setTarget(other.target);
                this.lower = 0;
                this.upper = -1;
                this.greedy = false;
                break;
            }
            case QQ: {
                this.setTarget(other.target);
                this.lower = 0;
                this.upper = 1;
                this.greedy = false;
                break;
            }
            case P_QQ: {
                this.setTarget(other);
                this.lower = 0;
                this.upper = 1;
                this.greedy = false;
                other.lower = 1;
                other.upper = -1;
                other.greedy = true;
                return;
            }
            case PQ_Q: {
                this.setTarget(other);
                this.lower = 0;
                this.upper = 1;
                this.greedy = true;
                other.lower = 1;
                other.upper = -1;
                other.greedy = false;
                return;
            }
            case ASIS: {
                this.setTarget(other);
                return;
            }
        }
        other.target = null;
    }
    
    public int setQuantifier(final Node tgt, final boolean group, final ScanEnvironment env, final byte[] bytes, final int p, final int end) {
        if (this.lower == 1 && this.upper == 1) {
            return 1;
        }
        switch (tgt.getType()) {
            case 0: {
                if (!group) {
                    final StringNode sn = (StringNode)tgt;
                    if (sn.canBeSplit(env.enc)) {
                        final StringNode n = sn.splitLastChar(env.enc);
                        if (n != null) {
                            this.setTarget(n);
                            return 2;
                        }
                    }
                    break;
                }
                break;
            }
            case 5: {
                final QuantifierNode qnt = (QuantifierNode)tgt;
                final int nestQNum = this.popularNum();
                final int targetQNum = qnt.popularNum();
                if (targetQNum < 0) {
                    break;
                }
                if (nestQNum >= 0) {
                    this.reduceNestedQuantifier(qnt);
                    return 0;
                }
                if ((targetQNum == 1 || targetQNum == 2) && !isRepeatInfinite(this.upper) && this.upper > 1 && this.greedy) {
                    this.upper = ((this.lower == 0) ? 1 : this.lower);
                    break;
                }
                break;
            }
        }
        this.setTarget(tgt);
        return 0;
    }
    
    public static boolean isRepeatInfinite(final int n) {
        return n == -1;
    }
}
