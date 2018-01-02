// 
// Decompiled by Procyon v0.5.30
// 

class ZObjectTree5 extends ZObjectTree
{
    ZObjectTree5(final ZMachine zm) {
        super(zm);
    }
    
    protected int ptableoffset() {
        return 12;
    }
    
    protected int getentryloc(final short object) {
        return super.object_tree + ((object & 0xFFFF) - 1) * 14;
    }
    
    protected int num_properties() {
        return 63;
    }
    
    short parent(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 6] << 8 | (super.zm.memory_image[entryloc + 7] & 0xFF));
    }
    
    short sibling(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 8] << 8 | (super.zm.memory_image[entryloc + 9] & 0xFF));
    }
    
    short child(final short object) {
        final int entryloc = this.getentryloc(object);
        return (short)(super.zm.memory_image[entryloc + 10] << 8 | (super.zm.memory_image[entryloc + 11] & 0xFF));
    }
    
    void set_parent(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 6] = (byte)(newparent >> 8 & 0xFF);
        super.zm.memory_image[entryloc + 7] = (byte)(newparent & 0xFF);
    }
    
    void set_sibling(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 8] = (byte)(newparent >> 8 & 0xFF);
        super.zm.memory_image[entryloc + 9] = (byte)(newparent & 0xFF);
    }
    
    void set_child(final short object, final short newparent) {
        final int entryloc = this.getentryloc(object);
        super.zm.memory_image[entryloc + 10] = (byte)(newparent >> 8 & 0xFF);
        super.zm.memory_image[entryloc + 11] = (byte)(newparent & 0xFF);
    }
    
    int prop_entry_address(final short object, final short propnum) {
        int entry_address = this.property_table_addr(object);
        entry_address += (super.zm.memory_image[entry_address] & 0xFF) * 2 + 1;
        for (int sizebyte = super.zm.memory_image[entry_address] & 0xFF; sizebyte != 0; sizebyte = (super.zm.memory_image[entry_address] & 0xFF)) {
            final int curpropnum = sizebyte & 0x3F;
            int length;
            if ((sizebyte & 0x80) == 0x80) {
                length = (super.zm.memory_image[entry_address + 1] & 0x3F) + 2;
            }
            else {
                length = (sizebyte >> 6) + 2;
            }
            if (curpropnum == propnum) {
                return entry_address;
            }
            if (curpropnum < propnum) {
                return 0;
            }
            entry_address += length;
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
            final int sizebyte = super.zm.memory_image[entry_address] & 0xFF;
            int length;
            if ((sizebyte & 0x80) == 0x80) {
                length = (super.zm.memory_image[entry_address + 1] & 0x3F) + 2;
            }
            else {
                length = (sizebyte >> 6) + 2;
            }
            entry_address += length;
        }
        final int sizebyte = super.zm.memory_image[entry_address] & 0xFF;
        return (short)(sizebyte & 0x3F);
    }
    
    short prop_address(final short object, final short propnum) {
        final int entry_address = this.prop_entry_address(object, propnum);
        if (entry_address == 0) {
            return 0;
        }
        if ((super.zm.memory_image[entry_address] & 0x80) == 0x80) {
            return (short)(entry_address + 2);
        }
        return (short)(entry_address + 1);
    }
    
    short prop_len(final short prop_address) {
        if (prop_address == 0) {
            super.zm.fatal("Tried to find length of missing property");
            return -1;
        }
        final int sizebyte = super.zm.memory_image[(prop_address & 0xFFFF) - 1] & 0xFF;
        if ((sizebyte & 0x80) == 0x80) {
            return (short)(sizebyte & 0x3F);
        }
        return (short)((sizebyte >> 6) + 1);
    }
}
