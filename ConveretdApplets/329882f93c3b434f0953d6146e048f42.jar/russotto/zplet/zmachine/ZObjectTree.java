// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine;

public abstract class ZObjectTree
{
    protected ZMachine zm;
    protected int object_table;
    protected int object_tree;
    
    public ZObjectTree(final ZMachine zm) {
        this.zm = zm;
        this.object_table = zm.header.object_table();
        this.object_tree = this.object_table + this.num_properties() * 2;
    }
    
    public short default_property(final short n) {
        final short n2 = (short)(n - 1);
        return (short)((this.zm.memory_image[this.object_table + n2 * 2] << 8 & 0xFF00) | (this.zm.memory_image[this.object_table + n2 * 2 + 1] & 0xFF));
    }
    
    protected abstract int num_properties();
    
    protected abstract int ptableoffset();
    
    protected abstract int getentryloc(final short p0);
    
    public boolean attribute(final short n, final short n2) {
        return (this.zm.memory_image[this.getentryloc(n) + (n2 >> 3)] & 1 << 7 - (n2 & 0x7)) != 0x0;
    }
    
    public void set_attribute(final short n, final short n2) {
        final int getentryloc = this.getentryloc(n);
        final int n3 = n2 >> 3;
        final int n4 = 1 << 7 - (n2 & 0x7);
        final byte[] memory_image = this.zm.memory_image;
        final int n5 = getentryloc + n3;
        memory_image[n5] |= (byte)n4;
    }
    
    public void clear_attribute(final short n, final short n2) {
        final int getentryloc = this.getentryloc(n);
        final int n3 = n2 >> 3;
        final int n4 = 1 << 7 - (n2 & 0x7);
        final byte[] memory_image = this.zm.memory_image;
        final int n5 = getentryloc + n3;
        memory_image[n5] &= (byte)(n4 ^ 0xFF);
    }
    
    public abstract short parent(final short p0);
    
    public abstract short child(final short p0);
    
    public abstract short sibling(final short p0);
    
    public abstract void set_parent(final short p0, final short p1);
    
    public abstract void set_sibling(final short p0, final short p1);
    
    public abstract void set_child(final short p0, final short p1);
    
    public int property_table_addr(final short n) {
        final int getentryloc = this.getentryloc(n);
        return (this.zm.memory_image[getentryloc + this.ptableoffset()] << 8 & 0xFF00) | (this.zm.memory_image[getentryloc + this.ptableoffset() + 1] & 0xFF);
    }
    
    public int short_name_addr(final short n) {
        return this.property_table_addr(n) + 1;
    }
    
    public abstract int prop_entry_address(final short p0, final short p1);
    
    public abstract short next_prop(final short p0, final short p1);
    
    public abstract short prop_address(final short p0, final short p1);
    
    public abstract short prop_len(final short p0);
    
    public short prop(final short n, final short n2) {
        final int prop_entry_address = this.prop_entry_address(n, n2);
        if (prop_entry_address == 0) {
            return this.default_property(n2);
        }
        if ((this.zm.memory_image[prop_entry_address] >>> 5) + 1 == 1) {
            return (short)(this.zm.memory_image[prop_entry_address + 1] & 0xFF);
        }
        return (short)((this.zm.memory_image[prop_entry_address + 1] << 8 & 0xFF00) | (this.zm.memory_image[prop_entry_address + 2] & 0xFF));
    }
    
    public void put_prop(final short n, final short n2, final short n3) {
        final int prop_entry_address = this.prop_entry_address(n, n2);
        if (prop_entry_address == 0) {
            this.zm.fatal("Tried to set nonexistent property");
        }
        else if ((this.zm.memory_image[prop_entry_address] >>> 5) + 1 == 1) {
            this.zm.memory_image[prop_entry_address + 1] = (byte)(n3 & 0xFF);
        }
        else {
            this.zm.memory_image[prop_entry_address + 1] = (byte)(n3 >>> 8);
            this.zm.memory_image[prop_entry_address + 2] = (byte)(n3 & 0xFF);
        }
    }
}
