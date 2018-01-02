// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.WarnCallback;
import java.util.Set;
import org.joni.UnsetAddrList;

public final class CallNode extends StateNode
{
    public byte[] name;
    public int nameP;
    public int nameEnd;
    public int groupNum;
    public Node target;
    public UnsetAddrList unsetAddrList;
    
    public CallNode(final byte[] name, final int nameP, final int nameEnd, final int gnum) {
        this.name = name;
        this.nameP = nameP;
        this.nameEnd = nameEnd;
        this.groupNum = gnum;
    }
    
    public int getType() {
        return 10;
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
        return "Call";
    }
    
    public void verifyTree(final Set<Node> set, final WarnCallback warnings) {
        if (this.target == null || this.target.parent == this) {
            warnings.warn(this.getAddressName() + " doesn't point to a target or the target has been stolen");
        }
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  name: " + new String(this.name, this.nameP, this.nameEnd - this.nameP));
        value.append("\n  groupNum: " + this.groupNum);
        value.append("\n  target: " + Node.pad(this.target.getAddressName(), level + 1));
        value.append("\n  unsetAddrList: " + Node.pad(this.unsetAddrList, level + 1));
        return value.toString();
    }
}
