// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine5;

import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.zmachine.ZObjectTree;

public class ZObjectTree5 extends ZObjectTree
{
    public ZObjectTree5(final ZMachine zMachine) {
        super(zMachine);
    }
    
    protected int ptableoffset() {
        return 12;
    }
    
    protected int getentryloc(final short n) {
        return super.object_tree + ((n & 0xFFFF) - 1) * 14;
    }
    
    protected int num_properties() {
        return 63;
    }
    
    public short parent(final short n) {
        final int getentryloc = this.getentryloc(n);
        return (short)(super.zm.memory_image[getentryloc + 6] << 8 | (super.zm.memory_image[getentryloc + 7] & 0xFF));
    }
    
    public short sibling(final short n) {
        final int getentryloc = this.getentryloc(n);
        return (short)(super.zm.memory_image[getentryloc + 8] << 8 | (super.zm.memory_image[getentryloc + 9] & 0xFF));
    }
    
    public short child(final short n) {
        final int getentryloc = this.getentryloc(n);
        return (short)(super.zm.memory_image[getentryloc + 10] << 8 | (super.zm.memory_image[getentryloc + 11] & 0xFF));
    }
    
    public void set_parent(final short n, final short n2) {
        final int getentryloc = this.getentryloc(n);
        super.zm.memory_image[getentryloc + 6] = (byte)(n2 >> 8 & 0xFF);
        super.zm.memory_image[getentryloc + 7] = (byte)(n2 & 0xFF);
    }
    
    public void set_sibling(final short n, final short n2) {
        final int getentryloc = this.getentryloc(n);
        super.zm.memory_image[getentryloc + 8] = (byte)(n2 >> 8 & 0xFF);
        super.zm.memory_image[getentryloc + 9] = (byte)(n2 & 0xFF);
    }
    
    public void set_child(final short n, final short n2) {
        final int getentryloc = this.getentryloc(n);
        super.zm.memory_image[getentryloc + 10] = (byte)(n2 >> 8 & 0xFF);
        super.zm.memory_image[getentryloc + 11] = (byte)(n2 & 0xFF);
    }
    
    public int prop_entry_address(final short n, final short n2) {
        final int property_table_addr = this.property_table_addr(n);
        for (int n3 = property_table_addr + ((super.zm.memory_image[property_table_addr] & 0xFF) * 2 + 1), i = super.zm.memory_image[n3] & 0xFF; i != 0; i = (super.zm.memory_image[n3] & 0xFF)) {
            final int n4 = i & 0x3F;
            int n5;
            if ((i & 0x80) == 0x80) {
                n5 = (super.zm.memory_image[n3 + 1] & 0x3F) + 2;
            }
            else {
                n5 = (i >> 6) + 2;
            }
            if (n4 == n2) {
                return n3;
            }
            if (n4 < n2) {
                return 0;
            }
            n3 += n5;
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
            final int n4 = super.zm.memory_image[prop_entry_address] & 0xFF;
            int n5;
            if ((n4 & 0x80) == 0x80) {
                n5 = (super.zm.memory_image[prop_entry_address + 1] & 0x3F) + 2;
            }
            else {
                n5 = (n4 >> 6) + 2;
            }
            n3 = prop_entry_address + n5;
        }
        return (short)(super.zm.memory_image[n3] & 0xFF & 0x3F);
    }
    
    public short prop_address(final short n, final short n2) {
        final int prop_entry_address = this.prop_entry_address(n, n2);
        if (prop_entry_address == 0) {
            return 0;
        }
        if ((super.zm.memory_image[prop_entry_address] & 0x80) == 0x80) {
            return (short)(prop_entry_address + 2);
        }
        return (short)(prop_entry_address + 1);
    }
    
    public short prop_len(final short n) {
        if (n == 0) {
            super.zm.fatal("Tried to find length of missing property");
            return -1;
        }
        final int n2 = super.zm.memory_image[(n & 0xFFFF) - 1] & 0xFF;
        if ((n2 & 0x80) == 0x80) {
            return (short)(n2 & 0x3F);
        }
        return (short)((n2 >> 6) + 1);
    }
}
