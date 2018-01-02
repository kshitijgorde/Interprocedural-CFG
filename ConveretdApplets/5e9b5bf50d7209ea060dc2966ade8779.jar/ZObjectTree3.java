// 
// Decompiled by Procyon v0.5.30
// 

class ZObjectTree3 extends ZObjectTree
{
    ZObjectTree3(final ZMachine zm) {
        super(zm);
    }
    
    protected int ptableoffset() {
        return 7;
    }
    
    protected int getentryloc(final short object) {
        return super.object_tree + ((object & 0xFFFF) - 1) * 9;
    }
    
    protected int num_properties() {
        return 31;
    }
    
    short parent(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 4] & 0xFF);
    }
    
    short sibling(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 5] & 0xFF);
    }
    
    short child(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 6] & 0xFF);
    }
    
    void set_parent(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 4] = (byte)newparent;
    }
    
    void set_sibling(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 5] = (byte)newparent;
    }
    
    void set_child(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 6] = (byte)newparent;
    }
    
    int prop_entry_address(final short object, final short propnum) {
        int entry_address = this.property_table_addr(object);
        entry_address += (super.zm.memory_image[entry_address] & 0xFF) * 2 + 1;
        for (int sizebyte = super.zm.memory_image[entry_address] & 0xFF; sizebyte != 0; sizebyte = (super.zm.memory_image[entry_address] & 0xFF)) {
            if ((sizebyte & 0x1F) == propnum) {
                return entry_address;
            }
            if ((sizebyte & 0x1F) < propnum) {
                return 0;
            }
            entry_address += (sizebyte >> 5) + 2;
        }
        return 0;
    }
    
    short next_prop(final short object, final short propnum) {
        int entry_address;
        if (propnum == 0) {
            entry_address = this.property_table_addr(object);
            if (entry_address == 0) {
                super.zm.fatal("Tried to get next property for object with no properties");
            }
            entry_address += (super.zm.memory_image[entry_address] & 0xFF) * 2 + 1;
        }
        else {
            entry_address = this.prop_entry_address(object, propnum);
            if (entry_address == 0) {
                super.zm.fatal("Tried to get next property for nonexistent property");
            }
            entry_address += ((super.zm.memory_image[entry_address] & 0xFF) >> 5) + 2;
        }
        final int sizebyte = super.zm.memory_image[entry_address] & 0xFF;
        return (short)(sizebyte & 0x1F);
    }
    
    short prop_address(final short object, final short propnum) {
        final int entry_address = this.prop_entry_address(object, propnum);
        if (entry_address == 0) {
            return 0;
        }
        return (short)(entry_address + 1);
    }
    
    short prop_len(final short prop_address) {
        if (prop_address == 0) {
            super.zm.fatal("Tried to find length of missing property");
            return -1;
        }
        final int sizebyte = super.zm.memory_image[(prop_address & 0xFFFF) - 1] & 0xFF;
        return (short)((sizebyte >> 5) + 1);
    }
}
