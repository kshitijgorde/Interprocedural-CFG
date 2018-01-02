// 
// Decompiled by Procyon v0.5.30
// 

class ZMachine8 extends ZMachine5
{
    public ZMachine8(final ZScreen screen, final byte[] memory_image) {
        super(screen, memory_image);
    }
    
    int string_address(final short addr) {
        return (addr & 0xFFFF) << 3;
    }
    
    int routine_address(final short addr) {
        return (addr & 0xFFFF) << 3;
    }
}
