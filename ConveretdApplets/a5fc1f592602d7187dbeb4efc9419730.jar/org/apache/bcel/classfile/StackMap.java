// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class StackMap extends Attribute implements Node
{
    private int map_length;
    private StackMapEntry[] map;
    
    public StackMap(final int name_index, final int length, final StackMapEntry[] map, final ConstantPool constant_pool) {
        super((byte)11, name_index, length, constant_pool);
        this.setStackMap(map);
    }
    
    StackMap(final int name_index, final int length, final DataInputStream file, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (StackMapEntry[])null, constant_pool);
        this.map_length = file.readUnsignedShort();
        this.map = new StackMapEntry[this.map_length];
        for (int i = 0; i < this.map_length; ++i) {
            this.map[i] = new StackMapEntry(file, constant_pool);
        }
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(this.map_length);
        for (int i = 0; i < this.map_length; ++i) {
            this.map[i].dump(file);
        }
    }
    
    public final StackMapEntry[] getStackMap() {
        return this.map;
    }
    
    public final void setStackMap(final StackMapEntry[] map) {
        this.map = map;
        this.map_length = ((map == null) ? 0 : map.length);
    }
    
    public final String toString() {
        final StringBuffer buf = new StringBuffer("StackMap(");
        for (int i = 0; i < this.map_length; ++i) {
            buf.append(this.map[i].toString());
            if (i < this.map_length - 1) {
                buf.append(", ");
            }
        }
        buf.append(')');
        return buf.toString();
    }
    
    public Attribute copy(final ConstantPool constant_pool) {
        final StackMap c = (StackMap)this.clone();
        c.map = new StackMapEntry[this.map_length];
        for (int i = 0; i < this.map_length; ++i) {
            c.map[i] = this.map[i].copy();
        }
        c.constant_pool = constant_pool;
        return c;
    }
    
    public void accept(final Visitor v) {
        v.visitStackMap(this);
    }
    
    public final int getMapLength() {
        return this.map_length;
    }
}
