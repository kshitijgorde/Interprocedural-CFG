// 
// Decompiled by Procyon v0.5.30
// 

abstract class ZObjectTree
{
    ZMachine zm;
    int object_table;
    int object_tree;
    
    ZObjectTree(final ZMachine zm) {
        this.zm = zm;
        this.object_table = zm.header.object_table();
        this.object_tree = this.object_table + this.num_properties() * 2;
    }
    
    short default_property(short property) {
        --property;
        final short result = (short)((this.zm.memory_image[this.object_table + property * 2] << 8 & 0xFF00) | (this.zm.memory_image[this.object_table + property * 2 + 1] & 0xFF));
        return result;
    }
    
    protected abstract int num_properties();
    
    protected abstract int ptableoffset();
    
    protected abstract int getentryloc(final short p0);
    
    boolean attribute(final short object, final short attr_num) {
        final int entryloc = this.getentryloc(object);
        final int bytenum = attr_num >> 3;
        final int bitmask = 1 << 7 - (attr_num & 0x7);
        return (this.zm.memory_image[entryloc + bytenum] & bitmask) != 0x0;
    }
    
    void set_attribute(final short object, final short attr_num) {
        final int entryloc = this.getentryloc(object);
        final int bytenum = attr_num >> 3;
        final int bitmask = 1 << 7 - (attr_num & 0x7);
        final byte[] memory_image = this.zm.memory_image;
        final int n = entryloc + bytenum;
        memory_image[n] |= (byte)bitmask;
    }
    
    void clear_attribute(final short object, final short attr_num) {
        final int entryloc = this.getentryloc(object);
        final int bytenum = attr_num >> 3;
        final int bitmask = 1 << 7 - (attr_num & 0x7);
        final byte[] memory_image = this.zm.memory_image;
        final int n = entryloc + bytenum;
        memory_image[n] &= (byte)(bitmask ^ 0xFF);
    }
    
    abstract short parent(final short p0);
    
    abstract short child(final short p0);
    
    abstract short sibling(final short p0);
    
    abstract void set_parent(final short p0, final short p1);
    
    abstract void set_sibling(final short p0, final short p1);
    
    abstract void set_child(final short p0, final short p1);
    
    int property_table_addr(final short object) {
        final int entryloc = this.getentryloc(object);
        return (this.zm.memory_image[entryloc + this.ptableoffset()] << 8 & 0xFF00) | (this.zm.memory_image[entryloc + this.ptableoffset() + 1] & 0xFF);
    }
    
    int short_name_addr(final short object) {
        return this.property_table_addr(object) + 1;
    }
    
    abstract int prop_entry_address(final short p0, final short p1);
    
    abstract short next_prop(final short p0, final short p1);
    
    abstract short prop_address(final short p0, final short p1);
    
    abstract short prop_len(final short p0);
    
    short prop(final short object, final short propnum) {
        final int entry_address = this.prop_entry_address(object, propnum);
        if (entry_address == 0) {
            return this.default_property(propnum);
        }
        final int size = (this.zm.memory_image[entry_address] >>> 5) + 1;
        if (size == 1) {
            return (short)(this.zm.memory_image[entry_address + 1] & 0xFF);
        }
        return (short)((this.zm.memory_image[entry_address + 1] << 8 & 0xFF00) | (this.zm.memory_image[entry_address + 2] & 0xFF));
    }
    
    void put_prop(final short object, final short propnum, final short value) {
        final int entry_address = this.prop_entry_address(object, propnum);
        if (entry_address == 0) {
            this.zm.fatal("Tried to set nonexistent property");
        }
        else {
            final int size = (this.zm.memory_image[entry_address] >>> 5) + 1;
            if (size == 1) {
                this.zm.memory_image[entry_address + 1] = (byte)(value & 0xFF);
            }
            else {
                this.zm.memory_image[entry_address + 1] = (byte)(value >>> 8);
                this.zm.memory_image[entry_address + 2] = (byte)(value & 0xFF);
            }
        }
    }
}
