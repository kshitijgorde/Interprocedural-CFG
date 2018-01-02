// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine3;

import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.zmachine.ZObjectTree;

class ZObjectTree3 extends ZObjectTree
{
    public ZObjectTree3(final ZMachine zMachine) {
        super(zMachine);
    }
    
    protected int ptableoffset() {
        return 7;
    }
    
    protected int getentryloc(final short n) {
        return super.object_tree + ((n & 0xFFFF) - 1) * 9;
    }
    
    protected int num_properties() {
        return 31;
    }
    
    public short parent(final short n) {
        return (short)(super.zm.memory_image[this.getentryloc(n) + 4] & 0xFF);
    }
    
    public short sibling(final short n) {
        return (short)(super.zm.memory_image[this.getentryloc(n) + 5] & 0xFF);
    }
    
    public short child(final short n) {
        return (short)(super.zm.memory_image[this.getentryloc(n) + 6] & 0xFF);
    }
    
    public void set_parent(final short n, final short n2) {
        super.zm.memory_image[this.getentryloc(n) + 4] = (byte)n2;
    }
    
    public void set_sibling(final short n, final short n2) {
        super.zm.memory_image[this.getentryloc(n) + 5] = (byte)n2;
    }
    
    public void set_child(final short n, final short n2) {
        super.zm.memory_image[this.getentryloc(n) + 6] = (byte)n2;
    }
    
    public int prop_entry_address(final short n, final short n2) {
        final int property_table_addr = this.property_table_addr(n);
        for (int n3 = property_table_addr + ((super.zm.memory_image[property_table_addr] & 0xFF) * 2 + 1), i = super.zm.memory_image[n3] & 0xFF; i != 0; i = (super.zm.memory_image[n3] & 0xFF)) {
            if ((i & 0x1F) == n2) {
                return n3;
            }
            if ((i & 0x1F) < n2) {
                return 0;
            }
            n3 += (i >> 5) + 2;
        }
        return 0;
    }
    
    public short next_prop(final short n, final short n2) {
        int n3;
        if (n2 == 0) {
            final int property_table_addr = this.property_table_addr(n);
            if (property_table_addr == 0) {
                super.zm.fatal("Tried to get next property for object with no properties");
            }
            n3 = property_table_addr + ((super.zm.memory_image[property_table_addr] & 0xFF) * 2 + 1);
        }
        else {
            final int prop_entry_address = this.prop_entry_address(n, n2);
            if (prop_entry_address == 0) {
                super.zm.fatal("Tried to get next property for nonexistent property");
            }
            n3 = prop_entry_address + (((super.zm.memory_image[prop_entry_address] & 0xFF) >> 5) + 2);
        }
        return (short)(super.zm.memory_image[n3] & 0xFF & 0x1F);
    }
    
    public short prop_address(final short n, final short n2) {
        final int prop_entry_address = this.prop_entry_address(n, n2);
        if (prop_entry_address == 0) {
            return 0;
        }
        return (short)(prop_entry_address + 1);
    }
    
    public short prop_len(final short n) {
        if (n == 0) {
            super.zm.fatal("Tried to find length of missing property");
            return -1;
        }
        return (short)(((super.zm.memory_image[(n & 0xFFFF) - 1] & 0xFF) >> 5) + 1);
    }
}
