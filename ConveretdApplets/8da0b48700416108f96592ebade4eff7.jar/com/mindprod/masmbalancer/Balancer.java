// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masmbalancer;

public enum Balancer
{
    ALL("balance everything"), 
    SEGMENT("SEGMENT / struct / ends"), 
    MACRO("MACRO / endm"), 
    PROC("PROC / endp"), 
    IF("IF / else / endif");
    
    private final String desc;
    
    public String toString() {
        return this.desc;
    }
    
    private Balancer(final String desc) {
        this.desc = desc;
    }
}
