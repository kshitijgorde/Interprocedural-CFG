// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.joni.constants.AnchorType;

public final class AnchorNode extends Node implements AnchorType
{
    public int type;
    public Node target;
    public int charLength;
    
    public AnchorNode(final int type) {
        this.type = type;
        this.charLength = -1;
    }
    
    public int getType() {
        return 7;
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
        return "Anchor";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder();
        value.append("\n  type: " + this.typeToString());
        value.append("\n  target: " + Node.pad(this.target, level + 1));
        return value.toString();
    }
    
    public String typeToString() {
        final StringBuilder type = new StringBuilder();
        if (this.isType(1)) {
            type.append("BEGIN_BUF ");
        }
        if (this.isType(2)) {
            type.append("BEGIN_LINE ");
        }
        if (this.isType(4)) {
            type.append("BEGIN_POSITION ");
        }
        if (this.isType(8)) {
            type.append("END_BUF ");
        }
        if (this.isType(16)) {
            type.append("SEMI_END_BUF ");
        }
        if (this.isType(32)) {
            type.append("END_LINE ");
        }
        if (this.isType(64)) {
            type.append("WORD_BOUND ");
        }
        if (this.isType(128)) {
            type.append("NOT_WORD_BOUND ");
        }
        if (this.isType(256)) {
            type.append("WORD_BEGIN ");
        }
        if (this.isType(512)) {
            type.append("WORD_END ");
        }
        if (this.isType(1024)) {
            type.append("PREC_READ ");
        }
        if (this.isType(2048)) {
            type.append("PREC_READ_NOT ");
        }
        if (this.isType(4096)) {
            type.append("LOOK_BEHIND ");
        }
        if (this.isType(8192)) {
            type.append("LOOK_BEHIND_NOT ");
        }
        if (this.isType(16384)) {
            type.append("ANYCHAR_STAR ");
        }
        if (this.isType(32768)) {
            type.append("ANYCHAR_STAR_ML ");
        }
        return type.toString();
    }
    
    private boolean isType(final int type) {
        return (this.type & type) != 0x0;
    }
}
