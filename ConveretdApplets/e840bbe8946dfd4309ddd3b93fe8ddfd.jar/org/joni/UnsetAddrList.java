// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.exception.InternalException;
import org.joni.ast.EncloseNode;
import org.joni.ast.Node;

public final class UnsetAddrList
{
    int num;
    Node[] targets;
    int[] offsets;
    
    public UnsetAddrList(final int size) {
        this.targets = new Node[size];
        this.offsets = new int[size];
    }
    
    public void add(final int offset, final Node node) {
        if (this.num >= this.offsets.length) {
            final Node[] ttmp = new Node[this.targets.length << 1];
            System.arraycopy(this.targets, 0, ttmp, 0, this.num);
            this.targets = ttmp;
            final int[] otmp = new int[this.offsets.length << 1];
            System.arraycopy(this.offsets, 0, otmp, 0, this.num);
            this.offsets = otmp;
        }
        this.targets[this.num] = node;
        this.offsets[this.num] = offset;
        ++this.num;
    }
    
    public void fix(final Regex regex) {
        for (int i = 0; i < this.num; ++i) {
            final EncloseNode en = (EncloseNode)this.targets[i];
            if (!en.isAddrFixed()) {
                new InternalException("internal parser error (bug)");
            }
            regex.code[this.offsets[i]] = en.callAddr;
        }
    }
    
    public String toString() {
        final StringBuilder value = new StringBuilder();
        if (this.num > 0) {
            for (int i = 0; i < this.num; ++i) {
                value.append("offset + " + this.offsets[i] + " target: " + this.targets[i].getAddressName());
            }
        }
        return value.toString();
    }
}
