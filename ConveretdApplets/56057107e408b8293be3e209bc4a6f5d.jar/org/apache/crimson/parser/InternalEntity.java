// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

class InternalEntity extends EntityDecl
{
    char[] buf;
    
    InternalEntity(final String name, final char[] value) {
        super.name = name;
        this.buf = value;
    }
}
