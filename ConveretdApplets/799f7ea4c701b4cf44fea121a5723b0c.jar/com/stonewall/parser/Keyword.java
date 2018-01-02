// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

public class Keyword
{
    String name;
    short skip;
    short offset;
    short limit;
    
    Keyword(final String name, final short skip, final short offset, final short limit) {
        this.skip = 0;
        this.offset = 1;
        this.limit = 1;
        this.name = name;
        this.skip = skip;
        this.offset = offset;
        this.limit = limit;
    }
    
    Keyword(final String name, final String skip, final String offset, final String limit) {
        this(name, Short.valueOf(skip), Short.valueOf(offset), Short.valueOf(limit));
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.name) + ":skip=" + this.skip + ":offset=" + this.offset;
    }
}
