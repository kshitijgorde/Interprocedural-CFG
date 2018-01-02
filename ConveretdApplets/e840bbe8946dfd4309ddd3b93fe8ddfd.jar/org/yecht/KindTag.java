// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public enum KindTag
{
    Map, 
    Seq, 
    Str;
    
    public Node allocNode() {
        final Node s = new Node();
        s.kind = this;
        s.id = null;
        return s;
    }
}
