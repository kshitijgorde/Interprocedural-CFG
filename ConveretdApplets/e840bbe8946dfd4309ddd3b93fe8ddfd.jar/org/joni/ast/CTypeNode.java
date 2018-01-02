// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

public final class CTypeNode extends Node
{
    public int ctype;
    public boolean not;
    
    public CTypeNode(final int type, final boolean not) {
        this.ctype = type;
        this.not = not;
    }
    
    public int getType() {
        return 2;
    }
    
    public String getName() {
        return "Character Type";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder();
        value.append("\n  ctype: " + this.ctype);
        value.append("\n  not: " + this.not);
        return value.toString();
    }
}
