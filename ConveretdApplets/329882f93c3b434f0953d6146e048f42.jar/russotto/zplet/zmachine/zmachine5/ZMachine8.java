// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine5;

import russotto.zplet.screenmodel.ZScreen;

public class ZMachine8 extends ZMachine5
{
    public ZMachine8(final ZScreen zScreen, final byte[] array) {
        super(zScreen, array);
    }
    
    public int string_address(final short n) {
        return (n & 0xFFFF) << 3;
    }
    
    public int routine_address(final short n) {
        return (n & 0xFFFF) << 3;
    }
}
